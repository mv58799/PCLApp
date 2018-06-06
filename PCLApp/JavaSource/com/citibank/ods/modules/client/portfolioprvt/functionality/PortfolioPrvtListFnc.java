package com.citibank.ods.modules.client.portfolioprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject.BasePortfolioPrvtListFncVO;
import com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject.PortfolioPrvtListFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.TplPortfolioPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.portfolioprvt.functionality;
 * @version 1.0
 * @author l.braga,31/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class PortfolioPrvtListFnc extends BasePortfolioPrvtListFnc implements
    ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {

    return new PortfolioPrvtListFncVO();
  }

  /*
   * Parametro PortfolioPrvtListFncVO Retorno vazil O metodo recupera as
   * carteiras do banco, com os parametros passado pela tela, e seta no fncVO.
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {

    BasePortfolioPrvtListFncVO fncVO = ( BasePortfolioPrvtListFncVO ) fncVO_;
    if ( !fncVO.hasErrors() )
    {

      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      TplPortfolioPrvtDAO portfolioPrvtDAO = factory.getTplPortfolioPrvtDAO();

      DataSet result = portfolioPrvtDAO.listPortfolio(
                                                       fncVO.getPortfCode(),
                                                       fncVO.getPortfNameText(),
                                                       fncVO.getPortfOffcrNbr(),
                                                       fncVO.getOffcrNameText() );
      fncVO.setResults( result );

      if ( result.size() > 0 )
      {
        fncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      }
      else
      {
        fncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
      }

    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    PortfolioPrvtListFncVO listFncVO = ( PortfolioPrvtListFncVO ) fncVO_;

    if ( listFncVO.isFromSearch() )
    {
      loadOfficerText( listFncVO );
      listFncVO.setFromSearch( false );
    }
    else
    {
      listFncVO.setPortfCode( null );
      listFncVO.setPortfNameText( null );
      listFncVO.setPortfOffcrNbr( null );
      listFncVO.setOffcrNameText( null );
      listFncVO.setResults( null );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

  public void loadOfficerText( PortfolioPrvtListFncVO portfolioPrvtListFncVO_ )
  {
    if ( portfolioPrvtListFncVO_.getPortfOffcrNbr() != null
         && portfolioPrvtListFncVO_.getPortfOffcrNbr().intValue() > 0 )
    {
      TbgOfficerEntity officerEntity = new TbgOfficerEntity();
      officerEntity.getData().setOffcrNbr(
                                           portfolioPrvtListFncVO_.getPortfOffcrNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TbgOfficerDAO tbgOfficerDAO = factory.getTbgOfficerDAO();

      //Realiza a consulta no DAO
      officerEntity = ( TbgOfficerEntity ) tbgOfficerDAO.find( officerEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      portfolioPrvtListFncVO_.setOffcrNameText( officerEntity.getData().getOffcrNameText() );
    }
    else
    {
      portfolioPrvtListFncVO_.setOffcrNameText( "" );
    }
  }

}