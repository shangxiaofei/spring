package com.spring.test.service.commons.beansutils;

import java.util.Date;

/**
 * @author <a href="mailto:shangxiaofei@meituan.com">尚晓飞</a>
 * @date 下午6:24 2018/6/4
 */
public class Account {
    private int id;
    private String name;
    private long money;
    private Date startDate;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getMoney() {
        return money;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
