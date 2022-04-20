package model.dal;

public class RetraitDAOFactory {
	public static RetraitDAOInterface getRetraitDAO() {
		return new RetraitDAOJdbcImpl();
	}
}
