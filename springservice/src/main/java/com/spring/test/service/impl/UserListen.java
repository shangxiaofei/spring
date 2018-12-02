package com.spring.test.service.impl;

import com.spring.test.service.guava.annotations.Cache;

import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午5:43 2018/6/26
 */
@Service
public class UserListen {

    @Cache
    public String save(String name,Integer age,String address){
        System.out.println("保存成功");
        return name+"保存成功";
    }

    @Cache
    public String saveNoPara(){
        System.out.println("没有参数执行保存");
        return "没有参数执行保存"+"保存成功";
    }
}
