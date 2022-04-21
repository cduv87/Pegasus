package model.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.Retrait;
import model.dal.RetraitDAOFactory;
import model.dal.RetraitDAOInterface;

public class RetraitManager {
private RetraitDAOInterface retraitDAO = RetraitDAOFactory.getRetraitDAO();
	
	public void ajouterRetrait(Retrait r) throws SQLException {
		
			retraitDAO.add(r);

	}
	
	public ArrayList<Retrait> afficherTousRetraits() throws SQLException{
		ArrayList<Retrait> listeRetrait = retraitDAO.selectAll();
		return listeRetrait;
	}
	
	public Retrait afficherUnRetrait(int id) throws SQLException {
		Retrait r = retraitDAO.selectBy(id);
		return r;
		
	}
	
	public void modifierRetrait(Retrait r)throws SQLException  {
		retraitDAO.update(r);
	}
	
	public void effacterTousRetraits() throws SQLException{
		retraitDAO.truncate();
	}
}
