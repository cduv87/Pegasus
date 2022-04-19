package model.dal;

public class CategorieDAOFactory {
	public static CategorieDAOInterface getCategorieDAO() {
		return new CategorieDAOJdbcImpl();
	}
}
