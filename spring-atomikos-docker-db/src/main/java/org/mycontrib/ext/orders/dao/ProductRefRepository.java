package org.mycontrib.ext.orders.dao;

import org.mycontrib.ext.orders.entity.ProductRef;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRefRepository  extends JpaRepository<ProductRef,Long>{

}
