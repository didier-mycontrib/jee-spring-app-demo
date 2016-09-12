
package tp.app.zz.impl.persistence.dao;

import java.util.List;

import org.mycontrib.generic.persistence.GenericDao;

import tp.app.zz.data.entity.Compte;

public interface DaoCompte extends GenericDao<Compte,Long> {
	
	public List<Compte> getAllCompte();

	Compte getCompteAvecOperations(long numCpt);

	List<Compte> getComptesDuClient(long numClient); 
}
