package tp.web.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //@ControllerAdvice plutot que @Controller pour application globale (sur tous les contrôleurs)
public class ExceptionHandlingController {
	
	@ExceptionHandler(Exception.class)
	  public String handleError(HttpServletRequest req, Exception ex , Model model) {
	   // logger.error("Request: " + req.getRequestURL() + " raised " + ex);

	    model.addAttribute("exception", ex);
	    model.addAttribute("url", req.getRequestURL());

	    return "genericError";
	  }

}
