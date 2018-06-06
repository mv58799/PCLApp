package com.citibank.ods.common.dataset;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.citibank.ods.common.BaseObject;
import com.citibank.ods.common.exception.UnexpectedException;

/**
 * Classe abstrata utilizada para manipular conjuntos de dados obtidos como
 * resultado de pesquisa. Simula organiza��o de dados tal qual uma tabela:
 * Possui lista de linhas, sendo que cada linha cont�m, por sua vez uma lista de
 * colunas. A primeira linha � indicada pelo �ndice 0. A �ltima linha � indicada
 * pelo �ndice n - 1, sendo n o tamanho do DataSet (ou n�mero de linhas
 * existentes).
 * 
 * Esta classe permite a cria��o de lista de valores por coluna e, uma vez
 * criada a lista, esta � mantida em cache interno. Entretanto, sempre que uma
 * opera��o que altere o conjunto de dados do DataSet ou altere a ordem de
 * disposi��o das informa��es for executado, o cache ser� destru�do e, se a
 * lista for solicitada, esta ser� criada novamente atrav�s de varredura em todo
 * o DataSet.
 * 
 * Outra funcionalidade dispon�vel � a cria��o de �ndices de pesquisa,
 * permitindo que sejam obtidos valores de uma c�lula a depender de um crit�rio
 * de pesquisa getMapped&lt;tipo&gt;. Antes de consultar uma c�lula mapeada, �
 * necess�rio que seja solilcitada a cria��o do mapa atrav�s do m�todo
 * mapRows(). Caso seja solicitado um valor atrav�s de pesquisa mapeada e o mapa
 * de pesquisa n�o tiver sido criado uma Unexpected Exception ser� lan�ada.
 * Importante ressaltar que, os mapas j� criados ser�o destru�dos sempre que for
 * executada uma opera��o que altere o conjunto de valores ou altere a
 * disposi��o das informa��es no DataSet e, neste caso, antes de solicitar a
 * consulta mapeada, o mapa desejado dever� ser recriado.
 * 
 * @version: 1.00
 * @author Luciano Marubayashi, Dec 21, 2006
 */
public abstract class DataSet extends BaseObject implements Serializable
{
  /**
   * m_rowSet - Lista de linhas da tabela.
   */
  protected ArrayList m_rowSet;

  /**
   * m_columnMapping - Mapa de colunas da tabela (chave do mapa: nome da coluna;
   * valor do mapa: �ndice da coluna em uma linha.
   */
  protected HashMap m_columnMapping;

  /**
   * m_columnsListCache - Cache de listas de valores de colunas (chave: �ndice
   * da coluna que para a qual foi gerada a lista de valores; valor: lista de
   * valores de uma coluna).
   */
  protected HashMap m_columnsListCache;

  /**
   * Construtor padr�o.
   */
  protected DataSet()
  {
    m_rowSet = new ArrayList();
    m_columnMapping = new HashMap();
    m_columnsListCache = new HashMap();
  }

  /**
   * 
   * Obt�m uma linha da tabela (m_rowSet), que � uma lista de valores das
   * colunas.
   * 
   * @param rowIndex_ - �ndice da linha.
   * @return lista de valores das colunas (ArrayList) da linha solicitada.
   */
  private ArrayList getInternalRow( int rowIndex_ )
  {
    ArrayList row = null;
    if ( rowIndex_ < 0 )
    {
      throw new UnexpectedException( "rowIndex_ cannot be smaller than 0." );
    }
    if ( rowIndex_ >= m_rowSet.size() )
    {
      throw new UnexpectedException( "rowIndex_ is bigger than last row index." );
    }
    row = ( ArrayList ) m_rowSet.get( rowIndex_ );
    return row;
  }

  /**
   * 
   * Obt�m um DataSetRow de uma determinada linha do DataSet.
   * 
   * @param rowIndex_ - �ndice da linha desejada.
   * @return DataSetRow da linha inidicada.
   */
  public DataSetRow getRow( int rowIndex_ )
  {
    ArrayList row = getInternalRow( rowIndex_ );
    DataSetRow dataSetRow = new DataSetRow( m_columnMapping, row );
    return dataSetRow;
  }

  /**
   * Obt�m o �ndice referente a um nome de coluna.
   * 
   * @param columnName_ - Nome da coluna que se deseja obter o �ndice.
   * @return �ndice da coluna.
   */
  public int getColumnIndex( String columnName_ )
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
   * Retorna o mapa de colunas do DataSet (posi��o da coluna por nome de
   * coluna).
   * 
   * @return Mapa de colunas do DataSet.
   */
  public HashMap getColumnMapping()
  {
    return m_columnMapping;
  }

  /**
   * Obt�m o valor referente a uma coluna (pelo �ndice da coluna) de uma
   * determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnIndex_ - N�mero da coluna.
   * @return Objeto localizado na linha rowIndex_ e coluna columnIndex_.
   */
  private Object getValue( int rowIndex_, int columnIndex_ )
  {
    ArrayList row = getInternalRow( rowIndex_ );
    if ( columnIndex_ < 0 )
    {
      throw new UnexpectedException( "columnIndex_ cannot be smaller than 0." );
    }
    if ( columnIndex_ >= this.m_columnMapping.size() )
    {
      throw new UnexpectedException(
                                     "columnIndex_ is bigger than last column index." );
    }
    return row.get( columnIndex_ );
  }

  /**
   * Obt�m o valor referente a uma coluna (pelo nome da coluna) de uma
   * determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnName_ - Nome da coluna.
   * @return Objeto localizado na linha rowIndex_ da coluna columnName_.
   */
  private Object getValue( int rowIndex_, String columnName_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    Object value = getValue( rowIndex_, columnIndex );
    return value;
  }

