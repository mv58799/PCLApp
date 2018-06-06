package com.citibank.ods.entity.pl.valueobject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Conta de Produto
 */
public class BaseTo3ProductAccountEntityVO extends BaseEntityVO
{
  // Código da conta produto
  private BigInteger m_prodAcctCode = null;

  // Código da sub conta produto
  private BigInteger m_prodUnderAcctCode = null;

  // Número do cliente no CMS
  private BigInteger m_custNbr = null;

  // Número do relacionamento do cliente.
  private BigInteger m_reltnNbr = null;

  // Número da conta corrente associada ao produto.
  private BigInteger m_curAcctNbr = null;
  
  //Número da conta cci associada ao produto.
  private String m_investCurAcctNbr = "";

  // Código do produto.
  private String m_prodCode = "";

  // Código do sistema origem do cadastro do produto.
  private String m_sysCode = "";

  // Codigo da segmentação do sistema origem do cadastro do produto.
  private BigInteger m_sysSegCode = null;

  // Número da conta produto no processador de origem.
  private String m_origProdAcctNbr = "";

  // Data de abertura/início do contrato da conta produto.
  private Date m_prodAcctStaDate = null;

  // Data de fim/encerramento do contrato da conta produto.
  private Date m_prodAcctEndDate = null;

  // Código da situação do contrato da conta produto.
  private String m_prodAcctSitCode = "";

  // Status registro.
  private String m_recStatCode = "";

  //Indicador de Carteira Administrada da Conta Produto
  private String m_prodAcctPortfMgmtCode = "";

  //Indicador de política sob contrato 23A
  private String m_prodAcctPlcy23aInd = "";

  //Indicador de política sob contrato 23B
  private String m_prodAcctPlcy23bInd = "";

  //Código Isin
  private String m_prodAcctIsinCode = "";

  //Código de entidade legal
  private BigInteger m_prodAcctLegalBusCode = null;

  //Código do usuário que aprovou o cadastro do registro
  private String m_lastAuthUserId = "";

  //Data e hora que o usuário aprovou o registro do cadastro
  private Date m_lastAuthDate = null;

  //Código do usuário da última alteração
  private String m_lastUpdUserId = "";

  //Data e hora da última alteração
  private Date m_lastUpdDate = null;

