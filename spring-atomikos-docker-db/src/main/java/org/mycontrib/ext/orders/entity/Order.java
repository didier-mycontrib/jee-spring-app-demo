package org.mycontrib.ext.orders.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//------ lombok generation code annotations ------
@Getter @Setter
@ToString(exclude={"orderLines"})
//@EqualsAndHashCode
@NoArgsConstructor
//------------------------------------------------

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long orderId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="order_date")
    private Date orderDate;
	
	@Column(name="customer_id")
    private Long customerId;
    
	@Column(name="total_price")
    private double totalPrice;
    
    @OneToMany(cascade=CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    @MapKeyColumn (name="line_number")
    private Map<Integer,OrderLine> orderLines;
    
    private int maxNumLine() {
    	int maxNumLine=0;
    	for(Integer oln : orderLines.keySet()) {
    		if(oln > maxNumLine)
    			maxNumLine=oln;
    	}
    	return maxNumLine;
    }
    
    public void addOrderLine(ProductRef productRef , int quantity) {
    	//new OrderLinePk(Long orderId, Integer lineNumber);
    	OrderLinePk orderLinePk = new OrderLinePk(this.orderId,maxNumLine()+1);
    	//new OrderLine(OrderLinePk pk, ProductRef productRef, Integer quantity)
    	OrderLine orderLine = new OrderLine(orderLinePk,productRef,quantity);
    	this.orderLines.put(orderLine.getLineNumber(),orderLine);
    	this.totalPrice += (productRef.getPrice() * quantity);
    }

	public Order(Long orderId, Date orderDate, Long cutomerId) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.customerId = cutomerId;
		this.totalPrice=0;
		this.orderLines = new HashMap<>();
	}
    
    

}
