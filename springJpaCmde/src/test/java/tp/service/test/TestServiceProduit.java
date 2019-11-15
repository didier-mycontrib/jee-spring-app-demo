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

import tp.entity.Categorie;
import tp.entity.Produit;
import tp.service.ServiceProduit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/serviceSpringConf.xml")
public class TestServiceProduit {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceProduit.class);
	
	@Autowired
	private ServiceProduit serviceProduit; //a tester
	
	@Test
	public void testRechercherProduit(){
		Produit p1 = this.serviceProduit.rechercherProduit(1L);
		Assert.assertNotNull(p1);
		Assert.assertTrue(1L== p1.getNumero());
		logger.debug("testRechercherProduit , p1="+p1);
	}
	
	@Test
	public void testProduitsParCategorie(){
		List<Categorie> listCategories = serviceProduit.rechercherCategories();
		Assert.assertNotNull(listCategories);
		Assert.assertTrue(listCategories.size()>=1);
		logger.debug("testProduitsParCategorie , listCategories="+listCategories);
		Categorie premiereCategorie = listCategories.get(0);
		
		List<Produit> listProduits = serviceProduit.rechercherProduitsSelonCategorie(premiereCategorie.getNumero());
		logger.debug("testProduitsParCategorie , listProduits de la premiere categorie="+listProduits);
	}
	
	@Test
	public void testAttacherProduitACategorie(){
		Categorie c1 = serviceProduit.rechercherCategorieSelonNumero(1L);
		List<Produit> listProdOfC1 = serviceProduit.rechercherProduitsSelonCategorie(1L);
		int nbProdOfCategorie1AvantAjoutP5 = listProdOfC1.size();
		logger.debug("testAttacherProduitACategorie , nbProdOfCategorie1AvantAjoutP5="+nbProdOfCategorie1AvantAjoutP5);
		
		Produit p5 = serviceProduit.rechercherProduit(5L);
		c1 = serviceProduit.associerCategorieProduit(c1, p5);
		logger.debug("testAttacherProduitACategorie , produits categorie 1 apres ajout p5="+c1.getProduits());
		
		listProdOfC1 = serviceProduit.rechercherProduitsSelonCategorie(1L);
		Assert.assertTrue(listProdOfC1.size() == (nbProdOfCategorie1AvantAjoutP5 + 1));
		
		c1 = serviceProduit.dissocierCategorieProduit(c1, p5);
		logger.debug("testAttacherProduitACategorie , produits categorie 1 apres retrait p5="+c1.getProduits());
		listProdOfC1 = serviceProduit.rechercherProduitsSelonCategorie(1L);
		Assert.assertTrue(listProdOfC1.size() == (nbProdOfCategorie1AvantAjoutP5 +1 -1) );
	}
	
	@Test
	public void testCrudProduit(){
		//ajout et verification:
		Produit p = new Produit(null,"nouveau produit",2.23);
		Produit savedProd = serviceProduit.sauvegarderProduit(p);
		Long idProd = savedProd.getNumero();
		Assert.assertNotNull(idProd);
		logger.debug("testCrudProduit , savedProd="+savedProd);
		//mise à jour et verification:
		p=savedProd; p.setPrix(p.getPrix()*2); p.setLabel(p.getLabel()+"***");
		serviceProduit.sauvegarderProduit(p);
		Produit reloadedProd = serviceProduit.rechercherProduit(idProd);
		Assert.assertEquals(reloadedProd.getPrix(), p.getPrix(),0.01);
		logger.debug("testCrudProduit , reloaded updated Prod="+reloadedProd);
		//suppression et verification:
		serviceProduit.supprimerProduit(idProd);
		Assert.assertNull(serviceProduit.rechercherProduit(idProd));
	}

}
