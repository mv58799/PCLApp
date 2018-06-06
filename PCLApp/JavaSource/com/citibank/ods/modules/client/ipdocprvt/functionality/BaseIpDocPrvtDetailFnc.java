package com.citibank.ods.modules.client.ipdocprvt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.bg.TbgBankEntity;
import com.citibank.ods.entity.bg.TbgBranchEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocPrvtDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgBankDAO;
import com.citibank.ods.persistence.bg.dao.TbgBranchDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * @see package com.citibank.ods.modules.client.ipdocprvt.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 *  
 */

public abstract class BaseIpDocPrvtDetailFnc extends BaseFnc
{

  protected static final String C_DOC_TRANSFER_CODE_NOT_NULL = "Código do Documento de Transferência";

  protected static final String C_AGN_BANK_CODE_NOT_NULL = "Código do Banco";

  protected static final String C_BRCH_CODE_NOT_NULL = "Código da Agência";

  protected static final String C_CUR_ACCT_NBR_NOT_NULL = "Código da Conta Corrente";

  protected static final String C_CUST_NBR_CODE_NOT_NULL = "Número do Cliente";

  protected static final String C_CTC_NBR_CODE_NOT_NULL = "Número do Contato";

  protected static final String C_IP_DOC_CODE_NOT_NULL = "Código do Documento IP";

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

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO = ( BaseIpDocPrvtDetailFncVO ) fncVO_;
    BaseIpDocPrvtDetailForm baseIpDocPrvtDetailForm = ( BaseIpDocPrvtDetailForm ) form_;
    
	String[] codeArray = null;
	if(baseIpDocPrvtDetailForm.getSelectedCode()!= null && !baseIpDocPrvtDetailForm.getSelectedCode().equals("")){
	  codeArray = baseIpDocPrvtDetailForm.getSelectedCode().split(","); 	
	}

    BigInteger agnBankCode = baseIpDocPrvtDetailForm.getAgnBankCode() != null
                             && baseIpDocPrvtDetailForm.getAgnBankCode().length() > 0
                                                                                     ? new BigInteger(
                                                                                                       baseIpDocPrvtDetailForm.getAgnBankCode() )
                                                                                     : null;
    BigInteger brchCode = baseIpDocPrvtDetailForm.getBrchCode() != null
                          && baseIpDocPrvtDetailForm.getBrchCode().length() > 0
                                                                               ? new BigInteger(
                                                                                                 baseIpDocPrvtDetailForm.getBrchCode() )
                                                                               : null;

    BigInteger ctcNbr = baseIpDocPrvtDetailForm.getCtcNbr() != null
                        && baseIpDocPrvtDetailForm.getCtcNbr().length() > 0
                                                                           ? new BigInteger(
                                                                                             baseIpDocPrvtDetailForm.getCtcNbr() )
                                                                           : null;

    BigInteger curAcctNbr = baseIpDocPrvtDetailForm.getCurAcctNbr() != null
                            && baseIpDocPrvtDetailForm.getCurAcctNbr().length() > 0
                                                                                   ? new BigInteger(
                                                                                                     baseIpDocPrvtDetailForm.getCurAcctNbr() )
                                                                                   : null;
    BigInteger custNbr = baseIpDocPrvtDetailForm.getCustNbrSrc() != null
                         && baseIpDocPrvtDetailForm.getCustNbrSrc().length() > 0
                                                                                ? new BigInteger(
                                                                                                  baseIpDocPrvtDetailForm.getCustNbrSrc() )
                                                                                : null;
    //Caso venha da consulta de aprovação centralizada                                                                            
    if(codeArray!= null){
		custNbr = new BigInteger(codeArray[1]);                                     
    }

    BigInteger docTranferCode = baseIpDocPrvtDetailForm.getDocTransferCode() != null
                                && baseIpDocPrvtDetailForm.getDocTransferCode().length() > 0
                                                                                            ? new BigInteger(
                                                                                                              baseIpDocPrvtDetailForm.getDocTransferCode() )
                                                                                            : null;

    BigInteger ipDocCode = baseIpDocPrvtDetailForm.getIpDocCode() != null
                           && baseIpDocPrvtDetailForm.getIpDocCode().length() > 0
                                                                                 ? new BigInteger(
                                                                                                   baseIpDocPrvtDetailForm.getIpDocCode() )
                                                                                 : null;
                                                                                 
