package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplEntryInterColumnEntity;
import com.citibank.ods.entity.pl.BaseTplEntryInterEntity;
import com.citibank.ods.entity.pl.TplEntryInterEntity;
import com.citibank.ods.entity.pl.TplLogicCritEntity;
import com.citibank.ods.entity.pl.valueobject.TplEntryInterEntityVO;
import com.citibank.ods.persistence.pl.dao.TplEntryInterDAO;
import com.citibank.ods.persistence.pl.dao.TplLogicCritDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Interface de
 * Entrada.
 */
public class OracleTplEntryInterDAO extends BaseOracleTplEntryInterDAO
    implements TplEntryInterDAO
{
  // Tabela TPL_ENTRY_INTER_COLUMN
  private static final String C_TPL_ENTRY_INTER_COLUMN = C_PL_SCHEMA
                                            + "TPL_ENTRY_INTER_COLUMN";

  // Tabela TPL_ENTRY_INTER
  private static final String C_TPL_ENTRY_INTER = C_PL_SCHEMA + "TPL_ENTRY_INTER";

  private String C_DATA_TYPE_TEXT = "DATA_TYPE_TEXT";

  /**
   * @see com.citibank.ods.persistence.pl.dao.BaseTplEntryInterDAO#list(java.math.BigInteger)
   */
  public DataSet list( BigInteger entryInterCode_ )
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
      query.append( C_COL_NAME + ", " );
      query.append( C_COL_NAME_TEXT + ", " );
      query.append( C_COL_DATA_TYPE_CODE + " AS DATA_TYPE_CODE " + ", " );
      query.append( C_COL_SIZE + ", " );
      query.append( C_COL_PRCSN_NBR );
      query.append( " FROM " );
      query.append( C_TPL_ENTRY_INTER_COLUMN );
      query.append( " WHERE " );
      query.append( C_ENTRY_INTER_CODE + " = ?" );
      query.append( " ORDER BY " + C_COL_NAME + ", " + C_COL_NAME_TEXT );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, entryInterCode_.longValue() );

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
      query.append( "SELECT " );
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
   * @see com.citibank.ods.persistence.pl.dao.BaseTplEntryInterColumnDAO#find(java.math.BigInteger,
   *      java.math.BigInteger,
   *      com.citibank.ods.entity.pl.BaseTplEntryInterColumnEntity)
   */
  public BaseTplEntryInterEntity find(
                                      BigInteger entryInterCode_,
                                      BigInteger entryTypeCode_,
                                      BaseTplEntryInterColumnEntity entryInterColumnEntity_ )
  {
    TplLogicCritEntity logicCritEntity;
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList entryInterEntities;
    BaseTplEntryInterEntity entryInterEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( "INTER." + C_ENTRY_INTER_TEXT + ", " );
      query.append( "INTER." + C_ENTRY_TYPE_CODE + ", " );
      query.append( "INTER." + C_ORIG_FILE_NAME );
      query.append( " FROM " );
      query.append( C_TPL_ENTRY_INTER + " INTER " );
      query.append( " WHERE " );
      query.append( "INTER." + C_ENTRY_INTER_CODE + " = ? AND " );
      query.append( "INTER." + C_ENTRY_TYPE_CODE + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, entryInterCode_.longValue() );
      preparedStatement.setLong( 2, entryTypeCode_.longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      entryInterEntities = instantiateFromResultSet( resultSet );

      if ( entryInterEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( entryInterEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        entryInterEntity = ( BaseTplEntryInterEntity ) entryInterEntities.get( 0 );
      }

      // Cria mapa com as críticas associadas ao atributo
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplLogicCritDAO logicCritDAO = odsDAOFactory.getTplLogicCritDAO();
      ArrayList logicCritEntitiesArray = logicCritDAO.findAssociatedLogicCritByPK(
                                                                                   entryInterCode_,
                                                                                   entryInterColumnEntity_.getData().getColName() );

      Iterator logicCritEntitiesIt = logicCritEntitiesArray.iterator();
      HashMap tplLogicCritEntities = new HashMap();
      while ( logicCritEntitiesIt.hasNext() )
      {
        logicCritEntity = ( TplLogicCritEntity ) logicCritEntitiesIt.next();
        tplLogicCritEntities.put( logicCritEntity.getData().getLogicCritCode(),
                                  logicCritEntity );
      }

      entryInterColumnEntity_.setTplLogicCritEntities( tplLogicCritEntities );

      return entryInterEntity;
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
    TplEntryInterEntity entryInterEntity;
    TplEntryInterEntityVO entryInterEntityVO;
    ArrayList entryInterEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        entryInterEntity = new TplEntryInterEntity();
        entryInterEntityVO = ( TplEntryInterEntityVO ) entryInterEntity.getData();

        entryInterEntityVO.setEntryInterText( resultSet_.getString( this.C_ENTRY_INTER_TEXT ) );
        entryInterEntityVO.setEntryTypeCode( new BigInteger(
                                                             resultSet_.getString( this.C_ENTRY_TYPE_CODE ) ) );
        entryInterEntityVO.setOrigFileName( resultSet_.getString( this.C_ORIG_FILE_NAME ) );

        entryInterEntities.add( entryInterEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }

    return entryInterEntities;
  }
}