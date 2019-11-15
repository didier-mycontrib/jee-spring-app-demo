package tp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tp.dao.DaoClient;
import tp.entity.AdresseDuClient;
import tp.entity.Client;

@Service
@Transactional
public class ServiceClientImpl implements ServiceClient {
	
	@Autowired
	private DaoClient daoClient;

	@Override
	public Client sauvegarderClient(Client c) {
		return daoClient.save(c);
	}

	@Override
	public void supprimerClient(Long numero) {
		daoClient.delete(numero);
	}

	@Override
	public Client rechercherClient(Long numero) {
		return daoClient.findOne(numero);
	}

	@Override
	public Client associerAdresseAuClient(Client c, AdresseDuClient adr) {
		c.setAdresse(adr);
		adr.setNumClient(c.getNumero());
		return daoClient.save(c);
	}

}
