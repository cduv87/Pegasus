package model.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.Enchere;
import model.dal.EncheresDAOInterface;
import model.dal.EncheresDAOFactory;

public class EnchereManager {
	private EncheresDAOInterface enchereDAO = EncheresDAOFactory.getEncheresDAO();
	
	public void ajouterEnchere(Enchere e) throws SQLException {
		enchereDAO.add(e);
	}
	
	public ArrayList<Enchere> afficherToutesEncheres() throws SQLException{
		ArrayList<Enchere> listeEnchere = enchereDAO.selectAll();
		return listeEnchere;
	}
	
	public Enchere afficherUneEnchere(int id) throws SQLException {
		Enchere e = enchereDAO.selectBy(id);
		return e;
		
	}
	
	public void modifierEnchere(Enchere e)throws SQLException  {
		enchereDAO.update(e);
	}
	
	
	public void effacterToutesEncheres() throws SQLException{
		enchereDAO.truncate();
	}
}
