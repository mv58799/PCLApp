/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.product.player.form.BasePlayerListForm;
import com.citibank.ods.modules.product.player.functionality.valueobject.BasePlayerListFncVO;

/**
 * @author atilio.l.araujo
 *  
 */
public abstract class BasePlayerListFnc extends BaseFnc implements ODSListFnc
{

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BasePlayerListForm basePlayerListForm = ( BasePlayerListForm ) form_;
    BasePlayerListFncVO basePlayerListFncVO = ( BasePlayerListFncVO ) fncVO_;

    basePlayerListFncVO.setPlyrCnpjNbrScr( basePlayerListForm.getPlyrCnpjNbrSrc() );
    basePlayerListFncVO.setPlyrNameScr( basePlayerListForm.getPlyrNameSrc() );
    basePlayerListFncVO.setPlyrRoleTypeCodeScr( basePlayerListForm.getPlyrRoleTypeCodeSrc() );

  }

  /**
   * Atualiza os atributos do Form com os atributos vindos da FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BasePlayerListForm basePlayerListForm = ( BasePlayerListForm ) form_;
    BasePlayerListFncVO basePlayerListFncVO = ( BasePlayerListFncVO ) fncVO_;

    basePlayerListForm.setPlyrCnpjNbrSrc( basePlayerListFncVO.getPlyrCnpjNbrScr() );
    basePlayerListForm.setPlyrNameSrc( basePlayerListFncVO.getPlyrNameScr() );
    basePlayerListForm.setPlyrRoleTypeCodeSrc( basePlayerListFncVO.getPlyrRoleTypeCodeScr() );
    basePlayerListForm.setPlayerRoleTypeDomain( basePlayerListFncVO.getPlayerRoleTypeDomain() );
    basePlayerListForm.setResults( basePlayerListFncVO.getResults() );

  }

  /**
   * Realiza o carregamento dos dados de tipo de papel do player
   * @author angelica.almeida
   */
  protected void loadRoleTypeDomain( BasePlayerListFncVO playerListFncVO_ )
  {
    DataSet resultDomain = ODSConstraintDecoder.decodeRoleType();
    playerListFncVO_.setPlayerRoleTypeDomain( resultDomain );
  }

  /**
   * Carrega todos os domínios utilizados pela transação
   */
  protected void loadDomains( BasePlayerListFncVO playerListFncVO_ )
  {
    this.loadRoleTypeDomain( playerListFncVO_ );
  }

}