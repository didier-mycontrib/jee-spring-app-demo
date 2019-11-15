<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>comptes du client identifie</title>
</head>
<body>
    numero client identifie: ${numClient}
	<h3>liste des comptes</h3>
	<table border="1" >
	<tr><th>numero</th><th>label</th><th>solde</th></tr>
		<c:forEach var="cpt" items="${listeCpt}">
			<tr><td><a href="dernieresOperations?numCptSel=${cpt.numero}">${cpt.numero}</a></td>
			    <td>${cpt.label}</td>
			    <td>${cpt.solde}</td>
			 </tr>
		</c:forEach>
	</table>
	<hr/>
	<a href="nouveau_virement">nouveau virement</a><br/>
	<br/>
	<a href="../app/to_welcome">retour welcome</a> <br/>
</body>
</html>