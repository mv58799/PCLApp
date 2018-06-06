package com.citibank.ods.modules.client.officercmpl.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplOfficerCmplEntity;
import com.citibank.ods.modules.client.officer.form.OfficerSearchable;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.BaseOfficerCmplListFncVO;
/**
 * @author bruno.zanetti
 *  
 */
public class BaseOfficerCmplListForm extends BaseForm implements
    OfficerCmplDetailable, OfficerSearchable
{

  private String m_offcrIntlNbrSrc = "";

  private String m_officerNbrSrc = "";

  private String m_offcrTypeCodeSrc = "";

  private DataSet m_offcrTypeCodeDomain = null;

  private DataSet m_results = null;

  private String m_selectedOffcrNbr = null;

  private String m_officerNameTextSrc = "";
  
  private String m_clickedSearch = "";

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
  /**
   * @return Returns the offcrIntlNbrSrc.
   */
  public String getOffcrIntlNbrSrc()
  {
    return m_offcrIntlNbrSrc;
  }
  /**
   * @param offcrIntlNbrSrc_ The offcrIntlNbrSrc to set.
   */
  public void setOffcrIntlNbrSrc( String offcrIntlNbrSrc_ )
  {
    m_offcrIntlNbrSrc = offcrIntlNbrSrc_;
  }
  /**
   * @return Returns the offcrTypeCodeDomain.
   */
  public DataSet getOffcrTypeCodeDomain()
  {
    return m_offcrTypeCodeDomain;
  }
  /**
   * @param offcrTypeCodeDomain_ The offcrTypeCodeDomain to set.
   */
  public void setOffcrTypeCodeDomain( DataSet offcrTypeCodeDomain_ )
  {
    m_offcrTypeCodeDomain = offcrTypeCodeDomain_;
  }
  /**
   * @return Returns the offcrTypeCodeSrc.
   */
  public String getOffcrTypeCodeSrc()
  {
    return m_offcrTypeCodeSrc;
  }
  /**
   * @param offcrTypeCodeSrc_ The offcrTypeCodeSrc to set.
   */
  public void setOffcrTypeCodeSrc( String offcrTypeCodeSrc_ )
  {
    m_offcrTypeCodeSrc = offcrTypeCodeSrc_;
  }
  /**
   * @return Returns the officerNameTextSrc.
   */
  public String getOfficerNameTextSrc()
  {
    return m_officerNameTextSrc;
  }
  /**
   * @param officerNameTextSrc_ The officerNameTextSrc to set.
   */
  public void setOfficerNameTextSrc( String officerNameTextSrc_ )
  {
    m_officerNameTextSrc = officerNameTextSrc_;
  }
  /**
   * @return Returns the officerNbrSrc.
   */
  public String getOfficerNbrSrc()
  {
    return m_officerNbrSrc;
  }
  /**
   * @param officerNbrSrc_ The officerNbrSrc to set.
   */
  public void setOfficerNbrSrc( String officerNbrSrc_ )
  {
    m_officerNbrSrc = officerNbrSrc_;
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
   * @return Returns the selectedOffcrNbr.
   */
  public String getSelectedOffcrNbr()
  {
    return m_selectedOffcrNbr;
  }
  /**
   * @param selectedOffcrNbr_ The selectedOffcrNbr to set.
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    m_selectedOffcrNbr = selectedOffcrNbr_;
    m_officerNbrSrc = m_selectedOffcrNbr;
  }
  
  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseOfficerCmplListFncVO.C_OFFCR_NBR_DESCRIPTION,
                                     m_officerNbrSrc,
                                     BaseTplOfficerCmplEntity.C_OFFCR_NBR_SIZE,
                                     errors );
    ODSValidator.validateBigInteger(
                                     BaseOfficerCmplListFncVO.C_OFFCR_INTL_DESCRIPTION,
                                     m_offcrIntlNbrSrc,
                                     BaseTplOfficerCmplEntity.C_OFFCR_INTL_NBR_SIZE,
                                     errors );

    return errors;
  }
  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearchable#getOffcrNbrSrc()
   */
  public String getOffcrNbrSrc()
  {
    return m_officerNbrSrc;
  }
  
  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearchable#setOffcrNbrSrc(java.lang.String)
   */
  public void setOffcrNbrSrc( String offcrNbrSrc_ )
  {
    m_officerNbrSrc = offcrNbrSrc_;
  }

}