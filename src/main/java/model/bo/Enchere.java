package model.bo;

import java.time.LocalDate;

public class Enchere {
	private LocalDate dateEnchere;
	private int montant_enchere;
	private ArticleVendu article;
	private Utilisateur utilisateur;
	
	public Enchere(LocalDate dateEnchere, int montant_enchere, ArticleVendu article, Utilisateur utilisateur) {
		super();
		this.dateEnchere = dateEnchere;
		this.montant_enchere = montant_enchere;
		this.article = article;
		this.utilisateur = utilisateur;
	}
	
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontant_enchere() {
		return montant_enchere;
	}
	public void setMontant_enchere(int montant_enchere) {
		this.montant_enchere = montant_enchere;
	}
	public ArticleVendu getArticle() {
		return article;
	}
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
