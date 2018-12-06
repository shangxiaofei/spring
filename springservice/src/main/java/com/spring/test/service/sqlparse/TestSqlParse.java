package com.spring.test.service.sqlparse;

import com.dianping.zebra.shard.parser.SQLParsedResult;
import com.dianping.zebra.shard.parser.SQLParser;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:57 2018/12/5
 */
public class TestSqlParse {

    public static void main(String[] args) {
        test01();
    }


    public static void test01(){
        String sql="slect * from tb_order where id=?";
        SQLParser.init();
        SQLParsedResult result=SQLParser.parseWithCache(sql);
        System.out.println(result);
    }
}
