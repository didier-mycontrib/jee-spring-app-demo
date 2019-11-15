<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>infosClient</title>
<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
</head>
<body>
	<h3>infos client</h3>
	<form action="info" method="GET">
	     numCli: <input name="numCli" value="${customer.numero}" /> 
	     <input  type="submit" value="rechercher" /> <br/>
	</form>
	<hr/>
	
	
	<!-- form:form plutot que form , 
	     form:input path="...." plutot que form name="..." value="..."
	NB: customer doit exister (non null) des le depart en tant que modelAttribute -->
	<form:form action="updateClient"  modelAttribute="customer" method="POST">
	    numero: ${customer.numero} <br/>
	    nom: <form:input path="nom"  /> <br/>
	    prenom: <form:input path="prenom"  /> <br/>
	    adresse: <form:input path="adresse" /> <form:errors path="adresse" cssClass="error"/> <br/>
	    telephone: <form:input path="telephone" /><form:errors path="telephone" cssClass="error"/> <br/>
	    email: <form:input path="email"  /> <form:errors path="email" cssClass="error"/> <br/>
	    <input  type="submit" value="update" />
	</form:form>

	<!-- 
	client(request)=${requestScope.customer}<br/>
	client(session)=${sessionScope.customer}<br/>
	client(application)=${applicationScope.customer}<br/>
	client(page)=${pageScope.customer}<br/>
	-->
	<hr/>
	<form action="endSession" method="GET">
	    <input  type="submit" value="se déconnecter (fin de session)" />
	</form>
	<br/>
	<a href="../app/to_welcome">retour welcome</a> <br/>
</body>
</html>