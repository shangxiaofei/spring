package com.spring.test.service.event;


import org.springframework.context.ApplicationEvent;

/**
 * 当一个用户被添加的事件
 *
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:13 2018/5/22
 */
public class UserAddEvent extends ApplicationEvent {

    public UserAddEvent(Object source) {
        super(source);
    }
}