  /**
   * 
   * Retorna o n�mero de linhas do DataSet.
   * 
   * @return N�mero de linhas do DataSet.
   */
  public int size()
  {
    return m_rowSet.size();
  }

  /**
   * 
   * Retorna o n�mero de colunas do DataSet.
   * 
   * @return N�mero de colunas do DataSet.
   */
  public int columnCount()
  {
    return m_columnMapping.size();
  }

  /**
   * 
   * Obt�m um java.math.BigDecimal referente a uma coluna (pelo �ndice da
   * coluna) de uma determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnIndex_ - N�mero da coluna.
   * @return BigDecimal localizado na linha rowIndex_ e coluna columnIndex_.
   */
  public BigDecimal getBigDecimal( int rowIndex_, int columnIndex_ )
  {
    Object value = getValue( rowIndex_, columnIndex_ );
    if ( !( value instanceof BigDecimal ) )
    {
      throw new UnexpectedException(
                                     "Value( row: ["
                                                                          + rowIndex_
                                                                          + "], col: ["
                                                                          + columnIndex_
                                                                          + "] ) is not a java.math.BigDecimal instance." );
    }
    return ( BigDecimal ) value;
  }

  /**
   * 
   * Obt�m um java.math.BigDecial referente a uma coluna (pelo nome da coluna)
   * de uma determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnName_ - Nome da coluna.
   * @return BigDecimal localizado na linha rowIndex_ da coluna columnName_.
   */
  public BigDecimal getBigDecimal( int rowIndex_, String columnName_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    BigDecimal value = getBigDecimal( rowIndex_, columnIndex );
    return value;
  }

  /**
   * 
   * Obt�m um Date referente a uma coluna (pelo �ndice da coluna) de uma
   * determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnIndex_ - N�mero da coluna.
   * @return Date localizado na linha rowIndex_ e coluna columnIndex_.
   */
  public Date getDate( int rowIndex_, int columnIndex_ )
  {
    Object cellContent = getValue( rowIndex_, columnIndex_ );
    Date dateValue = null;
    Date value = null;
    if ( !( cellContent instanceof Date ) )
    {
      throw new UnexpectedException( "Value( row: [" + rowIndex_ + "], col: ["
                                     + columnIndex_
                                     + "] ) is not a java.util.Date instance." );
    }
    dateValue = ( Date ) cellContent;

    if ( dateValue != null )
    {
      value = new Date( dateValue.getTime() );
    }

    return value;
  }

  /**
   * 
   * Obt�m um Date referente a uma coluna (pelo nome da coluna) de uma
   * determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnName_ - Nome da coluna.
   * @return Date localizado na linha rowIndex_ da coluna columnName_.
   */
  public Date getDate( int rowIndex_, String columnName_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    Date value = getDate( rowIndex_, columnIndex );
    return value;
  }

  /**
   * 
   * Obt�m uma String referente a uma coluna (pelo �ndice da coluna) de uma
   * determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnIndex_ - N�mero da coluna.
   * @return String localizada na linha rowIndex_ e coluna columnIndex_.
   */
  public String getString( int rowIndex_, int columnIndex_ )

  {
    Object value = getValue( rowIndex_, columnIndex_ );
    if ( !( value instanceof String ) )
    {
      throw new UnexpectedException(
                                     "Value( row: ["
                                                                          + rowIndex_
                                                                          + "], col: ["
                                                                          + columnIndex_
                                                                          + "] ) is not a java.lang.String instance." );
    }
    return ( String ) value;
  }

  /**
   * 
   * Obt�m uma String referente a uma coluna (pelo nome da coluna) de uma
   * determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnName_ - Nome da coluna.
   * @return String localizada na linha rowIndex_ da coluna columnName_.
   */
  public String getString( int rowIndex_, String columnName_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    String value = getString( rowIndex_, columnIndex );
    return value;
  }

  /**
   * 
   * Obt�m uma String (toString()) referente a uma coluna (pelo �ndice da
   * coluna) de uma determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnIndex_ - N�mero da coluna.
   * @return String localizada na linha rowIndex_ e coluna columnIndex_.
   */
  public String get( int rowIndex_, int columnIndex_ )
  {
    Object cellContent = getValue( rowIndex_, columnIndex_ );
    String value = null;
    if ( cellContent != null )
    {
      value = cellContent.toString();
    }
    return value;
  }

  /**
   * 
   * Obt�m uma String (toString) referente a uma coluna (pelo nome da coluna) de
   * uma determinada linha (pelo �ndice da linha).
   * 
   * @param rowIndex_ - N�mero da linha.
   * @param columnName_ - Nome da coluna.
   * @return String localizada na linha rowIndex_ da coluna columnName_.
   */
  public String get( int rowIndex_, String columnName_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    String value = getString( rowIndex_, columnIndex );
    return value;
  }

  /**
   * 
   * Ordena as linhas do DataSet.
   * 
   * @param columnCriteria_ - nome da coluna que ser� o crit�rio de ordena��o do
   *          DataSet.
   * @param ascending_ - true: indica que o sentido de ordena��o � ascendente.
   *          false, caso contr�rio.
   */
  public void sort( String columnCriteria_, boolean ascending_ )
  {
    int columnCriteria = getColumnIndex( columnCriteria_ );
    quickSort( 0, m_rowSet.size() - 1, columnCriteria, ascending_ );
  }

  /**
   * 
   * Ordena as linhas do DataSet.
   * 
   * @param columnCriteria_ - �ndice da coluna que ser� o crit�rio de ordena��o
   *          do DataSet.
   * @param ascending_ - true: indica que o sentido de ordena��o � ascendente.
   *          false, caso contr�rio.
   */
  public void sort( int columnCriteria_, boolean ascending_ )
  {
    quickSort( 0, m_rowSet.size() - 1, columnCriteria_, ascending_ );
  }

