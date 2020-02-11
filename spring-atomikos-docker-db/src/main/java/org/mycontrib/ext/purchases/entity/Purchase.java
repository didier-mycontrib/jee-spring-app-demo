package org.mycontrib.ext.purchases.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//------ lombok generation code annotations ------
@Getter @Setter
@ToString()
//@EqualsAndHashCode
@NoArgsConstructor
//------------------------------------------------

@Entity
@Table(name="purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="purchase_id")
	private Long purchaseId ;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="purchase_date_time")
    private Date purchaseDateTime;
    
	@Column(name="customer_id")
    private Long cutomerId;
    
    private double amount;

	public Purchase(Long purchaseId, Date purchaseDateTime, Long cutomerId, double amount) {
		super();
		this.purchaseId = purchaseId;
		this.purchaseDateTime = purchaseDateTime;
		this.cutomerId = cutomerId;
		this.amount = amount;
	}
    
    

}
