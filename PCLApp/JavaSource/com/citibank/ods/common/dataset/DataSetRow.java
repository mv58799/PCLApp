package com.citibank.ods.common.dataset;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

import com.citibank.ods.common.BaseObject;
import com.citibank.ods.common.exception.UnexpectedException;

/**
 * 
 * Classe que representa uma Linha de DataSet.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public class DataSetRow extends BaseObject implements Serializable
{
  /**
   * m_columnMapping - Mapa do índice de colunas por nome de coluna.
   */
  private HashMap m_columnMapping;

  /**
   * m_row - Lista de valores das colunas de uma linha do DataSet.
   */
  private ArrayList m_row;

  /**
   * 
   * @param columnMapping_ - - Mapa do índice de colunas por nome de coluna.
   * @param row_ - Lista de valores das colunas de uma linha do DataSet.
   */
  DataSetRow( HashMap columnMapping_, ArrayList row_ )
  {
    setRow( columnMapping_, row_ );
  }

  /*
   * Verifica se esta linha é igual a outra linha (outro DataSetRow),
   * considerando estrutura e valores.
   * @see java.lang.Object#equals(java.lang.Object)
   */
  public boolean equals( Object otherObject_ )
  {
    // Test whether both references point to the same object.
    // Fullfill equals() Golden Rule 1.
    if ( this == otherObject_ )
    {
      return true;
    }

    // Test if otherObject_ is null.
    // Fullfill equals() Golden Rule 5.
    if ( otherObject_ == null )
    {
      return false;
    }

    // Test if this and otherObject_ belong to the SAME class.]
    // Fullfill equals() Golden Rule 2.
    if ( this.getClass() != otherObject_.getClass() )
    {
      return false;
    }

    // If we hit this point, we know that otherObject_ is a non-null
    // DataSetRow.
    // We then upcast it and compare all fields.
    DataSetRow other = ( DataSetRow ) otherObject_;

    if ( other.m_columnMapping.equals( this.m_columnMapping )
         && other.m_row.equals( this.m_row ) )
    {
      return true;
    }
    return false;

  }

  /**
   * 
   * Define que linha esta instância de DataSetRow representará.
   * 
   * @param columnMapping_ - - Mapa do índice de colunas por nome de coluna.
   * @param row_ - Lista de valores das colunas de uma linha do DataSet.
   */
  public void setRow( HashMap columnMapping_, ArrayList row_ )
  {
    if ( columnMapping_ == null )
    {
      throw new UnexpectedException( "columnMapping_ cannot be null" );
    }

    if ( row_ == null )
    {
      throw new UnexpectedException( "row_ cannot be null" );
    }

    m_columnMapping = columnMapping_;
    m_row = row_;
  }

  /**
   * 
   * Obtém o conteúdo de uma coluna, pelo índice da coluna
   * 
   * @param columnIndex_ - índice da coluna.
   * @return conteúdo da coluna.
   */
  private Object getColumnContent( int columnIndex_ )
  {
    Object columnContent = null;
    try
    {
      columnContent = m_row.get( columnIndex_ );
    }
    catch ( IndexOutOfBoundsException e_ )
    {
      throw new UnexpectedException( "columnIndex_ [" + columnIndex_
                                                                   + "] doesn't exist. Max columnIndex is ["
                                                                   + m_row.size() + "]", e_ );
    }
    return columnContent;
  }

  /**
   * 
   * Obtém o índice da coluna, dado o seu nome.
   * 
   * @param columnName_ - Nome da coluna.
   * @return índice da coluna.
   */
  private int getColumnIndexByName( String columnName_ )
  {
    if ( columnName_ == null )
    {
      throw new UnexpectedException( "columnName_ cannot be null." );
    }
    Integer columnIndex = ( Integer ) m_columnMapping.get( columnName_.toUpperCase() );
    if ( columnIndex == null )
    {
      throw new UnexpectedException( "columnName_ [" + columnName_
                                     + "] not found." );
    }
    return columnIndex.intValue();
  }

  /**
   * 
   * Obtém um valor convertido em String (toString()) do conteúdo de uma coluna,
   * pelo seu nome.
   * 
   * @param columnName_ - nome da coluna.
   * @return valor da coluna convertido em String.
   */
  public String get( String columnName_ )
  {
    String value = null;
    int columnIndex = getColumnIndexByName( columnName_ );

    value = get( columnIndex );
    return value;
  }

  /**
   * 
   * Obtém um valor convertido em String (toString()) do conteúdo de uma coluna,
   * pelo índice da coluna.
   * 
   * @param columnIndex_ - índice da coluna.
   * @return valor da coluna convertido em String.
   */
  public String get( int columnIndex_ )
  {
    String columnValue = null;
    Object columnContent = getColumnContent( columnIndex_ );

    if ( columnContent != null )
    {
      columnValue = columnContent.toString();
    }

    return columnValue;
  }

  /**
   * 
   * Obtém um valor String de uma coluna, pelo nome da coluna.
   * 
   * @param columnName_ - nome da coluna.
   * @return valor da coluna.
   */
  public String getStringByName( String columnName_ )
  {
    String value = null;
    int columnIndex = getColumnIndexByName( columnName_ );

    value = get( columnIndex );
    return value;
  }

  /**
   * 
   * Obtém um valor String de uma coluna, pelo índice da coluna.
   * 
   * @param columnIndex_ - índice da coluna.
   * @return valor da coluna.
   */
  public String getStringByIndex( int columnIndex_ )
  {
    String columnValue = null;
    Object columnContent = getColumnContent( columnIndex_ );

    if ( columnContent != null )
    {
      columnValue = columnContent.toString();
    }

    return columnValue;
  }

  /**
   * 
   * Obtém um valor Date de uma coluna, pelo índice da coluna.
   * 
   * @param columnIndex_ - índice da coluna.
   * @return valor da coluna.
   */
  public Date getDateByIndex( int columnIndex_ )
  {
    Date columnValue = null;
    Object columnContent = getColumnContent( columnIndex_ );
    if ( columnContent != null && !( columnContent instanceof Date ) )
    {
      throw new UnexpectedException( "Column [" + columnIndex_
                                     + "] is not a java.util.Date instance." );
    }

    Date dateValue = ( Date ) columnContent;

    if ( dateValue != null )
    {
      columnValue = new Date( dateValue.getTime() );
    }
    return columnValue;
  }

  /**
   * 
   * Obtém um valor Date de uma coluna, pelo nome da coluna.
   * 
   * @param columnName_ - nome da coluna.
   * @return valor da coluna.
   */
  public Date getDateByName( String columnName_ )
  {
    int columnIndex = getColumnIndexByName( columnName_ );
    Date columnValue = getDateByIndex( columnIndex );
    return columnValue;
  }

  /**
   * 
   * Obtém um valor BigDecimal de uma coluna, pelo índice da coluna.
   * 
   * @param columnIndex_ - índice da coluna.
   * @return valor da coluna.
   */
  public BigDecimal getBigDecimalByIndex( int columnIndex_ )
  {
    Object columnContent = getColumnContent( columnIndex_ );
    BigDecimal columnValue = null;

    if ( columnContent != null && !( columnContent instanceof BigDecimal ) )
    {
      throw new UnexpectedException(
                                     "Column ["
                                                                          + columnIndex_
                                                                          + "] is not a java.math.BigDecimal instance." );

    }
    columnValue = ( BigDecimal ) columnContent;
    return columnValue;
  }

  /**
   * 
   * Obtém um valor BigDecimal de uma coluna, pelo nome da coluna.
   * 
   * @param columnName_ - nome da coluna.
   * @return valor da coluna.
   */
  public BigDecimal getBigDecimalByName( String columnName_ )
  {
    int columnIndex = getColumnIndexByName( columnName_ );
    BigDecimal columnValue = getBigDecimalByIndex( columnIndex );
    return columnValue;
  }

  /**
   * 
   * Obtém a quantidade de colunas que existem no DataSetRow.
   * 
   * @return quantidade de colunas existentes no DataSetRow.
   */
  public int getColumnCount()
  {
    return m_columnMapping.size();
  }
}