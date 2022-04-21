package model.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.dal.ArticleDAOInterface;
import model.dal.ArticleDAOFactory;

public class ArticleManager {
		private ArticleDAOInterface articleDAOInterface = ArticleDAOFactory.getArticleDAO();

		public void ajouterUnArticle(ArticleVendu article) throws BusinessException, SQLException {
			validation(article);
			validationNotNull(article);
			this.articleDAOInterface.add(article);
		}

		private void validation(ArticleVendu article) throws BusinessException {
			if (article.getDateFinEncheres().isBefore(article.getDateDebutEncheres())) {
				throw new BusinessException("la date du article doit >= a la date du jour");
			}
		}
		
		private void validationNotNull(ArticleVendu article) throws BusinessException {
			boolean erreur = false;
			if (article.getDateDebutEncheres() == null) {
				if(article.getDescription().isEmpty()) {
					erreur = true;
			}
			}
			if (article.getDateFinEncheres() != null) {
				if(article.getDescription().isEmpty()) {
					erreur = true;
			}
			}
			if (article.getMiseAPrix() ==0 ) {
				if(article.getDescription().isEmpty()) {
					erreur = true;
			}
			}
			if (article.getDescription() != null) {
				if(article.getDescription().isEmpty()) {
				erreur = true;
				}
				
			}
			if (article.getNomArticle()!= null) {
				if(article.getDescription().isEmpty()) {
				erreur = true;
				}
			}
			if (erreur) {
				throw new BusinessException("Les champs doivent tous être remplis");
			}
		}

		public ArticleVendu afficherUnArticle(int id) {
			try {
				return this.articleDAOInterface.selectBy(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		public ArrayList<ArticleVendu> afficherTousArticles() {
			try {
				return this.articleDAOInterface.selectAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public void effacerUnArticle(int id) {
			try {
				this.articleDAOInterface.delete(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void modifierUnArticle(ArticleVendu a) {
			try {
				this.articleDAOInterface.update(a);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public void effacerTousArticles() throws SQLException{
			try {
				this.articleDAOInterface.truncate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
}
