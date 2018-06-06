package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplLogicCritDomainDAO;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de domínios de
 * crítica lógica.
 */
public abstract class BaseOracleTplLogicCritDomainDAO extends BaseOracleDAO
    implements BaseTplLogicCritDomainDAO
{
  // Conteudo do dominio
  protected String C_DOMAIN_CNTNT_TEXT = "DOMAIN_CNTNT_TEXT";

  // Numero sequencial / Referencial para o dominio
  protected String C_DOMAIN_SEQ_NBR = "DOMAIN_SEQ_NBR";

  // Indica se havera necessidade de alteracoes no ETL (programa de carga)
  protected String C_LOAD_PROG_UPD_IND = "LOAD_PROG_UPD_IND";

  // Codigo da critica logica da carga.
  protected String C_LOGIC_CRIT_CODE = "LOGIC_CRIT_CODE";

  // Codigo do Status do registro.
  protected String C_REC_STAT_CODE = "REC_STAT_CODE";
}