package com.spring.test.service.drools.dr5discount;


import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.util.Collection;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:59 2018/8/7
 *
 * 该类使用的是drools5的方式，进行规则编译，匹配，计算。
 * drools7之后，之前的类已经被标记为过时。
 *
 *
 * 类名	使用说明
 *KnowledgeBuilder	在业务代码中收集已编写的规则，并对规则文件进行编译，生成编译好的KnowledgePackage集合，提供给其他API使用。通过其提供的hasErrors()方法获得编译过程中是否有错，getErrors()方法打印错误信息。支持.drl文件、.dslr文件和xls文件等。
 *KnowledgePackage	存放编译之后规则的对象
 *KnowledgeBase	收集应用当中知识（knowledge）定义的知识库对象（KnowledgePackage），在一个 KnowledgeBase 当中可以包含普通的规则（rule）、 规则流(rule flow)、函数定义(function)、用户自定义对象（type model）等，并创建session对象（StatefulKnowledgeSession和 StatelessKnowledgeSession）
 *StatefulKnowledgeSession	接收外部插入的数据fact对象（POJO），将编译好的规则包和业务数据通过fireAllRules()方法触发所有的规则执行。使用完成需调用dispose()方法以释放相关内存资源。
 *StatelessKnowledgeSession	对StatefulKnowledgeSession的封装实现，与其对比不需要调用dispose()方法释放内存，只能插入一次fact对象。
 */
public class ProductionDiscountTest {

    public static void main(String[] args) {
        //规则编译器
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        //解析规则文件
        Resource resource=ResourceFactory.newClassPathResource("drool/dr5discount/productionDiscount.drl", ProductionDiscountTest.class);
        //向编译器添加规则文件
        kbuilder.add(resource, ResourceType.DRL);
        //判断是否错
        if(kbuilder.hasErrors()){
            System.out.println("规则异常==>"+kbuilder.getErrors());
        }
        //从编译器获取规则包
        Collection<KiePackage> pkgs = kbuilder.getKnowledgePackages();
        //创建一个规则包库
        InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        //将规则包添加到规则库
        kbase.addPackages(pkgs);

        //创建要执行规则匹配的产品
        Product product=new Product();
        product.setType(Product.GOLD);

        //创建ksession
        KieSession session=kbase.newKieSession();
        session.insert(product);
        //激活规则
        session.fireAllRules();
        session.dispose();
        //查看规则运行结果
        System.out.println("产品折扣为==>"+product.getDiscount());

    }
}
