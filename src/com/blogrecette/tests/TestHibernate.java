/**
 * 
 */
package com.blogrecette.tests;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.blogrecette.model.Cni;
import com.blogrecette.model.Membre;

class TestHibernate {
	protected Session session;
	protected SessionFactory sessionFactory;

	public static void main(String args[]) throws Exception {

		Transaction tx = null;
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();
		tx = session.beginTransaction();


		Cni cni = new Cni("Santa","SantaKlaus",1234);
		session.save(cni);
		
		Membre membre = new Membre("Santa", "SantaKlaus","Xmax@gmail.com", new Date(), "elfe");
		membre.setCni(cni);
		session.save(membre);
		session.flush();
		
		Cni cni1 = membre.getCni();
		tx.commit();

		try {
		} catch (Exception e) {
			e.printStackTrace();
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


