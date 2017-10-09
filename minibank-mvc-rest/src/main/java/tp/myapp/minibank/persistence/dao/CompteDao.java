package tp.myapp.minibank.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tp.myapp.minibank.persistence.entity.Compte;


/*
avec "Spring-data , l'implementation de JpaRepository<T,ID> est entierement générée 
via <jpa:repositories base-package="org.xyz.dao.spring" />
ou bien via @EnableJpaRepositories(basePackages = "org.xyz.dao.spring")
*/

/*NB : org.springframework.data.jpa.repository.JpaRepository<E,ID> hérite de 
*     org.springframework.data.repository.CrudRepository<E,ID>
* et comporte les méthodes delete() , save() , findOne(pk)  , findAll()
*/

public interface CompteDao extends JpaRepository<Compte,Long> {
	public List<Compte> findComptesByNumCli(long numCli);
	//NB: NamedQuery "Compte.findComptesByNumCli" in Compte.class
}
