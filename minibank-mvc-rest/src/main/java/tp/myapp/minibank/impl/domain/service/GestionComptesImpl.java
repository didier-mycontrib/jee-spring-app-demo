package tp.myapp.minibank.impl.domain.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.myapp.minibank.details.DetailsFetch;
import tp.myapp.minibank.details.DetailsLevel.DetailsLevelEnum;
import tp.myapp.minibank.itf.domain.service.GestionComptes;
import tp.myapp.minibank.itf.domain.service.MyServiceException;
import tp.myapp.minibank.persistence.dao.CompteDao;
import tp.myapp.minibank.persistence.entity.Compte;
import tp.myapp.minibank.persistence.entity.Operation;

@Service
@Transactional()
public class GestionComptesImpl implements GestionComptes {
	
	private static Logger logger = LoggerFactory.getLogger(GestionComptesImpl.class);
	
	@Autowired
	private CompteDao compteDao;
	
	
	public Compte getCompteByNum(long numCpt,DetailsLevelEnum ... detailsLevel) throws MyServiceException {
		return  DetailsFetch.fetchOptionalDetails( 
				               compteDao.findOne(numCpt) , detailsLevel );
	}

	@Override
	public List<Compte> getComptesOfClient(long numClient)
			throws MyServiceException {		
		List<Compte> listeCpt = null;
		try {
			listeCpt = compteDao.findComptesByNumCli(numClient);
		} catch (Exception e) {
			logger.error("echec GestionComptesImpl.getComptesOfClient",e);
			throw new MyServiceException("echec GestionComptesImpl.getComptesOfClient",e);
		}
		return listeCpt;
	}
	
	

	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred)
			throws MyServiceException {
		try {
			Compte cptDeb = compteDao.findOne(numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			cptDeb.addOperation(new Operation("debit virement",-montant));
			//compteDao.updateEntity(cptDeb);//inutile si cptDeb est persistant (du fait de @Transactional)
			Compte cptCred = compteDao.findOne(numCptCred);
			cptCred.setSolde(cptCred.getSolde() + montant);
			cptCred.addOperation(new Operation("credit virement",+montant));
			//compteDao.updateEntity(cptCred);//inutile si cptCred est persistant (du fait de @Transactional)
		} catch (Exception e) {
			logger.error("echec GestionComptesImpl.transferer",e);
			throw new MyServiceException("echec GestionComptesImpl.transferer",e);
		}

	}

	@Transactional(readOnly=true,rollbackFor=MyServiceException.class)
	public List<Operation> getOperationsOfCompte(long numCpt)
			throws MyServiceException {
		List<Operation> listeOp = null;
		try {
			Compte cpt= compteDao.findOne(numCpt);
			listeOp = cpt.getOperations();
			listeOp.size(); //for lazy loading
		} catch (Exception e) {
			logger.error("echec GestionComptesImpl.getOperationsOfCompte",e);
			throw new MyServiceException("echec GestionComptesImpl.getgetOperationsOfCompte",e);
		}
		return listeOp;
	}

	public CompteDao getCompteDao() {
		return compteDao;
	}

	public void setCompteDao(CompteDao compteDao) {
		this.compteDao = compteDao;
	}
	
	

}
