package com.citibank.ods.common.dataset;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Implementação de DataSet que é carregado com valores de um ResultSet. <br>
 * Os dados do ResultSet que serão carregados no DataSet serão todos os dados
 * disponíveis no ResultSet. <br>
 * Ou seja, se o ResultSet estiver sido consumido parcialmente, o DataSet será
 * carregado com os dados restantes. Ou, se o ResultSet tiver sido totalmente
 * consumido, o DataSet será criado com o mapa de colunas, entretanto, sem
 * nenhuma linha.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class ResultSetDataSet extends DataSet implements Serializable
{

  /**
   * Construtor protegido.
   */
  protected ResultSetDataSet()
  {
    super();
  }

  /**
   * @param resultSet_ - ResultSet que contém os dados para preenchimento do
   *          DataSet.
   */
  public ResultSetDataSet( ResultSet resultSet_ )
  {
    ResultSetMetaData resultSetMetaData = null;

    if ( resultSet_ == null )
    {
      throw new UnexpectedException( "resultSet_ cannot be null." );
    }

    resultSetMetaData = defineColumnMapping( resultSet_ );

    loadRows( resultSet_, resultSetMetaData );
  }

  /**
   * 
   * Monta o mapa de índice de colunas por nome, na sequencia em que são
   * apresentadas no ResultSet.
   * 
   * @param resultSet_ - ResultSet que contém a meta-informação das colunas
   *          obtidas pela consulta.
   * @return Meta-informação do ResultSet.
   */
  protected ResultSetMetaData defineColumnMapping( ResultSet resultSet_ )
  {
    ResultSetMetaData resultSetMetaData = null;
    try
    {
      resultSetMetaData = resultSet_.getMetaData();
      for ( int i = 0; i < resultSetMetaData.getColumnCount(); i++ )
      {
        m_columnMapping.put(
                             resultSetMetaData.getColumnName( i + 1 ).toUpperCase(),
                             new Integer( i ) );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), "Error reading resultSetMetaData.", e );
    }
    return resultSetMetaData;
  }

  /**
   * 
   * Carrega no DataSet as linhas obtidas no ResultSet.
   * 
   * @param resultSet_ - ResultSet que contempla o resultado da pesquisa.
   * @param resultSetMetaData_ - Meta-informação das colunas contidas no
   *          ResultSet.
   */
  private void loadRows( ResultSet resultSet_,
                        ResultSetMetaData resultSetMetaData_ )
  {
    try
    {
      while ( resultSet_.next() )
      {
        m_rowSet.add( loadRow( resultSet_, resultSetMetaData_ ) );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(), "Error reading resultSet_.", e );
    }
  }

  /*
   * @see com.citibank.ods.common.dataset.DataSet#compare(java.util.ArrayList,
   *      java.util.ArrayList, int, boolean)
   */
  protected int compare( ArrayList comparingRow1_, ArrayList comparingRow2_,
                        int columnCriteriaIndex_, boolean ascending_ )
  {
    int comparingResult;
    Object comparingValue1 = comparingRow1_.get( columnCriteriaIndex_ );
    Object comparingValue2 = comparingRow2_.get( columnCriteriaIndex_ );

    if ( comparingValue1 == null )
    {
      //se o primeiro e o segundo forem nulos
      if ( comparingValue2 == null )
      {
        comparingResult = 0;
      }
      else
      {
        //se somente o primeiro for nulo
        comparingResult = -1;
      }
    }
    else
    {
      if ( comparingValue2 == null )
      {
        //se o primeiro e o segundo forem nulos
        comparingResult = 1;
      }
      else
      {
        //se nem o primeiro nem o segundo forem nulos
        if ( comparingValue1 instanceof String )
        {
          comparingResult = ( comparingValue1.toString() ).toUpperCase().compareTo(
                                                                                    ( comparingValue2.toString() ).toUpperCase() );
        }
        else if ( comparingValue1 instanceof BigDecimal )
        {
          comparingResult = ( ( BigDecimal ) comparingValue1 ).compareTo(( BigDecimal ) comparingValue2 );
        }
        else if ( comparingValue1 instanceof Byte )
        {
          comparingResult = ( ( Byte ) comparingValue1 ).compareTo(( Byte )comparingValue2 );
        }
        else if ( comparingValue1 instanceof Date )
        {
          comparingResult = ( ( Date ) comparingValue1 ).compareTo(( Date ) comparingValue2 );
        }
        else if ( comparingValue1 instanceof Time )
        {
          comparingResult = ( ( Time ) comparingValue1 ).compareTo( ( Time )comparingValue2 );
        }
        else if ( comparingValue1 instanceof Boolean )
        {
          comparingResult = -( comparingValue1.hashCode() - comparingValue2.hashCode() );
        }
        else if ( comparingValue1 instanceof Timestamp )
        {
          comparingResult = ( ( Timestamp ) comparingValue1 ).compareTo( ( Timestamp )comparingValue2 );
        }
        else
        {
          throw new UnsupportedOperationException( "Data Type not comparable." );
        }
      }
    }
    if ( !ascending_ )
    {
      comparingResult *= -1;
    }

    return comparingResult;
  }

  /*
   * @see com.citibank.ods.common.dataset.DataSet#newDataSetByRange(int, int)
   */
  public DataSet newDataSetByRange( int fromIndex_, int toIndex_ )
  {
    if ( fromIndex_ < 0 )
    {
      throw new UnexpectedException( "fromIndex_ cannot be smaller than 0." );
    }

    if ( toIndex_ >= this.size() )
    {
      throw new UnexpectedException(
                                     "toIndex_ cannot be bigger the this.size() - 1" );
    }

    if ( toIndex_ < fromIndex_ )
    {
      throw new UnexpectedException(
                                     "toIndex_ cannot be smaller than fromIndex_." );
    }
    DataSet newDataSet = newEmptyDataSet();
    newDataSet.m_rowSet = new ArrayList( m_rowSet.subList( fromIndex_ - 1,
                                                           toIndex_ ) );

    return newDataSet;
  }

  /*
   * @see com.citibank.ods.common.dataset.DataSet#newDataSetByLineNumber(int)
   */
  public DataSet newDataSetByLineNumber( int lineNumber_ )
  {
    if ( lineNumber_ < 0 || lineNumber_ > m_rowSet.size() - 1 )
    {
      throw new UnexpectedException(
                                     "Invalid lineNumber_ (greater than number of rows or smaller than 1)." );
    }

    DataSet newDataSet = ( ResultSetDataSet ) newEmptyDataSet();
    newDataSet.m_rowSet.add( m_rowSet.get( lineNumber_ ) );

    return newDataSet;
  }

  /*
   * @see com.citibank.ods.common.dataset.DataSet#newEmptyDataSet()
   */
  public DataSet newEmptyDataSet()
  {
    ResultSetDataSet newDataSet = new ResultSetDataSet();
    newDataSet.m_columnMapping = m_columnMapping;
    newDataSet.m_rowSet = new ArrayList();
    return newDataSet;
  }

  /**
   * 
   * Constrói uma tabela de dados com os dados do DataSet.
   * 
   * @param resultSet_ - ResultSet que contém o resultado da pesquisa.
   * @param resultSetMetaData_ - Meta-informação do ResultSet.
   * @return tabela de dados com os dados do DataSet.
   */
  public ArrayList loadRow( ResultSet resultSet_,
                           ResultSetMetaData resultSetMetaData_ )
  {
    Object columnValue;
    ArrayList rowRecord = null;
    try
    {
      int columnCount = resultSetMetaData_.getColumnCount();
      rowRecord = new ArrayList();
      for ( int i = 1; i <= columnCount; i++ )
      {
        columnValue = null;
        switch ( resultSetMetaData_.getColumnType( i ) )
        {
          case Types.DECIMAL:
          case Types.BIGINT:
          case Types.DOUBLE:
          case Types.FLOAT:
          case Types.INTEGER:
          case Types.NUMERIC:
          case Types.REAL:
          case Types.TINYINT:
          case Types.SMALLINT:
            columnValue = resultSet_.getBigDecimal( i );
            break;
          case Types.CHAR:
          case Types.LONGVARCHAR:
          case Types.VARCHAR:
            columnValue = resultSet_.getString( i );
            break;
          case Types.DATE:
            columnValue = resultSet_.getTimestamp( i );
            break;
          case Types.NULL:
            columnValue = null;
            break;
          case Types.TIME:
            columnValue = resultSet_.getTime( i );
            break;
          case Types.TIMESTAMP:
            columnValue = resultSet_.getTimestamp( i );
            break;
          default:
            throw new UnsupportedOperationException(
                                                     "Unsupported type: ["
                                                                                                          + resultSetMetaData_.getColumnType( i )
                                                                                                          + "]." );
        }
        rowRecord.add( columnValue );
      }
    }
    catch ( Exception e_ )
    {
      throw new UnexpectedException( "Error loading ResultSetDataSet.", e_ );
    }
    return rowRecord;
  }

}