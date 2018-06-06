package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @author acacio.domingos,Apr 10, 2007
 *  
 */

public class OracleTplProdPlayerRoleDAO extends BaseOracleTplProdPlayerRoleDAO
    implements TplProdPlayerRoleDAO
{
  /*
   * Nome da tabela
   */
  private static final String C_TPL_PROD_PLAYER_ROLE = C_PL_SCHEMA
                                                       + "TPL_PROD_PLAYER_ROLE";

  private static final String C_TPL_PRODUCT = C_PL_SCHEMA + "TPL_PRODUCT";

  private static final String C_TPL_PLAYER = C_PL_SCHEMA + "TPL_PLAYER";

  /*
   * Campos específicos da tabela
   */
  private String C_LAST_AUTH_DATE = "LAST_AUTH_DATE";

  private String C_LAST_AUTH_USER_ID = "LAST_AUTH_USER_ID";

  private String C_REC_STAT_CODE = "REC_STAT_CODE";

  private String C_REC_STAT_TEXT = "REC_STAT_TEXT";

  private String C_PROD_NAME = "PROD_NAME";

  private String C_PLYR_ROLE_TYPE_TEXT = "PLYR_ROLE_TYPE_TEXT";

  private String C_PLYR_NAME = "PLYR_NAME";

  /**
   * Insere um novo registro
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO#insert(com.citibank.ods.entity.pl.TplProdPlayerRoleEntity)
   */
  public TplProdPlayerRoleEntity insert(
                                        TplProdPlayerRoleEntity tplProdPlayerRoleEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_PROD_PLAYER_ROLE + " (" );
      query.append( C_PROD_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_PLYR_CNPJ_NBR + ", " );
      query.append( C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_AUTH_USER_ID + ", " );
      query.append( C_LAST_AUTH_DATE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " ) VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdPlayerRoleEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleEntity_.getData().getProdCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProdPlayerRoleEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleEntity_.getData().getSysCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProdPlayerRoleEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdPlayerRoleEntity_.getData().getSysSegCode().longValue() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProdPlayerRoleEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleEntity_.getData().getPlyrRoleTypeCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleEntity_.getData().getPlyrRoleTypeCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( tplProdPlayerRoleEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplProdPlayerRoleEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdPlayerRoleEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      // Casting para Obter os campos especificos da tabela
      TplProdPlayerRoleEntityVO tplProdPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntity_.getData();

      if ( tplProdPlayerRoleEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplProdPlayerRoleEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdPlayerRoleEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplProdPlayerRoleEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProdPlayerRoleEntity_;
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
   * Atualiza um registro
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO#insert(com.citibank.ods.entity.pl.TplProdPlayerRoleEntity)
   */
  public TplProdPlayerRoleEntity update(
                                        TplProdPlayerRoleEntity tplProdPlayerRoleEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_PROD_PLAYER_ROLE + "  SET " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_AUTH_USER_ID + " = ?, " );
      query.append( C_LAST_AUTH_DATE + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ? " );
      query.append( " WHERE " );
      query.append( C_PROD_CODE + " = ? AND " );
      query.append( C_SYS_CODE + " = ? AND " );
      query.append( C_SYS_SEG_CODE + " = ? AND " );
      query.append( C_PLYR_CNPJ_NBR + " = ? AND " );
      query.append( C_PLYR_ROLE_TYPE_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdPlayerRoleEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProdPlayerRoleEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdPlayerRoleEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      // Casting para Obter os campos especificos da tabela
      TplProdPlayerRoleEntityVO tplProdPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntity_.getData();

      if ( tplProdPlayerRoleEntityVO.getLastAuthUserId() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleEntityVO.getLastAuthUserId() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProdPlayerRoleEntityVO.getLastAuthDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdPlayerRoleEntityVO.getLastAuthDate().getTime() ) );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplProdPlayerRoleEntityVO.getRecStatCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleEntityVO.getRecStatCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProdPlayerRoleEntity_.getData().getProdCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleEntity_.getData().getProdCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProdPlayerRoleEntity_.getData().getSysCode() != null )
      {
        preparedStatement.setString( count++,
                             tplProdPlayerRoleEntity_.getData().getSysCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProdPlayerRoleEntity_.getData().getSysSegCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdPlayerRoleEntity_.getData().getSysSegCode().longValue() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      if ( tplProdPlayerRoleEntity_.getData().getPlyrCnpjNbr() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleEntity_.getData().getPlyrCnpjNbr() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }
      if ( tplProdPlayerRoleEntity_.getData().getPlyrRoleTypeCode() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdPlayerRoleEntity_.getData().getPlyrRoleTypeCode() );
      }
      else
      {
        throw new UnexpectedException( C_ERROR_EXECUTING_STATEMENT, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProdPlayerRoleEntity_;
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
   * Retorna um dataset , com os parametros informados
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO#list(java.lang.String,
   *      java.lang.String)
   */
  public DataSet list( String plyrCnpjNbr_, String plyrName_,
                      String plyrRoleTypeCode_, String prodCode_,
                      String sysCode_, String sysSegCode_, String productName_ )
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
      query.append( C_TPL_PROD_PLAYER_ROLE + " " + "PROD_PLAYER_ROLE" );
      query.append( " WHERE " );
      query.append( "PROD_PLAYER." + C_PLYR_CNPJ_NBR + " = " );
      query.append( "PROD_PLAYER_ROLE." + C_PLYR_CNPJ_NBR + ")QTDE_PROD, " );
      query.append( "PLAYER." + C_PLYR_NAME );
      query.append( " FROM " );
      query.append( C_TPL_PROD_PLAYER_ROLE + " PROD_PLAYER, " );
      query.append( C_TPL_PLAYER + " PLAYER, " );
      query.append( C_TPL_PRODUCT + " PRODUCT" );
      query.append( " WHERE " );
      query.append( "PROD_PLAYER." + C_PLYR_CNPJ_NBR );
      query.append( " = PLAYER." + C_PLYR_CNPJ_NBR + " AND " );
      query.append( " PRODUCT." + C_PROD_CODE + " = " );
      query.append( "PROD_PLAYER." + C_PROD_CODE );
      query.append( " AND PROD_PLAYER." + C_REC_STAT_CODE );
      query.append( " != " + "'"
                    + TplProdPlayerRoleEntity.C_REC_STAT_CODE_INACTIVE + "'" );

      String criteria = "";

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        criteria = criteria + " AND PROD_PLAYER." + C_PLYR_CNPJ_NBR + " =  ? ";
      }
      if ( plyrName_ != null && !"".equals( plyrName_ ) )
      {
        criteria = criteria + " AND UPPER(PLAYER." + C_PLYR_NAME + ") LIKE  ? ";
      }
      if ( plyrRoleTypeCode_ != null && !"".equals( plyrRoleTypeCode_ ) )
      {
        criteria = criteria + " AND PROD_PLAYER." + C_PLYR_ROLE_TYPE_CODE
                   + " =  ? ";
      }
      if ( prodCode_ != null && !"".equals( prodCode_ ) )
      {
        criteria = criteria + " AND UPPER(TRIM( PROD_PLAYER." + C_PROD_CODE
                   + ")) =  ? ";
      }
      if ( sysCode_ != null && !"".equals( sysCode_ ) )
      {
        criteria = criteria + " AND UPPER(PROD_PLAYER." + C_SYS_CODE
                   + ") =  ? ";
      }
      if ( sysSegCode_ != null )
      {
        criteria = criteria + " AND UPPER(PROD_PLAYER." + C_SYS_SEG_CODE
                   + ") =  ? ";
      }
      if ( productName_ != null && !"".equals( productName_ ) )
      {
        criteria = criteria + " AND UPPER( PRODUCT." + C_PROD_NAME
                   + ")LIKE  ? ";
      }

      if ( criteria.length() > 0 )
      {
        query.append( criteria );
      }

      query.append( " GROUP BY " );
      query.append( "PLAYER." + C_PLYR_NAME + ", " );
      query.append( "PROD_PLAYER." + C_PLYR_CNPJ_NBR );

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
        preparedStatement.setString( count++, sysCode_.toUpperCase() );
      }
      if ( sysSegCode_ != null && !"".equals( sysSegCode_ ) )
      {
        preparedStatement.setString( count++, sysSegCode_.toUpperCase() );
      }
      if ( productName_ != null && !"".equals( productName_ ) )
      {
        preparedStatement.setString( count++, "%" + productName_.toUpperCase() + "%" );
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
   * Remove um registro
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO#selectByPk(com.citibank.ods.entity.pl.TplPlayerEntity)
   */
  public void delete( String recStatCode_, String plyrCnpjNbr_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_PROD_PLAYER_ROLE );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?" );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( recStatCode_ != null )
      {
        preparedStatement.setString( count++, recStatCode_ );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }
      if ( plyrCnpjNbr_ != null )
      {
        preparedStatement.setString( count++, plyrCnpjNbr_ );
      }
      else
      {
        preparedStatement.setString( count++, null );
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
   * Retorna se um registro possui um relacionamento ativo entre produto e
   * player
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO#existsRelationActive(com.citibank.ods.entity.pl.TplPlayerEntity)
   */
  public boolean existsRelationActive( String plyrCnpjNbr_,
                                      String plyrRoleTypeCode_ )
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
      query.append( C_TPL_PROD_PLAYER_ROLE );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? AND " );
      query.append( C_PLYR_ROLE_TYPE_CODE + " NOT IN ( ? ) AND " );
      query.append( C_REC_STAT_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, plyrCnpjNbr_ );
      preparedStatement.setString( count++, plyrRoleTypeCode_ );
      preparedStatement.setString( count++,
                           BaseTplPlayerRoleEntity.C_REC_STAT_CODE_ACTIVE );
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

  public boolean exists( TplProdPlayerRoleEntity tplProdPlayerRoleEntity_ )
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
      query.append( C_TPL_PROD_PLAYER_ROLE );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? AND " );
      query.append( C_PLYR_ROLE_TYPE_CODE + " = ? AND " );
      query.append( C_PROD_CODE + " = ? AND " );
      query.append( C_SYS_CODE + " = ? AND " );
      query.append( C_SYS_SEG_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      TplProdPlayerRoleEntityVO tplProdPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntity_.getData();

      preparedStatement.setString( count++, tplProdPlayerRoleEntityVO.getPlyrCnpjNbr() );
      preparedStatement.setString( count++,
                           tplProdPlayerRoleEntityVO.getPlyrRoleTypeCode() );
      preparedStatement.setString( count++, tplProdPlayerRoleEntityVO.getProdCode() );
      preparedStatement.setString( count++, tplProdPlayerRoleEntityVO.getSysCode() );
      preparedStatement.setLong( count++,
                         tplProdPlayerRoleEntityVO.getSysSegCode().longValue() );

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
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_PROD_CODE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_SYS_CODE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_SYS_SEG_CODE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_PLYR_CNPJ_NBR + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_PLYR_ROLE_TYPE_CODE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_LAST_UPD_DATE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_LAST_AUTH_USER_ID + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_LAST_AUTH_DATE + ", " );
      query.append( C_TPL_PROD_PLAYER_ROLE + "." + C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PROD_PLAYER_ROLE + " , " );
      query.append( C_TPL_PRODUCT );

      query.append( " WHERE " + C_TPL_PROD_PLAYER_ROLE + "." + C_PROD_CODE );
      query.append( " = " + C_TPL_PRODUCT + "." + C_PROD_CODE );
      query.append( " AND " + C_TPL_PROD_PLAYER_ROLE + "." + C_SYS_CODE );
      query.append( " = " + C_TPL_PRODUCT + "." + C_SYS_CODE );
      query.append( " AND " + C_TPL_PROD_PLAYER_ROLE + "." + C_SYS_SEG_CODE );
      query.append( " = " + C_TPL_PRODUCT + "." + C_SYS_SEG_CODE );
      query.append( " AND " + C_TPL_PROD_PLAYER_ROLE + "." + C_REC_STAT_CODE );
      query.append( " != " + "'"
                    + TplProdPlayerRoleEntity.C_REC_STAT_CODE_INACTIVE + "'" );

      String criteria = "";

      if ( plyrCnpjNbr_ != null && !"".equals( plyrCnpjNbr_ ) )
      {
        criteria = criteria + " AND " + C_TPL_PROD_PLAYER_ROLE + "."
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
    TplProdPlayerRoleEntityVO tplProdPlayerRoleEntityVO;
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    ArrayList tplProdPlayerRoleEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplProdPlayerRoleEntity = new TplProdPlayerRoleEntity();

        tplProdPlayerRoleEntity.getData().setPlyrCnpjNbr(
                                                          resultSet_.getString( C_PLYR_CNPJ_NBR ) );

        tplProdPlayerRoleEntity.getData().setPlyrRoleTypeCode(
                                                               resultSet_.getString( C_PLYR_ROLE_TYPE_CODE ) );

        tplProdPlayerRoleEntity.getData().setProdCode(
                                                       resultSet_.getString( C_PROD_CODE ) );

        tplProdPlayerRoleEntity.getData().setSysCode(
                                                      resultSet_.getString( C_SYS_CODE ) );

        tplProdPlayerRoleEntity.getData().setSysSegCode(
                                                         new BigInteger(
                                                                         resultSet_.getString( C_SYS_SEG_CODE ) ) );

        tplProdPlayerRoleEntity.getData().setLastUpdDate(
                                                          resultSet_.getDate( C_LAST_UPD_DATE ) );

        tplProdPlayerRoleEntity.getData().setLastUpdUserId(
                                                            resultSet_.getString( C_LAST_UPD_USER_ID ) );

        //Casting para a atribuicao das colunas especificas
        tplProdPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntity.getData();

        tplProdPlayerRoleEntityVO.setRecStatCode( resultSet_.getString( C_REC_STAT_CODE ) );

        tplProdPlayerRoleEntityVO.setLastAuthDate( resultSet_.getDate( C_LAST_AUTH_DATE ) );

        tplProdPlayerRoleEntityVO.setLastAuthUserId( resultSet_.getString( C_LAST_AUTH_USER_ID ) );

        tplProdPlayerRoleEntities.add( tplProdPlayerRoleEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return tplProdPlayerRoleEntities;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO#getRoleTypes(java.lang.String,
   *      java.lang.String)
   */
  public ArrayList getRoleTypes( String plyrCnpjNbr_, String plyrRoleTypeCode_ )
  {
    String roleType;
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList listRoleType = new ArrayList();
    String[] splitPlyrRoleTypeCode = plyrRoleTypeCode_.split( "," );

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT DISTINCT " );
      query.append( C_PLYR_ROLE_TYPE_CODE );
      query.append( " FROM " );
      query.append( C_TPL_PROD_PLAYER_ROLE );
      query.append( " WHERE " + C_PLYR_CNPJ_NBR + " = ? AND " );
      query.append( C_REC_STAT_CODE + " = ? AND  " );
      query.append( C_PLYR_ROLE_TYPE_CODE + " NOT IN ( " + plyrRoleTypeCode_
                    + ")" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++, plyrCnpjNbr_ );
      preparedStatement.setString( count++,
                           BaseTplPlayerRoleEntity.C_REC_STAT_CODE_ACTIVE );
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());


      while ( resultSet.next() )
      {
        roleType = resultSet.getString( C_PLYR_ROLE_TYPE_CODE );
        listRoleType.add( ODSConstraintDecoder.decodeRoleType( roleType ) );
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
    return listRoleType;
  }

  /**
   * Consulta dos produtos
   */
  public ArrayList listProduct( String prodCode_, String prodName_ )
  {
    DataSetRow row;
    TplProductEntity tplProductEntity;
    TplProductEntityVO tplProductEntityVO;
    ArrayList result = new ArrayList();
    BigDecimal sysSegCode;

    //Cria uma instancia do DAO de produto para acessar a lista existente
    TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();

    //Passa os resultado da consulta para um arraylist de entities.
    DataSet rds = tplProductDAO.list( prodCode_, null, prodName_, null, null,
                                      null,"" );
    for ( int indexRow = 0; indexRow < rds.size(); indexRow++ )
    {
      row = rds.getRow( indexRow );

      tplProductEntity = new TplProductEntity();
      tplProductEntityVO = ( TplProductEntityVO ) tplProductEntity.getData();

      sysSegCode = row.getBigDecimalByName( C_SYS_SEG_CODE );
      tplProductEntityVO.setProdCode( row.getStringByName( C_PROD_CODE ) );
      tplProductEntityVO.setProdName( row.getStringByName( C_PROD_NAME ) );
      tplProductEntityVO.setSysCode( row.getStringByName( C_SYS_CODE ) );
      tplProductEntityVO.setSysSegCode( BigInteger.valueOf( sysSegCode.longValue() ) );
      tplProductEntityVO.setLastUpdUserId( row.getStringByName( C_LAST_UPD_USER_ID ) );
      tplProductEntityVO.setLastAuthDate( row.getDateByName( C_LAST_AUTH_DATE ) );
      tplProductEntityVO.setLastAuthUserId( row.getStringByName( C_LAST_AUTH_USER_ID ) );
      tplProductEntityVO.setRecStatCode( row.getStringByName( C_REC_STAT_CODE ) );

      result.add( tplProductEntity );
    }

    return result;
  }
}