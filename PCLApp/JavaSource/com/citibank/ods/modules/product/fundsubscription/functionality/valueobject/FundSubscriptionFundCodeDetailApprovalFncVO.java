package com.citibank.ods.modules.product.fundsubscription.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

@SuppressWarnings("serial")
public class FundSubscriptionFundCodeDetailApprovalFncVO extends BaseFncVO {
	
	private String codigo;
	private String nome;
	private String selectedCode;
	private Boolean canApprove;
	
	
	public Boolean getCanApprove() {
		return canApprove;
	}
	public void setCanApprove(Boolean canApprove) {
		this.canApprove = canApprove;
	}
	public String getSelectedCode() {
		return selectedCode;
	}
	public void setSelectedCode(String selectedCode) {
		this.selectedCode = selectedCode;
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
