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
import com.blogrecette.model.Recette;
import com.blogrecette.model.Tag;
import com.blogrecette.utils.HibernateUtil;
import com.sun.org.apache.bcel.internal.generic.Select;



/**
 * @author HB
 *
 */
public class TagService {
	
	public TagService () {
		
	}

	public Tag createTag(Tag tag) throws Exception {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.save(tag);
				session.flush();
				
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return tag;
	

	}



	public Tag getTagFromId(int id) throws Exception { 
	
		Tag tag = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
			tag = session.get(Tag.class, id);
	
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		return tag;
	} 



	public ArrayList<Tag> getAllTag() throws Exception {
		ArrayList<Tag>allTags = new ArrayList<Tag>();
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "From Tag";
			Query query = session.createQuery(hql);
			allTags= (ArrayList<Tag>) query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		
			}
		
		
		return allTags;
	}	


	public ArrayList<Tag> getTagFromRecette(int idRecette) throws Exception {     //selectionne tout les commentaire des recette
	ArrayList<Tag> tagFromRecette = new ArrayList<Tag>();

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql =  "SELECT t FROM Tag t JOIN t.recette recette WHERE recette.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", idRecette);
			tagFromRecette = (ArrayList<Tag>) query.getResultList();
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}

		return tagFromRecette;


	}





	public void updateTag (Tag tag) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.update(tag);
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

	public void  deleteTag(Tag tag) throws Exception {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (tag != null) {
				session.delete(tag);
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
