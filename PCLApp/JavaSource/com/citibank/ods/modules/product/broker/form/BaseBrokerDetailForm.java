package com.citibank.ods.modules.product.broker.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplBrokerEntity;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BaseBrokerDetailFncVO;

/**
 * @author Hamilton Matos
 *  
 */

public class BaseBrokerDetailForm extends BaseForm implements BrokerDetailable
{

  // Endereco da corretora.
  private String m_bkrAddrText = "";

  // Valor do Limite de Credito em Dolar aprovado.
  private String m_bkrApprvCrLimDlrAmt = "";

  // Valordo Limite de Credito em Real aprovado.
  private String m_bkrApprvCrLimLcyAmt = "";

  // Data da aprovacao
  private String m_bkrApprvDate = "";

  // Codigo do status da aprovacao da corretora.
  private String m_bkrApprvStatCode = "";

  // Descricao do Andamento do Processo de aprovacao.
  private String m_bkrAuthProcSitText = "";

  // Codigo de Mercado na BMF em que a corretora atua.
  private String m_bkrBmfMktCode = "";

  // Codigo de Mercado na Bovespa em que a corretora atua.
  private String m_bkrBovespaMktCode = "";

  // CNPJ da Corretora.
  private String m_bkrCnpjNbr = "";

  // Campo de Observacao
  private String m_bkrCommentText = "";

  // Nome Completo da corretora
  private String m_bkrNameText = "";

  // Percentual de rebate (repasse) da BMF
  private String m_bkrRbtBmfRate = "";

  // Percentual de rebate (repasse) da Bovespa.
  private String m_bkrRbtBovespaRate = "";

  // Valor do Limite de Credito em Dolar solicitado.
  private String m_bkrReqCrLimDlrAmt = "";

  // Valor do Limite de Credito em reais solicitado.
  private String m_bkrReqCrLimLcyAmt = "";

  // Data de Solicitacao da aprovacao.
  private String m_bkrReqDate = "";

  // Data de renovacao
  private String m_bkrRnwDate = "";

  // Descricao dos Servicos Prestados
  private String m_bkrSuplServText = "";

  // Data e hora da ultima atualiza cao efetuada pelo usuario
  private String m_lastUpdDate = "";

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro
  private String m_lastUpdUserId = "";

  /**
   * @return Returns m_bkrAddrText.
   */
  public String getBkrAddrText()
  {
    return m_bkrAddrText;
  }

  /**
   * @param bkrAddrText_ Field m_bkrAddrText to be setted.
   */
  public void setBkrAddrText( String bkrAddrText_ )
  {
    m_bkrAddrText = bkrAddrText_;
  }

  /**
   * @return Returns m_bkrApprvCrLimDlrAmt.
   */
  public String getBkrApprvCrLimDlrAmt()
  {
    return m_bkrApprvCrLimDlrAmt;
  }

  /**
   * @param bkrApprvCrLimDlrAmt_ Field m_bkrApprvCrLimDlrAmt to be setted.
   */
  public void setBkrApprvCrLimDlrAmt( String bkrApprvCrLimDlrAmt_ )
  {
    m_bkrApprvCrLimDlrAmt = bkrApprvCrLimDlrAmt_;
  }

  /**
   * @return Returns m_bkrApprvCrLimLcyAmt.
   */
  public String getBkrApprvCrLimLcyAmt()
  {
    return m_bkrApprvCrLimLcyAmt;
  }

  /**
   * @param bkrApprvCrLimLcyAmt_ Field m_bkrApprvCrLimLcyAmt to be setted.
   */
  public void setBkrApprvCrLimLcyAmt( String bkrApprvCrLimLcyAmt_ )
  {
    m_bkrApprvCrLimLcyAmt = bkrApprvCrLimLcyAmt_;
  }

  /**
   * @return Returns m_bkrApprvDate.
   */
  public String getBkrApprvDate()
  {
    return m_bkrApprvDate;
  }

  /**
   * @param bkrApprvDate_ Field m_bkrApprvDate to be setted.
   */
  public void setBkrApprvDate( String bkrApprvDate_ )
  {
    m_bkrApprvDate = bkrApprvDate_;
  }

  /**
   * @return Returns m_bkrApprvStatCode.
   */
  public String getBkrApprvStatCode()
  {
    return m_bkrApprvStatCode;
  }

