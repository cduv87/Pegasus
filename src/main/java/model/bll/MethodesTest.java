package model.bll;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.DefaultEditorKit.InsertContentAction;

import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Enchere;
import model.bo.Retrait;
import model.bo.Utilisateur;

public class MethodesTest {
	UtilisateurManager utilisateurManager = new UtilisateurManager();
	ArticleManager articleManager = new ArticleManager();
	EnchereManager enchereManager = new EnchereManager();
	CategorieManager categorieManager = new CategorieManager();
	RetraitManager retraitManager = new RetraitManager();

	public MethodesTest() {
	}

	public void testEffacerTousRetraits()throws SQLException, BusinessException {
		System.out.println("DEBUT test supression tous retraits");
		retraitManager.effacterTousRetraits();
		System.out.println("Fin test supression tous retrairs");
		System.out.println();
	}
	
	public void testEffacerToutesEncheres() throws SQLException {
		System.out.println("DEBUT suppression de TOUTES les enchere");
		enchereManager.effacterToutesEncheres();
		System.out.println("FIN suppression de TOUTES les enchere");
		System.out.println();
	}
	
	public void testEffacerTousArticles() throws SQLException {
		System.out.println("DEBUT Suppression de tous les articles");
		articleManager.effacerTousArticles();
		ArrayList<ArticleVendu> listeArticles = articleManager.afficherTousArticles();
		for (ArticleVendu articlesVendu : listeArticles) {
			System.out.println(articlesVendu);
		}
		System.out.println("FIN Suppression de tous les articles");
		System.out.println();
	}
	
	public void testEffacerToutesCategories() throws SQLException, BusinessException {
//		Test de suppression de tous les utilisateurs
		System.out.println("DEBUT test supression toutes les categories");
		categorieManager.effacterToutesCategories();
		System.out.println("FIN test supression toutes les categories");
		System.out.println();
	}
	
	public void testEffacerTousUtilisateurs() throws SQLException, BusinessException {
//		Test de suppression de tous les utilisateurs
			System.out.println("DEBUT test supression tous utilisateurs");
			utilisateurManager.effacerTousUtilisateurs();
			System.out.println("Fin test supression tous utilisateurs");
			System.out.println();

			// Affichage la liste de tous les utilisateurs
			ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
			for (Utilisateur utilisateur : listeUtilisateurTemp) {
				System.out.println(utilisateur);
			};
		}

