package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Commentaire;
import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Membre;
import com.blogrecette.model.Recette;
import com.blogrecette.services.CategorieService;
import com.blogrecette.services.CommentaireService;
import com.blogrecette.services.IngredientService;
import com.blogrecette.services.MembreService;
import com.blogrecette.services.RecetteService;
import com.blogrecette.services.TagService;
import com.blogrecette.utils.HibernateUtil;;

/**
 * Servlet implementation class Recette
 */
@WebServlet(name="Recette", urlPatterns = {"/recette"})
public class RecetteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecetteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		ArrayList<Recette>recettesCategorie =new ArrayList<Recette>();
		ArrayList<Recette>recettesCategorie2 =new ArrayList<Recette>();
		ArrayList<Commentaire>allCommentaires =new ArrayList<Commentaire>();
		ArrayList<Categorie>categories =new ArrayList<>(); 
		ArrayList<Ingredient>allIngredients =new ArrayList<Ingredient>();
				        
	        int noteMoyenneByRecette;
	        Recette recette = new Recette();
	        
	try {

			RecetteService recetteService = new RecetteService();
			CommentaireService commentaireService = new CommentaireService();
			CategorieService categorieService = new CategorieService();
			IngredientService ingredientService = new IngredientService();
			
			categories = categorieService.getAllCategorie();
			
			allIngredients = ingredientService.getIngredientByRecette(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("Ingredients", allIngredients);

			recette = recetteService.getRecetteFromId(Integer.parseInt(request.getParameter("id")));
			session.setAttribute("recette", recette);

			recettesCategorie2 = recetteService.getRecetteByCategorie(Integer.parseInt(request.getParameter("id")));
			session.setAttribute("recettesCategorie2", recettesCategorie2);
		
			allCommentaires = commentaireService.getCommentaireByRecette(Integer.parseInt(request.getParameter("id")));
			session.setAttribute("allCommentaires", allCommentaires);
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}




		this.getServletContext().getRequestDispatcher( "/WEB-INF/recette.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		

		String info = "";
		Date dateCreation = new Date();

		String auteur = request.getParameter("auteur");
		if(auteur.isEmpty()) {
			info +="Veuillez saisir un nom <br>";
		}
		String contenu = request.getParameter("contenu");
		if(contenu.isEmpty()) {
			info +="Veuillez saisir un commentaire <br>";
		}

		int note = Integer.parseInt(request.getParameter("note"));
		int idRecette = Integer.parseInt(request.getParameter("id"));
		
		RecetteService rService = new RecetteService();
		Recette recette = null;
	
			try {
				
				recette = rService.getRecetteFromId(idRecette);
				
		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("info", info);

			Commentaire commentaire = new Commentaire();
			commentaire.setAuteur(auteur);
			commentaire.setContenu(contenu);
			commentaire.setNote(note);
			commentaire.setDateCreation(new java.util.Date());
			
			if (info.trim().isEmpty()) {
				CommentaireService commentaireService = new CommentaireService();
				try {
					commentaireService.createCommentaire(commentaire);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("commentaire", commentaire);
				this.doGet(request, response);
	     
	            
	 






		}else {
			request.setAttribute("commentaire", commentaire);
			//response.sendRedirect("recette?id="+request.getParameter("id"));
			this.doGet(request, response);
			//this.getServletContext().getRequestDispatcher("/WEB-INF/recette.jsp").forward(request, response);//Return like

		}



	}
}

