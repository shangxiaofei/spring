package com.spring.test.service.springtranscantion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TestTransaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:13 2018/7/21
 */
@Component
public class JdbcTranscantion {

    @Autowired
    private ServiceA serviceA;

    public void testTranscantion(){
        serviceA.doThings();
    }


    public static void main(String[] args) throws SQLException {
        //数据库连接资源
        DataSource dataSource=null;
        //从数据库连接池获取数据库连接
        Connection connection= dataSource.getConnection();
        //开启数据库事务
        connection.setAutoCommit(false);
        Statement statement=null;
        try{
            //给id为1的账户减少10元
            String sql1="update account where set amount=amount-10 where id=1";
            //给id为2的账户增加10元
            String sql2="update account where set amount=amount+10 where id=2";
            //执行操作
            statement=connection.createStatement();
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
        }catch(Exception e){
            //在执行两条sql的过程中发生异常，进行事务回滚
            connection.rollback();
            return;
        }finally {
            //释放资源
            statement.close();
            connection.close();
        }
        //执行完sql,无异常,事务提交
        connection.commit();
    }
}
