package com.spring.test.service.guava.processor;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.parsing.BeanComponentDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午11:49 2018/6/6
 */
public class McacheBeanDefinitionParser implements BeanDefinitionParser {

    private static final String CACHE_BEAN_CLASS_NAME="com.spring.test.service.guava.processor.CacheBeanPostProcess";
    private static final String CACHE_BEAN_NAME="com.spring.test.service.guava.processor.cacheBeanPostProcess";


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        Object source = parserContext.extractSource(element);
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CACHE_BEAN_CLASS_NAME);
        builder.getRawBeanDefinition().setSource(source);
        boolean firstLeveCacheSwitch= Boolean.parseBoolean(element.getAttribute("firstLeveCacheSwitch"));
        boolean secondLeveCacheSwitch=Boolean.parseBoolean(element.getAttribute("secondLeveCacheSwitch"));
        boolean oneLeveCacheDataSafe=Boolean.parseBoolean(element.getAttribute("oneLeveCacheDataSafe"));
        String secondLevelCacheRef=element.getAttribute("secondLevelCacheRef");
        registerPostProcessor(parserContext,builder,CACHE_BEAN_NAME);
        //parserContext.popAndRegisterContainingComponent();
        return null;
    }

    private static void registerPostProcessor(
            ParserContext parserContext, BeanDefinitionBuilder builder, String beanName) {

        builder.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        parserContext.getRegistry().registerBeanDefinition(beanName, builder.getBeanDefinition());
        BeanDefinitionHolder holder = new BeanDefinitionHolder(builder.getBeanDefinition(), beanName);
        parserContext.registerComponent(new BeanComponentDefinition(holder));
    }
}
