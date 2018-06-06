/*
 * Created on 12/09/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtTypeDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

public class OracleTplCustomerPrvtTypeDAO extends BaseOracleTplCustomerPrvtTypeDAO
implements TplCustomerPrvtTypeDAO
{
	
	private static final String C_TPL_CUSTOMER_PRVT_TYPE = C_PL_SCHEMA + "TPL_CUSTOMER_PRVT_TYPE";
	
	public DataSet loadDomain()
	  {
		ManagedRdbConnection connection = null;
		CitiStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSetDataSet rsds = null;
		StringBuffer query = new StringBuffer();

		try
		{
		  connection = OracleODSDAOFactory.getConnection();
		  query.append( "SELECT " );
		  query.append( C_PRVT_CUST_TYPE_CODE + ", " );
		  query.append( C_PRVT_CUST_TYPE_TEXT + " " );
		  query.append( " FROM " );
		  query.append( C_TPL_CUSTOMER_PRVT_TYPE );
		  query.append( " WHERE " );
		  query.append( C_REC_STAT_CODE + " <> ?" );

		  preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
		  int count = 1;

		  preparedStatement.setString( count++, C_REC_STAT_CODE_INACTIVE );

		  resultSet = preparedStatement.executeQuery();
		  preparedStatement.replaceParametersInQuery(query.toString());

		  rsds = new ResultSetDataSet( resultSet );
		  resultSet.close();
		}
		catch ( SQLException e )
		{
		  throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
		}
		finally
		{
		  closeStatement( preparedStatement );
		  closeConnection( connection );
		}
		return rsds;
	  }

}