/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerListFncVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author atilio.l.araujo
 *  
 */
public class PlayerListFnc extends BasePlayerListFnc implements ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new PlayerListFncVO();
  }

  /**
   * Recupera uma lista de players com os criterios especificados
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    PlayerListFncVO playerListFncVO = ( PlayerListFncVO ) fncVO_;

    TplPlayerDAO tplPlayerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    DataSet results = tplPlayerDAO.list(
                                         playerListFncVO.getPlyrCnpjNbrScr(),
                                         playerListFncVO.getPlyrNameScr(),
                                         playerListFncVO.getPlyrRoleTypeCodeScr() );

    playerListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      playerListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      playerListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Carregameto inicial - consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    PlayerListFncVO playerListFncVO = ( PlayerListFncVO ) fncVO_;
    playerListFncVO.setPlyrNameScr( null );
    playerListFncVO.setPlyrRoleTypeCodeScr( null );
    playerListFncVO.setResults( null );
    super.loadDomains( playerListFncVO );
  }

  /**
   * Validação da consulta em lista
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

  //Botão Limpar
  public void clearPage( BaseFncVO fncVO_ )
  {
    PlayerListFncVO playerListFncVO = ( PlayerListFncVO ) fncVO_;

    playerListFncVO.clearErrors();
    playerListFncVO.clearMessages();
    playerListFncVO.setPlyrNameScr( null );
    playerListFncVO.setPlyrRoleTypeCodeScr( null );
    playerListFncVO.setResults( null );

  }
}