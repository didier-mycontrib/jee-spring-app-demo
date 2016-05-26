package tp.app.zz.app.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tp.app.zz.app.config.JsfConfig;

public class DeviseAppSpringBoot {

	public static void main(String[] args) {
		// on prépare la configuration de l'application en mode spring-boot
				//SpringApplication app = new SpringApplication(DomainAndPersistenceConfig.class);
				SpringApplication app = new SpringApplication(JsfConfig.class);//JsfConfig ou WebAppConfig
				//app.setWebEnvironment(true); //true by default (with EmbeddedTomcat)
				//app.setLogStartupInfo(false);
				
				// on lance l'application spring
				ConfigurableApplicationContext context = app.run(args);
				
				//context.close();
				
			}

}
