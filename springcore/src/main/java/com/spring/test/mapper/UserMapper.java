package com.spring.test.mapper;

import com.spring.test.conditions.Uconditions;
import com.spring.test.entry.User;

import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:09 2018/5/13
 */
public interface UserMapper {
    /**
     * 添加一个用户
     */
    public void addUser(User user);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public User queryUserById(Integer id);

    /**
     * 根据条件查询
     * @param uconditions
     * @return
     */
    public List<User> queryUserByConditions(Uconditions uconditions);

}
