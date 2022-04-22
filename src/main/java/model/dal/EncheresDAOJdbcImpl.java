package model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bll.ArticleManager;
import model.bll.UtilisateurManager;
import model.bo.ArticleVendu;
import model.bo.Enchere;
import model.bo.Utilisateur;

public class EncheresDAOJdbcImpl implements EncheresDAOInterface {
	private final static String INSERT_ENCHERE = "INSERT INTO encheres (date_enchere, montant_enchere, no_article, no_utilisateur) values(?,?,?,?);";
	private final static String SELECT_ENCHERE_ALL = "SELECT * FROM encheres;";
	private final static String SELECT_ENCHERE = "SELECT * FROM encheres WHERE no_enchere=?;";
	private final static String UPDATE_ENCHERE = "UPDATE encheres SET date_enchere=? , montant_enchere=? WHERE no_enchere=?;";
	private final static String DELETE_ENCHERE = "DELETE FROM encheres WHERE no_enchere=?";
	private final static String TRUNCATE_ENCHERE ="TRUNCATE TABLE encheres";
	
	@Override
	public void add(Enchere e) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_ENCHERE, Statement.RETURN_GENERATED_KEYS);
		
		pStmt.setDate(1, java.sql.Date.valueOf(e.getDateEnchere()));
		pStmt.setInt(2, e.getMontant_enchere());
		pStmt.setInt(3, e.getArticle().getNoArticle());
		pStmt.setInt(4, e.getUtilisateur().getNoUtilisateur());

		pStmt.executeUpdate();

		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) {
			e.setNoEnchere(rs.getInt(1));
		}
		cnx.close();

	}

	@Override
	public ArrayList<Enchere> selectAll() throws SQLException {
		ArrayList<Enchere> listeEnchere = new ArrayList<Enchere>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		ArticleManager articleManager = new ArticleManager();
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ENCHERE_ALL);
		ResultSet rs = pStmt.executeQuery();

		while (rs.next()) {
			ArticleVendu a = articleManager.afficherUnArticle(rs.getInt("no_article"));
			Utilisateur u = utilisateurManager.afficherUnUtilisateur(rs.getInt("no_utilisateur"));

			listeEnchere.add(new Enchere(
					rs.getInt("no_enchere"), 
					rs.getDate("date_enchere").toLocalDate(),
					rs.getInt("montant_enchere"), 
					a, 
					u));
		}

		cnx.close();

		return listeEnchere;
	}

	@Override
	public Enchere selectBy(int id) throws SQLException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		ArticleManager articleManager = new ArticleManager();
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ENCHERE);
		pStmt.setInt(1, id);
		ResultSet rs = pStmt.executeQuery();
		rs.next();
		ArticleVendu a = articleManager.afficherUnArticle(rs.getInt("no_article"));
		Utilisateur u = utilisateurManager.afficherUnUtilisateur(rs.getInt("no_utilisateur"));

		Enchere e = new Enchere(id, 
				rs.getDate("date_enchere").toLocalDate(), 
				rs.getInt("montant_enchere"), 
				a, 
				u);

		cnx.close();

		return e;
	}

	@Override
	public void update(Enchere e) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		try {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_ENCHERE);

			pStmt.setInt(3, e.getNoEnchere());
			
			pStmt.setDate(1, java.sql.Date.valueOf(e.getDateEnchere()));
			pStmt.setInt(2, e.getMontant_enchere());
//			pStmt.setInt(3, e.getArticle().getNoArticle());
//			pStmt.setInt(4, e.getUtilisateur().getNoUtilisateur());
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
			PreparedStatement pStmt = cnx.prepareStatement(DELETE_ENCHERE);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cnx.close();
	}

	@Override
	public void truncate() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(TRUNCATE_ENCHERE);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cnx.close();
	}

}
