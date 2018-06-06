/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplPlayerMovEntity;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerMovementListFncVO;

/**
 * @author atilio.l.araujo
 *  
 */
public class PlayerMovementListForm extends BasePlayerListForm
{
  // Nome do Usuário
  private String m_lastUpdUserIdSrc;

  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }
  
  /*
   * Realiza validação de tamanho de campo
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {

    ActionErrors errors = new ActionErrors();

    errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateMaxLength(
                                    PlayerMovementListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplPlayerMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );
    return errors;
  }
}