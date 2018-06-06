/*
 * Created on Mar 16, 2007
 *
 */
package com.citibank.ods.modules.product.prodqlfyprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplProdQlfyPrvtMovEntity;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.ProdQlfyPrvtMovementListFncVO;

/**
 * @author fernando.salgado
 *  
 */
public class ProdQlfyPrvtMovementListForm extends BaseProdQlfyPrvtListForm
{

  private String m_lastUpdUserIdSrc;

  /**
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {

    ActionErrors errors = new ActionErrors();

    errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateMaxLength(
                                    ProdQlfyPrvtMovementListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplProdQlfyPrvtMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );

    return errors;
  }

}