    //Caso venha da consulta de aprovação centralizada                                                                            
	if(codeArray!= null){
	  ipDocCode = new BigInteger(codeArray[0]);                                     
	}
                                                                                       
    String ipDocText = baseIpDocPrvtDetailForm.getIpDocText() != null
                       && baseIpDocPrvtDetailForm.getIpDocText().length() > 0
                                                                             ? baseIpDocPrvtDetailForm.getIpDocText()
                                                                             : null;
    String ownDestAcctInd = baseIpDocPrvtDetailForm.getOwnDestAcctInd() != null
                            && baseIpDocPrvtDetailForm.getOwnDestAcctInd().length() > 0
                                                                                       ? baseIpDocPrvtDetailForm.getOwnDestAcctInd()
                                                                                       : null;
    BigInteger txnTypeCode = baseIpDocPrvtDetailForm.getTxnTypeCode() != null
                             && baseIpDocPrvtDetailForm.getTxnTypeCode().length() > 0
                                                                                     ? new BigInteger(
                                                                                                       baseIpDocPrvtDetailForm.getTxnTypeCode() )
                                                                                     : null;
    String ipInvstCurAcctInd = baseIpDocPrvtDetailForm.getIpInvstCurAcctInd() != null
                               && baseIpDocPrvtDetailForm.getIpInvstCurAcctInd().length() > 0
                                                                                             ? baseIpDocPrvtDetailForm.getIpInvstCurAcctInd()
                                                                                             : null;

    String custFullNameText = baseIpDocPrvtDetailForm.getCustFullNameText() != null
                              && baseIpDocPrvtDetailForm.getCustFullNameText().length() > 0
                                                                                           ? baseIpDocPrvtDetailForm.getCustFullNameText()
                                                                                           : null;
                                                                                           
	BigInteger phoneDddCode = ( baseIpDocPrvtDetailForm.getPhoneDddCode() != null
											&& baseIpDocPrvtDetailForm.getPhoneDddCode().length() > 0
																					  ? new BigInteger(baseIpDocPrvtDetailForm.getPhoneDddCode() )
																					  : null );
	BigInteger phoneNbr = ( baseIpDocPrvtDetailForm.getPhoneNbr() != null
												&& baseIpDocPrvtDetailForm.getPhoneNbr().length() > 0
												? new BigInteger(baseIpDocPrvtDetailForm.getPhoneNbr() )
																						  : null );
																					  
	BigInteger phoneExtnNbr = ( baseIpDocPrvtDetailForm.getPhoneExtnNbr() != null
								&& baseIpDocPrvtDetailForm.getPhoneExtnNbr().length() > 0 
								? new BigInteger(baseIpDocPrvtDetailForm.getPhoneExtnNbr() )
																	  : null );
   
	String fullNameText = ( baseIpDocPrvtDetailForm.getFullNameText() != null
												 && baseIpDocPrvtDetailForm.getFullNameText().length() > 0 ? baseIpDocPrvtDetailForm.getFullNameText()
																							: null );																			
	
	
	String fullNameText_2 = ( baseIpDocPrvtDetailForm.getFullNameText_2() != null
											 && baseIpDocPrvtDetailForm.getFullNameText_2().length() > 0 ? baseIpDocPrvtDetailForm.getFullNameText_2()
																						: null );
	
	String fullNameText_3 = ( baseIpDocPrvtDetailForm.getFullNameText_3() != null
											 && baseIpDocPrvtDetailForm.getFullNameText_3().length() > 0 ? baseIpDocPrvtDetailForm.getFullNameText_3()
																						: null );  
																						
	baseIpDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().setCurAcctNbr(( baseIpDocPrvtDetailForm.getCustCurAcctNbrSrc() != null
														&& baseIpDocPrvtDetailForm.getCustCurAcctNbrSrc().length() > 0
														 ? new BigInteger(
																baseIpDocPrvtDetailForm.getCustCurAcctNbrSrc() )
															: null )
															);
											  				
	baseIpDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().setInvestCurAcctNbr(( baseIpDocPrvtDetailForm.getInvstCurAcctNbrSrc() != null	? baseIpDocPrvtDetailForm.getInvstCurAcctNbrSrc(): null ));                                                                                               

    baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setCustNbr(
                                                                               custNbr );
    baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setIpDocCode(
                                                                                 ipDocCode );
    baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setIpDocText(
                                                                                 ipDocText );
    baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setIpInvstCurAcctInd(
                                                                                         ipInvstCurAcctInd );
    baseIpDocPrvtDetailFncVO.setCustFullNameText( custFullNameText );

    baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setAgnBankCode(
                                                                                     agnBankCode );
    baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBrchCode(
                                                                                  brchCode );
    baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setCurAcctNbr(
                                                                                    curAcctNbr );
    baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setCustNbr(
                                                                                 custNbr );
    baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setDocTransferCode(
                                                                                         docTranferCode );
    baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setIpDocCode(
                                                                                   ipDocCode );
    baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setOwnDestAcctInd(
                                                                                        ownDestAcctInd );
    baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setTxnTypeCode(
                                                                                     txnTypeCode );
                                                                                     
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneNameText((baseIpDocPrvtDetailForm.getBeneNameText() != null
																						&& baseIpDocPrvtDetailForm.getBeneNameText().length() > 0
															   ? baseIpDocPrvtDetailForm.getBeneNameText()
															   : null));
    
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneCpfCnpjNbr((baseIpDocPrvtDetailForm.getBeneCpfCnpjNbr() != null
																							&& baseIpDocPrvtDetailForm.getBeneCpfCnpjNbr().length() > 0
																   ? baseIpDocPrvtDetailForm.getBeneCpfCnpjNbr()
																   : null));
    
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneMainDestAcctInd((baseIpDocPrvtDetailForm.getBeneMainDestAcctInd() != null
																								&& baseIpDocPrvtDetailForm.getBeneMainDestAcctInd().length() > 0
																	   ? baseIpDocPrvtDetailForm.getBeneMainDestAcctInd()
																	   : null));
																	                                                                                         
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneAcctTypeCode((baseIpDocPrvtDetailForm.getBeneAcctTypeCode() != null
																									&& baseIpDocPrvtDetailForm.getBeneAcctTypeCode().length() > 0
																		   ? baseIpDocPrvtDetailForm.getBeneAcctTypeCode()
																		   : null));
    																                                                                                         

    baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity().getData().setCtcNbr(
                                                                                  ctcNbr );
    baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity().getData().setCustNbr(
                                                                                   custNbr );
    baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity().getData().setIpDocCode(
                                                                                     ipDocCode );
                                                                                     
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneDddCode(phoneDddCode);
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneNbr(phoneNbr);
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneExtnNbr(phoneExtnNbr);
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText(fullNameText);
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText_2(fullNameText_2);
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText_3(fullNameText_3);
	                                                                                     

    //Manipulação dos arrays de string para os itens inseridos e excluídos do
    // grid
    if ( !baseIpDocPrvtDetailFncVO.isFromSearch()
         && !baseIpDocPrvtDetailFncVO.isInnerActionInd() )
    {
      if ( baseIpDocPrvtDetailForm.getSelectedCallBackInGrid() != null )
      {
        baseIpDocPrvtDetailFncVO.setSelectedCallBackInGrid( new ArrayList() );
        baseIpDocPrvtDetailFncVO.setDeletedCallBackInGrid( new ArrayList() );

        for ( int i = 0; i < baseIpDocPrvtDetailForm.getSelectedCallBackInGrid().length; i++ )
        {
          baseIpDocPrvtDetailFncVO.getSelectedCallBackInGrid().add(
                                                                    baseIpDocPrvtDetailForm.getSelectedCallBackInGrid()[ i ] );
          baseIpDocPrvtDetailFncVO.getDeletedCallBackInGrid().add(
                                                                   baseIpDocPrvtDetailForm.getDeletedCallBack()[ i ] );

        }

      }
      else
      {
        baseIpDocPrvtDetailFncVO.setSelectedCallBackInGrid( new ArrayList() );
        baseIpDocPrvtDetailFncVO.setDeletedCallBackInGrid( new ArrayList() );

      }

      if ( baseIpDocPrvtDetailForm.getSelectedDocTransferInGrid() != null )
      {
        baseIpDocPrvtDetailFncVO.setSelectedDocTransferInGrid( new ArrayList() );
        baseIpDocPrvtDetailFncVO.setDeletedDocTransferInGrid( new ArrayList() );
        for ( int j = 0; j < baseIpDocPrvtDetailForm.getSelectedDocTransferInGrid().length; j++ )
        {
          baseIpDocPrvtDetailFncVO.getSelectedDocTransferInGrid().add(
                                                                       baseIpDocPrvtDetailForm.getSelectedDocTransferInGrid()[ j ] );
          baseIpDocPrvtDetailFncVO.getDeletedDocTransferInGrid().add(
                                                                      baseIpDocPrvtDetailForm.getDeletedDocTransfer()[ j ] );

        }

      }
      else
      {
        baseIpDocPrvtDetailFncVO.setSelectedDocTransferInGrid( new ArrayList() );
        baseIpDocPrvtDetailFncVO.setDeletedDocTransferInGrid( new ArrayList() );
      }

    }

