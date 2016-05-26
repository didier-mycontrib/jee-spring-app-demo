package org.mycontrib.generic.test.dbunit.util;

import java.io.File;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class BasicDBUnitHelper {
	
	protected String initialDataSetFileName="initialDataSet.xml";//by default
	protected String dataSetDirectory="src/test/resources/dataset";//by default for maven
	
	protected IDatabaseTester databaseTester;
	
	protected IDataSet initialDataSet=null;
	
	protected DataSource dataSource;
	

	public DataSource getDataSource() {
		return dataSource;
	}
	
	//@Autowired à placer sur le setDataSource à redéfinir
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	
	public void reInitDataBaseFromInitialDataSet() throws Exception{
			//databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver","jdbc:hsqldb:sample", "sa", "");
		   	this.databaseTester = new DataSourceDatabaseTester(this.dataSource);
        	
			// initialize your dataset here
		   	String initialDataSetPathName = this.dataSetDirectory+"/"+this.initialDataSetFileName;
			this.initialDataSet = new FlatXmlDataSet(new File(initialDataSetPathName));
			databaseTester.setDataSet( this.initialDataSet );
			databaseTester.onSetup();// will call default setUpOperation	(clean_insert by default)	
	}
	/*
	public void end(){
		try {
			// will call default tearDownOperation
			databaseTester.onTearDown(); //nothing by default
		} catch (Exception e) {
			e.printStackTrace();
			TestCase.fail(e.getMessage());			
		}
	}
	*/
	
	
	public String getInitialDataSetFileName() {
		return initialDataSetFileName;
	}

	public void setInitialDataSetFileName(String initialDataSetFileName) {
		this.initialDataSetFileName = initialDataSetFileName;
	}

	public String getDataSetDirectory() {
		return dataSetDirectory;
	}

	public void setDataSetDirectory(String dataSetDirectory) {
		this.dataSetDirectory = dataSetDirectory;
	}

	

}
