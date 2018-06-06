/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.prodsubfamlprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplProdSubFamlPrvtMovEntity;
import com.citibank.ods.modules.product.prodsubfamlprvt.functionality.valueobject.ProdSubFamlPrvtMovementListFncVO;

/**
 * @author leonardo.nakada
 * 
 */
public class ProdSubFamlPrvtMovementListForm extends
    BaseProdSubFamlPrvtListForm
{
  //Nome do Usuário
  private String m_lastUpdUserIdSrc = "";

  /**
   * Retorna o Último usuário da realizar atualização
   * @return Returns the lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * Seta o último usuário a realizar atualização
   * @param lastUpdUserIdSrc_ The lastUpdUserIdSrc to set.
   */
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
                                    ProdSubFamlPrvtMovementListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplProdSubFamlPrvtMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );
    return errors;
  }
}