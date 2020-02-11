package org.mycontrib.ext.global.rest;

import java.util.ArrayList;
import java.util.List;

import org.mycontrib.ext.customers.dao.CustomerRepository;
import org.mycontrib.ext.customers.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController 
@RequestMapping(value="/rest/shopping-api/public/customer" , headers="Accept=application/json")
public class CustomerJsonRestCtrl {
	
	
	@Autowired //ou @Inject
	private CustomerRepository customerDao;
	
	
	 @RequestMapping(value="" , method=RequestMethod.GET) 
	 @ResponseBody  
	public List<Customer> getCustomersByCriteria(@RequestParam(value="email",required=false)String email) {
		 if(email==null)
			 return customerDao.findAll();
		 else{
			 List<Customer> listeC= new ArrayList<Customer>();
			 Customer c = customerDao.findByEmail(email);
			 if(c!=null)
				 listeC.add(c);
			 return listeC;
		     }
		 } 
	 
	
	 @RequestMapping(value="" , method=RequestMethod.PUT )   
	 public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c) {
		 try {
			System.out.println("customer to update:" + c);			
			customerDao.save(c);
			return new ResponseEntity<Customer>(c, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			 return new ResponseEntity<Customer>(HttpStatus.NOT_MODIFIED);
		}
	}
	 
	 
	
	 
	 @RequestMapping(value="/{id}" , method=RequestMethod.GET) 
	 public Customer getCustomerById(@PathVariable("id") Long id) { 
		 //System.out.println("id of customer (to search):"+id);
		 Customer c = customerDao.findById(id).get();
		 return c;
		 }
	 
	
	 
}

