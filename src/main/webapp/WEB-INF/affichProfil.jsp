<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil</title>
<%@ include file="./include/bootstrapAndJSTL.jsp"%>
</head>
<body>
<header>
<%@ include file="./include/nav.jsp"%>
</header>


    <div class="container mt-5">
        <div class="text-center row">
		
        	<p class="h1 text-center mb-5"><b>Profil</b></p>

			<p>Pseudo : ${profil.pseudo}</p> <br>
			<p>Nom : ${profil.nom}</p> <br>
			<p>Prénom : ${profil.prenom}</p> <br>
			<p>Email : ${profil.email}</p> <br>
			<p>Téléphone : ${profil.telephone}</p> <br>
			<p>Rue : ${profil.rue}</p> <br>
			<p>Code postal : ${profil.codePostal}</p> <br>
			<p>Ville : ${profil.ville}</p> <br><br>
			
			<c:if test="${utilisateurConnecte.pseudo.equals(profil.pseudo)}" >
			<form action="./editprofil" method="get">
			<button class="btn btn-primary" type="submit" name="creer">Modifier</button>
			</form>
			</c:if> 
		</div>
	</div>

</body>
</html>