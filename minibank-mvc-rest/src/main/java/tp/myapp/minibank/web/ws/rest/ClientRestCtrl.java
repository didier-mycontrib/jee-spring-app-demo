package tp.myapp.minibank.web.ws.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tp.myapp.minibank.itf.domain.service.GestionClients;
import tp.myapp.minibank.itf.domain.service.MyServiceException;
import tp.myapp.minibank.persistence.entity.Client;
import tp.myapp.minibank.web.ws.rest.data.ClientAuth;

@RestController 
@RequestMapping(value="/rest/clients" , headers="Accept=application/json")
public class ClientRestCtrl {
	@Autowired
	GestionClients gestionClients; //internal service
	
	// ancienne URL = http://localhost:8080/minibank2015-web/services/rest/json/gestionclients/clients/1
	// nouvelle URL = http://localhost:8080/minibank-mvc-rest/mvc/rest/clients/1
	@RequestMapping(value="/{numCli}" , method=RequestMethod.GET)
	public ResponseEntity<Client> clientByNum(@PathVariable("numCli") Long numCli){
		Client cli = gestionClients.getClientByNum(numCli);
		return new ResponseEntity<Client>(cli, HttpStatus.OK);
	}

	@RequestMapping(value="/verifyAuth" , method=RequestMethod.POST)
	// pour URL = http://localhost:8080/minibank-mvc-rest/mvc/rest/clients/verifyAuth
	// input : ClientAuth (json) with password to verify
	// return : ClientAuth (json) with ok=false or true
	public ResponseEntity<ClientAuth> verifyAuth(@RequestBody ClientAuth clientAuth){		
		boolean ok = false;
		try {
			System.out.println("verifyAuth was called with " + clientAuth.toString());
			ok=  gestionClients.isGoodPasswordOfClient(clientAuth.getNumClient(), clientAuth.getPassword());
		} catch (MyServiceException e) {
			e.printStackTrace();
		}
		clientAuth.setOk(ok);
		return new ResponseEntity<ClientAuth>(clientAuth, HttpStatus.OK);
	}

	
	@RequestMapping(value="/settingAuth" , method=RequestMethod.POST)
	// pour URL = http://localhost:8080/minibank-mvc-rest/mvc/rest/clients/settingAuth
	// input : ClientAuth (json) with new password to set
	// return : ClientAuth (json) with ok=false or true if setting 
	public ResponseEntity<ClientAuth> settingAuth(@RequestBody ClientAuth clientAuth){		
		try {
			gestionClients.changePasswordOfClient(clientAuth.getNumClient(), clientAuth.getPassword());
			clientAuth.setOk(true);
		} catch (MyServiceException e) {
			clientAuth.setOk(false);
			e.printStackTrace();
		}
		return new ResponseEntity<ClientAuth>(clientAuth, HttpStatus.OK);
	}
	
	

}
