package model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bll.UtilisateurManager;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;


/**
 * Implémentation des fonctionnalités de mon interface RepasDAO avec JDBC (en base de donnée)
 */
public class ArticleDAOJdbcImpl implements ArticleDAO {
	private final static String INSERT_ARTICLE = "INSERT INTO articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) values(?,?,?,?,?,?,?,?);";
	private final static String SELECT_ARTICLE_ALL = "SELECT * FROM articles_vendus;";
	private final static String SELECT_ARTICLE = "SELECT * FROM articles_vendus where no_article=?;";
	private final static String UPDATE_ARTICLE = "UPDATE articles_vendus SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=? where no_article=?;";
	private final static String DELETE_ARTICLE = "DELETE FROM articles_vendus WHERE no_article=?";
	private final static String TRUNCATE_ARTICLE = "DELETE FROM articles_vendus DBCC CHECKIDENT ('ENCHERES.dbo.ARTICLES_VENDUS', RESEED, 0)";
	
	private final static String INSERT_CATEGORIE = "INSERT INTO categories (libelle) values(?);";
	private final static String SELECT_CATEGORIE = "SELECT * FROM categories where no_categorie=?;";
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
	public ArrayList<ArticleVendu> getArticles() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ARTICLE_ALL);
		ResultSet rs = pStmt.executeQuery();
			
		ArrayList<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		UtilisateurManager utilisateurDAO = new UtilisateurManager();
		
		while(rs.next()) {
			Categorie c = this.getCategorieById(rs.getInt("no_categorie"));
			Utilisateur u = utilisateurDAO.afficherUnUtilisateur(rs.getInt("no_utilisateur"));
			
			articles.add(
				new ArticleVendu(
					rs.getInt("no_article"),
					rs.getString("nom_article"),
					rs.getString("description"),
					rs.getDate("date_debut_encheres").toLocalDate(),
					rs.getDate("date_fin_encheres").toLocalDate(),
					rs.getInt("prix_initial"),
					rs.getInt("prix_vente"),
					false,
					c,
					u
				)
			);
		}
		
		cnx.close();
		
		return articles;
	}
	
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
	public void deleteArticle(int id) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(DELETE_ARTICLE);
			pStmt.setInt(1, id);
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
	
	public void truncateArticles() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(TRUNCATE_ARTICLE);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
