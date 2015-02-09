package tp.myapp.minibank.itf.domain.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import tp.myapp.minibank.itf.domain.dto.Client;

@WebService(targetNamespace="http://tp.myapp.minibank/")
public interface GestionClients {
	
	public Client getClientByNum(@WebParam(name="numCli")long numCli)
			throws MyServiceException;
	
	public boolean isGoodPasswordOfClient(@WebParam(name="numClient")long numClient,
										@WebParam(name="password")String password)
												throws MyServiceException;
	
	public void changePasswordOfClient(@WebParam(name="numClient")long numClient,
									@WebParam(name="password")String password)
											throws MyServiceException;

}
