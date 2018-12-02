package com.spring.sxf.string;

import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:12 2018/7/12
 */
public class StringTest {
    public static void main(String[] args) {
        System.out.println("卡号校验规则=》"+validateCardNo("123-"));
    }

    public static boolean validateCardNo(String cardNo) {
        return! (StringUtils.isBlank(cardNo) || !cardNo.matches("^[\\d\\-\\－]+$"));
    }
}
