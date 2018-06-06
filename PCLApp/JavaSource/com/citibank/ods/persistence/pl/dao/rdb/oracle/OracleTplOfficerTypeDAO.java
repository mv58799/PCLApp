/*
 * Created on Jan 17, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.common.util.BaseConstraintDecoder;
//import com.citibank.ods.modules.client.functionality.valueobject.OfficerTypeFncVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerTypeDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author gerson.a.rodrigues
 *  
 */
public class OracleTplOfficerTypeDAO extends BaseOracleDAO implements
    TplOfficerTypeDAO
{

  private String C_OFFCR_TYPE_CODE = "OFFCR_TYPE_CODE";

  private String C_OFFCR_TYPE_TEXT = "OFFCR_TYPE_TEXT";

  private static final String C_TPL_OFFICER_TYPE = C_PL_SCHEMA
                                                   + "TPL_OFFICER_TYPE";

  public DataSet loadOfficerType()
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
      query.append( C_OFFCR_TYPE_CODE + " , " );
      query.append( C_OFFCR_TYPE_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_OFFICER_TYPE );
      query.append( " ORDER BY UPPER (" + C_OFFCR_TYPE_TEXT + ")");

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      rsds = new ResultSetDataSet( resultSet );
      resultSet.close();
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return rsds;
  }

}