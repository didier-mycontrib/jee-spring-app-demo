package tp.appliSpring6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppliSpring6Application {

	public static void main(String[] args) {
		//SpringApplication.run(AppliSpringApplication6.class, args);
		SpringApplication app = new SpringApplication(AppliSpring6Application.class);
		//EN PHASE DE DEV SEULEMENT et .bat en prod
		app.setAdditionalProfiles("embeddedDB","reInit","dev");
		ConfigurableApplicationContext context = app.run(args);
				
		System.out.println("http://localhost:8080/appliSpring6");
		//http://localhost:8080/appliSpring6/index.html
		// et index.html sera trouvé dans src/main/resources/static
	}

}
