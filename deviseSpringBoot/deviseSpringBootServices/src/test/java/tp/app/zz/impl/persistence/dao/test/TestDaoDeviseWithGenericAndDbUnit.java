
package tp.app.zz.impl.persistence.dao.test;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mycontrib.generic.test.GenericDaoTestWithDbUnit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import tp.app.zz.impl.persistence.dao.DaoDevise;
import tp.app.zz.impl.persistence.entity._Devise;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={tp.app.zz.config.DataSourceConfig.class,
        						tp.app.zz.config.DomainAndPersistenceConfig.class})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class TestDaoDeviseWithGenericAndDbUnit extends GenericDaoTestWithDbUnit<_Devise,String>{ 

	private DaoDevise dao = null; // dao a tester
	
	@Override
	public String getPkOfEntity(_Devise entity){
		return entity.getCodeDevise();
	}
	
	@Inject
	public void setDao(DaoDevise dao) {
		this.dao = dao;
		this.setGenericDao(dao);
	} 
	
	public DaoDevise getDao() {
		return dao;
	} 
	
	@Inject
	 public void setDataSource(DataSource dataSource){
		 super.setDataSource(dataSource);
	 }    
	 
	 

	//Start of user code test_DaoDevise_specific_methods
	@Test
    public void test_DaoDevise_specific_methods() {
 
     try{
        System.out.println("test_DaoDevise_specific_methods");
        //...
        }catch(RuntimeException ex){
      	    System.err.println(ex.getMessage());
      	    Assert.fail(ex.getMessage());
      	}
    }
    //End of user code
      
}
