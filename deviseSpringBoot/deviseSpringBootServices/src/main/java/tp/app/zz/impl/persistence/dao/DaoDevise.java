
package tp.app.zz.impl.persistence.dao;

import java.util.List;

import org.mycontrib.generic.persistence.GenericDao;

import tp.app.zz.impl.persistence.entity._Devise;

public interface DaoDevise extends GenericDao<_Devise,String> {	
	  public _Devise getDeviseByName(String deviseName);
	  public List<_Devise> getAllDevise(); 
}
