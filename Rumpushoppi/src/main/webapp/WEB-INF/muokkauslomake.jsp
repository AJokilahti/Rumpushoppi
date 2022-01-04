<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<link href="styles/demo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Tuotteen muokkaus</title>
</head>
<body>
	<h1>Tuotteen muokkaus</h1>
	
	<h5 style="padding-top:10px">
		<c:out value="${tuote.nimi}" />,
		<c:out value="${tuote.kuvaus}" />,  
		<c:out value="${tuote.hinta}" /> euroa
	</h5>
	
	<form style="padding-top:10px" action="/muokkaa_tuote" method="post">

		<p>
			<label style="padding-right:10px">Nimi: </label> <input type="text" value='<c:out value="${tuote.nimi}"/>' name="nimi" size="50" />
		</p>
		<p>
			<label style="padding-right:10px">Kuvaus: </label> <input type="text" value='<c:out value="${tuote.kuvaus}"/>' name="kuvaus" size="50" />
		</p>
		<p>
			<label style="padding-right:10px">Hinta: </label> <input type="text" value="${tuote.hinta}" name="hinta" size="50" />
		</p>
		<p>
			<span class="btn btn-danger"><a href="/listaa_tuotteet" >Peruuta</a></span>
			<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
			<input type ="hidden" name="id" value="${tuote.id}" />
		</p>
	
	</form>
</body>
</html>