package com.citibank.ods.modules.client.contactcust.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.BaseContactCustListFncVO;
import com.citibank.ods.modules.client.customerprvt.form.CustomerPrvtDetailable;

/**
 * @author leandro.braga
 */

public class BaseContactCustListForm extends BaseForm implements
    ContactCustDetailable, CustomerPrvtDetailable, ContactCustSearchable
{

  // Numero do Cliente selecionado
  private String m_selectedCustNbr = "";

  // Numero do Cliente
  private String m_custNbrSrc = "";

  private String m_fullNameTextSrc = "";

  // Nome do cliente
  private String m_custFullNameText = "";

  // CFF ou CNPJ do cliente
  private String m_custCpfCnpjNbr = "";

  // Numero do DDD
  private String m_phoneDDDCodeSrc = "";

  // Número de telefone
  private String m_phoneNbrSrc = "";

  // Número de telefone
  private String m_phoneExtnNbrSrc = "";

  // DataSet como os resultados do banco.
  private DataSet m_results;

  // Varialvel de controle.
  private String m_selectedCtcNbr = "";

  // Número do contato selecionado no grid.
  private String m_selectedCtcNbrSrc = "";

  private String m_ctcNbrSrc;

  /**
   * @return Returns phoneExtnNbrSrc.
   */
  public String getPhoneExtnNbrSrc()
  {
    return m_phoneExtnNbrSrc;
  }

  /**
   * @param phoneExtnNbrSrc_ Field phoneExtnNbrSrc to be setted.
   */
  public void setPhoneExtnNbrSrc( String phoneExtnNbrSrc_ )
  {
    m_phoneExtnNbrSrc = phoneExtnNbrSrc_;
  }

  /**
   * @param ctcNbrSrc_ Field ctcNbrSrc to be setted.
   */
  public void setCtcNbrSrc( String ctcNbrSrc_ )
  {
    m_ctcNbrSrc = ctcNbrSrc_;
  }

  /**
   * @return Returns ctcNbr.
   */
  public String getCtcNbrSrc()
  {
    return m_ctcNbrSrc;
  }

  /**
   * @param ctcNbr_ Field ctcNbr to be setted.
   */
  public void setCtcNbr( String ctcNbrSrc_ )
  {
    m_ctcNbrSrc = ctcNbrSrc_;
  }

  /**
   * @return Returns phoneDDDCode.
   */
  public String getPhoneDDDCodeSrc()
  {
    return m_phoneDDDCodeSrc;
  }

  /**
   * @param phoneDDDCode_ Field phoneDDDCode to be setted.
   */
  public void setPhoneDDDCodeSrc( String phoneDDDCodeSrc_ )
  {
    m_phoneDDDCodeSrc = phoneDDDCodeSrc_;
  }

  /**
   * @return Returns phoneNbr.
   */
  public String getPhoneNbrSrc()
  {
    return m_phoneNbrSrc;
  }

  /**
   * @param phoneNbr_ Field phoneNbr to be setted.
   */
  public void setPhoneNbrSrc( String phoneNbrSrc_ )
  {
    m_phoneNbrSrc = phoneNbrSrc_;
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
   * @return Returns selectedCtcNbr.
   */
  public String getSelectedCtcNbr()
  {
    return m_selectedCtcNbr;
  }

  /**
   * @param selectedCtcNbr_ Field selectedCtcNbr to be setted.
   */
  public void setSelectedCtcNbr( String selectedCtcNbr_ )
  {
    m_selectedCtcNbr = selectedCtcNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.contactcust.form.ContactCustDetailable#getSelectedCustNbr()
   */
  public String getSelectedCustNbr()
  {
    return m_selectedCustNbr;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.contactcust.form.ContactCustDetailable#setSelectedCustNbr(java.lang.String)
   */
  public void setSelectedCustNbr( String custNbr_ )
  {
    m_selectedCustNbr = custNbr_;
    setCustNbrSrc( custNbr_ );
  }

  /**
   * @return Returns custCpfCnpjNbr.
   */
  public String getCustCpfCnpjNbr()
  {
    return m_custCpfCnpjNbr;
  }

  /**
   * @param custCpfCnpjNbr_ Field custCpfCnpjNbr to be setted.
   */
  public void setCustCpfCnpjNbr( String custCpfCnpjNbr_ )
  {
    m_custCpfCnpjNbr = removeMask( custCpfCnpjNbr_ );
  }

  /**
   * @return Returns custFullNameText.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }

  /**
   * @param custFullNameText_ Field custFullNameText to be setted.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }

  /**
   * @return Returns fullNameTextSrc.
   */
  public String getFullNameTextSrc()
  {
    return m_fullNameTextSrc;
  }

  /**
   * @param fullNameTextSrc_ Field fullNameTextSrc to be setted.
   */
  public void setFullNameTextSrc( String fullNameTextSrc_ )
  {
    m_fullNameTextSrc = fullNameTextSrc_;
  }

  /**
   * @return Retorna o número do contato selecionado no grid.
   */
  public String getSelectedCtcNbrSrc()
  {
    return m_selectedCtcNbrSrc;
  }

  /**
   * @param selectedCtcNbrSrc_. Seta o número do contato selecionado.
   */
  public void setSelectedCtcNbrSrc( String selectedCtcNbrSrc_ )
  {
    m_selectedCtcNbrSrc = selectedCtcNbrSrc_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseContactCustListFncVO.C_CTC_NBR_DESCRIPTION,
                                     m_ctcNbrSrc,
                                     BaseTplContactCustEntity.C_CTC_NBR_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseContactCustListFncVO.C_FULL_NAME_TEXT_DESCRIPTION,
                                    m_fullNameTextSrc,
                                    BaseTplContactCustEntity.C_FULL_NAME_TEXT_SIZE,
                                    errors );

    ODSValidator.validateBigInteger(
                                     BaseContactCustListFncVO.C_PHONE_DDD_CODE_DESCRIPTION,
                                     m_phoneDDDCodeSrc,
                                     BaseTplContactCustEntity.C_PHONE_DDD_CODE_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseContactCustListFncVO.C_PHONE_NBR_DESCRIPTION,
                                     m_phoneNbrSrc,
                                     BaseTplContactCustEntity.C_PHONE_NBR_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseContactCustListFncVO.C_PHONE_EXTN_NBR_DESCRIPTION,
                                     m_phoneExtnNbrSrc,
                                     BaseTplContactCustEntity.C_PHONE_EXTN_NBR_SIZE,
                                     errors );

    return errors;
  }

}