    baseIpDocPrvtDetailFncVO.setClickedSearch( "" );
  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO = ( BaseIpDocPrvtDetailFncVO ) fncVO_;
    BaseIpDocPrvtDetailForm baseIpDocPrvtDetailForm = ( BaseIpDocPrvtDetailForm ) form_;

	String CustcurAcctNbr = ( baseIpDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().getCurAcctNbr() != null
																			? baseIpDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().getCurAcctNbr().toString()
																			: null );
                                                                        
	String invstCurAcctNbrSrc = ( baseIpDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().getInvestCurAcctNbr() != null
												? baseIpDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().getInvestCurAcctNbr() : null );
    
    String custNbr = baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() != null
                                                                                                        ? baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr().toString()
                                                                                                        : "";
    String ipDocCode = baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode() != null
                                                                                                            ? baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode().toString()
                                                                                                            : "";
    String ipDocText = baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocText() != null
                                                                                                            ? baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocText().toString()
                                                                                                            : "";
    String ipInvstCurAcctInd = baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpInvstCurAcctInd();

    String agnBankCode = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode() != null
                                                                                                                  ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode().toString()
                                                                                                                  : "";
    String brchCode = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBrchCode() != null
                                                                                                            ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBrchCode().toString()
                                                                                                            : "";
    String curAcctNbr = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getCurAcctNbr() != null
                                                                                                                ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getCurAcctNbr().toString()
                                                                                                                : "";
    String docTranferCode = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getDocTransferCode() != null
                                                                                                                         ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getDocTransferCode().toString()
                                                                                                                         : "";
    String ownDestAcctInd = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getOwnDestAcctInd() != null
                                                                                                                        ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getOwnDestAcctInd().toString()
                                                                                                                        : "";
    String txnTypeCode = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getTxnTypeCode() != null
                                                                                                                  ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getTxnTypeCode().toString()
                                                                                                                  : "";

    String ctcNbr = baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity().getData().getCtcNbr() != null
                                                                                                          ? baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity().getData().getCtcNbr().toString()
                                                                                                          : "";

    String fullNameText = baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getFullNameText() != null
										? baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getFullNameText()
										: "";
										
	String fullNameText_2 = baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getFullNameText_2() != null
											? baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getFullNameText_2()
											: "";
												
	String fullNameText_3 = baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getFullNameText_3() != null
												? baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getFullNameText_3()
												: "";										                                      																					                                                                             
                                                                            
    String phoneDddCode = baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getPhoneDddCode() != null
								? baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getPhoneDddCode().toString()
								: "";   
								
	String phoneNbr = baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getPhoneNbr() != null
												? baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getPhoneNbr().toString()
												: "";
	
	String phoneExtnNbr = baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getPhoneExtnNbr() != null
												? baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().getPhoneExtnNbr().toString()
												: "";							                                                                          

    String custFullName = baseIpDocPrvtDetailFncVO.getCustFullNameText() != null
                          && !baseIpDocPrvtDetailFncVO.getCustFullNameText().equals(
                                                                                     "" )
                                                                                         ? baseIpDocPrvtDetailFncVO.getCustFullNameText()
                                                                                         : "";
                                                                                         
	String beneCpfCnpjNbr = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneCpfCnpjNbr() != null
							  && !baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneCpfCnpjNbr().equals(
																						 "" )
																							 ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneCpfCnpjNbr()
																							 : "";
	String beneNameText = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneNameText() != null
								  && !baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneNameText().equals(
																							 "" )
																								 ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneNameText()
																								 : "";                                                                                         
	String beneMainDestAcctInd = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneMainDestAcctInd() != null
									  && !baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneMainDestAcctInd().equals(
																								 "" )
																									 ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneMainDestAcctInd()
																									 : "";																								 
	String beneAcctTypeCode = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode() != null
										  && !baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode().equals(
																									 "" )
																										 ? baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode()
																										 : "";                                                                         

