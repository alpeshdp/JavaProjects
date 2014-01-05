package com.tw.hibernate.basics.one2many.unidir;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.tw.hibernate.util.HibernateUtil;

public class ProductTest {

	
	@Test
	public void test() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

//        License license1 = new License("L1", 3l);
//        License license2 = new License("L2", 3l);
//        License license3 = new License("L3", 3l);
//        License license4 = new License("L4", 3l);
//        License license5 = new License("L5", 3l);
//        session.save(license1);
//        session.save(license2);
//        session.save(license3);
//        session.save(license4);
//        session.save(license5);
//        
//        System.out.println(license1);
//        System.out.println(license2);
//        System.out.println(license3);
//        System.out.println(license4);
//        System.out.println(license5);
//        
//        session.flush();
//        
//        UserSession userSession1 = new UserSession("s1", Arrays.asList(license5, license4, license3, license2, license1));
//        System.out.println(userSession1);
//        
//        UserSession userSession2 = new UserSession("s2", Arrays.asList(license5, license4, license3, license2));
//        System.out.println(userSession2);
//        
//        UserSession userSession3 = new UserSession("s3", Arrays.asList(license5, license4, license3));
//        System.out.println(userSession3);
//        
//        UserSession userSession4 = new UserSession("s4", Arrays.asList(license5, license4));
//        System.out.println(userSession4);
//        
//        UserSession userSession5 = new UserSession("s5", Arrays.asList(license5));
//        System.out.println(userSession5);
//        
//        session.save(userSession1);
//        session.save(userSession2);
//        session.save(userSession3);
//        session.save(userSession4);
//        session.save(userSession5);
//        
//        session.flush();
        
		asas(session, 3l, Arrays.asList(1l, 2l, 3l, 4l, 5l));
//		asas(session, 3l, license2);
//		asas(session, 3l, license3);
//		asas(session, 3l, license4);
//		asas(session, 3l, license5);
		session.getTransaction().commit();
		
	}

	private void asas(Session session, long threshold, List<Long> license) {
//		Query query = session.createQuery(
//				"select lics, count(*) from UserSession as ses " +
//				" inner join ses.licenses as lics group by lics.id " +
//				" having  lics.id in (:lid) and count(*) < lics.maxUsersForLicense");

		Query query = session.createQuery(
				"from License lic where lic.id in ( " +
					" select licenses.id from UserSession as session " +
					" inner join session.licenses as licenses " +
					" group by licenses.id, licenses.maxUsersForLicense " +
					" having licenses.id in (:licensesIds)" +
					" and count(*) < licenses.maxUsersForLicense " +
				")");			
		
		    		List list = query
	        		.setParameterList("licensesIds", license)
//	        		.setParameter("threshold", 4l)
	        		.list();
	       
//		    	Object object = uniqueResult;
	        System.out.println("============================ For Lic: " + license + "=== th:" + threshold);
	       
	        for (Object object : list) {
//	        	Object[] object2 = (Object[]) object;
//	        	System.out.println("============================>" + object.getClass() + ":" + object2[0]  + ":" + object2[1]);
	        	System.out.println("============================>" + object.getClass() + ":" + object);
			}
	        System.out.println("============================");
	}
	
    @Test
    public void shouldTest() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Part p1 = new Part();
        p1.setName("New LCD screen");
        p1.setPrice(12.12);

        Part p2 = new Part();
        p2.setName("Keypad");
        p2.setPrice(11.11);

        Set<Part> parts = new HashSet<Part>();
        parts.add(p1);
        parts.add(p2);

        Product product = new Product();
        product.setName("Macbook Pro");
        product.setParts(parts);

        session.save(product);
        session.getTransaction().commit();
        assertThat(product.getParts().size(), is(2));
    }
    
}
