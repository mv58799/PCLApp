package com.citibank.newcpb.service;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.citibank.newcpb.exception.UnexpectedException;
import com.citibank.ods.common.configuration.Configuration;
import com.citibank.ods.common.factory.JNDIFactory;
import com.citibank.ods.common.util.DefStaticVars;

public class CommonService {
	
	/** Nome da configuração do caminho completo JNDI do UserTransaction */
	private static final String C_CONFIGURATION_USER_TRANSACTION_JNDI_NAME_FULL_PATH = "usertransaction.jndi.full.path";
	
	protected UserTransaction getUserTransaction() throws com.citibank.ods.common.exception.UnexpectedException, SecurityException, IllegalStateException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		//String userTransactionJNDINameFullPath = Configuration.getInstance().getValue(C_CONFIGURATION_USER_TRANSACTION_JNDI_NAME_FULL_PATH);
		
		String userTrans = DefStaticVars.getInstance().dbUserTransaction;
		if (userTrans == null || "".equals(userTrans)) {
			throw new UnexpectedException(C_CONFIGURATION_USER_TRANSACTION_JNDI_NAME_FULL_PATH + " key must be configured.");
		}
		UserTransaction userTransaction = (UserTransaction) JNDIFactory.createObjectUserTrans(userTrans);
		if (userTransaction == null) {
			throw new UnexpectedException("userTransaction_ cannot be null.");
		}
		return userTransaction;
	}

	protected void beginTransaction(UserTransaction transaction) {
		if (transaction == null) {
			throw new UnexpectedException("transaction cannot be null.");
		}
		try {
			transaction.begin();
		} catch (Exception e_) {
			throw new UnexpectedException("Could not begin transaction.", e_);
		}
	}

	protected void commitTransaction(UserTransaction transaction) {
		if (transaction == null) {
			throw new UnexpectedException("transaction cannot be null.");
		}
		try {
			transaction.commit();
		} catch (Exception e_) {
			throw new UnexpectedException("Could not commit transaction.", e_);
		}
	}

	protected void rollbackTransaction(UserTransaction transaction) {
		if (transaction == null) {
			throw new UnexpectedException("transaction cannot be null.");
		}
		try {
			transaction.rollback();
		} catch (Exception e_) {
			throw new UnexpectedException("Could not begin transaction.", e_);
		}
	}
}