	public Boolean testAjouterUnUtilisateur() throws BusinessException {
		System.out.println("Test ajout de 4 utilisateurs");
		try {
			boolean administrateur = false;

			Utilisateur userTest = new Utilisateur();
			userTest.setPseudo("PP");
			userTest.setNom("Peron");
			userTest.setPrenom("Phileas");
			userTest.setEmail("phileas.peron");
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

			System.out.println("FIN Test ajout de 4 utilisateurs");
			System.out.println();
			
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public Boolean testAjouterLesCategories() throws BusinessException, SQLException {
		System.out.println("DEBUT Test ajout des catégories");
		Categorie c1 = new Categorie();
		c1.setLibelle("informatique");
		categorieManager.ajouterUneCategorie(c1);

		Categorie c2 = new Categorie();
		c2.setLibelle("ameublement");
		categorieManager.ajouterUneCategorie(c2);

		Categorie c3 = new Categorie();
		c3.setLibelle("vetement");
		categorieManager.ajouterUneCategorie(c3);

		Categorie c4 = new Categorie();
		c4.setLibelle("sport&loisir");
		categorieManager.ajouterUneCategorie(c4);
		System.out.println("FIN Test ajout des catégories");
		System.out.println();

		return true;
	}
	
	public void testAjouterArticleVendu() throws SQLException, BusinessException {
		System.out.println("DEBUT Test ajout d'un article");

		ArticleVendu article_temp = new ArticleVendu();
		article_temp.setNomArticle("PS5");
		article_temp.setDescription("Une console");
		article_temp.setDateDebutEncheres(LocalDate.of(2022, 04, 19));
		article_temp.setDateFinEncheres(LocalDate.of(2022, 05, 19));
		article_temp.setMiseAPrix(90);
		article_temp.setUtilisateur(utilisateurManager.afficherUnUtilisateur(1));
		article_temp.setCategorieArticle(categorieManager.afficherUneCategorie(1));
		article_temp.setEtatVente(true);
		articleManager.ajouterUnArticle(article_temp);
		System.out.println("FIN Test ajout d'un article");
		System.out.println();
	}
	
	public void testAjoutEnchere() throws SQLException {
		System.out.println("DEBUT Test ajout d'une enchere");
		Enchere enchere_temp = new Enchere();
		ArticleVendu article_temp = articleManager.afficherUnArticle(1);
		Utilisateur utilisateur_temp = utilisateurManager.afficherUnUtilisateur(2);
		enchere_temp.setDateEnchere(LocalDate.of(2022, 04, 01));
		enchere_temp.setMontant_enchere(100);
		enchere_temp.setArticle(article_temp);
		enchere_temp.setUtilisateur(utilisateur_temp);
		enchereManager.ajouterEnchere(enchere_temp);
		System.out.println("FIN Test ajout d'une enchere");
		System.out.println();
	}
	
		public void testAjoutRetrait(int id) throws SQLException {
			System.out.println("DEBUT Test ajout d'un retrait");
			ArticleVendu a = articleManager.afficherUnArticle(id);
			Utilisateur user = utilisateurManager.afficherUnUtilisateur(a.getUtilisateur().getNoUtilisateur());
			Retrait retrait_temp = new Retrait();
			retrait_temp.setArticle(a);
			retrait_temp.setNo_article(a.getNoArticle());
			retrait_temp.setRue(user.getRue());
			retrait_temp.setCode_postal(user.getCodePostal());
			retrait_temp.setVille(user.getVille());
			System.out.println(retrait_temp);
			retraitManager.ajouterRetrait(retrait_temp);
			System.out.println("FIN Test ajout d'un retrait");
			System.out.println();
		}

	public Boolean testAfficherTousUtilisateurs() throws BusinessException {
		System.out.println("DEBUT Test affichage de TOUS les utilisateurs");

		try {
			ArrayList<Utilisateur> listeUtilisateurTemp = utilisateurManager.afficherTousUtilisateurs();
			for (Utilisateur utilisateur : listeUtilisateurTemp) {
				System.out.println(utilisateur);
			}
			System.out.println("FIN Test affichage de TOUS les utilisateurs");
			System.out.println();

			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public void testAfficherToutesCategories() throws SQLException  {
		System.out.println("DEBUT Test affichage de TOUTES les catagories");
		ArrayList<Categorie> listeCategories = categorieManager.afficherToutesCategories();
		for (Categorie categorie : listeCategories) {
			System.out.println(categorie);
		}
		System.out.println("FIN Test affichage de TOUTES les catagories");
		System.out.println();
	}

	public void testAfficherTousArticles() {
		System.out.println("DEBUT Affichage de tous les articles");

		ArrayList<ArticleVendu> listeArticles = articleManager.afficherTousArticles();
		for (ArticleVendu articlesVendu : listeArticles) {
			System.out.println(articlesVendu);
		}
		System.out.println("FIN Affichage de tous les articles");
		System.out.println();
	}
	
	public void testAfficherToutesEncheres() throws SQLException {
		System.out.println("DEBUT affichage de TOUTES les enchere");

		ArrayList<Enchere> listeEnchere = enchereManager.afficherToutesEncheres();
		for (Enchere enchere : listeEnchere) {
			System.out.println(enchere);
		}
		System.out.println("FIN affichage de TOUTES les enchere");
		System.out.println();
	}
	
	public void testAfficherTousRetraits() throws SQLException {
		System.out.println("DEBUT Affichage de tous les retraits");

		ArrayList<Retrait> listeRetrait = retraitManager.afficherTousRetraits();
		for (Retrait retrait : listeRetrait) {
			System.out.println(retrait);
		}
		System.out.println("FIN Affichage de tous les retraits");
		System.out.println();
	}
	
	public Boolean testAfficherUnUtilisateur(int id) throws SQLException {
		System.out.println("DEBUT Test Affichage d'un utilisateur");

		try {
			// Affichage d'un utilisateur par ID/no_utilisateur
			Utilisateur userTemp = utilisateurManager.afficherUnUtilisateur(id);
			System.out.println(userTemp);
			System.out.println("FIN Test affichage d'un utilisateur");
			System.out.println();
			return true;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}

	public void testAfficherUneCategorie(int id) throws SQLException {
		System.out.println("DEBUT Test affichage d'une  catagorie");
		Categorie categorie_temp = categorieManager.afficherUneCategorie(id);
		System.out.println(categorie_temp.toString());
		System.out.println("FIN Test affichage d'une  catagorie");
		System.out.println();
	}

	public void testAfficherUnArticle(int id) {
		System.out.println("DEBUT Affichage d'un article");

		ArticleVendu article_temp = articleManager.afficherUnArticle(id);
		System.out.println(article_temp.toString());
		System.out.println("FIN Affichage d'un article");
		System.out.println();
	}


	public void testAfficherUneEnchere(int id) throws SQLException {
		System.out.println("DEBUT affichage d'une enchere");
		Enchere e = enchereManager.afficherUneEnchere(1);
		System.out.println(e);
		System.out.println("FIN affichage d'une enchere");
		System.out.println();
	}
	
	public void testAfficherUnRetrait(int id) throws SQLException {
		System.out.println("DEBUT affichage d'un retrait");
		Retrait r = retraitManager.afficherUnRetrait(1);
		System.out.println(r);
		System.out.println("FIN affichage d'un retrait");
		System.out.println();
	}

	public Boolean testModifierUtilisateur(int id) throws SQLException, BusinessException {
		System.out.println("DEBUT Test modification d'un utilisateur");
		try {
			// Test modification d'un utilsateur
			// On extrait un utilisateur existant de la base
			Utilisateur userTest = utilisateurManager.afficherUnUtilisateur(id);
			System.out.println(userTest);
			// Modification de son code postal
			userTest.setCodePostal("34000");
			// Modification en base de donnée
			utilisateurManager.modifierUtilisateur(userTest);
			// Syso pour vérirication
			System.out.println(userTest);
			
			System.out.println("FIN Test modification d'un utilisateur");
			System.out.println();

			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public Boolean testModifierCategorie(int id) throws SQLException, BusinessException {
		System.out.println("DEBUT Test modification d'une categorie");
		Categorie categorieTest = categorieManager.afficherUneCategorie(id);
		System.out.println(categorieTest);
		categorieTest.setLibelle("Litterature");
		categorieManager.modifierCategorie(categorieTest);
		System.out.println(categorieTest);
		
		System.out.println("FIN Test modification d'une categorie");
		System.out.println();

		return true;
	}
	
	public Boolean testModifierArticle(int id) throws SQLException, BusinessException {
		System.out.println("DEBUT Test modification d'un article");
		ArticleVendu articleTest = articleManager.afficherUnArticle(id);
		System.out.println(articleTest);
		articleTest.setMiseAPrix(800);
		articleManager.modifierUnArticle(articleTest);
		System.out.println(articleTest);
		
		System.out.println("FIN Test modification d'un article");
		System.out.println();

		return true;
	}
	
	public Boolean testModifierEnchere(int id) throws SQLException, BusinessException {
		System.out.println("DEBUT Test modification d'une enchere");
		Enchere enchereTest = enchereManager.afficherUneEnchere(id);
		System.out.println(enchereTest);
		enchereTest.setMontant_enchere(1000);
		enchereManager.modifierEnchere(enchereTest);
		System.out.println(enchereTest);
		
		System.out.println("FIN Test modification d'une enchere");
		System.out.println();

		return true;
	}
	
	public Boolean testEffacerUnUtilisateur() throws BusinessException {
		System.out.println("DEBUT Test Suprression d'un utilisateur");
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

			System.out.println("FIN Test Suprression d'un utilisateur");
			System.out.println();

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
		System.out.println();

	}
	
	public Boolean testConnexionUtilisateur() throws SQLException {
		try {
			System.out.println("DEBUT Test connexion utilisateur");
			Utilisateur userTest = utilisateurManager.findByPseudoAndPassword("Prout", "Pipi");
			System.out.println("Résultat attendu null : " + userTest);
			userTest = utilisateurManager.findByPseudoAndPassword("Olo", "motdepasse");
			System.out.println("Résultat attendu utilisateur : " + userTest);
			System.out.println("FIN Test Connexion d'utilisateur reussi");
			System.out.println();


			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	}
	
	public Boolean testConnexion() {
		System.out.println("DEBUT Test connexion base de donnee");
		try {
			utilisateurManager.seConnecter();
			System.out.println("FIN Test connexion base de donnee");
			System.out.println();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void testAjouterArticlesVendusRandom() throws SQLException, BusinessException {
        Random rd = new Random();
        int n = 100;
        System.out.println("DEBUT Test ajout d'articles random : n="+n);
        for (int i = 0; i < n; i++) {
            ArticleVendu article_temp = new ArticleVendu();
            article_temp.setNomArticle("article n°"+String.valueOf(i));
            article_temp.setDescription("description random n°"+String.valueOf(i));
            article_temp.setDateDebutEncheres(LocalDate.of(2021+rd.nextInt(3), 1+rd.nextInt(12), 1+rd.nextInt(28)));
            do
                article_temp.setDateFinEncheres(LocalDate.of(2021+rd.nextInt(4), 1+rd.nextInt(12), 1+rd.nextInt(28)));
            while(article_temp.getDateFinEncheres().isBefore(article_temp.getDateDebutEncheres()));
            article_temp.setMiseAPrix(1+rd.nextInt(100));
            article_temp.setUtilisateur(utilisateurManager.afficherUnUtilisateur(1+rd.nextInt(4)));
            article_temp.setCategorieArticle(categorieManager.afficherUneCategorie(1+rd.nextInt(4)));
            article_temp.setEtatVente(rd.nextBoolean());
            articleManager.ajouterUnArticle(article_temp);
        }
        System.out.println("FIN Test ajout d'un article");
        System.out.println();
    }
	
	public void testAjouterEncheresVendusRandom() throws SQLException, BusinessException {
        Random rd = new Random();
        int n = 100;
		List<ArticleVendu> articles_temp = articleManager.afficherTousArticles();
        System.out.println("DEBUT Test ajout d'ecnhseres random : n="+n);
        for (int i = 0; i < n; i++) {
    		Enchere enchere_temp = new Enchere();
    		ArticleVendu article_temp = articles_temp.get(rd.nextInt(articles_temp.size()));
    		Utilisateur utilisateur_temp = utilisateurManager.afficherUnUtilisateur(1+rd.nextInt(4));
            do
            	enchere_temp.setDateEnchere(LocalDate.of(2021+rd.nextInt(4), 1+rd.nextInt(12), 1+rd.nextInt(28)));
            while(
        		enchere_temp.getDateEnchere().isBefore(article_temp.getDateDebutEncheres()) ||
        		enchere_temp.getDateEnchere().isAfter(article_temp.getDateFinEncheres())
        	);
    		enchere_temp.setMontant_enchere(1+rd.nextInt(1000));
    		enchere_temp.setArticle(article_temp);
    		enchere_temp.setUtilisateur(utilisateur_temp);
    		enchereManager.ajouterEnchere(enchere_temp);
        }
        System.out.println("FIN Test ajout d'un article");
        System.out.println();
    }
}
