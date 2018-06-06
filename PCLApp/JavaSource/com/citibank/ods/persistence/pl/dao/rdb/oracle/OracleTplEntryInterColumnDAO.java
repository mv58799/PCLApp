package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplEntryInterColumnEntity;
import com.citibank.ods.entity.pl.TplEntryInterColumnEntity;
import com.citibank.ods.entity.pl.valueobject.TplEntryInterColumnEntityVO;
import com.citibank.ods.persistence.pl.dao.TplEntryInterColumnDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;
import com.citibank.ods.persistence.util.CitiStatement;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Interface de
 * Entrada de Colunas.
 */
public class OracleTplEntryInterColumnDAO extends
    BaseOracleTplEntryInterColumnDAO implements TplEntryInterColumnDAO
{
  // Tabela TPL_ENTRY_INTER_COLUMN
  private static final String C_TPL_ENTRY_INTER_COLUMN = C_PL_SCHEMA
                                            + "TPL_ENTRY_INTER_COLUMN";

  // Tabela TPL_ENTRY_INTER
  private static final String C_TPL_ENTRY_INTER = C_PL_SCHEMA + "TPL_ENTRY_INTER";

  private String C_DATA_TYPE_TEXT = "DATA_TYPE_TEXT";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplEntryInterColumnDAO#list(java.math.BigInteger,
   *      java.lang.String, java.lang.String)
   */
  public DataSet list( String entryInterText_, String colName_,
                      String colText_ )
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
      query.append( "INTER." + C_ENTRY_INTER_CODE + ", " );
      query.append( "INTER." + C_ENTRY_INTER_TEXT + ", " );
      query.append( "INTER." + C_ENTRY_TYPE_CODE + ", " );
      query.append( "COL." + C_COL_NAME + ", " );
      query.append( "COL." + C_COL_NAME_TEXT + ", " );
      query.append( "COL." + C_COL_PRCSN_NBR + ", " );
      query.append( "COL." + C_COL_SIZE + ", " );
      query.append( "COL." + C_COL_DATA_TYPE_CODE + " AS DATA_TYPE_CODE" );
      query.append( " FROM " );
      query.append( C_TPL_ENTRY_INTER_COLUMN + " COL, " );
      query.append( C_TPL_ENTRY_INTER + " INTER " );
      query.append( " WHERE " );
      query.append( "INTER." + C_ENTRY_INTER_CODE + " = " );
      query.append( "COL." + C_ENTRY_INTER_CODE );

      String criteria = "";

      if ( entryInterText_ != null && !entryInterText_.equals(""))
      {
        criteria = criteria + "INTER." + C_ENTRY_INTER_TEXT + " = ?  AND ";
      }

      if ( colName_ != null && !colName_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_COL_NAME + "\") LIKE ? AND ";
      }
      if ( colText_ != null && !colText_.equals( "" ) )
      {
        criteria = criteria + "UPPER(\"" + C_COL_NAME_TEXT + "\") LIKE ? AND ";
      }

      if ( criteria.length() > 0 )

      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( "	AND " + criteria );
      }

      query.append( " ORDER BY " + C_COL_NAME + " ASC," + C_COL_NAME_TEXT
                    + " ASC" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      int count = 1;

      if ( entryInterText_ != null && !entryInterText_.equals(""))
      {	
        preparedStatement.setString( count++, entryInterText_ );
      }

      if ( colName_ != null && !colName_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + colName_.toUpperCase() + "%" );
      }

      if ( colText_ != null && !colText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + colText_.toUpperCase() + "%" );
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

    String[] codeColumn = { "DATA_TYPE_CODE" };
    String[] nameColumn = { C_DATA_TYPE_TEXT };

    rsds.outerJoin( ODSConstraintDecoder.decodeDataType(), codeColumn,
                    codeColumn, nameColumn );

    return rsds;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplEntryInterColumnDAO#loadColDataTypeCodeDomain()
   */
  public DataSet loadColDataTypeCodeDomain()
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
      query.append( C_COL_DATA_TYPE_CODE );
      query.append( " FROM " );
      query.append( C_TPL_ENTRY_INTER_COLUMN );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
      resultSet.close();
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException (e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return rsds;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplEntryInterColumnDAO#loadEntryTypeCodeDomain()
   */
  public DataSet loadEntryTypeCodeDomain()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT DISTINCT " );
      query.append( C_ENTRY_TYPE_CODE );
      query.append( " FROM " );
      query.append( C_TPL_ENTRY_INTER );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
      resultSet.close();
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException (e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return rsds;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplEntryInterColumnDAO#loadEntryInterCodeDomain()
   */
  public DataSet loadEntryInterCodeDomain()
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT DISTINCT " );
      query.append( "INTER." + C_ENTRY_INTER_TEXT );
      query.append( ", INTER." + C_ENTRY_INTER_TEXT );
      query.append( " FROM " );
      query.append( C_TPL_ENTRY_INTER + " INTER, " );
      query.append( C_TPL_ENTRY_INTER_COLUMN + " COL " );
      query.append( " WHERE " );
      query.append( "INTER." + C_ENTRY_INTER_CODE + " = " );
      query.append( "COL." + C_ENTRY_INTER_CODE );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));
      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      rsds = new ResultSetDataSet( resultSet );
      resultSet.close();
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException (e.getErrorCode(), C_ERROR_EXECUTING_STATEMENT, e);
    }
    finally
    {
      closeStatement( preparedStatement );
      closeConnection( connection );
    }

    return rsds;
  }

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplEntryInterDAO#find(java.math.BigInteger,
   *      java.lang.String)
   */
  public BaseTplEntryInterColumnEntity find( BigInteger entryInterCode_,
                                            String colName_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList entryInterColumnEntities;
    BaseTplEntryInterColumnEntity entryInterColumnEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( "COL." + C_COL_NAME + ", " );
      query.append( "COL." + C_COL_NAME_TEXT + ", " );
      query.append( "COL." + C_COL_DATA_TYPE_CODE + ", " );
      query.append( "COL." + C_COL_SIZE + ", " );
      query.append( "COL." + C_COL_PRCSN_NBR );
      query.append( " FROM " );
      query.append( C_TPL_ENTRY_INTER_COLUMN + " COL " );
      query.append( " WHERE " );
      query.append( "COL." + C_ENTRY_INTER_CODE + " = ? AND " );
      query.append( "COL." + C_COL_NAME + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, entryInterCode_.longValue() );
      preparedStatement.setString( 2, colName_ );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());
	  

      entryInterColumnEntities = instantiateFromResultSet( resultSet );

      if ( entryInterColumnEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( entryInterColumnEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        entryInterColumnEntity = ( BaseTplEntryInterColumnEntity ) entryInterColumnEntities.get( 0 );
      }

      return entryInterColumnEntity;
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
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {
    
    TplEntryInterColumnEntity entryInterColumnEntity;
    TplEntryInterColumnEntityVO entryInterColumnEntityVO;
    ArrayList entryInterColumnEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        entryInterColumnEntity = new TplEntryInterColumnEntity();
        entryInterColumnEntityVO = ( TplEntryInterColumnEntityVO ) entryInterColumnEntity.getData();

        if ( resultSet_.getString( C_COL_NAME ) != null )
        {
          entryInterColumnEntityVO.setColName( resultSet_.getString( C_COL_NAME ) );
        }

        if ( resultSet_.getString( C_COL_NAME_TEXT ) != null )
        {
          entryInterColumnEntityVO.setColText( resultSet_.getString( C_COL_NAME_TEXT ) );
        }

        if ( resultSet_.getString( C_COL_DATA_TYPE_CODE ) != null )
        {
          entryInterColumnEntityVO.setColDataTypeCode( resultSet_.getString( C_COL_DATA_TYPE_CODE ) );
        }

        if ( resultSet_.getString( C_COL_SIZE ) != null )
        {

          entryInterColumnEntityVO.setColSize( new BigInteger(
                                                               resultSet_.getString( C_COL_SIZE ) ) );
        }
        if ( resultSet_.getString( C_COL_PRCSN_NBR ) != null )
        {
          entryInterColumnEntityVO.setColPrcsnNbr( new BigInteger(
                                                                   resultSet_.getString( C_COL_PRCSN_NBR ) ) );
        }

        entryInterColumnEntities.add( entryInterColumnEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return entryInterColumnEntities;
  }
}