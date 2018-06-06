/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplAggrProdPrvtEntity;
import com.citibank.ods.entity.pl.TplAggrProdPrvtHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplAggrProdPrvtHistEntityVO;
import com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplAggrProdPrvtHistDAO extends BaseOracleTplAggrProdPrvtDAO
    implements TplAggrProdPrvtHistDAO
{
  /*
   * Constante do nome da coluna da tabela de data de referência da tabela de
   * histórico
   */
  private String C_PRVT_PROD_AGGR_REF_DATE = "PRVT_PROD_AGGR_REF_DATE";

  /*
   * Constante que identifica o nome da tabel de histórico
   */
  private static final String C_TPL_AGGR_PROD_PRVT_HIST = C_PL_SCHEMA
                                             + "TPL_AGGR_PROD_PRVT_HIST";

  /**
   * Inicializa a tela para histórico de um agregador de produto
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtHistDAO#listProductAggregateHistory(java.lang.String,
   *      java.util.Date)
   */
  public DataSet listProductAggregateHistory( String prvtProdAggrCode_,
                                             String prvtProdAggrText_,
                                             Date prvtProdAggrRefDate_ )
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
      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( C_PRVT_PROD_AGGR_REF_DATE + ", " );
      query.append( C_PRVT_PROD_AGGR_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + "," );
      query.append( C_IX_CODE + "," );
      query.append( " FROM " );
      query.append( C_TPL_AGGR_PROD_PRVT_HIST );

      String criteria = "";

      if ( prvtProdAggrCode_ != null && !"".equals( prvtProdAggrCode_ ) )
      {
        criteria = criteria + C_PRVT_PROD_AGGR_CODE + " = ? AND ";
      }
      if ( prvtProdAggrText_ != null && !"".equals( prvtProdAggrText_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PRVT_PROD_AGGR_TEXT
                   + "\") like ? AND ";
      }
      if ( prvtProdAggrRefDate_ != null )
      {
        criteria = criteria + "TRUNC(" + C_PRVT_PROD_AGGR_REF_DATE
                   + ") >= ? AND ";
      }
      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY "
                      + C_PRVT_PROD_AGGR_TEXT + " ASC, " + " to_date( "
                      + C_PRVT_PROD_AGGR_REF_DATE + ", 'DD/MM/YYYY') DESC ,"
                      + C_PRVT_PROD_AGGR_CODE );
      }
      else
      {
        query.append( " ORDER BY " + C_PRVT_PROD_AGGR_TEXT + " ASC, "
                      + "to_date( " + C_PRVT_PROD_AGGR_REF_DATE
                      + ", 'DD/MM/YYYY') DESC ," + C_PRVT_PROD_AGGR_CODE );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prvtProdAggrCode_ != null && !"".equals( prvtProdAggrCode_ ) )
      {
        preparedStatement.setString( count++, prvtProdAggrCode_ );
      }
      if ( prvtProdAggrText_ != null && !"".equals( prvtProdAggrText_ ) )
      {
        preparedStatement.setString( count++, "%" + prvtProdAggrText_.toUpperCase() + "%" );
      }
      if ( prvtProdAggrRefDate_ != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new java.sql.Timestamp(
                                                        prvtProdAggrRefDate_.getTime() ) );
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

    String[] codeColumn = { C_REC_STAT_CODE };
    String[] nameColumn = { C_REC_STAT_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * Realiza a consulta de registros de acordo com um filtro pré-determinado.
   * 
   * @see com.citibank.ods.persistence.pl.dao.BaseTplAggrProdPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplAggrProdPrvtEntity)
   */
  public BaseTplAggrProdPrvtEntity find(
                                        BaseTplAggrProdPrvtEntity baseTplAggrProdPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplAggrProdPrvtHistEntities;
    BaseTplAggrProdPrvtEntity tplAggrHistEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( C_PRVT_PROD_AGGR_REF_DATE + ", " );
      query.append( C_PRVT_PROD_AGGR_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ", ");
      query.append( C_IX_CODE );
      query.append( " FROM " );
      query.append( C_TPL_AGGR_PROD_PRVT_HIST );
      query.append( " WHERE " );
      query.append( C_PRVT_PROD_AGGR_CODE + " = ? AND " );
      query.append( " TRUNC (" + C_PRVT_PROD_AGGR_REF_DATE + ") = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString(
                           1,
                           baseTplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() );
      TplAggrProdPrvtHistEntityVO hstVO = ( TplAggrProdPrvtHistEntityVO ) baseTplAggrProdPrvtEntity_.getData();
      preparedStatement.setTimestamp(
                              2,
                              new java.sql.Timestamp(
                                                      hstVO.getPrvtProdAggrRefDate().getTime() ) );

      resultSet = preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

      tplAggrProdPrvtHistEntities = instantiateFromResultSet( resultSet );

      if ( tplAggrProdPrvtHistEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplAggrProdPrvtHistEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplAggrHistEntity = ( BaseTplAggrProdPrvtEntity ) tplAggrProdPrvtHistEntities.get( 0 );
      }

      return tplAggrHistEntity;
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
    ArrayList tplAggrProdPrvtEntities = new ArrayList();
    TplAggrProdPrvtHistEntity tplAggrProdPrvtEntity;

    try
    {
      while ( resultSet_.next() )
      {
        tplAggrProdPrvtEntity = new TplAggrProdPrvtHistEntity();

        tplAggrProdPrvtEntity.getData().setPrvtProdAggrCode(
                                                             resultSet_.getString( C_PRVT_PROD_AGGR_CODE ) );
        ( ( TplAggrProdPrvtHistEntityVO ) tplAggrProdPrvtEntity.getData() ).setPrvtProdAggrRefDate( resultSet_.getTimestamp( C_PRVT_PROD_AGGR_REF_DATE ) );
        tplAggrProdPrvtEntity.getData().setPrvtProdAggrText(
                                                             resultSet_.getString( C_PRVT_PROD_AGGR_TEXT ) );
        tplAggrProdPrvtEntity.getData().setLastUpdDate(
                                                        resultSet_.getDate( C_LAST_UPD_DATE ) );
        tplAggrProdPrvtEntity.getData().setLastUpdUserID(
                                                          resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplAggrProdPrvtEntity.getData().setRecStatCode(
                                                        resultSet_.getString( C_REC_STAT_CODE ) );

        tplAggrProdPrvtEntity.getData().setIxCode(
                resultSet_.getString( C_IX_CODE ) );
        
        tplAggrProdPrvtEntities.add( tplAggrProdPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplAggrProdPrvtEntities;
  }

  /**
   * Realiza a inserção das informações do registro de histórico.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO#insert(com.citibank.ods.entity.pl.TplAggrProdPrvtEntity)
   */
  public TplAggrProdPrvtHistEntity insert(
                                          TplAggrProdPrvtHistEntity tplAggrProdPrvtHistEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();
    
    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_AGGR_PROD_PRVT_HIST + " (" );
      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( C_PRVT_PROD_AGGR_REF_DATE + ", " );
      query.append( C_PRVT_PROD_AGGR_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + "," );
      query.append( C_IX_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplAggrProdPrvtHistEntity_.getData().getPrvtProdAggrCode() != null
           && !"".equals( tplAggrProdPrvtHistEntity_.getData().getPrvtProdAggrCode() ) )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtHistEntity_.getData().getPrvtProdAggrCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( ( ( TplAggrProdPrvtHistEntityVO ) tplAggrProdPrvtHistEntity_.getData() ).getPrvtProdAggrRefDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               ( ( TplAggrProdPrvtHistEntityVO ) tplAggrProdPrvtHistEntity_.getData() ).getPrvtProdAggrRefDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplAggrProdPrvtHistEntity_.getData().getPrvtProdAggrText() != null
           && !"".equals( tplAggrProdPrvtHistEntity_.getData().getPrvtProdAggrText() ) )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtHistEntity_.getData().getPrvtProdAggrText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplAggrProdPrvtHistEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplAggrProdPrvtHistEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplAggrProdPrvtHistEntity_.getData().getLastUpdUserID() != null
           && !"".equals( tplAggrProdPrvtHistEntity_.getData().getLastUpdUserID() ) )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtHistEntity_.getData().getLastUpdUserID() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplAggrProdPrvtHistEntity_.getData().getRecStatCode() != null
           && !"".equals( tplAggrProdPrvtHistEntity_.getData().getRecStatCode() ) )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtHistEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      
      if ( tplAggrProdPrvtHistEntity_.getData().getIxCode() != null
              && !"".equals( tplAggrProdPrvtHistEntity_.getData().getIxCode() ) )
         {
           preparedStatement.setString(
                                count++,
                                tplAggrProdPrvtHistEntity_.getData().getIxCode() );
         }
         else
         {
           preparedStatement.setString( count++, "" );
         }
      
      preparedStatement.executeUpdate();
      
	  preparedStatement.replaceParametersInQuery(query.toString());

      return tplAggrProdPrvtHistEntity_;

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
}