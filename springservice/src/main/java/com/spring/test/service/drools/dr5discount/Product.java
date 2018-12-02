package com.spring.test.service.drools.dr5discount;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午2:50 2018/8/7
 */
public class Product {
    /**
     * 钻石
     */
    public static final String DIAMOND="0";
    /**
     * 黄金
     */
    public static final String GOLD="1";

    /**
     * 产品类型
     */
    private String type;
    /**
     * 折扣
     */
    private int discount;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
