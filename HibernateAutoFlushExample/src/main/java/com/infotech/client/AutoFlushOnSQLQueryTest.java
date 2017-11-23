package com.infotech.client;

import java.math.BigInteger;

import org.hibernate.Session;

import com.infotech.entities.Person;
import com.infotech.util.HibernateUtil;

public class AutoFlushOnSQLQueryTest {


	public static void main(String[] args) {

		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			
			Person  person = new Person();
			person.setFirstName("PK");
			person.setLastName("Singh");
			
			session.beginTransaction();
			
			BigInteger intialCount =(BigInteger)session.createNativeQuery( "select count(*) from Person").getSingleResult();
			System.out.println("Person Records Count:"+intialCount);
			
			session.save(person);
			
			
			BigInteger finalCount =(BigInteger)session .createNativeQuery( "select count(*) from Person")
					.addSynchronizedEntityClass(Person.class).uniqueResult();
			/*BigInteger finalCount =(BigInteger)session .createNativeQuery( "select count(*) from Person").
			uniqueResult();*/
			System.out.println("Now Person Records Count:"+finalCount);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}