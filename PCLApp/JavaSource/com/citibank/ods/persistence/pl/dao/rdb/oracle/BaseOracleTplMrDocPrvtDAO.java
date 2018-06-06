package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplMrDocPrvtDAO;

/**
 * @author m.nakamura
 * 
 * Classe abstrata para acesso ao banco de dados de Memória de Risco.
 */
public abstract class BaseOracleTplMrDocPrvtDAO extends BaseOracleDAO implements
    BaseTplMrDocPrvtDAO
{

  // Codigo Documento MR
  protected static final String C_MR_DOC_CODE = "PRVT_MR_CODE";

  // Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
  protected static final String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  // Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
  protected static final String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  // Descricao do Instrucao Permanente
  protected static final String C_MR_DOC_TEXT = "PRVT_MR_TEXT";

  // Indicador de Utilizacao de Conta CCI: 'S' (Sim), 'N' (Nao)
  protected static final String C_MR_INVST_CUR_ACCT_IND = "PRVT_MR_INVST_CUR_ACCT_IND";

  // Codigo da Conta Produto
  protected static final String C_PROD_ACCT_CODE = "PROD_ACCT_CODE";

  // Codigo da Sub-conta Produto
  protected static final String C_PROD_UNDER_ACCT_CODE = "PROD_UNDER_ACCT_CODE";

  //Código do produto
  protected static final String C_PROD_CODE = "PROD_CODE";

  //Código do produto - Conta corrente
  protected static final String C_PROD_CUR_CODE = "010";

  //Segmento do Sistema - Conta Corrente
  protected static final int C_SYS_SEG_VALUE = 1;

  //Código do produto - Conta corrente
  protected static final String C_SYS_SEG_CODE = "SYS_SEG_CODE";

  //Nome do cliente
  protected static final String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";
}