package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplContactCustEntityVO;

/**
 * @author hamilton.matos
 */

public class BaseTplContactCustEntity extends BaseODSEntity
{
  /**
   * Entity VO de contato de cliente
   */
  protected BaseTplContactCustEntityVO m_data = null;

  /**
   * Constante do numero do contato
   */
  public static final int C_CTC_NBR_SIZE = 6;

  /**
   * Constante do nome do contato
   */
  public static final int C_FULL_NAME_TEXT_SIZE = 60;
  
  /**
   * Constante do Nome do Segundo Contato
   */
  public static final int C_FULL_NAME_2_TEXT_SIZE = 60;
	
 /**
  * Constante do Nome do Terceiro Contato
  */
  public static final int C_FULL_NAME_3_TEXT_SIZE = 60;

  /**
   * Constante do numero do DDD
   */
  public static final int C_PHONE_DDD_CODE_SIZE = 4;

  /**
   * Constante do número de telefone
   */
  public static final int C_PHONE_NBR_SIZE = 10;

  /**
   * Constante do número de telefone
   */
  public static final int C_PHONE_EXTN_NBR_SIZE = 5;

  /**
   * Construtor padrão
   *  
   */
  public BaseTplContactCustEntity()
  {
    m_data = new BaseTplContactCustEntityVO();
  }

  /**
   * Retorna o entity VO do contato de cliente
   * @return
   */
  public BaseTplContactCustEntityVO getData()
  {
    return m_data;
  }

}