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




<p>Pseudo : </p> <br>
<p>Nom : </p> <br>
<p>Prénom : </p> <br>
<p>Email : </p> <br>
<p>Téléphone : </p> <br>
<p>Rue : </p> <br>
<p>Code postal : </p> <br>
<p>Ville : </p> <br><br>

<c:if test="${utilisateurConnecte.pseudo.equals()}" >
<form action="./editprofil" method="get">
<button type="submit" name="creer">Modifier</button>
</form>
</c:if> 

</body>
</html>