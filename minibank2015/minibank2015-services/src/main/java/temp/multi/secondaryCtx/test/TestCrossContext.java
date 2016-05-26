package temp.multi.secondaryCtx.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import temp.multi.mainCtx.C2;
import temp.multi.secondaryCtx.Ca;
import temp.multi.secondaryCtx.Cb;

public class TestCrossContext {
	
	@Test
	public void testAccessingOtherIndependantSpringContext(){
	ApplicationContext mainCtx = new ClassPathXmlApplicationContext("mainCtx.xml"); //context indépendant (sans lien de parenté)
	
	ApplicationContext secondaryCtx = new ClassPathXmlApplicationContext("secondaryCtx.xml");
	Ca ca = secondaryCtx.getBean(Ca.class);
	ca.testAccessingComponentOfMainContext();
	}
	
	
	@Test
	public void testAccessingParentSpringContext(){
	ApplicationContext mainCtxAsParentCtx = new ClassPathXmlApplicationContext("mainCtx.xml");
	
	ApplicationContext secondaryCtx = new ClassPathXmlApplicationContext(new String[]{"secondaryCtx.xml"},mainCtxAsParentCtx);
	Cb cb = secondaryCtx.getBean(Cb.class);
	cb.testAccessingComponentOfParentContext(); //ok : components of parent context are visibles from child context:
	
	//not working : components of child context are not visible from parent context:
	//mainCtxAsParentCtx.getBean(C2.class).testAccessingComponentOfChildContext(); 
	}
}
