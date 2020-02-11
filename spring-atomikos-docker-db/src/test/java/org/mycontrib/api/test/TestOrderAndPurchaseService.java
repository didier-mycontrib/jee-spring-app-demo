package org.mycontrib.api.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mycontrib.ext.MySpringBootApplication;
import org.mycontrib.ext.global.service.OrderAndPurchaseService;
import org.mycontrib.ext.orders.dao.OrderRepository;
import org.mycontrib.ext.orders.dao.ProductRefRepository;
import org.mycontrib.ext.orders.entity.Order;
import org.mycontrib.ext.orders.entity.ProductRef;
import org.mycontrib.ext.purchases.dao.PurchaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
@ActiveProfiles("reInit,embeddedDb,permitAllSecurity")
//@ActiveProfiles("reInit,remoteDb,permitAllSecurity")
//@ActiveProfiles("noReInit,remoteDb,permitAllSecurity")
public class TestOrderAndPurchaseService {
	
	private static Logger logger = LoggerFactory.getLogger(TestOrderAndPurchaseService.class);
	
	@Autowired
	private OrderAndPurchaseService orderAndPurchaseService ;

	@Autowired
	private OrderRepository orderRepository ;
	
	@Autowired
	private ProductRefRepository productRefRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Test
	public void testPurchaseOrderForExistingCustomer() throws Exception{
		List<ProductRef> listOfProductRef = new ArrayList<ProductRef>();
		ProductRef prA = productRefRepository.findById(5L)
				         .orElse(new ProductRef(5L,"stylo bille noir " , 1.5));
		listOfProductRef.add(prA);
		ProductRef prB = productRefRepository.findById(6L)
		         .orElse(new ProductRef(6L,"cahier 48 pages " , 2.5));
		listOfProductRef.add(prB);
		
		//a tester avec customerId=1L (existant) with reInit profile , avec bases h2 ou mysql & postgres
		Long newOrderId = orderAndPurchaseService.purchaseOrder(1L, listOfProductRef);
		Assertions.assertNotNull(newOrderId);
		
		Order newOrder = orderRepository.findById(newOrderId).orElse(null);
		Assertions.assertTrue(newOrder.getOrderLines().keySet().size()==2);
		logger.info("new order : " + newOrder.toString());
		for(Integer numLine : newOrder.getOrderLines().keySet() ){
			logger.info("\t" + numLine + ":" + newOrder.getOrderLines().get(numLine));
		}
		
	}
	
	@Test
	public void testPurchaseOrderForNotExistingCustomer() throws Exception{
		List<ProductRef> listOfProductRef = new ArrayList<ProductRef>();
		ProductRef prA = productRefRepository.findById(7L)
				         .orElse(new ProductRef(7L,"stylo bille rouge " , 1.6));
		listOfProductRef.add(prA);
		ProductRef prB = productRefRepository.findById(8L)
		         .orElse(new ProductRef(8L,"cahier96 pages " , 2.9));
		listOfProductRef.add(prB);
		
		long nbOrdersBeforePurchaseOrder = orderRepository.count();
		long nbPurchasesBeforePurchaseOrder = purchaseRepository.count();
		
		try {
			//a tester avec customerId=999L (non existant) with reInit profile , avec bases h2 ou mysql & postgres
			Long newOrderId = orderAndPurchaseService.purchaseOrder(999L, listOfProductRef);
			Assertions.fail("une exception aurait du remonter");
		} catch (RuntimeException e) {
			//execption attendue
			logger.info("exception attendue:" +e);
		}
		
		long nbOrdersAfterPurchaseOrder = orderRepository.count();
		Assertions.assertTrue(nbOrdersAfterPurchaseOrder==nbOrdersBeforePurchaseOrder);
		
		long nbPurchasesAfterPurchaseOrder = purchaseRepository.count();
		Assertions.assertTrue(nbPurchasesAfterPurchaseOrder==nbPurchasesBeforePurchaseOrder);
	}
	
	
	
}
