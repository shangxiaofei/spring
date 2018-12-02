package com.spring.test.service.guava.processor;

import com.spring.test.service.guava.advisor.CacheAdvisor;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.scheduling.annotation.AsyncAnnotationAdvisor;

import java.lang.annotation.Annotation;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:53 2018/5/30
 */
public class CacheBeanPostProcess extends AbstractAdvisingBeanPostProcessor implements  BeanFactoryAware {

    private Class<? extends Annotation> cacheAnnotationType;

    public CacheBeanPostProcess(){
        setBeforeExistingAdvisors(true);
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        CacheAdvisor advisor =  new CacheAdvisor();
        if (this.cacheAnnotationType != null) {
            advisor.setCacheAnnotationType(this.cacheAnnotationType);
        }
        advisor.setBeanFactory(beanFactory);
        this.advisor = advisor;
    }
}
