<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<div id="global">

	<article>

		<header>
			<h1 class="titreRecette">${recette.titre}</h1>
		<c:forEach var ="i" begin="1" end ="${recette.moyenneNote}">
			<span class="fa fa-star checked"></span> 
		</c:forEach>
		<c:forEach var="i" begin ="${recette.moyenneNote+1}" end ="5">
			<span class="fa fa-star "></span> 
		</c:forEach>
			<br>
			<time> ${recette.dateCreation} </time>
		</header>
		<p>${recette.description}</p>
		<!-- Insérer la description de la recette -->

	</article>

	<hr />
	<header>
		<h2 id="titreIngredient">Ingrédients</h2>
		<ul>
			<c:forEach var="ingredient" items="${Ingredients}">
				<li>${ingredient.quantite}${ingredient.unit}
					${ingredient.nomIngredient}</li>

			</c:forEach>
		</ul>
		<!-- Liste des ingrédients de la recette. 
				Faire une boucle pour afficher la liste -->

	</header>

	<h2 id="titreCommentaire">Commentaires</h2>

	<div class="divCommentaire">
		<c:forEach var="commentaire" items="${allCommentaires}">
			<!-- Ajouter une bloucle dans le div <div class="divCommentaire">
			 Pour afficher la liste des commentaires. -->
			<p>${commentaire.auteur}:${commentaire.contenu}</p>
			<!-- Auteur + Commentaire -->
			<p>Note : ${commentaire.note }</p>
			<!--  Note sur 5 du commentaire -->
			<p>${commentaire.dateCreation}</p>
			<!-- Date de création de la recette -->
			<hr>
		</c:forEach>
	</div>

	
		<form method="post" action="recette?id=${recette.id}">
			<!-- Ajouter l'id de la recette après ?id=  -->
			
			<input id="auteur" name="auteur" type="text"
				placeholder="Votre nom *" class="inputChamp" /><br />
			<textarea id="txtCommentaire" name="contenu" rows="4"
				placeholder="Votre commentaire *" class="inputTextArea">${commentaire.contenu}</textarea>
			<br /> <label for="note">Note</label><br /> <select name="note"
				id="note" class="select">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select> <br /> <input type="submit" value="Commenter" class="submitBtn" />
		</form>

	<div id="erreur">
		<p>${info}</p>
		<!-- Remplacer "Erreurs" par la variable erreurs 
								DONE
															-->
	</div>

</div>

<%@ include file="footer.jsp"%>