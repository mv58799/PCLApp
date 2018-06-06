package com.citibank.ods.modules.client.relationeg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplRelationEgMovEntity;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgDetailFncVO;

/**
 * @author leonardo.nakada
 *  
 */

public class RelationEgDetailForm extends BaseRelationEgDetailForm
{

  // Data e Hora que o usuario aprovou o registro cadastrado
  private String m_lastAuthDate = "";

  // Codigo do usuario (SOE ID) que aprovou o cadastro do registro
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";

  private String m_clickedSearch;

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

  /**
   * Realiza as validações dos campos
   */
  public ActionErrors validate( ActionMapping mapping_, HttpServletRequest req_ )
  {
    ActionErrors errors = super.validate( mapping_, req_ );

    ODSValidator.validateMaxLength(
                                    RelationEgDetailFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    super.getLastUpdUserId(),
                                    TplRelationEgMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );

    return errors;
  }
}