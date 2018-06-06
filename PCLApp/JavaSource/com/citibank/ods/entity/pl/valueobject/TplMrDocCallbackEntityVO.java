package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.entity.pl.TplMrDocCallbackMovEntity;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de relacionamento Contato X Memória de Risco.
 */
public class TplMrDocCallbackEntityVO extends BaseTplMrDocCallbackEntityVO
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

  public TplMrDocCallbackEntityVO()
  {
    //
  }

  /**
   * Cria novo objeto TplMrDocCallbackEntityVO com valores definidos.
   * 
   * @param mrDocCallbackMovEntity_ - Entidade com os valores de Movimento do
   *          Cadastro.
   * @param lastAuthDate_ - A data da aprovação.
   * @param lastAuthUserId_ - O usuário da aprovação.
   */
  public TplMrDocCallbackEntityVO(
                                  TplMrDocCallbackMovEntity mrDocCallbackMovEntity_,
                                  Date lastAuthDate_, String lastAuthUserId_ )
  {
    TplMrDocCallbackMovEntityVO mrDocCallbackMovEntityVO = ( TplMrDocCallbackMovEntityVO ) mrDocCallbackMovEntity_.getData();

    setMrCallbackCode( mrDocCallbackMovEntityVO.getMrCallbackCode() );
    setLastUpdUserId( mrDocCallbackMovEntityVO.getLastUpdUserId() );
    setLastAuthUserId( lastAuthUserId_ );
    setLastUpdDate( mrDocCallbackMovEntityVO.getLastUpdDate() );
    setLastAuthDate( lastAuthDate_ );
    setProdAcctCode( mrDocCallbackMovEntityVO.getProdAcctCode() );
    setProdUnderAcctCode( mrDocCallbackMovEntityVO.getProdUnderAcctCode() );
    setCustNbr( mrDocCallbackMovEntityVO.getCustNbr() );
    setCtcNbr( mrDocCallbackMovEntityVO.getCtcNbr() );
    setMrDocCode( mrDocCallbackMovEntityVO.getMrDocPrvt() );
    if (mrDocCallbackMovEntityVO.getOpernCode().equals("E")) 
    {
      setRecStatCode( "I" );      
    } 
    else 
    {
      setRecStatCode( "A" );
    }
  }
   
}