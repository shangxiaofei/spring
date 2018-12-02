package com.spring.test.service.commons.beansutils;

import net.sf.cglib.beans.BeanCopier;

import org.apache.commons.beanutils.BeanUtils;



import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午6:26 2018/6/4
 */
public class BeansUtilsTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        test01();
    }

    public static void  test01() throws InvocationTargetException, IllegalAccessException {
        Account account=new Account();
        account.setName("天赐良机");
        account.setId(888888);
        account.setMoney(100);
        account.setStartDate(new Date());

        Account newAccount=new Account();

        BeanUtils.copyProperties(newAccount,account);
        System.out.println("copy后的name=>"+newAccount.getName());
        System.out.println("copy后的date=>"+newAccount.getStartDate());
    }


}
