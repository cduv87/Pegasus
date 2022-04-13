<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>

<body>


	<h1>Connexion</h1>

	<!-- on affiche les éventuelles erreurs -->
	<p class="erreur">${erreur}</p>

	<form action="./connexion" method="post">
		<div class="container  py 5 mb-5">
			<div class="col-md-6 mx-20">
				<div class="card bg-light">
					<div class="card-body ">
						<label>Utilisateur : </label> <input type="text" name="username" /><br>

						<label>Mot de passe : </label> <input type="password"
							name="password" /><br>

						<button class="btn btn-primary my-5">Connexion</button>
					</div>
				</div>
			</div>
		</div>
	</form>

</body>
</html>