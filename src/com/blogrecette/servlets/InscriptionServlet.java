package com.blogrecette.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blogrecette.model.Membre;
import com.blogrecette.services.MembreService;

import sun.misc.CEFormatException;


/**
 * Servlet implementation class Inscription
 */
@WebServlet(name = "Inscription", urlPatterns = { "/inscription" })
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InscriptionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();



		String info = "";
		String success = "Bienvenue";
		Date dateInscription = null;

		String nom = request.getParameter("nom");
		if (nom.isEmpty()) {
			info += "Veuillez saisir un nom <br>";
		}

		String pseudo = request.getParameter("pseudo");
		if (pseudo.isEmpty()) {
			info += "Veuillez saisir un pseudo <br>";
		}

		String email = request.getParameter("email");
		if (email.isEmpty()) {
			info += "Veuillez saisir un mail <br>";
		}

		try {
			dateInscription =new Date();
			
		} catch (IllegalArgumentException e) {
			info += "Veuillez saisir une date <br>";
		}
		
		String mdp = request.getParameter("mdp");
		if (mdp.isEmpty()) {
			info += "Veuillez saisir un mot de passe <br>";
		}

		String mdpconf = request.getParameter("mdpconf");
		if (mdpconf.isEmpty()) {
			info += "Veuillez confirmer votre mot de passe <br>";
		}

		request.setAttribute("info", info);
	



		Membre membre=new Membre();
		membre.setNom(nom);
		membre.setPseudo(pseudo);
		membre.setEmail(email);
		membre.setMdp(mdpconf);
		membre.setDateInscription(dateInscription);







		if (info.trim().isEmpty()) {
			
				MembreService membreservice = new MembreService();
				try {
					membreservice.createMembre(membre);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			request.setAttribute("success", success);
			session.setAttribute("member", membre);

			response.sendRedirect("index");
			
		}else {
			request.setAttribute("member", membre);
			this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(request, response);

		}








		//this.getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(request, response);//Return like



	}
}

