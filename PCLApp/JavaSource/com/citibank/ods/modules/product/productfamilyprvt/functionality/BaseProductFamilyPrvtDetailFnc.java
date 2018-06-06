/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplProductFamilyPrvtEntity;
import com.citibank.ods.modules.product.productfamilyprvt.form.BaseProductFamilyPrvtDetailForm;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.BaseProductFamilyPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductFamilyPrvtDAO;

/**
 * @author leonardo.nakada
 *  
 */
public abstract class BaseProductFamilyPrvtDetailFnc extends BaseFnc
{
  /*
   * Display Name - Código
   */
  protected static final String C_DISPLAY_NAME_CODE = "Código da Família";

  /*
   * Display Name - Nome
   */
  protected static final String C_DISPLAY_NAME = "Nome da Família";

  /**
   * Atualiza o FncVO com os dados vindos do Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProductFamilyPrvtDetailForm detailForm = ( BaseProductFamilyPrvtDetailForm ) form_;
    BaseProductFamilyPrvtDetailFncVO detailFncVO = ( BaseProductFamilyPrvtDetailFncVO ) fncVO_;

    if ( detailForm.getProdFamlCode() != null
         && !detailForm.getProdFamlCode().equals( "" ) )
    {
      detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().setProdFamlCode(
                                                                                 new BigInteger(
                                                                                                 detailForm.getProdFamlCode() ) );
    }
    
    //Seta o código do asset caso o mesmo venha seleciado da consulta de aprovação centralizada
	if(detailForm.getSelectedCode()!= null && !detailForm.getSelectedCode().equals("")){
	  detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().setProdFamlCode(new BigInteger(detailForm.getSelectedCode().substring(1,8)));
	}    
    
    detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().setProdFamlName(
                                                                               detailForm.getProdFamlName() );
    detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().setProdFamlText(
                                                                               detailForm.getProdFamlText() );
  }

  /**
   * Atualiza o Form com os dados vindos do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProductFamilyPrvtDetailForm detailForm = ( BaseProductFamilyPrvtDetailForm ) form_;
    BaseProductFamilyPrvtDetailFncVO detailFncVO = ( BaseProductFamilyPrvtDetailFncVO ) fncVO_;

    if ( detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().getProdFamlCode() != null )
    {
      detailForm.setProdFamlCode( detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().getProdFamlCode().toString() );
    }
    else
    {
      detailForm.setProdFamlCode( null );
    }

    detailForm.setProdFamlName( detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().getProdFamlName() );
    detailForm.setProdFamlText( detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().getProdFamlText() );
    detailForm.setLastUpdUserId( detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().getLastUpdUserId() );
    //Formatar a data para o Formato DD/MM/AAAA
    SimpleDateFormat formatDate = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
    Date formDate = new Date();
    formDate = detailFncVO.getBaseTplProductFamilyPrvtEntity().getData().getLastUpdDate();
    if ( formDate != null )
    {
      String date = formatDate.format( formDate );
      detailForm.setLastUpdDate( date );
    }

  }

  /**
   * Recupera um elementos de Família de Produtos, dado os critérios
   */
  public void loadProductFamily( BaseProductFamilyPrvtDetailFncVO detailFncVO_ )
  {
    BaseTplProductFamilyPrvtDAO familyPrvtDAO = this.getDAO();
    BaseTplProductFamilyPrvtEntity entity = familyPrvtDAO.find( detailFncVO_.getBaseTplProductFamilyPrvtEntity() );
    detailFncVO_.setBaseTplProductFamilyPrvtEntity( entity );
  }

  /**
   * 
   * @return instancia do DAO a ser utilizado pelo método loadProductFamily
   */
  protected abstract BaseTplProductFamilyPrvtDAO getDAO();

}