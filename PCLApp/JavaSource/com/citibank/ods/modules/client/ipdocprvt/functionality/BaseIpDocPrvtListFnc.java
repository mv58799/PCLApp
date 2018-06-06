package com.citibank.ods.modules.client.ipdocprvt.functionality;

import java.lang.reflect.Array;
import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocPrvtListForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.ipdocprvt.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 *  
 */

public abstract class BaseIpDocPrvtListFnc extends BaseFnc
{

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseIpDocPrvtListForm baseIpDocPrvtListForm = ( BaseIpDocPrvtListForm ) form_;
    BaseIpDocPrvtListFncVO baseIpDocPrvtListFncVO = ( BaseIpDocPrvtListFncVO ) fncVO_;

    BigInteger custNbrSrc = baseIpDocPrvtListForm.getCustNbrSrc() != null
                            && baseIpDocPrvtListForm.getCustNbrSrc().length() > 0
                                                                                 ? new BigInteger(
                                                                                                   baseIpDocPrvtListForm.getCustNbrSrc() )
                                                                                 : null;

    BigInteger ipDocCodeSrc = baseIpDocPrvtListForm.getIpDocCodeSrc() != null
                              && baseIpDocPrvtListForm.getIpDocCodeSrc().length() > 0
                                                                                     ? new BigInteger(
                                                                                                       baseIpDocPrvtListForm.getIpDocCodeSrc() )
                                                                                     : null;

    String ipDocTextSrc = baseIpDocPrvtListForm.getIpDocTextSrc() != null
                          && baseIpDocPrvtListForm.getIpDocTextSrc().length() > 0
                                                                                 ? baseIpDocPrvtListForm.getIpDocTextSrc()
                                                                                 : null;

    DataSet results = baseIpDocPrvtListForm.getResults() != null
                                                                ? baseIpDocPrvtListForm.getResults()
                                                                : null;

    String ipInvstCurAcctInd = baseIpDocPrvtListForm.getIpInvstCurAcctIndSrc() != null
                               && baseIpDocPrvtListForm.getIpInvstCurAcctIndSrc().length() > 0
                                                                                              ? baseIpDocPrvtListForm.getIpInvstCurAcctIndSrc()
                                                                                              : null;

    DataSet ipInvstCurAcctIndDomain = baseIpDocPrvtListForm.getIpInvstCurAcctIndDomain() != null
                                                                                                ? baseIpDocPrvtListForm.getIpInvstCurAcctIndDomain()
                                                                                                : null;

    String selectedCustNbr = baseIpDocPrvtListForm.getSelectedCustNbr() != null
                             && baseIpDocPrvtListForm.getSelectedCustNbr().length() > 0
                                                                                       ? baseIpDocPrvtListForm.getSelectedCustNbr()
                                                                                       : null;

    String selectedIpDocCode = baseIpDocPrvtListForm.getSelectedIpDocCode() != null
                               && baseIpDocPrvtListForm.getSelectedIpDocCode().length() > 0
                                                                                           ? baseIpDocPrvtListForm.getSelectedIpDocCode()
                                                                                           : null;

    String custFullNameText = ( baseIpDocPrvtListForm.getCustFullNameText() != null
                                                                                   ? new String(
                                                                                                 baseIpDocPrvtListForm.getCustFullNameText() )
                                                                                   : null );
    String fromCurAcct = ( baseIpDocPrvtListForm.getFromCurAcct() != null
                           && !baseIpDocPrvtListForm.getFromCurAcct().equals(
                                                                              "" )
                                                                                  ? baseIpDocPrvtListForm.getFromCurAcct()
                                                                                  : "" );

    baseIpDocPrvtListFncVO.setCustNbrSrc( custNbrSrc );
    baseIpDocPrvtListFncVO.setIpDocCodeSrc( ipDocCodeSrc );
    baseIpDocPrvtListFncVO.setIpDocTextSrc( ipDocTextSrc );
    baseIpDocPrvtListFncVO.setResults( results );
    baseIpDocPrvtListFncVO.setIpInvstCurAcctInd( ipInvstCurAcctInd );
   	baseIpDocPrvtListFncVO.setIpInvstCurAcctIndDomain( ipInvstCurAcctIndDomain );
    baseIpDocPrvtListFncVO.setSelectedCustNbr( selectedCustNbr );
    baseIpDocPrvtListFncVO.setSelectedIpDocCode( selectedIpDocCode );
    baseIpDocPrvtListFncVO.setCustFullNameText( custFullNameText );
    baseIpDocPrvtListFncVO.setFromCurAcct( fromCurAcct );

