package com.spring.test.service.drools;


import org.drools.examples.helloworld.HelloWorldExample;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:06 2018/8/3
 */
public class DroolsTest {


    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("HelloWorldKS");
        HelloWorldExample helloWorldExample=new HelloWorldExample();
    }
}
