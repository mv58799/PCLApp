package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.persistence.pl.dao.TbgSystemSegmentDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @author michele.monteiro,02/05/2007
 *  
 */

public class OracleTbgSystemSegmentDAO extends BaseOracleTbgSystemSegmentDAO
    implements TbgSystemSegmentDAO
{

  /**
   * Nome da tabela
   */
  private static final String C_TBG_SYSTEM_SEGMENT = C_BG_SCHEMA + "TBG_SYSTEM_SEGMENT";

  /**
   * Campos da tabela
   */
  private String C_SYS_SEG_CODE = "SYS_SEG_CODE";

  private String C_SYS_SEG_NAME = "SYS_SEG_NAME";
  
  /**
   * Método popula o combo Segmento do sistema
   */
  public DataSet loadSysSegCodeDomain()
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
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_SYS_SEG_NAME );
      query.append( " FROM " );
      query.append( C_TBG_SYSTEM_SEGMENT );
      
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

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