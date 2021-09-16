package org.mycontrib.tp.serverRest.dao;

import org.mycontrib.tp.serverRest.entity.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoPays extends JpaRepository<Pays,String>{
  
}
