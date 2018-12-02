package com.spring.test.testzreab;

import com.spring.test.entry.generator.TxOrder;
import com.spring.test.mapper.generator.TxOrderMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:55 2018/10/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-spring-core.xml"})
public class ZebraTest {

    @Autowired
    private TxOrderMapper txOrderMapper;

    @Test
    public void testInsert(){
        TxOrder txOrder=new TxOrder();
        txOrder.setId(1);
        txOrder.setName("可乐");
        txOrder.setMoney(12);
        txOrderMapper.insert(txOrder);
    }
}
