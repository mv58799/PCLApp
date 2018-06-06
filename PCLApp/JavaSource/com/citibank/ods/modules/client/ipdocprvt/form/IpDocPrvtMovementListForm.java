package com.citibank.ods.modules.client.ipdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.TplIpDocPrvtMovEntity;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtMovementListFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.contactcust.form;
 * @version 1.0
 * @author l.braga,26/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class IpDocPrvtMovementListForm extends BaseIpDocPrvtListForm
{

  // Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
  private String m_lastUpdUserIdSrc;

  // Codigo da Acao que originou o registro (I-Insert; U-update)
  private String m_opernCode;

  /*
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = super.validate( actionMapping_, request_ );

    ODSValidator.validateMaxLength(
                                    IpDocPrvtMovementListFncVO.C_LAST_UPD_USER_ID_DESCRIPTION,
                                    m_lastUpdUserIdSrc,
                                    TplIpDocPrvtMovEntity.C_LAST_UPD_USER_ID_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    IpDocPrvtMovementListFncVO.C_OPERN_CODE_DESCRIPTION,
                                    m_opernCode,
                                    TplIpDocPrvtMovEntity.C_OPERN_CODE_SIZE,
                                    errors );

    return errors;
  }

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

  /**
   * @return Returns opernCode.
   */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
   * @param opernCode_ Field opernCode to be setted.
   */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}