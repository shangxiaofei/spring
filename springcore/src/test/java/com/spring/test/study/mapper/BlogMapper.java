package com.spring.test.study.mapper;

import com.spring.test.study.entry.Blog;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:22 2018/8/11
 */
public interface BlogMapper {
    /**
     * 根据id获取一篇博文
     * @param id
     * @return
     */
    public Blog getById(long id);
}