  /**
   * @param bkrApprvStatCode_ Field m_bkrApprvStatCode to be setted.
   */
  public void setBkrApprvStatCode( String bkrApprvStatCode_ )
  {
    m_bkrApprvStatCode = bkrApprvStatCode_;
  }

  /**
   * @return Returns m_bkrAuthProcSitText.
   */
  public String getBkrAuthProcSitText()
  {
    return m_bkrAuthProcSitText;
  }

  /**
   * @param bkrAuthProcSitText_ Field m_bkrAuthProcSitText to be setted.
   */
  public void setBkrAuthProcSitText( String bkrAuthProcSitText_ )
  {
    m_bkrAuthProcSitText = bkrAuthProcSitText_;
  }

  /**
   * @return Returns m_bkrBmfMktCode.
   */
  public String getBkrBmfMktCode()
  {
    return m_bkrBmfMktCode;
  }

  /**
   * @param bkrBmfMktCode_ Field m_bkrBmfMktCode to be setted.
   */
  public void setBkrBmfMktCode( String bkrBmfMktCode_ )
  {
    m_bkrBmfMktCode = bkrBmfMktCode_;
  }

  /**
   * @return Returns m_bkrBovespaMktCode.
   */
  public String getBkrBovespaMktCode()
  {
    return m_bkrBovespaMktCode;
  }

  /**
   * @param bkrBovespaMktCode_ Field m_bkrBovespaMktCode to be setted.
   */
  public void setBkrBovespaMktCode( String bkrBovespaMktCode_ )
  {
    m_bkrBovespaMktCode = bkrBovespaMktCode_;
  }

  /**
   * @return Returns m_bkrCnpjNbr.
   */
  public String getBkrCnpjNbr()
  {
    return m_bkrCnpjNbr;
  }

  /**
   * @param bkrCnpjNbr_ Field m_bkrCnpjNbr to be setted.
   */
  public void setBkrCnpjNbr( String bkrCnpjNbr_ )
  {
    m_bkrCnpjNbr = removeMask( bkrCnpjNbr_ );
  }

  /**
   * @return Returns m_bkrCommentText.
   */
  public String getBkrCommentText()
  {
    return m_bkrCommentText;
  }

  /**
   * @param bkrCommentText_ Field m_bkrCommentText to be setted.
   */
  public void setBkrCommentText( String bkrCommentText_ )
  {
    m_bkrCommentText = bkrCommentText_;
  }

  /**
   * @return Returns m_bkrNameText.
   */
  public String getBkrNameText()
  {
    return m_bkrNameText;
  }

  /**
   * @param bkrNameText_ Field m_bkrNameText to be setted.
   */
  public void setBkrNameText( String bkrNameText_ )
  {
    m_bkrNameText = bkrNameText_;
  }

  /**
   * @return Returns m_bkrRbtBmfRate.
   */
  public String getBkrRbtBmfRate()
  {
    return m_bkrRbtBmfRate;
  }

  /**
   * @param bkrRbtBmfRate_ Field m_bkrRbtBmfRate to be setted.
   */
  public void setBkrRbtBmfRate( String bkrRbtBmfRate_ )
  {
    m_bkrRbtBmfRate = bkrRbtBmfRate_;
  }

  /**
   * @return Returns m_bkrRbtBovespaRate.
   */
  public String getBkrRbtBovespaRate()
  {
    return m_bkrRbtBovespaRate;
  }

  /**
   * @param bkrRbtBovespaRate_ Field m_bkrRbtBovespaRate to be setted.
   */
  public void setBkrRbtBovespaRate( String bkrRbtBovespaRate_ )
  {
    m_bkrRbtBovespaRate = bkrRbtBovespaRate_;
  }

  /**
   * @return Returns m_bkrReqCrLimDlrAmt.
   */
  public String getBkrReqCrLimDlrAmt()
  {
    return m_bkrReqCrLimDlrAmt;
  }

  /**
   * @param bkrReqCrLimDlrAmt_ Field m_bkrReqCrLimDlrAmt to be setted.
   */
  public void setBkrReqCrLimDlrAmt( String bkrReqCrLimDlrAmt_ )
  {
    m_bkrReqCrLimDlrAmt = bkrReqCrLimDlrAmt_;
  }

