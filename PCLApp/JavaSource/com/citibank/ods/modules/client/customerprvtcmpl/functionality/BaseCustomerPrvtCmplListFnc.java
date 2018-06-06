/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.customerprvtcmpl.form.BaseCustomerPrvtCmplListForm;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.BaseCustomerPrvtCmplListFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplPotentialWealthDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author bruno.zanetti
 *  
 */
public abstract class BaseCustomerPrvtCmplListFnc extends BaseFnc
{
  /**
   * @return
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  protected abstract BaseTplCustomerPrvtCmplDAO getDAO();

  /**
   * @param customerFncVO
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public void getDomainLists( BaseCustomerPrvtCmplListFncVO customerFncVO_ )
  {
    loadClassCmplcDomain( customerFncVO_ );
    loadPrvtCustTypeCodeDomain( customerFncVO_);
    loadWealthPotnlDomain( customerFncVO_ );
    loadCustPrvtStatCodeDomain( customerFncVO_ );
  }

  private void loadClassCmplcDomain(
                                    BaseCustomerPrvtCmplListFncVO customerFncVO_ )
  {

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplClassCmplcDAO classCmplcDAO = factory.getTplClassCmplcDAO();
    //Realiza a consulta no DAO
    DataSet result = classCmplcDAO.loadDomain();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    customerFncVO_.setClassCmplcCodeDomain( result );

  }
  
  private void loadPrvtCustTypeCodeDomain(
									  BaseCustomerPrvtCmplListFncVO customerFncVO_ )
  {

  //Obtém a instância da Factory
  ODSDAOFactory factory = ODSDAOFactory.getInstance();
  //Obtém a instância do DAO necessário
  TplCustomerPrvtTypeDAO customerPrvtTypeDAO = factory.getTplCustomerPrvtTypeDAO();
  //Realiza a consulta no DAO
  DataSet result = customerPrvtTypeDAO.loadDomain();
  //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
  customerFncVO_.setPrvtCustTypeCodeDomain( result );

  }

  private void loadWealthPotnlDomain(
                                     BaseCustomerPrvtCmplListFncVO customerFncVO_ )
  {

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplPotentialWealthDAO potentialWealthDAO = factory.getTplPotentialWealthDAO();
    //Realiza a consulta no DAO
    DataSet result = potentialWealthDAO.loadDomain();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    customerFncVO_.setWealthPotnlCodeDomain( result );

  }

  /**
   * @param customerFncVO
   * @generated "UML to Java
   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
   */
  public void load( BaseCustomerPrvtCmplListFncVO customerFncVO_ )
  {
    getDomainLists( customerFncVO_ );
    loadCustomerText( customerFncVO_ );
    loadOfficerText( customerFncVO_ );
  }

