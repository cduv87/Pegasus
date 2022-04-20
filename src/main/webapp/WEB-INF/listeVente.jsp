<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<%@ page import="model.bll.ArticleManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>Page d'accueil</title>
<%@ include file="./include/bootstrapAndJSTL.jsp"%>
 
<script>
function verifyAnswer() {
    var dev = document.getElementById("filtreAchats").checked;
    if (dev == true) {
        document.getElementById("filtreMesAchatsEncheresOuvertes").disabled = false;
        document.getElementById("filtreMesAchatsEncheres").disabled = false;
        document.getElementById("filtreMesAchatsEncheresRemportees").disabled = false;
        document.getElementById("filtreMesVentesEnCours").disabled = true;
        document.getElementById("filtreMesVentesNonDebutees").disabled = true;
        document.getElementById("filtreMesVentesTerminees").disabled = true;
    } else {
        document.getElementById("filtreMesAchatsEncheresOuvertes").disabled = true;
        document.getElementById("filtreMesAchatsEncheres").disabled = true;
        document.getElementById("filtreMesAchatsEncheresRemportees").disabled = true;
        document.getElementById("filtreMesVentesEnCours").disabled = false;
        document.getElementById("filtreMesVentesNonDebutees").disabled = false;
        document.getElementById("filtreMesVentesTerminees").disabled = false;
    }
}
</script>
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
                        <option value="0">Toutes</option>
            			<c:forEach var="nomCategorie" items="${nomsCategorie}">
                        <option value="${nomCategorie.getNoCategorie()}">${nomCategorie.getLibelle()}</option>
            			</c:forEach>
                    </select>
                </div>
                <c:if test="${utilisateurConnecte!=null}">
                <div class="mt-3 d-flex  justify-content-center">
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="filtre" id="filtreAchats" onchange="verifyAnswer()" checked/>
                        <label class="form-check-label" for="filtreAchats">Mes achats</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="filtreMesAchatsEncheresOuvertes">
                            <label class="form-check-label" for="filtreMesAchatsEncheresOuvertes">mes enchères ouvertes</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="filtreMesAchatsEncheres">
                            <label class="form-check-label" for="filtreMesAchatsEncheres">mes enchères</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="filtreMesAchatsEncheresRemportees">
                            <label class="form-check-label" for="filtreMesAchatsEncheresRemportees">mes enchères remportées</label>
                        </div>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="filtre" id="filtreVentes" onchange="verifyAnswer()"/>
                        <label class="form-check-label" for="filtreVentes">Mes ventes</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="filtreMesVentesEnCours" disabled>
                            <label class="form-check-label" for="filtreMesVentesEnCours">mes ventes en cours</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="filtreMesVentesNonDebutees" disabled>
                            <label class="form-check-label" for="filtreMesVentesNonDebutees">mes ventes non débutées</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="filtreMesVentesTerminees" disabled>
                            <label class="form-check-label" for="filtreMesVentesTerminees">mes ventes terminées</label>
                        </div>
                    </div>
                </div>
                </c:if>
                <div class="col-auto mt-3 text-center">
                    <button type="submit" class="btn btn-primary" id="filtreBouton" name="filtreBouton">Rechercher</button>
                </div>
            </form>
        </div>


        <div class="row mt-5">
            <c:forEach var="donneesCartel" items="${donneesCartels}">
            <div class="col-xl-6 my-2" >
                <div class="card mb-3 mx-auto" style="max-width: 540px;">
                    <div class="row g-0">
                        <div class="col-md-4 d-flex justify-content-center">
                            <img src="https://dummyimage.com/100x150/000/fff" class="rounded m-3 text-center" alt="">
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