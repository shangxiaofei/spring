package com.spring.test.service;

import com.spring.test.conditions.Uconditions;
import com.spring.test.entry.User;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午1:53 2018/5/13
 */
public interface UserService {


    public int addUser(User user);

    public String getUserById(String id,int a);

    public User queryUserById(String userId);

    public User updateUser(User user);

    public List<User> queryUserByCondtions(Uconditions uconditions);
}
