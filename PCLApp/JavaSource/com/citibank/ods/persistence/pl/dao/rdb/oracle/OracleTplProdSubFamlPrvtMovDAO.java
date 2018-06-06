/*
 * Created on Mar 23, 2007
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
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdSubFamlPrvtMovEntityVO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplProdSubFamlPrvtMovDAO extends
    BaseOracleTplProdSubFamlPrvtDAO implements TplProdSubFamlPrvtMovDAO
{
  /*
   * Campos específicos da tabela
   */
  private static final String C_OPERN_CODE = "OPERN_CODE";

  private static final String C_OPERN_TEXT = "OPERN_TEXT";

  private static final String C_PROD_FAML_NAME = "PROD_FAML_NAME";

  /*
   * Nome da tabela de movimento
   */
  private static final String C_TABLE_NAME = C_PL_SCHEMA + "TPL_PROD_SUB_FAML_PRVT_MOV";

  /*
   * Nome da tabela referenciada - Família
   */
  private String C_REF_TABLE_PRODUCT_FAMILY = C_PL_SCHEMA
                                              + "TPL_PRODUCT_FAMILY_PRVT";

  /**
   * Realiza a consulta de um registro pendente de aprovação
   */
  public DataSet list( BigInteger prodSubFamlCode_, String prodSubFamlName_,
                      String prodSubFamlText_, String lastUpdUserId_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();
    String logicNameSub = "sub";
    String logicNameProd = "prod";

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( logicNameSub + "." + C_PROD_SUB_FAML_CODE + ", " );
      query.append( logicNameSub + "." + C_PROD_SUB_FAML_NAME + ", " );
      query.append( logicNameSub + "." + C_PROD_SUB_FAML_TEXT + ", " );
      query.append( logicNameSub + "." + C_PROD_FAML_CODE + ", " );
      query.append( logicNameSub + "." + C_LAST_UPD_DATE + ", " );
      query.append( logicNameSub + "." + C_LAST_UPD_USER_ID + ", " );
      query.append( logicNameSub + "." + C_OPERN_CODE + ", " );
      query.append( logicNameProd + "." + C_PROD_FAML_NAME );
      query.append( " FROM " );
      query.append( C_TABLE_NAME + " " + logicNameSub );
      query.append( " LEFT JOIN  " );
      query.append( C_REF_TABLE_PRODUCT_FAMILY + " " + logicNameProd );
      query.append( " ON " );
      query.append( logicNameSub + "." + C_PROD_FAML_CODE );
      query.append( " = " );
      query.append( logicNameProd + "." + C_PROD_FAML_CODE );

      String criteria = "";

      if ( prodSubFamlCode_ != null )
      {
        criteria = criteria + C_PROD_SUB_FAML_CODE + " = ? AND ";
      }

      if ( prodSubFamlName_ != null && !"".equals( prodSubFamlName_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_SUB_FAML_NAME
                   + "\") like ? AND ";
      }

      if ( prodSubFamlText_ != null && !"".equals( prodSubFamlText_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PROD_SUB_FAML_TEXT
                   + "\") like ? AND ";
      }

      if ( lastUpdUserId_ != null && !"".equals( lastUpdUserId_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_LAST_UPD_USER_ID
                   + "\") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY "
                      + C_PROD_SUB_FAML_NAME + " ASC " + " , "
                      + C_PROD_SUB_FAML_CODE );
      }
      else
      {
        query.append( " ORDER BY " + C_PROD_SUB_FAML_NAME + " ASC " + " , "
                      + C_PROD_SUB_FAML_CODE );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prodSubFamlCode_ != null )
      {
        preparedStatement.setLong( count++, prodSubFamlCode_.longValue() );
      }

      if ( prodSubFamlName_ != null && !"".equals( prodSubFamlName_ ) )
      {
        preparedStatement.setString( count++, "%" + prodSubFamlName_.toUpperCase() + "%" );
      }

      if ( prodSubFamlText_ != null && !"".equals( prodSubFamlText_ ) )
      {
        preparedStatement.setString( count++, "%" + prodSubFamlText_.toUpperCase() + "%" );
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
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e );
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    String[] codeColumn = { C_OPERN_CODE };
    String[] nameColumn = { C_OPERN_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeOpern(), codeColumn, codeColumn,
                    nameColumn );

    return rsds;
  }

  /**
   * Realiza a atualização de um registro pendente de aprovação
   */
  public void update( TplProdSubFamlPrvtMovEntity tplProductSubFamilyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TABLE_NAME );
      query.append( " SET " );
      query.append( C_PROD_SUB_FAML_NAME + " = ?, " );
      query.append( C_PROD_SUB_FAML_TEXT + " = ?, " );
      query.append( C_PROD_FAML_CODE + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_OPERN_CODE + " = ?" );
      query.append( " WHERE " );
      query.append( C_PROD_SUB_FAML_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlName() );
      }

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductSubFamilyPrvtEntity_.getData().getProdSubFamlText() );
      }

      if ( tplProductSubFamilyPrvtEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProductSubFamilyPrvtEntity_.getData().getProdFamlCode().longValue() );
      }

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             tplProductSubFamilyPrvtEntity_.getData().getLastUpdDate().getTime() ) );

      if ( tplProductSubFamilyPrvtEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProductSubFamilyPrvtEntity_.getData().getLastUpdUserId() );
      }

      TplProdSubFamlPrvtMovEntityVO familyPrvtMovEntityVO = ( TplProdSubFamlPrvtMovEntityVO ) tplProductSubFamilyPrvtEntity_.getData();

      if ( familyPrvtMovEntityVO.getOpernCode() != null )
      {
        preparedStatement.setString( count++, familyPrvtMovEntityVO.getOpernCode() );
      }

      if ( familyPrvtMovEntityVO.getProdSubFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           familyPrvtMovEntityVO.getProdSubFamlCode().longValue() );
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
  }

  /**
   * Realiza a remoção de um registro pendente de aprovação
   */
  public void delete( TplProdSubFamlPrvtMovEntity productSubFamilyPrvtMovEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "DELETE FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_PROD_SUB_FAML_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         productSubFamilyPrvtMovEntity_.getData().getProdSubFamlCode().longValue() );

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
   * Verifica a existência de um registro pendente de aprovação
   */
  public boolean exists(
                        TplProdSubFamlPrvtMovEntity productSubFamilyPrvtMovEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( productSubFamilyPrvtMovEntity_ );
    }
    catch ( NoRowsReturnedException e )
    {
      exists = false;
    }

    return exists;
  }

  /**
   * Recupera um registro pendente de aprovação
   */
  public BaseTplProdSubFamlPrvtEntity find(
                                           BaseTplProdSubFamlPrvtEntity baseTplProductFamilyPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplProductFamilyPrvtEntities;
    BaseTplProdSubFamlPrvtEntity baseTplProductSubFamilyPrvtEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_SUB_FAML_NAME + ", " );
      query.append( C_PROD_SUB_FAML_TEXT + ", " );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE );
      query.append( " FROM " );
      query.append( C_TABLE_NAME );
      query.append( " WHERE " );
      query.append( C_PROD_SUB_FAML_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         baseTplProductFamilyPrvtEntity_.getData().getProdSubFamlCode().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      tplProductFamilyPrvtEntities = instantiateFromResultSet( resultSet );

      if ( tplProductFamilyPrvtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplProductFamilyPrvtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        baseTplProductSubFamilyPrvtEntity = ( BaseTplProdSubFamlPrvtEntity ) tplProductFamilyPrvtEntities.get( 0 );
      }

      return baseTplProductSubFamilyPrvtEntity;
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

  /*
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    TplProdSubFamlPrvtMovEntity productFamilyPrvtEntity;
    TplProdSubFamlPrvtMovEntityVO familyPrvtEntityVO;
    ArrayList tplProductFamilyPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        productFamilyPrvtEntity = new TplProdSubFamlPrvtMovEntity();

        productFamilyPrvtEntity.getData().setProdSubFamlCode(
                                                              new BigInteger(
                                                                              resultSet_.getString( C_PROD_SUB_FAML_CODE ) ) );
        productFamilyPrvtEntity.getData().setProdSubFamlName(
                                                              resultSet_.getString( C_PROD_SUB_FAML_NAME ) );
        productFamilyPrvtEntity.getData().setProdSubFamlText(
                                                              resultSet_.getString( C_PROD_SUB_FAML_TEXT ) );

        productFamilyPrvtEntity.getData().setProdFamlCode(
                                                           new BigInteger(
                                                                           resultSet_.getString( C_PROD_FAML_CODE ) ) );

        productFamilyPrvtEntity.getData().setLastUpdDate(
                                                          resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        productFamilyPrvtEntity.getData().setLastUpdUserId(
                                                            resultSet_.getString( C_LAST_UPD_USER_ID ) );
        // Casting para a atribuicao das colunas especificas
        familyPrvtEntityVO = ( TplProdSubFamlPrvtMovEntityVO ) productFamilyPrvtEntity.getData();
        familyPrvtEntityVO.setOpernCode( resultSet_.getString( C_OPERN_CODE ) );

        tplProductFamilyPrvtEntities.add( productFamilyPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplProductFamilyPrvtEntities;
  }

  /**
   * Realiza a inserção de um registro de movimenro sub-família de produtos
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtMovDAO#insert(com.citibank.ods.entity.pl.TplProdSubFamlPrvtMovEntity)
   */
  public TplProdSubFamlPrvtMovEntity insert(
                                            TplProdSubFamlPrvtMovEntity tplProdSubFamlPrvtMovEntity_ )
  {

    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TABLE_NAME + " (" );
      query.append( C_PROD_SUB_FAML_CODE + ", " );
      query.append( C_PROD_SUB_FAML_NAME + ", " );
      query.append( C_PROD_SUB_FAML_TEXT + ", " );
      query.append( C_PROD_FAML_CODE + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_OPERN_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplProdSubFamlPrvtMovEntity_.getData().getProdSubFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdSubFamlPrvtMovEntity_.getData().getProdSubFamlCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplProdSubFamlPrvtMovEntity_.getData().getProdSubFamlName() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdSubFamlPrvtMovEntity_.getData().getProdSubFamlName() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProdSubFamlPrvtMovEntity_.getData().getProdSubFamlText() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdSubFamlPrvtMovEntity_.getData().getProdSubFamlText() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      if ( tplProdSubFamlPrvtMovEntity_.getData().getProdFamlCode() != null )
      {
        preparedStatement.setLong(
                           count++,
                           tplProdSubFamlPrvtMovEntity_.getData().getProdFamlCode().longValue() );
      }
      else
      {
        preparedStatement.setLong( count++, 0 );
      }

      if ( tplProdSubFamlPrvtMovEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplProdSubFamlPrvtMovEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplProdSubFamlPrvtMovEntity_.getData().getLastUpdUserId() != null )
      {
        preparedStatement.setString(
                             count++,
                             tplProdSubFamlPrvtMovEntity_.getData().getLastUpdUserId() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      TplProdSubFamlPrvtMovEntityVO prodSubFamlPrvtMovEntityVO = ( TplProdSubFamlPrvtMovEntityVO ) tplProdSubFamlPrvtMovEntity_.getData();

      if ( prodSubFamlPrvtMovEntityVO.getOpernCode() != null )
      {
        preparedStatement.setString( count++, prodSubFamlPrvtMovEntityVO.getOpernCode() );
      }
      else
      {
        preparedStatement.setString( count++, null );
      }

      preparedStatement.executeUpdate();
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplProdSubFamlPrvtMovEntity_;

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

/* (non-Javadoc)
 * @see com.citibank.ods.persistence.pl.dao.BaseTplProdSubFamlPrvtDAO#findByProdCode(java.lang.String)
 */
 public BaseTplProdSubFamlPrvtEntity findByProdCode(String prodPk) {
	// TODO Auto-generated method stub
	return null;
}
}