package model.bll;

import java.util.ArrayList;
import java.util.List;
import model.bo.Utilisateur;


/**
 * On initialise la liste des utilisateurs de l'application dans le constructeur
 */
public class UtilisateurManager {
	
	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

	/**
	 * On initialise la liste des utilisateurs de l'application dans le constructeur
	 */
	public UtilisateurManager() {
		Utilisateur user1 = new Utilisateur();
		Utilisateur user2 = new Utilisateur();
		user1.setPseudo("arnold");
		user2.setPseudo("bruce");
		user1.setMotDePasse("123");
		user2.setMotDePasse("456");

		utilisateurs.add(user1);
		utilisateurs.add(user2);
		
	}

	/**
	 * findByUsernameAndPassword()
	 * cherche si un utilisateur correspond au username/password donnée en paramètre
	 * si non trouvé, retourne null
	 */
	public Utilisateur findByPseudoAndPassword(String pseudo, String password) {
		for (Utilisateur user : utilisateurs) {
			if (user.getPseudo().equals(pseudo) && user.getMotDePasse().equals(password)) {
				return user;
			}
		}
		return null; // on retourne null si non trouvé
	}
}
