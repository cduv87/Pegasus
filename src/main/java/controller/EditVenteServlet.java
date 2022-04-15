package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String date = request.getParameter("Début de l'enchère");
		String date2 =request.getParameter("Fin de l'enchère");
		
		LocalDate debutEnchere=LocalDate.parse(date);
		LocalDate finEnchere=LocalDate.parse(date2);
		
		
		
		/*List<String>= StringToList();
		
		Enchere enchere = new Enchere(,,);*/
		
		//this.articleManager.add(vente);
		
	
	}catch(Exception e){
		
	e.printStackTrace();
	}
	}
}