package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bll.UtilisateurManager;
import model.bo.Utilisateur;

@WebServlet("/affichProfil")
public class AffichProfilServlet extends HttpServlet{
	
	private UtilisateurManager userManager = new UtilisateurManager();	
	
	  public AffichProfilServlet() {}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/affichProfil.jsp").forward(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	Integer UserId = 0;
	Utilisateur utilisateur = new Utilisateur();
	UserId = Integer.parseInt(request.getParameter("user_id"));
	
	
	try {
		utilisateur = userManager.afficherUnUtilisateur(UserId);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	request.setAttribute("profil", utilisateur);
	request.getRequestDispatcher("/WEB-INF/affichProfil.jsp").forward(request, response);

	
}

}