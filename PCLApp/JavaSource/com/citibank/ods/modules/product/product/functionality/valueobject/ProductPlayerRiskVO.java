package com.citibank.ods.modules.product.product.functionality.valueobject;

import java.math.BigDecimal;


/**
 * 
 * Classe que representa a lista de Emiisores associados ao produto, quando o mesmo for do tipo de família Renda Fixa. 
 * 
 * @author jplima
 *
 */
public class ProductPlayerRiskVO {
	
	String playerEmissorText;
	
	String playerEmissorCode;
	
	String catRiskText;
	
	BigDecimal catRiskCode;
	
	public ProductPlayerRiskVO(){
		
	}

	public BigDecimal getCatRiskCode() {
		return catRiskCode;
	}

	public void setCatRiskCode(BigDecimal catRiskCode) {
		this.catRiskCode = catRiskCode;
	}

	public String getCatRiskText() {
		return catRiskText;
	}

	public void setCatRiskText(String catRiskText) {
		this.catRiskText = catRiskText;
	}

	public String getPlayerEmissorCode() {
		return playerEmissorCode;
	}

	public void setPlayerEmissorCode(String playerEmissorCode) {
		this.playerEmissorCode = playerEmissorCode;
	}

	public String getPlayerEmissorText() {
		return playerEmissorText;
	}

	public void setPlayerEmissorText(String playerEmissorText) {
		this.playerEmissorText = playerEmissorText;
	}
	
	

}
