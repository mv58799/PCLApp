/*
 * Created on 14/11/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.client.ipdocprvt.functionality;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import org.apache.log4j.helpers.DateTimeDateFormat;
import org.apache.struts.action.ActionForm;
import com.citibank.ods.Globals;
import com.citibank.ods.Globals.FuncionalityFormatKeys;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.bg.TbgBankEntity;
import com.citibank.ods.entity.bg.TbgBranchEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocTransFinancEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocTransFinancDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocTransFinancDetailFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocTransFinancDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgBankDAO;
import com.citibank.ods.persistence.bg.dao.TbgBranchDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocTransFinancDAO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author lfabiano
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class BaseIpDocTransFinancDetailFnc extends BaseFnc
{  

	protected static final String C_DOC_TRANSFER_CODE_NOT_NULL = "Código do Documento de Transferência";

	protected static final String C_AGN_BANK_CODE_NOT_NULL = "Código do Banco";

	protected static final String C_BRCH_CODE_NOT_NULL = "Código da Agência";

	protected static final String C_CUR_ACCT_NBR_NOT_NULL = "Código da Conta Corrente";

	protected static final String C_CUST_NBR_CODE_NOT_NULL = "Número do Cliente";

	protected static final String C_CTC_NBR_CODE_NOT_NULL = "Número do Contato";

	protected static final String C_IP_DOC_CODE_NOT_NULL = "Código do Documento IP";
	
	protected static final String C_TRF_AMT_NBR_NOT_NULL = "Valor";
	
	protected static final String C_CHNNL_ATTD_TEXT_NOT_NULL = "PA";
	
	protected static final String C_TRF_DATE_NOT_NULL = "Data";
	
	protected static final String C_TRF_HOUR_NOT_NULL = "Hora";
	
	protected static final String C_TRF_ACCT_TYPE_NOT_NULL = "Tipo da Conta";

	protected static final String C_IP_DOC_TEXT_NOT_NULL = "Descrição do Documento IP";

	protected static final String C_CUST_NBR_NOT_NULL = "Número do Cliente";

	protected static final String C_TXN_TYPE_CODE = "Tipo de Transação";

	protected static final String C_OWN_DEST_ACCT_IND = "Mesma Titularidade";

	protected static final String C_IP_CALLBACK_CODE_NOT_NULL = "Código do Callback";

	protected static final String C_IP_DOC_CALLBACK = "Callback";

	protected static final String C_DOC_TRANSFER = "Documento de Transferência";

	protected String C_CONTACT_CUST = "Callback";

	protected String C_BANK = "Banco";

	protected String C_BRANCH = "Agência";

	protected String C_INSERT_ACTION = "inserido";

	protected String C_INSERT_ASSOCIATION_ACTION = "inserida";

	protected String C_UPDATE_ASSOCIATION_ACTION = "atualizada";

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm, com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) 
	{
		BaseIpDocTransFinancDetailFncVO baseIpDocTransFinancDetailFncVO = ( BaseIpDocTransFinancDetailFncVO ) fncVO_;
		BaseIpDocTransFinancDetailForm baseIpDocTransFinancDetailForm = ( BaseIpDocTransFinancDetailForm ) form_;

		String custFullNameText = baseIpDocTransFinancDetailForm.getCustFullNameText() != null
						   && baseIpDocTransFinancDetailForm.getCustFullNameText().length() > 0
																				 ? baseIpDocTransFinancDetailForm.getCustFullNameText()
																				 : null;
		
		BigInteger bankCode = baseIpDocTransFinancDetailForm.getBankCode() != null
								 && baseIpDocTransFinancDetailForm.getBankCode().length() > 0
																						 ? new BigInteger(
																										   baseIpDocTransFinancDetailForm.getBankCode() )
																						 : null;
		BigInteger brchCode = baseIpDocTransFinancDetailForm.getBrchCode() != null
							  && baseIpDocTransFinancDetailForm.getBrchCode().length() > 0
																				   ? new BigInteger(
																									 baseIpDocTransFinancDetailForm.getBrchCode() )
																				   : null;

		BigInteger custCurAcctNbr = baseIpDocTransFinancDetailForm.getCustCurAcctNbr() != null
								&& baseIpDocTransFinancDetailForm.getCustCurAcctNbr().length() > 0
																					   ? new BigInteger(
																										 baseIpDocTransFinancDetailForm.getCustCurAcctNbr() )
																					   : null;
		
		String invstCurAcctNbr = baseIpDocTransFinancDetailForm.getInvstCurAcctNbr() != null							 
																					? baseIpDocTransFinancDetailForm.getInvstCurAcctNbr() 
																					: null;

		BigInteger docTranferCode = baseIpDocTransFinancDetailForm.getDocTransferCode() != null
									&& baseIpDocTransFinancDetailForm.getDocTransferCode().length() > 0
																								? new BigInteger(
																												  baseIpDocTransFinancDetailForm.getDocTransferCode() )
																								: null;

		BigInteger beneCurAcctNbr = baseIpDocTransFinancDetailForm.getBeneCurAcctNbr() != null
							   && baseIpDocTransFinancDetailForm.getBeneCurAcctNbr().length() > 0
																					 ? new BigInteger(
																									   baseIpDocTransFinancDetailForm.getBeneCurAcctNbr() )
																					 : null;
																					 
		String beneCpfCnpjNbr = baseIpDocTransFinancDetailForm.getBeneCpfCnpjNbr() != null
								? baseIpDocTransFinancDetailForm.getBeneCpfCnpjNbr() 
								: null;																			 
		
		String beneNameText = baseIpDocTransFinancDetailForm.getBeneNameText() != null
								? baseIpDocTransFinancDetailForm.getBeneNameText()
								: null;
																						   
		BigDecimal trfAmtNbr = baseIpDocTransFinancDetailForm.getTrfAmtNbr() != null
									   && baseIpDocTransFinancDetailForm.getTrfAmtNbr().length() > 0
																							 ? new BigDecimal(baseIpDocTransFinancDetailForm.getTrfAmtNbr().replace(",", ".") )
																							 : null;
																							 
		BigInteger trfAcctType = baseIpDocTransFinancDetailForm.getTrfAcctType() != null
		                         && baseIpDocTransFinancDetailForm.getTrfAcctType().length() > 0
															  ? new BigInteger(baseIpDocTransFinancDetailForm.getTrfAcctType() )
															  : null;																					 
		
		SimpleDateFormat formatter = new SimpleDateFormat(FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
		Date trfDate = new Date();
		try
		{ 	
		  trfDate = formatter.parse(baseIpDocTransFinancDetailForm.getTrfDate());
		}
		catch ( Exception e )
		{
		  trfDate = null;
		}				   
						   
		String chnnlAttdText = baseIpDocTransFinancDetailForm.getChnnlAttdText() != null
								   && baseIpDocTransFinancDetailForm.getChnnlAttdText().length() > 0
																								 ? baseIpDocTransFinancDetailForm.getChnnlAttdText()
																								 : null;

		BigInteger ipDocCode = baseIpDocTransFinancDetailForm.getIpDocCode() != null
								 && baseIpDocTransFinancDetailForm.getIpDocCode().length() > 0
																						 ? new BigInteger(baseIpDocTransFinancDetailForm.getIpDocCode() )
																						 : null;
																						 
		String beneMainDestAcctInd = baseIpDocTransFinancDetailForm.getBeneMainDestAcctInd() != null
		                            ? baseIpDocTransFinancDetailForm.getBeneMainDestAcctInd()
		                            : null;
		                            
		String beneAcctTypeCode = baseIpDocTransFinancDetailForm.getBeneAcctTypeCode() != null
		                        ? baseIpDocTransFinancDetailForm.getBeneAcctTypeCode()
		                        : null;          
		                        
		String tpOperacao =   baseIpDocTransFinancDetailForm.getTpOperacao()!= null
							  ?baseIpDocTransFinancDetailForm.getTpOperacao()
							  :null;                                        
																							   
		baseIpDocTransFinancDetailFncVO.setTpOperacao(tpOperacao);
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setIpDocCode(ipDocCode);
		baseIpDocTransFinancDetailFncVO.getBaseTplCustomerPrvtEntity().getData().setCustFullNameText(custFullNameText);
		baseIpDocTransFinancDetailFncVO.getTo3ProductAccountEntity().getData().setCurAcctNbr(custCurAcctNbr);
		baseIpDocTransFinancDetailFncVO.getTo3ProductAccountEntity().getData().setInvestCurAcctNbr(invstCurAcctNbr);
		baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().setDocTransferCode(docTranferCode);
		baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().setAgnBankCode(bankCode);
		baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().setBrchCode(brchCode);
		baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().setCurAcctNbr(beneCurAcctNbr);
		baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneCpfCnpjNbr(beneCpfCnpjNbr);
		baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneNameText(beneNameText);
		baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneMainDestAcctInd(beneMainDestAcctInd);
		baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneAcctTypeCode(beneAcctTypeCode);
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().setChnnlAttdText(chnnlAttdText);
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().setTrfAmtNbr(trfAmtNbr);
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().setTrfDate(trfDate);
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().setTrfAcctType(trfAcctType);
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm, com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		
		BaseIpDocTransFinancDetailFncVO baseIpDocTransFinancDetailFncVO = ( BaseIpDocTransFinancDetailFncVO ) fncVO_;
		BaseIpDocTransFinancDetailForm baseIpDocTransFinancDetailForm = ( BaseIpDocTransFinancDetailForm ) form_;
		
		String ipDocCode = baseIpDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode() != null
		                    ?   baseIpDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode().toString()
		                    :"";
		
		String invstCurAcctNbr = baseIpDocTransFinancDetailFncVO.getTo3ProductAccountEntity().getData().getInvestCurAcctNbr() != null
		                         ? baseIpDocTransFinancDetailFncVO.getTo3ProductAccountEntity().getData().getInvestCurAcctNbr()
		                         : "";
		                        
		String CustCurAcctNbr = baseIpDocTransFinancDetailFncVO.getTo3ProductAccountEntity().getData().getCurAcctNbr() != null
		                    ? baseIpDocTransFinancDetailFncVO.getTo3ProductAccountEntity().getData().getCurAcctNbr().toString()
		                     : "";
		                     
		String custFullNameText = baseIpDocTransFinancDetailFncVO.getBaseTplCustomerPrvtEntity().getData().getCustFullNameText() != null
		                          ? baseIpDocTransFinancDetailFncVO.getBaseTplCustomerPrvtEntity().getData().getCustFullNameText()
		                          : "";
		                          
		String docTranferCode = baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getDocTransferCode() != null
		                       ? baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getDocTransferCode().toString()
		                       : "";
		                       
		String bankCode = baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode() != null
		                  ? baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode().toString()
		                  : "";
		                  
		String brchCode = baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBrchCode() != null
		                  ? baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBrchCode().toString()
		                  : "";
		               
		String beneCpfCnpjNbr = baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneCpfCnpjNbr() != null
		                      ? baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneCpfCnpjNbr()
		                      : "";                                                                                                            
 
        String beneNameText = baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneNameText() != null
		                      ? baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneNameText()
		                      : "";
		                      
		String beneCurAcctNbr = baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getCurAcctNbr() != null
		                        ? baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getCurAcctNbr().toString()
		                        : "";    
		                        
		                        
		String chnnlAttdText = baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getChnnlAttdText()!= null
							   ?baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getChnnlAttdText()
							   : "";	
				
				
		BigDecimal trfAmtNbr = 	baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfAmtNbr()!= null
							? baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfAmtNbr()
							: null;
							
		Date trfDate = baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfDate()!= null
						 ? baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfDate()
						 : null;
						 
		SimpleDateFormat formatter = new SimpleDateFormat(FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
		String trfDateFmt = trfDate != null
						  ? formatter.format(trfDate)
						  :"";
						 
		
		String trfAcctType = baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfAcctType()!= null
							 ? baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfAcctType().toString()
							 : "";
							 
		String beneMainDestAcctInd = baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneMainDestAcctInd() != null
											? baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneMainDestAcctInd()
											: null;
		                            
		String beneAcctTypeCode = baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode() != null
								  ? baseIpDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode()
								  : null;
								  
		String tpOperacao = baseIpDocTransFinancDetailFncVO.getTpOperacao() != null						  							 																						   																						   
                           ? baseIpDocTransFinancDetailFncVO.getTpOperacao()
                           : null;
                           
		NumberFormat nf = new DecimalFormat("#0.00");		
		String trfAmtNbrFmt = null;
		try{
			trfAmtNbrFmt = nf.format(trfAmtNbr);			
		}
		catch(Exception e){
			trfAmtNbrFmt = "";
		}
						
		baseIpDocTransFinancDetailForm.setTpOperacao(tpOperacao);
		baseIpDocTransFinancDetailForm.setIpDocCode(ipDocCode);
		baseIpDocTransFinancDetailForm.setCustCurAcctNbr(CustCurAcctNbr);
		baseIpDocTransFinancDetailForm.setInvstCurAcctNbr(invstCurAcctNbr);
		baseIpDocTransFinancDetailForm.setCustFullNameText(custFullNameText);
		baseIpDocTransFinancDetailForm.setDocTransferCode(docTranferCode);
		baseIpDocTransFinancDetailForm.setBankCode(bankCode);
		baseIpDocTransFinancDetailForm.setBrchCode(brchCode);
		baseIpDocTransFinancDetailForm.setBeneCpfCnpjNbr(beneCpfCnpjNbr);
		baseIpDocTransFinancDetailForm.setBeneNameText(beneNameText);
		baseIpDocTransFinancDetailForm.setBeneCurAcctNbr(beneCurAcctNbr);
		baseIpDocTransFinancDetailForm.setChnnlAttdText(chnnlAttdText);
		baseIpDocTransFinancDetailForm.setTrfAcctType(trfAcctType);
		baseIpDocTransFinancDetailForm.setTrfAmtNbr(trfAmtNbrFmt);
		baseIpDocTransFinancDetailForm.setTrfDate(trfDateFmt);
		baseIpDocTransFinancDetailForm.setBeneMainDestAcctInd(beneMainDestAcctInd);
		baseIpDocTransFinancDetailForm.setBeneAcctTypeCode(beneAcctTypeCode);		
		
	}

	protected void loadCustFullNameText(BaseIpDocTransFinancDetailFncVO fncVO_ )
	{
		
      //Casting do fncVO específico
	  BaseIpDocTransFinancDetailFncVO ipDocTransFinancDetailFncVO = ( BaseIpDocTransFinancDetailFncVO ) fncVO_;
	  
	  TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
	  customerPrvtEntity.getData().setCustNbr(ipDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() );

	  //Obtém a instância da Factory
	  ODSDAOFactory factory = ODSDAOFactory.getInstance();
	  //Obtém a instância do DAO necessário
	  TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

	  //Verifica se existe o cliente, se existir carrega o nome do cliente.
	  if ( tplCustomerPrvtDAO.exists( customerPrvtEntity ) )
	  {
		//Realiza a consulta no DAO
		customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );		
		ipDocTransFinancDetailFncVO.setBaseTplCustomerPrvtEntity(customerPrvtEntity);

	  }
		
	}	
	
	protected abstract BaseTplIpDocTransFinancDAO getDAO();


}