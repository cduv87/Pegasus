package model.dal;

import java.sql.SQLException;
import java.util.ArrayList;
import model.bo.Utilisateur;

/**
 * Interface : définit les fonctionnalités qu'on cherche à avoir
 */
public interface UtilisateurDAOInterface {
	/*
	 * Je veux pouvoir ajouter un avis
	 */
//	public void add(Repas repas) throws SQLException;
//	
//	public ArrayList<Repas> show() throws SQLException;
//	
//	public ArrayList<Ingredient> detail(int id) throws SQLException;
	
	public void seConnecter() throws SQLException;
	
	public void add(Utilisateur user) throws SQLException;
	
	public ArrayList<Utilisateur> selectAll() throws SQLException;
	
	public Utilisateur selectBy(int id) throws SQLException;
	
	public void update(Utilisateur user) throws SQLException;
	
	public void delete(int id) throws SQLException;
	
	public void truncate() throws SQLException;

}
