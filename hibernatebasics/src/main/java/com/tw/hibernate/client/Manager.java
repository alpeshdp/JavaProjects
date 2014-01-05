package com.tw.hibernate.client;

import com.tw.hibernate.basics.User;
import com.tw.hibernate.basics.one2many.unidir.Part;
import com.tw.hibernate.basics.one2many.unidir.Product;
import com.tw.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.usertype.UserCollectionType;
import org.joda.time.DateTimeZone;

import java.util.HashSet;
import java.util.Set;

public class Manager {

    public static void main(String args[]) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

//        Part p1 = new Part();
//        p1.setName("New LCD screen");
//        p1.setPrice(12.12);
//
//        Set<Part> parts = new HashSet<Part>();
//        parts.add(p1);
//
//        Product product = new Product();
//        product.setName("New Macbook Pro");
//        product.setParts(parts);
        
        com.tw.hibernate.basics.User user = new User();
//        user.setId(user.createUid("A", "B"));
        user.setAge(11);
        user.setDateTimeZone(DateTimeZone.forID("Asia/Kolkata"));
        session.save(user);

//        session.save(product);


        //--- Part 2
//        Part p2 = new Part();
//        p2.setName("New Part");
//        p2.setPrice(11.11);
//
//
//        Product p = (Product) session.load(Product.class, 2);
//        p.getParts().addEmployer(p2);
//        session.saveOrUpdate(p);

        // -- delete --
//        Product p = (Product) session.load(Product.class, 1);
//        session.delete(p);
        
        session.getTransaction().commit();
    }

}