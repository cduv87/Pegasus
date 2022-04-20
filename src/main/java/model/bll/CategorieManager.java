package model.bll;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.Categorie;
import model.dal.CategorieDAOFactory;
import model.dal.CategorieDAOInterface;

public class CategorieManager {
	
private CategorieDAOInterface categorieDAO = CategorieDAOFactory.getCategorieDAO();
	
	public void ajouterUneCategorie(Categorie c) throws SQLException {
		categorieDAO.add(c);
	}
	
	public ArrayList<Categorie> afficherToutesCategories() throws SQLException{
		ArrayList<Categorie> listeCategories = categorieDAO.selectAll();
		return listeCategories;
	}
	
	public Categorie afficherUneCategorie(int id) throws SQLException {
		Categorie c = categorieDAO.selectBy(id);
		return c;
		
	}
	
	public void modifierCategorie(Categorie c)throws SQLException  {
		categorieDAO.update(c);
	}
	
	
	public void effacterToutesCategories() throws SQLException{
		categorieDAO.truncate();
	}
}
