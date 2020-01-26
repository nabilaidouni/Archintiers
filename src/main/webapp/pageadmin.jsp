<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Admin</title>
<link href="style/theme1.css" rel="stylesheet">
</head>
<body>
	<h1>Bonjour et bienvenue sur l'interface d'administration</h1>
	<c:if test="${contrats.isEmpty() eq false}">
		<table>
			<c:forEach items="${clients}" var="client">
				<tr>
					<td><p>${client.nom}</p></td>
				  	<td><p> : ${client.contrat.commentaire}</p></td>
					<td><p>${client.contrat.lienPdf}</p></td>
					<td><p>${client.contrat.entreprise.nom}</p></td>
					<c:if test="${client.contrat.valide eq true}">
						<td><img src="style/valide.png"/></td>
					</c:if>
					<c:if test="${client.contrat.valide eq false}">
						<td>
							<form action="validationContratPost" method="post">
								<input type="number" name="ID_CONTRAT" value="${contrat.id}" style="display: none;"/>
								<input type="submit" value="Valider"></input>
							</form>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${contrats.isEmpty() eq true}">
		<p>Il n'y a aucune demande de contrat.</p>
	</c:if>
</body>
</html>