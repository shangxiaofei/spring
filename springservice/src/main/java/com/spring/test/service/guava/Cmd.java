package com.spring.test.service.guava;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午7:54 2018/6/1
 */
public interface Cmd<T,D> {
    public T execue(D d) throws Exception;
}
