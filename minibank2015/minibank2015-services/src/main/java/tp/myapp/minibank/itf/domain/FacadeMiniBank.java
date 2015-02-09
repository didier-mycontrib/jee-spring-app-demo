package tp.myapp.minibank.itf.domain;

import tp.myapp.minibank.itf.domain.service.GestionClients;
import tp.myapp.minibank.itf.domain.service.GestionComptes;

public interface FacadeMiniBank {
    public GestionComptes getServiceGestionComptes();
    public GestionClients getServiceGestionClients();
}
