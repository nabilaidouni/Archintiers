<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajouter Entreprise</title>
</head>
	<body>
		<table width="100%">
			<tr>
				<td><h1>Ajouter entreprise </h1></td>
			</tr>
		</table>
		<form action="newEntreprisePost" method="post">
		<table>
			<tr>
				<td><p>Nom : </p></td>
				<td>
					<input type="text" name="NOM" size="35" required></textarea>
				</td>
				<td><input type="text" name="IDENTIFIANT_CLIENT" value="${clientConnecte.identifiant}" style="display: none;" /></td>
				<td><input type="text" name="PAGE_PRECEDENTE" value="${pagePrecedente}" style="display: none;" /></td>
			</tr>
			<tr>
				<td><p>Adresse : </p></td>
				<td>
					<input type="text" name="ADRESSE" size="35" required></textarea>
				</td>
			</tr>
			<tr>
				<td><p>Mot de passe : </p></td>
				<td>
					<input type="text" name="SIRET" size="35" required></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Ajouter"></input>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>