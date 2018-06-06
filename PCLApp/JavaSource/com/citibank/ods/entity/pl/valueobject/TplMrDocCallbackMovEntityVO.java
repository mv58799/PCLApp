package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

import com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity;
import com.citibank.ods.modules.client.mrdocprvt.ContactCustKey;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de relacionamento Contato X Memória de Risco.
 */
public class TplMrDocCallbackMovEntityVO extends BaseTplMrDocCallbackEntityVO
{
  // Codigo Documento MR
  private BigInteger m_mrDocPrvt = null;

  // Código da ação que originou o registro.
  private String m_opernCode = "";

  /**
   * Recupera Codigo Documento MR
   * 
   * @return Retorna Codigo Documento MR
   */
  public BigInteger getMrDocPrvt()
  {
    return m_mrDocPrvt;
  }

  /**
   * Seta Codigo Documento MR
   * 
   * @param mrDocPrvt_ - Codigo Documento MR
   */
  public void setMrDocPrvt( BigInteger mrDocPrvt_ )
  {
    m_mrDocPrvt = mrDocPrvt_;
  }

  /**
   * Recupera o Código da ação que originou o registro.
   * 
   * @return Retorna o Código da ação que originou o registro.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * Seta o Código da ação que originou o registro.
   * 
   * @param opernCode_ - Código da ação que originou o registro.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }

  /**
   * Cria novo objeto TplMrDocCallbackMovEntityVO.
   */
  public TplMrDocCallbackMovEntityVO()
  {
    //
  }

  /**
   * Cria novo objeto com valores definidos.
   * 
   * @param mrDocPrvtMovEntity_ - Entidade com os valores de Movimento do
   *          Cadastro.
   * @param contactCustKey_ - Chave com número do contato e número do cliente.
   * @param opernCode_ - Código da operação realizada no movimento.
   */
  public TplMrDocCallbackMovEntityVO(
                                     TplMrDocPrvtMovEntity mrDocPrvtMovEntity_,
                                     ContactCustKey contactCustKey_,
                                     String opernCode_ )
  {
    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity_.getData();

    setMrDocPrvt( mrDocPrvtMovEntityVO.getMrDocCode() );
    setProdAcctCode( mrDocPrvtMovEntityVO.getProdAcctCode() );
    setProdUnderAcctCode( mrDocPrvtMovEntityVO.getProdUnderAcctCode() );
    setOpernCode( opernCode_ );
    setLastUpdDate( mrDocPrvtMovEntityVO.getLastUpdDate() );
    setLastUpdUserId( mrDocPrvtMovEntityVO.getLastUpdUserId() );
    setCustNbr( contactCustKey_.getCustNbr() );
    setCtcNbr( contactCustKey_.getCtcNbr() );
  }
}