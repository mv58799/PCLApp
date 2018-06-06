package com.citibank.ods.modules.client.officercmpl.functionality;

import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.officercmpl.form.OfficerCmplHistoryListForm;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplHistoryListFncVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
/**
 * @author bruno.zanetti
 * 
 * Classe Fnc de Lista do hist�rico. Esta classe concentra as regras de neg�cio
 * do m�dulo de lista do hist�rico
 */
public class OfficerCmplHistoryListFnc extends BaseOfficerCmplListFnc implements
    ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    OfficerCmplHistoryListFncVO listFncVO = ( OfficerCmplHistoryListFncVO ) fncVO_;
    super.load( listFncVO );
    if ( !listFncVO.isFromSearch() )
    {
      listFncVO.setOffcrTextSrc( null );
    }

  }

  /**
   * Realiza as valida��es iniciais para a execu��o da consulta em lista. N�o se
   * aplica � esta transa��o
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
   * Recupera uma instancia de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new OfficerCmplHistoryListFncVO();
  }

  /**
   * Atualiza os dados do FncVO a partir do Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Atualizando campos comuns
    super.updateFncVOFromForm( form_, fncVO_ );

    // Acertando os tipos
    OfficerCmplHistoryListFncVO officerCmplHistoryFncVO = ( OfficerCmplHistoryListFncVO ) fncVO_;
    OfficerCmplHistoryListForm officerListForm = ( OfficerCmplHistoryListForm ) form_;
    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
    try
    {

      officerCmplHistoryFncVO.setOffcrRefDateSrc( formatter.parse( officerListForm.getOffcrRefDateSrc() ) );
    }
    catch ( Exception e1 )
    {
      officerCmplHistoryFncVO.setOffcrRefDateSrc( null );
    }
  }

  /**
   * Realiza a consulta em lista
   */
  public void list( BaseFncVO fncVO_ )
  {

    OfficerCmplHistoryListFncVO officerCmplHistoryListFncVO = ( OfficerCmplHistoryListFncVO ) fncVO_;

    //Obt�m a inst�ncia da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obt�m a inst�ncia do DAO necess�rio
    TplOfficerCmplHistDAO officerDAO = factory.getTplOfficerCmplHistDAO();
    // Recupera valores do DAO
    DataSet result = officerDAO.list(
                                      officerCmplHistoryListFncVO.getOffcrIntnlNbrSrc(),
                                      officerCmplHistoryListFncVO.getOffcrNbrSrc(),
                                      officerCmplHistoryListFncVO.getOffcrTypeCodeSrc(),
                                      officerCmplHistoryListFncVO.getOffcrRefDateSrc() );
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    officerCmplHistoryListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      officerCmplHistoryListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      officerCmplHistoryListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

}