<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>liste des devises</title>
</head>
<body>
	<h3>liste des devises (spring web mvc)</h3>
	<table border="1" >
	<tr><th>code</th><th>devise</th><th>change</th></tr>
		<c:forEach var="d" items="${allDevises}">
			<tr><td>${d.codeDevise}</td><td>${d.monnaie}</td><td>${d.DChange}</td></tr>
		</c:forEach>
	</table>
	<hr/>
	<a href="../app/to_welcome">retour welcome</a> <br/>
</body>
</html>