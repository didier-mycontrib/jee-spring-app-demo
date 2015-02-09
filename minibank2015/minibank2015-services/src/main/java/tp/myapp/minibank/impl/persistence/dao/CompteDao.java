package tp.myapp.minibank.impl.persistence.dao;

import java.util.List;

import org.mycontrib.generic.persistence.GenericDao;

import tp.myapp.minibank.impl.persistence.entity._Compte;

public interface CompteDao extends GenericDao<_Compte,Long> {
	public List<_Compte> findComptesByNumCli(long numCli);
}
