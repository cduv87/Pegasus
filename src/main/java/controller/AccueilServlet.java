package controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.bo.Categorie;
import model.dal.ConnectionProvider;


import model.bll.ArticleManager;
import model.bll.BusinessException;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;





/**
 * Servlet implementation class Accueil
 */	
@WebServlet("/")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleManager articleManager = new ArticleManager();

    public AccueilServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	


		
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("donneesCartels", recupererDonneesCartels(request.getParameter("filtreTexte"),request.getParameter("filtreCategorie")));
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);
	}

	
	private List<Object> recupererDonneesCartels(String searchText, String searchCategory) {
		List<ArticleVendu> articles = this.articleManager.afficherTousArticles();
		List<Object> donneesCartels = new ArrayList<Object>();
		
		for(ArticleVendu article : articles) {
			if( /*article.isEtatVente() &&*/ ( searchText == null || searchText.equals("") || article.getNomArticle().contains(searchText) ) &&
					( searchCategory == null || searchCategory.equals("all") || article.getCategorieArticle().getLibelle().equals(searchCategory) ) ) {
				List<Object> donneesCartel = new ArrayList<Object>();
				donneesCartel.add(article.getNomArticle());
				donneesCartel.add(article.getPrixVente());
				donneesCartel.add(article.getDateFinEncheres());
				donneesCartel.add(article.getUtilisateur().getPseudo());
				donneesCartel.add(article.getUtilisateur().getNoUtilisateur());
				donneesCartel.add(1);//no_enchere
				donneesCartels.add(donneesCartel);
			}
		}
		
		return donneesCartels;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("donneesCartels", recupererDonneesCartels("","all"));
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);

	}
}

