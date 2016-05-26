package tp.app.zz.web.mvc.simple.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import tp.app.zz.itf.domain.dto.Devise;
import tp.app.zz.itf.domain.service.GestionDevises;



public class CtrlSimpleBoot {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CtrlSimpleConfig.class, args);
		GestionDevises serviceDevises = context.getBean(GestionDevises.class);
		serviceDevises.createNewDevise(new Devise("EUR","euro",0.9));
		serviceDevises.createNewDevise(new Devise("USD","dollar",1.0));
		serviceDevises.createNewDevise(new Devise("L","livre",0.7));
		serviceDevises.createNewDevise(new Devise("YEN","yen",12.3));
	}

}
