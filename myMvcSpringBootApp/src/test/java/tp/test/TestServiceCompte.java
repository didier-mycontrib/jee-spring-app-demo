
package tp.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tp.MySpringBootApplication;
import tp.entity.Compte;
import tp.entity.Operation;
import tp.service.ServiceCompte;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
public class TestServiceCompte { 

	private static Logger logger = LoggerFactory.getLogger(TestServiceCompte.class);
	
	
    @Autowired
	private ServiceCompte serviceCompte ; //  a tester
	
    @Test
    public void test_virement(){
    	
    	double s1_avant = serviceCompte.rechercherCompte(1L).getSolde();
    	double s2_avant = serviceCompte.rechercherCompte(2L).getSolde();
    	logger.debug("avant s1="+s1_avant + " et s2=" + s2_avant);
    	serviceCompte.virement(50, 1L, 2L);
    	double s1_apres = serviceCompte.rechercherCompte(1L).getSolde();
    	double s2_apres = serviceCompte.rechercherCompte(2L).getSolde();
    	logger.debug("apres s1="+s1_apres + " et s2=" + s2_apres);
    	Assert.assertEquals(s1_apres, s1_avant - 50,0.01);
		Assert.assertEquals(s2_apres, s2_avant + 50,0.01);
    }
    
    @Test
    public void test_comptesDuClient(){
    	List<Compte> listeComptes = serviceCompte.rechercherComptesDuClient(1L);
    	Assert.assertTrue(listeComptes.size()>=2);
    	logger.debug("liste des comptes du client 1:");
    	for(Compte c : listeComptes)
    		logger.debug("\t"+c);
    }
	
    @Test
    public void test_compteAvecOperations(){
    	//Compte c = daoCompte.getEntityById(1L); //avec "lazy exception"
    	
    	Compte c = serviceCompte.rechercherCompteAvecOperations(1L);
    	logger.debug("compte_1 : "+c);
    	for(Operation op : c.getOperations()){
    		logger.debug("\t"+op);
    	}
    	Assert.assertTrue(c.getOperations().size()>0);
    }
	
      
}
