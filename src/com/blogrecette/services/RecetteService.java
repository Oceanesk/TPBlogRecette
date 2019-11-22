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
import org.omg.CORBA.PUBLIC_MEMBER;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Membre;
import com.blogrecette.model.Recette;
import com.blogrecette.utils.HibernateUtil;


/**
 * @author HB
 *
 */
public class RecetteService {

	private Connection connection; 

	public RecetteService (Connection connection) {
		this.connection = connection; 
	}

	public RecetteService() {
	
	}

	public Recette createRecette(Recette recette) throws Exception {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.save(recette);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return recette;
	}


	public Recette getRecetteFromId(int id) throws SQLException {		
		Recette recette = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			recette = session.get(Recette.class, id);
		} catch (Exception e) {
			
			e.printStackTrace();


		}
		recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getId()));

		return recette;





	}


	public ArrayList<Recette> getAll() throws Exception {
		ArrayList<Recette>allRecettes = new ArrayList<Recette>();
	
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Recette";
			Query query = session.createQuery(hql);
			allRecettes= (ArrayList<Recette>) query.getResultList();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		for ( Recette recette :allRecettes) {

        recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getId()));
		
	}

		return allRecettes;
	}



	public ArrayList<Recette> getRecetteByCategorie(int idCategorie) throws Exception {    //recupère toute les recettes par categorie
		ArrayList<Recette>allRecettes = new ArrayList<Recette>();
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT r FROM Recette r Join r.categorie c "
					+ "WHERE c.id= :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", idCategorie);
			allRecettes= (ArrayList<Recette>) query.getResultList();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		for (Recette recette : allRecettes) {
			recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getId()));
	}

		return allRecettes;
	}

	public List<Recette> getRecetteByTag(int idTag) throws Exception {    //recupère toute les recettes par categorie
		Transaction transaction = null;
		List<Recette> recettebytags = null;

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			recettebytags = session.createQuery("from Recette").getResultList();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();

		}

		return recettebytags;
	}

	public List<Recette> getRecetteByIngredient(int idIngredient) throws SQLException {    //recup tout les ingrédients des recette

		List<Recette> recettebyIngredients = new ArrayList<>();

		String query = "SELECT * FROM recette WHERE idIngredient=?";

		PreparedStatement pStatement = connection.prepareStatement(query);
		pStatement.setInt(1, idIngredient);
		ResultSet rSet = pStatement.executeQuery();

		while (rSet.next()) {												
			Recette recette = new Recette();

			recette.setId(rSet.getInt("id"));
			recette.setTitre(rSet.getString("titre"));
			recette.setDescription(rSet.getString("description"));
			recette.setPhoto(rSet.getString("photo"));
			recette.setDateCreation(rSet.getDate ("dateCreation"));
			recettebyIngredients.add(recette);
		}

		return recettebyIngredients;

	}





	public void updateRecette (Recette recette) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.update(recette);
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


	

	public void deleteRecette (Recette recette) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (recette != null) {
				session.delete(recette);
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
	
	public int moyNoteRecetteByRecette (int idRecette) {
		int note = 0;
		try(Session session = HibernateUtil.getSessionFactory()
				.openSession()) {
			String hql = "SELECT FLOOR(AVG(c.note)) FROM Recette R "
					+ "JOIN r.commentaire c "
					+ "WHERE r.id =:id";
			Query query = session.createQuery(hql);
			query.setParameter("id", idRecette);
		
		try {
			note= (int) query.getSingleResult();
			
		}catch (Exception e) {
			 e.printStackTrace();		
			 note =0;
			 }
		
	}catch (Exception e) {
		 e.printStackTrace();	
	}
	
		return note;

	
}
}