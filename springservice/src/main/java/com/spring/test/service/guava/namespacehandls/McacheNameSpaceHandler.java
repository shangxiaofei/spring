package com.spring.test.service.guava.namespacehandls;

import com.spring.test.service.guava.processor.McacheBeanDefinitionParser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:35 2018/6/6
 */
public class McacheNameSpaceHandler extends NamespaceHandlerSupport{

    @Override
    public void init() {
        registerBeanDefinitionParser("annotation-driven",new McacheBeanDefinitionParser());
    }
}
