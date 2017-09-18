package tp.myapp.minibank.impl.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.myapp.minibank.itf.domain.service.GestionClients;
import tp.myapp.minibank.itf.domain.service.MyServiceException;
import tp.myapp.minibank.persistence.dao.ClientDao;
import tp.myapp.minibank.persistence.entity.Client;

@Service
@Transactional()
public class GestionClientsImpl implements GestionClients {
	
	
private static Logger logger = LoggerFactory.getLogger(GestionClientsImpl.class);
	
 
	@Autowired
	private ClientDao clientDao;
	

	public Client getClientByNum(long numCli) throws MyServiceException {
		Client client=  clientDao.findOne(numCli);
		//client.getAdresse().getIdAdr();//si LAZY entre Client et Adresse
		//appel ici (dans @Transactional) pour eviter lazy exception
		return client;
	}


	public boolean isGoodPasswordOfClient(long numClient, String password)
			throws MyServiceException {
		boolean res=false;
		try {
			Client cli= clientDao.findOne(numClient);
			if(cli.getPassword()!=null && cli.getPassword().equals(password))
				res=true;
		} catch (Exception e) {
			logger.error("echec GestionClientsImpl.isGoodPasswordOfClient",e);
			throw new MyServiceException("echec GestionClientsImpl.isGoodPasswordOfClient",e);
		}
		return res;
	}

	@Override
	public void changePasswordOfClient(long numClient, String password)
			throws MyServiceException {
		try {
			Client cli= clientDao.findOne(numClient);
			cli.setPassword(password);
			//clientDao.save(cli); pas utile cat cli persistant du fait de @Transactional
		} catch (Exception e) {		
			logger.error("echec GestionClientsImpl.changePasswordOfClient",e);
			throw new MyServiceException("echec GestionClientsImpl.changePasswordOfClient",e);
		}
	}

}
