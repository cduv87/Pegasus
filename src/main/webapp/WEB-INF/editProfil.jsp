<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="./include/bootstrapAndJSTL.jsp"%>
<title>Mon Profil</title>
</head>
<body>
<%@ include file="./include/nav.jsp"%>


<form action="./editprofil" method="post">
		<div class="row m-3 text-center">
		<h2>Mon Profil</h2>
		<p class="erreur">${erreur}</p>
		<div class="col-2"></div>
	  		<div class="col-4">
				  <label for="pseudo">Pseudo :</label><br>
				  <input type="text" name="pseudo" value=""><br>
				  <label for="prenom">Prénom :</label><br>
				  <input type="text" name="prenom" value=""><br>
				  <label for="telephone">Téléphone :</label><br>
				  <input type="text" name="tel" value=""><br>
				  <label for="codepo">Code postal :</label><br>
				  <input type="text" name="cpo" value=""><br>
				  <label for="motpasse">Mot de passe :</label><br>
				  <input type="text" name="mdp1" value=""><br>
			  </div>
			  <div class="col-4">
				  <label for="nom">Nom:</label><br>
				  <input type="text" name="nom" value=""><br>
				  <label for="mail">Email :</label><br>
				  <input type="text" name="mail" value=""><br>
				  <label for="rue">Rue :</label><br>
				  <input type="text" name="rue" value=""><br>
				  <label for="ville">Ville :</label><br>
				  <input type="text" name="ville" value=""><br>
				  <label for="confirmotpasse">Confirmation :</label><br>
				  <input type="text" name="mdp2" value=""><br><br>
			  </div>
			  <div class="col-2"></div>
			  <button type="submit" class="btn btn-primary">Créer</button> <a href="./" id="cancel" name="cancel" class="btn btn-default">Annuler</a>
		 </div>
		</form> 


</body>
</html>