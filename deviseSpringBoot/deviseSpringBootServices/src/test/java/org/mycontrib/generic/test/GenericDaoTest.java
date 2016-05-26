package org.mycontrib.generic.test;

import java.io.Serializable;

import junit.framework.Assert;

import org.junit.Test;
import org.mycontrib.generic.persistence.GenericDao;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Didier Defrance
 * 
 * Classe de Test générique pour tester un dao dans le cadre suivant:
 *     - via JUnit4 et SpringTest
 *     - test d'un dao dont le code est basé sur héritage de GenericDao(JpaImpl)
 *     
 * Utilisation:
 * 
 *     créer une sous classe héritant de GenericDaoTest<T> 
 *     où T est le type d'entité persistante
 */

public abstract class  GenericDaoTest<T,ID extends Serializable> {
	
	protected GenericDao<T,ID> genericDao;
	protected ID genericPk;
	
	public void setGenericDao(GenericDao<T,ID> genericDao) {
		this.genericDao = genericDao;
	}
	
	//callback à coder:
	public abstract ID getPkOfEntity(T entity);
	public abstract T newEntity();
	public abstract void assertValuesOfNewEntity(T entity);
	public abstract void changeValuesOfEntity(T entity);
	public abstract void assertChangedValuesOfNewEntity(T entity);
	
	//callback utiles si relations (1-1 , 1-n , n-n , ... ) , redéfinition non obligatoire si héritage de GenericDaoSimpleTest
	public abstract void displayAttachedOtherEntities(T mainEntity); // afficher choses en dehors de toString()
	
	public abstract void attachNewOtherEntities(T mainEntity);
	public abstract void assertNewAttachedOtherEntities(T mainEntity);
	
	public abstract void updateAttachedOtherEntities(T mainEntity);
	public abstract void assertApdatedAttachedOtherEntities(T mainEntity);
		
	//code du TestGenerique
	public GenericDao<T,ID> getGenericDao() {
		return genericDao;
	}

	
	public ID getGenericPk() {
		return genericPk;
	}

	public void setGenericPk(ID genericPk) {
		this.genericPk = genericPk;
	}

	@Test
	public void testGenericDao_CRUD() {
		//************* version sans grande transaction globale (plein de petites transactions)
    	//              persistant, détaché , persistant, détaché , ....
    	//              pas d'affichage sophistiqué (problème sur lazy=true)
    	//******************************************************************
		System.out.println("****** test CRUD sur T avec plusieurs petites transactions (via DAO GenericDao<T>) *****");
		// séquence habituelle : new & save , get(All) , maj , update , get , delete , ...
		boolean withDeep=false;
		sequence_crudT(withDeep);
		System.out.println("****** end of CRUD test avec plusieurs petites transactions *****\n");
	}
    
    @Test
	@Transactional
	public void testGenericDao_CRUD_InOneTx() {
		//************* version avec grande transaction globale (de bout en bout à l'état persistant)
    	//              affichages sophistiqués possibles (pas de problème avec lazy=true)
    	//******************************************************************
		System.out.println("****** test CRUD sur T en une seule transaction (via DAO GenericDao<T>) *****");
		// séquence habituelle : new & save , get(All) , maj , update , get , delete , ...
		boolean withDeep=true;
		sequence_crudT(withDeep);
		System.out.println("****** end of CRUD test en une seule transaction *****\n");
	}
              
    private void sequence_crudT(boolean withDeep){
		createEntity(withDeep); verifNewEntity(withDeep);
		updateEntity(withDeep); verifUpdateEntity(withDeep); 
		deleteEntity(withDeep); verifDeleteEntity(withDeep);
	}
	private void createEntity(boolean withDeep) {
		try {
		    T newEntity = this.newEntity(); //callback (in subclass)
	        if(withDeep){
	        // eventuelles liaisions avec d'autres entités
	        	this.attachNewOtherEntities(newEntity);
	        }
			genericDao.persistNewEntity(newEntity); // appel d'une méthode transactionnelle
			this.genericPk = this.getPkOfEntity(newEntity);
			if(withDeep)				
			     System.out.println("\t id(pk) du nouveau Compte cr��: " + genericPk);
			} catch(RuntimeException ex){
      	    	System.err.println(ex.getMessage());
      	    	Assert.fail(ex.getMessage());
      			}
		}
		
		private void verifNewEntity(boolean withDeep) {
		T e=null;
			try {
			e = genericDao.getEntityById(this.genericPk);
			this.assertValuesOfNewEntity(e);
			if(withDeep){
			    System.out.println("\t valeurs initiales de l'entit� (cr��e): " + e.toString());
			    // éventuel test ou affichage d'un élément (ou collection) lié(e) - lazy=true ? 
			    assertNewAttachedOtherEntities(e);
			    displayAttachedOtherEntities(e);			
			  }
			} catch(RuntimeException  ex){
      	    	System.err.println(ex.getMessage());
      	    	Assert.fail(ex.getMessage());
      		}
		}
					
		private void updateEntity(boolean withDeep) {
		T e=null;
		try {
			e = genericDao.getEntityById(this.genericPk);
			this.changeValuesOfEntity(e);
			
			if(withDeep){
			    // éventuelle modification d'une liaison avec une autre entité
				updateAttachedOtherEntities(e);
			  }
			genericDao.updateEntity(e);  // appel d'une méthode transactionnelle
		    } catch(Exception ex){
      	    	System.err.println(ex.getMessage());
      	    	Assert.fail(ex.getMessage());
      		}
		}
		
		
		private void verifUpdateEntity(boolean withDeep) {
		T e=null;
			try {
			e = genericDao.getEntityById(this.genericPk);
			this.assertChangedValuesOfNewEntity(e);
			if(withDeep){
				System.out.println("\t nouvelle valeur de l'entit� (modifi�e): " + e.toString());
			    // + éventuel test ou affichage d'un élément (ou collection) lié(e) - lazy=true ? 
				assertApdatedAttachedOtherEntities(e);
				displayAttachedOtherEntities(e);
			  }
			} catch(RuntimeException  ex){
      	    	System.err.println(ex.getMessage());
      	    	Assert.fail(ex.getMessage());
      		}
		}
		    	    
		private void deleteEntity(boolean withDeep) {
		try {
			genericDao.deleteEntity(this.genericPk);
			} catch(RuntimeException  ex){
      	    	System.err.println(ex.getMessage());
      	    	Assert.fail(ex.getMessage());
      		}	
		}   
		
	
		private void verifDeleteEntity(boolean withDeep) {
		try {
			T e = genericDao.getEntityById(this.genericPk);
			if(e==null && withDeep)
			     System.out.println("\t entit� bien supprim�e");
			Assert.assertTrue(e == null);
		} catch(RuntimeException ex){
      	    	System.err.println(ex.getMessage());
      	    	Assert.fail(ex.getMessage());
      		}
	   }

	
}
