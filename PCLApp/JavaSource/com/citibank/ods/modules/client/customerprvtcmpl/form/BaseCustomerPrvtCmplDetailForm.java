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
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.BaseCustomerPrvtCmplDetailFncVO;
import com.citibank.ods.modules.client.officer.form.OfficerSearchable;

/**
 * @author bruno.zanetti
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java
 *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class BaseCustomerPrvtCmplDetailForm extends BaseForm implements
	CustomerPrvtCmplDetailable, OfficerSearchable, CustomerSearchable
{
	
  private String m_selectedCustNbr = "";
  
  private String m_emNbr;

  private String m_mailRecvInd;
  
  private DataSet m_mailRecvIndDomain ;
  
  //Status do cliente
  private String m_custPrvtStatCode = "";
  
  //Status do cliente - Combo (Dataset)
  private DataSet m_custPrvtStatCodeDomain = null;
 

  /**
   * Comment for <code>m_offclCustNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_glbRevenSysOffcrNbr;

  private String m_offclMailRecvInd;
  
  private String m_clickedSearch;
  
  /*
   * DataSet Popula combo.
   */
  private DataSet m_offclMailRecvIndDomain;

  /**
   * Comment for <code>m_pbNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prvtCustNbr;

  /**
   * Comment for <code>m_prvtKeyNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prvtKeyNbr;

  /**
   * Comment for <code>m_revenPotnlCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_wealthPotnlCode;

  /**
   * Comment for <code>m_custNbr</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_custNbrSrc;

  private String m_custText;

  /**
   * Comment for <code>m_prvtCustTypeCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_prvtCustTypeCode;
  
  /**
   * Comment for <code>m_classCmplcCode</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_classCmplcCode;

  /**
   * Comment for <code>m_offcrText</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_offcrText;

  /**
   * Comment for <code>m_offcrText</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private String m_offcrNbrSrc;

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
   * Comment for <code>results</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_results;
  
  /**
   * 
   */
  private boolean m_offcrClear;

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
							   HttpServletRequest request_ )
  {
	ActionErrors errors = new ActionErrors();

	ODSValidator.validateBigInteger(
									 BaseCustomerPrvtCmplDetailFncVO.C_CUST_NBR_DESCRIPTION,
									 m_custNbrSrc,
									 BaseTplCustomerPrvtCmplEntity.C_CUST_NBR_SIZE,
									 errors );

	ODSValidator.validateMaxLength(
									BaseCustomerPrvtCmplDetailFncVO.C_EM_NBR_DESCRIPTION,
									m_emNbr,
									BaseTplCustomerPrvtCmplEntity.C_EM_NBR_SIZE,
									errors );
	ODSValidator.validateMaxLength(
									BaseCustomerPrvtCmplDetailFncVO.C_MAIL_RECV_IND_DESCRIPTION,
									m_mailRecvInd,
									BaseTplCustomerPrvtCmplEntity.C_MAIL_RECV_IND_SIZE,
									errors );
	ODSValidator.validateMaxLength(
									BaseCustomerPrvtCmplDetailFncVO.C_OFFCL_MAIL_RECV_IND_DESCRIPTION,
									m_offclMailRecvInd,
									BaseTplCustomerPrvtCmplEntity.C_OFFCL_MAIL_RECV_IND_SIZE,
									errors );
	ODSValidator.validateBigInteger(
									 BaseCustomerPrvtCmplDetailFncVO.C_PRVT_CUST_NBR_DESCRIPTION,
									 m_prvtCustNbr,
									 BaseTplCustomerPrvtCmplEntity.C_PRVT_CUST_NBR_SIZE,
									 errors );

	ODSValidator.validateBigInteger(
									 BaseCustomerPrvtCmplDetailFncVO.C_PRVT_KEY_NBR_DESCRIPTION,
									 m_prvtKeyNbr,
									 BaseTplCustomerPrvtCmplEntity.C_PRVT_KEY_NBR_SIZE,
									 errors );

	ODSValidator.validateBigInteger(
									 BaseCustomerPrvtCmplDetailFncVO.C_WEALTH_POTNL_CODE_DESCRIPTION,
									 m_wealthPotnlCode,
									 BaseTplCustomerPrvtCmplEntity.C_WEALTH_POTNL_CODE_SIZE,
									 errors );

	ODSValidator.validateBigInteger(
									 BaseCustomerPrvtCmplDetailFncVO.C_CLASS_CMPLC_CODE_DESCRIPTION,
									 m_classCmplcCode,
									 BaseTplCustomerPrvtCmplEntity.C_CLASS_CMPLC_CODE_SIZE,
									 errors );

	ODSValidator.validateBigInteger(
									 BaseCustomerPrvtCmplDetailFncVO.C_OFFCR_NBR_DESCRIPTION,
									 m_offcrNbrSrc,
									 BaseTplCustomerPrvtCmplEntity.C_OFFCR_NBR_SIZE,
									 errors );

	ODSValidator.validateBigInteger(
									 BaseCustomerPrvtCmplDetailFncVO.C_GLB_REVEN_SYS_OFFCR_NBR_DESCRIPTION,
									 m_glbRevenSysOffcrNbr,
									 BaseTplCustomerPrvtCmplEntity.C_GLB_REVEN_SYS_OFFCR_NBR_SIZE,
									 errors );

	return errors;
  }

  /**
   * @return Returns classCmplcCode.
   */
  public String getClassCmplcCode()
  {
	return m_classCmplcCode;
  }

  /**
   * @param classCmplcCode_ Field classCmplcCode to be setted.
   */
  public void setClassCmplcCode( String classCmplcCode_ )
  {
	m_classCmplcCode = classCmplcCode_;
  }

  /**
   * @return Returns classCmplcCodeDomain.
   */
  public DataSet getClassCmplcCodeDomain()
  {
	return m_classCmplcCodeDomain;
  }

  /**
   * @param classCmplcCodeDomain_ Field classCmplcCodeDomain to be setted.
   */
  public void setClassCmplcCodeDomain( DataSet classCmplcCodeDomain_ )
  {
	m_classCmplcCodeDomain = classCmplcCodeDomain_;
  }

  /**
   * @return Returns emNbr.
   */
  public String getEmNbr()
  {
	return m_emNbr;
  }

  /**
   * @param emNbr_ Field emNbr to be setted.
   */
  public void setEmNbr( String emNbr_ )
  {
	m_emNbr = emNbr_;
  }

  /**
   * @return Returns glbRevenSysOffcrNbrScr.
   */
  public String getGlbRevenSysOffcrNbr()
  {
	return m_glbRevenSysOffcrNbr;
  }

  /**
   * @param glbRevenSysOffcrNbrScr_ Field glbRevenSysOffcrNbrScr to be setted.
   */
  public void setGlbRevenSysOffcrNbr( String glbRevenSysOffcrNbr_ )
  {
	m_glbRevenSysOffcrNbr = glbRevenSysOffcrNbr_;
  }

  /**
   * @return Returns mailRecvInd.
   */
  public String getMailRecvInd()
  {
	return m_mailRecvInd;
  }

  /**
   * @param mailRecvInd_ Field mailRecvInd to be setted.
   */
  public void setMailRecvInd( String mailRecvInd_ )
  {
	m_mailRecvInd = mailRecvInd_;
  }

  /**
   * @return Returns offclMailRecvInd.
   */
  public String getOffclMailRecvInd()
  {
	return m_offclMailRecvInd;
  }

  /**
   * @param offclMailRecvInd_ Field offclMailRecvInd to be setted.
   */
  public void setOffclMailRecvInd( String offclMailRecvInd_ )
  {
	m_offclMailRecvInd = offclMailRecvInd_;
  }

  /**
   * @return Returns offcrText.
   */
  public String getOffcrText()
  {
	return m_offcrText;
  }

  /**
   * @param offcrText_ Field offcrText to be setted.
   */
  public void setOffcrText( String offcrText_ )
  {
	m_offcrText = offcrText_;
  }

  /**
   * @return Returns prvtCustNbr.
   */
  public String getPrvtCustNbr()
  {
	return m_prvtCustNbr;
  }

  /**
   * @param prvtCustNbr_ Field prvtCustNbr to be setted.
   */
  public void setPrvtCustNbr( String prvtCustNbr_ )
  {
	m_prvtCustNbr = prvtCustNbr_;
  }

  /**
   * @return Returns prvtKeyNbr.
   */
  public String getPrvtKeyNbr()
  {
	return m_prvtKeyNbr;
  }

  /**
   * @param prvtKeyNbr_ Field prvtKeyNbr to be setted.
   */
  public void setPrvtKeyNbr( String prvtKeyNbr_ )
  {
	m_prvtKeyNbr = prvtKeyNbr_;
  }

  /**
   * @return Returns results.
   */
  public DataSet getResults()
  {
	return m_results;
  }

  /**
   * @param results_ Field results to be setted.
   */
  public void setResults( DataSet results_ )
  {
	m_results = results_;
  }

  /**
   * @return Returns selectedCustNbr.
   */
  public String getSelectedCustNbr()
  {
	return m_selectedCustNbr;
  }

  /**
   * @param selectedCustNbr_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
	setCustNbrSrc( selectedCustNbr_ );
  }

  /**
   * @return Returns wealthPotnlCode.
   */
  public String getWealthPotnlCode()
  {
	return m_wealthPotnlCode;
  }

  /**
   * @param wealthPotnlCode_ Field wealthPotnlCode to be setted.
   */
  public void setWealthPotnlCode( String wealthPotnlCode_ )
  {
	m_wealthPotnlCode = wealthPotnlCode_;
  }

  /**
   * @return Returns wealthPotnlCodeDomain.
   */
  public DataSet getWealthPotnlCodeDomain()
  {
	return m_wealthPotnlCodeDomain;
  }

  /**
   * @param wealthPotnlCodeDomain_ Field wealthPotnlCodeDomain to be setted.
   */
  public void setWealthPotnlCodeDomain( DataSet wealthPotnlCodeDomain_ )
  {
	m_wealthPotnlCodeDomain = wealthPotnlCodeDomain_;
  }

  /**
   * @return Returns custText.
   */
  public String getCustText()
  {
	return m_custText;
  }

  /**
   * @param custText_ Field custText to be setted.
   */
  public void setCustText( String custText_ )
  {
	m_custText = custText_;
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
	setOffcrNbrSrc(selectedOffcrNbr_);
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
   * @return Returns mailRecvIndDomain.
   */
  public DataSet getMailRecvIndDomain()
  {
	return m_mailRecvIndDomain;
  }
  /**
   * @param mailRecvIndDomain_ Field mailRecvIndDomain to be setted.
   */
  public void setMailRecvIndDomain( DataSet mailRecvIndDomain_ )
  {
	m_mailRecvIndDomain = mailRecvIndDomain_;
  }
  /**
   * @return Returns offclMailRecvIndDomain.
   */
  public DataSet getOffclMailRecvIndDomain()
  {
	return m_offclMailRecvIndDomain;
  }
  /**
   * @param offclMailRecvIndDomain_ Field offclMailRecvIndDomain to be setted.
   */
  public void setOffclMailRecvIndDomain( DataSet offclMailRecvIndDomain_ )
  {
	m_offclMailRecvIndDomain = offclMailRecvIndDomain_;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getCustNbrSrc()
   */
  public String getCustNbrSrc()
  {
	return m_custNbrSrc;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setCustNbrSrc(java.lang.String)
   */
  public void setCustNbrSrc( String custNbrSrc_ )
  {
	 m_custNbrSrc = custNbrSrc_;
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
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getSelectedCustNbrList()
   */
  public String getSelectedCustNbrList()
  {
	return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setSelectedListCustNbrList(java.lang.String)
   */
  public void setSelectedCustNbrList( String selectedCustNbr_ )
  {
	setCustNbrSrc(selectedCustNbr_);
  }
 
  
  /**
   * @return Returns custPrvtStatCode.
   */
  public String getCustPrvtStatCode()
  {
	return m_custPrvtStatCode;
  }
  /**
   * @param custPrvtStatCode_ Field custPrvtStatCode to be setted.
   */
  public void setCustPrvtStatCode( String custPrvtStatCode_ )
  {
	m_custPrvtStatCode = custPrvtStatCode_;
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
  
//INICIO======================Alteraçao G&P	
  //Tag para indicar se o cliente mudou e consequentemente limpar a tela
  private boolean clrScreen;
 
/**
 * @return clrScreen
 */
public boolean getClrScreen() {
	return clrScreen;
}

/**
 * @param clrScreenParm
 */
public void setClrScreen(boolean clrScreenParm) {
	clrScreen = clrScreenParm;
}
//FIM=========================Alteraçao G&P
/**
 * @return
 */
public boolean getOffcrClear() {
	return m_offcrClear;
}

/**
 * @param string
 */
public void setOffcrClear(boolean m_offcrClear_) {
	m_offcrClear = m_offcrClear_;
}

/**
 * @return
 */
public String getPrvtCustTypeCode() {
	return m_prvtCustTypeCode;
}

/**
 * @return
 */
public DataSet getPrvtCustTypeCodeDomain() {
	return m_prvtCustTypeCodeDomain;
}

/**
 * @param string
 */
public void setPrvtCustTypeCode(String prvtCustTypeCode_) {
	m_prvtCustTypeCode = prvtCustTypeCode_;
}

/**
 * @param set
 */
public void setPrvtCustTypeCodeDomain(DataSet prvtCustTypeCodeDomain_) {
	m_prvtCustTypeCodeDomain = prvtCustTypeCodeDomain_;
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