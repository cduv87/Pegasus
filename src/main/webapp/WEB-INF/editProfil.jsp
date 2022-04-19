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
		<p class="erreur text-danger">${erreur}</p>
		<div class="col-2"></div>
	  		<div class="col-4">
				  <label for="pseudo">Pseudo :</label><br>
				  <input type="text" name="pseudo" value="${utilisateurConnecte.pseudo}"><br>
				  <label for="prenom">Prénom :</label><br>
				  <input type="text" name="prenom" value="${utilisateurConnecte.prenom}"><br>
				  <label for="telephone">Téléphone :</label><br>
				  <input type="text" name="tel" value="${utilisateurConnecte.telephone}"><br>
				  <label for="codepo">Code postal :</label><br>
				  <input type="text" name="cpo" value="${utilisateurConnecte.codePostal}"><br>
					<c:choose>
						<c:when test="${utilisateurConnecte != null}">
							<label for="motpasse">Mot de passe actuel :</label><br>
							<input type="text" name="mdpactu" value=""><br>
							<label for="motpasse">Nouveau mot de passe :</label><br>
							<input type="text" name="mdp1" value=""><br><br>
							<p><b>Crédit : ${utilisateurConnecte.credit}</b></p> 
					 	</c:when>
					 	<c:otherwise>
							<label for="motpasse">Mot de passe :</label><br>
					 		<input type="text" name="mdp1" value=""><br>
						</c:otherwise>
					</c:choose>
			  </div>
			  <div class="col-4">
				  <label for="nom">Nom:</label><br>
				  <input type="text" name="nom" value="${utilisateurConnecte.nom}"><br>
				  <label for="mail">Email :</label><br>
				  <input type="text" name="mail" value="${utilisateurConnecte.email}"><br>
				  <label for="rue">Rue :</label><br>
				  <input type="text" name="rue" value="${utilisateurConnecte.rue}"><br>
				  <label for="ville">Ville :</label><br>
				  <input type="text" name="ville" value="${utilisateurConnecte.ville}"><br>
				  <label for="confirmotpasse">Confirmation :</label><br>
				  <input type="text" name="mdp2" value=""><br><br>
			  </div>
			  <div class="col-2"></div>
			  	<c:choose>
						<c:when test="${utilisateurConnecte != null}">
							<button type="submit" name="modifier" class="btn btn-primary">Modifier</button> 
					 	</c:when>
					 	<c:otherwise>
							<button type="submit" name="creer" class="btn btn-primary">Créer</button>
						</c:otherwise>
					</c:choose>		  
			  <a href="./" id="cancel" name="cancel" class="btn btn-default">Annuler</a>
		 </div>
		</form> 


</body>
</html>