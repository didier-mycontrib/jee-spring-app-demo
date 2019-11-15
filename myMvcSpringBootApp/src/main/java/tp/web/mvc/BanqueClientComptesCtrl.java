package tp.web.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import tp.entity.Compte;
import tp.entity.Operation;
import tp.service.ServiceCompte;
import tp.web.mvc.form.VirementForm;

@Controller //but not "@Component" for spring web controller
//@Scope(value="singleton")//by default
@RequestMapping("/mvc/banque")
@SessionAttributes( value={"numClient","listeCpt"} ) //noms des "modelAttributes" qui sont EN PLUS récupérés/stockés en SESSION HTTP
                                       //au niveau de la page de rendu --> visibles en requestScope ET en sessionScope
public class BanqueClientComptesCtrl {
	
	
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
	    public String toDernieresOperations(Model model,@RequestParam("numCptSel")Long numCptSel){
		 List<Operation> listeOp = 
				 this.serviceComptes.rechercherCompteAvecOperations(numCptSel).getOperations();
		 model.addAttribute("listeOp", listeOp);
		 model.addAttribute("numCptSel", numCptSel);
		 return "dernieresOperations";
	 }
	
	 @RequestMapping(value="/identification" )
	    public String doIdentification(Model model,@RequestParam("numClient")Long numClient) {
		    
		    List<Compte> listeCpt = serviceComptes.rechercherComptesDuClient(numClient);
		    if(listeCpt!=null && !listeCpt.isEmpty()){
		          model.addAttribute("numClient",numClient);
	              model.addAttribute("listeCpt",listeCpt);
		    }
	        
	        return "listeComptes"; //WEB-INF/view/listeComptes.jsp selon viewResolver de WEB-INF/mvc-config
	    }
	 
	 
	
}
