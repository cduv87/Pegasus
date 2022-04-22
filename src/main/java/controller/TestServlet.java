package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bll.BusinessException;
import model.bll.MethodesTest;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		//Copier le code entre début de test et fin de test dans AccueilServlet pour tester les fonctions.
		//
		System.out.println("Debut du test");
		MethodesTest test = new MethodesTest();

		//Creation d'un utilisateur en base de donnee.
		try {
//			A decommenter apres recreation de la base pour le premier run de test puis a recommenter.
//			test.testAjouterUnUtilisateur();
//			test.testAjouterLesCategories();
//			test.testAjouterArticleVendu();
//			test.testAjoutEnchere();
//			test.testAjoutRetrait(1);
			
//			test.testEffacerTousRetraits();
//			test.testEffacerToutesEncheres();
//			test.testEffacerTousArticles();
//			test.testEffacerToutesCategories();
//			test.testEffacerTousUtilisateurs();

			test.testAjouterUnUtilisateur();
			test.testAjouterLesCategories();
			test.testAjouterArticleVendu();
			test.testAjouterArticlesVendusRandom();
			test.testAjoutEnchere();
			test.testAjoutRetrait(1);
			
			test.testAfficherTousUtilisateurs();
			test.testAfficherToutesCategories();
			test.testAfficherTousArticles();
			test.testAfficherToutesEncheres();
			test.testAfficherTousRetraits();

			test.testAfficherUnUtilisateur(4);
			test.testAfficherUneCategorie(2);
			test.testAfficherUnArticle(1);
			test.testAfficherUneEnchere(1);
			
			test.testAjouterArticlesVendusRandom();
			test.testAjouterEncheresVendusRandom();
			
//			test.testModifierUtilisateur(2);
//			test.testModifierCategorie(3);
//			test.testModifierArticle(1);
//			test.testModifierEnchere(1);

//			test.testEffacerUnUtilisateur();
//			
//			test.testConnexionUtilisateur();
//			test.testImport();
			
			request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);

			
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Fin du test");
		}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
