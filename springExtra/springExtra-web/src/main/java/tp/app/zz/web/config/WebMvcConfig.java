package tp.app.zz.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import tp.app.zz.config.DomainAndPersistenceConfig;

/*  different uses:
 
   in main() :
   JavaConfigApplicationContext context =
    new JavaConfigApplicationContext(AppConfig.class, DataConfig.class);
    Service service = context.getBean(Service.class);
    
   in springContext.xml :
   <bean class="tp.app.zz.web.config.WebMvcConfig"/>
  
   ...
 */



@Configuration
@Import(DomainAndPersistenceConfig.class)
//@ImportResource("classpath:/xy.xml")
@ComponentScan(basePackages={"tp.app.zz.web"}) //to find and interpret @Autowired, @Inject , @Component , ...
@EnableWebMvc //un peu comme <mvc:annotation-driven  />
public class WebMvcConfig {
	
	/*
	 <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	        <!-- Example: a logical view name of 'showMessage' is mapped to
                                          '/WEB-INF/view/showMessage.jsp' -->
	        <property name="prefix" value="/WEB-INF/view/"/>
	        <property name="suffix" value=".jsp"/>
	</bean>
	 */

	@Bean
	public /*ViewResolver*/InternalResourceViewResolver mcvViewResolver(){
		InternalResourceViewResolver viewResolver =new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
}
