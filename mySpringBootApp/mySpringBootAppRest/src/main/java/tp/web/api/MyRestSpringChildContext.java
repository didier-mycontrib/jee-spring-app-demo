package tp.web.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

//NB: @SpringBootApplication est un equivalent 
//    de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package

@SpringBootApplication
@PropertySource("classpath:/rest-api.properties") 
public class MyRestSpringChildContext extends SpringBootServletInitializer {

}