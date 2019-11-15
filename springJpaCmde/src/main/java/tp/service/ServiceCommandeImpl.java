package tp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.dao.DaoCommande;
import tp.dao.DaoLigneCommande;
import tp.entity.Client;
import tp.entity.Commande;
import tp.entity.LigneCommande;
import tp.entity.Produit;

@Service
@Transactional
public class ServiceCommandeImpl implements ServiceCommande {

	@Autowired
	private DaoCommande daoCommande;
	/*
	@Autowired
	private DaoCommande daoClient;
	
	@Autowired
	private DaoCommande daoProduit;
	*/
	@Autowired
	private DaoLigneCommande daoLigneCommande;
	
	@Override
	public Commande sauvegarderCommande(Commande c) {
		return daoCommande.save(c);
	}

	@Override
	public void supprimerCommande(Long numero) {
		daoCommande.delete(numero);
	}

	@Override
	public Commande rechercherCommande(Long numero) {
		return daoCommande.findOne(numero);
	}

	@Override
	public LigneCommande ajouterLigne(Commande cmde,Produit p, Integer quantite) {
		LigneCommande nouvelleLigne = new LigneCommande();
		nouvelleLigne.setProduit(p);
		nouvelleLigne.setQuantite(quantite);
		nouvelleLigne.setCommande(cmde);
		return daoLigneCommande.save(nouvelleLigne);
	}

	@Override
	public void attacherClient(Commande cmde,Client cli) {
		cmde.setClient(cli);
		daoCommande.save(cmde);
	}

	@Override
	public List<LigneCommande> recupererLignesDeCommande(Long numCmde) {
		return daoLigneCommande.findByNumCommande(numCmde);
	}

}
