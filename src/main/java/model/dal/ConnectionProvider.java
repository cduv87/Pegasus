package model.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Classe dediée à la gestion de notre pool de connexion
 */
public class ConnectionProvider {
	// on va stocker une référence vers notre pool de connexion dans un attribut : "datasource"
	private static DataSource datasource;
	
	// bloc static : bloc d'initialisation qui va être appelé UNE fois au chargement de la classe
	// est pratique pour initialiser des variables "statiques"
	static {
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.datasource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * getConnection() : méthode statique qui va pouvoir être appelée dans nos implémentations DAO pour récupérer une connexion à la base de donéne depuis le pool de connexion
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.datasource.getConnection();
	}
}
