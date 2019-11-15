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
    compte selectionne : numero ${numCptSel}
	<h3>dernieres operations</h3>
	<table border="1" >
	<tr><th>numero</th><th>montant</th><th>date</th><th>label</th></tr>
		<c:forEach var="op" items="${listeOp}">
			<tr><td>${op.numOp}</td><td>${op.montant}</td><td>${op.dateOp}</td><td>${op.label}</td></tr>
		</c:forEach>
	</table>
	<hr/>
	<a href="listeComptes">retour liste comptes</a><br/>
</body>
</html>