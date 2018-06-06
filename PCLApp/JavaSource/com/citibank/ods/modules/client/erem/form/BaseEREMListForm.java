package com.citibank.ods.modules.client.erem.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;
import com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable;
import com.citibank.ods.modules.client.erem.functionality.valueobject.BaseEREMListFncVO;
import com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.erem.form;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseEREMListForm extends BaseForm implements EREMDetailable,
    RelationPrvtSearchable, CustomerPrvtCmplSearchable
{

  private String m_selectedERNbr = "";

  private String m_selectedEMNbr = "";

  private String m_erNbrSrc = "";

  private String m_emNbrSrc = "";

  private String m_custNbrSrc = "";

  private String m_custFullNameTextSrc = "";

  private String m_reltnNbrSrc = "";

  private String m_curAcctNbrSrc = "";

  private DataSet results;

  private String m_clickedSearch;

  /**
   * @return Returns results.
   */
  public DataSet getResults()
  {
    return results;
  }

  /**
   * @param results_ Field results to be setted.
   */
  public void setResults( DataSet results_ )
  {
    results = results_;
  }

  /**
   * @return Returns curAcctNbrSrc.
   */
  public String getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param curAcctNbrSrc_ Field curAcctNbrSrc to be setted.
   */
  public void setCurAcctNbrSrc( String curAcctNbrSrc_ )
  {
    m_curAcctNbrSrc = curAcctNbrSrc_;
  }

  /**
   * @return Returns custFullNameTextSrc.
   */
  public String getCustFullNameTextSrc()
  {
    return m_custFullNameTextSrc;
  }

  /**
   * @param custFullNameTextSrc_ Field custFullNameTextSrc to be setted.
   */
  public void setCustFullNameTextSrc( String custFullNameTextSrc_ )
  {
    m_custFullNameTextSrc = custFullNameTextSrc_;
  }

  /**
   * @return Returns custNbrSrc.
   */
  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbrSrc_ Field custNbrSrc to be setted.
   */
  public void setCustNbrSrc( String custNbrSrc_ )
  {
    m_custNbrSrc = custNbrSrc_;
  }

  /**
   * @return Returns erNbrSrc.
   */
  public String getErNbrSrc()
  {
    return m_erNbrSrc;
  }

  /**
   * @param erNbrSrc_ Field erNbrSrc to be setted.
   */
  public void setErNbrSrc( String erNbrSrc_ )
  {
    m_erNbrSrc = erNbrSrc_;
  }

  /**
   * @return Returns reltnNbrSrc.
   */
  public String getReltnNbrSrc()
  {
    return m_reltnNbrSrc;
  }

  /**
   * @param reltnNbrSrc_ Field reltnNbrSrc to be setted.
   */
  public void setReltnNbrSrc( String reltnNbrSrc_ )
  {
    m_reltnNbrSrc = reltnNbrSrc_;
  }

  public String getEmNbrSrc()
  {
    return m_emNbrSrc;
  }

  public void setEmNbrSrc( String emNbrSrc_ )
  {
    m_emNbrSrc = emNbrSrc_;
  }

  /**
   * @return Returns selectedEMNbr.
   */
  public String getSelectedEMNbr()
  {
    return m_selectedEMNbr;
  }

  /**
   * @param selectedEMNbr_ Field selectedEMNbr to be setted.
   */
  public void setSelectedEMNbr( String selectedEMNbr_ )
  {
    m_selectedEMNbr = selectedEMNbr_;
  }

  /**
   * @return Returns selectedERNbr.
   */
  public String getSelectedERNbr()
  {
    return m_selectedERNbr;
  }

  /**
   * @param selectedERNbr_ Field selectedERNbr to be setted.
   */
  public void setSelectedERNbr( String selectedERNbr_ )
  {
    m_selectedERNbr = selectedERNbr_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength( BaseEREMListFncVO.C_ER_NBR_SRC_DESCRIPTION,
                                    m_erNbrSrc,
                                    BaseTplErEmEntity.C_ER_NBR_SRC_SIZE, errors );

    ODSValidator.validateBigInteger(
                                     BaseEREMListFncVO.C_CUST_NBR_SRC_DESCRIPTION,
                                     m_custNbrSrc,
                                     BaseTplErEmEntity.C_CUST_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseEREMListFncVO.C_CUST_FULL_NAME_TEXT_DESCRIPTION,
                                    m_custFullNameTextSrc,
                                    BaseTplErEmEntity.C_CUST_FULL_NAME_TEXT_SIZE,
                                    errors );

    ODSValidator.validateBigInteger( BaseEREMListFncVO.C_RELTN_NBR_DESCRIPTION,
                                     m_reltnNbrSrc,
                                     BaseTplErEmEntity.C_RELTN_NBR_SIZE, errors );

    ODSValidator.validateBigInteger(
                                     BaseEREMListFncVO.C_CUR_ACCT_NBR_DESCRIPTION,
                                     m_curAcctNbrSrc,
                                     BaseTplErEmEntity.C_CUR_ACCT_NBR_SIZE,
                                     errors );

    return errors;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable#getSelectedReltnNbr()
   */
  public String getSelectedReltnNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.relationprvt.form.RelationPrvtSearchable#setSelectedReltnNbr(java.lang.String)
   */
  public void setSelectedReltnNbr( String reltnNbr_ )
  {
    m_reltnNbrSrc = reltnNbr_;
  }

  /**
   * @return Returns the clickedSearch.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_ The clickedSearch to set.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedCurAcctNbrSrc()
   */
  public String getSelectedCurAcctNbrSrc()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedCurAcctNbrSrc(java.lang.String)
   */
  public void setSelectedCurAcctNbrSrc( String curAcctNbr_ )
  {
    m_curAcctNbrSrc = curAcctNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedProdAcctCodeSrc()
   */
  public String getSelectedProdAcctCodeSrc()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedProdAcctCodeSrc(java.lang.String)
   */
  public void setSelectedProdAcctCodeSrc( String prodAcctCode_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedProdUnderAcctCodeSrc()
   */
  public String getSelectedProdUnderAcctCodeSrc()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedProdUnderAcctCodeSrc(java.lang.String)
   */
  public void setSelectedProdUnderAcctCodeSrc( String prodUnderAcctCode_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#setSelectedEmNbr(java.lang.String)
   */
  public void setSelectedEmNbr( String selectedEmNbr_ )
  {
    m_emNbrSrc = selectedEmNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#getSelectedEmNbr()
   */
  public String getSelectedEmNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#setSelectedCustNbr(java.lang.String)
   */
  public void setSelectedCustNbr( String selectedECustNbr_ )
  {
    m_custNbrSrc = selectedECustNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#getSelectedCustNbr()
   */
  public String getSelectedCustNbr()
  {
    return m_custNbrSrc;
  }
}