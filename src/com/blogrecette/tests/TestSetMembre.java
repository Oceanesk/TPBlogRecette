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
public class TestSetMembre {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			String hql = "FROM Membre M WHERE M.dateInscription = :date";
			Query query = session.createQuery(hql)
					.setParameter("date", new Date());
			List results = query.getResultList();

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


