package tp.appliSpring6.core.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) //or GenerationType.UUID , Note that if a field or property has the java.util.UUID type, the AUTO strategy is equivalent to UUID.
    private java.util.UUID id;
    
    private String description;

	public Person() {
		super();
	}

	public Person(UUID id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public java.util.UUID getId() {
		return id;
	}

	public void setId(java.util.UUID id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", description=" + description + "]";
	}
    
    

}
