package com.spring.test.service.springtranscantion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午7:31 2018/7/22
 */
@Service
public class ServiceA {

    @Autowired
    private ServiceB serviceB;
    @Autowired
    private ServiceC serviceC;

    @Transactional(propagation= Propagation.REQUIRED)
    public void doThings(){
        //ServiceA 自己的数据库操作
        serviceB.doStep1();
        serviceC.doStep2();
    }
}
