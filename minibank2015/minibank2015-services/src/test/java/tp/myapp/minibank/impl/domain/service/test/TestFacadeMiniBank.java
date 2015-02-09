package tp.myapp.minibank.impl.domain.service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import tp.myapp.minibank.itf.domain.dto.Compte;
import tp.myapp.minibank.itf.domain.service.MyServiceException;

public class TestFacadeMiniBank extends MiniBankAbstractServiceSpringTest {
		
	
	@Test
	public void testGetComptesOfClient(){
		try {
			System.out.println("liste des comptes du client 1 :");
			List<Compte> listeCpt = facadeMiniBank.getServiceGestionComptes().getComptesOfClient(1L);
			for(Compte cpt:listeCpt){
				System.out.println("\t"+cpt.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

}
