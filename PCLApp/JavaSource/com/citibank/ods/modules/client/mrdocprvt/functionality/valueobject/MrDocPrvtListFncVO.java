package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.math.BigInteger;


/**
 * @author m.nakamura
 * 
 * Agregador dos campos necessários para realizar a consulta em lista de memória
 * de risco.
 */
public class MrDocPrvtListFncVO extends BaseMrDocPrvtListFncVO
{
  
  // Código do documento MR
  private BigInteger m_mrDocPrvt = null;
  
  //Constante do número do documento MR
  public static final String C_MR_DOC_PRVT= "Número do Documento MR";



  /**
   * @return Retorna o número do documento MR.
   */
  public BigInteger getMrDocPrvt()
  {
    return m_mrDocPrvt;
  }
  /**
   * @param mrDocPrvt_.Seta o número do documento MR.
   */
  public void setMrDocPrvt( BigInteger mrDocPrvt_ )
  {
    m_mrDocPrvt = mrDocPrvt_;
  }
  
  
}