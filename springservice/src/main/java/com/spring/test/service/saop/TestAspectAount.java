package com.spring.test.service.saop;

import com.alibaba.fastjson.JSONObject;
import com.spring.test.service.guava.annotations.Cache;
import com.spring.test.service.guava.enums.CacheLeveEnum;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午3:51 2018/6/26
 */
@Aspect
@Component("testAspectArount")
public class TestAspectAount {
    private static final String NO_PARA_PREFIX="NO_PARA";
    private static final String HAVE_PARA_PREFIX="HAVE_PARA";
    private static final String SEPARATOR="-";


    public void test() throws Exception {
        throw  new Exception();
    }


    @Around("@annotation(cache)")
    public Object doInvoker(ProceedingJoinPoint pjd, Cache cache) throws Throwable {
        System.out.println("=========代理开始执行=============");
        Object obj=pjd.getTarget();
        Object[] agr=pjd.getArgs();
        System.out.println("被代理的类为==>"+obj.getClass());
        for(int i=0;i<agr.length;i++){
            System.out.println("被代理的参数==>"+agr[i]);
        }
        if(cache==null){
            System.out.println("该方法没有注解");
        }else{
            CacheLeveEnum[] cacheLeveEnum=cache.cacheLeves();

            System.out.println("注解的等级为==>"+cacheLeveEnum[0].name());
        }
        Method method=computeMethod(pjd);
        System.out.println("执行的method==>"+method.getName());

        Object[] arges=pjd.getArgs();
        String argeStrs=parseArguments(arges);
        System.out.println("参数列表key==>"+argeStrs);

        return pjd.proceed();
    }

    private String parseArguments(Object[] arguments) {
        if (arguments == null || arguments.length==0) {
            return NO_PARA_PREFIX;
        }
        StringBuilder argBuilder = new StringBuilder("");
        int argumentsLength = arguments.length;
        for (int i = 0; i < argumentsLength; i++) {
            Object arg = arguments[i];
            if (arg == null) {
                argBuilder.append(SEPARATOR).append("null");
            } else {
                argBuilder.append(SEPARATOR).append(JSONObject.toJSONString(arg));
            }
        }
        return HAVE_PARA_PREFIX+argBuilder.toString();
    }
    private Method computeMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = pjp.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        return currentMethod;
    }


    public static void main(String[] args) {
        String a="sxf";
        System.out.println(JSONObject.toJSONString(a));

        boolean flag1=true;
        boolean flag2=false;
        if(flag1==flag2){
            System.out.println("两个boolean相等");
        }else{
            System.out.println("两个boolean不相等");
        }
    }
}
