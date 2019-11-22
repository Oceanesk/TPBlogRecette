package com.blogrecette.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Recette;
import com.blogrecette.utils.HibernateUtil;

public class CommentaireService {


	public CommentaireService() {
		// TODO Auto-generated constructor stub
	}

	public Commentaire createCommentaire(Commentaire commentaire) throws Exception {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.save(commentaire);
				session.flush();
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return commentaire;
	}

	

	
	
	/*public int moyenneCommentaire(int idRecette) throws Exception {
		String query = "SELECT floor(AVG(note)) as moy FROM commentaire WHERE idRecette = ?";
		PreparedStatement pStatement = connection.prepareStatement(query);
		int moy= 0;
		pStatement.setInt(1, idRecette);
		
		ResultSet rSet = pStatement.executeQuery();
		if (rSet.next()) {
			 moy = rSet.getInt("moy");
		}
	
		
		return moy;
		 
		
		
		
		
	} */
	
	public Double getNoteAverageFromRecette(int id) throws Exception {
        //On initialise noteAverage à null
        Double noteAverage = null;
        
        //On prepare le requette HQL
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
             String hql = "SELECT floor(avg(commentaire.note)) FROM Commentaire commentaire "
             		+ "JOIN commentaire.recette recette "
             		+ "WHERE recette.id = :id";
             
              Query query = session.createQuery(hql);
                query.setParameter("id", id);
                noteAverage = (Double) query.getSingleResult();
         } catch (Exception e) {
             e.printStackTrace();
        }
        
        return noteAverage;
    }

	public Commentaire getCommentaireFromId(int id) throws Exception {		
		Commentaire commentaire = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			commentaire = session.get(Commentaire.class, id);
		} catch (Exception e) {
	
			e.printStackTrace();
		}


		return commentaire;

	}
		public ArrayList<Commentaire> getAllCommentaire() throws Exception {
			ArrayList<Commentaire>allCommentaire = new ArrayList<Commentaire>();
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				 String hql = "from Commentaire";
				 Query query = session.createQuery(hql);
				 allCommentaire = (ArrayList<Commentaire>) query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			
		
			}
			return allCommentaire;
		}	
		
	

	public ArrayList<Commentaire> getCommentaireByRecette(int idRecette) throws Exception {     //selectionne tout les commentaire des recette
		ArrayList<Commentaire> allCommentaires = new ArrayList<Commentaire>();
	
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT c FROM Commentaire c JOIN c.recette recette WHERE recette.id = :id";
			 Query query = session.createQuery(hql);
			 query.setParameter("id", idRecette);
			 allCommentaires = (ArrayList<Commentaire>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allCommentaires;


	}




	public void updateCommentaire (Commentaire commentaire) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.update(commentaire);
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


	public void deleteCommentaire (Commentaire commentaire) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (commentaire != null) {
				session.delete(commentaire);
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

