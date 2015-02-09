package tp.myapp.minibank.itf.domain;

import java.lang.reflect.Constructor;

//import javax.servlet.ServletContext;

public class FacadeMiniBankFactory {
	
	private static FacadeMiniBank singleton=null;
	
	public static final String DEFAULT_IMPL_PACKAGE = "tp.myapp.minibank.impl.domain";
	public static final String DEFAULT_LOCALE_FACADE_CLASS_NAME="FacadeMiniBankImpl";
	public static final String DEFAULT_DELEGATE_FACADE_CLASS_NAME="FacadeMiniBankDelegate";
	
	private static String implFacadePackage = DEFAULT_IMPL_PACKAGE;
	private static String localeFacadeClassName=DEFAULT_LOCALE_FACADE_CLASS_NAME;
	private static String delegateFacadeClassName=DEFAULT_DELEGATE_FACADE_CLASS_NAME;
	
	
	

    //with default configuration (ex: default Spring config)
	public static FacadeMiniBank getInstance(){

		if(singleton==null){
			try {
				//premier essai : implementation locale
				singleton=(FacadeMiniBank) Class.forName(implFacadePackage + "." + localeFacadeClassName).newInstance();
			} catch (Exception e) {
				System.err.println(e.getMessage() + " not found or not created");
			}						
		}
		if(singleton==null){
			try {
				//deuxieme essai :  business delegate 
				singleton=(FacadeMiniBank) Class.forName(implFacadePackage + "." + delegateFacadeClassName).newInstance();
			} catch (Exception e) {
				System.err.println(e.getMessage() + " not found or not created");
			}						
		}
		return singleton;
	}
	
	
	public static FacadeMiniBank getInstanceFromContext(Object /* as ServletContext or (Spring)ApplicationContext */ ctx){

		Class contextClass = null; //ServletContext.class or ApplicationContext.class ?
		try{
			Class ctxExcactClass = ctx.getClass();
			System.out.println("ctx in FacadeMiniBankFactory.getInstanceFromContext() of exact class: "
					+ctxExcactClass.getName() );
			Class servletContextClass = Class.forName("javax.servlet.ServletContext");
			if(servletContextClass.isAssignableFrom(ctxExcactClass))
				contextClass = servletContextClass;
			else {				
				Class springApplicationContextClass = Class.forName("org.springframework.context.ApplicationContext");
				 if(springApplicationContextClass.isAssignableFrom(ctxExcactClass)) 
					 contextClass = springApplicationContextClass; 
			}
			System.out.println("FacadeMiniBankFactory - creation facade via contexte de type " + contextClass.getName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
		
		if(singleton==null){
			try {
				//premier essai : implementation locale
				Constructor constructor  = Class.forName(implFacadePackage + "." + localeFacadeClassName).getConstructor(contextClass);				
				singleton=(FacadeMiniBank) constructor.newInstance(ctx);
			} catch (Exception e) {
				System.err.println(e.getMessage() + " not found or not created");
			}						
		}
		if(singleton==null){
			try {
				//deuxieme essai :  business delegate 
				Constructor constructor  = Class.forName(implFacadePackage + "." + delegateFacadeClassName).getConstructor(contextClass);
				singleton=(FacadeMiniBank) constructor.newInstance(ctx);
			} catch (Exception e) {
				System.err.println(e.getMessage() + " not found or not created");
			}						
		}
		return singleton;
	}
	

	public static String getImplFacadePackage() {
		return implFacadePackage;
	}


	public static void setImplFacadePackage(String implFacadePackage) {
		FacadeMiniBankFactory.implFacadePackage = implFacadePackage;
	}


	public static String getLocaleFacadeClassName() {
		return localeFacadeClassName;
	}


	public static void setLocaleFacadeClassName(String localeFacadeClassName) {
		FacadeMiniBankFactory.localeFacadeClassName = localeFacadeClassName;
	}


	public static String getDelegateFacadeClassName() {
		return delegateFacadeClassName;
	}


	public static void setDelegateFacadeClassName(String delegateFacadeClassName) {
		FacadeMiniBankFactory.delegateFacadeClassName = delegateFacadeClassName;
	}

	
}
