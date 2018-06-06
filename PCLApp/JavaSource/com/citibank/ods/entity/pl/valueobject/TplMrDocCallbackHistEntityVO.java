package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.entity.pl.TplMrDocCallbackEntity;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de histórico de relacionamento Contato X Memória de
 * Risco.
 */
public class TplMrDocCallbackHistEntityVO extends BaseTplMrDocCallbackEntityVO
{
  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private Date m_lastAuthDate = null;

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Codigo Documento MR
  private BigInteger m_mrDocCode = null;

  // Status do Registro: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
  // Aprovacao)
  private String m_recStatCode = "";

  // Data de referência do histórico.
  private Date m_mrDocCallbackRefDate = null;

  /**
   * Recupera a Data e Hora que o Usuario Aprovou o Registro Cadastrado.
   * 
   * @return Retorna a Data e Hora que o Usuario Aprovou o Registro Cadastrado.
   */
  public Date getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * Seta a Data e Hora que o Usuario Aprovou o Registro Cadastrado.
   * 
   * @param lastAuthDate_ - A Data e Hora que o Usuario Aprovou o Registro
   *          Cadastrado.
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * Recupera o Codigo do Usuario que Aprovou o Cadastro do Registro.
   * 
   * @return Retorna o Codigo do Usuario que Aprovou o Cadastro do Registro.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * Seta o Codigo do Usuario que Aprovou o Cadastro do Registro.
   * 
   * @param lastAuthUserId_ - O Codigo do Usuario que Aprovou o Cadastro do
   *          Registro.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * Recupera a Data de referência do histórico.
   * 
   * @return Retorna a Data de referência do histórico.
   */
  public Date getMrDocCallbackRefDate()
  {
    return m_mrDocCallbackRefDate;
  }

  /**
   * Seta a Data de referência do histórico.
   * 
   * @param mrDocCallbackRefDate_ - Data de referência do histórico.
   */
  public void setMrDocCallbackRefDate( Date mrDocCallbackRefDate_ )
  {
    m_mrDocCallbackRefDate = mrDocCallbackRefDate_;
  }

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
   * Recupera o Status do Registro
   * 
   * @return Retorna o Status do Registro
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Seta o Status do Registro
   * 
   * @param recStatCode_ - O Status do Registro
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * Cria novo objeto TplMrDocCallbackHistEntityVO
   */
  public TplMrDocCallbackHistEntityVO()
  {
    //
  }

  /**
   * Cria novo objeto TplMrDocCallbackHistEntityVO com valores definidos.
   * 
   * TplMrDocCallbackEntity mrDocCallbackEntity_ - Entidade com os valores de
   * Current Date mrDocRefDate_ - Data de referência do histórico.
   */
  public TplMrDocCallbackHistEntityVO(
                                      TplMrDocCallbackEntity mrDocCallbackEntity_,
                                      Date mrDocRefDate_ )
  {
    TplMrDocCallbackEntityVO mrDocCallbackEntityVO = ( TplMrDocCallbackEntityVO ) mrDocCallbackEntity_.getData();

    setMrDocCallbackRefDate( mrDocRefDate_ );
    setLastAuthUserId( mrDocCallbackEntityVO.getLastAuthUserId() );
    setLastUpdUserId( mrDocCallbackEntityVO.getLastUpdUserId() );
    setLastAuthDate( mrDocCallbackEntityVO.getLastAuthDate() );
    setLastUpdDate( mrDocCallbackEntityVO.getLastUpdDate() );
    setRecStatCode( mrDocCallbackEntityVO.getRecStatCode() );
    setMrDocCode( mrDocCallbackEntityVO.getMrDocCode() );
    setCustNbr( mrDocCallbackEntityVO.getCustNbr() );
    setCtcNbr( mrDocCallbackEntityVO.getCtcNbr() );
    setMrCallbackCode( mrDocCallbackEntityVO.getMrCallbackCode() );
    setProdAcctCode( mrDocCallbackEntityVO.getProdAcctCode() );
    setProdUnderAcctCode( mrDocCallbackEntityVO.getProdUnderAcctCode() );
  }
}