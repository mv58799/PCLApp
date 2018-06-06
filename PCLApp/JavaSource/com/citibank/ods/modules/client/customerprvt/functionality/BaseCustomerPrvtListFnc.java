package com.citibank.ods.modules.client.customerprvt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.modules.client.customerprvt.form.BaseCustomerPrvtListForm;
import com.citibank.ods.modules.client.customerprvt.functionality.valueobject.BaseCustomerPrvtListFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplPotentialWealthDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.customerPrvt.functionality;
 * @version 1.0
 * @author l.braga,13/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseCustomerPrvtListFnc extends BaseFnc
{

  /*
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
	// Faz cast para os tipos corretos
	BaseCustomerPrvtListFncVO customerListFncVo = ( BaseCustomerPrvtListFncVO ) fncVO_;
	BaseCustomerPrvtListForm customerListForm = ( BaseCustomerPrvtListForm ) form_;

	// Atualiza os dados: Form -> FncVO
	String orderBy = customerListForm.getOrderBy();
	
	
	BigInteger custNbr = ( customerListForm.getCustNbrSrc() != null
						   && customerListForm.getCustNbrSrc().length() > 0
																		   ? new BigInteger(
																							 customerListForm.getCustNbrSrc() )
																		   : null );
	BigInteger custCpfCnpjNbr = ( customerListForm.getCustCpfCnpjNbrSrc() != null
								  && customerListForm.getCustCpfCnpjNbrSrc().length() > 0
																						 ? new BigInteger(
																										   customerListForm.getCustCpfCnpjNbrSrc() )
																						 : null );
	String custFullNameText = ( customerListForm.getCustFullNameTextSrc() != null
								&& customerListForm.getCustFullNameTextSrc().length() > 0
																						 ? ( customerListForm.getCustFullNameTextSrc() )
																						 : null );
	String custFullName2Text = ( customerListForm.getCustFullName2TextSrc() != null
									&& customerListForm.getCustFullName2TextSrc().length() > 0
																							 ? ( customerListForm.getCustFullName2TextSrc() )
																							 : null );
																							 
	String custFullName3Text = ( customerListForm.getCustFullName3TextSrc() != null
									&& customerListForm.getCustFullName3TextSrc().length() > 0
																							 ? ( customerListForm.getCustFullName3TextSrc() )
																							 : null );
																							 
	String custFullName4Text = ( customerListForm.getCustFullName4TextSrc() != null
									&& customerListForm.getCustFullName4TextSrc().length() > 0
																							 ? ( customerListForm.getCustFullName4TextSrc() )
																							 : null );
																							 
																						 
	BigInteger reltNbr = ( customerListForm.getReltnNbrSrc() != null
						   && customerListForm.getReltnNbrSrc().length() > 0
																			? ( new BigInteger(
																								customerListForm.getReltnNbrSrc() ) )
																			: null );
	BigInteger curAcctNbr = ( customerListForm.getCurAcctNbrSrc() != null
						   && customerListForm.getCurAcctNbrSrc().length() > 0
																			  ? ( new BigInteger(
																								  customerListForm.getCurAcctNbrSrc() ) )
																			  : null );
																			  
	String invstCurAcctNbr = ( customerListForm.getInvstCurAcctNbrSrc() != null
										&& customerListForm.getInvstCurAcctNbrSrc().length() > 0
																								 ? ( customerListForm.getInvstCurAcctNbrSrc() )
																								 : null );
	// Seta os valores do Form no fncVO
	customerListFncVo.setCustNbr( custNbr );
	customerListFncVo.setCustNbrSrc( custNbr );
	customerListFncVo.setCustCpfCnpjNbr( custCpfCnpjNbr );
	customerListFncVo.setCustFullNameText( custFullNameText );
	customerListFncVo.setCustFullName2Text( custFullName2Text );
	customerListFncVo.setCustFullName3Text( custFullName3Text );
	customerListFncVo.setCustFullName4Text( custFullName4Text );
	customerListFncVo.setCurAcctNbrSrc( curAcctNbr );
	customerListFncVo.setInvstCurAcctNbrSrc( invstCurAcctNbr);
	customerListFncVo.setReltnNbrSrc( reltNbr );
	customerListFncVo.setOrderBy(orderBy);

	// Informações referentes a dados complementares de cliente

	// Atualizando os dados: Form -> FncVO
	customerListFncVo.setClassCmplcCodeSrc( toBigInteger(
														  customerListForm,
														  customerListForm.getClassCmplcCodeSrc(),
														  Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	if ( customerListFncVo.getErrors().size() == 0 )
	{
	  customerListFncVo.setOffcrNbrSrc( toBigInteger(
													  customerListForm,
													  customerListForm.getOffcrNbrSrc(),
													  Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
	}
	else
	{
	  customerListFncVo.setOffcrNbrSrc( null );
	}

	customerListFncVo.setPrvtKeyNbrSrc( toBigInteger(
															customerListForm,
															customerListForm.getPrvtKeyNbrSrc(),
															Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	customerListFncVo.setPrvtCustNbrSrc( toBigInteger(
													   customerListForm,
													   customerListForm.getPrvtCustNbrSrc(),
													   Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	customerListFncVo.setWealthPotnlCodeSrc( toBigInteger(
														   customerListForm,
														   customerListForm.getWealthPotnlCodeSrc(),
														   Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	customerListFncVo.setEmNbrSrc( customerListForm.getEmNbrSrc() );

	customerListFncVo.setGlbRevenSysOffcrNbrSrc( toBigInteger(
															   customerListForm,
															   customerListForm.getOffcrNbrSrc(),
															   Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
	customerListFncVo.setSelectedCustNbr( toBigInteger(
														customerListForm,
														customerListForm.getSelectedCustNbr(),
														Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	customerListFncVo.setGlbRevenSysOffcrNbrSrc( toBigInteger(
															   customerListForm,
															   customerListForm.getGlbRevenSysOffcrNbrSrc(),
															   Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	customerListFncVo.setPrvtCustTypeCodeSrc( toBigInteger(
															  customerListForm,
															  customerListForm.getPrvtCustTypeCodeSrc(),
															  Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
															  
	customerListFncVo.setOffcrTextSrc( customerListForm.getOffcrTextSrc() != null
									   && !customerListForm.getOffcrTextSrc().equals(
																					  "" )
										//Correçao G&P - 09/05/2008 - rviana:
									   && !customerListForm.getOffcrNbrSrc().equals(
																					  "" )
										//Fim correçao G&P - 09/05/2008.
																						  ? customerListForm.getOffcrTextSrc()
																						  : "" );
	customerListFncVo.setCustPrvtStatCodeSrc( customerListForm.getCustPrvtStatCodeSrc() != null
																							   ? customerListForm.getCustPrvtStatCodeSrc()
																							   : "" );

	customerListFncVo.setClickedSearch( "" );

  }

  /*
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
	//  Faz cast para os tipos corretos
	BaseCustomerPrvtListFncVO customerListFnvVO = ( BaseCustomerPrvtListFncVO ) fncVO_;
	BaseCustomerPrvtListForm customerLisForm = ( BaseCustomerPrvtListForm ) form_;

	//  Atualiza os dados: FncVO -> Form
	/*DataSet results = ( customerListFnvVO.getResults() != null
															  ? customerListFnvVO.getResults()
															  : null );
	customerLisForm.setResults( results );*/
	
	ArrayList list = ( customerListFnvVO.getListCustomer() != null
													   ? customerListFnvVO.getListCustomer()
														: null );    															  
	customerLisForm.setCustList(list);

	// Informações referentes a dados complementares de cliente
	// Atualizando os dados: FncVO -> Form

	if ( customerListFnvVO.getCustNbrSrc() != null )
	{
	  customerLisForm.setCustNbrSrc( customerListFnvVO.getCustNbrSrc().toString() );
	}
	else
	{
	  customerLisForm.setCustNbrSrc( "" );
	}

	if ( customerListFnvVO.getClassCmplcCodeSrc() != null )
	{
	  customerLisForm.setClassCmplcCodeSrc( customerListFnvVO.getClassCmplcCodeSrc().toString() );
	}

	if ( customerListFnvVO.getPrvtCustTypeCodeSrc() != null )
	{
	  customerLisForm.setPrvtCustTypeCodeSrc( customerListFnvVO.getPrvtCustTypeCodeSrc().toString() );
	}
	
	if ( customerListFnvVO.getEmNbrSrc() != null
		 && !customerListFnvVO.getEmNbrSrc().equals( "" ) )
	{
	  customerLisForm.setEmNbrSrc( customerListFnvVO.getEmNbrSrc().toString() );
	}
	else
	{
	  customerLisForm.setEmNbrSrc( "" );
	}

	if ( customerListFnvVO.getOffcrNbrSrc() != null )
	{
	  customerLisForm.setOffcrNbrSrc( customerListFnvVO.getOffcrNbrSrc().toString() );
	}
	else
	{
	  customerLisForm.setOffcrNbrSrc( "" );
	}

	if ( customerListFnvVO.getPrvtKeyNbrSrc() != null )
	{
	  customerLisForm.setPrvtKeyNbrSrc( customerListFnvVO.getPrvtKeyNbrSrc().toString() );
	}
	else
	{
	  customerLisForm.setPrvtKeyNbrSrc( null );
	}

	if ( customerListFnvVO.getPrvtCustNbrSrc() != null )
	{
	  customerLisForm.setPrvtCustNbrSrc( customerListFnvVO.getPrvtCustNbrSrc().toString() );
	}
	else
	{
	  customerLisForm.setPrvtCustNbrSrc( null );
	}

	if ( customerListFnvVO.getWealthPotnlCodeSrc() != null )
	{
	  customerLisForm.setWealthPotnlCodeSrc( customerListFnvVO.getWealthPotnlCodeSrc().toString() );
	}

	if ( customerListFnvVO.getClassCmplcCodeDomain() != null )
	{
	  customerLisForm.setClassCmplcCodeDomain( customerListFnvVO.getClassCmplcCodeDomain() );
	}

	if ( customerListFnvVO.getPrvtCustTypeDomain() != null )
	{
	  customerLisForm.setPrvtCustTypeCodeDomain( customerListFnvVO.getPrvtCustTypeDomain() );
	}
	
	if ( customerListFnvVO.getWealthPotnlCodeDomain() != null )
	{
	  customerLisForm.setWealthPotnlCodeDomain( customerListFnvVO.getWealthPotnlCodeDomain() );
	}

	if ( customerListFnvVO.getGlbRevenSysOffcrNbrSrc() != null )
	{
	  customerLisForm.setGlbRevenSysOffcrNbrSrc( customerListFnvVO.getGlbRevenSysOffcrNbrSrc().toString() );
	}
	else
	{
	  customerLisForm.setGlbRevenSysOffcrNbrSrc( null );
	}

	if ( customerListFnvVO.getCustTextSrc() != null
		 && customerListFnvVO.getCustNbrSrc() != null )
	{
	  customerLisForm.setCustTextSrc( customerListFnvVO.getCustTextSrc() );
	}
	else
	{
	  customerLisForm.setCustTextSrc( "" );
	}

	customerLisForm.setOffcrTextSrc( customerListFnvVO.getOffcrTextSrc() != null
									 && !customerListFnvVO.getOffcrTextSrc().equals(
																					 "" )
										//Correçao G&P - 09/05/2008 - rviana:
									   && !customerListFnvVO.getOffcrNbrSrc().equals(
																					  "" )
										//Fim correçao G&P - 09/05/2008.
																						 ? customerListFnvVO.getOffcrTextSrc()
																						 : "" );
	customerLisForm.setCustPrvtStatCodeSrc( customerListFnvVO.getCustPrvtStatCodeSrc() != null
																							  ? customerListFnvVO.getCustPrvtStatCodeSrc()
																							  : "" );
	customerLisForm.setCustFullNameTextSrc( customerListFnvVO.getCustFullNameText() != null
											&& !customerListFnvVO.getCustFullNameText().equals(
																								"" )
																									? customerListFnvVO.getCustFullNameText()
																									: "" );
	customerLisForm.setCustFullName2TextSrc( customerListFnvVO.getCustFullName2Text() != null
												&& !customerListFnvVO.getCustFullName2Text().equals(
																									"" )
																										? customerListFnvVO.getCustFullName2Text()
																										: "" );
	customerLisForm.setCustFullName3TextSrc( customerListFnvVO.getCustFullName3Text() != null
												&& !customerListFnvVO.getCustFullName3Text().equals(
																									"" )
																										? customerListFnvVO.getCustFullName3Text()
																										: "" );																																																	
	customerLisForm.setCustFullName4TextSrc( customerListFnvVO.getCustFullName4Text() != null
												&& !customerListFnvVO.getCustFullName4Text().equals(
																									"" )
																										? customerListFnvVO.getCustFullName4Text()
																										: "" );																								
	customerLisForm.setCustPrvtStatCodeDomain( customerListFnvVO.getCustPrvtStatCodeDomain() );

	customerLisForm.setClickedSearch( customerListFnvVO.getClickedSearch() );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
	//
  }

  public void getDomainLists( BaseCustomerPrvtListFncVO customerListFnvVO_ )
  {
	loadClassCmplcDomain( customerListFnvVO_ );
	loadPrvtCustTypeDomain( customerListFnvVO_);
	loadWealthPotnlDomain( customerListFnvVO_ );
	loadCustPrvtStatCodeDomain( customerListFnvVO_ );
  }

  private void loadClassCmplcDomain(
									BaseCustomerPrvtListFncVO customerListFnvVO_ )
  {
	//Obtém a instância da Factory
	ODSDAOFactory factory = ODSDAOFactory.getInstance();
	//Obtém a instância do DAO necessário
	TplClassCmplcDAO classCmplcDAO = factory.getTplClassCmplcDAO();
	//Realiza a consulta no DAO
	DataSet result = classCmplcDAO.loadDomain();
	//Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
	customerListFnvVO_.setClassCmplcCodeDomain( result );
  }
  
  private void loadPrvtCustTypeDomain(
									  BaseCustomerPrvtListFncVO customerListFnvVO_ )
  {
    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplCustomerPrvtTypeDAO prvtCustTypeDAO = factory.getTplCustomerPrvtTypeDAO();
    //Realiza a consulta no DAO
    DataSet result = prvtCustTypeDAO.loadDomain();
    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    customerListFnvVO_.setPrvtCustTypeDomain( result );
  }
  
  private void loadWealthPotnlDomain(
									 BaseCustomerPrvtListFncVO customerListFnvVO_ )
  {
	//Obtém a instância da Factory
	ODSDAOFactory factory = ODSDAOFactory.getInstance();
	//Obtém a instância do DAO necessário
	TplPotentialWealthDAO potentialWealthDAO = factory.getTplPotentialWealthDAO();
	//Realiza a consulta no DAO
	DataSet result = potentialWealthDAO.loadDomain();
	//Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
	customerListFnvVO_.setWealthPotnlCodeDomain( result );
  }

  public void loadOfficerText( BaseCustomerPrvtListFncVO customerFncVO_ )
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

  public void loadCustPrvtStatCodeDomain(
										 BaseCustomerPrvtListFncVO customerFncVO_ )
  {
	customerFncVO_.setCustPrvtStatCodeDomain( ODSConstraintDecoder.decodeCustomerStatCode() );
  }

}