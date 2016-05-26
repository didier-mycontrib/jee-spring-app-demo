
package tp.app.zz.itf.domain.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import tp.app.zz.itf.domain.dto.Devise;

public interface GestionDevises {  
	//CRUD methods for entity "Devise"
	public Devise getDeviseByPk(String pk) throws RuntimeException;
	public java.io.Serializable createNewDevise(Devise e) throws RuntimeException;
	public void updateDevise(Devise e) throws RuntimeException;
	public void deleteDevise(String pk) throws RuntimeException;
	//Other specific methods:
	public Devise getDeviseByName(String name) throws RuntimeException;
	public double convertir(double montant,String monnaieScr,String monnaieDest) throws RuntimeException;
	public List<Devise> getListeDevises() throws RuntimeException;
}

/*
 @WebService
public interface GestionDevises {  
	//CRUD methods for entity "Devise"
	public Devise getDeviseByPk(@WebParam(name="pk") String pk) throws RuntimeException;
	public java.io.Serializable createNewDevise(@WebParam(name="obj")Devise e) throws RuntimeException;
	public void updateDevise(@WebParam(name="obj")Devise e) throws RuntimeException;
	public void deleteDevise(@WebParam(name="pk") String pk) throws RuntimeException;
	//Other specific methods:
	public Devise getDeviseByName(@WebParam(name="name")String name) throws RuntimeException;
	public double convertir(@WebParam(name="montant")double montant,@WebParam(name="monnaieScr")String monnaieScr,@WebParam(name="monnaieDest")String monnaieDest) throws RuntimeException;
	public List<Devise> getListeDevises() throws RuntimeException;
}

 */
