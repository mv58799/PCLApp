/*
 * Created on Mar 18, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplProdSubFamlPrvtEntity;
import com.citibank.ods.modules.product.prodsubfamlprvt.form.BaseProdSubFamlPrvtDetailForm;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.BaseProdSubFamlPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseProdSubFamlPrvtDetailFnc extends BaseFnc
{

  /**
   * Atualiza as informações do FncVO com as informações vindas da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdSubFamlPrvtDetailForm detailForm = ( BaseProdSubFamlPrvtDetailForm ) form_;
    BaseProdSubFamlPrvtDetailFncVO detailFncVO = ( BaseProdSubFamlPrvtDetailFncVO ) fncVO_;

    if ( detailForm.getProdSubFamlCode() != null
         && !detailForm.getProdSubFamlCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdSubFamlCode(
                                                                                       new BigInteger(
                                                                                                       detailForm.getProdSubFamlCode() ) );
    }
    else
    {
      detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdSubFamlCode(
                                                                                       null );
    }
    
    //Seta o código do asset caso o mesmo venha seleciado da consulta de aprovação centralizada
	if(detailForm.getSelectedCode()!= null && !detailForm.getSelectedCode().equals("")){
	  detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdSubFamlCode(new BigInteger(detailForm.getSelectedCode().substring(1,8)));
	}

    detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdSubFamlName(
                                                                                     detailForm.getProdSubFamlName() );
    detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdSubFamlText(
                                                                                     detailForm.getProdSubFamlText() );

    if ( detailForm.getProdFamlCode() != null
         && !detailForm.getProdFamlCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdFamlCode(
                                                                                    new BigInteger(
                                                                                                    detailForm.getProdFamlCode() ) );
    }
    else
    {
      detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdFamlCode(
                                                                                    null );
    }
    //Seta o código do asset caso o mesmo venha seleciado da consulta de aprovação centralizada
	if(detailForm.getSelectedCode()!= null && !detailForm.getSelectedCode().equals("")){
		detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().setProdFamlCode(new BigInteger(detailForm.getSelectedCode().substring(10,17)));
	}
    
  }

  /**
   * Atualiza as informações da Form com as informações vindas do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdSubFamlPrvtDetailForm detailForm = ( BaseProdSubFamlPrvtDetailForm ) form_;
    BaseProdSubFamlPrvtDetailFncVO detailFncVO = ( BaseProdSubFamlPrvtDetailFncVO ) fncVO_;

    if ( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getProdSubFamlCode() != null )
    {
      detailForm.setProdSubFamlCode( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getProdSubFamlCode().toString() );
    }
    else
    {
      detailForm.setProdSubFamlCode( null );
    }
    detailForm.setProdSubFamlName( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getProdSubFamlName() );
    detailForm.setProdSubFamlText( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getProdSubFamlText() );
    detailForm.setProdFamlCodeDomain( detailFncVO.getProdFamlCodeDomain() );
    if ( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getProdFamlCode() != null )
    {
      detailForm.setProdFamlCode( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getProdFamlCode().toString() );
    }
    else
    {
      detailForm.setProdFamlCode( null );
    }
    detailForm.setLastUpdUserId( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getLastUpdUserId() );
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
    if ( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getLastUpdDate() != null )
    {
      detailForm.setLastUpdDate( dateFormat.format( detailFncVO.getBaseTplProductSubFamilyPrvtEntity().getData().getLastUpdDate() ) );
    }
  }

  /**
   * Recupera um elementos de Sub Família de Produtos, dado os critérios
   */
  public void loadProductSubFamily( BaseProdSubFamlPrvtDetailFncVO detailFncVO )
  {
    BaseTplProdSubFamlPrvtDAO productSubFamilyPrvtDAO = this.getDAO();
    BaseTplProdSubFamlPrvtEntity tplProductSubFamilyPrvtEntity = productSubFamilyPrvtDAO.find( detailFncVO.getBaseTplProductSubFamilyPrvtEntity() );
    detailFncVO.setBaseTplProductSubFamilyPrvtEntity( tplProductSubFamilyPrvtEntity );
  }

  /**
   * 
   * @return instancia do DAO a ser utilizado pelo método loadSubProductFamily
   */
  protected abstract BaseTplProdSubFamlPrvtDAO getDAO();

  /**
   * Realiza o carregamento dos dados de família de Produtos
   * @author leonardo.nakada
   */
  protected void loadProductFamilyDomain(
                                         BaseProdSubFamlPrvtDetailFncVO detailFncVO )
  {
    TplProductFamilyPrvtDAO productFamilyPrvtDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtDAO();
    DataSet resultDomain = productFamilyPrvtDAO.loadDomain();
    detailFncVO.setProdFamlCodeDomain( resultDomain );
  }

  /**
   * Carrega todos os domínios utilizados pela transação
   */
  protected void loadDomains( BaseProdSubFamlPrvtDetailFncVO detailFncVO )
  {
    this.loadProductFamilyDomain( detailFncVO );
  }
}