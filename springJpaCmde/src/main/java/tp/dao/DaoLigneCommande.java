package tp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tp.entity.LigneCommande;

public interface DaoLigneCommande extends CrudRepository<LigneCommande,Long>{
	List<LigneCommande> findByNumCommande(Long numCmde);
}
