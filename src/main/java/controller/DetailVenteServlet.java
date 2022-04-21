package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
import model.bll.UtilisateurManager;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Enchere;
import model.bo.Utilisateur;

@WebServlet("/detailVente")
public class DetailVenteServlet extends HttpServlet{
	 Integer no_article = null;
	 
	  public DetailVenteServlet() {
		 
}
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	  }
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateurConnecte");

			ArticleManager articleManager = new ArticleManager();
			EnchereManager enchereManager = new EnchereManager();
			UtilisateurManager utilisateurManager = new UtilisateurManager();
			
			ArticleVendu article = new ArticleVendu();
			Enchere enchere = new Enchere();
			utilisateurConnecte.setCredit(2000);
			try {
				utilisateurManager.modifierUtilisateur(utilisateurConnecte);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if (no_article == null) {
				no_article = Integer.parseInt(request.getParameter("no_article"));
			}
			article = articleManager.afficherUnArticle(no_article);
			request.setAttribute("article", article);

			try {
				enchere = enchereManager.afficherUneEnchere(article.getNoArticle());
				request.setAttribute("enchere", enchere);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Integer meilleureOffre = article.getPrixVente();

			Integer nouvelleOffre = null;

			if (request.getParameter("nouvelleEnchere") != null) {
				nouvelleOffre = Integer.parseInt(request.getParameter("nouvelleEnchere"));
			
			if (nouvelleOffre > article.getMiseAPrix() && nouvelleOffre > meilleureOffre && nouvelleOffre <= utilisateurConnecte.getCredit()) {
				meilleureOffre = nouvelleOffre;
				
				enchere.setArticle(article);
				enchere.setDateEnchere(LocalDate.now());
				enchere.setMontant_enchere(meilleureOffre);
				enchere.setUtilisateur(utilisateurConnecte);
				article.setPrixVente(meilleureOffre);
				utilisateurConnecte.setCredit(utilisateurConnecte.getCredit()-meilleureOffre);
				
			try {
				enchereManager.modifierEnchere(enchere);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			articleManager.modifierUnArticle(article);
			}
			try {
				utilisateurManager.modifierUtilisateur(utilisateurConnecte);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			request.getRequestDispatcher("/WEB-INF/detailVente.jsp").forward(request, response);

	}
}


