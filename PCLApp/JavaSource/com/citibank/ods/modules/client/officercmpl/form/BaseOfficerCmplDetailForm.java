/*
 * Created on Mar 2, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseOfficerCmplDetailForm extends BaseForm implements
    OfficerCmplDetailable, OfficerSearchable
{

  private String m_offcrNbr = "";

  private String m_offcrTypeCode = "";

  private String m_offcrIntlNbr = "";

  private DataSet m_offcrTypeCodeDomain = null;

  private String m_lastUpdDate = "";

  private String m_lastUpdUserId = "";

  private String m_recStatCode = "";

  private String m_offcrNameText = "";

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
   * @return Returns lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns offcrIntlNbr.
   */
  public String getOffcrIntlNbr()
  {
    return m_offcrIntlNbr;
  }

  /**
   * @param offcrIntlNbr_ Field offcrIntlNbr to be setted.
   */
  public void setOffcrIntlNbr( String offcrIntlNbr_ )
  {
    m_offcrIntlNbr = offcrIntlNbr_;
  }

  /**
   * @return Returns offcrNbr.
   */
  public String getOffcrNbr()
  {
    return m_offcrNbr;
  }

  /**
   * @param offcrNbr_ Field offcrNbr to be setted.
   */
  public void setOffcrNbr( String offcrNbr_ )
  {
    m_offcrNbr = offcrNbr_;
  }

  /**
   * @return Returns offcrTypeCode.
   */
  public String getOffcrTypeCode()
  {
    return m_offcrTypeCode;
  }

  /**
   * @param offcrTypeCode_ Field offcrTypeCode to be setted.
   */
  public void setOffcrTypeCode( String offcrTypeCode_ )
  {
    m_offcrTypeCode = offcrTypeCode_;
  }

  /**
   * @return Returns offcrTypeCodeDomain.
   */
  public DataSet getOffcrTypeCodeDomain()
  {
    return m_offcrTypeCodeDomain;
  }

  /**
   * @param offcrTypeCodeDomain_ Field offcrTypeCodeDomain to be setted.
   */
  public void setOffcrTypeCodeDomain( DataSet offcrTypeCodeDomain_ )
  {
    m_offcrTypeCodeDomain = offcrTypeCodeDomain_;
  }

  /**
   * @return Returns recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
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
                                     m_offcrNbr,
                                     BaseTplOfficerCmplEntity.C_OFFCR_NBR_SIZE,
                                     errors );
    ODSValidator.validateBigInteger(
                                     BaseOfficerCmplListFncVO.C_OFFCR_INTL_DESCRIPTION,
                                     m_offcrIntlNbr,
                                     BaseTplOfficerCmplEntity.C_OFFCR_INTL_NBR_SIZE,
                                     errors );

    return errors;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearchable#getOffcrNbrSrc()
   */
  public String getOffcrNbrSrc()
  {
    return m_offcrNbr;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearchable#setOffcrNbrSrc(java.lang.String)
   */
  public void setOffcrNbrSrc( String offcrNbrSrc_ )
  {
    //this.m_offcrNbr = offcrNbrSrc_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officercmpl.form.OfficerCmplDetailable#getSelectedOffcrNbr()
   */
  public String getSelectedOffcrNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.officer.form.OfficerSearchable#setSelectedOffcrNbr(java.lang.String)
   */
  public void setSelectedOffcrNbr( String selectedOffcrNbr_ )
  {
    m_offcrNbr = selectedOffcrNbr_;
  }

  /**
   * @return Returns offcrNameText.
   */
  public String getOffcrNameText()
  {
    return m_offcrNameText;
  }

  /**
   * @param offcrNameText_ Field offcrNameText to be setted.
   */
  public void setOffcrNameText( String offcrNameText_ )
  {
    m_offcrNameText = offcrNameText_;
  }

}