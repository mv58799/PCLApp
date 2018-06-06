/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.persistence.pl.dao.rdb.oracle;
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
import com.citibank.ods.entity.pl.BaseTplAggrProdPrvtEntity;
import com.citibank.ods.entity.pl.TplAggrProdPrvtEntity;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtEntity;
import com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author leonardo.nakada
 *  
 */
public class OracleTplAggrProdPrvtDAO extends BaseOracleTplAggrProdPrvtDAO
    implements TplAggrProdPrvtDAO
{
  private static final String C_TPL_AGGR_PROD_PRVT = C_PL_SCHEMA
                                                     + "TPL_AGGR_PROD_PRVT";

  /**
   * Lista os Agregadores de Produtos de acordo com o filtro pré-determinado
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO#listProductAggregate(java.lang.String,
   *      java.lang.String)
   */
  public DataSet listProductAggregate( String prvtProdAggrCode_,
                                      String prvtProdAggrText_ )
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
      query.append( C_PRVT_PROD_AGGR_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_IX_CODE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_AGGR_PROD_PRVT );

      String criteria = "";

      criteria = criteria + C_REC_STAT_CODE + " != '"
                 + BaseTplAggrProdPrvtEntity.C_REC_STAT_CODE_INACTIVE + "'"
                 + " AND ";

      if ( prvtProdAggrCode_ != null && !"".equals( prvtProdAggrCode_ ) )
      {
        criteria = criteria + C_PRVT_PROD_AGGR_CODE + " = ? AND ";
      }

      if ( prvtProdAggrText_ != null && !"".equals( prvtProdAggrText_ ) )
      {
        criteria = criteria + "UPPER(\"" + C_PRVT_PROD_AGGR_TEXT
                   + "\") like ? AND ";
      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	WHERE " + criteria + " ORDER BY "
                      + C_PRVT_PROD_AGGR_TEXT + " ASC " + " , "
                      + C_PRVT_PROD_AGGR_CODE );
      }

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( prvtProdAggrCode_ != null && !"".equals( prvtProdAggrCode_ ) )
      {
        preparedStatement.setString( count++, prvtProdAggrCode_ );
      }
      if ( prvtProdAggrText_ != null && !"".equals( prvtProdAggrText_ ) )
      {
        preparedStatement.setString( count++, "%" + prvtProdAggrText_.toUpperCase()
                                      + "%" );
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

    String[] codeColumn = { C_REC_STAT_CODE };
    String[] nameColumn = { C_REC_STAT_TEXT };
    rsds.outerJoin( ODSConstraintDecoder.decodeRecStatus(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * Realiza a consulta por um registro de acordo com os critérios de entrada
   * 
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.BaseTplAggrProdPrvtDAO#find(com.citibank.ods.entity.pl.BaseTplAggrProdPrvtEntity)
   */
  public BaseTplAggrProdPrvtEntity find(
                                        BaseTplAggrProdPrvtEntity baseTplAggrProdPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();

    ArrayList tplAggrProdPrvtEntities;
    BaseTplAggrProdPrvtEntity tplAggrEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( C_PRVT_PROD_AGGR_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_IX_CODE + ", " );
      query.append( C_REC_STAT_CODE );
      query.append( " FROM " );
      query.append( C_TPL_AGGR_PROD_PRVT );
      query.append( " WHERE " );
      query.append( C_PRVT_PROD_AGGR_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setString(
                           1,
                           baseTplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() );

      resultSet = preparedStatement.executeQuery();

	  preparedStatement.replaceParametersInQuery(query.toString());

      tplAggrProdPrvtEntities = instantiateFromResultSet( resultSet );

      if ( tplAggrProdPrvtEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( tplAggrProdPrvtEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        tplAggrEntity = ( BaseTplAggrProdPrvtEntity ) tplAggrProdPrvtEntities.get( 0 );
      }

      return tplAggrEntity;
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
   * Retorna uma coleção de entities a partir do rs
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplAggrProdPrvtEntity tplAggrProdPrvtEntity;
    ArrayList tplAggrProdPrvtEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplAggrProdPrvtEntity = new TplAggrProdPrvtEntity();

        tplAggrProdPrvtEntity.getData().setPrvtProdAggrCode(
                                                             resultSet_.getString( C_PRVT_PROD_AGGR_CODE ) );
        tplAggrProdPrvtEntity.getData().setPrvtProdAggrText(
                                                             resultSet_.getString( C_PRVT_PROD_AGGR_TEXT ) );
        tplAggrProdPrvtEntity.getData().setLastUpdDate(
                                                        resultSet_.getTimestamp( C_LAST_UPD_DATE ) );
        tplAggrProdPrvtEntity.getData().setLastUpdUserID(
                                                          resultSet_.getString( C_LAST_UPD_USER_ID ) );
        tplAggrProdPrvtEntity.getData().setRecStatCode(
                                                        resultSet_.getString( C_REC_STAT_CODE ) );
       
        tplAggrProdPrvtEntity.getData().setIxCode(resultSet_.getString( C_IX_CODE) );
        
        tplAggrProdPrvtEntities.add( tplAggrProdPrvtEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return tplAggrProdPrvtEntities;
  }

  /**
   * Realiza a inserção das informações do registro.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO#insert(com.citibank.ods.entity.pl.TplAggrProdPrvtEntity)
   */
  public TplAggrProdPrvtEntity insert(
                                      TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "INSERT INTO " + C_TPL_AGGR_PROD_PRVT + " (" );
      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( C_PRVT_PROD_AGGR_TEXT + ", " );
      query.append( C_LAST_UPD_DATE + ", " );
      query.append( C_LAST_UPD_USER_ID + ", " );
      query.append( C_REC_STAT_CODE + ", " );
      query.append( C_IX_CODE );
      query.append( ") VALUES ( " );
      query.append( "?, ?, ?, ?, ?, ? )" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() != null
           && tplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplAggrProdPrvtEntity_.getData().getPrvtProdAggrText() != null
           && tplAggrProdPrvtEntity_.getData().getPrvtProdAggrText() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtEntity_.getData().getPrvtProdAggrText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplAggrProdPrvtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplAggrProdPrvtEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplAggrProdPrvtEntity_.getData().getLastUpdUserID() != null
           && tplAggrProdPrvtEntity_.getData().getLastUpdUserID() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtEntity_.getData().getLastUpdUserID() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplAggrProdPrvtEntity_.getData().getRecStatCode() != null
           && tplAggrProdPrvtEntity_.getData().getRecStatCode() != "" )
      {
        preparedStatement.setString( count++,
                             tplAggrProdPrvtEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }


      
      if ( tplAggrProdPrvtEntity_.getData().getIxCode() != null
              && tplAggrProdPrvtEntity_.getData().getIxCode() != "" )
         {
           preparedStatement.setString( count++,
                                tplAggrProdPrvtEntity_.getData().getIxCode() );
         }
         else
         {
           preparedStatement.setString( count++, "" );
         }
      
      preparedStatement.executeUpdate();

	  preparedStatement.replaceParametersInQuery(query.toString());

      return ( TplAggrProdPrvtEntity ) find( tplAggrProdPrvtEntity_ );

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
   * Realiza a atualização das informações do registro selecionado.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO#update(com.citibank.ods.entity.pl.TplAggrProdPrvtEntity)
   */
  public void update( TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "UPDATE " + C_TPL_AGGR_PROD_PRVT );
      query.append( " SET " );
      query.append( C_PRVT_PROD_AGGR_TEXT + " = ?, " );
      query.append( C_LAST_UPD_DATE + " = ?, " );
      query.append( C_LAST_UPD_USER_ID + " = ?, " );
      query.append( C_REC_STAT_CODE + " = ?, " );
      query.append( C_IX_CODE + " = ?" );
      query.append( " WHERE " );
      query.append( C_PRVT_PROD_AGGR_CODE + "= ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( tplAggrProdPrvtEntity_.getData().getPrvtProdAggrText() != null
           && tplAggrProdPrvtEntity_.getData().getPrvtProdAggrText() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtEntity_.getData().getPrvtProdAggrText() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplAggrProdPrvtEntity_.getData().getLastUpdDate() != null )
      {
        preparedStatement.setTimestamp(
                                count++,
                                new Timestamp(
                                               tplAggrProdPrvtEntity_.getData().getLastUpdDate().getTime() ) );
      }
      else
      {
        preparedStatement.setTimestamp( count++, null );
      }

      if ( tplAggrProdPrvtEntity_.getData().getLastUpdUserID() != null
           && tplAggrProdPrvtEntity_.getData().getLastUpdUserID() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtEntity_.getData().getLastUpdUserID() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplAggrProdPrvtEntity_.getData().getRecStatCode() != null
           && tplAggrProdPrvtEntity_.getData().getRecStatCode() != "" )
      {
        preparedStatement.setString( count++,
                             tplAggrProdPrvtEntity_.getData().getRecStatCode() );
      }
      else
      {
        preparedStatement.setString( count++, "" );
      }

      if ( tplAggrProdPrvtEntity_.getData().getIxCode() != null
              && tplAggrProdPrvtEntity_.getData().getIxCode() != "" )
         {
           preparedStatement.setString( count++,
                                tplAggrProdPrvtEntity_.getData().getIxCode() );
         }
         else
         {
           preparedStatement.setNull( count++, java.sql.Types.VARCHAR);
         }
      
      if ( tplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() != null
           && tplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() != "" )
      {
        preparedStatement.setString(
                             count++,
                             tplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() );
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
   * Realiza a remoção do registro selecionado.
   * 
   * @see com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO#delete(com.citibank.ods.entity.pl.TplAggrProdPrvtEntity)
   */
  public void delete( TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( " UPDATE " + C_TPL_AGGR_PROD_PRVT );
      query.append( " SET " );
      query.append( C_REC_STAT_CODE + "= ?," );
      query.append( C_LAST_UPD_DATE + "= ?," );
      query.append( C_LAST_UPD_USER_ID + "= ?, " );
      query.append( C_IX_CODE + "= ? " );
      query.append( " WHERE " + C_PRVT_PROD_AGGR_CODE + " = ? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      preparedStatement.setString( count++,
                           tplAggrProdPrvtEntity_.getData().getRecStatCode() );

      preparedStatement.setTimestamp(
                              count++,
                              new Timestamp(
                                             tplAggrProdPrvtEntity_.getData().getLastUpdDate().getTime() ) );

      preparedStatement.setString( count++,
                           tplAggrProdPrvtEntity_.getData().getLastUpdUserID() );

      preparedStatement.setString(
                           count++,
                           tplAggrProdPrvtEntity_.getData().getPrvtProdAggrCode() );

      preparedStatement.setString(
              count++,
              tplAggrProdPrvtEntity_.getData().getIxCode() );
      
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO#exists(com.citibank.ods.entity.pl.TplAggrProdPrvtEntity)
   */
  public boolean exists( TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      this.find( tplAggrProdPrvtEntity_ );
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO#existsActive(com.citibank.ods.entity.pl.TplAggrProdPrvtEntity)
   */
  public boolean existsActive( TplAggrProdPrvtEntity tplAggrProdPrvtEntity_ )
  {
    boolean exists = true;

    try
    {
      TplAggrProdPrvtEntity prodPrvtEntity = ( TplAggrProdPrvtEntity ) this.find( tplAggrProdPrvtEntity_ );
      if ( !TplAggrProdPrvtEntity.C_REC_STAT_CODE_ACTIVE.equals( prodPrvtEntity.getData().getRecStatCode() ) )
      {
        exists = false;
      }
    }
    catch ( NoRowsReturnedException exception )
    {
      exists = false;
    }

    return exists;
  }

  
  
  
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
      query.append( C_PRVT_PROD_AGGR_CODE + ", " );
      query.append( C_PRVT_PROD_AGGR_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_AGGR_PROD_PRVT );      
      query.append( " WHERE " );
      query.append( C_REC_STAT_CODE + " <> ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      preparedStatement.setString( count++,
                           TplProductFamilyPrvtEntity.C_REC_STAT_CODE_INACTIVE );

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

  

  
  public DataSet loadIndexAggrs()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT DISTINCT NOM_INTERNO_IFI IX_CODE, NOM_IFI NOME FROM COMMON.IFI ORDER BY NOME" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

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

  
}