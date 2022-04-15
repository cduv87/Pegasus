package auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/deconnexion")
public class DeConnexionServlet extends HttpServlet {
	
	/**
	 * doPost() :  enlève l'utilisateur de la session
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// l'objet HttpSession est le même dans TOUS les servlets de l'application, mais différent pour chaque utilisateur
		HttpSession session = request.getSession();
		session.removeAttribute("utilisateurConnecte");  // c'est en supprimant l'attribut "utilisateurConnecte" de la session qu'on effectue la deconnexion
		// session.invalidate(); : enlève TOUS les attributs de session 
		response.sendRedirect("./"); // je redirige sur la page d'accueil donc pas besoin de faire de JSP pour cette fonctionnalité
	}
}
