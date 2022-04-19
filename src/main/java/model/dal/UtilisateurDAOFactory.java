package model.dal;

/**
 * Classe qui va juste servir à faire le lien entre ma couche BLL et ma couche DAL
 */
public abstract class UtilisateurDAOFactory {
	
	/**
	 * Cette méthode sert à éviter le : AvisDAO avisDAO = new AvisDAOJdbcImpl() dans la couche BLL
	 */
	public static UtilisateurDAOInterface getUtilisateurDAO() {
		return new UtilisateurDAOJdbcImpl();
	}
}
