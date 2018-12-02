package com.spring.test.service.saop;

import com.alibaba.fastjson.JSONObject;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:09 2018/6/22
 */
@Aspect
@Component("testAspect")
public class TestAspect {

    private static final String NO_PARA_PREFIX="NO_PARA";
    private static final String HAVE_PARA_PREFIX="HAVE_PARA";
    private static final String SEPARATOR="-";

    @Before(value = "execution(public * com.spring.test.service.impl.UserManage.doSpringAopTest(..))")
    public void methodBefore(JoinPoint point){
        System.out.println("参数列表=》"+point.getArgs()[0]);
        System.out.println("======我是拦截器，我在【方法执行前】执行======");
    }

    @After("execution(public * com.spring.test.service.impl.UserManage.doSpringAopTest(..))")
    public void methodAfter(){
        System.out.println("======我是拦截器，我在【方法执行后】执行======");
    }

    @AfterReturning("execution(public * com.spring.test.service.impl.UserManage.doSpringAopTest(..))")
    public void methodAfterRuning(){
        System.out.println("======我是拦截器，我在【方法执行结束】执行=====");
    }

    @AfterThrowing("execution(public * com.spring.test.service.impl.UserManage.doSpringAopTest(..))")
    public void methodAfterThrowing(){
        System.out.println("======我是拦截器，我在【方法执行抛出异常】执行====");
    }


    @Around("execution(public * com.spring.test.service.impl.UserManage.doSpringAopTest(..))")
    public void round(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("======【执行之前】执行=======");
        System.out.println("参数列表==》"+proceedingJoinPoint.getArgs()[0]);
        Object object=proceedingJoinPoint.getTarget();
        System.out.println("代理的目标类为==>"+object.getClass());
        try {
            proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        }catch (Throwable e){
            System.out.println("铺货异常");
        }

        System.out.println("======【执行之后】执行=======");
    }


    public static boolean isFinal(int a){
        return a==Integer.valueOf(3)||a==Integer.valueOf(4)||a==Integer.valueOf(5);
    }

    private String parseArguments(Object[] arguments) {
        if (arguments == null) {
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

    public static void main(String[] args) {
        System.out.println(isFinal(6));
    }
}
