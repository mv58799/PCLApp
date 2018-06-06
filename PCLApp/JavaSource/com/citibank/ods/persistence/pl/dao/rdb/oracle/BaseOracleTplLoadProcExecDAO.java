package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecDAO;

/**
 * @author m.nakamura
 *  
 * Implementação da interface para acesso ao banco de dados de Execução de Processos de Carga.
 */
public abstract class BaseOracleTplLoadProcExecDAO extends BaseOracleDAO
    implements BaseTplLoadProcExecDAO
{

  // Data de termino da execucao
  protected String C_EXEC_END_DATE = "EXEC_END_DATE";

  // Quantidade de segundos da duracao de uma determinada execucao batch.
  protected String C_EXEC_PER_SEC_QTY = "EXEC_PER_SEC_QTY";

  // Data de referencia de execucao
  protected String C_EXEC_REF_DATE = "EXEC_REF_DATE";

  // Numero de Sequencia / Referencial da execucao
  protected String C_EXEC_SEQ_NBR = "EXEC_SEQ_NBR";

  // Quantidade total de registros inseridos na execucao de determinado Job.
  protected String C_INP_REC_QTY = "INP_REC_QTY";

  // Data e hora da ultima atualizacao efetuada pelo usuario.
  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro.
  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  // Codigo da situacao da execucao da carga
  protected String C_LOAD_EXEC_SIT_CODE = "LOAD_EXEC_SIT_CODE";

  // Numero do processo de carga: Codigo do batch que sera executado em
  // umadeterminada carga.
  protected String C_LOAD_PROC_NBR = "LOAD_PROC_NBR";
  
  // Nome do batch que sera executado em uma determinada carga.
  protected String C_LOAD_PROC_TEXT = "LOAD_PROC_TEXT";

  // Indicador de Execucao Manual
  protected String C_MNL_EXEC_IND = "MNL_EXEC_IND";

  // Quantidade total de registros lidos na execucao de determinado Job.
  protected String C_READ_REC_QTY = "READ_REC_QTY";

  // Quantidade total de registros rejeitados na execucao de determinado Job.
  protected String C_REJC_REC_QTY = "REJC_REC_QTY";

  // Quantidade total de registros atualizados na execucao de determinado Job.
  protected String C_UPD_REC_QTY = "UPD_REC_QTY";

  // Data da ultima execucao do processo de carga.
  protected String C_LAST_EXEC_DATE = "LAST_EXEC_DATE";
}