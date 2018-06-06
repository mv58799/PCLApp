/*
 * Created on Mar 2, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.customerprvtcmpl.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;
import com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.BaseCustomerPrvtCmplListFncVO;
import com.citibank.ods.modules.client.officer.form.OfficerSearchable;

/**
 * @author gerson.a.rodrigues
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class BaseCustomerPrvtCmplListForm extends BaseForm implements
    CustomerPrvtCmplDetailable, OfficerSearchable, CustomerSearchable, CustomerPrvtCmplSearchable
{
  
  
  private String m_clickedSearch;
  /**
   * Comment for <code>m_emNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_emNbrSrc;

  private String m_custTextSrc;


  /**
   * Comment for <code>m_pbNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prvtCustNbrSrc;

  /**
   * Comment for <code>m_pbKeyNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prvtKeyNbrSrc;

  /**
   * Comment for <code>m_revenPotnlCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_wealthPotnlCodeSrc;

  /**
   * Comment for <code>m_custNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_custNbrSrc;

  /**
   * Comment for <code>m_offcrNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_offcrNbrSrc;

  /**
   * Comment for <code>m_classCmplcCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_classCmplcCodeSrc;
  
  /**
   * Comment for <code>m_prvtCustTypeCodeSrc</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prvtCustTypeCodeSrc;

  /**
   * Comment for <code>m_offcrText</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_offcrTextSrc;

  /**
   * Comment for <code>m_offcrText</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_glbRevenSysOffcrNbrSrc;

  /**
   * Comment for <code>m_revenPotnlCodeDomain</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_wealthPotnlCodeDomain;

  /**
   * Comment for <code>m_classCmplcCodeDomain</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_classCmplcCodeDomain;
  
  /**
	* Comment for <code>m_prvtCustTypeCodeDomain</code>
	* @generated "UML to Java
	*            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	*/
   private DataSet m_prvtCustTypeCodeDomain;

  /**
   * Comment for <code>m_results</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_results;

  private String m_selectedCustNbr;
  
  private String m_selectedEmNbr;
  
  //Status do cliente
  private String m_custPrvtStatCodeSrc = "";
  
  //Status do cliente - Combo (Dataset)
  private DataSet m_custPrvtStatCodeDomain = null;

  /**
   * @param glbRevenSysOffcrNbrSrc_ Field glbRevenSysOffcrNbrSrc to be setted.
   */
  public void setGlbRevenSysOffcrNbrSrc( String glbRevenSysOffcrNbrSrc_ )
  {
    m_glbRevenSysOffcrNbrSrc = glbRevenSysOffcrNbrSrc_;
  }
  
  /**
   * @return Returns custNbr.
   */
  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbr_ Field custNbr to be setted.
   */
  public void setCustNbrSrc( String custNbr_ )
  {
    m_custNbrSrc = custNbr_;
  }

  /**
   * @return Returns the classCmplcCodeDomain.
   */
  public DataSet getClassCmplcCodeDomain()
  {
    return m_classCmplcCodeDomain;
  }

  /**
   * @param classCmplcCodeDomain_ The classCmplcCodeDomain to set.
   */
  public void setClassCmplcCodeDomain( DataSet classCmplcCodeDomain_ )
  {
    m_classCmplcCodeDomain = classCmplcCodeDomain_;
  }

  /**
   * @return Returns the classCmplcCodeSrc.
   */
  public String getClassCmplcCodeSrc()
  {
    return m_classCmplcCodeSrc;
  }

  /**
   * @param classCmplcCodeSrc_ The classCmplcCodeSrc to set.
   */
  public void setClassCmplcCodeSrc( String classCmplcCodeSrc_ )
  {
    m_classCmplcCodeSrc = classCmplcCodeSrc_;
  }

  /**
   * @return Returns the emNbrSrc.
   */
  public String getEmNbrSrc()
  {
    return m_emNbrSrc;
  }

  /**
   * @param emNbrSrc_ The emNbrSrc to set.
   */
  public void setEmNbrSrc( String emNbrSrc_ )
  {
    m_emNbrSrc = emNbrSrc_;
  }

  /**
   * @return Returns the offcrNbrSrc.
   */
  public String getOffcrNbrSrc()
  {
    return m_offcrNbrSrc;
  }

  /**
   * @param offcrNbrSrc_ The offcrNbrSrc to set.
   */
  public void setOffcrNbrSrc( String offcrNbrSrc_ )
  {
    m_offcrNbrSrc = offcrNbrSrc_;
  }

  /**
   * @return Returns the offcrTextSrc.
   */
  public String getOffcrTextSrc()
  {
    return m_offcrTextSrc;
  }

  /**
   * @param offcrTextSrc_ The offcrTextSrc to set.
   */
  public void setOffcrTextSrc( String offcrTextSrc_ )
  {
    m_offcrTextSrc = offcrTextSrc_;
  }

  /**
   * @return Returns the prvtCustNbrSrc.
   */
  public String getPrvtCustNbrSrc()
  {
    return m_prvtCustNbrSrc;
  }

  /**
   * @param prvtCustNbrSrc_ The prvtCustNbrSrc to set.
   */
  public void setPrvtCustNbrSrc( String prvtCustNbrSrc_ )
  {
    m_prvtCustNbrSrc = prvtCustNbrSrc_;
  }

  /**
   * @return Returns the prvtKeyNbrSrc.
   */
  public String getPrvtKeyNbrSrc()
  {
    return m_prvtKeyNbrSrc;
  }

  /**
   * @param prvtKeyNbrSrc_ The prvtKeyNbrSrc to set.
   */
  public void setPrvtKeyNbrSrc( String prvtKeyNbrSrc_ )
  {
    m_prvtKeyNbrSrc = prvtKeyNbrSrc_;
  }

  /**
   * @return Returns the results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ The results to set.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Returns the wealthPotnlCodeDomain.
   */
  public DataSet getWealthPotnlCodeDomain()
  {
    return m_wealthPotnlCodeDomain;
  }

  /**
   * @param wealthPotnlCodeDomain_ The wealthPotnlCodeDomain to set.
   */
  public void setWealthPotnlCodeDomain( DataSet wealthPotnlCodeDomain_ )
  {
    m_wealthPotnlCodeDomain = wealthPotnlCodeDomain_;
  }

  /**
   * @return Returns the wealthPotnlCodeSrc.
   */
  public String getWealthPotnlCodeSrc()
  {
    return m_wealthPotnlCodeSrc;
  }

  /**
   * @param wealthPotnlCodeSrc_ The wealthPotnlCodeSrc to set.
   */
  public void setWealthPotnlCodeSrc( String wealthPotnlCodeSrc_ )
  {
    m_wealthPotnlCodeSrc = wealthPotnlCodeSrc_;
  }

  /**
   * @return Returns the glbRevenSysOffcrNbrSrc.
   */
  public String getGlbRevenSysOffcrNbrSrc()
  {
    return m_glbRevenSysOffcrNbrSrc;
  }

  /**
   * @param glbRevenSysOffcrNbrSrc_ The glbRevenSysOffcrNbrSrc to set.
   */
  public void setGlbRevenSysOffcrNbr( String glbRevenSysOffcrNbrSrc_ )
  {
    m_glbRevenSysOffcrNbrSrc = glbRevenSysOffcrNbrSrc_;
  }

  /**
   * @return Returns the selectedCustNbrSrc.
   */
  public String getSelectedCustNbr()
  {
    return m_selectedCustNbr;
  }
  
  /**
   * @return Returns the selectedCustNbrSrc.
   */
  public String getSelectedCustNbrList()
  {
    return m_selectedCustNbr;
  }
  
  /**
   * @return Returns the selectedCustNbrSrc.
   */
  public void setSelectedCustNbrList(String custNbr_)
  {
    this.m_selectedCustNbr = custNbr_;
  }
  
  /**
   * @param selectedCustNbrSrc_ The selectedCustNbrSrc to set.
   */
  public void setSelectedCustNbr( String selectedCustNbrSrc_ )
  {
    m_selectedCustNbr = selectedCustNbrSrc_;
    this.setCustNbrSrc(selectedCustNbrSrc_);
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearcheable#getSelectedOffcrNbr()
   */
  public String getSelectedOffcrNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearcheable#setSelectedOffcrNbr(java.lang.String)
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    this.setOffcrNbrSrc( selectedOffcrNbr_ );
  }

  /**
   * @return Returns custTextSrc.
   */
  public String getCustTextSrc()
  {
    return m_custTextSrc;
  }

  /**
   * @param custTextSrc_ Field custTextSrc to be setted.
   */
  public void setCustTextSrc( String custTextSrc_ )
  {
    m_custTextSrc = custTextSrc_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseCustomerPrvtCmplListFncVO.C_EM_NBR_SRC_DESCRIPTION,
                                    m_emNbrSrc,
                                    BaseTplCustomerPrvtCmplEntity.C_EM_NBR_SRC_SIZE,
                                    errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtCmplListFncVO.C_GLB_REVEN_SYS_OFFCR_NBR_SRC_DESCRIPTION,
                                     m_glbRevenSysOffcrNbrSrc,
                                     BaseTplCustomerPrvtCmplEntity.C_GLB_REVEN_SYS_OFFCR_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtCmplListFncVO.C_PRVT_CUST_NBR_SRC_DESCRIPTION,
                                     m_prvtCustNbrSrc,
                                     BaseTplCustomerPrvtCmplEntity.C_PRVT_CUST_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtCmplListFncVO.C_PRVT_KEY_NBR_SRC_DESCRIPTION,
                                     m_prvtKeyNbrSrc,
                                     BaseTplCustomerPrvtCmplEntity.C_PRVT_KEY_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtCmplListFncVO.C_WEALTH_POTNL_CODE_SRC_DESCRIPTION,
                                     m_wealthPotnlCodeSrc,
                                     BaseTplCustomerPrvtCmplEntity.C_WEALTH_POTNL_CODE_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtCmplListFncVO.C_CUST_NBR_SRC_DESCRIPTION,
                                     m_custNbrSrc,
                                     BaseTplCustomerPrvtCmplEntity.C_CUST_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtCmplListFncVO.C_OFFCR_NBR_SRC_DESCRIPTION,
                                     m_offcrNbrSrc,
                                     BaseTplCustomerPrvtCmplEntity.C_OFFCR_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseCustomerPrvtCmplListFncVO.C_CLASS_CMPLC_CODE_SRC_DESCRIPTION,
                                     m_classCmplcCodeSrc,
                                     BaseTplCustomerPrvtCmplEntity.C_CLASS_CMPLC_CODE_SRC_SIZE,
                                     errors );                                 

    ODSValidator.validateMaxLength(
                                    BaseCustomerPrvtCmplListFncVO.C_OFFCR_TEXT_SRC_DESCRIPTION,
                                    m_offcrTextSrc,
                                    BaseTplCustomerPrvtCmplEntity.C_OFFCR_TEXT_SRC_SIZE,
                                    errors );

    return errors;
  }

  /**
   * @return Returns clickedSearch.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }
  /**
   * @param clickedSearch_ Field clickedSearch to be setted.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#setSelectedEmNbr(java.lang.String)
   */
  public void setSelectedEmNbr( String selectedEmNbr_ )
  {
    m_selectedEmNbr = selectedEmNbr_;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#getSelectedEmNbr()
   */
  public String getSelectedEmNbr()
  {
    return m_emNbrSrc;
  }


  /**
   * @return Returns custPrvtStatCodeDomain.
   */
  public DataSet getCustPrvtStatCodeDomain()
  {
    return m_custPrvtStatCodeDomain;
  }
  /**
   * @param custPrvtStatCodeDomain_ Field custPrvtStatCodeDomain to be setted.
   */
  public void setCustPrvtStatCodeDomain( DataSet custPrvtStatCodeDomain_ )
  {
    m_custPrvtStatCodeDomain = custPrvtStatCodeDomain_;
  }
  /**
   * @return Returns custPrvtStatCodeSrc.
   */
  public String getCustPrvtStatCodeSrc()
  {
    return m_custPrvtStatCodeSrc;
  }
  /**
   * @param custPrvtStatCodeSrc_ Field custPrvtStatCodeSrc to be setted.
   */
  public void setCustPrvtStatCodeSrc( String custPrvtStatCodeSrc_ )
  {
    m_custPrvtStatCodeSrc = custPrvtStatCodeSrc_;
  }
  /**
   * @return Retorna o Domain do Código do Tipo de Cliente.
   */
  public DataSet getPrvtCustTypeCodeDomain() {
	return m_prvtCustTypeCodeDomain;
  }

  /**
   * @return Retorna o Source do Código do Tipo de Cliente.
   */
  public String getPrvtCustTypeCodeSrc() {
    return m_prvtCustTypeCodeSrc;
  }

  /**
   * @param DataSet prvtCustTypeCodeDomain_.
   * Seta o Domain do Código do Tipo de Cliente.
   */
  public void setPrvtCustTypeCodeDomain(DataSet prvtCustTypeCodeDomain_) {
  	m_prvtCustTypeCodeDomain = prvtCustTypeCodeDomain_;
  }

  /**
   * @param String prvtCustTypeCodeSrc_.
   * Seta Seta o Source do Código do Tipo de Cliente.
   */
  public void setPrvtCustTypeCodeSrc(String prvtCustTypeCodeSrc_) {
	m_prvtCustTypeCodeSrc = prvtCustTypeCodeSrc_;
  }

/* (non-Javadoc)
 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getReltnNbr()
 */
public String getReltnNbr() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setReltnNbr(java.lang.String)
 */
public void setReltnNbr(String reltnNbr_) {
	// TODO Auto-generated method stub
	
}

}