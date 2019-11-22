/**
 * 
 */
package com.blogrecette.tests;

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
public class TestGetMembreFromId {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			String hql = "FROM Membre M where M.id = 1";
			Query query = session.createQuery(hql);
			List list = query.getResultList();
			Membre membre = (Membre) list.toArray()[0];
			System.out.println(membre);
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
