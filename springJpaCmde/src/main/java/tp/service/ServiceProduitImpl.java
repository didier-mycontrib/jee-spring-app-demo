package tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.dao.DaoCategorie;
import tp.dao.DaoProduit;
import tp.entity.Categorie;
import tp.entity.Produit;

@Service
@Transactional
public class ServiceProduitImpl implements ServiceProduit {
	
	@Autowired
	private DaoProduit daoProduit;
	
	@Autowired
	private DaoCategorie daoCategorie;

	@Override
	public Produit sauvegarderProduit(Produit p) {
		return daoProduit.save(p);
	}

	@Override
	public void supprimerProduit(Long numero) {
		daoProduit.delete(numero);
	}

	@Override
	public Produit rechercherProduit(Long numero) {
		return daoProduit.findOne(numero);
	}

	@Override
	public List<Categorie> rechercherCategories() {
		return (List<Categorie>)daoCategorie.findAll();
	}

	@Override
	public List<Produit> rechercherProduitsSelonCategorie(long numCategorie) {
		return daoCategorie.findProduitsByCategorie(numCategorie);
	}

	@Override
	public Categorie associerCategorieProduit(Categorie c, Produit p) {
		c=daoCategorie.findOne(c.getNumero());//pour bien recharger en mode persistant
		                                      //avec lazy collection "produits"
		c.getProduits().add(p);
		Categorie sc = daoCategorie.save(c);
		sc.getProduits().size();//loading lazy collection
		return sc;
	}
	
	@Override
	public Categorie dissocierCategorieProduit(Categorie c, Produit p) {
		Categorie pc=daoCategorie.findOne(c.getNumero());//pour bien recharger en mode persistant
		                                      //avec lazy collection "produits"
		Produit pp=daoProduit.findOne(p.getNumero());//recharge , persistant
		pc.getProduits().remove(pp);
		Categorie sc = daoCategorie.save(pc);
		sc.getProduits().size();//loading lazy collection
		return sc;
	}

	@Override
	public Categorie rechercherCategorieSelonNumero(long numCategorie) {
		return daoCategorie.findOne(numCategorie);
	}

}
