package com.spring.test.service;

import com.spring.test.entry.User;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午1:53 2018/5/13
 */
public interface UserService {


    public int addUser(User user);

    public String getUserById(String id,int a);
}
