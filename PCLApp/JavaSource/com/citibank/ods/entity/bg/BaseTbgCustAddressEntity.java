package com.citibank.ods.entity.bg;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.bg.valueobject.BaseTbgCustAddressEntityVO;

/**
 * @author hamilton.matos
 */

public class BaseTbgCustAddressEntity extends BaseODSEntity
{
  /**
   * Entity VO da categoria de risco
   */
  protected BaseTbgCustAddressEntityVO m_data;

  /**
   * Constante do Numero do Cliente
   */
  public static final int C_CUST_NBR_SIZE = 11;

  /**
   * Constante do Nome do cliente
   */
  public static final int C_CUST_FULL_NAME_TEXT_SIZE = 60;

  /**
   * Constante do CFF ou CNPJ do cliente
   */
  public static final int C_CUST_CPF_CNPJ_NBR_SIZE = 15;

  /**
   * Retorna o entity VO do agregador de produtos
   * @return
   */
  public BaseTbgCustAddressEntityVO getData()
  {
    return m_data;
  }
}

