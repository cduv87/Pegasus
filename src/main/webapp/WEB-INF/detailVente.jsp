<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Vente</title>
</head>
<body>
<h1>Detail vente</h1>
<h2>Nom article : ${article.nomArticle}</h2>
<h2>Description : ${article.description}</h2>
<h2>Categorie : ${article.categorieArticle.libelle}</h2>
<h2>Meilleure offre : ${article.prixVente}  </h2>
<h2>Mise a prix : ${article.miseAPrix}</h2>
<h2>Fin de l enchere : <%-- ${article.dateFinEncheres} --%></h2>
<h2>Retrait : ${article.utilisateur.rue} ${article.utilisateur.codePostal} ${article.utilisateur.ville}</h2>
<h2>Vendeur : ${article.utilisateur.prenom} ${article.utilisateur.nom}</h2>
	<p>
	<form action="./detailVente" method="POST">
		<label for="nouvelleEnchere">Ma proposition :</label> <input type="text"
			id="nouvelleEnchere" name="nouvelleEnchere" value="0" size="20"
			maxlength="60" />

	</form>
</body>
</html>