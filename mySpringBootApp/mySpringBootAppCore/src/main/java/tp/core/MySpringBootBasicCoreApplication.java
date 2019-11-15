package tp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tp.core.service.GestionDevises;

//NB: @SpringBootApplication est un equivalent 
//    de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package

//@SpringBootApplication (now over MySpringRootContext)
public class MySpringBootBasicCoreApplication{

    public static void main(String[] args) {
    	
    	SpringApplication app = new SpringApplication(MySpringRootContext.class);
    	app.setWebApplicationType(WebApplicationType.NONE);
    	ConfigurableApplicationContext context = app.run(args);
    	System.out.println("mySpringBootAppCore is started / console mode / no web");
    	
    	//Simple action (test):
    	GestionDevises s  = context.getBean(GestionDevises.class);
    	System.out.println("res conversion=" + s.convertir(20, "euro", "dollar"));
    	context.close();
    	
    }

}