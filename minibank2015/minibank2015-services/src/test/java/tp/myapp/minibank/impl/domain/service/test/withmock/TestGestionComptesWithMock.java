package tp.myapp.minibank.impl.domain.service.test.withmock;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tp.myapp.minibank.impl.persistence.dao.CompteDao;
import tp.myapp.minibank.impl.persistence.entity._Compte;
import tp.myapp.minibank.itf.domain.dto.Compte;
import tp.myapp.minibank.itf.domain.service.GestionComptes;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mockServiceSpringConf.xml"})
public class TestGestionComptesWithMock {
	
	@Inject
	private GestionComptes serviceGestionComptes;
	
	@Inject 
	private CompteDao compteDaoMock ; 
	
	@Test
	public void testGetCompteByNum(){
		try {
			//initialiser (ici ou dans initMethod() prefixee par @Before) le comportement du mock:
			Mockito.reset(compteDaoMock);//éventuel besoin (dans un contexte spring) de ré-initialiser le "mock"
			                             //potentiellement injecté/réutilisé à plusieurs endroits
			Mockito.when(compteDaoMock.getEntityById(1L)).thenReturn(new _Compte(1L,"compte_1_que_j_aime",52.0));
			
			
			Compte cpt = serviceGestionComptes.getCompteByNum(1L);
			System.out.println("compte 1 = " + cpt.toString());
			Assert.assertTrue(cpt.getNumero()==1L);
			
			//vérifier (à la fin) que la méthode getEntityById() a bien été appelée 
			//du composant "serviceGestionComptes" vers le sous composant "compteDao"
			//avec un paramètre ayant une valeur à 1.
			Mockito.verify(compteDaoMock).getEntityById(1L);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	/*
	@Test
	public void testGetComptesOfClient(){
		try {
			System.out.println("liste des comptes du client 1 :");
			List<Compte> listeCpt = serviceGestionComptes.getComptesOfClient(1L);
			for(Compte cpt:listeCpt){
				System.out.println("\t"+cpt.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetOperationsOfCompte(){
		try {
			System.out.println("liste des operations du compte 1 :");
			List<Operation> listeOp = serviceGestionComptes.getOperationsOfCompte(1L);
			for(Operation op:listeOp){
				System.out.println("\t"+op.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testBonTransfert(){		
		try {
			System.out.println("test de virement interne");
			double s1_avant = serviceGestionComptes.getCompteByNum(1L).getSolde();
			double s2_avant = serviceGestionComptes.getCompteByNum(2L).getSolde();
			System.out.println("avant bon virement s1="+s1_avant + " s2=" +s2_avant);
			serviceGestionComptes.transferer(50,1L,2L);
			double s1_apres = serviceGestionComptes.getCompteByNum(1L).getSolde();
			double s2_apres = serviceGestionComptes.getCompteByNum(2L).getSolde();
			System.out.println("apres bon virement s1="+s1_apres + " s2=" +s2_apres);
			Assert.assertEquals(s1_apres, s1_avant - 50,0.01);
			Assert.assertEquals(s2_apres, s2_avant + 50,0.01);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testMauvaisTransfert(){		
		try {
			System.out.println("test de rollback sur virement interne avec mauvais numCptCred");
			double s1_avant = serviceGestionComptes.getCompteByNum(1L).getSolde();
			double s2_avant = serviceGestionComptes.getCompteByNum(2L).getSolde();
			System.out.println("avant mauvais virement s1="+s1_avant + " s2=" +s2_avant);
			try {
				serviceGestionComptes.transferer(50,1L,-2L);
			} catch (Exception e) {
				System.out.println("echec normal (virement vers compte -2)");
				//e.printStackTrace();
			}
			double s1_apres = serviceGestionComptes.getCompteByNum(1L).getSolde();
			double s2_apres = serviceGestionComptes.getCompteByNum(2L).getSolde();
			System.out.println("apres mauvais virement s1="+s1_apres + " s2=" +s2_apres);
			Assert.assertEquals(s1_apres, s1_avant - 0,0.01);
			Assert.assertEquals(s2_apres, s2_avant + 0,0.01);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		
	}
    */
}
