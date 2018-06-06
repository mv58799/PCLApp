	/*
	 * Created on 14/11/2008
	*/
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class BaseTplIpDocTransFinancEntityVO extends BaseEntityVO
{
	
	/**
	*  Código do Cliente
	*/
	private BigInteger m_custNbr;	
	
	/**
	*  Código da Instrução Permanente
	*/
	private BigInteger m_prmntInstrCode;
	
	/**
	*  Código dos dados de tranferência
	*/
	private BigInteger m_prmntInstrTrfDataCode;
	
	/**
	*  Código da transação financeira
	*/
	private BigInteger m_prmntInstrTrfSeqNbr;
	
	/**
	 * Canal de Atendimento.
	 */
	private String m_chnnlAttdText;
	
	/**
	* Valor da Transferencia.
	*/
	private BigDecimal m_trfAmtNbr;
	
	/**
	* Data da Transferencia.
	*/
	private Date m_trfDate;
	
	/**
	 * Hora da Transferencia.
	*/
    private Date m_trfHour;
    
    private BigInteger m_trfAcctType;
	
	/**
	 * @return
	 */
	public String getChnnlAttdText() {
		return m_chnnlAttdText;
	}

	/**
	 * @return
	 */
	public BigInteger getCustNbr() {
		return m_custNbr;
	}

	/**
	 * @return
	 */
	public BigInteger getPrmntInstrCode() {
		return m_prmntInstrCode;
	}

	/**
	 * @return
	 */
	public BigInteger getPrmntInstrTrfDataCode() {
		return m_prmntInstrTrfDataCode;
	}

	/**
	 * @return
	 */
	public BigInteger getPrmntInstrTrfSeqNbr() {
		return m_prmntInstrTrfSeqNbr;
	}

	/**
	 * @return
	 */
	public BigDecimal getTrfAmtNbr() {
		return m_trfAmtNbr;
	}

	/**
	 * @return
	 */
	public Date getTrfDate() {
		return m_trfDate;
	}

	/**
	 * @return
	 */
	public Date getTrfHour() {
		return m_trfHour;
	}

	/**
	 * @param string
	 */
	public void setChnnlAttdText(String m_chnnlAttdText_) {
		m_chnnlAttdText = m_chnnlAttdText_;
	}

	/**
	 * @param integer
	 */
	public void setCustNbr(BigInteger m_custNbr_) {
		m_custNbr = m_custNbr_;
	}

	/**
	 * @param integer
	 */
	public void setPrmntInstrCode(BigInteger m_prmntInstrCode_) {
		m_prmntInstrCode = m_prmntInstrCode_;
	}

	/**
	 * @param integer
	 */
	public void setPrmntInstrTrfDataCode(BigInteger m_prmntInstrTrfDataCode_) {
		m_prmntInstrTrfDataCode = m_prmntInstrTrfDataCode_;
	}

	/**
	 * @param integer
	 */
	public void setPrmntInstrTrfSeqNbr(BigInteger m_prmntInstrTrfSeqNbr_) {
		m_prmntInstrTrfSeqNbr = m_prmntInstrTrfSeqNbr_;
	}

	/**
	 * @param integer
	 */
	public void setTrfAmtNbr(BigDecimal m_trfAmtNbr_) {
		m_trfAmtNbr = m_trfAmtNbr_;
	}

	/**
	 * @param date
	 */
	public void setTrfDate(Date m_trfDate_) {
		m_trfDate = m_trfDate_;
	}

	/**
	 * @param date
	 */
	public void setTrfHour(Date m_trfHour_) {
		m_trfHour = m_trfHour_;
	}

	/**
	 * @return
	 */
	public BigInteger getTrfAcctType() {
		return m_trfAcctType;
	}

	/**
	 * @param integer
	 */
	public void setTrfAcctType(BigInteger m_trfAcctType_) {
		m_trfAcctType = m_trfAcctType_;
	}

}