/*
 * Created on Mar 2, 2007
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
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplPotentialWealthEntity;
import com.citibank.ods.persistence.pl.dao.TplPotentialWealthDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
/**
 * @author gerson.a.rodrigues
 * 
 */
public class OracleTplPotentialWealthDAO extends OraclePotentialWealthDAO implements TplPotentialWealthDAO
{
  private String C_TPL_POTENTIAL_WEALTH = C_PL_SCHEMA + "TPL_POTENTIAL_WEALTH";

  /* (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO#loadDomain()
   */
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
      query.append( C_WEALTH_POTNL_CODE + ", " );
      query.append( C_WEALTH_POTNL_TEXT + " " );
      query.append( " FROM " );
      query.append( C_TPL_POTENTIAL_WEALTH);
      query.append( " WHERE ");
      query.append( C_REC_STAT_CODE + " <> ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
            
      preparedStatement.setString( 1, C_STATUS_INATIVO );      

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

  /* (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplOfficerCmplDAO#find(com.citibank.ods.entity.pl.BaseTplOfficerCmplEntity)
   */
  public BaseTplPotentialWealthEntity find( BaseTplPotentialWealthEntity potentialWealthEntity_ )
  {
    return null;
  }
}