  public void loadCustomerText( BaseCustomerPrvtCmplListFncVO customerFncVO_ )
  {
    if ( customerFncVO_.getCustNbrSrc() != null
         && customerFncVO_.getCustNbrSrc().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr( customerFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      customerFncVO_.setCustTextSrc( customerPrvtEntity.getData().getCustFullNameText() );
    }
    else
    {
      customerFncVO_.setCustTextSrc( "" );
    }
  }

  public void loadOfficerText( BaseCustomerPrvtCmplListFncVO customerFncVO_ )
  {
    if ( customerFncVO_.getOffcrNbrSrc() != null
         && customerFncVO_.isFromSearch() )
    {
      TbgOfficerEntity officerEntity = new TbgOfficerEntity();
      officerEntity.getData().setOffcrNbr( customerFncVO_.getOffcrNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TbgOfficerDAO tbgOfficerDAO = factory.getTbgOfficerDAO();

      //Realiza a consulta no DAO
      officerEntity = ( TbgOfficerEntity ) tbgOfficerDAO.find( officerEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      customerFncVO_.setOffcrTextSrc( officerEntity.getData().getOffcrNameText() );
      customerFncVO_.setFromSearch( false );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Acertando os tipos
    BaseCustomerPrvtCmplListFncVO customerFncVO = ( BaseCustomerPrvtCmplListFncVO ) fncVO_;
    BaseCustomerPrvtCmplListForm customerForm = ( BaseCustomerPrvtCmplListForm ) form_;

    // Atualizando os dados: Form -> FncVO
    customerFncVO.setClassCmplcCodeSrc( toBigInteger(
                                                      customerForm,
                                                      customerForm.getClassCmplcCodeSrc(),
                                                      Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
    if ( customerFncVO.getErrors().size() == 0 )
    {
      customerFncVO.setCustNbrSrc( toBigInteger(
                                                 customerForm,
                                                 customerForm.getCustNbrSrc(),
                                                 Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
    }
    else
    {
      customerFncVO.setCustNbrSrc( null );
    }
    if ( customerFncVO.getErrors().size() == 0 )
    {
      customerFncVO.setOffcrNbrSrc( toBigInteger(
                                                  customerForm,
                                                  customerForm.getOffcrNbrSrc(),
                                                  Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
    }
    else
    {
      customerFncVO.setOffcrNbrSrc( null );
    }

    customerFncVO.setPrvtKeyNbrSrc( toBigInteger(
                                                        customerForm,
                                                        customerForm.getPrvtKeyNbrSrc(),
                                                        Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    customerFncVO.setPrvtCustTypeCodeSrc( toBigInteger(
    													customerForm,
    													customerForm.getPrvtCustTypeCodeSrc(),
    													Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER));
    
    customerFncVO.setPrvtCustNbrSrc( toBigInteger(
                                                   customerForm,
                                                   customerForm.getPrvtCustNbrSrc(),
                                                   Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    customerFncVO.setWealthPotnlCodeSrc( toBigInteger(
                                                       customerForm,
                                                       customerForm.getWealthPotnlCodeSrc(),
                                                       Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    customerFncVO.setEmNbrSrc( customerForm.getEmNbrSrc() );

    customerFncVO.setGlbRevenSysOffcrNbrSrc( toBigInteger(
                                                           customerForm,
                                                           customerForm.getOffcrNbrSrc(),
                                                           Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
    customerFncVO.setSelectedCustNbr( toBigInteger(
                                                    customerForm,
                                                    customerForm.getSelectedCustNbr(),
                                                    Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

    customerFncVO.setGlbRevenSysOffcrNbrSrc( toBigInteger(
                                                           customerForm,
                                                           customerForm.getGlbRevenSysOffcrNbrSrc(),
                                                           Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
    customerFncVO.setCustFullNameSrc( customerForm.getCustTextSrc() != null
                                      && !customerForm.getCustTextSrc().equals(
                                                                                "" )
                                                                                    ? customerForm.getCustTextSrc()
                                                                                    : "" );
    customerFncVO.setOffcrTextSrc( customerForm.getOffcrTextSrc() != null
                                   && !customerForm.getOffcrTextSrc().equals(
                                                                              "" )
                                                                                  ? customerForm.getOffcrTextSrc()
                                                                                  : "" );
    customerFncVO.setCustPrvtStatCodeSrc( customerForm.getCustPrvtStatCodeSrc() != null
                                          && !customerForm.getCustPrvtStatCodeSrc().equals(
                                                                                            "" )
                                                                                                ? customerForm.getCustPrvtStatCodeSrc()
                                                                                                : "" );

    customerFncVO.setClickedSearch( "" );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.Base
   *      Fnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Converte inputs para o tipo correto
    BaseCustomerPrvtCmplListFncVO customerFncVO = ( BaseCustomerPrvtCmplListFncVO ) fncVO_;
    BaseCustomerPrvtCmplListForm customerForm = ( BaseCustomerPrvtCmplListForm ) form_;

    customerForm.setResults( customerFncVO.getResults() );

    //	Atualizando os dados: FncVO -> Form
    if ( customerFncVO.getClassCmplcCodeSrc() != null )
    {
      customerForm.setClassCmplcCodeSrc( customerFncVO.getClassCmplcCodeSrc().toString() );
    }
    
    if (customerFncVO.getPrvtCustTypeCodeSrc() != null )
    {
      customerForm.setPrvtCustTypeCodeSrc( customerFncVO.getPrvtCustTypeCodeSrc().toString() );
    }

    if ( customerFncVO.getCustNbrSrc() != null
         && customerFncVO.getErrors().size() == 0 )
    {
      customerForm.setCustNbrSrc( customerFncVO.getCustNbrSrc().toString() );
    }
    else
    {
      customerForm.setCustNbrSrc( "" );
    }

    if ( customerFncVO.getEmNbrSrc() != null
         && customerFncVO.getErrors().size() == 0 )
    {
      customerForm.setEmNbrSrc( customerFncVO.getEmNbrSrc().toString() );
    }
    else
    {
      customerForm.setEmNbrSrc( "" );
    }

    if ( customerFncVO.getOffcrNbrSrc() != null
         && customerFncVO.getErrors().size() == 0 )
    {
      customerForm.setOffcrNbrSrc( customerFncVO.getOffcrNbrSrc().toString() );
    }
    else
    {
      customerForm.setOffcrNbrSrc( "" );
    }

    if ( customerFncVO.getPrvtKeyNbrSrc() != null )
    {
      customerForm.setPrvtKeyNbrSrc( customerFncVO.getPrvtKeyNbrSrc().toString() );
    }
    else
    {
      customerForm.setPrvtKeyNbrSrc( null );
    }

    if ( customerFncVO.getPrvtCustNbrSrc() != null )
    {
      customerForm.setPrvtCustNbrSrc( customerFncVO.getPrvtCustNbrSrc().toString() );
    }
    else
    {
      customerForm.setPrvtCustNbrSrc( null );
    }

    if ( customerFncVO.getWealthPotnlCodeSrc() != null )
    {
      customerForm.setWealthPotnlCodeSrc( customerFncVO.getWealthPotnlCodeSrc().toString() );
    }

    customerForm.setResults( customerFncVO.getResults() );

    if ( customerFncVO.getClassCmplcCodeDomain() != null )
    {
      customerForm.setClassCmplcCodeDomain( customerFncVO.getClassCmplcCodeDomain() );
    }

    if ( customerFncVO.getPrvtCustTypeCodeDomain() != null)
    {
      customerForm.setPrvtCustTypeCodeDomain( customerFncVO.getPrvtCustTypeCodeDomain() );
    }
    
    if ( customerFncVO.getWealthPotnlCodeDomain() != null )
    {
      customerForm.setWealthPotnlCodeDomain( customerFncVO.getWealthPotnlCodeDomain() );
    }

    if ( customerFncVO.getGlbRevenSysOffcrNbrSrc() != null )
    {
      customerForm.setGlbRevenSysOffcrNbrSrc( customerFncVO.getGlbRevenSysOffcrNbrSrc().toString() );
    }
    else
    {
      customerForm.setGlbRevenSysOffcrNbrSrc( null );
    }

    if ( customerFncVO.getCustTextSrc() != null
         && customerFncVO.getCustNbrSrc() != null )
    {
      customerForm.setCustTextSrc( customerFncVO.getCustTextSrc() );
    }
    
    

    customerForm.setOffcrTextSrc( customerFncVO.getOffcrTextSrc() != null
                                  && !customerFncVO.getOffcrTextSrc().equals(
                                                                              "" )
                                                                                  ? customerFncVO.getOffcrTextSrc()
                                                                                  : "" );
    customerForm.setCustPrvtStatCodeSrc( customerFncVO.getCustPrvtStatCodeSrc() != null
                                         && !customerFncVO.getCustPrvtStatCodeSrc().equals(
                                                                                            "" )
                                                                                                ? customerFncVO.getCustPrvtStatCodeSrc()
                                                                                                : "" );
    customerForm.setCustPrvtStatCodeDomain( customerFncVO.getCustPrvtStatCodeDomain() );

    customerForm.setClickedSearch( customerFncVO.getClickedSearch() );
  }

  public void loadCustPrvtStatCodeDomain(
                                         BaseCustomerPrvtCmplListFncVO customerFncVO_ )
  {
    customerFncVO_.setCustPrvtStatCodeDomain( ODSConstraintDecoder.decodeCustomerStatCode() );
  }
}