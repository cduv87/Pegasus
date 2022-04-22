package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bll.ArticleManager;
import model.bll.UtilisateurManager;
import model.bo.ArticleVendu;
import model.bo.Utilisateur;

public class AffichVenteServlet extends HttpServlet{
	private ArticleManager articleManager = new ArticleManager();
	private UtilisateurManager utilisateurManager = new UtilisateurManager();
	
	 public AffichVenteServlet() {}
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/affichVente.jsp").forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			Integer numArticle = 0;
			ArticleVendu articleVendu = new ArticleVendu();
			numArticle = Integer.parseInt(request.getParameter("noArticle"));
			
			
			try {
				articleVendu = articleManager.afficherUnArticle(numArticle);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("profil", utilisateur);
			request.getRequestDispatcher("/WEB-INF/affichProfil.jsp").forward(request, response);

			
		}

		}
}
