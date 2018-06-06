package com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject;

import java.util.ArrayList;

import org.apache.struts.action.ActionMessage;

import com.citibank.ods.entity.pl.TplBkrPortfMgmtMovEntity;

/**
 * @author Hamilton Matos
 *  
 */

public class BkrPortfMgmtMovementDetailFncVO extends
    BaseBkrPortfMgmtDetailFncVO
{

  // Mensagens geradas a partir da ação de aprovação / rejeição
  private ActionMessage m_approvalMessages = new ActionMessage( "" );

  // Lista de entidades de associação corretora x carteira selecionadas
  private ArrayList m_selectedBkrPortfMgmtEntityList;

  public BkrPortfMgmtMovementDetailFncVO()
  {
    m_baseTplBkrPortfMgmtEntity = new TplBkrPortfMgmtMovEntity();
  }

  /**
   * @return Returns selectedBkrPortfMgmtEntityList.
   */
  public ArrayList getSelectedBkrPortfMgmtEntityList()
  {
    return m_selectedBkrPortfMgmtEntityList;
  }

  /**
   * @param selectedBkrPortfMgmtEntityList_ Field selectedBkrPortfMgmtEntityList
   *          to be setted.
   */
  public void setSelectedBkrPortfMgmtEntityList(
                                                ArrayList selectedBkrPortfMgmtEntityList_ )
  {
    m_selectedBkrPortfMgmtEntityList = selectedBkrPortfMgmtEntityList_;
  }

  public ActionMessage getApprovalMessages()
  {
    return m_approvalMessages;
  }

  public void setApprovalMessages( ActionMessage approvalMessages_ )
  {
    m_approvalMessages = approvalMessages_;
  }
}