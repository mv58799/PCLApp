/*
 * Created on 14/11/2008
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class TplIpDocTransFinancEntityVO extends BaseTplIpDocTransFinancEntityVO
{

  /**
   * Data e Hora que o Usuario Aprovou o Registro Cadastrado.
   */
  private Date m_lastAuthDate;

  /**
   * Codigo do Usuario que Aprovou o Cadastro do Registro.
   */
  private String m_lastAuthUserId;

  /**
   * Codigo da Acao que originou o registro (I-Insert; U-update)
   */
  private String m_opernCode;

  /**
   * "Status do Registro Constraint: 'S' (Ativo), 'N' (Inativo), 'A' (Aguardando
   * Aprovacao)"
   */
  private String m_recStatCode;

  /**
   * Retorna atributo lastAuthDate
   * @author lfabiano
   * @date 14/11/2008
   */
  public Date getLastAuthDate()
  {
	return m_lastAuthDate;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_lastAuthDate
   * @param lastAuthDate
   */
  public void setLastAuthDate( Date lastAuthDate_ )
  {
	m_lastAuthDate = lastAuthDate_;
  }

  /**
   * Retorna atributo lastAuthUserId
   * @author lfabiano
   * @date 14/11/2008
   */
  public String getLastAuthUserId()
  {
	return m_lastAuthUserId;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_lastAuthUserId
   * @param lastAuthUserId
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
	m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * Retorna atributo opernCode
   * @author lfabiano
   * @date 14/11/2008
   */
  public String getOpernCode()
  {
	return m_opernCode;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_opernCode
   * @param opernCode
   */
  public void setOpernCode( String opernCode_ )
  {
	m_opernCode = opernCode_;
  }

  /**
   * Retorna atributo recStatCode
   * @author lfabiano
   * @date 14/11/2008
   */
  public String getRecStatCode()
  {
	return m_recStatCode;
  }

  /**
   * Atribui o valor passado como parametro a variavel m_recStatCode
   * @param recStatCode
   */
  public void setRecStatCode( String recStatCode_ )
  {
	m_recStatCode = recStatCode_;
  }
}