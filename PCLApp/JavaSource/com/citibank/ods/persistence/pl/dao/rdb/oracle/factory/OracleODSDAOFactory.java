package com.citibank.ods.persistence.pl.dao.rdb.oracle.factory;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.factory.JNDIFactory;
import com.citibank.ods.common.persistence.dao.factory.BaseDAOFactory;
import com.citibank.ods.common.util.DefStaticVars;
import com.citibank.ods.persistence.bg.dao.TbgBankDAO;
import com.citibank.ods.persistence.bg.dao.TbgBranchDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustAddressDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustCellDAO;
import com.citibank.ods.persistence.bg.dao.TbgCustMailDAO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.bg.dao.TbgPointAcctInvstDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgBankDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgBranchDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgCustAddressDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgCustCellDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgCustMailDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgOfficerDAO;
import com.citibank.ods.persistence.bg.dao.rdb.oracle.OracleTbgPointAcctInvstDAO;
import com.citibank.ods.persistence.pl.dao.*;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.*;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetHistDAO;
import com.citibank.sad.passwordmanager.PasswordManager;

/**
 * <p>
 * Title: Classe DAO Factory do sistema ODS para o banco de dados Oracle
 * </p>
 * <p>
 * Description: Classe para acesso a dados do sistema ODS no banco de dados
 * Oracle
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: ACCENTURE
 * </p>
 * @author ralf.davi.filho
 * @version 1.0
 */
public class OracleODSDAOFactory extends ODSDAOFactory {

	private static DataSource ms_dataSource = null;

	private static final String C_DAO_FACTORY_SYSTEM = "ods";

	public OracleODSDAOFactory() {
		super();
	}

	protected final void initializeDataSource() {
		if (ms_dataSource != null) {
			throw new UnexpectedException("Datasource already initialized.");
		}

		String dataSourceJNDIName = DefStaticVars.getInstance().dbDataSource;

		if (dataSourceJNDIName == null || "".equals(dataSourceJNDIName)) {
			throw new UnexpectedException(
				"configuration ["
					+ DefStaticVars.getInstance().dbDataSource
					+ "] undefined.");
		}

		ms_dataSource =
			(DataSource) JNDIFactory.createObject(dataSourceJNDIName);

	}

	public static ManagedRdbConnection getConnection() {

		Connection connection = null;
		ManagedRdbConnection managedRDBConnection = null;
		if (ms_dataSource == null) {
			throw new UnexpectedException("DataSource not initialized.");
		}

		try {
			{
				{
					String userId =
						DefStaticVars.getInstance().getOraclePassUserID;
					String passwd =
						DefStaticVars.getInstance().getOraclePassPwd;

					if (passwd == null) {
						String passwordFile =
							DefStaticVars
								.getInstance()
								.getOraclePassPasswordFile;
						String keyFile =
							DefStaticVars.getInstance().getOraclePassKeyFile;
						String serverDB =
							DefStaticVars.getInstance().getOraclePassServerName;
						passwd =
							getPassword(
								userId,
								passwordFile,
								keyFile,
								serverDB);
					}

					try {
					connection = ms_dataSource.getConnection(userId, passwd);
					}catch(java.lang.UnsupportedOperationException ue){
					connection = ms_dataSource.getConnection();
				}
				}
			}
		} catch (SQLException e) {
			throw new UnexpectedException(
				e.getErrorCode(),
				"Não foi possível criar conexão a partir do DataSource",
				e);
		} catch (Exception e_) {
			throw new UnexpectedException(e_.getMessage(), e_);
		}

		managedRDBConnection = new ManagedRdbConnection(connection);

		return managedRDBConnection;

	}

