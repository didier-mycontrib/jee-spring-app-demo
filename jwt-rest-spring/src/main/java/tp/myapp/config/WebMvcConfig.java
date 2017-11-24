package tp.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
//@Import(DomainAndPersistenceConfig.class)
@ComponentScan(basePackages={"tp.myapp.web.ws.rest"}) //to find and interpret @Autowired, @Inject , @Component , ...
@EnableWebMvc
@Import({MyWebMvcInterceptorConfig.class, MySwaggerConfig.class })
public class WebMvcConfig {
	
	@Bean
	public ViewResolver mcvViewResolver(){
		InternalResourceViewResolver viewResolver =new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
}
