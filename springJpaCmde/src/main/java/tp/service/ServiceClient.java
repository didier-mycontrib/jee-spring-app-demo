package tp.service;

import tp.entity.AdresseDuClient;
import tp.entity.Client;

public interface ServiceClient {
	Client sauvegarderClient(Client c);
	void supprimerClient(Long numero);
	Client rechercherClient(Long numero);
	Client associerAdresseAuClient(Client c, AdresseDuClient adr);
}
