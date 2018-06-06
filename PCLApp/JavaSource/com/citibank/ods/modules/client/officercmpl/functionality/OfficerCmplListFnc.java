package com.citibank.ods.modules.client.officercmpl.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplListFncVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
/**
 * @author bruno.zanetti
 * 
 * Classe Fnc da consulta em lista dos Dados complementares de Officer
 */
public class OfficerCmplListFnc extends BaseOfficerCmplListFnc implements
    ODSListFnc
{

  /**
   * Realiza as valida��es iniciais para a execu��o da consulta em lista
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Recupera uma inst�ncia de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new OfficerCmplListFncVO();
  }

  /**
   * Realiza a consulta em lista
   */
  public void list( BaseFncVO fncVO_ )
  {
    OfficerCmplListFncVO officerCmplListFncVO = ( OfficerCmplListFncVO ) fncVO_;

    validateList( fncVO_ );

    //Obt�m a inst�ncia da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obt�m a inst�ncia do DAO necess�rio
    TplOfficerCmplDAO officerDAO = factory.getTplOfficerCmplDAO();
    // Recupera valores do DAO
    DataSet result = officerDAO.list(
                                      officerCmplListFncVO.getOffcrIntnlNbrSrc(),
                                      officerCmplListFncVO.getOffcrNbrSrc(),
                                      officerCmplListFncVO.getOffcrTypeCodeSrc() );

    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    officerCmplListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      officerCmplListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      officerCmplListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

}