package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author m.nakamura
 * 
 * Agregador dos campos necessários para realizar a consulta em lista do
 * histórico de memória de risco.
 */
public class MrDocPrvtHistListFncVO extends BaseMrDocPrvtListFncVO
{
  // Código do documento MR
  private BigInteger m_mrDocCode = null;
  
  // Data de referência do registro no histórico
  private Date m_mrDocRefDate = null;
  
  
  //Constante da Data de Referência
  public static final String C_MR_DATE_REF = "Data de referência";
  
  //Constante do número do documento MR
  public static final String C_MR_DOC_CODE = "Código do documento MR";
  
  /**
   * @return Retorna o número do documento MR.
   */
  public BigInteger getMrDocCode()
  {
    return m_mrDocCode;
  }

  /**
   * @param mrDocPrvt_.Seta o número do documento MR.
   */
  public void setMrDocCode( BigInteger mrDocCode_ )
  {
    m_mrDocCode = mrDocCode_;
  }

  
  /**
   * @return Retorna a data de refêrencia do histórico.
   */
  public Date getMrDocRefDate()
  {
    return m_mrDocRefDate;
  }
  /**
   * @param mrDocRefDate_.Seta a data de refêrencia do histórico.
   */
  public void setMrDocRefDate( Date mrDocRefDate_ )
  {
    m_mrDocRefDate = mrDocRefDate_;
  }
  


}