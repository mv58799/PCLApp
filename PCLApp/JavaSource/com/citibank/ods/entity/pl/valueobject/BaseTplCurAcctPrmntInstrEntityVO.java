package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
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
   * @return m_lastUpdDate. Retorna data/hora da última atualização.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ .Seta data/hora da última atualização.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return m_lastUpdUserId. Retorna o usuário da última atualização.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_.Seta o usuário da última atualização.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return m_prmntInstrCode. Retorna o número da instrução permanente.
   */
  public BigInteger getPrmntInstrCode()
  {
    return m_prmntInstrCode;
  }

  /**
   * @param prmntInstrCode_.Seta o número da instrução permanente.
   */
  public void setPrmntInstrCode( BigInteger prmntInstrCode_ )
  {
    m_prmntInstrCode = prmntInstrCode_;
  }

  /**
   * @return m_prodAcctCode. Retorna o número da conta produto.
   */
  public BigInteger getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * @param prodAcctCode_. Seta o número da conta produto.
   */
  public void setProdAcctCode( BigInteger prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * @return m_prodUnderAcctCode. Retorna o número da sub conta produto.
   */
  public BigInteger getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * @param prodUnderAcctCode_.Seta o número da sub conta produto.
   */
  public void setProdUnderAcctCode( BigInteger prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

  /**
   * @return m_custNbr. Retorna o número do cliente.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_.Seta o número do cliente.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }


}