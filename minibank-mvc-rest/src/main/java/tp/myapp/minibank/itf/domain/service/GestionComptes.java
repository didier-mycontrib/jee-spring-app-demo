package tp.myapp.minibank.itf.domain.service;

import java.util.List;

import tp.myapp.minibank.details.DetailsLevel.DetailsLevelEnum;
import tp.myapp.minibank.persistence.entity.Compte;
import tp.myapp.minibank.persistence.entity.Operation;


public interface GestionComptes {
	
	public Compte getCompteByNum(long numCpt,DetailsLevelEnum ...detailsLevel) throws MyServiceException;
	
	public List<Compte> getComptesOfClient(long numClient) 
			                    throws MyServiceException;
	
	public List<Operation> getOperationsOfCompte(long numCpt) 
			throws MyServiceException;
	
	public void transferer(double montant,long numCptDeb,long numCptCred) 
			throws MyServiceException;

}
