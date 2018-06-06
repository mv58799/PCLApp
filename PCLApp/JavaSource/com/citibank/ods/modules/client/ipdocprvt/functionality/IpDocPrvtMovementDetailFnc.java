package com.citibank.ods.modules.client.ipdocprvt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrHistEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrMovEntity;
import com.citibank.ods.entity.pl.TplDocTransferEntity;
import com.citibank.ods.entity.pl.TplDocTransferHistEntity;
import com.citibank.ods.entity.pl.TplDocTransferMovEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackHistEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtHistEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCurAcctPrmntInstrMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtMovEntityVO;
import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocPrvtDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtMovementDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtDetailFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.To3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrHistDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferDAO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferHistDAO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferMovDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackHistDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackMovDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO;
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

public class IpDocPrvtMovementDetailFnc extends BaseIpDocPrvtDetailFnc
	implements ODSMovementDetailFnc
{

  /**
   * Retorna o DAO
   */
  protected BaseTplIpDocPrvtDAO getDAO()
  {

	return ODSDAOFactory.getInstance().getTplIpDocPrvtMovDAO();
  }

  /**
   * Carregamento iniciais - Retorna instancia do FncVO
   */
  public BaseFncVO createFncVO()
  {

	return new IpDocPrvtMovementDetailFncVO();
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
	super.updateFncVOFromForm( form_, fncVO_ );

	IpDocPrvtMovementDetailFncVO ipDocPrvtDetailFncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;
	IpDocPrvtMovementDetailForm ipDocPrvtDetailForm = ( IpDocPrvtMovementDetailForm ) form_;

	ipDocPrvtDetailFncVO.setSelectedCtcNbr( ipDocPrvtDetailForm.getSelectedCtcNbr() != null
											&& ipDocPrvtDetailForm.getSelectedCtcNbr().length() > 0
																								   ? new BigInteger(
																													 ipDocPrvtDetailForm.getSelectedCtcNbr() )
																								   : null );
	ipDocPrvtDetailFncVO.setSelectedDocTransferCode( ipDocPrvtDetailForm.getSelectedDocTransferCode() != null
													 && ipDocPrvtDetailForm.getSelectedDocTransferCode().length() > 0
																													 ? new BigInteger(
																																	   ipDocPrvtDetailForm.getSelectedDocTransferCode() )
																													 : null );
	ipDocPrvtDetailFncVO.setSelectedFullNameText( ipDocPrvtDetailForm.getSelectedFullNameText() != null
												  && ipDocPrvtDetailForm.getSelectedFullNameText().length() > 0
																											   ? new BigInteger(
																																 ipDocPrvtDetailForm.getSelectedFullNameText() )
																											   : null );

  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
	super.updateFormFromFncVO( form_, fncVO_ );

	BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO = ( BaseIpDocPrvtDetailFncVO ) fncVO_;
	BaseIpDocPrvtDetailForm baseIpDocPrvtDetailForm = ( BaseIpDocPrvtDetailForm ) form_;

	//  Atualização do grid de Dados de Transferência
	ArrayList domainsDocTransferCodeList = new ArrayList();
	ArrayList domainsAgnBankCodeList = new ArrayList();
	ArrayList domainsBrchCodeList = new ArrayList();
	ArrayList domainsCurAcctCodeList = new ArrayList();
	ArrayList domainsOwnDestAcctIndList = new ArrayList();
	ArrayList domainsTxnTypeCodeList = new ArrayList();
	ArrayList opernCodeList = new ArrayList();
	
	ArrayList domainsBeneCpfCnpjNbrList = new ArrayList();  
	ArrayList domainsBeneNameTextList = new ArrayList();  
	ArrayList domainsBeneMainDestAcctIndList = new ArrayList();  
	ArrayList domainsBeneAcctTypeCodeList = new ArrayList();

	//    BaseTplIpDocPrvtEntity baseTplIpDocPrvtEntity =
	// baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity();
	BaseTplDocTransferEntity baseTplDocTransferEntity = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity();

	ArrayList ipDocTransferEntities = baseIpDocPrvtDetailFncVO.getInsertedDocTransfer();
	Iterator ipDocTransferEntitiesIt = ipDocTransferEntities.iterator();

	while ( ipDocTransferEntitiesIt.hasNext() )
	{

	  baseTplDocTransferEntity = ( TplDocTransferMovEntity ) ipDocTransferEntitiesIt.next();

	  if ( baseTplDocTransferEntity.getData().getAgnBankCode() != null )
	  {
		domainsAgnBankCodeList.add( baseTplDocTransferEntity.getData().getAgnBankCode() );
	  }
	  else
	  {
		domainsAgnBankCodeList.add( "" );
	  }

	  if ( baseTplDocTransferEntity.getData().getBrchCode() != null )
	  {
		domainsBrchCodeList.add( baseTplDocTransferEntity.getData().getBrchCode() );
	  }
	  else
	  {
		domainsBrchCodeList.add( "" );
	  }

	  if ( baseTplDocTransferEntity.getData().getCurAcctNbr() != null )
	  {
		domainsCurAcctCodeList.add( baseTplDocTransferEntity.getData().getCurAcctNbr() );
	  }
	  else
	  {
		domainsCurAcctCodeList.add( "" );
	  }

	  if ( baseTplDocTransferEntity.getData().getDocTransferCode() != null )
	  {
		domainsDocTransferCodeList.add( baseTplDocTransferEntity.getData().getDocTransferCode() );
	  }
	  else
	  {
		domainsDocTransferCodeList.add( "" );
	  }

	  if ( baseTplDocTransferEntity.getData().getOwnDestAcctInd() != null )
	  {
		domainsOwnDestAcctIndList.add( baseTplDocTransferEntity.getData().getOwnDestAcctInd() );
	  }
	  else
	  {
		domainsOwnDestAcctIndList.add( "" );
	  }

	  if ( baseTplDocTransferEntity.getData().getTxnTypeCode() != null )
	  {
		domainsTxnTypeCodeList.add( baseTplDocTransferEntity.getData().getTxnTypeCode() );
	  }
	  else
	  {
		domainsTxnTypeCodeList.add( "" );
	  }
	  if ( ( ( TplDocTransferMovEntityVO ) baseTplDocTransferEntity.getData() ).getOpernCode() != null )
	  {
		opernCodeList.add( ( ( TplDocTransferMovEntityVO ) baseTplDocTransferEntity.getData() ).getOpernCode() );
	  }
	  else
	  {
		opernCodeList.add( "" );
	  }
	  if ( baseTplDocTransferEntity.getData().getBeneCpfCnpjNbr() != null )
	  {
	    domainsBeneCpfCnpjNbrList.add( baseTplDocTransferEntity.getData().getBeneCpfCnpjNbr() );
	  }
	  else
	  {
	    domainsBeneCpfCnpjNbrList.add( "" );
	  }
	  
	  if ( baseTplDocTransferEntity.getData().getBeneNameText() != null )
	  {
	    domainsBeneNameTextList.add( baseTplDocTransferEntity.getData().getBeneNameText() );
	  }
	  else
	  {
	    domainsBeneNameTextList.add( "" );
	  }
	  
	  if ( baseTplDocTransferEntity.getData().getBeneMainDestAcctInd() != null )
	  {
	    domainsBeneMainDestAcctIndList.add( baseTplDocTransferEntity.getData().getBeneMainDestAcctInd() );
	  }
	  else
	  {
	    domainsBeneMainDestAcctIndList.add( "" );
	  }
	  
	  if ( baseTplDocTransferEntity.getData().getBeneAcctTypeCode() != null )
	  {
	    domainsBeneAcctTypeCodeList.add( baseTplDocTransferEntity.getData().getBeneAcctTypeCode() );
	  }
	  else
	  {
	    domainsBeneAcctTypeCodeList.add( "" );
	  }	  

	}

	String[] domainsDocTransferCodeArray = new String[ domainsDocTransferCodeList.size() ];
	String[] domainsAgnBankCodeArray = new String[ domainsAgnBankCodeList.size() ];
	String[] domainsAgnBankNameArray = new String[ domainsAgnBankCodeList.size() ];
	String[] domainsBrchCodeArray = new String[ domainsBrchCodeList.size() ];
	String[] domainsBrchNameArray = new String[ domainsBrchCodeList.size() ];
	String[] domainsCurAcctCodeArray = new String[ domainsCurAcctCodeList.size() ];
	String[] domainsOwnDestAcctIndArray = new String[ domainsOwnDestAcctIndList.size() ];
	String[] domainsTxnTypeCodeArray = new String[ domainsTxnTypeCodeList.size() ];
	String[] opernCodeDocTransferArray = new String[ opernCodeList.size() ];
	
	String[] domainsBeneCpfCnpjNbrArray = new String[ domainsBeneCpfCnpjNbrList.size() ];  
	String[] domainsBeneNameTextArray = new String[ domainsBeneNameTextList.size() ];  
	String[] domainsBeneMainDestAcctIndArray = new String[ domainsBeneMainDestAcctIndList.size() ];  
	String[] domainsBeneAcctTypeCodeArray = new String[ domainsBeneAcctTypeCodeList.size() ];

	//Fazendo a verredura de campos pelo atributo chave, atribuindo os
	// valores
	// aos vetores
	for ( int i = 0; i < domainsDocTransferCodeList.size(); i++ )
	{
	  domainsDocTransferCodeArray[ i ] = domainsDocTransferCodeList.get( i ).toString();
	  domainsCurAcctCodeArray[ i ] = domainsCurAcctCodeList.get( i ).toString();
	  domainsOwnDestAcctIndArray[ i ] = domainsOwnDestAcctIndList.get( i ).toString();
	  domainsTxnTypeCodeArray[ i ] = domainsTxnTypeCodeList.get( i ).toString();
	  domainsAgnBankNameArray[ i ] = buildBankName( domainsAgnBankCodeList.get(
																				i ).toString() );
	  domainsAgnBankCodeArray[ i ] = domainsAgnBankCodeList.get( i ).toString();
	  domainsBrchNameArray[ i ] = buildBranchName(
												   domainsBrchCodeList.get( i ).toString(),
												   domainsAgnBankCodeList.get(
																			   i ).toString() );
	  domainsBrchCodeArray[ i ] = ( domainsBrchCodeList.get( i ).toString() );
	  opernCodeDocTransferArray[ i ] = ODSConstraintDecoder.decodeOpern( opernCodeList.get(
																							i ).toString() );
																							
	  domainsBeneCpfCnpjNbrArray[i] = domainsBeneCpfCnpjNbrList.get( i ).toString();
	  domainsBeneNameTextArray[i] = domainsBeneNameTextList.get( i ).toString();
	  domainsBeneMainDestAcctIndArray[i] = domainsBeneMainDestAcctIndList.get( i ).toString();
	  domainsBeneAcctTypeCodeArray[i] = domainsBeneAcctTypeCodeList .get( i ).toString();																							
	}

	baseIpDocPrvtDetailForm.setDomainsDocTransferCode( domainsDocTransferCodeArray );
	baseIpDocPrvtDetailForm.setDomainsAgnBankCode( domainsAgnBankCodeArray );
	baseIpDocPrvtDetailForm.setDomainsAgnBankName( domainsAgnBankNameArray );
	baseIpDocPrvtDetailForm.setDomainsBrchCode( domainsBrchCodeArray );
	baseIpDocPrvtDetailForm.setDomainsBrchName( domainsBrchNameArray );
	baseIpDocPrvtDetailForm.setDomainsCurAcctCode( domainsCurAcctCodeArray );
	baseIpDocPrvtDetailForm.setDomainsOwnDestAcctInd( domainsOwnDestAcctIndArray );
	baseIpDocPrvtDetailForm.setDomainsTxnTypeCode( domainsTxnTypeCodeArray );
	baseIpDocPrvtDetailForm.setDomainsBeneCpfCnpjNbr(domainsBeneCpfCnpjNbrArray);
	baseIpDocPrvtDetailForm.setDomainsBeneNameText(domainsBeneNameTextArray);
	baseIpDocPrvtDetailForm.setDomainsBeneMainDestAcctInd(domainsBeneMainDestAcctIndArray);
	baseIpDocPrvtDetailForm.setDomainsBeneAcctTypeCode(domainsBeneAcctTypeCodeArray);
	( ( IpDocPrvtMovementDetailForm ) baseIpDocPrvtDetailForm ).setOpernCodeDocTransferArray( opernCodeDocTransferArray );
	

	baseIpDocPrvtDetailForm.setDocTransferCode( "" );
	baseIpDocPrvtDetailForm.setAgnBankCode( "" );
	baseIpDocPrvtDetailForm.setBrchCode( "" );
	baseIpDocPrvtDetailForm.setOwnDestAcctInd( "" );
	baseIpDocPrvtDetailForm.setTxnTypeCode( "" );

	//  Atualização do grid de Callback
	ArrayList domainsFullNameTextList = new ArrayList();
	ArrayList domainsCtcNbrList = new ArrayList();
	ArrayList opernCodeCallBackList = new ArrayList();

	BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity = baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity();

	//Varre a lista de items e atribui a um array de string no form
	ArrayList ipDocCallbackEntities = baseIpDocPrvtDetailFncVO.getInsertedIpDocCallback();
	Iterator ipDocCallbackEntitiesIt = ipDocCallbackEntities.iterator();

	while ( ipDocCallbackEntitiesIt.hasNext() )
	{
	  baseTplIpDocCallbackEntity = ( TplIpDocCallbackMovEntity ) ipDocCallbackEntitiesIt.next();

	  if ( baseTplIpDocCallbackEntity.getData().getCtcNbr() != null )
	  {
		domainsCtcNbrList.add( baseTplIpDocCallbackEntity.getData().getCtcNbr() );
	  }
	  else
	  {
		domainsCtcNbrList.add( "" );
	  }

	  if ( baseTplIpDocCallbackEntity.getFullNameText() != null )
	  {
		domainsFullNameTextList.add( baseTplIpDocCallbackEntity.getFullNameText() );
	  }
	  else
	  {
		domainsFullNameTextList.add( "" );
	  }
	  if ( ( ( TplIpDocCallbackMovEntityVO ) baseTplIpDocCallbackEntity.getData() ).getOpernCode() != null )
	  {
		opernCodeCallBackList.add( ( ( TplIpDocCallbackMovEntityVO ) baseTplIpDocCallbackEntity.getData() ).getOpernCode() );
	  }
	  else
	  {
		opernCodeCallBackList.add( "" );
	  }
	}

	String[] domainsCtcNbrArray = new String[ domainsCtcNbrList.size() ];
	String[] domainsFullNameTextArray = new String[ domainsFullNameTextList.size() ];
	String[] domainsFullNameTextArray_2 = new String[ domainsFullNameTextList.size() ];
	String[] domainsFullNameTextArray_3 = new String[ domainsFullNameTextList.size() ];	
	String[] opernCodeCallBackArray = new String[ opernCodeCallBackList.size() ];
	String[] domainsPhoneArray = new String[ domainsCtcNbrList.size() ];
	String[] domainsDddPhoneArray = new String[ domainsCtcNbrList.size() ];
	String[] domainsRamalPhoneArray = new String[ domainsCtcNbrList.size() ];

	//Fazendo a verredura de campos pelo atributo chave, atribuindo os
	// valores
	// aos vetores
	for ( int i = 0; i < domainsCtcNbrList.size(); i++ )
	{
	  domainsCtcNbrArray[ i ] = domainsCtcNbrList.get( i ).toString();
	  
	  String domainsArray[] = buildCtcName(new BigInteger(
												   domainsCtcNbrList.get(i ).toString() ),
												   baseTplIpDocCallbackEntity.getData().getCustNbr() ); 
	  
	  domainsFullNameTextArray[ i ] = domainsArray[0]; 
	  domainsPhoneArray[i] = domainsArray[1];	  
	  domainsDddPhoneArray[i] = domainsArray[2];
	  domainsRamalPhoneArray[i] = domainsArray[3];
	  domainsFullNameTextArray_2[ i ] = domainsArray[4];
	  domainsFullNameTextArray_3[ i ] = domainsArray[5];
	  opernCodeCallBackArray[ i ] = ODSConstraintDecoder.decodeOpern( opernCodeCallBackList.get(
																								 i ).toString() );
	}

	baseIpDocPrvtDetailForm.setDomainsCtcNbr( domainsCtcNbrArray );
	baseIpDocPrvtDetailForm.setDomainsFullNameText( domainsFullNameTextArray );
	baseIpDocPrvtDetailForm.setDomainsFullNameText_2(domainsFullNameTextArray_2);
	baseIpDocPrvtDetailForm.setDomainsFullNameText_3(domainsFullNameTextArray_3);
	baseIpDocPrvtDetailForm.setDomainsPhone(domainsPhoneArray);
	baseIpDocPrvtDetailForm.setDomainsDDDPhone(domainsDddPhoneArray);
	baseIpDocPrvtDetailForm.setDomainsRamalPhone(domainsRamalPhoneArray);

	( ( IpDocPrvtMovementDetailForm ) baseIpDocPrvtDetailForm ).setOpernCodeCallBackArray( opernCodeCallBackArray );

	//Casting do Form específico
	IpDocPrvtMovementDetailForm form = ( IpDocPrvtMovementDetailForm ) form_;

	String lastUserId = ( baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdUserId() != null
						  && baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdUserId().length() > 0
																														   ? baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdUserId()
																														   : null );
	String lastUpdDate = ( baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdDate() != null
																												  ? baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdDate().toString()
																												  : null );

	form.setLastUpdDate( lastUpdDate );
	form.setLastUpdUserId( lastUserId );

	if ( baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity() != null
		 && baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getDocTransferCode() != null )
	{
	  form.setDocTransferCode( baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().getDocTransferCode().toString() );
	}

  }

  public void deleteDocTransfer( BaseFncVO baseFncVO_ )
  {
  }

  public void insertDocTransfer( BaseFncVO baseFncVO_ )
  {
	baseFncVO_.clearErrors();
	baseFncVO_.clearMessages();

	this.validateInsertDocTransfer( baseFncVO_ );

	IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) baseFncVO_;

	if ( !baseFncVO_.hasErrors() )
	{

	  TplDocTransferMovEntity tplDocTransferEntity = new TplDocTransferMovEntity(
																				  fncVO.getBaseTplDocTransferEntity() );

	  tplDocTransferEntity.getData().setLastUpdDate( new Date() );
	  tplDocTransferEntity.getData().setLastUpdUserId(
													   fncVO.getLoggedUser().getUserID() );
	  ( ( TplDocTransferMovEntityVO ) tplDocTransferEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
	  TplDocTransferDAO tplDocTransferDAO = ODSDAOFactory.getInstance().getTplDocTransferDAO();

	  if ( tplDocTransferEntity.getData().getDocTransferCode() == null )
	  {
		tplDocTransferEntity.getData().setDocTransferCode(
														   new BigInteger(
																		   tplDocTransferDAO.getNextDocTransferCode().toString() ) );

	  }

	  fncVO.getInsertedDocTransfer().add( tplDocTransferEntity );

	  tplDocTransferEntity = new TplDocTransferMovEntity();

	  fncVO.setBaseTplDocTransferEntity( tplDocTransferEntity );
	  fncVO.getSelectedDocTransferInGrid().add( "S" );
	  fncVO.getDeletedDocTransferInGrid().add( "N" );
	}

	fncVO.setInnerActionInd( true );
  }

  public void deleteCallback( BaseFncVO baseFncVO_ )
  {
  }

  public void insertCallback( BaseFncVO baseFncVO_ )
  {
	baseFncVO_.clearErrors();
	baseFncVO_.clearMessages();

	this.validateInsertCallback( baseFncVO_ );

	IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) baseFncVO_;

	if ( !baseFncVO_.hasErrors() )
	{
	  TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
	  TplIpDocCallbackDAO tplIpDocCallbackDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackDAO();		
	  TplContactCustEntity contactCustEntity = new TplContactCustEntity();

	  //Prepara o objeto de contato para inserção
	  contactCustEntity.getData().setLastAuthDate( new Date() );
	  contactCustEntity.getData().setLastAuthUserId(fncVO.getLoggedUser() != null ? fncVO.getLoggedUser().getUserID() : "" );
	  contactCustEntity.getData().setLastUpdDate( new Date() );
	  contactCustEntity.getData().setLastUpdUserId(fncVO.getLoggedUser() != null ? fncVO.getLoggedUser().getUserID() : "" );
	  contactCustEntity.getData().setRecStatCode( "A" );
      
	  //Gera um novo valor para sequence e retorna o mesmo
	  contactCustEntity.getData().setCtcNbr(new BigInteger(tplContactCustDAO.getNextValContactCustNbr().toString()));	  
	  contactCustEntity.getData().setCustNbr(fncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr());
	  contactCustEntity.getData().setFullNameText(fncVO.getTplContactCustEntity().getData().getFullNameText());
	  contactCustEntity.getData().setFullNameText_2(fncVO.getTplContactCustEntity().getData().getFullNameText_2());
	  contactCustEntity.getData().setFullNameText_3(fncVO.getTplContactCustEntity().getData().getFullNameText_3());	  
	  contactCustEntity.getData().setPhoneNbr(fncVO.getTplContactCustEntity().getData().getPhoneNbr());
	  contactCustEntity.getData().setPhoneDddCode(fncVO.getTplContactCustEntity().getData().getPhoneDddCode());
	  contactCustEntity.getData().setPhoneExtnNbr(fncVO.getTplContactCustEntity().getData().getPhoneExtnNbr());	  
	  tplContactCustDAO.insert( contactCustEntity );	
		
      //Aloca o callback da mov com base nos dados passados do fnc
	  TplIpDocCallbackMovEntity tplIpDocCallbackEntity = new TplIpDocCallbackMovEntity(fncVO.getBaseTplIpDocCallbackEntity() );

	  tplIpDocCallbackEntity.getData().setLastUpdDate( new Date() );
	  tplIpDocCallbackEntity.getData().setLastUpdUserId(fncVO.getLoggedUser().getUserID() );
	  ( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );	  

	  if ( tplIpDocCallbackEntity.getData().getIpCallbackCode() == null )
	  {
		tplIpDocCallbackEntity.getData().setIpCallbackCode(
															new BigInteger(
																			tplIpDocCallbackDAO.getNextIpCallBackCode().toString() ) );

	  }
	  
      //Seta o novo código de contato gerado
	  tplIpDocCallbackEntity.getData().setCtcNbr(contactCustEntity.getData().getCtcNbr());		
	  
	  //Adiciona o callback na memória
	  fncVO.getInsertedIpDocCallback().add(tplIpDocCallbackEntity );

	  tplIpDocCallbackEntity = new TplIpDocCallbackMovEntity();

	  fncVO.setBaseTplIpDocCallbackEntity( tplIpDocCallbackEntity );
	  fncVO.getSelectedCallBackInGrid().add( "S" );
	  fncVO.getDeletedCallBackInGrid().add( "N" );
	}

	fncVO.setInnerActionInd( true );
  }

  private void validateInsertDocTransfer( BaseFncVO fncVO_ )
  {
	IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;

	if ( fncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
					   C_AGN_BANK_CODE_NOT_NULL );
	}

	if ( fncVO.getBaseTplDocTransferEntity().getData().getBrchCode() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
						   C_BRCH_CODE_NOT_NULL );
	}

	if ( fncVO.getBaseTplDocTransferEntity().getData().getCurAcctNbr() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
						   C_CUR_ACCT_NBR_NOT_NULL );
	}
	
	if ( fncVO.getBaseTplDocTransferEntity().getData().getBeneCpfCnpjNbr() == null )
	{
		  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
								   "Cpf/Cnpj do Beneficiário" );
	}
	
	if ( fncVO.getBaseTplDocTransferEntity().getData().getBeneNameText() == null )
	{
		  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
									   "Nome do Beneficiário" );
	}
	
	if ( fncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
									   "Tipo de Conta" );
	}
	
	if ( fncVO.getBaseTplDocTransferEntity().getData().getBeneMainDestAcctInd() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
									   "Conta Destino de Mesma Titularidade" );
	}

	if ( !fncVO.hasErrors() )
	{
	  if ( !existsAgnBankCode( fncVO ) )
	  {
		fncVO_.addError( BaseODSFncVO.C_INVALID_FK, C_INSERT_ACTION, C_BANK );
	  }

	  if ( !fncVO.hasErrors() )
	  {
		if ( !existsBrchCode( fncVO ) )
		{
		  fncVO_.addError( BaseODSFncVO.C_INVALID_FK, C_INSERT_ACTION, C_BRANCH );
		}
	  }

	}
  }

  private void validateInsertCallback( BaseFncVO fncVO_ )
  {
	IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;

	// Validar Campos Obrigatórios	
	if ( fncVO.getTplContactCustEntity().getData().getPhoneDddCode() == null )
	{
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
						   "DDD" );
	}
	
	if ( fncVO.getTplContactCustEntity().getData().getPhoneNbr() == null )
	{
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
						   "Telefone" );
	}
	
	if ( fncVO.getTplContactCustEntity().getData().getFullNameText() == null )
	{
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
						   "Nome do Contato Principal" );
	}

	/*if ( !fncVO.hasErrors() )
	{

	  if ( !existsCtcNbr( fncVO ) )
	  {
		fncVO_.addError( BaseODSFncVO.C_INVALID_FK, C_INSERT_ACTION,
						 C_CONTACT_CUST );
	  }

	  if ( !fncVO.hasErrors() )
	  {

		boolean existsCurrent = fncVO.getBaseTplIpDocPrvtEntity().getBaseIpDocCallbackEntities().contains(
																										   fncVO.getBaseTplIpDocCallbackEntity() );
		boolean existsInserted = fncVO.getInsertedIpDocCallback().contains(
																			fncVO.getBaseTplIpDocCallbackEntity() );

		if ( existsCurrent || existsInserted )
		{
		  fncVO_.addError( BaseODSFncVO.C_ERROR_DUPLICATE_ASSOCIATION,
						   C_IP_DOC_CALLBACK );
		}
	  }
	}*/
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
	this.validateUpdate( fncVO_ );
	IpDocPrvtMovementDetailFncVO detailFncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;
	Date date = new Date();

	if ( !fncVO_.hasErrors() )
	{
	  int count = 0;
	  boolean hasRemove = false;
	  for ( int j = 0; j < detailFncVO.getSelectedDocTransferInGrid().size(); j++ )
	  {
		  TplDocTransferMovEntity baseTplDocTransferEntity = ( TplDocTransferMovEntity ) detailFncVO.getInsertedDocTransfer().get(count);
		  TplDocTransferMovEntityVO tplDocTransferMovEntityVO = (TplDocTransferMovEntityVO) baseTplDocTransferEntity.getData();
		  //Remove da MOV - Caso esteja na MOV como insercao, e foi na tela está checado exclusao
		  if ( detailFncVO.getDeletedDocTransferInGrid().get( j ).equals( "S" ) && tplDocTransferMovEntityVO.getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_INSERT))
		  {
			  hasRemove = true;
			  
		  }//Remove da MOV - Caso esteja na MOV como exclusao, e foi na tela está retirado o check exclusao 
		  else if ( detailFncVO.getDeletedDocTransferInGrid().get( j ).equals( "N" ) && tplDocTransferMovEntityVO.getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_DELETE))
		  {
			  hasRemove = true; 
		  } else {
			  hasRemove = false;
		  }
		  
		  if (hasRemove){
			  detailFncVO.getInsertedDocTransfer().remove( count );
			  count--;
		  }
		  count++;
	  }

	  count = 0;
	  for ( int j = 0; j < detailFncVO.getSelectedCallBackInGrid().size(); j++ )
	  {
		  TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity = (TplIpDocCallbackMovEntity)detailFncVO.getInsertedIpDocCallback().get(count);
		  TplIpDocCallbackMovEntityVO tplIpDocCallbackMovEntityVO = (TplIpDocCallbackMovEntityVO)tplIpDocCallbackMovEntity.getData();
		  
		  //Remove da MOV - Caso esteja na MOV como insercao, e foi na tela está checado exclusao
		  if ( detailFncVO.getDeletedCallBackInGrid().get( j ).equals( "S" ) && tplIpDocCallbackMovEntityVO.getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_INSERT))
		  {
			  hasRemove = true;
			  TplContactCustEntity tplContactCustEntity = new TplContactCustEntity();
			  TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();

			  //exclusao logica na TPL_CONTACT CUST    	
			  tplContactCustEntity.getData().setCtcNbr(tplIpDocCallbackMovEntity.getData().getCtcNbr());
			  tplContactCustEntity.getData().setCustNbr(tplIpDocCallbackMovEntity.getData().getCustNbr());
			  tplContactCustEntity.getData().setLastAuthDate(date);
			  tplContactCustEntity.getData().setLastAuthUserId(fncVO_.getLoggedUser().getUserID());
			  tplContactCustEntity.getData().setLastUpdDate(date);
			  tplContactCustEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser().getUserID());
			  tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
			  tplContactCustDAO.delete(tplContactCustEntity);

		  }//Remove da MOV - Caso esteja na MOV como exclusao, e foi na tela está retirado o check exclusao 
		  else if ( detailFncVO.getDeletedCallBackInGrid().get( j ).equals( "N" ) && tplIpDocCallbackMovEntityVO.getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_DELETE))
		  {
			  hasRemove = true; 
		  } else {
			  hasRemove = false;
		  }
		  
		  if (hasRemove){
				detailFncVO.getInsertedIpDocCallback().remove( count );
				count--;			
		  }
		  
		  count++;
	  }

	  if ( !fncVO_.hasErrors() )
	  {

		TplIpDocPrvtMovEntity ipDocPrvtEntity = ( TplIpDocPrvtMovEntity ) detailFncVO.getBaseTplIpDocPrvtEntity();

		ipDocPrvtEntity.getData().setLastUpdDate( new Date() );
		ipDocPrvtEntity.getData().setLastUpdUserId(
													fncVO_.getLoggedUser() != null
																				  ? fncVO_.getLoggedUser().getUserID()
																				  : "" );

		( ( TplIpDocPrvtMovEntityVO ) ipDocPrvtEntity.getData() ).setOpernCode( TplIpDocPrvtMovEntity.C_OPERN_CODE_UPDATE );

		ipDocPrvtEntity.getBaseDocTransferEntities().addAll(
															 detailFncVO.getInsertedDocTransfer() );
		ipDocPrvtEntity.getBaseIpDocCallbackEntities().addAll(
															   detailFncVO.getInsertedIpDocCallback() );

		TplIpDocPrvtMovDAO tplIpDocPrvtMovDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtMovDAO();
		tplIpDocPrvtMovDAO.update( ipDocPrvtEntity );
	  }
	}
  }

  /**
   * Aprova os dados de uma Instrução Permanente com Pendência de Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {
	validateApprove( fncVO_ );

	if ( !fncVO_.hasErrors() )
	{
	  IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;
	  TplIpDocPrvtMovDAO movDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtMovDAO();

	  // Recupera o registro que está sendo aprovado nas tabelas de IpDocPrvt,
	  // CallBack e DocTransfer

	  TplIpDocPrvtMovEntity movDetailEntity = ( TplIpDocPrvtMovEntity ) movDAO.find( fncVO.getBaseTplIpDocPrvtEntity() );

	  // Constroi um objeto entity de Current com os dados de movimento
	  TplIpDocPrvtEntity entity = new TplIpDocPrvtEntity(
														  movDetailEntity,
														  new Date(),
														  fncVO_.getLoggedUser().getUserID(),
														  TplIpDocPrvtEntity.C_REC_STAT_CODE_ACTIVE );

	  // Recupera o opernCode de movimento para as operações
	  String movOpernCode = ( ( TplIpDocPrvtMovEntityVO ) movDetailEntity.getData() ).getOpernCode();

	  // 1. Se a operacao for de delete, seta recStatCode para inativo para o
	  // Ip, callback e documento de transferência
	  if ( TplIpDocPrvtEntity.C_OPERN_CODE_DELETE.equals( movOpernCode ) )
	  {
		( ( TplIpDocPrvtEntityVO ) entity.getData() ).setRecStatCode( TplIpDocPrvtMovEntity.C_REC_STAT_CODE_INACTIVE );
	  }

	  // 2. Se existe um registro na tabela de Current com o mesmo código,
	  // copia para histórico e atualiza Current
	  TplIpDocPrvtDAO ipDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();

	  // Insere histórico
	  TplIpDocPrvtHistDAO ipDocPrvtHistDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtHistDAO();

	  //Cria instancia das entity de histórico.
	  if ( ipDocPrvtDAO.exists( entity ) )
	  {
		TplIpDocPrvtEntity ipDocPrvtEntityOld = ( TplIpDocPrvtEntity ) ipDocPrvtDAO.find( entity );

		TplIpDocPrvtHistEntity tplIpDOcPrvtHistEntity = new TplIpDocPrvtHistEntity(
																					ipDocPrvtEntityOld,
																					new Date() );

		ipDocPrvtHistDAO.insert( tplIpDOcPrvtHistEntity );

		// atualiza Current
		ipDocPrvtDAO.update( entity );
	  }
	  else
	  {
		ipDocPrvtDAO.insert( entity );
	  }

	  //Chama o approve de Callback , DocTransfer e CurPrmnt
	  this.approveCallBack( fncVO_ );
	  this.approveTransfer( fncVO_ );
	  this.approveCurPrmnt(fncVO_);

	  //Remove do movimento
	  movDAO.delete( movDetailEntity );
	}

  }

  /**
   * Rejeita os dados de uma Intrução Permanente com Pendência de Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
	//  realiza validação
	validateReprove( fncVO_ );

	if ( !fncVO_.hasErrors() )
	{
	  TplIpDocPrvtMovDAO ipDocPrvtMovDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtMovDAO();
	  TplDocTransferMovDAO docTransferMovDAO = ODSDAOFactory.getInstance().getTplDocTransferMovDAO();
	  TplIpDocCallbackMovDAO ipDocCallbackMovDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackMovDAO();
	  TplCurAcctPrmntInstrMovDAO curAcctPrmntInstrmovDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
	  TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
	  
	  TplIpDocPrvtMovEntity movDetailEntity = ( TplIpDocPrvtMovEntity ) ipDocPrvtMovDAO.find( ( ( IpDocPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplIpDocPrvtEntity() );
	  TplDocTransferMovEntity docTransferMovEntity = ( TplDocTransferMovEntity ) ( ( IpDocPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplDocTransferEntity();
	  TplIpDocCallbackMovEntity ipDocCallbackMovEntity = ( TplIpDocCallbackMovEntity ) ( ( IpDocPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplIpDocCallbackEntity();
	  TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity = (TplCurAcctPrmntInstrMovEntity) curAcctPrmntInstrmovDAO.findById(( ( IpDocPrvtMovementDetailFncVO ) fncVO_ ).getBaseTplIpDocPrvtEntity().getData().getIpDocCode());

	  ArrayList listContactCust = ipDocCallbackMovDAO.findContactCustByPK(ipDocCallbackMovEntity.getData().getIpDocCode(), ipDocCallbackMovEntity.getData().getCustNbr());
	  Iterator itContactCust = listContactCust.iterator();
	  Date date =  new Date();
	  while (itContactCust.hasNext()) {
		  TplContactCustEntity tplContactCustEntity = (TplContactCustEntity)itContactCust.next();

		//exclusao logica na TPL_CONTACT CUST    	
    	tplContactCustEntity.getData().setLastAuthDate(date);
    	tplContactCustEntity.getData().setLastAuthUserId(fncVO_.getLoggedUser().getUserID());
    	tplContactCustEntity.getData().setLastUpdDate(date);
    	tplContactCustEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser().getUserID());
    	tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
    	tplContactCustDAO.delete(tplContactCustEntity);
      
	  }
	  
	  docTransferMovDAO.delete( docTransferMovEntity );
	  ipDocCallbackMovDAO.delete( ipDocCallbackMovEntity );
	  ipDocPrvtMovDAO.delete( movDetailEntity );
	  curAcctPrmntInstrmovDAO.deleteById(tplCurAcctPrmntInstrMovEntity.getData().getPrmntInstrCode());

	}

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
	IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;

	//Verifica se ha pelo menos um IP associado
	if ( !fncVO_.hasErrors() )
	{
	  int findCallBackDeleted = 0;
	  int findDocTransferDeleted = 0;
	  boolean hasDocTransferMov = true;
	  boolean hasCallBackMov = true;
	  

	  for ( int i = 0; i < fncVO.getDeletedCallBackInGrid().size(); i++ )
	  {
		if ( fncVO.getDeletedCallBackInGrid().get( i ).equals( "S" ) )
		{
		  findCallBackDeleted++;
		}
	  }

	  for ( int i = 0; i < fncVO.getDeletedDocTransferInGrid().size(); i++ )
	  {
		if ( fncVO.getDeletedDocTransferInGrid().get( i ).equals( "S" ) )
		{
		  findDocTransferDeleted++;

		}
	  }

	  if ( findDocTransferDeleted == fncVO.getInsertedDocTransfer().size() )
	  {
		  
		  hasDocTransferMov = false;
//		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
//						 C_INSERT_ACTION, C_DOC_TRANSFER );
	  }
	  if ( findCallBackDeleted == fncVO.getInsertedIpDocCallback().size() )
	  {
		  hasCallBackMov = false;
//		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
//						 C_INSERT_ASSOCIATION_ACTION, C_IP_DOC_CALLBACK );
	  }
	  
	  if (!hasDocTransferMov && !hasCallBackMov){
		  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
					 C_INSERT_ACTION, C_DOC_TRANSFER );
		  
	  }
	}
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
	IpDocPrvtMovementDetailFncVO ipDocPrvtMovementDetailFncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;
	TplIpDocPrvtMovEntityVO ipDocPrvtMovEntityVO = ( TplIpDocPrvtMovEntityVO ) ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getData();

	// testar usuário
	if ( ipDocPrvtMovementDetailFncVO.getLoggedUser() == null
		 || ipDocPrvtMovementDetailFncVO.getLoggedUser().getUserID().equals(
																			 ipDocPrvtMovEntityVO.getLastUpdUserId() ) )
	{
	  ipDocPrvtMovementDetailFncVO.addError( IpDocPrvtMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
	}

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
	TplDocTransferMovEntity tplDocTransferMovEntity;
	TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity;
	BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO = ( BaseIpDocPrvtDetailFncVO ) fncVO_;

	loadContactFullNameText( baseIpDocPrvtDetailFncVO );

	if ( !baseIpDocPrvtDetailFncVO.isInnerActionInd()
		 && !baseIpDocPrvtDetailFncVO.isFromSearch() )
	{
	  IpDocPrvtMovementDetailFncVO ipDocPrvtMovementDetailFncVO = ( IpDocPrvtMovementDetailFncVO ) baseIpDocPrvtDetailFncVO;

	  this.loadForConsult( ipDocPrvtMovementDetailFncVO );

	  for ( int i = 0; i < ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseDocTransferEntities().size(); i++ )
	  {

		tplDocTransferMovEntity = ( TplDocTransferMovEntity ) ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseDocTransferEntities().get(
																																						 i );

		if ( ( ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity.getData() ).getOpernCode().equals(
																										BaseODSEntity.C_OPERN_CODE_INSERT ) )
		{
		  baseIpDocPrvtDetailFncVO.getInsertedDocTransfer().add(
																 tplDocTransferMovEntity );
		}
	  }

	  for ( int i = 0; i < ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseIpDocCallbackEntities().size(); i++ )
	  {

		tplIpDocCallbackMovEntity = ( TplIpDocCallbackMovEntity ) ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseIpDocCallbackEntities().get(
																																							   i );

		if ( ( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity.getData() ).getOpernCode().equals(
																											BaseODSEntity.C_OPERN_CODE_INSERT ) )
		{
		  baseIpDocPrvtDetailFncVO.getInsertedIpDocCallback().add(
																   tplIpDocCallbackMovEntity );
		}
	  }

	  loadCustFullNameText( ipDocPrvtMovementDetailFncVO );
	}
	this.loadAccount(baseIpDocPrvtDetailFncVO);
	
	baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setIpDocText("" );
	baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setIpInvstCurAcctInd("" );
	baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setLastUpdDate(null );
	baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setLastUpdUserId(null );
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setAgnBankCode(null );
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBrchCode(null );
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setCurAcctNbr(null );
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setTxnTypeCode(null );
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setOwnDestAcctInd("" );
	  
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneAcctTypeCode("");
	  
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneMainDestAcctInd("");
	  
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneNameText("");
	  
	baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneCpfCnpjNbr("");
	  																							  
	baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity().getData().setCtcNbr(null );
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText("");
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText_2("");
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText_3("");
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneNbr(null);
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneDddCode(null);
	baseIpDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneExtnNbr(null);
	baseIpDocPrvtDetailFncVO.setInnerActionInd( false );
	
	

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
	TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity;
	TplDocTransferMovEntity tplDocTransferMovEntity;

	IpDocPrvtMovementDetailFncVO ipDocPrvtMovementDetailFncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;

	this.loadForConsult( fncVO_ );

	if ( !( ( TplIpDocPrvtMovEntityVO ) ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getData() ).getOpernCode().equals(
																																	BaseODSEntity.C_OPERN_CODE_DELETE ) )
	{
	  for ( int i = 0; i < ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseDocTransferEntities().size(); i++ )
	  {

		tplDocTransferMovEntity = ( TplDocTransferMovEntity ) ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseDocTransferEntities().get(
																																						 i );

		if ( ( ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity.getData() ).getOpernCode().equals(
																										BaseODSEntity.C_OPERN_CODE_INSERT ) )
		{
		  ipDocPrvtMovementDetailFncVO.getInsertedDocTransfer().add(
																	 tplDocTransferMovEntity );
		}

	  }

	  for ( int i = 0; i < ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseIpDocCallbackEntities().size(); i++ )
	  {

		tplIpDocCallbackMovEntity = ( TplIpDocCallbackMovEntity ) ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseIpDocCallbackEntities().get(
																																							   i );

		if ( ( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity.getData() ).getOpernCode().equals(
																											BaseODSEntity.C_OPERN_CODE_INSERT ) )
		{
		  ipDocPrvtMovementDetailFncVO.getInsertedIpDocCallback().add(
																	   tplIpDocCallbackMovEntity );
		}

	  }
	}
	else
	{
	  for ( int i = 0; i < ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseDocTransferEntities().size(); i++ )
	  {

		tplDocTransferMovEntity = ( TplDocTransferMovEntity ) ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseDocTransferEntities().get(
																																						 i );
		ipDocPrvtMovementDetailFncVO.getInsertedDocTransfer().add(
																   tplDocTransferMovEntity );
	  }

	  for ( int i = 0; i < ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseIpDocCallbackEntities().size(); i++ )
	  {
		tplIpDocCallbackMovEntity = ( TplIpDocCallbackMovEntity ) ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getBaseIpDocCallbackEntities().get(
																																							   i );
		ipDocPrvtMovementDetailFncVO.getInsertedIpDocCallback().add(
																	 tplIpDocCallbackMovEntity );
	  }
	}

	loadCustFullNameText( ipDocPrvtMovementDetailFncVO );
	this.loadAccount(ipDocPrvtMovementDetailFncVO);
	//ipDocPrvtMovementDetailFncVO.setInnerActionInd( false );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {

	super.load( fncVO_ );

	TplIpDocCallbackMovDAO ipDocCallbackMovDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackMovDAO();
	TplDocTransferMovDAO docTransferMovDAO = ODSDAOFactory.getInstance().getTplDocTransferMovDAO();

	IpDocPrvtMovementDetailFncVO ipDocPrvtMovementDetailFncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;
	ipDocPrvtMovementDetailFncVO.setInsertedDocTransfer( new ArrayList() );
	ipDocPrvtMovementDetailFncVO.setInsertedIpDocCallback( new ArrayList() );

	ipDocPrvtMovementDetailFncVO.setInsertedIpDocCallback( ipDocCallbackMovDAO.findByPK(
																						 ipDocPrvtMovementDetailFncVO.getBaseTplDocTransferEntity().getData().getIpDocCode(),
																						 ipDocPrvtMovementDetailFncVO.getBaseTplDocTransferEntity().getData().getCustNbr() ) );
	
	ipDocPrvtMovementDetailFncVO.setInsertedDocTransfer( docTransferMovDAO.findByPK(
																					 ipDocPrvtMovementDetailFncVO.getBaseTplDocTransferEntity().getData().getIpDocCode(),
																					 ipDocPrvtMovementDetailFncVO.getBaseTplDocTransferEntity().getData().getCustNbr() ) );
	
	ArrayList listInsertedDocTransfer =  ipDocPrvtMovementDetailFncVO.getInsertedDocTransfer();
	ArrayList<String> selectedDocTransfer = new ArrayList<String>();
	ArrayList<String> deletedDocTransfer = new ArrayList<String>();
	 for ( int i = 0; i < listInsertedDocTransfer.size(); i++ )
		{
		 TplDocTransferMovEntity tplDocTransferMovEntity = (TplDocTransferMovEntity)listInsertedDocTransfer.get(i);
		 if (( ( TplDocTransferMovEntityVO ) tplDocTransferMovEntity.getData() ).getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_DELETE)){
			 deletedDocTransfer.add(BaseODSEntity.C_INDICATOR_YES);
			 selectedDocTransfer.add(BaseODSEntity.C_INDICATOR_NO);
		 } else {
			 deletedDocTransfer.add(BaseODSEntity.C_INDICATOR_NO);
			 selectedDocTransfer.add(BaseODSEntity.C_INDICATOR_NO);			 
		 }
		 
		}
	ipDocPrvtMovementDetailFncVO.setSelectedDocTransferInGrid(selectedDocTransfer);
	ipDocPrvtMovementDetailFncVO.setDeletedDocTransferInGrid(deletedDocTransfer);
	
	
	ArrayList listInsertedIpoDocCallback =  ipDocPrvtMovementDetailFncVO.getInsertedIpDocCallback();
	ArrayList<String> selectedCallBackInGrid = new ArrayList<String>();
	ArrayList<String> deletedCallBackInGrid = new ArrayList<String>();
	 for ( int i = 0; i < listInsertedIpoDocCallback.size(); i++ )
		{
		 TplIpDocCallbackMovEntity tplIpDocCallbackMovEntity = (TplIpDocCallbackMovEntity)listInsertedIpoDocCallback.get(i);
		 if (( ( TplIpDocCallbackMovEntityVO ) tplIpDocCallbackMovEntity.getData() ).getOpernCode().equals(BaseODSEntity.C_OPERN_CODE_DELETE)){
			 deletedCallBackInGrid.add(BaseODSEntity.C_INDICATOR_YES);
			 selectedCallBackInGrid.add(BaseODSEntity.C_INDICATOR_NO);
		 } else {
			 deletedCallBackInGrid.add(BaseODSEntity.C_INDICATOR_NO);
			 selectedCallBackInGrid.add(BaseODSEntity.C_INDICATOR_NO);			 
		 }
		 
		}
	ipDocPrvtMovementDetailFncVO.setSelectedCallBackInGrid(selectedCallBackInGrid);
	ipDocPrvtMovementDetailFncVO.setDeletedCallBackInGrid(deletedCallBackInGrid);
  
  

  }
  
  public void loadAccount(BaseFncVO fncVO_){
  	
  	TplCurAcctPrmntInstrMovDAO tplCurAcctPrmntInstrMovDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
	To3ProductAccountDAO productAccountDAO = ODSDAOFactory.getInstance().getTo3ProductAccountDAO();	
  	
	IpDocPrvtMovementDetailFncVO ipDocPrvtMovementDetailFncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;	
	TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity = (TplCurAcctPrmntInstrMovEntity) tplCurAcctPrmntInstrMovDAO.findById(ipDocPrvtMovementDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode());
	
	BaseTo3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity();
	productAccountEntity.getData().setProdAcctCode(tplCurAcctPrmntInstrMovEntity.getData().getProdAcctCode());
	productAccountEntity.getData().setProdUnderAcctCode(tplCurAcctPrmntInstrMovEntity.getData().getProdUnderAcctCode());
	productAccountEntity = productAccountDAO.findByPK(productAccountEntity);	
	
	ipDocPrvtMovementDetailFncVO.setTo3ProductAccountEntity(productAccountEntity);
  	
  }

  /**
   * Aprova os dados de tranferencia de uma Instrução Permanente com Pendência
   * de Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approveTransfer( BaseFncVO fncVO_ )
  {
	//Cria instancias das entities específicas
	TplDocTransferMovEntity docTransferMovEntity = new TplDocTransferMovEntity();
	TplDocTransferEntity docTransferEntity = new TplDocTransferEntity();
	TplDocTransferHistEntity docTransferHistEntity = new TplDocTransferHistEntity();

	//Cria instancias dos DAOS específicos
	TplDocTransferDAO docTranferDAO = ODSDAOFactory.getInstance().getTplDocTransferDAO();
	TplDocTransferHistDAO docTranferHistDAO = ODSDAOFactory.getInstance().getTplDocTransferHistDAO();
	TplDocTransferMovDAO docTransferMovDAO = ODSDAOFactory.getInstance().getTplDocTransferMovDAO();

	if ( !fncVO_.hasErrors() )
	{

	  IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;

	  ArrayList listDocTransferCurrent = docTranferDAO.findByPK(
																 fncVO.getBaseTplDocTransferEntity().getData().getIpDocCode(),
																 fncVO.getBaseTplDocTransferEntity().getData().getCustNbr() );

	  ArrayList listDocTransferMov = fncVO.getInsertedDocTransfer();

	  if ( listDocTransferCurrent != null && listDocTransferCurrent.size() > 0 )
	  {
		for ( int i = 0; i < listDocTransferCurrent.size(); i++ )
		{
		  docTransferEntity = ( TplDocTransferEntity ) listDocTransferCurrent.get( i );
		  docTransferHistEntity = new TplDocTransferHistEntity(
																docTransferEntity,
																new Date() );

		  docTranferHistDAO.insert( docTransferHistEntity );
		}
	  }

	  for ( int i = 0; i < listDocTransferMov.size(); i++ )
	  {

		docTransferMovEntity = ( TplDocTransferMovEntity ) listDocTransferMov.get( i );

		//Cria uma instancia da Entity da tabela corrente com os dados da
		// tabela de movimento
		docTransferEntity = new TplDocTransferEntity( docTransferMovEntity );
		TplDocTransferEntityVO tplDocTransferEntityVO = ( ( TplDocTransferEntityVO ) docTransferEntity.getData() );
		tplDocTransferEntityVO.setRecStatCode( TplDocTransferEntity.C_REC_STAT_CODE_ACTIVE );
		tplDocTransferEntityVO.setLastAuthDate(new Date());
		tplDocTransferEntityVO.setLastAuthUserId(fncVO_.getLoggedUser().getUserID());

		if ( !( ( TplDocTransferMovEntityVO ) docTransferMovEntity.getData() ).getOpernCode().equals(
																									  TplDocTransferEntity.C_OPERN_CODE_DELETE ) )
		{
		  if ( docTranferDAO.exists( docTransferEntity ) )
		  {
			docTranferDAO.update( docTransferEntity );
		  }
		  else
		  {
			docTranferDAO.insert( docTransferEntity );
		  }
		}
		else
		{
		  tplDocTransferEntityVO.setRecStatCode( TplIpDocCallbackEntity.C_REC_STAT_CODE_INACTIVE );
		  docTranferDAO.update( docTransferEntity );
		}

	  }
	  // 3. Remove o movimento
	  docTransferMovDAO.delete( docTransferMovEntity );
	}
  }
  
  public void approveCurPrmnt( BaseFncVO fncVO_ ){
	
	IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;
	
	TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = null;
	TplCurAcctPrmntInstrHistEntity tplCurAcctPrmntInstrHistEntity = null;
	TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity = null;
	
	TplCurAcctPrmntInstrMovDAO movDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
	TplCurAcctPrmntInstrDAO curAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();
	TplCurAcctPrmntInstrHistDAO histDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrHistDAO();
	
    
	//Retorna o registro da current
	try{
		tplCurAcctPrmntInstrEntity  = ( TplCurAcctPrmntInstrEntity ) curAcctPrmntInstrDAO.findById(fncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode());
	}catch(NoRowsReturnedException e){
		tplCurAcctPrmntInstrEntity = null;		 
	}
	
	
	if(tplCurAcctPrmntInstrEntity != null){
		
        //Inseri o registro da tabela corrente na tabela de histórico
		tplCurAcctPrmntInstrHistEntity = new TplCurAcctPrmntInstrHistEntity(tplCurAcctPrmntInstrEntity,
																			new Date() );
		histDAO.insert( tplCurAcctPrmntInstrHistEntity );
	}
	
	//Retorna o registro da movement
	tplCurAcctPrmntInstrMovEntity = (TplCurAcctPrmntInstrMovEntity) movDAO.findById(fncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode());
	
	tplCurAcctPrmntInstrEntity = new TplCurAcctPrmntInstrEntity(tplCurAcctPrmntInstrMovEntity,
																new Date(),
																fncVO_.getLoggedUser().getUserID(),
																TplCurAcctPrmntInstrEntity.C_REC_STAT_CODE_ACTIVE );
	
	if ( !( ( TplCurAcctPrmntInstrMovEntityVO ) tplCurAcctPrmntInstrMovEntity.getData() ).getOpernCode().equals(
																														 TplCurAcctPrmntInstrMovEntity.C_OPERN_CODE_DELETE ) )
	{		
		if (curAcctPrmntInstrDAO.exists(tplCurAcctPrmntInstrEntity)) {
			curAcctPrmntInstrDAO.update(tplCurAcctPrmntInstrEntity);
		} else {
			curAcctPrmntInstrDAO.insert(tplCurAcctPrmntInstrEntity);
		}	  
	}
	else
	{
	  ( ( TplCurAcctPrmntInstrEntityVO ) tplCurAcctPrmntInstrEntity.getData() ).setRecStatCode( TplCurAcctPrmntInstrEntity.C_REC_STAT_CODE_INACTIVE );
	  curAcctPrmntInstrDAO.update( tplCurAcctPrmntInstrEntity );
	}

	
    //Remove o movimento
	movDAO.deleteById(fncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode());
  	
  }

  /**
   * Aprova os dados de tranferencia de uma Instrução Permanente com Pendência
   * de Aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approveCallBack( BaseFncVO fncVO_ )
  {
	//Cria instancias das entities específicas
	TplIpDocCallbackMovEntity callbackMovEntity = new TplIpDocCallbackMovEntity();
	TplIpDocCallbackEntity callbackEntity = new TplIpDocCallbackEntity();
	TplIpDocCallbackHistEntity callbackHistEntity = new TplIpDocCallbackHistEntity();

	//Cria instancias dos DAOS específicos
	TplIpDocCallbackDAO callbackDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackDAO();
	TplIpDocCallbackHistDAO callbackHistDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackHistDAO();
	TplIpDocCallbackMovDAO callbackMovDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackMovDAO();
	TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();
	
	Date date = new Date();

	if ( !fncVO_.hasErrors() )
	{

	  IpDocPrvtMovementDetailFncVO fncVO = ( IpDocPrvtMovementDetailFncVO ) fncVO_;

	  ArrayList listCallBackCurrent = callbackDAO.findByPK(
															fncVO.getBaseTplDocTransferEntity().getData().getIpDocCode(),
															fncVO.getBaseTplDocTransferEntity().getData().getCustNbr() );

	  ArrayList listCallBackMov = fncVO.getInsertedIpDocCallback();

	  if ( listCallBackCurrent != null && listCallBackCurrent.size() > 0 )
	  {
		for ( int i = 0; i < listCallBackCurrent.size(); i++ )
		{
		  callbackEntity = ( TplIpDocCallbackEntity ) listCallBackCurrent.get( i );
		  callbackHistEntity = new TplIpDocCallbackHistEntity( callbackEntity,new Date());

		  callbackHistDAO.insert( callbackHistEntity );
		}

	  }

	  if (listCallBackMov != null && listCallBackMov.size() > 0) {
				for (int i = 0; i < listCallBackMov.size(); i++) {
					callbackMovEntity = (TplIpDocCallbackMovEntity) listCallBackMov
							.get(i);

					// Cria uma instancia de entity da tabela corrente com os
					// dados da
					// tabela de movimento
					callbackEntity = new TplIpDocCallbackEntity(
							callbackMovEntity);
					callbackEntity.getData().setRecStatCode(
							TplIpDocCallbackEntity.C_REC_STAT_CODE_ACTIVE);
					callbackEntity.getData().setLastAuthDate(new Date());
					callbackEntity.getData().setLastAuthUserId(
							fncVO_.getLoggedUser().getUserID());

					if (!((TplIpDocCallbackMovEntityVO) callbackMovEntity
							.getData()).getOpernCode().equals(
							TplIpDocCallbackEntity.C_OPERN_CODE_DELETE)) {

						if (callbackDAO.exists(callbackEntity)) {
							callbackDAO.update(callbackEntity);
						} else {
							callbackDAO.insert(callbackEntity);
						}
					} else {

						TplContactCustEntity tplContactCustEntity = new TplContactCustEntity();

						//exclusao logica na TPL_CONTACT CUST    	
						tplContactCustEntity.getData().setCtcNbr(callbackEntity.getData().getCtcNbr());
						tplContactCustEntity.getData().setCustNbr(callbackEntity.getData().getCustNbr());
						tplContactCustEntity.getData().setLastAuthDate(date);
						tplContactCustEntity.getData().setLastAuthUserId(fncVO_.getLoggedUser().getUserID());
						tplContactCustEntity.getData().setLastUpdDate(date);
						tplContactCustEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser().getUserID());
						tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
						tplContactCustDAO.delete(tplContactCustEntity);



						callbackEntity
						.getData()
						.setRecStatCode(
								TplIpDocCallbackEntity.C_REC_STAT_CODE_INACTIVE);
						callbackDAO.update(callbackEntity);
					}

				}
				// 3. Remove o movimento
				callbackMovDAO.delete(callbackMovEntity);
			}
		}

  }
}