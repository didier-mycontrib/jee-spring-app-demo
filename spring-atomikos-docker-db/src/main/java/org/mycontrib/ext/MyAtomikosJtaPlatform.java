package org.mycontrib.ext;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;


/**
 pour 
 properties.put("hibernate.transaction.jta.platform", 
                MyAtomikosJtaPlatform.class.getName());
 dans paramétrage des "entityManagerFactory jpa"
 */

public class MyAtomikosJtaPlatform extends AbstractJtaPlatform {
	
	private static final long serialVersionUID = 1L;

	static protected TransactionManager transactionManager;
	static protected UserTransaction transaction;

	@Override
	public TransactionManager locateTransactionManager() {
		return transactionManager;
	}

	@Override
	public UserTransaction locateUserTransaction() {
		return transaction;
	}
	
}