	private static String getPassword(
		String userDB,
		String passwordFile,
		String keyFile,
		String serverDB)
		throws Exception {
		PasswordManager passwordManager =
			new PasswordManager(passwordFile, keyFile, userDB, serverDB);
		int result = passwordManager.get();
		if (result == PasswordManager.TRUE_) {
			return passwordManager.getPassword();

		} else {
			throw new UnexpectedException(
				result,
				"Erro ao recuperar senha de criptografia."
					+ "["
					+ passwordFile
					+ "]["
					+ keyFile
					+ "]["
					+ userDB
					+ "]["
					+ serverDB
					+ "]");
		}

	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getCustomerDAO()
	 */

	public TplOfficerTypeDAO getTplOfficerTypeDAO() {
		return new OracleTplOfficerTypeDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getCustomerDAO()
	 */

	public TplErEmHistDAO getTplErEmHistDAO() {
		return new OracleTplErEmHistDAO();
	}

	/**
	 * 
	 * @generated "UML to Java
	 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	public static void bootstrap() {
		if (ms_instance != null)
			throw new UnexpectedException("bootstrap cannot be invoked more than once.");
		ms_instance =
			(ODSDAOFactory) BaseDAOFactory.createDAOFactory(
				C_DAO_FACTORY_SYSTEM);
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplOfficerCmplDAO()
	 */
	public TplOfficerCmplDAO getTplOfficerCmplDAO() {
		return new OracleTplOfficerCmplDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getOfficerCmplMovementDAO()
	 */
	public TplOfficerCmplMovDAO getTplOfficerCmplMovDAO() {
		return new OracleTplOfficerCmplMovDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getOfficerCmplHistoryDAO()
	 */
	public TplOfficerCmplHistDAO getTplOfficerCmplHistDAO() {
		return new OracleTplOfficerCmplHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplCustomerPrvtCmplCurrentDAO()
	 */
	public TplCustomerPrvtCmplDAO getTplCustomerPrvtCmplDAO() {
		return new OracleTplCustomerPrvtCmplDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplCustomerPrvtCmplCurrentDAO()
	 */
	public TplCustomerPrvtCmplMovDAO getTplCustomerPrvtCmplMovDAO() {
		return new OracleTplCustomerPrvtCmplMovDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getOfficerCmplCurrentDAO()
	 */
	public TplOfficerCmplDAO getOfficerCmplCurrentDAO() {
		return new OracleTplOfficerCmplDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getOfficerCmplMovementDAO()
	 */
	public TplOfficerCmplMovDAO getOfficerCmplMovementDAO() {
		return new OracleTplOfficerCmplMovDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getOfficerCmplHistoryDAO()
	 */
	public TplOfficerCmplHistDAO getOfficerCmplHistoryDAO() {
		return new OracleTplOfficerCmplHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getOfficerCmplHistoryDAO()
	 */
	public TplCustomerPrvtCmplHistDAO getTplCustomerPrvtCmplHistDAO() {
		return new OracleTplCustomerPrvtCmplHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplAggrProdPrvtDAO()
	 */
	public TplAggrProdPrvtDAO getTplAggrProdPrvtDAO() {
		return new OracleTplAggrProdPrvtDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplAggrProdPrvtHistDAO()
	 */
	public TplAggrProdPrvtHistDAO getTplAggrProdPrvtHistDAO() {
		return new OracleTplAggrProdPrvtHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplClassCmplcDAO()
	 */
	public TplClassCmplcDAO getTplClassCmplcDAO() {
		return new OracleTplClassCmplcDAO();
	}
	
	/**
		 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplCustomerPrvtTypeDAO()
		 */
		public TplCustomerPrvtTypeDAO getTplCustomerPrvtTypeDAO() {
			return new OracleTplCustomerPrvtTypeDAO();
		}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProdRiskCatPrvtDAO()
	 */
	public TplProdRiskCatPrvtDAO getTplProdRiskCatPrvtDAO() {
		return new OracleTplProdRiskCatPrvtDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProdRiskCatPrvtMovDAO()
	 */
	public TplProdRiskCatPrvtMovDAO getTplProdRiskCatPrvtMovDAO() {
		return new OracleTplProdRiskCatPrvtMovDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProdRiskCatPrvtHistDAO()
	 */
	public TplProdRiskCatPrvtHistDAO getTplProdRiskCatPrvtHistDAO() {
		return new OracleTplProdRiskCatPrvtHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplLoadProcessDAO()
	 */
	public TplLoadProcessDAO getTplLoadProcessDAO() {
		return new OracleTplLoadProcessDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplLoadProcExecDAO()
	 */
	public TplLoadProcExecDAO getTplLoadProcExecDAO() {
		return new OracleTplLoadProcExecDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPotentialWealthDAO()
	 */
	public TplPotentialWealthDAO getTplPotentialWealthDAO() {
		return new OracleTplPotentialWealthDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplCustomerPrvtDAO()
	 */
	public TplCustomerPrvtDAO getTplCustomerPrvtDAO() {
		return new OracleTplCustomerPrvtDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProdQlfyPrvtDAO()
	 */
	public TplProdQlfyPrvtDAO getTplProdQlfyPrvtDAO() {
		return new OracleTplProdQlfyPrvtDAO();
	}

	/*
	 * (non-Javadoc) 18.03.2007)
	 * 
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProdQlfyPrvtMovDAO()
	 */
	public TplProdQlfyPrvtMovDAO getTplProdQlfyPrvtMovDAO() {
		return new OracleTplProdQlfyPrvtMovDAO(); //OracleTplProdQlfy;
	}

	public TplProdQlfyPrvtHistDAO getTplProdQlfyPrvtHistDAO() {
		return new OracleTplProdQlfyPrvtHistDAO(); //OracleTplProdQlfy;
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProductFamilyPrvtDAO()
	 */
	public TplProductFamilyPrvtDAO getTplProductFamilyPrvtDAO() {
		return new OracleTplProductFamilyPrvtDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProductFamilyPrvtMovDAO()
	 */
	public TplProductFamilyPrvtMovDAO getTplProductFamilyPrvtMovDAO() {
		return new OracleTplProductFamilyPrvtMovDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProductFamilyPrvtHistDAO()
	 */
	public TplProductFamilyPrvtHistDAO getTplProductFamilyPrvtHistDAO() {
		return new OracleTplProductFamilyPrvtHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProductSubFamilyPrvtDAO()
	 */
	public TplProdSubFamlPrvtDAO getTplProductSubFamilyPrvtDAO() {
		return new OracleTplProdSubFamlPrvtDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProductSubFamilyPrvtMovDAO()
	 */
	public TplProdSubFamlPrvtMovDAO getTplProductSubFamilyPrvtMovDAO() {
		return new OracleTplProdSubFamlPrvtMovDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProductSubFamilyPrvtHistDAO()
	 */
	public TplProdSubFamlPrvtHistDAO getTplProductSubFamilyPrvtHistDAO() {
		return new OracleTplProdSubFamlPrvtHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplEntyInterColumnDAO()
	 */
	public TplEntryInterColumnDAO getTplEntryInterColumnDAO() {
		return new OracleTplEntryInterColumnDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplLoadProcExecRequestDAO()
	 */
	public TplLoadProcExecRequestDAO getTplLoadProcExecRequestDAO() {
		return new OracleTplLoadProcExecRequestDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplEntryInterDAO()
	 */
	public TplEntryInterDAO getTplEntryInterDAO() {
		return new OracleTplEntryInterDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplContactCustDAO()
	 */
	public TplContactCustDAO getTplContactCustDAO() {
		return new OracleTplContactCustDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplLogicCritDAO()
	 */
	public TplLogicCritDAO getTplLogicCritDAO() {
		return new OracleTplLogicCritDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplLogicCritDomainDAO()
	 */
	public TplLogicCritDomainDAO getTplLogicCritDomainDAO() {
		return new OracleTplLogicCritDomainDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplLogicCritDomainDAO()
	 */
	public TplRelationPrvtDAO getTplRelationPrvtDAO() {
		return new OracleTplRelationPrvtDAO();
	}

	/*
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPortfolioPrvtDAO()
	 */
	public TplPortfolioPrvtDAO getTplPortfolioPrvtDAO() {

		return new OracleTplPortfolioPrvtDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPlayerDAO()
	 */
	public TplPlayerDAO getTplPlayerDAO() {
		return new OracleTplPlayerDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPlayerMovDAO()
	 */
	public TplPlayerMovDAO getTplPlayerMovDAO() {
		return new OracleTplPlayerMovDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPlayerHistDAO()
	 */
	public TplPlayerHistDAO getTplPlayerHistDAO() {
		return new OracleTplPlayerHistDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPlayerRoleDAO()
	 */
	public TplPlayerRoleDAO getTplPlayerRoleDAO() {
		return new OracleTplPlayerRoleDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPlayerRoleHistDAO()
	 */
	public TplPlayerRoleHistDAO getTplPlayerRoleHistDAO() {
		return new OracleTplPlayerRoleHistDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPlayerRoleMovDAO()
	 */
	public TplPlayerRoleMovDAO getTplPlayerRoleMovDAO() {
		return new OracleTplPlayerRoleMovDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProdPlayerRoleDAO()
	 */
	public TplProdPlayerRoleDAO getTplProdPlayerRoleDAO() {
		return new OracleTplProdPlayerRoleDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplProdPlayerRoleDAO()
	 */
	public TplProdPlayerRoleMovDAO getTplProdPlayerRoleMovDAO() {
		return new OracleTplProdPlayerRoleMovDAO();
	}

	public TplProdPlayerRoleHistDAO getTplProdPlayerRoleHistDAO() {
		return new OracleTplProdPlayerRoleHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTo3ProductAccountDAO()
	 */
	public To3ProductAccountDAO getTo3ProductAccountDAO() {
		return new OracleTo3ProductAccountDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTo3ProductAccountHistDAO()
	 */
	public To3ProductAccountHistDAO getTo3ProductAccountHistDAO() {
		return new OracleTo3ProductAccountHistDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplRelationEgDAO()
	 */
	public TplRelationEgDAO getTplRelationEgDAO() {
		return new OracleTplRelationEgDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplRelationEgMovDAO()
	 */
	public TplRelationEgMovDAO getTplRelationEgMovDAO() {
		return new OracleTplRelationEgMovDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplRelationEgHistDAO()
	 */
	public TplRelationEgHistDAO getTplRelationEgHistDAO() {
		return new OracleTplRelationEgHistDAO();

	}

	public TplProductDAO getTplProductDAO() {
		return new OracleTplProductDAO();
	}

	public TplProductMovDAO getTplProductMovDAO() {
		return new OracleTplProductMovDAO();
	}

	public TplProductHistDAO getTplProductHistDAO() {
		return new OracleTplProductHistDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplDocTransferDAO()
	 */
	public TplDocTransferDAO getTplDocTransferDAO() {
		return new OracleTplDocTransferDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplDocTransferMovDAO()
	 */
	public TplDocTransferMovDAO getTplDocTransferMovDAO() {
		return new OracleTplDocTransferMovDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplDocTransferHistDAO()
	 */
	public TplDocTransferHistDAO getTplDocTransferHistDAO() {
		return new OracleTplDocTransferHistDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getIpDocCallbackDAO()
	 */
	public TplIpDocCallbackDAO getTplIpDocCallbackDAO() {
		return new OracleTplIpDocCallbackDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getIpDocCallbackMovDAO()
	 */
	public TplIpDocCallbackMovDAO getTplIpDocCallbackMovDAO() {
		return new OracleTplIpDocCallbackMovDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getIpDocCallbackHistDAO()
	 */
	public TplIpDocCallbackHistDAO getTplIpDocCallbackHistDAO() {
		return new OracleTplIpDocCallbackHistDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplIpDocPrvtDAO()
	 */
	public TplIpDocPrvtDAO getTplIpDocPrvtDAO() {
		return new OracleTplIpDocPrvtDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplIpDocPrvtDAO()
	 */
	public TplIpDocPrvtMovDAO getTplIpDocPrvtMovDAO() {
		return new OracleTplIpDocPrvtMovDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplIpDocPrvtDAO()
	 */
	public TplIpDocPrvtHistDAO getTplIpDocPrvtHistDAO() {
		return new OracleTplIpDocPrvtHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplMrDocPrvtDAO()
	 */
	public TplMrDocPrvtDAO getTplMrDocPrvtDAO() {
		return new OracleTplMrDocPrvtDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplMrDocPrvtHistDAO()
	 */
	public TplMrDocPrvtHistDAO getTplMrDocPrvtHistDAO() {
		return new OracleTplMrDocPrvtHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplMrDocPrvtMovDAO()
	 */
	public TplMrDocPrvtMovDAO getTplMrDocPrvtMovDAO() {
		return new OracleTplMrDocPrvtMovDAO();
	}

	//  Comentado por Gerson
	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplMrDocCallbackDAO()
	 */
	public TplMrDocCallbackDAO getTplMrDocCallbackDAO() {
		return new OracleTplMrDocCallbackDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplMrDocCallbackHistDAO()
	 */
	public TplMrDocCallbackHistDAO getTplMrDocCallbackHistDAO() {
		return new OracleTplMrDocCallbackHistDAO();
	}

	/**
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplMrDocCallbackMovDAO()
	 */
	public TplMrDocCallbackMovDAO getTplMrDocCallbackMovDAO() {
		return new OracleTplMrDocCallbackMovDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplRoleCustDAO()
	 */
	public TplRoleCustDAO getTplRoleCustDAO() {
		return new OracleTplRoleCustDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplErEmDAO()
	 */
	public TplErEmDAO getTplErEmDAO() {
		return new OracleTplErEmDAO();
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplErEmMovDAO()
	 */
	public TplErEmMovDAO getTplErEmMovDAO() {
		return new OracleTplErEmMovDAO();
	}

	public TbgSegmentDAO getTbgSegmentDAO() {
		return new OracleTbgSegmentDAO();
	}

	public TbgSystemDAO getTbgSystemDAO() {
		return new OracleTbgSystemDAO();
	}

	public TbgSystemSegmentDAO getTbgSystemSegmentDAO() {
		return new OracleTbgSystemSegmentDAO();
	}

	public To3ProductAccountMovDAO getTo3ProductAccountMovDAO() {
		return new OracleTo3ProductAccountMovDAO();
	}

	public TbgOfficerDAO getTbgOfficerDAO() {
		return new OracleTbgOfficerDAO();
	}

	public TbgCustAddressDAO getTbgCustAddressDAO() {
		return new OracleTbgCustAddressDAO();
	}

	public TbgCustCellDAO getTbgCustCellDAO() {
		return new OracleTbgCustCellDAO();
	}

	public TbgCustMailDAO getTbgCustMailDAO() {
		return new OracleTbgCustMailDAO();
	}

	public TbgBranchDAO getTbgBranchDAO() {
		return new OracleTbgBranchDAO();
	}

	public TbgBankDAO getTbgBankDAO() {
		return new OracleTbgBankDAO();
	}

	public TplCurAcctPrmntInstrDAO getTplCurAcctPrmntInstrDAO() {
		return new OracleTplCurAcctPrmntInstrDAO();
	}

	public TplCurAcctPrmntInstrMovDAO getTplCurAcctPrmntInstrMovDAO() {
		return new OracleTplCurAcctPrmntInstrMovDAO();
	}

	public TplCurAcctPrmntInstrHistDAO getTplCurAcctPrmntInstrHistDAO() {
		return new OracleTplCurAcctPrmntInstrHistDAO();
	}

	public TbgPointAcctInvstDAO getTbgPointAcctInvstDAO() {
		return new OracleTbgPointAcctInvstDAO();
	}

	public TplBrokerDAO getTplBrokerDAO() {
		return new OracleTplBrokerDAO();
	}

	public TplBrokerMovDAO getTplBrokerMovDAO() {
		return new OracleTplBrokerMovDAO();
	}

	public TplBrokerHistDAO getTplBrokerHistDAO() {
		return new OracleTplBrokerHistDAO();
	}

	public TplBkrPortfMgmtDAO getTplBkrPortfMgmtDAO() {
		return new OracleTplBkrPortfMgmtDAO();
	}

	public TplBkrPortfMgmtMovDAO getTplBkrPortfMgmtMovDAO() {
		return new OracleTplBkrPortfMgmtMovDAO();
	}

	public TplBkrPortfMgmtHistDAO getTplBkrPortfMgmtHistDAO() {
		return new OracleTplBkrPortfMgmtHistDAO();
	}

	public TplProdSubAssetMovDAO getTplProdSubAssetMovDAO() {
		return new OracleTplProdSubAssetMovDAO();
	}
	
	public TplProdSubAssetHistDAO getTplProdSubAssetHistDAO() {
				return new OracleTplProdSubAssetHistDAO();
			}

	public TplProdSubAssetDAO getTplProdSubAssetDAO() {
		return new OracleTplProdSubAssetDAO();
	}

	public TplProdAssetTypeMovDAO getTplProdAssetTypeMovDAO() {
		return new OracleTplProdAssetTypeMovDAO();
	}
	
	public TplProdAssetHistDAO getTplProdAssetHistDAO() {
			return new OracleTplProdAssetHistDAO();
		}

	public TplProdAssetTypeDAO getTplProdAssetTypeDAO() {
		return new OracleTplProdAssetTypeDAO();
	}
	
	public TplProdAssetTypeHistDAO getTplProdAssetTypeHistDAO() {
			return new OracleTplProdAssetTypeHistDAO();
		}

	public TplProdAssetMovDAO getTplProdAssetMovDAO() {
		return new OracleTplProdAssetMovDAO();
	}

	public TplProdAssetDAO getTplProdAssetDAO() {
		return new OracleTplProdAssetDAO();
	}
	
	public TplCentrApprovalMovDAO getTplCentrApprovalMovDAO(){
		return new OracleTplCentrApprovalMovDAO();
	}

	public TplIpDocTransFinancDAO getTplIpDocTransFinancDAO() {
		return new OracleTplIpDocTransFinancDAO();
	}

	public TplErDAO getTplErDAO() {
		return new OracleTplErDAO();
	}

	public TplErMovDAO getTplErMovDAO() {
		return new OracleTplErMovDAO();
	}

	public TplErHistDAO getTplErHistDAO() {
		return new OracleTplErHistDAO();
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplReasonEndRelationDAO()
	 */
	public TplReasonEndRelationDAO getTplReasonEndRelationDAO() {		
		return new OracleTplReasonEndRelationDAO();
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplReltnClassEquityDAO()
	 */
	public TplReltnClassEquityDAO getTplReltnClassEquityDAO() {
		return new OracleTplReltnClassEquityDAO();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory#getTplPlayerDAO()
	 */
	public TplAssocClassProdRdipDAO getTplAssocClassProdRdipDAO() {
		return new OracleTplAssocClassProdRdipDAO();
	}
	
	public TplShortNamePlayerMovDAO getTplShortNamePlayerMovDAO() {
		return new OracleTplShortNameMovDAO();
	}

	
	@Override
	public TplRiskFamilyProdPlayerDAO getTplRiskFamilyProdPlayerDAO() {
		return new OracleTplRiskFamilyProdPlayerDAO();
	}

	@Override
	public TplRiskFamilyProdPlayerHistDAO getTplRiskFamilyProdPlayerHistDAO() {
		return new OracleTplRiskFamilyProdPlayerHistDAO();
	}

	@Override
	public TplRiskFamilyProdPlayerMovDAO getTplRiskFamilyProdPlayerMovDAO() {
		return new OracleTplRiskFamilyProdPlayerMovDAO();
	}	

	@Override
	public TplShortNamePlayerDAO getTplShortNamePlayerDAO() {
		return new OracleTplShortNameDAO();
	}

	@Override
	public TplShortNamePlayerHistDAO getTplShortNamePlayerHistDAO() {
		return new OracleTplShortNameHistDAO();
	}

	@Override
	public TplProductCorpDAO getTplProductCorpDAO() {
		// TODO Auto-generated method stub
		return new OracleTplProductCorpDAO();
	}

	@Override
	public TplProductCorpHistDAO getTplProductCorpHistDAO() {
		// TODO Auto-generated method stub
		return new OracleTplProductCorpHistDAO();
	}

	@Override
	public TplProductCorpMovDAO getTplProductCorpMovDAO() {
		// TODO Auto-generated method stub
		return new OracleTplProductCorpMovDAO();
	}
	
	@Override
	public TbgClassTypeFundAnbidDAO getTbgClassTypeFundAnbidDAO() {
		return new OracleTbgClassTypeFundAnbidDAO();
	}

	@Override
	public TplAssetClassOnesrcDao getTplAssetClassOnesrcDao() {
		
		return new OracleTplAssetClassOnesrcDao();
	}

}	
