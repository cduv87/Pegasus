package controller;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import model.bo.Categorie;
import model.dal.ConnectionProvider;


import model.bll.ArticleManager;
import model.bll.BusinessException;
import model.bll.CategorieManager;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;





/**
 * Servlet implementation class Accueil
 */	
@WebServlet("/")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static boolean[] filtre = {false,false,false,false};
	
	private ArticleManager articleManager = new ArticleManager();
	private CategorieManager categorieManager = new CategorieManager();

    public AccueilServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		if( session.getAttribute("utilisateurConnecte") != null ) {
			filtre[0] = (request.getParameter("filtre").equals("achats")?true:false);
			if(filtre[0]) {
				filtre[1] = (request.getParameter("filtreMesAchatsEncheresOuvertes")==null?false:true);
				filtre[2] = (request.getParameter("filtreMesAchatsEncheres")==null?false:true);
				filtre[3] = (request.getParameter("filtreMesAchatsEncheresRemportees")==null?false:true);
			} else {
				filtre[1] = (request.getParameter("filtreMesVentesEnCours")==null?false:true);
				filtre[2] = (request.getParameter("filtreMesVentesNonDebutees")==null?false:true);
				filtre[3] = (request.getParameter("filtreMesVentesTerminees")==null?false:true);
			}
		}
		
		request.setAttribute("donneesCartels", recupererDonneesCartels(request.getParameter("filtreTexte"),Integer.parseInt(request.getParameter("filtreCategorie"))));
		request.setAttribute("nomsCategorie", categorieManager.afficherToutesCategories());
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("donneesCartels", recupererDonneesCartels("",0));
		request.setAttribute("nomsCategorie", categorieManager.afficherToutesCategories());
		
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);

	}
	
	private List<Object> recupererDonneesCartels(String searchText, int i) {
		List<ArticleVendu> articles = articleManager.afficherTousArticles();
		List<Object> donneesCartels = new ArrayList<Object>();
		
		for(ArticleVendu article : articles) {
			if( /*article.isEtatVente() &&*/ ( searchText == null || searchText.equals("") || article.getNomArticle().toLowerCase().contains(searchText.toLowerCase()) ) &&
					( i == 0 || article.getCategorieArticle().getNoCategorie() == i ) ) {
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
}

