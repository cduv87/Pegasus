package model.dal;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.Categorie;

public interface CategorieDAOInterface {
	
	public void add(Categorie c) throws SQLException;
	
	public ArrayList<Categorie> selectAll() throws SQLException;
	
	public Categorie selectBy(int id) throws SQLException;
	
	public void update(Categorie c) throws SQLException;
	
	public void delete(int id) throws SQLException;
	
	public void truncate() throws SQLException;
}
