package com.citibank.ods.modules.client.curacctprmntinstr.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable;
import com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtSearchable;


//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.form;
 * @version 1.0
 * @author michele.monteiro,18/06/2007
 *  
 */

public class BaseCurAcctPrmntInstrListForm extends BaseForm implements
     CustomerSearchable, IpDocPrvtSearchable
{

  // Data Set para popular o grid.
  private DataSet m_results = null;

  //Tela origem destino do botão buscar
  private String m_clickedSearch = "";

  //Número do cliente
  private String m_custNbrSrc = "";

  //Código da instrução permanente
  private String m_prmntInstrCodeSrc = "";

  //Descrição da instrução permanente
  private String m_prmntInstrTextSrc = "";

  //Indicador de Conta CCI - Combo.
  private DataSet m_prmntInstrInvstCurAcctIndDomain = null;

  //Indicador de Conta CCI.
  private String m_prmntInstrInvstCurAcctIndSrc = "";

  //Número da conta
  private String m_curAcctNbrSrc = "";

  // Codigo da Conta Produto. Informacao gerada pela ODS Private para
  // identificar uma operacao ou um contrato
  private String m_selectedProdAcctCode = "";

  // Codigo da sub conta produto. Codigo gerado pela ODS para identificar
  // produtos que tenham subcontas ou subcontratos
  private String m_selectedProdUnderAcctCode = "";

  //Número do cliente selecionado no grid
  private String m_selectedCustNbr = "";

  //Código da instrução permanente selecionada no grid.
  private String m_selectedPrmntInstrCode = "";

  //Nome do cliente
  private String m_custFullNameSrc = "";

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
   * @return Returns curAcctNbrSrc.
   */
  public String getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param curAcctNbrSrc_ Field curAcctNbrSrc to be setted.
   */
  public void setCurAcctNbrSrc( String curAcctNbrSrc_ )
  {
    m_curAcctNbrSrc = curAcctNbrSrc_;
  }

  /**
   * @return Returns custFullNameSrc.
   */
  public String getCustFullNameSrc()
  {
    return m_custFullNameSrc;
  }

  /**
   * @param custFullNameSrc_ Field custFullNameSrc to be setted.
   */
  public void setCustFullNameSrc( String custFullNameSrc_ )
  {
    m_custFullNameSrc = custFullNameSrc_;
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
   * @return Returns prmntInstrCodeSrc.
   */
  public String getPrmntInstrCodeSrc()
  {
    return m_prmntInstrCodeSrc;
  }

  /**
   * @param prmntInstrCodeSrc_ Field prmntInstrCodeSrc to be setted.
   */
  public void setPrmntInstrCodeSrc( String prmntInstrCodeSrc_ )
  {
    m_prmntInstrCodeSrc = prmntInstrCodeSrc_;
  }

  /**
   * @return Returns prmntInstrInvstCurAcctIndDomain.
   */
  public DataSet getPrmntInstrInvstCurAcctIndDomain()
  {
    return m_prmntInstrInvstCurAcctIndDomain;
  }

  /**
   * @param prmntInstrInvstCurAcctIndDomain_ Field
   *          prmntInstrInvstCurAcctIndDomain to be setted.
   */
  public void setPrmntInstrInvstCurAcctIndDomain(
                                                 DataSet prmntInstrInvstCurAcctIndDomain_ )
  {
    m_prmntInstrInvstCurAcctIndDomain = prmntInstrInvstCurAcctIndDomain_;
  }

  /**
   * @return Returns prmntInstrInvstCurAcctIndSrc.
   */
  public String getPrmntInstrInvstCurAcctIndSrc()
  {
    return m_prmntInstrInvstCurAcctIndSrc;
  }

  /**
   * @param prmntInstrInvstCurAcctIndSrc_ Field prmntInstrInvstCurAcctIndSrc to
   *          be setted.
   */
  public void setPrmntInstrInvstCurAcctIndSrc(
                                              String prmntInstrInvstCurAcctIndSrc_ )
  {
    m_prmntInstrInvstCurAcctIndSrc = prmntInstrInvstCurAcctIndSrc_;
  }

  /**
   * @return Returns selectedCustNbr.
   */
  public String getSelectedCustNbr()
  {
    return m_selectedCustNbr;
  }

  /**
   * @param selectedCustNbr_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
    m_selectedCustNbr = selectedCustNbr_;
  }

  /**
   * @return Returns selectedPrmntInstrCode.
   */
  public String getSelectedPrmntInstrCode()
  {
    return m_selectedPrmntInstrCode;
  }

  /**
   * @param selectedPrmntInstrCode_ Field selectedPrmntInstrCode to be setted.
   */
  public void setSelectedPrmntInstrCode( String selectedPrmntInstrCode_ )
  {
    m_selectedPrmntInstrCode = selectedPrmntInstrCode_;
  }

  /**
   * @return Returns selectedProdAcctCode.
   */
  public String getSelectedProdAcctCode()
  {
    return m_selectedProdAcctCode;
  }

  /**
   * @param selectedProdAcctCode_ Field selectedProdAcctCode to be setted.
   */
  public void setSelectedProdAcctCode( String selectedProdAcctCode_ )
  {
    m_selectedProdAcctCode = selectedProdAcctCode_;
  }

  /**
   * @return Returns selectedProdUnderAcctCode.
   */
  public String getSelectedProdUnderAcctCode()
  {
    return m_selectedProdUnderAcctCode;
  }

  /**
   * @param selectedProdUnderAcctCode_ Field selectedProdUnderAcctCode to be
   *          setted.
   */
  public void setSelectedProdUnderAcctCode( String selectedProdUnderAcctCode_ )
  {
    m_selectedProdUnderAcctCode = selectedProdUnderAcctCode_;
  }

  /**
   * @return Returns prmntInstrTextSrc.
   */
  public String getPrmntInstrTextSrc()
  {
    return m_prmntInstrTextSrc;
  }

  /**
   * @param prmntInstrTextSrc_ Field prmntInstrTextSrc to be setted.
   */
  public void setPrmntInstrTextSrc( String prmntInstrTextSrc_ )
  {
    m_prmntInstrTextSrc = prmntInstrTextSrc_;
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedCurAcctNbrSrc()
   */
  public String getSelectedCurAcctNbrSrc()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedCurAcctNbrSrc(java.lang.String)
   */
  public void setSelectedCurAcctNbrSrc( String curAcctNbr_ )
  {
    m_curAcctNbrSrc = curAcctNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedProdAcctCodeSrc()
   */
  public String getSelectedProdAcctCodeSrc()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedProdAcctCodeSrc(java.lang.String)
   */
  public void setSelectedProdAcctCodeSrc( String selectedProdAcctCodeSrc_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedProdUnderAcctCodeSrc()
   */
  public String getSelectedProdUnderAcctCodeSrc()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedProdUnderAcctCodeSrc(java.lang.String)
   */
  public void setSelectedProdUnderAcctCodeSrc(
                                              String selectedProdUnderAcctCodeSrc_ )
  {
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getSelectedCustNbrList()
   */
  public String getSelectedCustNbrList()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setSelectedCustNbrList(java.lang.String)
   */
  public void setSelectedCustNbrList( String selectedCustNbr_ )
  {
    m_custNbrSrc = selectedCustNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtSearchable#getSelectedPrmntInstrCodeSrc()
   */
  public String getSelectedPrmntInstrCodeSrc()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtSearchable#setSelectedPrmntInstrCodeSrc(java.lang.String)
   */
  public void setSelectedPrmntInstrCodeSrc( String prmntInstrCode_ )
  {
    m_prmntInstrCodeSrc = prmntInstrCode_;
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