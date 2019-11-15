package tp.main;

import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import tp.core.MySpringRootContext;
import tp.web.api.MyRestSpringChildContext;
import tp.web.mvc.MyMvcJavaWebSpringChildContext;

//NB: @SpringBootApplication est un equivalent 
//    de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package

//@SpringBootApplication
public class MyMultiContextSpringBootApplication /* extends SpringBootServletInitializer */{

    public static void main(String[] args) {
    	SpringApplicationBuilder appBuilder = new
    		SpringApplicationBuilder()
         		//.parent() or .sources() ?
    		    .sources(MySpringRootContext.class) //RootContext (core / back-end , domain & persistence )
    		    .child(MyRestSpringChildContext.class) //ChildContext for rest api
    		    .sibling(MyMvcJavaWebSpringChildContext.class)// other ChildContext (sibling) for java web-mvc part.
    		    .bannerMode(Banner.Mode.OFF);
    		ConfigurableApplicationContext applicationContext  = appBuilder.run();
    		System.out.println("base url:\n http://localhost:8081/myRestSpringBootApp/index-rest.html");
    		System.out.println("http://localhost:8082/myJavaWebMvcSpringBootApp/index.html");
    }

}