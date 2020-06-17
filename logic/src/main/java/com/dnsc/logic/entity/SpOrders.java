package com.dnsc.logic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class SpOrders {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "oid")
    private String oid;
    @Column(name = "spid")
    private int spId;
    @Column(name = "userid")
    private int userId;
    @Column(name = "count")
    private int count;
    @Column(name = "price")
    private double price;
    @Column(name = "sumprice")
    private double sumPrice;
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "status")
    private int status;


    public SpOrders() {
        super();
    }

    public SpOrders(String oid,int spId, int userId, int count, double price, double sumPrice, int status) {
        super();
        this.oid = oid;
        this.spId = spId;
        this.userId = userId;
        this.count = count;
        this.price = price;
        this.sumPrice = sumPrice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