  /**
   * @return Returns m_bkrReqCrLimLcyAmt.
   */
  public String getBkrReqCrLimLcyAmt()
  {
    return m_bkrReqCrLimLcyAmt;
  }

  /**
   * @param bkrReqCrLimLcyAmt_ Field m_bkrReqCrLimLcyAmt to be setted.
   */
  public void setBkrReqCrLimLcyAmt( String bkrReqCrLimLcyAmt_ )
  {
    m_bkrReqCrLimLcyAmt = bkrReqCrLimLcyAmt_;
  }

  /**
   * @return Returns m_bkrReqDate.
   */
  public String getBkrReqDate()
  {
    return m_bkrReqDate;
  }

  /**
   * @param bkrReqDate_ Field m_bkrReqDate to be setted.
   */
  public void setBkrReqDate( String bkrReqDate_ )
  {
    m_bkrReqDate = bkrReqDate_;
  }

  /**
   * @return Returns m_bkrRnwDate.
   */
  public String getBkrRnwDate()
  {
    return m_bkrRnwDate;
  }

  /**
   * @param bkrRnwDate_ Field m_bkrRnwDate to be setted.
   */
  public void setBkrRnwDate( String bkrRnwDate_ )
  {
    m_bkrRnwDate = bkrRnwDate_;
  }

  /**
   * @return Returns m_bkrSuplServText.
   */
  public String getBkrSuplServText()
  {
    return m_bkrSuplServText;
  }

