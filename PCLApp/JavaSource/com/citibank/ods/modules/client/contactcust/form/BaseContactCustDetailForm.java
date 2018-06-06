package com.citibank.ods.modules.client.contactcust.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.BaseContactCustDetailFncVO;

/**
 * @author l.braga
 *  
 */

public class BaseContactCustDetailForm extends BaseForm implements
    ContactCustDetailable
{

  // Numero do Contato
  private String m_ctcNbr = "";

  // Numero do Cliente no CMS.
  private String m_custNbrSrc = "";

  // Nome do cliente.
  private String m_custFullNameText = "";

  // Nome Completo do Cliente/ Empresa.
  private String m_fullNameText = "";

  // Nome Completo do Segundo Contato.
  private String m_fullNameText_2 = "";

  // Nome Completo do Terceiro Contato.
  private String m_fullNameText_3 = "";

  // Data e Hora que o usuario aprovou o registro cadastrado
  private String m_lastAuthDate = "";

  // Codigo do usuario (SOE ID) que aprovou o cadastro do registro
  private String m_lastAuthUserId = "";

  // Data e hora da ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro
  private String m_lastUpdUserId = "";

  // Codigo de Area do Telefone.
  private String m_phoneDddCode = "";

  // Numero do Ramal Telefonico.
  private String m_phoneExtnNbr = "";

  // Numero do Telefone
  private String m_phoneNbr = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";

  /**
   * @return Returns m_ctcNbr.
   */
  public String getCtcNbr()
  {
    return m_ctcNbr;
  }

  /**
   * @param ctcNbr_ Field m_ctcNbr to be setted.
   */
  public void setCtcNbr( String ctcNbr_ )
  {
    m_ctcNbr = ctcNbr_;
  }

  /**
   * @return Returns m_fullNameText.
   */
  public String getFullNameText()
  {
    return m_fullNameText;
  }

  /**
   * @param fullNameText_ Field m_fullNameText to be setted.
   */
  public void setFullNameText( String fullNameText_ )
  {
    m_fullNameText = fullNameText_;
  }

  /**
   * @return
   */
  public String getFullNameText_2() {
	  return m_fullNameText_2;
  }

  /**
   * @return
   */
  public String getFullNameText_3() {
	  return m_fullNameText_3;
  }

  /**
   * @param string
   */
  public void setFullNameText_2(String fullNameText_2_) {
	  m_fullNameText_2 = fullNameText_2_;
  }

  /**
   * @param string
   */
  public void setFullNameText_3(String fullNameText_3_) {
	  m_fullNameText_3 = fullNameText_3_;
  }

  /**
   * @return Returns m_lastAuthDate.
   */
  public String getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
   * @param lastAuthDate_ Field m_lastAuthDate to be setted.
   */
  public void setLastAuthDate( String lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
   * @return Returns m_lastAuthUserId.
   */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
   * @param lastAuthUserId_ Field m_lastAuthUserId to be setted.
   */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
  }

  /**
   * @return Returns m_lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field m_lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns m_lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field m_lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns m_phoneDddCode.
   */
  public String getPhoneDddCode()
  {
    return m_phoneDddCode;
  }

  /**
   * @param phoneDddCode_ Field m_phoneDddCode to be setted.
   */
  public void setPhoneDddCode( String phoneDddCode_ )
  {
    m_phoneDddCode = phoneDddCode_;
  }

  /**
   * @return Returns m_phoneExtnNbr.
   */
  public String getPhoneExtnNbr()
  {
    return m_phoneExtnNbr;
  }

  /**
   * @param phoneExtnNbr_ Field m_phoneExtnNbr to be setted.
   */
  public void setPhoneExtnNbr( String phoneExtnNbr_ )
  {
    m_phoneExtnNbr = phoneExtnNbr_;
  }

  /**
   * @return Returns m_phoneNbr.
   */
  public String getPhoneNbr()
  {
    return m_phoneNbr;
  }

  /**
   * @param phoneNbr_ Field m_phoneNbr to be setted.
   */
  public void setPhoneNbr( String phoneNbr_ )
  {
    m_phoneNbr = phoneNbr_;
  }

  /**
   * @return Returns m_recStatCode.
   */
  public String getRecStatCode()
  {
    return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field m_recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
    m_recStatCode = recStatCode_;
  }

  /**
   * @return Returns NUll.
   */
  public String getSelectedCtcNbr()
  {
    return null;
  }

  /**
   * @param ctcNbr_ Field m_ctcNbr to be setted. O metodo seta o valor
   *          selecionado na tela.
   */
  public void setSelectedCtcNbr( String ctcNbr_ )
  {
    setCtcNbr( ctcNbr_ );

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

  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  public void setCustNbrSrc( String custNbr_ )
  {
    m_custNbrSrc = custNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.contactcust.form.ContactCustDetailable#getSelectedCustNbr()
   */
  public String getSelectedCustNbr()
  {
    return m_custNbrSrc;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.contactcust.form.ContactCustDetailable#setSelectedCustNbr(java.lang.String)
   */
  public void setSelectedCustNbr( String custNbr_ )
  {
    setCustNbrSrc( custNbr_ );
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseContactCustDetailFncVO.C_CTC_NBR_DESCRIPTION,
                                     m_ctcNbr,
                                     BaseTplContactCustEntity.C_CTC_NBR_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseContactCustDetailFncVO.C_FULL_NAME_TEXT_DESCRIPTION,
                                    m_fullNameText,
                                    BaseTplContactCustEntity.C_FULL_NAME_TEXT_SIZE,
                                    errors );

    ODSValidator.validateBigInteger(
                                     BaseContactCustDetailFncVO.C_PHONE_DDD_CODE_DESCRIPTION,
                                     m_phoneDddCode,
                                     BaseTplContactCustEntity.C_PHONE_DDD_CODE_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseContactCustDetailFncVO.C_PHONE_NBR_DESCRIPTION,
                                     m_phoneNbr,
                                     BaseTplContactCustEntity.C_PHONE_NBR_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseContactCustDetailFncVO.C_PHONE_EXTN_NBR_DESCRIPTION,
                                     m_phoneExtnNbr,
                                     BaseTplContactCustEntity.C_PHONE_EXTN_NBR_SIZE,
                                     errors );

    return errors;
  }

}