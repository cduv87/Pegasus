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

		request.getRequestDispatcher("/WEB-INF/editProfil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Utilisateur utilisateur = new Utilisateur();
		Utilisateur connecte = new Utilisateur();
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
		
		//CREER
		if ((request.getParameter("bouton")).equals("creer")) {
			if (VerifConform(motDePasse1, motDePasse2)) {
				utilisateur.setCredit(creditsUser);
				try {
					userManager.ajouterUtilisateur(utilisateur);
					session.setAttribute("utilisateurConnecte", utilisateur);
					request.setAttribute("message", "Utilisateur crée. Bienvenue !");
					request.getRequestDispatcher("").forward(request, response);
				} catch (BusinessException | SQLException e) {
					request.setAttribute("erreur", e.getMessage());
					e.printStackTrace();
					this.doGet(request, response);
				}
			} else {
				request.setAttribute("erreur", "Vous avez inséré des mots de passe différents");
				request.getRequestDispatcher("/WEB-INF/editProfil.jsp").forward(request, response);
			}
		}
		
		//MODIFIER
		if ((request.getParameter("bouton")).equals("modifier")) {
			connecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
			String mdpUser = connecte.getMotDePasse();
			if (VerifConform(mdpUser, motDePasseActu) && VerifConform(motDePasse1, motDePasse2)) {
				utilisateur.setNoUtilisateur(connecte.getNoUtilisateur());
				try {
					userManager.modifierUtilisateur(utilisateur);
					session.setAttribute("utilisateurConnecte", utilisateur);
					request.setAttribute("message", "Utilisateur modifié.");
					request.getRequestDispatcher("").forward(request, response);
				} catch (SQLException | BusinessException e) {
					request.setAttribute("erreur", e.getMessage());
					e.printStackTrace();
					this.doGet(request, response);
				}
			} else {
				if (!mdpUser.equals(motDePasseActu)) {
					request.setAttribute("erreur", "Votre ancien mot de passe n'est pas le bon");
				}
				if (!motDePasse1.equals(motDePasse2)) {
					request.setAttribute("erreur", "Vous avez inséré des mots de passe différents");
				}
				request.getRequestDispatcher("/WEB-INF/editProfil.jsp").forward(request, response);
			}
		}
		
		//SUPPRIMER
		if ((request.getParameter("bouton")).equals("supprimer")) {
			connecte = (Utilisateur) session.getAttribute("utilisateurConnecte");
			try {
				userManager.effacerUnUtilisateur(connecte.getNoUtilisateur());
				session.removeAttribute("utilisateurConnecte");
				request.setAttribute("message", "Utilisateur suppprimé.");
				request.getRequestDispatcher("").forward(request, response);
			} catch (SQLException e) {
				request.setAttribute("erreur", "Veuillez supprimer vos articles avant de pouvoir supprimer votre compte.");
				this.doGet(request, response);
			}
		}
	}

	public boolean VerifConform(String mdp1, String mdp2) {
		boolean conform = false;
		if (mdp1.equals(mdp2)) {
			conform = true;
		}
		return conform;
	}
}
