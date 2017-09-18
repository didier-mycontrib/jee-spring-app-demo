package tp.myapp.minibank.itf.domain.service;

import tp.myapp.minibank.persistence.entity.Client;

public interface GestionClients {
	
	public Client getClientByNum(long numCli)
			throws MyServiceException;
	
	public boolean isGoodPasswordOfClient(long numClient,String password)
												throws MyServiceException;
	
	public void changePasswordOfClient(long numClient,String password)
											throws MyServiceException;

}
