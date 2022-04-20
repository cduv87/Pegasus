package model.dal;

public abstract class EncheresDAOFactory {

	public static EncheresDAOInterface getEncheresDAO() {
		return new EncheresDAOJdbcImpl();
	}
}
