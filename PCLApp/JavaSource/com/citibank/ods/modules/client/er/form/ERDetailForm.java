/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.form;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERDetailForm extends BaseERDetailForm
{    
  //Data e Hora que o Usuario Aprovou o Registro Cadastrado.
  private String lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String lastAuthUserId = "";

/**
 * @return
 */
  public String getLastAuthDate() {
	return lastAuthDate;
  }

/**
 * @return
 */
  public String getLastAuthUserId() {
	return lastAuthUserId;
  }

/**
 * @param string
 */
  public void setLastAuthDate(String lastAuthDate) {
	this.lastAuthDate = lastAuthDate;
  }

/**
 * @param string
 */
  public void setLastAuthUserId(String lastAuthUserId) {
	this.lastAuthUserId = lastAuthUserId;
  }


}
