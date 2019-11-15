package tp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.dao.CompteDao;
import tp.entity.Compte;
import tp.service.ServiceCompte;

@Service
@Transactional
public class ServiceCompteImpl implements ServiceCompte {
	
	@Autowired
	private CompteDao daoCompte;

	@Override
	public void virement(double montant, long numCptDeb, long numCptCred)
	{
		//spring ou EJB cree/initialise "entityManager" et "transaction"
		//dès le debut de l'execution d'une méthode
		Compte cptDeb= daoCompte.findById(numCptDeb).get();
		Compte cptCred= daoCompte.findById(numCptCred).get();
        cptDeb.setSolde(cptDeb.getSolde()-montant);
        cptCred.setSolde(cptCred.getSolde()+montant);
        //pas besoin d'appeler daoCompte.save(cptDeb)
        //qui declenche le merge car cptDeb est a l'etat PERSISTANT
        //dans une méthode transactionnelle spring ou EJB
        
      //spring ou EJB ferme "entityManager" et "transaction"
      //en fin de l'execution d'une méthode
      //.commit() appel .flush() qui fait l'équivalent de .merge()
	}

	@Override
	public Compte rechercherCompte(long num) {
		return daoCompte.findById(num).get();
	}

	@Override
	public Compte rechercherCompteAvecOperations(long num) {
		return daoCompte.findCompteWithOperations(num);
	}

	@Override
	public List<Compte> rechercherComptesDuClient(long numCli) {
		return daoCompte.findByClientNum(numCli);
	}

}
