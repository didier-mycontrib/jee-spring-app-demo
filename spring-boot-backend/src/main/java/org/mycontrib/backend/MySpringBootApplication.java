package org.mycontrib.backend;

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
		//app.setAdditionalProfiles("embeddedDb","reInit","basicSecurity");
		app.setAdditionalProfiles("embeddedDb","reInit","appDbSecurity");
		
		ConfigurableApplicationContext context = app.run(args);
		
		System.out.println("http://localhost:8181/spring-boot-backend");
		
		//securité par défaut si la classe WebSecurityConfig n'existe pas dans l'application:
		//System.out.println("default username=user et password précisé au démarrage");
	}
}
