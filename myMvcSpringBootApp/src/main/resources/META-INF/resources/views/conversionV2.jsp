<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>param conversion</title>
<style>
    .error {
        color: red; font-weight: bold;
    }
</style>
</head>
<body>
	<h3>conversion de devises (v2 avec form:form)</h3>
	<form:form action="doConversion"  modelAttribute="conv" method="POST">
	    source: <form:select path="monnaieSrc" >
	              <form:options items="${allDevises}" itemLabel="monnaie" itemValue="monnaie"/>
	            </form:select>  <br/>
	    cible: <form:select path="monnaieDest" > 
	              <form:options items="${allDevises}" itemLabel="monnaie" itemValue="monnaie"/>
	           </form:select> <br/>
	    montant: <form:input path="montant" /> <form:errors path="montant" cssClass="error"/><br/>
	    <input  type="submit" value="convertir" /> <br/>
	</form:form>
	sommeConvertie=<b>${sommeConvertie}</b>
	<hr/>
	<a href="../app/to_welcome">retour welcome</a> <br/>
</body>
</html>