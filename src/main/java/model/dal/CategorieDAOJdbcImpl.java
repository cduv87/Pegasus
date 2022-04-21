package model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bo.Categorie;

public class CategorieDAOJdbcImpl implements CategorieDAOInterface{

	private final static String INSERT_CATEGORIE = "INSERT INTO categories (libelle) values(?);";
	private final static String SELECT_ARTICLE_ALL = "SELECT * FROM categories;";
	private final static String SELECT_CATEGORIE = "SELECT * FROM categories where no_categorie=?;";
	private final static String UPDATE_CATEGORIE = "UPDATE categories SET libelle=? where no_categorie=?;";
	private final static String DELETE_CATEGORIE = "DELETE FROM categories WHERE no_categorie=?";
	private final static String TRUNCATE_CATEGORIE = "DELETE FROM categories DBCC CHECKIDENT ('ENCHERES.dbo.categories', RESEED, 0)";


	@Override
	public void add(Categorie c) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_CATEGORIE,Statement.RETURN_GENERATED_KEYS);
		
		pStmt.setString(1, c.getLibelle());
		
		pStmt.executeUpdate();

		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next())
			c.setNoCategorie(rs.getInt(1));
		
		cnx.close();
	}
	
	@Override
	public ArrayList<Categorie> selectAll() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ARTICLE_ALL);
		ResultSet rs = pStmt.executeQuery();
			
		ArrayList<Categorie> listeCategories = new ArrayList<Categorie>();
		
		while(rs.next()) {
			
			listeCategories.add(
				new Categorie(
					rs.getInt("no_categorie"),
					rs.getString("libelle")
				)
			);
		}
		cnx.close();
		
		return listeCategories;
	}
	
	@Override
	public Categorie selectBy(int id) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_CATEGORIE);
		pStmt.setInt(1, id);
		ResultSet rs = pStmt.executeQuery();
		rs.next();
		
		Categorie c = new Categorie(
			id,
			rs.getString("libelle")
		);
		
		cnx.close();
		
		return c;
	}

	@Override
	public void update(Categorie c) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		try {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_CATEGORIE);

			pStmt.setInt(2, c.getNoCategorie());
			
			pStmt.setString(1, c.getLibelle());
			pStmt.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		cnx.close();
	}
	
	@Override
	public void delete(int id) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE_CATEGORIE);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cnx.close();
	}

	public void truncate() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(TRUNCATE_CATEGORIE);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cnx.close();
	}

}
