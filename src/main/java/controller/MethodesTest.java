package controller;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;import javax.swing.text.DefaultEditorKit.InsertContentAction;

import model.bll.ArticleManager;
import model.bll.BusinessException;
import model.bll.UtilisateurManager;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;

public class MethodesTest {
	UtilisateurManager utilisateurManager = new UtilisateurManager();
	ArticleManager articleManager = new ArticleManager();
	
	public MethodesTest() {
	}

	public Boolean testConnexion() {
		System.out.println("Test connexion base de donnee");
		try {
			utilisateurManager.seConnecter();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Boolean testAjouterUnUtilisateur() throws BusinessException {
		System.out.println("Test ajout d'un utilisateur");
		try {
			boolean administrateur = false;

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
			userTest.setAdministrateur(administrateur);

			Utilisateur userTest2 = new Utilisateur();
			userTest2.setPseudo("Olo");
			userTest2.setNom("Billant");
			userTest2.setPrenom("Olivier");
			userTest2.setEmail("olivier.billant");
			userTest2.setTelephone("06");
			userTest2.setRue("OB");
			userTest2.setCodePostal("29200");
			userTest2.setVille("Brest");
			userTest2.setMotDePasse("motdepasse");
			userTest2.setCredit(100);
			userTest2.setAdministrateur(administrateur);

			Utilisateur userTest3 = new Utilisateur();
			userTest3.setPseudo("cduv");
			userTest3.setNom("Duval");
			userTest3.setPrenom("Clement");
			userTest3.setEmail("clement.duval");
			userTest3.setTelephone("06");
			userTest3.setRue("CD");
			userTest3.setCodePostal("29590");
			userTest3.setVille("Le Faou");
			userTest3.setMotDePasse("motdepasse");
			userTest3.setCredit(100);
			userTest3.setAdministrateur(administrateur);

			Utilisateur userTest4 = new Utilisateur();
			userTest4.setPseudo("AG");
			userTest4.setNom("Guillou");
			userTest4.setPrenom("Antoine");
			userTest4.setEmail("antoine.guillou");
			userTest4.setTelephone("06");
			userTest4.setRue("AG");
			userTest4.setCodePostal("29200");
			userTest4.setVille("Brest");
			userTest4.setMotDePasse("motdepasse");
			userTest4.setCredit(100);
			userTest4.setAdministrateur(administrateur);

			utilisateurManager.ajouterUtilisateur(userTest);
			utilisateurManager.ajouterUtilisateur(userTest2);
			utilisateurManager.ajouterUtilisateur(userTest3);
			utilisateurManager.ajouterUtilisateur(userTest4);

			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public Boolean testAfficherTousUtilisateurs() throws BusinessException {
		System.out.println("Test affichage de TOUS les utilisateurs");

		try {
			// Affichage de tous les utilisateurs de la table Utilisateur

			ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
			for (Utilisateur utilisateur : listeUtilisateurTemp) {
				System.out.println(utilisateur);
			}
			System.out.println("Ajout utilisateur reussi");
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public Boolean testAfficherUnUtilisateur() throws SQLException {
		System.out.println("Test Affichage d'un utilisateur");

		try {
			// Affichage d'un utilisateur par ID/no_utilisateur
			Utilisateur userTemp = utilisateurManager.afficherUnUtilisateur(3);
			System.out.println(userTemp);
			System.out.println("Affichage tous utilisateurs reussi");

			return true;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public Boolean testEffacerUnUtilisateur() throws BusinessException {
		System.out.println("Test Suprression d'un utilisateur");
		try {

			UtilisateurManager utilisateurManager = new UtilisateurManager();
			// On donne l'identifiant (no_utilisateur
			int utilisateurASupprimer = 1;

			// Suppression d'un utilisateur par ID/no_utilisateur
			// On Liste tous les utilisateurs
			ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
			for (Utilisateur utilisateur : listeUtilisateurTemp) {
				System.out.println(utilisateur);
			}
			// On supprimer l'utilisateur n°12 dans cet example
			utilisateurManager.effacerUnUtilisateur(utilisateurASupprimer);
			// On vérifie la suppression en relistant tous les utilisateurs
			listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
			for (Utilisateur utilisateur : listeUtilisateurTemp) {
				System.out.println(utilisateur);
			}

			System.out.println("Affichage d'un utilisateur reussi");

			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public Boolean testModifierUtilisateur() throws SQLException, BusinessException {
		System.out.println("Test modification d'un utilisateur");
		try {
			// Test modification d'un utilsateur
			// On extrait un utilisateur existant de la base
			Utilisateur userTest = utilisateurManager.afficherUnUtilisateur(3);
			System.out.println(userTest);
			// Modification de son code postal
			userTest.setCodePostal("34000");
			// Modification en base de donnée
			utilisateurManager.modifierUtilisateur(userTest);
			// Syso pour vérirication
			System.out.println(userTest);
			// Syso de tous les utilisateurs
			ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
			for (Utilisateur utilisateur : listeUtilisateurTemp) {
				System.out.println(utilisateur);
			}
			;
			System.out.println("Modification d'un utilisateur reussie");

			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public Boolean testConnexionUtilisateur() throws SQLException {
		try {
			System.out.println("Test connexion utilisateur");
			Utilisateur userTest = utilisateurManager.findByPseudoAndPassword("Prout", "Pipi");
			System.out.println("Résultat attendu false : " + userTest);
			userTest = utilisateurManager.findByPseudoAndPassword("Olo", "motdepasse");
			System.out.println("Résultat attendu utilisateur : " + userTest);
			System.out.println("Connexion d'utilisateur reussi");

			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public void testImport() throws SQLException, BusinessException, IOException {

		System.out.println("DEBUT import d'une liste utilisateurs");
		ArrayList<Utilisateur> listeUtilisateurs = utilisateurManager.creerListeUtilisateur();

		for (Utilisateur utilisateur : listeUtilisateurs) {
			utilisateurManager.ajouterUtilisateur(utilisateur);
		}

		ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
		for (Utilisateur user : listeUtilisateurTemp) {
			System.out.println(user);
		}
		;
		System.out.println("FIN import d'une liste utilisateurs");
	}

	public void testEffacerTousUtilisateurs() throws SQLException, BusinessException {
//	Test de suppression de tous les utilisateurs
		System.out.println("DEBUT test supression tous utilisateurs");
		utilisateurManager.effacerTousUtilisateurs();
		System.out.println("Fin test supression tous utilisateurs");
		// Affichage la liste de tous les utilisateurs
		ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
		for (Utilisateur utilisateur : listeUtilisateurTemp) {
			System.out.println(utilisateur);
		}
		;
	}
	
	public Boolean testAjouterLesCategories() throws BusinessException, SQLException {
		System.out.println("DEBUT Test ajout des catégories");
		Categorie c1 = new Categorie();
		c1.setLibelle("Informatique");
		articleManager.insertCategorie(c1);
		
		Categorie c2 = new Categorie();
		c2.setLibelle("Ameublement");
		articleManager.insertCategorie(c2);
		
		Categorie c3 = new Categorie();
		c3.setLibelle("Vetement");
		articleManager.insertCategorie(c3);
		
		Categorie c4 = new Categorie();
		c4.setLibelle("Sport&Loisir");
		articleManager.insertCategorie(c4);
		System.out.println("FIN Test ajout des catégories");

		return true;
	}

	public void testEffacerToutesCategories() throws SQLException, BusinessException {
//		Test de suppression de tous les utilisateurs
			System.out.println("DEBUT test supression toutes les categories");
			articleManager.truncateCategorie();
			System.out.println("FIN test supression toutes les categories");
			// Affichage la liste de tous les utilisateurs
			ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
			for (Utilisateur utilisateur : listeUtilisateurTemp) {
				System.out.println(utilisateur);
			}
			;
		}
	
	public void testAfficherUneCategorie(int id) {
		Categorie categorie_temp = articleManager.getCategorieById(id);
		System.out.println(categorie_temp.toString());
	}
	
	public void testAjouterArticleVendu() throws SQLException, BusinessException {
		ArticleVendu article_temp = new ArticleVendu();
		article_temp.setNomArticle("PS5");
		article_temp.setDescription("Une console");
		article_temp.setDateDebutEncheres(LocalDate.of(2022,04,19));
		article_temp.setDateFinEncheres(LocalDate.of(2022,05,19));
		article_temp.setMiseAPrix(500);
		article_temp.setUtilisateur(utilisateurManager.afficherUnUtilisateur(1));
		article_temp.setCategorieArticle(articleManager.getCategorieById(1));
		articleManager.add(article_temp);
	}
	
	public void testAfficherUnArticle(int id) {
		System.out.println("DEBUT Affichage d'un article");

		ArticleVendu article_temp = articleManager.getById(id);
		System.out.println(article_temp.toString());
		System.out.println("FIN Affichage d'un article");

	}
	
	public void testAfficherTousArticles() {
		System.out.println("DEBUT Affichage de tous les articles");

		ArrayList<ArticleVendu> listeArticles = articleManager.getAll();
		for (ArticleVendu articlesVendu : listeArticles) {
			System.out.println(articlesVendu);
		}
		System.out.println("FIN Affichage de tous les articles");

	}
	
	public void testEffacerTousArticles() throws SQLException {
		System.out.println("DEBUT Suppression de tous les articles");
		articleManager.truncateArticles();
		ArrayList<ArticleVendu> listeArticles = articleManager.getAll();
		for (ArticleVendu articlesVendu : listeArticles) {
			System.out.println(articlesVendu);
		}
		System.out.println("DEBUT Suppression de tous les articles");

	}
	
}

