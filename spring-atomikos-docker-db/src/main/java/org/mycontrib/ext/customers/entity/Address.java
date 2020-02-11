package org.mycontrib.ext.customers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//------ lombok generation code annotations ------
@Getter @Setter
@ToString
//@EqualsAndHashCode
@NoArgsConstructor
//------------------------------------------------

@Entity
@Table(name="address")
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="number_and_street")
	private String numberAndStreet;
	
	@Column(length=32)
	private String zip;
	
	@Column(length=64)
	private String town;
	
	@Column(length=64)
	private String country;
	
	public Address(Long id, String numberAndStreet, String zip, String town, String country) {
		super();
		this.id = id;
		this.numberAndStreet = numberAndStreet;
		this.zip = zip;
		this.town = town;
		this.country = country;
	}
	
	

}
