package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplLoadProcExecRequestEntity;
import com.citibank.ods.entity.pl.TplLoadProcExecRequestEntity;
import com.citibank.ods.entity.pl.valueobject.TplLoadProcExecRequestEntityVO;
import com.citibank.ods.persistence.pl.dao.TplLoadProcExecRequestDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Requerimento de
 * Execução de Processos de Carga.
 */
public class OracleTplLoadProcExecRequestDAO extends
    BaseOracleTplLoadProcExecRequestDAO implements TplLoadProcExecRequestDAO
{
  private static final String C_TPL_LOAD_PROC_EXEC_REQUEST = C_PL_SCHEMA
                                                             + "TPL_LOAD_PROC_EXEC_REQUEST";

  private static final String C_TPL_LOAD_PROCESS = C_PL_SCHEMA
                                                   + "TPL_LOAD_PROCESS";

  private static final String C_TPL_ENTRY_INTER_COLUMN = C_PL_SCHEMA
                                                         + "TPL_ENTRY_INTER_COLUMN";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecRequestDAO#update(com.citibank.ods.entity.pl.BaseTplLoadProcExecRequestEntity)
   */
  public void update(
                     BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "UPDATE " );
      query.append( C_TPL_LOAD_PROC_EXEC_REQUEST );
      query.append( " SET " );
      query.append( C_EXEC_REF_DATE + " = ?, " );
      query.append( C_EXEC_REAS_TEXT + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ? " );
      query.append( " WHERE " );
      query.append( C_LOAD_PROC_NBR + " = ? AND " );
      query.append( C_EXEC_REF_DATE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setTimestamp(
                              1,
                              new Timestamp(
                                             loadProcExecRequestEntity_.getData().getExecRefDate().getTime() ) );

      preparedStatement.setString(
                           2,
                           loadProcExecRequestEntity_.getData().getExecReasText() );

      preparedStatement.setTimestamp(
                              3,
                              new Timestamp(
                                             loadProcExecRequestEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString(
                           4,
                           loadProcExecRequestEntity_.getData().getLastUpdUserId() );

      preparedStatement.setLong(
                         5,
                         loadProcExecRequestEntity_.getData().getLoadProcNbr().longValue() );

      preparedStatement.setTimestamp(
                              6,
                              new Timestamp(
                                             loadProcExecRequestEntity_.getData().getOldExecRefDate().getTime() ) );

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      query = new StringBuffer();
      query.append( "UPDATE " );
      query.append( C_TPL_LOAD_PROCESS );
      query.append( " SET " );
      query.append( C_LOAD_PROC_TEXT + " = ? " );
      query.append( " WHERE " + C_LOAD_PROC_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString(
                           1,
                           loadProcExecRequestEntity_.getData().getLoadProcText() );

      preparedStatement.setLong(
                         2,
                         loadProcExecRequestEntity_.getData().getLoadProcNbr().longValue() );

      preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
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

  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecRequestDAO#list(java.math.BigInteger,
   *      java.lang.String, java.util.Date)
   */
  public DataSet list( BigInteger loadProcNbr_, String execReasText_,
                      Date execRefDate_ )
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
      query.append( C_LOAD_PROC_NBR + ", " );
      query.append( C_EXEC_REAS_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_EXEC_REF_DATE );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROC_EXEC_REQUEST );

      String criteria = "";
      if ( loadProcNbr_ != null )
      {
        criteria = criteria + C_LOAD_PROC_NBR + " = ? AND ";
      }

      if ( execReasText_ != null && !execReasText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_EXEC_REAS_TEXT + "\") LIKE ? AND ";
      }

      if ( execRefDate_ != null )
      {
        criteria = criteria + "TRUNC (" + C_EXEC_REF_DATE
                   + ", \'DD\') = ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " WHERE " + criteria );
      }

      query.append( " ORDER BY " + C_EXEC_REAS_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( loadProcNbr_ != null )
      {
        preparedStatement.setLong( count++, loadProcNbr_.longValue() );
      }

      if ( execReasText_ != null && !execReasText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + execReasText_.toUpperCase() + "%" );
      }

      if ( execRefDate_ != null )
      {
        preparedStatement.setDate( count, new java.sql.Date( execRefDate_.getTime() ) );
      }

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
    catch ( Exception e )
    {
      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return rsds;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecRequestDAO#find(com.citibank.ods.entity.pl.BaseTplLoadProcExecRequestEntity)
   */
  public BaseTplLoadProcExecRequestEntity find(
                                               BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList loadProcExecRequestEntities;
    BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT " );
      query.append( "REQ." + C_EXEC_REF_DATE + ", " );
      query.append( "REQ." + C_EXEC_REAS_TEXT + ", " );
      query.append( "PROC." + C_LOAD_PROC_NBR + ", " );
      query.append( "PROC." + C_LOAD_PROC_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROC_EXEC_REQUEST + " REQ, " );
      query.append( C_TPL_LOAD_PROCESS + " PROC " );
      query.append( " WHERE " );
      query.append( "PROC." + C_LOAD_PROC_NBR + " = " + "REQ."
                    + C_LOAD_PROC_NBR );
      query.append( " AND " );
      query.append( "PROC." + C_LOAD_PROC_NBR + " = ?" );
      query.append( " AND " );
      query.append( "REQ." + C_EXEC_REF_DATE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         loadProcExecRequestEntity_.getData().getLoadProcNbr().longValue() );

      Date date = loadProcExecRequestEntity_.getData().getExecRefDate();

      preparedStatement.setTimestamp( 2, new Timestamp( date.getTime() ) );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      loadProcExecRequestEntities = instantiateFromResultSetFind( resultSet );

      if ( loadProcExecRequestEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( loadProcExecRequestEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        loadProcExecRequestEntity = ( BaseTplLoadProcExecRequestEntity ) loadProcExecRequestEntities.get( 0 );
      }

      return loadProcExecRequestEntity;
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

  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecRequestDAO#insert(com.citibank.ods.entity.pl.BaseTplLoadProcExecRequestEntity)
   */
  public void insert(
                     BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_LOAD_PROC_EXEC_REQUEST + " ( " );
      query.append( C_LOAD_PROC_NBR + ", " );
      query.append( C_EXEC_REAS_TEXT + ", " );
      query.append( C_EXEC_REF_DATE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + " ) " );
      query.append( "VALUES ( ?, ?, ? , ?, ?) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setLong(
                         count++,
                         loadProcExecRequestEntity_.getData().getLoadProcNbr().longValue() );

      if ( loadProcExecRequestEntity_.getData().getExecReasText() != null
           && !loadProcExecRequestEntity_.getData().getExecReasText().equals(
                                                                              "" ) )
      {
        preparedStatement.setString(
                             count++,
                             loadProcExecRequestEntity_.getData().getExecReasText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             loadProcExecRequestEntity_.getData().getExecRefDate().getTime() ) );

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             loadProcExecRequestEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString(
                           count++,
                           loadProcExecRequestEntity_.getData().getLastUpdUserId() );

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());

      query = new StringBuffer();
      query.append( "UPDATE " );
      query.append( C_TPL_LOAD_PROCESS );
      query.append( " SET " );
      query.append( C_LAST_EXEC_DATE + " = ? " );
      query.append( " WHERE " + C_LOAD_PROC_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setTimestamp(
                              1,
                              new Timestamp(
                                             loadProcExecRequestEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setLong(
                         2,
                         loadProcExecRequestEntity_.getData().getLoadProcNbr().longValue() );

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());

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

  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecRequestDAO#delete(java.math.BigInteger,
   *      java.util.Date)
   */
  public void delete( BigInteger loadProcNbr_, Date execRefDate_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " + C_TPL_LOAD_PROC_EXEC_REQUEST );
      query.append( " WHERE " );
      query.append( C_LOAD_PROC_NBR + " = ? AND " );
      query.append( C_EXEC_REF_DATE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, loadProcNbr_.longValue() );

      preparedStatement.setTimestamp( 2, new Timestamp( execRefDate_.getTime() ) );

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());
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
    
  }

  /**
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSetFind( ResultSet resultSet_ )
  {

    TplLoadProcExecRequestEntityVO loadProcExecRequestEntityVO;
    TplLoadProcExecRequestEntity loadProcExecRequestEntity;
    ArrayList loadProcExecRequestEntitiesFind = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        loadProcExecRequestEntity = new TplLoadProcExecRequestEntity();
        loadProcExecRequestEntityVO = ( TplLoadProcExecRequestEntityVO ) loadProcExecRequestEntity.getData();

        loadProcExecRequestEntityVO.setLoadProcNbr( new BigInteger(
                                                                    resultSet_.getString( C_LOAD_PROC_NBR ) ) );
        loadProcExecRequestEntityVO.setExecRefDate( resultSet_.getDate( this.C_EXEC_REF_DATE ) );
        loadProcExecRequestEntityVO.setOldExecRefDate( resultSet_.getDate( this.C_EXEC_REF_DATE ) );
        loadProcExecRequestEntityVO.setExecReasText( resultSet_.getString( this.C_EXEC_REAS_TEXT ) );
        loadProcExecRequestEntityVO.setLoadProcText( resultSet_.getString( this.C_LOAD_PROC_TEXT ) );

        loadProcExecRequestEntitiesFind.add( loadProcExecRequestEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return loadProcExecRequestEntitiesFind;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecRequestDAO#hasCriteria(com.citibank.ods.entity.pl.BaseTplLoadProcExecRequestEntity)
   */
  public boolean hasCriteria(
                             BaseTplLoadProcExecRequestEntity loadProcExecRequestEntity_ )
  {
    Integer logicCritCode;
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT ENTRY_INTER_CODE FROM " );
      query.append( C_TPL_LOAD_PROCESS );
      query.append( " WHERE " + C_LOAD_PROC_NBR + " = ?" );
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      preparedStatement.setLong(
                         1,
                         loadProcExecRequestEntity_.getData().getLoadProcNbr().longValue() );
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
      Integer entryInterCode = null;

      if ( resultSet.next() )
      {
        entryInterCode = new Integer( resultSet.getString( "ENTRY_INTER_CODE" ) );
      }

      query = new StringBuffer();
      query.append( "SELECT COL_NAME FROM " );
      query.append( C_TPL_ENTRY_INTER_COLUMN );
      query.append( " WHERE ENTRY_INTER_CODE = ?" );
      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      preparedStatement.setLong( 1, entryInterCode.longValue() );
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
      rsds = new ResultSetDataSet( resultSet );
      Iterator colNamesIt = rsds.getColumnValuesByName( "COL_NAME" ).iterator();
      String colName;
      while ( colNamesIt.hasNext() )
      {
        colName = ( String ) colNamesIt.next();
        query = new StringBuffer();
        query.append( "SELECT LOGIC_CRIT_CODE FROM TPL_COL_LOGIC_CRIT WHERE ENTRY_INTER_CODE = ? AND COL_NAME = ?" );
        preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
        preparedStatement.setLong( 1, entryInterCode.longValue() );
        preparedStatement.setString( 2, colName );
        resultSet = preparedStatement.executeQuery();
		preparedStatement.replaceParametersInQuery(query.toString());

        logicCritCode = null;
        if ( resultSet.next() )
        {
          logicCritCode = new Integer( resultSet.getInt( "LOGIC_CRIT_CODE" ) );
        }
        if ( logicCritCode != null )
        {
          return true;
        }
      }

      return false;
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

  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecRequestDAO#existReexecution(java.math.BigInteger,
   *      java.util.Date)
   */
  public boolean existReexecution( BigInteger loadProcNbr, Date execRefDate )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT " );
      query.append( C_LOAD_PROC_NBR );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROC_EXEC_REQUEST );
      query.append( " WHERE " + C_LOAD_PROC_NBR + " = ?" );
      query.append( " AND " );
      query.append( C_EXEC_REF_DATE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, loadProcNbr.longValue() );

      preparedStatement.setTimestamp( 2, new Timestamp( execRefDate.getTime() ) );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        return true;
      }

      return false;
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

  }

  public ArrayList selectByPK( BigInteger loadProcNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList loadProcExecRequestEntities;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_LOAD_PROC_NBR + ", " );
      query.append( C_EXEC_REAS_TEXT + ", " );
      query.append( C_EXEC_REF_DATE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROC_EXEC_REQUEST );
      query.append( " WHERE  " + C_LOAD_PROC_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( loadProcNbr_ != null && !loadProcNbr_.equals( "" ) )
      {
        preparedStatement.setLong( count++, loadProcNbr_.longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      loadProcExecRequestEntities = instantiateFromResultSet( resultSet );

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

    return loadProcExecRequestEntities;
  }

  /**
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplLoadProcExecRequestEntityVO loadProcExecRequestEntityVO;
    TplLoadProcExecRequestEntity loadProcExecRequestEntity;
    ArrayList loadProcExecRequestEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        loadProcExecRequestEntity = new TplLoadProcExecRequestEntity();
        loadProcExecRequestEntityVO = ( TplLoadProcExecRequestEntityVO ) loadProcExecRequestEntity.getData();

        loadProcExecRequestEntityVO.setLoadProcNbr( new BigInteger(
                                                                    resultSet_.getString( C_LOAD_PROC_NBR ) ) );
        loadProcExecRequestEntityVO.setExecReasText( resultSet_.getString( this.C_EXEC_REAS_TEXT ) );
        loadProcExecRequestEntityVO.setLastUpdDate( resultSet_.getTimestamp( this.C_LAST_UPD_DATE ) );
        loadProcExecRequestEntityVO.setLastUpdUserId( resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        loadProcExecRequestEntityVO.setExecRefDate( resultSet_.getDate( this.C_EXEC_REF_DATE ) );

        loadProcExecRequestEntities.add( loadProcExecRequestEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return loadProcExecRequestEntities;
  }

  /**
   * Deleta todas as re-execuções existentes.
   */
  public void deleteAll( BigInteger loadProcNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " + C_TPL_LOAD_PROC_EXEC_REQUEST );
      query.append( " WHERE " );
      query.append( C_LOAD_PROC_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, loadProcNbr_.longValue() );

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());
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

  }
}