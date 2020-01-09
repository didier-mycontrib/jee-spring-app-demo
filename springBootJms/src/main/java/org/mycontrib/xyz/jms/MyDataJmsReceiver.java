package org.mycontrib.xyz.jms;

import org.mycontrib.xyz.dto.MyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyDataJmsReceiver {
	
	@JmsListener(destination = "MyDataQueue", containerFactory = "myFactory")
	  public void receiveMessage(MyData data) {
	    System.out.println("Received <" + data + ">");
	    //...
	    forwardData(data);
	  }
	
	@Autowired 
	private JmsTemplate jmsTemplate; //for re-sending / forwarding message in other queue

	private void forwardData(MyData data){
		jmsTemplate.convertAndSend("MyForwardDataQueue", data);
	}
	
}
