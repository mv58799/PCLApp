package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplLoadProcExecRequestDAO;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Requerimento de
 * Execução de Processos de Carga.
 */
public abstract class BaseOracleTplLoadProcExecRequestDAO extends BaseOracleDAO
    implements BaseTplLoadProcExecRequestDAO
{

  // Motivo da re-execucao
  protected String C_EXEC_REAS_TEXT = "EXEC_REAS_TEXT";

  // Data de referencia de execucao
  protected String C_EXEC_REF_DATE = "EXEC_REF_DATE";

  // Data e hora da ultima atualiza efetuada pelo usuario.
  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro.
  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  // Numero do processo de carga: Codigo do batch que sera executado em
  // umadeterminada carga.
  protected String C_LOAD_PROC_NBR = "LOAD_PROC_NBR";

  // Descrição do processo.
  protected String C_LOAD_PROC_TEXT = "LOAD_PROC_TEXT";
  
  //Data da última execução do processo
  protected String C_LAST_EXEC_DATE = "LAST_EXEC_DATE";
}