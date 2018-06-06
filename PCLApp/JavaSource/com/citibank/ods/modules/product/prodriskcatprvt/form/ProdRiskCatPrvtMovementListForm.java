/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplProdRiskCatPrvtMovEntity;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.ProdRiskCatPrvtMovementListFncVO;

/**
 * @author leonardo.nakada
 * 
 */
public class ProdRiskCatPrvtMovementListForm extends
    BaseProdRiskCatPrvtListForm
{
  //Nome do Usuário
  private String m_lastUpdUserIdSrc = "";

  /**
   * @return Returns the lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param updUserIdSrc The lastUpdUserIdSrc to set.
   */
  public void setLastUpdUserIdSrc( String updUserIdSrc )
  {
    m_lastUpdUserIdSrc = updUserIdSrc;
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
                                    ProdRiskCatPrvtMovementListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplProdRiskCatPrvtMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );
    return errors;
  }
}