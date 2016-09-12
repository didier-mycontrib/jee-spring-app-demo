package tp.app.zz.itf.domain.service;

import java.util.List;

import tp.app.zz.data.entity.Compte;

public interface ServiceCompte {
	
	void virement(double montant,long numCptDeb,long numCptCred);
	Compte rechercherCompte(long num);
	Compte rechercherCompteAvecOperations(long num);
	List<Compte> rechercherComptesDuClient(long numCli);
	

}
