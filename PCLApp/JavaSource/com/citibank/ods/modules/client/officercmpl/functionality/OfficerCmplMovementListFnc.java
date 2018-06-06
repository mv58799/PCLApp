package com.citibank.ods.modules.client.officercmpl.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.officercmpl.form.OfficerCmplMovementListForm;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
/**
 * @author bruno.zanetti
 * 
 * Classe Fnc de consulta em Lista dos dados de movimento
 */
public class OfficerCmplMovementListFnc extends BaseOfficerCmplListFnc
    implements ODSListFnc
{

  /**
   * Realiza as validações iniciais para a execução da consulta em lista
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */

  }

  /**
   * Recupera uma instância de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new OfficerCmplMovementListFncVO();
  }

  /**
   * Atualiza os dados do FncVO a partir do Fomr
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Atualizando campos comuns
    super.updateFncVOFromForm( form_, fncVO_ );

    // Acertando os tipos
    OfficerCmplMovementListFncVO officerCmplMovementFncVO = ( OfficerCmplMovementListFncVO ) fncVO_;
    OfficerCmplMovementListForm officerCmplMovementListForm = ( OfficerCmplMovementListForm ) form_;

    String lastUpdUserIdSrc = ( officerCmplMovementListForm.getLastUpdUserIdSrc() != null
                                && officerCmplMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                                 ? officerCmplMovementListForm.getLastUpdUserIdSrc()
                                                                                                 : null );
    officerCmplMovementFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

  }

  /**
   * Realiza a consulta em lista do movimento
   */
  public void list( BaseFncVO fncVO_ )
  {
    OfficerCmplMovementListFncVO officerCmplMovementListFncVO = ( OfficerCmplMovementListFncVO ) fncVO_;

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplOfficerCmplMovDAO officerDAO = factory.getTplOfficerCmplMovDAO();
    // Recupera valores do DAO
    DataSet result = officerDAO.list(
                                      officerCmplMovementListFncVO.getOffcrIntnlNbrSrc(),
                                      officerCmplMovementListFncVO.getOffcrNbrSrc(),
                                      officerCmplMovementListFncVO.getOffcrTypeCodeSrc(),
                                      officerCmplMovementListFncVO.getLastUpdUserIdSrc(),
                                      officerCmplMovementListFncVO.getOffcrTextSrc());
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    officerCmplMovementListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      officerCmplMovementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      officerCmplMovementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }
}