package com.spring.test.service.javacore.clone;

import java.io.Serializable;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 上午10:19 2019/4/4
 */
public class Pet implements Serializable{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
