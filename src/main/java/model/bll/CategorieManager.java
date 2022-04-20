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
	
	public ArrayList<Categorie> afficherToutesCategories() {
		ArrayList<Categorie> listeCategories;
		try {
			listeCategories = categorieDAO.selectAll();
			return listeCategories;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Categorie afficherUneCategorie(int id) {
		Categorie c;
		try {
			c = categorieDAO.selectBy(id);
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void modifierCategorie(Categorie c) {
		try {
			categorieDAO.update(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void effacterToutesCategories() {
		try {
			categorieDAO.truncate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