    baseIpDocPrvtListFncVO.setClickedSearch( "" );
  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseIpDocPrvtListForm baseIpDocPrvtListForm = ( BaseIpDocPrvtListForm ) form_;
    BaseIpDocPrvtListFncVO baseIpDocPrvtListFncVO = ( BaseIpDocPrvtListFncVO ) fncVO_;

    String custNbrSrc = baseIpDocPrvtListFncVO.getCustNbrSrc() != null
                                                                      ? baseIpDocPrvtListFncVO.getCustNbrSrc().toString()
                                                                      : "";
    String ipDocCodeSrc = baseIpDocPrvtListFncVO.getIpDocCodeSrc() != null
                                                                          ? baseIpDocPrvtListFncVO.getIpDocCodeSrc().toString()
                                                                          : "";
    String ipDocTextSrc = baseIpDocPrvtListFncVO.getIpDocTextSrc() != null
                                                                          ? baseIpDocPrvtListFncVO.getIpDocTextSrc().toString()
                                                                          : "";
    DataSet results = baseIpDocPrvtListFncVO.getResults() != null
                                                                 ? baseIpDocPrvtListFncVO.getResults()
                                                                 : null;

    String ipInvstCurAcctInd = baseIpDocPrvtListFncVO.getIpInvstCurAcctInd() != null
                                                                                    ? baseIpDocPrvtListFncVO.getIpInvstCurAcctInd().toString()
                                                                                    : "";

    DataSet ipInvstCurAcctIndDomain = baseIpDocPrvtListFncVO.getIpInvstCurAcctIndDomain() != null
                                                                                                 ? baseIpDocPrvtListFncVO.getIpInvstCurAcctIndDomain()
                                                                                                 : null;

    String selectedCustNbr = baseIpDocPrvtListFncVO.getSelectedCustNbr() != null
                                                                                ? baseIpDocPrvtListFncVO.getSelectedCustNbr().toString()
                                                                                : "";
    String selectedIpDocCode = baseIpDocPrvtListFncVO.getSelectedIpDocCode() != null
                                                                                    ? baseIpDocPrvtListFncVO.getSelectedIpDocCode().toString()
                                                                                    : "";

    String custFullNameText = ( baseIpDocPrvtListFncVO.getCustFullNameText() != null
                                && baseIpDocPrvtListFncVO.getCustFullNameText().length() > 0
                                                                                            ? baseIpDocPrvtListFncVO.getCustFullNameText()
                                                                                            : "" );

    String fromCurAcct = ( baseIpDocPrvtListFncVO.getFromCurAcct() != null
                           && !baseIpDocPrvtListFncVO.getFromCurAcct().equals(
                                                                               "" )
                                                                                   ? baseIpDocPrvtListFncVO.getFromCurAcct()
                                                                                   : "" );

    baseIpDocPrvtListForm.setCustNbrSrc( custNbrSrc );
    baseIpDocPrvtListForm.setIpDocCodeSrc( ipDocCodeSrc );
    baseIpDocPrvtListForm.setIpDocTextSrc( ipDocTextSrc );
    baseIpDocPrvtListForm.setResults( results );
    baseIpDocPrvtListForm.setCustFullNameText( custFullNameText );
    baseIpDocPrvtListForm.setIpInvstCurAcctIndSrc( ipInvstCurAcctInd );
    baseIpDocPrvtListForm.setIpInvstCurAcctIndDomain( ipInvstCurAcctIndDomain );
    baseIpDocPrvtListForm.setSelectedCustNbr( selectedCustNbr );
    baseIpDocPrvtListForm.setSelectedIpDocCode( selectedIpDocCode );
    baseIpDocPrvtListForm.setFromCurAcct( fromCurAcct );

    baseIpDocPrvtListForm.setClickedSearch( baseIpDocPrvtListFncVO.getClickedSearch() );

  }
}