package tp.myapp.web.mbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIParameter;

import tp.myapp.minibank.itf.domain.dto.Operation;
import tp.myapp.minibank.itf.domain.service.GestionComptes;

@ManagedBean
@RequestScoped
public class Operations {
	
	private UIParameter paramNumCptSel;

	
	private Long numCpt;
	private List<Operation> listeOperations;
	
	private GestionComptes getServiceGestionComptes(){
		return clientComptes.getReferentiel().getFacadeMiniBank().getServiceGestionComptes();
	}
	
	public String listerDernieresOperations(){
		String suite="operations";//.jsp
		try {
			this.numCpt=(Long) paramNumCptSel.getValue();
			listeOperations=getServiceGestionComptes().getOperationsOfCompte(numCpt);
		} catch (Exception e) {
			e.printStackTrace();
			suite=null;
		}
		return suite;
	}
	
	
	@ManagedProperty(value="#{clientComptes}")
	private ClientComptes clientComptes;

	public ClientComptes getClientComptes() {
		return clientComptes;
	}

	public void setClientComptes(ClientComptes clientComptes) {
		this.clientComptes = clientComptes;
	}

	public Long getNumCpt() {
		return numCpt;
	}

	public void setNumCpt(Long numCpt) {
		this.numCpt = numCpt;
	}

	public List<Operation> getListeOperations() {
		return listeOperations;
	}

	public void setListeOperations(List<Operation> listeOperations) {
		this.listeOperations = listeOperations;
	}

	public UIParameter getParamNumCptSel() {
		return paramNumCptSel;
	}

	public void setParamNumCptSel(UIParameter paramNumCptSel) {
		this.paramNumCptSel = paramNumCptSel;
	}

	
	
	

}
