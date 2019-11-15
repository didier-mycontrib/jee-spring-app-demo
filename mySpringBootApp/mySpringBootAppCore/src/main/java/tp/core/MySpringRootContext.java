package tp.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//NB: @SpringBootApplication est un equivalent 
//    de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package

@SpringBootApplication
@PropertySource("classpath:/app-core.properties") 
public class MySpringRootContext  {

    

}