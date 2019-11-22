
package com.blogrecette.servlets;
import java.io.IOException;
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
import com.blogrecette.model.Ingredient;
import com.blogrecette.model.Recette;
import com.blogrecette.model.Tag;
import com.blogrecette.services.CategorieService;
import com.blogrecette.services.IngredientService;
import com.blogrecette.services.MembreService;
import com.blogrecette.services.RecetteService;
import com.blogrecette.services.TagService;
/**
* Servlet implementation class Tags
*/
@WebServlet(name = "Tag", urlPatterns = { "/tag" })
public class TagServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
   /**
    * @see HttpServlet#HttpServlet()
    */
   public TagServlet() {
       super();
       // TODO Auto-generated constructor stub
   }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

            TagService tagService= new TagService();
           
			try {
			ArrayList<Tag> tags = tagService.getAllTag();
			  request.setAttribute("tags", tags);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            // on envoie listRecettegetAll sous le nom de listeRecette
            
          
            this.getServletContext().getRequestDispatcher("/WEB-INF/tag.jsp").forward(request, response);
       
   
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
    
        String info = "";
        Tag tag = new Tag(request.getParameter("nom"), new ArrayList<Recette>());
    
        String nom = request.getParameter("nom");
       
        if (nom.isEmpty()) {
        	info += "Veuillez saisir un nom de tag <br>";
        }
        
       
            	
				TagService tagService = new TagService();
				try {
					tagService.createTag(tag );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
        doGet(request, response);
    }
}
