package com.citibank.ods.modules.client.officercmpl.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.client.officercmpl.form.BaseOfficerCmplListForm;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.BaseOfficerCmplListFncVO;
import com.citibank.ods.persistence.pl.dao.TplOfficerTypeDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
/**
 * @author bruno.zanetti
 * 
 * Classe base do Fnc de Lista
 */
public abstract class BaseOfficerCmplListFnc extends BaseFnc
{

  /**
   * Carrega os valores existentes de tipos de officers para exibição em combo
   * boxes
   * 
   * @param officerCmplListFncVO_ - Objeto contendo o estado atual da aplicação
   */
  public void loadOfficerTypeDomain(
                                    BaseOfficerCmplListFncVO officerCmplListFncVO_ )
  {

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplOfficerTypeDAO officerTypeDAO = factory.getTplOfficerTypeDAO();
    //Realiza a consulta no DAO
    DataSet result = officerTypeDAO.loadOfficerType();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    officerCmplListFncVO_.setOffcrTypeCodeDomain( result );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BaseOfficerCmplListFncVO baseOfficerCmplListFncVO = ( BaseOfficerCmplListFncVO ) fncVO_;
    loadOfficerTypeDomain( baseOfficerCmplListFncVO );
    baseOfficerCmplListFncVO.setResults( null );
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(
   *      org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Faz cast para os tipos corretos
    BaseOfficerCmplListFncVO officerCmplListFncVO = ( BaseOfficerCmplListFncVO ) fncVO_;
    BaseOfficerCmplListForm officerListForm = ( BaseOfficerCmplListForm ) form_;

    // Atualiza os dados: Form -> FncVO
    BigInteger offcrIntlNbr = ( officerListForm.getOffcrIntlNbrSrc() != null
                                && officerListForm.getOffcrIntlNbrSrc().length() > 0
                                                                                    ? new BigInteger(
                                                                                                      officerListForm.getOffcrIntlNbrSrc() )
                                                                                    : null );
    BigInteger offcrNbr = ( officerListForm.getOfficerNbrSrc() != null
                            && officerListForm.getOfficerNbrSrc().length() > 0
                                                                              ? new BigInteger(
                                                                                                officerListForm.getOfficerNbrSrc() )
                                                                              : null );

    BigInteger SelectedOffcrNbr = ( officerListForm.getSelectedOffcrNbr() != null && !officerListForm.getSelectedOffcrNbr().equals(
                                                                                                                                    "" ) )
                                                                                                                                          ? new BigInteger(
                                                                                                                                                            officerListForm.getSelectedOffcrNbr() )
                                                                                                                                          : null;

    officerCmplListFncVO.setOffcrIntnlNbrSrc( offcrIntlNbr );
    officerCmplListFncVO.setOffcrNbrSrc( offcrNbr );
    officerCmplListFncVO.setOffcrTextSrc( officerListForm.getOfficerNameTextSrc() != null
                                          && !officerListForm.getOfficerNameTextSrc().equals(
                                                                                              "" )
                                                                                                  ? officerListForm.getOfficerNameTextSrc()
                                                                                                  : "" );
    officerCmplListFncVO.setOffcrTypeCodeSrc( officerListForm.getOffcrTypeCodeSrc() );
    officerCmplListFncVO.setSelectedOffcrNbr( SelectedOffcrNbr );

    officerCmplListFncVO.setClickedSearch( "" );
  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(
   *      org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    //  Faz cast para os tipos corretos
    BaseOfficerCmplListFncVO officerCmplListFncVO = ( BaseOfficerCmplListFncVO ) fncVO_;
    BaseOfficerCmplListForm officerListForm = ( BaseOfficerCmplListForm ) form_;

    //  Atualiza os dados: FncVO -> Form
    DataSet results = ( officerCmplListFncVO.getResults() != null
                                                                 ? officerCmplListFncVO.getResults()
                                                                 : null );
    officerListForm.setResults( results );

    officerListForm.setOffcrTypeCodeDomain( officerCmplListFncVO.getOffcrTypeCodeDomain() );

    officerListForm.setOfficerNameTextSrc( officerCmplListFncVO.getOffcrTextSrc() );

    officerListForm.setClickedSearch( officerCmplListFncVO.getClickedSearch() );

    officerListForm.setOfficerNameTextSrc( officerCmplListFncVO.getOffcrTextSrc() != null
                                           && !officerCmplListFncVO.getOffcrTextSrc().equals(
                                                                                              "" )
                                                                                                  ? officerCmplListFncVO.getOffcrTextSrc()
                                                                                                  : "" );
  }

}