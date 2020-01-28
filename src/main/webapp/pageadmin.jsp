<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Admin</title>
<link href="style/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
   	<!-- jQuery et popper toujours avant JS !	 -->
   	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
   <script type="text/javascript">$(document).ready(function() {$('#tableau').DataTable({"pageLength": 10} );} );</script>
</head>
<body>
	<nav class="navbar navbar-light bg-light">
	  <span class="navbar-brand mb-0 h1">Bonjour ${clientConnecte.nom}</span>
	  <a class="navbar-brand" href="pageadmin">Home</a>
	  <a class="navbar-brand" href="index">Deconnexion</a>
	</nav>
	<h1>Bonjour et bienvenue sur l'interface d'administration</h1>
	<c:if test="${contrats.isEmpty() eq false}">
		<table class="table table-striped" id="tableau">
			<thead>
			<tr>
				<th>Contrat Professionnel</th>
				<th>detail du contrat (pdf)</th>
				<th>Entreprise</th>
				<th>Status de la demande</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${contrats}" var="contrat">
				<tr>
				  	<td><p>${contrat.commentaire}</p></td>
					<td><a href="file:///style/${contrat.lienPdf }">${contrat.lienPdf}</a></td>
					<td><p>${contrat.entreprise.nom}</p></td>
					<c:if test="${contrat.valide eq true}">
						<td><img src="style/valide.png"/></td>
					</c:if>
					<c:if test="${contrat.valide eq false}">
						<td>
							<form action="validationContratPost" method="post">
								<input type="number" name="ID_CONTRAT" value="${contrat.id}" style="display: none;"/>
								<input type="submit" value="Valider"></input>
							</form>
						</td>
					</c:if>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${contrats.isEmpty() eq true}">
		<p>Il n'y a aucune demande de contrat.</p>
	</c:if>
</body>
</html>