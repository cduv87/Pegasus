package model.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.bo.Utilisateur;

/**
 * Implémentation des fonctionnalités de mon interface AvisDAO avec JDBC (en base de donnée)
 */
public class UtilisateurDAOJdbcImpl implements UtilisateurDAOInterface {
	
	private final static String INSERT_USER = "INSERT INTO utilisateurs(pseudo,nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";
	private final static String SELECT_USER_ALL = "SELECT * FROM utilisateurs;";
	private final static String SELECT_USER = "SELECT * FROM articles_vendus where no_article=?;";
	private final static String UPDATE_USER = "UPDATE articles_vendus SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=? where no_article=?;";
	private final static String DELETE_USER = "DELETE FROM articles_vendus WHERE no_article=?";
	private final static String TRUNCATE_USER = "DELETE FROM utilisateurs DBCC CHECKIDENT ('ENCHERES.dbo.UTILISATEURS', RESEED, 0)";
	
	public void add(Utilisateur user) throws SQLException{
		Connection cnx = ConnectionProvider.getConnection();
		
		PreparedStatement pStmt = cnx.prepareStatement(INSERT_USER,Statement.RETURN_GENERATED_KEYS);
		
		pStmt.setString(1, user.getPseudo()); 
		pStmt.setString(2, user.getNom()); 
		pStmt.setString(3, user.getPrenom()); 
		pStmt.setString(4, user.getEmail()); 
		pStmt.setString(5, user.getTelephone()); 
		pStmt.setString(6, user.getRue()); 
		pStmt.setString(7, user.getCodePostal()); 
		pStmt.setString(8, user.getVille()); 
		pStmt.setString(9, user.getMotDePasse()); 
		pStmt.setInt(10, user.getCredit()); 
		pStmt.setBoolean(11, user.isAdministrateur()); 
		
		pStmt.executeUpdate();
		
		ResultSet rs = pStmt.getGeneratedKeys();
		if (rs.next()) {
			user.setNoUtilisateur(rs.getInt(1));
		}
	}

	public ArrayList<Utilisateur> selectAll() throws SQLException {
		ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pStmt = cnx.prepareStatement(SELECT_USER_ALL);
	
			ResultSet rs = pStmt.executeQuery();
			try {

			while (rs.next()) {
				Utilisateur user = new Utilisateur();
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
				listeUtilisateurs.add(user);
				}
			}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listeUtilisateurs;
	}
	
	public Utilisateur selectBy(int id) throws SQLException {
		Utilisateur user = new Utilisateur();
		Connection cnx = ConnectionProvider.getConnection();
		String sqlPrepared = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
		try {
			PreparedStatement pStmt = cnx.prepareStatement(sqlPrepared);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			user.setNoUtilisateur(rs.getInt("no_utilisateur"));
			user.setPseudo(rs.getString("pseudo"));
			user.setNom(rs.getString("nom"));
			user.setPrenom(rs.getString("prenom"));
			user.setEmail(rs.getString("email"));
			user.setTelephone(rs.getString("telephone"));
			user.setRue(rs.getString("rue"));
			user.setCodePostal(rs.getString("code_postal"));
			user.setVille(rs.getString("ville"));
			user.setMotDePasse(rs.getString("mot_de_passe"));
			user.setCredit(rs.getInt("credit"));
			user.setAdministrateur(rs.getBoolean("administrateur"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public void delete(int id) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		String sqlPrepared = "DELETE FROM utilisateurs WHERE no_utilisateur = ?";
		try {
			PreparedStatement pStmt = cnx.prepareStatement(sqlPrepared);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Utilisateur user) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		String sqlPrepared = "UPDATE utilisateurs";
		sqlPrepared += " SET pseudo= ? ,";
		sqlPrepared += " nom=? ,";
		sqlPrepared += " prenom=? ,";
		sqlPrepared += " email=? ,";
		sqlPrepared += " telephone=? ,";
		sqlPrepared += " rue=? ,";
		sqlPrepared += " code_postal=? ,";
		sqlPrepared += " ville=? ,";
		sqlPrepared += " mot_de_passe=? ,";
		sqlPrepared += " credit=? ,";
		sqlPrepared += " administrateur=? ";
		sqlPrepared += " WHERE no_utilisateur = ? ;";
		
		try {
			PreparedStatement pStmt = cnx.prepareStatement(sqlPrepared);
			pStmt.setString(1, user.getPseudo());
			pStmt.setString(2, user.getNom());
			pStmt.setString(3, user.getPrenom());
			pStmt.setString(4, user.getEmail());
			pStmt.setString(5, user.getTelephone());
			pStmt.setString(6, user.getRue());
			pStmt.setString(7, user.getCodePostal());
			pStmt.setString(8, user.getVille());
			pStmt.setString(9, user.getMotDePasse());
			pStmt.setInt(10, user.getCredit());
			pStmt.setBoolean(11, user.isAdministrateur());
			pStmt.setInt(12, user.getNoUtilisateur());
			
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void truncate() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		String sql = "DELETE FROM utilisateurs DBCC CHECKIDENT ('ENCHERES.dbo.UTILISATEURS', RESEED, 0)";
		try {
			Statement stm = cnx.createStatement();
			stm.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void seConnecter() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		System.out.println("Connexion reussie a la base de donnees");
	}

	
	}

