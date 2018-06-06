/*
 * Created on Abr 12, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.entity.pl.BaseTplProdPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
/**
 * @author acacio.domingos
 *  
 */
public class OracleTplProdPlayerRoleMovDAO extends
    BaseOracleTplProdPlayerRoleDAO implements TplProdPlayerRoleMovDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TPL_PROD_PLAYER_ROLE_MOV = C_PL_SCHEMA
                                                           + "TPL_PROD_PLAYER_ROLE_MOV";

  private static final String C_TPL_PRODUCT = C_PL_SCHEMA + "TPL_PRODUCT";

  private static final String C_TPL_PLAYER = C_PL_SCHEMA + "TPL_PLAYER";

  /*
   * Campos específicos da tabela
   */
  private String C_OPERN_CODE = "OPERN_CODE";

  private String C_OPERN_TEXT = "OPERN_TEXT";

  private String C_PROD_NAME = "PROD_NAME";

  private String C_PLYR_ROLE_TYPE_TEXT = "PLYR_ROLE_TYPE_TEXT";

  private String C_PLYR_NAME = "PLYR_NAME";

  /**
   * Insere um novo registro
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO#insert(com.citibank.ods.entity.pl.TplProdPlayerRoleEntity)
   */
  public TplProdPlayerRoleMovEntity insert(
                                           TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PROD_PLAYER_ROLE_MOV + " (" );
      query.append( C_PROD_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_OPERN_CODE );
      query.append( " ) VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdPlayerRoleMovEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleMovEntity_.getData().getProdCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProdPlayerRoleMovEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleMovEntity_.getData().getSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProdPlayerRoleMovEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdPlayerRoleMovEntity_.getData().getSysSegCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleMovEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleMovEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleMovEntity_.getData().getPlyrRoleTypeCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleMovEntity_.getData().getPlyrRoleTypeCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleMovEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdPlayerRoleMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      //    Casting para Obter os campos especificos da tabela

      TplProdPlayerRoleMovEntityVO tplProdPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity_.getData();
      if ( tplProdPlayerRoleMovEntityVO.getopernCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleMovEntityVO.getopernCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProdPlayerRoleMovEntity_;
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
   * Retorna um Dataset de acordo com os parametros informados
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO#list(java.lang.String,
   *      java.lang.String, java.lang.String, java.lang.String,
   *      java.lang.String, java.lang.String, java.lang.String)
   */
  public DataSet list( String plyrCnpjNbr_, String plyrName_,
                      String plyrRoleTypeCode_, String prodCode_,
                      String sysCode_, String sysSegCode_, String productName_,
                      String lastUpdUserId_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " SELECT " );
      query.append( "PROD_PLAYER." + C_PLYR_CNPJ_NBR + ", " );
      query.append( "(SELECT COUNT(*) FROM " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + " PROD_PLAYER_ROLE" );
      query.append( " WHERE " );
      query.append( "PROD_PLAYER." + C_PLYR_CNPJ_NBR + " = " );
      query.append( "PROD_PLAYER_ROLE." + C_PLYR_CNPJ_NBR + ")QTDE_PROD, " );
      query.append( "PLAYER." + C_PLYR_NAME + ", " );
      query.append( "PROD_PLAYER." + C_LAST_UPD_USER_ID );
      query.append( " FROM " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + " PROD_PLAYER, " );
      query.append( C_TPL_PLAYER + " PLAYER, " );
      query.append( C_TPL_PRODUCT + " PRODUCT" );
      query.append( " WHERE " );
      query.append( "PROD_PLAYER." + C_PLYR_CNPJ_NBR );
      query.append( " = PLAYER." + C_PLYR_CNPJ_NBR + " AND " );
      query.append( " PRODUCT." + C_PROD_CODE + " = " );
      query.append( "PROD_PLAYER." + C_PROD_CODE );

      String criteria = "";

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        criteria = criteria + " AND PROD_PLAYER." + C_PLYR_CNPJ_NBR + " = "
                   + " ? ";
      }
      if ( plyrName_ != null && !"".equals( plyrName_ ) )
      {
        criteria = criteria + " AND UPPER(PLAYER." + C_PLYR_NAME + ") LIKE ? ";
      }
      if ( plyrRoleTypeCode_ != null && !"".equals( plyrRoleTypeCode_ ) )
      {
        criteria = criteria + " AND PROD_PLAYER." + C_PLYR_ROLE_TYPE_CODE
                   + " = " + " ? ";
      }
      if ( prodCode_ != null && !"".equals( prodCode_ ) )
      {
        criteria = criteria + " AND UPPER(TRIM(PROD_PLAYER." + C_PROD_CODE
                   + ")) = ? ";
      }
      if ( sysCode_ != null && !"".equals( sysCode_ ) )
      {
        criteria = criteria + " AND UPPER(PROD_PLAYER." + C_SYS_CODE + ") = ? ";
      }
      if ( sysSegCode_ != null )
      {
        criteria = criteria + " AND UPPER(PROD_PLAYER." + C_SYS_SEG_CODE
                   + ") = ? ";
      }
      if ( productName_ != null && !"".equals( productName_ ) )
      {
        criteria = criteria + " AND UPPER(PRODUCT." + C_PROD_NAME
                   + " ) LIKE ? ";
      }
      if ( lastUpdUserId_ != null && !"".equals( lastUpdUserId_ ) )
      {
        criteria = criteria + " AND UPPER(PROD_PLAYER." + C_LAST_UPD_USER_ID
                   + ") LIKE ? ";
      }

      if ( criteria.length() > 0 )
      {
        query.append( criteria );
      }

      query.append( " GROUP BY " );
      query.append( "PROD_PLAYER." + C_PLYR_CNPJ_NBR + ", " );
      query.append( "PLAYER." + C_PLYR_NAME + ", " );
      query.append( "PROD_PLAYER." + C_LAST_UPD_USER_ID );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        preparedStatement.setString( count++, plyrCnpjNbr_ );
      }
      if ( plyrName_ != null && !"".equals( plyrName_ ) )
      {
        preparedStatement.setString( count++, "%" + plyrName_.toUpperCase() + "%" );
      }
      if ( plyrRoleTypeCode_ != null && !"".equals( plyrRoleTypeCode_ ) )
      {
        preparedStatement.setString( count++, plyrRoleTypeCode_ );
      }
      if ( prodCode_ != null && !"".equals( prodCode_ ) )
      {
        preparedStatement.setString( count++, prodCode_.toUpperCase() );
      }
      if ( sysCode_ != null && !"".equals( sysCode_ ) )
      {
        preparedStatement.setString( count++, "%" + sysCode_.toUpperCase() + "%" );
      }
      if ( sysSegCode_ != null && !"".equals( sysSegCode_ ) )
      {
        preparedStatement.setString( count++, sysSegCode_.toUpperCase() );
      }
      if ( productName_ != null && !"".equals( productName_ ) )
      {
        preparedStatement.setString( count++, "%" + productName_.toUpperCase() + "%" );
      }
      if ( lastUpdUserId_ != null && !"".equals( lastUpdUserId_ ) )
      {
        preparedStatement.setString( count++, "%" + lastUpdUserId_.toUpperCase() + "%" );
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
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
    return rsds;
  }

  /**
   * Apaga um registro fisicamente
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO#delete(com.citibank.ods.entity.pl.TplPlayerEntity)
   */
  public void delete( String plyrCnpjNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV );
      query.append( " WHERE " );
      query.append( C_PLYR_CNPJ_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;
      if ( plyrCnpjNbr_ != null && plyrCnpjNbr_.equals( plyrCnpjNbr_ ) )
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
   * Verifica se já existe um relacionamento entre produto e player
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO#existsRelation(com.citibank.ods.entity.pl.TplPlayerEntity)
   */
  public boolean existsRelation( String plyrCnpjNbr_, String plyrRoleTypeCode_ )
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
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV );
      query.append( "WHERE " + C_PLYR_CNPJ_NBR + " = ? AND " );
      query.append( C_PLYR_ROLE_TYPE_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, plyrCnpjNbr_ );
      preparedStatement.setString( count++, plyrRoleTypeCode_ );
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      if ( resultSet.getInt( 0 ) != 0 )
      {
        return true;
      }
      else
      {
        return false;
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
   * Procura um registro de acordo com os parametros informados
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdPlayerRoleDAO#find(com.citibank.ods.entity.pl.BaseTplProdPlayerRoleEntity)
   */
  public BaseTplProdPlayerRoleEntity find(
                                          BaseTplProdPlayerRoleEntity baseTplProdPlayerRoleEntity_ )
  {

    return null;
  }

  /**
   * Verifica se já existe um relacionamento entre produto e player
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO#existsRelation(com.citibank.ods.entity.pl.TplPlayerEntity)
   */
  public boolean exists( String plyrCnpjNbr_ )
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
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      if ( plyrCnpjNbr_ != null && !plyrCnpjNbr_.equals( "" ) )
      {
        preparedStatement.setString( 1, plyrCnpjNbr_ );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
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
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplProdPlayerRoleDAO#selectByPlyr(java.lang.String)
   */
  public ArrayList selectByPlyr( String plyrCnpjNbr_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList prodPlayerRoleEntities;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_PROD_CODE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_SYS_CODE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_SYS_SEG_CODE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_PLYR_CNPJ_NBR + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_PLYR_ROLE_TYPE_CODE
                    + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_LAST_UPD_USER_ID
                    + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PROD_PLAYER_ROLE_MOV + " , " );
      query.append( C_TPL_PRODUCT );

      query.append( " WHERE " + C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_PROD_CODE );
      query.append( " = " + C_TPL_PRODUCT + "." + C_PROD_CODE );
      query.append( " AND " + C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_SYS_CODE );
      query.append( " = " + C_TPL_PRODUCT + "." + C_SYS_CODE );
      query.append( " AND " + C_TPL_PROD_PLAYER_ROLE_MOV + "." + C_SYS_SEG_CODE );
      query.append( " = " + C_TPL_PRODUCT + "." + C_SYS_SEG_CODE );

      String criteria = "";

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        criteria = criteria + " AND " + C_TPL_PROD_PLAYER_ROLE_MOV + "."
                   + C_PLYR_CNPJ_NBR + " = " + " ? ";
      }

      if ( criteria.length() > 0 )
      {
        query.append( criteria );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        preparedStatement.setString( count++, plyrCnpjNbr_ );
      }

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      prodPlayerRoleEntities = instantiateFromResultSet( resultSet );

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

    return prodPlayerRoleEntities;
  }

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    ArrayList tplProdPlayerRoleEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity = new TplProdPlayerRoleMovEntity();

        tplProdPlayerRoleMovEntity.getData().setPlyrCnpjNbr(
                                                             resultSet_.getString( C_PLYR_CNPJ_NBR ) );

        tplProdPlayerRoleMovEntity.getData().setPlyrRoleTypeCode(
                                                                  resultSet_.getString( C_PLYR_ROLE_TYPE_CODE ) );

        tplProdPlayerRoleMovEntity.getData().setProdCode(
                                                          resultSet_.getString( C_PROD_CODE ) );

        tplProdPlayerRoleMovEntity.getData().setSysCode(
                                                         resultSet_.getString( C_SYS_CODE ) );

        tplProdPlayerRoleMovEntity.getData().setSysSegCode(
                                                            new BigInteger(
                                                                            resultSet_.getString( C_SYS_SEG_CODE ) ) );

        tplProdPlayerRoleMovEntity.getData().setLastUpdDate(
                                                             resultSet_.getTimestamp( C_LAST_UPD_DATE ) );

        tplProdPlayerRoleMovEntity.getData().setLastUpdUserId(
                                                               resultSet_.getString( C_LAST_UPD_USER_ID ) );

        //Casting para a atribuicao das colunas especificas
        TplProdPlayerRoleMovEntityVO tplProdPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity.getData();

        tplProdPlayerRoleMovEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplProdPlayerRoleEntities.add( tplProdPlayerRoleMovEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplProdPlayerRoleEntities;
  }

}