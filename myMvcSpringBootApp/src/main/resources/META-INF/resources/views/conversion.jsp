<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>param conversion</title>
</head>
<body>
	<h3>conversion de devises (v1 sans form:form)</h3>
	<form action="doConversion" method="GET">
	    source: <select name="source" >
	              <c:forEach var="d" items="${allDevises}">
	              <c:choose>
	                <c:when test="${d.monnaie == monnaieSrc}">
	                   <option selected="selected" value="${d.monnaie}">${d.monnaie}</option> 
	                </c:when>
	                 <c:otherwise> 
	                      <option  value="${d.monnaie}">${d.monnaie}</option>
	                  </c:otherwise>
	                </c:choose>
	              </c:forEach>
	            </select>  <br/>
	    cible: <select name="cible" > 
	               <c:forEach var="d" items="${allDevises}">
	                <c:choose>
	                <c:when test="${d.monnaie == monnaieDest}">
	                   <option selected="selected" value="${d.monnaie}">${d.monnaie}</option> 
	                </c:when>
	                 <c:otherwise> 
	                      <option  value="${d.monnaie}">${d.monnaie}</option> 
	                 </c:otherwise>
	                 </c:choose>
	              </c:forEach>
	           </select> <br/>
	    montant: <input name="montant" value="${montant}"/> <br/>
	    <input  type="submit" value="convertir" /> <br/>
	</form>
	sommeConvertie=<b>${sommeConvertie}</b>
	<hr/>
	<a href="../app/to_welcome">retour welcome</a> <br/>
</body>
</html>