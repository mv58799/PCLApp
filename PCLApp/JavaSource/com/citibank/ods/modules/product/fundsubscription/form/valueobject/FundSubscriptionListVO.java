package com.citibank.ods.modules.product.fundsubscription.form.valueobject;

import com.citibank.ods.common.form.BaseForm;

@SuppressWarnings("serial")
public class FundSubscriptionListVO extends BaseForm{
	private String protocolo;
	private String codigoFundo;
	private String nomeProduto;
	private String nomeCliente;
	private String evento;
	
	
	public FundSubscriptionListVO() {}
	public FundSubscriptionListVO(String protocolo, String codigoFundo, String nomeProduto, String nomeCliente, String evento) {
		this.protocolo = protocolo;
		this.codigoFundo = codigoFundo;
		this.nomeProduto = nomeProduto;
		this.nomeCliente = nomeCliente;
		this.evento = evento;
	}
	public String getProtocolo() {
		return protocolo;
	}
	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
	public String getCodigoFundo() {
		return codigoFundo;
	}
	public void setCodigoFundo(String codigoFundo) {
		this.codigoFundo = codigoFundo;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}

	
}
