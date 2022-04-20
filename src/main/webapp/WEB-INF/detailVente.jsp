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
<h2>Meilleure offre : ${enchere.montant_enchere}  </h2>
<h2>Mise a prix : ${article.miseAPrix}</h2>
<h2>Fin de l enchere : <%-- ${article.dateFinEncheres} --%></h2>
<h2>Retrait : </h2>
<h2>Vendeur : ${article.utilisateur.prenom} ${article.utilisateur.nom}</h2>
<h2>Ma proposition :</h2>

<form action="./detailVente" method="">


</body>
</html>