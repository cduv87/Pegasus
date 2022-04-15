package controller;


import java.io.IOException;
import java.time.LocalDate;

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
@WebServlet("")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleManager articleManager = new ArticleManager();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);
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
	

	System.out.println("Fin du test");
	
	
	}

	
}
