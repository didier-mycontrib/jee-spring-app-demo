package tp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tp.entity.Categorie;
import tp.entity.Produit;

public interface DaoCategorie extends CrudRepository<Categorie,Long>{
     public List<Produit> findProduitsByCategorie(Long numCategorie);
}
