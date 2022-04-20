package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bll.ArticleManager;
import model.bll.CategorieManager;
import model.bll.EnchereManager;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Enchere;

@WebServlet("/detailVente")
public class DetailVenteServlet extends HttpServlet{

	  public DetailVenteServlet() {
}
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  HttpSession session = request.getSession();
		  ArticleManager articleManager = new ArticleManager();
		  EnchereManager enchereManager = new EnchereManager();
		  ArticleVendu article = new ArticleVendu();
		  Enchere enchere = new Enchere();
		  article = articleManager.afficherUnArticle(1); //a lier avec la session?
		  request.setAttribute("article", article);

		  try {
			enchere = enchereManager.afficherUneEnchere(article.getNoArticle());
			request.setAttribute("enchere", enchere);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);
	  }
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	  
	  
	  }
  }


