package org.mycontrib.tp.serverRest.test;

//pour assertTrue (res==5) au lieu de Assertions.assertTrue(res==5)
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mycontrib.tp.serverRest.ServerRestApplication;
import org.mycontrib.tp.serverRest.entity.Devise;
import org.mycontrib.tp.serverRest.service.ServiceDevise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= {ServerRestApplication.class})
//@ActiveProfiles("embeddedDb")
public class TestServiceDevise {
	
	@Autowired
	private ServiceDevise serviceDevise; //à tester
	
	public void init() {
		serviceDevise.createDevise(new Devise("EUR","Euro",1.0));
		serviceDevise.createDevise(new Devise("USD","Dollar",1.1));
		serviceDevise.createDevise(new Devise("GBP","Livre",0.9));
		serviceDevise.createDevise(new Devise("JPY","Yen",123.6));
	}
	
	@Test
	public void testRechercherDevises() {
		init();
		List<Devise> listeDevises = serviceDevise.rechercherDevises();
		System.out.println("listeDevises="+listeDevises);
		assertTrue(listeDevises.size()>=4);
	}
	
	

}
