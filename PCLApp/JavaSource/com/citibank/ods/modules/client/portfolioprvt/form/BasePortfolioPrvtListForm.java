package com.citibank.ods.modules.client.portfolioprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplPortfolioPrvtEntity;
import com.citibank.ods.modules.client.officer.form.OfficerSearchable;
import com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject.BasePortfolioPrvtDetailFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.portfolioPrvt.form;
 * @version 1.0
 * @author l.braga,16/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BasePortfolioPrvtListForm extends BaseForm implements
    PortfolioPrvtDetailable, OfficerSearchable
{

  // Descricao da Carteira
  private String m_portfNameTextSrc = "";

  // Officer da carteira
  private String m_offcrNbrSrc = "";

  // nome do officer
  private String m_portfCodeSrc = "";

  // nome do officer
  private String m_offcrNameTextSrc = "";

  // DataSet como os resultados do banco.
  private DataSet m_results;

  // Varialvel de controle.
  private String m_selectedPortfCode = "";

  private String m_clickedSearch;

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BasePortfolioPrvtDetailFncVO.C_PORTF_NAME_TEXT_DESCRIPTION,
                                    m_portfNameTextSrc,
                                    BaseTplPortfolioPrvtEntity.C_PORTF_NAME_TEXT_SIZE,
                                    errors );

    ODSValidator.validateBigInteger(
                                     BasePortfolioPrvtDetailFncVO.C_PORTF_OFFCR_NBR_DESCRIPTION,
                                     m_offcrNbrSrc,
                                     BaseTplPortfolioPrvtEntity.C_PORTF_OFFCR_NBR_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BasePortfolioPrvtDetailFncVO.C_OFFCR_NAME_TEXT_DESCRIPTION,
                                    m_offcrNameTextSrc,
                                    BaseTplPortfolioPrvtEntity.C_OFFCR_NAME_TEXT_SIZE,
                                    errors );

    return errors;
  }

  /**
   * @return Returns offcrNameTextSrc.
   */
  public String getOffcrNameTextSrc()
  {
    return m_offcrNameTextSrc;
  }

  /**
   * @param offcrNameTextSrc_ Field offcrNameTextSrc to be setted.
   */
  public void setOffcrNameTextSrc( String offcrNameTextSrc_ )
  {
    m_offcrNameTextSrc = offcrNameTextSrc_;
  }

  /**
   * @return Returns portfNameTextSrc.
   */
  public String getPortfNameTextSrc()
  {
    return m_portfNameTextSrc;
  }

  /**
   * @param portfNameTextSrc_ Field portfNameTextSrc to be setted.
   */
  public void setPortfNameTextSrc( String portfNameTextSrc_ )
  {
    m_portfNameTextSrc = portfNameTextSrc_;
  }

  /**
   * @return Returns portfOffcrNbrSrc.
   */
  public String getOffcrNbrSrc()
  {
    return m_offcrNbrSrc;
  }

  /**
   * @param portfOffcrNbrSrc_ Field portfOffcrNbrSrc to be setted.
   */
  public void setOffcrNbrSrc( String portfOffcrNbrSrc_ )
  {
    m_offcrNbrSrc = portfOffcrNbrSrc_;
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
   * @return Returns selectedPortfCode.
   */
  public String getSelectedPortfCode()
  {
    return m_selectedPortfCode;
  }

  /**
   * @param selectedPortfCode_ Field selectedPortfCode to be setted.
   */
  public void setSelectedPortfCode( String selectedPortfCode_ )
  {
    m_selectedPortfCode = selectedPortfCode_;
  }

  /**
   * @return Returns portfCodeSrc.
   */
  public String getPortfCodeSrc()
  {
    return m_portfCodeSrc;
  }

  /**
   * @param portfCodeSrc_ Field portfCodeSrc to be setted.
   */
  public void setPortfCodeSrc( String portfCodeSrc_ )
  {
    m_portfCodeSrc = portfCodeSrc_;
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

  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }
}