package org.mycontrib.api.conv;

import javax.jws.WebService;

import org.mycontrib.api.dao.DeviseDao;
import org.mycontrib.api.entity.Devise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service  //de Spring
@WebService(endpointInterface="org.mycontrib.api.conv.Convertisseur")
public class ConvertisseurImpl implements Convertisseur {
	
	@Autowired //ou @Inject si inject dans le pom
	private DeviseDao deviseDao;
	
	private static final double COEFF_EURO_FRANC = 6.55957; 
	public ConvertisseurImpl() {
		super();
	}
	
	public double convertir(double montant, String source, String cible) {
		Devise deviseSource = deviseDao.findById(source).get();
		Devise deviseCible = deviseDao.findById(cible).get();
		return montant * deviseSource.getTauxChange() / deviseCible.getTauxChange();
		//ou return montant * deviseCible.getTauxChange() / deviseSource.getTauxChange();
	}

	public double euroToFranc(double montant) {
		return montant * COEFF_EURO_FRANC;
	}

	public double francToEuro(double montant) {
		return montant / COEFF_EURO_FRANC;
	}

	public String getAuteur() {
		return "didier";
	}

	

}
