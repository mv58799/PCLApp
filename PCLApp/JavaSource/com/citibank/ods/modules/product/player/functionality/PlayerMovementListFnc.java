/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.player.form.PlayerMovementListForm;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author atilio.l.araujo
 *  
 */
public class PlayerMovementListFnc extends BasePlayerListFnc implements
    ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new PlayerMovementListFncVO();
  }

  /**
   * Recupera uma lista de players com pendencia de aprovação com os criterios
   * especificados
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    PlayerMovementListFncVO playerMovementListFncVO = ( PlayerMovementListFncVO ) fncVO_;

    TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();
    DataSet results = tplPlayerMovDAO.list(
                                            playerMovementListFncVO.getPlyrCnpjNbrScr(),
                                            playerMovementListFncVO.getPlyrNameScr(),
                                            playerMovementListFncVO.getPlyrRoleTypeCodeScr(),
                                            playerMovementListFncVO.getLastUpdUserIdSrc() );

    playerMovementListFncVO.setResults( results );
   
    if ( results.size() > 0 )
    {
      playerMovementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      playerMovementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Carregameto inicial - consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    PlayerMovementListFncVO playerMovementListFncVO = ( PlayerMovementListFncVO ) fncVO_;
    playerMovementListFncVO.setPlyrCnpjNbrScr( null );
    playerMovementListFncVO.setPlyrNameScr( null );
    playerMovementListFncVO.setPlyrRoleTypeCodeScr( null );
    playerMovementListFncVO.setLastUpdUserIdSrc( null );
    playerMovementListFncVO.setResults( null );
    super.loadDomains( ( PlayerMovementListFncVO ) fncVO_ );
  }

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Atualizando campos comuns
    super.updateFncVOFromForm( form_, fncVO_ );

    // Acertando os tipos
    PlayerMovementListFncVO playerMovementListFncVO = ( PlayerMovementListFncVO ) fncVO_;
    PlayerMovementListForm playerMovementListForm = ( PlayerMovementListForm ) form_;

    String lastUpdUserIdSrc = ( playerMovementListForm.getLastUpdUserIdSrc() != null
                                && playerMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                            ? playerMovementListForm.getLastUpdUserIdSrc()
                                                                                            : null );
    playerMovementListFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

  }

  /**
   * Validação da consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

}