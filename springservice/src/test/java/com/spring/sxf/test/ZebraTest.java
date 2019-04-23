package com.spring.sxf.test;

import com.spring.test.entry.generator.TxOrder;
import com.spring.test.entry.generator.TxOrderExample;
import com.spring.test.mapper.generator.TxOrderMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
        txOrder.setId(10);
        txOrder.setName("mtpp_129_123");
        txOrder.setMoney(9999);
        txOrderMapper.insert(txOrder);
    }

    @Test
    public void testSelect(){
        TxOrderExample example=new TxOrderExample();
       TxOrderExample.Criteria criteria= example.createCriteria();
       criteria.andNameEqualTo("mtpp_123_123");
        List<TxOrder> txOrders= txOrderMapper.selectByExample(example);
        for(TxOrder order:txOrders){
            System.out.println("id==>"+order.getId());
            System.out.println("name=>"+order.getName());
        }
    }

    @Test
    public void testSelectByEx(){
        TxOrderExample example=new TxOrderExample();
        TxOrderExample.Criteria criteria= example.createCriteria();
        criteria.andIdEqualTo(10);
        List<TxOrder> txOrders= txOrderMapper.selectByExample(example);
        for(TxOrder order:txOrders){
            System.out.println("id==>"+order.getId());
            System.out.println("name=>"+order.getName());
        }
    }
}