  /**
   * 
   * Ordena as linhas do DataSet.
   * 
   * @param columnCriteria_ - lista de nomes das colunas que ser�o o crit�rio de
   *          ordena��o do DataSet.
   * @param ascending_ - true: indica que o sentido de ordena��o � ascendente.
   *          false, caso contr�rio.
   */
  public void sort( String[] columnCriteria_, boolean ascending_ )
  {
    int[] columnCriteria = new int[ columnCriteria_.length ];
    for ( int i = 0; i < columnCriteria_.length; i++ )
    {
      columnCriteria[ i ] = getColumnIndex( columnCriteria_[ i ] );
    }
    sort( columnCriteria, ascending_ );
  }

  /**
   * 
   * Ordena as linhas do DataSet.
   * 
   * @param columnCriteria_ - lista de �ndices das colunas que ser�o o crit�rio
   *          de ordena��o do DataSet.
   * @param ascending_ - true: indica que o sentido de ordena��o � ascendente.
   *          false, caso contr�rio.
   */
  public void sort( int[] columnCriteria_, boolean ascending_ )
  {
    
    sort( columnCriteria_, ascending_, new int[] { 0, m_rowSet.size() - 1 }, 0 );
  }

  /**
   * 
   * Ordena uma parti��o (sub-conjunto de linhas) do DataSet utilizando apenas
   * uma coluna como crit�rio de ordena��o.
   * 
   * @param columnCriteria_ - lista de �ndices das colunas que ser�o o crit�rio
   *          de ordena��o da parti��o do DataSet.
   * @param ascending_ - true: indica que o sentido de ordena��o da parti��o �
   *          ascendente. false, caso contr�rio.
   * @param partition_ - array de �ndices, onde a primeira posi��o do array
   *          ([0]) determina a linha inicial da parti��o a ser ordenada e a
   *          �ltima posi��o do array ([1]) determina a linha final da parti��o
   *          a ser ordenada.
   * @param currentCriteriaIndex_ - �ndice, da lista de �ndices de colunas
   *          crit�rio de ordena��o, que contempla o �ndice da coluna crit�rio
   *          de ordena��o da parti��o.
   */
  private void sort( int[] columnCriteria_, boolean ascending_,
                    int[] partition_, int currentCriteriaIndex_ )
  {
    int lastIndex;

    quickSort( partition_[ 0 ], partition_[ 1 ],
               columnCriteria_[ currentCriteriaIndex_ ], ascending_ );
    // n�o � ultimo crit�rio de ordena��o?
    if ( currentCriteriaIndex_ < columnCriteria_.length - 1 )
    {
      Object lastValue = null;
      for ( int j = partition_[ 0 ]; j < partition_[ 1 ]; j++ )
      {
        lastValue = getValue( j, columnCriteria_[ currentCriteriaIndex_ ] );
        lastIndex = lastOccurrenceOf( lastValue,
                                      columnCriteria_[ currentCriteriaIndex_ ],
                                      partition_[ 0 ], partition_[ 1 ] );
        sort( columnCriteria_, ascending_, new int[] { j, lastIndex },
              currentCriteriaIndex_ + 1 );
        j = lastIndex;
      }
    }
  }

  /**
   * 
   * QuickSort de parti��es do DataSet.
   * 
   * @param initialLowIndex_ - �ndice da linha inicial da parti��o a ser
   *          ordenada.
   * @param initialHighIndex_ - �ndice da linha final da parti��o a ser
   *          ordenada.
   * @param columnCriteriaIndex_ - �ndice da coluna crit�rio de ordena��o da
   *          parti��o.
   * @param ascending_ - true: indica que o sentido de ordena��o da parti��o �
   *          ascendente. false, caso contr�rio.
   */
  public void quickSort( int initialLowIndex_, int initialHighIndex_,
                        int columnCriteriaIndex_, boolean ascending_ )
  {
    resetColumnListCache();
    int currentLowIndex = initialLowIndex_;
    int currentHighIndex = initialHighIndex_;
    ArrayList midElement;

    if ( initialHighIndex_ > initialLowIndex_ )
    {

      /**
       * Arbitrarily establishing partition element as the midpoint of the
       * array.
       */
      midElement = ( ArrayList ) m_rowSet.get( ( initialLowIndex_ + initialHighIndex_ ) / 2 );

      // loop through the array until indices cross
      while ( currentLowIndex <= currentHighIndex )
      {
        /**
         * find the first element that is greater than or equal to the partition
         * element starting from the left Index.
         */
        while ( currentLowIndex < initialHighIndex_
                && compare( ( ArrayList ) m_rowSet.get( currentLowIndex ),
                            midElement, columnCriteriaIndex_, ascending_ ) < 0 )
        {
          ++currentLowIndex;
        }

        /**
         * find an element that is smaller than or equal to the partition
         * element starting from the right Index.
         */
        while ( currentHighIndex > initialLowIndex_
                && compare( ( ArrayList ) m_rowSet.get( currentHighIndex ),
                            midElement, columnCriteriaIndex_, ascending_ ) > 0 )
        {
          --currentHighIndex;
        }

        // if the indexes have not crossed, swap
        if ( currentLowIndex <= currentHighIndex )
        {
          if ( currentLowIndex != currentHighIndex )
          {
            swapPositions( currentLowIndex, currentHighIndex );
          }

          ++currentLowIndex;
          --currentHighIndex;
        }
      }

      /**
       * If the right index has not reached the left side of array must now sort
       * the left partition.
       */
      if ( initialLowIndex_ < currentHighIndex )
      {
        quickSort( initialLowIndex_, currentHighIndex, columnCriteriaIndex_,
                   ascending_ );
      }

      /**
       * If the left index has not reached the right side of array must now sort
       * the right partition.
       */
      if ( currentLowIndex < initialHighIndex_ )
      {
        quickSort( currentLowIndex, initialHighIndex_, columnCriteriaIndex_,
                   ascending_ );
      }

    }
  }

