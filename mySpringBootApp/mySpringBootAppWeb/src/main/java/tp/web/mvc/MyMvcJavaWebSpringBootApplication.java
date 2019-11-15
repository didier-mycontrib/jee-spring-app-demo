package tp.web.mvc;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import tp.core.MySpringRootContext;

//NB: @SpringBootApplication est un equivalent 
//    de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package

//@SpringBootApplication //already on ...Context
public class MyMvcJavaWebSpringBootApplication {

		
	public static void main(String[] args) {
    	SpringApplicationBuilder appBuilder = new
    		SpringApplicationBuilder()
         		//.parent() or .sources() ?
    		    .sources(MySpringRootContext.class) //RootContext (core / back-end , domain & persistence )
    		    .child(MyMvcJavaWebSpringChildContext.class) //ChildContext for rest api
    	        .bannerMode(Banner.Mode.OFF);
    		    //.sibling(ChildContext2.class);
    		ConfigurableApplicationContext applicationContext  = appBuilder.run();
    		System.out.println("base url of mvc-web-app : http://localhost:8082/myJavaWebMvcSpringBootApp/index.html");
    }

}