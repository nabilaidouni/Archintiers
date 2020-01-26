<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mettre à jour un contrat</title>
		<link href="style/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
   		<!-- jQuery et popper toujours avant JS !	 -->
   		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
		<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
	</head>
	<body>
	<nav class="navbar navbar-light bg-light">
	  <span class="navbar-brand mb-0 h1">Bonjour ${clientConnecte.nom}</span>
	  <a class="navbar-brand" href="pageclient?IDENTIFIANT_CLIENT=${clientConnecte.nom}">Home</a>
	  <a class="navbar-brand" href="index">Deconnexion</a>
	</nav>
	<div class="text-center"><h1 class="display-2">Modification de contrat</h1></div>
		<div class=" container text-center border">
		<form action="newContratPost" method="post">
		<table>
			<tr>
				<td><p>Entreprise : </p></td>
				<td>
					<select name="ID_ENTREPRISE" required>
  						<c:forEach items="${entreprises}" var="ent">
  							<c:if test="${ent.id eq clientConnecte.contrat.entreprise.id}">
								<option value ="${ent.id}" selected>${ent.nom}</option>
							</c:if>
							<c:if test="${ent.id ne clientConnecte.contrat.entreprise.id}">
								<option value ="${ent.id}">${ent.nom}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<td><a href="entreprises?IDENTIFIANT_CLIENT=${clientConnecte.identifiant}&PAGE_PRECEDENTE=2">+</a></td>
				<td><input type="text" name="IDENTIFIANT_CLIENT" value="${clientConnecte.identifiant}" style="display: none;" /></td>
			</tr>
			<tr>
				<td><p>Commentaire du contrat : </p></td>
				<td>
					<textarea name="COMMENTAIRE" rows="5" cols="33" required>${clientConnecte.contrat.commentaire}</textarea>
				</td>
			</tr>
			<tr>
				<td><p>Lien du pdf de description du contrat : </p></td>
				<td>
					<textarea name="LIEN_PDF" rows="5" cols="33" required>${clientConnecte.contrat.lienPdf}</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<button type="submit" class="btn btn-primary" onclick="index">Modifier</button>
				</td>
			</tr>
		</table>
		</form>
		</div>
	</body>
</html>