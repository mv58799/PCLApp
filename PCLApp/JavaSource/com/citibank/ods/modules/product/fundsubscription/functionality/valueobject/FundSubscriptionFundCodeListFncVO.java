package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import java.util.Collection;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.fundsubscription.form.FundSubscriptionFundCodeUpdateForm;

@SuppressWarnings("serial")
public class FundSubscriptionFundCodeListFncVO extends BaseFncVO{
	private String nomeFundo;
	private boolean fundosComCodigo;
	private Collection<FundSubscriptionFundCodeUpdateForm> resultado;
	
	public String getNomeFundo() {
		return nomeFundo;
	}
	public void setNomeFundo(String nomeFundo) {
		this.nomeFundo = nomeFundo;
	}
	public boolean isFundosComCodigo() {
		return fundosComCodigo;
	}
	public boolean getFundosComCodigo() {
		return fundosComCodigo;
	}
	public void setFundosComCodigo(boolean fundosComCodigo) {
		this.fundosComCodigo = fundosComCodigo;
	}
	public Collection<FundSubscriptionFundCodeUpdateForm> getResultado() {
		return resultado;
	}
	public void setResultado(
			Collection<FundSubscriptionFundCodeUpdateForm> resultado) {
		this.resultado = resultado;
	}
	

	
}
