
package tp.app.zz.impl.persistence.dao;

import java.util.List;

import org.mycontrib.generic.persistence.GenericDao;

import tp.app.zz.data.entity.Devise;

public interface DaoDevise extends GenericDao<Devise,String> {	
	  public Devise getDeviseByName(String deviseName);
	  public List<Devise> getAllDevise(); 
}
