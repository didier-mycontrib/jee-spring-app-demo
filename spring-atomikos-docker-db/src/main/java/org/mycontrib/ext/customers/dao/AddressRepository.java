package org.mycontrib.ext.customers.dao;

import org.mycontrib.ext.customers.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long>{

}
