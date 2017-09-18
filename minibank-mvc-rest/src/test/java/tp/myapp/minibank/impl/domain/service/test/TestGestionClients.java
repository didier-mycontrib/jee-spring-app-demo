package tp.myapp.minibank.impl.domain.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tp.myapp.minibank.itf.domain.service.GestionClients;
import tp.myapp.minibank.persistence.entity.Client;
	
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="/springContextOfModule.xml") // xml config
@ContextConfiguration(classes={tp.myapp.minibank.config.DomainAndPersistenceConfig.class}) //java config
public class TestGestionClients {
	
	@Autowired
	private GestionClients serviceGestionClients;
	
	
	@Test
	public void testGetClientByNum(){
		try {
			Client cli = serviceGestionClients.getClientByNum(1L);
			System.out.println("client 1 = " + cli.toString());
			Assert.assertTrue(cli.getNumero()==1L);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testPasswordOfClient(){
		try {
		
			System.out.println("affectation  et verification du mot de passe new-pwd1 au client 1  :");
			serviceGestionClients.changePasswordOfClient(1L, "new-pwd1");
			Assert.assertTrue(serviceGestionClients.isGoodPasswordOfClient(1L, "new-pwd1"));
			System.out.println("affectation et verification du mot de passe pwd1 au client 1  :");
			serviceGestionClients.changePasswordOfClient(1L, "pwd1");
			Assert.assertTrue(serviceGestionClients.isGoodPasswordOfClient(1L, "pwd1"));			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	
	
}
