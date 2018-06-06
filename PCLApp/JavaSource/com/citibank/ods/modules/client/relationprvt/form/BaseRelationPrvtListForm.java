package com.citibank.ods.modules.client.relationprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplRelationPrvtEntity;
import com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable;
import com.citibank.ods.modules.client.relationprvt.functionality.valueobject.BaseRelationPrvtListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.relationPrvt.form;
 * @version 1.0
 * @author l.braga,16/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseRelationPrvtListForm extends BaseForm implements
    RelationPrvtDetailable, RelationPrvtSearchable, CustomerSearchable
{
  // Numero do Cliente
  private String m_custNbrSrc = "";

  // Nome do cliente
  private String m_custFullNameTextSrc = "";

  // CFF ou CNPJ do cliente
  private String m_custCpfCnpjNbrSrc = "";

  // DataSet como os resultados do banco.
  private DataSet m_results;

  // Numero do Relacionamento
  private String m_reltnNbrSrc = "";

  // variavel de controle
  private String m_selectedReltnNbr = "";
  
  private String m_clickedSearch;

  /**
   * @return Returns the ownerSelectedSrc.
   */
  public String getOwnerSelectedSrc()
  {
    return m_ownerSelectedSrc;
  }

  /**
   * @param ownerSelectedSrc_ The ownerSelectedSrc to set.
   */
  public void setOwnerSelectedSrc( String ownerSelectedSrc_ )
  {
    m_ownerSelectedSrc = ownerSelectedSrc_;
  }

  // Número da Conta Corrente.
  private String m_curAcctNbrSrc = "";

  // Indicador de cliente owner.
  private String m_ownerSelectedSrc = "";

  /**
   * @return Returns acctNbrSrc.
   */
  public String getCurAcctNbrSrc()
  {
    return m_curAcctNbrSrc;
  }

  /**
   * @param acctNbrSrc_ Field acctNbrSrc to be setted.
   */
  public void setCurAcctNbrSrc( String curAcctNbrSrc_ )
  {
    m_curAcctNbrSrc = curAcctNbrSrc_;
  }

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
    m_custCpfCnpjNbrSrc = custCpfCnpjNbrSrc_;
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
   * @return Returns reltnNbrSrc.
   */
  public String getReltnNbrSrc()
  {
    return m_reltnNbrSrc;
  }

  /**
   * @param reltnNbrSrc_ Field reltnNbrSrc to be setted.
   */
  public void setReltnNbrSrc( String reltnNbrSrc_ )
  {
    m_reltnNbrSrc = reltnNbrSrc_;
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
   * @return Returns selectedReltnNbr.
   */
  public String getSelectedReltnNbr()
  {
    return m_selectedReltnNbr;
  }

  /**
   * @param selectedReltnNbr_ Field selectedReltnNbr to be setted.
   */
  public void setSelectedReltnNbr( String selectedReltnNbr_ )
  {
    m_selectedReltnNbr = selectedReltnNbr_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateBigInteger(
                                     BaseRelationPrvtListFncVO.C_CUST_NBR_SRC_DESCRIPTION,
                                     m_custNbrSrc,
                                     BaseTplRelationPrvtEntity.C_CUST_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseRelationPrvtListFncVO.C_ACCT_NBR_SRC_DESCRIPTION,
                                     m_curAcctNbrSrc,
                                     BaseTplRelationPrvtEntity.C_ACCT_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseRelationPrvtListFncVO.C_RELTN_NBR_SRC_DESCRIPTION,
                                     m_reltnNbrSrc,
                                     BaseTplRelationPrvtEntity.C_RELTN_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateBigInteger(
                                     BaseRelationPrvtListFncVO.C_CUST_CPF_CNPJ_NBR_SRC_DESCRIPTION,
                                     m_custCpfCnpjNbrSrc,
                                     BaseTplRelationPrvtEntity.C_CUST_CPF_CNPJ_NBR_SRC_SIZE,
                                     errors );

    ODSValidator.validateMaxLength(
                                    BaseRelationPrvtListFncVO.C_OWNER_SELECTED_SRC_DESCRIPTION,
                                    m_ownerSelectedSrc,
                                    BaseTplRelationPrvtEntity.C_OWNER_SELECTED_SRC_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseRelationPrvtListFncVO.C_CUST_FULL_NAME_TEXT_SRC_DESCRIPTION,
                                    m_custFullNameTextSrc,
                                    BaseTplRelationPrvtEntity.C_CUST_FULL_NAME_TEXT_SRC_SIZE,
                                    errors );

    return errors;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getSelectedCustNbr()
   */
  public String getSelectedCustNbrList()
  {
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setSelectedCustNbr(java.lang.String)
   */
  public void setSelectedCustNbrList( String selectedCustNbr_ )
  {
    this.setCustNbrSrc(selectedCustNbr_);    
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

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedCurAcctNbrSrc()
   */
  public String getSelectedCurAcctNbrSrc()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedCurAcctNbrSrc(java.lang.String)
   */
  public void setSelectedCurAcctNbrSrc( String curAcctNbr_ )
  {
    setCurAcctNbrSrc(curAcctNbr_);
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedProdAcctCodeSrc()
   */
  public String getSelectedProdAcctCodeSrc()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedProdAcctCodeSrc(java.lang.String)
   */
  public void setSelectedProdAcctCodeSrc( String prodAcctCode_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedProdUnderAcctCodeSrc()
   */
  public String getSelectedProdUnderAcctCodeSrc()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return null;
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedProdUnderAcctCodeSrc(java.lang.String)
   */
  public void setSelectedProdUnderAcctCodeSrc( String prodUnderAcctCode_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    
  }
  public void reset( ActionMapping map, HttpServletRequest req )
  {
    m_ownerSelectedSrc = "N";
    
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