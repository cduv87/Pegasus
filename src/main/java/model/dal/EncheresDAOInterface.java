package model.dal;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.Enchere;

public interface EncheresDAOInterface {
	public void add(Enchere e) throws SQLException;
	
	public ArrayList<Enchere> selectAll() throws SQLException;
	
	public Enchere selectBy(int id) throws SQLException;
	
	public void update(Enchere e) throws SQLException;
	
	public void delete(int id) throws SQLException;
		
	public void truncate() throws SQLException;
}
