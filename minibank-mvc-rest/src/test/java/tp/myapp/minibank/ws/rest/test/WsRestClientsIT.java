package tp.myapp.minibank.ws.rest.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import tp.myapp.minibank.persistence.entity.Client;
import tp.myapp.minibank.web.ws.rest.data.ClientAuth;
	
//NB: ...IT = convention des noms des "Integration Test" avec Maven

//NB: ici volontairement pas de @RunWith(SpringJUnit4ClassRunner.class)
//ni de @ContextConfiguration
//car ce test EXTERNE d'intégration est supposé être indépendant de la
//structure interne de l'application à tester (et à préalableament démarrer dans Tomcat)
public class WsRestClientsIT {
	
	private static Logger logger = LoggerFactory.getLogger(WsRestClientsIT.class);
	
	private static RestTemplate restTemplate;
	
	@BeforeClass
	public static void initRestTemplate(){
		restTemplate = new RestTemplate();
	}
	
	
	@Test
	public  void testRestGetClientByNum()
	{
		final long numCli = 1L;
	    final String uri = "http://localhost:8080/minibank-mvc-rest/mvc/rest/clients/"+numCli;
	    String resultAsJsonString = restTemplate.getForObject(uri, String.class);
	    logger.info("json client 1 via rest: " + resultAsJsonString);
	    Client cli = restTemplate.getForObject(uri, Client.class);
	    //logger.info("java client 1 via rest: "  +client.toString());
	    Assert.assertTrue(cli.getNumero()==1L);
	}
	
	@Test
	public  void testRestSettingAuth()
	{
	    final String uri = "http://localhost:8080/minibank-mvc-rest/mvc/rest/clients/settingAuth";
	    ClientAuth newClientAuth = new ClientAuth();
	    newClientAuth.setNumClient(1L);
	    newClientAuth.setPassword("nouveau-pwd1");
	    newClientAuth.setOk(null);
	    /*
	    ClientAuth savedClientAuth = restTemplate.postForObject(uri, newClientAuth, ClientAuth.class);
	    logger.info("savedClientAuth via rest: " + savedClientAuth.toString());
	    Assert.assertTrue(savedClientAuth.getOk());
	    */
	    String savedClientAuthAsJsonString = restTemplate.postForObject(uri, newClientAuth, String.class);
	    logger.info("savedClientAuth via rest (as Json String): " + savedClientAuthAsJsonString);

	}
	
	
	
	
}
