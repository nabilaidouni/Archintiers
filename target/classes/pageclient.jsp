<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page Client</title>
<link href="style/theme1.css" rel="stylesheet">
</head>
<body>
	<h1>Bonjour ${clientConnecte.nom}</h1>
		<table>
			<c:if test="${clientConnecte.contrat eq null}">
				<td>
					<p>Vous n'avez pas encore fait de demande de contrat.</p>
				</td>
			</c:if>
			<c:if test="${clientConnecte.contrat ne null}">
				<tr>
				  	<td><p> ${clientConnecte.contrat.commentaire} </p></td>
					<td><p> ${clientConnecte.contrat.lienPdf} </p></td>
					<td><p> ${clientConnecte.contrat.entreprise.nom} </p></td>
					<c:if test="${clientConnecte.contrat.valide eq true}">
						<td><img src="style/valide.png"/></td>
					</c:if>
					<c:if test="${clientConnecte.contrat.valide eq false}">
						<td>
							<p>En attente de validation</p>
						</td>
					</c:if>
				</tr>
			</c:if>
		</table>
		<c:if test="${clientConnecte.contrat eq null}">
			<td>
				<p><a href="contrat?ID_CLIENT=${clientConnecte.id}">Ajouter un contrat</a></p>
			</td>
		</c:if>
		<c:if test="${clientConnecte.contrat ne null}">
			<td>
				<p><a href="putanddelete?ID_CONTRAT=${clientConnecte.contrat.id}">Modifier mon contrat</a></p> <p><a data-method="delete" href="putanddelete?ID_CONTRAT=${clientConnecte.contrat.id}&IDENTIFIANT_CLIENT=${clientConnecte.identifiant}&METHOD_SELECTED=2">Supprimer mon contrat</a></p>
			</td>
		</c:if>
</body>
</html>