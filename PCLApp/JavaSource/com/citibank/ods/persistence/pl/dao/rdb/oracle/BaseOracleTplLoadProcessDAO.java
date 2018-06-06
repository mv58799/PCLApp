package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplLoadProcessDAO;

/**
 * @author m.nakamura
 *  
 * Implementação da interface para acesso ao banco de dados de Processos de Carga.
 */
public abstract class BaseOracleTplLoadProcessDAO extends BaseOracleDAO
    implements BaseTplLoadProcessDAO
{

  // Codigo da interface de entrada
  protected String C_ENTRY_INTER_CODE = "ENTRY_INTER_CODE";
  
  // Descrição da interface de entrada
  protected String C_ENTRY_INTER_TEXT = "ENTRY_INTER_TEXT";
  
  // Tipo da interface de entrada
  protected String C_ENTRY_TYPE_CODE = "ENTRY_TYPE_CODE";

  // Indicador de Obrigatoriedade de execucao.
  protected String C_EXEC_OBLIG_IND = "EXEC_OBLIG_IND";

  // Data de Referencia de execucao do processo de carga.
  protected String C_EXEC_REF_DATE = "EXEC_REF_DATE";

  // Data da ultima execucao do processo de carga.
  protected String C_LAST_EXEC_DATE = "LAST_EXEC_DATE";

  // Codigo da periodicidade da Carga.
  protected String C_LOAD_PRDCTY_CODE = "LOAD_PRDCTY_CODE";

  // Numero do processo de carga: Codigo do batch que sera executado em
  // uma determinada carga.
  protected String C_LOAD_PROC_NBR = "LOAD_PROC_NBR";

  // Nome do batch que sera executado em uma determinada carga.
  protected String C_LOAD_PROC_TEXT = "LOAD_PROC_TEXT";

  // Indicado bloqueio do processo nos casos de erro.
  protected String C_PROC_BLOCK_IND = "PROC_BLOCK_IND";

  // Codigo do Sistema
  protected String C_SYS_CODE = "SYS_CODE";

  // Segmento do sistema
  protected String C_SYS_SEG_CODE = "SYS_SEG_CODE";

}