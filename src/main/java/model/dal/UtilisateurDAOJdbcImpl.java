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
	private final static String SELECT_USER = "SELECT * FROM utilisateurs WHERE no_utilisateur = ?";
	private final static String UPDATE_USER = "UPDATE utilisateurs SET pseudo= ? , nom=? , prenom=? , email=? , telephone=? , rue=? , code_postal=? , ville=? , mot_de_passe=? , credit=? , administrateur=? WHERE no_utilisateur = ? ;";
	private final static String DELETE_USER = "DELETE FROM utilisateurs WHERE no_utilisateur = ?";
	private final static String TRUNCATE_USER = "DELETE FROM utilisateurs DBCC CHECKIDENT ('ENCHERES.dbo.UTILISATEURS', RESEED, 0) ";
	
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
		
		cnx.close();
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
			
		cnx.close();
		return listeUtilisateurs;
	}
	
	public Utilisateur selectBy(int id) throws SQLException {
		Utilisateur user = new Utilisateur();
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_USER);
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
		
		cnx.close();
		return user;
	}
	
	public void update(Utilisateur user) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();		
		try {
			PreparedStatement pStmt = cnx.prepareStatement(UPDATE_USER);
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
		
		cnx.close();
	}
	
	public void delete(int id) throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();

			PreparedStatement pStmt = cnx.prepareStatement(DELETE_USER);
			pStmt.setInt(1, id);
			pStmt.executeUpdate();

	}
	
	public void truncate() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		try {
			PreparedStatement pStmt = cnx.prepareStatement(TRUNCATE_USER);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		cnx.close();
	}
	
	@Override
	public void seConnecter() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		System.out.println("Connexion reussie a la base de donnees");
		
		cnx.close();
	}

}

