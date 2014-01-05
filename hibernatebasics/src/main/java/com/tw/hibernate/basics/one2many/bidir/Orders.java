package com.tw.hibernate.basics.one2many.bidir;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Orders {

    private Integer id;
    private Integer orderNumber;
    private Date orderDate;
    private Customer customer;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @ManyToOne
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
