package model.dal;

import java.sql.SQLException;
import java.util.List;

import model.bo.ArticleVendu;
import model.bo.Categorie;

public interface ArticleDAO {
	Categorie getCatById(int id) throws SQLException;

	ArticleVendu getArticleById(int id) throws SQLException;

	void insertArticle(ArticleVendu a) throws SQLException;

	void updateArticle(ArticleVendu a) throws SQLException;

	void deleteArticle(ArticleVendu a) throws SQLException;

	List<ArticleVendu> getArticles() throws SQLException;
}
