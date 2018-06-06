package com.citibank.ods.modules.client.customerprvt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.dataset.DataSetRow;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.customerprvt.form.CustomerPrvtListForm;
import com.citibank.ods.modules.client.customerprvt.functionality.valueobject.CustomerPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.customerPrvt.functionality;
 * @version 1.0
 * @author l.braga,13/03/2007
 */

public class CustomerPrvtListFnc extends BaseCustomerPrvtListFnc implements
	ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
	CustomerPrvtListFncVO customerListFncVO = ( CustomerPrvtListFncVO ) fncVO_;
	ArrayList list = null;
	DataSetRow row;
	CustomerPrvtGrid custGrid;  
	String flagAllData = "S";
	
	if ( !customerListFncVO.hasErrors() )
	{
	  //Obtém a instância da Factory
	  ODSDAOFactory factory = ODSDAOFactory.getInstance();

	  //Obtém a instância do DAO necessário
	  TplCustomerPrvtDAO customerPrvtDAO = factory.getTplCustomerPrvtDAO();

	  DataSet result = customerPrvtDAO.list(
											 customerListFncVO.getCustNbr(),
											 customerListFncVO.getCustFullNameText(),
											 customerListFncVO.getCustFullName2Text(),
											 customerListFncVO.getCustFullName3Text(),
											 customerListFncVO.getCustFullName4Text(),
											 customerListFncVO.getCurAcctNbrSrc(),
											 customerListFncVO.getInvstCurAcctNbrSrc(),
											 customerListFncVO.getCustCpfCnpjNbr(),
		                                     customerListFncVO.getReltnNbrSrc(),
											 customerListFncVO.getPrvtCustNbrSrc(),
											 customerListFncVO.getPrvtKeyNbrSrc(),
											 customerListFncVO.getEmNbrSrc(),
											 customerListFncVO.getOffcrNbrSrc(),
											 customerListFncVO.getWealthPotnlCodeSrc(),
											 customerListFncVO.getClassCmplcCodeSrc(),
											 customerListFncVO.getOffcrTextSrc(),
											 customerListFncVO.getCustPrvtStatCodeSrc(),
											 customerListFncVO.getPrvtCustTypeCodeSrc(),
											 customerListFncVO.getOrderBy() );					 

		//Monta o arrayList que será utilizado para o DisplayTable
	  if(result.size() > 0){
		list = new ArrayList();
	  }	  
	  
	  //limita a 2000 mil registros, para corrigir erro de Java heap  
	  if (result.size() > MAXIMUM_REGISTERS){
		  result = result.newDataSetByRange(1, MAXIMUM_REGISTERS);
		  flagAllData = "N";
	  }
	  	  
	  for ( int indexRow = 0; indexRow < result.size(); indexRow++ )
	  {
	    row = result.getRow( indexRow );			

		custGrid = new CustomerPrvtGrid();
		custGrid.setCmplDataButtonControl(Integer.valueOf(row.getStringByName("CMPL_DATA_BUTTON_CONTROL")).intValue());
		custGrid.setCustNbr(Integer.valueOf(row.getStringByName("CUST_NBR")));
		custGrid.setCustFullNameText(row.getStringByName("CUST_FULL_NAME_TEXT"));
		    
		if(row.getStringByName("CUST_CPF_CNPJ_NBR") != null){				
		  custGrid.setCustCpfCnpjNbr(BigInteger.valueOf(row.getBigDecimalByName("CUST_CPF_CNPJ_NBR").longValue()));
		}
		      
		if(row.getStringByName("RELTN_NBR")!= null){
		  custGrid.setReltnNbr(BigInteger.valueOf(row.getBigDecimalByName("RELTN_NBR").longValue()));				  
		}	
		    
		if(row.getStringByName("CUR_ACCT_NBR")!= null){
		  custGrid.setCurAcctNbr(BigInteger.valueOf(row.getBigDecimalByName("CUR_ACCT_NBR").longValue()));						    
		}	
		        
		if(row.getStringByName("PRVT_CUST_NBR")!= null){
		  custGrid.setPrvtCustNbr(BigInteger.valueOf(row.getBigDecimalByName("PRVT_CUST_NBR").longValue()));				  	
		}	
		    
		if(row.getStringByName("PRVT_KEY_NBR")!= null){
		  custGrid.setPrvtPrincCustNbr(BigInteger.valueOf(row.getBigDecimalByName("PRVT_KEY_NBR").longValue()));						    	
		}
		    
		if(row.getStringByName("OFFCR_NAME_TEXT")!= null){
		  custGrid.setOffcrNameText(row.getStringByName("OFFCR_NAME_TEXT"));						    	
		}
		if(row.getStringByName("APROVACAO") != null && !row.getStringByName("APROVACAO").equals("")){
		  custGrid.setAprovacao(row.getStringByName("APROVACAO"));
		}
		    
		list.add( custGrid );
	  }
	  
	  customerListFncVO.setListCustomer(list);	  	  
	  
	  // Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
	  customerListFncVO.setResults( result );
	  customerListFncVO.setFlagAllData(flagAllData);
	  
	  if ( result.size() > 0 )
	  {
		customerListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
	  }
	  else
	  {
		customerListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
	  }
	}

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
	return new CustomerPrvtListFncVO();
  }

  /**
   * Carregamentos iniciais da tela de consulta
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
	CustomerPrvtListFncVO customerPrvtListFncVO = ( CustomerPrvtListFncVO ) fncVO_;

	// Informações referentes a dados complementares de cliente
	getDomainLists( customerPrvtListFncVO );
	loadOfficerText( customerPrvtListFncVO );

	//  customerPrvtListFncVO.setCustNbrSrc( null );
	customerPrvtListFncVO.setResults( null );
	customerPrvtListFncVO.setListCustomer(null);

  }

  public void clearPage( BaseFncVO fncVO_ )
  {
	CustomerPrvtListFncVO customerListFncVO = ( CustomerPrvtListFncVO ) fncVO_;

	customerListFncVO.clearErrors();
	customerListFncVO.clearMessages();
	customerListFncVO.setResults( null );
	//Correçao G&P - 09/05/2008 - rviana:
	customerListFncVO.setOffcrNbrSrc( null );
	customerListFncVO.setOffcrTextSrc( null );
	//Fim correçao G&P - 09/05/2008.

  }
  
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    CustomerPrvtListFncVO fncVO = ( CustomerPrvtListFncVO ) fncVO_;
    CustomerPrvtListForm form = ( CustomerPrvtListForm ) form_;

    String flagAllData = ( fncVO.getFlagAllData() != null
                             && !"".equals( fncVO.getFlagAllData() )
                                                                      ? fncVO.getFlagAllData()
                                                                      : "" );
    //Atualiza o form com a flag de que retornou todos os dados da base
    form.setFlagAllData( flagAllData );
  }


}