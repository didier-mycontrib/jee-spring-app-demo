
package tp.app.zz.impl.domain.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

import org.mycontrib.generic.converter.GenericBeanConverter;
import org.springframework.transaction.annotation.Transactional;

import tp.app.zz.impl.persistence.dao.DaoDevise;
import tp.app.zz.impl.persistence.entity._Devise;
import tp.app.zz.itf.domain.dto.Devise;
import tp.app.zz.itf.domain.service.GestionDevises;

 //@Service
@Named
//@WebService (endpointInterface="tp.conversion.devises.itf.domain.service.GestionDevises")
public  class GestionDevisesImpl implements GestionDevises {
	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(GestionDevisesImpl.class);

	@Inject
	private GenericBeanConverter beanConverter;
	
	@Inject
	private DaoDevise daoDevise;
	
	public GestionDevisesImpl(){
		super(); 
	}      
       
    
    //CRUD methods for entity "Devise" :
    @Transactional(readOnly=true)
	public Devise getDeviseByPk(String pk) throws RuntimeException {
		try{
			 _Devise entity =  daoDevise.getEntityById(pk);
			 Devise dto = null;
			 if(entity!=null)
			     dto=beanConverter.convert(entity, Devise.class);
			 return dto;
		 }catch(Exception ex){
		    logger.error("echec getDeviseByPk",ex);
		    throw new RuntimeException("echec getDeviseByPk",ex);
		}
	}
	@Transactional(rollbackFor=Exception.class)
	public java.io.Serializable createNewDevise(Devise dto) throws RuntimeException {
		try{
			_Devise entity = beanConverter.convert(dto, _Devise.class);
			daoDevise.persistNewEntity(entity);
			return entity.getPk();
		}catch(Exception ex){
		    logger.error("echec createNewDevise",ex);
		    throw new RuntimeException("echec createNewDevise",ex);
		}
	}
	@Transactional(rollbackFor=Exception.class)
	public void updateDevise(Devise dto) throws RuntimeException {
		try{
			_Devise entity = beanConverter.convert(dto, _Devise.class);
			daoDevise.updateEntity(entity);
		}catch(Exception ex){
		    logger.error("echec updateDevise",ex);
		    throw new RuntimeException("echec updateDevise",ex);
		}
	}
	@Transactional(rollbackFor=Exception.class)
	public void deleteDevise(String pk) throws RuntimeException {
		try{
			daoDevise.deleteEntity(pk);
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
			return beanConverter.convert(daoDevise.getDeviseByName(name),Devise.class);			
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
			_Devise devSrc = daoDevise.getDeviseByName(monnaieScr);
			_Devise devDest = daoDevise.getDeviseByName(monnaieDest);
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
			return beanConverter.convertList(daoDevise.getAllDevise(), Devise.class);			
		}catch(Exception ex){
		    logger.error("echec getListeDevises",ex);
		    throw new RuntimeException("echec getListeDevises",ex);
		}	   
		//End of user code               
	}
}
