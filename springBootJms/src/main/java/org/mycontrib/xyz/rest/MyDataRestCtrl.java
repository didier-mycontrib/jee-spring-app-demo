package org.mycontrib.xyz.rest;

import org.mycontrib.xyz.dto.MyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="my-api/data" , headers="Accept=application/json")
public class MyDataRestCtrl {
	
	@Autowired 
	private JmsTemplate jmsTemplate;

	  //POST http://localhost:8484/springBootJms/my-api/data
	  // { "ref" : "EUR" , "value" : 0.923 }
	  @PostMapping("")
	  public MyData send(@RequestBody MyData data) {
	    System.out.println("Sending data in queue as jms message");
	    // send message to the message queue named "MyDataQueue"
	    jmsTemplate.convertAndSend("MyDataQueue", data);
	    return data;
	  }

}
