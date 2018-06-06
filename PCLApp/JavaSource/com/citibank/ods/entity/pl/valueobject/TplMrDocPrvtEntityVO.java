package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.entity.pl.TplMrDocPrvtMovEntity;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Memória de Risco.
 */
public class TplMrDocPrvtEntityVO extends BaseTplMrDocPrvtEntityVO
{
  // Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private Date m_lastAuthDate = null;

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Codigo Documento MR
  private BigInteger m_mrDocPrvt = null;

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
   * Recupera Status do Registro
   * 
   * @return Retorna Status do Registro
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * Seta Status do Registro
   * 
   * @param recStatCode_ - Status do Registro
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * Cria novo objeto TplMrDocPrvtEntityVO com valores definidos.
   * 
   * @param mrDocPrvtMovEntity_ - Entidade com os valores de Movimento do
   *          Cadastro.
   * @param lastAuthDate_ - A data da aprovação
   * @param lastAuthUserId_ - O usuário da aprovação
   */
  public TplMrDocPrvtEntityVO( TplMrDocPrvtMovEntity mrDocPrvtMovEntity_,
                              Date lastAuthDate_, String lastAuthUserId_ )
  {
    TplMrDocPrvtMovEntityVO mrDocPrvtMovEntityVO = ( TplMrDocPrvtMovEntityVO ) mrDocPrvtMovEntity_.getData();

    setMrDocPrvt( mrDocPrvtMovEntityVO.getMrDocCode() );
    setMrDocText( mrDocPrvtMovEntityVO.getMrDocText() );
    setMrInvstCurAcctInd( mrDocPrvtMovEntityVO.getMrInvstCurAcctInd() );
    setLastAuthUserId( lastAuthUserId_ );
    setLastAuthDate( lastAuthDate_ );
    setLastUpdUserId( mrDocPrvtMovEntityVO.getLastUpdUserId() );
    setLastUpdDate( mrDocPrvtMovEntityVO.getLastUpdDate() );
    if ( !mrDocPrvtMovEntityVO.getOpernCode().equals( "E" ) )
    {
      setRecStatCode( "A" );
    }
    else
    {
      setRecStatCode( "I" );
    }
    setProdAcctCode( mrDocPrvtMovEntityVO.getProdAcctCode() );
    setProdUnderAcctCode( mrDocPrvtMovEntityVO.getProdUnderAcctCode() );
  }

  /**
   * Cria novo objeto TplMrDocPrvtEntityVO.
   */
  public TplMrDocPrvtEntityVO()
  {
    //
  }
}