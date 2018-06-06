package com.citibank.ods.modules.client.ipdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.ipdocprvt.form;
 * @version 1.0
 * @author l.braga,11/04/2007
 */

public class BaseIpDocPrvtListForm extends BaseForm implements
    IpDocPrvtDetailable, CustomerSearchable
{
  // Numero do Cliente no CMS (Customer Number)
  private String m_custNbrSrc = "";

  // Codigo do Documento IP
  private String m_ipDocCodeSrc = "";

  // Descricao do Instrucao Permanente
  private String m_ipDocTextSrc = "";

  // "Indicador de Utilizacao de Conta CCI Constraint: 'S' (Sim), 'N' (Nao)"
  private String m_ipInvstCurAcctIndSrc = "";

  // Data set com os valores de retorno
  private DataSet m_results = null;

  // Data set com os valores do controle indicador de utilização de CCI
  private DataSet m_ipInvstCurAcctIndDomain = null;

  // Nome Completo do Cliente/Empre sa.
  private String m_custFullNameText;

  private String m_selectedCustNbr = "";

  private String m_selectedIpDocCode = "";

  private String m_clickedSearch = "";

  private String m_selectedPrmntInstrCodeSrc = "";
  
  //Variável de verificação se vem da tela de Associação Ip x Conta Corrente
  private String m_fromCurAcct = "";


  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseIpDocPrvtListFncVO.C_CUST_NBR_DESCRIPTION,
                                     m_custNbrSrc,
                                     BaseTplIpDocPrvtEntity.C_CUST_NBR_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseIpDocPrvtListFncVO.C_IP_DOC_CODE_DESCRIPTION,
                                     m_ipDocCodeSrc,
                                     BaseTplIpDocPrvtEntity.C_IP_DOC_CODE_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseIpDocPrvtListFncVO.C_IP_DOC_TEXT_DESCRIPTION,
                                    m_ipDocTextSrc,
                                    BaseTplIpDocPrvtEntity.C_IP_DOC_TEXT_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseIpDocPrvtListFncVO.C_IP_INVST_CUR_ACCT_IND_DESCRIPTION,
                                    m_ipInvstCurAcctIndSrc,
                                    BaseTplIpDocPrvtEntity.C_IP_INVST_CUR_ACCT_IND_SIZE,
                                    errors );

    return errors;
  }

  /**
   * @return Returns custNbrSrc.
   */
  public String getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbrSrc_ Field custNbrSrc to be setted.
   */
  public void setCustNbrSrc( String custNbrSrc_ )
  {
    m_custNbrSrc = custNbrSrc_;
  }

  /**
   * @return Returns ipDocCodeSrc.
   */
  public String getIpDocCodeSrc()
  {
    return m_ipDocCodeSrc;
  }

  /**
   * @param ipDocCodeSrc_ Field ipDocCodeSrc to be setted.
   */
  public void setIpDocCodeSrc( String ipDocCodeSrc_ )
  {
    m_ipDocCodeSrc = ipDocCodeSrc_;
  }

  /**
   * @return Returns ipDocTextSrc.
   */
  public String getIpDocTextSrc()
  {
    return m_ipDocTextSrc;
  }

  /**
   * @param ipDocTextSrc_ Field ipDocTextSrc to be setted.
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
    return m_selectedCustNbr;
  }

  /**
   * @param selectedCustNbr_ The selectedCustNbr to set.
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
    m_selectedCustNbr = selectedCustNbr_;
  }

  /**
   * @return Returns the selectedIpDocCode.
   */
  public String getSelectedIpDocCode()
  {
    return m_selectedIpDocCode;
  }

  /**
   * @param selectedIpDocCode_ The selectedIpDocCode to set.
   */
  public void setSelectedIpDocCode( String selectedIpDocCode_ )
  {
    m_selectedIpDocCode = selectedIpDocCode_;
  }

  /**
   * @return Returns ipInvstCurAcctIndSrc.
   */
  public String getIpInvstCurAcctIndSrc()
  {
    return m_ipInvstCurAcctIndSrc;
  }

  /**
   * @param ipInvstCurAcctIndSrc_ Field ipInvstCurAcctIndSrc to be setted.
   */
  public void setIpInvstCurAcctIndSrc( String ipInvstCurAcctIndSrc_ )
  {
    m_ipInvstCurAcctIndSrc = ipInvstCurAcctIndSrc_;
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getSelectedCustNbrList()
   */
  public String getSelectedCustNbrList()
  {
    return m_selectedCustNbr;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setSelectedCustNbrList(java.lang.String)
   */
  public void setSelectedCustNbrList( String selectedCustNbr_ )
  {
    setCustNbrSrc( selectedCustNbr_ );
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
   * @return Returns selectedPrmntInstrCodeSrc.
   */
  public String getSelectedPrmntInstrCodeSrc()
  {
    return m_selectedPrmntInstrCodeSrc;
  }

  /**
   * @param selectedPrmntInstrCodeSrc_ Field selectedPrmntInstrCodeSrc to be
   *          setted.
   */
  public void setSelectedPrmntInstrCodeSrc( String selectedPrmntInstrCodeSrc_ )
  {
    m_selectedPrmntInstrCodeSrc = selectedPrmntInstrCodeSrc_;
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

/* (non-Javadoc)
 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getReltnNbr()
 */
public String getReltnNbr() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setReltnNbr(java.lang.String)
 */
public void setReltnNbr(String reltnNbr_) {
	// TODO Auto-generated method stub
	
}
}

