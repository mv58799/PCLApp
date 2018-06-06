package com.citibank.ods.modules.client.classcmplc.functionality;

import java.math.BigInteger;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplClassCmplcEntity;
import com.citibank.ods.modules.client.classcmplc.form.BaseClassCmplcDetailForm;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.BaseClassCmplcDetailFncVO;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.ClassCmplcDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplClassCmplcDAO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.classcmplc.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 13, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseClassCmplcDetailFnc extends BaseFnc
{

  /*
   * Diplay Name - Código
   */
  protected static final String C_DISPLAY_NAME_CODE = "Código de Classificação Compliance";

  /*
   * Diplay Name - Descrição
   */
  protected static final String C_DISPLAY_NAME_TEXT = "Descrição de Classificação Compliance";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseClassCmplcDetailFncVO classCmplcFncVO = ( BaseClassCmplcDetailFncVO ) fncVO_;
    BaseClassCmplcDetailForm classCmplcForm = ( BaseClassCmplcDetailForm ) form_;
    TplClassCmplcEntity tplClassCmplcEntity = new TplClassCmplcEntity();
    
    classCmplcFncVO.setBaseTplClassCmplcEntity( tplClassCmplcEntity );

    // Atualizando os dados: Form -> FncVO
    BigInteger classCmplcCode = ( classCmplcForm.getClassCmplcCode() != null
                                  && classCmplcForm.getClassCmplcCode().length() > 0
                                                                                    ? new BigInteger(
                                                                                                      classCmplcForm.getClassCmplcCode() )
                                                                                    : null );
    String classCmplcText = ( classCmplcForm.getClassCmplcText() != null
                              && classCmplcForm.getClassCmplcText().length() > 0
                                                                                ? classCmplcForm.getClassCmplcText()
                                                                                : null );
    String sensInd = ( classCmplcForm.getSensInd() != null
                                                          ? classCmplcForm.getSensInd().equals(
                                                                                                Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM )
                                                                                                                                                ? classCmplcForm.getSensInd()
                                                                                                                                                : Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO
                                                          : null );
    Date lastUpdateDate = new Date();

    classCmplcFncVO.getBaseTplClassCmplcEntity().getData().setClassCmplcCode(
                                                                              classCmplcCode );
    classCmplcFncVO.getBaseTplClassCmplcEntity().getData().setClassCmplcText(
                                                                              classCmplcText );
    classCmplcFncVO.getBaseTplClassCmplcEntity().getData().setSensInd( sensInd );
    classCmplcFncVO.getBaseTplClassCmplcEntity().getData().setLastUpdDate(
                                                                           lastUpdateDate );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    BaseClassCmplcDetailForm form = ( BaseClassCmplcDetailForm ) form_;
    BaseClassCmplcDetailFncVO fncVO = ( BaseClassCmplcDetailFncVO ) fncVO_;

    String lastUpdDate = ( fncVO.getBaseTplClassCmplcEntity().getData().getLastUpdDate() != null
                                                                                                ? fncVO.getBaseTplClassCmplcEntity().getData().getLastUpdDate().toString()
                                                                                                : "" );
    String lastUpdUserId = ( fncVO.getBaseTplClassCmplcEntity().getData().getLastUpdUserId() != null
                                                                                                    ? fncVO.getBaseTplClassCmplcEntity().getData().getLastUpdUserId().toString()
                                                                                                    : "" );
    String classCmplcCode = ( fncVO.getBaseTplClassCmplcEntity().getData().getClassCmplcCode() != null
                                                                                                      ? fncVO.getBaseTplClassCmplcEntity().getData().getClassCmplcCode().toString()
                                                                                                      : "" );
    String classCmplcText = ( fncVO.getBaseTplClassCmplcEntity().getData().getClassCmplcText() != null
                                                                                                      ? fncVO.getBaseTplClassCmplcEntity().getData().getClassCmplcText().toString()
                                                                                                      : "" );
    String sensInd = ( fncVO.getBaseTplClassCmplcEntity().getData().getSensInd() != null
                                                                                        ? fncVO.getBaseTplClassCmplcEntity().getData().getSensInd().toString()
                                                                                        : "" );
    String recStatCode = ( fncVO.getBaseTplClassCmplcEntity().getData().getRecStatCode() != null
                                                                                                ? fncVO.getBaseTplClassCmplcEntity().getData().getRecStatCode().toString()
                                                                                                : "" );

    DataSet sensIndDomain = fncVO.getSensIndDomain();
    form.setLastUpdDate( lastUpdDate );
    form.setLastUpdUserId( lastUpdUserId );
    form.setClassCmplcCode( classCmplcCode );
    form.setClassCmplcText( classCmplcText );
    form.setSensInd( sensInd );
    form.setRecStatCode( recStatCode );
    form.setSensIndDomain( sensIndDomain );
  }

  public void load( ClassCmplcDetailFncVO fncVO_ )
  {

    loadSensIndDomain( fncVO_ );

  }

  public void loadSensIndDomain(
                                BaseClassCmplcDetailFncVO classCmplcDetailFncVO_ )
  {

    classCmplcDetailFncVO_.setSensIndDomain( ODSConstraintDecoder.decodeIndicador() );

  }

  protected abstract BaseTplClassCmplcDAO getDAO();

}