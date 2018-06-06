package com.citibank.ods.entity.pl.valueobject;

public class TplRiskFamilyProdPlayerMovEntityVO extends BaseTplRiskFamilyProdPlayerEntityVO {
	/**
	 * Codigo da Operacao realizada no registro: inclusao, alteracao, exclusao
	 */
	private String opernCode;

	public String getOpernCode() {
		return opernCode;
	}

	public void setOpernCode(String opernCode) {
		this.opernCode = opernCode;
	}

}
