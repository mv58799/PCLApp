package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplBkrPortfMgmtEntityVO;

/**
 * Classe que instancia a entidade correspondente a tabela Tpl_Bkr_Portf_Mgmt
 * @author Hamilton Matos
 */

public class BaseTplBkrPortfMgmtEntity extends BaseODSEntity
{

  protected BaseTplBkrPortfMgmtEntityVO m_data = null;

  /**
   * Constante do tamanho do campo CNPJ do broker.
   */
  public static final int C_BKR_CNPJ_NBR_SIZE = 18;

  /**
   * Constante do tamanho do campo Nome do broker.
   */
  public static final int C_BKR_NAME_TEXT_SIZE = 60;

  /**
   * Constante do tamanho do campo Número do cliente.
   */
  public static final int C_CUST_NBR_SIZE = 11;

  /**
   * Constante do tamanho do campo Nome do cliente.
   */
  public static final int C_CUST_FULL_NAME_TEXT_SIZE = 60;

  public BaseTplBkrPortfMgmtEntityVO getData()
  {
    return m_data;
  }

}