package com.citibank.ods.modules.product.fundsubscription.form;

import java.util.Collection;

import com.citibank.ods.common.form.BaseForm;

@SuppressWarnings("serial")
public class FundSubscriptionFundCodeListForm  extends BaseForm {
		
	private String nomeFundo;
	private boolean fundosComCodigo;
	private Collection<FundSubscriptionFundCodeUpdateForm> resultado;
	
	public String getNomeFundo() {
		return nomeFundo;
	}
	public void setNomeFundo(String nomeFundo) {
		this.nomeFundo = nomeFundo;
	}
	public Collection<FundSubscriptionFundCodeUpdateForm> getResultado() {
		return resultado;
	}
	public void setResultado(Collection<FundSubscriptionFundCodeUpdateForm> resultado) {
		this.resultado = resultado;
	}
	
	public boolean isFundosComCodigo() {
		return fundosComCodigo;
	}
	public void setFundosComCodigo(boolean fundosComCodigo) {
		this.fundosComCodigo = fundosComCodigo;
	}
}
