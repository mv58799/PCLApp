package com.citibank.ods.modules.client.ipdocprvt.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.functionality.ODSHistoryDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplDocTransferHistEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackHistEntity;
import com.citibank.ods.entity.pl.valueobject.TplIpDocPrvtHistEntityVO;
import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocPrvtDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtHistoryDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtDetailFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtHistoryDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferHistDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackHistDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.ipdocprvt.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class IpDocPrvtHistoryDetailFnc extends BaseIpDocPrvtDetailFnc implements
	ODSHistoryDetailFnc
{

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.modules.client.ipdocprvt.functionality.BaseIpDocPrvtDetailFnc#getDAO()
   */
  protected BaseTplIpDocPrvtDAO getDAO()
  {
	ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
	TplIpDocPrvtHistDAO tplIpDocPrvtHistDAO = odsDAOFactory.getTplIpDocPrvtHistDAO();
	return tplIpDocPrvtHistDAO;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
	return new IpDocPrvtHistoryDetailFncVO();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSHistoryDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
	this.load( fncVO_ );
	super.loadCustFullNameText( ( BaseIpDocPrvtDetailFncVO ) fncVO_ );
  }

  /**
   *  
   */
  public void load( BaseFncVO fncVO_ )
  {
	super.load( fncVO_ );
	IpDocPrvtHistoryDetailFncVO ipDocPrvtHistoryDetailFncVO = ( IpDocPrvtHistoryDetailFncVO ) fncVO_;
	TplIpDocCallbackHistDAO ipDocCallbackHistDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackHistDAO();
	TplDocTransferHistDAO docTransferHistDAO = ODSDAOFactory.getInstance().getTplDocTransferHistDAO();

	ipDocPrvtHistoryDetailFncVO.getBaseTplIpDocPrvtEntity().setBaseIpDocCallbackEntities(
																						  ipDocCallbackHistDAO.findByPK(
																														 ipDocPrvtHistoryDetailFncVO.getBaseTplDocTransferEntity().getData().getIpDocCode(),
																														 ipDocPrvtHistoryDetailFncVO.getBaseTplDocTransferEntity().getData().getCustNbr() ) );

	ipDocPrvtHistoryDetailFncVO.getBaseTplIpDocPrvtEntity().setBaseDocTransferEntities(
																						docTransferHistDAO.findByPK(
																													 ipDocPrvtHistoryDetailFncVO.getBaseTplDocTransferEntity().getData().getIpDocCode(),
																													 ipDocPrvtHistoryDetailFncVO.getBaseTplDocTransferEntity().getData().getCustNbr() ) );

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

	BaseTplIpDocPrvtEntity baseTplIpDocPrvtEntity = baseIpDocPrvtDetailFncVO.getBaseTplIpDocPrvtEntity();
	BaseTplDocTransferEntity baseTplDocTransferEntity = baseIpDocPrvtDetailFncVO.getBaseTplDocTransferEntity();

	ArrayList ipDocTransferEntities = baseTplIpDocPrvtEntity.getBaseDocTransferEntities();
	Iterator ipDocTransferEntitiesIt = ipDocTransferEntities.iterator();

	while ( ipDocTransferEntitiesIt.hasNext() )
	{
	  baseTplDocTransferEntity = ( TplDocTransferHistEntity ) ipDocTransferEntitiesIt.next();

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
	}

	String[] domainsDocTransferCodeArray = new String[ domainsDocTransferCodeList.size() ];
	String[] domainsAgnBankCodeArray = new String[ domainsAgnBankCodeList.size() ];
	String[] domainsAgnBankNameArray = new String[ domainsAgnBankCodeList.size() ];
	String[] domainsBrchCodeArray = new String[ domainsBrchCodeList.size() ];
	String[] domainsBrchNameArray = new String[ domainsBrchCodeList.size() ];
	String[] domainsCurAcctCodeArray = new String[ domainsCurAcctCodeList.size() ];
	String[] domainsOwnDestAcctIndArray = new String[ domainsOwnDestAcctIndList.size() ];
	String[] domainsTxnTypeCodeArray = new String[ domainsTxnTypeCodeList.size() ];

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
	  domainsAgnBankNameArray[ i ] = buildBankName( domainsAgnBankCodeList.get(
																				i ).toString() );
	  domainsBrchCodeArray[ i ] = domainsBrchCodeList.get( i ).toString();
	  domainsBrchNameArray[ i ] = buildBranchName(
												   domainsBrchCodeList.get( i ).toString(),
												   domainsAgnBankCodeList.get(
																			   i ).toString() );
	}

	baseIpDocPrvtDetailForm.setDomainsDocTransferCode( domainsDocTransferCodeArray );
	baseIpDocPrvtDetailForm.setDomainsAgnBankCode( domainsAgnBankCodeArray );
	baseIpDocPrvtDetailForm.setDomainsAgnBankName( domainsAgnBankNameArray );
	baseIpDocPrvtDetailForm.setDomainsBrchCode( domainsBrchCodeArray );
	baseIpDocPrvtDetailForm.setDomainsBrchName( domainsBrchNameArray );
	baseIpDocPrvtDetailForm.setDomainsCurAcctCode( domainsCurAcctCodeArray );
	baseIpDocPrvtDetailForm.setDomainsOwnDestAcctInd( domainsOwnDestAcctIndArray );
	baseIpDocPrvtDetailForm.setDomainsTxnTypeCode( domainsTxnTypeCodeArray );

	baseIpDocPrvtDetailForm.setDocTransferCode( "" );
	baseIpDocPrvtDetailForm.setAgnBankCode( "" );
	baseIpDocPrvtDetailForm.setBrchCode( "" );
	baseIpDocPrvtDetailForm.setCurAcctNbr( "" );
	baseIpDocPrvtDetailForm.setOwnDestAcctInd( "" );
	baseIpDocPrvtDetailForm.setTxnTypeCode( "" );

	//  Atualização do grid de Callback
	ArrayList domainsFullNameTextList = new ArrayList();
	ArrayList domainsCtcNbrList = new ArrayList();

	BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity = baseIpDocPrvtDetailFncVO.getBaseTplIpDocCallbackEntity();

	ArrayList ipDocCallbackEntities = baseTplIpDocPrvtEntity.getBaseIpDocCallbackEntities();
	Iterator ipDocCallbackEntitiesIt = ipDocCallbackEntities.iterator();

	while ( ipDocCallbackEntitiesIt.hasNext() )
	{
	  baseTplIpDocCallbackEntity = ( TplIpDocCallbackHistEntity ) ipDocCallbackEntitiesIt.next();

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
	String[] domainsPhoneArray = new String[ domainsCtcNbrList.size() ];

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
	}

	baseIpDocPrvtDetailForm.setDomainsCtcNbr( domainsCtcNbrArray );
	baseIpDocPrvtDetailForm.setDomainsFullNameText( domainsFullNameTextArray );

	baseIpDocPrvtDetailForm.setCtcNbr( "" );
	baseIpDocPrvtDetailForm.setFullNameText( "" );

	IpDocPrvtHistoryDetailFncVO ipDocPrvtHistoryDetailFncVO = ( IpDocPrvtHistoryDetailFncVO ) fncVO_;
	IpDocPrvtHistoryDetailForm ipDocPrvtHistoryDetailForm = ( IpDocPrvtHistoryDetailForm ) form_;
	TplIpDocPrvtHistEntityVO tplIpDocPrvtHistEntityVO = ( TplIpDocPrvtHistEntityVO ) ipDocPrvtHistoryDetailFncVO.getBaseTplIpDocPrvtEntity().getData();

	String lastUserId = ( ipDocPrvtHistoryDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdUserId() != null
						  && ipDocPrvtHistoryDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdUserId().length() > 0
																															  ? ipDocPrvtHistoryDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdUserId()
																															  : null );

	String lastAuthUserId = ( tplIpDocPrvtHistEntityVO.getLastAuthUserId() != null
							  && tplIpDocPrvtHistEntityVO.getLastAuthUserId().length() > 0
																						  ? tplIpDocPrvtHistEntityVO.getLastAuthUserId()
																						  : null );
	String lastUpdDate = ( ipDocPrvtHistoryDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdDate() != null
																													 ? formatDateTime( ipDocPrvtHistoryDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getLastUpdDate() )
																													 : null );
	String lastAuthUpdDate = ( tplIpDocPrvtHistEntityVO.getLastAuthDate() != null
																				 ? formatDateTime( tplIpDocPrvtHistEntityVO.getLastAuthDate() )
																				 : null );
	String ipRefDate = ( tplIpDocPrvtHistEntityVO.getIpDocRefDate() != null
																		   ? formatDateTime( tplIpDocPrvtHistEntityVO.getIpDocRefDate() )
																		   : null );
	ipDocPrvtHistoryDetailForm.setLastAuthDate( lastAuthUpdDate );
	ipDocPrvtHistoryDetailForm.setLastAuthUserId( lastAuthUserId );
	ipDocPrvtHistoryDetailForm.setLastUpdDate( lastUpdDate );
	ipDocPrvtHistoryDetailForm.setLastUpdUserId( lastUserId );
	ipDocPrvtHistoryDetailForm.setIpDocRefDate( ipRefDate );

  } /*
	 * (non-Javadoc)
	 * @see com.citibank.ods.modules.client.ipdocprvt.functionality.BaseIpDocPrvtDetailFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
	 *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
	super.updateFncVOFromForm( form_, fncVO_ );

	IpDocPrvtHistoryDetailFncVO fncVO = ( IpDocPrvtHistoryDetailFncVO ) fncVO_;
	IpDocPrvtHistoryDetailForm form = ( IpDocPrvtHistoryDetailForm ) form_;

	String lastUpdUserId = ( form.getLastUpdUserId() != null
															? form.getLastUpdUserId()
															: null );

	SimpleDateFormat formatter = new SimpleDateFormat(
													   FuncionalityFormatKeys.C_FORMAT_TIMESTAMP );
	Date ipDocRefDate;

	try
	{

	  ipDocRefDate = ( form.getIpDocRefDate() != null
													 ? formatter.parse( form.getIpDocRefDate() )
													 : null );

	}
	catch ( ParseException e_ )
	{

	  ipDocRefDate = null;

	}

	fncVO.setLastUpdUserId( lastUpdUserId );
	TplIpDocPrvtHistEntityVO entityVO = ( TplIpDocPrvtHistEntityVO ) fncVO.getBaseTplIpDocPrvtEntity().getData();
	entityVO.setIpDocRefDate( ipDocRefDate );

  }

  private String formatDateTime( Date date_ )
  {
	DateFormat dateFormat = new SimpleDateFormat(
												  Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
	return dateFormat.format( date_ );
  }

}