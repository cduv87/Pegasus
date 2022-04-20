package model.dal;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bo.ArticleVendu;
import model.bo.Categorie;

public interface ArticleDAOInterface {

	void add(ArticleVendu a) throws SQLException;
	
	ArrayList<ArticleVendu> selectAll() throws SQLException;
	
	ArticleVendu selectBy(int id) throws SQLException;

	void delete(int id) throws SQLException;
	
	void update(ArticleVendu a) throws SQLException;
	
	void truncate() throws SQLException;
	
}
