package model.dal;

import java.sql.SQLException;

import model.bo.ArticleVendu;
import model.bo.Categorie;

public interface ArticleDAO {
	Categorie getCategorieById(int id) throws SQLException;

	ArticleVendu getArticleById(int id) throws SQLException;

	void insertArticle(ArticleVendu a) throws SQLException;

	void updateArticle(ArticleVendu a) throws SQLException;

	void deleteArticle(ArticleVendu a) throws SQLException;
	
	void insertCategorie(Categorie c) throws SQLException;

	void truncateCategorie() throws SQLException;
	
}
