package temp.multi.mainCtx;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component //ou equivalent xml ou javaConfig
public class ApplicationMainContextHolder  implements ApplicationContextAware {

	private static ApplicationContext myAppMainContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		myAppMainContext = context;
		
	}
	
	public static ApplicationContext getAppMainContext(){
		return myAppMainContext;
	}

}