  /**
   * 
   * Remove linhas duplicadas no DataSet. O crit�rio que determina a igualdade
   * entre duas linhas �:<br>
   * linhas iguais: todos os objetos de uma linha retornam true na compara��o
   * (equals()) com o objeto correspondente da outra linha ou ambos s�o null.
   * <br>
   * linhas diferentes: caso contr�rio.
   * 
   * @return n�mero de linhas duplicadas removidas.
   */
  public int removeDuplicateRows()
  {
    DataSetRow row;
    DataSetRow compareRow;
    resetColumnListCache();
    int howManyRowsRemoved = 0;

    for ( int rowCount = 1; rowCount <= this.size() - 1; rowCount++ )
    {
      row = this.getRow( rowCount );

      for ( int compareRowCount = rowCount + 1; compareRowCount <= this.size(); compareRowCount++ )
      {
        compareRow = this.getRow( compareRowCount );

        if ( row.equals( compareRow ) )
        {
          this.removeRow( compareRowCount );
          compareRowCount--;
          howManyRowsRemoved++;
        }
      }
    }

    return howManyRowsRemoved;
  }

  /**
   * 
   * Remove uma linha do DataSet.
   * 
   * @param rowIndex_ - �ndice da linha que ser� removida.
   */
  public void removeRow( int rowIndex_ )
  {
    resetColumnListCache();
    if ( rowIndex_ < 0 )
    {
      throw new UnexpectedException( "Invalid rowIndex_: [" + rowIndex_ + "]." );
    }

    if ( rowIndex_ > this.size() )
    {
      throw new UnexpectedException( "rowIndex_: [" + rowIndex_
                                     + "] is bigger than DataSet las row: ["
                                     + ( m_rowSet.size() - 1 ) + "]." );
    }

    m_rowSet.remove( rowIndex_ );
  }

  /**
   * 
   * Troca a posi��o de duas linhas.
   * 
   * @param leftIndex_ - n�mero de uma das linhas que ser� trocada de posi��o.
   * @param rightIndex_ - n�mero de outra linha que ser� trocada de posi��o.
   */
  private void swapPositions( int leftIndex_, int rightIndex_ )
  {
    Object swappingObject = m_rowSet.get( leftIndex_ );
    m_rowSet.set( leftIndex_, m_rowSet.get( rightIndex_ ) );
    m_rowSet.set( rightIndex_, swappingObject );
  }

  /**
   * Mostra o conte�do de todas linhas do DataSet.
   * 
   * @return String que apresenta o conte�do de todas as colunas de todas as
   *         linhas da tabela.
   */
  public String toString()
  {
    StringBuffer columns = new StringBuffer();
    boolean isFirst = true;
    columns.append( "Columns: " );
    if ( m_columnMapping != null && m_columnMapping.size() > 0 )
    {
      Iterator columnsNames = m_columnMapping.keySet().iterator();
      while ( columnsNames.hasNext() )
      {
        if ( isFirst )
        {
          isFirst = false;
        }
        else
        {
          columns.append( ", " );
        }
        columns.append( columnsNames.next() );
      }
    }
    return columns.toString();
  }

  /**
   * 
   * Verifica se um outro DataSet tem a mesma estrutura para que possa ser
   * realizada opera��es de cojunto entre estes DataSets. Dois DataSets tem a
   * mesma estrututra se seus m_columnsMappings forem iguais.
   * 
   * @param appendingDataSet_ - DataSet cuja estrutura ser� comparada �
   *          estrutura deste DataSet.
   */
  private void validateExternalStructure( DataSet appendingDataSet_ )
  {
    Iterator internalColumnsSequence = this.m_columnMapping.keySet().iterator();
    Iterator externalColumnsSequence = appendingDataSet_.m_columnMapping.keySet().iterator();
    while ( internalColumnsSequence.hasNext() )
    {
      if ( !externalColumnsSequence.hasNext() )
      {
        throw new UnexpectedException(
                                       "DataSet instances are differrent in columns count." );
      }
      Object internalColumn = internalColumnsSequence.next();
      Object externalColum = externalColumnsSequence.next();
      if ( !internalColumn.equals( externalColum ) )
      {
        throw new UnexpectedException(
                                       "DataSet instances are differrent in columns structure: this column ["
                                                                              + internalColumn
                                                                              + "] != external column ["
                                                                              + externalColum
                                                                              + "]" );
      }
    }
    if ( externalColumnsSequence.hasNext() )
    {
      throw new UnexpectedException(
                                     "DataSet instances are differrent in columns count." );
    }
  }

  /**
   * 
   * Adiciona, no final deste DataSet as todas as linhas de outro DataSet que
   * possua a mesma estrutura e a ordem das linhas do outro DataSet � mantida.
   * 
   * @param appendingDataSet_ DataSet de mesma estrutura que este DataSet, cujas
   *          linhas ser�o adicionadas a este DataSet.
   */
  public void append( DataSet appendingDataSet_ )
  {
    resetColumnListCache();
    validateExternalStructure( appendingDataSet_ );
    int rowIndex;
    int externalViewDataSize = appendingDataSet_.m_rowSet.size();
    for ( rowIndex = 0; rowIndex < externalViewDataSize; rowIndex++ )
    {
      this.m_rowSet.add( appendingDataSet_.m_rowSet.get( rowIndex ) );
    }
  }

