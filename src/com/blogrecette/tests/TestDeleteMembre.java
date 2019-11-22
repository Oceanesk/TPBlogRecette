/**
 * 
 */
package com.blogrecette.tests;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.blogrecette.model.Membre;

/**
 * @author HB
 *
 */
public class TestDeleteMembre {

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
			String hql = "FROM Membre M";
			Query query = session.createQuery(hql);
			List avantSuppression = query.getResultList();
			Membre membre = (Membre)session.load(Membre.class,3);
			session.delete(membre);
			session.flush() ;
			tx.commit();
			Query query2 = session.createQuery(hql);
			List apresSuppression = query2.getResultList();
			System.out.println(apresSuppression);
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}

	}

}
