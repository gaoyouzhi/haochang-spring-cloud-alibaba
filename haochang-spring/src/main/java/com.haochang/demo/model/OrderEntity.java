package com.haochang.demo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 描述：订单实体
 * @author: youzhi.gao
 * @date: 2020-05-11 09:36
 */
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 8997439436202616563L;

    private Long id;
    private String name;
    private BigDecimal price;
    private String mobile;
    private String address;
    private short status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                '}';
    }
}
