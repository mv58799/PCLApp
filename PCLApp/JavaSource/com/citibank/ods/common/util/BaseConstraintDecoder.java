package com.citibank.ods.common.util;

import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.logger.ApplicationLogger;
import com.citibank.ods.common.logger.LoggerFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Clase base para a construção de classes de decodificação de constantes, seja
 * retornando uma única decodificação ou uma lista de decodificações
 * 
 * @see package com.citibank.ods.common.util;
 * @version 1.0
 * @author bruno.zanetti,Mar 14, 2007
 *  
 */

public abstract class BaseConstraintDecoder
{

  public static final String C_COLUMN_NAME_CODE = "CODE";

  public static final String C_COLUMN_NAME_DESCRIPTION = "DESCRIPTION";

  protected static class DecoderDataSet extends ResultSetDataSet
  {
    private DecoderDataSet()
    {
    }

    private DecoderDataSet( String[] orderedColumnNames_ )
    {
      super();

      buildColumnMapping( orderedColumnNames_ );
      m_rowSet = new ArrayList();
    }

    protected DecoderDataSet( String[] orderedColumnNames_,
                             String[] orderedCodes_,
                             String[] orderedDescriptions_ )
    {
      super();

      if ( orderedColumnNames_ == null || orderedColumnNames_.length < 2 )
      {
        throw new UnexpectedException( "orderedCodes_ must have length 2" );
      }
      // Constrói colunas
      buildColumnMapping( new String[] { orderedColumnNames_[ 0 ],
                                        orderedColumnNames_[ 1 ] } );
      m_rowSet = new ArrayList();

      // Validações
      if ( orderedCodes_ == null || orderedCodes_.length == 0 )
      {
        throw new UnexpectedException( "orderedCodes_ cannot be null or empty" );
      }
      if ( orderedDescriptions_ == null || orderedDescriptions_.length == 0 )
      {
        throw new UnexpectedException(
                                       "orderedDescriptions_ cannot be null or empty" );
      }
      if ( orderedCodes_.length != orderedDescriptions_.length )
      {
        throw new UnexpectedException(
                                       "orderedCodes_ and orderedDescriptions_ must have the same length" );
      }

      // Constrói linhas
      for ( int i = 0; i < orderedCodes_.length; i++ )
      {
        this.addRow( new String[] { orderedCodes_[ i ],
                                   orderedDescriptions_[ i ] } );
      }
    }

    private void buildColumnMapping( String[] orderedColumnNames_ )
    {
      HashMap columnMapping = new HashMap();

      if ( orderedColumnNames_ == null || orderedColumnNames_.length == 0 )
      {
        throw new UnexpectedException(
                                       "orderedColumnNames_ cannot be null or empty" );
      }

      for ( int i = 0; i < orderedColumnNames_.length; i++ )
      {
        if ( orderedColumnNames_[ i ] == null )
        {
          throw new UnexpectedException(
                                         "orderedColumnNames_ has a null pointer at index ["
                                                                                  + i
                                                                                  + "]." );
        }

        columnMapping.put( orderedColumnNames_[ i ], new Integer( i ) );
      }

      m_columnMapping = columnMapping;
    }

    public void addRow( ArrayList columnsValues_ )
    {
      if ( columnsValues_ == null )
      {
        throw new UnexpectedException( "columnsValues_ cannot be null." );
      }
      if ( columnsValues_.size() != m_columnMapping.size() )
      {
        throw new UnexpectedException(
                                       "columnsValues_ size ["
                                                                              + columnsValues_.size()
                                                                              + "] differs from current DataSetRow size ["
                                                                              + m_columnMapping.size()
                                                                              + "]." );
      }
      m_rowSet.add( columnsValues_ );
    }

    public void addRow( Object[] columnsValues_ )
    {
      if ( columnsValues_ == null || columnsValues_.length == 0 )
      {
        throw new UnexpectedException(
                                       "columnsValues_ cannot be null or empty." );
      }
      if ( columnsValues_.length != m_columnMapping.size() )
      {
        throw new UnexpectedException(
                                       "columnsValues_ size ["
                                                                              + columnsValues_.length
                                                                              + "] differs from current DataSetRow size ["
                                                                              + m_columnMapping.size()
                                                                              + "]." );
      }
      ArrayList columnsValues = new ArrayList();
      for ( int i = 0; i < columnsValues_.length; columnsValues.add( columnsValues_[ i++ ] ) )
      {
        ;
      }
      m_rowSet.add( columnsValues );
    }
  }
  public static void printSqlCommand(String sqlCommand){

	  //Teste com logger: Ronaldo G&P Java Team - Inicializa factory de Login
	  LoggerFactory.initialize();
			
	  //Recupera instância de Aplication
	  ApplicationLogger applicationLogger = LoggerFactory.getApplicationLoggerInstance();
	  applicationLogger.info(sqlCommand); 		
  }
 	
}