  /**
   * 
   * Adiciona, no final deste DataSet uma linha de outro DataSet que possua a
   * mesma estrutura.
   * 
   * @param appendingDataSet_ - DataSet de mesma estrutura que este DataSet,
   *          cuja linha ser� adicionada a este DataSet.
   * @param appendingRowIndex_ - �ndice da l�nha do outro DataSet que ser�
   *          adicionada a este DataSet.
   */
  public void append( DataSet appendingDataSet_, int appendingRowIndex_ )
  {
    resetColumnListCache();
    validateExternalStructure( appendingDataSet_ );
    int externalViewDataSize = appendingDataSet_.m_rowSet.size();
    if ( appendingRowIndex_ < 0 || appendingRowIndex_ > externalViewDataSize )
    {
      throw new UnexpectedException(
                                     "Invalid appendingRowIndex_ (greater than first unused row or smaller than 0)." );
    }
    this.m_rowSet.add( appendingDataSet_.m_rowSet.get( appendingRowIndex_ ) );
  }

  /**
   * 
   * Obt�m o primeiro (ou �ltimo, dependendo do sentido de pesquisa) n�mero da
   * linha de uma parti��o do DataSet que possui a ocorr�ncia de um objeto em
   * uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - �ndice da coluna que ser� pesquisada.
   * @param fromIndex_ - In�cio da parti��o (primeira linha) em que o objeto
   *          ser� procurado.
   * @param toIndex_ - F�m da parti��o (�ltima linha) em que o objeto ser�
   *          procurado.
   * @param ascending_ - true: para pesquisa ascendente. false, caso contr�rio.
   * @return �ndice da linha da primeira (ou �ltima, dependendo do sentido da
   *         pesquisa) ocorr�ncia do objeto. -1 se o objeto n�o for encontrado.
   */
  private int occurrenceIndexOf( Object value_, int columnIndex_,
                                int fromIndex_, int toIndex_, boolean ascending_ )
  {
    ArrayList comparingRow2;
    int occurrenceIndexOf = -1;
    if ( m_rowSet.size() > 0 )
    {
      ArrayList comparingRow1 = ( ArrayList ) getRowAsArrayList( 0 ).clone();
      comparingRow1.set( columnIndex_, value_ );

      int start = ( ascending_ ? fromIndex_ : toIndex_ );
      int end = ( ascending_ ? toIndex_ : fromIndex_ );
      for ( int rowIndex = start; ( ascending_ ? rowIndex <= end
                                              : rowIndex >= end )
                                  && occurrenceIndexOf == -1; rowIndex += ( ascending_
                                                                                      ? +1
                                                                                      : -1 ) )
      {
        comparingRow2 = ( ArrayList ) m_rowSet.get( rowIndex );
        if ( compare( comparingRow1, comparingRow2, columnIndex_, true ) == 0 )
        {
          occurrenceIndexOf = rowIndex;
        }
      }
    }
    return occurrenceIndexOf;
  }

  /**
   * 
   * Valida os par�metros informados na pesquisa de ocorr�ncia de objetos.
   * 
   * @param columnIndex_ - �ndice da coluna que ser� pesquisada.
   * @param fromIndex_ - In�cio da parti��o (primeira linha) em que o objeto
   *          ser� procurado.
   * @param toIndex_ - F�m da parti��o (�ltima linha) em que o objeto ser�
   *          procurado.
   */
  private void validateOccurrenceParameters( int columnIndex_, int fromIndex_,
                                            int toIndex_ )
  {
    // Parameters checking...
    if ( columnIndex_ < 0 )
    {
      throw new UnexpectedException( "columnIndex_ cannot be smaller than 0." );
    }

    if ( columnIndex_ >= this.columnCount() )
    {
      throw new UnexpectedException(
                                     "columnIndex_ cannot be bigger than this.columnCount() - 1." );
    }

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
  }

  /**
   * 
   * Obt�m o primeiro n�mero da linha de uma parti��o do DataSet que possui a
   * ocorr�ncia de um objeto em uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - �ndice da coluna que ser� pesquisada.
   * @param fromIndex_ - In�cio da parti��o (primeira linha) em que o objeto
   *          ser� procurado.
   * @param toIndex_ - F�m da parti��o (�ltima linha) em que o objeto ser�
   *          procurado.
   * @param ascending_ - true: para pesquisa ascendente. false, caso contr�rio.
   * @return �ndice da linha da primeira ocorr�ncia do objeto. -1 se o objeto
   *         n�o for encontrado.
   */
  public int firstOccurrenceOf( Object value_, int columnIndex_,
                               int fromIndex_, int toIndex_ )
  {
    int firstOccurrenceOf = -1;
    validateOccurrenceParameters( columnIndex_, fromIndex_, toIndex_ );
    firstOccurrenceOf = occurrenceIndexOf( value_, columnIndex_, fromIndex_,
                                           toIndex_, true );
    return firstOccurrenceOf;
  }

  /**
   * 
   * Obt�m o primeiro n�mero da linha que possui a ocorr�ncia de um objeto em
   * uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - �ndice da coluna que ser� pesquisada.
   * @return �ndice da linha da primeira ocorr�ncia do objeto. -1 se o objeto
   *         n�o for encontrado.
   */
  public int firstOccurrenceOf( Object value_, int columnIndex_ )
  {
    int firstOccurenceOf = this.firstOccurrenceOf( value_, columnIndex_, 0,
                                                   this.size() - 1 );
    return firstOccurenceOf;
  }

  /**
   * 
   * Obt�m o primeiro n�mero da linha de uma parti��o do DataSet que possui a
   * ocorr�ncia de um objeto em uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnName_ - Nome da coluna que ser� pesquisada.
   * @param fromIndex_ - In�cio da parti��o (primeira linha) em que o objeto
   *          ser� procurado.
   * @param toIndex_ - F�m da parti��o (�ltima linha) em que o objeto ser�
   *          procurado.
   * @return �ndice da linha da primeira ocorr�ncia do objeto. -1 se o objeto
   *         n�o for encontrado.
   */
  public int firstOccurrenceOf( Object value_, String columnName_,
                               int fromIndex_, int toIndex_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    int firstOccurrenceOf = firstOccurrenceOf( value_, columnIndex, fromIndex_,
                                               toIndex_ );
    return firstOccurrenceOf;
  }

