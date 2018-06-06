package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Memória de Risco.
 */
public class TplMrDocPrvtMovEntityVO extends BaseTplMrDocPrvtEntityVO
{

  /**
   * @param mrDocPrvtEntity_
   * @param lastAuthDate_
   * @param lastAuthUserId_
   */
  public TplMrDocPrvtMovEntityVO( TplMrDocPrvtEntity mrDocPrvtEntity_,
                                 BigInteger prvtMrCode_, String prodAcctCode_,
                                 String prodUnderAcctCode_ )
  {
    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) mrDocPrvtEntity_.getData();
    String prodAcctCode = prodAcctCode_;
    String prodUnderAcctCode = prodUnderAcctCode_;
    BigInteger prvtMrCode = prvtMrCode_;
    this.setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
    this.setMrDocText( mrDocPrvtEntityVO.getMrDocText() );
    this.setMrInvstCurAcctInd( mrDocPrvtEntityVO.getMrInvstCurAcctInd() );
    this.setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
    this.setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );

  }

  /**
   * Cria novo objeto TplMrDocPrvtEntityVO.
   */
  public TplMrDocPrvtMovEntityVO()
  {
    //
  }

  //Codigo Documento MR
  private BigInteger m_mrDocCode = null;

  // Código da ação que originou o registro
  private String m_opernCode = "";

  /**
   * Recupera Codigo Documento MR
   * 
   * @return Retorna Codigo Documento MR
   */
  public BigInteger getMrDocCode()
  {
    return m_mrDocCode;
  }

  /**
   * Seta Codigo Documento MR
   * 
   * @param mrDocCode_ - Codigo Documento MR
   */
  public void setMrDocCode( BigInteger mrDocCode_ )
  {
    m_mrDocCode = mrDocCode_;
  }

  /**
   * Recupera o Código da ação que originou o registro
   * 
   * @return Retorna o Código da ação que originou o registro
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * Seta o Código da ação que originou o registro
   * 
   * @param opernCode_ - O Código da ação que originou o registro
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}