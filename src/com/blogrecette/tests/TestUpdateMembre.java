/**
 * 
 */
package com.blogrecette.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecette.model.Membre;

/**
 * @author HB
 *
 */
public class TestUpdateMembre {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		 SessionFactory sessionFactory = sf;
		 Session session = sessionFactory.openSession();
		 Transaction tx = null;
		 try {
		 tx = session.beginTransaction();
		 Membre membre = (Membre) session.load(Membre.class, 4);
		 membre.setNom("PereFouettard");
		 tx.commit();
		 } catch (Exception e) {
		 if (tx != null) {
		 tx.rollback();
		 }
		 throw e;
		 } finally {
		 session.close();
		 }
		 sessionFactory.close();
	}

}