	baseIpDocPrvtDetailForm.setCustCurAcctNbrSrc(CustcurAcctNbr);
	baseIpDocPrvtDetailForm.setInvstCurAcctNbrSrc(invstCurAcctNbrSrc);
    baseIpDocPrvtDetailForm.setAgnBankCode( agnBankCode );
    baseIpDocPrvtDetailForm.setBrchCode( brchCode );
    baseIpDocPrvtDetailForm.setCtcNbr( ctcNbr );    
    baseIpDocPrvtDetailForm.setCurAcctNbr( curAcctNbr );
    baseIpDocPrvtDetailForm.setCustFullNameText( custFullName );
    baseIpDocPrvtDetailForm.setCustNbrSrc( custNbr );
    baseIpDocPrvtDetailForm.setDocTransferCode( docTranferCode );
    baseIpDocPrvtDetailForm.setIpDocCode( ipDocCode );
    baseIpDocPrvtDetailForm.setIpDocText( ipDocText );
    baseIpDocPrvtDetailForm.setOwnDestAcctInd( ownDestAcctInd );
    baseIpDocPrvtDetailForm.setTxnTypeCode( txnTypeCode );
    baseIpDocPrvtDetailForm.setIpInvstCurAcctInd( ipInvstCurAcctInd );
    baseIpDocPrvtDetailForm.setIpInvstCurAcctIndDomain( baseIpDocPrvtDetailFncVO.getIpIpInvstCurAcctIndDomain() );
    baseIpDocPrvtDetailForm.setOwnDestAcctIndDomain( baseIpDocPrvtDetailFncVO.getOwnDestAcctIndDomain() );
	baseIpDocPrvtDetailForm.setBeneMainDestAcctInd(beneMainDestAcctInd);
	baseIpDocPrvtDetailForm.setBeneMainDestAcctIndDomain(baseIpDocPrvtDetailFncVO.getBeneMainDestAcctIndDomain());
    baseIpDocPrvtDetailForm.setTxnTypeCodeDomain( baseIpDocPrvtDetailFncVO.getTxnTypeCodeDomain() );
    baseIpDocPrvtDetailForm.setClickedSearch( baseIpDocPrvtDetailFncVO.getClickedSearch() );
	baseIpDocPrvtDetailForm.setBeneCpfCnpjNbr(beneCpfCnpjNbr);
	baseIpDocPrvtDetailForm.setBeneNameText(beneNameText);
	baseIpDocPrvtDetailForm.setBeneAcctTypeCode(beneAcctTypeCode);
	baseIpDocPrvtDetailForm.setBeneAcctTypeCodeIndDomain(baseIpDocPrvtDetailFncVO.getBeneAcctTypeCodeIndDomain());
	baseIpDocPrvtDetailForm.setFullNameText( fullNameText );
	baseIpDocPrvtDetailForm.setFullNameText_2(fullNameText_2);
	baseIpDocPrvtDetailForm.setFullNameText_3(fullNameText_3);
	baseIpDocPrvtDetailForm.setPhoneNbr(phoneNbr);
	baseIpDocPrvtDetailForm.setPhoneDddCode(phoneDddCode);
	baseIpDocPrvtDetailForm.setPhoneExtnNbr(phoneExtnNbr);
    //Manipulação dos arrays de string para os itens inseridos e excluídos do
    // grid

