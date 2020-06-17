package com.dnsc.logic.entity;

import javax.persistence.*;

@Entity
@Table(name = "payorders")
public class PayOrders {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "orderid")
    private String orderid;
    @Column(name = "status")
    private int status;

    public PayOrders() {
        super();
    }

    public PayOrders( int id, String orderId, int status) {
        super();
        this.id = id;
        this.orderid = orderId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderId) {
        this.orderid = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
