package tp.app.zz.web.mvc;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tp.app.zz.data.entity.Devise;
import tp.app.zz.itf.domain.service.GestionDevises;

@Controller //but not "@Component" for spring web controller
//@Scope(value="singleton")//by default
@RequestMapping("/devisesV1")
public class DeviseListCtrl {
	
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
    
    		 
	 @RequestMapping("/liste")
	    public String toDeviseList(Model model) {
	        //model.addAttribute("allDevises", listeDevises); //pre-load via @PostConstruct gestionDevises.getListeDevises();
	        return "deviseList"; //WEB-INF/view/deviseList.jsp selon viewResolver de WEB-INF/mvc-config
	    }
	 
	 @RequestMapping("/paramConv")
	    public String paramConv(Model model) {
	       // model.addAttribute("allDevises", listeDevises); //pre-load via @PostConstruct gestionDevises.getListeDevises();
	        return "conversion"; //WEB-INF/view/conversion.jsp selon viewResolver de WEB-INF/mvc-config
	    }
	 
	 @RequestMapping("/doConversion")
	    public String doConversion(Model model,@RequestParam(name="montant")double montant,
	    									   @RequestParam(name="source")String monnaieSrc,
	    									   @RequestParam(name="cible")String monnaieDest) {
		    //model est de scope=request
		   // model.addAttribute("allDevises", listeDevises); //pre-load via @PostConstruct gestionDevises.getListeDevises();
		    model.addAttribute("montant", montant);
		    model.addAttribute("monnaieSrc", monnaieSrc);
		    model.addAttribute("monnaieDest", monnaieDest);
	        model.addAttribute("sommeConvertie", gestionDevises.convertir(montant, monnaieSrc, monnaieDest));
	        return "conversion"; //WEB-INF/view/conversion.jsp selon viewResolver de WEB-INF/mvc-config
	    }

}

