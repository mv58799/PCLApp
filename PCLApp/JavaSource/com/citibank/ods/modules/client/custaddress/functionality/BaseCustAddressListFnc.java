package com.citibank.ods.modules.client.custaddress.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.custaddress.form.BaseCustAddressListForm;
import com.citibank.ods.modules.client.custaddress.functionality.valueobject.BaseCustAddressListFncVO;

/**
 * @author hamilton.matos
 *  
 */

public abstract class BaseCustAddressListFnc extends BaseFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCustAddressListFncVO custAddressListFncVO = ( BaseCustAddressListFncVO ) fncVO_;
    BaseCustAddressListForm custAddressListForm = ( BaseCustAddressListForm ) form_;

    BigInteger custNbr = ( custAddressListForm.getCustNbrSrc() != null
                           && custAddressListForm.getCustNbrSrc().length() > 0
                                                                              ? new BigInteger(
                                                                                                custAddressListForm.getCustNbrSrc() )
                                                                              : null );

    BigInteger cpfNumber = ( custAddressListForm.getCustCpfCnpjNbrSrc() != null
                             && custAddressListForm.getCustCpfCnpjNbrSrc().length() > 0
                                                                                       ? new BigInteger(
                                                                                                         custAddressListForm.getCustCpfCnpjNbrSrc() )
                                                                                       : null );

    String custFullNameText = ( custAddressListForm.getCustFullNameTextSrc() != null
                                                                                    ? new String(
                                                                                                  custAddressListForm.getCustFullNameTextSrc() )
                                                                                    : null );

    // Seta os valores do Form no fncVO
    custAddressListFncVO.setCustNbrSrc( custNbr );
    custAddressListFncVO.setCustCpfCnpjNbrSrc( cpfNumber );
    custAddressListFncVO.setCustFullNameTextSrc( custFullNameText );
  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseCustAddressListFncVO custAddressListFncVO = ( BaseCustAddressListFncVO ) fncVO_;
    BaseCustAddressListForm custAddressListForm = ( BaseCustAddressListForm ) form_;

    DataSet results = ( custAddressListFncVO.getResults() != null
                                                                 ? custAddressListFncVO.getResults()
                                                                 : null );

    String custFullNameText = ( custAddressListFncVO.getCustFullNameTextSrc() != null
                                && custAddressListFncVO.getCustFullNameTextSrc().length() > 0
                                                                                             ? custAddressListFncVO.getCustFullNameTextSrc()
                                                                                             : "" );

    String custCpfCnpj = ( custAddressListFncVO.getCustCpfCnpjNbrSrc() != null
                           && custAddressListFncVO.getCustCpfCnpjNbrSrc().longValue() > 0
                                                                                         ? custAddressListFncVO.getCustCpfCnpjNbrSrc().toString()
                                                                                         : "" );
    String custNbr = ( custAddressListFncVO.getCustNbrSrc() != null
                                                                   //&&
                                                                   // custAddressListFncVO.getCustNbrSrc().intValue()
                                                                   // > 0
                                                                   ? custAddressListFncVO.getCustNbrSrc().toString()
                                                                   : "" );

    custAddressListForm.setResults( results );
    custAddressListForm.setCustFullNameTextSrc( custFullNameText );
    custAddressListForm.setCustCpfCnpjNbrSrc( custCpfCnpj );
    custAddressListForm.setCustNbrSrc( custNbr );
  }
}