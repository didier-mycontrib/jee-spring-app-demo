package tp.app.zz.test.standalone.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tp.app.zz.config.DomainAndPersistenceConfig;
import tp.app.zz.itf.domain.dto.Devise;
import tp.app.zz.itf.domain.service.GestionDevises;

public class Boot {
	// le boot en mode console/text .
	public static void main(String[] args) {
		// on prépare la configuration de l'application en mode spring-boot
		SpringApplication app = new SpringApplication(DomainAndPersistenceConfig.class);
		//SpringApplication app = new SpringApplication(DomainAndPersistenceAutoConfig.class);
		app.setWebEnvironment(false);
		//app.setLogStartupInfo(false);
		
		// on lance l'application spring
		ConfigurableApplicationContext context = app.run(args);
		
		try {
		// appels specifiques:
		   GestionDevises serviceDevises = context.getBean(GestionDevises.class);
		   /* //test temporaire:
		   DataSource ds = context.getBean(DataSource.class);
		   Connection cn = ds.getConnection();
		   cn.createStatement().executeUpdate("CREATE TABLE Client(numero integer primary key, nom varchar(64));");
		   cn.createStatement().executeUpdate("INSERT INTO Client values(1,'toto');");
		   
		   ResultSet rs = cn.createStatement().executeQuery("select * from Client");
		   if(rs.next()){
			   System.out.println("nom="+rs.getString("nom"));
		   }
		   */
		   serviceDevises.createNewDevise(new Devise("EUR","euro",0.9));
		   serviceDevises.createNewDevise(new Devise("USD","dollar",1.0));
		   serviceDevises.createNewDevise(new Devise("L","livre",0.7));
		   serviceDevises.createNewDevise(new Devise("YEN","yen",12.3));
		   
		   double res = serviceDevises.convertir(50.0, "euro", "dollar");
		   System.out.println("50 euros = " + res + " dollars");
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
