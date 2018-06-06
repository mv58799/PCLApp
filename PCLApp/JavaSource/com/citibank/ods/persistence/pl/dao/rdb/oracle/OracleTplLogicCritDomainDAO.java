package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplLogicCritDomainEntity;
import com.citibank.ods.entity.pl.TplLogicCritDomainEntity;
import com.citibank.ods.entity.pl.valueobject.TplLogicCritDomainEntityVO;
import com.citibank.ods.persistence.pl.dao.TplLogicCritDomainDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de domínios de
 * crítica lógica
 */
public class OracleTplLogicCritDomainDAO extends
    BaseOracleTplLogicCritDomainDAO implements TplLogicCritDomainDAO
{
  // Tabela TPL_LOGIC_CRIT_DOMAIN
  private static final String C_TPL_LOGIC_CRIT_DOMAIN = C_PL_SCHEMA
                                                        + "TPL_LOGIC_CRIT_DOMAIN";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDomainDAO#findDomainsByCriteriaPK(java.math.BigInteger)
   */
  public ArrayList findDomainsByCriteriaPK( BigInteger logicCritCode_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList logicCritDomainEntities;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_DOMAIN_CNTNT_TEXT + ", " );
      query.append( C_DOMAIN_SEQ_NBR + ", " );
      query.append( C_LOAD_PROG_UPD_IND + ", " );
      query.append( C_LOGIC_CRIT_CODE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_LOGIC_CRIT_DOMAIN );
      query.append( " WHERE " );
      query.append( C_LOGIC_CRIT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, logicCritCode_.longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      logicCritDomainEntities = instantiateFromResultSet( resultSet );

      return logicCritDomainEntities;
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

    TplLogicCritDomainEntityVO logicCritDomainEntityVO;
    TplLogicCritDomainEntity logicCritDomainEntity;
    ArrayList logicCritDomainEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        logicCritDomainEntity = new TplLogicCritDomainEntity();
        logicCritDomainEntityVO = ( TplLogicCritDomainEntityVO ) logicCritDomainEntity.getData();

        logicCritDomainEntityVO.setDomainCntntText( resultSet_.getString( this.C_DOMAIN_CNTNT_TEXT ) );
        logicCritDomainEntityVO.setDomainSeqNbr( new BigInteger(
                                                                 resultSet_.getString( this.C_DOMAIN_SEQ_NBR ) ) );
        logicCritDomainEntityVO.setLoadProgUpdInd( resultSet_.getString( this.C_LOAD_PROG_UPD_IND ) );
        logicCritDomainEntityVO.setLogicCritCode( new BigInteger(
                                                                  resultSet_.getString( this.C_LOGIC_CRIT_CODE ) ) );
        logicCritDomainEntityVO.setRecStatCode( resultSet_.getString( this.C_REC_STAT_CODE ) );

        logicCritDomainEntities.add( logicCritDomainEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    
    return logicCritDomainEntities;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDomainDAO#insert(com.citibank.ods.entity.pl.BaseTplLogicCritDomainEntity)
   */
  public void insert( BaseTplLogicCritDomainEntity logicCritDomainEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_LOGIC_CRIT_DOMAIN + " ( " );
      query.append( C_LOGIC_CRIT_CODE + ", " );
      query.append( C_DOMAIN_SEQ_NBR + ", " );
      query.append( C_REC_STAT_CODE );

      int attCount = 0;
      if ( logicCritDomainEntity_.getData().getDomainCntntText() != null
           && !logicCritDomainEntity_.getData().getDomainCntntText().equals( "" ) )
      {
        query.append( ", " + C_DOMAIN_CNTNT_TEXT );
        attCount++;
      }

      if ( logicCritDomainEntity_.getData().getLoadProgUpdInd() != null
           && !logicCritDomainEntity_.getData().getLoadProgUpdInd().equals( "" ) )
      {
        query.append( ", " + C_LOAD_PROG_UPD_IND );
        attCount++;
      }

      query.append( " ) " );

      query.append( "VALUES ( ?, ?, ? " );

      for ( int i = 0; i < attCount; i++ )
      {
        query.append( ", ? " );
      }

      query.append( ") " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         logicCritDomainEntity_.getData().getLogicCritCode().longValue() );

      preparedStatement.setLong(
                         2,
                         logicCritDomainEntity_.getData().getDomainSeqNbr().longValue() );

      preparedStatement.setString( 3, logicCritDomainEntity_.getData().getRecStatCode() );

      int index = 4;

      if ( logicCritDomainEntity_.getData().getDomainCntntText() != null
           && !logicCritDomainEntity_.getData().getDomainCntntText().equals( "" ) )
      {
        preparedStatement.setString(
                             index++,
                             logicCritDomainEntity_.getData().getDomainCntntText() );
      }

      if ( logicCritDomainEntity_.getData().getLoadProgUpdInd() != null
           && !logicCritDomainEntity_.getData().getLoadProgUpdInd().equals( "" ) )
      {
        preparedStatement.setString(
                             index,
                             logicCritDomainEntity_.getData().getLoadProgUpdInd() );
      }

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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDomainDAO#deleteAll(java.math.BigInteger)
   */
  public void deleteAll( BigInteger logicCritCode_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " + C_TPL_LOGIC_CRIT_DOMAIN );
      query.append( " WHERE " );
      query.append( C_LOGIC_CRIT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, logicCritCode_.longValue() );

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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDomainDAO#deleteLogicAll(java.math.BigInteger)
   */
  public void deleteLogicAll( BigInteger logicCritCode_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {

      connection = OracleODSDAOFactory.getConnection();

      query.append( "UPDATE " );
      query.append( C_TPL_LOGIC_CRIT_DOMAIN );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " + C_LOGIC_CRIT_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString( 1, "I" );
      preparedStatement.setLong( 2, logicCritCode_.longValue() );

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

  public Integer getNextLogicCritDomain()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    Integer nextVal = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT PL.SQ_LOGIC_CRIT_DOMAIN_SEQ_NBR.NEXTVAL FROM DUAL " );

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
}