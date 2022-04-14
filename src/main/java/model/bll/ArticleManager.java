package model.bll;

import java.sql.SQLException;

import model.bo.ArticleVendu;
import model.dal.ArticleDAO;
import model.dal.DAOFactory;

public class ArticleManager {
		private ArticleDAO articleDAO = DAOFactory.getArticleDAO();

		public void add(ArticleVendu article) throws BusinessException {
			//validation(article);
			try {
				this.articleDAO.insertArticle(article);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException("erreur SQL lors de l'insertion en base de donnÃ©e");
			}
		}

		private void validation(ArticleVendu article)  throws BusinessException{
			if (article.getDateDebutEncheres().isBefore(article.getDateFinEncheres())) {
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
}
