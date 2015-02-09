package tp.myapp.web.mbean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import tp.myapp.minibank.itf.domain.FacadeMiniBank;
import tp.myapp.minibank.itf.domain.FacadeMiniBankFactory;

@ManagedBean
@ApplicationScoped
public class Referentiel {
	
	private FacadeMiniBank facadeMiniBank;
	
	public Referentiel(){
		
		ServletContext sc = (ServletContext) 
				FacesContext.getCurrentInstance().getExternalContext().getContext();

		//facadeMiniBank = FacadeMiniBankFactory.getInstance();
		facadeMiniBank = FacadeMiniBankFactory.getInstanceFromContext(sc);
	}

	public FacadeMiniBank getFacadeMiniBank() {
		return facadeMiniBank;
	}
	
	

}
