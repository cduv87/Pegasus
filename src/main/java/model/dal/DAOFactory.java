package model.dal;

public class DAOFactory {
	/**
	 * Cette méthode sert à éviter le : RepasDAO repasDAO = new RepasDAOJdbcImpl() dans la couche BLL
	 */
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOJdbcImpl();
	}
}
