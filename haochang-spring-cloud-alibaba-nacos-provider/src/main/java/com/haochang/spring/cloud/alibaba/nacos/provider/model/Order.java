package com.haochang.spring.cloud.alibaba.nacos.provider.model;

import java.io.Serializable;

/**
 * @description: 描述：订单实体
 * @author: youzhi.gao@ikang.com
 * @date: 2020-03-13 12:26
 */
public class Order implements Serializable {
    private static final long serialVersionUID = -4625044704685823957L;

    private Long id;
    private Double price;
    private String name;
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
