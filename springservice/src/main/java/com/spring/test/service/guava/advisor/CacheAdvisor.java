package com.spring.test.service.guava.advisor;

import com.spring.test.service.guava.annotations.Cache;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:56 2018/5/30
 */
public class CacheAdvisor extends AbstractPointcutAdvisor implements BeanFactoryAware{

    private BeanFactory beanFactory;

    private Advice advice;

    private Pointcut pointcut;

    private Class<? extends Annotation> cacheAnnotationType;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
        Set<Class<? extends Annotation>> asyncAnnotationTypes = new LinkedHashSet<Class<? extends Annotation>>(2);
        asyncAnnotationTypes.add(Cache.class);
        try {
            asyncAnnotationTypes.add((Class<? extends Annotation>)
                    ClassUtils.forName("javax.ejb.Asynchronous", CacheAdvisor.class.getClassLoader()));
        }
        catch (ClassNotFoundException ex) {
            // If EJB 3.1 API not present, simply ignore.
        }
        this.advice=buildAdvice();
        this.pointcut=buildPointcut(asyncAnnotationTypes);
    }

    protected  Advice buildAdvice(){
        return new CacheMethodInterceptor();
    }

    protected Pointcut buildPointcut(Set<Class<? extends Annotation>> cacheAnnotationTypes) {
        ComposablePointcut result = null;
        for (Class<? extends Annotation> cacheAnnotationType : cacheAnnotationTypes) {
            Pointcut cpc = new AnnotationMatchingPointcut(cacheAnnotationType, true);
            Pointcut mpc = AnnotationMatchingPointcut.forMethodAnnotation(cacheAnnotationType);
            if (result == null) {
                result = new ComposablePointcut(cpc).union(mpc);
            }
            else {
                result.union(cpc).union(mpc);
            }
        }
        return result;
    }

    public Class<? extends Annotation> getCacheAnnotationType() {
        return cacheAnnotationType;
    }

    public void setCacheAnnotationType(Class<? extends Annotation> cacheAnnotationType) {
        this.cacheAnnotationType = cacheAnnotationType;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }
}
