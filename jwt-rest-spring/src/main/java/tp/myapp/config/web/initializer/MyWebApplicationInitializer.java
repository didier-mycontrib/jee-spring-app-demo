package tp.myapp.config.web.initializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


/*
 * VERSION pour déploiement d'un .WAR dans un réel serveur d'application JEE (ex: tomcat8)
 * 
 * La classe préfénie org.springframework.web.SpringServletContainerInitializer (de spring-web...jar)
 * sera automatiquement utilisée au démarrage d'une appli web >= 3 dans un conteneur web tel que tomcat
 * Cette classe appelera automatiquement la méthode onStartup() sur une classe implémentant l'interface WebApplicationInitializer
 * 
 * Cet automatisme permet de déplacer la configuration de "WEB-INF/web.xml + ContextLoaderListener + ... + springConf.xml"
 * vers une configuration entièrement "java-config" (sans xml).
 */

public class MyWebApplicationInitializer implements WebApplicationInitializer {
	
	

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		    WebApplicationContext context = getContext();
		    //servletContext.setInitParameter("spring.profiles.active", "jta , with-thymeleaf");
		    //servletContext.setInitParameter("spring.profiles.active", "jta");
		    //ou bien ((ConfigurableEnvironment)context.getEnvironment()).setActiveProfiles(new String[]{"jta","with-thymeleaf" } );
	        servletContext.addListener(new ContextLoaderListener(context));
	        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("mvc-dispatcher", new DispatcherServlet(context));
	        dispatcher.setLoadOnStartup(1);
	        dispatcher.setInitParameter("contextConfigLocation", "");
	        dispatcher.addMapping("/mvc/*");
	        
	        String uploadDirectoryFullPath="c:\\temp";
	        int maxFileSize = 5 * 1024 * 1024; //5Mo
	        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadDirectoryFullPath,
	        		maxFileSize,maxFileSize*2,maxFileSize/2);
	        
	        dispatcher.setMultipartConfig(multipartConfigElement);
	    }

	    private AnnotationConfigWebApplicationContext getContext() {
	        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	        //context.setConfigLocation() est l'equivalent du context-param contextConfigLocation de web.xml (classpath*:....,...)
	        context.register(tp.myapp.config.WebMvcConfig.class /*, tp.myapp.minibank.config.OtherConfig.class */);
	        return context;
	    }

}
