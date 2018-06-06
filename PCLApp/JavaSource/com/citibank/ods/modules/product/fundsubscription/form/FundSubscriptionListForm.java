package com.citibank.ods.modules.product.fundsubscription.form;

import java.util.Set;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionListVO;

public class FundSubscriptionListForm extends BaseForm{
	private static final long serialVersionUID = 3708794612849374462L;

	private String nomeCliente;
	private String numeroConta;
	private String tipoProduto;
	private String nomeProduto;
	private Set<FundSubscriptionListVO> resultado;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNumeroConta() {
		return numeroConta;
	}
	public void setNumeroConta(String evento) {
		this.numeroConta = evento;
	}
	public String getTipoProduto() {
		return tipoProduto;
	}
	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Set<FundSubscriptionListVO> getResultado() {
		return resultado;
	}
	public void setResultado(Set<FundSubscriptionListVO> resultado) {
		this.resultado = resultado;
	}
	

}
