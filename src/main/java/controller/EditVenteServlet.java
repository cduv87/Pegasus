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
			
			//Categorie categorie =  Integer.valueOf(request.getParameter("categorie"));

			int miseAprix=  Integer.valueOf(request.getParameter("prix"));
		
			String date = request.getParameter("Début de l'enchère");
			String date2 =request.getParameter("Fin de l'enchère");
			LocalDate dateDebutEncheres=LocalDate.parse(date);
			LocalDate dateFinEncheres=LocalDate.parse(date2);

			// il faut créer un constructeur utilisateur (rue,codePostal, ville)
			Utilisateur utilisateur = new Utilisateur () ;// utilisateur.getNoUtilisateur(),
			
			// création de l'instance articleVendu
			ArticleVendu articleVendu= new ArticleVendu (article,description,/*categorie,*/miseAprix,dateDebutEncheres,dateFinEncheres, utilisateur.getNoUtilisateur());


			//Appel de la BLL

			this.articleManager.add(articleVendu);

			//Insertion
			// Message de confirmation d'ajout d'article
			response.getWriter().println("Nouvel article en vent: "+articleVendu);
		}
		catch (BusinessException e){ 
			request.setAttribute("messageErreur", e.getMessage());
		}


		// j'enregistre la vente et retour page à la JSP d'acceuil 
		request.getRequestDispatcher("/WEB-INF/listeVente.jsp").forward(request, response);

	}


}
