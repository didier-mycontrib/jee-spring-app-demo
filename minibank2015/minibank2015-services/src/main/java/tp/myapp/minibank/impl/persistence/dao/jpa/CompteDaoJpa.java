package tp.myapp.minibank.impl.persistence.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import org.mycontrib.generic.persistence.GenericDaoJpaImpl;

import tp.myapp.minibank.impl.persistence.dao.CompteDao;
import tp.myapp.minibank.impl.persistence.entity._Compte;

@Named
//@Transactional inherited
public class CompteDaoJpa extends GenericDaoJpaImpl<_Compte,Long> implements CompteDao {

	
	@Override
	public List<_Compte> findComptesByNumCli(long numCli) {		
		//query for 1-n :  "select c from _Compte c where c.proprietaire.numClient= :numCli"
	    Query q= this.getEntityManager().createQuery("select c.comptes from _Client c where c.numero= :numCli");
		/*//test syntaxique temporaire (non appliquable fonctionnellement):
		Query q= this.getEntityManager().createQuery("select c.comptes from _Client c where c.numero in (:listeNumCli)");
		List<Long> listeNumCli = new ArrayList<Long>();
		listeNumCli.add(numCli);//listeNumCli.add(2L);
		q.setParameter("listeNumCli", listeNumCli);*/
		q.setParameter("numCli", numCli);
		/*
		//test temporaire:
		List l = this.getEntityManager().createNativeQuery("select numCpt,label,solde from Compte where numCpt>0").getResultList();
		System.out.println("**"+l);
		for(Object obj :l){
		   //	System.out.println("\t***" + o.getClass().getName());
			Object[] objArray = (Object[]) obj;
			for(Object valObj : objArray){
				System.out.println("\t***" + valObj.toString());
			}
		}*/
			
		/*
		//test temporaire2:
		List l = this.getEntityManager().createNativeQuery("select * from Compte where numCpt>0",_Compte.class).getResultList();
		System.out.println("**"+l);
		*/
		
		return q.getResultList();
	}

}
