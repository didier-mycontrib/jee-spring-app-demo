package org.mycontrib.xyz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

//NB: @SpringBootApplication est un équivalent
// de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package
@SpringBootApplication
public class MySpringBootApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		//SpringApplication.run(MySpringBootApplication.class, args);
		
		SpringApplication app = new SpringApplication(MySpringBootApplication.class);
		//app.setLogStartupInfo(false);
		//app.setAdditionalProfiles("p1","p2","p3");
		
		ConfigurableApplicationContext context = app.run(args);
		
		System.out.println("http://localhost:8484/springBootJms");
		
	}
}
