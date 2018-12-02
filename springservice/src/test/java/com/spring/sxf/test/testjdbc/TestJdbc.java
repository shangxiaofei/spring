package com.spring.sxf.test.testjdbc;

import com.dianping.zebra.group.jdbc.GroupDataSource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.After;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.sql.StatementEvent;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:58 2018/11/29
 */
public class TestJdbc {

    private static String jdbcUrl = "jdbc:mysql://localhost:3306/test";
    private static String user = "root";
    private static String pwd = "shangxiaofei";

    public static void main(String[] args) throws SQLException {
        GroupDataSource groupDataSource=null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        testBatch();
    }


    public static void jdbc2db() throws SQLException {
        DataSource dataSource = null;//数据源接口
        Connection connection = null;//数据库连接接口
        Statement statement = null;//sql执行器
        PreparedStatement preparedStatement = null;//防止sql注入的执行器
        ResultSet resultSet = null;//sql执行结果集合

        //一次写操作
        connection = dataSource.getConnection();
        //开启事务
        connection.setAutoCommit(false);
        try {
            String writeSql = "";
            String params = "123";
            preparedStatement = connection.prepareStatement(writeSql);
            preparedStatement.setString(1, params);
            preparedStatement.executeUpdate();
            //执行成功，提交
            connection.commit();
        } catch (Exception e) {
            //执行失败，回滚
            connection.rollback();
        } finally {
            //释放资源
            preparedStatement.close();
            connection.close();
        }


        //一次读操作
        connection = dataSource.getConnection();
        try {
            String readSql = "";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(readSql);
            while (resultSet.next()) {
                String name = resultSet.getString(1);
            }
        } catch (Exception e) {

        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

    }


    public static void testBatch() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        for (int i = 4; i < 104; i++) {
            String sql = "insert into t_blog (id,name,context,createDateTime) values ('" + i + "','tst3数据库','jdbc学习3','2018-01-01 12:00:00') ";
            statement.addBatch(sql);
        }
        statement.executeBatch();
        statement.close();
        connection.close();

    }

    public static void testResult() throws SQLException {
        Connection connection = getConnection();
        int id = 2;
        String sql = "select * from t_blog where id>=?";
        PreparedStatement preparedStatement = createPreparedStatement(connection, sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int rid = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String context = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println("读取的记录=>" + rid + "-" + name + "-" + context + "-" + DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
        }

        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement createPreparedStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, pwd);
    }

    public static void testPreparedStatement() throws SQLException {
        Connection connection = getConnection();
        int id = 1;
        String sql = "delete from t_blog where id=?";
        //PreparedStatement 有效的防止sql注入(SQL语句在程序运行前已经进行了预编译,当运行时动态地把参数传给PreprareStatement时，
        //即使参数里有敏感字符如 or '1=1'也数据库会作为一个参数一个字段的属性值来处理而不会作为一个SQL指令)
        PreparedStatement preparedStatement = createPreparedStatement(connection, sql);
        preparedStatement.setInt(1, id);//占位符顺序从1开始
        int clomSize = preparedStatement.executeUpdate();
        System.out.println("影响的数据库行数=》" + clomSize);

    }


    public static void testDelete() throws SQLException {
        Connection connection = getConnection();
        Statement statement = createStatement(connection);
        String id = "2";
        //存在sql注入的危险
        //如果用户传入的id为“5 or 1=1”，那么将删除表中的所有记录
        String sql = "delete from t_blog where id=" + id;
        statement.execute(sql);
    }


    public static Statement createStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }


    /**
     * 插入一条数据
     *
     * @throws SQLException
     */
    public static void testInsert() throws SQLException {
        Connection connection = getConnection();
        Statement statement = createStatement(connection);
        String sql = "insert into t_blog (id,name,context,createDateTime) values (3,'test3数据库','jdbc学习3','2018-01-01 12:00:00') ";
        System.out.println("插入记录数=》" + insertOne(sql, statement));
        statement.close();
        connection.close();
    }


    public static Statement createPreStatement(Connection connection, String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }


    public static int insertOne(String sql, Statement statement) throws SQLException {
        return statement.executeUpdate(sql);
    }


}
