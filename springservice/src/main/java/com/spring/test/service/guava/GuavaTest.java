package com.spring.test.service.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.SocketUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.naming.Name;
import javax.swing.plaf.TableHeaderUI;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:36 2018/5/29
 */
public class GuavaTest {
    private static volatile int a=0;

    private static Logger logger = LogManager.getLogger("AsyncFileLogger");
    public static ThreadLocal<Cmd> threadLocal=new ThreadLocal<>();

    public static  ListeningExecutorService backgroundRefreshPools =
            MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20, new ThreadFactory() {
                private AtomicInteger atomicInteger=new AtomicInteger(1);
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread=new Thread(r);
                    thread.setName("LoadCache-async-Thread"+atomicInteger.getAndIncrement());
                    return thread;
                }
            }));

    //初始容量
    //最大缓存数据量
    //并添加10秒钟过期的配置
    //过期移除的监听器
    //当过期去做load
    private static LoadingCache<String,Object> loadingCache = CacheBuilder.newBuilder().initialCapacity(3).maximumSize(5).refreshAfterWrite(10,TimeUnit.SECONDS).removalListener(new RemovalListener<String, Object>() { //设置监听事件，就是在 删除key的时候触发这个事件

        //当key到期，是不会被移除，只有在有线程访问，才会删除，并同步触发该通知
        @Override
        public void onRemoval(RemovalNotification<String, Object> notification) {
            String value = (String) notification.getValue();
            String key = notification.getKey();
            System.out.println("线程：【"+Thread.currentThread().getName()+"】key:"+key +"  ,value:"+value +"到期删除");
            RemovalCause cause = notification.getCause();

        }
    }).build(new CacheLoader<String,Object>() {
        //加载
        //多线程同时读取已失效的key的时候，只会有一个线程去load，其他线程阻塞等待，防止缓存穿透
        @Override
        public Object load(String key) throws Exception {
            Cmd cmd=GuavaTest.threadLocal.get();
           return  cmd.execue(key);
        }

        @Override
        public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
            final Cmd cmd=GuavaTest.threadLocal.get();
            final String   key1=key;
            return backgroundRefreshPools.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    return cmd.execue(key1);
                }
            });
        }
    });





    private static Cache<String,String> macahe=CacheBuilder.newBuilder().initialCapacity(3).maximumSize(5).expireAfterWrite(10,TimeUnit.SECONDS).removalListener(new RemovalListener<String, String>() { //设置监听事件，就是在 删除key的时候触发这个事件

        //当key到期，是不会被移除，只有在有线程访问，才会删除，并同步触发该通知
        @Override
        public void onRemoval(RemovalNotification<String, String> notification) {
            String value = notification.getValue();
            String key = notification.getKey();
            System.out.println("线程：【"+Thread.currentThread().getName()+"】key:"+key +"  ,value:"+value +"到期删除");
            RemovalCause cause = notification.getCause();

        }
    }).build();


   // private static LoadingCache<String,String> loadingCache2=CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000);

    public static void setKey(String key,String val){
        loadingCache.put(key,val);

    }

    public static long getSize(){
        return loadingCache.size();
    }

    public  static String getKey(String key){
        GuavaTest.threadLocal.set( new Cmd<Object,String>(){
            @Override
            public Object execue(String s) throws Exception {
                a++;
                if(a<3){
                    Exception e = new RuntimeException("数据库加载异常");
                    logger.error("异步数据加载异常【"+a+"】", e);
                    throw e;
                }else{
                    return null;
                }

            }
        });
        Object val=null;
        try {
            val = loadingCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String vaule= (String) val;


        return vaule;
    }







    public static String getMaulKey(final String key) throws ExecutionException {
     String a= macahe.get(key, new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName()+"加载key="+key);
                Thread.sleep(30*1000);
                String value="加载的成功";
                System.out.println(Thread.currentThread().getName()+"加载key="+key+"结束");
                throw  new Exception("读取数据库异常");
               // return value;
            }
        });
     return a;
    }

    public  static  void setMaulKey(String key,String value){
        macahe.put(key,value);
    }


    public static void test02() throws ExecutionException {
        setMaulKey("name","1234");
        setMaulKey("xyz","789");

        String value=getMaulKey("name");
        System.out.println(Thread.currentThread().getName()+"得到的value为："+value);
        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始运行");
                try {
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"开始准备获取");
                String vaue="";
                try {
                   vaue= getMaulKey("name");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread().getName()+"加载异常");
                }
                System.out.println(Thread.currentThread().getName()+"获取成功，value="+vaue);
            }
        });
        thread.start();
        System.out.println(Thread.currentThread().getName()+"开始准备获取");
        String value2=getMaulKey("name");
        System.out.println(Thread.currentThread().getName()+"获取成功value:"+value2);

    }



    public static void test01(){
        setKey("1","1个");
        setKey("2","2个");
        setKey("3","3个");
        setKey("4","4个");
        setKey("5","5个");
        setKey("6","6个");
        setKey("name","TSD");
        System.out.println("放入两个数值loadCache size:"+getSize());

        System.out.println("线程【"+Thread.currentThread().getName()+"】设置成功");
        System.out.println("线程【"+Thread.currentThread().getName()+"】读取key:"+1+"的value:"+getKey("1"));
        try {
            Thread.sleep(20*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //开启一个线程读取，证明多线程同时读取一个过期的缓存key，只会有一个线程去load
        Thread thread=new Thread(new Runnable() {


            @Override
            public void run() {
                System.out.println("线程【"+Thread.currentThread().getName()+"】开始运行");
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程【"+Thread.currentThread().getName()+"】时间："+new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS").format(new Date())+" 准备读取key");
                String value=GuavaTest.getKey("name");
                System.out.println("线程【"+Thread.currentThread().getName()+"】时间："+new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS").format(new Date())+"  准备读取key的结果为"+value);
            }
        });
        thread.start();

        System.out.println("线程【"+Thread.currentThread().getName()+"】时间："+new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS").format(new Date())+"  准备读取");
        String v=getKey("name");
        System.out.println("线程【"+Thread.currentThread().getName()+"】时间："+new SimpleDateFormat("YYYY-MM-dd HH:mm:ss SSS").format(new Date())+" 在20秒后得到的名字为"+v);
//        System.out.println("读取name的value:"+getKey("1"));
//        System.out.println("所有的key都过期后localCacheSize:"+getSize());
//        System.out.println("所有的key都过期后localCacheSize:"+getSize());
//        System.out.println("读取第二个key的数值开始");
//        String name=getKey("第二个key");
//        System.out.println("读取第二个key的数值结束："+name);
//        System.out.println("读取一个没有存储的key，ddd开始");
//        String ddd=getKey("ddd");
//        System.out.println("读取ddd的结果为:"+ddd);
//        System.out.println("缓存大小为："+getSize());
        CacheLoader cacheLoader;
        ListeningExecutorService backgroundRefreshPools =
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));
    }


    public static void testStack(){
        Stack<Integer> stack=new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        int a=stack.pop();
        System.out.println("第一次读栈==>"+a);
        int b=stack.pop();
        System.out.println("第二次读栈==>"+b);
        int c=stack.pop();
        System.out.println("第三次读栈==>"+c);
        boolean flag=stack.isEmpty();
        if(flag){
            System.out.println("第四尝试读栈，但栈已是空的");
            return;
        }
        int d=stack.pop();
        System.out.println("第四次读栈==>"+d);
    }



    public static void testLoacdException(){
        loadingCache.put("names","尚晓飞");
       String value= getKey("names");
        logger.info("第一次正常访问==》"+value);
        System.out.println("第一次正常访问===>"+value);
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value2=getKey("names");
        logger.info("第二次访问异常访问==》"+value2);
        System.out.println("第二次访问异常访问==》"+value2);
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String value3=getKey("names");
        logger.info("第三次访问异常访问==》"+value3);
        System.out.println("第三次访问异常访问==》"+value3);
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String value4=getKey("names");
        logger.info("第四次访问异常访问==》"+value4);
        System.out.println("第四次访问异常访问==》"+value4);

        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String value5=getKey("names");
        logger.info("第五次访问异常访问==》"+value5);
        System.out.println("第五次访问异常访问==》"+value5);
    }
    public static void main(String[] args) throws ExecutionException {
        testLoacdException();
        //testStack();
       // test01();
        //test02();
    }


}
