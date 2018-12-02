package com.spring.test.service.impl;

import com.spring.test.entry.User;
import com.spring.test.entry.generator.SUser;
import com.spring.test.entry.generator.SUserExample;
import com.spring.test.mapper.UserMapper;
import com.spring.test.mapper.generator.SUserMapper;
import com.spring.test.service.event.UserAddEvent;
import com.spring.test.service.guava.annotations.Cache;
import com.spring.test.service.guava.enums.CacheLeveEnum;
import com.spring.test.service.publish.SpringUtilsPublish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午9:24 2018/6/2
 */
@Service
public class UserManage {
    public static final Logger logger = LoggerFactory.getLogger(UserManage.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SUserMapper sUserMapper;

    @Autowired
    private SpringUtilsPublish springUtilsPublish;


    @Cache(cacheLeves={CacheLeveEnum.GUAVA})
    public String addUser(User user) {
        String name=Thread.currentThread().getName();
        logger.info("添加一个用户到数据库-sxftest");
        System.out.println("当前线程的名字："+name);
        userMapper.addUser(user);
        UserAddEvent event=new UserAddEvent(user);
        springUtilsPublish.publish(event);
        return "我是真正的执行者";
    }

    @Cache
    public String doSpringAopTest(String aop){
        System.out.println("我是被代理的动作，我正在执行");
        if("throw".equals(aop)){
            throw new RuntimeException("需要执行异常，抛出运行时异常");
        }
        return "尚晓飞";
    }

    @Cache
    public int doAdd(String a,int age){
        System.out.println("被代理类执行，参数列表为==>"+a+","+age);
        return 10+age;
    }


    public SUser getUser(){

        SUserExample sUserExample=new SUserExample();
        SUserExample.Criteria criteria=sUserExample.createCriteria();
        criteria.andAdressEqualTo("二级缓存");
        List<SUser> list=sUserMapper.selectByExample(sUserExample);
        System.out.println("获取的用户的个数为："+list.size());

        return sUserMapper.selectByPrimaryKey(13);
    }
}
