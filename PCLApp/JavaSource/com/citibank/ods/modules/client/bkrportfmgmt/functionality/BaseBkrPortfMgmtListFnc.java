package com.citibank.ods.modules.client.bkrportfmgmt.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.form.BaseBkrPortfMgmtListForm;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BaseBkrPortfMgmtListFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BkrPortfMgmtListFncVO;

/**
 * @see package com.citibank.ods.modules.client.customerbroker.functionality;
 * @author Hamilton Matos
 */

public class BaseBkrPortfMgmtListFnc extends BaseFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseBkrPortfMgmtListForm baseBkrPortfMgmtListForm = ( BaseBkrPortfMgmtListForm ) form_;
    BaseBkrPortfMgmtListFncVO baseBkrPortfMgmtListFncVO = ( BaseBkrPortfMgmtListFncVO ) fncVO_;

    baseBkrPortfMgmtListFncVO.setCustNbrSrc( baseBkrPortfMgmtListForm.getCustNbrSrc() );
    baseBkrPortfMgmtListFncVO.setCustFullNameTextSrc( baseBkrPortfMgmtListForm.getCustFullNameTextSrc() );
    baseBkrPortfMgmtListFncVO.setBkrCnpjNbrSrc( baseBkrPortfMgmtListForm.getBkrCnpjNbrSrc() );
    baseBkrPortfMgmtListFncVO.setBkrNameTextSrc( baseBkrPortfMgmtListForm.getBkrNameTextSrc() );
  }

  /**
   * Atualiza os atributos do Form com os atributos vindos da FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseBkrPortfMgmtListForm baseBkrPortfMgmtListForm = ( BaseBkrPortfMgmtListForm ) form_;
    BaseBkrPortfMgmtListFncVO baseBkrPortfMgmtListFncVO = ( BaseBkrPortfMgmtListFncVO ) fncVO_;

    baseBkrPortfMgmtListForm.setBkrCnpjNbrSrc( baseBkrPortfMgmtListFncVO.getBkrCnpjNbrSrc() );
    baseBkrPortfMgmtListForm.setBkrNameTextSrc( baseBkrPortfMgmtListFncVO.getBkrNameTextSrc() );
    baseBkrPortfMgmtListForm.setCustNbrSrc( baseBkrPortfMgmtListFncVO.getCustNbrSrc() );
    baseBkrPortfMgmtListForm.setCustFullNameTextSrc( baseBkrPortfMgmtListFncVO.getCustFullNameTextSrc() );
    baseBkrPortfMgmtListForm.setResults( baseBkrPortfMgmtListFncVO.getResults() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BkrPortfMgmtListFncVO();
  }

}