package com.citibank.ods.modules.client.erem.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;
import com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable;
import com.citibank.ods.modules.client.erem.functionality.valueobject.BaseEREMListFncVO;

/**
 * @author gerson.a.rodrigues
 *  
 */

public class BaseEREMDetailForm extends BaseForm implements EREMDetailable,
    CustomerPrvtCmplSearchable
{

  // Numero Individual gerado no CARE. (Entitlement Member)
  private String m_emNbr = "";

  // Identificador do grupo de relacionamento sob o qual varios EMs estao
  // agrupados. (Entitlement Relationship)
  private String m_erNbr = "";

  //Número do Cliente
  private String m_custNbr = "";

  //Número do Cliente
  // private String m_selectedCustNbr = "";

  // Data e Hora que o usuario aprovou o registro cadastrado
  private String m_lastAuthDate = "";

  // Codigo do usuario (SOE ID) que aprovou o cadastro do registro
  private String m_lastAuthUserId = "";

  // Data e hora da ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro
  private String m_lastUpdUserId = "";

  // Codigo do Papel desempenhado pelo Cliente.
  private String m_roleCustCode = "";

  //Nome do cliente
  private String m_custFullNameText = "";

  //Dataset do Papel desempenhado pelo Cliente.
  private DataSet m_roleCustCodeDomain;

  // valor seleiconado no grid
  private String m_selectedErNbrInGrid = "";

  private String m_selectedEmNbrInGrid = "";

  private String m_clickedSearch;

  //Lista com os ítens inseridos
  private String[] m_selectedItemsInGrid = null;

  //Lista com os ítens a serem excluídos
  private String[] m_deletedItems = null;
  
  /*
   *Dados Complementares
   */
   
  // Idenfica se houve transferencia do relacionamento do consumer para o 
  // Private. S = relacionamento foi transferido do consumer N = nao houve transferencia

  private String erReltnTrfInd;

  //Codigo do motivo de encerramento de relacionamento. 
  private String reltnEndReasCode;
  
  //Descricao do motivo de encerramento de relacionamento, somente preenchido quando o codigo acima for do tipo "Outros".

  private String reltnEndReasText;
	
  //Codigo da classificacao da estimativa de patrimonio do relacionamento.

  private String equityClassCode;
  
  //Domínio - Codigo do motivo de encerramento de relacionamento.
  
  private DataSet reltnEndReasCodeDomain;
  
  // Domínio - Idenfica se houve transferencia do relacionamento do consumer para o 
  // Private. S = relacionamento foi transferido do consumer N = nao houve transferencia
  
  private DataSet erReltnTrfIndDomain;
  
  //Domínio - Codigo da classificacao da estimativa de patrimonio do relacionamento. 
  
  private DataSet equityClassCodeDomain;

  //

  /*
   * Grid
   */
  private String[][] m_erEmGrid;

  public static final int COL_POS_ER_NBR = 0;

  public static final int COL_POS_EM_NBR = 1;

  public static final int COL_POS_ROLE_CUST_CODE = 2;

  public static final int COL_POS_CUST_FULL_NAME = 3;

  public static final int COL_POS_LAST_UPD_USER_ID = 4;

  public static final int COL_POS_LAST_UPD_DATE = 5;

  public static final int COL_POS_LAST_AUTH_USER_ID = 6;

  public static final int COL_POS_LAST_AUTH_DATE = 7;

  public static final int COL_POS_OPERN_CODE = 8;

  /**
   * @return Returns the erEmGrid_.
   */
  public String[][] getErEmGrid()
  {
    return m_erEmGrid;
  }

  /**
   * @param erEmGrid__ The erEmGrid_ to set.
   */
  public void setErEmGrid( String[][] erEmGrid__ )
  {
    m_erEmGrid = erEmGrid__;
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
   * @return Returns m_roleCustCode.
   */
  public String getRoleCustCode()
  {
    return m_roleCustCode;
  }

  /**
   * @param roleCustCode_ Field m_roleCustCode to be setted.
   */
  public void setRoleCustCode( String roleCustCode_ )
  {
    m_roleCustCode = roleCustCode_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.erem.form.ErEmDetailable#getSelectedErNbr()
   */
  public String getSelectedERNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.erem.form.ErEmDetailable#setSelectedErNbr(java.lang.String)
   */
  public void setSelectedERNbr( String ERNbr_ )
  {
    setErNbr( ERNbr_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.erem.form.ErEmDetailable#getSelectedEmNbr()
   */
  public String getSelectedEMNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.erem.form.ErEmDetailable#setSelectedEmNbr(java.lang.String)
   */
  public void setSelectedEMNbr( String EMNbr_ )
  {
    setEmNbr( EMNbr_ );
  }

  /**
   * @return Returns the roleCustCodeDomain.
   */
  public DataSet getRoleCustCodeDomain()
  {
    return m_roleCustCodeDomain;
  }

  /**
   * @param roleCustCodeDomain_ The roleCustCodeDomain to set.
   */
  public void setRoleCustCodeDomain( DataSet roleCustCodeDomain_ )
  {
    m_roleCustCodeDomain = roleCustCodeDomain_;
  }

  /**
   * @return Returns the emNbr.
   */
  public String getEmNbr()
  {
    return m_emNbr;
  }

  /**
   * @param emNbr_ The emNbr to set.
   */
  public void setEmNbr( String emNbr_ )
  {
    m_emNbr = emNbr_;
  }

  /**
   * @return Returns the erNbr.
   */
  public String getErNbr()
  {
    return m_erNbr;
  }

  /**
   * @param erNbr_ The erNbr to set.
   */
  public void setErNbr( String erNbr_ )
  {
    m_erNbr = erNbr_;
  }

  /**
   * @return Returns the selectedEmNbrInGrid.
   */
  public String getSelectedEmNbrInGrid()
  {
    return m_selectedEmNbrInGrid;
  }

  /**
   * @param selectedEmNbrInGrid_ The selectedEmNbrInGrid to set.
   */
  public void setSelectedEmNbrInGrid( String selectedEmNbrInGrid_ )
  {
    m_selectedEmNbrInGrid = selectedEmNbrInGrid_;
  }

  /**
   * @return Returns the selectedErNbrInGrid.
   */
  public String getSelectedErNbrInGrid()
  {
    return m_selectedErNbrInGrid;
  }

  /**
   * @param selectedErNbrInGrid_ The selectedErNbrInGrid to set.
   */
  public void setSelectedErNbrInGrid( String selectedErNbrInGrid_ )
  {
    m_selectedErNbrInGrid = selectedErNbrInGrid_;
  }

  /**
   * @return Returns the clickedSearch.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_ The clickedSearch to set.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#setSelectedEmNbr(java.lang.String)
   */
  public void setSelectedEmNbr( String selectedEmNbr_ )
  {
    m_emNbr = selectedEmNbr_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#getSelectedEmNbr()
   */
  public String getSelectedEmNbr()
  {
    return null;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength( BaseEREMListFncVO.C_ER_NBR_SRC_DESCRIPTION,
                                    m_erNbr,
                                    BaseTplErEmEntity.C_ER_NBR_SRC_SIZE, errors );

    ODSValidator.validateMaxLength( BaseEREMListFncVO.C_EM_NBR_SRC_DESCRIPTION,
                                    m_emNbr,
                                    BaseTplErEmEntity.C_EM_NBR_SRC_SIZE, errors );

    return errors;
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
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#setSelectedCustNbr(java.lang.String)
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
    setCustNbr( selectedCustNbr_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvtcmpl.form.CustomerPrvtCmplSearchable#getSelectedCustNbr()
   */
  public String getSelectedCustNbr()
  {
    return null;
  }

  public String getCustNbr()
  {
    return m_custNbr;
  }

  public void setCustNbr( String custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  public String[] getSelectedItemsInGrid()
  {
    return m_selectedItemsInGrid;
  }

  public void setSelectedItemsInGrid( String[] selectedItemsInGrid_ )
  {
    m_selectedItemsInGrid = selectedItemsInGrid_;
  }

  /**
   * @return Returns deletedItems.
   */
  public String[] getDeletedItems()
  {
    return m_deletedItems;
  }

  /**
   * @param deletedItems_ Field deletedItems to be setted.
   */
  public void setDeletedItems( String[] deletedItems_ )
  {
    m_deletedItems = deletedItems_;
  }

  public void reset( ActionMapping arg0_, HttpServletRequest arg1_ )
  {
    if ( m_selectedItemsInGrid != null )
    {
      for ( int i = 0; i < m_selectedItemsInGrid.length; i++ )
      {
        m_selectedItemsInGrid[ i ] = "N";
      }
    }

    if ( m_deletedItems != null )
    {
      for ( int i = 0; i < m_deletedItems.length; i++ )
      {
        m_deletedItems[ i ] = "N";
      }
    }

  }
	/**
	 * @return
	 */
	public String getEquityClassCode() {
		return equityClassCode;
	}
	
	/**
	 * @return
	 */
	public String getErReltnTrfInd() {
		return erReltnTrfInd;
	}
	
	/**
	 * @return
	 */
	public String getReltnEndReasCode() {
		return reltnEndReasCode;
	}
	
	/**
	 * @return
	 */
	public String getReltnEndReasText() {
		return reltnEndReasText;
	}
	
	/**
	 * @param string
	 */
	public void setEquityClassCode(String equityClassCode_) {
		equityClassCode = equityClassCode_;
	}
	
	/**
	 * @param string
	 */
	public void setErReltnTrfInd(String erReltnTrfInd_) {
		erReltnTrfInd = erReltnTrfInd_;
	}
	
	/**
	 * @param string
	 */
	public void setReltnEndReasCode(String reltnEndReasCode_) {
		reltnEndReasCode = reltnEndReasCode_;
	}
	
	/**
	 * @param string
	 */
	public void setReltnEndReasText(String reltnEndReasText_) {
		reltnEndReasText = reltnEndReasText_;
	}

	/**
	 * @return
	 */
	public DataSet getEquityClassCodeDomain() {
		return equityClassCodeDomain;
	}
	
	/**
	 * @return
	 */
	public DataSet getErReltnTrfIndDomain() {
		return erReltnTrfIndDomain;
	}
	
	/**
	 * @return
	 */
	public DataSet getReltnEndReasCodeDomain() {
		return reltnEndReasCodeDomain;
	}
	
	/**
	 * @param set
	 */
	public void setEquityClassCodeDomain(DataSet equityClassCodeDomain_) {
		equityClassCodeDomain = equityClassCodeDomain_;
	}
	
	/**
	 * @param set
	 */
	public void setErReltnTrfIndDomain(DataSet erReltnTrfIndDomain_) {
		erReltnTrfIndDomain = erReltnTrfIndDomain_;
	}
	
	/**
	 * @param set
	 */
	public void setReltnEndReasCodeDomain(DataSet reltnEndReasCodeDomain_) {
		reltnEndReasCodeDomain = reltnEndReasCodeDomain_;
	}

}