package model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bll.CategorieManager;
import model.bll.UtilisateurManager;
import model.bo.ArticleVendu;
import model.bo.Categorie;
import model.bo.Utilisateur;


/**
 * Implémentation des fonctionnalités de mon interface RepasDAO avec JDBC (en base de donnée)
 */
public class ArticleDAOJdbcImpl implements ArticleDAOInterface {
	private final static String INSERT_ARTICLE = "INSERT INTO articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, etat_vente) values(?,?,?,?,?,?,?,?,?);";
	private final static String SELECT_ARTICLE_ALL = "SELECT * FROM articles_vendus;";
	private final static String SELECT_ARTICLE = "SELECT * FROM articles_vendus where no_article=?;";
	private final static String UPDATE_ARTICLE = "UPDATE articles_vendus SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=? WHERE no_article=? ;";
	private final static String DELETE_ARTICLE = "DELETE FROM articles_vendus WHERE no_article=?";
	private final static String TRUNCATE_ARTICLE = "DELETE FROM articles_vendus DBCC CHECKIDENT ('ENCHERES.dbo.ARTICLES_VENDUS', RESEED, 0) ";
	
	@Override
	public void add(ArticleVendu a) throws SQLException {
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
		pStmt.setBoolean(9, a.isEtatVente());
		pStmt.executeUpdate();

		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next())
			a.setNoArticle(rs.getInt(1));
		
		cnx.close();
	}
	
	@Override
	public ArrayList<ArticleVendu> selectAll() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ARTICLE_ALL);
		ResultSet rs = pStmt.executeQuery();
			
		ArrayList<ArticleVendu> articles = new ArrayList<ArticleVendu>();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		CategorieManager categorieManager = new CategorieManager();
		
		while(rs.next()) {
			Categorie c = categorieManager.afficherUneCategorie(rs.getInt("no_categorie"));
			Utilisateur u = utilisateurManager.afficherUnUtilisateur(rs.getInt("no_utilisateur"));
			
			articles.add(
				new ArticleVendu(
					rs.getInt("no_article"),
					rs.getString("nom_article"),
					rs.getString("description"),
					rs.getDate("date_debut_encheres").toLocalDate(),
					rs.getDate("date_fin_encheres").toLocalDate(),
					rs.getInt("prix_initial"),
					rs.getInt("prix_vente"),
					rs.getBoolean("etat_vente"),
					c,
					u
				)
			);
		}
		
		cnx.close();
		
		return articles;
	}
	
	@Override
	public ArticleVendu selectBy(int id) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		CategorieManager categorieManager = new CategorieManager();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_ARTICLE);
		pStmt.setInt(1, id);
		ResultSet rs = pStmt.executeQuery();
		rs.next();
		Categorie c = categorieManager.afficherUneCategorie(rs.getInt("no_categorie"));
		Utilisateur u = utilisateurManager.afficherUnUtilisateur(rs.getInt("no_utilisateur"));
		//Utilisateur u = null;
		
		ArticleVendu a = new ArticleVendu(
			id,
			rs.getString("nom_article"),
			rs.getString("description"),
			rs.getDate("date_debut_encheres").toLocalDate(),
			rs.getDate("date_fin_encheres").toLocalDate(),
			rs.getInt("prix_initial"),
			rs.getInt("prix_vente"),
			rs.getBoolean("etat_vente"),
			c,
			u
		);
		
		cnx.close();
		
		return a;
	}

	@Override
	public void update(ArticleVendu a) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		CategorieManager categorieManager = new CategorieManager();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_ARTICLE);

			pStmt.setInt(7, a.getNoArticle());
			
			pStmt.setString(1, a.getNomArticle());
			pStmt.setString(2, a.getDescription());
			pStmt.setDate(3, java.sql.Date.valueOf(a.getDateDebutEncheres()));
			pStmt.setDate(4, java.sql.Date.valueOf(a.getDateFinEncheres()));
			pStmt.setInt(5, a.getMiseAPrix());
			pStmt.setInt(6, a.getPrixVente());
//			pStmt.setInt(7, a.getUtilisateur().getNoUtilisateur());
//			pStmt.setInt(8, a.getCategorieArticle().getNoCategorie());
			
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cnx.close();
	}
	
	@Override
	public void delete(int id) throws SQLException {
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
	
	public void truncate() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(TRUNCATE_ARTICLE);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cnx.close();
	}
	
	
	
	
}
