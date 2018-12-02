package com.spring.test.service.guava.cache;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:50 2018/6/1
 */
public class GuavaCache implements Cache<String,Object>{


    @Override
    public Object getKey(String key) {
        return null;
    }

    @Override
    public boolean putKey(String key) {
        return false;
    }

    @Override
    public boolean detelte(String key) {
        return false;
    }
}
