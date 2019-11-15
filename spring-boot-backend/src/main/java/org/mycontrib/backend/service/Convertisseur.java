package org.mycontrib.backend.service;


public interface Convertisseur {
	public double euroToFranc(double montant);
	public double francToEuro(double montant);
	public String getAuteur();
	public double convertir(double montant,
			                String source, 
			                String cible);
}
