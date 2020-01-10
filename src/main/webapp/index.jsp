<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Tableau Contrat</title>
		<link href="style/theme1.css" rel="stylesheet">
	</head>
	<body>
		<table width="100%">
			<tr>
				<td><h1>Sign In & Sign Up : </h1></td>
			</tr>
		</table>
		<form action="SignIn" method="post">
		<table>
			<tr>
				<td><p>Bienvenue sur le portail de connexion ContratTelecom</p></td>
			</tr>
			<tr>
				<td><p>Identifiant : </p></td>
				<td>
					<input type="text" name="IDENTIFIANT" size="35" required></textarea>
				</td>
			</tr>
			<tr>
				<td><p>Mot de passe : </p></td>
				<td>
					<input type="password" name="MOT_DE_PASSE" size="35" required></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="Se connecter"></input>
				</td>
			</tr>
		</table>
		</form>
		<p><a href="inscription">S'inscrire</a></p>
	</body>
</html>