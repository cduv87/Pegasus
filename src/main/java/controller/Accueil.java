package controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bll.BusinessException;
import model.bll.UtilisateurManager;
import model.bo.Utilisateur;
import model.dal.UtilisateurDAO;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
	System.out.println("Début du test");
	//Creation d'un utilisateur en base de donnée.
	UtilisateurManager utilisateurManager = new UtilisateurManager();
	try {
		utilisateurManager.seConnecter();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		Utilisateur userTest = new Utilisateur();
		userTest.setPseudo("cduv2");
		userTest.setNom("Duval2");
		userTest.setPrenom("Clement2");
		userTest.setEmail("clement.duval2");
		userTest.setTelephone("072");
		userTest.setRue("AA2");
		userTest.setCodePostal("29590");
		userTest.setVille("Quimper");
		userTest.setMotDePasse("motdepasse");
		userTest.setCredit(100);
		boolean administrateur = false;
		userTest.setAdministrateur(administrateur);
		
		utilisateurManager.ajouterUtilisateur(userTest);
		System.out.println("Ajout utilisateur réussi");

		System.out.println("DEBUT Affichage de tous les utilisateurs");

		ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
		for (Utilisateur utilisateur : listeUtilisateurTemp) {
			System.out.println(utilisateur.toString());
		}
		System.out.println("FIN Affichage de tous les utilisateurs");
		
	} catch (BusinessException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	System.out.println("Milieu du test");
	System.out.println("Fin du test");
	
	
	}

	
}
