<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>virement.jsp</title>
</head>
<body>
<f:view>
	<h3>virement interne</h3>
	<h:form id="virement">
	    montant à transférer:  <h:inputText id="montant" value="#{virement.montant}"/><br/><br/>
	 
	    compte à débiter: <h:selectOneMenu id="numCptDeb" value="#{virement.numCptDeb}">
	    	<f:selectItems value="#{virement.comptesPossibles}" />
	    </h:selectOneMenu><br/><br/><br/>
	    compte à créditer: <h:selectOneMenu id="numCptCred" value="#{virement.numCptCred}">
	    	<f:selectItems value="#{virement.comptesPossibles}" />
	    </h:selectOneMenu> <br/>
	    <h:commandButton value="effectuer virement" 
	                     action="#{virement.effectuerVirement}"/>
	</h:form>
	<h:messages/> 
</f:view>
</body>
</html>