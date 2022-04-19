package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bll.ArticleManager;
import model.bll.BusinessException;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;

@WebServlet("/editVente")
public class EditVenteServlet extends HttpServlet {
	private ArticleManager articleManager = new ArticleManager();

	public EditVenteServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/editVente.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			String article = request.getParameter("article");
			String description = request.getParameter("description");
			//En attente DAO ;   En attente  de l'énumération
			
		//	Categorie categorie =  Integer.valueOf(request.getParameter("categorie"));

			int miseAprix=  Integer.valueOf(request.getParameter("prix"));
		
			String date = request.getParameter("Début de l'enchère");
			String date2 =request.getParameter("Fin de l'enchère");

			LocalDate dateDebutEncheres=LocalDate.parse(date);
			LocalDate dateFinEncheres=LocalDate.parse(date2);

			// il faut créer un constructeur utilisateur (rue,codePostal, ville)
			Utilisateur utilisateur = new Utilisateur () ;// utilisateur.getNoUtilisateur(),

			ArticleVendu articleVendu= new ArticleVendu (article,description,categorie,miseAprix,dateDebutEncheres,dateFinEncheres, utilisateur.getNoUtilisateur());


			//Appel de la BLL

			this.articleManager.add(articleVendu);

			//Insertion

		}
		catch (BusinessException e){ 
			request.setAttribute("messageErreur", e.getMessage());
		}


		// je délègue l'affichage de la réponse à la JSP
		request.getRequestDispatcher("/WEB-INF/editVente.jsp").forward(request, response);

	}


}
