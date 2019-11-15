package tp.service;

import java.util.List;

import tp.entity.Compte;

public interface ServiceCompte {
	
	void virement(double montant,long numCptDeb,long numCptCred);
	Compte rechercherCompte(long num);
	Compte rechercherCompteAvecOperations(long num);
	List<Compte> rechercherComptesDuClient(long numCli);
	

}
