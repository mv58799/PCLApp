/*
 * Created on 13/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.broker.functionality;

import java.math.BigInteger;

/**
 * @author citi
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ProductListGrid {
	
	private String prodCode;
	
	private String prodText;
	
	private String prodName;
	
	private String sysCode;
	
	private BigInteger sysSegCode;
	
	private String prodFamlName;
	
	private String prodSubFamlName;
	
	private String aprovacao;
	

	/**
	 * @return
	 */
	public String getProdCode() {
		return prodCode;
	}

	/**
	 * @return
	 */
	public String getProdFamlName() {
		return prodFamlName;
	}

	/**
	 * @return
	 */
	public String getProdSubFamlName() {
		return prodSubFamlName;
	}

	/**
	 * @return
	 */
	public String getProdText() {
		return prodText;
	}

	/**
	 * @return
	 */
	public String getSysCode() {
		return sysCode;
	}

	/**
	 * @return
	 */
	public BigInteger getSysSegCode() {
		return sysSegCode;
	}

	/**
	 * @param string
	 */
	public void setProdCode(String prodCode_) {
		prodCode = prodCode_;
	}

	/**
	 * @param string
	 */
	public void setProdFamlName(String prodFamlName_) {
		prodFamlName = prodFamlName_;
	}

	/**
	 * @param string
	 */
	public void setProdSubFamlName(String prodSubFamlName_) {
		prodSubFamlName = prodSubFamlName_;
	}

	/**
	 * @param string
	 */
	public void setProdText(String prodText_) {
		prodText = prodText_;
	}

	/**
	 * @param string
	 */
	public void setSysCode(String sysCode_) {
		sysCode = sysCode_;
	}

	/**
	 * @param integer
	 */
	public void setSysSegCode(BigInteger sysSegCode_) {
		sysSegCode = sysSegCode_;
	}

	/**
	 * @return
	 */
	public String getProdName() {
		return prodName;
	}

	/**
	 * @param string
	 */
	public void setProdName(String prodName_) {
		prodName = prodName_;
	}
	/**
	 * @return
	 */
	public String getAprovacao() {
		return aprovacao;
	}
	
	/**
	 * @param string
	 */
	public void setAprovacao(String aprovacao_) {
		aprovacao = aprovacao_;
	}

}
