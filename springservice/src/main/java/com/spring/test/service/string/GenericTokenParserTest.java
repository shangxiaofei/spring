package com.spring.test.service.string;

import org.apache.ibatis.scripting.xmltags.TextSqlNode;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午5:06 2019/1/26
 */
public class GenericTokenParserTest {

    public static void main(String[] args) {
        String sql="select * from table where id=\\${id} and name=\\${name}";
        TextSqlNode textSqlNode=new TextSqlNode(sql);
        boolean flag=textSqlNode.isDynamic();
        System.out.println("是否为动态sql语句:"+flag);
    }
}
