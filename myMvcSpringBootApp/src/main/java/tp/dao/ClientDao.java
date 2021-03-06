package tp.dao;

import org.springframework.data.repository.CrudRepository;

import tp.entity.Client;

/*
 * DAO = Data Access Object :
 * objet spécialisé dans l'acces aux données
 * (alias DataService ou Repository ou ...)
 * avec methodes "CRUD" (avec souvent du SQL)
 * 
 * avec throws RuntimeException implicites
 * 
 * JpaRepository herite de CrudRepository (encore plus abstrait)
 * 
 * NB: le code d'une méthode de recherche simple 
 * public List<E> findByXxxAndYyy(xxx,yyy) 
 * peut être généré automatiquement
 * 
 * NB: le code d'une méthode de recherche complexe peut (en JPA)
 * être codé comme un @NameQuery de nom "EntityClass.findXyz"
 */

public interface ClientDao extends CrudRepository<Client,Long>{

   /* méthodes héritées:
  	   ... findById(...)
  	   .... findAll()
  	   ...save(...)
  	   ...deleteById(...)
    */

}
