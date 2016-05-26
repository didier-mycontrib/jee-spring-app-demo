<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>conversion</title>
</head>
<body>
    <f:view>
	<h3>conversion</h3>
	<h:form id="frm">
	   monnaie1 (source):<h:inputText id="m1" value="#{conversion.monnaie1}"/><br/>
	   monnaie2 (cible) :<h:inputText id="m2" value="#{conversion.monnaie2}"/><br/>
	   montant:<h:inputText id="montantInput" value="#{conversion.montant}"/><br/>
	   <h:commandButton  id="convertButton" value="convertir" action="#{conversion.convertir}"  /> <br/>
	   somme convertie: <h:outputText id="somme_convertie" value="#{conversion.sommeConvertie}"/>
	</h:form>
	</f:view>
</body>
</html>