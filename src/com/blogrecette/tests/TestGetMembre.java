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
public class TestGetMembre {

	public static void main(String[] args) throws Exception  {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		SessionFactory sessionFactory = sf;
		Session session = sessionFactory.openSession();

		Transaction tx = null;
		try {
			String hql = "FROM Membre";
			Query query = session.createQuery(hql);
			List results = query.getResultList();
			System.out.println(results);
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





