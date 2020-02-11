package org.mycontrib.ext.orders.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="product_ref")
public class ProductRef {
	
	@Id
	@Column(name="product_id")
	private Long productId;
	
	@Column(length=128)
    private String label ;
    
    private double price;
    
	public ProductRef(Long productId, String label, double price) {
		super();
		this.productId = productId;
		this.label = label;
		this.price = price;
	}
    
    

}
