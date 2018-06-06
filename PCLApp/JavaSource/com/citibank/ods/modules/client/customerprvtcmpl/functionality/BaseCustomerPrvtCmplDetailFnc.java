/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TbgOfficerEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.customerprvtcmpl.form.BaseCustomerPrvtCmplDetailForm;
import com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject.BaseCustomerPrvtCmplDetailFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplClassCmplcDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtTypeDAO;
import com.citibank.ods.persistence.pl.dao.TplPotentialWealthDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author gerson.a.rodrigues
 *  
 */
public abstract class BaseCustomerPrvtCmplDetailFnc extends BaseFnc
{

  protected static final String C_USER_ID_NAO_PERMITIDO = "User ID não permitido";

  protected static final String C_EM_NBR = "Número EM";

  protected static final String C_CUST_NBR = "Número do Cliente";

  protected static final String C_OFFCR_NBR = "Número do Officer";

  protected static final String C_INSERT = "inserido";

  protected static final String C_UPDATE = "alterado";
  
  protected static final String C_PRVT_CUST_TYPE_CODE = "Código do Tipo de Cliente";

  protected static final String C_CLASS_CMPLC_CODE = "Código de Classificação Compliance";

  protected static final String C_WEALTH_POTNL_CODE = "Código de Potencial de Receita";

  protected static final String C_SUCESS = "sucess";

  protected static final String C_ERROR = "error";

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
  public void getDomainLists( BaseCustomerPrvtCmplDetailFncVO customerFncVO_ )
  {
	loadClassCmplcDomain( customerFncVO_ );
	loadPrvtCustTypeCodeDomain( customerFncVO_ );
	loadWealthPotnlDomain( customerFncVO_ );
	loadCustPrvtStatCodeDomain( customerFncVO_ );
  }

  public void load( BaseCustomerPrvtCmplDetailFncVO customerFncVO_ )
  {
	getDomainLists( customerFncVO_ );
	loadCustText( customerFncVO_ );
	loadOffcrText( customerFncVO_ );
	loadMailRecvIndDomain( customerFncVO_ );
	loadOffclMailRecvIndDomain( customerFncVO_ );
  }

  private void loadMailRecvIndDomain(
									 BaseCustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO_ )
  {

	customerPrvtCmplDetailFncVO_.setMailRecvIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  private void loadOffclMailRecvIndDomain(
										  BaseCustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO_ )
  {

	customerPrvtCmplDetailFncVO_.setOffclMailRecvIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  public void loadCustText( BaseCustomerPrvtCmplDetailFncVO customerFncVO_ )
  {
	if ( customerFncVO_.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr() != null
		 && customerFncVO_.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr().longValue() > 0 )
	{
	  TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
	  customerPrvtEntity.getData().setCustNbr(
											   customerFncVO_.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr() );

	  //Obtém a instância da Factory
	  ODSDAOFactory factory = ODSDAOFactory.getInstance();
	  //Obtém a instância do DAO necessário
	  TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

	  //Realiza a consulta no DAO
	  customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

	  //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
	  customerFncVO_.setCustText( customerPrvtEntity.getData().getCustFullNameText() );
	}
  }

  public void loadOffcrText( BaseCustomerPrvtCmplDetailFncVO customerFncVO_ )
  {
	if ( customerFncVO_.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr() != null
		 && customerFncVO_.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr().intValue() > 0 )
	{
	  TbgOfficerEntity officerEntity = new TbgOfficerEntity();
	  officerEntity.getData().setOffcrNbr(
										   customerFncVO_.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr() );

	  //Obtém a instância da Factory
	  ODSDAOFactory factory = ODSDAOFactory.getInstance();
	  //Obtém a instância do DAO necessário
	  TbgOfficerDAO tbgOfficerDAO = factory.getTbgOfficerDAO();

	  //Realiza a consulta no DAO
	  officerEntity = ( TbgOfficerEntity ) tbgOfficerDAO.find( officerEntity );

	  //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
	  customerFncVO_.setOffcrText( officerEntity.getData().getOffcrNameText() );
	}
  }

  private void loadClassCmplcDomain(
									BaseCustomerPrvtCmplDetailFncVO customerFncVO_ )
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
									  BaseCustomerPrvtCmplDetailFncVO customerFncVO_ )
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
									 BaseCustomerPrvtCmplDetailFncVO customerFncVO_ )
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

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
	// Acertando os tipos
	BaseCustomerPrvtCmplDetailFncVO customerFncVO = ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_;
	BaseCustomerPrvtCmplDetailForm customerForm = ( BaseCustomerPrvtCmplDetailForm ) form_;
    
	if(customerForm.getOffcrClear()){
		customerForm.setOffcrNbrSrc(null);
		customerForm.setOffcrText("");
		customerFncVO.setOffcrClear(customerForm.getOffcrClear());
		customerForm.setOffcrClear(false);		
	}

	// Atualizando os dados: Form -> FncVO	
	
