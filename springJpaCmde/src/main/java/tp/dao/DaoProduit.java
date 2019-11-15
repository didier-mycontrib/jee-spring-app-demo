package tp.dao;

import org.springframework.data.repository.CrudRepository;

import tp.entity.Produit;

public interface DaoProduit extends CrudRepository<Produit,Long>{

}