    if ( baseIpDocPrvtDetailFncVO.getSelectedCallBackInGrid() != null
         && !baseIpDocPrvtDetailFncVO.getSelectedCallBackInGrid().isEmpty() )
    {
      String[] callBackSelected = new String[ baseIpDocPrvtDetailFncVO.getSelectedCallBackInGrid().size() ];
      String[] deletedCallBack = new String[ baseIpDocPrvtDetailFncVO.getDeletedCallBackInGrid().size() ];
      for ( int i = 0; i < baseIpDocPrvtDetailFncVO.getSelectedCallBackInGrid().size(); i++ )
      {
        callBackSelected[ i ] = ( String ) baseIpDocPrvtDetailFncVO.getSelectedCallBackInGrid().get(
                                                                                                     i );
        deletedCallBack[ i ] = ( String ) baseIpDocPrvtDetailFncVO.getDeletedCallBackInGrid().get(
                                                                                                   i );

      }

      baseIpDocPrvtDetailForm.setSelectedCallBackInGrid( callBackSelected );
      baseIpDocPrvtDetailForm.setDeletedCallBack( deletedCallBack );
    }
    else
    {
      String[] callBackSelected = new String[ baseIpDocPrvtDetailFncVO.getInsertedIpDocCallback().size() ];
      String[] deletedCallBack = new String[ baseIpDocPrvtDetailFncVO.getInsertedIpDocCallback().size() ];
      for ( int i = 0; i < baseIpDocPrvtDetailFncVO.getInsertedIpDocCallback().size(); i++ )
      {
        callBackSelected[ i ] = "N";
        deletedCallBack[ i ] = "N";
      }

      baseIpDocPrvtDetailForm.setSelectedCallBackInGrid( callBackSelected );
      baseIpDocPrvtDetailForm.setDeletedCallBack( deletedCallBack );

    }

