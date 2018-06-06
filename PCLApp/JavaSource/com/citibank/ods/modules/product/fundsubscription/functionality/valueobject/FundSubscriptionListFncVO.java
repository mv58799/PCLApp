package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import java.util.Set;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionListVO;

public class FundSubscriptionListFncVO extends BaseFncVO{
	private static final long serialVersionUID = 2687234805471620237L;

	private String nomeCliente;
	private String numeroConta;
	private String tipoProduto;
	private String nomeProduto;
	private Set<FundSubscriptionListVO> resultado;
	
	
	
	public Set<FundSubscriptionListVO> getResultado() {
		return resultado;
	}
	public void setResultado(Set<FundSubscriptionListVO> resultado) {
		this.resultado = resultado;
	}
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
	
	
	
}
