/*
 * Created on Apr 5, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplPlayerRoleEntity;
import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleEntityVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author acacio.domingos
 *  
 */
public class OracleTplPlayerRoleDAO extends BaseOracleTplPlayerRoleDAO
    implements TplPlayerRoleDAO
{

  /*
   * Nome da tabela
   */
  private static final String C_TPL_PLAYER_ROLE = C_PL_SCHEMA + "TPL_PLAYER_ROLE";

  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  private String C_PLYR_ROLE_TYPE_CODE = "PLYR_ROLE_TYPE_CODE";

  private String C_PLYR_ROLE_TYPE_TEXT = "PLYR_ROLE_TYPE_TEXT";

  /**
   * Este método insere um novo registro de um player
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerDAO#insert(com.citibank.ods.entity.pl.TplPlayerEntity)
   */

  public TplPlayerRoleEntity insert( TplPlayerRoleEntity tplPlayerRoleEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PLAYER_ROLE + " (" );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( ") VALUES  " );
      query.append( "( ?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( tplPlayerRoleEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerRoleEntity_.getData().getPlyrRoleTypeCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplPlayerRoleEntity_.getData().getPlyrRoleTypeCode() );

      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerRoleEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerRoleEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerRoleEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      /*
       * Casting para Obter os campos especificos
       */

      TplPlayerRoleEntityVO tplPlayerRoleEntityVO = ( TplPlayerRoleEntityVO ) tplPlayerRoleEntity_.getData();

      if ( tplPlayerRoleEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerRoleEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplPlayerRoleEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplPlayerRoleEntityVO.getLastAuthUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplPlayerRoleEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplPlayerRoleEntityVO.getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplPlayerRoleEntity_;
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
  }

  /**
   * Remove fisicamente o registro solicitado
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleDAO#delete(com.citibank.ods.entity.pl.TplPlayerRoleEntity)
   */
  public void delete( String plyrCnpjNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " DELETE FROM  " + C_TPL_PLAYER_ROLE );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( plyrCnpjNbr_ != null && !plyrCnpjNbr_.equals( "" ) )
      {
        preparedStatement.setString( 1, plyrCnpjNbr_ );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
  }

  /**
   * Remove logicamente um registro de player
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleDAO#inactivate(com.citibank.ods.entity.pl.TplPlayerRoleEntity)
   */
  public void inactivate( String plyrCnpjNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_PLAYER_ROLE );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++,
                           TplPlayerRoleEntity.C_REC_STAT_CODE_INACTIVE );

      if ( plyrCnpjNbr_ != null && !plyrCnpjNbr_.equals( "" ) )
      {
        preparedStatement.setString( count++, plyrCnpjNbr_ );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
  }

  /**
   * Este método busca um Player que se enquadre com os critérios informados
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO#find(com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity)
   */
  public BaseTplPlayerRoleEntity find(
                                      BaseTplPlayerRoleEntity baseTplPlayerRoleEntity )
  {
    return null;
  }

  /**
   * Recupera papéis de player que estejam relacionados com o CNPJ passado
   */
  public ArrayList selectByPk( String plyrCnpjNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList playerRoleEntities = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PLAYER_ROLE );

      String criteria = "";

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        criteria = criteria + C_PLYR_CNPJ_NBR + " = ? AND ";
        criteria = criteria + C_REC_STAT_CODE + "!= '"
                   + TplPlayerRoleEntity.C_REC_STAT_CODE_INACTIVE + "'";
        query.append( "	WHERE " + criteria );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        preparedStatement.setString( 1, plyrCnpjNbr_ );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
      playerRoleEntities = instantiateFromResultSet( resultSet );

      resultSet.close();
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return playerRoleEntities;
  }

  /**
   * Recupera papéis de player que estejam relacionados com o CNPJ passado
   */
  public DataSet selectByPlyr( String plyrCnpjNbr_ )
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
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PLAYER_ROLE );

      String criteria = "";

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        criteria = criteria + C_PLYR_CNPJ_NBR + " = ? ";
        query.append( "	WHERE " + criteria );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        preparedStatement.setString( 1, plyrCnpjNbr_ );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
      rsds = new ResultSetDataSet( resultSet );

      resultSet.close();
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    String[] codeColumn = { C_PLYR_ROLE_TYPE_CODE };
    String[] nameColumn = { C_PLYR_ROLE_TYPE_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeRoleType(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplPlayerRoleEntity tplPlayerRoleEntity;
    TplPlayerRoleEntityVO tplPlayerRoleEntityVO;
    ArrayList tplPlayerRoleEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplPlayerRoleEntity = new TplPlayerRoleEntity();

        tplPlayerRoleEntity.getData().setPlyrCnpjNbr(
                                                      resultSet_.getString( C_PLYR_CNPJ_NBR ) );

        tplPlayerRoleEntity.getData().setPlyrRoleTypeCode(
                                                           resultSet_.getString( C_PLYR_ROLE_TYPE_CODE ) );

        tplPlayerRoleEntity.getData().setLastUpdDate(
                                                      resultSet_.getDate( C_LAST_UPD_DATE ) );

        tplPlayerRoleEntity.getData().setLastUpdUserId(
                                                        resultSet_.getString( C_LAST_UPD_USER_ID ) );

        //Casting para a atribuicao das colunas especificas
        tplPlayerRoleEntityVO = ( TplPlayerRoleEntityVO ) tplPlayerRoleEntity.getData();

        tplPlayerRoleEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        tplPlayerRoleEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );

        tplPlayerRoleEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );

        tplPlayerRoleEntities.add( tplPlayerRoleEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplPlayerRoleEntities;
  }

  /**
   * Retorna se existe um registro com os criterios passados
   */
  public boolean exists( TplPlayerRoleEntity tplPlayerRoleEntity_ )
  {
    
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT COUNT(*)" );
      query.append( " FROM " );
      query.append( C_TPL_PLAYER_ROLE );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? AND " );
      query.append( C_PLYR_ROLE_TYPE_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplPlayerRoleEntity_.getData().getPlyrCnpjNbr() != null
           && !tplPlayerRoleEntity_.getData().getPlyrCnpjNbr().equals( "" ) )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleEntity_.getData().getPlyrCnpjNbr() );
      }

      if ( tplPlayerRoleEntity_.getData().getPlyrRoleTypeCode() != null
           && !tplPlayerRoleEntity_.getData().getPlyrRoleTypeCode().equals( "" ) )
      {
        preparedStatement.setString(
                             count++,
                             tplPlayerRoleEntity_.getData().getPlyrRoleTypeCode() );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.next() )
      {
        if ( resultSet.getInt( 1 ) != 0 )
        {
          return true;
        }
        else
        {
          return false;
        }
      }
      else
      {
        return false;
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }    
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleDAO#update(com.citibank.ods.entity.pl.TplPlayerRoleEntity)
   */
  public TplPlayerRoleEntity activate( TplPlayerRoleEntity tplPlayerRoleEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PLAYER_ROLE + "  SET " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_PLYR_CNPJ_NBR + " = ? AND " );
      query.append( C_PLYR_ROLE_TYPE_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplPlayerRoleEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerRoleEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      // Casting para Obter os campos especificos da tabela
      TplPlayerRoleEntityVO tplPlayerRoleEntityVO = ( TplPlayerRoleEntityVO ) tplPlayerRoleEntity_.getData();

      if ( tplPlayerRoleEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++, tplPlayerRoleEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerRoleEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++, tplPlayerRoleEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleEntity_.getData().getPlyrRoleTypeCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplPlayerRoleEntity_.getData().getPlyrRoleTypeCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

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

    return tplPlayerRoleEntity_;
  }

}