package controller;

import java.io.IOException;
import java.sql.SQLException;
import model.bo.Utilisateur;
import model.bll.BusinessException;
import model.bll.UtilisateurManager;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditProfilServlet
 */
@WebServlet("/editprofil")
public class EditProfilServlet extends HttpServlet {

	private UtilisateurManager userManager = new UtilisateurManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("METHODE GET");
		request.getRequestDispatcher("/WEB-INF/editProfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utilisateur utilisateur = new Utilisateur();
		Utilisateur connecte = new Utilisateur();
		
//		TESTS
//		System.out.println("METHODE POST");
//		System.out.println("connecté debut post : " + connecte);
//		System.out.println("utilisateur debut post : " + utilisateur);

		HttpSession session = request.getSession();
		String messageErreur = null;
		final int creditsUser = 100;
		String pseudo = null;
		String nom = null;
		String prenom = null;
		String email = null;
		String telephone = null;
		String rue = null;
		String codePostal = null;
		String ville = null;
		String motDePasse1 = null;
		String motDePasse2 = null;
		String motDePasseActu = null;

		pseudo = request.getParameter("pseudo");
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		email = request.getParameter("mail");
		telephone = request.getParameter("tel");
		rue = request.getParameter("rue");
		codePostal = request.getParameter("cpo");
		ville = request.getParameter("ville");
		motDePasse1 = request.getParameter("mdp1");
		motDePasse2 = request.getParameter("mdp2");
		motDePasseActu = request.getParameter("mdpactu");

		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setMotDePasse(motDePasse1);

//		TESTS
//		System.out.println("mdp1 : " + motDePasse1 + "/ mdp2 : " + motDePasse2);
//		System.out.println("utilisateur constucteur : " + utilisateur);
//		System.out.println("utilisateur connecte : " + session.getAttribute("utilisateurConnecte"));

		// on rentre dans ce IF si il y a un utilisateur connecté
		if (session.getAttribute("utilisateurConnecte") != null) {
			connecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
			String mdpUser = connecte.getMotDePasse();
			
//			TESTS
//			System.out.println("mdp session actuelle : " + mdpUser);
//			System.out.println("mdp ancien saisie : " + motDePasseActu);

			// on rentre dans ce IF si le mot de passe de la session et celui saisi ne sont
			// pas les memes ou si la confirmation de mdp est mauvaise
			if (!mdpUser.equals(motDePasseActu) || !motDePasse1.equals(motDePasse2)) {
				if (!mdpUser.equals(motDePasseActu)) {
					request.setAttribute("erreur", "Votre ancien mot de passe n'est pas le bon");
				}
				if (!motDePasse1.equals(motDePasse2)) {
					request.setAttribute("erreur", "Vous avez inséré des mots de passe différents");
				}
				request.getRequestDispatcher("/WEB-INF/editProfil.jsp").forward(request, response);
			} else {
				// si les MDP sont valides on rentre dans ce ELSE et on lance la modification
				// utilisateur BDD

				// ajout de la clé d'ID de l'user connecte sur l'user construit
				System.out.println("Numero utilisateur connecte : " + connecte.getNoUtilisateur());
				utilisateur.setNoUtilisateur(connecte.getNoUtilisateur());
				System.out.println("utilisateur constructeur avec numero utilisateur connecté : " + utilisateur);

				try {
					// Update de l'user dans la BDD :
					userManager.modifierUtilisateur(utilisateur);
					session.setAttribute("utilisateurConnecte", utilisateur);
					response.sendRedirect("./");
				} catch (SQLException e) {
					request.setAttribute("erreur", e.getMessage());
					e.printStackTrace();
					this.doGet(request, response);
				}
			}
		} else {
			// si on rentre dans ce ELSE c'est qu'il n'ya pas de session ouverte
			// on procede donc a la creation d'un nouvel utilisateur BDD
			
			//le IF test la confirmation de mot de passe
			if (!motDePasse1.equals(motDePasse2)) {
				request.setAttribute("erreur", "Vous avez inséré des mots de passe différents");
				request.getRequestDispatcher("/WEB-INF/editProfil.jsp").forward(request, response);
			} else {
				//ELSE car mot de passes conformes
				try {
					// Ici on donne des credits juste avant insertion BDD
					utilisateur.setCredit(creditsUser);
					userManager.ajouterUtilisateur(utilisateur);
					session.setAttribute("utilisateurConnecte", utilisateur);
					response.sendRedirect("./");
				} catch (BusinessException | SQLException e) {
					request.setAttribute("erreur", e.getMessage());
					e.printStackTrace();
					this.doGet(request, response);
				}
			}
		}
		
	}

} // final
