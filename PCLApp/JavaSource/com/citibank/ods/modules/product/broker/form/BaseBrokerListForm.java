package com.citibank.ods.modules.product.broker.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BaseBrokerListFncVO;

/**
 * @author Hamilton Matos,Jul 22, 2007
 *  
 */

public class BaseBrokerListForm extends BaseForm implements BrokerDetailable
{

  private String m_bkrCnpjNbrSrc;

  private String m_bkrNameTextSrc;

  private DataSet m_results;

  private String m_selectedBkrCnpjNbr;

  /**
   * @return Returns bkrCnpjNbrSrc.
   */
  public String getBkrCnpjNbrSrc()
  {
    return m_bkrCnpjNbrSrc;
  }

  /**
   * @param bkrCnpjNbrSrc_ Field bkrCnpjNbrSrc to be setted.
   */
  public void setBkrCnpjNbrSrc( String bkrCnpjNbrSrc_ )
  {
    m_bkrCnpjNbrSrc = removeMask( bkrCnpjNbrSrc_ );

  }

  /**
   * @return Returns bkrNameTextSrc.
   */
  public String getBkrNameTextSrc()
  {
    return m_bkrNameTextSrc;
  }

  /**
   * @param bkrNameTextSrc_ Field bkrNameTextSrc to be setted.
   */
  public void setBkrNameTextSrc( String bkrNameTextSrc_ )
  {
    m_bkrNameTextSrc = bkrNameTextSrc_;
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
   * @return Returns selectedBkrCnpjNbr.
   */
  public String getSelectedBkrCnpjNbr()
  {
    return m_selectedBkrCnpjNbr;
  }

  /**
   * @param selectedBkrCnpjNbr_ Field selectedBkrCnpjNbr to be setted.
   */
  public void setSelectedBkrCnpjNbr( String selectedBkrCnpjNbr_ )
  {
    m_selectedBkrCnpjNbr = selectedBkrCnpjNbr_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseBrokerListFncVO.C_BKR_CNPJ_NBR_DESCRIPTION,
                                    m_bkrCnpjNbrSrc,
                                    BaseTplBrokerEntity.C_BKR_CNPJ_NBR_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerListFncVO.C_BKR_NAME_TEXT_DESCRIPTION,
                                    m_bkrNameTextSrc,
                                    BaseTplBrokerEntity.C_BKR_NAME_TEXT_SIZE,
                                    errors );

    return errors;
  }

}