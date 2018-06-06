package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseIpDocPrvtListFncVO extends BaseODSFncVO
{
  public static final String C_CUST_NBR_DESCRIPTION = "Numero do Cliente";

  public static final String C_IP_DOC_CODE_DESCRIPTION = "Codigo do Documento IP";

  public static final String C_IP_DOC_TEXT_DESCRIPTION = "Descricao do Instrucao Permanente";

  public static final String C_IP_INVST_CUR_ACCT_IND_DESCRIPTION = "Indicador de Utilizacao de Conta CCI";

  public static final String C_LAST_AUTH_USER_ID_DESCRIPTION = "Codigo do Usuario que Aprovou o Cadastro do Registro.";

  public static final String C_LAST_AUTH_DATE_DESCRIPTION = "Data e Hora que o Usuario Aprovou o Registro Cadastrado.";

  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.";

  public static final String C_LAST_UPD_DATE_DESCRIPTION = "Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.";

  public static final String C_REC_STAT_CODE_DESCRIPTION = "Status do Registro";

  // Numero do Cliente no CMS (Customer Number)
  private BigInteger m_custNbrSrc;

  // Codigo do Documento IP
  private BigInteger ipDocCodeSrc;

  // Descricao do Instrucao Permanente
  private String m_ipDocTextSrc;

  // Nome Completo do Cliente/Empre sa.
  private String m_custFullNameText;

  private DataSet m_results;

  private String m_ipInvstCurAcctInd;

  private DataSet m_ipInvstCurAcctIndDomain;

  private String selectedCustNbr;

  private String selectedIpDocCode;
  
  private String m_clickedSearch = "";
 
  //Variável de controle do botão buscar
  private boolean m_isFromSearch = false;
  
  //Variável de controle da chamada da tela 
  private String m_fromCurAcct ="";  
   
  /**
   * @return Returns the ipDocCodeSrc.
   */
  public BigInteger getIpDocCodeSrc()
  {
    return ipDocCodeSrc;
  }

  /**
   * @param ipDocCodeSrc_ The ipDocCodeSrc to set.
   */
  public void setIpDocCodeSrc( BigInteger ipDocCodeSrc_ )
  {
    ipDocCodeSrc = ipDocCodeSrc_;
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
   * @return Returns the ipDocTextSrc.
   */
  public String getIpDocTextSrc()
  {
    return m_ipDocTextSrc;
  }

  /**
   * @param ipDocTextSrc_ The ipDocTextSrc to set.
   */
  public void setIpDocTextSrc( String ipDocTextSrc_ )
  {
    m_ipDocTextSrc = ipDocTextSrc_;
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
   * @return Returns the selectedCustNbr.
   */
  public String getSelectedCustNbr()
  {
    return selectedCustNbr;
  }

  /**
   * @param selectedCustNbr_ The selectedCustNbr to set.
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
    selectedCustNbr = selectedCustNbr_;
  }

  /**
   * @return Returns the selectedIpDocCode.
   */
  public String getSelectedIpDocCode()
  {
    return selectedIpDocCode;
  }

  /**
   * @param selectedIpDocCode_ The selectedIpDocCode to set.
   */
  public void setSelectedIpDocCode( String selectedIpDocCode_ )
  {
    selectedIpDocCode = selectedIpDocCode_;
  }

  /**
   * @return Returns ipInvstCurAcctInd.
   */
  public String getIpInvstCurAcctInd()
  {
    return m_ipInvstCurAcctInd;
  }

  /**
   * @param ipInvstCurAcctInd_ Field ipInvstCurAcctInd to be setted.
   */
  public void setIpInvstCurAcctInd( String ipInvstCurAcctInd_ )
  {
    m_ipInvstCurAcctInd = ipInvstCurAcctInd_;
  }

  /**
   * @return Returns ipInvstCurAcctIndDomain.
   */
  public DataSet getIpInvstCurAcctIndDomain()
  {
    return m_ipInvstCurAcctIndDomain;
  }

  /**
   * @param ipInvstCurAcctIndDomain_ Field ipInvstCurAcctIndDomain to be setted.
   */
  public void setIpInvstCurAcctIndDomain( DataSet ipInvstCurAcctIndDomain_ )
  {
    m_ipInvstCurAcctIndDomain = ipInvstCurAcctIndDomain_;
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
   * @return Returns isFromSearch.
   */
  public boolean isFromSearch()
  {
    return m_isFromSearch;
  }
  /**
   * @param isFromSearch_ Field isFromSearch to be setted.
   */
  public void setFromSearch( boolean isFromSearch_ )
  {
    m_isFromSearch = isFromSearch_;
  }
  
  /**
   * @return Returns m_fromCurAcct.
   */
  public String getFromCurAcct()
  {
    return m_fromCurAcct;
  }

  /**
   * @param fromCurAcct_ Field fromCurAcct_ to be setted.
   */
  public void setFromCurAcct( String fromCurAcct_ )
  {
    m_fromCurAcct = fromCurAcct_;
  }
}