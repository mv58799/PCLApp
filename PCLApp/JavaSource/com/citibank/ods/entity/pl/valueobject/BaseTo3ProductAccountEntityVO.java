package com.citibank.ods.entity.pl.valueobject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representa��o da tabela de Conta de Produto
 */
public class BaseTo3ProductAccountEntityVO extends BaseEntityVO
{
  // C�digo da conta produto
  private BigInteger m_prodAcctCode = null;

  // C�digo da sub conta produto
  private BigInteger m_prodUnderAcctCode = null;

  // N�mero do cliente no CMS
  private BigInteger m_custNbr = null;

  // N�mero do relacionamento do cliente.
  private BigInteger m_reltnNbr = null;

  // N�mero da conta corrente associada ao produto.
  private BigInteger m_curAcctNbr = null;
  
  //N�mero da conta cci associada ao produto.
  private String m_investCurAcctNbr = "";

  // C�digo do produto.
  private String m_prodCode = "";

  // C�digo do sistema origem do cadastro do produto.
  private String m_sysCode = "";

  // Codigo da segmenta��o do sistema origem do cadastro do produto.
  private BigInteger m_sysSegCode = null;

  // N�mero da conta produto no processador de origem.
  private String m_origProdAcctNbr = "";

  // Data de abertura/in�cio do contrato da conta produto.
  private Date m_prodAcctStaDate = null;

  // Data de fim/encerramento do contrato da conta produto.
  private Date m_prodAcctEndDate = null;

  // C�digo da situa��o do contrato da conta produto.
  private String m_prodAcctSitCode = "";

  // Status registro.
  private String m_recStatCode = "";

  //Indicador de Carteira Administrada da Conta Produto
  private String m_prodAcctPortfMgmtCode = "";

  //Indicador de pol�tica sob contrato 23A
  private String m_prodAcctPlcy23aInd = "";

  //Indicador de pol�tica sob contrato 23B
  private String m_prodAcctPlcy23bInd = "";

  //C�digo Isin
  private String m_prodAcctIsinCode = "";

  //C�digo de entidade legal
  private BigInteger m_prodAcctLegalBusCode = null;

  //C�digo do usu�rio que aprovou o cadastro do registro
  private String m_lastAuthUserId = "";

  //Data e hora que o usu�rio aprovou o registro do cadastro
  private Date m_lastAuthDate = null;

  //C�digo do usu�rio da �ltima altera��o
  private String m_lastUpdUserId = "";

  //Data e hora da �ltima altera��o
  private Date m_lastUpdDate = null;

  //�ltima Posi��o Carregada
  private Date m_balRefDate = null;
  
  //Valor
  private BigDecimal m_acctAmt = null;
  
  private String segCode = "";

  private String segName = "";

