package tp.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tp.entity.AdresseDuClient;
import tp.entity.Client;
import tp.service.ServiceClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/serviceSpringConf.xml")
public class TestServiceClient {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceClient.class);
	
	@Autowired
	private ServiceClient serviceClient; //a tester
	
	@Test
	public void testRechercherClient(){
		Client c1 = this.serviceClient.rechercherClient(1L);
		Assert.assertNotNull(c1);
		Assert.assertTrue(1L== c1.getNumero());
		logger.debug("testRechercherClient , c1="+c1
				+ " habitant " + c1.getAdresse());
	}
	
	@Test
	public void testCrudClient(){
		//ajout et verification:
		Client c = new Client(null,"nouveau Client");
		AdresseDuClient adresse = new AdresseDuClient("rue xy","69000","Lyon");
		Client savedClient = serviceClient.sauvegarderClient(c);
		Long idCli= savedClient.getNumero();
		savedClient = serviceClient.associerAdresseAuClient(savedClient, adresse);
		Assert.assertNotNull(idCli);
		logger.debug("testCrudClient , savedClient="+savedClient
				    + " habitant " + savedClient.getAdresse());
		//mise à jour et verification:
		c=savedClient; c.setNom(c.getNom()+"***");
		serviceClient.sauvegarderClient(c);
		Client reloadedClient = serviceClient.rechercherClient(idCli);
		Assert.assertEquals(reloadedClient.getNom(), c.getNom());
		logger.debug("testCrudClient , reloaded updated Client="+reloadedClient);
		//suppression et verification:
		serviceClient.supprimerClient(idCli);
		Assert.assertNull(serviceClient.rechercherClient(idCli));
	}

}
