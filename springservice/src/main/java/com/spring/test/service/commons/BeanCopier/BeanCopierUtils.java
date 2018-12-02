package com.spring.test.service.commons.BeanCopier;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午7:52 2018/6/4
 */
public class BeanCopierUtils {

    private final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>();

    private static BeanCopierUtils beanCopierUtils;

    private BeanCopierUtils(){

    }
    public static BeanCopierUtils getInstance(){
        if(beanCopierUtils==null) {
            synchronized (BeanCopierUtils.class) {
                if (beanCopierUtils == null) {
                    beanCopierUtils = new BeanCopierUtils();
                }
            }
        }
        return beanCopierUtils;
    }

    public void copy(Object source,Object target){
        Class sourceCls=source.getClass();
        Class targetCls=target.getClass();
        String key=calculateKey(sourceCls,targetCls);
        BeanCopier beanCopier=BEAN_COPIERS.get(key);
        if(beanCopier==null){
            beanCopier=BeanCopier.create(sourceCls,targetCls,false);
            BEAN_COPIERS.put(key,beanCopier);
        }
        beanCopier.copy(source,target,null);
    }

    public void copy(Object source,Object target,Converter converter){

    }

    private String calculateKey(Class source,Class target){
        return source.getName() + target.getName();
    }
}
