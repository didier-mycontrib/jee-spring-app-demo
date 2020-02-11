package org.mycontrib.ext.global.reinit;

import javax.annotation.PostConstruct;

import org.mycontrib.ext.devises.entity.Devise;
import org.mycontrib.ext.devises.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("reInit")
public class ReInitDevisesDefaultDataSet {
	
	@Autowired
	private DeviseService deviseService;
	
	@PostConstruct
	public void initDataSet() {
		 deviseService.saveOrUpdate(new Devise("EUR","Euro",1.0));
		 deviseService.saveOrUpdate(new Devise("USD","Dollar",1.1243));
		 deviseService.saveOrUpdate(new Devise("GBP","Livre",0.884848));
		 deviseService.saveOrUpdate(new Devise("JPY","Yen",121.6477)); 
	}

}
