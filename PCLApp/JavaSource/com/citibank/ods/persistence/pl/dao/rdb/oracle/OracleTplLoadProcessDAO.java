package com.citibank.ods.persistence.pl.dao.rdb.oracle;
import com.citibank.ods.persistence.util.CitiStatement;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.connection.rdb.ManagedRdbConnection;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.ResultSetDataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.exception.UnexpectedException;
import com.citibank.ods.common.util.BaseConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplLoadProcessEntity;
import com.citibank.ods.entity.pl.TplLoadProcessEntity;
import com.citibank.ods.entity.pl.valueobject.TplLoadProcessEntityVO;
import com.citibank.ods.persistence.pl.dao.TplLoadProcessDAO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Processos de
 * Carga.
 */
public class OracleTplLoadProcessDAO extends BaseOracleTplLoadProcessDAO
    implements TplLoadProcessDAO
{
  // Tabela TPL_LOAD_PROCESS
  private static final String C_TPL_LOAD_PROCESS = C_PL_SCHEMA
                                                   + "TPL_LOAD_PROCESS";

  // Tabela TPL_ENTRY_INTER
  private static final String C_TPL_ENTRY_INTER = C_PL_SCHEMA
                                                  + "TPL_ENTRY_INTER";

  //Tabela tpl_load_proc_exec_request
  private static final String C_TPL_LOAD_PROC_EXEC_REQUEST = C_PL_SCHEMA
                                                             + "TPL_LOAD_PROC_EXEC_REQUEST";

  //Alias da tabela TPL_LOAD_PROCESS
  private static final String C_TPL_LOAD_PROCESS_ALIAS = "LOAD_PROC";

  //Alias da tabela TPL_LOAD_PROC_EXEC_REQUEST
  private static final String C_TPL_LOAD_PROC_EXEC_REQUEST_ALIAS = "REEXEC";

  //Alias da tabela TPL_LOAD_PROC_EXEC
  private static final String C_TPL_LOAD_PROC_EXEC_ALIAS = "LOAD_PROC_EXEC";

  //Alias da tabela TPL_LOAD_PROC_EXEC - MAX
  private static final String C_TPL_LOAD_PROC_EXEC_MAX_ALIAS = "LOAD_PROC_EXEC_MAX";

  //Tabela TPL_LOAD_PROC_EXEC
  private static final String C_TPL_LOAD_PROC_EXEC = C_PL_SCHEMA
                                                     + "TPL_LOAD_PROC_EXEC";

  //Quantidade de registros lidos na execução do processo
  private static final String C_READ_REC_QTY = "READ_REC_QTY";

  //Quantidade de registros lidos na execução do processo
  private static final String C_INP_REC_QTY = "INP_REC_QTY";

  //Quantidade de registros atualizados na execução do processo
  private static final String C_UPD_REC_QTY = "UPD_REC_QTY";

  //Quantidade de registros rejeitados na execução do processo
  private static final String C_REJC_REC_QTY = "REJC_REC_QTY";

  //Número sequencial referencial da execução
  private static final String C_EXEC_SEQ_NBR = "EXEC_SEQ_NBR";

  //Duração da execução
  private static final String C_EXEC_PER_SEC_QTY = "EXEC_PER_SEC_QTY";

  //Tabela de Processo x Interface
  private static final String C_TPL_LOAD_PROC_ENTRY_INTER = C_PL_SCHEMA
                                                            + "TPL_LOAD_PROC_ENTRY_INTER";

  //Alias da tabela de Processo x Interface
  private static final String C_TPL_LOAD_PROC_ENTRY_INTER_ALIAS = "LOAD_PROC_ENTRY_INTER";

  //Alias da tabela de Interface
  private static final String C_TPL_ENTRY_INTER_ALIAS = "ENTRY_INTER";

  /**
   * Retorna Data Set com os campos do grid da consulta em lista.
   * 
   * @param loadProcNbr - Código do processo.
   * @param loadProcText - Descrição do processo.
   * @param execRefDate - Data de refência de execução do processo de carga.
   * @return DataSet - Data Set com os campos do grid da consulta em lista.
   */
  public DataSet list( BigInteger loadProcNbr_, String loadProcText_,
                      Date execRefDate_, String reexecutionInd_, Date lastExecDate_ , String sysCode )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSetDataSet rsds = null;
    StringBuffer query = new StringBuffer();

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " + C_LOAD_PROC_NBR + ", " + C_LOAD_PROC_TEXT
                    + ", " );
      query.append( C_EXEC_REF_DATE + ", " + C_LAST_EXEC_DATE + ", "
                    + C_READ_REC_QTY + ", " );
      query.append( C_REJC_REC_QTY + ", " + C_UPD_REC_QTY + ", "
                    + C_INP_REC_QTY + ", " );
      query.append( C_EXEC_PER_SEC_QTY + ", " + C_SYS_CODE +  ", QUANTIDADE " );
      query.append( " FROM (SELECT " );
      query.append( C_TPL_LOAD_PROCESS_ALIAS + "." + C_LOAD_PROC_NBR + ", " );
      query.append( C_TPL_LOAD_PROCESS_ALIAS + "." + C_LOAD_PROC_TEXT + ", " );
      query.append( C_TPL_LOAD_PROCESS_ALIAS + "." + C_EXEC_REF_DATE + ", " );
      query.append( C_TPL_LOAD_PROCESS_ALIAS + "." + C_LAST_EXEC_DATE + ", " );
      query.append( C_TPL_LOAD_PROC_EXEC_ALIAS + "." + C_READ_REC_QTY + ", " );
      query.append( C_TPL_LOAD_PROC_EXEC_ALIAS + "." + C_UPD_REC_QTY + ", " );
      query.append( C_TPL_LOAD_PROC_EXEC_ALIAS + "." + C_REJC_REC_QTY + ", " );
      query.append( C_TPL_LOAD_PROC_EXEC_ALIAS + "." + C_INP_REC_QTY + ", " );
      query.append( C_TPL_LOAD_PROC_EXEC_ALIAS + "." + C_EXEC_PER_SEC_QTY 
                    + ", " );
		query.append( C_TPL_LOAD_PROCESS_ALIAS + "." + C_SYS_CODE + ", " );                    
      query.append( "(SELECT COUNT( " + C_TPL_LOAD_PROC_EXEC_REQUEST_ALIAS
                    + "." + C_LOAD_PROC_NBR + ") COUNT_PROC_NBR FROM " );
      query.append( C_TPL_LOAD_PROC_EXEC_REQUEST + " "
                    + C_TPL_LOAD_PROC_EXEC_REQUEST_ALIAS );
      query.append( " WHERE " + C_TPL_LOAD_PROCESS_ALIAS + "."
                    + C_LOAD_PROC_NBR + " = " );
      query.append( C_TPL_LOAD_PROC_EXEC_REQUEST_ALIAS + "." + C_LOAD_PROC_NBR
                    + "(+)) QUANTIDADE " );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROCESS + " " + C_TPL_LOAD_PROCESS_ALIAS + "," );
      query.append( "( SELECT " + C_TPL_LOAD_PROC_EXEC_ALIAS + "."
                    + C_LOAD_PROC_NBR + ", " );
      query.append( C_READ_REC_QTY + ", " + C_REJC_REC_QTY + ", "
                    + C_INP_REC_QTY + ", " );
      query.append( C_UPD_REC_QTY + ", " + C_EXEC_PER_SEC_QTY );
      query.append( " FROM " + C_TPL_LOAD_PROC_EXEC + " "
                    + C_TPL_LOAD_PROC_EXEC_ALIAS + ", " );
      query.append( "( SELECT " + C_LOAD_PROC_NBR + ", MAX (" + C_EXEC_SEQ_NBR
                    + ") SEQ_NBR " );
      query.append( " FROM " + C_TPL_LOAD_PROC_EXEC + " GROUP BY "
                    + C_LOAD_PROC_NBR + ")" + C_TPL_LOAD_PROC_EXEC_MAX_ALIAS );
      query.append( " WHERE " );
      query.append( C_TPL_LOAD_PROC_EXEC_ALIAS + "." + C_LOAD_PROC_NBR + " = " );
      query.append( C_TPL_LOAD_PROC_EXEC_MAX_ALIAS + "." + C_LOAD_PROC_NBR
                    + " AND " );
      query.append( C_TPL_LOAD_PROC_EXEC_ALIAS + "." + C_EXEC_SEQ_NBR + " = " );
      query.append( C_TPL_LOAD_PROC_EXEC_MAX_ALIAS + ".SEQ_NBR) "
                    + C_TPL_LOAD_PROC_EXEC_ALIAS );
      query.append( " WHERE " + C_TPL_LOAD_PROCESS_ALIAS + "."
                    + C_LOAD_PROC_NBR + " = " );
      query.append( C_TPL_LOAD_PROC_EXEC_ALIAS + "." + C_LOAD_PROC_NBR
                    + "(+)) A " );
      query.append( " WHERE " + C_LOAD_PROC_NBR + " > 0 " );

      String criteria = "";
      String criteriaHaving = "";

      if ( loadProcNbr_ != null )
      {
        criteria = C_LOAD_PROC_NBR + " = ? AND ";
      }
      if ( loadProcText_ != null && !loadProcText_.equals( "" ) )
      {
        criteria = criteria + " UPPER(\"" + C_LOAD_PROC_TEXT
                   + "\") LIKE ? AND ";
      }
      if ( execRefDate_ != null )
      {
        criteria = criteria + " TRUNC (" + C_EXEC_REF_DATE
                   + ", \'DD\') = ? AND ";
      }
      
	  if ( lastExecDate_ != null )
	  {
	    criteria = criteria + C_LAST_EXEC_DATE  + " = ? AND ";
	  }
	  
	  if(!sysCode.equals("") && sysCode != null){
		criteria = criteria + C_SYS_CODE  + " = TRIM(UPPER(?)) AND ";
	  }      

      if ( reexecutionInd_.equals( "S" ) )
      {
        criteria = criteria + " QUANTIDADE > 0 AND ";
      }

      if ( reexecutionInd_.equals( "N" ) )
      {
        criteria = criteria + " QUANTIDADE  = 0  AND";

      }

      if ( criteria.length() > 0 )
      {
        criteria = criteria.substring( 0, criteria.length() - 5 );
        query.append( " AND " + criteria );
      }

      query.append( " ORDER BY " + C_LOAD_PROC_TEXT + ", " );
      query.append( C_LOAD_PROC_NBR );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      int count = 1;

      if ( loadProcNbr_ != null )
      {
        preparedStatement.setLong( count++, loadProcNbr_.longValue() );
      }
      if ( loadProcText_ != null && !loadProcText_.equals( "" ) )
      {
        preparedStatement.setString( count++, "%" + loadProcText_.toUpperCase() + "%" );
      }
      if ( execRefDate_ != null )
      {
        preparedStatement.setDate( count++, new java.sql.Date( execRefDate_.getTime() ) );
      }
      
	  if ( lastExecDate_ != null )
	  {
	    preparedStatement.setDate( count++, new java.sql.Date( lastExecDate_.getTime() ) );
	  }
	  
	  if(!sysCode.equals("") && sysCode != null){
		preparedStatement.setString( count, sysCode );
	  }

	  preparedStatement.replaceParametersInQuery(query.toString());
      resultSet = preparedStatement.executeQuery();

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

  /**
   * Retorna uma entidade com os campos de detalhes.
   * 
   * @param loadProcessEntity_ - Entidade que possui a chave usada para busca na
   *          tabela.
   * @return BaseTplLoadProcessEntity - Entidade com os campos de detalhes.
   */
  public BaseTplLoadProcessEntity find(
                                       BaseTplLoadProcessEntity loadProcessEntity_ )
  {
    ManagedRdbConnection connection = null;
    CitiStatement preparedStatement = null;
    ResultSet resultSet = null;
    StringBuffer query = new StringBuffer();
    ArrayList loadProcessEntities;
    BaseTplLoadProcessEntity loadProcessEntity = null;

    try
    {
      connection = OracleODSDAOFactory.getConnection();
      query.append( "SELECT " );
      query.append( C_LOAD_PROC_NBR + ", " );
      query.append( C_LOAD_PROC_TEXT + ", " );
      query.append( C_EXEC_OBLIG_IND + ", " );
      query.append( C_PROC_BLOCK_IND + ", " );
      query.append( C_LOAD_PRDCTY_CODE + ", " );
      query.append( C_SYS_CODE + ", " );
      query.append( C_SYS_SEG_CODE + ", " );
      query.append( C_EXEC_REF_DATE + ", " );
      query.append( C_LAST_EXEC_DATE );
      query.append( " FROM " );
      query.append( C_TPL_LOAD_PROCESS );
      query.append( " WHERE " );
      query.append( C_LOAD_PROC_NBR + " =? " );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong(
                         1,
                         loadProcessEntity_.getData().getLoadProcNbr().longValue() );

      resultSet = preparedStatement.executeQuery();
	  preparedStatement.replaceParametersInQuery(query.toString());

      loadProcessEntities = instantiateFromResultSet( resultSet );

      if ( loadProcessEntities.size() == 0 )
      {
        throw new NoRowsReturnedException();
      }
      else if ( loadProcessEntities.size() > 1 )
      {
        throw new UnexpectedException( C_ERROR_TOO_MANY_ROWS_RETURNED );
      }
      else
      {
        loadProcessEntity = ( BaseTplLoadProcessEntity ) loadProcessEntities.get( 0 );
      }

      return loadProcessEntity;
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

  /**
   * Instancia entidades a partir do ResultSet.
   * 
   * @param resultSet_ - ResultSet utilizado para instanciar entidades.
   * @return ArrayList - Entidades instanciadas a partir do ResultSet.
   */
  private ArrayList instantiateFromResultSet( ResultSet resultSet_ )
  {

    TplLoadProcessEntity tplLoadProcessEntity;
    TplLoadProcessEntityVO tplLoadProcessEntityVO;
    ArrayList oracleTplLoadProcessEntities = new ArrayList();

    try
    {
      while ( resultSet_.next() )
      {
        tplLoadProcessEntity = new TplLoadProcessEntity();
        tplLoadProcessEntityVO = ( TplLoadProcessEntityVO ) tplLoadProcessEntity.getData();

        tplLoadProcessEntityVO.setLoadProcNbr( new BigInteger(
                                                               resultSet_.getString( this.C_LOAD_PROC_NBR ) ) );
        tplLoadProcessEntityVO.setLoadProcText( resultSet_.getString( this.C_LOAD_PROC_TEXT ) );
        tplLoadProcessEntityVO.setExecObligInd( resultSet_.getString( this.C_EXEC_OBLIG_IND ) );
        tplLoadProcessEntityVO.setProcBlockInd( resultSet_.getString( this.C_PROC_BLOCK_IND ) );
        tplLoadProcessEntityVO.setLoadPrdctyCode( new BigInteger(
                                                                  resultSet_.getString( this.C_LOAD_PRDCTY_CODE ) ) );
        tplLoadProcessEntityVO.setSysCode( resultSet_.getString( this.C_SYS_CODE ) );
        tplLoadProcessEntityVO.setSysSegCode( resultSet_.getString( this.C_SYS_SEG_CODE ) );

        tplLoadProcessEntityVO.setExecRefDate( resultSet_.getTimestamp( this.C_EXEC_REF_DATE ) );
        tplLoadProcessEntityVO.setLastExecDate( resultSet_.getTimestamp( this.C_LAST_EXEC_DATE ) );

        oracleTplLoadProcessEntities.add( tplLoadProcessEntity );
      }
    }
    catch ( SQLException e )
    {
      throw new UnexpectedException( e.getErrorCode(),
                                     C_ERROR_INSTANTIATE_FROM_RESULT_SET, e );
    }
    return oracleTplLoadProcessEntities;
  }

  public DataSet listInterfaceByProcess( BigInteger loadProcNbr_ )
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
      query.append( C_TPL_LOAD_PROC_ENTRY_INTER_ALIAS + "."
                    + C_ENTRY_INTER_CODE + ", " );
      query.append( C_TPL_ENTRY_INTER_ALIAS + "." + C_ENTRY_INTER_TEXT + ", " );
      query.append( C_TPL_ENTRY_INTER_ALIAS + "." + C_ENTRY_TYPE_CODE + ", " );
      query.append( C_TPL_LOAD_PROCESS_ALIAS + "." + C_LOAD_PROC_NBR );
      query.append( " FROM " );
      query.append( C_TPL_ENTRY_INTER + " " + C_TPL_ENTRY_INTER_ALIAS + ", " );
      query.append( C_TPL_LOAD_PROC_ENTRY_INTER + " "
                    + C_TPL_LOAD_PROC_ENTRY_INTER_ALIAS + ", " );
      query.append( C_TPL_LOAD_PROCESS + " " + C_TPL_LOAD_PROCESS_ALIAS );
      query.append( " WHERE " );
      query.append( C_TPL_LOAD_PROCESS_ALIAS + "." + C_LOAD_PROC_NBR + " = " );
      query.append( C_TPL_LOAD_PROC_ENTRY_INTER_ALIAS + "." + C_LOAD_PROC_NBR
                    + " AND " );
      query.append( C_TPL_LOAD_PROC_ENTRY_INTER_ALIAS + "."
                    + C_ENTRY_INTER_CODE + " = " );
      query.append( C_TPL_ENTRY_INTER_ALIAS + "." + C_ENTRY_INTER_CODE
                    + " AND " );
      query.append( C_TPL_LOAD_PROCESS_ALIAS + "." + C_LOAD_PROC_NBR + " = ?" );

      preparedStatement = new CitiStatement(connection.prepareStatement( query.toString() ));

      preparedStatement.setLong( 1, loadProcNbr_.longValue() );

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