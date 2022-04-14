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
public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {
	
	// on définit notre requête SQL d'insertion avec des ? qu'on remplira par la suite
	private final static String INSERT_USER = "INSERT INTO utilisateurs(pseudo,nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";
//	private final static String INSERT_INGREDIENT = "insert into Ingredient(nom, id_repas) values(?,?);";

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
		String sql = "SELECT * FROM utilisateurs;";
		try {
			Statement state = cnx.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = state.executeQuery(sql);
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
	
//	public ArrayList<Ingredient> detail(int id) throws SQLException {
//		ArrayList<Ingredient> listeIngredient = new ArrayList<Ingredient>();
//		Connection cnx = ConnectionProvider.getConnection();
//		String sqlPrepared = "SELECT * FROM Ingredient WHERE id_repas = ?";
//		try {
//			PreparedStatement pStmt = cnx.prepareStatement(sqlPrepared);
//			pStmt.setInt(1, id);
//			ResultSet rs = pStmt.executeQuery();
//			while(rs.next()) {
//				Ingredient ing = new Ingredient();
//				ing.setLibelle(rs.getString("nom"));
//				listeIngredient.add(ing);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return listeIngredient;
//	}

	@Override
	public void seConnecter() throws SQLException {
		Connection cnx = ConnectionProvider.getConnection();
		System.out.println("Connexion reussie a� la base de donnees");
	}

	
	}

