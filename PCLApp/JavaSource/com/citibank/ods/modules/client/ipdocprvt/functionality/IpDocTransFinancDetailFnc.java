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
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocTransFinancEntity;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplContactCustEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplDocTransferEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.TplIpDocTransFinancEntity;
import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocTransFinancDetailForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocTransFinancDetailFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocTransFinancDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplIpDocTransFinancDAO;
import com.citibank.ods.persistence.pl.dao.To3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.TplContactCustDAO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.TplDocTransferDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocCallbackDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocTransFinancDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class IpDocTransFinancDetailFnc	extends BaseIpDocTransFinancDetailFnc implements ODSDetailFnc {

	/* (non-Javadoc)
	* @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	*/
	public void insert(BaseFncVO fncVO_) {
		
		fncVO_.clearErrors();
		fncVO_.clearMessages();
		
		IpDocTransFinancDetailFncVO fncVO =	(IpDocTransFinancDetailFncVO) fncVO_;
		
		this.validateInsert(fncVO);
		
		if(!fncVO.hasErrors()){			
			
			TplIpDocTransFinancDAO ipDocTransFinancDAO = ODSDAOFactory.getInstance().getTplIpDocTransFinancDAO();			
			
			fncVO.getBaseTplIpDocTransFinancEntity().getData().setPrmntInstrTrfSeqNbr(new BigInteger(ipDocTransFinancDAO.getSeqNextVal().toString()));			
			fncVO.getBaseTplIpDocTransFinancEntity().getData().setPrmntInstrCode(fncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode());
			fncVO.getBaseTplIpDocTransFinancEntity().getData().setPrmntInstrTrfDataCode(fncVO.getBaseTplDocTransferEntity().getData().getDocTransferCode());
			fncVO.getBaseTplIpDocTransFinancEntity().getData().setCustNbr(fncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr());
			ipDocTransFinancDAO.insert((TplIpDocTransFinancEntity)fncVO.getBaseTplIpDocTransFinancEntity());			
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void update(BaseFncVO fncVO_) {
		

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void delete(BaseFncVO fncVO_) {

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateInsert(BaseFncVO fncVO_) {
		
		IpDocTransFinancDetailFncVO fncVO =	(IpDocTransFinancDetailFncVO) fncVO_;

		// Validar Campos Obrigatórios
		if (fncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfAmtNbr() == null) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,C_TRF_AMT_NBR_NOT_NULL);
		}
		
		if (fncVO.getBaseTplIpDocTransFinancEntity().getData().getChnnlAttdText() == null) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,C_CHNNL_ATTD_TEXT_NOT_NULL);
		}

		if (fncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfDate()	== null) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,C_TRF_DATE_NOT_NULL);
		}
				
		if (fncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfAcctType()	== null) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,C_TRF_ACCT_TYPE_NOT_NULL);
		}

	}

	/*
	 * Verifica se já existe um registro na tabela de "Current" com o código
	 * passado e o seu status é "Ativo"
	 */
	private boolean existsActive(IpDocTransFinancDetailFncVO ipDocTransFinancDetailFncVO_) {
		TplIpDocTransFinancDAO tplIpDocTransFinancDAO =
			ODSDAOFactory.getInstance().getTplIpDocTransFinancDAO();
		return tplIpDocTransFinancDAO.existsActive(
			(TplIpDocTransFinancEntity) ipDocTransFinancDetailFncVO_
				.getBaseTplIpDocTransFinancEntity());
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateUpdate(BaseFncVO fncVO_) {

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void validateDelete(BaseFncVO fncVO_) {

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForConsult(BaseFncVO fncVO_) {
        //Casting do fncVO específico
		BaseIpDocTransFinancDetailFncVO ipDocTransFinancDetailFncVO = (BaseIpDocTransFinancDetailFncVO) fncVO_;
		
		String tpOperacao = "";		 
		double trfAmtNbr = ipDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().getTrfAmtNbr().doubleValue();
		
		if(ipDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode().longValue() == 745 ){
			tpOperacao = "TRANSFERENCIA";
		}
		else{	
		  if(trfAmtNbr >= 3000.00){
		    tpOperacao = "TED";
		  }
		  else{
		    tpOperacao = "DOC";
		  }
			
		  if(ipDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode().equals("C")){
		    tpOperacao = "TED C";				
		  }
			
		  if(ipDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode().equals("C") 
		  && ipDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneMainDestAcctInd().equals("S")){
				
		    tpOperacao = "TED D";			  	
		  }
		  else if(ipDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneMainDestAcctInd().equals("S")
		          && ipDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode().equals("P")){
		    tpOperacao = tpOperacao + " D";
		  }
		  else if(ipDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getBeneAcctTypeCode().equals("P")){
		    tpOperacao = tpOperacao + " C";				
		  }	
	    }
	    		
		ipDocTransFinancDetailFncVO.setTpOperacao(tpOperacao);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForInsert(BaseFncVO fncVO_) {
		BaseIpDocTransFinancDetailFncVO baseIpDocTransFinancDetailFncVO = (BaseIpDocTransFinancDetailFncVO) fncVO_;
		
		this.load(baseIpDocTransFinancDetailFncVO);
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().setChnnlAttdText("");
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().setTrfAcctType(null);
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().setTrfAmtNbr(null);
		baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity().getData().setTrfDate(null);
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForUpdate(BaseFncVO fncVO_) {

	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void loadForDelete(BaseFncVO fncVO_) {

	}
	public void insertDocTransfer(BaseFncVO baseFncVO_) {
		baseFncVO_.clearErrors();
		baseFncVO_.clearMessages();

		this.validateInsertDocTransfer(baseFncVO_);

		IpDocTransFinancDetailFncVO fncVO =
			(IpDocTransFinancDetailFncVO) baseFncVO_;

		if (!baseFncVO_.hasErrors()) {

			TplDocTransferEntity tplDocTransferEntity =
				new TplDocTransferEntity(fncVO.getBaseTplDocTransferEntity());

			TplDocTransferDAO tplDocTransferDAO =
				ODSDAOFactory.getInstance().getTplDocTransferDAO();
			tplDocTransferEntity.getData().setDocTransferCode(
				new BigInteger(
					tplDocTransferDAO.getNextDocTransferCode().toString()));

			fncVO.getInsertedDocTransfer().add(
				fncVO.getBaseTplDocTransferEntity());
			tplDocTransferEntity = new TplDocTransferEntity();
			fncVO.setBaseTplDocTransferEntity(tplDocTransferEntity);
			fncVO.getDeletedDocTransferInGrid().add("N");
			fncVO.getSelectedDocTransferInGrid().add("S");

		}

		fncVO.setInnerActionInd(true);
	}

	private void validateInsertDocTransfer(BaseFncVO fncVO_) {
		IpDocTransFinancDetailFncVO fncVO =
			(IpDocTransFinancDetailFncVO) fncVO_;

		if (fncVO.getBaseTplDocTransferEntity().getData().getAgnBankCode()
			== null) {
			fncVO_.addError(
				BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
				C_AGN_BANK_CODE_NOT_NULL);
		}

		if (fncVO.getBaseTplDocTransferEntity().getData().getBrchCode()
			== null) {
			fncVO_.addError(
				BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
				C_BRCH_CODE_NOT_NULL);
		}

		if (fncVO.getBaseTplDocTransferEntity().getData().getCurAcctNbr()
			== null) {
			fncVO_.addError(
				BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
				C_CUR_ACCT_NBR_NOT_NULL);
		}
		if (fncVO.getOwnDestAcctIndDomain() == null
			&& fncVO.getOwnDestAcctIndDomain().equals("")) {
			fncVO_.addError(
				BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
				C_OWN_DEST_ACCT_IND);
		}

		if (fncVO.getTxnTypeCodeDomain() == null
			&& fncVO.getTxnTypeCodeDomain().equals("")) {
			fncVO_.addError(
				BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
				C_TXN_TYPE_CODE);
		}

	}

	/*
	* (non-Javadoc)
	* @see com.citibank.ods.modules.client.ipdocprvt.functionality.BaseIpDocPrvtDetailFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	*/
	public void load(BaseFncVO fncVO_) {

		//Casting do fncVO específico
		BaseIpDocTransFinancDetailFncVO ipDocTransFinancDetailFncVO = (BaseIpDocTransFinancDetailFncVO) fncVO_;

		//Instacia os DAOs
		TplIpDocPrvtDAO ipDocPrvtDAO = ODSDAOFactory.getInstance().getTplIpDocPrvtDAO();
		TplIpDocCallbackDAO ipDocCallbackDAO = ODSDAOFactory.getInstance().getTplIpDocCallbackDAO();
		
		//Busca os dados principais na IpDocPrvt		
		BaseTplIpDocPrvtEntity tplIpDocPrvtEntity = null;
		
		tplIpDocPrvtEntity = (BaseTplIpDocPrvtEntity) ipDocPrvtDAO.find(ipDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity());
		ipDocTransFinancDetailFncVO.setBaseTplIpDocPrvtEntity(tplIpDocPrvtEntity);

		//Busca os dados do cliente
		loadCustFullNameText(ipDocTransFinancDetailFncVO);
		
		//Busca os dados da conta do cliente
		this.loadAccount(ipDocTransFinancDetailFncVO);
		
		//Busca os dados do beneficiário
		this.loadDocTransfer(ipDocTransFinancDetailFncVO);
		
		//Busca a lista d Callback relaciona ao IP
		ipDocTransFinancDetailFncVO.setInsertedDocTransfer( new ArrayList() );
		ipDocTransFinancDetailFncVO.setInsertedIpDocCallback( ipDocCallbackDAO.findByPK(ipDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode(),
																						ipDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr() ) );	

		
	}
	
	public void loadAccount(BaseFncVO fncVO_)
    {
  
		TplCurAcctPrmntInstrDAO tplCurAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();
		To3ProductAccountDAO productAccountDAO = ODSDAOFactory.getInstance().getTo3ProductAccountDAO();	
  	
		BaseIpDocTransFinancDetailFncVO ipDocTransFinancDetailFncVO = (BaseIpDocTransFinancDetailFncVO) fncVO_;	
		TplCurAcctPrmntInstrEntity tplCurAcctPrmntInstrEntity = (TplCurAcctPrmntInstrEntity) tplCurAcctPrmntInstrDAO.findById(ipDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode());
	
		BaseTo3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity();
		productAccountEntity.getData().setProdAcctCode(tplCurAcctPrmntInstrEntity.getData().getProdAcctCode());
		productAccountEntity.getData().setProdUnderAcctCode(tplCurAcctPrmntInstrEntity.getData().getProdUnderAcctCode());
		productAccountEntity = productAccountDAO.find(productAccountEntity);	
	
		ipDocTransFinancDetailFncVO.setTo3ProductAccountEntity(productAccountEntity);
  	
    }
    
    public void loadDocTransfer(BaseFncVO fncVO_)    
    {
		BaseIpDocTransFinancDetailFncVO ipDocTransFinancDetailFncVO = (BaseIpDocTransFinancDetailFncVO) fncVO_;
		TplDocTransferDAO docTransferDAO = ODSDAOFactory.getInstance().getTplDocTransferDAO();
		
		BaseTplDocTransferEntity tplDocTransferEntity = new TplDocTransferEntity(); 
		tplDocTransferEntity.getData().setCustNbr(ipDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getCustNbr());
		tplDocTransferEntity.getData().setIpDocCode(ipDocTransFinancDetailFncVO.getBaseTplIpDocPrvtEntity().getData().getIpDocCode());
		tplDocTransferEntity.getData().setDocTransferCode(ipDocTransFinancDetailFncVO.getBaseTplDocTransferEntity().getData().getDocTransferCode());
		tplDocTransferEntity = docTransferDAO.find(tplDocTransferEntity);
		
		ipDocTransFinancDetailFncVO.setBaseTplDocTransferEntity(tplDocTransferEntity);
    	
    }


	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.client.ipdocprvt.functionality.BaseIpDocTransFinancDetailFnc#getDAO()
	 */
	protected BaseTplIpDocTransFinancDAO getDAO() {

		return null;
	}
	
	public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
	{		
  	  super.updateFncVOFromForm( form_, fncVO_ );
	}
	
	public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ ){
	  super.updateFormFromFncVO( form_, fncVO_ );
	  
	  BaseIpDocTransFinancDetailFncVO baseIpDocTransFinancDetailFncVO = ( BaseIpDocTransFinancDetailFncVO ) fncVO_;
	  BaseIpDocTransFinancDetailForm baseIpDocTransFinancDetailForm = ( BaseIpDocTransFinancDetailForm ) form_;
	  
      //Atualização do grid de Callback
	  ArrayList domainsFullNameTextList = new ArrayList();
	  ArrayList domainsCtcNbrList = new ArrayList();
	  ArrayList opernCodeCallBackList = new ArrayList();

	  BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity = baseIpDocTransFinancDetailFncVO.getBaseTplIpDocCallbackEntity();

	  //Varre a lista de items e atribui a um array de string no form
	  ArrayList ipDocCallbackEntities = baseIpDocTransFinancDetailFncVO.getInsertedIpDocCallback();
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
		
	  }

	  baseIpDocTransFinancDetailForm.setDomainsCtcNbr( domainsCtcNbrArray );
	  baseIpDocTransFinancDetailForm.setDomainsFullNameText( domainsFullNameTextArray );
	  baseIpDocTransFinancDetailForm.setDomainsFullNameText_2(domainsFullNameTextArray_2);
	  baseIpDocTransFinancDetailForm.setDomainsFullNameText_3(domainsFullNameTextArray_3);
	  baseIpDocTransFinancDetailForm.setDomainsPhone(domainsPhoneArray);
	  baseIpDocTransFinancDetailForm.setDomainsDDDPhone(domainsDddPhoneArray);
	  baseIpDocTransFinancDetailForm.setDomainsRamalPhone(domainsRamalPhoneArray);
	  
	  //Dados Financeiros de transferencia
	  BaseTplIpDocTransFinancEntity baseTplIpDocTransFinancEntity = baseIpDocTransFinancDetailFncVO.getBaseTplIpDocTransFinancEntity() ;
	  String chnnlAttdText = baseTplIpDocTransFinancEntity.getData().getChnnlAttdText()!= null
		   ?baseTplIpDocTransFinancEntity.getData().getChnnlAttdText()
		   : "";	


	  BigDecimal trfAmtNbr = baseTplIpDocTransFinancEntity.getData().getTrfAmtNbr()!= null
		? baseTplIpDocTransFinancEntity.getData().getTrfAmtNbr()
		: null;
		
		 NumberFormat nf = new DecimalFormat("#0.00");		
		 String trfAmtNbrFmt = null;
		 
		 try{
			 trfAmtNbrFmt = nf.format(trfAmtNbr);			
		 }
		 catch(Exception e){
			 trfAmtNbrFmt = "";
		 }
		
	  Date trfDate = baseTplIpDocTransFinancEntity.getData().getTrfDate()!= null
	  	? baseTplIpDocTransFinancEntity.getData().getTrfDate()
	  	: null;
	 
	  SimpleDateFormat formatter = new SimpleDateFormat(FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
	  String trfDateFmt = trfDate != null
	  	? formatter.format(trfDate)
	  :"";
	 

	  String trfAcctType = baseTplIpDocTransFinancEntity.getData().getTrfAcctType()!= null
		 ? baseTplIpDocTransFinancEntity.getData().getTrfAcctType().toString()
		 : "";
		 
    
	  baseIpDocTransFinancDetailForm.setChnnlAttdText(chnnlAttdText);  
	  baseIpDocTransFinancDetailForm.setTrfAcctType(trfAcctType);
	  baseIpDocTransFinancDetailForm.setTrfAmtNbr(trfAmtNbrFmt);
	  baseIpDocTransFinancDetailForm.setTrfDate(trfDateFmt);
	  baseIpDocTransFinancDetailForm.setTrfHour("");
	 
	  
	
	}
	
	/* (non-Javadoc)
		 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
		 */
	public BaseFncVO createFncVO() {		
		return new IpDocTransFinancDetailFncVO();
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


}
