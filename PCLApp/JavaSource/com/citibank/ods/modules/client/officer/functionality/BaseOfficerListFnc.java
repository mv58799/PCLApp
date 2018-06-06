package com.citibank.ods.modules.client.officer.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.officer.form.BaseOfficerListForm;
import com.citibank.ods.modules.client.officer.functionality.valueobject.BaseOfficerListFncVO;
import com.citibank.ods.modules.client.officer.functionality.valueobject.OfficerListFncVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerTypeDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.officer.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 26, 2007
 *  
 */

public class BaseOfficerListFnc extends BaseFnc
{

  /**
   * 
   * Atualiza o fncVO com as informações do form.
   *  
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    //  Faz cast para os tipos corretos
    BaseOfficerListFncVO officerListFncVO = ( BaseOfficerListFncVO ) fncVO_;
    BaseOfficerListForm officerListForm = ( BaseOfficerListForm ) form_;

    // Atualiza os dados: Form -> FncVO
    BigInteger offcrNbrSrc = ( officerListForm.getOffcrNbrSrc() != null
                               && officerListForm.getOffcrNbrSrc().length() > 0
                                                                               ? new BigInteger(
                                                                                                 officerListForm.getOffcrNbrSrc() )
                                                                               : null );
    String offcrNameTextSrc = ( officerListForm.getOffcrNameTextSrc() != null
                                && officerListForm.getOffcrNameTextSrc().length() > 0
                                                                                     ? new String(
                                                                                                   officerListForm.getOffcrNameTextSrc() )
                                                                                     : null );
    BigInteger offcrRealNbrSrc = ( officerListForm.getOffcrRealNbrSrc() != null
                                   && officerListForm.getOffcrRealNbrSrc().length() > 0
                                                                                       ? new BigInteger(
                                                                                                         officerListForm.getOffcrRealNbrSrc() )
                                                                                       : null );

    String selectedOffcrNbr = ( officerListForm.getSelectedOffcrNbr() != null
                                && !officerListForm.getSelectedOffcrNbr().equals(
                                                                                  "" )
                                                                                      ? officerListForm.getSelectedOffcrNbr().toString()
                                                                                      : null );
    String typeOffcr = ( officerListForm.getOffcrTypeCodeSrc() != null
                         && !officerListForm.getOffcrTypeCodeSrc().equals( "" )
                                                                               ? officerListForm.getOffcrTypeCodeSrc()
                                                                               : "" );
    BigInteger offcIntNbr = ( officerListForm.getOffcrIntlNbrSrc() != null
                              && officerListForm.getOffcrIntlNbrSrc().length() > 0
                                                                                  ? new BigInteger(
                                                                                                    officerListForm.getOffcrIntlNbrSrc() )
                                                                                  : null );

    officerListFncVO.setOffcrNbrSrc( offcrNbrSrc );
    officerListFncVO.setOffcrNameTextSrc( offcrNameTextSrc );
    officerListFncVO.setOffcrRealNbrSrc( offcrRealNbrSrc );
    officerListFncVO.setSelectedOffcrNbr( selectedOffcrNbr );
    officerListFncVO.setOffcrTypeCodeSrc( typeOffcr );
    officerListFncVO.setOffcrIntlNbrSrc( offcIntNbr );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseOfficerListFncVO officerListFncVO = ( BaseOfficerListFncVO ) fncVO_;
    BaseOfficerListForm officerListForm = ( BaseOfficerListForm ) form_;

    officerListForm.setOffcrIntlNbrSrc( officerListFncVO.getOffcrIntlNbrSrc() != null
                                                                                     ? officerListFncVO.getOffcrIntlNbrSrc().toString()
                                                                                     : "" );
    officerListForm.setOffcrNameTextSrc( officerListFncVO.getOffcrNameTextSrc() != null
                                         && !officerListFncVO.getOffcrNameTextSrc().equals(
                                                                                            "" )
                                                                                                ? officerListFncVO.getOffcrNameTextSrc()
                                                                                                : null );
    officerListForm.setOffcrNbrSrc( officerListFncVO.getOffcrNbrSrc() != null
                                                                             ? officerListFncVO.getOffcrNbrSrc().toString()
                                                                             : "" );
    officerListForm.setOffcrRealNbrSrc( officerListFncVO.getOffcrRealNbrSrc() != null
                                                                                     ? officerListFncVO.getOffcrRealNbrSrc().toString()
                                                                                     : "" );
    officerListForm.setOffcrTypeCodeDomain( officerListFncVO.getOffcrTypeCodeDomain() );
    officerListForm.setResults( officerListFncVO.getResults() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new OfficerListFncVO();
  }

  /**
   * Carrega os valores existentes de tipos de officers para exibição em combo
   * boxes
   * 
   * @param officerListFncVO_ - Objeto contendo o estado atual da aplicação
   */
  public void loadOfficerTypeDomain( BaseOfficerListFncVO fncVO_ )
  {

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplOfficerTypeDAO officerTypeDAO = factory.getTplOfficerTypeDAO();
    //Realiza a consulta no DAO
    DataSet result = officerTypeDAO.loadOfficerType();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    fncVO_.setOffcrTypeCodeDomain( result );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    //Casting do fncVO específico
    BaseOfficerListFncVO fncVO = ( BaseOfficerListFncVO ) fncVO_;
    loadOfficerTypeDomain( fncVO );
    fncVO.setResults( null );
  }

}