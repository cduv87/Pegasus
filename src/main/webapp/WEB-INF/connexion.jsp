<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<%@ include file="./include/bootstrapAndJSTL.jsp"%>
</head>

<body>


	<h1>Connexion</h1>

	<!-- on affiche les éventuelles erreurs -->
	<p class="erreur">${erreur}</p>

	
		<div class="container  py 5 mb-5">
			<div class="col-md-6 mx-20">
				<div class="card bg-light">
					<div class="card-body ">
						<form action="./connexion" method="post">
							<label>Pseudo : </label> <input type="text" name="pseudo" /><br>
							<label>Mot de passe : </label> <input type="password"
								name="password" /><br>
							<button class="btn btn-primary my-5">Connexion</button>
						</form>						
						<form action="./editprofil" method="get">
							<button class="btn btn-primary my-5">Créer un compte</button>
						</form>
					</div>
				</div>
			</div>
		</div>



</body>
</html>