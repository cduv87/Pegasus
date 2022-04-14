package model.dal;

import java.sql.SQLException;

import model.bo.ArticleVendu;
import model.bo.Categorie;

public interface ArticleDAO {
	Categorie getCatById(int id) throws SQLException;

	ArticleVendu getArticleById(int id) throws SQLException;

	void insertArticle(ArticleVendu a) throws SQLException;

	void updateArticle(ArticleVendu a) throws SQLException;

	void deleteArticle(ArticleVendu a) throws SQLException;
}
