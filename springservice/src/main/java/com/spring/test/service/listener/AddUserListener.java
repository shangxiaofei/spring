package com.spring.test.service.listener;

import com.spring.test.entry.User;
import com.spring.test.service.event.UserAddEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 当用户被添加的事件监听执行逻辑
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:15 2018/5/22
 */
@Component
public class AddUserListener implements ApplicationListener<UserAddEvent> {

    @Async
    public void onApplicationEvent(UserAddEvent event) {
        User user = (User) event.getSource();
        String name = Thread.currentThread().getName();
        System.out.println("AddUserListener 当前线程为：" + name);
        System.out.println("AddUserListener 添加的uerName：" + user.getName());
    }
}
