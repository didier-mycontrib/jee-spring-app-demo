<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>error</title>
</head>
<body>
	<h3>erreur technique (spring web mvc)</h3>
	exception=<i>${exception.message}</i> <br/>
	url=${url}
	<hr/>
	<a href="../app/to_welcome">retour welcome</a> <br/>
</body>
</html>