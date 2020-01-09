package org.mycontrib.xyz;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.mycontrib.xyz.dto.MyData;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.fasterxml.jackson.databind.ObjectMapper;


public class MyOtherJmsAppSendingMessage extends SpringBootServletInitializer {
	public static void main(String[] args) {
		
		try {
			ActiveMQConnectionFactory queueConnectionFactory = 
					new ActiveMQConnectionFactory("tcp://localhost:61616"/*"vm://localhost"*/);
			
			QueueConnection qcn = queueConnectionFactory.createQueueConnection("admin","admin");
			
			QueueSession session = qcn.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);
			
			Queue queue = (Queue) session.createQueue("MyDataQueue"); //open existing queue or create new one
			
			TextMessage msg = session.createTextMessage();
			ObjectMapper jacksonObjectMapper = new ObjectMapper();
			MyData data = new MyData("ref1",123.456);
			msg.setText(jacksonObjectMapper.writeValueAsString(data));
			msg.setStringProperty("_type", data.getClass().getName());
			
			QueueSender sender = session.createSender(queue);
			sender.send(msg);
			System.out.println("Message sent successfully to remote queue.");
			
			session.close();qcn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
