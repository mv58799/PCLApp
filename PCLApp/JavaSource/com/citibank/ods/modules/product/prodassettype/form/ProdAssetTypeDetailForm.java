/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.form;

/**
 * @author rcoelho
 * @since 19/08/2008
 */
public class ProdAssetTypeDetailForm extends BaseProdAssetTypeDetailForm
{

  // Data e Hora que o usuario aprovou o registro cadastrado.
  private String m_lastAuthDate = "";

  // Codigo do usuario (SOE ID) que aprovou o cadastro do registro.
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao.
  private String m_recStatCode = "";

  /**
  * @return Retorna a Data e Hora que o usuario aprovou o registro cadastrado.
  */
  public String getLastAuthDate()
  {
	return m_lastAuthDate;
  }

  /**
  * @param String lastAuthDate_.
  * Seta a Data e Hora que o usuario aprovou o registro cadastrado.
  */
  public void setLastAuthDate( String lastAuthDate_ )
  {
	m_lastAuthDate = lastAuthDate_;
  }

  /**
  * @return Retorna o Codigo do usuario (SOE ID) que aprovou o cadastro do registro.
  */
  public String getLastAuthUserId()
  {
	return m_lastAuthUserId;
  }

  /**
  * @param String lastAuthUserId_.
  * Seta o Codigo do usuario (SOE ID) que aprovou o cadastro do registro.
  */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
	m_lastAuthUserId = lastAuthUserId_;
  }

  /**
  * @return Retorna o Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao.
  */
  public String getRecStatCode()
  {
	return m_recStatCode;
  }

  /**
  * @param String recStatCode_.
  * Seta o Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao.
  */
  public void setRecStatCode( String recStatCode_ )
  {
	m_recStatCode = recStatCode_;
  }

}
