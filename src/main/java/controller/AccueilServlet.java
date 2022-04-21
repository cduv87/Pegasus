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
	
	private static boolean[] filtre = {false,false,false,false};
	
	private static ArticleManager articleManager = new ArticleManager();
	private static CategorieManager categorieManager = new CategorieManager();
	private static EnchereManager enchereManager = new EnchereManager();

    public AccueilServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		//final String[] params = { request.getParameter("filtre") , request.getParameter("filtreMesAchatsEncheresOuvertes") , request.getParameter("filtreMesAchatsEncheres") , request.getParameter("filtreMesAchatsEncheresRemportees") };
		
		if( session.getAttribute("utilisateurConnecte") != null ) {
			if( request.getParameter("filtre") != null ) {
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
			for (Boolean f : filtre) {
				System.out.print(f+",");
			}
			//System.out.println();
			//System.out.println(((Utilisateur)session.getAttribute("utilisateurConnecte")).getNoUtilisateur());
			if(request.getParameter("filtreCategorie")!=null)
				request.setAttribute("donneesCartels", recupererDonneesCartels(request.getParameter("filtreTexte"),Integer.parseInt(request.getParameter("filtreCategorie")),filtre,(Utilisateur)session.getAttribute("utilisateurConnecte")));
			else
				request.setAttribute("donneesCartels", recupererDonneesCartels(request.getParameter("filtreTexte"),0,filtre,(Utilisateur)session.getAttribute("utilisateurConnecte")));
		} else {
			if(request.getParameter("filtreCategorie")!=null)
				request.setAttribute("donneesCartels", recupererDonneesCartels(request.getParameter("filtreTexte"),Integer.parseInt(request.getParameter("filtreCategorie")),null,null));
			else
				request.setAttribute("donneesCartels", recupererDonneesCartels(request.getParameter("filtreTexte"),0,null,null));
		}
		
		request.setAttribute("nomsCategorie", categorieManager.afficherToutesCategories());
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("donneesCartels", recupererDonneesCartels("",0,null,null));
		request.setAttribute("nomsCategorie", categorieManager.afficherToutesCategories());
		
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);

	}
	
	private boolean isMesEncheres() {
		try {
			List<Enchere> encheres = enchereManager.afficherToutesEncheres();
			
			for (Enchere enchere : encheres) {
				//if()
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	private List<Object> recupererDonneesCartels(String searchText, int noCategorie, boolean[] filtre, Utilisateur u) {
		List<ArticleVendu> articles = articleManager.afficherTousArticles();
		List<Object> donneesCartels = new ArrayList<Object>();
		
		/*try {
			List<Enchere> encheres = enchereManager.afficherToutesEncheres();
			encheres.get(0).get
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		for(ArticleVendu article : articles) {
			if( /*article.isEtatVente() &&*/ ( searchText == null || searchText.equals("") || article.getNomArticle().toLowerCase().contains(searchText.toLowerCase()) ) &&

					( noCategorie == 0 || article.getCategorieArticle().getNoCategorie() == noCategorie ) ) {
				if( filtre == null ||
						filtre[0] == true && 
							article.getUtilisateur().getNoUtilisateur() != u.getNoUtilisateur() &&  
								( !filtre[1] && !filtre[2] && !filtre[3] ||
									filtre[1] == true && article.isEtatVente() ||
										filtre[2] == true && article.getDateDebutEncheres().isBefore(LocalDate.now())/* || 
											filtre[3] == true && article.getDateFinEncheres().isAfter(LocalDate.now())*/ ) ||
						filtre[0] == false && 
							article.getUtilisateur().getNoUtilisateur() == u.getNoUtilisateur() && 
								( !filtre[1] && !filtre[2] && !filtre[3] ||
									filtre[1] == true && article.isEtatVente() ||
										filtre[2] == true && article.getDateDebutEncheres().isBefore(LocalDate.now()) || 
											filtre[3] == true && article.getDateFinEncheres().isBefore(LocalDate.now()) ) ) {
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

