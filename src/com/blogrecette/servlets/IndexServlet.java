package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.model.Categorie;
import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Recette;
import com.blogrecette.services.CategorieService;
import com.blogrecette.services.CommentaireService;
import com.blogrecette.services.IngredientService;
import com.blogrecette.services.RecetteService;


/**
 * Servlet implementation class Index
 */
@WebServlet(name="Index", urlPatterns = {"","/index"})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Recette> Allrecettes =new ArrayList<>();
		ArrayList<Categorie> allCategories = new ArrayList<Categorie>();
		ArrayList<Ingredient> allIngredients = new ArrayList<Ingredient>();
		int moyenneById=0;

		try {
			
		
			RecetteService recetteService = new RecetteService(); //creer un objet RecetteService   
			CategorieService categorieService = new CategorieService();
			CommentaireService commentaireService = new CommentaireService();
			IngredientService ingredientService = new IngredientService();
			
			Allrecettes = recetteService.getAll();
			request.setAttribute("recettes", Allrecettes);      
			
			allCategories = categorieService.getAllCategorie();
			request.setAttribute("categories",allCategories);
			
			
			
			
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		
		 this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

}
