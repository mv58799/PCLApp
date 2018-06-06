package com.citibank.ods.modules.product.prodplayerrole.form;

import com.citibank.ods.modules.product.prodplayerrole.form.BaseProdPlayerRoleDetailForm;

/**
 * @author atilio.l.araujo
 *  
 */
public class ProdPlayerRoleMovementDetailForm extends
    BaseProdPlayerRoleDetailForm
{

  //Ação
  private String m_opernCode = "";

  // Razão Social do Player
  private String m_plyrName;

  // Papéis do Player
  private String[] m_prodPlayerRole;

  //Código do papel do player
  private String m_selectedPlyrRoleTypeCode;

  //Código do produto
  private String m_selectedProdCodeGrid;

  // Código de Sistema
  private String m_selectedSysCodeGrid;

  // Código do seguimento do Sistema
  private String m_selectedSysSegCodeGrid;

  /**
   * @return Returns m_opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ Field m_opernCode to be setted.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }

  /**
   * @return Returns plyrName.
   */
  public String getPlyrName()
  {
    return m_plyrName;
  }

  /**
   * @param plyrName_ Field plyrName to be setted.
   */
  public void setPlyrName( String plyrName_ )
  {
    m_plyrName = plyrName_;
  }

  /**
   * @return Returns prodPlayerRole.
   */
  public String[] getProdPlayerRole()
  {
    return m_prodPlayerRole;
  }

  /**
   * @param prodPlayerRole_ Field prodPlayerRole to be setted.
   */
  public void setProdPlayerRole( String[] prodPlayerRole_ )
  {
    m_prodPlayerRole = prodPlayerRole_;
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