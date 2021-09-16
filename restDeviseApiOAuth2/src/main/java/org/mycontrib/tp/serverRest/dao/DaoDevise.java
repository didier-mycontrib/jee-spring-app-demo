package org.mycontrib.tp.serverRest.dao;

import java.util.List;

import org.mycontrib.tp.serverRest.entity.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoDevise extends JpaRepository<Devise,String>{
  List<Devise> findByChangeGreaterThanEqual(Double changeMini);
}
