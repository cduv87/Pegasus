package model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAOInterface {
	private final static String INSERT_RETRAIT = "INSERT INTO retraits (no_article, rue, code_postal, ville) values(?,?,?,?);";
	private final static String SELECT_RETRAIT_ALL = "SELECT * FROM retraits;";
	private final static String SELECT_RETRAIT = "SELECT * FROM retraits WHERE no_enchere=?;";
	private final static String UPDATE_RETRAIT = "UPDATE retraits SET date_enchere=? , montant_enchere=? WHERE no_enchere=?;";
	private final static String DELETE_RETRAIT = "DELETE FROM retraits WHERE no_article=?";
	private final static String TRUNCATE_RETRAIT ="TRUNCATE TABLE ENCHERES.dbo.RETRAITS ";
	
	@Override
	public void add(Retrait r) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_RETRAIT);

		pStmt.setInt(1, r.getArticle().getNoArticle());
		pStmt.setString(2, r.getArticle().getUtilisateur().getRue());
		pStmt.setString(3, r.getArticle().getUtilisateur().getCodePostal());
		pStmt.setString(4, r.getArticle().getUtilisateur().getVille());

		pStmt.executeUpdate();
		cnx.close();
	}		

	@Override
	public ArrayList<Retrait> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retrait selectBy(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Retrait r) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void truncate() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(TRUNCATE_RETRAIT);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