  /**
   * 
   * Obt�m o primeiro n�mero da linha que possui a ocorr�ncia de um objeto em
   * uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnName_ - Nome da coluna que ser� pesquisada.
   * @return �ndice da linha da primeira ocorr�ncia do objeto. -1 se o objeto
   *         n�o for encontrado.
   */
  public int firstOccurrenceOf( Object value_, String columnName_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    int firstOccurenceOf = firstOccurrenceOf( value_, columnIndex );
    return firstOccurenceOf;
  }

  /**
   * 
   * Obt�m o �ltimo n�mero da linha de uma parti��o do DataSet que possui a
   * ocorr�ncia de um objeto em uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - �ndice da coluna que ser� pesquisada.
   * @param fromIndex_ - In�cio da parti��o (primeira linha) em que o objeto
   *          ser� procurado.
   * @param toIndex_ - F�m da parti��o (�ltima linha) em que o objeto ser�
   *          procurado.
   * @return �ndice da linha da primeira ocorr�ncia do objeto. -1 se o objeto
   *         n�o for encontrado.
   */
  public int lastOccurrenceOf( Object value_, int columnIndex_, int fromIndex_,
                              int toIndex_ )
  {
    int lastOccurrenceOf = -1;
    validateOccurrenceParameters( columnIndex_, fromIndex_, toIndex_ );
    lastOccurrenceOf = occurrenceIndexOf( value_, columnIndex_, fromIndex_,
                                          toIndex_, false );
    return lastOccurrenceOf;
  }

  /**
   * 
   * Obt�m o �ltimo n�mero da linha que possui a ocorr�ncia de um objeto em uma
   * determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - �ndice da coluna que ser� pesquisada.
   * @return �ndice da linha da primeira ocorr�ncia do objeto. -1 se o objeto
   *         n�o for encontrado.
   */
  public int lastOccurrenceOf( Object value_, int columnIndex_ )
  {
    int lastOccurenceOf = this.lastOccurrenceOf( value_, columnIndex_, 0,
                                                 this.size() - 1 );
    return lastOccurenceOf;
  }

  /**
   * 
   * Obt�m o �ltimo n�mero da linha de uma parti��o do DataSet que possui a
   * ocorr�ncia de um objeto em uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnName_ - Nome da coluna que ser� pesquisada.
   * @param fromIndex_ - In�cio da parti��o (primeira linha) em que o objeto
   *          ser� procurado.
   * @param toIndex_ - F�m da parti��o (�ltima linha) em que o objeto ser�
   *          procurado.
   * @return �ndice da linha da primeira ocorr�ncia do objeto. -1 se o objeto
   *         n�o for encontrado.
   */
  public int lastOccurrenceOf( Object value_, String columnName_,
                              int fromIndex_, int toIndex_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    int lastOccurrenceOf = lastOccurrenceOf( value_, columnIndex, fromIndex_,
                                             toIndex_ );
    return lastOccurrenceOf;
  }

  /**
   * 
   * Obt�m o �ltimo n�mero da linha que possui a ocorr�ncia de um objeto em uma
   * determinada coluna.
   * 
   * Importante ressaltar que o estado de ordena��o do DataSet � determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnName_ - Nome da coluna que ser� pesquisada.
   * @return �ndice da linha da �ltima ocorr�ncia do objeto. -1 se o objeto n�o
   *         for encontrado.
   */
  public int lastOccurrenceOf( Object value_, String columnName_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    int lastOccurenceOf = lastOccurrenceOf( value_, columnIndex );
    return lastOccurenceOf;
  }

  /**
   * 
   * Obt�m um ArrayList com os valores das colunas de uma linha do DataSet.
   * 
   * @param rowIndex_ - �ndice da linha.
   * @return ArrayList com os valores das colunas da linha do DataSet.
   */
  private ArrayList getRowAsArrayList( int rowIndex_ )
  {
    if ( rowIndex_ < 0 || rowIndex_ > m_rowSet.size() - 1 )
    {
      throw new UnexpectedException( "row does not exist" );
    }
    ArrayList rowAsArrayList = ( ArrayList ) m_rowSet.get( rowIndex_ );
    return rowAsArrayList;
  }

  /**
   * 
   * Faz a verifica��o dos par�metros utilizados nas chamandas dos m�todos
   * respons�veis por outerJoin.
   * 
   * @param joinnedDataSet_ - DataSet cujos dados ser�o outerJoined.
   * @param addingDataSetColumnNamesJoinCriteria_ - nomes das colunas do
   *          DataSet, que ser� outerJoined, que ser�o o crit�rio para
   *          compara��o.
   * @param addedDataSetColumnNamesCriteria_ - nomes das colunas deste DataSet
   *          que ser�o o crit�rio para compara��o.
   * @param joinnedColumnNames_ - nomes das colunas do DataSet, que ser�
   *          outerJoinned, que ser�o adicionadas a este DataSet.
   */
  private void validateOuterJoinParameters(
                                           DataSet joinnedDataSet_,
                                           String[] addingDataSetColumnNamesJoinCriteria_,
                                           String[] addedDataSetColumnNamesCriteria_,
                                           String[] joinnedColumnNames_ )
  {
    if ( joinnedDataSet_ == null )
    {
      throw new UnexpectedException( "joinnedDataSet cannot be null." );
    }
    if ( addingDataSetColumnNamesJoinCriteria_ == null
         || addingDataSetColumnNamesJoinCriteria_.length == 0 )
    {
      throw new UnsupportedOperationException(
                                               "DataSet outterJoin requires at least one column for join criteria. (addingDataSetColumnNamesJoinCriteria_ null or empty)" );
    }
    if ( addedDataSetColumnNamesCriteria_ == null
         || addingDataSetColumnNamesJoinCriteria_.length != addedDataSetColumnNamesCriteria_.length )
    {
      throw new UnexpectedException(
                                     "Joinning and joinned criteria must be the same size." );
    }
    if ( joinnedColumnNames_ == null || joinnedColumnNames_.length == 0 )
    {
      throw new UnsupportedOperationException(
                                               "DataSet outterJoin requires at least on column to be joinned. (joinnedColumnNames_ null or empty)" );
    }
    for ( int i = 0; i < joinnedColumnNames_.length; i++ )
    {
      if ( m_columnMapping.containsKey( joinnedColumnNames_[ i ] ) )
      {
        throw new UnexpectedException(
                                       "joinnedColumnName_ ["
                                                                              + joinnedColumnNames_[ i ]
                                                                              + "] already exists in the joinning DataSet. " );
      }
    }
  }

