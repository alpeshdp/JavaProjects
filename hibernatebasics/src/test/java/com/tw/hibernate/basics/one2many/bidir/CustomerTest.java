package com.tw.hibernate.basics.one2many.bidir;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
//import org.hibernate.classic.Session;
import org.junit.Test;

import com.tw.hibernate.util.HibernateUtil;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void should() {

        Session session = getSession();
        Customer customer = new Customer();

        Orders order1 = new Orders();
        order1.setOrderNumber(111);
        order1.setOrderDate(new Date());
        order1.setCustomer(customer);

        Orders order2 = new Orders();
        order2.setOrderNumber(112);
        order2.setOrderDate(new Date());
        order2.setCustomer(customer);

        List<Orders> orders = Arrays.asList(order1, order2);
        customer.setOrders(orders);

        session.save(customer);
        commit(session);
        Integer customerId = customer.getOrders().get(0).getId();
        assertThat(customerId, notNullValue());

        Customer freshMe = (Customer) session.get(Customer.class, customerId);
        assertEquals(2, freshMe.getOrders().size());
    }

    @Test
    public void shouldReadByCustomerId() {

        Session session = getSession();
        Customer customer = (Customer) session.load(Customer.class, 1);
        assertThat(customer.getOrders().get(0).getOrderNumber(), is(111));
        commit(session);

    }

    @Test
    public void shouldReadByOrderId() {
        Session session = getSession();
        Orders orders = (Orders) session.load(Orders.class, 2);
        assertThat(orders.getId(), is(2));
        assertThat(orders.getOrderNumber(), is(112));
        assertThat(orders.getCustomer().getId(), is(1));
        commit(session);
    }

    private Session getSession() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        return session;
    }

    private void commit(Session session) {
        session.getTransaction().commit();
    }
}
