package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplBrokerEntityVO;

/**
 * @author Hamilton Matos
 */
public class BaseTplBrokerEntity extends BaseODSEntity
{

  /**
   * Constante do tamanho do campo CNPJ do broker.
   */
  public static final int C_BKR_CNPJ_NBR_SIZE = 18;

  /**
   * Constante do tamanho do campo Nome do broker.
   */
  public static final int C_BKR_NAME_TEXT_SIZE = 60;

  public static final int C_BKR_ADDR_TEXT_SIZE = 80;

  public static final int C_BKR_BMF_MKT_CODE_SIZE = 10;

  public static final int C_BKR_BOVESPA_MKT_CODE_SIZE = 10;

  public static final int C_BKR_RBT_BMF_RATE_SIZE = 8;

  public static final int C_BKR_RBT_BMF_RATE_SCALE = 3;

  public static final int C_BKR_RBT_BOVESPA_RATE_SIZE = 8;

  public static final int C_BKR_RBT_BOVESPA_RATE_SCALE = 3;

  public static final int C_BKR_REQ_DATE_SIZE = 12;

  public static final int C_BKR_RNW_DATE_SIZE = 12;

  public static final int C_BKR_APPRV_STAT_CODE_SIZE = 1;

  public static final int C_BKR_APPRV_DATE_SIZE = 12;

  public static final int C_BKR_AUTH_PROC_SIT_TEXT_SIZE = 255;

  public static final int C_BKR_REQ_CR_LIM_LCY_AMT_SIZE = 18;

  public static final int C_BKR_REQ_CR_LIM_LCY_AMT_SCALE = 2;

  public static final int C_BKR_APPRV_CR_LIM_LCY_AMT_SIZE = 18;

  public static final int C_BKR_APPRV_CR_LIM_LCY_AMT_SCALE = 2;

  public static final int C_BKR_REQ_CR_LIM_DLR_AMT_SIZE = 18;

  public static final int C_BKR_REQ_CR_LIM_DLR_AMT_SCALE = 2;

  public static final int C_BKR_APPRV_CR_LIM_DLR_AMT_SIZE = 18;

  public static final int C_BKR_APPRV_CR_LIM_DLR_AMT_SCALE = 2;

  public static final int C_BKR_SUPL_SERV_TEXT_SIZE = 100;

  public static final int C_BKR_COMMENT_TEXT_SIZE = 255;

  protected BaseTplBrokerEntityVO m_data;

  public BaseTplBrokerEntityVO getData()
  {
    return m_data;
  }
}