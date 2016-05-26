package temp.multi.secondaryCtx;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import temp.multi.mainCtx.ApplicationMainContextHolder;

@Component
public class Ca {
	public void testAccessingComponentOfMainContext(){
		ApplicationContext mainCtx = ApplicationMainContextHolder.getAppMainContext();
		System.out.println("mainCtx=" + mainCtx);
		System.out.println("components of main context:");
		Map<String,Object> mapAllComponents = mainCtx.getBeansOfType(Object.class);
		for(Entry<String,Object> entry : mapAllComponents.entrySet()){
			System.out.println("\t" + entry.getKey() + ":" + entry.getValue().getClass().getSimpleName());
		}
	}
}
