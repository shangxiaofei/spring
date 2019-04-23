package com.spring.test.service.sqlparse;

import com.dianping.zebra.druid.sql.ast.SQLStatement;
import com.dianping.zebra.druid.sql.dialect.mysql.parser.MySqlLexer;
import com.dianping.zebra.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.dianping.zebra.druid.sql.parser.SQLStatementParser;
import com.dianping.zebra.shard.parser.MultiSQLParsedResult;
import com.dianping.zebra.shard.parser.SQLHint;
import com.dianping.zebra.shard.parser.SQLParsedResult;
import com.dianping.zebra.shard.parser.SQLParser;

import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:57 2018/12/5
 */
public class TestSqlParse {

    public static void main(String[] args) {
        test01();
    }



    public static void test02(){
         boolean[] firstIdentifierFlags = new boolean[256];

            for (char c = 0; c < firstIdentifierFlags.length; ++c) {
                if (c >= 'A' && c <= 'Z') {
                    firstIdentifierFlags[c] = true;
                } else if (c >= 'a' && c <= 'z') {
                    firstIdentifierFlags[c] = true;
                }
            }
            firstIdentifierFlags['`'] = true;
            firstIdentifierFlags['_'] = true;
            firstIdentifierFlags['$'] = true;
    }

    public static void test01(){
        String sql="select * from tb_order where id in (select id from tb_account)";
        SQLParser.init();
        SQLParsedResult result=SQLParser.parseWithCache(sql);
        System.out.println(result);
        System.out.println(result.getRouterContext().getTableSet());
    }

}
