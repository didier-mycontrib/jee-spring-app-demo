package org.mycontrib.ext.purchases.dao;

import org.mycontrib.ext.purchases.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PurchaseRepository  extends JpaRepository<Purchase,Long>{

}
