package org.mycontrib.tp.serverRest.service;

import java.util.List;

import org.mycontrib.tp.serverRest.entity.Devise;
import org.mycontrib.tp.serverRest.exception.MyEntityNotFoundException;

public interface ServiceDevise {
   List<Devise> rechercherDevises();
   Devise rechercherDeviseParCode(String code) throws MyEntityNotFoundException;
   List<Devise> rechercherDevisesParChangeMini(Double changeMini);
   double convertir(double montant, 
		            String codeDeviseSource, 
		            String codeDeviseCible);
   //..
   Devise createDevise(Devise d);
   void updateDevise(Devise d);
   void deleteDevise(String code);
}
