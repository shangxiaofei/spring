package com.spring.test.service.commons.BeanCopier;

import com.spring.test.service.commons.beansutils.Account;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:26 2018/6/4
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        CacheValue cacheValue=new CacheValue();
        List<String> b=new ArrayList<String>();
        b.add("sxf") ;
        cacheValue.setResult(b);
        cacheValue.setHaveThrowable(false);


        Object object= cacheValue.getResult();

        Class cls=object.getClass();
        System.out.println("typeClass=【"+cls+"】");
        if(ArrayList.class.isAssignableFrom(cls)){
            List list= (List) object;
            for(int i=0;i<list.size();i++){
                Object object1=list.get(i);
                Class cla=object1.getClass();
                System.out.println("元素的类型为=>"+cla);
            }
        }

    }




    public static void test02(){
        List<String> stringList=new ArrayList<>();
        Object object=stringList;

        Class cls=object.getClass();
        System.out.println("cls类型=>"+cls);
        if(List.class.isAssignableFrom(cls)){
            System.out.println("当前对象是List的实现");
        }else if(Set.class.isAssignableFrom(cls)){
            System.out.println("当前对象是Set的实现");
        }else if(Map.class.isAssignableFrom(cls)){
            System.out.println("当前对象是Map的实现");
        }else {
            System.out.println("非常用集合");
        }

        Type[] tpe= cls.getGenericInterfaces();
        System.out.println(getSuperClassGenricType(cls,0));
    }
    public static Class<Object> getSuperClassGenricType(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

//        if (!(genType instanceof ParameterizedType)) {
//            return Object.class;
//        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class) params[index];
    }


    public static void test01() throws IllegalAccessException, InstantiationException {
        Account account=new Account();
        account.setId(123);
        account.setName("天天向上");
        account.setStartDate(new Date());
        account.setMoney(101);

        Object o=account;
        Class cls=o.getClass();
        Object t=cls.newInstance();
        Account target= (Account) t;
        BeanCopierUtils.getInstance().copy(o,t);
        System.out.println("copy后的名字=>"+target.getName());
        System.out.println("copy后的id=>"+target.getId());
        System.out.println("copy后的金额=>"+target.getMoney());
        System.out.println("copy后的日期=>"+target.getStartDate());
    }
}
