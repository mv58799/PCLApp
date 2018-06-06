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
 * resultado de pesquisa. Simula organização de dados tal qual uma tabela:
 * Possui lista de linhas, sendo que cada linha contém, por sua vez uma lista de
 * colunas. A primeira linha é indicada pelo índice 0. A última linha é indicada
 * pelo índice n - 1, sendo n o tamanho do DataSet (ou número de linhas
 * existentes).
 * 
 * Esta classe permite a criação de lista de valores por coluna e, uma vez
 * criada a lista, esta é mantida em cache interno. Entretanto, sempre que uma
 * operação que altere o conjunto de dados do DataSet ou altere a ordem de
 * disposição das informações for executado, o cache será destruído e, se a
 * lista for solicitada, esta será criada novamente através de varredura em todo
 * o DataSet.
 * 
 * Outra funcionalidade disponível é a criação de índices de pesquisa,
 * permitindo que sejam obtidos valores de uma célula a depender de um critério
 * de pesquisa getMapped&lt;tipo&gt;. Antes de consultar uma célula mapeada, é
 * necessário que seja solilcitada a criação do mapa através do método
 * mapRows(). Caso seja solicitado um valor através de pesquisa mapeada e o mapa
 * de pesquisa não tiver sido criado uma Unexpected Exception será lançada.
 * Importante ressaltar que, os mapas já criados serão destruídos sempre que for
 * executada uma operação que altere o conjunto de valores ou altere a
 * disposição das informações no DataSet e, neste caso, antes de solicitar a
 * consulta mapeada, o mapa desejado deverá ser recriado.
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
   * valor do mapa: índice da coluna em uma linha.
   */
  protected HashMap m_columnMapping;

  /**
   * m_columnsListCache - Cache de listas de valores de colunas (chave: índice
   * da coluna que para a qual foi gerada a lista de valores; valor: lista de
   * valores de uma coluna).
   */
  protected HashMap m_columnsListCache;

  /**
   * Construtor padrão.
   */
  protected DataSet()
  {
    m_rowSet = new ArrayList();
    m_columnMapping = new HashMap();
    m_columnsListCache = new HashMap();
  }

  /**
   * 
   * Obtém uma linha da tabela (m_rowSet), que é uma lista de valores das
   * colunas.
   * 
   * @param rowIndex_ - Índice da linha.
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
   * Obtém um DataSetRow de uma determinada linha do DataSet.
   * 
   * @param rowIndex_ - Índice da linha desejada.
   * @return DataSetRow da linha inidicada.
   */
  public DataSetRow getRow( int rowIndex_ )
  {
    ArrayList row = getInternalRow( rowIndex_ );
    DataSetRow dataSetRow = new DataSetRow( m_columnMapping, row );
    return dataSetRow;
  }

  /**
   * Obtém o índice referente a um nome de coluna.
   * 
   * @param columnName_ - Nome da coluna que se deseja obter o índice.
   * @return Índice da coluna.
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
   * Retorna o mapa de colunas do DataSet (posição da coluna por nome de
   * coluna).
   * 
   * @return Mapa de colunas do DataSet.
   */
  public HashMap getColumnMapping()
  {
    return m_columnMapping;
  }

  /**
   * Obtém o valor referente a uma coluna (pelo índice da coluna) de uma
   * determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
   * @param columnIndex_ - Número da coluna.
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
   * Obtém o valor referente a uma coluna (pelo nome da coluna) de uma
   * determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
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
   * Retorna o número de linhas do DataSet.
   * 
   * @return Número de linhas do DataSet.
   */
  public int size()
  {
    return m_rowSet.size();
  }

  /**
   * 
   * Retorna o número de colunas do DataSet.
   * 
   * @return Número de colunas do DataSet.
   */
  public int columnCount()
  {
    return m_columnMapping.size();
  }

  /**
   * 
   * Obtém um java.math.BigDecimal referente a uma coluna (pelo índice da
   * coluna) de uma determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
   * @param columnIndex_ - Número da coluna.
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
   * Obtém um java.math.BigDecial referente a uma coluna (pelo nome da coluna)
   * de uma determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
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
   * Obtém um Date referente a uma coluna (pelo índice da coluna) de uma
   * determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
   * @param columnIndex_ - Número da coluna.
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
   * Obtém um Date referente a uma coluna (pelo nome da coluna) de uma
   * determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
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
   * Obtém uma String referente a uma coluna (pelo índice da coluna) de uma
   * determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
   * @param columnIndex_ - Número da coluna.
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
   * Obtém uma String referente a uma coluna (pelo nome da coluna) de uma
   * determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
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
   * Obtém uma String (toString()) referente a uma coluna (pelo índice da
   * coluna) de uma determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
   * @param columnIndex_ - Número da coluna.
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
   * Obtém uma String (toString) referente a uma coluna (pelo nome da coluna) de
   * uma determinada linha (pelo índice da linha).
   * 
   * @param rowIndex_ - Número da linha.
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
   * @param columnCriteria_ - nome da coluna que será o critério de ordenação do
   *          DataSet.
   * @param ascending_ - true: indica que o sentido de ordenação é ascendente.
   *          false, caso contrário.
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
   * @param columnCriteria_ - índice da coluna que será o critério de ordenação
   *          do DataSet.
   * @param ascending_ - true: indica que o sentido de ordenação é ascendente.
   *          false, caso contrário.
   */
  public void sort( int columnCriteria_, boolean ascending_ )
  {
    quickSort( 0, m_rowSet.size() - 1, columnCriteria_, ascending_ );
  }

  /**
   * 
   * Ordena as linhas do DataSet.
   * 
   * @param columnCriteria_ - lista de nomes das colunas que serão o critério de
   *          ordenação do DataSet.
   * @param ascending_ - true: indica que o sentido de ordenação é ascendente.
   *          false, caso contrário.
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
   * @param columnCriteria_ - lista de índices das colunas que serão o critério
   *          de ordenação do DataSet.
   * @param ascending_ - true: indica que o sentido de ordenação é ascendente.
   *          false, caso contrário.
   */
  public void sort( int[] columnCriteria_, boolean ascending_ )
  {
    
    sort( columnCriteria_, ascending_, new int[] { 0, m_rowSet.size() - 1 }, 0 );
  }

  /**
   * 
   * Ordena uma partição (sub-conjunto de linhas) do DataSet utilizando apenas
   * uma coluna como critério de ordenação.
   * 
   * @param columnCriteria_ - lista de índices das colunas que serão o critério
   *          de ordenação da partição do DataSet.
   * @param ascending_ - true: indica que o sentido de ordenação da partição é
   *          ascendente. false, caso contrário.
   * @param partition_ - array de índices, onde a primeira posição do array
   *          ([0]) determina a linha inicial da partição a ser ordenada e a
   *          última posição do array ([1]) determina a linha final da partição
   *          a ser ordenada.
   * @param currentCriteriaIndex_ - índice, da lista de índices de colunas
   *          critério de ordenação, que contempla o índice da coluna critério
   *          de ordenação da partição.
   */
  private void sort( int[] columnCriteria_, boolean ascending_,
                    int[] partition_, int currentCriteriaIndex_ )
  {
    int lastIndex;

    quickSort( partition_[ 0 ], partition_[ 1 ],
               columnCriteria_[ currentCriteriaIndex_ ], ascending_ );
    // não é ultimo critério de ordenação?
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
   * QuickSort de partições do DataSet.
   * 
   * @param initialLowIndex_ - índice da linha inicial da partição a ser
   *          ordenada.
   * @param initialHighIndex_ - índice da linha final da partição a ser
   *          ordenada.
   * @param columnCriteriaIndex_ - índice da coluna critério de ordenação da
   *          partição.
   * @param ascending_ - true: indica que o sentido de ordenação da partição é
   *          ascendente. false, caso contrário.
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
   * Remove linhas duplicadas no DataSet. O critério que determina a igualdade
   * entre duas linhas é:<br>
   * linhas iguais: todos os objetos de uma linha retornam true na comparação
   * (equals()) com o objeto correspondente da outra linha ou ambos são null.
   * <br>
   * linhas diferentes: caso contrário.
   * 
   * @return número de linhas duplicadas removidas.
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
   * @param rowIndex_ - índice da linha que será removida.
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
   * Troca a posição de duas linhas.
   * 
   * @param leftIndex_ - número de uma das linhas que será trocada de posição.
   * @param rightIndex_ - número de outra linha que será trocada de posição.
   */
  private void swapPositions( int leftIndex_, int rightIndex_ )
  {
    Object swappingObject = m_rowSet.get( leftIndex_ );
    m_rowSet.set( leftIndex_, m_rowSet.get( rightIndex_ ) );
    m_rowSet.set( rightIndex_, swappingObject );
  }

  /**
   * Mostra o conteúdo de todas linhas do DataSet.
   * 
   * @return String que apresenta o conteúdo de todas as colunas de todas as
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
   * realizada operações de cojunto entre estes DataSets. Dois DataSets tem a
   * mesma estrututra se seus m_columnsMappings forem iguais.
   * 
   * @param appendingDataSet_ - DataSet cuja estrutura será comparada à
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
   * possua a mesma estrutura e a ordem das linhas do outro DataSet é mantida.
   * 
   * @param appendingDataSet_ DataSet de mesma estrutura que este DataSet, cujas
   *          linhas serão adicionadas a este DataSet.
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
   *          cuja linha será adicionada a este DataSet.
   * @param appendingRowIndex_ - índice da línha do outro DataSet que será
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
   * Obtém o primeiro (ou último, dependendo do sentido de pesquisa) número da
   * linha de uma partição do DataSet que possui a ocorrência de um objeto em
   * uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - Índice da coluna que será pesquisada.
   * @param fromIndex_ - Início da partição (primeira linha) em que o objeto
   *          será procurado.
   * @param toIndex_ - Fím da partição (última linha) em que o objeto será
   *          procurado.
   * @param ascending_ - true: para pesquisa ascendente. false, caso contrário.
   * @return Índice da linha da primeira (ou última, dependendo do sentido da
   *         pesquisa) ocorrência do objeto. -1 se o objeto não for encontrado.
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
   * Valida os parâmetros informados na pesquisa de ocorrência de objetos.
   * 
   * @param columnIndex_ - Índice da coluna que será pesquisada.
   * @param fromIndex_ - Início da partição (primeira linha) em que o objeto
   *          será procurado.
   * @param toIndex_ - Fím da partição (última linha) em que o objeto será
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
   * Obtém o primeiro número da linha de uma partição do DataSet que possui a
   * ocorrência de um objeto em uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - Índice da coluna que será pesquisada.
   * @param fromIndex_ - Início da partição (primeira linha) em que o objeto
   *          será procurado.
   * @param toIndex_ - Fím da partição (última linha) em que o objeto será
   *          procurado.
   * @param ascending_ - true: para pesquisa ascendente. false, caso contrário.
   * @return Índice da linha da primeira ocorrência do objeto. -1 se o objeto
   *         não for encontrado.
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
   * Obtém o primeiro número da linha que possui a ocorrência de um objeto em
   * uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - Índice da coluna que será pesquisada.
   * @return Índice da linha da primeira ocorrência do objeto. -1 se o objeto
   *         não for encontrado.
   */
  public int firstOccurrenceOf( Object value_, int columnIndex_ )
  {
    int firstOccurenceOf = this.firstOccurrenceOf( value_, columnIndex_, 0,
                                                   this.size() - 1 );
    return firstOccurenceOf;
  }

  /**
   * 
   * Obtém o primeiro número da linha de uma partição do DataSet que possui a
   * ocorrência de um objeto em uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnName_ - Nome da coluna que será pesquisada.
   * @param fromIndex_ - Início da partição (primeira linha) em que o objeto
   *          será procurado.
   * @param toIndex_ - Fím da partição (última linha) em que o objeto será
   *          procurado.
   * @return Índice da linha da primeira ocorrência do objeto. -1 se o objeto
   *         não for encontrado.
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
   * Obtém o primeiro número da linha que possui a ocorrência de um objeto em
   * uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnName_ - Nome da coluna que será pesquisada.
   * @return Índice da linha da primeira ocorrência do objeto. -1 se o objeto
   *         não for encontrado.
   */
  public int firstOccurrenceOf( Object value_, String columnName_ )
  {
    int columnIndex = getColumnIndex( columnName_ );
    int firstOccurenceOf = firstOccurrenceOf( value_, columnIndex );
    return firstOccurenceOf;
  }

  /**
   * 
   * Obtém o último número da linha de uma partição do DataSet que possui a
   * ocorrência de um objeto em uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - Índice da coluna que será pesquisada.
   * @param fromIndex_ - Início da partição (primeira linha) em que o objeto
   *          será procurado.
   * @param toIndex_ - Fím da partição (última linha) em que o objeto será
   *          procurado.
   * @return Índice da linha da primeira ocorrência do objeto. -1 se o objeto
   *         não for encontrado.
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
   * Obtém o último número da linha que possui a ocorrência de um objeto em uma
   * determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnIndex_ - Índice da coluna que será pesquisada.
   * @return Índice da linha da primeira ocorrência do objeto. -1 se o objeto
   *         não for encontrado.
   */
  public int lastOccurrenceOf( Object value_, int columnIndex_ )
  {
    int lastOccurenceOf = this.lastOccurrenceOf( value_, columnIndex_, 0,
                                                 this.size() - 1 );
    return lastOccurenceOf;
  }

  /**
   * 
   * Obtém o último número da linha de uma partição do DataSet que possui a
   * ocorrência de um objeto em uma determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnName_ - Nome da coluna que será pesquisada.
   * @param fromIndex_ - Início da partição (primeira linha) em que o objeto
   *          será procurado.
   * @param toIndex_ - Fím da partição (última linha) em que o objeto será
   *          procurado.
   * @return Índice da linha da primeira ocorrência do objeto. -1 se o objeto
   *         não for encontrado.
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
   * Obtém o último número da linha que possui a ocorrência de um objeto em uma
   * determinada coluna.
   * 
   * Importante ressaltar que o estado de ordenação do DataSet é determinante no
   * resultado da pesquisa.
   * 
   * @param value_ - Objeto a ser pesquisado.
   * @param columnName_ - Nome da coluna que será pesquisada.
   * @return Índice da linha da última ocorrência do objeto. -1 se o objeto não
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
   * Obtém um ArrayList com os valores das colunas de uma linha do DataSet.
   * 
   * @param rowIndex_ - Índice da linha.
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
   * Faz a verificação dos parâmetros utilizados nas chamandas dos métodos
   * responsáveis por outerJoin.
   * 
   * @param joinnedDataSet_ - DataSet cujos dados serão outerJoined.
   * @param addingDataSetColumnNamesJoinCriteria_ - nomes das colunas do
   *          DataSet, que será outerJoined, que serão o critério para
   *          comparação.
   * @param addedDataSetColumnNamesCriteria_ - nomes das colunas deste DataSet
   *          que serão o critério para comparação.
   * @param joinnedColumnNames_ - nomes das colunas do DataSet, que será
   *          outerJoinned, que serão adicionadas a este DataSet.
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
   * Faz o outerJoin entre este dois DataSets. A este DataSet serão incorporadas
   * colunas de outros DataSets e estas serão preenchidas com os valores do
   * outro DataSet com base nas colunas critério de comparação. Para as linhas
   * deste DataSet que não fizerem match com as linhas do outro DataSet, as
   * colunas adicionadas serão preenchidas com null.
   * 
   * @param joinnedDataSet_ - DataSet cujos dados serão outerJoined.
   * @param addingDataSetColumnNamesJoinCriteria_ - nomes das colunas do
   *          DataSet, que será outerJoined, que serão o critério para
   *          comparação.
   * @param addedDataSetColumnNamesCriteria_ - nomes das colunas deste DataSet
   *          que serão o critério para comparação.
   * @param joinnedColumnNames_ - nomes das colunas do DataSet, que será
   *          outerJoinned, que serão adicionadas a este DataSet.
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
   * @param joinnedColumnNames_ - nomes das colunas que serão adicionadas.
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
   * Constrói uma mapa de linhas para um DataSet. <br>
   * Chave do Mapa: Lista de valores de colunas que serão critério de pesquisa.
   * <br>
   * Valores do Mapa: Lista de Valores de colunas que serão adicionadas a este
   * DataSet.
   * 
   * @param joinnedDataSet_ - DataSet que será outerJoined a este DataSet.
   * @param addedDataSetColumnNamesCriteria_ - Nomes das colunas que serão
   *          critério de join, do DataSet que será outerJoined a este DataSet.
   * @param joinnedColumnNames_ - Nomes das colunas que serão adicionadas, do
   *          DataSet que será outerJoined a este DataSet.
   * @return Mapa de linhas das colunas que serão adicionadas a este DataSet
   *         pelos nomes das colunas que serão o critério de join, do DataSet
   *         que será outerJoined a este DataSet.
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
   * Constrói uma lista de valores de colunas de uma linha de um DataSet.
   * 
   * @param dataSet_ - DataSet que será origem da lista de valores.
   * @param keyColumnNames_ - nomes das colunas do DataSet que serão adicionadas
   *          a lista.
   * @param rowIndex_ - número da linha do DataSet que contém os valores que
   *          irão compor a lista.
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
   * Constrói uma lista de valores de colunas de uma linha de um DataSet.
   * 
   * @param joinnedDataSet_ - DataSet que será origem da lista de valores.
   * @param joinnedColumnNames_ - nomes das colunas do DataSet que serão
   *          adicionadas a lista.
   * @param rowIndex_ - número da linha do DataSet que contém os valores que
   *          irão compor a lista.
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
   * Obtém uma lista de valores de uma coluna. Se a lista para a coluna desejada
   * já existir em cache, a lista em cache é retornada. Caso contrário, constroi
   * a lista e adiciona a lista ao cache, identificada pelo índice da coluna da
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
   * que houver uma alteração no conjunto de informações do DataSet (por ex.;
   * outerJoin) ou houver uma alteração no conjunto de dados (incluindo a ordem
   * dos dados) (por ex.: sort, append)
   *  
   */
  private void resetColumnListCache()
  {
    m_columnsListCache.clear();
  }

  /**
   * 
   * Constrói uma nova instância de DataSet a partir deste DataSet, com a mesma
   * estrutrutura de colunas, inicializada com uma cópia de uma partição das
   * linhas.
   * 
   * @param fromIndex_ - índice da primeira linha da partição que será copiada.
   * @param toIndex_ - índice da última linha da partição que será copiada.
   * @return Nova instância de DataSet com a mesma estrutura deste DataSet e uma
   *         cópia de uma partição deste DataSet.
   */
  abstract public DataSet newDataSetByRange( int fromIndex_, int toIndex_ );

  /**
   * 
   * Constrói uma nova instância de DataSet a partir deste DataSet, com a mesma
   * estrutrutura de colunas, inicializada com uma cópia de uma linha deste
   * DataSet.
   * 
   * @param lineNumber_ - número da linha que será copiada à nova instância do
   *          DataSet.
   * @return Nova instância de DataSet a partir deste DataSet, com a mesma
   *         estrutrutura de colunas, inicializada com uma cópia de uma linha
   *         deste DataSet.
   */
  abstract public DataSet newDataSetByLineNumber( int lineNumber_ );

  /**
   * 
   * Constrói uma nova instância de DataSet a partir deste DataSet, com a mesma
   * estrutrutura de colunas.
   * 
   * @return nova instância de DataSet a partir deste DataSet, com a mesma
   *         estrutrutura de colunas.
   */
  abstract public DataSet newEmptyDataSet();

  /**
   * 
   * Compara duas linhas do DataSet e verifica qual é "maior". A definição de
   * "maior" é resultado da comparação do valor de uma coluna para as duas
   * linhas.
   * 
   * @param comparingRow1_ - Linha1 que será comparada.
   * @param comparingRow2_ - Linha2 que será comparada.
   * @param columnCriteriaIndex_ - índice da coluna que será utilizada para a
   *          comparação.
   * @param ascending_ - Indica se a comparação será ascendente (true) ou
   *          descendente (false)
   * @return na comparação ascendente: 0 se forem iguais; um valor &lt; 0 se a
   *         Linha1 for menor que a Linha2; um valor &gt; 0 se a Linha1 for
   *         maior que a Linha2. <br>
   *         A comparação descendente, é o valor da comparação ascendente
   *         multiplicada por -1.
   *  
   */
  abstract protected int compare( ArrayList comparingRow1_,
                                 ArrayList comparingRow2_,
                                 int columnCriteriaIndex_, boolean ascending_ );

}