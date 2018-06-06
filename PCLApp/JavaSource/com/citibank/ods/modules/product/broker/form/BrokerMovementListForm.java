package com.citibank.ods.modules.product.broker.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplBrokerMovEntity;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BrokerMovementListFncVO;

/**
 * @author hamilton.matos
 *  
 */
public class BrokerMovementListForm extends BaseBrokerListForm
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
                                    BrokerMovementListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplBrokerMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );
    return errors;
  }
}