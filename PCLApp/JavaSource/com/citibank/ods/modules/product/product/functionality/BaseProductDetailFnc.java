package com.citibank.ods.modules.product.product.functionality;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.FormatUtils;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplProductCorpEntity;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtEntity;
import com.citibank.ods.entity.pl.TplProductCorpEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.TplProductHistEntity;
import com.citibank.ods.entity.pl.TplProductMovEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerHistEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductHistEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerMovEntityVO;
import com.citibank.ods.modules.product.product.form.BaseProductDetailForm;
import com.citibank.ods.modules.product.product.functionality.valueobject.BaseProductDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductCorpDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductDAO;
import com.citibank.ods.persistence.pl.dao.TbgClassTypeFundAnbidDAO;
import com.citibank.ods.persistence.pl.dao.TbgSystemSegmentDAO;
import com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplAssetClassOnesrcDao;
import com.citibank.ods.persistence.pl.dao.TplAssocClassProdRdipDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerHistDAO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.product.product.functionality;
 * @version 1.0
 * @author atilio.l.araujo,Apr 17, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseProductDetailFnc extends BaseFnc
{

  protected static final String C_PROD_EMISSOR_CODE_DOMAIN_NOT_NULL = "Código do Emissor";

  protected static final String C_PROD_EMISSOR_CAT_RISK_CODE_DOMAIN_NOT_NULL = "Código da Categoria de Risco do Emissor";
	
  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */

  protected abstract BaseTplProductDAO getDAO();
  
  protected abstract BaseTplProductCorpDAO getCorpDAO();

  /**
   * Atualiza as informações do FncVO com as informações vindas da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProductDetailForm detailForm = ( BaseProductDetailForm ) form_;
    BaseProductDetailFncVO detailFncVO = ( BaseProductDetailFncVO ) fncVO_;
    FormatUtils fUtils = FormatUtils.getInstance();	
    
	String[] codeArray = null;
	if(detailForm.getSelectedCode()!= null && !detailForm.getSelectedCode().equals("")){
	  codeArray = detailForm.getSelectedCode().split(","); 	
	}

    if ( detailForm.getCitiGrpTieReltnPlcyInd() != null
         && !detailForm.getCitiGrpTieReltnPlcyInd().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setCitiGrpTieReltnPlcyInd(
                                                                                 detailForm.getCitiGrpTieReltnPlcyInd() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setCitiGrpTieReltnPlcyInd(
                                                                                 null );
    }

    if ( detailForm.getCitiGrpTieRstrnPlcyInd() != null
         && !detailForm.getCitiGrpTieRstrnPlcyInd().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setCitiGrpTieRstrnPlcyInd(
                                                                                 detailForm.getCitiGrpTieRstrnPlcyInd() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setCitiGrpTieRstrnPlcyInd(
                                                                                 null );
    }

	if(codeArray != null)
	{
	  detailFncVO.getBaseTplProductEntity().getData().setSysCode( codeArray[1] );
	}
    else if ( detailForm.getSysCode() != null
         && !detailForm.getSysCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setSysCode(
                                                                  detailForm.getSysCode() );
    }    
    else{
		detailFncVO.getBaseTplProductEntity().getData().setSysCode( null );
    }

    
	if(codeArray != null)
	{
	  detailFncVO.getBaseTplProductEntity().getData().setSysSegCode(new BigInteger(codeArray[2]) );
	}
    else if ( detailForm.getSysSegCode() != null
         && !detailForm.getSysSegCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setSysSegCode(
                                                                     new BigInteger(
                                                                                     detailForm.getSysSegCode() ) );
    }    
    else{
		detailFncVO.getBaseTplProductEntity().getData().setSysSegCode( null );
    }

    if ( detailForm.getProdName() != null
         && !detailForm.getProdName().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdName(
                                                                   detailForm.getProdName() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdName( null );
    }

    if ( detailForm.getProdCreateDate() != null
         && !detailForm.getProdCreateDate().equals( "" ) )
    {
      SimpleDateFormat formatter = new SimpleDateFormat(
                                                         FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      try
      {
        detailFncVO.getBaseTplProductEntity().getData().setProdCreateDate(
                                                                           formatter.parse( detailForm.getProdCreateDate() ) );
      }
      catch ( Exception e )
      {
        detailForm.setProdCreateDate( null );
      }
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCreateDate( null );
    }

    if ( detailForm.getProdSubFamlCode() != null
         && !detailForm.getProdSubFamlCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdSubFamlCode(
                                                                          new BigInteger(
                                                                                          detailForm.getProdSubFamlCode() ) );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdSubFamlCode( null );
    }

    detailFncVO.getBaseTplProductEntity().getData().setProdLegalClassCode(
                                                                           detailForm.getProdLegalClassCode() );

    if ( detailForm.getProdCrTypeClassCode() != null
         && !detailForm.getProdCrTypeClassCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCrTypeClassCode(
                                                                              detailForm.getProdCrTypeClassCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCrTypeClassCode(
                                                                              null );
    }

    if ( detailForm.getProdSelicCode() != null
         && !detailForm.getProdSelicCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdSelicCode(
                                                                        detailForm.getProdSelicCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdSelicCode( null );
    }

    if ( detailForm.getProdIsoCode() != null
         && !detailForm.getProdIsoCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdIsoCode(
                                                                      detailForm.getProdIsoCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdIsoCode( null );
    }

    if ( detailForm.getProdAdminCnpjNbr() != null
         && !detailForm.getProdAdminCnpjNbr().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdAdminCnpjNbr(
                                                                           detailForm.getProdAdminCnpjNbr() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdAdminCnpjNbr( null );
    }

    if ( detailForm.getProdCtlCnpjNbr() != null
         && !detailForm.getProdCtlCnpjNbr().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCtlCnpjNbr(
                                                                         detailForm.getProdCtlCnpjNbr() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCtlCnpjNbr( null );
    }

    if ( detailForm.getLastUpdUserId() != null
         && !detailForm.getLastUpdUserId().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setLastUpdUserId(
                                                                        detailForm.getLastUpdUserId() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setLastUpdUserId( null );
    }

    if ( detailForm.getProdStatCode() != null
         && !detailForm.getProdStatCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdStatCode(
                                                                       detailForm.getProdStatCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdStatCode( null );
    }

    if ( detailForm.getProdFamlCode() != null
         && !detailForm.getProdFamlCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdFamlCode(
                                                                       detailForm.getProdFamlCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdFamlCode( null );
    }

    if ( detailForm.getProdText() != null
         && !detailForm.getProdText().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdText(
                                                                   detailForm.getProdText() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdText( null );
    }

    if ( detailForm.getProdOpernStaDate() != null
         && !detailForm.getProdOpernStaDate().equals( "" ) )
    {
      SimpleDateFormat formatter = new SimpleDateFormat(
                                                         FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      try
      {
        detailFncVO.getBaseTplProductEntity().getData().setProdOpernStaDate(
                                                                             formatter.parse( detailForm.getProdOpernStaDate() ) );
      }
      catch ( Exception e )
      {
        detailForm.setProdOpernStaDate( null );
      }

    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdQlfyCode( null );
    }

    if ( detailForm.getProdQlfyCode() != null
         && !detailForm.getProdQlfyCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdQlfyCode(
                                                                       new BigInteger(
                                                                                       detailForm.getProdQlfyCode() ) );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdQlfyCode( null );
    }

    if ( detailForm.getProdRiskCatCode() != null
         && !detailForm.getProdRiskCatCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdRiskCatCode(
                                                                          new BigInteger(
                                                                                          detailForm.getProdRiskCatCode() ) );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdRiskCatCode( null );
    }

    if ( detailForm.getProdBovespaCode() != null
         && !detailForm.getProdBovespaCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdBovespaCode(
                                                                          detailForm.getProdBovespaCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdBovespaCode( null );
    }

    if ( detailForm.getProdAnbidCode() != null
         && !detailForm.getProdAnbidCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdAnbidCode(
                                                                        detailForm.getProdAnbidCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdAnbidCode( null );
    }

    if ( detailForm.getProdCstdyCnpjNbr() != null
         && !detailForm.getProdCstdyCnpjNbr().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCstdyCnpjNbr(
                                                                           detailForm.getProdCstdyCnpjNbr() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCstdyCnpjNbr( null );
    }

    if ( detailForm.getProdMgmtCnpjNbr() != null
         && !detailForm.getProdMgmtCnpjNbr().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdMgmtCnpjNbr(
                                                                          detailForm.getProdMgmtCnpjNbr() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdMgmtCnpjNbr( null );
    }

    if ( detailForm.getProdApprvDate() != null
         && !detailForm.getProdApprvDate().equals( "" ) )
    {
      SimpleDateFormat formatter = new SimpleDateFormat(
                                                         FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      try
      {
        detailFncVO.getBaseTplProductEntity().getData().setProdApprvDate(
                                                                          formatter.parse( detailForm.getProdApprvDate() ) );
      }
      catch ( Exception e )
      {
        detailForm.setProdApprvDate( null );
      }

    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdApprvDate( null );
    }

    if ( detailForm.getLastUpdDate() != null
         && !detailForm.getLastUpdDate().equals( "" ) )
    {
      SimpleDateFormat formatter = new SimpleDateFormat(
                                                         FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
      try
      {
        detailFncVO.getBaseTplProductEntity().getData().setLastUpdDate(
                                                                        formatter.parse( detailForm.getLastUpdDate() ) );
      }
      catch ( Exception e )
      {
        detailForm.setLastUpdDate( null );
      }

    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setLastUpdDate( null );
    }

    if ( detailForm.getProdMgmtCnpjNbr() != null
         && !detailForm.getProdMgmtCnpjNbr().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdMgmtCnpjNbr(
                                                                          detailForm.getProdMgmtCnpjNbr() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdMgmtCnpjNbr( null );
    }

    
	if(codeArray != null)
	{
	  //Seta o código do asset caso o mesmo venha seleciado da consulta de aprovação centralizada
	  detailFncVO.getBaseTplProductEntity().getData().setProdCode(codeArray[0]);      
	}
    else if ( detailForm.getProdCode() != null
         && !detailForm.getProdCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCode(
                                                                   detailForm.getProdCode() );
    }    
    else{
		detailFncVO.getBaseTplProductEntity().getData().setProdCode( null );    	
    }

    if ( detailForm.getProdCcyCode() != null
         && !detailForm.getProdCcyCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCcyCode(
                                                                      detailForm.getProdCcyCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCcyCode( null );
    }

    if ( detailForm.getProdCetipCode() != null
         && !detailForm.getProdCetipCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCetipCode(
                                                                        detailForm.getProdCetipCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdCetipCode( null );
    }

    if ( detailForm.getProdBmfCode() != null
         && !detailForm.getProdBmfCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdBmfCode(
                                                                      detailForm.getProdBmfCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdBmfCode( null );
    }

    if ( detailForm.getProdAuditCnpjNbr() != null
         && !detailForm.getProdAuditCnpjNbr().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdAuditCnpjNbr(
                                                                           detailForm.getProdAuditCnpjNbr() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdAuditCnpjNbr( null );
    }
    if ( detailForm.getProdProcSysCode() != null
         && !detailForm.getProdProcSysCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdProcSysCode(
                                                                          detailForm.getProdProcSysCode() );
    }    
    else{
	  detailFncVO.getBaseTplProductEntity().getData().setProdProcSysCode( null );
    }

    if ( detailForm.getProdProcSysSegCode() != null
         && !detailForm.getProdProcSysSegCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdProcSysSegCode(
                                                                             new BigInteger(
                                                                                             detailForm.getProdProcSysSegCode() ) );
    }    
    else{
	  detailFncVO.getBaseTplProductEntity().getData().setProdProcSysSegCode(
																					 null );
    }

    if ( detailForm.getPrvtProdAggrCode() != null
         && !detailForm.getPrvtProdAggrCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setPrvtProdAggrCode(
                                                                           detailForm.getPrvtProdAggrCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setPrvtProdAggrCode( null );
    }
    if ( detailForm.getProdAcctCode() != null
         && !detailForm.getProdAcctCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdAcctCode(
                                                                       detailForm.getProdAcctCode() );
    }
    else
    {
      detailFncVO.getBaseTplProductEntity().getData().setProdAcctCode( null );
    }
    if ( detailForm.getProdSubFamlCode() != null
         && !detailForm.getProdSubFamlCode().equals( "" ) )
    {
      detailFncVO.setProdSubFamlCode( new BigInteger(
                                                      detailForm.getProdSubFamlCode() ) );
    }
    else
    {
      detailFncVO.setProdSubFamlCode( null );
    }

    //flag para controle da sub-familia
    if ( detailForm.getFirstLoaded() != null
         && detailForm.getFirstLoaded().equals( "true" ) )
    {
      detailFncVO.setFirstLoaded( true );
    }
    else
    {
      detailFncVO.setFirstLoaded( false );
    }
    
	if ( detailForm.getAssetTypeCode() != null
			 && !detailForm.getAssetTypeCode().equals( "" ) )
	{
	  detailFncVO.getBaseTplProductEntity().getData().setAssetTypeCode(new BigInteger( detailForm.getAssetTypeCode()) );
	}
	else
	{
	  detailFncVO.getBaseTplProductEntity().getData().setAssetTypeCode( null );
	}
    
	if ( detailForm.getFindType().equals("true")){
		detailFncVO.setSubFamFromUpdate(true);
		detailForm.setFindType("false");		
	}
	else{
		detailFncVO.setSubFamFromUpdate(false);
	}
	
    //Emissor - Lista
	if(detailFncVO.getBaseTplProductEntity() instanceof TplProductEntity ){
		TplProductEntity TplProductEntity = (TplProductEntity)detailFncVO.getBaseTplProductEntity();
		TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) TplProductEntity.getData();
		tplProductEntityVO.setListProductPlayerRiskVO(detailForm.getListProductPlayerRiskVO());
	}

	//Emissor - Mov
	if(detailFncVO.getBaseTplProductEntity() instanceof TplProductMovEntity ){
		TplProductMovEntity tplProductMovEntity = (TplProductMovEntity)detailFncVO.getBaseTplProductEntity();
		TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO) tplProductMovEntity.getData();
		
		List<TplRiskFamilyProdPlayerEntity> listaTplRiskFamilyProdPlayerEntity = detailForm.getListProductPlayerRiskVO();
		
		List<TplRiskFamilyProdPlayerMovEntity> listaTplRiskFamilyProdPlayerMovEntity = new ArrayList<TplRiskFamilyProdPlayerMovEntity>();
		
		for(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity : listaTplRiskFamilyProdPlayerEntity){

			TplRiskFamilyProdPlayerMovEntity  TplRiskFamilyProdPlayerMovEntity = new TplRiskFamilyProdPlayerMovEntity(tplRiskFamilyProdPlayerEntity, null);
			
			listaTplRiskFamilyProdPlayerMovEntity.add(TplRiskFamilyProdPlayerMovEntity);
		}
		
		tplProductMovEntityVO.setListProductPlayerRiskVO(listaTplRiskFamilyProdPlayerMovEntity);
	}	
	
	
    //Enviar Produto para o IA    
	detailFncVO.getBaseTplProductEntity().getData().setProdSentIaInd(detailForm.getProdSentIaInd());
    
    //Classificação Interface Global
	detailFncVO.getBaseTplProductEntity().getData().setAssocClassProdCode(detailForm.getAssocClassProdCode());

    
    //Controle para insert na lista de Emissor
	detailFncVO.setEmissorProdEmissorCode(detailForm.getEmissorProdEmissorCode());
	detailFncVO.setEmissorProdRiskCatCode(detailForm.getEmissorProdRiskCatCode());
	detailFncVO.setEmissorSeqNbr(detailForm.getEmissorSeqNbr());
	
	//Fase 3
	//Dados do Produto
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdCode(detailForm.getProdCode());
	detailFncVO.getBaseTplProductCorpEntity().getData().setSysCode(detailForm.getSysCode());
	if(detailForm.getSysSegCode() != null && !detailForm.getSysSegCode().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setSysSegCode(new BigInteger(detailForm.getSysSegCode()));
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setSysSegCode(null);
	}
	//Data de Aporte - Converter para Data
	if(detailForm.getProdEvnConTrbDate() != null && !detailForm.getProdEvnConTrbDate().equals("")){
		SimpleDateFormat formatter = new SimpleDateFormat( FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
		try{
			detailFncVO.getBaseTplProductCorpEntity().getData().setProdEvnContrbDate( formatter.parse( detailForm.getProdEvnConTrbDate() ) );
		}catch ( Exception e ){
			detailFncVO.getBaseTplProductCorpEntity().getData().setProdEvnContrbDate( null );
		}
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdEvnContrbDate( null );
	}
	//Perfil Fundo
	if(detailForm.getProdFundPrflTyp()!= null && !detailForm.getProdFundPrflTyp().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdFundPrflTyp(new BigInteger(detailForm.getProdFundPrflTyp()));
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdFundPrflTyp(null);
	}
	//CNPJ
	if(detailForm.getProdCnpjNbr() != null){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdCnpjNbr(detailForm.getProdCnpjNbr().replace(".", "").replace("-", "").replace("/", ""));
	}
	//ISIN - Fase 3    
	detailFncVO.getBaseTplProductEntity().getData().setProdIsinCode(detailForm.getProdIsinCode());
	//Tipo de encerramento
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdCloseTypCode(detailForm.getProdCloseTypCode());
	//Tipo de abertura
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdOpenTypCode(detailForm.getProdOpenTypCode());
	//Nome Original do Produto
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdOrigName(detailForm.getProdOrigName());
	//Data do Evento Abertura
	if(detailForm.getProdOpenEvnDate()!= null && !detailForm.getProdOpenEvnDate().equals("")){
		SimpleDateFormat formatter = new SimpleDateFormat( FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
		try{
			detailFncVO.getBaseTplProductCorpEntity().getData().setProdOpenEvnDate( formatter.parse( detailForm.getProdOpenEvnDate() ) );
		}catch ( Exception e ){
			detailFncVO.getBaseTplProductCorpEntity().getData().setProdOpenEvnDate( null );
		}		
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdOpenEvnDate(null);
	}
	//Taxa de Administração
	if(detailForm.getProdAdminRate()!= null && !detailForm.getProdAdminRate().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdAdminRate(new BigDecimal(detailForm.getProdAdminRate()));
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdAdminRate(null);
	}
	//Taxa de Performance
	if(detailForm.getProdPerfmRate()!= null && !detailForm.getProdPerfmRate().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdPerfmRate(new BigDecimal(detailForm.getProdPerfmRate()));
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdPerfmRate(null);
	}
	//Taxa de ingresso
	if(detailForm.getProdPrtfinvApplRate()!= null && !detailForm.getProdPrtfinvApplRate().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdPrtfinvApplRate(new BigDecimal(detailForm.getProdPrtfinvApplRate()));
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdPrtfinvApplRate(null);
	}
	//Taxa de saída
	if(detailForm.getProdExitRate()!= null && !detailForm.getProdExitRate().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdExitRate(new BigDecimal(detailForm.getProdExitRate()));
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdExitRate(null);
	}
	//Tipo cota
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdQuotTypeCode(detailForm.getProdQuotTypeCode());
	//Horário Fechamento
	if(detailForm.getProdCloseTime() != null && !detailForm.getProdCloseTime().equals("")){
		SimpleDateFormat formatter0 = new SimpleDateFormat( FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
		SimpleDateFormat formatter = new SimpleDateFormat( FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );		
		try{
			String dataClose = formatter0.format(new Date());
			detailFncVO.getBaseTplProductCorpEntity().getData().setProdCloseTime( formatter.parse( dataClose + " " + detailForm.getProdCloseTime() ) );
		}catch ( Exception e ){
			detailFncVO.getBaseTplProductCorpEntity().getData().setProdCloseTime( null );
		}
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdCloseTime(null);
	}
	//Cotização Aplicação
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdDepQuotDateType("D+" + detailForm.getProdDepQuotDateType());
	//Cotização Resgate
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdWthdrCrDateType("D+" + detailForm.getProdWthdrCrDateType());
	//Liquidação de Aplicação
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdApplLiqDateType("D+" + detailForm.getProdApplLiqDateType());
	//Liquidação de Resgate
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdWthdrLiqDateType("D+" + detailForm.getProdWthdrLiqDateType());
	//Aplicação inicial mínima
	if(detailForm.getProdMinStaApplAmt()!=null && !detailForm.getProdMinStaApplAmt().equals("")){				
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdMinStaApplAmt(fUtils.toBigDecimal(detailForm.getProdMinStaApplAmt()));	
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdMinStaApplAmt(null);
	}
	//Movimentação Mínima 
	if(detailForm.getProdMovMinAmt()!=null && !detailForm.getProdMovMinAmt().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdMovMinAmt(fUtils.toBigDecimal(detailForm.getProdMovMinAmt()));	
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdMovMinAmt(null);
	}
	//Resgate mínimo
	if(detailForm.getProdMinWthdrAmt()!=null && !detailForm.getProdMinWthdrAmt().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdMinWthdrAmt(fUtils.toBigDecimal(detailForm.getProdMinWthdrAmt()));	
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdMinWthdrAmt(null);
	}
	//Valor Permanência
	if(detailForm.getProdHoldMinAmt()!=null && !detailForm.getProdHoldMinAmt().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdHoldMinAmt(fUtils.toBigDecimal(detailForm.getProdHoldMinAmt()));	
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdHoldMinAmt(null);
	}
	//Carência
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdGraceInd(detailForm.getProdGraceInd());
	//Data Enc. Balanço
	if(detailForm.getProdBalCloseDate()!= null && !detailForm.getProdBalCloseDate().equals("")){
		SimpleDateFormat formatter = new SimpleDateFormat( FuncionalityFormatKeys.C_FORMAT_DATE_DDMM );
		try{
			detailFncVO.getBaseTplProductCorpEntity().getData().setProdBalCloseDate( formatter.parse( detailForm.getProdBalCloseDate() ) );
		}catch ( Exception e ){
			detailFncVO.getBaseTplProductCorpEntity().getData().setProdBalCloseDate( null );
		}		
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdBalCloseDate( null );
	}
	//Distribuição CVM
	if(detailForm.getProdCvmDistCode()!=null && !detailForm.getProdCvmDistCode().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdCvmDistCode(new BigInteger(detailForm.getProdCvmDistCode()));	
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setProdCvmDistCode(null);
	}
	//Condomínio
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdQuotCndmCode(detailForm.getProdQuotCndmCode());
	//Restrito
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdRstrnCode(detailForm.getProdRstrnCode());
	//Forma de Divulgação(Gazeta)
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdGazetaDclrFormCode(detailForm.getProdGazetaDclrFormCode());
	//Classificação ANBIMA
	if(detailForm.getAnbidFundClassCode()!= null && !detailForm.getAnbidFundClassCode().equals("")){
		detailFncVO.getBaseTplProductCorpEntity().getData().setAnbidFundClassCode(new BigInteger(detailForm.getAnbidFundClassCode()));
	}else{
		detailFncVO.getBaseTplProductCorpEntity().getData().setAnbidFundClassCode(null);
	}
	//Divulgar como ANBIMA
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdAnbidDclrCode(detailForm.getProdAnbidDclrCode());
	//Tributação CVM
	detailFncVO.getBaseTplProductCorpEntity().getData().setProdCvmTaxCode(detailForm.getProdCvmTaxCode());
	
	//Classificaco do produto no onesource
	if(detailForm.getAssetClassOnesrc() != null && !detailForm.getAssetClassOnesrc().equals("")){
		detailFncVO.getBaseTplProductEntity().getData().setAssetClassOnesrc(detailForm.getAssetClassOnesrc());
	}else{
	
		
		detailFncVO.getBaseTplProductEntity().getData().setAssetClassOnesrc(null);
	}
	
//	detailFncVO.setFundDistFormTypeCode(detailForm.getFundDistFormTypeCode());
//	detailFncVO.setTermText(detailForm.getTermText());
//	detailFncVO.setStrategyStartDate(Util.parseDate(detailForm.getStrategyStartDate(), "dd/MM/yyyy"));
//	detailFncVO.setStrategyCloseDate(Util.parseDate(detailForm.getStrategyCloseDate(), "dd/MM/yyyy"));
//	detailFncVO.setApplicationStatCode(detailForm.getApplicationStatCode());
//	detailFncVO.setWthdrStatCode(detailForm.getWthdrStatCode());
//	detailFncVO.setPerfmRateText(detailForm.getPerfmRateText());
//	detailFncVO.setCloseDate(Util.parseDate(detailForm.getCloseDate(), "dd/MM/yyyy"));
//	

	if (detailForm.getFundDistFormTypeCode() != null){
		detailFncVO.getBaseTplProductCorpEntity().getData().setFundDistFormTypeCode(detailForm.getFundDistFormTypeCode());
	}
	if (detailForm.getTermText() != null)
		detailFncVO.getBaseTplProductCorpEntity().getData().setTermText(detailForm.getTermText());
	if (detailForm.getStrategyStartDate()!= null)
		detailFncVO.getBaseTplProductCorpEntity().getData().setStrategyStartDate(com.citibank.ods.common.util.Util.parseDate(detailForm.getStrategyStartDate(), "dd/MM/yyyy"));
	if (detailForm.getStrategyCloseDate()!=null)
		detailFncVO.getBaseTplProductCorpEntity().getData().setStrategyCloseDate(com.citibank.ods.common.util.Util.parseDate(detailForm.getStrategyCloseDate(), "dd/MM/yyyy"));
	if (detailForm.getApplicationStatCode()!=null)
		detailFncVO.getBaseTplProductCorpEntity().getData().setApplicationStatCode(detailForm.getApplicationStatCode());
	if (detailForm.getWthdrStatCode()!= null)
		detailFncVO.getBaseTplProductCorpEntity().getData().setWthdrStatCode(detailForm.getWthdrStatCode());
	if (detailForm.getPerfmRateText()!= null)
		detailFncVO.getBaseTplProductCorpEntity().getData().setPerfmRateText(detailForm.getPerfmRateText());
	if (detailForm.getCloseDate()!=null)
		detailFncVO.getBaseTplProductCorpEntity().getData().setCloseDate(com.citibank.ods.common.util.Util.parseDate(detailForm.getCloseDate(), "dd/MM/yyyy"));
	
  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProductDetailFncVO baseProductDetailFncVO = ( BaseProductDetailFncVO ) fncVO_;
    BaseProductDetailForm baseProductDetailForm = ( BaseProductDetailForm ) form_;
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );

    SimpleDateFormat dateFormat2 = new SimpleDateFormat(
                                                         Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
    
    SimpleDateFormat dateFormat3 = new SimpleDateFormat(
            											 Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_HHMM );
    
    SimpleDateFormat dateFormat_DDMM = new SimpleDateFormat(
            Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMM );
    
    FormatUtils fUtils = FormatUtils.getInstance();

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getCitiGrpTieReltnPlcyInd() != null )
    {
      baseProductDetailForm.setCitiGrpTieReltnPlcyInd( baseProductDetailFncVO.getBaseTplProductEntity().getData().getCitiGrpTieReltnPlcyInd().toString() );
    }
    else
    {
      baseProductDetailForm.setCitiGrpTieReltnPlcyInd( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getCitiGrpTieRstrnPlcyInd() != null )
    {
      baseProductDetailForm.setCitiGrpTieRstrnPlcyInd( baseProductDetailFncVO.getBaseTplProductEntity().getData().getCitiGrpTieRstrnPlcyInd().toString() );
    }
    else
    {
      baseProductDetailForm.setCitiGrpTieReltnPlcyInd( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCode() != null )
    {
      baseProductDetailForm.setProdCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdCode( "" );
    }

    //  Carrega o combo
    baseProductDetailForm.setProdFamlCodeDomain( baseProductDetailFncVO.getProdFamlCodeDomain() );

    //  Carrega o combo
    baseProductDetailForm.setLoadprodCrTypeClassCodeDomain( baseProductDetailFncVO.getLoadprodCrTypeClassCodeDomain() );

    //  Carrega o combo
    baseProductDetailForm.setLoadProdStatCodeDomain( baseProductDetailFncVO.getLoadProdStatCodeDomain() );

    //  Carrega o combo
    baseProductDetailForm.setLoadAdministratorDomain( baseProductDetailFncVO.getLoadAdministratorDomain() );

    //  Carrega o combo
    baseProductDetailForm.setLoadAuditDomain( baseProductDetailFncVO.getLoadAuditDomain() );

    //  Carrega o combo
    baseProductDetailForm.setLoadCtlDomain( baseProductDetailFncVO.getLoadCtlDomain() );

    //  Carrega o combo
    baseProductDetailForm.setLoadCustodDomain( baseProductDetailFncVO.getLoadCustodDomain() );

    //  Carrega o combo
    baseProductDetailForm.setLoadManagerDomain( baseProductDetailFncVO.getLoadManagerDomain() );

    //  Carrega o combo
    baseProductDetailForm.setCitiGrpTieReltnPlcyIndDomain( baseProductDetailFncVO.getCitiGrpTieReltnPlcyIndDomain() );

    // 	Carrega o combo
    baseProductDetailForm.setCitiGrpTieRstrnPlcyIndDomain( baseProductDetailFncVO.getCitiGrpTieRstrnPlcyIndDomain() );

    //Carrega o combo
    baseProductDetailForm.setSystemSegmentCodeDomain( baseProductDetailFncVO.getSystemSegmentDomain() );

    //  Carrega o combo
    baseProductDetailForm.setLoadProdLegalClassCodeDomain( baseProductDetailFncVO.getLoadProdLegalClassCodeDomain() );
    
    //Carrega o combo de Asset
	baseProductDetailForm.setAssetTypeCodeDomain(baseProductDetailFncVO.getAssetTypeCodeDomain());
	
	//Carrega o combo de classificacao onesource
	baseProductDetailForm.setAssetClassOnesrcDomain(baseProductDetailFncVO.getAssetClassOnesrcDomain());

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdProcSysSegCode() != null )
    {
      baseProductDetailForm.setProdProcSysSegCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdProcSysSegCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdProcSysSegCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdName() != null )
    {
      baseProductDetailForm.setProdName( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdName().toString() );
    }
    else
    {
      baseProductDetailForm.setProdName( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCreateDate() != null )
    {
      baseProductDetailForm.setProdCreateDate( dateFormat2.format( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCreateDate() ) );

    }
    else
    {
      baseProductDetailForm.setProdCreateDate( "" );
    }

    //Carrega o combo
    baseProductDetailForm.setProdSubFamlCodeDomain( baseProductDetailFncVO.getProdSubFamlCodeDomain() );

    //if ( baseProductDetailFncVO.getProdFamlCode() != null )
    //{
      //baseProductDetailForm.setProdFamlCode( baseProductDetailFncVO.getProdFamlCode().toString() );
    //}
    //else
   // {
    //  baseProductDetailForm.setProdFamlCode( "" );
   // }

    baseProductDetailForm.setProdLegalClassCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdLegalClassCode() );

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCrTypeClassCode() != null )
    {
      baseProductDetailForm.setProdCrTypeClassCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCrTypeClassCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdCrTypeClassCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdSelicCode() != null )
    {
      baseProductDetailForm.setProdSelicCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdSelicCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdSelicCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdIsoCode() != null )
    {
      baseProductDetailForm.setProdIsoCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdIsoCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdIsoCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdAdminCnpjNbr() != null )
    {
      baseProductDetailForm.setProdAdminCnpjNbr( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdAdminCnpjNbr().toString() );
    }
    else
    {
      baseProductDetailForm.setProdAdminCnpjNbr( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCtlCnpjNbr() != null )
    {
      baseProductDetailForm.setProdCtlCnpjNbr( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCtlCnpjNbr().toString() );
    }
    else
    {
      baseProductDetailForm.setProdCtlCnpjNbr( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getLastUpdUserId() != null )
    {
      baseProductDetailForm.setLastUpdUserId( baseProductDetailFncVO.getBaseTplProductEntity().getData().getLastUpdUserId().toString() );
    }
    else
    {
      baseProductDetailForm.setLastUpdUserId( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdStatCode() != null )
    {
      baseProductDetailForm.setProdStatCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdStatCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdStatCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getSysSegCode() != null )
    {
      baseProductDetailForm.setSysSegCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getSysSegCode().toString() );
    }
    else
    {
      baseProductDetailForm.setSysSegCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdText() != null )
    {
      baseProductDetailForm.setProdText( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdText().toString() );
    }
    else
    {
      baseProductDetailForm.setProdText( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdOpernStaDate() != null )
    {

      baseProductDetailForm.setProdOpernStaDate( dateFormat2.format( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdOpernStaDate() ) );

    }
    else
    {
      baseProductDetailForm.setProdOpernStaDate( "" );
    }

    //Carrega o Combo
    baseProductDetailForm.setProdQlfyCodeDomain( baseProductDetailFncVO.getProdQlfyCodeDomain() );

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdQlfyCode() != null )
    {
      baseProductDetailForm.setProdQlfyCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdQlfyCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdQlfyCode( "" );
    }

    //Carrega o combo
    baseProductDetailForm.setProdRiskCatCodeDomain( baseProductDetailFncVO.getProdRiskCatCodeDomain() );

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdRiskCatCode() != null )
    {
      baseProductDetailForm.setProdRiskCatCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdRiskCatCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdRiskCatCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdBovespaCode() != null )
    {
      baseProductDetailForm.setProdBovespaCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdBovespaCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdBovespaCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdAnbidCode() != null )
    {
      baseProductDetailForm.setProdAnbidCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdAnbidCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdAnbidCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCstdyCnpjNbr() != null )
    {
      baseProductDetailForm.setProdCstdyCnpjNbr( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCstdyCnpjNbr().toString() );
    }
    else
    {
      baseProductDetailForm.setProdCstdyCnpjNbr( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdMgmtCnpjNbr() != null )
    {
      baseProductDetailForm.setProdMgmtCnpjNbr( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdMgmtCnpjNbr().toString() );
    }
    else
    {
      baseProductDetailForm.setProdMgmtCnpjNbr( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdApprvDate() != null )
    {
      baseProductDetailForm.setProdApprvDate( dateFormat.format( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdApprvDate() ) );

    }
    else
    {
      baseProductDetailForm.setProdApprvDate( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getLastUpdDate() != null )
    {
      baseProductDetailForm.setLastUpdDate( dateFormat.format( baseProductDetailFncVO.getBaseTplProductEntity().getData().getLastUpdDate() ) );

    }
    else
    {
      baseProductDetailForm.setLastUpdDate( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdProcSysCode() != null )
    {
      baseProductDetailForm.setProdProcSysCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdProcSysCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdProcSysCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCode() != null )
    {
      baseProductDetailForm.setProdCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdCode( "" );
    }
    //Carrega o Combo
    baseProductDetailForm.setPrvtProdAggrCodeDomain( baseProductDetailFncVO.getPrvtProdAggrCodeDomain() );

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getPrvtProdAggrCode() != null )
    {
      baseProductDetailForm.setPrvtProdAggrCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getPrvtProdAggrCode().toString() );
    }
    else
    {
      baseProductDetailForm.setPrvtProdAggrCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCcyCode() != null )
    {
      baseProductDetailForm.setProdCcyCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCcyCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdCcyCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdBmfCode() != null )
    {
      baseProductDetailForm.setProdBmfCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdBmfCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdBmfCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCetipCode() != null )
    {
      baseProductDetailForm.setProdCetipCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCetipCode().toString() );
    }
    else
    {
      baseProductDetailForm.setProdCetipCode( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdAuditCnpjNbr() != null )
    {
      baseProductDetailForm.setProdAuditCnpjNbr( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdAuditCnpjNbr().toString() );
    }
    else
    {
      baseProductDetailForm.setProdAuditCnpjNbr( "" );
    }

    if ( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdAcctCode() != null )
    {
      baseProductDetailForm.setProdAcctCode( baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdAcctCode() );
    }
    else
    {
      baseProductDetailForm.setProdAcctCode( "" );
    }
	//Alteraçao Pacote 03 - Item 03 27/05/2008 INICIO
    if ( baseProductDetailFncVO.getProdSubFamlCode() != null )
    {

      baseProductDetailForm.setProdSubFamlCode(baseProductDetailFncVO.getProdSubFamlCode().toString() );
	  if ( baseProductDetailFncVO.getProdFamlCode() != null )
	  {
		baseProductDetailForm.setProdFamlCode( baseProductDetailFncVO.getProdFamlCode().toString() );
	  }
	  else
	  {
	    baseProductDetailForm.setProdFamlCode( "" );
	  }
    }
    else
    {
      baseProductDetailForm.setProdSubFamlCode( "" );
	  baseProductDetailForm.setProdFamlCode( "" );
    }
	//Alteraçao Pacote 03 27/05/2008 FIM
    //flag para controle da sub-familia
    
    if(baseProductDetailFncVO.getBaseTplProductEntity().getData().getAssetTypeCode() != null ){
		baseProductDetailForm.setAssetTypeCode(baseProductDetailFncVO.getBaseTplProductEntity().getData().getAssetTypeCode().toString());
    }
    else{
		baseProductDetailForm.setAssetTypeCode(null);
    }
    
    
    if ( baseProductDetailFncVO.isFirstLoaded() )
    {
      baseProductDetailForm.setFirstLoaded( "true" );
    }
    else
    {
      baseProductDetailForm.setFirstLoaded( "false" );
    }    
	
    //Emissor - Lista
	if(baseProductDetailFncVO.getBaseTplProductEntity() instanceof TplProductEntity ){
		TplProductEntity tplProductEntity = (TplProductEntity)baseProductDetailFncVO.getBaseTplProductEntity();
		TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) tplProductEntity.getData(); 
		baseProductDetailForm.setListProductPlayerRiskVO(tplProductEntityVO.getListProductPlayerRiskVO());
	}

	//Emissor - Mov
	if(baseProductDetailFncVO.getBaseTplProductEntity() instanceof TplProductMovEntity ){
		TplProductMovEntity tplProductMovEntity = (TplProductMovEntity)baseProductDetailFncVO.getBaseTplProductEntity();
		TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO) tplProductMovEntity.getData(); 
		
		List<TplRiskFamilyProdPlayerMovEntity> listaTplRiskFamilyProdPlayerMovEntity = tplProductMovEntityVO.getListProductPlayerRiskVO();
		
		List<TplRiskFamilyProdPlayerEntity> listaTplRiskFamilyProdPlayerEntity = new ArrayList<TplRiskFamilyProdPlayerEntity>();
		
		for(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity : listaTplRiskFamilyProdPlayerMovEntity){
			TplRiskFamilyProdPlayerEntity  tplRiskFamilyProdPlayerEntity = new TplRiskFamilyProdPlayerEntity(tplRiskFamilyProdPlayerMovEntity, null, null, null);
			
			listaTplRiskFamilyProdPlayerEntity.add(tplRiskFamilyProdPlayerEntity);			
		}
		
		baseProductDetailForm.setListProductPlayerRiskVO(listaTplRiskFamilyProdPlayerEntity);
	}	    
    
    //Enviar Produto para o IA    
    baseProductDetailForm.setProdSentIaInd(baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdSentIaInd());
    
    //Classificação Interface Global
    baseProductDetailForm.setAssocClassProdCode(baseProductDetailFncVO.getBaseTplProductEntity().getData().getAssocClassProdCode());
        
    //Emissor - Carrega o combo - Lista de Classificacao Global 
    baseProductDetailForm.setAssocClassProdCodeDomain( baseProductDetailFncVO.getAssocClassProdCodeDomain() );
    
    //Emissor - Carrega o combo - Mesmo combo de risco da tela do produto
    baseProductDetailForm.setEmissorProdRiskCatCodeDomain( baseProductDetailFncVO.getProdRiskCatCodeDomain() );
    
    //Emissor - Carrega o combo - Lista de Emissores
    baseProductDetailForm.setProdEmissorsCodeDomain( baseProductDetailFncVO.getProdEmissorsCodeDomain() );
    
    
    
    //Controle para insert na lista de Emissor
    baseProductDetailForm.setEmissorProdEmissorCode(baseProductDetailFncVO.getEmissorProdEmissorCode());
    baseProductDetailForm.setEmissorProdRiskCatCode(baseProductDetailFncVO.getEmissorProdRiskCatCode());
    baseProductDetailForm.setEmissorSeqNbr(baseProductDetailFncVO.getEmissorSeqNbr());
    
    //Campos FRD - Fase 3
    
    //Dados Produto
    baseProductDetailForm.setProdCode(baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdCode());
    baseProductDetailForm.setSysCode(baseProductDetailFncVO.getBaseTplProductEntity().getData().getSysCode());
    if(baseProductDetailFncVO.getBaseTplProductEntity().getData().getSysSegCode() != null){
    	baseProductDetailForm.setSysSegCode(baseProductDetailFncVO.getBaseTplProductEntity().getData().getSysSegCode().toString());
    }
    //Data de Aporte
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdEvnContrbDate() != null){
    	baseProductDetailForm.setProdEvnConTrbDate(dateFormat2.format(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdEvnContrbDate()));
    }else{
    	baseProductDetailForm.setProdEvnConTrbDate(null);
    }
    //Perfil Fundo
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdFundPrflTyp() != null){
    	baseProductDetailForm.setProdFundPrflTyp(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdFundPrflTyp().toString());
    }else{
    	baseProductDetailForm.setProdFundPrflTyp(null);
    }
    //CNPJ
    baseProductDetailForm.setProdCnpjNbr(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdCnpjNbr());
    //ISIN
    baseProductDetailForm.setProdIsinCode(baseProductDetailFncVO.getBaseTplProductEntity().getData().getProdIsinCode());
    //Tipo de encerramento
    baseProductDetailForm.setProdCloseTypCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdCloseTypCode());
    //Tipo de abertura
    baseProductDetailForm.setProdOpenTypCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdOpenTypCode());
    //Nome Original do Produto
    baseProductDetailForm.setProdOrigName(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdOrigName());
    //Data do Evento Abertura
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdOpenEvnDate() != null){
    	baseProductDetailForm.setProdOpenEvnDate(dateFormat2.format(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdOpenEvnDate()));
    }else{
    	baseProductDetailForm.setProdOpenEvnDate(null);
    }
    //Taxa de Administração
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdAdminRate()!= null){
    	baseProductDetailForm.setProdAdminRate(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdAdminRate().toString());
    }else{
    	baseProductDetailForm.setProdAdminRate(null);
    }
    //Taxa de Performance
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdPerfmRate()!= null){
    	baseProductDetailForm.setProdPerfmRate(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdPerfmRate().toString());
    }else{
    	baseProductDetailForm.setProdPerfmRate(null);
    }
    //Taxa de ingresso
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdPrtfinvApplRate()!= null){
    	baseProductDetailForm.setProdPrtfinvApplRate(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdPrtfinvApplRate().toString());
    }else{
    	baseProductDetailForm.setProdPrtfinvApplRate(null);
    }
    //Taxa de saída
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdExitRate()!= null){
    	baseProductDetailForm.setProdExitRate(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdExitRate().toString());
    }else{
    	baseProductDetailForm.setProdExitRate(null);
    }
    //Tipo cota
    baseProductDetailForm.setProdQuotTypeCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdQuotTypeCode());
    //Horário Fechamento
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdCloseTime() != null){
    	baseProductDetailForm.setProdCloseTime(dateFormat3.format(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdCloseTime()));
    }else{
    	baseProductDetailForm.setProdCloseTime(null);
    }
    //Cotização Aplicação
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdDepQuotDateType()!= null && !baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdDepQuotDateType().equals("")){
    	baseProductDetailForm.setProdDepQuotDateType(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdDepQuotDateType().replace("D+", ""));
    }else{
    	baseProductDetailForm.setProdDepQuotDateType(null);
    }
    //Cotização Resgate
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdWthdrCrDateType()!=null && !baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdWthdrCrDateType().equals("")){
    	baseProductDetailForm.setProdWthdrCrDateType(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdWthdrCrDateType().replace("D+", ""));
    }else{
    	baseProductDetailForm.setProdWthdrCrDateType(null);
    }
    //Liquidação de Aplicação
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdApplLiqDateType()!= null && !baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdApplLiqDateType().equals("")){
    	baseProductDetailForm.setProdApplLiqDateType(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdApplLiqDateType().replace("D+", ""));
    }else{
    	baseProductDetailForm.setProdApplLiqDateType(null);
    }
    //Liquidação de Resgate
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdWthdrLiqDateType()!=null && !baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdWthdrLiqDateType().equals("")){
    	baseProductDetailForm.setProdWthdrLiqDateType(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdWthdrLiqDateType().replace("D+", ""));
    }else{
    	baseProductDetailForm.setProdWthdrLiqDateType(null);
    }
    //Aplicação inicial mínima
    if( baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdMinStaApplAmt()!= null){    	    	
    	baseProductDetailForm.setProdMinStaApplAmt(fUtils.formatNumber(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdMinStaApplAmt()));
    }else{
    	baseProductDetailForm.setProdMinStaApplAmt(null);
    }
    //Movimentação Mínima
    if( baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdMovMinAmt()!= null){
    	baseProductDetailForm.setProdMovMinAmt(fUtils.formatNumber(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdMovMinAmt()));
    }else{
    	baseProductDetailForm.setProdMovMinAmt(null);
    }
    //Resgate mínimo
    if( baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdMinWthdrAmt()!= null){
    	baseProductDetailForm.setProdMinWthdrAmt(fUtils.formatNumber(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdMinWthdrAmt()));
    }else{
    	baseProductDetailForm.setProdMinWthdrAmt(null);
    }
    //Valor Permanência
    if( baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdHoldMinAmt()!= null){
    	baseProductDetailForm.setProdHoldMinAmt(fUtils.formatNumber(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdHoldMinAmt()));
    }else{
    	baseProductDetailForm.setProdHoldMinAmt(null);
    }
    //Carência
    baseProductDetailForm.setProdGraceInd(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdGraceInd());
    //Data Enc. Balanço
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdBalCloseDate() != null){
    	baseProductDetailForm.setProdBalCloseDate(dateFormat_DDMM.format(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdBalCloseDate()));
    }else{
    	baseProductDetailForm.setProdBalCloseDate(null);
    }
    //Distribuição CVM
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdCvmDistCode() != null){
    	baseProductDetailForm.setProdCvmDistCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdCvmDistCode().toString());
    }else{
    	baseProductDetailForm.setProdCvmDistCode(null);
    }
    //Condomínio
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdQuotCndmCode() != null){
    	baseProductDetailForm.setProdQuotCndmCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdQuotCndmCode().toString());
    }else{
    	baseProductDetailForm.setProdQuotCndmCode(null);
    }
    //Restrito
    baseProductDetailForm.setProdRstrnCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdRstrnCode());
    //Forma de Divulgação(Gazeta)
    baseProductDetailForm.setProdGazetaDclrFormCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdGazetaDclrFormCode());
    //Classificação ANBIMA
    if(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getAnbidFundClassCode() != null){
    	baseProductDetailForm.setAnbidFundClassCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getAnbidFundClassCode().toString());
    }else{
    	baseProductDetailForm.setAnbidFundClassCode(null);
    }
    //combo
    baseProductDetailForm.setAnbidFundClassCodeDomain( baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getAnbidFundClassCodeDomain());
    //Divulgar como ANBIMA
    baseProductDetailForm.setProdAnbidDclrCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdAnbidDclrCode());
    //Tributação CVM
    baseProductDetailForm.setProdCvmTaxCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getProdCvmTaxCode());
    
    baseProductDetailForm.setAssetClassOnesrc(baseProductDetailFncVO.getBaseTplProductEntity().getData().getAssetClassOnesrc());  
    
    
    baseProductDetailForm.setFundDistFormTypeCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getFundDistFormTypeCode());
    baseProductDetailForm.setTermText(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getTermText());
    baseProductDetailForm.setStrategyStartDate(com.citibank.ods.common.util.Util.dateToString(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getStrategyStartDate(), "dd/MM/yyyy"));
    baseProductDetailForm.setStrategyCloseDate(com.citibank.ods.common.util.Util.dateToString(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getStrategyCloseDate(), "dd/MM/yyyy"));
    baseProductDetailForm.setApplicationStatCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getApplicationStatCode());
    baseProductDetailForm.setWthdrStatCode(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getWthdrStatCode());
    baseProductDetailForm.setPerfmRateText(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getPerfmRateText());
    baseProductDetailForm.setCloseDate(com.citibank.ods.common.util.Util.dateToString(baseProductDetailFncVO.getBaseTplProductCorpEntity().getData().getCloseDate(), "dd/MM/yyyy"));
 }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BaseProductDetailFncVO();
  }

  /**
   * Recupera um elementos de Sub Família de Produtos, dado os critérios
   */
  public void loadProduct( BaseProductDetailFncVO detailFncVO )
  {
    BaseTplProductDAO productDAO = this.getDAO();
    BaseTplProductEntity tplProductEntity = productDAO.find( detailFncVO.getBaseTplProductEntity() );
    detailFncVO.setBaseTplProductEntity( tplProductEntity );
  }
  
  /**
   * Recupera Produtos Corp
   */
  public void loadProductCorp( BaseProductDetailFncVO detailFncVO )
  {
    BaseTplProductCorpDAO productCorpDAO = this.getCorpDAO();
    BaseTplProductCorpEntity tplProductCorpEntity = productCorpDAO.find( detailFncVO.getBaseTplProductEntity() );
    detailFncVO.setBaseTplProductCorpEntity( tplProductCorpEntity );
  }  

  /**
   * Realiza o carregamento do dominio do segmento do sistema
   *  
   */
  protected void loadProductSubFamilyDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplProdSubFamlPrvtDAO prodSubFamlPrvtDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtDAO();
    DataSet resultDomain = prodSubFamlPrvtDAO.loadDomain();
    detailFncVO_.setProdSubFamlCodeDomain( resultDomain );
  }

  protected void loadProductQlfyDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplProdQlfyPrvtDAO prodQlfyPrvtDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtDAO();
    DataSet resultDomain = prodQlfyPrvtDAO.loadDomain();
    detailFncVO_.setProdQlfyCodeDomain( resultDomain );
  }

  protected void loadProductRiskDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplProdRiskCatPrvtDAO prodRiskCatPrvtDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtDAO();
    DataSet resultDomain = prodRiskCatPrvtDAO.loadDomain();
    detailFncVO_.setProdRiskCatCodeDomain( resultDomain );
  }

  protected void loadProductAggrDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplAggrProdPrvtDAO prvtAggrProdPrvtDAO = ODSDAOFactory.getInstance().getTplAggrProdPrvtDAO();
    DataSet resultDomain = prvtAggrProdPrvtDAO.loadDomain();
    detailFncVO_.setPrvtProdAggrCodeDomain( resultDomain );
  }

  protected void loadSystemSegmentDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TbgSystemSegmentDAO segmentDAO = ODSDAOFactory.getInstance().getTbgSystemSegmentDAO();
    DataSet resultDomain = segmentDAO.loadSysSegCodeDomain();
    detailFncVO_.setSystemSegmentDomain( resultDomain );
  }
  
  protected void loadAssetTypeDomain( BaseProductDetailFncVO detailFncVO_ )
  {
     TplProdAssetTypeDAO assetTypeDAO = ODSDAOFactory.getInstance().getTplProdAssetTypeDAO();
	 DataSet resultDomain = assetTypeDAO.loadFullDomain();
	 detailFncVO_.setAssetTypeCodeDomain(resultDomain);
  }
  

  private void citiGrpTieReltnPlcyIndDomain( BaseProductDetailFncVO detailFncVO_ )
  {

    detailFncVO_.setCitiGrpTieReltnPlcyIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void citiGrpTieRstrnPlcyIndDomain( BaseProductDetailFncVO detailFncVO_ )
  {

    detailFncVO_.setCitiGrpTieRstrnPlcyIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  protected void loadAdministratorDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplPlayerDAO playerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    DataSet resultDomain = playerDAO.list(
                                           null,
                                           null,
                                           BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_ADMINISTRATOR );
    detailFncVO_.setLoadAdministratorDomain( resultDomain );
  }

  protected void loadCtlDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplPlayerDAO playerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    DataSet resultDomain = playerDAO.list(
                                           null,
                                           null,
                                           BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_CONTROLLER );
    detailFncVO_.setLoadCtlDomain( resultDomain );
  }

  protected void loadCustodDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplPlayerDAO playerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    DataSet resultDomain = playerDAO.list(
                                           null,
                                           null,
                                           BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_CUSTODIANTE );
    detailFncVO_.setLoadCustodDomain( resultDomain );
  }

  protected void loadManagerDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplPlayerDAO playerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    DataSet resultDomain = playerDAO.list(
                                           null,
                                           null,
                                           BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_MANAGER );
    detailFncVO_.setLoadManagerDomain( resultDomain );
  }

  protected void loadAuditDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplPlayerDAO playerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    DataSet resultDomain = playerDAO.list(
                                           null,
                                           null,
                                           BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_AUDITOR );
    detailFncVO_.setLoadAuditDomain( resultDomain );
  }

  protected void loadEmissorDomain( BaseProductDetailFncVO detailFncVO_ )
  {
    TplPlayerDAO playerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    DataSet resultDomain = playerDAO.list(
                                           null,
                                           null,
                                           BaseODSEntity.C_PLYR_ROLE_TYPE_CODE_EMISSOR );
    detailFncVO_.setLoadEmissorDomain( resultDomain );
  }
  
  private void loadProdStatCodeDomain( BaseProductDetailFncVO detailFncVO_ )
  {

    detailFncVO_.setLoadProdStatCodeDomain( ODSConstraintDecoder.decodeProdStatCode() );
  }

  private void loadprodCrTypeClassCodeDomain(
                                             BaseProductDetailFncVO detailFncVO_ )
  {

    detailFncVO_.setLoadProdCrTypeClassCodeDomain( ODSConstraintDecoder.decodeClassTypeCredit() );
  }

  private void loadProdLegalClassCodeDomain( BaseProductDetailFncVO detailFncVO_ )
  {

    detailFncVO_.setLoadProdLegalClassCodeDomain( ODSConstraintDecoder.decodeProdLegalClass() );
  }

  /**
   * Realiza o carregamento dos dados de família de Produtos
   * @author
   */
  protected void loadProductFamilyDomain( BaseProductDetailFncVO listFncVO_ )
  {
    TplProductFamilyPrvtDAO prodFamlPrvtDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtDAO();
    DataSet resultDomain = prodFamlPrvtDAO.loadDomain();
    listFncVO_.setProdFamlCodeDomain( resultDomain );
  }

  
  /**
   * Carrega Emissores
   *  
   */
  protected void loadProductEmissorDomain( BaseProductDetailFncVO detailFncVO_ ){

    TplPlayerDAO playerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    DataSet resultEmissors = playerDAO.listEmissor();
    detailFncVO_.setProdEmissorsCodeDomain( resultEmissors );
  }  
  
  /**
   * Carrega Classificacao de Interface Global.
   *  
   */
  protected void loadAssocClassProdDdipDomain( BaseProductDetailFncVO detailFncVO_ ){
  
	TplAssocClassProdRdipDAO assocClassProdRdipDAO = ODSDAOFactory.getInstance().getTplAssocClassProdRdipDAO();
    DataSet resultAssocClassProd = assocClassProdRdipDAO.listClassGlobal();
    detailFncVO_.setAssocClassProdCodeDomain( resultAssocClassProd ); 
  }    
  
  protected void loadAssetClassOnesrc( BaseProductDetailFncVO detailFncVO_ )
  {
    TplAssetClassOnesrcDao assetClassOnesrcDao = ODSDAOFactory.getInstance().getTplAssetClassOnesrcDao();
    DataSet resultDomain = assetClassOnesrcDao.loadDomain();
    detailFncVO_.setAssetClassOnesrcDomain( resultDomain );
  }
  
  /**
   * Carrega todos os domínios utilizados pela transação
   */
  protected void loadDomains( BaseProductDetailFncVO productDetailFncVO_ )
  {

    this.loadProductQlfyDomain( productDetailFncVO_ );

    this.loadProductRiskDomain( productDetailFncVO_ );

    this.loadProductAggrDomain( productDetailFncVO_ );

    this.loadProductSubFamilyDomain( productDetailFncVO_ );

    this.loadProductFamilyDomain( productDetailFncVO_ );

    this.loadSystemSegmentDomain( productDetailFncVO_ );

    this.citiGrpTieReltnPlcyIndDomain( productDetailFncVO_ );

    this.citiGrpTieRstrnPlcyIndDomain( productDetailFncVO_ );

    this.loadAdministratorDomain( productDetailFncVO_ );

    this.loadCtlDomain( productDetailFncVO_ );

    this.loadCustodDomain( productDetailFncVO_ );
    
    this.loadEmissorDomain( productDetailFncVO_ );

    this.loadManagerDomain( productDetailFncVO_ );

    this.loadAuditDomain( productDetailFncVO_ );

    this.loadProdStatCodeDomain( productDetailFncVO_ );

    this.loadprodCrTypeClassCodeDomain( productDetailFncVO_ );

    this.loadProdLegalClassCodeDomain( productDetailFncVO_ );
    
    this.loadAssetTypeDomain(productDetailFncVO_);
    
    this.loadProductEmissorDomain(productDetailFncVO_);
    
    this.loadAssocClassProdDdipDomain( productDetailFncVO_ );
    
    this.loadClassTypeFundAnbidDAO(productDetailFncVO_);
    
    this.loadAssetClassOnesrc(productDetailFncVO_);
  }

  /**
)   * Carrega todos os domínios utilizados pela transação
   */
  protected void loadProdFamlCode( BaseProductDetailFncVO productDetailFncVO_ )
  {   
    
	if(productDetailFncVO_.isSubFamFromUpdate()){
		
		if ( productDetailFncVO_.getBaseTplProductEntity().getData().getProdSubFamlCode() != null)
		{
	       TplProdSubFamlPrvtEntity prodSubFamlEntity = new TplProdSubFamlPrvtEntity();
	       prodSubFamlEntity.getData().setProdSubFamlCode(
													productDetailFncVO_
													.getBaseTplProductEntity()
													.getData()
													.getProdSubFamlCode() );

	         //Obtém a instância da Factory
	  		ODSDAOFactory factory = ODSDAOFactory.getInstance();
	  		//Obtém a instância do DAO necessário
	  		TplProdSubFamlPrvtDAO tplProdSubFamlDAO = factory.getTplProductSubFamilyPrvtDAO();

	  		//Realiza a consulta no DAO
	  		prodSubFamlEntity = ( TplProdSubFamlPrvtEntity ) tplProdSubFamlDAO.find( prodSubFamlEntity );

	  		//Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
	  		productDetailFncVO_.setProdFamlCode( prodSubFamlEntity.getData().getProdFamlCode() );
	  		productDetailFncVO_.setProdSubFamlCode( prodSubFamlEntity.getData().getProdSubFamlCode() );

		}
		else{
			productDetailFncVO_.setProdFamlCode( null );
			productDetailFncVO_.setProdSubFamlCode( null );    	
		}		
	}
	else{
		if ( productDetailFncVO_.getBaseTplProductEntity().getData().getProdCode() != null &&
				 productDetailFncVO_.getBaseTplProductEntity().getData().getProdCode() != "")
			{
			  TplProdSubFamlPrvtEntity prodSubFamlEntity = new TplProdSubFamlPrvtEntity();
      
			  //Obtém a instância da Factory
			  ODSDAOFactory factory = ODSDAOFactory.getInstance();
			  //Obtém a instância do DAO necessário
			  TplProdSubFamlPrvtDAO tplProdSubFamlDAO = factory.getTplProductSubFamilyPrvtDAO();

			  //Realiza a consulta no DAO
			  prodSubFamlEntity = ( TplProdSubFamlPrvtEntity ) tplProdSubFamlDAO.findByProdCode(productDetailFncVO_.getBaseTplProductEntity().getData().getProdCode());

			  //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
			  productDetailFncVO_.setProdFamlCode( prodSubFamlEntity.getData().getProdFamlCode() );
			  productDetailFncVO_.setProdSubFamlCode( prodSubFamlEntity.getData().getProdSubFamlCode() );

			}
			else{
				productDetailFncVO_.setProdFamlCode( null );
				productDetailFncVO_.setProdSubFamlCode( null );    	
			}

	}
	
	    
  }

  public void load( BaseProductDetailFncVO baseProductDetailFncVO_ )
  {
    // Recupera as informações do Produto
    BaseTplProductDAO tplProductDAO = this.getDAO();
    BaseTplProductEntity tplProductEntity = ( BaseTplProductEntity ) tplProductDAO.find( baseProductDetailFncVO_.getBaseTplProductEntity() );
    
    //Recupera Emissor
    this.getListEmissor(tplProductEntity);
    
    baseProductDetailFncVO_.setBaseTplProductEntity( tplProductEntity );

  }
  
  
  public void loadCorp( BaseProductDetailFncVO baseProductDetailFncVO_ ){

	  //Recupera as informações do Produto 
	  BaseTplProductCorpDAO baseTplProductCorpDAO = this.getCorpDAO();
	  BaseTplProductCorpEntity tplProductCorpEntity = null;
	  try{
		  tplProductCorpEntity = ( BaseTplProductCorpEntity ) baseTplProductCorpDAO.find( baseProductDetailFncVO_.getBaseTplProductEntity() );
	  }catch(NoRowsReturnedException e){
		  TplProductCorpEntity tplProductCorp = new TplProductCorpEntity();
		  tplProductCorp.getData().setProdCode(baseProductDetailFncVO_.getBaseTplProductEntity().getData().getProdCode());
		  tplProductCorp.getData().setSysCode(baseProductDetailFncVO_.getBaseTplProductEntity().getData().getSysCode());
		  tplProductCorp.getData().setSysSegCode(baseProductDetailFncVO_.getBaseTplProductEntity().getData().getSysSegCode());
		  tplProductCorpEntity = ( BaseTplProductCorpEntity ) tplProductCorp;
	  }
	  
	  baseProductDetailFncVO_.setBaseTplProductCorpEntity( tplProductCorpEntity );
  }
  

  /**
   * Recupra emissor do produto se existir
   * 
   * @param tplProductEntity
   */
  private void getListEmissor(BaseTplProductEntity tplProductEntity){
	  
	  String prodCode = tplProductEntity.getData().getProdCode();
	  
	  if(tplProductEntity instanceof TplProductEntity ){
		  TplRiskFamilyProdPlayerDAO emissorDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerDAO();
		  List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = emissorDAO.list((TplProductEntity)tplProductEntity, TplProductEntity.C_REC_STAT_CODE_ACTIVE);
		  
		  TplProductEntityVO tplProductEntityVO = (TplProductEntityVO)tplProductEntity.getData();
		  
		  tplProductEntityVO.setListProductPlayerRiskVO(listTplRiskFamilyProdPlayerEntity);
		  
	  }
	  
	  if(tplProductEntity instanceof TplProductMovEntity ){
		  TplRiskFamilyProdPlayerMovDAO emissorDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerMovDAO();
		  List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity= emissorDAO.list((TplProductMovEntity)tplProductEntity);
		  
		  TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO)tplProductEntity.getData();
		  
		  tplProductMovEntityVO.setListProductPlayerRiskVO(listTplRiskFamilyProdPlayerMovEntity);
	  }
	  
	  if(tplProductEntity instanceof TplProductHistEntity ){
		  TplRiskFamilyProdPlayerHistDAO emissorDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerHistDAO();
		  List<TplRiskFamilyProdPlayerHistEntity> listTplRiskFamilyProdPlayerHistEntity = emissorDAO.list(prodCode);
		  
		  TplProductHistEntityVO tplProductHistEntityVO = (TplProductHistEntityVO)tplProductEntity.getData();
		  
		  tplProductHistEntityVO.setListProductPlayerRiskVO(listTplRiskFamilyProdPlayerHistEntity);
	  }	  

  }

  /**
   * Metodo re cupera a descricao do emisso e dos riscos de cada emissor do produto, para renda Fixa.
   * 
   * @param productDetailFncVO
   */
  public void completeDataListaEmisso(BaseProductDetailFncVO productDetailFncVO) {

		if (productDetailFncVO.getBaseTplProductEntity() instanceof TplProductEntity) {

			ProductDetailFnc productDetailFnc = new ProductDetailFnc();

			TplProductEntity tplProductEntity = (TplProductEntity) productDetailFncVO.getBaseTplProductEntity();
			TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) tplProductEntity.getData();

			List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();

			for (TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity : listTplRiskFamilyProdPlayerEntity) {

				TplRiskFamilyProdPlayerEntityVO tplRiskFamilyProdPlayerEntityVO = (TplRiskFamilyProdPlayerEntityVO) tplRiskFamilyProdPlayerEntity.getData();

				String strEmissorCode = tplRiskFamilyProdPlayerEntityVO.getPlyrCnpjNbr();
				String strRiskCatCode = tplRiskFamilyProdPlayerEntityVO.getProdInvstRiskCode().toString();

				BigDecimal riskCatCode = new BigDecimal(strRiskCatCode);

				String playerEmissorText = productDetailFnc.getEmissorDataSetText(strEmissorCode, productDetailFncVO.getProdEmissorsCodeDomain(),"PLYR_CNPJ_NBR", "PLYR_NAME");
				String catRiskText = productDetailFnc.getRiskDataSetText(riskCatCode, productDetailFncVO.getProdRiskCatCodeDomain(), "PROD_INVST_RISK_CODE","PROD_INVST_RISK_TEXT");

				tplRiskFamilyProdPlayerEntityVO.setPlayerEmissorText(playerEmissorText);
				tplRiskFamilyProdPlayerEntityVO.setCatRiskText(catRiskText);
			}

		}

		if (productDetailFncVO.getBaseTplProductEntity() instanceof TplProductMovEntity) {
			
			ProductDetailFnc productDetailFnc = new ProductDetailFnc();
			
			TplProductMovEntity tplProductMovEntity = (TplProductMovEntity) productDetailFncVO.getBaseTplProductEntity();
			TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO) tplProductMovEntity.getData();

			List<TplRiskFamilyProdPlayerMovEntity> listaTplRiskFamilyProdPlayerMovEntity = tplProductMovEntityVO.getListProductPlayerRiskVO();

			for (TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity : listaTplRiskFamilyProdPlayerMovEntity) {

				TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = (TplRiskFamilyProdPlayerMovEntityVO) tplRiskFamilyProdPlayerMovEntity.getData();

				String strEmissorCode = tplRiskFamilyProdPlayerMovEntityVO.getPlyrCnpjNbr();
				String strRiskCatCode = tplRiskFamilyProdPlayerMovEntityVO.getProdInvstRiskCode().toString();

				BigDecimal riskCatCode = new BigDecimal(strRiskCatCode);

				String playerEmissorText = productDetailFnc.getEmissorDataSetText(strEmissorCode, productDetailFncVO.getProdEmissorsCodeDomain(),"PLYR_CNPJ_NBR", "PLYR_NAME");
				String catRiskText = productDetailFnc.getRiskDataSetText(riskCatCode, productDetailFncVO.getProdRiskCatCodeDomain(), "PROD_INVST_RISK_CODE","PROD_INVST_RISK_TEXT");

				tplRiskFamilyProdPlayerMovEntityVO.setPlayerEmissorText(playerEmissorText);
				tplRiskFamilyProdPlayerMovEntityVO.setCatRiskText(catRiskText);
			}

		}

	}
  
  /**
   * Carrega Classificação ANBIMA.
   *  
   */
  protected void loadClassTypeFundAnbidDAO( BaseProductDetailFncVO detailFncVO_ ){  
	  TbgClassTypeFundAnbidDAO classTypeFundAnbidDAO = ODSDAOFactory.getInstance().getTbgClassTypeFundAnbidDAO();
	  DataSet resultAnbidFundClassCode = classTypeFundAnbidDAO.listClassTypeFundAnbid();
	  detailFncVO_.getBaseTplProductCorpEntity().getData().setAnbidFundClassCodeDomain(resultAnbidFundClassCode);
  }

}