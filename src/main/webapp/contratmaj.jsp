<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mettre à jour un contrat</title>
		<link href="style/theme1.css" rel="stylesheet">
	</head>
	<body>
		<table width="100%">
			<tr>
				<td><h1>Modifier un contrat : </h1></td>
			</tr>
		</table>
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
					<input type="submit" value="Modifier" onclick="index"></input>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>