package tp.myapp.minibank.impl.domain.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

import org.mycontrib.generic.converter.GenericBeanConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import tp.myapp.minibank.impl.persistence.dao.CompteDao;
import tp.myapp.minibank.impl.persistence.entity._Compte;
import tp.myapp.minibank.impl.persistence.entity._Operation;
import tp.myapp.minibank.itf.domain.dto.Compte;
import tp.myapp.minibank.itf.domain.dto.Operation;
import tp.myapp.minibank.itf.domain.service.GestionComptes;
import tp.myapp.minibank.itf.domain.service.MyServiceException;

@Named
@Transactional(rollbackFor=MyServiceException.class)
@WebService(targetNamespace="http://tp.myapp.minibank/",
endpointInterface="tp.myapp.minibank.itf.domain.service.GestionComptes")
public class GestionComptesImpl implements GestionComptes {
	
	private static Logger logger = LoggerFactory.getLogger(GestionComptesImpl.class);
	
	@Inject
	private CompteDao compteDao;
	
	@Inject
	private GenericBeanConverter beanConverter;
	

	@Transactional(readOnly=true,rollbackFor=MyServiceException.class)
	public Compte getCompteByNum(long numCpt) throws MyServiceException {
		Compte cpt=null;
		try {
			cpt= beanConverter.convert(compteDao.getEntityById(numCpt),Compte.class);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("echec GestionComptesImpl.getCompteByNum",e);
			throw new MyServiceException("echec GestionComptesImpl.getCompteByNum",e);
		}
		return cpt;
	}

	@Override
	@Transactional(readOnly=true,rollbackFor=MyServiceException.class)
	public List<Compte> getComptesOfClient(long numClient)
			throws MyServiceException {		
		List<Compte> listeCpt = null;
		try {
			listeCpt = beanConverter.convertList(
					compteDao.findComptesByNumCli(numClient), Compte.class);
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
			_Compte cptDeb = compteDao.getEntityById(numCptDeb);
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			cptDeb.addOperation(new _Operation("debit virement",-montant));
			//compteDao.updateEntity(cptDeb);//inutile si cptDeb est persistant (du fait de @Transactional)
			_Compte cptCred = compteDao.getEntityById(numCptCred);
			cptCred.setSolde(cptCred.getSolde() + montant);
			cptCred.addOperation(new _Operation("credit virement",+montant));
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
			_Compte cpt= compteDao.getEntityById(numCpt);
			listeOp = beanConverter.convertList(cpt.getOperations(), Operation.class);
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
