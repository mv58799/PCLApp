package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDAO;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de Crítica Lógica.
 */
public abstract class BaseOracleTplLogicCritDAO extends BaseOracleDAO implements
    BaseTplLogicCritDAO
{
  // Tipo de dados
  protected String C_DATA_TYPE_CODE = "DATA_TYPE_CODE";

  // Data e hora da ultima atualizaca efetuada pelo usuario.
  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro.
  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  // Indica se havera necessidade de alteracoes no ETL (programa de carga)
  protected String C_LOAD_PROG_UPD_IND = "LOAD_PROG_UPD_IND";

  // Codigo da critica logica da carga.
  protected String C_LOGIC_CRIT_CODE = "LOGIC_CRIT_CODE";

  // Indica se ha dominio para esta critica
  protected String C_LOGIC_CRIT_DOM_IND = "LOGIC_CRIT_DOM_IND";

  // Descricao da critica logica da carga
  protected String C_LOGIC_CRIT_TEXT = "LOGIC_CRIT_TEXT";

  // Codigo do Status do registro.
  protected String C_REC_STAT_CODE = "REC_STAT_CODE";
}