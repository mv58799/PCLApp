package com.citibank.ods.persistence.pl.dao.rdb.oracle;

import com.citibank.ods.common.persistence.dao.rdb.oracle.BaseOracleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTo3ProductAccountDAO;

/**
 * @author m.nakamura
 * 
 * Implementação da interface para acesso ao banco de dados de conta de
 * produtos.
 */
public abstract class BaseOracleTo3ProductAccountDAO extends BaseOracleDAO
    implements BaseTo3ProductAccountDAO
{

  // Código da conta produto
  protected String C_PROD_ACCT_CODE = "PROD_ACCT_CODE";

  // Código da sub conta produto
  protected String C_PROD_UNDER_ACCT_CODE = "PROD_UNDER_ACCT_CODE";

  // Número do cliente no CMS
  protected String C_CUST_NBR = "CUST_NBR";

  // Número do relacionamento do cliente.
  protected String C_RELTN_NBR = "RELTN_NBR";

  // Número da conta corrente associada ao produto.
  protected String C_CUR_ACCT_NBR = "CUR_ACCT_NBR";

  // Código do produto.
  protected String C_PROD_CODE = "PROD_CODE";

  //Descrição do produto.
  protected String C_PROD_NAME = "PROD_NAME";

  // Código do sistema origem do cadastro do produto.
  protected String C_SYS_CODE = "SYS_CODE";

  // Codigo da segmentação do sistema origem do cadastro do produto.
  protected String C_SYS_SEG_CODE = "SYS_SEG_CODE";

  // Número da conta produto no processador de origem.
  protected String C_ORIG_PROD_ACCT_NBR = "ORIG_PROD_ACCT_NBR";

  // Data de abertura/início do contrato da conta produto.
  protected String C_PROD_ACCT_STA_DATE = "PROD_ACCT_STA_DATE";

  // Data de fim/encerramento do contrato da conta produto.
  protected String C_PROD_ACCT_END_DATE = "PROD_ACCT_END_DATE";

  // Código da situação do contrato da conta produto.
  protected String C_PROD_ACCT_SIT_CODE = "PROD_ACCT_SIT_CODE";

  // Status registro.
  protected String C_REC_STAT_CODE = "REC_STAT_CODE";

  //Indicador de carteira adminstrada da conta produro
  protected String C_PROD_ACCT_PORTF_MGMT_CODE = "PROD_ACCT_MGMT_PORTF_TYPE_CODE";

  //Indicador de política 23A
  protected String C_PROD_PLCY_23A_IND = "CITI_GRP_TIE_RELTN_PLCY_IND";

  //Indicador de política 23B
  protected String C_PROD_PLCY_23B_IND = "CITI_GRP_TIE_RSTRN_PLCY_IND";

  //Código Isin
  protected String C_PROD_ACCT_ISIN_CODE = "PROD_ACCT_ISIN_CODE";

  //Código da Entidade Legal
  protected String C_PROD_ACCT_LEGAL_BUS_CODE = "PROD_ACCT_LEGAL_BUS_CODE";

  // Data/hora da última atualização
  protected String C_LAST_UPD_DATE = "LAST_UPD_DATE";

  //Usuário da última atualização
  protected String C_LAST_UPD_USER_ID = "LAST_UPD_USER_ID";

  //Nome do cliente
  protected String C_CUST_FULL_NAME_TEXT = "CUST_FULL_NAME_TEXT";

  //Código do Produto Conta corrente
  protected String C_PROD_CUR_CODE = "010";
  
}