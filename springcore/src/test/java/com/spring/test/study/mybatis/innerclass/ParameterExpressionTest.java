package com.spring.test.study.mybatis.innerclass;

import org.apache.ibatis.builder.ParameterExpression;

import java.util.HashMap;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午6:15 2019/1/30
 */
public class ParameterExpressionTest {

    public static void main(String[] args) {
        HashMap<String, String> parameterExpression=new ParameterExpression("createdTime,jdbcType=BIGINT,typeHandler=com.meituan.payment.fundsgateway.core.model.handler.DateForLongTypeHandler");
        String result=parameterExpression.get("property");
        System.out.println("result=>"+result);
    }
}
