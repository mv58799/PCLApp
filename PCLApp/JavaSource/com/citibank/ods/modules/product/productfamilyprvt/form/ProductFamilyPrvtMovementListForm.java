/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplProductFamilyPrvtMovEntity;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.ProductFamilyPrvtMovementListFncVO;

/**
 * @author leonardo.nakada
 *  
 */
public class ProductFamilyPrvtMovementListForm extends
    BaseProductFamilyPrvtListForm
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
                                    ProductFamilyPrvtMovementListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplProductFamilyPrvtMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );
    return errors;
  }
}

