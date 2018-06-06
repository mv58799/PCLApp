package com.citibank.ods.modules.client.contactcust.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author hamilton.matos
 */

public class BaseContactCustListFncVO extends BaseODSFncVO
{

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCodeSrc = null;

  // Numero do Contato
  private BigInteger m_ctcNbrSrc = null;

  // Nome Completo do Cliente/ Empresa.
  private String m_fullNameTextSrc = null;

  // DataSet como os resultados do banco.
  private DataSet m_results;

  private BigInteger m_selectedCustNbr = null;

  // Numero do Cliente
  private BigInteger m_custNbrSrc = null;

  // Nome do cliente
  private String m_custFullNameText = null;

  // CFF ou CNPJ do cliente
  private BigInteger m_custCpfCnpjNbr = null;

  // Variavel de controle
  private BigInteger m_phoneDDDCodeSrc;

  // Variavel de controle
  private BigInteger m_phoneExtnNbrSrc;

  // Variavel de controle
  private BigInteger m_phoneNbrSrc;

  /**
   * Constante do numero do contato
   */
  public static final String C_CTC_NBR_DESCRIPTION = "Numero do Contato";

  /**
   * Constante do nome do contato
   */
  public static final String C_FULL_NAME_TEXT_DESCRIPTION = "Nome do Contato";

  /**
   * Constante do codigo DDD
   */
  public static final String C_PHONE_DDD_CODE_DESCRIPTION = "Código DDD";

  /**
   * Constante do numero do telefone
   */
  public static final String C_PHONE_NBR_DESCRIPTION = "Numero do Telefone";

  /**
   * Constante do ramal
   */
  public static final String C_PHONE_EXTN_NBR_DESCRIPTION = "Ramal ";

  /**
   * @return Returns ctcNbrSrc.
   */
  public BigInteger getCtcNbrSrc()
  {
    return m_ctcNbrSrc;
  }

  /**
   * @param ctcNbrSrc_ Field ctcNbrSrc to be setted.
   */
  public void setCtcNbrSrc( BigInteger ctcNbrSrc_ )
  {
    m_ctcNbrSrc = ctcNbrSrc_;
  }

  /**
   * @return Returns custCpfCnpjNbr.
   */
  public BigInteger getCustCpfCnpjNbr()
  {
    return m_custCpfCnpjNbr;
  }

  /**
   * @param custCpfCnpjNbr_ Field custCpfCnpjNbr to be setted.
   */
  public void setCustCpfCnpjNbr( BigInteger custCpfCnpjNbr_ )
  {
    m_custCpfCnpjNbr = custCpfCnpjNbr_;
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
   * @return Returns custNbr.
   */
  public BigInteger getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbr_ Field custNbr to be setted.
   */
  public void setCustNbrSrc( BigInteger custNbr_ )
  {
    m_custNbrSrc = custNbr_;
  }

  /**
   * @return Returns phoneDDDCodeSrc.
   */
  public BigInteger getPhoneDDDCodeSrc()
  {
    return m_phoneDDDCodeSrc;
  }

  /**
   * @param phoneDDDCodeSrc_ Field phoneDDDCodeSrc to be setted.
   */
  public void setPhoneDDDCodeSrc( BigInteger phoneDDDCodeSrc_ )
  {
    m_phoneDDDCodeSrc = phoneDDDCodeSrc_;
  }

  /**
   * @return Returns phoneExtnNbrSrc.
   */
  public BigInteger getPhoneExtnNbrSrc()
  {
    return m_phoneExtnNbrSrc;
  }

  /**
   * @param phoneExtnNbrSrc_ Field phoneExtnNbrSrc to be setted.
   */
  public void setPhoneExtnNbrSrc( BigInteger phoneExtnNbrSrc_ )
  {
    m_phoneExtnNbrSrc = phoneExtnNbrSrc_;
  }

  /**
   * @return Returns phoneNbrSrc.
   */
  public BigInteger getPhoneNbrSrc()
  {
    return m_phoneNbrSrc;
  }

  /**
   * @param phoneNbrSrc_ Field phoneNbrSrc to be setted.
   */
  public void setPhoneNbrSrc( BigInteger phoneNbrSrc_ )
  {
    m_phoneNbrSrc = phoneNbrSrc_;
  }

  /**
   * @return Returns recStatCodeSrc.
   */
  public String getRecStatCodeSrc()
  {
    return m_recStatCodeSrc;
  }

  /**
   * @param recStatCodeSrc_ Field recStatCodeSrc to be setted.
   */
  public void setRecStatCodeSrc( String recStatCodeSrc_ )
  {
    m_recStatCodeSrc = recStatCodeSrc_;
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
}