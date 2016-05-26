package tp.app.zz.app.config;

import java.util.concurrent.TimeUnit;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import tp.app.zz.config.DomainAndPersistenceConfig;

/*
 NB1: la partie 
 
    ..  extends SpringBootServletInitializer {
	
	@Override //de SpringBootServletInitializer
    protected SpringApplicationBuilder configure(
                 SpringApplicationBuilder application) {
        return application.sources(JsfConfig.class);
    }
    
    n'est uniquement nécessaire qu'en mode ".war" fabriqué et déployé dans un serveur d'application JEE
    et n'est pas utile lors d'un démarrage spring-boot / main() .
    
    
  NB2: la partie 
  
  @Bean
	public EmbeddedServletContainerFactory servletContainer()
	
	est indispensable si l'annotation @EnableAutoConfiguration n'est pas présente.
	
  NB3: 
      si l'annotation @EnableAutoConfiguration est présente ,
      DispatchServlet (de Spring-mvc) est le servlet principal et "FacesServlet" le servlet secondaire
      sinon seul le servlet "FaceServlet" explicitement enregistré est pris en compte	
  
 */


@Configuration
@Import({DomainAndPersistenceConfig.class})
@ComponentScan(basePackages={"tp.app.zz.web.mbean"})
//@EnableAutoConfiguration
public class JsfConfig extends SpringBootServletInitializer {
	
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet facesServlet = new FacesServlet();
        ServletRegistrationBean facesServletRegistrationBean 
            = new ServletRegistrationBean(facesServlet, "*.jsf");
        //l'enregistrement du FacesServlet de jsf est nécessaire pour le bon fonctionnement de Spring-boot
        //bizarrement , le fichier WEB-INF/web.xml doit obligatoirement être également présent 
        //(avec la declaration du FacesServlet et son url-mapping)
    return facesServletRegistrationBean;
    }
	
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
	    factory.setPort(8080);
	    factory.setContextPath("/deviseSpringBootWeb"); //equivalent of server.context-path=/deviseSpringBootWeb in application.properties
	    factory.setSessionTimeout(5, TimeUnit.MINUTES);
	    //factory.setInitializers(initializers); //initializers = list of ServletContextInitializer
	    //factory.addErrorPages(new ErrorPage(HttpStatus.404, "/notfound.html");
	    
	    TomcatContextCustomizer contextCustomizer = new TomcatContextCustomizer() {
	        @Override
	        public void customize(org.apache.catalina.Context context) {
	            context.addWelcomeFile("/index.html");
	        }
	    };
	    factory.addContextCustomizers(contextCustomizer);

	    return factory;
	}
	

	@Override //de SpringBootServletInitializer
	//utile que si fonctionnement dans .war deploye dans servApp
    protected SpringApplicationBuilder configure(
                 SpringApplicationBuilder application) {
        return application.sources(JsfConfig.class);
    }
	


	/*
	 * la dépendance suivante est nécessaire pour JSF pour éviter une erreur de type missing factory/ServletContextListener 
	 * (aussi bien avec MyFaces que com.sun.faces):
	 * <dependency>
    		<groupId>org.apache.tomcat.embed</groupId>
    		<artifactId>tomcat-embed-jasper</artifactId>
	</dependency>
	 */
	
}

/*
 * javax.servlet.ServletContainerInitializer (of Servlet3 spec)  
        with  void onStartup(java.util.Set<java.lang.Class<?>> c, ServletContext ctx) method .
   est le STANDARD pour  effectuer en java des configurations identiques à celle de web.xml.
   
   org.springframework.web.SpringServletContainerInitializer is an "spring" implementation of this interface.
   Cette implémentation prédéfinie appelle automatiquement à son tour la méthode onStartup(ServletContext container)
   d'une (ou plusieurs) classe(s) (à coder) implémentant l'interface WebApplicationInitializer de Spring  .
   L'ordre peut être contrôlé via @Order
      
   l'interface org.springframework.web.WebApplicationInitializer  semble être prise en charge
   par la classe SpringBootServletInitializer (à étendre par héritage en programant .configure() ) ....
   
   La documentation officielle indiquer que SpringBootServletInitializer n'est utile que pour 
   transformer l'application spring-boot en un ".war" à déployer dans un vrai serveur JEE.
   
   C'est à priori pour cette raison que .configure() redirige à son tour vers une ou plusieurs "classes" sources:
     @Override //de SpringBootServletInitializer
    protected SpringApplicationBuilder configure(
                 SpringApplicationBuilder application) {
        return application.sources(XyConfig.class);
    }
   
 */


/* simple exemple de syntaxe:
@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet facesServlet = new FacesServlet();
        ServletRegistrationBean facesServletRegistrationBean 
            = new ServletRegistrationBean(facesServlet, "*.jsf");
        Map<String,String> params = new HashMap<String,String>();
        params.put("key1","value1"); 
        params.put("key2","value2");
        facesServletRegistrationBean.setInitParameters(params);
        facesServletRegistrationBean.setName("FacesServlet");
    return facesServletRegistrationBean;
    }
*/

/* possible dans autre classe (mais il est plus simple de configurer web.xml):
@Configuration
	public class MyServletContextInitializer implements ServletContextInitializer {
		
	@Override
	  public void onStartup(ServletContext sc) throws ServletException {
	    sc.setInitParameter("facelets.REFRESH_PERIOD", "2");
	    sc.setInitParameter("facelets.DEVELOPMENT", "true");
	  }
	}
*/