package org.mycontrib.ext.global.reinit;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.mycontrib.ext.customers.dao.AddressRepository;
import org.mycontrib.ext.customers.dao.CustomerRepository;
import org.mycontrib.ext.customers.entity.Address;
import org.mycontrib.ext.customers.entity.Customer;
import org.mycontrib.ext.orders.dao.OrderRepository;
import org.mycontrib.ext.orders.dao.ProductRefRepository;
import org.mycontrib.ext.orders.entity.Order;
import org.mycontrib.ext.orders.entity.ProductRef;
import org.mycontrib.ext.purchases.dao.PurchaseRepository;
import org.mycontrib.ext.purchases.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("reInit")
public class ReInitCustomersOrdersPurchasesDefaultDataSet {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRefRepository productRefRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	
	@PostConstruct
	public void initDataSet() {
		//new Address(Long id, String numberAndStreet, String zip, String town, String country) 
		Address a1 = new Address(null,"8 rue elle" , "75000" , "Paris" , "France");
		addressRepository.save(a1);
		//new Customer(Long id, String firstName, String lastName, String email, String phoneNumber)
		Customer c1 = new Customer(null,"alex" , "Therieur" , "alex-therieur@iciOula.fr" , "0102030405");
		c1.setAddress(a1);
		customerRepository.save(c1);
		
		//new  ProductRef(Long productId, String label, double price)
		ProductRef pr1 = new ProductRef(1L,"smartPhone xy" , 120.5);
		productRefRepository.save(pr1);
		ProductRef pr2 = new ProductRef(2L,"micro SD memory card" , 8.0);
		productRefRepository.save(pr2);
		//new Order(Long orderId, Date orderDate, Long cutomerId)
		Order o1 = new Order(null,new Date() , c1.getId());
		orderRepository.save(o1); //first call to save() for initialize auto_incr orderId
		o1.addOrderLine(pr1,1);//(productRef,quantity)
		o1.addOrderLine(pr2,3);//(productRef,quantity)
		orderRepository.save(o1); //second call to save() for saving orderLines
		
		//new Purchase(Long purchaseId, Date purchaseDateTime, Long cutomerId, double amount)
		Purchase p1 = new Purchase(null,new Date() , c1.getId() , o1.getTotalPrice());
		purchaseRepository.save(p1);
	}

}
