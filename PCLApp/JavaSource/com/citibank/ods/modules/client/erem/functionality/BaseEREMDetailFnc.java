package com.citibank.ods.modules.client.erem.functionality;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;
import com.citibank.ods.entity.pl.BaseTplErEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplErEntity;
import com.citibank.ods.modules.client.er.functionality.valueobject.BaseERDetailFncVO;
import com.citibank.ods.modules.client.erem.form.BaseEREMDetailForm;
import com.citibank.ods.modules.client.erem.functionality.valueobject.BaseEREMDetailFncVO;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMHistoryDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplErDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplErEmDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplReasonEndRelationDAO;
import com.citibank.ods.persistence.pl.dao.TplReltnClassEquityDAO;
import com.citibank.ods.persistence.pl.dao.TplRoleCustDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.erem.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 */

public abstract class BaseEREMDetailFnc extends BaseFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseEREMDetailFncVO baseErEmDetailFncVO = ( BaseEREMDetailFncVO ) fncVO_;
    BaseEREMDetailForm baseErEmDetailForm = ( BaseEREMDetailForm ) form_;

	String erNbr;
	String emNbr;
    
	String[] codeArray = null;
	if(baseErEmDetailForm.getSelectedCode()!= null && !baseErEmDetailForm.getSelectedCode().equals("")){
	  codeArray = baseErEmDetailForm.getSelectedCode().split(","); 	
	}
	
	if(codeArray != null){
		erNbr = codeArray[0]; 	
	} 
	else if(baseErEmDetailForm.getErNbr() != null && baseErEmDetailForm.getErNbr().length() > 0){
		erNbr = baseErEmDetailForm.getErNbr();
	}
	else{
		erNbr = null;
	}
	
	if(codeArray != null){
	  emNbr = codeArray[1]; 	
	} 
	else if(baseErEmDetailForm.getEmNbr() != null && baseErEmDetailForm.getEmNbr().length() > 0){
	  emNbr = baseErEmDetailForm.getEmNbr();
	}
	else{
	  emNbr = null;
	}
	    
    baseErEmDetailFncVO.getBaseTplErEmEntity().getData().setEmNbr( emNbr );    
    baseErEmDetailFncVO.getBaseTplErEmEntity().getData().setErNbr( erNbr );

    String roleCustCode = ( baseErEmDetailForm.getRoleCustCode() != null
                            && baseErEmDetailForm.getRoleCustCode().length() > 0
                                                                                ? baseErEmDetailForm.getRoleCustCode()
                                                                                : null );

    BigInteger custNbr = ( baseErEmDetailForm.getCustNbr() != null
                           && baseErEmDetailForm.getCustNbr().length() > 0
                                                                          ? new BigInteger(
                                                                                            baseErEmDetailForm.getCustNbr() )
                                                                          : null );
    String custFullName = ( baseErEmDetailForm.getCustFullNameText() != null
                            && !baseErEmDetailForm.getCustFullNameText().equals(
                                                                                 "" )
                                                                                     ? baseErEmDetailForm.getCustFullNameText()
                                                                                     : "" );
                                                                                     
	BigInteger reltnEndReasCode = ( baseErEmDetailForm.getReltnEndReasCode() != null
						   && baseErEmDetailForm.getReltnEndReasCode().length() > 0
																		  ? new BigInteger(
																					baseErEmDetailForm.getReltnEndReasCode() )
																		  : null );
																		  
	String reltnEndReasText = ( baseErEmDetailForm.getReltnEndReasText() != null
							&& !baseErEmDetailForm.getReltnEndReasText().equals(
																				 "" )
																					 ? baseErEmDetailForm.getReltnEndReasText()
																					 : "" );
	    
    baseErEmDetailFncVO.setCustNbr( custNbr );
    baseErEmDetailFncVO.setCustFullNameText( custFullName );
    String selectedErNbr = baseErEmDetailForm.getSelectedErNbrInGrid();
    baseErEmDetailFncVO.setSelectedErNbrInGrid( selectedErNbr );

    String selectedEmNbr = baseErEmDetailForm.getSelectedEmNbrInGrid();
    baseErEmDetailFncVO.setSelectedEmNbrInGrid( selectedEmNbr );

    baseErEmDetailFncVO.getBaseTplErEmEntity().getData().setRoleCustCode( roleCustCode );
    
    if (!(baseErEmDetailFncVO instanceof EREMHistoryDetailFncVO)){
	baseErEmDetailFncVO.getBaseTplErEntity().getData().setReltnEndReasCode(reltnEndReasCode);
	baseErEmDetailFncVO.getBaseTplErEntity().getData().setReltnEndReasText(reltnEndReasText);
    }

    BaseTplErEmEntity erEmEntity = new BaseTplErEmEntity();

    if ( !baseErEmDetailFncVO.isFromSearch() )
    {
      if ( baseErEmDetailForm.getSelectedItemsInGrid() != null )
      {
        baseErEmDetailFncVO.setSelectedItemsInGrid( new ArrayList() );
        for ( int i = 0; i < baseErEmDetailForm.getSelectedItemsInGrid().length; i++ )
        {
          baseErEmDetailFncVO.getSelectedItemsInGrid().add(
                                                            baseErEmDetailForm.getSelectedItemsInGrid()[ i ] );
        }

      }
      else
      {
        baseErEmDetailFncVO.setSelectedItemsInGrid( new ArrayList() );
      }

      if ( baseErEmDetailForm.getDeletedItems() != null )
      {
        baseErEmDetailFncVO.setDeletedItems( new ArrayList() );
        for ( int j = 0; j < baseErEmDetailForm.getDeletedItems().length; j++ )
        {
          baseErEmDetailFncVO.getDeletedItems().add(
                                                     baseErEmDetailForm.getDeletedItems()[ j ] );
        }

      }
      else
      {
        baseErEmDetailFncVO.setDeletedItems( new ArrayList() );
      }
    }
	
    baseErEmDetailFncVO.setClickedSearch( "" );
  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseEREMDetailFncVO baseErEmDetailFncVO = ( BaseEREMDetailFncVO ) fncVO_;
    BaseEREMDetailForm baseErEmDetailForm = ( BaseEREMDetailForm ) form_;

    String emNbr = ( baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getEmNbr() != null
                                                                                            ? baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getEmNbr().toString()
                                                                                            : null );
    String erNbr = ( baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getErNbr() != null
                                                                                            ? baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getErNbr().toString()
                                                                                            : null );

    String custName = ( baseErEmDetailFncVO.getCustFullNameText() != null
                        && !baseErEmDetailFncVO.getCustFullNameText().equals(
                                                                              "" )
                                                                                  ? baseErEmDetailFncVO.getCustFullNameText()
                                                                                  : "" );

    String lastUpdDate = ( baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getLastUpdDate() != null
                                                                                                        ? formatDateTime( baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getLastUpdDate() )
                                                                                                        : null );
    String lastUpdUserId = ( baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getLastUpdUserId() != null
                                                                                                            ? baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getLastUpdUserId().toString()
                                                                                                            : null );
    String roleCustCode = ( baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getRoleCustCode() != null
                                                                                                          ? baseErEmDetailFncVO.getBaseTplErEmEntity().getData().getRoleCustCode().toString()
                                                                                                          : null );
    String custNbr = ( baseErEmDetailFncVO.getCustNbr() != null
                                                               ? baseErEmDetailFncVO.getCustNbr().toString()
                                                               : "" );

    if (!(baseErEmDetailFncVO instanceof EREMHistoryDetailFncVO)){
	String erReltnTrfInd = ( baseErEmDetailFncVO.getBaseTplErEntity().getData().getErReltnTrfInd() != null
																										 ? baseErEmDetailFncVO.getBaseTplErEntity().getData().getErReltnTrfInd().toString()
																										 : null);
																					
	String reltnEndReasCode = ( baseErEmDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasCode() != null
																											   ? baseErEmDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasCode().toString()
																											   : null );
																				        
	String reltnEndReasText = ( baseErEmDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasText() != null
																											   ? baseErEmDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasText().toString()
																											   : null );
																					
	String equityClassCode = ( baseErEmDetailFncVO.getBaseTplErEntity().getData().getEquityClassCode() != null
																											 ? baseErEmDetailFncVO.getBaseTplErEntity().getData().getEquityClassCode().toString()
																											 : null );
	
    baseErEmDetailForm.setReltnEndReasCode( reltnEndReasCode );
    baseErEmDetailForm.setReltnEndReasText( reltnEndReasText );
    baseErEmDetailForm.setEquityClassCode( equityClassCode );
    baseErEmDetailForm.setErReltnTrfInd( erReltnTrfInd );

    }
    
    baseErEmDetailForm.setEmNbr( emNbr );
    baseErEmDetailForm.setErNbr( erNbr );
    baseErEmDetailForm.setCustNbr( custNbr );
    baseErEmDetailForm.setCustFullNameText( custName );
    baseErEmDetailForm.setLastUpdDate( lastUpdDate );
    baseErEmDetailForm.setLastUpdUserId( lastUpdUserId );
    baseErEmDetailForm.setRoleCustCode( roleCustCode );
	baseErEmDetailForm.setErReltnTrfIndDomain(baseErEmDetailFncVO.getErReltnTrfIndDomain());
	baseErEmDetailForm.setReltnEndReasCodeDomain(baseErEmDetailFncVO.getReltnEndReasCodeDomain());
	baseErEmDetailForm.setEquityClassCodeDomain(baseErEmDetailFncVO.getEquityClassCodeDomain());

    if ( baseErEmDetailFncVO.getSelectedItemsInGrid() != null
         && !baseErEmDetailFncVO.getSelectedItemsInGrid().isEmpty() )
    {
      String[] itemsSelectedInForm = new String[ baseErEmDetailFncVO.getSelectedItemsInGrid().size() ];

      for ( int i = 0; i < baseErEmDetailFncVO.getSelectedItemsInGrid().size(); i++ )
      {
        itemsSelectedInForm[ i ] = ( String ) baseErEmDetailFncVO.getSelectedItemsInGrid().get(
                                                                                                i );
      }

      baseErEmDetailForm.setSelectedItemsInGrid( itemsSelectedInForm );
    }
    else
    {
      String[] itemsLoaded = new String[ baseErEmDetailFncVO.getBaseTplErEmEntities().size() ];

      for ( int i = 0; i < baseErEmDetailFncVO.getBaseTplErEmEntities().size(); i++ )
      {
        itemsLoaded[ i ] = "N";
      }

      baseErEmDetailForm.setSelectedItemsInGrid( itemsLoaded );

    }

    if ( baseErEmDetailFncVO.getDeletedItems() != null
         && !baseErEmDetailFncVO.getDeletedItems().isEmpty() )
    {
      String[] deletedItems = new String[ baseErEmDetailFncVO.getDeletedItems().size() ];

      for ( int i = 0; i < baseErEmDetailFncVO.getDeletedItems().size(); i++ )
      {
        deletedItems[ i ] = ( String ) baseErEmDetailFncVO.getDeletedItems().get(
                                                                                  i );
      }

      baseErEmDetailForm.setDeletedItems( deletedItems );
    }
    else
    {
      String[] itemsDeleted = new String[ baseErEmDetailFncVO.getBaseTplErEmEntities().size() ];

      for ( int i = 0; i < baseErEmDetailFncVO.getBaseTplErEmEntities().size(); i++ )
      {
        itemsDeleted[ i ] = "N";
      }

      baseErEmDetailForm.setDeletedItems( itemsDeleted );

    }

    baseErEmDetailForm.setRoleCustCodeDomain( baseErEmDetailFncVO.getRoleCustCodeDomain() );
    baseErEmDetailForm.setClickedSearch( baseErEmDetailFncVO.getClickedSearch() );
  }

  /**
   * Recupera um elementos de Categoria de Risco, dado os critérios
   */
  public void load( BaseEREMDetailFncVO detailFncVO_ )
  {
    BaseTplErEmDAO erEmDAO = this.getDAO();
    BaseTplErEmEntity tplErEmEntity = ( BaseTplErEmEntity ) erEmDAO.find( detailFncVO_.getBaseTplErEmEntity() );
    detailFncVO_.setBaseTplErEmEntity( tplErEmEntity );
  }
  
  public void loadEr( BaseEREMDetailFncVO detailFncVO_ )
  {
	  BaseTplErDAO erDAO = ODSDAOFactory.getInstance().getTplErDAO();
	  TplErEntity tplErEntity = new TplErEntity();
	  BaseTplErEntity baseTplErEntity = detailFncVO_.getBaseTplErEntity();
	  baseTplErEntity.getData().setErNbr(detailFncVO_.getBaseTplErEmEntity().getData().getErNbr());
	  
	  
	  try{
		tplErEntity = ( TplErEntity ) erDAO.find(baseTplErEntity );
	  }
	  catch(NoRowsReturnedException ex){
		tplErEntity.getData().setErNbr(null);
		tplErEntity.getData().setEquityClassCode(null);
		tplErEntity.getData().setErReltnTrfInd(null);
		tplErEntity.getData().setReltnEndReasCode(null);
		tplErEntity.getData().setReltnEndReasText(null);
	  }
	  
	  detailFncVO_.setBaseTplErEntity( tplErEntity );
  }
  

  /**
   * Retorna a instância de um DAO
   * @return
   */
  protected abstract BaseTplErEmDAO getDAO();

  /*
   * Formata a data/hora
   */
  private String formatDateTime( Date date_ )
  {
    DateFormat dateFormat = new SimpleDateFormat(
                                                  Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
    return dateFormat.format( date_ );
  }

  /*
   * Carregamento do Role Customer
   */
  private void loadRoleCustomer( BaseEREMDetailFncVO detailFncVO_ )
  {
    TplRoleCustDAO dao = ODSDAOFactory.getInstance().getTplRoleCustDAO();
    DataSet ds = dao.loadDomain();
    detailFncVO_.setRoleCustCodeDomain( ds );
  }

  public void loadCustText( BaseEREMDetailFncVO erEmFncVO_ )
  {
    if ( erEmFncVO_.getCustNbr() != null
         && erEmFncVO_.getCustNbr().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr( erEmFncVO_.getCustNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Verifica se o registro existe
      if ( tplCustomerPrvtDAO.exists( customerPrvtEntity ) )
      {
        //Realiza a consulta no DAO
        customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

        erEmFncVO_.getBaseTplErEmEntity().setCustomerPrvtEntity(
                                                                 customerPrvtEntity );
        //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
        erEmFncVO_.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );
      }
      else
      {
        erEmFncVO_.setCustFullNameText( "" );
      }
    }

  }
  private void loadErReltnTrfIndDomain(BaseEREMDetailFncVO baseEREmDetailFncVO )
  {
	baseEREmDetailFncVO.setErReltnTrfIndDomain( ODSConstraintDecoder.decodeOriginCode() );
  }
		
  private void loadReltnEndReasCodeDomain(BaseEREMDetailFncVO baseEREmDetailFncVO )
  {
	  TplReasonEndRelationDAO dao = ODSDAOFactory.getInstance().getTplReasonEndRelationDAO();
	  DataSet resultDomain = dao.loadDomain();
	  baseEREmDetailFncVO.setReltnEndReasCodeDomain( resultDomain );
  }
	
  private void loadEquityClassCodeDomain(BaseEREMDetailFncVO baseEREmDetailFncVO )
  {
	  TplReltnClassEquityDAO dao = ODSDAOFactory.getInstance().getTplReltnClassEquityDAO();
	  DataSet resultDomain = dao.loadDomain();
	  baseEREmDetailFncVO.setEquityClassCodeDomain( resultDomain );
  }

  /**
   * Realiza o carregamento dos combos utilizados pela aplicação
   */
  protected void loadDomains( BaseEREMDetailFncVO detailFncVO_ )
  {
    this.loadRoleCustomer( detailFncVO_ );
	this.loadErReltnTrfIndDomain(detailFncVO_);
	this.loadReltnEndReasCodeDomain(detailFncVO_);
	this.loadEquityClassCodeDomain(detailFncVO_);
	
  }
  protected void loadEmNbr( BaseEREMDetailFncVO detailFncVO_ )
  {
    if ( detailFncVO_.getCustNbr() != null
         && detailFncVO_.getCustNbr().longValue() > 0 )
    {

      TplCustomerPrvtCmplEntity customerPrvtCmplEntity = new TplCustomerPrvtCmplEntity();

      customerPrvtCmplEntity.getData().setCustNbr( detailFncVO_.getCustNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtCmplDAO tplCustomerPrvtCmplDAO = factory.getTplCustomerPrvtCmplDAO();

      if ( tplCustomerPrvtCmplDAO.existsActive( ( TplCustomerPrvtCmplEntity ) customerPrvtCmplEntity ) )
      {
        customerPrvtCmplEntity = ( TplCustomerPrvtCmplEntity ) tplCustomerPrvtCmplDAO.find( customerPrvtCmplEntity );
        detailFncVO_.getBaseTplErEmEntity().getData().setEmNbr(
                                                                customerPrvtCmplEntity.getData().getEmNbr() );
      }
      else
      {
        detailFncVO_.getBaseTplErEmEntity().getData().setEmNbr( null );
        detailFncVO_.addError( BaseEREMDetailFncVO.C_ERROR_CUSTOMER_PRVT_CMPL );
      }

    }

  }

}