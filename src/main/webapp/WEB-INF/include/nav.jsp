<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container-fluid">
	    <a class="navbar-brand" href="./">
			ENI ENCHERES
		</a>
	<c:if test="${utilisateurConnecte != null}">
		<ul class="navbar-nav ms-auto">
		    <li class="nav-item">
				<a class="nav-link fw-bold text-white"><i class="fa fa-user"></i> ${utilisateurConnecte.pseudo}</a>
		    </li>
		</ul>
	</c:if>
	</div>
</nav>