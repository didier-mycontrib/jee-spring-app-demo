
package tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.dao.DeviseDao;
import tp.entity.Devise;
import tp.service.GestionDevises;

@Service
public  class GestionDevisesImpl implements GestionDevises {
	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GestionDevisesImpl.class);

	@Autowired
	private DeviseDao daoDevise;
	
	public GestionDevisesImpl(){
		super(); 
	}      
       
    
    //CRUD methods for entity "Devise" :
    @Transactional(readOnly=true)
	public Devise getDeviseByPk(String pk) throws RuntimeException {
		try{
			 Devise entity =  daoDevise.findById(pk).get();
			 return entity;
		 }catch(Exception ex){
		    logger.error("echec getDeviseByPk",ex);
		    throw new RuntimeException("echec getDeviseByPk",ex);
		}
	}
	@Transactional(rollbackFor=Exception.class)
	public java.io.Serializable createNewDevise(Devise dev) throws RuntimeException {
		try{
			daoDevise.save(dev);
			return dev.getCodeDevise();
		}catch(Exception ex){
		    logger.error("echec createNewDevise",ex);
		    throw new RuntimeException("echec createNewDevise",ex);
		}
	}
	@Transactional(rollbackFor=Exception.class)
	public void updateDevise(Devise dev) throws RuntimeException {
		try{
			daoDevise.save(dev);
		}catch(Exception ex){
		    logger.error("echec updateDevise",ex);
		    throw new RuntimeException("echec updateDevise",ex);
		}
	}
	@Transactional(rollbackFor=Exception.class)
	public void deleteDevise(String pk) throws RuntimeException {
		try{
			daoDevise.deleteById(pk);
		}catch(Exception ex){
		    logger.error("echec deleteDevise",ex);
		    throw new RuntimeException("echec deleteDevise",ex);
		}
	}

//other specific methods :         
	@Transactional(rollbackFor=Exception.class)
	public Devise getDeviseByName(String name) throws RuntimeException {
		//Start of user code getDeviseByName
		try{
			return daoDevise.findByMonnaie(name);			
		}catch(Exception ex){
		    logger.error("echec getDeviseByName",ex);
		    throw new RuntimeException("echec getDeviseByName",ex);
		}	   
		//End of user code               
	}
	@Transactional(rollbackFor=Exception.class)
	public double convertir(double montant,String monnaieScr,String monnaieDest) throws RuntimeException {
		//Start of user code convertir
		double res=0.0;
		try{
			Devise devSrc = daoDevise.findByMonnaie(monnaieScr);
			Devise devDest = daoDevise.findByMonnaie(monnaieDest);
			res=montant *  devSrc.getDChange() / devDest.getDChange()  ;
			return res;			
		}catch(Exception ex){
		    logger.error("echec convertir",ex);
		    throw new RuntimeException("echec convertir",ex);
		}	   
		//End of user code               
	}
	@Transactional(rollbackFor=Exception.class)
	public List<Devise> getListeDevises() throws RuntimeException {
		//Start of user code getListeDevises
		try{
			return (List<Devise>) daoDevise.findAll();			
		}catch(Exception ex){
		    logger.error("echec getListeDevises",ex);
		    throw new RuntimeException("echec getListeDevises",ex);
		}	   
		//End of user code               
	}
}
