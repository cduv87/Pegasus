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
}
