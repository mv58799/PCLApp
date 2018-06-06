/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;

/**
 * @author bruno.zanetti
 * 
 */
public abstract class BaseCustomerPrvtCmplDetailFncVO extends BaseODSFncVO
{

  /**
   * @param baseTplCustomerPrvtCmplEntity_ Field baseTplCustomerPrvtCmplEntity
   *          to be setted.
   */

  private String m_custText;
  
  private String m_clickedSearch;

  private String m_offcrText;
  

  public static final String C_CUST_NBR_DESCRIPTION = "Numero do Cliente";

  public static final String C_EM_NBR_DESCRIPTION = "Número EM";

  public static final String C_MAIL_RECV_IND_DESCRIPTION = "Indicador de Envio de Mala Direta";

  public static final String C_OFFCL_MAIL_RECV_IND_DESCRIPTION = "Indicador de Envio de Corresponência Oficial";

  public static final String C_PRVT_CUST_NBR_DESCRIPTION = "Número do Cliente Private";

  public static final String C_PRVT_KEY_NBR_DESCRIPTION = "Número Principal do Cliente Private";

  public static final String C_WEALTH_POTNL_CODE_DESCRIPTION = "Código de Potencial de Receita";

  public static final String C_CLASS_CMPLC_CODE_DESCRIPTION = "Código de Classificação Compliance";

  public static final String C_OFFCR_NBR_DESCRIPTION = "Número de Officer";

  public static final String C_GLB_REVEN_SYS_OFFCR_NBR_DESCRIPTION = "Número de Officer Global";

  private boolean isFromSearch = false;
  
  private boolean isOffcrClear = false;
  
  //Status do cliente - Combo (Dataset)
  private DataSet m_custPrvtStatCodeDomain = null;

  public void setBaseTplCustomerPrvtCmplEntity(
											   BaseTplCustomerPrvtCmplEntity baseTplCustomerPrvtCmplEntity_ )
  {
	baseTplCustomerPrvtCmplEntity = baseTplCustomerPrvtCmplEntity_;
  }

  /**
   * @return Returns the baseTplCustomerPrvtCmplEntity.
   */
  public BaseTplCustomerPrvtCmplEntity getBaseTplCustomerPrvtCmplEntity()
  {
	return baseTplCustomerPrvtCmplEntity;

  }

  protected BaseTplCustomerPrvtCmplEntity baseTplCustomerPrvtCmplEntity = null;

  /**
   * Comment for <code>REVEN_POTNL_CODEDomain</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_wealthPotnlCodeDomain;
  
  /*
   * Combo para indicadores
   */
  
  private DataSet m_mailRecvIndDomain;
  
  /*
   * Combo para indicadores
   */
  private DataSet m_offclMailRecvIndDomain;

  /**
   * @return Returns mailRecvIndDomai.
   */
  public DataSet getMailRecvIndDomain()
  {
	return m_mailRecvIndDomain;
  }
  /**
   * @param mailRecvIndDomai_ Field mailRecvIndDomai to be setted.
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
  /**
   * Comment for <code>CLASS_CMPLC_CODEDomain</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_classCmplcCodeDomain;
  
  /**
   * Comment for <code>CLASS_CMPLC_CODEDomain</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_prvtCustTypeCodeDomain;

  /**
   * Comment for <code>OFFCR_NBRDomain</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_offcrNbrDomain;

  /**
   * Comment for <code>results</code>
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  private DataSet m_results;

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
   * @return Returns the offcrNbrDomain.
   */
  public DataSet getOffcrNbrDomain()
  {
	return m_offcrNbrDomain;
  }

  /**
   * @param offcrNbrDomain_ The offcrNbrDomain to set.
   */
  public void setOffcrNbrDomain( DataSet offcrNbrDomain_ )
  {
	m_offcrNbrDomain = offcrNbrDomain_;
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
   * @return Returns the revenPotnlCodeDomain.
   */
  public DataSet getWealthPotnlCodeDomain()
  {
	return m_wealthPotnlCodeDomain;
  }

  /**
   * @param revenPotnlCodeDomain_ The revenPotnlCodeDomain to set.
   */
  public void setWealthPotnlCodeDomain( DataSet wealthPotnlCodeDomain_ )
  {
	m_wealthPotnlCodeDomain = wealthPotnlCodeDomain_;
  }

  /**
   * @return Returns isFromSearch.
   */
  public boolean isFromSearch()
  {
	return isFromSearch;
  }

  /**
   * @param isFromSearch_ Field isFromSearch to be setted.
   */
  public void setFromSearch( boolean isFromSearch_ )
  {
	isFromSearch = isFromSearch_;
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
 * @return
 */
public boolean isOffcrClear() {
	return isOffcrClear;
}

/**
 * @param b
 */
public void setOffcrClear(boolean isOffcrClear_) {
	isOffcrClear = isOffcrClear_;
}

/**
 * @return Retorno o Dominio do Codigo do Cliente.
 */
public DataSet getPrvtCustTypeCodeDomain() {
	return m_prvtCustTypeCodeDomain;
}

/**
 * @param DataSet prvtCustCodeDomain_.
 * Seta o Dominio do Codigo do Cliente.
 */
public void setPrvtCustTypeCodeDomain(DataSet prvtCustTypeCodeDomain_) {
	m_prvtCustTypeCodeDomain = prvtCustTypeCodeDomain_;
}

}