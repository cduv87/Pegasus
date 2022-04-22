package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bll.ArticleManager;
import model.bll.BusinessException;
import model.bll.CategorieManager;
import model.bll.RetraitManager;
import model.bll.UtilisateurManager;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Retrait;
import model.bo.Utilisateur;

@WebServlet("/editVente")
public class EditVenteServlet extends HttpServlet {
	
	private ArticleManager articleManager = new ArticleManager();
	private UtilisateurManager utilisateurManager = new UtilisateurManager();
	private RetraitManager retraitManager = new RetraitManager();
	private CategorieManager categorieManager = new CategorieManager();
	
	public EditVenteServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nomsCategorie", categorieManager.afficherToutesCategories());
		request.getRequestDispatcher("/WEB-INF/editVente.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
			int miseAprix = 0;
			request.setAttribute("nomsCategorie", categorieManager.afficherToutesCategories());
			
			try {
				
				
			System.out.println("ppp");
			validationInput(request.getParameter("article"));
			System.out.println(request.getParameter("article"));
			validationInput(request.getParameter("description"));
			System.out.println(request.getParameter("description"));
			validationInput(request.getParameter("prix"));
			System.out.println(request.getParameter("prix"));
			validationInput(request.getParameter("debut"));
			System.out.println(request.getParameter("debut"));
			validationInput(request.getParameter("fin"));
			System.out.println(request.getParameter("fin"));

			String article = request.getParameter("article");
			String description = request.getParameter("description");
			Categorie categorie = categorieManager.afficherUneCategorie(Integer.parseInt(request.getParameter("filtreCategorie")));
			miseAprix= Integer.valueOf(request.getParameter("prix"));	
			String date = request.getParameter("debut");
			String date2 =request.getParameter("fin");
			LocalDate dateDebutEncheres = LocalDate.parse(date);
			LocalDate dateFinEncheres = LocalDate.parse(date2);

			// Crï¿½ation d'un constructeur utilisateur (rue,codePostal, ville)?
			//Utilisateur utilisateur = new Utilisateur () ;// utilisateur.getNoUtilisateur(),
			//Utilisateur utilisateur = utilisateurManager.findByPseudoAndPassword(request.getNoUtilisateur().getNom());

			HttpSession session = request.getSession();
			Utilisateur u = (Utilisateur) session.getAttribute("utilisateurConnecte");

			// crï¿½ation de l'instance articleVendu

			ArticleVendu articleVendu= new ArticleVendu(
			article,description,categorie,miseAprix,dateDebutEncheres,dateFinEncheres,u);
			
			//Appel de la BLL
			
			this.articleManager.ajouterUnArticle(articleVendu);
			
			//Insertion
			
			// Section retrait et ajout dans la bdd
			Retrait retrait = new Retrait();
				retrait.setArticle(articleVendu);
				retrait.setNo_article(articleVendu.getNoArticle()); //articleVendu.getNoArticle()
				retrait.setRue(request.getParameter("rue"));
				retrait.setCode_postal(request.getParameter("code_postal"));
				retrait.setVille(request.getParameter("ville"));
				
				this.retraitManager.ajouterRetrait(retrait);
				request.setAttribute("message", "Un nouvel article a été ajouté");
					// Message de confirmation d'ajout d'article
			

				request.getRequestDispatcher("").forward(request, response);
				
				
			} catch (SQLException | NumberFormatException| BusinessException e) {
				request.setAttribute("messageErreur", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/editVente.jsp").forward(request, response);
			}
			
			/*request.setAttribute("messageErreur", "Erreur, tous les champs doivent être remplis");
			request.getRequestDispatcher("/WEB-INF/editVente.jsp");*/
			

		
		

	}
	
	public void validationInput(String aValider) throws BusinessException {
		
		if(aValider == null || aValider.isEmpty() || aValider.trim().isEmpty()) {
		
		throw new BusinessException("Tous les champs doivent être remplis.");
		
		}
	}
}
