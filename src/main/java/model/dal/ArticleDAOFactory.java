package model.dal;

public class ArticleDAOFactory {
	/**
	 * Cette méthode sert à éviter le : RepasDAO repasDAO = new RepasDAOJdbcImpl() dans la couche BLL
	 */
	public static ArticleDAOInterface getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
}
