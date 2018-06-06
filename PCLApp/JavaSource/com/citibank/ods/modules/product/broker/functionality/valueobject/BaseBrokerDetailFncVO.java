package com.citibank.ods.modules.product.broker.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;

/**
 * @author hamilton.matos
 *  
 */
public class BaseBrokerDetailFncVO extends BaseODSFncVO
{

  public static final String C_BKR_CNPJ_NBR_DESCRIPTION = "CNPJ";

  public static final String C_BKR_NAME_TEXT_DESCRIPTION = "Razão social da corretora";

  public static final String C_BKR_ADDR_TEXT_DESCRIPTION = "Endereço";

  public static final String C_BKR_BMF_MKT_CODE_DESCRIPTION = "Código Mercado Corretora BMF";

  public static final String C_BKR_BOVESPA_MKT_CODE_DESCRIPTION = "Código de Mercado da Corretora Bovespa";

  public static final String C_BKR_RBT_BMF_RATE_DESCRIPTION = "Percentual Repasse BMF";

  public static final String C_BKR_RBT_BOVESPA_RATE_DESCRIPTION = "Percentual Repasse Bovespa";

  public static final String C_BKR_REQ_DATE_DESCRIPTION = "Data Solicitação Aprovação";

  public static final String C_BKR_RNW_DATE_DESCRIPTION = "Data de renovação";

  public static final String C_BKR_APPRV_STAT_CODE_DESCRIPTION = "Status Aprovação";

  public static final String C_BKR_APPRV_DATE_DESCRIPTION = "Data de Aprovação";

  public static final String C_BKR_AUTH_PROC_SIT_TEXT_DESCRIPTION = "Andamento Processo Aprovação";

  public static final String C_BKR_REQ_CR_LIM_LCY_AMT_DESCRIPTION = "Limite Crédito Real Solicitado";

  public static final String C_BKR_APPRV_CR_LIM_LCY_AMT_DESCRIPTION = "Limite de Crédito Dólar Aprovado";

  public static final String C_BKR_REQ_CR_LIM_DLR_AMT_DESCRIPTION = "Limite Crédito USD Solicitado";

  public static final String C_BKR_APPRV_CR_LIM_DLR_AMT_DESCRIPTION = "Limite Crédito Real Aprovado";

  public static final String C_BKR_SUPL_SERV_TEXT_DESCRIPTION = "Serviços Prestados";

  public static final String C_BKR_COMMENT_TEXT_DESCRIPTION = "Observação";

  /**
   * Entity de Corretora
   */
  protected BaseTplBrokerEntity m_baseTplBrokerEntity;

  /**
   * @return Returns baseTplBrokerEntity.
   */
  public BaseTplBrokerEntity getBaseTplBrokerEntity()
  {
    return m_baseTplBrokerEntity;
  }

  /**
   * @param baseTplBrokerEntity_ Field baseTplBrokerEntity to be setted.
   */
  public void setBaseTplBrokerEntity( BaseTplBrokerEntity baseTplBrokerEntity_ )
  {
    m_baseTplBrokerEntity = baseTplBrokerEntity_;
  }
}