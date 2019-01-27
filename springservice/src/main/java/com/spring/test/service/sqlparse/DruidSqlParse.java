package com.spring.test.service.sqlparse;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.util.JdbcConstants;
import com.dianping.zebra.shard.parser.SQLParsedResult;
import com.dianping.zebra.shard.parser.SQLParser;

import java.util.List;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午8:48 2018/12/6
 */
public class DruidSqlParse {

    public static void main(String[] args) {
        String sql="select * from tb where id=?";
        SQLParsedResult parsedResult = SQLParser.parseWithCache(sql);
        System.out.println("========");
    }
}
