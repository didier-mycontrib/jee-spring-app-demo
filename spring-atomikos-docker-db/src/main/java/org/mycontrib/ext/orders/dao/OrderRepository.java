package org.mycontrib.ext.orders.dao;

import org.mycontrib.ext.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository  extends JpaRepository<Order,Long>{

}
