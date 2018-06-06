package com.citibank.ods.modules.client.ipdocprvt.form;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Interface implementada para passar os campos chave da tabela
 * TPL_PRMNT_INSTR_PRVT_HIST para a tela de detalhe
 *  
 */

public interface IpDocPrvtHistoryDetailable
{
  
	/**
	 * @return Retorna o número do cliente selecionado no grid.
	 */
	public String getSelectedCustNbr();
	/**
	 * @param CustNbr_.Seta o número do cliente selecionado.
	 */
	public void setSelectedCustNbr(String CustNbr_);
	
	/**
	 * @return Retorna o código da instrução permanente.
	 */
	public String getSelectedIpDocCode();
	/**
	 * @param ipDocCode_.Seta o código da instrução permanente.
	 */
	public void setSelectedIpDocCode(String ipDocCode_);
	
	/**
	 * @return Retorna a data de referência.
	 */
	public String getSelectedIpDocRefDate();
	/**
	 * @param ipDocRefDate_.Seta a data de referência.
	 */
	public void setSelectedIpDocRefDate(String ipDocRefDate_);
	
	
	
	

}