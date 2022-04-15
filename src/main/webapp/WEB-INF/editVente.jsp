<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Nouvelle vente</h1>



	<!-- bloc qui affiche le message de confirmation après validation vente ( succès )-->
	<p class="message-confirmation">${messageConfirmation}</p>

	<!-- bloc qui affiche l'éventuel message d'erreur -->

	<p class="message-erreur">${messageErreur}</p>
	<div class="container bg-white text-center py 5">

		<form action="./editVente" method="POST">
			<h1>Article</h1>
			<textarea name="article" placeholder="article" cols="20" rows="1"></textarea>
			<br />
			<br />
			<h2>Description</h2>
			<textarea name="description" placeholder="description" cols=
				"20" rows="20"></textarea>
			<br />
			<br />
			
			<!-- Combobox pour lister les catégories (ENUM) -->
			<h2>Catégorie</h2>
			<input type=text list=categories>
			<datalist id=categories>
				<option>
					<!-- pointe une des enums de categories -->
				<option>
					<!-- pointe une des enums de categories -->
				<option>
					<!-- pointe une des enums de categories -->
				<option>
					<!-- pointe une des enums de categories -->
			</datalist>
			<br />
			<br />
			<h2>Mise à prix</h2>
			<input type=text list=prix>
			<datalist id=prix>
				<option>
					<!-- pointe une des enums de prix -->
				<option>
					<!-- pointe une des enums de prix -->
				<option>
					<!-- pointe une des enums de prix -->
				<option>
					<!-- pointe une des enums de prix -->
			</datalist>
			<br />
			<br /> <label>Date : </label> <input type="date"
				name="Début de l'enchère" /> <br />
			<br /> <label>Heure : </label> <input type="date"
				name="Fin de l'enchère" /> <br />
			<br /> <input type="submit" class="btn btn-success m-5"
				value="valider" /> <a href="./" class="btn btn-danger m-5">annuler</a>
		</form>
	</div>

</body>
</html>