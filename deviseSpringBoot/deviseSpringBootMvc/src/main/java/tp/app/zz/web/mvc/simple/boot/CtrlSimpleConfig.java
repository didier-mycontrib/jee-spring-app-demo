package tp.app.zz.web.mvc.simple.boot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*
 * avec @EnableAutoConfiguration , si spring-security ou spring-boot-security-starter est présent dans le classPath , alors
 * tous les "endpoints/Urls" seront automatiquement sécurisés (avec basic Http Authentication)
 * default userName = "user" , defaultPassword : (displayed in console when start of spring boot app)
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"tp.app.zz.web.mvc"}) //to find and interpret @Controller , ...
public  class CtrlSimpleConfig{
	
}