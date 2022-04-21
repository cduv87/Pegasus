<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="">
			<h1 class="display-4 text-white">ENI ENCHERES</h1>
		</a>

		<ul class="navbar-nav mr-auto my-2 my-lg-0">
			<c:choose>
			<c:when test="${utilisateurConnecte != null}">
				<li class="nav-item"><h6 class="text-white my-auto">Connecté : ${utilisateurConnecte.pseudo}  </h6></li>
				<!-- le lien enchère ci dessous est destiné a devenir un post vers listeVenteServlet, rendant le resultat des encheres de l'utilisateur en cours  -->
				<li class="nav-item"><a href="listeVente" name="encheres" class="btn btn-primary">Enchères</a></li>
				<li class="nav-item"><a href="editVente" name="creer vente" class="btn btn-primary">Vendre un article</a></li>
				<!-- ancien lien pour afficher son profil -->
				<!-- <li class="nav-item"><a href="affichProfil" name="affiche profile" class="btn btn-primary">Mon Profil</a></li> -->
				<form class="inline-form" action="./affichProfil" method="post">
					<li class="nav-item"><button name="user_id" value="${utilisateurConnecte.noUtilisateur}" type="submit" class="btn btn-primary">Mon Profil</button></li>
				</form>
				<form class="inline-form" action="${pageContext.request.contextPath}/deconnexion" method="POST">
					<li class="nav-item"><button name="deconnecter" type="submit" class="btn btn-danger">Se déconnecter</button></li>
				</form>
			</c:when>
			<c:otherwise>
				<a href="connexion" name="connecter" class="btn btn-primary">Se connecter</a>
			</c:otherwise>
			</c:choose>
		</ul>

	</div>
</nav>
