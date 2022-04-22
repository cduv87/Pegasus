<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.bll.ArticleManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle vente</title>
<%@ include file="./include/bootstrapAndJSTL.jsp"%>
<%@ include file="./include/nav.jsp"%>
</head>

 <body>
    <p class="messageErreur text-center text-danger fw-bold" >${messageErreur}</p>
         <div class="container">
             <h1 class="h1 text-center mb-5">Nouvelle vente</h1>           	
             <form action="./editVente" method="POST">
               <fieldset>
                
                 <div class="form-group">
                   <label for="article">Article</label>
                   <input type="text" class="form-control" id="article" name="article" value="" placeholder="Exemple: Vieux blouson en daim">
                 </div>
                 
                 <div class="form-group">
                   <label for="description">Description</label>
                   <textarea class="form-control" id="description"  name="description" placeholder="Exemple: Blouson miteux jamais lavé, aux notes olfactives relevées rappelant les sorties de PMU"></textarea>
                 </div>
                 
                 <div class="form-group">
                   <!-- Combobox pour lister les catégories  -->

						<label for="Categorie">Catégorie :</label> 
						<select class="form-select" name="filtreCategorie" id="filtreCategorie">
								<option value="0">Toutes</option>
								<c:forEach var="nomCategorie" items="${nomsCategorie}">
									<option value="${nomCategorie.getNoCategorie()}">${nomCategorie.getLibelle()}</option>
								</c:forEach>
						</select>
						<br /> <br />
                 </div>
                 <div class="form-group">
                    <label for="prix">Mise à prix</label>
                    <input type="text" class="form-control" id="prix" name="prix" value="" >
                  </div>
                  <br /> <br />
                  <div class="mt-3 d-flex justify-content-center">
                  <div class="form-group">
                     <label for="date">Début de l'enchère : </label><br />
                      <input type="date" id="debut" name="debut" />
                    </div>
                    </div>
                    <br /> 
                    <div class="mt-1 d-flex justify-content-center">
                    <div class="form-group">
                        <label for="date">Fin de l'enchère : </label> <br />
                        <input type="date" id="fin" name="fin" />
                    </div>
                     </div>
                    <fieldset>
                    <br /> <br />
                        <legend>Retrait</legend>
                 <div class="form-group">
                    <c:choose>
                        <c:when test="${utilisateurConnecte != null}">
                            <label>Rue: </label> <input type="text" class="form-control" id="rue" value="${utilisateurConnecte.rue}"/><br />
                            <label>Code Postal: </label><input type="text" class="form-control" id="code_postal" value="${utilisateurConnecte.codePostal}"/><br />
                            <label>Ville : </label><input type="text" class="form-control" id="ville" value="${utilisateurConnecte.ville}"/><br />
                        </c:when>
                        <c:otherwise>
                            Rue : ${null} <br />
                            Code Postal : ${null} <br />
                            Ville : ${null} <br />
                        </c:otherwise>
                    </c:choose>
                 </div>
                </fieldset>
               </fieldset>
               <br />
               <input type="submit" class="btn btn-primary m-5" value="Enregistrer" /> 
						<a href="./" class="btn btn-danger m-5">annuler</a>
             </form>
         </div>
    </body>
</html>