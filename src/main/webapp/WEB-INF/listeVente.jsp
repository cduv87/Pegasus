<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.bll.ArticleManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Page d'accueil</title>
<%@ include file="./include/bootstrapAndJSTL.jsp"%>

</head>
<body>
<header>
<%@ include file="./include/nav-connection-status.jsp"%>
</header>
    <div class="container mt-5">
        <p class="h1 text-center mb-5"><b>Liste des enchères</b></p>
        <div class="row">
			<form action="./" method="post">
				<div class="col-auto">
					<label class="form-label" for="filtreTexte">Filtres :</label>
					<input class="form-control" type="search" placeholder="Le nom de l'article contient" id="filtreTexte" name="filtreTexte">
				</div>
				<div class="col-auto mt-3">
					<label class="form-label" for="filtreCategorie">Catégorie :</label>
					<select class="form-select" name="filtreCategorie" id="filtreCategorie">
						<option value="all">Toutes</option>
						<option value="informatique">Informatique</option>
						<option value="ameublement">Ameublement</option>
						<option value="vetement">Vêtement</option>
						<option value="sportLoisirs">Sport &amp; Loisirs</option>
					</select>
				</div>
				<c:if test="${utilisateurConnecte!=null}">
				<div class="col-auto mt-3">
					<div class="row">
					<div class="col-xl-6 form-check">
						<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1"/>
						<label class="form-check-label" for="flexRadioDefault1">Achats</label>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
							<label class="form-check-label" for="flexCheckDefault">enchères ouvertes</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
							<label class="form-check-label" for="flexCheckChecked">mes enchères</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
							<label class="form-check-label" for="flexCheckChecked">mes enchères remportées</label>
						</div>
					</div>
					<div class="col-xl-6 form-check">
						<input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" checked/>
						<label class="form-check-label" for="flexRadioDefault2">Mes ventes</label>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
							<label class="form-check-label" for="flexCheckDefault">mes ventes en cours</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
							<label class="form-check-label" for="flexCheckChecked">ventes non débutées</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" checked>
							<label class="form-check-label" for="flexCheckChecked">ventes terminées</label>
						</div>
					</div>
					</div>
				</div>
				</c:if>
				<div class="col-auto mt-3">
					<button type="submit" class="btn btn-primary" id="filtreBouton" name="filtreBouton">Rechercher</button>
				</div>
			</form>
		</div>

        <div class="row mt-5">
			<c:forEach var="donneesCartel" items="${donneesCartels}">
            <div class="col-xl-6 my-2" >
				<div class="card mb-3 mx-auto" style="max-width: 540px;">
					<div class="row g-0">
						<div class="col-md-4">
							<img src="https://dummyimage.com/100x150/000/fff" class="img-fluid rounded-start" alt="">
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-title"><a href="./detailVente?noEnchere=${donneesCartel.get(5)}">${donneesCartel.get(0)}</a></h5>
								<p class="card-text">Prix : ${donneesCartel.get(1)} points</p>
								<p class="card-text">Fin de l'enchère : ${donneesCartel.get(2)}</p>
								<p class="card-text">Vendeur : <a href="./affichProfil?no_utilisateur=${donneesCartel.get(4)}">${donneesCartel.get(3)}</a></p>
							</div>
						</div>
					</div>
				</div>
			</div>
			</c:forEach>
        </div>
	</div>


</body>
</html>