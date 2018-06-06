/*
 * Created on Apr 15, 2007
 *
 */
package com.citibank.ods.modules.client.relationeg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplRelationEgMovEntity;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgMovementListFncVO;

/**
 * @author leonardo.nakada
 *  
 */
public class RelationEgMovementListForm extends BaseRelationEgListForm
{
  private String m_lastUpdUserIdSrc;

  /**
   * @return Returns the lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }
  
  /**
   * @param lastUpdUserId_ The lastUpdUserIdSrc to set.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /**
   * Realiza a validação dos dados do Form
   */
  public ActionErrors validate( ActionMapping mapping_, HttpServletRequest req_ )
  {
    ActionErrors errors = super.validate( mapping_, req_ );

    ODSValidator.validateMaxLength(
                                    RelationEgMovementListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplRelationEgMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );

    return super.validate( mapping_, req_ );
  }

}