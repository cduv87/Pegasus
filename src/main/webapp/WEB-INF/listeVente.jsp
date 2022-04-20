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
        var dev = document.getElementById("flexRadioDefault1").checked;
        if (dev == true) {
            document.getElementById("a1").disabled = false;
            document.getElementById("a2").disabled = false;
            document.getElementById("a3").disabled = false;
            document.getElementById("b1").disabled = true;
            document.getElementById("b2").disabled = true;
            document.getElementById("b3").disabled = true;
        } else {
            document.getElementById("a1").disabled = true;
            document.getElementById("a2").disabled = true;
            document.getElementById("a3").disabled = true;
            document.getElementById("b1").disabled = false;
            document.getElementById("b2").disabled = false;
            document.getElementById("b3").disabled = false;
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
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" onchange="verifyAnswer()" checked/>
                        <label class="form-check-label" for="flexRadioDefault1">Achats</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="a1">
                            <label class="form-check-label" for="flexCheckDefault21">enchères ouvertes</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="a2">
                            <label class="form-check-label" for="flexCheckChecked22">mes enchères</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="a3">
                            <label class="form-check-label" for="flexCheckChecked23">mes enchères remportées</label>
                        </div>
                    </div>
                    <div class="col-xl-6 form-check">
                        <input class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" onchange="verifyAnswer()"/>
                        <label class="form-check-label" for="flexRadioDefault2">Mes ventes</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="b1" disabled>
                            <label class="form-check-label" for="flexCheckDefault1">mes ventes en cours</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="b2" disabled>
                            <label class="form-check-label" for="flexCheckChecked2">ventes non débutées</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="b3" disabled>
                            <label class="form-check-label" for="flexCheckChecked3">ventes terminées</label>
                        </div>
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