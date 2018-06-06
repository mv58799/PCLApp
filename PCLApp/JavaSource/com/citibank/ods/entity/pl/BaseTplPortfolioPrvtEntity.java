package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplPortfolioPrvtEntityVO;
/**
 * Classe que instancia a entidade correspondente a tabela :
 * BaseTplPortfolioPrvt
 * @author l.braga
 * @date 28/03/2007
 */

public class BaseTplPortfolioPrvtEntity extends BaseEntity {

  public BaseTplPortfolioPrvtEntityVO m_data ;

  public static final int C_PORTF_NAME_TEXT_SIZE = 40;
  public static final int C_PORTF_OFFCR_NBR_SIZE = 6;
  public static final int C_OFFCR_NAME_TEXT_SIZE = 40;


  
  public BaseTplPortfolioPrvtEntityVO getData()
  {
    return m_data;
  }
  
}
