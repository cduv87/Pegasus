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

import model.bll.ArticleManager;
import model.bll.BusinessException;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;






/**
 * Servlet implementation class Accueil
 */	
@WebServlet("/")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleManager articleManager = new ArticleManager();

    public Accueil() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);
	
	
		System.out.println("DÃ©but du test");
		
		Utilisateur u = new Utilisateur(1, "phil", "peron", "phileas", "phileas.peron2022@campus-eni.fr", "0666666666", "4 rue de la paix", "42000", "VILLE", "superMdp", 0, false);
		Categorie c1 = new Categorie(1, "ameublement");
		Categorie c2 = new Categorie(1, "jardin");
		ArticleVendu a1 = new ArticleVendu(0, "chaise ergonomique", "tout confort, pac cher", LocalDate.of(2020, 10, 25), LocalDate.of(2022, 11, 29), 50, 100, true, c1, u);
		ArticleVendu a2 = new ArticleVendu(0, "fourche bêche", "idéal pour préparer la terre de son potager", LocalDate.of(2020, 10, 25), LocalDate.of(2022, 11, 29), 50, 100, true, c2, u);
		
		try {
			articleManager.add(a1);
			articleManager.add(a2);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		System.out.println(articleManager.getById(1));
		a2.setPrixVente(154);
		System.out.println("update");
		articleManager.update(a2);
		System.out.println("delete");
		articleManager.delete(a1);
		
	
		System.out.println("Fin du test");*/

		/*
		Utilisateur u1 = new Utilisateur(1, "phil", "peron", "phileas", "phileas.peron2022@campus-eni.fr", "0666666666", "4 rue de la paix", "42000", "VILLE", "superMdp", 0, false);
		Utilisateur u2 = new Utilisateur(1, "clem", "duval", "clement", "clement.duval2022@campus-eni.fr", "0677777777", "6 route des enfers", "66000", "Marseille", "chepas", 0, false);
		Categorie c1 = new Categorie(1, "informatique");
		Categorie c2 = new Categorie(2, "ameublement");
		Categorie c3 = new Categorie(3, "vetement");
		Categorie c4 = new Categorie(4, "sportLoisirs");
		ArticleVendu a1 = new ArticleVendu(0, "chaise ergonomique", "tout confort, pac cher", LocalDate.of(2020, 10, 25), LocalDate.of(2022, 11, 29), 50, 100, true, c2, u1);
		ArticleVendu a2 = new ArticleVendu(0, "fourche bêche", "idéal pour préparer la terre de son potager", LocalDate.of(2020, 10, 25), LocalDate.of(2022, 11, 29), 50, 100, true, c4, u1);
		ArticleVendu a3 = new ArticleVendu(0, "caravelle", "à utiliser que par beau temps", LocalDate.of(2021, 12, 25), LocalDate.of(2026, 6, 29), 5, 1000, true, c4, u2);
		ArticleVendu a4 = new ArticleVendu(0, "bêche", "obligatoire pour preparer la terre du potager", LocalDate.of(2019, 10, 25), LocalDate.of(2021, 11, 29), 50, 166, true, c4, u2);
		ArticleVendu a5 = new ArticleVendu(0, "chemise", "être beau en toutes circonstances est important", LocalDate.of(2019, 10, 25), LocalDate.of(2021, 11, 29), 10, 588, true, c3, u2);
		
		try {
			articleManager.add(a1);
			articleManager.add(a2);
			articleManager.add(a3);
			articleManager.add(a4);
			articleManager.add(a5);
		} catch (BusinessException e) {
			e.printStackTrace();
		}*/
		
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("donneesCartels", recupererDonneesCartels(request.getParameter("filtreTexte"),request.getParameter("filtreCategorie")));
		
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);
	}
	
	private List<Object> recupererDonneesCartels(String searchText, String searchCategory) {
		List<ArticleVendu> articles = this.articleManager.getAll();
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
