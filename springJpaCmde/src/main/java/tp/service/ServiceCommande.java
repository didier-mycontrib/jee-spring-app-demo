package tp.service;

import java.util.List;

import tp.entity.Client;
import tp.entity.Commande;
import tp.entity.LigneCommande;
import tp.entity.Produit;

public interface ServiceCommande {
	Commande sauvegarderCommande(Commande c);
	void supprimerCommande(Long numero);
	Commande rechercherCommande(Long numero);
	//..
	LigneCommande ajouterLigne(Commande cmde,Produit p, Integer quantite);
	void attacherClient(Commande cmde,Client c);
	List<LigneCommande> recupererLignesDeCommande(Long numCmde);
}
