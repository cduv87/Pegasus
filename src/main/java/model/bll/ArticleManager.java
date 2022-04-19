package model.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.dal.ArticleDAO;
import model.dal.DAOFactory;

public class ArticleManager {
		private ArticleDAO articleDAO = DAOFactory.getArticleDAO();

		public void add(ArticleVendu article) throws BusinessException {
			validation(article);
			try {
				this.articleDAO.insertArticle(article);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException("erreur SQL lors de l'insertion en base de donnée");
			}
		}

		private void validation(ArticleVendu article) throws BusinessException {
			if (article.getDateFinEncheres().isBefore(article.getDateDebutEncheres())) {
				throw new BusinessException("la date du article doit >= a la date du jour");
			}
		}

		public ArticleVendu getById(int id) {
			try {
				return this.articleDAO.getArticleById(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		public ArrayList<ArticleVendu> getAll() {
			try {
				return this.articleDAO.getArticles();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public void delete(ArticleVendu a) {
			try {
				this.articleDAO.deleteArticle(a);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void update(ArticleVendu a) {
			try {
				this.articleDAO.updateArticle(a);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void insertCategorie(Categorie c) throws BusinessException {
			try {
				this.articleDAO.insertCategorie(c);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException("erreur SQL lors de l'insertion en base de donnée");
			}
		}
		
		public void truncateCategorie() throws SQLException {
			articleDAO.truncateCategorie();
		}
		
		public Categorie getCategorieById(int id) {
			try {
				return this.articleDAO.getCategorieById(id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public void truncateArticles() throws SQLException{
			try {
				this.articleDAO.truncateArticles();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
}
