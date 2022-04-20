package model.bll;

import java.sql.SQLException;

import model.bo.ArticleVendu;
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
				throw new BusinessException("erreur SQL lors de l'insertion en base de donnÃ©e");
			}
		}

		private void validation(ArticleVendu article) throws BusinessException {
			if (article.getDateFinEncheres().isBefore(article.getDateDebutEncheres())) {
				throw new BusinessException("la date du article doit >= à  la date du jour");
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
}
