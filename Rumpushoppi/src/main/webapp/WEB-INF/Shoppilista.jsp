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
<title>Rumpushoppi - Tuotteet</title>
</head>
<body>
	<h1>Rumpushoppi - Tuotteet</h1>
	<table>
	<tr>
	<td style="padding-top:10px"><span class="btn btn-success"><a href ="/lisaa_tuote">Lis‰‰ tuote</a></span></td>
	</tr>
		<tr>
			
			<th>Tuotenimi</th>
			<th>Kuvaus</th>
			<th>Hinta</th>
		
		</tr>	
		<c:forEach items="${tuote}" var="tuote">
			<tr>
				
				<td style="padding-right:10px"><c:out value="${tuote.nimi}"/></td> 
				<td style="padding-right:10px"><c:out value="${tuote.kuvaus}"/></td> 
				<td style="padding-right:10px"><c:out value="${tuote.hinta}" /></td>
				<td><span class="btn btn-danger"><a href ="/poista_tuote?id=<c:out value='${tuote.id}'/>">Poista</a></span></td>
				<td><span class="btn btn-warning"><a href ="/muokkaa_tuote?id=<c:out value='${tuote.id}'/>">Muokkaa</a></span></td>
			</tr>
			
		</c:forEach>
		
	</table>

</body>
</html>