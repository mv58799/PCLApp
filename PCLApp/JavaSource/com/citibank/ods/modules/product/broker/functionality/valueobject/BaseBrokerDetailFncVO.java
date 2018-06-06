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

  public static final String C_BKR_NAME_TEXT_DESCRIPTION = "Raz�o social da corretora";

  public static final String C_BKR_ADDR_TEXT_DESCRIPTION = "Endere�o";

  public static final String C_BKR_BMF_MKT_CODE_DESCRIPTION = "C�digo Mercado Corretora BMF";

  public static final String C_BKR_BOVESPA_MKT_CODE_DESCRIPTION = "C�digo de Mercado da Corretora Bovespa";

  public static final String C_BKR_RBT_BMF_RATE_DESCRIPTION = "Percentual Repasse BMF";

  public static final String C_BKR_RBT_BOVESPA_RATE_DESCRIPTION = "Percentual Repasse Bovespa";

  public static final String C_BKR_REQ_DATE_DESCRIPTION = "Data Solicita��o Aprova��o";

  public static final String C_BKR_RNW_DATE_DESCRIPTION = "Data de renova��o";

  public static final String C_BKR_APPRV_STAT_CODE_DESCRIPTION = "Status Aprova��o";

  public static final String C_BKR_APPRV_DATE_DESCRIPTION = "Data de Aprova��o";

  public static final String C_BKR_AUTH_PROC_SIT_TEXT_DESCRIPTION = "Andamento Processo Aprova��o";

  public static final String C_BKR_REQ_CR_LIM_LCY_AMT_DESCRIPTION = "Limite Cr�dito Real Solicitado";

  public static final String C_BKR_APPRV_CR_LIM_LCY_AMT_DESCRIPTION = "Limite de Cr�dito D�lar Aprovado";

  public static final String C_BKR_REQ_CR_LIM_DLR_AMT_DESCRIPTION = "Limite Cr�dito USD Solicitado";

  public static final String C_BKR_APPRV_CR_LIM_DLR_AMT_DESCRIPTION = "Limite Cr�dito Real Aprovado";

  public static final String C_BKR_SUPL_SERV_TEXT_DESCRIPTION = "Servi�os Prestados";

  public static final String C_BKR_COMMENT_TEXT_DESCRIPTION = "Observa��o";

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