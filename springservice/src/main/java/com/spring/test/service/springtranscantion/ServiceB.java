package com.spring.test.service.springtranscantion;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午7:31 2018/7/22
 */
@Service
public class ServiceB {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void doStep1(){
        //ServiceB数据库的操作
    }
}
