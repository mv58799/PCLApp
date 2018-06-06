/*
 * Created on 04/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.client.customerprvt.functionality;

import java.math.BigInteger;

/**
 * @author Ronaldo Machado G&P - Java Team
 *
 * Classe que representa os dados para o grid de Customer 
 */

public class CustomerPrvtGrid {
	
	private Integer custNbr;
	
	private String custFullNameText;
	
	private int cmplDataButtonControl;
	
	private BigInteger custCpfCnpjNbr;
	
	private BigInteger reltnNbr;
	
	private BigInteger curAcctNbr;
	
	private String offcrNameText;
	
	private BigInteger prvtCustNbr;
	
	private BigInteger prvtPrincCustNbr;
	
	private String aprovacao;

	/**
	 * @return
	 */
	public int getCmplDataButtonControl() {
		return cmplDataButtonControl;
	}

	/**
	 * @return
	 */
	public BigInteger getCurAcctNbr() {
		return curAcctNbr;
	}

	/**
	 * @return
	 */
	public BigInteger getCustCpfCnpjNbr() {
		return custCpfCnpjNbr;
	}

	/**
	 * @return
	 */
	public String getCustFullNameText() {
		return custFullNameText;
	}

	/**
	 * @return
	 */
	public Integer getCustNbr() {
		return custNbr;
	}

	/**
	 * @return
	 */
	public String getOffcrNameText() {
		return offcrNameText;
	}

	/**
	 * @return
	 */
	public BigInteger getPrvtCustNbr() {
		return prvtCustNbr;
	}

	/**
	 * @return
	 */
	public BigInteger getPrvtPrincCustNbr() {
		return prvtPrincCustNbr;
	}

	/**
	 * @return
	 */
	public BigInteger getReltnNbr() {
		return reltnNbr;
	}

	/**
	 * @param i
	 */
	public void setCmplDataButtonControl(int cmplDataButtonControl) {
		this.cmplDataButtonControl = cmplDataButtonControl;
	}

	/**
	 * @param integer
	 */
	public void setCurAcctNbr(BigInteger curAcctNbr) {
		this.curAcctNbr = curAcctNbr;
	}

	/**
	 * @param integer
	 */
	public void setCustCpfCnpjNbr(BigInteger custCpfCnpjNbr) {
		this.custCpfCnpjNbr = custCpfCnpjNbr;
	}

	/**
	 * @param string
	 */
	public void setCustFullNameText(String custFullNameText) {
		this.custFullNameText = custFullNameText;
	}

	/**
	 * @param integer
	 */
	public void setCustNbr(Integer custNbr) {
		this.custNbr = custNbr;
	}

	/**
	 * @param string
	 */
	public void setOffcrNameText(String offcrNameText) {
		this.offcrNameText = offcrNameText;
	}

	/**
	 * @param integer
	 */
	public void setPrvtCustNbr(BigInteger prvtCustNbr) {
		this.prvtCustNbr = prvtCustNbr;
	}

	/**
	 * @param integer
	 */
	public void setPrvtPrincCustNbr(BigInteger prvtPrincCustNbr) {
		this.prvtPrincCustNbr = prvtPrincCustNbr;
	}

	/**
	 * @param integer
	 */
	public void setReltnNbr(BigInteger reltnNbr) {
		this.reltnNbr = reltnNbr;
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
