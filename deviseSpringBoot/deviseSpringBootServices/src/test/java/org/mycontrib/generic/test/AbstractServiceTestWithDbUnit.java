package org.mycontrib.generic.test;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;
import org.mycontrib.generic.test.dbunit.util.DBUnitHelper;
import org.mycontrib.generic.test.dbunit.util.EntityLoaderFromXmlDataSet;

/**
 * @author Didier Defrance
 * 
 * Classe abstraite de  Test de service dans le cadre suivant:
 *     - via JUnit4 et SpringTest
 *     - utilisation de dbUnit pour initialiser le contenu la base de données vide lors des tests
 *          
 * Utilisation:
 * 
 *     créer une sous classe héritant de AbstractServiceTestWithDbUnit
 */

public abstract class AbstractServiceTestWithDbUnit {
	
	 private DBUnitHelper dbUnitHelper = null;

	 
	 //par convention (dans src/test/resources) :
	 // dataset/initialDataSet.xml  [chargé comme état initial de la base de données]
	 
	 //@Autowired (ici et/ou dans sous classe)
	 public void setDataSource(DataSource dataSource){
		 dbUnitHelper.setDataSource(dataSource);
	 }
	 
	
		
		public AbstractServiceTestWithDbUnit() {
			super();
			dbUnitHelper = new DBUnitHelper();
			
	     }
		

	 
	 @Before
     public void setUp()
    {
        try {
			dbUnitHelper.reInitDataBaseFromInitialDataSet();
		} catch (Exception e) {
			e.printStackTrace();
			TestCase.fail(e.getMessage());
			
		}
    }

	
	 
	 

}
