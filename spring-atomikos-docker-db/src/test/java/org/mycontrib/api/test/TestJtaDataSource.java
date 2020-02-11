package org.mycontrib.api.test;


import java.sql.Connection;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mycontrib.ext.MySpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
//import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
@ActiveProfiles("reInit,embeddedDb,permitAllSecurity")
//@ActiveProfiles("reInit,remoteDb,permitAllSecurity")
//@ActiveProfiles("noReInit,remoteDb,permitAllSecurity")
public class TestJtaDataSource {
	
	private static Logger logger = LoggerFactory.getLogger(TestJtaDataSource.class);
	
	@Autowired
	@Qualifier("customersDataSource")
	private DataSource customersDataSource ;
	
	@Autowired
	@Qualifier("ordersDataSource")
	private DataSource ordersDataSource ;
	
	@Autowired
	@Qualifier("purchasesDataSource")
	private DataSource purchasesDataSource ;
	
	
	@Test
	public void testCustomersDs() throws Exception{
		AtomikosDataSourceBean adsb = (AtomikosDataSourceBean) this.customersDataSource;
		logger.trace("customersDataSource:"+adsb.getXaProperties().toString());
		Connection cn = customersDataSource.getConnection();
		Assertions.assertTrue(cn!=null);
		   try {
			ResultSet rs = cn.createStatement().executeQuery("select * from customer");
			   if(rs.next()){
				   logger.info("email of customer="+rs.getString("email"));
			   }
			   else  Assertions.fail("table customer is empty");
			   //should not be empty with reInit profile
			  rs.close();
		} catch (Exception e) {
			Assertions.fail("table customer doesnt exist or ...",e);
		}
		cn.close();
		
	}
	
	@Test
	public void testOrdersDS() throws Exception{
		AtomikosDataSourceBean adsb = (AtomikosDataSourceBean) this.ordersDataSource;
		logger.trace("ordersDataSource:"+adsb.getXaProperties().toString());
		Connection cn = ordersDataSource.getConnection();
		Assertions.assertTrue(cn!=null);
		   try {
			ResultSet rs = cn.createStatement().executeQuery("select * from order_line");
			   if(rs.next()){
				   logger.info("id of order_line="+rs.getString("order_id"));
			   }
			   else  Assertions.fail("table order_line is empty");
			 //should not be empty with reInit profile
			  rs.close();
		} catch (Exception e) {
			Assertions.fail("table order_line doesnt exist or ...",e);
		}
		cn.close();
		
	}
		

	
}
