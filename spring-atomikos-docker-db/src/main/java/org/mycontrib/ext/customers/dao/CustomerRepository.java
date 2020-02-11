package org.mycontrib.ext.customers.dao;

import org.mycontrib.ext.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository  extends JpaRepository<Customer,Long>{
	
	Customer findByEmail(String email);

}
