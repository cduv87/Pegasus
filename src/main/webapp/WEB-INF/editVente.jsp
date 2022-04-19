<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.bll.ArticleManager" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<h1>Article</h1>
			<textarea name="article" placeholder="article" cols="20" rows="1"></textarea>
			<br />
			<br />
			<h2>Description</h2>
			<textarea name="description" placeholder="description" cols=
				"20" rows="20"></textarea>
			<br />
			<br />
			
			<!-- Combobox pour lister les catégories (ENUM) -->
			<h2>Catégorie</h2>
			<input type=text list=categories>
			<datalist id=categories>
				<option>
					<!-- pointe une des enums de categories -->
				<option>
					<!-- pointe une des enums de categories -->
				<option>
					<!-- pointe une des enums de categories -->
				<option>
					<!-- pointe une des enums de categories -->
			</datalist>
			<br />
			<br />
			<h2>Mise à prix</h2>
			<textarea name="prix" placeholder="prix" cols="5" rows="1"></textarea>
			<br />
			<br /> <label>Début de l'enchère : </label> <input type="date"
				name="Début de l'enchère" /> <br />
			<br /> <label>Fin de l'enchère : </label> <input type="date"
				name="Fin de l'enchère" /> <br />
				
		<!-- Utilisation de l'EL pour récupérer les informations de l'utilisateur connecté  -->
		
		<h1>Retrait</h1>	
				Rue : ${utilisateurConnecte.rue}<br/>
				Code Postal : ${utilisateurConnecte.codePostal}<br/>
				Ville : ${utilisateurConnecte.ville}<br/>
				
			<br /> <input type="submit" class="btn btn-success m-5"
				value="Enregistrer" /> 
				<a href="./" class="btn btn-danger m-5">annuler</a>
		</form>
	</div>

</body>
</html>