	String[] codeArray = null;
	if(customerForm.getSelectedCode()!= null && !customerForm.getSelectedCode().equals("")){
	  codeArray = customerForm.getSelectedCode().split(","); 	
	}
    
	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setCustNbr(
																		   toBigInteger(
																						 customerForm,
																						 customerForm.getCustNbrSrc(),
																						 Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
    
    if(codeArray != null){
		customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setCustNbr(new BigInteger(codeArray[0]));																							 
    }

	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setClassCmplcCode(
																				  toBigInteger(
																								customerForm,
																								customerForm.getClassCmplcCode(),
																								Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );
																								
	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setPrvtCustTypeCode(
																					toBigInteger(
																								  customerForm,
																								  customerForm.getPrvtCustTypeCode(),
																								  Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );																							

	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setOffcrNbr(
																			toBigInteger(
																						  customerForm,
																						  customerForm.getOffcrNbrSrc(),
																						  Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setPrvtKeyNbr(
																					toBigInteger(
																								  customerForm,
																								  customerForm.getPrvtKeyNbr(),
																								  Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setPrvtCustNbr(
																			   toBigInteger(
																							 customerForm,
																							 customerForm.getPrvtCustNbr(),
																							 Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	
	if ( customerForm.getEmNbr()!= null && !customerForm.getEmNbr().equals("") ){
		customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setEmNbr(customerForm.getEmNbr());
	}
	else{
		customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setEmNbr(null);
	}

	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setWealthPotnlCode(
																				   toBigInteger(
																								 customerForm,
																								 customerForm.getWealthPotnlCode(),
																								 Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	customerFncVO.setClickedSearch( "" );

	if ( customerForm.getMailRecvInd() != null
		 && customerForm.getMailRecvInd().equals(
												  Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM ) )
	{
	  customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setMailRecvInd(
																				 Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM );
	}
	else
	{
	  customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setMailRecvInd(
																				 Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO );
	}

	if ( customerForm.getOffclMailRecvInd() != null
		 && customerForm.getOffclMailRecvInd().equals(
													   Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM ) )
	{
	  customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setOffclMailRecvInd(
																					  Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_SIM );
	}
	else
	{
	  customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setOffclMailRecvInd(
																					  Globals.BooleanIndicatorKeys.C_BOOLEAN_IND_NAO );
	}

	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setGlbRevenSysOffcrNbr(
																					   toBigInteger(
																									 customerForm,
																									 customerForm.getGlbRevenSysOffcrNbr(),
																									 Globals.FormatKeys.C_FORMAT_NUMBER_INTEGER ) );

	String custPrvtStatCode = customerForm.getCustPrvtStatCode() != null
																		? customerForm.getCustPrvtStatCode()
																		: "";
	customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().setCustPrvtStatCode(
																					custPrvtStatCode );

  } /*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
	 *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
	// Converte inputs para o tipo correto
	BaseCustomerPrvtCmplDetailFncVO customerFncVO = ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_;
	BaseCustomerPrvtCmplDetailForm customerForm = ( BaseCustomerPrvtCmplDetailForm ) form_;	

	//	Atualizando os dados: FncVO -> Form
	customerForm.setClassCmplcCode( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getClassCmplcCode() != null
																														  ? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getClassCmplcCode().toString()
																															  : "" );                                                                                                               
                                                                        
	customerForm.setPrvtCustTypeCode( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getPrvtCustTypeCode()!= null
																														  ? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getPrvtCustTypeCode().toString()
																														      : "" );
	
	customerForm.setCustNbrSrc( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr() != null
																											   ? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr().toString()
																											   : "" );
																											   
	customerForm.setEmNbr( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr() == null
													     												? "" 
																										:customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr()  );
																										
																										
	customerForm.setPrvtKeyNbr( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getPrvtKeyNbr() != null
									  && customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getPrvtKeyNbr().longValue() > 0
																																		 ? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getPrvtKeyNbr().toString()
																																		 : "" );
	customerForm.setPrvtCustNbr( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getPrvtCustNbr() != null
								 && customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getPrvtCustNbr().longValue() > 0
																															   ? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getPrvtCustNbr().toString()
																															   : "" );
	customerForm.setWealthPotnlCode( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getWealthPotnlCode() != null
																															? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getWealthPotnlCode().toString()
																															: "" );
	customerForm.setMailRecvInd( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getMailRecvInd() != null
																													? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getMailRecvInd()
																													: "" );
	customerForm.setOffclMailRecvInd( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffclMailRecvInd() != null
																															  ? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffclMailRecvInd()
																															  : "" );
	customerForm.setGlbRevenSysOffcrNbr( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getGlbRevenSysOffcrNbr() != null
										 && customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getGlbRevenSysOffcrNbr().longValue() > 0
																																			   ? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getGlbRevenSysOffcrNbr().toString()
																																			   : "" );
	customerForm.setClassCmplcCodeDomain( customerFncVO.getClassCmplcCodeDomain() );
	customerForm.setPrvtCustTypeCodeDomain( customerFncVO.getPrvtCustTypeCodeDomain());
	customerForm.setWealthPotnlCodeDomain( customerFncVO.getWealthPotnlCodeDomain() );
	customerForm.setOffclMailRecvIndDomain( customerFncVO.getOffclMailRecvIndDomain() );
	customerForm.setMailRecvIndDomain( customerFncVO.getMailRecvIndDomain() );
	customerForm.setCustPrvtStatCodeDomain( customerFncVO.getCustPrvtStatCodeDomain() );

	if ( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr() != null
		 && customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr().longValue() > 0 )
	{
	  customerForm.setCustText( customerFncVO.getCustText() );
	}
	else
	{
	  customerForm.setCustText( "" );
	}

    
	if ( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr() != null
		 && customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr().intValue() > 0 )
	{
	  //Alteraçao G&P INICIO
	  customerForm.setOffcrNbrSrc( customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getOffcrNbr().toString() );
	  //Alteraçao G&P FIM
	  customerForm.setOffcrText( customerFncVO.getOffcrText() );
	}
	else
	{
	  //Alteraçao G&P INICIO
	  customerForm.setOffcrNbrSrc( "" );
	  //Alteraçao G&P FIM
	  customerForm.setOffcrText( "" );
	}

	String custPrvtStatCode = customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustPrvtStatCode() != null
																													  ? customerFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustPrvtStatCode()
																													  : "";
	customerForm.setCustPrvtStatCode( custPrvtStatCode );
	customerForm.setClickedSearch( customerFncVO.getClickedSearch() );
   

  }

  /**
   * Recupera um elementos de Categoria de Risco, dado os critérios
   */
  public void loadCustomerPrvtCmpl( BaseCustomerPrvtCmplDetailFncVO detailFncVO_ )
  {
	BaseTplCustomerPrvtCmplDAO customerDAO = this.getDAO();
	BaseTplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity = ( BaseTplCustomerPrvtCmplEntity ) customerDAO.find( detailFncVO_.getBaseTplCustomerPrvtCmplEntity() );
	detailFncVO_.setBaseTplCustomerPrvtCmplEntity( tplCustomerPrvtCmplEntity );
  }

  //Combo com o status do cliente - "A" Ativo ou "I" Inativo
  private void loadCustPrvtStatCodeDomain(
										  BaseCustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO_ )
  {
	customerPrvtCmplDetailFncVO_.setCustPrvtStatCodeDomain( ODSConstraintDecoder.decodeCustomerStatCode() );
  }

  public boolean verifyEmNbrByCpf( BaseFncVO fncVO_ )
  {

	//Cria uma instancia do DAO
	TplCustomerPrvtCmplDAO customerPrvtCmplDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtCmplDAO();
	TplCustomerPrvtDAO customerPrvtDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtDAO();
	TplCustomerPrvtEntity customerPrvtEntity;
	TplCustomerPrvtEntity customerPrvtEntityNew;
	TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity = null;

	BaseCustomerPrvtCmplDetailFncVO customerPrvtCmplDetailFncVO = ( BaseCustomerPrvtCmplDetailFncVO ) fncVO_;

	DataSet result = null;
	DataSetRow row;
	boolean existsEmNbr = true;

	result = customerPrvtCmplDAO.list(
									   customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getEmNbr(),
									   null, null, null, null, null, null,
									   null, null, null);

	if ( result.size() > 0 )
	{
	  tplCustomerPrvtCmplEntity = new TplCustomerPrvtCmplEntity();
	  row = result.getRow( 0 );

	  tplCustomerPrvtCmplEntity.getData().setCustNbr(
													  new BigInteger(
																	  row.getStringByName( "CUST_NBR" ) ) );
	  tplCustomerPrvtCmplEntity.getData().setEmNbr(
													row.getStringByName( "EM_NBR" ) );
	}

	//Cria uma instancia da entity de customer e passa como chave o número do
	// cliente que esta sendo complementado
	customerPrvtEntity = new TplCustomerPrvtEntity();
	customerPrvtEntity.getData().setCustNbr(
											 customerPrvtCmplDetailFncVO.getBaseTplCustomerPrvtCmplEntity().getData().getCustNbr() );
	customerPrvtEntity = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( customerPrvtEntity );

	//Cria uma instancia da entitie de customer e passa o número do cliente que
	// retornou da consulta de mesmo EM.
	customerPrvtEntityNew = new TplCustomerPrvtEntity();
	customerPrvtEntityNew.getData().setCustNbr(
												tplCustomerPrvtCmplEntity.getData().getCustNbr() );
	customerPrvtEntityNew = ( TplCustomerPrvtEntity ) customerPrvtDAO.find( customerPrvtEntityNew );

	if ( customerPrvtEntity.getData().getCustCpfCnpjNbr() != null
		 && customerPrvtEntityNew.getData().getCustCpfCnpjNbr() != null )
	{
	  if ( customerPrvtEntity.getData().getCustCpfCnpjNbr().equals(
																	customerPrvtEntityNew.getData().getCustCpfCnpjNbr() ) )
	  {
		
		existsEmNbr = false;
	  }

	}

	return existsEmNbr;

  }

}