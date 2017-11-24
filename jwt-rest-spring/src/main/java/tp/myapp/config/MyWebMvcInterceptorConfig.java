package tp.myapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import tp.myapp.web.ws.rest.interceptor.MyMvcAuthInterceptor;


@Configuration
public class MyWebMvcInterceptorConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public MyMvcAuthInterceptor myMvcAuthInterceptor(){
		return new MyMvcAuthInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//super.addInterceptors(registry);
		registry.addInterceptor(myMvcAuthInterceptor());
		         //.addPathPatterns("/private/rest/confidential-infos/");//default *
		//NB: pathPattern handler is relative to mvc (only java path)
	}
	
}
