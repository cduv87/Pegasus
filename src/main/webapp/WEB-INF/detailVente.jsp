<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Vente</title>
<%@ include file="./include/bootstrapAndJSTL.jsp"%>
<%@ include file="./include/nav.jsp"%>
</head>
<body>
    <div class="container mt-5">
        <p class="h1 text-center mb-5"><b>Detail vente</b></p>
        <p class="message text-center text-dark fw-bold">${message}</p>
        <div class="row justify-content-center">
        <div class="col-auto">
			<table class="table">
			  <tbody>
			    <tr>
			      <th scope="row">Nom article</th>
			      <td>${article.nomArticle}</td>
			    </tr>
			    <tr>
			      <th scope="row">Description</th>
			      <td>${article.description}</td>
			    </tr>
			    <tr>
			      <th scope="row">Categorie</th>
			      <td colspan="2">${article.categorieArticle.libelle}</td>
			    </tr>
			    <tr>
			      <th scope="row">Meilleure offre</th>
			      <td colspan="2">${article.prixVente}</td>
			    </tr>
			    <tr>
			      <th scope="row">Mise a prix</th>
			      <td colspan="2">${article.miseAPrix}</td>
			    </tr>
			    <tr>
			      <th scope="row">Fin de l enchere</th>
			      <td colspan="2">${article.dateFinEncheres}</td>
			    </tr>
			    <tr>
			      <th scope="row">Retrait</th>
			      <td colspan="2">${article.utilisateur.rue}<br>${article.utilisateur.codePostal}<br>${article.utilisateur.ville}</td>
			    </tr>
			    <tr>
			      <th scope="row">Vendeur</th>
			      <td colspan="2">${article.utilisateur.prenom}<br>${article.utilisateur.nom}</td>
			    </tr>
			  </tbody>
			</table>
		</div>
		</div>


		<div class="row text-center my-3">
			<div class="d-flex justify-content-center">
				<c:choose>
					<c:when test="${utilisateurConnecte.noUtilisateur == article.utilisateur.noUtilisateur}">
						<a role="button" href="./editVente" class="btn btn-primary m-5">Modifier votre vente</a>
					</c:when>
					<c:otherwise>
						<form action="./detailVente" method="POST">
							<label class="form-label" for="nouvelleEnchere">Ma proposition :</label>
							<input class="form-control" type="number" id="nouvelleEnchere" name="nouvelleEnchere" value="0" size="20" maxlength="60" />
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>