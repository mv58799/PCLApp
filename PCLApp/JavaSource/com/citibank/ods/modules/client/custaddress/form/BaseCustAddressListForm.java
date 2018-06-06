package com.citibank.ods.modules.client.custaddress.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable;

/**
 * @author l.braga
 *  
 */

public class BaseCustAddressListForm extends BaseForm implements
    CustAddressDetailable, CustomerSearchable
{
  // Numero do Cliente
  private String m_custNbrSrc = "";

  // Nome do cliente
  private String m_custFullNameTextSrc = "";

  // CFF ou CNPJ do cliente
  private String m_custCpfCnpjNbrSrc = "";

  // DataSet como os resultados do banco.
  private DataSet m_results;

  // Varialvel de controle.
  private String m_selectedCustNbr = "";

  // Varialvel de controle.
  private String m_selectedAddrSeqNbr = "";

  /**
   * @return Returns custCpfCnpjNbrSrc.
   */
  public String getCustCpfCnpjNbrSrc()
  {
    return m_custCpfCnpjNbrSrc;
  }

  /**
   * @param custCpfCnpjNbrSrc_ Field custCpfCnpjNbrSrc to be setted.
   */
  public void setCustCpfCnpjNbrSrc( String custCpfCnpjNbrSrc_ )
  {
    m_custCpfCnpjNbrSrc = removeMask( custCpfCnpjNbrSrc_ );
  }

  /**
   * @return Returns custFullNameTextSrc.
   */
  public String getCustFullNameTextSrc()
  {
    return m_custFullNameTextSrc;
  }

  /**
   * @param custFullNameTextSrc_ Field custFullNameTextSrc to be setted.
   */
  public void setCustFullNameTextSrc( String custFullNameTextSrc_ )
  {
    m_custFullNameTextSrc = custFullNameTextSrc_;
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
   * @return Returns selectedAddrSeqNbr.
   */
  public String getSelectedAddrSeqNbr()
  {
    return m_selectedAddrSeqNbr;
  }

  /**
   * @param selectedAddrSeqNbr_ Field selectedAddrSeqNbr to be setted.
   */
  public void setSelectedAddrSeqNbr( String selectedAddrSeqNbr_ )
  {
    m_selectedAddrSeqNbr = selectedAddrSeqNbr_;
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
    setCustNbrSrc( selectedCustNbr_ );
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
    //
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