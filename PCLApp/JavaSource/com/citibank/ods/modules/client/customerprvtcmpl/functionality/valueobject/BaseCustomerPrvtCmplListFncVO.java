/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

/**
 * @author bruno.zanetti
 *  
 */
public abstract class BaseCustomerPrvtCmplListFncVO extends BaseFncVO
{

  private String m_clickedSearch;

  private DataSet m_wealthPotnlCodeDomain;

  private DataSet m_classCmplcCodeDomain;
  
  private DataSet m_prvtCustTypeCodeDomain;

  private DataSet m_offcrNbrDomain;

  private DataSet m_results;

  private String m_custTextSrc;

  private String m_offcrTextSrc;

  private BigInteger m_classCmplcCodeSrc;
  
  private BigInteger m_prvtCustTypeCodeSrc;
  
  private BigInteger m_offcrNbrSrc;

  private BigInteger m_prvtKeyNbrSrc;

  private BigInteger m_prvtCustNbrSrc;

  private BigInteger m_wealthPotnlCodeSrc;

  private BigInteger m_custNbrSrc;

  private String m_emNbrSrc;

  private BigInteger m_glbRevenSysOffcrNbrSrc;

  private BigInteger m_selectedCustNbr;

  //Nome do cliente
  private String m_custFullNameSrc = "";

  //Status do cliente
  private String m_custPrvtStatCodeSrc = "";

  //Status do cliente - Combo (Dataset)
  private DataSet m_custPrvtStatCodeDomain = null;

  public static final String C_EM_NBR_SRC_DESCRIPTION = "Número EM";

  public static final String C_CUST_TEXT_SRC_DESCRIPTION = "Nome do Cliente";

  public static final String C_GLB_REVEN_SYS_OFFCR_NBR_SRC_DESCRIPTION = "Número de Officer Global";

  public static final String C_PRVT_CUST_NBR_SRC_DESCRIPTION = "Número do Cliente Private";

  public static final String C_PRVT_KEY_NBR_SRC_DESCRIPTION = "Número Principal do Cliente Private";

  public static final String C_WEALTH_POTNL_CODE_SRC_DESCRIPTION = "Potencial de Receita";

  public static final String C_CUST_NBR_SRC_DESCRIPTION = "Número do Cliente";

  public static final String C_OFFCR_NBR_SRC_DESCRIPTION = "Número do Officer";

  public static final String C_CLASS_CMPLC_CODE_SRC_DESCRIPTION = "Classificação Compliance";

  public static final String C_OFFCR_TEXT_SRC_DESCRIPTION = "Nome do Officer";

  private boolean isFromSearch = false;

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
  public BigInteger getClassCmplcCodeSrc()
  {
    return m_classCmplcCodeSrc;
  }

  /**
   * @param classCmplcCodeSrc_ The classCmplcCodeSrc to set.
   */
  public void setClassCmplcCodeSrc( BigInteger classCmplcCodeSrc_ )
  {
    m_classCmplcCodeSrc = classCmplcCodeSrc_;
  }

