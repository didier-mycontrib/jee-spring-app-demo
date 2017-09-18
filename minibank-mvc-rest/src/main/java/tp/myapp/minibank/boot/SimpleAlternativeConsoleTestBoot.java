package tp.myapp.minibank.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tp.myapp.minibank.config.DomainAndPersistenceConfig;
import tp.myapp.minibank.itf.domain.service.GestionComptes;

public class SimpleAlternativeConsoleTestBoot {
	// le boot en mode console/text .
	
	
	
	
	public static void main(String[] args) {
		// on prépare la configuration de l'application en mode spring-boot
		
	     SpringApplication app = new SpringApplication(DomainAndPersistenceConfig.class);
	     
		
		
		app.setWebEnvironment(false);
		//app.setLogStartupInfo(false);
		
		//setting profile (context.getEnvironment().setActiveProfiles("...") ) :
		//app.setAdditionalProfiles( "p1"); 
		 
		
		// on lance l'application spring
		ConfigurableApplicationContext context =  app.run(args);
		
		try {
			
		// appels specifiques:
			GestionComptes gestionComptes = context.getBean(GestionComptes.class);
			System.out.println("compte1: " + gestionComptes.getCompteByNum(1L));
			display("comptes du client 1",gestionComptes.getComptesOfClient(1L) );
			
		 
		} catch (Exception ex) {
			System.err.println("Exception : " + ex.getMessage());
		}finally{
		// fermeture du contexte Spring
		context.close();
		}
	}

	// méthode utilitaire - affiche les éléments d'une collection
	private static <T> void display(String message, Iterable<T> elements) {
		System.out.println(message);
		for (T e : elements) {
			System.out.println("\t"+e);
		}
	}

}
