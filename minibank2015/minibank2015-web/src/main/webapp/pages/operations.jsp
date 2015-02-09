<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>operation.jsp</title>
</head>
<body>
<f:view>
    <h3>(dernieres) operations du compte  <h:outputText  value="#{operations.numCpt}"/> </h3>
     <hr>
	<h:dataTable var="op" border="2" value="#{operations.listeOperations}">
	   <h:column>
	     <f:facet name="header"><f:verbatim>numero</f:verbatim></f:facet>
	     <h:outputText  value="#{op.numero}"/>
	   </h:column>
	    <h:column>
	     <f:facet name="header"><f:verbatim>date</f:verbatim></f:facet>
	     <h:outputText  value="#{op.dateOp}"/>
	   </h:column>
	   <h:column>
	     <f:facet name="header"><f:verbatim>label</f:verbatim></f:facet>
	     <h:outputText  value="#{op.label}"/>
	   </h:column>
	   <h:column>
	     <f:facet name="header"><f:verbatim>montant</f:verbatim></f:facet>
	     <h:outputText  value="#{op.montant}"/>
	   </h:column>
	</h:dataTable>
	<hr/>
	<a href="listeComptes.jsf" >retourner à la liste des comptes</a>
</f:view>
</body>
</html>