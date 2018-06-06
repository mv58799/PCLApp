/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class BaseTplErEntityVO extends BaseEntityVO
{

	private String erNbr;

	private String erReltnTrfInd;

	private BigInteger reltnEndReasCode;

	private String reltnEndReasText;

	private BigInteger equityClassCode;

	private Date lastUpdDate;

	private String lastUpdUserId;

	private String recStatCode;
	
	private Date lastAuthDate;

	private String lastAuthUserId;	
	
	/**
	 * @return
	 */
	public BigInteger getEquityClassCode() {
		return equityClassCode;
	}

	/**
	 * @return
	 */
	public String getErNbr() {
		return erNbr;
	}

	/**
	 * @return
	 */
	public String getErReltnTrfInd() {
		return erReltnTrfInd;
	}

	/**
	 * @return
	 */
	public Date getLastUpdDate() {
		return lastUpdDate;
	}

	/**
	 * @return
	 */
	public String getLastUpdUserId() {
		return lastUpdUserId;
	}

	/**
	 * @return
	 */
	public String getRecStatCode() {
		return recStatCode;
	}

	/**
	 * @return
	 */
	public BigInteger getReltnEndReasCode() {
		return reltnEndReasCode;
	}

	/**
	 * @return
	 */
	public String getReltnEndReasText() {
		return reltnEndReasText;
	}

	/**
	 * @param integer
	 */
	public void setEquityClassCode(BigInteger equityClassCode) {
		this.equityClassCode = equityClassCode;
	}

	/**
	 * @param integer
	 */
	public void setErNbr(String erNbr) {
		this.erNbr = erNbr;
	}

	/**
	 * @param string
	 */
	public void setErReltnTrfInd(String erReltnTrfInd) {
		this.erReltnTrfInd = erReltnTrfInd;
	}

	/**
	 * @param date
	 */
	public void setLastUpdDate(Date lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	/**
	 * @param string
	 */
	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}

	/**
	 * @param string
	 */
	public void setRecStatCode(String recStatCode) {
		this.recStatCode = recStatCode;
	}

	/**
	 * @param integer
	 */
	public void setReltnEndReasCode(BigInteger reltnEndReasCode) {
		this.reltnEndReasCode = reltnEndReasCode;
	}

	/**
	 * @param string
	 */
	public void setReltnEndReasText(String reltnEndReasText) {
		this.reltnEndReasText = reltnEndReasText;
	}

	/**
	 * @return
	 */
	public Date getLastAuthDate() {
		return lastAuthDate;
	}

	/**
	 * @return
	 */
	public String getLastAuthUserId() {
		return lastAuthUserId;
	}

	/**
	 * @param date
	 */
	public void setLastAuthDate(Date date) {
		lastAuthDate = date;
	}

	/**
	 * @param string
	 */
	public void setLastAuthUserId(String string) {
		lastAuthUserId = string;
	}

}
