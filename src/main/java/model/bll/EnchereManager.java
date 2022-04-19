package model.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.Enchere;
import model.dal.EncheresDAO;
import model.dal.EncheresDAOFactory;

public class EnchereManager {
	private EncheresDAO enchereDAO = EncheresDAOFactory.getEncheresDAO();
	
	public void ajouterEnchere(Enchere e) throws SQLException {
		enchereDAO.add(e);
	}
	
	public ArrayList<Enchere> afficherToutesEncheres() throws SQLException{
		ArrayList<Enchere> listeEnchere = enchereDAO.selectAll();
		return listeEnchere;
	}
	
	public void effacterToutesEncheres() throws SQLException{
		enchereDAO.truncate();
	}
}
