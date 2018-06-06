package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.entity.pl.TplMrDocPrvtEntity;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Memória de Risco.
 */
public class TplMrDocPrvtHistEntityVO extends BaseTplMrDocPrvtEntityVO
{
  //Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private Date m_lastAuthDate = null;

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Status do Registro: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
  // Aprovacao)
  private String m_recStatCode = "";

  // Codigo Documento MR
  private BigInteger m_mrDocCode = null;

  // Data de referência do histórico
  private Date m_mrDocRefDate = null;

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
   * @param lastAuthDate_- A Data e Hora que o Usuario Aprovou o Registro
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
   * Recupera Data de referência do histórico
   * 
   * @return Retorna Data de referência do histórico
   */
  public Date getMrDocRefDate()
  {
    return m_mrDocRefDate;
  }

  /**
   * Seta Data de referência do histórico
   * 
   * @param mrDocRefDate_ - Data de referência do histórico
   */
  public void setMrDocRefDate( Date mrDocRefDate_ )
  {
    m_mrDocRefDate = mrDocRefDate_;
  }

  /**
   * Recupera Status do Registro.
   * 
   * @return Retorna Status do Registro.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Seta Status do Registro.
   * 
   * @param recStatCode_ - Status do Registro.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * Constrói novo objeto TplMrDocPrvtHistEntityVO.
   */
  public TplMrDocPrvtHistEntityVO()
  {
    //
  }

  /**
   * Constrói novo objeto TplMrDocPrvtHistEntityVO com valores definidos.
   * 
   * @param mrDocPrvtEntity_ - Entidade com os valores correntes.
   * @param mrDocRefDate_ - Data de referência do histórico.
   */
  public TplMrDocPrvtHistEntityVO( TplMrDocPrvtEntity mrDocPrvtEntity_, Date mrDocRefDate_ )
  {
    TplMrDocPrvtEntityVO mrDocPrvtEntityVO = ( TplMrDocPrvtEntityVO ) mrDocPrvtEntity_.getData();

    // Seta a data de referência do histórico com a data da aprovação
    setMrDocRefDate( mrDocRefDate_ );
    
    setMrDocCode( mrDocPrvtEntityVO.getMrDocPrvt() );
    setMrDocText( mrDocPrvtEntityVO.getMrDocText() );
    setMrInvstCurAcctInd( mrDocPrvtEntityVO.getMrInvstCurAcctInd() );
    setLastAuthUserId( mrDocPrvtEntityVO.getLastAuthUserId() );
    setLastAuthDate( mrDocPrvtEntityVO.getLastAuthDate() );
    setLastUpdUserId( mrDocPrvtEntityVO.getLastUpdUserId() );
    setLastUpdDate( mrDocPrvtEntityVO.getLastUpdDate() );
    setRecStatCode( mrDocPrvtEntityVO.getRecStatCode() );
    setProdAcctCode( mrDocPrvtEntityVO.getProdAcctCode() );
    setProdUnderAcctCode( mrDocPrvtEntityVO.getProdUnderAcctCode() );
  }
}