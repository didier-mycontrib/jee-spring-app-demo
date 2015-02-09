<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>listeComptes.jsp</title>
</head>
<body>
<f:view>
    
    <h3>client identifié [ numClient: <b><h:outputText  value="#{clientComptes.numClient}"/></b> ]</h3>
     prenom :  <b><h:outputText  value="#{clientComptes.client.prenom}"/></b> &nbsp;&nbsp; nom: <b><h:outputText  value="#{clientComptes.client.nom}"/></b><br/>
     adresse : <b><h:outputText  value="#{clientComptes.client.adresse.rue}"/></b> &nbsp;&nbsp;
       <b><h:outputText value="#{clientComptes.client.adresse.codePostal}" /></b> &nbsp;&nbsp; 
       <b><h:outputText value="#{clientComptes.client.adresse.ville}" /></b> <br/>
     telephone: <b><h:outputText  value="#{clientComptes.client.telephone}"/></b><br/>
     email :  <b><h:outputText  value="#{clientComptes.client.email}"/></b><br/>
     <hr/>
     <h:outputText  value="#{clientComptes.message}"/><br/>
     <hr/>
	<h3>liste des comptes</h3>
	<h:form id="comptes">
	<h:dataTable var="cpt" border="2" value="#{clientComptes.listeComptes}">
	   <h:column>
	     <f:facet name="header"><f:verbatim>numero</f:verbatim></f:facet>
	     <h:outputText  value="#{cpt.numero}"/>
	   </h:column>
	   <h:column>
	     <f:facet name="header"><f:verbatim>label</f:verbatim></f:facet>
	     <h:outputText  value="#{cpt.label}"/>
	   </h:column>
	   <h:column>
	     <f:facet name="header"><f:verbatim>solde</f:verbatim></f:facet>
	     <h:outputText  value="#{cpt.solde}"/>
	   </h:column>
	   <h:column>
			<f:facet name="header"><f:verbatim>détails</f:verbatim></f:facet>
			<h:commandLink action="#{operations.listerDernieresOperations}"
				value="dernières opérations" >
					<f:param name="paramNumCptSel"
						value="#{cpt.numero}"
				binding="#{operations.paramNumCptSel}" />
			</h:commandLink>
		</h:column>	   
	</h:dataTable>
	<h:commandLink action="#{clientComptes.deconnexion}" >se déconnecter </h:commandLink>
	</h:form>
	<hr/>
	<a href="virement.jsf">effectuer un virement interne</a><br/>	
</f:view>
</body>
</html>