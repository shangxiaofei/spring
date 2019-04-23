package com.spring.test.service.sqlparse;


import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;

import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:48 2018/12/6
 */
public class DruidSqlParse {

    public static void main(String[] args) {
        test01();
    }


    public static void test01() {
        StringBuilder stringBuilder = new StringBuilder();
        MySqlSchemaStatVisitor mySqlOutputVisitor = new MySqlSchemaStatVisitor();
       // String sql = "select name,age,address from  t_user where id=123";
        String sql="select id,count(*) from t_user group by id having id>3 order by id desc limit 0,10";
        MySqlStatementParser mySqlStatementParser = new MySqlStatementParser(sql);
        List<SQLStatement> sqlStatements = mySqlStatementParser.parseStatementList();
        for (SQLStatement sqlStatement : sqlStatements) {
            sqlStatement.accept(mySqlOutputVisitor);
            System.out.println(mySqlOutputVisitor.getTables());

        }
    }
}