package temp.multi.secondaryCtx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import temp.multi.mainCtx.C1;

@Component
public class Cb {
	
	@Autowired
	private ApplicationContext context;
	
	public void testAccessingComponentOfParentContext(){
		//ok : components of parent context are visibles from child context:
		context.getBean(C1.class).sayHello();
	}
	
	public void sayHelloCb(){
    	System.out.println("hello from Cb of secondaryCtx");
    }

}
