package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.math.BigInteger;


/**
 * @author m.nakamura
 * 
 * Agregador dos campos necess�rios para realizar a consulta em lista de mem�ria
 * de risco.
 */
public class MrDocPrvtListFncVO extends BaseMrDocPrvtListFncVO
{
  
  // C�digo do documento MR
  private BigInteger m_mrDocPrvt = null;
  
  //Constante do n�mero do documento MR
  public static final String C_MR_DOC_PRVT= "N�mero do Documento MR";



  /**
   * @return Retorna o n�mero do documento MR.
   */
  public BigInteger getMrDocPrvt()
  {
    return m_mrDocPrvt;
  }
  /**
   * @param mrDocPrvt_.Seta o n�mero do documento MR.
   */
  public void setMrDocPrvt( BigInteger mrDocPrvt_ )
  {
    m_mrDocPrvt = mrDocPrvt_;
  }
  
  
}