  /**
   * @param bkrSuplServText_ Field m_bkrSuplServText to be setted.
   */
  public void setBkrSuplServText( String bkrSuplServText_ )
  {
    m_bkrSuplServText = bkrSuplServText_;
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.broker.form.BrokerDetailable#getSelectedBkrCnpjNbr()
   */
  public String getSelectedBkrCnpjNbr()
  {
    return m_bkrCnpjNbr;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.broker.form.BrokerDetailable#setSelectedBkrCnpjNbr(java.lang.String)
   */
  public void setSelectedBkrCnpjNbr( String setSelectedBkrCnpjNbr_ )
  {
    setBkrCnpjNbr( setSelectedBkrCnpjNbr_ );
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_CNPJ_NBR_DESCRIPTION,
                                    m_bkrCnpjNbr,
                                    BaseTplBrokerEntity.C_BKR_CNPJ_NBR_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_NAME_TEXT_DESCRIPTION,
                                    m_bkrNameText,
                                    BaseTplBrokerEntity.C_BKR_NAME_TEXT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_ADDR_TEXT_DESCRIPTION,
                                    m_bkrAddrText,
                                    BaseTplBrokerEntity.C_BKR_ADDR_TEXT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_BMF_MKT_CODE_DESCRIPTION,
                                    m_bkrBmfMktCode,
                                    BaseTplBrokerEntity.C_BKR_BMF_MKT_CODE_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_BOVESPA_MKT_CODE_DESCRIPTION,
                                    m_bkrBovespaMktCode,
                                    BaseTplBrokerEntity.C_BKR_BOVESPA_MKT_CODE_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_RBT_BMF_RATE_DESCRIPTION,
                                    m_bkrRbtBmfRate,
                                    BaseTplBrokerEntity.C_BKR_RBT_BMF_RATE_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_RBT_BOVESPA_RATE_DESCRIPTION,
                                    m_bkrRbtBovespaRate,
                                    BaseTplBrokerEntity.C_BKR_RBT_BOVESPA_RATE_SIZE,
                                    errors );

    ODSValidator.validateDate(
                               BaseBrokerDetailFncVO.C_BKR_REQ_DATE_DESCRIPTION,
                               m_bkrReqDate, errors );

    ODSValidator.validateDate(
                               BaseBrokerDetailFncVO.C_BKR_RNW_DATE_DESCRIPTION,
                               m_bkrRnwDate, errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_APPRV_STAT_CODE_DESCRIPTION,
                                    m_bkrApprvStatCode,
                                    BaseTplBrokerEntity.C_BKR_APPRV_STAT_CODE_SIZE,
                                    errors );

    ODSValidator.validateDate(
                               BaseBrokerDetailFncVO.C_BKR_APPRV_DATE_DESCRIPTION,
                               m_bkrApprvDate, errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_AUTH_PROC_SIT_TEXT_DESCRIPTION,
                                    m_bkrAuthProcSitText,
                                    BaseTplBrokerEntity.C_BKR_AUTH_PROC_SIT_TEXT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_REQ_CR_LIM_LCY_AMT_DESCRIPTION,
                                    m_bkrReqCrLimLcyAmt,
                                    BaseTplBrokerEntity.C_BKR_REQ_CR_LIM_LCY_AMT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_APPRV_CR_LIM_LCY_AMT_DESCRIPTION,
                                    m_bkrApprvCrLimLcyAmt,
                                    BaseTplBrokerEntity.C_BKR_APPRV_CR_LIM_LCY_AMT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_REQ_CR_LIM_DLR_AMT_DESCRIPTION,
                                    m_bkrReqCrLimDlrAmt,
                                    BaseTplBrokerEntity.C_BKR_REQ_CR_LIM_DLR_AMT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_APPRV_CR_LIM_DLR_AMT_DESCRIPTION,
                                    m_bkrApprvCrLimDlrAmt,
                                    BaseTplBrokerEntity.C_BKR_APPRV_CR_LIM_DLR_AMT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_SUPL_SERV_TEXT_DESCRIPTION,
                                    m_bkrSuplServText,
                                    BaseTplBrokerEntity.C_BKR_SUPL_SERV_TEXT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseBrokerDetailFncVO.C_BKR_COMMENT_TEXT_DESCRIPTION,
                                    m_bkrCommentText,
                                    BaseTplBrokerEntity.C_BKR_COMMENT_TEXT_SIZE,
                                    errors );

    ODSValidator.validateBigDecimal(
                                     BaseBrokerDetailFncVO.C_BKR_RBT_BMF_RATE_DESCRIPTION,
                                     m_bkrRbtBmfRate,
                                     BaseTplBrokerEntity.C_BKR_RBT_BMF_RATE_SIZE,
                                     BaseTplBrokerEntity.C_BKR_RBT_BMF_RATE_SCALE,
                                     errors );

    ODSValidator.validateBigDecimal(
                                     BaseBrokerDetailFncVO.C_BKR_RBT_BOVESPA_RATE_DESCRIPTION,
                                     m_bkrRbtBovespaRate,
                                     BaseTplBrokerEntity.C_BKR_RBT_BOVESPA_RATE_SIZE,
                                     BaseTplBrokerEntity.C_BKR_RBT_BOVESPA_RATE_SCALE,
                                     errors );

    ODSValidator.validateBigDecimal(
                                     BaseBrokerDetailFncVO.C_BKR_REQ_CR_LIM_LCY_AMT_DESCRIPTION,
                                     m_bkrReqCrLimLcyAmt,
                                     BaseTplBrokerEntity.C_BKR_REQ_CR_LIM_LCY_AMT_SIZE,
                                     BaseTplBrokerEntity.C_BKR_REQ_CR_LIM_LCY_AMT_SCALE,
                                     errors );

    ODSValidator.validateBigDecimal(
                                     BaseBrokerDetailFncVO.C_BKR_APPRV_CR_LIM_LCY_AMT_DESCRIPTION,
                                     m_bkrApprvCrLimLcyAmt,
                                     BaseTplBrokerEntity.C_BKR_APPRV_CR_LIM_LCY_AMT_SIZE,
                                     BaseTplBrokerEntity.C_BKR_APPRV_CR_LIM_LCY_AMT_SCALE,
                                     errors );
    ODSValidator.validateBigDecimal(
                                     BaseBrokerDetailFncVO.C_BKR_REQ_CR_LIM_DLR_AMT_DESCRIPTION,
                                     m_bkrReqCrLimDlrAmt,
                                     BaseTplBrokerEntity.C_BKR_REQ_CR_LIM_DLR_AMT_SIZE,
                                     BaseTplBrokerEntity.C_BKR_REQ_CR_LIM_DLR_AMT_SCALE,
                                     errors );
    ODSValidator.validateBigDecimal(
                                     BaseBrokerDetailFncVO.C_BKR_APPRV_CR_LIM_DLR_AMT_DESCRIPTION,
                                     m_bkrApprvCrLimDlrAmt,
                                     BaseTplBrokerEntity.C_BKR_APPRV_CR_LIM_DLR_AMT_SIZE,
                                     BaseTplBrokerEntity.C_BKR_APPRV_CR_LIM_DLR_AMT_SCALE,
                                     errors );

    return errors;
  }
}