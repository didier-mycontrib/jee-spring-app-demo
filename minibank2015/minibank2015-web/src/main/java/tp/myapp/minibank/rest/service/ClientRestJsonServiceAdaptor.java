package tp.myapp.minibank.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import tp.myapp.minibank.itf.domain.dto.Client;
import tp.myapp.minibank.itf.domain.service.GestionClients;
import tp.myapp.minibank.itf.domain.service.MyServiceException;
import tp.myapp.minibank.rest.service.data.ClientAuth;

@Path("/json/gestionclients")
@Produces("application/json")
@Consumes("application/json")
@CrossOriginResourceSharing(allowAllOrigins = true)// ou bien autorisations plus fines
public class ClientRestJsonServiceAdaptor {
	
private GestionClients serviceGestionClients;//injected by spring

public void setServiceGestionClients(GestionClients serviceGestionClients) {
	this.serviceGestionClients = serviceGestionClients;
}
	
@GET
@Path("clients/{num}")
// pour URL = http://localhost:8080/minibank2015-web/services/rest/json/gestionclients/clients/1
public Client getClientByNum(@PathParam("num")Long num){		
	Client cli = null;
	try {
		cli = serviceGestionClients.getClientByNum(num);
	} catch (MyServiceException e) {
		e.printStackTrace();
	}
	return cli;
}
	


@POST
@Path("/verifyAuth")
// pour URL = http://localhost:8080/minibank-dry/services/rest/json/gestionclients/verifyAuth
// input : ClientAuth (json) with password to verify
// return : ClientAuth (json) with ok=false or true
public ClientAuth verifyAuth(ClientAuth clientAuth){		
	boolean ok = false;
	try {
		System.out.println("verifyAuth was called with " + clientAuth.toString());
		ok=  serviceGestionClients.isGoodPasswordOfClient(clientAuth.getNumClient(), clientAuth.getPassword());
	} catch (MyServiceException e) {
		e.printStackTrace();
	}
	clientAuth.setOk(ok);
	return clientAuth;
}

@POST
@Path("/settingAuth")
// pour URL = http://localhost:8080/minibank-dry/services/rest/json/gestionclients/settingAuth
// input : ClientAuth (json) with new password to set
// return : ClientAuth (json) with ok=false or true if setting 
public ClientAuth settingAuth(ClientAuth clientAuth){		
	try {
		serviceGestionClients.changePasswordOfClient(clientAuth.getNumClient(), clientAuth.getPassword());
		clientAuth.setOk(true);
	} catch (MyServiceException e) {
		clientAuth.setOk(false);
		e.printStackTrace();
	}
	return clientAuth;
}
	
}
