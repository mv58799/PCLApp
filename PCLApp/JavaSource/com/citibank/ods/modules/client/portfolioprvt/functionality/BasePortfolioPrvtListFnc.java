package com.citibank.ods.modules.client.portfolioprvt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.portfolioprvt.form.BasePortfolioPrvtListForm;
import com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject.BasePortfolioPrvtListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.portfolioprvt.functionality;
 * @version 1.0
 * @author l.braga,28/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BasePortfolioPrvtListFnc extends BaseFnc
{

  /*
   * Atualiza o FncVO com as informacoes da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {

    BasePortfolioPrvtListFncVO fncVO = ( BasePortfolioPrvtListFncVO ) fncVO_;
    BasePortfolioPrvtListForm form = ( BasePortfolioPrvtListForm ) form_;

    String portfCode = form.getPortfCodeSrc() != null
                       && form.getPortfCodeSrc().length() > 0
                                                             ? form.getPortfCodeSrc()
                                                             : null;

    BigInteger portfOffcr = form.getOffcrNbrSrc() != null
                            && form.getOffcrNbrSrc().length() > 0
                                                                 ? new BigInteger(
                                                                                   form.getOffcrNbrSrc() )
                                                                 : null;

    String portName = form.getPortfNameTextSrc() != null
                      && form.getPortfNameTextSrc().length() > 0
                                                                ? form.getPortfNameTextSrc()
                                                                : null;

    String officername = form.getOffcrNameTextSrc() != null
                         && form.getOffcrNameTextSrc().length() > 0
                                                                   ? form.getOffcrNameTextSrc()
                                                                   : null;
    fncVO.setPortfCode( portfCode );
    fncVO.setPortfOffcrNbr( portfOffcr );
    fncVO.setPortfNameText( portName );
    fncVO.setOffcrNameText( officername );

    fncVO.setClickedSearch( "" );

  }

  /*
   * Atualiza a Form com as informacoes do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BasePortfolioPrvtListFncVO fncVO = ( BasePortfolioPrvtListFncVO ) fncVO_;
    BasePortfolioPrvtListForm form = ( BasePortfolioPrvtListForm ) form_;

    DataSet result = fncVO.getResults() != null ? fncVO.getResults() : null;

    form.setResults( result );

    form.setPortfCodeSrc( fncVO.getPortfCode() );
    form.setPortfNameTextSrc( fncVO.getPortfNameText() );
    form.setOffcrNbrSrc( fncVO.getPortfOffcrNbr() != null
                                                         ? fncVO.getPortfOffcrNbr().toString()
                                                         : null );
    form.setOffcrNameTextSrc( fncVO.getOffcrNameText() );
    form.setClickedSearch( fncVO.getClickedSearch() );

  }
}