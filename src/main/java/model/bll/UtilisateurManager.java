package model.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.Utilisateur;
import model.dal.UtilisateurDAO;
import model.dal.UtilisateurDAOFactory;

public class UtilisateurManager {

	private UtilisateurDAO utilisateurDAO = UtilisateurDAOFactory.getUtilisateurDAO();

	public void seConnecter() throws SQLException {
//		validation(user);
		
		this.utilisateurDAO.seConnecter();;
	}
	
	
	public void ajouterUtilisateur(Utilisateur user) throws BusinessException, SQLException {
//		validation(user);
		
		this.utilisateurDAO.add(user);
	}
	
	
	public ArrayList<Utilisateur> afficherTousUtilisateurs() throws BusinessException, SQLException {
//		validation(user);
		
		return this.utilisateurDAO.selectAll();
	}
	
	public Utilisateur afficherUnUtilisateur(int id) throws SQLException {
//		validation(user);

		return this.utilisateurDAO.selectBy(id);
	}
	
	public void effacerUnUtilisateur(int id)  throws SQLException {
//		validation(user);

		this.utilisateurDAO.delete(id);
	}
	
	public void modifierUtilisateur(Utilisateur user) throws SQLException {
		this.utilisateurDAO.update(user);
	}
	
	 public Utilisateur findByPseudoAndPassword(String pseudo, String password) throws SQLException {
	    	ArrayList<Utilisateur> utilisateurs = utilisateurDAO.selectAll();

	        for (Utilisateur user : utilisateurs) {
	            if (user.getPseudo().equals(pseudo) && user.getMotDePasse().equals(password)) {
	                return user;
	            }
	        }
	        return null; // on retourne null si non trouvé
	    }
	
}
