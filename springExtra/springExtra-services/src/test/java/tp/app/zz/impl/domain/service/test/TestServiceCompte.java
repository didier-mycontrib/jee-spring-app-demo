
package tp.app.zz.impl.domain.service.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tp.app.zz.config.DomainAndPersistenceConfig;
import tp.app.zz.data.entity.Compte;
import tp.app.zz.data.entity.Operation;
import tp.app.zz.itf.domain.service.ServiceCompte;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"/springContext.xml"})
@ContextConfiguration(classes={DomainAndPersistenceConfig.class})
public class TestServiceCompte { 

    @Inject
	private ServiceCompte serviceCompte = null; //  a tester
	
    @Test
    public void test_virement(){
    	double s1_avant = serviceCompte.rechercherCompte(1L).getSolde();
    	double s2_avant = serviceCompte.rechercherCompte(2L).getSolde();
    	System.out.println("avant s1="+s1_avant + " et s2=" + s2_avant);
    	serviceCompte.virement(50, 1L, 2L);
    	double s1_apres = serviceCompte.rechercherCompte(1L).getSolde();
    	double s2_apres = serviceCompte.rechercherCompte(2L).getSolde();
    	System.out.println("apres s1="+s1_apres + " et s2=" + s2_apres);
    	Assert.assertEquals(s1_apres, s1_avant - 50,0.01);
		Assert.assertEquals(s2_apres, s2_avant + 50,0.01);
    }
    
    @Test
    public void test_comptesDuClient(){
    	List<Compte> listeComptes = serviceCompte.rechercherComptesDuClient(1L);
    	Assert.assertTrue(listeComptes.size()>=2);
    	System.out.println("liste des comptes du client 1:");
    	for(Compte c : listeComptes)
    		System.out.println("\t"+c);
    }
	
    @Test
    public void test_compteAvecOperations(){
    	//Compte c = daoCompte.getEntityById(1L); //avec "lazy exception"
    	
    	Compte c = serviceCompte.rechercherCompteAvecOperations(1L);
    	System.out.println("compte_1 : "+c);
    	for(Operation op : c.getOperations()){
    		System.out.println("\t"+op);
    	}
    	Assert.assertTrue(c.getOperations().size()>0);
    }
	
      
}
