package org.mycontrib.api.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mycontrib.api.MySpringBootApplication;
import org.mycontrib.api.entity.News;
import org.mycontrib.api.service.ServiceNews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
public class TestServiceNews {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceNews.class);
	
	@Autowired
	private ServiceNews serviceNews ; //à tester
	
	
	@Test
	public void testNews() {
		News n1= new News(1L,"news 1");
		News n2= new News(2L,"news 2");
		serviceNews.addNews(n1); serviceNews.addNews(n2);
		
		News n1Relu= serviceNews.rechercherNews(1L);
		Assert.assertTrue(n1Relu.getText().equals("news 1"));
		System.out.println(n1Relu);
		
		try {
			serviceNews.toggleNewsUpperCase(2L);
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		News n2Relu= serviceNews.rechercherNews(2L);
		System.out.println(n2Relu);
		Assert.assertTrue(n2Relu.getText().equals("NEWS 2"));
		
		
	}
		

	
}
