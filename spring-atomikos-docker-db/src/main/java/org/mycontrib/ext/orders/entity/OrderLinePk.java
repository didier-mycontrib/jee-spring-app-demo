package org.mycontrib.ext.orders.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//------ lombok generation code annotations ------
@Getter @Setter
@ToString()
@EqualsAndHashCode
@NoArgsConstructor
//------------------------------------------------

@Embeddable
public class OrderLinePk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name="order_id")
	private Long orderId;

	@Column(name="line_number")
	private Integer lineNumber ;

	public OrderLinePk(Long orderId, Integer lineNumber) {
		super();
		this.orderId = orderId;
		this.lineNumber = lineNumber;
	}

	
}
