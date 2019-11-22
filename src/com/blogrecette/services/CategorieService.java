/**
 * 
 */
package com.blogrecette.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Ingredient;
import com.blogrecette.utils.HibernateUtil;
import com.sun.org.apache.bcel.internal.generic.Select;



/**
 * @author HB
 *
 */
public class CategorieService {


	public CategorieService () {
		
	}

	public Categorie createCategorie(Categorie categorie) throws Exception {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.save(categorie);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return categorie;

	}



	public Categorie getCategorieFromId(int id) throws Exception { 
		Categorie categorie = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			categorie = session.get(Categorie.class, id);
	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return categorie;
	} 



	public ArrayList<Categorie> getAllCategorie() throws Exception {
		ArrayList<Categorie>allCategories = new ArrayList<Categorie>();
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			 String hql = "from Categorie";
			 Query query = session.createQuery(hql);
			 allCategories = (ArrayList<Categorie>) query.getResultList();
		 } catch (Exception e) {
	            e.printStackTrace();
	     }
		return allCategories;

	}	


	public ArrayList<Categorie> getCategorieFromRecette(int idRecette) throws Exception {     //selectionne tout les commentaire des recette
		ArrayList<Categorie>AllCatfromRec = new ArrayList<Categorie>();
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			 String hql = "SELECT c FROM Categorie c JOIN c.recette r "
				 		+ "WHERE r.id = :id";
				 Query query = session.createQuery(hql);
				 query.setParameter("id", idRecette);
				 AllCatfromRec = (ArrayList<Categorie>) query.getResultList();
		}

		return AllCatfromRec;


	}





	public void updateCategorie (Categorie categorie) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.update(categorie);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}




	}

	public void  deleteCategorie(Categorie categorie) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (categorie != null) {
				session.remove(categorie);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();


		}
	}
}
