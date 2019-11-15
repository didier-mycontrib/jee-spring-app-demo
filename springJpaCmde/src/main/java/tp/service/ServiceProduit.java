package tp.service;

import java.util.List;

import tp.entity.Categorie;
import tp.entity.Produit;

public interface ServiceProduit {
	Produit sauvegarderProduit(Produit p);
	void supprimerProduit(Long numero);
	Produit rechercherProduit(Long numero);
	
	List<Categorie> rechercherCategories();
	Categorie rechercherCategorieSelonNumero(long numCategorie);
	List<Produit> rechercherProduitsSelonCategorie(long numCategorie);
	Categorie associerCategorieProduit(Categorie c,Produit p);
	Categorie dissocierCategorieProduit(Categorie c, Produit p);
}
