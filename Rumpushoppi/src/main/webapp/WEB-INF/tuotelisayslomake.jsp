<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<link href="styles/demo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Lis‰‰ Tuote</title>
<style type="text/css">
label {
	display: block;
	width: 8em;
	float: left;
}
</style>
</head>
<body>
	<h1>Lis‰‰ tuote</h1>
	<form action="/lisaa_tuote" method="post">
	<p>
		<label>Nimi: </label> <input type="text" value="" name="nimi" size="50" />
	</p>
	<p>
		<label>Kuvaus: </label> <input type="text" value="" name="kuvaus" size="50" />
	</p>
	<p>
		<label>Hinta: </label> <input type="text" value="" name="hinta" size="50" />
	</p>
	<p>
		<span class="btn btn-danger"><a href="/listaa_tuotteet" >Peruuta</a></span>
		<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
	</p>
	
	</form>

</body>
</html>