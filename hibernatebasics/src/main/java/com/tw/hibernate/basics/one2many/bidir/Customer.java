package com.tw.hibernate.basics.one2many.bidir;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    private Integer id;
    private List<Orders> orders;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "customer")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    @OrderBy("orderNumber")
    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orderses) {
        this.orders = orderses;
    }
}
