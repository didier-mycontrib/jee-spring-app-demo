package tp.web.mvc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import tp.entity.Devise;
import tp.service.GestionDevises;
import tp.web.mvc.form.ConversionForm;

@Controller //but not "@Component" for spring web controller
//@Scope(value="singleton")//by default
@RequestMapping("/mvc/devises")
public class DeviseListCtrlV2 {
	
	@Autowired //ou @Inject
	private GestionDevises gestionDevises;
	
	private List<Devise> listeDevises = null; //cache
	
	@PostConstruct
	private void loadListeDevises(){
		if(listeDevises==null)
			listeDevises=gestionDevises.getListeDevises();
	}
	
    @ModelAttribute("allDevises")
	public List<Devise> addAllDevisesAttributeInModel() {
	        return listeDevises;//pre-load via @PostConstruct gestionDevises.getListeDevises();
	}
    
    //pour version V2 seulement (avec form:form , form:select , ...)
    @ModelAttribute("conv")
	public ConversionForm addConvAttributeInModel() {
	        return new ConversionForm();//with .monnaieSrc=.monnaieSrc='dollar' by default
	}

		 
	 @RequestMapping("/liste")
	    public String toDeviseList(Model model) {
	        //model.addAttribute("allDevises", listeDevises); //pre-load via @PostConstruct gestionDevises.getListeDevises();
	        return "deviseList"; //WEB-INF/view/deviseList.jsp selon viewResolver de WEB-INF/mvc-config
	    }
	 
	 @RequestMapping("/paramConv")
	    public String paramConv(Model model) {
	       // model.addAttribute("allDevises", listeDevises); //pre-load via @PostConstruct gestionDevises.getListeDevises();
	        return "conversionV2"; //WEB-INF/view/conversion.jsp selon viewResolver de WEB-INF/mvc-config
	    }
	 
	 @RequestMapping("/doConversion")
	    public String doConversion(Model model,@ModelAttribute("conv") @Valid ConversionForm conv , BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			    // form validation error
				System.out.println("form validation error: " + bindingResult.toString());
			} else {
			    // form input is ok*/
		    System.out.println("conversion montant="+conv.getMontant() + " monnaieSrc="+conv.getMonnaieSrc() +" monnaieDest="+conv.getMonnaieDest());
	        model.addAttribute("sommeConvertie", gestionDevises.convertir(conv.getMontant(), conv.getMonnaieSrc(), conv.getMonnaieDest()));
			}
	        return "conversionV2"; //WEB-INF/view/conversionV2.jsp selon viewResolver de WEB-INF/mvc-config
	    }

}

