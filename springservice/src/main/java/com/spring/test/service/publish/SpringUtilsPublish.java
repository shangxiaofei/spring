package com.spring.test.service.publish;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 事件发布容器
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:22 2018/5/22
 */
@Component
public class SpringUtilsPublish implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void publish(final ApplicationEvent event) {

        //当前线程存在事务，则事务提交后发布事件
        if (TransactionSynchronizationManager.isSynchronizationActive()) {

            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                @Override
                public void afterCommit() {
                    System.out.println("事务提交后发布事件");
                    applicationContext.publishEvent(event);
                }
            });
        } else {
            System.out.println("当前线程无事务，发布事件");
            applicationContext.publishEvent(event);
        }
    }
}
