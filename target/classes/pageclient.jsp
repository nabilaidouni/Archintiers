<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Client</title>
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
	<h1>Bonjour ${clientConnecte.nom}</h1>
		<table class="table table-striped">
			<c:if test="${clientConnecte.contrat eq null}">
				<td>
					<p>Vous n'avez pas encore fait de demande de contrat.</p>
				</td>
			</c:if>
			<c:if test="${clientConnecte.contrat ne null}">
				<tr>
					<th>Contrat Professionnel</th>
					<th>detail du contrat (pdf)</th>
					<th>Entreprise</th>
					<th>Status de la demande</th>
				</tr>
				<tr>
				  	<td>${clientConnecte.contrat.commentaire}</td>
					<td>${clientConnecte.contrat.lienPdf}</td>
					<td>${clientConnecte.contrat.entreprise.nom}</td>
					<c:if test="${clientConnecte.contrat.valide eq true}">
						<td><img src="style/valide.png"/></td>
					</c:if>
					<c:if test="${clientConnecte.contrat.valide eq false}">
						<td>En attente de validation</td>
					</c:if>
				</tr>
			</c:if>
		</table>
		<c:if test="${clientConnecte.contrat eq null}">
			<td>
				<p><a href="contrat?IDENTIFIANT_CLIENT=${clientConnecte.identifiant}">Ajouter un contrat</a></p>
			</td>
		</c:if>
		<c:if test="${clientConnecte.contrat ne null}">
		<c:if test="${clientConnecte.contrat.valide eq false}">
			<td>
				<p><a href="contratmaj?IDENTIFIANT_CLIENT=${clientConnecte.identifiant}">Modifier mon contrat</a></p>
				<p><a data-method="delete" href="putanddelete?ID_CONTRAT=${clientConnecte.contrat.id}&IDENTIFIANT_CLIENT=${clientConnecte.identifiant}&METHOD_SELECTED=2">Supprimer mon contrat</a></p>
			</td>
		</c:if>
		</c:if>
</body>
</html>