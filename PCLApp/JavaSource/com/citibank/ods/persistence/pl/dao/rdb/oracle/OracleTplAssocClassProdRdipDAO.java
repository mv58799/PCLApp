package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.persistence.pl.dao.TplAssocClassProdRdipDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplAssocClassProdRdipDAO extends BaseOracleTplAssocClassProdRdipDAO 
implements TplAssocClassProdRdipDAO {

	/*
	 * Nome da tabela
	 */
							   
	private static final String C_TPL_ASSOC_CLASS_PROD_RDIP = C_PL_SCHEMA + "TPL_ASSOC_CLASS_PROD_RDIP";
	
	private static final String C_TPL_TYPE_PROD_RDIP = C_PL_SCHEMA + "TPL_TYPE_PROD_RDIP";
	
	private static final String C_TPL_SUB_TYPE_PROD_RDIP = C_PL_SCHEMA + "TPL_SUB_TYPE_PROD_RDIP";
	
	private static final String C_TPL_CLASS_ASSET_PROD_RDIP = C_PL_SCHEMA + "TPL_CLASS_ASSET_PROD_RDIP";
	
	/**
	 * Lista de Classificação Interface Global 
	 */
	public DataSet listClassGlobal() {

		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try {
			connection = OracleODSDAOFactory.getConnection();
			
			query.append(" SELECT "); 
			query.append("   (TO_CHAR(" + C_TPL_ASSOC_CLASS_PROD_RDIP + ".PRCLAS_PROD_ASSET_CLASS_CODE) ");
			query.append("   || '_' || TO_CHAR(" + C_TPL_ASSOC_CLASS_PROD_RDIP + ".PRCLAS_PROD_STYP_CODE) ");
			query.append("   || '_' || TO_CHAR(" + C_TPL_ASSOC_CLASS_PROD_RDIP + ".PRCLAS_PROD_TYPE_CODE) ) ");
			query.append("   AS ASSOC_CLASS_CODE, ");
			query.append("   (" + C_TPL_CLASS_ASSET_PROD_RDIP + ".PROD_ASSET_CLASS_TEXT || ' -> ' "); 
			query.append("   || " + C_TPL_TYPE_PROD_RDIP + ".PROD_TYPE_TEXT || ' -> ' "); 
			query.append("   || " + C_TPL_SUB_TYPE_PROD_RDIP + ".PROD_STYP_SUB_TYPE_TEXT) AS ASSOC_CLASS_TEXT ");
			query.append(" FROM ");
			query.append(" 	 " + C_TPL_ASSOC_CLASS_PROD_RDIP + " ");
			query.append(" INNER JOIN " + C_TPL_TYPE_PROD_RDIP + " ON TPL_TYPE_PROD_RDIP.PROD_TYPE_CODE = " + C_TPL_ASSOC_CLASS_PROD_RDIP + ".PRCLAS_PROD_TYPE_CODE ");
			query.append(" INNER JOIN " + C_TPL_SUB_TYPE_PROD_RDIP + " ON TPL_SUB_TYPE_PROD_RDIP.PROD_STYP_CODE = " + C_TPL_ASSOC_CLASS_PROD_RDIP + ".PRCLAS_PROD_STYP_CODE ");
			query.append(" INNER JOIN " + C_TPL_CLASS_ASSET_PROD_RDIP + " ON TPL_CLASS_ASSET_PROD_RDIP.PROD_ASSET_CLASS_CODE = " + C_TPL_ASSOC_CLASS_PROD_RDIP + ".PRCLAS_PROD_ASSET_CLASS_CODE ");
			query.append(" ORDER BY " + C_TPL_CLASS_ASSET_PROD_RDIP + ".PROD_ASSET_CLASS_TEXT ");

			preparedStatement = new CitiStatement(connection.prepareStatement(query.toString()));

			resultSet = preparedStatement.executeQuery();
			preparedStatement.replaceParametersInQuery(query.toString());

			rsds = new ResultSetDataSet(resultSet);
			resultSet.close();
			
		} catch (SQLException e) {
			throw new UnexpectedException(e.getErrorCode(),
					C_ERROR_EXECUTING_STATEMENT, e);
		} finally {
			closeStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return rsds;
	}

}
