
package tp.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tp.MySpringBootApplication;
import tp.entity.Devise;
import tp.service.GestionDevises;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
public class TestServiceGestionDevises { 
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceGestionDevises.class);
	

    @Autowired
	private GestionDevises service ; // service metier a tester
	
	
	@Test
   public void test_getDeviseByName() {
     try{
    	 logger.debug("test_getDeviseByName");
        Devise d = service.getDeviseByName("euro");
        logger.debug("monnaie euro (getDeviseByName) : " + d);
        Assert.assertTrue(d.getMonnaie().equals("euro"));
        }catch(Exception /*ServiceException*/ ex){
        	logger.error(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   }
	@Test
   public void test_convertir() {
     try{
    	 logger.debug("test_convertir");
    	 logger.debug("50 euros= "  + service.convertir(50, "euro", "yen") + " yens");
    	 logger.debug("20 euros= "  + service.convertir(20, "euro", "dollar") + " dollars");
        }catch(Exception /*ServiceException*/ ex){
      	    System.err.println(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   }
	@Test
   public void test_getListeDevises() {
     try{
    	 logger.debug("test_getListeDevises");
       for(Devise d : service.getListeDevises()){
    	   logger.debug("\t"+d);
       }
        }catch(Exception /*ServiceException*/ ex){
        	logger.error(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   }
      
}
