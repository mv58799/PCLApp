package com.citibank.ods.modules.client.contactcust.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.contactcust.form.BaseContactCustListForm;
import com.citibank.ods.modules.client.contactcust.form.ContactCustListForm;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.BaseContactCustListFncVO;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.ContactCustListFncVO;

/**
 * @author hamilton.matos
 *  
 */
public abstract class BaseContactCustListFnc extends BaseFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Faz cast para os tipos corretos
    BaseContactCustListFncVO contactCustListFncVO = ( BaseContactCustListFncVO ) fncVO_;
    BaseContactCustListForm contactCustListForm = ( BaseContactCustListForm ) form_;

    BigInteger custNumber = ( contactCustListForm.getCustNbrSrc() != null
                              && contactCustListForm.getCustNbrSrc().length() > 0
                                                                                 ? new BigInteger(
                                                                                                   contactCustListForm.getCustNbrSrc() )
                                                                                 : null );

    BigInteger cpfNumber = ( contactCustListForm.getCustCpfCnpjNbr() != null
                             && contactCustListForm.getCustCpfCnpjNbr().length() > 0
                                                                                    ? new BigInteger(
                                                                                                      contactCustListForm.getCustCpfCnpjNbr() )
                                                                                    : null );

    String fullName = ( contactCustListForm.getFullNameTextSrc() != null
                                                                        ? new String(
                                                                                      contactCustListForm.getFullNameTextSrc() )
                                                                        : null );

    String custFullName = ( contactCustListForm.getCustFullNameText() != null
                                                                             ? new String(
                                                                                           contactCustListForm.getCustFullNameText() )
                                                                             : null );

    BigInteger ctcNumber = ( contactCustListForm.getCtcNbrSrc() != null
                             && contactCustListForm.getCtcNbrSrc().length() > 0
                                                                               ? new BigInteger(
                                                                                                 contactCustListForm.getCtcNbrSrc() )
                                                                               : null );

    BigInteger phoneDDDCode = ( contactCustListForm.getPhoneDDDCodeSrc() != null
                                && contactCustListForm.getPhoneDDDCodeSrc().length() > 0
                                                                                        ? new BigInteger(
                                                                                                          contactCustListForm.getPhoneDDDCodeSrc() )
                                                                                        : null );

    BigInteger phoneNbr = ( contactCustListForm.getPhoneNbrSrc() != null
                            && contactCustListForm.getPhoneNbrSrc().length() > 0
                                                                                ? new BigInteger(
                                                                                                  contactCustListForm.getPhoneNbrSrc() )
                                                                                : null );

    BigInteger phoneExtNbr = ( contactCustListForm.getPhoneExtnNbrSrc() != null
                               && contactCustListForm.getPhoneExtnNbrSrc().length() > 0
                                                                                       ? new BigInteger(
                                                                                                         contactCustListForm.getPhoneExtnNbrSrc() )
                                                                                       : null );

    // Seta os valores do Form no fncVO
    contactCustListFncVO.setCustNbrSrc( custNumber );
    contactCustListFncVO.setCustCpfCnpjNbr( cpfNumber );
    contactCustListFncVO.setCtcNbrSrc( ctcNumber );
    contactCustListFncVO.setFullNameTextSrc( fullName );
    contactCustListFncVO.setCustFullNameText( custFullName );
    contactCustListFncVO.setPhoneDDDCodeSrc( phoneDDDCode );
    contactCustListFncVO.setPhoneNbrSrc( phoneNbr );
    contactCustListFncVO.setPhoneExtnNbrSrc( phoneExtNbr );

  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    //  Faz cast para os tipos corretos
    BaseContactCustListFncVO contactCustListFncVO = ( ContactCustListFncVO ) fncVO_;
    BaseContactCustListForm contactCustListForm = ( ContactCustListForm ) form_;

    //  Atualiza os dados: FncVO -> Form
    DataSet results = ( contactCustListFncVO.getResults() != null
                                                                 ? contactCustListFncVO.getResults()
                                                                 : null );
    String fullNameText = ( contactCustListFncVO.getCustFullNameText() != null
                            && contactCustListFncVO.getCustFullNameText().length() > 0
                                                                                      ? contactCustListFncVO.getCustFullNameText()
                                                                                      : "" );

    String custCpfCnpj = ( contactCustListFncVO.getCustCpfCnpjNbr() != null
                           && contactCustListFncVO.getCustCpfCnpjNbr().longValue() > 0
                                                                                      ? contactCustListFncVO.getCustCpfCnpjNbr().toString()
                                                                                      : "" );

    String custNbr = ( contactCustListFncVO.getCustNbrSrc() != null
                       && contactCustListFncVO.getCustNbrSrc().longValue() > 0
                                                                              ? contactCustListFncVO.getCustNbrSrc().toString()
                                                                              : "" );

    String ctcNbr = ( contactCustListFncVO.getCtcNbrSrc() != null
                      && contactCustListFncVO.getCtcNbrSrc().longValue() > 0
                                                                            ? contactCustListFncVO.getCtcNbrSrc().toString()
                                                                            : "" );

    contactCustListForm.setResults( results );

    contactCustListForm.setCustFullNameText( fullNameText );
    contactCustListForm.setCustCpfCnpjNbr( custCpfCnpj );
    contactCustListForm.setCustNbrSrc( custNbr );
    contactCustListForm.setCtcNbrSrc( ctcNbr );
  }
}