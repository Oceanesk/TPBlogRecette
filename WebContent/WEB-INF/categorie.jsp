<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<div id="global">
	<div id="categorie">
		<ul>
			<c:forEach items="${categories}" var= "categorie">    <!--  $ pour rentre dynamique forEach = creer une boucle -->
			<li class="select"><a href="categorie?idCategorie=${categorie.id}">${categorie.nom}</a> 
		</c:forEach>
		
		</ul>
	</div>
	<article>
		<c:forEach items="${recettesCategorie1}" var="recette" >
		<header>
			<img class="imgRecette" src="img/${recette.photo}"
				width="300px" height="242px" /> <a
				href="recette?id=${recette.id}">
				<h1 class="titreRecette">${recette.titre }</h1>
			</a>
			<time> ${recette.dateCreation } </time>
		</header>
		<p>${recette.description }</p>
		</c:forEach>
	</article>
	<hr />
</div>

<%@ include file="footer.jsp"%>