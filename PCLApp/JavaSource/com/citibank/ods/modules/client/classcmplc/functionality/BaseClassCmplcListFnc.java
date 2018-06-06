package com.citibank.ods.modules.client.classcmplc.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.client.classcmplc.form.BaseClassCmplcListForm;
import com.citibank.ods.modules.client.classcmplc.functionality.valueobject.BaseClassCmplcListFncVO;

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

public abstract class BaseClassCmplcListFnc extends BaseFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    //  Faz cast para os tipos corretos
    BaseClassCmplcListFncVO classCmplcListFncVO = ( BaseClassCmplcListFncVO ) fncVO_;
    BaseClassCmplcListForm classCmplcListForm = ( BaseClassCmplcListForm ) form_;

    // Atualiza os dados: Form -> FncVO
    BigInteger classCmplcCode = ( classCmplcListForm.getClassCmplcCodeSrc() != null
                                  && classCmplcListForm.getClassCmplcCodeSrc().length() > 0
                                                                                           ? new BigInteger(
                                                                                                             classCmplcListForm.getClassCmplcCodeSrc() )
                                                                                           : null );
    String classCmplcText = ( classCmplcListForm.getClassCmplcTextSrc() != null
                              && classCmplcListForm.getClassCmplcTextSrc().length() > 0
                                                                                       ? new String(
                                                                                                     classCmplcListForm.getClassCmplcTextSrc() )
                                                                                       : null );
    
    String sensInd = ( classCmplcListForm.getSensIndSrc() != null
                       && classCmplcListForm.getSensIndSrc().length() > 0
                                                                         ? new String(
                                                                                       classCmplcListForm.getSensIndSrc() )
                                                                         : null );
    classCmplcListFncVO.setClassCmplcCodeSrc( classCmplcCode );
    classCmplcListFncVO.setClassCmplcTextSrc( classCmplcText );
    classCmplcListFncVO.setSensIndSrc( sensInd );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseClassCmplcListFncVO classCmplcListFncVO = ( BaseClassCmplcListFncVO ) fncVO_;
    BaseClassCmplcListForm classCmplcListForm = ( BaseClassCmplcListForm ) form_;

    classCmplcListForm.setSensIndDomain( classCmplcListFncVO.getSensIndDomain() );
    classCmplcListForm.setResults( classCmplcListFncVO.getResults() );

  }

  /*
   * Parametro BaseFncVO
   * 
   * O metodo correga as irmformações iniciais da tela
   */
  public void load( BaseFncVO fncVO_ )
  {
    BaseClassCmplcListFncVO classCmplcListFncVO = ( BaseClassCmplcListFncVO ) fncVO_;

    loadSensIndDomain( classCmplcListFncVO );
    classCmplcListFncVO.setResults( null );
    classCmplcListFncVO.setClassCmplcCodeSrc( null );
    classCmplcListFncVO.setClassCmplcTextSrc( null );

  }

  public void loadSensIndDomain( BaseClassCmplcListFncVO classCmplcListFncVO_ )
  {

    classCmplcListFncVO_.setSensIndDomain( ODSConstraintDecoder.decodeIndicador() );

  }

}