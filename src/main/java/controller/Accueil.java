package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bll.BusinessException;
import model.bll.UtilisateurManager;






/**
 * Servlet implementation class Accueil
 */	
@WebServlet("")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Accueil() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);

	System.out.println("Debut du test");
	MethodesTest test = new MethodesTest();
	UtilisateurManager utilisateurManager = new UtilisateurManager();

	//Creation d'un utilisateur en base de donnee.
	try {
		test.effacerToutesEncheres();
		test.testEffacerTousArticles();
		test.testEffacerTousUtilisateurs();
		test.testEffacerToutesCategories();
		test.testAjouterUnUtilisateur();
		test.testAjouterLesCategories();
		test.testAjouterArticleVendu();
		test.testAjoutEnchere();
//		test.testAfficherTousUtilisateurs();
//		test.testAfficherUnUtilisateur();
		test.testAfficherUneCategorie(2);
		test.testAfficherUnArticle(1);
		test.testAfficherTousArticles();
		test.afficherToutesEncheres();
//		test.testEffacerUnUtilisateur();
//		test.testModifierUtilisateur();
//		test.testConnexionUtilisateur();
//		test.testImport();
		
		
	} catch (BusinessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	System.out.println("Fin du test");
	}	
	
	}


