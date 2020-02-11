package org.mycontrib.ext;

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
		//app.setAdditionalProfiles("reInit","basicSecurity");
		app.setAdditionalProfiles("reInit","embeddedDb","appDbSecurity");//ok with H2
		//app.setAdditionalProfiles("reInit","remoteDb","appDbSecurity");//ok with empty mysql or postgres database in docker
		//app.setAdditionalProfiles("noReInit","remoteDb","appDbSecurity");//ok with prepared mysql or postgres database in docker
		//one of "reInit" or "noReInit" profile is required
		//one of "embeddedDb" or "remoteDb" profile is required
		
		ConfigurableApplicationContext context = app.run(args);
		
		System.out.println("http://localhost:8181/spring-boot-backend");
		
		//securité par défaut si la classe WebSecurityConfig n'existe pas dans l'application:
		//System.out.println("default username=user et password précisé au démarrage");
	}
}
