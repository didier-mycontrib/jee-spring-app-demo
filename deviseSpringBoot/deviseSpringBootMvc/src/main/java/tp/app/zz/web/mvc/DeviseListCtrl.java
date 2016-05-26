package tp.app.zz.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //but not "@Component" for spring web controller
@RequestMapping("/devises")
public class DeviseListCtrl {
	
	//complete path/url is "http://localhost:8080" 
	//                    + "/deviseSpringBootWeb" (value of server.context-path in application.properties)
	//                    + "/devises" + "/hello"
	 @RequestMapping("/hello")
	    @ResponseBody
	    String say_hello() {
	        return "Hello World!";
	    }

}

