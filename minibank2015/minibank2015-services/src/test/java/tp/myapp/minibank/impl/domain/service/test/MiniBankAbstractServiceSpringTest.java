package tp.myapp.minibank.impl.domain.service.test;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tp.myapp.minibank.itf.domain.FacadeMiniBank;
import tp.myapp.minibank.itf.domain.FacadeMiniBankFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/serviceSpringConf.xml","/dataSourceForTestSpringConf.xml"})
public abstract class MiniBankAbstractServiceSpringTest {
	
	
	protected ApplicationContext applicationContext;
	
	protected FacadeMiniBank facadeMiniBank;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	@Inject
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		//this.facadeMiniBank = FacadeMiniBankFactory.getInstance(); //avec config Spring selon valeur par defaut de la facade 
		this.facadeMiniBank = FacadeMiniBankFactory.getInstanceFromContext(applicationContext);// avec config Spring choisie plus haut
	}
	
    
}
