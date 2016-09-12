
package tp.app.zz.impl.persistence.jpa;

import java.util.List;

import javax.inject.Named;

import org.mycontrib.generic.persistence.GenericDaoJpaImpl;
import org.springframework.transaction.annotation.Transactional;

import tp.app.zz.data.entity.Compte;
import tp.app.zz.impl.persistence.dao.DaoCompte;

@Named
@Transactional
public class DaoCompteJpa extends GenericDaoJpaImpl<Compte,Long> implements DaoCompte {

	@Override
	public List<Compte> getComptesDuClient(long numClient){
		//solution1:
		/*
		Client cli = entityManager.find(Client.class, numClient);
		cli.getComptes().size();
		return cli.getComptes();
		*/
		//solution 2:
		String reqJpql = "SELECT cpt FROM Client cli join cli.comptes cpt "
				+ "WHERE cli.numero = :numCli";
		
		return entityManager.createQuery(reqJpql,Compte.class)
				.setParameter("numCli", numClient)
				.getResultList();
		
	}
	
	@Override
	public Compte getCompteAvecOperations(long numCpt){
		//spring ou EJB cree/initialise "entityManager" et "transaction"
		//dès le debut de l'execution d'une méthode
		/*
		//solution 1 (au lazy exception):
		Compte c = entityManager.find(Compte.class, numCpt);
		
		//Boucle for ou appel à .size() permet de REMONTER TOUT DE SUITE
		//les operations rattachées au comptes (même en mode LAZY)
		//for(Operation op : c.getOperations());
		c.getOperations().size();
		
		return c;*/
		
		//solution 2 (avec requete JPQL avec fetch):
		String reqJPQL = "SELECT c FROM Compte c JOIN fetch c.operations o "
				+ "WHERE c.numero = :numCompte";
		return entityManager.createQuery(reqJPQL, Compte.class)
				.setParameter("numCompte", numCpt)
				.getSingleResult(); 
		//spring ou EJB ferme "entityManager" et "transaction"
		//en fin de l'execution d'une méthode
	}

	@Override
	public List<Compte> getAllCompte() {
		return  this.entityManager
				.createQuery("select c from Compte c",Compte.class).getResultList();
	}
}
