package org.mycontrib.ext.global.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mycontrib.ext.customers.dao.CustomerRepository;
import org.mycontrib.ext.customers.entity.Customer;
import org.mycontrib.ext.orders.dao.OrderRepository;
import org.mycontrib.ext.orders.dao.ProductRefRepository;
import org.mycontrib.ext.orders.entity.Order;
import org.mycontrib.ext.orders.entity.OrderLine;
import org.mycontrib.ext.orders.entity.ProductRef;
import org.mycontrib.ext.purchases.dao.PurchaseRepository;
import org.mycontrib.ext.purchases.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional //@Transactional("transactionManager") by default ("transactionManager" = JTA in this app )
@Service
public class OrderAndPurchaseServiceImpl implements OrderAndPurchaseService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRefRepository productRefRepository;
	

	@Override
	public Long purchaseOrder(Long customerId, List<ProductRef> listOfProducts) {
		Long orderId=null;
		Order newOrder = orderRepository.save(new Order(null,new Date(), customerId ));
		orderId= newOrder.getOrderId();
		
		Map<Integer,OrderLine> mapOrderLines = new HashMap<Integer,OrderLine>();
		int i=0;
		double prixTotal = 0;
		for(ProductRef prod : listOfProducts){
			i++;
			productRefRepository.save(prod);
			OrderLine orderLine =new OrderLine(null,prod,1 /*quantity*/);
			orderLine.setOrderId(orderId);
			orderLine.setLineNumber(i);
			mapOrderLines.put(i, orderLine);
			prixTotal+=prod.getPrice();
		}
		
		newOrder.setOrderLines(mapOrderLines);
		newOrder.setTotalPrice(prixTotal);
		
		purchaseRepository.save(new Purchase(null,new Date(),customerId , prixTotal));
		
		//verification de l'existence du client:
		Customer customer = customerRepository.findById(customerId).orElse(null);
		if(customer==null){
			//System.out.println("customer not exists with id="+customerId); 
			throw new RuntimeException("customer not exists with id="+customerId); //transaction will be rollback (all in jta mode)
		}
		 
		orderRepository.save(newOrder);
		System.out.println("savedOrder: "+newOrder.toString());
		return orderId;
	}

}
