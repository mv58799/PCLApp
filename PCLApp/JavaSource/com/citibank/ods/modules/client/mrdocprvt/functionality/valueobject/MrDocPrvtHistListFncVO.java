package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author m.nakamura
 * 
 * Agregador dos campos necess�rios para realizar a consulta em lista do
 * hist�rico de mem�ria de risco.
 */
public class MrDocPrvtHistListFncVO extends BaseMrDocPrvtListFncVO
{
  // C�digo do documento MR
  private BigInteger m_mrDocCode = null;
  
  // Data de refer�ncia do registro no hist�rico
  private Date m_mrDocRefDate = null;
  
  
  //Constante da Data de Refer�ncia
  public static final String C_MR_DATE_REF = "Data de refer�ncia";
  
  //Constante do n�mero do documento MR
  public static final String C_MR_DOC_CODE = "C�digo do documento MR";
  
  /**
   * @return Retorna o n�mero do documento MR.
   */
  public BigInteger getMrDocCode()
  {
    return m_mrDocCode;
  }

  /**
   * @param mrDocPrvt_.Seta o n�mero do documento MR.
   */
  public void setMrDocCode( BigInteger mrDocCode_ )
  {
    m_mrDocCode = mrDocCode_;
  }

  
  /**
   * @return Retorna a data de ref�rencia do hist�rico.
   */
  public Date getMrDocRefDate()
  {
    return m_mrDocRefDate;
  }
  /**
   * @param mrDocRefDate_.Seta a data de ref�rencia do hist�rico.
   */
  public void setMrDocRefDate( Date mrDocRefDate_ )
  {
    m_mrDocRefDate = mrDocRefDate_;
  }
  


}