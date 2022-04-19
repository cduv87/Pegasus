package model.bll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.CsvUploader;
import model.bo.Utilisateur;
import model.dal.UtilisateurDAOInterface;
import model.dal.UtilisateurDAOFactory;

public class UtilisateurManager {

	private UtilisateurDAOInterface utilisateurDAOInterface = UtilisateurDAOFactory.getUtilisateurDAO();

	public void seConnecter() throws SQLException {
//		validation(user);
		
		this.utilisateurDAOInterface.seConnecter();;
	}
	
	
	public void ajouterUtilisateur(Utilisateur user) throws BusinessException, SQLException {
//		validation(user);
		
		this.utilisateurDAOInterface.add(user);
	}
	
	
	public ArrayList<Utilisateur> afficherTousUtilisateurs() throws BusinessException, SQLException {
//		validation(user);
		
		return this.utilisateurDAOInterface.selectAll();
	}
	
	public Utilisateur afficherUnUtilisateur(int id) throws SQLException {
//		validation(user);

		return this.utilisateurDAOInterface.selectBy(id);
	}
	
	public void effacerUnUtilisateur(int id)  throws SQLException {
//		validation(user);

		this.utilisateurDAOInterface.delete(id);
	}
	
	public void modifierUtilisateur(Utilisateur user) throws SQLException {
		this.utilisateurDAOInterface.update(user);
	}
	
	 public Utilisateur findByPseudoAndPassword(String pseudo, String password) throws SQLException {
	    	ArrayList<Utilisateur> utilisateurs = utilisateurDAOInterface.selectAll();

	        for (Utilisateur user : utilisateurs) {
	            if (user.getPseudo().equals(pseudo) && user.getMotDePasse().equals(password)) {
	                return user;
	            }
	        }
	        return null; // on retourne null si non trouvé
	    }
	 
	 public void effacerTousUtilisateurs() throws SQLException{
		 this.utilisateurDAOInterface.truncate();
	 }
	 
	 public  ArrayList<Utilisateur> creerListeUtilisateur() throws BusinessException, SQLException, IOException {
		 ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
			String[] tableTemp;
			Utilisateur utilisateur = null;
			try {
			List<String> liste = CsvUploader.readFile("import.csv");

			liste.forEach(s -> {System.out.println(s.toString());});
			
			for (String string : liste) {
				tableTemp = string.split(",");
				
				for (String elementUtilisateur : tableTemp) {
					Utilisateur user_temp = new Utilisateur();
					user_temp.setPseudo(tableTemp[0]);
					user_temp.setNom(tableTemp[1]);
					user_temp.setPrenom(tableTemp[2]);
					user_temp.setEmail(tableTemp[3]);
					user_temp.setTelephone(tableTemp[4]);
					user_temp.setRue(tableTemp[5]);
					user_temp.setCodePostal(tableTemp[6]);
					user_temp.setVille(tableTemp[7]);
					user_temp.setMotDePasse(tableTemp[8]);
					user_temp.setCredit(Integer.parseInt(tableTemp[9]));
					user_temp.setAdministrateur(Boolean.parseBoolean(tableTemp[10]));
					
					utilisateur = user_temp;
				}
				listeUtilisateurs.add(utilisateur);
			}
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return listeUtilisateurs;
	 }
}
