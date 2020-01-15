package org.mycontrib.xyz;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
//import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
//import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
//import org.apache.activemq.ActiveMQConnectionFactory;
import org.mycontrib.xyz.dto.MyData;

import com.fasterxml.jackson.databind.ObjectMapper;


public class MyOtherJmsAppSendingMessage {
	public static void main(String[] args) {
		
		try {
			ActiveMQConnectionFactory amqConnectionFactory = 
					new ActiveMQConnectionFactory("tcp://localhost:61616"/*"vm://localhost"*/);
			
			//Connection : QueueConnection or TopicConnection
			Connection jmsCn = amqConnectionFactory.createConnection("admin","admin");
			
			Session jmsSession = jmsCn.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			//Destination : Queue or Topic
			/*Queue*/ Destination myDataQueue =  jmsSession.createQueue("MyDataQueue"); //open existing queue or create new one
			
			TextMessage msg = jmsSession.createTextMessage();
			ObjectMapper jacksonObjectMapper = new ObjectMapper();
			MyData data = new MyData("ref1",123.456);
			msg.setText(jacksonObjectMapper.writeValueAsString(data));
			msg.setStringProperty("_type", data.getClass().getName());
			
			//queueSender = queueSession.createSender(queue); queueSender.send(msg);
			//topicPublisher topicSession.createPublisher(topic); ....
			//MessageProducer msgProducer = jmsSession.createProducer() for queue or topic
			MessageProducer msgProducer = jmsSession.createProducer(myDataQueue);
			msgProducer.send(msg);
			System.out.println("Message sent successfully to remote queue.");
			
			jmsSession.close();jmsCn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
