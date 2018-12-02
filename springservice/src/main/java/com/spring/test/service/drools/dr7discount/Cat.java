package com.spring.test.service.drools.dr7discount;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午4:17 2018/8/7
 */
public class Cat {
    /**
     * a类型猫 为黑色
     */
    public static final String A="aCat";
    /**
     * b类型猫 为白色
     */
    public static final String B="bCat";

    private String type;
    private String color;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
