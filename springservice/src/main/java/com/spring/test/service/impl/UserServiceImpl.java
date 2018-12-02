package com.spring.test.service.impl;

import com.spring.test.service.UserService;
import com.spring.test.entry.User;
import com.spring.test.mapper.UserMapper;
import com.spring.test.service.event.UserAddEvent;
import com.spring.test.service.guava.annotations.Cache;
import com.spring.test.service.guava.enums.DoType;
import com.spring.test.service.publish.SpringUtilsPublish;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午1:54 2018/5/13
 */
@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SpringUtilsPublish springUtilsPublish;



    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED,timeout= TransactionDefinition.TIMEOUT_DEFAULT,noRollbackFor =RuntimeException.class )
    @Override
    public int addUser(User user) {
        String name=Thread.currentThread().getName();
        logger.info("添加一个用户到数据库-sxftest");
        System.out.println("当前线程的名字："+name);
        userMapper.addUser(user);
        UserAddEvent event=new UserAddEvent(user);
        springUtilsPublish.publish(event);
        return 0;
    }

    @Cache(doType=DoType.READ)
    @Transactional
    @Override
    public String getUserById(String id,int b) {
        return "我是真正的执行者";
    }
}
