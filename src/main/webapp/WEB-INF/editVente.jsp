<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.bll.ArticleManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
<%@ include file="./include/bootstrapAndJSTL.jsp"%>
<%@ include file="./include/nav.jsp"%>
</head>
<body>
	<h1>Nouvelle vente</h1>



	<!-- bloc qui affiche le message de confirmation après validation vente ( succès )-->
	<p class="message-confirmation">${messageConfirmation}</p>

	<!-- bloc qui affiche l'éventuel message d'erreur -->

	<p class="message-erreur">${messageErreur}</p>
	
	<div class="container bg-white text-center py 5">

		<form action="./editVente" method="POST">
			<label for="article">Article :</label>
			<textarea name="article" placeholder="article" cols="20" rows="1"></textarea>
			<br />
			<br />
			<label for="description">Description :</label>
			<textarea name="description" placeholder="description" cols=
				"20" rows="20"></textarea>
			<br />
			<br />
			
			<!-- Combobox pour lister les catégories (ENUM) -->
			<label for="Categorie">Catégorie :</label>
    <select name="categorie" id="categorie">
        <option value="all" selected>Toutes</option>
        <option value="informatique">Informatique</option>
        <option value="ameublement">Ameublement</option>
        <option value="vetement">Vêtement</option>
        <option value="sportLoisirs">Sport &amp; Loisirs</option>
    </select>
			<br />
			<br />
			<label for="prix">Mise à prix:</label>
			<textarea name="prix" placeholder="prix" cols="5" rows="1"></textarea>
			<br />
			<br /> <label>Début de l'enchère : </label> <input type="date"
				name="Début de l'enchère" /> <br />
			<br /> <label>Fin de l'enchère : </label> <input type="date"
				name="Fin de l'enchère" /> <br />
				
		<!-- Utilisation de l'EL pour récupérer les informations de l'utilisateur connecté  -->
		
		<h1>Retrait</h1>
		<c:choose>
			<c:when test="${utilisateurConnecte != null}">	
				Rue : ${utilisateurConnecte.rue}<br/>
				Code Postal : ${utilisateurConnecte.codePostal}<br/>
				Ville : ${utilisateurConnecte.ville}<br/>
				</c:when>
			<c:otherwise>
				Rue : ${null} <br/>
				Code Postal : ${null} <br/>
				Ville : ${null} <br/>
			</c:otherwise>
			</c:choose>
				
			<br /> <input type="submit" class="btn btn-success m-5"
				value="Enregistrer" /> 
				<a href="./" class="btn btn-danger m-5">annuler</a>
		</form>
	</div>

</body>
</html>