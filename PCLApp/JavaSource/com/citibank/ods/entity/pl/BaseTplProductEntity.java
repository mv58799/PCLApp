package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProductEntityVO;
/**
 * Classe que instancia a entidade correspondente a tabela : BaseTplProduct
 * @author leonardo.nakada
 * @date 04/04/2007
 */

public class BaseTplProductEntity extends BaseODSEntity
{

  public static final int C_PROD_CODE_SIZE = 50;
  
  public static final int C_SYS_CODE_SIZE = 5;
  
  public static final int C_SYS_SEG_CODE_SIZE = 2;
  
  public static final int C_PROD_NAME_SIZE = 50;
  
  public static final int C_PROD_TEXT_SIZE = 100;
  
  public static final int C_PROD_FAML_CODE_SIZE = 2;
  
  public static final int C_PROD_FAML_NAME_SIZE = 40;
  
  public static final int C_PROD_CCY_CODE_SIZE = 3;
  
  public static final int C_PROD_ISO_CODE_SIZE = 3;
  
  public static final int C_PROD_ANBID_CODE_SIZE = 20;
  
  public static final int C_PROD_CETIP_CODE_SIZE = 20;
  
  public static final int C_PROD_SELIC_CODE_SIZE= 20;
  
  public static final int C_PROD_BOVESPA_CODE_SIZE = 20;
  
  public static final int C_PROD_BMF_CODE_SIZE = 20;
  
  public static final int C_PROD_CREATE_DATE_SIZE = 12;
  
  public static final int C_PROD_STAT_CODE_SIZE = 1;
  
  public static final int C_PRVT_PROD_AGGR_CODE_SIZE = 5;
  
  public static final int C_PROD_CR_TYPE_CLASS_CODE_SIZE = 1;
  
  public static final int C_PROD_PROC_SYS_CODE_SIZE = 5;
  
  public static final int C_PROD_PROC_SYS_SEG_CODE_SIZE = 2;
  
  public static final int C_PROD_APPRV_DATE_SIZE = 12;
  
  public static final int C_PROD_OPERN_STA_DATE_SIZE = 12;
  
  public static final int C_PROD_CSTDY_CNPJ_NBR_SIZE = 14;
  
  public static final int C_PROD_AUDIT_CNPJ_NBR_SIZE = 14;
  
  public static final int C_PROD_MGMT_CNPJ_NBR_SIZE = 14;
  
  public static final int C_PROD_CTL_CNPJ_NBR_SIZE = 14;
  
  public static final int C_PROD_ADMIN_CNPJ_NBR_SIZE = 14;
  
  public static final int C_CITI_GRP_TIE_RELTN_PLCY_IND_SIZE = 1;
  
  public static final int C_CITI_GRP_TIE_RSTRN_PLCY_IND_SIZE = 1;
  
  /**
   * 20110321 apos a alteracao do compo de risco PROD_RISK_CAT_CODE -> PROD_INVST_RISK_CODE
   * a constante mudou de 4 para 2.
   */
  public static final int C_PROD_INVST_RISK_CODE_SIZE = 2;
  
  public static final int C_PROD_QLFY_CODE_SIZE = 4;
  
  public static final int C_PROD_SUB_FAML_CODE_SIZE = 6;
  
  protected BaseTplProductEntityVO m_data;

  public BaseTplProductEntityVO getData()
  {
    return m_data;
  }

}