  /**
   * 
   * Faz o outerJoin entre este dois DataSets. A este DataSet ser�o incorporadas
   * colunas de outros DataSets e estas ser�o preenchidas com os valores do
   * outro DataSet com base nas colunas crit�rio de compara��o. Para as linhas
   * deste DataSet que n�o fizerem match com as linhas do outro DataSet, as
   * colunas adicionadas ser�o preenchidas com null.
   * 
   * @param joinnedDataSet_ - DataSet cujos dados ser�o outerJoined.
   * @param addingDataSetColumnNamesJoinCriteria_ - nomes das colunas do
   *          DataSet, que ser� outerJoined, que ser�o o crit�rio para
   *          compara��o.
   * @param addedDataSetColumnNamesCriteria_ - nomes das colunas deste DataSet
   *          que ser�o o crit�rio para compara��o.
   * @param joinnedColumnNames_ - nomes das colunas do DataSet, que ser�
   *          outerJoinned, que ser�o adicionadas a este DataSet.
   */
  public void outerJoin( DataSet joinnedDataSet_,
                        String[] addingDataSetColumnNamesJoinCriteria_,
                        String[] addedDataSetColumnNamesCriteria_,
                        String[] joinnedColumnNames_ )
  {
    ArrayList key;
    ArrayList appendingColumns;
    ArrayList joinnedRow;
    validateOuterJoinParameters( joinnedDataSet_,
                                 addingDataSetColumnNamesJoinCriteria_,
                                 addedDataSetColumnNamesCriteria_,
                                 joinnedColumnNames_ );
    updateColumnMapping( joinnedColumnNames_ );
    HashMap outterJoinMap = buildJoinMap( joinnedDataSet_,
                                          addedDataSetColumnNamesCriteria_,
                                          joinnedColumnNames_ );
    for ( int i = 0; i < m_rowSet.size(); i++ )
    {
      key = buildKey( this, addingDataSetColumnNamesJoinCriteria_, i );
      appendingColumns = ( ArrayList ) outterJoinMap.get( key );
      if ( appendingColumns == null )
      {
        appendingColumns = new ArrayList();
        for ( int j = 0; j < joinnedColumnNames_.length; j++ )
        {
          appendingColumns.add( null );
        }
      }
      joinnedRow = ( ArrayList ) m_rowSet.get( i );
      joinnedRow.addAll( appendingColumns );
    }
  }

  /**
   * 
   * Inclui colunas no m_columnMapping.
   * 
   * @param joinnedColumnNames_ - nomes das colunas que ser�o adicionadas.
   */
  private void updateColumnMapping( String[] joinnedColumnNames_ )
  {
    int startIndex = m_columnMapping.size();
    for ( int i = 0; i < joinnedColumnNames_.length; i++ )
    {
      m_columnMapping.put( joinnedColumnNames_[ i ], new Integer( i
                                                                  + startIndex ) );
    }

  }

  /**
   * 
   * Constr�i uma mapa de linhas para um DataSet. <br>
   * Chave do Mapa: Lista de valores de colunas que ser�o crit�rio de pesquisa.
   * <br>
   * Valores do Mapa: Lista de Valores de colunas que ser�o adicionadas a este
   * DataSet.
   * 
   * @param joinnedDataSet_ - DataSet que ser� outerJoined a este DataSet.
   * @param addedDataSetColumnNamesCriteria_ - Nomes das colunas que ser�o
   *          crit�rio de join, do DataSet que ser� outerJoined a este DataSet.
   * @param joinnedColumnNames_ - Nomes das colunas que ser�o adicionadas, do
   *          DataSet que ser� outerJoined a este DataSet.
   * @return Mapa de linhas das colunas que ser�o adicionadas a este DataSet
   *         pelos nomes das colunas que ser�o o crit�rio de join, do DataSet
   *         que ser� outerJoined a este DataSet.
   */
  private HashMap buildJoinMap( DataSet joinnedDataSet_,
                               String[] addedDataSetColumnNamesCriteria_,
                               String[] joinnedColumnNames_ )
  {
    HashMap joinMap = new HashMap();
    ArrayList key;
    ArrayList value;
    for ( int i = 0; i < joinnedDataSet_.size(); i++ )
    {
      key = buildKey( joinnedDataSet_, addedDataSetColumnNamesCriteria_, i );
      value = buildAppendingData( joinnedDataSet_, joinnedColumnNames_, i );
      joinMap.put( key, value );
    }
    return joinMap;

  }

