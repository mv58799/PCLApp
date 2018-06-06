/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplProdRiskCatPrvtEntity;
import com.citibank.ods.modules.product.prodriskcatprvt.form.BaseProdRiskCatPrvtDetailForm;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.BaseProdRiskCatPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdRiskCatPrvtDAO;

/**
 * @author leonardo.nakada
 * 
 */
public abstract class BaseProdRiskCatPrvtDetailFnc extends BaseFnc
{
  /*
   * Diplay Name - Código
   */
  protected static final String C_DISPLAY_NAME_CODE = "Código da Categoria de Risco";
  
  /*
   * Diplay Name - Descrição
   */
  protected static final String C_DISPLAY_NAME_TEXT = "Descrição da Categoria de Risco";
  
  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdRiskCatPrvtDetailFncVO baseProdRiskCatPrvtDetailFncVO = ( BaseProdRiskCatPrvtDetailFncVO ) fncVO_;
    BaseProdRiskCatPrvtDetailForm baseProdRiskCatPrvtDetailForm = ( BaseProdRiskCatPrvtDetailForm ) form_;

    if ( baseProdRiskCatPrvtDetailForm.getProdRiskCatCode()!=null && !"".equals(baseProdRiskCatPrvtDetailForm.getProdRiskCatCode())){
    baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().setProdRiskCatCode(
                                                                                                   new BigInteger(
                                                                                                                   baseProdRiskCatPrvtDetailForm.getProdRiskCatCode() ) );
    }
    baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().setProdRiskCatText(
                                                                                                   baseProdRiskCatPrvtDetailForm.getProdRiskCatText() );
                                                                                                   
    //Seta o código do asset caso o mesmo venha seleciado da consulta de aprovação centralizada
	if(baseProdRiskCatPrvtDetailForm.getSelectedCode()!= null && !baseProdRiskCatPrvtDetailForm.getSelectedCode().equals("")){
	  baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().setProdRiskCatCode(new BigInteger(baseProdRiskCatPrvtDetailForm.getSelectedCode().substring(1,8)));
	}                                                                                                   
  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdRiskCatPrvtDetailFncVO baseProdRiskCatPrvtDetailFncVO = ( BaseProdRiskCatPrvtDetailFncVO ) fncVO_;
    BaseProdRiskCatPrvtDetailForm baseProdRiskCatPrvtDetailForm = ( BaseProdRiskCatPrvtDetailForm ) form_;

    if (baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatCode()!=null){
      baseProdRiskCatPrvtDetailForm.setProdRiskCatCode( baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatCode().toString() );
    }
    else{
      baseProdRiskCatPrvtDetailForm.setProdRiskCatCode( "" );
    }
    baseProdRiskCatPrvtDetailForm.setProdRiskCatText( baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getProdRiskCatText() );
    baseProdRiskCatPrvtDetailForm.setLastUpdUserId(baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getLastUpdUserID());
    SimpleDateFormat dateFormat = new SimpleDateFormat(Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM);
    if (baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getLastUpdDate()!=null){
      baseProdRiskCatPrvtDetailForm.setLastUpdDate(dateFormat.format(baseProdRiskCatPrvtDetailFncVO.getBaseTplProdRiskCatPrvtEntity().getData().getLastUpdDate()));
    }
  }
  
  /**
   * Recupera um elementos de Categoria de Risco, 
   * dado os critérios
   */
  public void loadProdRiskCatPrvt(BaseProdRiskCatPrvtDetailFncVO detailFncVO_){
    BaseTplProdRiskCatPrvtDAO riskCatPrvtDAO = this.getDAO();
    BaseTplProdRiskCatPrvtEntity tplProdRiskCatPrvtEntity = (BaseTplProdRiskCatPrvtEntity) riskCatPrvtDAO.find(detailFncVO_.getBaseTplProdRiskCatPrvtEntity());
    detailFncVO_.setBaseTplProdRiskCatPrvtEntity(tplProdRiskCatPrvtEntity);
  }
  
  protected abstract BaseTplProdRiskCatPrvtDAO getDAO();
}