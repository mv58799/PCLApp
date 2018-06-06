package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

@SuppressWarnings("serial")
public class FundSubscriptionFundCodeUpdateFncVO extends BaseFncVO{
	
	private String codigo;
	private String nome;
	
	private String codigoAnterior;
	private String nomeAnterior;
	
	private String selectedCode;

	private Boolean blockConfirm = false;
	
	public Boolean getBlockConfirm() {
		return blockConfirm;
	}
	public void setBlockConfirm(Boolean blockCOnfirm) {
		this.blockConfirm = blockCOnfirm;
	}
	
	public String getSelectedCode() {
		return selectedCode;
	}
	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
	}
	
	public String getCodigoAnterior() {
		return codigoAnterior;
	}
	public void setCodigoAnterior(String codigoAnterior) {
		this.codigoAnterior = codigoAnterior;
	}
	public String getNomeAnterior() {
		return nomeAnterior;
	}
	public void setNomeAnterior(String nomeAnterior) {
		this.nomeAnterior = nomeAnterior;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	


	
}
