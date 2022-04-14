package controller;

import java.io.IOException;

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
		
		String date = request.getParameter("date");
		String heure =request.getParameter("heure");
		String ingredients=request.getParameter("ingredients");
		
		LocalDate dateFinale=LocalDate.parse(date);
	}
}