package model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;


/**
 * Implémentation des fonctionnalités de mon interface RepasDAO avec JDBC (en base de donnée)
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	private final static String INSERT_ARTICLE = "insert into articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values(?,?,?,?,?,?,?,?);";
	private final static String SELECT_ARTICLE = "select * from articles_vendus where no_article=?;";
	private final static String INSERT_CATEGORIE = "insert into categories (libelle) values(?);";
	private final static String SELECT_CATEGORIE = "select * from categories where no_categorie=?;";
	private final static String UPDATE_ARTICLE = "UPDATE articles_vendus SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=? where no_article=?;";
	private final static String DELETE_ARTICLE = "DELETE FROM articles_vendus WHERE no_article=?";
	
	@Override
	public ArticleVendu getArticleById(int id) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ARTICLE);
		pStmt.setInt(1, id);
		ResultSet rs = pStmt.executeQuery();
		rs.next();
		Categorie c = this.getCategorieById(rs.getInt("no_categorie"));
		//Utilisateur u = UtilisateurDAO.getUserById(rs.getInt("no_utilisateur"));
		Utilisateur u = null;
		
		ArticleVendu a = new ArticleVendu(
			id,
			rs.getString("nom_article"),
			rs.getString("description"),
			rs.getDate("date_debut_encheres").toLocalDate(),
			rs.getDate("date_fin_encheres").toLocalDate(),
			rs.getInt("prix_initial"),
			rs.getInt("prix_vente"),
			false,
			c,
			u
		);
		
		cnx.close();
		
		return a;
	}
	
	@Override
	public void insertArticle(ArticleVendu a) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_ARTICLE,Statement.RETURN_GENERATED_KEYS);
		
		pStmt.setString(1, a.getNomArticle());
		pStmt.setString(2, a.getDescription());
		pStmt.setDate(3, java.sql.Date.valueOf(a.getDateDebutEncheres()));
		pStmt.setDate(4, java.sql.Date.valueOf(a.getDateFinEncheres()));
		pStmt.setInt(5, a.getMiseAPrix());
		pStmt.setInt(6, a.getPrixVente());
		pStmt.setInt(7, a.getUtilisateur().getNoUtilisateur());
		pStmt.setInt(8, a.getCategorieArticle().getNoCategorie());
		pStmt.executeUpdate();

		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next())
			a.setNoArticle(rs.getInt(1));
		
		cnx.close();
	}
	
	@Override
	public Categorie getCategorieById(int id) throws SQLException {
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
	public void deleteArticle(ArticleVendu a) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE_ARTICLE);
			pStmt.setInt(1, a.getNoArticle());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cnx.close();
	}

	@Override
	public void updateArticle(ArticleVendu a) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

		try {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_ARTICLE);

			pStmt.setInt(9, a.getNoArticle());
			
			pStmt.setString(1, a.getNomArticle());
			pStmt.setString(2, a.getDescription());
			pStmt.setDate(3, java.sql.Date.valueOf(a.getDateDebutEncheres()));
			pStmt.setDate(4, java.sql.Date.valueOf(a.getDateFinEncheres()));
			pStmt.setInt(5, a.getMiseAPrix());
			pStmt.setInt(6, a.getPrixVente());
			pStmt.setInt(7, a.getUtilisateur().getNoUtilisateur());
			pStmt.setInt(8, a.getCategorieArticle().getNoCategorie());
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cnx.close();
	}
	
	@Override
	public void insertCategorie(Categorie c) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_CATEGORIE,Statement.RETURN_GENERATED_KEYS);
		
		pStmt.setString(1, c.getLibelle());
		
		pStmt.executeUpdate();

		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next())
			c.setNoCategorie(rs.getInt(1));
		
		cnx.close();
	}
	
	public void truncateCategorie() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		String sql = "DELETE FROM categories DBCC CHECKIDENT ('ENCHERES.dbo.CATEGORIES', RESEED, 0)";
		try {
			Statement stm = cnx.createStatement();
			stm.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
