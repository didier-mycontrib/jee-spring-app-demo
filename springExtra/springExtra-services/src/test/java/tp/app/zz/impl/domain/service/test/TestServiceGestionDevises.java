
package tp.app.zz.impl.domain.service.test;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tp.app.zz.data.entity.Devise;
import tp.app.zz.itf.domain.service.GestionDevises;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/springContext.xml"})
//@ContextConfiguration(classes={tp.app.zz.config.DomainAndPersistenceConfig.class}) //sans spring-boot
//@SpringApplicationConfiguration(classes={tp.app.zz.config.DomainAndPersistenceConfig.class}) //avec spring-boot
public class TestServiceGestionDevises { 

    @Inject
	private GestionDevises service = null; // service metier a tester
	
	
	@Test
   public void test_getDeviseByName() {
   //Start of user code test_getDeviseByName_implementation
     try{
        System.out.println("test_getDeviseByName");
        Devise d = service.getDeviseByName("euro");
        System.out.println("monnaie euro (getDeviseByName) : " + d);
        Assert.assertTrue(d.getMonnaie().equals("euro"));
        }catch(Exception /*ServiceException*/ ex){
      	    System.err.println(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   //End of user code
   }
	@Test
   public void test_convertir() {
   //Start of user code test_convertir_implementation
     try{
        System.out.println("test_convertir");
        System.out.println("50 euros= "  + service.convertir(50, "euro", "yen") + " yens");
        System.out.println("20 euros= "  + service.convertir(20, "euro", "dollar") + " dollars");
        }catch(Exception /*ServiceException*/ ex){
      	    System.err.println(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   //End of user code
   }
	@Test
   public void test_getListeDevises() {
   //Start of user code test_getListeDevises_implementation
     try{
        System.out.println("test_getListeDevises");
       for(Devise d : service.getListeDevises()){
    	   System.out.println("\t"+d);
       }
        }catch(Exception /*ServiceException*/ ex){
      	    System.err.println(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
   //End of user code
   }
      
}
