package controller;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.ArticleVendu;
import model.dal.ArticleDAO;
import model.dal.DAOFactory;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleDAO articleDao = DAOFactory.getArticleDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	System.out.println("Début du test");
	try {
		articleDao.insertArticle(new ArticleVendu(0, "chaise ergonomique", "tout confort, pac cher", LocalDate.of(2020, 10, 25), null, 0, 0, false, null, null));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	System.out.println("Fin du test");
	
	
	}

	
}
