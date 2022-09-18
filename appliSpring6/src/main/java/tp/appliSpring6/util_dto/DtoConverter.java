package tp.appliSpring6.util_dto;

import java.util.ArrayList;
import java.util.List;

import tp.appliSpring6.core.entity.Devise;
import tp.appliSpring6.dto.Currency;

public class DtoConverter {
	
	public static List<Currency> deviseListToCurrencyList(List<Devise> listeDevise){
		List<Currency> listeCurrency = new ArrayList<>();
		for(Devise d : listeDevise) {
			listeCurrency.add(deviseToCurrency(d));
		}
		return listeCurrency; 
	}
	
	
	public static Currency deviseToCurrency(Devise d) {
		return new Currency(d.getCode(),d.getNom(),d.getChange());
	}
	

	
}
