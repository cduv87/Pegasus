package model.dal;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.Retrait;

public interface RetraitDAOInterface {
	public void add(Retrait r) throws SQLException;
	
	public ArrayList<Retrait> selectAll() throws SQLException;
	
	public Retrait selectBy(int id) throws SQLException;
	
	public void update(Retrait r) throws SQLException;
	
	public void delete(int id) throws SQLException;
		
	public void truncate() throws SQLException;
}
