<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>member</title>
</head>
<body>
   page normalement accessible que pour les personnes ayant un 
   des roles suivants: 'ROLE_SUPERVISOR','ROLE_TELLER'
   <hr/>
   num session http/jee= <%=session.getId()%>
   <hr/>
   <a href="../app/finSession">fin de session / deconnexion</a>
   
</body>
</html>