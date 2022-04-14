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
	System.out.println("Debut du test");
	
	//Creation d'un utilisateur en base de donn�e.
	UtilisateurManager utilisateurManager = new UtilisateurManager();
	try {
		utilisateurManager.seConnecter();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		Utilisateur userTest = new Utilisateur();
		userTest.setPseudo("PP");
		userTest.setNom("Perron");
		userTest.setPrenom("Phileas");
		userTest.setEmail("phileas.perron");
		userTest.setTelephone("06");
		userTest.setRue("PP");
		userTest.setCodePostal("29000");
		userTest.setVille("Quimper");
		userTest.setMotDePasse("motdepasse");
		userTest.setCredit(100);
		boolean administrateur = false;
		userTest.setAdministrateur(administrateur);
		
		utilisateurManager.ajouterUtilisateur(userTest);
		System.out.println("Ajout utilisateur reussi");

		
		//Affichage de tous les utilisateurs de la table Utilisateur
		System.out.println("DEBUT Affichage de tous les utilisateurs");

		ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
		for (Utilisateur utilisateur : listeUtilisateurTemp) {
			System.out.println(utilisateur);
		}
		System.out.println("FIN Affichage de tous les utilisateurs");
		
		
		//Affichage d'un utilisateur par ID/no_utilisateur
		System.out.println("DEBUT Affichage d'un utilisateur");
		Utilisateur userTemp = utilisateurManager.afficherUnUtilisateur(4);
		System.out.println(userTemp);
		System.out.println("FIN Affichage d'un utilisateur");

//		System.out.println("DEBUT Suprression d'un utilisateur");
//		//Suppression d'un utilisateur par ID/no_utilisateur
//			//On Liste tous les utilisateurs
//		listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
//		for (Utilisateur utilisateur : listeUtilisateurTemp) {
//			System.out.println(utilisateur);
//		}
//			//On supprimer l'utilisateur n°12 dans cet example
//		utilisateurManager.effacerUnUtilisateur(12);
//			//On vérifie la suppression en relistant tous les utilisateurs
//		listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
//		for (Utilisateur utilisateur : listeUtilisateurTemp) {
//			System.out.println(utilisateur);
//		}		
//		System.out.println("FIN Suppression d'un utilisateur");
//		
		
		System.out.println("DEBUT modification d'un utilisateur");

		//Test modification d'un utilsateur
			//On extrait un utilisateur existant de la base
		userTest = utilisateurManager.afficherUnUtilisateur(1);
		System.out.println(userTest);
			//Modification de son code postal
		userTest.setCodePostal("34000");
			//Modification en base de donnée
		utilisateurManager.modifierUtilisateur(userTest);
			//Syso pour vérirication
		System.out.println(userTest);
			//Syso de tous les utilisateurs
		listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
		for (Utilisateur utilisateur : listeUtilisateurTemp) {
			System.out.println(utilisateur);
		};
		
		System.out.println("FIN Modification d'un utilisateur");

		System.out.println("DEBUT test connexion utilisateur");
		userTest = utilisateurManager.findByPseudoAndPassword("Prout", "Pipi");
		System.out.println(userTest);
		userTest = utilisateurManager.findByPseudoAndPassword("Olo", "motdepasse");
		System.out.println(userTest);

		System.out.println("Fin test connexion utilisateur");

		
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
