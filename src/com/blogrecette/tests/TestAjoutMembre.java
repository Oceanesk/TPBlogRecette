/**
 * 
 */
package com.blogrecette.tests;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecette.model.Membre;
import com.blogrecette.servlets.RecetteServlet;
import com.blogrecette.utils.HibernateUtil;

/**
 * @author HB
 *
 */
public class TestAjoutMembre {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception  {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Membre membre = new Membre( "Pedro", "Vasquez","PV@gmail.com", new Date(), "mexico");
			session.save(membre);
			session.flush() ;
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




