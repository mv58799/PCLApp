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
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplLogicCritDomainEntity;
import com.citibank.ods.entity.pl.BaseTplLogicCritEntity;
import com.citibank.ods.entity.pl.TplLogicCritEntity;
import com.citibank.ods.entity.pl.valueobject.TplLogicCritEntityVO;
import com.citibank.ods.persistence.pl.dao.TplLogicCritDAO;
import com.citibank.ods.persistence.pl.dao.TplLogicCritDomainDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Crítica Lógica
 */
public class OracleTplLogicCritDAO extends BaseOracleTplLogicCritDAO implements
    TplLogicCritDAO
{
  // Tabela TPL_LOGIC_CRIT
  private static final String C_TPL_LOGIC_CRIT = C_PL_SCHEMA + "TPL_LOGIC_CRIT";

  // Tabela TPL_COL_LOGIC_CRIT
  private static final String C_TPL_COL_LOGIC_CRIT = C_PL_SCHEMA
                                                     + "TPL_COL_LOGIC_CRIT";

  private String C_INDICATOR_TEXT = "INDICATOR_TEXT";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#find(com.citibank.ods.entity.pl.BaseTplLogicCritEntity)
   */
  public BaseTplLogicCritEntity find( BaseTplLogicCritEntity logicCritEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList logicCritEntities;
    BaseTplLogicCritEntity logicCritEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_LOGIC_CRIT_CODE + ", " );
      query.append( C_LOGIC_CRIT_TEXT + ", " );
      query.append( C_DATA_TYPE_CODE + ", " );
      query.append( C_LOGIC_CRIT_DOM_IND + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LOAD_PROG_UPD_IND + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_LOGIC_CRIT );
      query.append( " WHERE " );
      query.append( C_LOGIC_CRIT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         logicCritEntity_.getData().getLogicCritCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      logicCritEntities = instantiateFromResultSet( resultSet );

      if ( logicCritEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( logicCritEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        logicCritEntity = ( BaseTplLogicCritEntity ) logicCritEntities.get( 0 );
      }

      // Obtém a instância da Factory
      ODSDAOFactory daoFactory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplLogicCritDomainDAO logicCritDomainDAO = daoFactory.getTplLogicCritDomainDAO();

      ArrayList logicCritDomainEntities = logicCritDomainDAO.findDomainsByCriteriaPK( logicCritEntity.getData().getLogicCritCode() );

      logicCritEntity.setLogicCritDomainEntities( logicCritDomainEntities );

      return logicCritEntity;
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
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplLogicCritEntityVO logicCritEntityVO;
    TplLogicCritEntity logicCritEntity;
    ArrayList logicCritEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        logicCritEntity = new TplLogicCritEntity();
        logicCritEntityVO = ( TplLogicCritEntityVO ) logicCritEntity.getData();

        logicCritEntityVO.setLogicCritCode( new BigInteger(
                                                            resultSet_.getString( this.C_LOGIC_CRIT_CODE ) ) );
        logicCritEntityVO.setLogicCritText( resultSet_.getString( this.C_LOGIC_CRIT_TEXT ) );
        logicCritEntityVO.setDataTypeCode( resultSet_.getString( this.C_DATA_TYPE_CODE ) );
        logicCritEntityVO.setLogicCritDomInd( resultSet_.getString( this.C_LOGIC_CRIT_DOM_IND ) );
        logicCritEntityVO.setLastUpdUserId( resultSet_.getString( this.C_LAST_UPD_USER_ID ) );
        logicCritEntityVO.setLastUpdDate( resultSet_.getTimestamp( this.C_LAST_UPD_DATE ) );
        logicCritEntityVO.setLoadProgUpdInd( resultSet_.getString( this.C_LOAD_PROG_UPD_IND ) );
        logicCritEntityVO.setRecStatCode( resultSet_.getString( this.C_REC_STAT_CODE ) );

        logicCritEntities.add( logicCritEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return logicCritEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#getNextLogicCritCode()
   */
  public Integer getNextLogicCritCode()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    Integer nextVal = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT PL.SQ_LOGIC_CRIT_CODE.NEXTVAL FROM DUAL " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        nextVal = new Integer( resultSet.getInt( "NEXTVAL" ) );
      }

      return nextVal;
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#insert(com.citibank.ods.entity.pl.BaseTplLogicCritEntity)
   */
  public void insert( BaseTplLogicCritEntity logicCritEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_LOGIC_CRIT + " ( " );
      query.append( C_LOGIC_CRIT_CODE + ", " );
      query.append( C_DATA_TYPE_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID );

      int attCount = 0;
      if ( logicCritEntity_.getData().getLogicCritText() != null
           && !logicCritEntity_.getData().getLogicCritText().equals( "" ) )
      {
        query.append( ", " + C_LOGIC_CRIT_TEXT );
        attCount++;
      }

      if ( logicCritEntity_.getData().getLogicCritDomInd() != null
           && !logicCritEntity_.getData().getLogicCritDomInd().equals( "" ) )
      {
        query.append( ", " + C_LOGIC_CRIT_DOM_IND );
        attCount++;
      }

      if ( logicCritEntity_.getData().getLoadProgUpdInd() != null
           && !logicCritEntity_.getData().getLoadProgUpdInd().equals( "" ) )
      {
        query.append( ", " + C_LOAD_PROG_UPD_IND );
        attCount++;
      }

      query.append( " )" );

      query.append( "VALUES ( ?, ?, ? , ?, ? " );

      for ( int i = 0; i < attCount; i++ )
      {
        query.append( ", ? " );
      }

      query.append( ") " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         logicCritEntity_.getData().getLogicCritCode().longValue() );

      preparedStatement.setString( 2, logicCritEntity_.getData().getDataTypeCode() );

      preparedStatement.setTimestamp(
                              3,
                              new Timestamp(
                                             logicCritEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString( 4, logicCritEntity_.getData().getRecStatCode() );

      preparedStatement.setString( 5, logicCritEntity_.getData().getLastUpdUserId() );

      int index = 6;

      if ( logicCritEntity_.getData().getLogicCritText() != null
           && !logicCritEntity_.getData().getLogicCritText().equals( "" ) )
      {
        preparedStatement.setString( index++,
                             logicCritEntity_.getData().getLogicCritText() );
      }

      if ( logicCritEntity_.getData().getLogicCritDomInd() != null
           && !logicCritEntity_.getData().getLogicCritDomInd().equals( "" ) )
      {
        preparedStatement.setString( index++,
                             logicCritEntity_.getData().getLogicCritDomInd() );
      }
      if ( logicCritEntity_.getData().getLoadProgUpdInd() != null
           && !logicCritEntity_.getData().getLoadProgUpdInd().equals( "" ) )
      {
        preparedStatement.setString( index++,
                             logicCritEntity_.getData().getLoadProgUpdInd() );
      }

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());

      Iterator logicCritDomainEntities = logicCritEntity_.getLogicCritDomainEntities().iterator();
      BaseTplLogicCritDomainEntity logicCritDomainEntity;

      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplLogicCritDomainDAO logicCritDomainDAO = odsDAOFactory.getTplLogicCritDomainDAO();
      while ( logicCritDomainEntities.hasNext() )
      {
        logicCritDomainEntity = ( BaseTplLogicCritDomainEntity ) logicCritDomainEntities.next();
        logicCritDomainDAO.insert( logicCritDomainEntity );
      }

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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#update(com.citibank.ods.entity.pl.BaseTplLogicCritEntity)
   */
  public void update( BaseTplLogicCritEntity logicCritEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " );
      query.append( C_TPL_LOGIC_CRIT );
      query.append( " SET " );
      query.append( C_LOGIC_CRIT_CODE + " = ?," );
      query.append( C_LOGIC_CRIT_TEXT + " = ?, " );
      query.append( C_LOGIC_CRIT_DOM_IND + " = ?, " );
      query.append( C_DATA_TYPE_CODE + " = ?, " );
      query.append( C_LOAD_PROG_UPD_IND + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ? " );
      query.append( " WHERE " + C_LOGIC_CRIT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         logicCritEntity_.getData().getLogicCritCode().longValue() );

      preparedStatement.setString( 2, logicCritEntity_.getData().getLogicCritText() );

      preparedStatement.setString( 3, logicCritEntity_.getData().getLogicCritDomInd() );

      preparedStatement.setString( 4, logicCritEntity_.getData().getDataTypeCode() );

      preparedStatement.setString( 5, logicCritEntity_.getData().getLoadProgUpdInd() );

      preparedStatement.setTimestamp(
                              6,
                              new Timestamp(
                                             logicCritEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString( 7, logicCritEntity_.getData().getRecStatCode() );

      preparedStatement.setString( 8, logicCritEntity_.getData().getLastUpdUserId() );

      preparedStatement.setLong(
                         9,
                         logicCritEntity_.getData().getLogicCritCode().longValue() );

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());

      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplLogicCritDomainDAO logicCritDomainDAO = odsDAOFactory.getTplLogicCritDomainDAO();

      if ( logicCritEntity_.getData().getLogicCritDomInd() == null
           || logicCritEntity_.getData().getLogicCritDomInd().toLowerCase().equals(
                                                                                    "n" ) )
      {
        // Remoção lógica de todos domínios associados
        logicCritDomainDAO.deleteLogicAll( logicCritEntity_.getData().getLogicCritCode() );
      }
      else
      {
        // Remove todos domínios antigos
        logicCritDomainDAO.deleteAll( logicCritEntity_.getData().getLogicCritCode() );

        // Adiciona todos domínios recentes
        Iterator logicCritDomainEntities = logicCritEntity_.getLogicCritDomainEntities().iterator();
        BaseTplLogicCritDomainEntity logicCritDomainEntity;

        while ( logicCritDomainEntities.hasNext() )
        {
          logicCritDomainEntity = ( BaseTplLogicCritDomainEntity ) logicCritDomainEntities.next();
          logicCritDomainDAO.insert( logicCritDomainEntity );
        }
      }
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#delete(com.citibank.ods.entity.pl.BaseTplLogicCritEntity)
   */
  public void delete( BaseTplLogicCritEntity logicCritEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "UPDATE " );
      query.append( C_TPL_LOGIC_CRIT );
      query.append( " SET " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ? " );
      query.append( " WHERE " + C_LOGIC_CRIT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setTimestamp(
                              1,
                              new Timestamp(
                                             logicCritEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString( 2, logicCritEntity_.getData().getRecStatCode() );

      preparedStatement.setString( 3, logicCritEntity_.getData().getLastUpdUserId() );

      preparedStatement.setLong(
                         4,
                         logicCritEntity_.getData().getLogicCritCode().longValue() );

      preparedStatement.execute();
	  preparedStatement.replaceParametersInQuery(query.toString());

      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplLogicCritDomainDAO logicCritDomainDAO = odsDAOFactory.getTplLogicCritDomainDAO();
      // Remoção lógica de todos domínios associados
      logicCritDomainDAO.deleteLogicAll( logicCritEntity_.getData().getLogicCritCode() );
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#list(java.math.BigInteger,
   *      java.lang.String)
   */
  public DataSet list( BigInteger logicCritCode_, String logicCritText_ )
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
      query.append( C_LOGIC_CRIT_CODE + ", " );
      query.append( C_LOGIC_CRIT_TEXT + ", " );
      query.append( C_DATA_TYPE_CODE + ", " );
      query.append( C_LOAD_PROG_UPD_IND + " AS INDICATOR_CODE " + ", " );
      query.append( C_LOGIC_CRIT_DOM_IND );
      query.append( " FROM " );
      query.append( C_TPL_LOGIC_CRIT );

      String criteria = "";
      if ( logicCritCode_ != null )
      {
        criteria = criteria + C_LOGIC_CRIT_CODE + " = ? AND ";
      }

      if ( logicCritText_ != null && !logicCritText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_LOGIC_CRIT_TEXT
                   + "\") LIKE ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " WHERE " + criteria );
        query.append( " AND " + C_REC_STAT_CODE + " <> ? " );
      }
      else
      {
        query.append( " WHERE " );
        query.append( C_REC_STAT_CODE + " <> ? " );
      }

      query.append( " ORDER BY " + C_LOGIC_CRIT_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( logicCritCode_ != null )
      {
        preparedStatement.setLong( count++, logicCritCode_.longValue() );
      }

      if ( logicCritText_ != null && !logicCritText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + logicCritText_.toUpperCase() + "%" );
      }

      preparedStatement.setString( count, "I" );

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

    String[] codeColumn = { "INDICATOR_CODE" };
    String[] nameColumn = { C_INDICATOR_TEXT };

    rsds.outerJoin( ODSConstraintDecoder.decodeIndicador(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#canHasLogicCritDom(com.citibank.ods.entity.pl.BaseTplLogicCritEntity)
   */
  public boolean canHasLogicCritDom( BaseTplLogicCritEntity logicCritEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();

      query.append( "SELECT " );
      query.append( C_LOGIC_CRIT_DOM_IND );
      query.append( " FROM " );
      query.append( C_TPL_LOGIC_CRIT );
      query.append( " WHERE " );
      query.append( C_LOGIC_CRIT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         logicCritEntity_.getData().getLogicCritCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      String logicCritDomInd = "";
      if ( resultSet.next() )
      {
        logicCritDomInd = resultSet.getString( C_LOGIC_CRIT_DOM_IND );
      }

      return "s".equals( logicCritDomInd );
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#find(java.math.BigInteger)
   */
  public BaseTplLogicCritEntity find( BigInteger logicCritCode_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList logicCritEntities;
    BaseTplLogicCritEntity logicCritEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_LOGIC_CRIT_CODE + ", " );
      query.append( C_LOGIC_CRIT_TEXT + ", " );
      query.append( C_DATA_TYPE_CODE + ", " );
      query.append( C_LOGIC_CRIT_DOM_IND + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LOAD_PROG_UPD_IND + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_LOGIC_CRIT );
      query.append( " WHERE " );
      query.append( C_LOGIC_CRIT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, logicCritCode_.longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      logicCritEntities = instantiateFromResultSet( resultSet );

      if ( logicCritEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( logicCritEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        logicCritEntity = ( BaseTplLogicCritEntity ) logicCritEntities.get( 0 );
      }

      return logicCritEntity;
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#findAssociatedLogicCritByPK(java.math.BigInteger,
   *      java.lang.String)
   */
  public ArrayList findAssociatedLogicCritByPK( BigInteger entryInterCode_,
                                               String colName_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList logicCritEntities;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( "LOGIC." + C_LOGIC_CRIT_CODE + ", " );
      query.append( "LOGIC." + C_LOGIC_CRIT_TEXT + ", " );
      query.append( "LOGIC." + C_DATA_TYPE_CODE + ", " );
      query.append( "LOGIC." + C_LOGIC_CRIT_DOM_IND + ", " );
      query.append( "LOGIC." + C_LAST_UPD_USER_ID + ", " );
      query.append( "LOGIC." + C_LAST_UPD_DATE + ", " );
      query.append( "LOGIC." + C_LOAD_PROG_UPD_IND + ", " );
      query.append( "LOGIC." + C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_COL_LOGIC_CRIT + " COLXLOGIC, " );
      query.append( C_TPL_LOGIC_CRIT + " LOGIC " );
      query.append( " WHERE " );
      query.append( "COLXLOGIC." + "ENTRY_INTER_CODE = ? AND " );
      query.append( "COLXLOGIC." + "COL_NAME = ? AND " );
      query.append( "COLXLOGIC." + C_REC_STAT_CODE + " = ? AND " );
      query.append( "COLXLOGIC." + C_LOGIC_CRIT_CODE + " = LOGIC."
                    + C_LOGIC_CRIT_CODE );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, entryInterCode_.longValue() );
      preparedStatement.setString( 2, colName_ );
      preparedStatement.setString( 3, "A" );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      logicCritEntities = instantiateFromResultSet( resultSet );

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

    return logicCritEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#updateColLogicCrit(java.util.Date,
   *      java.lang.String, java.lang.String, java.math.BigInteger,
   *      java.math.BigInteger, java.lang.String)
   */
  public void updateColLogicCrit( Date lastUpdDate_, String lastUpdUserId_,
                                 String recStatCode_,
                                 BigInteger logicCritCode_,
                                 BigInteger entryInterCode_, String colName_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " );
      query.append( C_TPL_COL_LOGIC_CRIT );
      query.append( " SET " );
      query.append( C_LAST_UPD_DATE + " = ?," );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_LOGIC_CRIT_CODE + " = ? AND " );
      query.append( "ENTRY_INTER_CODE" + " = ? AND " );
      query.append( "COL_NAME" + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setTimestamp( 1, new Timestamp( lastUpdDate_.getTime() ) );

      preparedStatement.setString( 2, lastUpdUserId_ );

      preparedStatement.setString( 3, recStatCode_ );

      preparedStatement.setLong( 4, logicCritCode_.longValue() );

      preparedStatement.setLong( 5, entryInterCode_.longValue() );

      preparedStatement.setString( 6, colName_ );

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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#insertColLogicCrit(java.util.Date,
   *      java.lang.String, java.lang.String, java.math.BigInteger,
   *      java.math.BigInteger, java.lang.String)
   */
  public void insertColLogicCrit( Date lastUpdDate_, String lastUpdUserId_,
                                 String recStatCode_,
                                 BigInteger logicCritCode_,
                                 BigInteger entryInterCode_, String colName_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_COL_LOGIC_CRIT );
      query.append( " VALUES ( ?, ?, ? , ?, ?, ?)" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, logicCritCode_.longValue() );

      preparedStatement.setLong( 2, entryInterCode_.longValue() );

      preparedStatement.setString( 3, colName_ );

      preparedStatement.setTimestamp( 4, new Timestamp( lastUpdDate_.getTime() ) );

      preparedStatement.setString( 5, lastUpdUserId_ );

      preparedStatement.setString( 6, recStatCode_ );

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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO#existColLogicCrit(java.math.BigInteger,
   *      java.math.BigInteger, java.lang.String)
   */
  public boolean existColLogicCrit( BigInteger logicCritCode_,
                                   BigInteger entryInterCode_, String colName_ )
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
      query.append( C_LOGIC_CRIT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_COL_LOGIC_CRIT );
      query.append( " WHERE " );
      query.append( C_LOGIC_CRIT_CODE + " = ? AND " );
      query.append( "ENTRY_INTER_CODE" + " = ? AND " );
      query.append( "COL_NAME" + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, logicCritCode_.longValue() );

      preparedStatement.setLong( 2, entryInterCode_.longValue() );

      preparedStatement.setString( 3, colName_ );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
      rsds = new ResultSetDataSet( resultSet );

      if ( rsds.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }

      if ( rsds.size() == 0 )
      {
        return false;
      }
      else
      {
        return true;
      }
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