package tp.appliSpring6.core.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring6.core.entity.Devise;

@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest
@ActiveProfiles({ "embeddedDB" , "dev" })
//@ActiveProfiles({ "remoteDB" , "dev" })
public class TestServiceDevise {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceDevise.class);
	
	@Autowired
	private DeviseService deviseService; //à tester
	
	
	@Test
	public void testRechercherDeviseParNumero() {

		Devise deviseEuro =  new Devise("EUR","euro",1.0);
		deviseService.sauvegarderDevise(deviseEuro); //INSERT INTO
	
		Devise deviseRelu = this.deviseService.rechercherDeviseParCode("EUR"); //SELECT
		Assertions.assertEquals("euro",deviseRelu.getNom());
		Assertions.assertEquals(1.0,deviseRelu.getChange());
		logger.debug("deviseRelu=" + deviseRelu);
		
	}
	
	
}
