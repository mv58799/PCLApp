package com.citibank.ods.modules.client.ipdocprvt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrMovEntity;
import com.citibank.ods.entity.pl.TplDocTransferEntity;
import com.citibank.ods.entity.pl.TplDocTransferMovEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackMovEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplDocTransferMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplIpDocCallbackMovEntityVO;
import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocPrvtDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtDetailFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.To3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 *  
 */

public class IpDocPrvtDetailFnc extends BaseIpDocPrvtDetailFnc implements
	ODSDetailFnc
{
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
	super.updateFncVOFromForm( form_, fncVO_ );

	IpDocPrvtDetailFncVO ipDocPrvtDetailFncVO = ( IpDocPrvtDetailFncVO ) fncVO_;
	IpDocPrvtDetailForm ipDocPrvtDetailForm = ( IpDocPrvtDetailForm ) form_;

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
	
	ArrayList domainsBeneCpfCnpjNbrList = new ArrayList();  
	ArrayList domainsBeneNameTextList = new ArrayList();  
	ArrayList domainsBeneMainDestAcctIndList = new ArrayList();  
	ArrayList domainsBeneAcctTypeCodeList = new ArrayList();
	

	BaseTplDocTransferEntity baseTplDocTransferEntity = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity();

	ArrayList ipDocTransferEntities = baseIpDocPrvtDetailFncVO.getInsertedDocTransfer();
	Iterator ipDocTransferEntitiesIt = ipDocTransferEntities.iterator();

	while ( ipDocTransferEntitiesIt.hasNext() )
	{

	  baseTplDocTransferEntity = ( TplDocTransferEntity ) ipDocTransferEntitiesIt.next();

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
	  domainsAgnBankCodeArray[ i ] = domainsAgnBankCodeList.get( i ).toString();
	  domainsBrchCodeArray[ i ] = domainsBrchCodeList.get( i ).toString();
	  
	  domainsBeneCpfCnpjNbrArray[i] = domainsBeneCpfCnpjNbrList.get( i ).toString();
	  domainsBeneNameTextArray[i] = domainsBeneNameTextList.get( i ).toString();
	  domainsBeneMainDestAcctIndArray[i] = domainsBeneMainDestAcctIndList.get( i ).toString();
	  domainsBeneAcctTypeCodeArray[i] = domainsBeneAcctTypeCodeList .get( i ).toString();																		   
	}

	baseIpDocPrvtDetailForm.setDomainsDocTransferCode( domainsDocTransferCodeArray );
	baseIpDocPrvtDetailForm.setDomainsAgnBankCode( domainsAgnBankCodeArray );
	baseIpDocPrvtDetailForm.setDomainsAgnBankName( domainsAgnBankCodeArray );
	baseIpDocPrvtDetailForm.setDomainsBrchCode( domainsBrchCodeArray );
	baseIpDocPrvtDetailForm.setDomainsBrchName( domainsBrchCodeArray );
	baseIpDocPrvtDetailForm.setDomainsCurAcctCode( domainsCurAcctCodeArray );
	baseIpDocPrvtDetailForm.setDomainsOwnDestAcctInd( domainsOwnDestAcctIndArray );
	baseIpDocPrvtDetailForm.setDomainsTxnTypeCode( domainsTxnTypeCodeArray );
	
	baseIpDocPrvtDetailForm.setDomainsBeneCpfCnpjNbr(domainsBeneCpfCnpjNbrArray);
	baseIpDocPrvtDetailForm.setDomainsBeneNameText(domainsBeneNameTextArray);
	baseIpDocPrvtDetailForm.setDomainsBeneMainDestAcctInd(domainsBeneMainDestAcctIndArray);
	baseIpDocPrvtDetailForm.setDomainsBeneAcctTypeCode(domainsBeneAcctTypeCodeArray);
	

	//  Atualização do grid de Callback
	ArrayList domainsFullNameTextList = new ArrayList();
	ArrayList domainsCtcNbrList = new ArrayList();

	BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity = baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity();

	ArrayList ipDocCallbackEntities = baseIpDocPrvtDetailFncVO.getInsertedIpDocCallback();
	Iterator ipDocCallbackEntitiesIt = ipDocCallbackEntities.iterator();

	while ( ipDocCallbackEntitiesIt.hasNext() )
	{
	  baseTplIpDocCallbackEntity = ( TplIpDocCallbackEntity ) ipDocCallbackEntitiesIt.next();

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
	}

	String[] domainsCtcNbrArray = new String[ domainsCtcNbrList.size() ];
	String[] domainsFullNameTextArray = new String[ domainsFullNameTextList.size() ];
	String[] domainsFullNameTextArray_2 = new String[ domainsFullNameTextList.size() ];
	String[] domainsFullNameTextArray_3 = new String[ domainsFullNameTextList.size() ];
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
	}

	baseIpDocPrvtDetailForm.setDomainsCtcNbr( domainsCtcNbrArray );
	baseIpDocPrvtDetailForm.setDomainsFullNameText( domainsFullNameTextArray );
	baseIpDocPrvtDetailForm.setDomainsFullNameText_2(domainsFullNameTextArray_2);
	baseIpDocPrvtDetailForm.setDomainsFullNameText_3(domainsFullNameTextArray_3);
	baseIpDocPrvtDetailForm.setDomainsPhone(domainsPhoneArray);
	baseIpDocPrvtDetailForm.setDomainsDDDPhone(domainsDddPhoneArray);
	baseIpDocPrvtDetailForm.setDomainsRamalPhone(domainsRamalPhoneArray);

  } /*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
	 */

  public BaseFncVO createFncVO()
  {
	return new IpDocPrvtDetailFncVO();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.modules.client.ipdocprvt.functionality.BaseIpDocPrvtDetailFnc#getDAO()
   */
  protected BaseTplIpDocPrvtDAO getDAO()
  {
	ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
	TplIpDocPrvtDAO tplIpDocPrvtDAO = odsDAOFactory.getTplIpDocPrvtDAO();
	return tplIpDocPrvtDAO;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {

	this.validateInsert( fncVO_ );
	if ( !fncVO_.hasErrors() )
	{
	  IpDocPrvtDetailFncVO detailFncVO = ( IpDocPrvtDetailFncVO ) fncVO_;
	  TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();	
	  Date date =  new Date();
	  int count =0;
	  for ( int i = 0; i < detailFncVO.getSelectedCallBackInGrid().size(); i++ )
	  {		
		  if ( detailFncVO.getSelectedCallBackInGrid().get( i ).equals( "S" )
			   && detailFncVO.getDeletedCallBackInGrid().get( i ).equals( "S" ) )
		  {
			    //exclusao logica na TPL_CONTACT_CUST
			  TplIpDocCallbackEntity ipDocCallbackEntity = ( TplIpDocCallbackEntity ) detailFncVO.getInsertedIpDocCallback().get(count);
			    TplContactCustEntity tplContactCustEntity = new TplContactCustEntity();
			    tplContactCustEntity.getData().setCtcNbr(ipDocCallbackEntity.getData().getCtcNbr());
			    tplContactCustEntity.getData().setCustNbr(ipDocCallbackEntity.getData().getCustNbr());
			    tplContactCustEntity.getData().setLastAuthDate(date);
			    tplContactCustEntity.getData().setLastAuthUserId(detailFncVO.getLoggedUser().getUserID());
			    tplContactCustEntity.getData().setLastUpdDate(date);
			    tplContactCustEntity.getData().setLastUpdUserId(detailFncVO.getLoggedUser().getUserID());
			    tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
			    tplContactCustDAO.delete(tplContactCustEntity);
			    
			    detailFncVO.getInsertedIpDocCallback().remove( count );
			count --;			
		  }
		  count++;
	  }

	  count =0;
	  for ( int i = 0; i < detailFncVO.getSelectedDocTransferInGrid().size(); i++ )
	  {
		  if ( detailFncVO.getSelectedDocTransferInGrid().get( i ).equals( "S" )
			   && detailFncVO.getDeletedDocTransferInGrid().get( i ).equals(
																			 "S" ) )
		  {
			detailFncVO.getInsertedDocTransfer().remove( i );
			count --;
		  }
		  count++;
	  }

	  TplIpDocPrvtEntity ipDocPrvtEntity = ( TplIpDocPrvtEntity ) detailFncVO.getBaseTplIpDocPrvtEntity();

	  ipDocPrvtEntity.getData().setLastUpdDate( new Date() );
	  ipDocPrvtEntity.getData().setLastUpdUserId(
												  fncVO_.getLoggedUser() != null
																				? fncVO_.getLoggedUser().getUserID()
																				: "" );
      //Retorna o próximo código da sequence
	  TplIpDocPrvtDAO ipDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();																				
	  ipDocPrvtEntity.getData().setIpDocCode(( new BigInteger( ipDocPrvtDAO.getNextIpCode().toString() ) ));																				
		
	  TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity = new TplIpDocPrvtMovEntity();

	  TplDocTransferEntity docTransferEntity = new TplDocTransferEntity();
	  docTransferEntity.getData().setLastUpdDate( new Date() );
	  docTransferEntity.getData().setLastUpdUserId(
													fncVO_.getLoggedUser() != null
																				  ? fncVO_.getLoggedUser().getUserID()
																				  : "" );

	  TplIpDocPrvtMovDAO tplIpDocPrvtMovDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtMovDAO();
	  TplDocTransferDAO tplDocTransferDAO = ODSDAOFactory.getInstance().getTplDocTransferDAO();

	  for ( int i = 0; i < detailFncVO.getInsertedDocTransfer().size(); i++ )
	  {
		TplDocTransferMovEntity movEntity = new TplDocTransferMovEntity();

		docTransferEntity = ( TplDocTransferEntity ) detailFncVO.getInsertedDocTransfer().get(
																							   i );

		if ( docTransferEntity != null )
		{

		  movEntity.getData().setAgnBankCode(
											  docTransferEntity.getData().getAgnBankCode() );
		  movEntity.getData().setDocTransferCode(docTransferEntity.getData().getDocTransferCode());
		  movEntity.getData().setBrchCode(
										   docTransferEntity.getData().getBrchCode() );
		  movEntity.getData().setCurAcctNbr(
											 docTransferEntity.getData().getCurAcctNbr() );
		  movEntity.getData().setCustNbr(
										  detailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() );
		  movEntity.getData().setIpDocCode(ipDocPrvtEntity.getData().getIpDocCode() );
		  /*movEntity.getData().setLastUpdDate(
											  docTransferEntity.getData().getLastUpdDate() );
		  movEntity.getData().setLastUpdUserId(
												docTransferEntity.getData().getLastUpdUserId() );*/
		  movEntity.getData().setOwnDestAcctInd(
												 docTransferEntity.getData().getOwnDestAcctInd() );
		  movEntity.getData().setTxnTypeCode(
											  docTransferEntity.getData().getTxnTypeCode() );
											  
		  movEntity.getData().setBeneCpfCnpjNbr(docTransferEntity.getData().getBeneCpfCnpjNbr());
		  movEntity.getData().setBeneNameText(docTransferEntity.getData().getBeneNameText());
		  movEntity.getData().setBeneAcctTypeCode(docTransferEntity.getData().getBeneAcctTypeCode());
		  movEntity.getData().setBeneMainDestAcctInd(docTransferEntity.getData().getBeneMainDestAcctInd());
		  											  

		  ( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );

		  ( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setLastUpdDate( new Date());

		  ( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setLastUpdUserId( fncVO_.getLoggedUser() != null
		  																			? fncVO_.getLoggedUser().getUserID()
		  																		: "" );

		  tplIpDocPrvtMovEntity.getBaseDocTransferEntities().add( movEntity );
		}
	  }

	  TplIpDocCallbackEntity ipDocCallbackEntity = new TplIpDocCallbackEntity();

	  ipDocCallbackEntity.getData().setLastUpdDate( new Date() );

	  ipDocCallbackEntity.getData().setLastUpdUserId(
													  fncVO_.getLoggedUser() != null
																					? fncVO_.getLoggedUser().getUserID()
																					: "" );

	  TplIpDocCallbackDAO tplIpDocCallbackDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackDAO();

	  for ( int i = 0; i < detailFncVO.getInsertedIpDocCallback().size(); i++ )
	  {
		TplIpDocCallbackMovEntity movEntity = new TplIpDocCallbackMovEntity();

		ipDocCallbackEntity = ( TplIpDocCallbackEntity ) detailFncVO.getInsertedIpDocCallback().get(
																									 i );

		if ( ipDocCallbackEntity != null )
		{
		  movEntity.getData().setIpCallbackCode(
												 new BigInteger(
																 tplIpDocCallbackDAO.getNextIpCallBackCode().toString() ) );
		  movEntity.getData().setCtcNbr(
										 ipDocCallbackEntity.getData().getCtcNbr() );
		  movEntity.getData().setCustNbr(
										  detailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() );
		  movEntity.getData().setIpDocCode(ipDocPrvtEntity.getData().getIpDocCode() );
		 /* movEntity.getData().setLastUpdDate(
											  ipDocCallbackEntity.getData().getLastUpdDate() );
		  movEntity.getData().setLastUpdUserId(
												ipDocCallbackEntity.getData().getLastUpdUserId() );*/

		  ( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );

		  ( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setLastUpdDate( new Date() );

		  ( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setLastUpdUserId( fncVO_.getLoggedUser() != null
		  																			? fncVO_.getLoggedUser().getUserID()
		  																			: "" );

		  tplIpDocPrvtMovEntity.getBaseIpDocCallbackEntities().add( movEntity );
		}
	  }

	  TplIpDocPrvtMovEntity prvtMovEntity = new TplIpDocPrvtMovEntity(
																	   ipDocPrvtEntity,
																	   tplIpDocPrvtMovEntity.getBaseDocTransferEntities(),
																	   tplIpDocPrvtMovEntity.getBaseIpDocCallbackEntities(),
																	   TplIpDocPrvtMovEntity.C_OPERN_CODE_INSERT );

	  tplIpDocPrvtMovDAO.insert( prvtMovEntity );
	  
	  //Associa a conta do cliente no IP
	  TplCurAcctPrmntInstrMovDAO tplCurAcctPrmntInstrMovDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
	  TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;	  
	  	  
	  TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = new TplCurAcctPrmntInstrEntity();
	  tplCurAcctPrmntInstrEntity.getData().setLastUpdDate( new Date() );
	  tplCurAcctPrmntInstrEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID(): "" );
	  
	  tplCurAcctPrmntInstrEntity.getData().setCustNbr(ipDocPrvtEntity.getData().getCustNbr());
	  tplCurAcctPrmntInstrEntity.getData().setPrmntInstrCode(ipDocPrvtEntity.getData().getIpDocCode());
	  tplCurAcctPrmntInstrEntity.getData().setProdAcctCode(detailFncVO.getTo3ProductAccountEntity().getData().getProdAcctCode());
	  tplCurAcctPrmntInstrEntity.getData().setProdUnderAcctCode(detailFncVO.getTo3ProductAccountEntity().getData().getProdUnderAcctCode());
	  
	  tplCurAcctPrmntInstrMovEntity = new TplCurAcctPrmntInstrMovEntity(tplCurAcctPrmntInstrEntity,
																		BaseODSEntity.C_OPERN_CODE_INSERT );
	  
	  tplCurAcctPrmntInstrMovDAO.insert(tplCurAcctPrmntInstrMovEntity);    
	  
	}
  }

  //Atualiza os dados da instrução permanente
  public void update( BaseFncVO fncVO_ )
  {

	this.validateUpdate( fncVO_ );
	if ( !fncVO_.hasErrors() )
	{

	  IpDocPrvtDetailFncVO detailFncVO = ( IpDocPrvtDetailFncVO ) fncVO_;
	  TplIpDocPrvtEntity ipDocPrvtEntity = ( TplIpDocPrvtEntity ) detailFncVO.getBaseTplIpDocPrvtEntity();
	  ipDocPrvtEntity.getData().setLastUpdDate( new Date() );
	  ipDocPrvtEntity.getData().setLastUpdUserId(
												  fncVO_.getLoggedUser() != null
																				? fncVO_.getLoggedUser().getUserID()
																				: "" );

	  TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity = new TplIpDocPrvtMovEntity();
	  TplDocTransferEntity docTransferEntity = new TplDocTransferEntity();

	  //Retira da lista final os ítens que foram inseridos e excluídos na mesma
	  // operação - Dados de transferência
	  int docTransferList = detailFncVO.getInsertedDocTransfer().size();
	  Iterator docTransferIterator;

	  for ( int count = 0; count < docTransferList; count++ )
	  {

		docTransferIterator = detailFncVO.getSelectedDocTransferInGrid().iterator();
		int max = 0;

		while ( docTransferIterator.hasNext() )
		{
		  docTransferIterator.next();
		  if ( detailFncVO.getSelectedDocTransferInGrid().get( max ).equals(
																			 "S" )
			   && detailFncVO.getDeletedDocTransferInGrid().get( max ).equals(
																			   "S" ) )
		  {
			detailFncVO.getInsertedDocTransfer().remove( max );
			detailFncVO.getSelectedDocTransferInGrid().remove( max );
			detailFncVO.getDeletedDocTransferInGrid().remove( max );
			break;
		  }
		  else if ( detailFncVO.getSelectedDocTransferInGrid().get( max ).equals(
																				  "N" )
					&& detailFncVO.getDeletedDocTransferInGrid().get( max ).equals(
																					"N" ) )
		  {
			detailFncVO.getInsertedDocTransfer().remove( max );
			detailFncVO.getSelectedDocTransferInGrid().remove( max );
			detailFncVO.getDeletedDocTransferInGrid().remove( max );
			break;
		  }
		  max++;

		}
		docTransferList = detailFncVO.getInsertedDocTransfer().size();
	  }

	  //Retira da lista final os ítens que foram inseridos e excluídos na mesma
	  // operação - Dados do Callabck
	  int callbackList = detailFncVO.getInsertedIpDocCallback().size();
	  Iterator callbackIterator;
	  Date date = new Date();
	  TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();

	  for ( int count = 0; count < callbackList; count++ )
	  {

		callbackIterator = detailFncVO.getInsertedIpDocCallback().iterator();
		int max = 0;

		while ( callbackIterator.hasNext() )
		{
			TplIpDocCallbackEntity ipDocCallbackEntity =( TplIpDocCallbackEntity ) callbackIterator.next();
		  if ( detailFncVO.getSelectedCallBackInGrid().get( max ).equals( "S" )
			   && detailFncVO.getDeletedCallBackInGrid().get( max ).equals( "S" ) )
		  {
			detailFncVO.getInsertedIpDocCallback().remove( max );
			detailFncVO.getSelectedCallBackInGrid().remove( max );
			detailFncVO.getDeletedCallBackInGrid().remove( max );
			
		    //exclusao logica na TPL_CONTACT_CUST		    
		    TplContactCustEntity tplContactCustEntity = new TplContactCustEntity();
		    tplContactCustEntity.getData().setCtcNbr(ipDocCallbackEntity.getData().getCtcNbr());
		    tplContactCustEntity.getData().setCustNbr(ipDocCallbackEntity.getData().getCustNbr());
		    tplContactCustEntity.getData().setLastAuthDate(date);
		    tplContactCustEntity.getData().setLastAuthUserId(detailFncVO.getLoggedUser().getUserID());
		    tplContactCustEntity.getData().setLastUpdDate(date);
		    tplContactCustEntity.getData().setLastUpdUserId(detailFncVO.getLoggedUser().getUserID());
		    tplContactCustEntity.getData().setRecStatCode(BaseODSEntity.C_REC_STAT_CODE_INACTIVE);
		    tplContactCustDAO.delete(tplContactCustEntity);
		    
			break;
		  }
		  else if ( detailFncVO.getSelectedCallBackInGrid().get( max ).equals(
																			   "N" )
					&& detailFncVO.getDeletedCallBackInGrid().get( max ).equals(
																				 "N" ) )
		  {
			detailFncVO.getInsertedIpDocCallback().remove( max );
			detailFncVO.getSelectedCallBackInGrid().remove( max );
			detailFncVO.getDeletedCallBackInGrid().remove( max );
			break;
		  }
		  max++;

		}
		callbackList = detailFncVO.getInsertedIpDocCallback().size();
	  }
	  TplDocTransferDAO tplDocTransferDAO = ODSDAOFactory.getInstance().getTplDocTransferDAO();

	  for ( int i = 0; i < detailFncVO.getInsertedDocTransfer().size(); i++ )
	  {
		TplDocTransferMovEntity movEntity = new TplDocTransferMovEntity();

		docTransferEntity = ( TplDocTransferEntity ) detailFncVO.getInsertedDocTransfer().get(

		i );

		if ( docTransferEntity != null )
		{
			
		  movEntity.getData().setBeneNameText(docTransferEntity.getData().getBeneNameText());
		  
		  movEntity.getData().setBeneAcctTypeCode(docTransferEntity.getData().getBeneAcctTypeCode());
		  
		  movEntity.getData().setBeneCpfCnpjNbr(docTransferEntity.getData().getBeneCpfCnpjNbr());
		  
		  movEntity.getData().setBeneMainDestAcctInd(docTransferEntity.getData().getBeneMainDestAcctInd());
		  
		  movEntity.getData().setAgnBankCode(
											  docTransferEntity.getData().getAgnBankCode() );
		  movEntity.getData().setBrchCode(
										   docTransferEntity.getData().getBrchCode() );
		  movEntity.getData().setCurAcctNbr(
											 docTransferEntity.getData().getCurAcctNbr() );
		  movEntity.getData().setCustNbr(
										  docTransferEntity.getData().getCustNbr() );

		 
		  movEntity.getData().setIpDocCode(
											docTransferEntity.getData().getIpDocCode() );
		  movEntity.getData().setLastUpdDate(
											  docTransferEntity.getData().getLastUpdDate() );
		  movEntity.getData().setLastUpdUserId(
												docTransferEntity.getData().getLastUpdUserId() );
		  movEntity.getData().setOwnDestAcctInd(
												 docTransferEntity.getData().getOwnDestAcctInd() );
		  movEntity.getData().setTxnTypeCode(
											  docTransferEntity.getData().getTxnTypeCode() );

		  //Verifica qual ação setar o opern code
		  if ( detailFncVO.getSelectedDocTransferInGrid().get( i ).equals( "S" )
			   && detailFncVO.getDeletedDocTransferInGrid().get( i ).equals(
																			 "N" ) )
		  {
			( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
			 if ( movEntity.getData().getDocTransferCode() == null )
			  {
				movEntity.getData().setDocTransferCode(
														new BigInteger(
																		tplDocTransferDAO.getNextDocTransferCode().toString() ) );

			  }
		  }
		  else
		  {
			( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );
			movEntity.getData().setDocTransferCode(	docTransferEntity.getData(). getDocTransferCode());
		  }

		  ( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setLastUpdDate( new Date() );

		  ( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );

		  tplIpDocPrvtMovEntity.getBaseDocTransferEntities().add( movEntity );
		}
	  }
	  TplIpDocCallbackEntity ipDocCallbackEntity = new TplIpDocCallbackEntity();
	  TplIpDocCallbackDAO tplIpDocCallbackDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackDAO();

	  for ( int i = 0; i < detailFncVO.getInsertedIpDocCallback().size(); i++ )
	  {
		TplIpDocCallbackMovEntity movEntity = new TplIpDocCallbackMovEntity();

		ipDocCallbackEntity = ( TplIpDocCallbackEntity ) detailFncVO.getInsertedIpDocCallback().get(
																									 i );

		if ( ipDocCallbackEntity != null )
		{
		  movEntity.getData().setCtcNbr(
										 ipDocCallbackEntity.getData().getCtcNbr() );
		  movEntity.getData().setCustNbr(
										  ipDocCallbackEntity.getData().getCustNbr() );
		  
		  movEntity.getData().setIpCallbackCode(ipDocCallbackEntity.getData().getIpCallbackCode());
		  
		  if ( movEntity.getData().getIpCallbackCode() == null )
		  {
			movEntity.getData().setIpCallbackCode(
												   new BigInteger(
																   tplIpDocCallbackDAO.getNextIpCallBackCode().toString() ) );

		  }
		  movEntity.getData().setIpDocCode(
											ipDocCallbackEntity.getData().getIpDocCode() );
		  movEntity.getData().setLastUpdDate(
											  ipDocCallbackEntity.getData().getLastUpdDate() );
		  movEntity.getData().setLastUpdUserId(
												ipDocCallbackEntity.getData().getLastUpdUserId() );
		  //Verifica qual ação setar o opern code
		  if ( detailFncVO.getSelectedCallBackInGrid().get( i ).equals( "S" )
			   && detailFncVO.getDeletedCallBackInGrid().get( i ).equals( "N" ) )
		  {
			( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
		  }
		  else
		  {
			( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );
		  }
		  ( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setLastUpdDate( new Date() );

		  ( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );

		  tplIpDocPrvtMovEntity.getBaseIpDocCallbackEntities().add( movEntity );
		}
	  }

	  TplIpDocPrvtMovEntity prvtMovEntity = new TplIpDocPrvtMovEntity(
																	   ipDocPrvtEntity,
																	   tplIpDocPrvtMovEntity.getBaseDocTransferEntities(),
																	   tplIpDocPrvtMovEntity.getBaseIpDocCallbackEntities(),
																	   TplIpDocPrvtMovEntity.C_OPERN_CODE_UPDATE );

	  TplIpDocPrvtMovDAO tplIpDocPrvtMovDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtMovDAO();
	  tplIpDocPrvtMovDAO.insert( prvtMovEntity );
	  
	  //Associa a conta do cliente no IP
	  TplCurAcctPrmntInstrMovDAO tplCurAcctPrmntInstrMovDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
	  TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;	  
	  	  
	  TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = new TplCurAcctPrmntInstrEntity();
	  tplCurAcctPrmntInstrEntity.getData().setLastUpdDate( new Date() );
	  tplCurAcctPrmntInstrEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID(): "" );
	  
	  tplCurAcctPrmntInstrEntity.getData().setCustNbr(ipDocPrvtEntity.getData().getCustNbr());
	  tplCurAcctPrmntInstrEntity.getData().setPrmntInstrCode(ipDocPrvtEntity.getData().getIpDocCode());
	  tplCurAcctPrmntInstrEntity.getData().setProdAcctCode(detailFncVO.getTo3ProductAccountEntity().getData().getProdAcctCode());
	  tplCurAcctPrmntInstrEntity.getData().setProdUnderAcctCode(detailFncVO.getTo3ProductAccountEntity().getData().getProdUnderAcctCode());
	  
	  tplCurAcctPrmntInstrMovEntity = new TplCurAcctPrmntInstrMovEntity(tplCurAcctPrmntInstrEntity,
																		BaseODSEntity.C_OPERN_CODE_INSERT );
	  
	  tplCurAcctPrmntInstrMovDAO.insert(tplCurAcctPrmntInstrMovEntity);   
	  
	  detailFncVO.setInnerActionInd( false );
	  detailFncVO.setFromSearch( false );
	}
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {
	//this.validateDelete( fncVO_ );
	if ( !fncVO_.hasErrors() )
	{
	  IpDocPrvtDetailFncVO detailFncVO = ( IpDocPrvtDetailFncVO ) fncVO_;
	  TplIpDocPrvtEntity ipDocPrvtEntity = ( TplIpDocPrvtEntity ) detailFncVO.getBaseTplIpDocPrvtEntity();
	  ipDocPrvtEntity.getData().setLastUpdDate( new Date() );
	  ipDocPrvtEntity.getData().setLastUpdUserId(
												  fncVO_.getLoggedUser() != null
																				? fncVO_.getLoggedUser().getUserID()
																				: "" );

	  TplIpDocPrvtMovEntity tplIpDocPrvtMovEntity = new TplIpDocPrvtMovEntity();

	  TplDocTransferEntity docTransferEntity = new TplDocTransferEntity();
	  docTransferEntity.getData().setLastUpdDate( new Date() );
	  docTransferEntity.getData().setLastUpdUserId(
													fncVO_.getLoggedUser() != null
																				  ? fncVO_.getLoggedUser().getUserID()
																				  : "" );

	  for ( int i = 0; i < detailFncVO.getInsertedDocTransfer().size(); i++ )
	  {
		TplDocTransferMovEntity movEntity = new TplDocTransferMovEntity();

		docTransferEntity = ( TplDocTransferEntity ) detailFncVO.getInsertedDocTransfer().get(
																							   i );

		if ( docTransferEntity != null )
		{
		  movEntity.getData().setAgnBankCode(
											  docTransferEntity.getData().getAgnBankCode() );
		  movEntity.getData().setBrchCode(
										   docTransferEntity.getData().getBrchCode() );
		  movEntity.getData().setCurAcctNbr(
											 docTransferEntity.getData().getCurAcctNbr() );
		  movEntity.getData().setCustNbr(
										  docTransferEntity.getData().getCustNbr() );
		  movEntity.getData().setDocTransferCode(
												  docTransferEntity.getData().getDocTransferCode() );
		  movEntity.getData().setIpDocCode(
											docTransferEntity.getData().getIpDocCode() );
		  movEntity.getData().setLastUpdDate(
											  docTransferEntity.getData().getLastUpdDate() );
		  movEntity.getData().setLastUpdUserId(
												docTransferEntity.getData().getLastUpdUserId() );
		  movEntity.getData().setOwnDestAcctInd(
												 docTransferEntity.getData().getOwnDestAcctInd() );
		  movEntity.getData().setTxnTypeCode(
											  docTransferEntity.getData().getTxnTypeCode() );
											  
		  movEntity.getData().setBeneAcctTypeCode(docTransferEntity.getData().getBeneAcctTypeCode());		
		  movEntity.getData().setBeneCpfCnpjNbr(docTransferEntity.getData().getBeneCpfCnpjNbr());
		  movEntity.getData().setBeneMainDestAcctInd(docTransferEntity.getData().getBeneMainDestAcctInd());
		  movEntity.getData().setBeneNameText(docTransferEntity.getData().getBeneNameText());
		  							  

		  ( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );

		  ( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setLastUpdDate( docTransferEntity.getData().getLastUpdDate() );

		  ( ( TplDocTransferMovEntityVO ) movEntity.getData() ).setLastUpdUserId( docTransferEntity.getData().getLastUpdUserId() );

		  tplIpDocPrvtMovEntity.getBaseDocTransferEntities().add( movEntity );
		}
	  }

	  TplIpDocCallbackEntity ipDocCallbackEntity = new TplIpDocCallbackEntity();

	  ipDocCallbackEntity.getData().setLastUpdDate( new Date() );

	  ipDocCallbackEntity.getData().setLastUpdUserId(
													  fncVO_.getLoggedUser() != null
																					? fncVO_.getLoggedUser().getUserID()
																					: "" );

	  for ( int i = 0; i < detailFncVO.getInsertedIpDocCallback().size(); i++ )
	  {
		TplIpDocCallbackMovEntity movEntity = new TplIpDocCallbackMovEntity();

		ipDocCallbackEntity = ( TplIpDocCallbackEntity ) detailFncVO.getInsertedIpDocCallback().get(
																									 i );

		if ( ipDocCallbackEntity != null )
		{
		  movEntity.getData().setCtcNbr(
										 ipDocCallbackEntity.getData().getCtcNbr() );
		  movEntity.getData().setCustNbr(
										  ipDocCallbackEntity.getData().getCustNbr() );
		  movEntity.getData().setIpCallbackCode(
												 ipDocCallbackEntity.getData().getIpCallbackCode() );
		  movEntity.getData().setIpDocCode(
											ipDocCallbackEntity.getData().getIpDocCode() );
		  movEntity.getData().setLastUpdDate(
											  ipDocCallbackEntity.getData().getLastUpdDate() );
		  movEntity.getData().setLastUpdUserId(
												ipDocCallbackEntity.getData().getLastUpdUserId() );

		  ( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setOpernCode( BaseODSEntity.C_OPERN_CODE_DELETE );

		  ( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setLastUpdDate( ipDocCallbackEntity.getData().getLastUpdDate() );

		  ( ( TplIpDocCallbackMovEntityVO ) movEntity.getData() ).setLastUpdUserId( ipDocCallbackEntity.getData().getLastUpdUserId() );

		  tplIpDocPrvtMovEntity.getBaseIpDocCallbackEntities().add( movEntity );
		}
	  }

	  TplIpDocPrvtMovEntity prvtMovEntity = new TplIpDocPrvtMovEntity(
																	   ipDocPrvtEntity,
																	   tplIpDocPrvtMovEntity.getBaseDocTransferEntities(),
																	   tplIpDocPrvtMovEntity.getBaseIpDocCallbackEntities(),
																	   TplIpDocPrvtMovEntity.C_OPERN_CODE_DELETE );
	  
	  TplIpDocPrvtMovDAO tplIpDocPrvtMovDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtMovDAO();
	  tplIpDocPrvtMovDAO.insert( prvtMovEntity );
	  
      //Associa a conta do cliente no IP
	  TplCurAcctPrmntInstrMovDAO tplCurAcctPrmntInstrMovDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
	  TplCurAcctPrmntInstrMovEntity tplCurAcctPrmntInstrMovEntity;	  
	  	  
	  TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = new TplCurAcctPrmntInstrEntity();
	  tplCurAcctPrmntInstrEntity.getData().setLastUpdDate( new Date() );
	  tplCurAcctPrmntInstrEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID(): "" );
	  
	  tplCurAcctPrmntInstrEntity.getData().setCustNbr(ipDocPrvtEntity.getData().getCustNbr());
	  tplCurAcctPrmntInstrEntity.getData().setPrmntInstrCode(ipDocPrvtEntity.getData().getIpDocCode());
	  tplCurAcctPrmntInstrEntity.getData().setProdAcctCode(detailFncVO.getTo3ProductAccountEntity().getData().getProdAcctCode());
	  tplCurAcctPrmntInstrEntity.getData().setProdUnderAcctCode(detailFncVO.getTo3ProductAccountEntity().getData().getProdUnderAcctCode());
	  
	  tplCurAcctPrmntInstrMovEntity = new TplCurAcctPrmntInstrMovEntity(tplCurAcctPrmntInstrEntity,
																			BaseODSEntity.C_OPERN_CODE_DELETE );
	  
	  tplCurAcctPrmntInstrMovDAO.insert(tplCurAcctPrmntInstrMovEntity);    

	}
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
	IpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) fncVO_;

	// Validar Campos Obrigatórios
	/*if ( fncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
					   C_IP_DOC_CODE_NOT_NULL );
	}*/

	if ( fncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
					   C_CUST_NBR_NOT_NULL );
	}

	//Verifica se tem callback associado ao ip e documento de transferencia
	// associado a instrução permanente

	if ( !fncVO.hasErrors() )
	{
	  int findCallBackDeleted = 0;
	  int findDocTransferDeleted = 0;

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

	  if ( fncVO.getInsertedIpDocCallback().size() == findCallBackDeleted )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
						 C_INSERT_ASSOCIATION_ACTION, C_IP_DOC_CALLBACK );
	  }

	  if ( fncVO.getInsertedDocTransfer().size() == findDocTransferDeleted )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
						 C_INSERT_ACTION, C_DOC_TRANSFER );
	  }
	}

	/*if ( !fncVO.hasErrors() )
	{
	  //    Validar se já existe um registro com este codigo na
	  // "Current",
	  if ( this.existsActive( fncVO ) )
	  {
		fncVO_.addError( IpDocPrvtDetailFncVO.C_ERROR_DUPLICATE_PK );
	  }

	  // Validar se já existe movimento com este codigo, caso os
	  // campos
	  // obrigatórios estejam presentes
	  if ( this.existsInMovement( fncVO ) )
	  {
		fncVO_.addError( IpDocPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
	  }
	}*/
  }

  /*
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   */
  private boolean existsInMovement( IpDocPrvtDetailFncVO ipDocPrvtDetailFncVO_ )
  {
	TplIpDocPrvtMovDAO tplIpDocPrvtMovDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtMovDAO();

	TplIpDocPrvtMovEntity prvtMovEntity = new TplIpDocPrvtMovEntity();
	prvtMovEntity.getData().setCustNbr(
										ipDocPrvtDetailFncVO_.getBaseTplIpDocPrvtEntity().getData().getCustNbr() );
	prvtMovEntity.getData().setIpDocCode(
										  ipDocPrvtDetailFncVO_.getBaseTplIpDocPrvtEntity().getData().getIpDocCode() );

	return tplIpDocPrvtMovDAO.exists( prvtMovEntity );
  }

  /*
   * Verifica se já existe um registro na tabela de "Current" com o código
   * passado e o seu status é "Ativo"
   */
  private boolean existsActive( IpDocPrvtDetailFncVO ipDocPrvtDetailFncVO_ )
  {
	TplIpDocPrvtDAO tplIpDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();
	return tplIpDocPrvtDAO.existsActive( ( TplIpDocPrvtEntity ) ipDocPrvtDetailFncVO_.getBaseTplIpDocPrvtEntity() );
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
	IpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) fncVO_;

	int findCallBackDeleted = 0;
	int findDocTransferDeleted = 0;

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

	if ( fncVO.getInsertedIpDocCallback().size() == findCallBackDeleted )
	{
	  if ( fncVO.getInsertedIpDocCallback() == null
		   || fncVO.getInsertedIpDocCallback().isEmpty() )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
						 C_INSERT_ASSOCIATION_ACTION, C_IP_DOC_CALLBACK );
	  }
	  if ( fncVO.getInsertedDocTransfer().size() == findDocTransferDeleted )
	  {
		fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION,
						 C_INSERT_ACTION, C_DOC_TRANSFER );
	  }
	}
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
	IpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) fncVO_;

	if ( !fncVO.hasErrors() )
	{
	  // Validar se já existe movimento com este codigo, caso os
	  // campos
	  // obrigatórios estejam presentes
	  if ( this.existsInMovement( fncVO ) )
	  {
		fncVO_.addError( IpDocPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
	  }

	}
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
	IpDocPrvtDetailFncVO ipDocPrvtDetailFncVO = ( IpDocPrvtDetailFncVO ) fncVO_;
	this.load( ipDocPrvtDetailFncVO );

	BaseIpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) fncVO_;
	//Seta o resultado da busca no entity específico no fncVO.
	loadCustFullNameText( fncVO );
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
	IpDocPrvtDetailFncVO ipDocPrvtDetailFncVO = ( IpDocPrvtDetailFncVO ) fncVO_;	
	BaseIpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) fncVO_;	
	
	if(ipDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().getCurAcctNbr() == null && 
	   ipDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().getInvestCurAcctNbr() == null){		
		  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
							 "Conta Corrente ou Conta CCI");
	}
	
	if(!fncVO_.hasErrors()){
		
		if ( !ipDocPrvtDetailFncVO.isInnerActionInd()
				 && !ipDocPrvtDetailFncVO.isFromSearch() )
			{
	  
			  ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
			  To3ProductAccountDAO productAccountDAO = odsDAOFactory.getTo3ProductAccountDAO();
			  BaseTo3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity();		
				
			  productAccountEntity.getData().setCurAcctNbr(ipDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().getCurAcctNbr());
			  productAccountEntity.getData().setInvestCurAcctNbr((ipDocPrvtDetailFncVO.getTo3ProductAccountEntity().getData().getInvestCurAcctNbr()));
				
			  try{
				//Recupera a chave da conta produto através da conta corrente ou conta CCI.
				productAccountEntity = productAccountDAO.findByCurAcct(productAccountEntity);
				ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setCustNbr(productAccountEntity.getData().getCustNbr() );
				loadCustFullNameText( ipDocPrvtDetailFncVO );
				ipDocPrvtDetailFncVO.setTo3ProductAccountEntity(productAccountEntity);
				
			  }
			  catch( NoRowsReturnedException exception ){
				productAccountEntity = null;
			  }
				
			  if(productAccountEntity == null){
				fncVO_.addError(BaseODSFncVO.C_ERROR_REG_NOT_EXISTS,"Conta Corrente ou Conta CCI");					  
			  }

			  if(!fncVO_.hasErrors()){
		
				TplIpDocPrvtDAO ipDocPrvtDAO = odsDAOFactory.getTplIpDocPrvtDAO();				
	  
				TplDocTransferDAO docTransferDAO = odsDAOFactory.getTplDocTransferDAO();
		
				ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setIpDocText("" );
				ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setIpInvstCurAcctInd("" );
				ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setLastUpdDate(null );
				ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().setLastUpdUserId(null );
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setAgnBankCode(null );
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBrchCode(null );
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setCurAcctNbr(null );
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setTxnTypeCode(null );
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setOwnDestAcctInd("" );
	  
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneAcctTypeCode("");
	  
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneMainDestAcctInd("");
	  
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneNameText("");
	  
				ipDocPrvtDetailFncVO.getBaseTplDocTransferEntity().getData().setBeneCpfCnpjNbr("");
	  																							  
				ipDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity().getData().setCtcNbr(null );
				ipDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText("");
				ipDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText_2("");
				ipDocPrvtDetailFncVO.getTplContactCustEntity().getData().setFullNameText_3("");
				ipDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneNbr(null);
				ipDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneDddCode(null);
				ipDocPrvtDetailFncVO.getTplContactCustEntity().getData().setPhoneExtnNbr(null);
	  
				//ipDocPrvtDetailFncVO.setCustFullNameText( "" );
				ipDocPrvtDetailFncVO.getInsertedDocTransfer().clear();
				ipDocPrvtDetailFncVO.getInsertedIpDocCallback().clear();
				loadDomains( ipDocPrvtDetailFncVO );
				//fncVO.getBaseTplIpDocPrvtEntity().getData().setIpDocCode(null);
			  }
	  
	  
			}	
			
			ipDocPrvtDetailFncVO.setFromSearch( false );
			ipDocPrvtDetailFncVO.setInnerActionInd( false );

		
	}
	
	
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
	BaseIpDocPrvtDetailFncVO baseIpDocPrvtDetailFncVO = ( BaseIpDocPrvtDetailFncVO ) fncVO_;

	loadContactFullNameText( baseIpDocPrvtDetailFncVO );
	if ( !baseIpDocPrvtDetailFncVO.isInnerActionInd()
		 && !baseIpDocPrvtDetailFncVO.isFromSearch() )
	{
	  if ( this.existsInMovement( ( IpDocPrvtDetailFncVO ) baseIpDocPrvtDetailFncVO ) )
	  {
		fncVO_.addError( IpDocPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
	  }
	  else
	  {
		this.load( ( IpDocPrvtDetailFncVO ) baseIpDocPrvtDetailFncVO );
		loadCustFullNameText( ( BaseIpDocPrvtDetailFncVO ) baseIpDocPrvtDetailFncVO );
	  }
	}

	baseIpDocPrvtDetailFncVO.setInnerActionInd( false );
	baseIpDocPrvtDetailFncVO.setFromSearch( false );
	
	this.loadAccount(baseIpDocPrvtDetailFncVO);

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {	
  	
	if ( this.existsInMovement( ( IpDocPrvtDetailFncVO ) fncVO_ ) )
	{
	  fncVO_.addError( IpDocPrvtDetailFncVO.C_ERROR_IN_MOVEMENT );
	}
	else
	{
	  this.load( ( IpDocPrvtDetailFncVO ) fncVO_ );
	  loadCustFullNameText( ( IpDocPrvtDetailFncVO ) fncVO_ );
	  this.loadAccount(fncVO_);
	}
  	
	
  }

  public void insertDocTransfer( BaseFncVO baseFncVO_ )
  {
	baseFncVO_.clearErrors();
	baseFncVO_.clearMessages();

	this.validateInsertDocTransfer( baseFncVO_ );

	IpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) baseFncVO_;

	if ( !baseFncVO_.hasErrors() )
	{

	  TplDocTransferEntity tplDocTransferEntity = new TplDocTransferEntity(
																			fncVO.getBaseTplDocTransferEntity() );

	  TplDocTransferDAO tplDocTransferDAO = ODSDAOFactory.getInstance().getTplDocTransferDAO();
	  /*tplDocTransferEntity.getData().setDocTransferCode(
														 new BigInteger(
																		 tplDocTransferDAO.getNextDocTransferCode().toString() ) );*/
																		 
	  fncVO.getBaseTplDocTransferEntity().getData().setDocTransferCode( new BigInteger(tplDocTransferDAO.getNextDocTransferCode().toString() ) );	
	  fncVO.getInsertedDocTransfer().add(fncVO.getBaseTplDocTransferEntity() );
	  tplDocTransferEntity = new TplDocTransferEntity();
	  fncVO.setBaseTplDocTransferEntity( tplDocTransferEntity );
	  fncVO.getDeletedDocTransferInGrid().add( "N" );
	  fncVO.getSelectedDocTransferInGrid().add( "S" );

	}

	fncVO.setInnerActionInd( true );
  }

  public void insertCallback( BaseFncVO baseFncVO_ )
  {
	baseFncVO_.clearErrors();
	baseFncVO_.clearMessages();

	this.validateInsertCallback( baseFncVO_ );

	IpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) baseFncVO_;

	if ( !baseFncVO_.hasErrors() )
	{
		
		TplContactCustDAO tplContactCustDAO = ODSDAOFactory.getInstance().getTplContactCustDAO();		
		TplContactCustEntity contactCustEntity = new TplContactCustEntity();

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
	

	  TplIpDocCallbackEntity tplIpDocCallbackEntity = new TplIpDocCallbackEntity(
																				  fncVO.getBaseTplIpDocCallbackEntity() );

	  fncVO.getBaseTplIpDocCallbackEntity().getData().setCtcNbr(contactCustEntity.getData().getCtcNbr());
	  fncVO.getInsertedIpDocCallback().add(
											fncVO.getBaseTplIpDocCallbackEntity() );

	  tplIpDocCallbackEntity = new TplIpDocCallbackEntity();

	  fncVO.setBaseTplIpDocCallbackEntity( tplIpDocCallbackEntity );
	  fncVO.getSelectedCallBackInGrid().add( "S" );
	  fncVO.getDeletedCallBackInGrid().add( "N" );

	}

	fncVO.setInnerActionInd( true );
  }

  private void validateInsertDocTransfer( BaseFncVO fncVO_ )
  {
	IpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) fncVO_;

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
	
	
	/*if ( fncVO.getOwnDestAcctIndDomain() == null
		 && fncVO.getOwnDestAcctIndDomain().equals( "" ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
					   C_OWN_DEST_ACCT_IND );
	}*/

	/*if ( fncVO.getTxnTypeCodeDomain() == null
		 && fncVO.getTxnTypeCodeDomain().equals( "" ) )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD, C_TXN_TYPE_CODE );
	}*/

	if ( !fncVO.hasErrors() )
	{
	  if ( !super.existsAgnBankCode( fncVO ) )
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

	  /*if ( !fncVO.hasErrors() )
	  {

		boolean existsCurrent = fncVO.getBaseTplIpDocPrvtEntity().getBaseDocTransferEntities().contains(
																										 fncVO.getBaseTplDocTransferEntity() );
		boolean existsInserted = fncVO.getInsertedDocTransfer().contains(
																		  fncVO.getBaseTplDocTransferEntity() );

		if ( existsCurrent || existsInserted )
		{
		  fncVO_.addError( BaseODSFncVO.C_ERROR_DUPLICATE_ASSOCIATION,
						   C_DOC_TRANSFER );
		}
	  }*/
	}
  }

  private void validateInsertCallback( BaseFncVO fncVO_ )
  {
	IpDocPrvtDetailFncVO fncVO = ( IpDocPrvtDetailFncVO ) fncVO_;

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
	
	
	
	/*if ( fncVO.getBaseTplIpDocCallbackEntity().getData().getCtcNbr() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
					   C_CTC_NBR_CODE_NOT_NULL );
	}*/

	/*if ( fncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() == null )
	{
	  fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
					   C_CUST_NBR_CODE_NOT_NULL );
	}*/

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
   * @see com.citibank.ods.modules.client.ipdocprvt.functionality.BaseIpDocPrvtDetailFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
	super.load( fncVO_ );
	IpDocPrvtDetailFncVO ipDocPrvtDetailFncVO = ( IpDocPrvtDetailFncVO ) fncVO_;

	//Método que carrega o nome do cliente
	loadCustFullNameText( ipDocPrvtDetailFncVO );
	TplIpDocCallbackDAO ipDocCallbackDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackDAO();
	TplDocTransferDAO docTransferDAO = ODSDAOFactory.getInstance().getTplDocTransferDAO();

	ipDocPrvtDetailFncVO.setInsertedDocTransfer( new ArrayList() );
	ipDocPrvtDetailFncVO.setInsertedIpDocCallback( new ArrayList() );

	ipDocPrvtDetailFncVO.setInsertedIpDocCallback( ipDocCallbackDAO.findByPK(
																			  ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode(),
																			  ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() ) );

	ipDocPrvtDetailFncVO.setInsertedDocTransfer( docTransferDAO.findByPK(
																		  ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode(),
																		  ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() ) );

  }
  
  public void loadAccount(BaseFncVO fncVO_)
  {
  
    TplCurAcctPrmntInstrDAO tplCurAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();
    To3ProductAccountDAO productAccountDAO = ODSDAOFactory.getInstance().getTo3ProductAccountDAO();	
  	
    IpDocPrvtDetailFncVO ipDocPrvtDetailFncVO = ( IpDocPrvtDetailFncVO ) fncVO_;	
    TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = (TplCurAcctPrmntInstrEntity) tplCurAcctPrmntInstrDAO.findById(ipDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode());
	
    BaseTo3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity();
    productAccountEntity.getData().setProdAcctCode(tplCurAcctPrmntInstrEntity.getData().getProdAcctCode());
    productAccountEntity.getData().setProdUnderAcctCode(tplCurAcctPrmntInstrEntity.getData().getProdUnderAcctCode());
    productAccountEntity = productAccountDAO.findByPK(productAccountEntity);	
	
    ipDocPrvtDetailFncVO.setTo3ProductAccountEntity(productAccountEntity);
  	
  }


}