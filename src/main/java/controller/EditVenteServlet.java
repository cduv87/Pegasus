package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;

@WebServlet("/editVente")
public class EditVenteServlet extends HttpServlet {
	
	 public EditVenteServlet() {}

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	request.getRequestDispatcher("/WEB-INF/editVente.jsp").forward(request, response);
	}
 

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {			
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		Categorie categorie = request.getParameter("categorie");
		
		int miseAprix=request.getParameter("prix");
		/*List<String>= StringToList();
		
		*/
		String date = request.getParameter("D�but de l'ench�re");
		String date2 =request.getParameter("Fin de l'ench�re");
		
		LocalDate dateDebutEncheres=LocalDate.parse(date);
		LocalDate dateFinEncheres=LocalDate.parse(date2);
		
		// il faut cr�er un constructeur utilisateur (rue,codePostal, ville)
		Utilisateur utilisateur = new Utilisateur (utilisateur.getRue(),utilisateur.getCodePostal(),utilisateur.getVille()) ;
		
		ArticleVendu articleVendu= new ArticleVendu (article,description,categorie,miseAprix,dateDebutEncheres,dateFinEncheres,utilisateur);
		
	
		//Appel de la BLL
		
		this.articleManager.add(articleVendu);
		
		//Insertion
	
	}catch(Exception e){
		
	e.printStackTrace();
	}
	}
}