package com.citibank.ods.modules.client.ipdocprvt.form;

import com.citibank.ods.modules.client.ipdocprvt.form.BaseIpDocPrvtDetailForm;

/**
*@author l.braga
*
*/

public class IpDocPrvtMovementDetailForm extends BaseIpDocPrvtDetailForm
{
  //Status do registro
  private String m_opernCode = "";  
  
  private String m_selectedCtcNbr;

  private String m_selectedFullNameText;

  private String m_selectedDocTransferCode;  
  
  //Usuário da última atualização
  private String m_lastUpdUserId = "";
    
  //Data/Hora da última atualização
  private String m_lastUpdDate = "";
  
  //Lista de opern Code de Doc Transfer
  private String[] m_opernCodeDocTransferArray ;

  //Lista de opern Code de Doc Transfer
  private String[] m_opernCodeCallBackArray ;  
  
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
   * @return Returns selectedCtcNbr.
   */
  public String getSelectedCtcNbr()
  {
    return m_selectedCtcNbr;
  }

  /**
   * @param selectedCtcNbr_ Field selectedCtcNbr to be setted.
   */
  public void setSelectedCtcNbr( String selectedCtcNbr_ )
  {
    m_selectedCtcNbr = selectedCtcNbr_;
  }

  /**
   * @return Returns selectedDocTransferCode.
   */
  public String getSelectedDocTransferCode()
  {
    return m_selectedDocTransferCode;
  }

  /**
   * @param selectedDocTransferCode_ Field selectedDocTransferCode to be setted.
   */
  public void setSelectedDocTransferCode( String selectedDocTransferCode_ )
  {
    m_selectedDocTransferCode = selectedDocTransferCode_;
  }
  /**
   * @return Returns selectedFullNameText.
   */
  public String getSelectedFullNameText()
  {
    return m_selectedFullNameText;
  }
  /**
   * @param selectedFullNameText_ Field selectedFullNameText to be setted.
   */
  public void setSelectedFullNameText( String selectedFullNameText_ )
  {
    m_selectedFullNameText = selectedFullNameText_;
  }
  
  
  /**
   * @return Retorna a data da última atualização.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }
  /**
   * @param lastUpdDate_.Seta a data da última atualização.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }
  /**
   * @return Retorna o usuário da última atualização.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }
  /**
   * @param lastUpdUserId_. Seta o usuário da última atualização.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }
  
  

  /**
   * @return Returns opernCodeCallBackArray.
   */
  public String[] getOpernCodeCallBackArray()
  {
    return m_opernCodeCallBackArray;
  }
  /**
   * @param opernCodeCallBackArray_ Field opernCodeCallBackArray to be setted.
   */
  public void setOpernCodeCallBackArray( String[] opernCodeCallBackArray_ )
  {
    m_opernCodeCallBackArray = opernCodeCallBackArray_;
  }
  /**
   * @return Returns opernCodeDocTransferArray.
   */
  public String[] getOpernCodeDocTransferArray()
  {
    return m_opernCodeDocTransferArray;
  }
  /**
   * @param opernCodeDocTransferArray_ Field opernCodeDocTransferArray to be setted.
   */
  public void setOpernCodeDocTransferArray( String[] opernCodeDocTransferArray_ )
  {
    m_opernCodeDocTransferArray = opernCodeDocTransferArray_;
  }
}
