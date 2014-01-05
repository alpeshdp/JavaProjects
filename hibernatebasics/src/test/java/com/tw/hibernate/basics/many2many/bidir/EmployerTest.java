package com.tw.hibernate.basics.many2many.bidir;

import com.tw.hibernate.util.HibernateUtil;
import org.hamcrest.core.IsNull;
import org.hibernate.Session;
//import org.hibernate.classic.Session;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class EmployerTest {

    @Test
    public void shouldSaveEmpyerWithEmployees() {

        Employer kleinerP = new Employer();
        kleinerP.setName("Kleiner Perkins");

        Employer sunMicro = new Employer();
        sunMicro.setName("Sun Micro System");

        Employee vKhosla = new Employee();
        vKhosla.setfName("Vinod");
        vKhosla.setlName("Khosla");

        Employee jGosling = new Employee();
        jGosling.setfName("James");
        jGosling.setlName("Gosling");

        sunMicro.setEmployees(Arrays.asList(vKhosla, jGosling));
        kleinerP.setEmployees(Arrays.asList(vKhosla));

        vKhosla.setEmployers(Arrays.asList(sunMicro, kleinerP));
        jGosling.setEmployers(Arrays.asList(sunMicro));

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(kleinerP);
        session.save(sunMicro);
        session.getTransaction().commit();

        Assert.assertThat(sunMicro.getId(), IsNull.<Object>notNullValue());
        Assert.assertThat(sunMicro.getEmployees().get(0), IsNull.<Object>notNullValue());
    }

}
