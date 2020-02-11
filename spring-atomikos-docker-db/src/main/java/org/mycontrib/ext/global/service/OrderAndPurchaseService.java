package org.mycontrib.ext.global.service;

import java.util.List;

import org.mycontrib.ext.orders.entity.ProductRef;
	


public interface OrderAndPurchaseService {
	
	public Long purchaseOrder(Long customerId , List<ProductRef> listOfProducts);//return new OrderId

}
