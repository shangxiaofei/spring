package com.spring.test.service.guava.advisor;

import com.spring.test.service.guava.cache.Cache;
import com.spring.test.service.guava.enums.CacheLeveEnum;
import com.spring.test.service.guava.enums.DoType;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;

import java.io.File;
import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午12:20 2018/5/30
 */
public class CacheMethodInterceptor implements MethodInterceptor{

    private Cache cache;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
        Object[] objects=invocation.getArguments();
        String cacheKey=targetClass.getName();
        Method method=invocation.getMethod();
        String methodName=method.getName();

        AnnotationAttributes annotationAttributes=AnnotatedElementUtils.getAnnotationAttributes(invocation.getMethod(),com.spring.test.service.guava.annotations.Cache.class.getName());
        DoType doType= (DoType) annotationAttributes.get("doType");
        CacheLeveEnum[] enums= (CacheLeveEnum[]) annotationAttributes.get("cacheLeves");


        if(objects==null){

        }else{
            for(int i=0;i<objects.length;i++){
                Object object=objects[i];
                if(object instanceof  String){
                    cacheKey = cacheKey+(String) object;
                }else if(object instanceof  Integer){
                    cacheKey =cacheKey+(Integer)object;
                }else{
                    Class cls=object.getClass();
                    Field[] fields=cls.getFields();
                    for(int j=0;j<fields.length;j++){
                        Field field=fields[i];
                    }
                }
            }
        }
        System.out.println(cacheKey);
        System.out.println(methodName);

        return "我是缓存拦截器";
    }
}
