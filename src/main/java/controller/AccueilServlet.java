package controller;

import java.io.IOException;
import java.sql.SQLException;
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
import model.bo.Enchere;
import model.dal.ConnectionProvider;


import model.bll.ArticleManager;
import model.bll.BusinessException;
import model.bll.CategorieManager;
import model.bll.EnchereManager;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;





/**
 * Servlet implementation class Accueil
 */	
@WebServlet("")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static boolean[] filtres = {false,false,false,false};
	
	private static ArticleManager articleManager = new ArticleManager();
	private static CategorieManager categorieManager = new CategorieManager();
	private static EnchereManager enchereManager = new EnchereManager();

    public AccueilServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Utilisateur user = (Utilisateur)request.getSession().getAttribute("utilisateurConnecte");
		String filtreTexte = request.getParameter("filtreTexte");
		int filtreCategorie;
		if(request.getParameter("filtreCategorie")!=null)
			filtreCategorie = Integer.parseInt(request.getParameter("filtreCategorie"));
		else
			filtreCategorie = 0;
		
		if( user != null ) {
			if( request.getParameter("filtre") != null ) {
				filtres[0] = (request.getParameter("filtre").equals("achats")?true:false);
				if(filtres[0]) {
					filtres[1] = (request.getParameter("filtreAchatsEncheresOuvertes")==null?false:true);
					filtres[2] = (request.getParameter("filtreMesAchatsEncheres")==null?false:true);
					filtres[3] = (request.getParameter("filtreMesAchatsEncheresRemportees")==null?false:true);
				} else {
					filtres[1] = (request.getParameter("filtreMesVentesEnCours")==null?false:true);
					filtres[2] = (request.getParameter("filtreMesVentesNonDebutees")==null?false:true);
					filtres[3] = (request.getParameter("filtreMesVentesTerminees")==null?false:true);
				}
			}

			request.setAttribute("donneesCartels", recupererDonneesCartels(filtreTexte,filtreCategorie,filtres,user));
		} else {
			request.setAttribute("donneesCartels", recupererDonneesCartels(filtreTexte,filtreCategorie,null,null));
		}
		
		request.setAttribute("nomsCategorie", categorieManager.afficherToutesCategories());
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("donneesCartels", recupererDonneesCartels("",0,null,null));
		request.setAttribute("nomsCategorie", categorieManager.afficherToutesCategories());
		
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);

	}
	
	private boolean isMesEncheres(int no_user, int no_article) {
		try {
			List<Enchere> encheres = enchereManager.afficherToutesEncheres();
			
			for (Enchere enchere : encheres) {
				if( enchere.getArticle().getNoArticle() == no_article && enchere.getUtilisateur().getNoUtilisateur() == no_user )
					return true;
			}
			
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	private List<Object> recupererDonneesCartels(String searchText, int noCategorie, boolean[] filtres, Utilisateur u) {
		List<ArticleVendu> articles = articleManager.afficherTousArticles();
		List<Object> donneesCartels = new ArrayList<Object>();
		
		for(ArticleVendu article : articles) {
			/*if(filtres != null && article.getNoArticle()<9) {
				for (int i = 0; i < 4; i++) {
					System.out.print(filtres[i]);
				}
				System.out.println();
				System.out.println(article);
				System.out.println(article.getUtilisateur().getNoUtilisateur());
				System.out.println(u.getNoUtilisateur());
				//System.out.println(!filtres[1] && !filtres[2] && !filtres[3]);
				System.out.println(filtres[1] == true && article.isEtatVente());
				System.out.println(filtres[1] == true);
				System.out.println(article.isEtatVente());
				System.out.println();
			}*/
			if( /*article.isEtatVente() &&*/ ( searchText == null || searchText.equals("") || 
					article.getNomArticle().toLowerCase().contains(searchText.toLowerCase()) ) &&
					( noCategorie == 0 || article.getCategorieArticle().getNoCategorie() == noCategorie ) ) {
				if( filtres == null ||
						filtres[0] == true && 
							article.getUtilisateur().getNoUtilisateur() != u.getNoUtilisateur() &&  
								( !filtres[1] && !filtres[2] && !filtres[3] ||
									filtres[1] == true && article.isEtatVente() ||
										filtres[2] == true && isMesEncheres(article.getNoArticle(),u.getNoUtilisateur())/* || 
											filtres[3] == true && article.getDateFinEncheres().isAfter(LocalDate.now())*/ ) ||
						filtres[0] == false && 
							article.getUtilisateur().getNoUtilisateur() == u.getNoUtilisateur() && 
								( !filtres[1] && !filtres[2] && !filtres[3] ||
									filtres[1] == true && article.isEtatVente() ||
										filtres[2] == true && article.getDateDebutEncheres().isBefore(LocalDate.now()) || 
											filtres[3] == true && article.getDateFinEncheres().isBefore(LocalDate.now()) ) ) {
					List<Object> donneesCartel = new ArrayList<Object>();
					donneesCartel.add(article.getNomArticle());
					donneesCartel.add(article.getMiseAPrix());
					donneesCartel.add(article.getDateFinEncheres());
					donneesCartel.add(article.getUtilisateur().getPseudo());
					donneesCartel.add(article.getUtilisateur().getNoUtilisateur());
					donneesCartel.add(article.getNoArticle());
					donneesCartels.add(donneesCartel);
				}
			}
		}
		
		return donneesCartels;
	}
}

