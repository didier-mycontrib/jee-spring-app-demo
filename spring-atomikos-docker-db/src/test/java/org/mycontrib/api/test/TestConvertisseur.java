package org.mycontrib.api.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mycontrib.ext.MySpringBootApplication;
import org.mycontrib.ext.devises.service.Convertisseur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
@ActiveProfiles("reInit,embeddedDb,permitAllSecurity")
//@ActiveProfiles("reInit,remoteDb,permitAllSecurity")
//@ActiveProfiles("noReInit,remoteDb,permitAllSecurity")
public class TestConvertisseur {
	
	private static Logger logger = LoggerFactory.getLogger(TestConvertisseur.class);
	
	@Autowired
	private Convertisseur convertisseur ; //à tester
	
	
	@Test
	public void testConvertir() {
		double resConv = convertisseur.convertir(50, "EUR", "USD");
		Assert.assertTrue(resConv >40);
		logger.info("resConv="+resConv);
	}
		

	
}
