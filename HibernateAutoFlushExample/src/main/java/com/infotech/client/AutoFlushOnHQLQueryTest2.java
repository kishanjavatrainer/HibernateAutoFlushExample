package com.infotech.client;

import org.hibernate.Session;

import com.infotech.entities.Person;
import com.infotech.util.HibernateUtil;

public class AutoFlushOnHQLQueryTest2 {


	public static void main(String[] args) {

		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			
			Person  person = new Person();
			person.setFirstName("KK");
			person.setLastName("Singh");
			
			session.beginTransaction();
			
			session.save(person);
			session.createQuery( "select p from Person p" ).getResultList();
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}