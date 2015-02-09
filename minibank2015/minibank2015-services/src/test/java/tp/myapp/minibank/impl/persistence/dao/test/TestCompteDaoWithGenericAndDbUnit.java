package tp.myapp.minibank.impl.persistence.dao.test;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.mycontrib.generic.test.GenericDaoTestWithDbUnit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import tp.myapp.minibank.impl.persistence.dao.CompteDao;
import tp.myapp.minibank.impl.persistence.entity._Compte;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/serviceSpringConf.xml","/dataSourceForTestSpringConf.xml"})
@TransactionConfiguration(transactionManager="txManager",defaultRollback=false)
public class TestCompteDaoWithGenericAndDbUnit extends GenericDaoTestWithDbUnit<_Compte,Long>{
	
	private CompteDao dao ;

	public CompteDao getDao() {
		return dao;
	}
	
	@Inject
	 public void setDataSource(DataSource dataSource){
		 super.setDataSource(dataSource);
	 }

	@Inject
	public void setDao(CompteDao dao) {
		this.dao = dao;
		this.setGenericDao(dao);
	}
	
	@Override
	public Long getPkOfEntity(_Compte cpt){
		return cpt.getNumero();
	}
			
	
}
