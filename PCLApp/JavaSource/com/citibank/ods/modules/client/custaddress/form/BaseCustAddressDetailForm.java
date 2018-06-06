package com.citibank.ods.modules.client.custaddress.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;

/**
 * @author l.braga
 *  
 */

public class BaseCustAddressDetailForm extends BaseForm implements
    CustAddressDetailable
{

  // Numero do Cliente
  private String m_custNbr = "";

  // Cidade
  private String m_addrCityText = "";

  // Complemento
  private String m_addrCmplText = "";

  // País
  private String m_addrCntryCode = "";

  // Nome do contato no endereço
  private String m_addrContactName = "";

  // Endereço
  private String m_addrNameText = "";

  // Bairro
  private String m_addrNeighbText = "";

  // Sequência do endereço
  private String m_addrSeqNbr = "";

  // Estado
  private String m_addrStateCode = "";

  // Tipo de endereço
  private String m_addrTypeCode = "";

  // Código de área do celular(DDD)
  private String m_cellAreaCode = "";

  // Indica se cliente receberá mala direta no celular
  private String m_cellMailInd = "";

  // Código da empresa operadora do celular
  private String m_cellOpCode = "";

  // Número do celular
  private String m_cellPhoneNbr = "";

  // Indica se cliente receberá mala direta no e-mail
  private String m_emailMailInd = "";

  // E-mail
  private String m_emailText = "";

  // Código de área do fax
  private String m_faxAreaCode = "";

  // Número do Fax
  private String m_faxNbr = "";

  // Número do mail Box
  private String m_mailBoxNbr = "";

  // Código de área do telefone do cliente
  private String m_phoneAreaCode = "";

  // Número de identificação da extensão do telefone
  private String m_phoneExtnNbr = "";

  // Número do telefone
  private String m_phoneNbr = "";

  // Código da empresa operadora da linha telefônica
  private String m_phoneOpCode = "";

  // Código de área do telex
  private String m_telexAreaCode = "";

  // Número de identificação do telex
  private String m_telexNbr = "";

  // CEP
  private String m_zipCode = "";

  // Motivo da troca do cep pela rotina do correiro
  private String m_zipCodeChangeText = "";

  // Extensão do CEP
  private String m_zipExtnCode = "";

  // Tipo do Endereço - Combo
  private DataSet m_addrTypeCodeDomain = null;

  // Indicador de mala direta por celular - Combo
  private DataSet m_cellMailIndDomain = null;

  // Indicador de mala direta por email - Combo
  private DataSet m_emailMailIndDomain = null;
  
  private DataSet m_custMailResults = null;
  
  private DataSet m_custCellResults = null;

  /**
   * @return Returns m_addrCityText.
   */
  public String getAddrCityText()
  {
    return m_addrCityText;
  }

  /**
   * @param addrCityText_ Field m_addrCityText to be setted.
   */
  public void setAddrCityText( String addrCityText_ )
  {
    m_addrCityText = addrCityText_;
  }

  /**
   * @return Returns m_addrCmplText.
   */
  public String getAddrCmplText()
  {
    return m_addrCmplText;
  }

  /**
   * @param addrCmplText_ Field m_addrCmplText to be setted.
   */
  public void setAddrCmplText( String addrCmplText_ )
  {
    m_addrCmplText = addrCmplText_;
  }

  /**
   * @return Returns m_addrCntryCode.
   */
  public String getAddrCntryCode()
  {
    return m_addrCntryCode;
  }

  /**
   * @param addrCntryCode_ Field m_addrCntryCode to be setted.
   */
  public void setAddrCntryCode( String addrCntryCode_ )
  {
    m_addrCntryCode = addrCntryCode_;
  }

  /**
   * @return Returns m_addrContactName.
   */
  public String getAddrContactName()
  {
    return m_addrContactName;
  }

  /**
   * @param addrContactName_ Field m_addrContactName to be setted.
   */
  public void setAddrContactName( String addrContactName_ )
  {
    m_addrContactName = addrContactName_;
  }

  /**
   * @return Returns m_addrNameText.
   */
  public String getAddrNameText()
  {
    return m_addrNameText;
  }

  /**
   * @param addrNameText_ Field m_addrNameText to be setted.
   */
  public void setAddrNameText( String addrNameText_ )
  {
    m_addrNameText = addrNameText_;
  }

  /**
   * @return Returns m_addrNeighbText.
   */
  public String getAddrNeighbText()
  {
    return m_addrNeighbText;
  }

  /**
   * @param addrNeighbText_ Field m_addrNeighbText to be setted.
   */
  public void setAddrNeighbText( String addrNeighbText_ )
  {
    m_addrNeighbText = addrNeighbText_;
  }

  /**
   * @return Returns m_addrSeqNbr.
   */
  public String getAddrSeqNbr()
  {
    return m_addrSeqNbr;
  }

  /**
   * @param addrSeqNbr_ Field m_addrSeqNbr to be setted.
   */
  public void setAddrSeqNbr( String addrSeqNbr_ )
  {
    m_addrSeqNbr = addrSeqNbr_;
  }

  /**
   * @return Returns m_addrStateCode.
   */
  public String getAddrStateCode()
  {
    return m_addrStateCode;
  }

  /**
   * @param addrStateCode_ Field m_addrStateCode to be setted.
   */
  public void setAddrStateCode( String addrStateCode_ )
  {
    m_addrStateCode = addrStateCode_;
  }

  /**
   * @return Returns m_addrTypeCode.
   */
  public String getAddrTypeCode()
  {
    return m_addrTypeCode;
  }

  /**
   * @param addrTypeCode_ Field m_addrTypeCode to be setted.
   */
  public void setAddrTypeCode( String addrTypeCode_ )
  {
    m_addrTypeCode = addrTypeCode_;
  }

  /**
   * @return Returns m_cellAreaCode.
   */
  public String getCellAreaCode()
  {
    return m_cellAreaCode;
  }

  /**
   * @param cellAreaCode_ Field m_cellAreaCode to be setted.
   */
  public void setCellAreaCode( String cellAreaCode_ )
  {
    m_cellAreaCode = cellAreaCode_;
  }

  /**
   * @return Returns m_cellMailInd.
   */
  public String getCellMailInd()
  {
    return m_cellMailInd;
  }

  /**
   * @param cellMailInd_ Field m_cellMailInd to be setted.
   */
  public void setCellMailInd( String cellMailInd_ )
  {
    m_cellMailInd = cellMailInd_;
  }

  /**
   * @return Returns m_cellOpCode.
   */
  public String getCellOpCode()
  {
    return m_cellOpCode;
  }

  /**
   * @param cellOpCode_ Field m_cellOpCode to be setted.
   */
  public void setCellOpCode( String cellOpCode_ )
  {
    m_cellOpCode = cellOpCode_;
  }

  /**
   * @return Returns m_cellPhoneNbr.
   */
  public String getCellPhoneNbr()
  {
    return m_cellPhoneNbr;
  }

  /**
   * @param cellPhoneNbr_ Field m_cellPhoneNbr to be setted.
   */
  public void setCellPhoneNbr( String cellPhoneNbr_ )
  {
    m_cellPhoneNbr = cellPhoneNbr_;
  }

  /**
   * @return Returns m_custNbr.
   */
  public String getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_ Field m_custNbr to be setted.
   */
  public void setCustNbr( String custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * @return Returns m_emailMailInd.
   */
  public String getEmailMailInd()
  {
    return m_emailMailInd;
  }

  /**
   * @param emailMailInd_ Field m_emailMailInd to be setted.
   */
  public void setEmailMailInd( String emailMailInd_ )
  {
    m_emailMailInd = emailMailInd_;
  }

  /**
   * @return Returns m_emailText.
   */
  public String getEmailText()
  {
    return m_emailText;
  }

  /**
   * @param emailText_ Field m_emailText to be setted.
   */
  public void setEmailText( String emailText_ )
  {
    m_emailText = emailText_;
  }

  /**
   * @return Returns m_faxAreaCode.
   */
  public String getFaxAreaCode()
  {
    return m_faxAreaCode;
  }

  /**
   * @param faxAreaCode_ Field m_faxAreaCode to be setted.
   */
  public void setFaxAreaCode( String faxAreaCode_ )
  {
    m_faxAreaCode = faxAreaCode_;
  }

  /**
   * @return Returns m_faxNbr.
   */
  public String getFaxNbr()
  {
    return m_faxNbr;
  }

  /**
   * @param faxNbr_ Field m_faxNbr to be setted.
   */
  public void setFaxNbr( String faxNbr_ )
  {
    m_faxNbr = faxNbr_;
  }

  /**
   * @return Returns m_mailBoxNbr.
   */
  public String getMailBoxNbr()
  {
    return m_mailBoxNbr;
  }

  /**
   * @param mailBoxNbr_ Field m_mailBoxNbr to be setted.
   */
  public void setMailBoxNbr( String mailBoxNbr_ )
  {
    m_mailBoxNbr = mailBoxNbr_;
  }

  /**
   * @return Returns m_phoneAreaCode.
   */
  public String getPhoneAreaCode()
  {
    return m_phoneAreaCode;
  }

  /**
   * @param phoneAreaCode_ Field m_phoneAreaCode to be setted.
   */
  public void setPhoneAreaCode( String phoneAreaCode_ )
  {
    m_phoneAreaCode = phoneAreaCode_;
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
   * @return Returns m_phoneOpCode.
   */
  public String getPhoneOpCode()
  {
    return m_phoneOpCode;
  }

  /**
   * @param phoneOpCode_ Field m_phoneOpCode to be setted.
   */
  public void setPhoneOpCode( String phoneOpCode_ )
  {
    m_phoneOpCode = phoneOpCode_;
  }

  /**
   * @return Returns m_telexAreaCode.
   */
  public String getTelexAreaCode()
  {
    return m_telexAreaCode;
  }

  /**
   * @param telexAreaCode_ Field m_telexAreaCode to be setted.
   */
  public void setTelexAreaCode( String telexAreaCode_ )
  {
    m_telexAreaCode = telexAreaCode_;
  }

  /**
   * @return Returns m_telexNbr.
   */
  public String getTelexNbr()
  {
    return m_telexNbr;
  }

  /**
   * @param telexNbr_ Field m_telexNbr to be setted.
   */
  public void setTelexNbr( String telexNbr_ )
  {
    m_telexNbr = telexNbr_;
  }

  /**
   * @return Returns m_zipCode.
   */
  public String getZipCode()
  {
    return m_zipCode;
  }

  /**
   * @param zipCode_ Field m_zipCode to be setted.
   */
  public void setZipCode( String zipCode_ )
  {
    m_zipCode = zipCode_;
  }

  /**
   * @return Returns m_zipCodeChangeText.
   */
  public String getZipCodeChangeText()
  {
    return m_zipCodeChangeText;
  }

  /**
   * @param zipCodeChangeText_ Field m_zipCodeChangeText to be setted.
   */
  public void setZipCodeChangeText( String zipCodeChangeText_ )
  {
    m_zipCodeChangeText = zipCodeChangeText_;
  }

  /**
   * @return Returns m_zipExtnCode.
   */
  public String getZipExtnCode()
  {
    return m_zipExtnCode;
  }

  /**
   * @param zipExtnCode_ Field m_zipExtnCode to be setted.
   */
  public void setZipExtnCode( String zipExtnCode_ )
  {
    m_zipExtnCode = zipExtnCode_;
  }

  /**
   * @return Returns addrTypeCodeDomain.
   */
  public DataSet getAddrTypeCodeDomain()
  {
    return m_addrTypeCodeDomain;
  }

  /**
   * @param addrTypeCodeDomain_ Field addrTypeCodeDomain to be setted.
   */
  public void setAddrTypeCodeDomain( DataSet addrTypeCodeDomain_ )
  {
    m_addrTypeCodeDomain = addrTypeCodeDomain_;
  }

  /**
   * @return Returns cellMailIndDomain.
   */
  public DataSet getCellMailIndDomain()
  {
    return m_cellMailIndDomain;
  }

  /**
   * @param cellMailIndDomain_ Field cellMailIndDomain to be setted.
   */
  public void setCellMailIndDomain( DataSet cellMailIndDomain_ )
  {
    m_cellMailIndDomain = cellMailIndDomain_;
  }

  /**
   * @return Returns emailMailIndDomain.
   */
  public DataSet getEmailMailIndDomain()
  {
    return m_emailMailIndDomain;
  }

  /**
   * @param emailMailIndDomain_ Field emailMailIndDomain to be setted.
   */
  public void setEmailMailIndDomain( DataSet emailMailIndDomain_ )
  {
    m_emailMailIndDomain = emailMailIndDomain_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.custaddress.form.CustAddressDetailable#getSelectedCustNbr()
   */
  public String getSelectedCustNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.custaddress.form.CustAddressDetailable#setSelectedCustNbr(java.lang.String)
   */
  public void setSelectedCustNbr( String CustNbr_ )
  {
    setCustNbr( CustNbr_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.custaddress.form.CustAddressDetailable#getSelectedAddrSeqNbr()
   */
  public String getSelectedAddrSeqNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.custaddress.form.CustAddressDetailable#setSelectedAddrSeqNbr(java.lang.String)
   */
  public void setSelectedAddrSeqNbr( String addrSeqNbr_ )
  {
    setAddrSeqNbr( addrSeqNbr_ );
  }
  /**
   * @return Returns custCellResults.
   */
  public DataSet getCustCellResults()
  {
    return m_custCellResults;
  }
  /**
   * @param custCellResults_ Field custCellResults to be setted.
   */
  public void setCustCellResults( DataSet custCellResults_ )
  {
    m_custCellResults = custCellResults_;
  }
  /**
   * @return Returns custMailResults.
   */
  public DataSet getCustMailResults()
  {
    return m_custMailResults;
  }
  /**
   * @param custMailResults_ Field custMailResults to be setted.
   */
  public void setCustMailResults( DataSet custMailResults_ )
  {
    m_custMailResults = custMailResults_;
  }
}