package com.spring.test.service.drools.dr7discount;

import com.spring.test.service.drools.dr5discount.Product;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:20 2018/8/7
 *
 * drools7以上的执行api
 * 上一章中介绍了Drools5x版本中规则引擎使用的实例，很明显在Drools7中KnowledgeBase类已经标注为“@Deprecated”——废弃。在本章节中介绍Drools7版本中的使用方法。后续实例都将默认使用此版本
 */
public class CatDroolsTest {


    public static void main(String[] args) {
        // 构建KieServices
        KieServices ks = KieServices.Factory.get();
        KieContainer kieContainer = ks.getKieClasspathContainer();
        // 获取kmodule.xml中配置中名称为ksession-rule的session，默认为有状态的。
        KieSession kSession = kieContainer.newKieSession("ksession-rule");

        Cat cat=new Cat();
        cat.setType(Cat.B);

        kSession.insert(cat);
        int count = kSession.fireAllRules();
        System.out.println("匹配到【"+count+"】条规则");
        System.out.println("当前猫的颜色为==》"+cat.getColor());
    }
}
