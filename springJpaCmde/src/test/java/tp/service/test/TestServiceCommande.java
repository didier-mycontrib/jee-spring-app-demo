package tp.service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tp.entity.Client;
import tp.entity.Commande;
import tp.entity.LigneCommande;
import tp.entity.Produit;
import tp.service.ServiceClient;
import tp.service.ServiceCommande;
import tp.service.ServiceProduit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/serviceSpringConf.xml")
public class TestServiceCommande {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceCommande.class);
	
	@Autowired
	private ServiceCommande serviceCommande; //a tester
	
	@Autowired
	private ServiceClient serviceClient; //service annexe
	
	@Autowired
	private ServiceProduit serviceProduit; //service annexe
	
	@Test
	public void testRechercherCommande(){
		Commande c1 = this.serviceCommande.rechercherCommande(1L);
		Assert.assertNotNull(c1);
		Assert.assertTrue(1L== c1.getNumero());
		logger.debug("testRechercherCommande , c1="+c1);
	}
	
	@Test
	public void testCrudCommande(){
		//ajout et verification:
		Commande c = new Commande(null,null);
		Commande savedCmde = serviceCommande.sauvegarderCommande(c);
		Long idCmde = savedCmde.getNumero();
		Assert.assertNotNull(idCmde);
		logger.debug("testCrudCommande , savedCmde="+savedCmde);
		//ajout/rattachements:
		Client cli1 = serviceClient.rechercherClient(1L);
		c=savedCmde; serviceCommande.attacherClient(c, cli1);
		
		Produit p1 = serviceProduit.rechercherProduit(1L);
		serviceCommande.ajouterLigne(c, p1, 2);
		Produit p2 = serviceProduit.rechercherProduit(2L);
		serviceCommande.ajouterLigne(c, p2, 3);
		
		Commande reloadedCommande = serviceCommande.rechercherCommande(idCmde);
		Assert.assertNotNull(reloadedCommande.getClient());
		logger.debug("testCrudCommande , client rattache a la commande="+reloadedCommande.getClient());
		List<LigneCommande> LignesCommande = serviceCommande.recupererLignesDeCommande(idCmde);
		Assert.assertTrue(LignesCommande.size()>=2);
		for(LigneCommande ligne : LignesCommande){
			logger.debug("testCrudCommande , ligne de commande="+ligne);
		}
		
		//suppression et verification:
		serviceCommande.supprimerCommande(idCmde);
		Assert.assertNull(serviceCommande.rechercherCommande(idCmde));
	}

}