  public String getSegName() {
	return segName;
}

public void setSegName(String segName) {
	this.segName = segName;
}

public String getSegCode() {
	return segCode;
}

public void setSegCode(String segCode) {
	this.segCode = segCode;
}

/**
   * Seta o c�digo da conta produto.
   * 
   * @param prodAcctCode_ - O c�digo da conta produto.
   */
  public void setProdAcctCode( BigInteger prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * Recupera o c�digo da conta produto.
   * 
   * @return BigInteger - Retorna o c�digo da conta produto.
   */
  public BigInteger getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * Seta o c�digo da sub conta produto
   * 
   * @param prodUnderAcctCode_ - O c�digo da sub conta produto
   */
  public void setProdUnderAcctCode( BigInteger prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

  /**
   * Recupera o c�digo da sub conta produto
   * 
   * @return BigInteger - Retorna o c�digo da sub conta produto
   */
  public BigInteger getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * Seta o n�mero do cliente no CMS
   * 
   * @param custNbr_ - O n�mero do cliente no CMS
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * Recupera o n�mero do cliente no CMS
   * 
   * @return BigInteger - Retorna o n�mero do cliente no CMS
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * Seta o n�mero do relacionamento do cliente.
   * 
   * @param reltnNbr_ - O n�mero do relacionamento do cliente.
   */
  public void setReltnNbr( BigInteger reltnNbr_ )
  {
    m_reltnNbr = reltnNbr_;
  }

  /**
   * Recupera o n�mero do relacionamento do cliente.
   * 
   * @return BigInteger - Retorna o n�mero do relacionamento do cliente.
   */
  public BigInteger getReltnNbr()
  {
    return m_reltnNbr;
  }

  /**
   * Seta o n�mero da conta corrente associada ao produto.
   * 
   * @param curAcctNbr_ - O n�mero da conta corrente associada ao produto.
   */
  public void setCurAcctNbr( BigInteger curAcctNbr_ )
  {
    m_curAcctNbr = curAcctNbr_;
  }

  /**
   * Recupera o n�mero da conta corrente associada ao produto.
   * 
   * @return BigInteger - Retorna o n�mero da conta corrente associada ao
   *         produto.
   */
  public BigInteger getCurAcctNbr()
  {
    return m_curAcctNbr;
  }

  /**
   * Seta o c�digo do produto.
   * 
   * @param prodCode_ - O c�digo do produto.
   */
  public void setProdCode( String prodCode_ )
  {
    m_prodCode = prodCode_;
  }

  /**
   * Recupera o c�digo do produto.
   * 
   * @return String - Retorna o c�digo do produto.
   */
  public String getProdCode()
  {
    return m_prodCode;
  }

  /**
   * Seta o c�digo do sistema origem do cadastro do produto.
   * 
   * @param sysCode_ - O c�digo do sistema origem do cadastro do produto.
   */
  public void setSysCode( String sysCode_ )
  {
    m_sysCode = sysCode_;
  }

  /**
   * Recupera o c�digo do sistema origem do cadastro do produto.
   * 
   * @return String - Retorna o c�digo do sistema origem do cadastro do produto.
   */
  public String getSysCode()
  {
    return m_sysCode;
  }

  /**
   * Seta o codigo da segmenta��o do sistema origem do cadastro do produto.
   * 
   * @param sysSegCode_ - O codigo da segmenta��o do sistema origem do cadastro
   *          do produto.
   */
  public void setSysSegCode( BigInteger sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
  }

  /**
   * Recupera o codigo da segmenta��o do sistema origem do cadastro do produto.
   * 
   * @return BigInteger - Retorna o codigo da segmenta��o do sistema origem do
   *         cadastro do produto.
   */
  public BigInteger getSysSegCode()
  {
    return m_sysSegCode;
  }

  /**
   * Seta o n�mero da conta produto no processador de origem.
   * 
   * @param origProdAcctNbr_ - O n�mero da conta produto no processador de
   *          origem. do produto.
   */
  public void setOrigProdAcctNbr( String origProdAcctNbr_ )
  {
    m_origProdAcctNbr = origProdAcctNbr_;
  }

  /**
   * Recupera o n�mero da conta produto no processador de origem.
   * 
   * @return BigInteger - Retorna o n�mero da conta produto no processador de
   *         origem.
   */
  public String getOrigProdAcctNbr()
  {
    return m_origProdAcctNbr;
  }

  /**
   * Seta a data de abertura/in�cio do contrato da conta produto.
   * 
   * @param prodAcctStaDate_ - A data de abertura/in�cio do contrato da conta
   *          produto.
   */
  public void setProdAcctStaDate( Date prodAcctStaDate_ )
  {
    m_prodAcctStaDate = prodAcctStaDate_;
  }

  /**
   * Recupera a data de abertura/in�cio do contrato da conta produto.
   * 
   * @return Date - Retorna a data de abertura/in�cio do contrato da conta
   *         produto.
   */
  public Date getProdAcctStaDate()
  {
    return m_prodAcctStaDate;
  }

  /**
   * Seta a data de fim/encerramento do contrato da conta produto.
   * 
   * @param prodAcctEndDate_ - A data de fim/encerramento do contrato da conta
   *          produto.
   */
  public void setProdAcctEndDate( Date prodAcctEndDate_ )
  {
    m_prodAcctEndDate = prodAcctEndDate_;
  }

  /**
   * Recupera a data de fim/encerramento do contrato da conta produto.
   * 
   * @return Date - Retorna a data de fim/encerramento do contrato da conta
   *         produto.
   */
  public Date getProdAcctEndDate()
  {
    return m_prodAcctEndDate;
  }

  /**
   * Seta o c�digo da situa��o do contrato da conta produto.
   * 
   * @param prodAcctSitCode_ - O c�digo da situa��o do contrato da conta
   *          produto.
   */
  public void setProdAcctSitCode( String prodAcctSitCode_ )
  {
    m_prodAcctSitCode = prodAcctSitCode_;
  }

  /**
   * Recupera o c�digo da situa��o do contrato da conta produto.
   * 
   * @return String - Retorna o c�digo da situa��o do contrato da conta produto.
   */
  public String getProdAcctSitCode()
  {
    return m_prodAcctSitCode;
  }

  /**
   * Seta o status registro.
   * 
   * @param recStatCode_ - O status registro.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * Recupera o status registro.
   * 
   * @return String - Retorna o status registro.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @return Retorna data/hora da aprova��o do cadastro.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_.Seta data/hora da aprova��o do cadastro.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Retorna o usu�rio que aprovou a �ltima altera��o.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_.Seta o usu�rio que aprovou a �ltima altera��o.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Retorna data/hora da �ltima atualiza��o.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_.Seta data/hora da �ltima atualiza��o.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Retorna usu�rio da �ltima atualiza��o.
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
   * @return Retorna o c�digo Isin.
   */
  public String getProdAcctIsinCode()
  {
    return m_prodAcctIsinCode;
  }

  /**
   * @param prodAcctIsinCode_.Seta o c�digo Isin.
   */
  public void setProdAcctIsinCode( String prodAcctIsinCode_ )
  {
    m_prodAcctIsinCode = prodAcctIsinCode_;
  }

  /**
   * @return Retorna o c�digo da entidade legal.
   */
  public BigInteger getProdAcctLegalBusCode()
  {
    return m_prodAcctLegalBusCode;
  }

  /**
   * @param prodAcctLegalBusCode_.Seta o c�digo da entidade legal.
   */
  public void setProdAcctLegalBusCode( BigInteger prodAcctLegalBusCode_ )
  {
    m_prodAcctLegalBusCode = prodAcctLegalBusCode_;
  }

  /**
   * @return Retorna o indicador de pol�tica 23A.
   */
  public String getProdAcctPlcy23aInd()
  {
    return m_prodAcctPlcy23aInd;
  }

  /**
   * @param prodAcctPlcy23aInd_.Seta o indicador de pol�tica 23A.
   */
  public void setProdAcctPlcy23aInd( String prodAcctPlcy23aInd_ )
  {
    m_prodAcctPlcy23aInd = prodAcctPlcy23aInd_;
  }

  /**
   * @return Retorna o indicador de pol�tica 23B.
   */
  public String getProdAcctPlcy23bInd()
  {
    return m_prodAcctPlcy23bInd;
  }

  /**
   * @param prodAcctPlcy23bInd_.Seta o indicador de pol�tica 23B.
   */
  public void setProdAcctPlcy23bInd( String prodAcctPlcy23bInd_ )
  {
    m_prodAcctPlcy23bInd = prodAcctPlcy23bInd_;
  }

  /**
   * @return Retorna o c�digo da carteira administrada da conta produto.
   */
  public String getProdAcctPortfMgmtCode()
  {
    return m_prodAcctPortfMgmtCode;
  }

  /**
   * @param prodAcctPortfMgmtCode_.Seta o c�digo da carteira administrada da
   *          conta produto.
   */
  public void setProdAcctPortfMgmtCode( String prodAcctPortfMgmtCode_ )
  {
    m_prodAcctPortfMgmtCode = prodAcctPortfMgmtCode_;
  }

   /**
   * @return Retorna o Valor
   */
  public BigDecimal getAcctAmt()
   {
	return m_acctAmt;
   }
	
  /**
   * @return a �ltima Posi��o Carregada
   */
  public Date getBalRefDate() 
  {
   return m_balRefDate;
  }
	
  /**
   * @param AcctAmt_.Seta o valor
   */
  public void setAcctAmt(BigDecimal m_acctAmt_)
   {
 	  m_acctAmt = m_acctAmt_;
   }
	
  /**
   * @param BalRefDate_.Seta a �ltima Posi��o Carregada
   */
  public void setBalRefDate(Date m_balRefDate_) 
  {
      m_balRefDate = m_balRefDate_;
  }

/**
 * @return
 */
  public String getInvestCurAcctNbr() {
	return m_investCurAcctNbr;
  }

/**
 * @param string
 */
  public void setInvestCurAcctNbr(String m_investCurAcctNbr_) {
	m_investCurAcctNbr = m_investCurAcctNbr_;
  }

}