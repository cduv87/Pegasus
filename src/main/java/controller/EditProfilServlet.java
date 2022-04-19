package controller;

import java.io.IOException;
import java.sql.SQLException;
import model.bo.Utilisateur;
import model.bll.BusinessException;
import model.bll.UtilisateurManager;
import javax.servlet.ServletException;
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
	private Utilisateur utilisateur = new Utilisateur();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("METHODE GET");
		request.getRequestDispatcher("/WEB-INF/editProfil.jsp").forward(request, response);
	}

	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("METHODE POST");
		
	
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
		
		System.out.println(request.getParameter("pseudo"));
		
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
		
		if ( ! motDePasse1.equals(motDePasse2)) {
			request.setAttribute("erreur", "Vous avez inséré des mots de passe différents");
			this.doGet(request, response);
		}
		
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setMotDePasse(motDePasse1);
		utilisateur.setCredit(creditsUser);
			
		
		try {
			userManager.ajouterUtilisateur(utilisateur);
			HttpSession session = request.getSession();
			session.setAttribute("utilisateurConnecte", utilisateur);
			response.sendRedirect("./");
		} catch (BusinessException | SQLException e) {
			request.setAttribute("erreur", e.getMessage());
			e.printStackTrace();
			this.doGet(request, response);
		}
	}
	
	
	
	
	

} //final


