package tp.app.zz.web.mvc;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tp.app.zz.data.entity.Compte;
import tp.app.zz.data.entity.Operation;
import tp.app.zz.itf.domain.service.ServiceCompte;
import tp.app.zz.web.mvc.form.VirementForm;

@Controller //but not "@Component" for spring web controller
//@Scope(value="singleton")//by default
@RequestMapping("/banque")
@SessionAttributes( value={"numClient","listeCpt"} ) //noms des "modelAttributes" qui sont EN PLUS récupérés/stockés en SESSION HTTP
                                       //au niveau de la page de rendu --> visibles en requestScope ET en sessionScope
public class BanqueCtrl {
	
	private Long numClientIdentifie = null;
	
	@Autowired //ou @Inject
	private ServiceCompte serviceComptes;
	
	 //pour  form:form , form:select , ...
    @ModelAttribute("virementForm")
	public VirementForm addConvAttributeInModel() {
	        return new VirementForm();
	}
	
	 @RequestMapping("/form_identification")
	    public String toFormIdentification(){
		 return "identificationClientBanque"; //WEB-INF/view/identificationClientBanque.jsp selon viewResolver de WEB-INF/mvc-config
	 }
	 
	 @RequestMapping("/listeComptes") //retour vers ...
	    public String toListeComptes(){
		 //if ... else ...
		 return "listeComptes";
	 }
	 
	 @RequestMapping("/dernieresOperations") //init selon numCptSel avant affichage
	    public String toDernieresOperations(Model model,@RequestParam(name="numCptSel")Long numCptSel){
		 List<Operation> listeOp = 
				 this.serviceComptes.rechercherCompteAvecOperations(numCptSel).getOperations();
		 model.addAttribute("listeOp", listeOp);
		 model.addAttribute("numCptSel", numCptSel);
		 return "dernieresOperations";
	 }
	
	 @RequestMapping(value="/identification" )
	    public String doIdentification(Model model,@RequestParam(name="numClient")Long numClient) {
		    
		    List<Compte> listeCpt = serviceComptes.rechercherComptesDuClient(numClient);
		    if(listeCpt!=null && !listeCpt.isEmpty()){
		    	  this.numClientIdentifie = numClient;
		          model.addAttribute("numClient",numClient);
	              model.addAttribute("listeCpt",listeCpt);
		    }
	        
	        return "listeComptes"; //WEB-INF/view/listeComptes.jsp selon viewResolver de WEB-INF/mvc-config
	    }
	 
	 @RequestMapping("/nouveau_virement")
	    public String initNouveauVirment(){
		 return "virement"; //WEB-INF/view/virement.jsp selon viewResolver de WEB-INF/mvc-config
	 }
	 
	 @RequestMapping("/doVirement")
	    public String doVirement(Model model,@ModelAttribute("virementForm") @Valid VirementForm virementForm , BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			    // form validation error
				System.out.println("form validation error: " + bindingResult.toString());
				return "virement";
			} else {
			    // form input is ok*/
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
