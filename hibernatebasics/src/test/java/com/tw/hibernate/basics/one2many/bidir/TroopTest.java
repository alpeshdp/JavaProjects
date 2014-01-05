package com.tw.hibernate.basics.one2many.bidir;

import com.tw.hibernate.util.HibernateUtil;
import org.hibernate.Session;
//import org.hibernate.classic.Session;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class TroopTest {

    @Test
    public void shouldCreateTroops() {

        Troop troop1 = new Troop();
        troop1.setName("Pune Warriors");

        Soldier soldier1 = new Soldier();
        soldier1.setfName("Joravar");
        soldier1.setlName("singh");
        soldier1.setTroop(troop1);

        Soldier soldier2 = new Soldier();
        soldier2.setfName("Dimagi");
        soldier2.setlName("Takat");
        soldier2.setTroop(troop1);

        List soldiersForTroop1 = new ArrayList();
        soldiersForTroop1.addAll(Arrays.asList(soldier1, soldier2));
        troop1.setSoldiers(soldiersForTroop1);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(troop1);
        session.getTransaction().commit();
        assertThat(troop1.getId(), notNullValue());
    }
}
