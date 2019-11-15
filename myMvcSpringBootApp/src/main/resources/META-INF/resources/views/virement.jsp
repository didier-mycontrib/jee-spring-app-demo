<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>virement</title>
<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
</head>
<body>
	<h3> virement interne (mini-banque) </h3>
	<form:form action="doVirement"  modelAttribute="virementForm" method="POST">
	    compte a debiter: <form:select path="numCptDeb" >
	              <form:options items="${listeCpt}" itemLabel="label" itemValue="numero"/>
	            </form:select>  <br/>
	    compte a crediter: <form:select path="numCptCred" > 
	              <form:options items="${listeCpt}" itemLabel="label" itemValue="numero"/>
	           </form:select> <br/>
	    montant: <form:input path="montant" /> <form:errors path="montant" cssClass="error"/><br/>
	    <input  type="submit" value="effectuer virement" /> <br/>
	</form:form>
	<hr/>
	<a href="../app/to_welcome">retour page accueil</a> <br/>
</body>
</html>