package com.citibank.ods.modules.client.ipdocprvt.form;
//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * Interface implementada para passar os campos chave da tabela
 * TPL_PRMNT_INSTR_PRVT_HIST para a tela de detalhe
 *  
 */

public interface IpDocPrvtHistoryDetailable
{
  
	/**
	 * @return Retorna o n�mero do cliente selecionado no grid.
	 */
	public String getSelectedCustNbr();
	/**
	 * @param CustNbr_.Seta o n�mero do cliente selecionado.
	 */
	public void setSelectedCustNbr(String CustNbr_);
	
	/**
	 * @return Retorna o c�digo da instru��o permanente.
	 */
	public String getSelectedIpDocCode();
	/**
	 * @param ipDocCode_.Seta o c�digo da instru��o permanente.
	 */
	public void setSelectedIpDocCode(String ipDocCode_);
	
	/**
	 * @return Retorna a data de refer�ncia.
	 */
	public String getSelectedIpDocRefDate();
	/**
	 * @param ipDocRefDate_.Seta a data de refer�ncia.
	 */
	public void setSelectedIpDocRefDate(String ipDocRefDate_);
	
	
	
	

}