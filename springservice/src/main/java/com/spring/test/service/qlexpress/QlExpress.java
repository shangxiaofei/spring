package com.spring.test.service.qlexpress;

import com.ql.util.express.ExpressRunner;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午3:13 2018/7/20
 */
public class QlExpress {

    public static void main(String[] args) throws Exception {
        String express1 = " 1 in (2) ";
        String express2 = " if(false){ 1} else{2} ";
        String express3 = " round(4.34,1) ";
        String express4 = " '哈哈'.equals('哈哈') ";
        ExpressRunner runner = new ExpressRunner();

        System.out.println("表达式计算：" + express1 + " 处理结果： " + runner.execute(express1, null, null, false, false) );
        System.out.println("表达式计算：" + express2 + " 处理结果： " + runner.execute(express2, null, null, false, false) );
        System.out.println("表达式计算：" + express3 + " 处理结果： " + runner.execute(express3, null, null, false, false) );
        System.out.println("表达式计算：" + express4 + " 处理结果： " + runner.execute(express4, null, null, false, false) );

    }
}