  /**
   * @return Returns the custNbrSrc.
   */
  public BigInteger getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbrSrc_ The custNbrSrc to set.
   */
  public void setCustNbrSrc( BigInteger custNbrSrc_ )
  {
    m_custNbrSrc = custNbrSrc_;
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
   * @return Returns the glbRevenSysOffcrNbrSrc.
   */
  public BigInteger getGlbRevenSysOffcrNbrSrc()
  {
    return m_glbRevenSysOffcrNbrSrc;
  }

  /**
   * @param glbRevenSysOffcrNbrSrc_ The glbRevenSysOffcrNbrSrc to set.
   */
  public void setGlbRevenSysOffcrNbrSrc( BigInteger glbRevenSysOffcrNbrSrc_ )
  {
    m_glbRevenSysOffcrNbrSrc = glbRevenSysOffcrNbrSrc_;
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
   * @return Returns the offcrNbrSrc.
   */
  public BigInteger getOffcrNbrSrc()
  {
    return m_offcrNbrSrc;
  }

  /**
   * @param offcrNbrSrc_ The offcrNbrSrc to set.
   */
  public void setOffcrNbrSrc( BigInteger offcrNbrSrc_ )
  {
    m_offcrNbrSrc = offcrNbrSrc_;
  }

  /**
   * @return Returns the prvtCustNbrSrc.
   */
  public BigInteger getPrvtCustNbrSrc()
  {
    return m_prvtCustNbrSrc;
  }

  /**
   * @param prvtCustNbrSrc_ The prvtCustNbrSrc to set.
   */
  public void setPrvtCustNbrSrc( BigInteger prvtCustNbrSrc_ )
  {
    m_prvtCustNbrSrc = prvtCustNbrSrc_;
  }

  /**
   * @return Returns the prvtKeyNbrSrc.
   */
  public BigInteger getPrvtKeyNbrSrc()
  {
    return m_prvtKeyNbrSrc;
  }

  /**
   * @param prvtKeyNbrSrc_ The prvtKeyNbrSrc to set.
   */
  public void setPrvtKeyNbrSrc( BigInteger prvtKeyNbrSrc_ )
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
   * @return Returns the wealthPotnlCodeSrc.
   */
  public BigInteger getWealthPotnlCodeSrc()
  {
    return m_wealthPotnlCodeSrc;
  }

  /**
   * @param wealthPotnlCodeSrc_ The wealthPotnlCodeSrc to set.
   */
  public void setWealthPotnlCodeSrc( BigInteger wealthPotnlCodeSrc_ )
  {
    m_wealthPotnlCodeSrc = wealthPotnlCodeSrc_;
  }

  /**
   * @return Returns selectedCustNbr.
   */
  public BigInteger getSelectedCustNbr()
  {
    return m_selectedCustNbr;
  }

  /**
   * @param selectedCustNbr_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbr( BigInteger selectedCustNbr_ )
  {
    m_selectedCustNbr = selectedCustNbr_;
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

  /**
   * @return Returns offcrTextSrc.
   */
  public String getOffcrTextSrc()
  {
    return m_offcrTextSrc;
  }

  /**
   * @param offcrTextSrc_ Field offcrTextSrc to be setted.
   */
  public void setOffcrTextSrc( String offcrTextSrc_ )
  {
    m_offcrTextSrc = offcrTextSrc_;
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
   * @return Returns custFullNameSrc.
   */
  public String getCustFullNameSrc()
  {
    return m_custFullNameSrc;
  }

  /**
   * @param custFullNameSrc_ Field custFullNameSrc to be setted.
   */
  public void setCustFullNameSrc( String custFullNameSrc_ )
  {
    m_custFullNameSrc = custFullNameSrc_;
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
 * @return Retorna o Dominio do Código do Tipo de Cliente.
 */
public DataSet getPrvtCustTypeCodeDomain() {
	return m_prvtCustTypeCodeDomain;
}

/**
 * @return Retorna o Source do Código do Tipo de Cliente.
 */
public BigInteger getPrvtCustTypeCodeSrc() {
	return m_prvtCustTypeCodeSrc;
}

/**
 * @param DataSet prvtCustTypeCodeDomain_.
 * Seta o Dominio do Código do Tipo de Cliente.
 */
public void setPrvtCustTypeCodeDomain(DataSet prvtCustTypeCodeDomain_) {
	m_prvtCustTypeCodeDomain = prvtCustTypeCodeDomain_;
}

/**
 * @param BigInteger prvtCustTypeCodeSrc_.
 * Seta o Source do Código do Tipo de Cliente.
 */
public void setPrvtCustTypeCodeSrc(BigInteger prvtCustTypeCodeSrc_) {
	m_prvtCustTypeCodeSrc = prvtCustTypeCodeSrc_;
}

}