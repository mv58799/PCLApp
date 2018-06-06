package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

public class BaseTplRiskFamilyProdPlayerEntityVO  extends BaseEntityVO{
	
	private String plyrCnpjNbr;
	
	private BigInteger prodFamlCode;
	
	private BigInteger prodInvstRiskCode;
	
	private String lastUpdUserId;
	
	private Date lastUpdDate;
	
	private String prodCode;
	
	private String sysCode;
	
	private BigInteger sysSegCode;
	
	//Campos de Descricao do Emissor e do Risco
	String playerEmissorText;
	String catRiskText;	
	
	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	public String getLastUpdUserId() {
		return lastUpdUserId;
	}

	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}

	public String getPlyrCnpjNbr() {
		return plyrCnpjNbr;
	}

	public void setPlyrCnpjNbr(String plyrCnpjNbr) {
		this.plyrCnpjNbr = plyrCnpjNbr;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public BigInteger getProdFamlCode() {
		return prodFamlCode;
	}

	public void setProdFamlCode(BigInteger prodFamlCode) {
		this.prodFamlCode = prodFamlCode;
	}

	public BigInteger getProdInvstRiskCode() {
		return prodInvstRiskCode;
	}

	public void setProdInvstRiskCode(BigInteger prodInvstRiskCode) {
		this.prodInvstRiskCode = prodInvstRiskCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public BigInteger getSysSegCode() {
		return sysSegCode;
	}

	public void setSysSegCode(BigInteger sysSegCode) {
		this.sysSegCode = sysSegCode;
	}

	public String getCatRiskText() {
		return catRiskText;
	}

	public void setCatRiskText(String catRiskText) {
		this.catRiskText = catRiskText;
	}

	public String getPlayerEmissorText() {
		return playerEmissorText;
	}

	public void setPlayerEmissorText(String playerEmissorText) {
		this.playerEmissorText = playerEmissorText;
	}
	
}
