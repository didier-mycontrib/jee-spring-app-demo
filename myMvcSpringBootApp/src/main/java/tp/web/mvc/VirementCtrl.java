package tp.web.mvc;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import tp.service.ServiceCompte;
import tp.web.mvc.form.VirementForm;

@Controller //but not "@Component" for spring web controller
//@Scope(value="singleton")//by default
@RequestMapping("/mvc/banque")
@SessionAttributes( value={"numClient","listeCpt"} ) //noms des "modelAttributes" qui sont EN PLUS récupérés/stockés en SESSION HTTP
                                       //au niveau de la page de rendu --> visibles en requestScope ET en sessionScope
public class VirementCtrl {
	
	
	@Autowired //ou @Inject
	private ServiceCompte serviceComptes;
	
	 //pour  form:form , form:select , ...
    @ModelAttribute("virementForm")
	public VirementForm addConvAttributeInModel() {
	        return new VirementForm();
	}
		
	 
	 @RequestMapping("/nouveau_virement")
	    public String initNouveauVirment(){
		 return "virement"; //WEB-INF/view/virement.jsp selon viewResolver de WEB-INF/mvc-config
	 }
	 
	 @RequestMapping("/doVirement")
	    public String doVirement(Model model,
	    		                 @ModelAttribute("virementForm") @Valid VirementForm virementForm , 
	    		                 BindingResult bindingResult,
	    		                 HttpSession session) {
		if (bindingResult.hasErrors()) {
			    // form validation error
				System.out.println("form validation error: " + bindingResult.toString());
				return "virement";
			} else {
			    // form input is ok*/
			Long numClientIdentifie = (Long) session.getAttribute("numClient");
		    System.out.println("virement montant="+virementForm.getMontant() 
		                           + " numCptDeb="+virementForm.getNumCptDeb() 
		                           +" numCptCred="+virementForm.getNumCptCred());
	       
		    this.serviceComptes.virement(virementForm.getMontant(), virementForm.getNumCptDeb(), virementForm.getNumCptCred());
		    //reactualiser les valeurs des comptes en session http:
		    
		    model.addAttribute("listeCpt", this.serviceComptes.rechercherComptesDuClient(numClientIdentifie));
		    model.addAttribute("message", "virement bien effectue");
	        return "listeComptes"; //WEB-INF/view/conversionV2.jsp selon viewResolver de WEB-INF/mvc-config
			}
	    }
	
}