    if ( baseIpDocPrvtDetailFncVO.getSelectedDocTransferInGrid() != null
         && !baseIpDocPrvtDetailFncVO.getSelectedDocTransferInGrid().isEmpty() )
    {
      String[] docTransferSelected = new String[ baseIpDocPrvtDetailFncVO.getSelectedDocTransferInGrid().size() ];
      String[] deletedDocTransfer = new String[ baseIpDocPrvtDetailFncVO.getDeletedDocTransferInGrid().size() ];
      for ( int i = 0; i < baseIpDocPrvtDetailFncVO.getSelectedDocTransferInGrid().size(); i++ )
      {
        docTransferSelected[ i ] = ( String ) baseIpDocPrvtDetailFncVO.getSelectedDocTransferInGrid().get(
                                                                                                           i );
        deletedDocTransfer[ i ] = ( String ) baseIpDocPrvtDetailFncVO.getDeletedDocTransferInGrid().get(
                                                                                                         i );

      }

      baseIpDocPrvtDetailForm.setSelectedDocTransferInGrid( docTransferSelected );
      baseIpDocPrvtDetailForm.setDeletedDocTransfer( deletedDocTransfer );
    
		 
   } else {
      String[] docTransferSelected = new String[ baseIpDocPrvtDetailFncVO.getInsertedDocTransfer().size() ];
      String[] deletedDocTransfer = new String[ baseIpDocPrvtDetailFncVO.getInsertedDocTransfer().size() ];

      for ( int i = 0; i < baseIpDocPrvtDetailFncVO.getInsertedDocTransfer().size(); i++ )
      {
        docTransferSelected[ i ] = "N";
        deletedDocTransfer[ i ] = "N";
      }

      baseIpDocPrvtDetailForm.setSelectedDocTransferInGrid( docTransferSelected );
      baseIpDocPrvtDetailForm.setDeletedDocTransfer( deletedDocTransfer );

    }

  }

  public String buildBranchName( String agnCode_, String agnBankCode_ )
  {

    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TbgBranchDAO tbgBranchDAO = factory.getTbgBranchDAO();

    TbgBranchEntity tbgBranchEntity = new TbgBranchEntity();

    tbgBranchEntity.getData().setAgnCode( agnCode_ );
    tbgBranchEntity.getData().setAgnBankCode( agnBankCode_ );

    String branchName = "";

    if ( tbgBranchDAO.exists( tbgBranchEntity ) )
    {

      tbgBranchEntity = ( TbgBranchEntity ) tbgBranchDAO.find( tbgBranchEntity );

      branchName = ( tbgBranchEntity.getData().getAgnText() != null
                                                                   ? tbgBranchEntity.getData().getAgnText()
                                                                   : "" );
    }

    return branchName;
  }

  public String buildBankName( String agnBankCode_ )
  {

    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TbgBankDAO tbgBankDAO = factory.getTbgBankDAO();

    TbgBankEntity tbgBankEntity = new TbgBankEntity();

    tbgBankEntity.getData().setBankCode( agnBankCode_ );

    tbgBankEntity = ( TbgBankEntity ) tbgBankDAO.find( tbgBankEntity );

    return tbgBankEntity.getData().getBankNameText() == null
                                                            ? ""
                                                            : tbgBankEntity.getData().getBankNameText();
  }

  public String[] buildCtcName( BigInteger ctcNbr_, BigInteger custNbr_ )
  {
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TplContactCustDAO tplContactCustDAO = factory.getTplContactCustDAO();

    TplContactCustEntity tplContactCustEntity = new TplContactCustEntity();

    tplContactCustEntity.getData().setCtcNbr( ctcNbr_ );
    tplContactCustEntity.getData().setCustNbr( custNbr_ );

    tplContactCustEntity = ( TplContactCustEntity ) tplContactCustDAO.find( tplContactCustEntity );

    String[] arrayBuild = new String[6];
	arrayBuild[0] = tplContactCustEntity.getData().getFullNameText() == null
                                                                   ? ""
                                                                   : tplContactCustEntity.getData().getFullNameText();
	
	arrayBuild[1] = tplContactCustEntity.getData().getPhoneNbr() == null
																	   ? ""
																	   : String.valueOf(tplContactCustEntity.getData().getPhoneNbr());
																	   
	arrayBuild[2] = tplContactCustEntity.getData().getPhoneDddCode() == null
																		   ? ""
																		   : String.valueOf(tplContactCustEntity.getData().getPhoneDddCode());
																		   
	arrayBuild[3] = tplContactCustEntity.getData().getPhoneExtnNbr() == null
																			   ? ""
																			   : String.valueOf(tplContactCustEntity.getData().getPhoneExtnNbr());
																			   
	arrayBuild[4] = tplContactCustEntity.getData().getFullNameText_2() == null
																	   ? ""
																	   : tplContactCustEntity.getData().getFullNameText_2();
	
	arrayBuild[5] = tplContactCustEntity.getData().getFullNameText_3() == null
																		   ? ""
																		   : tplContactCustEntity.getData().getFullNameText_3();																   																			   																		   																	   
																	                                                                      
   return arrayBuild;                                                                
  }

  protected void loadCustFullNameText(
                                      BaseIpDocPrvtDetailFncVO ipDocPrvtDetailFncVO_ )
  {

    if ( ipDocPrvtDetailFncVO_.getBaseTplIpDocPrvtEntity().getData().getCustNbr() != null)
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               ipDocPrvtDetailFncVO_.getBaseTplIpDocPrvtEntity().getData().getCustNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Verifica se existe o cliente, se existir carrega o nome do cliente.
      if ( tplCustomerPrvtDAO.exists( customerPrvtEntity ) )
      {
        //Realiza a consulta no DAO
        customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

        //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
        ipDocPrvtDetailFncVO_.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );

      }
    }
  }

  protected void loadContactFullNameText(
                                         BaseIpDocPrvtDetailFncVO ipDocPrvtFncVO_ )
  {

    if ( ipDocPrvtFncVO_.getBaseTplIpDocPrvtEntity().getData().getCustNbr() != null
         && ipDocPrvtFncVO_.getBaseTplIpDocPrvtEntity().getData().getCustNbr().longValue() > 0
         && ipDocPrvtFncVO_.getBaseTplIpDocCallbackEntity().getData().getCtcNbr() != null
         && ipDocPrvtFncVO_.getBaseTplIpDocCallbackEntity().getData().getCtcNbr().intValue() > 0 )
    {
      //Cria uma instancia do DAO de Cliente
      TplCustomerPrvtDAO tplCustomerPrvtDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtDAO();
      //Cria uma instancia da Entity de Cliente
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               ipDocPrvtFncVO_.getBaseTplIpDocPrvtEntity().getData().getCustNbr() );

      if ( tplCustomerPrvtDAO.exists( customerPrvtEntity ) )
      {

        TplContactCustEntity contactCustEntity = new TplContactCustEntity();
        contactCustEntity.getData().setCustNbr(
                                                ipDocPrvtFncVO_.getBaseTplIpDocPrvtEntity().getData().getCustNbr() );

        contactCustEntity.getData().setCtcNbr(
                                               ipDocPrvtFncVO_.getBaseTplIpDocCallbackEntity().getData().getCtcNbr() );

        //Obtém a instância da Factory
        ODSDAOFactory factory = ODSDAOFactory.getInstance();
        //Obtém a instância do DAO necessário
        TplContactCustDAO tplContactCustDAO = factory.getTplContactCustDAO();

        if ( tplContactCustDAO.existsActive( contactCustEntity ) )
        {
          //Realiza a consulta no DAO
          contactCustEntity = ( TplContactCustEntity ) tplContactCustDAO.find( contactCustEntity );

          //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
          ipDocPrvtFncVO_.setFullNameText( contactCustEntity.getData().getFullNameText() );

        }
        else
        {
          ipDocPrvtFncVO_.setFullNameText( "" );
        }
      }
    }
    else
    {
      ipDocPrvtFncVO_.setFullNameText( "" );
    }

  }

  protected boolean existsAgnBankCode( BaseIpDocPrvtDetailFncVO fncVO )
  {

    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TbgBankDAO tbgBankDAO = factory.getTbgBankDAO();

    TbgBankEntity tbgBankEntity = new TbgBankEntity();
    tbgBankEntity.getData().setBankCode(
                                         fncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode().toString() );

    return tbgBankDAO.exists( tbgBankEntity );
  }

  protected boolean existsBrchCode( BaseIpDocPrvtDetailFncVO fncVO )
  {
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TbgBranchDAO tbgBranchDAO = factory.getTbgBranchDAO();

    TbgBranchEntity tbgBranchEntity = new TbgBranchEntity();
    tbgBranchEntity.getData().setAgnBankCode(
                                              fncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode().toString() );
    tbgBranchEntity.getData().setAgnCode(
                                          fncVO.getBaseTplDocTransferEntity().getData().getBrchCode().toString() );

    return tbgBranchDAO.exists( tbgBranchEntity );
  }

  protected boolean existsCtcNbr( BaseIpDocPrvtDetailFncVO fncVO )
  {
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    TplContactCustDAO tplContactCustDAO = factory.getTplContactCustDAO();

    TplContactCustEntity tplContactCustEntity = new TplContactCustEntity();
    tplContactCustEntity.getData().setCtcNbr(
                                              fncVO.getBaseTplIpDocCallbackEntity().getData().getCtcNbr() );
    tplContactCustEntity.getData().setCustNbr(
                                               fncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() );

    return tplContactCustDAO.existsActive( tplContactCustEntity );
  }

  /**
   * Recupera um elementos de Categoria de Risco, dado os critérios
   */
  public void load( BaseFncVO fncVO_ )
  {
    //Casting do fncVO específico
    BaseIpDocPrvtDetailFncVO ipDocPrvtDetailFncVO = ( BaseIpDocPrvtDetailFncVO ) fncVO_;

    //Chama os DAOs
    BaseTplIpDocPrvtDAO ipDocPrvtDAO = this.getDAO();

    //Cria instancia dos entities especifícos
    BaseTplIpDocPrvtEntity tplIpDocPrvtEntity = ( BaseTplIpDocPrvtEntity ) ipDocPrvtDAO.find( ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity() );
    ipDocPrvtDetailFncVO.setBaseTplIpDocPrvtEntity( tplIpDocPrvtEntity );

    loadDomains( ipDocPrvtDetailFncVO );

  }

  public void loadDomains( BaseIpDocPrvtDetailFncVO ipDocPrvtDetailFncVO_ )
  {
    loadIpInvstCurAcctIndDomain( ipDocPrvtDetailFncVO_ );
    loadBeneMainDestAcctIndDomain(ipDocPrvtDetailFncVO_);
	loadAcctTypeCode(ipDocPrvtDetailFncVO_);
    loadOwnDestAcctIndDomain( ipDocPrvtDetailFncVO_ );
    loadDocTransfer( ipDocPrvtDetailFncVO_ );
  }

  private void loadIpInvstCurAcctIndDomain(
                                           BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO )
  {

    baseIpDocPrvtDetailFncVO.setIpIpInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }
  
  private void loadBeneMainDestAcctIndDomain(BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO){
	baseIpDocPrvtDetailFncVO.setBeneMainDestAcctIndDomain(ODSConstraintDecoder.decodeIndicador());  	
  }

  private void loadOwnDestAcctIndDomain(
                                        BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO )
  {

    baseIpDocPrvtDetailFncVO.setOwnDestAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadDocTransfer(
                               BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO )
  {

    baseIpDocPrvtDetailFncVO.setTxnTypeCodeDomain( ODSConstraintDecoder.decodeTransaction() );
  }
  
  private void loadAcctTypeCode(BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO){
	baseIpDocPrvtDetailFncVO.setBeneAcctTypeCodeIndDomain(ODSConstraintDecoder.decodeAcctTypeCode());  	
  }

  protected abstract BaseTplIpDocPrvtDAO getDAO();

}