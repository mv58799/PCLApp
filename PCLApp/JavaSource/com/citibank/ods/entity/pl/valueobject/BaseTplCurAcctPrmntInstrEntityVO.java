package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.pl.valueobject;
 * @version 1.0
 * @author michele.monteiro,04/06/2007
 *  
 */

public class BaseTplCurAcctPrmntInstrEntityVO extends BaseEntityVO
{

  // Numero do Cliente no CMS
  private BigInteger m_custNbr = null;

  // Data e Hora da Ultima atualizacao efetuada pelo usuario.
  private Date m_lastUpdDate = null;

  // Codigo do Usuario que efetuou a ultima atualizacao registro.
  private String m_lastUpdUserId = "";

  // Codigo do Documento IP
  private BigInteger m_prmntInstrCode = null;

  // Codigo da Conta Produto. Informacao gerada pela ODS Private para
  // identificar uma operacao ou um contrato
  private BigInteger m_prodAcctCode = null;

  // Codigo da sub conta produto. Codigo gerado pela ODS para identificar
  // produtos que tenham subcontas ou subcontratos
  private BigInteger m_prodUnderAcctCode = null;


  /**
   * @return m_lastUpdDate. Retorna data/hora da �ltima atualiza��o.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ .Seta data/hora da �ltima atualiza��o.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return m_lastUpdUserId. Retorna o usu�rio da �ltima atualiza��o.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_.Seta o usu�rio da �ltima atualiza��o.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return m_prmntInstrCode. Retorna o n�mero da instru��o permanente.
   */
  public BigInteger getPrmntInstrCode()
  {
    return m_prmntInstrCode;
  }

  /**
   * @param prmntInstrCode_.Seta o n�mero da instru��o permanente.
   */
  public void setPrmntInstrCode( BigInteger prmntInstrCode_ )
  {
    m_prmntInstrCode = prmntInstrCode_;
  }

  /**
   * @return m_prodAcctCode. Retorna o n�mero da conta produto.
   */
  public BigInteger getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * @param prodAcctCode_. Seta o n�mero da conta produto.
   */
  public void setProdAcctCode( BigInteger prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * @return m_prodUnderAcctCode. Retorna o n�mero da sub conta produto.
   */
  public BigInteger getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * @param prodUnderAcctCode_.Seta o n�mero da sub conta produto.
   */
  public void setProdUnderAcctCode( BigInteger prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

  /**
   * @return m_custNbr. Retorna o n�mero do cliente.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_.Seta o n�mero do cliente.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }


}