  //Última Posição Carregada
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
   * Seta o código da conta produto.
   * 
   * @param prodAcctCode_ - O código da conta produto.
   */
  public void setProdAcctCode( BigInteger prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * Recupera o código da conta produto.
   * 
   * @return BigInteger - Retorna o código da conta produto.
   */
  public BigInteger getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * Seta o código da sub conta produto
   * 
   * @param prodUnderAcctCode_ - O código da sub conta produto
   */
  public void setProdUnderAcctCode( BigInteger prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

  /**
   * Recupera o código da sub conta produto
   * 
   * @return BigInteger - Retorna o código da sub conta produto
   */
  public BigInteger getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * Seta o número do cliente no CMS
   * 
   * @param custNbr_ - O número do cliente no CMS
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * Recupera o número do cliente no CMS
   * 
   * @return BigInteger - Retorna o número do cliente no CMS
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * Seta o número do relacionamento do cliente.
   * 
   * @param reltnNbr_ - O número do relacionamento do cliente.
   */
  public void setReltnNbr( BigInteger reltnNbr_ )
  {
    m_reltnNbr = reltnNbr_;
  }

  /**
   * Recupera o número do relacionamento do cliente.
   * 
   * @return BigInteger - Retorna o número do relacionamento do cliente.
   */
  public BigInteger getReltnNbr()
  {
    return m_reltnNbr;
  }

  /**
   * Seta o número da conta corrente associada ao produto.
   * 
   * @param curAcctNbr_ - O número da conta corrente associada ao produto.
   */
  public void setCurAcctNbr( BigInteger curAcctNbr_ )
  {
    m_curAcctNbr = curAcctNbr_;
  }

  /**
   * Recupera o número da conta corrente associada ao produto.
   * 
   * @return BigInteger - Retorna o número da conta corrente associada ao
   *         produto.
   */
  public BigInteger getCurAcctNbr()
  {
    return m_curAcctNbr;
  }

  /**
   * Seta o código do produto.
   * 
   * @param prodCode_ - O código do produto.
   */
  public void setProdCode( String prodCode_ )
  {
    m_prodCode = prodCode_;
  }

  /**
   * Recupera o código do produto.
   * 
   * @return String - Retorna o código do produto.
   */
  public String getProdCode()
  {
    return m_prodCode;
  }

  /**
   * Seta o código do sistema origem do cadastro do produto.
   * 
   * @param sysCode_ - O código do sistema origem do cadastro do produto.
   */
  public void setSysCode( String sysCode_ )
  {
    m_sysCode = sysCode_;
  }

  /**
   * Recupera o código do sistema origem do cadastro do produto.
   * 
   * @return String - Retorna o código do sistema origem do cadastro do produto.
   */
  public String getSysCode()
  {
    return m_sysCode;
  }

  /**
   * Seta o codigo da segmentação do sistema origem do cadastro do produto.
   * 
   * @param sysSegCode_ - O codigo da segmentação do sistema origem do cadastro
   *          do produto.
   */
  public void setSysSegCode( BigInteger sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
  }

  /**
   * Recupera o codigo da segmentação do sistema origem do cadastro do produto.
   * 
   * @return BigInteger - Retorna o codigo da segmentação do sistema origem do
   *         cadastro do produto.
   */
  public BigInteger getSysSegCode()
  {
    return m_sysSegCode;
  }

  /**
   * Seta o número da conta produto no processador de origem.
   * 
   * @param origProdAcctNbr_ - O número da conta produto no processador de
   *          origem. do produto.
   */
  public void setOrigProdAcctNbr( String origProdAcctNbr_ )
  {
    m_origProdAcctNbr = origProdAcctNbr_;
  }

  /**
   * Recupera o número da conta produto no processador de origem.
   * 
   * @return BigInteger - Retorna o número da conta produto no processador de
   *         origem.
   */
  public String getOrigProdAcctNbr()
  {
    return m_origProdAcctNbr;
  }

  /**
   * Seta a data de abertura/início do contrato da conta produto.
   * 
   * @param prodAcctStaDate_ - A data de abertura/início do contrato da conta
   *          produto.
   */
  public void setProdAcctStaDate( Date prodAcctStaDate_ )
  {
    m_prodAcctStaDate = prodAcctStaDate_;
  }

  /**
   * Recupera a data de abertura/início do contrato da conta produto.
   * 
   * @return Date - Retorna a data de abertura/início do contrato da conta
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
   * Seta o código da situação do contrato da conta produto.
   * 
   * @param prodAcctSitCode_ - O código da situação do contrato da conta
   *          produto.
   */
  public void setProdAcctSitCode( String prodAcctSitCode_ )
  {
    m_prodAcctSitCode = prodAcctSitCode_;
  }

  /**
   * Recupera o código da situação do contrato da conta produto.
   * 
   * @return String - Retorna o código da situação do contrato da conta produto.
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
   * @return Retorna data/hora da aprovação do cadastro.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_.Seta data/hora da aprovação do cadastro.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Retorna o usuário que aprovou a última alteração.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_.Seta o usuário que aprovou a última alteração.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Retorna data/hora da última atualização.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_.Seta data/hora da última atualização.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Retorna usuário da última atualização.
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
   * @return Retorna o código Isin.
   */
  public String getProdAcctIsinCode()
  {
    return m_prodAcctIsinCode;
  }

  /**
   * @param prodAcctIsinCode_.Seta o código Isin.
   */
  public void setProdAcctIsinCode( String prodAcctIsinCode_ )
  {
    m_prodAcctIsinCode = prodAcctIsinCode_;
  }

  /**
   * @return Retorna o código da entidade legal.
   */
  public BigInteger getProdAcctLegalBusCode()
  {
    return m_prodAcctLegalBusCode;
  }

  /**
   * @param prodAcctLegalBusCode_.Seta o código da entidade legal.
   */
  public void setProdAcctLegalBusCode( BigInteger prodAcctLegalBusCode_ )
  {
    m_prodAcctLegalBusCode = prodAcctLegalBusCode_;
  }

  /**
   * @return Retorna o indicador de política 23A.
   */
  public String getProdAcctPlcy23aInd()
  {
    return m_prodAcctPlcy23aInd;
  }

  /**
   * @param prodAcctPlcy23aInd_.Seta o indicador de política 23A.
   */
  public void setProdAcctPlcy23aInd( String prodAcctPlcy23aInd_ )
  {
    m_prodAcctPlcy23aInd = prodAcctPlcy23aInd_;
  }

  /**
   * @return Retorna o indicador de política 23B.
   */
  public String getProdAcctPlcy23bInd()
  {
    return m_prodAcctPlcy23bInd;
  }

  /**
   * @param prodAcctPlcy23bInd_.Seta o indicador de política 23B.
   */
  public void setProdAcctPlcy23bInd( String prodAcctPlcy23bInd_ )
  {
    m_prodAcctPlcy23bInd = prodAcctPlcy23bInd_;
  }

  /**
   * @return Retorna o código da carteira administrada da conta produto.
   */
  public String getProdAcctPortfMgmtCode()
  {
    return m_prodAcctPortfMgmtCode;
  }

  /**
   * @param prodAcctPortfMgmtCode_.Seta o código da carteira administrada da
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
   * @return a Última Posição Carregada
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
   * @param BalRefDate_.Seta a Última Posição Carregada
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