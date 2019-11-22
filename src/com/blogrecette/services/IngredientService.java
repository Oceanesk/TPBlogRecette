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
import com.blogrecette.model.Membre;
import com.blogrecette.model.Recette;
import com.blogrecette.utils.HibernateUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @author HB
 *
 */
public class IngredientService {



	public IngredientService() {
		// TODO Auto-generated constructor stub
	}

	public void createIngredient(Ingredient ingredient) throws Exception {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.save(ingredient);
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


	public Ingredient getIngredientById(int id) throws SQLException{

		Ingredient ingredient = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			ingredient = session.get(Ingredient.class, id);
			
		} catch (Exception e) {
		
			
			e.printStackTrace();

		}

		return ingredient;

	}


	public ArrayList<Ingredient> getAllIngredient() throws Exception {
	ArrayList<Ingredient>allIngredient  =new ArrayList<Ingredient>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Ingredient";
			Query query= session.createQuery(hql);
			allIngredient =(ArrayList<Ingredient>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return allIngredient;
	}	



	public ArrayList<Ingredient> getIngredientByRecette(int idRecette) throws Exception {     //selectionne tout les commentaire des recette
	ArrayList<Ingredient>allIngredient = new ArrayList<Ingredient>();

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			 String hql = "SELECT i FROM Ingredient i JOIN i.recette r "
				 		+ "WHERE r.id = :id";
				 Query query = session.createQuery(hql);
				 query.setParameter("id", idRecette);
				 allIngredient = (ArrayList<Ingredient>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allIngredient;




	}






	public Ingredient ingredientUpdate(Ingredient ingredient) throws SQLException {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.update(ingredient);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}


		return ingredient;

	}

	public void  ingredientDelete(Ingredient ingredient) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (ingredient != null) {
				session.delete(ingredient);
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
