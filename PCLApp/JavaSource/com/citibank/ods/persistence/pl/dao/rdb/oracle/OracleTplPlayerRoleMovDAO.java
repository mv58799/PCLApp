package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplPlayerRoleMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.persistence.pl.dao.rdb.oracle;
 * @version 1.0
 * @author acacio.domingos,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class OracleTplPlayerRoleMovDAO extends BaseOracleTplPlayerRoleDAO
    implements TplPlayerRoleMovDAO
{
  /*
   * Nome da tabela
   *  
   */
  private static final String C_TPL_PLAYER_ROLE_MOV = C_PL_SCHEMA + "TPL_PLAYER_ROLE_MOV";

  /*
   * Campos específicos da tabela
   */
  private String C_OPERN_CODE = "OPERN_CODE";

  protected String C_OPERN_TEXT = "OPERN_TEXT";

  /**
   * Este metodo retorna uma lista de players que se enquadre com os criterios
   * informados
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleMovDAO#list(java.lang.String,
   *      java.lang.String, java.lang.String)
   */
  public DataSet list( String plyrCnpjNbr_, String plyrName_,
                      String lastUpdUserId_ )
  {
    return null;
  }

  /**
   * Este metodo insere um novo registro
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleMovDAO#insert(com.citibank.ods.entity.pl.TplPlayerRoleMovEntity)
   */
  public TplPlayerRoleMovEntity insert(
                                       TplPlayerRoleMovEntity tplPlayerRoleMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PLAYER_ROLE_MOV + " ( " );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE );
      query.append( " ) VALUES ( " );
      query.append( "?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      /*
       * Casting para obter os campos especificos da tabela
       */

      TplPlayerRoleMovEntityVO tplPlayerRoleMovEntityVO = ( TplPlayerRoleMovEntityVO ) tplPlayerRoleMovEntity_.getData();

      if ( tplPlayerRoleMovEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleMovEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleMovEntity_.getData().getPlyrRoleTypeCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplPlayerRoleMovEntity_.getData().getPlyrRoleTypeCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleMovEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplPlayerRoleMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplPlayerRoleMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplPlayerRoleMovEntityVO.getOpernCode() != null )
      {
        preparedStatement.setString( count++, tplPlayerRoleMovEntityVO.getOpernCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplPlayerRoleMovEntity_;

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

  }

  /**
   * Remove Fisicamente um registro
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleMovDAO#update(com.citibank.ods.entity.pl.TplPlayerRoleMovEntity)
   */
  public TplPlayerRoleMovEntity delete(
                                       TplPlayerRoleMovEntity tplPlayerRoleMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " DELETE FROM  " + C_TPL_PLAYER_ROLE_MOV );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplPlayerRoleMovEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString( count++,
                             tplPlayerRoleMovEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplPlayerRoleMovEntity_;
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
  }

  /**
   * Remove Fisicamente um registro
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleMovDAO#update(com.citibank.ods.entity.pl.TplPlayerRoleMovEntity)
   */
  public void delete( String plyrCnpjNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " DELETE FROM  " + C_TPL_PLAYER_ROLE_MOV );
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
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
  }

  /**
   * Verifica se existe um registro com o código passado
   * @see com.citibank.ods.persistence.pl.dao.TplPlayerRoleMovDAO#exists(com.citibank.ods.entity.pl.TplPlayerRoleMovEntity)
   */
  public boolean exists( TplPlayerRoleMovEntity tplPlayerRoleMovEntity_ )
  {
    return false;
  }

  /**
   * Este método busca uma player que se enquadre com os critérios passados
   * @see com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO#find(com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity)
   */
  public BaseTplPlayerRoleEntity find(
                                      BaseTplPlayerRoleEntity baseTplPlayerRoleEntity_ )
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
      query.append( C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PLAYER_ROLE_MOV );

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
      playerRoleEntities = instantiateFromResultSet( resultSet );

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

    return playerRoleEntities;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplPlayerRoleMovEntity tplPlayerRoleMovEntity;
    TplPlayerRoleMovEntityVO tplPlayerRoleMovEntityVO;
    ArrayList tplPlayerRoleEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplPlayerRoleMovEntity = new TplPlayerRoleMovEntity();

        tplPlayerRoleMovEntity.getData().setPlyrCnpjNbr(
                                                         resultSet_.getString( C_PLYR_CNPJ_NBR ) );

        tplPlayerRoleMovEntity.getData().setPlyrRoleTypeCode(
                                                              resultSet_.getString( C_PLYR_ROLE_TYPE_CODE ) );

        tplPlayerRoleMovEntity.getData().setLastUpdDate(
                                                         resultSet_.getTimestamp( C_LAST_UPD_DATE ) );

        tplPlayerRoleMovEntity.getData().setLastUpdUserId(
                                                           resultSet_.getString( C_LAST_UPD_USER_ID ) );

        //Casting para a atribuicao das colunas especificas

        tplPlayerRoleMovEntityVO = ( TplPlayerRoleMovEntityVO ) tplPlayerRoleMovEntity.getData();

        tplPlayerRoleMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplPlayerRoleEntities.add( tplPlayerRoleMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplPlayerRoleEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO#selectByPlyr(java.lang.String)
   */
  public DataSet selectByPlyr( String plyrCnpjNbr_ )
  {
    return null;
  }

}