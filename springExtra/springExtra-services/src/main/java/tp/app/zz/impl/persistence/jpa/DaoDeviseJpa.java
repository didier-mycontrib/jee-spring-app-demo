
package tp.app.zz.impl.persistence.jpa;

import java.util.List;

import javax.inject.Named;

import org.mycontrib.generic.persistence.GenericDaoJpaImpl;

import tp.app.zz.data.entity.Devise;
import tp.app.zz.impl.persistence.dao.DaoDevise;

@Named
public class DaoDeviseJpa extends GenericDaoJpaImpl<Devise,String> implements DaoDevise {

	
	@Override
	public Devise getDeviseByName(String deviseName) {
		return (Devise) this.entityManager
				.createQuery("select d from Devise d where d.monnaie = :deviseName")
				.setParameter("deviseName",deviseName)
				.getSingleResult();
	}
	    //public List<_Devise> findDeviseByXyz(...){ .... }

	
	@Override
	public List<Devise> getAllDevise() {
		return ( List<Devise>) this.entityManager
				.createQuery("select d from Devise d").getResultList();
	}
}
