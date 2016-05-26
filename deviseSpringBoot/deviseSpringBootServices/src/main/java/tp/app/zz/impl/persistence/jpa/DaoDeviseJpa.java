
package tp.app.zz.impl.persistence.jpa;

import java.util.List;

import javax.inject.Named;

import org.mycontrib.generic.persistence.GenericDaoJpaImpl;

import tp.app.zz.impl.persistence.dao.DaoDevise;
import tp.app.zz.impl.persistence.entity._Devise;

@Named
public class DaoDeviseJpa extends GenericDaoJpaImpl<_Devise,String> implements DaoDevise {

	
	@Override
	public _Devise getDeviseByName(String deviseName) {
		return (_Devise) this.entityManager
				.createQuery("select d from _Devise d where d.monnaie = :deviseName")
				.setParameter("deviseName",deviseName)
				.getSingleResult();
	}
	    //public List<_Devise> findDeviseByXyz(...){ .... }

	
	@Override
	public List<_Devise> getAllDevise() {
		return ( List<_Devise>) this.entityManager
				.createQuery("select d from _Devise d").getResultList();
	}
}
