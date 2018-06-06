package com.citibank.ods.modules.product.prodplayerrole.form;

import com.citibank.ods.common.dataset.DataSet;

/**
 * @author atilio.l.araujo
 *  
 */

public class ProdPlayerRoleDetailForm extends BaseProdPlayerRoleDetailForm
{

  // Data e Hora que o Usuario aprovou o Registro Cadastrado
  private String m_lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";

  // Razão social
  private String m_plyrName = "";

  // Tipo Role
  private String m_plyrRoleType = "";

  // Papéis do Player
  private String[] prodPlayerRole;

  //Código do papel do player
  private String m_selectedPlyrRoleTypeCode;

  //Código do produto
  private String m_selectedProdCodeGrid;

  // Código de Sistema
  private String m_selectedSysCodeGrid;

  // Código do seguimento do Sistema
  private String m_selectedSysSegCodeGrid;

  // Valores da Lista
  private DataSet m_results;

  /**
   * @return Returns the plyrRoleType.
   */
  public String getPlyrRoleType()
  {
    return m_plyrRoleType;
  }

  /**
   * @param plyrRoleType_ The plyrRoleType to set.
   */
  public void setPlyrRoleType( String plyrRoleType_ )
  {
    m_plyrRoleType = plyrRoleType_;
  }

  /**
   * @return Returns the plyrName.
   */
  public String getPlyrName()
  {
    return m_plyrName;
  }

  /**
   * @param plyrName_ The plyrName to set.
   */
  public void setPlyrName( String plyrName_ )
  {
    m_plyrName = plyrName_;
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
   * @return Returns the selectedProdCode.
   */

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
   * @return Returns prodPlayerRole.
   */
  public String[] getProdPlayerRole()
  {
    return prodPlayerRole;
  }

  /**
   * @param prodPlayerRole_ Field prodPlayerRole to be setted.
   */
  public void setProdPlayerRole( String[] prodPlayerRole_ )
  {
    prodPlayerRole = prodPlayerRole_;
  }

  public String getSelectedPlyrRoleTypeCode()
  {
    return m_selectedPlyrRoleTypeCode;
  }

  public void setSelectedPlyrRoleTypeCode( String selectedPlyrRoleTypeCode_ )
  {
    m_selectedPlyrRoleTypeCode = selectedPlyrRoleTypeCode_;
  }

  public String getSelectedProdCodeGrid()
  {
    return m_selectedProdCodeGrid;
  }

  public void setSelectedProdCodeGrid( String selectedProdCodeGrid_ )
  {
    m_selectedProdCodeGrid = selectedProdCodeGrid_;
  }

  public String getSelectedSysCodeGrid()
  {
    return m_selectedSysCodeGrid;
  }

  public void setSelectedSysCodeGrid( String selectedSysCodeGrid_ )
  {
    m_selectedSysCodeGrid = selectedSysCodeGrid_;
  }

  public String getSelectedSysSegCodeGrid()
  {
    return m_selectedSysSegCodeGrid;
  }

  public void setSelectedSysSegCodeGrid( String selectedSysSegCodeGrid_ )
  {
    m_selectedSysSegCodeGrid = selectedSysSegCodeGrid_;
  }

}