package tp.myapp.minibank.test.withmock.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import tp.myapp.minibank.impl.persistence.dao.ClientDao;
import tp.myapp.minibank.impl.persistence.dao.CompteDao;
import tp.myapp.minibank.impl.persistence.entity._Compte;

// MyMockSpringConfig sera prise en compte par Spring si context:annot.... ou bien javaConfig

@Configuration
public class MyMockSpringConfig {
	
CompteDao compteDaoMock;
ClientDao clientDaoMock;

public MyMockSpringConfig(){
	super();
}

@Bean
@Scope("singleton") //pratique pour injecter plusieurs fois meme instance 
//--> init supplémentaire et verify dans test unitaire , effectuer s'il le faut un Mockito.reset()
//@Scope("prototype") //ok au sens "mock unitaire" mais impossible à injecter plusieurs fois même instance
public CompteDao getCompteDaoMock(){
	compteDaoMock = Mockito.mock(CompteDao.class);
	Mockito.when(compteDaoMock.getEntityById(1L)).thenReturn(new _Compte(1L,"compte_1_que_j_adore",53.0));
	//....
	return compteDaoMock;
}
	
@Bean
public ClientDao getClientDaoMock(){
	clientDaoMock = Mockito.mock(ClientDao.class);
	return clientDaoMock;
}
	

}
