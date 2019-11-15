<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>identification client banque</title>
</head>
<body>
	<h3>identification client banque</h3>
	<hr/>
	<form action="identification" method="POST">
	    numClient : <input name="numClient" type="text" /> <br/>
	    <input  type="submit" value="identification client banque" /> <br/>
	    <input type="hidden"   name="${_csrf.parameterName}"      value="${_csrf.token}"/>
	</form>
</body>
</html>