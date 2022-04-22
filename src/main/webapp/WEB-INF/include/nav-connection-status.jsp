<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container-fluid">
		<%-- <%@ include file="/WEB-INF/include/nav.jsp"%> --%>
		<a class="navbar-brand" href="">
			ENI ENCHERES
		</a>
		<c:choose>
			<c:when test="${utilisateurConnecte != null}">
		        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		            <span class="navbar-toggler-icon"></span>
		        </button>
		        <div class="collapse navbar-collapse" id="navbarNav">
		            <ul class="navbar-nav ms-auto">
		                <li class="nav-item">
		                    <a class="nav-link" href="editVente">Vendre un article</a>
		                </li>
		                <li class="nav-item">
							<form class="inline-form" action="./affichProfil" method="post">
								<input type="hidden" name="user_id" value="${utilisateurConnecte.noUtilisateur}">
								<button type="submit" class="btn nav-link border-0">Mon Profil</button>
							</form>
		                    <!-- <a class="nav-link" href="affichProfil">Mon Profil</a> -->
		                </li>
		                <li class="nav-item">
							<form class="inline-form" action="./deconnexion" method="post">
								<button type="submit" class="btn btn-danger">Se déconnecter</button>
							</form>
		                    <!-- <a class="nav-link" href="deconnexion">Se déconnecter</a> -->
		                </li>
					<c:if test="${utilisateurConnecte != null}">
		                <li class="nav-item">
		                    <a class="nav-link fw-bold text-white"><i class="fa fa-user"></i> ${utilisateurConnecte.pseudo}</a>
		                </li>
					</c:if>
		            </ul>
		        </div>
			</c:when>
			<c:otherwise>
				<a href="connexion" name="connecter" class="btn btn-primary">Se connecter</a>
			</c:otherwise>
		</c:choose>
    </div>
</nav>