  /**
   * 
   * Constr�i uma lista de valores de colunas de uma linha de um DataSet.
   * 
   * @param dataSet_ - DataSet que ser� origem da lista de valores.
   * @param keyColumnNames_ - nomes das colunas do DataSet que ser�o adicionadas
   *          a lista.
   * @param rowIndex_ - n�mero da linha do DataSet que cont�m os valores que
   *          ir�o compor a lista.
   * @return lista de valores de colunas de uma linha de um DataSet.
   */
  private ArrayList buildKey( DataSet dataSet_, String[] keyColumnNames_,
                             int rowIndex_ )
  {
    ArrayList key = new ArrayList();
    for ( int i = 0; i < keyColumnNames_.length; i++ )
    {
      key.add( dataSet_.getValue( rowIndex_, keyColumnNames_[ i ] ) );
    }
    return key;
  }

  /**
   * 
   * Constr�i uma lista de valores de colunas de uma linha de um DataSet.
   * 
   * @param joinnedDataSet_ - DataSet que ser� origem da lista de valores.
   * @param joinnedColumnNames_ - nomes das colunas do DataSet que ser�o
   *          adicionadas a lista.
   * @param rowIndex_ - n�mero da linha do DataSet que cont�m os valores que
   *          ir�o compor a lista.
   * @return lista de valores de colunas de uma linha de um DataSet.
   */
  private ArrayList buildAppendingData( DataSet joinnedDataSet_,
                                       String[] joinnedColumnNames_,
                                       int rowIndex_ )
  {
    ArrayList appendingData = new ArrayList();
    for ( int i = 0; i < joinnedColumnNames_.length; i++ )
    {
      appendingData.add( joinnedDataSet_.getValue( rowIndex_,
                                                   joinnedColumnNames_[ i ] ) );
    }
    return appendingData;
  }

  /**
   * 
   * Obt�m uma lista de valores de uma coluna. Se a lista para a coluna desejada
   * j� existir em cache, a lista em cache � retornada. Caso contr�rio, constroi
   * a lista e adiciona a lista ao cache, identificada pelo �ndice da coluna da
   * lista.
   * 
   * @param columnName_ - nome da coluna que se deseja a lista de valores.
   * @return lista de valores da coluna.
   */
  public ArrayList getColumnValuesByName( String columnName_ )
  {
    Integer columnId = new Integer( getColumnIndex( columnName_ ) );
    ArrayList columnList = ( ArrayList ) m_columnsListCache.get( columnId );
    if ( columnList == null )
    {
      columnList = new ArrayList();
      for ( int i = 0; i < m_rowSet.size(); i++ )
      {
        columnList.add( ( ( ArrayList ) m_rowSet.get( i ) ).get( columnId.intValue() ) );
      }
      m_columnsListCache.put( columnId, columnList );
    }
    return columnList;
  }

  /**
   * 
   * Limpa o cache de lista de colunas. Esta limpeza deve ser invocada sempre
   * que houver uma altera��o no conjunto de informa��es do DataSet (por ex.;
   * outerJoin) ou houver uma altera��o no conjunto de dados (incluindo a ordem
   * dos dados) (por ex.: sort, append)
   *  
   */
  private void resetColumnListCache()
  {
    m_columnsListCache.clear();
  }

  /**
   * 
   * Constr�i uma nova inst�ncia de DataSet a partir deste DataSet, com a mesma
   * estrutrutura de colunas, inicializada com uma c�pia de uma parti��o das
   * linhas.
   * 
   * @param fromIndex_ - �ndice da primeira linha da parti��o que ser� copiada.
   * @param toIndex_ - �ndice da �ltima linha da parti��o que ser� copiada.
   * @return Nova inst�ncia de DataSet com a mesma estrutura deste DataSet e uma
   *         c�pia de uma parti��o deste DataSet.
   */
  abstract public DataSet newDataSetByRange( int fromIndex_, int toIndex_ );

  /**
   * 
   * Constr�i uma nova inst�ncia de DataSet a partir deste DataSet, com a mesma
   * estrutrutura de colunas, inicializada com uma c�pia de uma linha deste
   * DataSet.
   * 
   * @param lineNumber_ - n�mero da linha que ser� copiada � nova inst�ncia do
   *          DataSet.
   * @return Nova inst�ncia de DataSet a partir deste DataSet, com a mesma
   *         estrutrutura de colunas, inicializada com uma c�pia de uma linha
   *         deste DataSet.
   */
  abstract public DataSet newDataSetByLineNumber( int lineNumber_ );

  /**
   * 
   * Constr�i uma nova inst�ncia de DataSet a partir deste DataSet, com a mesma
   * estrutrutura de colunas.
   * 
   * @return nova inst�ncia de DataSet a partir deste DataSet, com a mesma
   *         estrutrutura de colunas.
   */
  abstract public DataSet newEmptyDataSet();

  /**
   * 
   * Compara duas linhas do DataSet e verifica qual � "maior". A defini��o de
   * "maior" � resultado da compara��o do valor de uma coluna para as duas
   * linhas.
   * 
   * @param comparingRow1_ - Linha1 que ser� comparada.
   * @param comparingRow2_ - Linha2 que ser� comparada.
   * @param columnCriteriaIndex_ - �ndice da coluna que ser� utilizada para a
   *          compara��o.
   * @param ascending_ - Indica se a compara��o ser� ascendente (true) ou
   *          descendente (false)
   * @return na compara��o ascendente: 0 se forem iguais; um valor &lt; 0 se a
   *         Linha1 for menor que a Linha2; um valor &gt; 0 se a Linha1 for
   *         maior que a Linha2. <br>
   *         A compara��o descendente, � o valor da compara��o ascendente
   *         multiplicada por -1.
   *  
   */
  abstract protected int compare( ArrayList comparingRow1_,
                                 ArrayList comparingRow2_,
                                 int columnCriteriaIndex_, boolean ascending_ );

}