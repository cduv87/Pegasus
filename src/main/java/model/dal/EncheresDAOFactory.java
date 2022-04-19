package model.dal;

public abstract class EncheresDAOFactory {

	public static EncheresDAO getEncheresDAO() {
		return new EncheresDAOJdbcImpl();
	}
}
