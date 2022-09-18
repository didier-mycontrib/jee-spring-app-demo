package tp.appliSpring6.reinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import tp.appliSpring6.core.entity.Devise;
import tp.appliSpring6.core.service.DeviseService;

@Component
@Profile("reInit")
public class ReInitDefaultDataSet {
	
	
	@Autowired
	private DeviseService deviseService;

	
	@PostConstruct
	public void initDataSet() {
		
		
		Devise deviseEuro =  new Devise("EUR","euro",1.0);
		deviseService.sauvegarderDevise(deviseEuro);
		
		deviseService.sauvegarderDevise(new Devise("USD","dollar",1.1));
		deviseService.sauvegarderDevise(new Devise("GBP","livre",0.9));
		deviseService.sauvegarderDevise(new Devise("JPY","yen",120.0));
	}

}
