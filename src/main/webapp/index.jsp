<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Tableau Contrat</title>
		<link href="style/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
   	<!-- jQuery et popper toujours avant JS !	 -->
   	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  	
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css"/>
	<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
   <!--<script type="text/javascript">$(document).ready(function() {$('#tableau').DataTable({"pageLength": 4} );} );</script>-->
	</head>
	<body>
		<table width="100%">
			<tr>
				<td><h1>Sign In : </h1></td>
			</tr>
		</table>
		<form  action="SignIn" method="post" class="container rounded">
		  <div class="form-group">
		    <label for="identifiant">Identifiant:</label>
		    <input type="text" class="form-control" name="IDENTIFIANT" placeholder="Entrez votre identifiant" required>
		  </div>
		  <div class="form-group">
		    <label for="password">Mot de passe:</label>
		    <input type="password" class="form-control" name="MOT_DE_PASSE" placeholder="Password" required>
		  </div>
		  <button type="submit" class="btn btn-primary">Se connecter</button>
		</form>
		<div class="container">
		<p><a href="inscription">S'inscrire</a></p>
		</div>
	</body>
</html>