package com.citibank.ods.modules.client.mrdocprvt.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.mrdocprvt.form.BaseMrDocPrvtListForm;
import com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject.BaseMrDocPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author m.nakamura
 * 
 * Implementação da funcionalidade de consulta em list da tabela de Memória de
 * Risco.
 */
public abstract class BaseMrDocPrvtListFnc extends BaseFnc
{

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Faz cast para os tipos corretos
    BaseMrDocPrvtListFncVO fncVO = ( BaseMrDocPrvtListFncVO ) fncVO_;
    BaseMrDocPrvtListForm form = ( BaseMrDocPrvtListForm ) form_;

    // Número do cliente (campo de pesquisa)
    BigInteger custNbr = ( form.getCustNbrSrc() != null
                           && form.getCustNbrSrc().length() > 0
                                                               ? new BigInteger(
                                                                                 form.getCustNbrSrc() )
                                                               : null );

    // Nome do cliente
    String custFullNameText = ( form.getCustFullNameTextSrc() != null
                                && form.getCustFullNameTextSrc().length() > 0
                                                                             ? form.getCustFullNameTextSrc()
                                                                             : null );
                                                                             
   //Codigo do Co-Titular 1
   String custFullName2Text = ( form.getCustFullName2TextSrc() != null
								&& form.getCustFullName2TextSrc().length() > 0
																			 ? form.getCustFullName2TextSrc()
																			 : null );
																			
  //Codigo do Co-Titular 2
  String custFullName3Text = ( form.getCustFullName3TextSrc() != null
								&& form.getCustFullName3TextSrc().length() > 0
																			 ? form.getCustFullName3TextSrc()
																			 : null );
																			  
  //Codigo do Co-Titular 3
  String custFullName4Text = ( form.getCustFullName4TextSrc() != null
								&& form.getCustFullName4TextSrc().length() > 0
																			 ? form.getCustFullName4TextSrc()
																			 : null );

    // Número da conta corrente (campo de pesquisa)
    BigInteger curAcctNbr = ( form.getCurAcctNbrSrc() != null
                              && form.getCurAcctNbrSrc().length() > 0
                                                                     ? new BigInteger(
                                                                                       form.getCurAcctNbrSrc() )
                                                                     : null );
                                                                     
   // Numero da conta CCI (campo de pesquisa)
    String invstCurAcctNbr = ( form.getInvstCurAcctNbrSrc() != null
								  && form.getInvstCurAcctNbrSrc().length() > 0
	                                                                         ? form.getMrInvstCurAcctIndSrc()
			                                                                 : null );

    // Indicador da conta CCI (campo de pesquisa)
    String mrInvstCurAcctInd = ( form.getMrInvstCurAcctIndSrc() != null
                                 && form.getMrInvstCurAcctIndSrc().length() > 0
                                                                               ? form.getMrInvstCurAcctIndSrc()
                                                                               : null );

    String executingList = ( form.getExecutingList() != null
                             && !form.getExecutingList().equals( "" )
                                                                     ? form.getExecutingList()
                                                                     : "" );
    
    // Numero de Relacionamento
	BigInteger reltnNbr = ( form.getReltnNbr() != null
                            && form.getReltnNbr().length() > 0
										                               ? new BigInteger(
														                                form.getReltnNbr() )
										                               : null );                                                              

    fncVO.setCustNbr( custNbr );
    fncVO.setCustFullNameText( custFullNameText );
	fncVO.setCustFullName2Text( custFullName2Text );
	fncVO.setCustFullName3Text( custFullName3Text );
	fncVO.setCustFullName4Text( custFullName4Text );
    fncVO.setCurAcctNbr( curAcctNbr );
	fncVO.setInvstCurAcctNbr( invstCurAcctNbr );
    fncVO.setMrInvstCurAcctInd( mrInvstCurAcctInd );
    fncVO.setClickedSearch( "" );
    fncVO.setExecutingList( executingList );
	fncVO.setReltnNbr( reltnNbr );

  }

  /**
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Faz cast para os tipos corretos
    BaseMrDocPrvtListFncVO fncVO = ( BaseMrDocPrvtListFncVO ) fncVO_;
    BaseMrDocPrvtListForm form = ( BaseMrDocPrvtListForm ) form_;

    DataSet results = ( fncVO.getResults() != null ? fncVO.getResults() : null );
    form.setResults( results );

    form.setMrInvstCurAcctIndDomain( fncVO.getMrInvstCurAcctIndDomain() );
    form.setClickedSearch( fncVO.getClickedSearch() );
    form.setCustFullNameTextSrc( fncVO.getCustFullNameText() );
	form.setCustFullName2TextSrc( fncVO.getCustFullName2Text() );
	form.setCustFullName3TextSrc( fncVO.getCustFullName3Text() );
	form.setCustFullName4TextSrc( fncVO.getCustFullName4Text() );
	form.setCurAcctNbrSrc( fncVO.getCurAcctNbr()!=null?fncVO.getCurAcctNbr().toString():"");
	form.setInvstCurAcctNbrSrc( fncVO.getInvstCurAcctNbr()!=null?fncVO.getInvstCurAcctNbr().toString():"");
	
	
  }

  public void load( BaseFncVO fncVO_ )
  {
    BaseMrDocPrvtListFncVO fncVO = ( BaseMrDocPrvtListFncVO ) fncVO_;
    BaseTplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
    fncVO.setResults( null );

    if ( fncVO.getCustNbr() != null && fncVO.getReltnNbr() != null && fncVO.isFromSearch() )
    {
      customerPrvtEntity.getData().setCustNbr( fncVO.getCustNbr() );
	  customerPrvtEntity.getData().setReltnNbr( fncVO.getReltnNbr() );
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      BaseTplCustomerPrvtDAO customerPrvtDAO = odsDAOFactory.getTplCustomerPrvtDAO();
      
      if(fncVO.getReltnNbr().longValue() > 0)
      {
		customerPrvtEntity = customerPrvtDAO.findByReltn( customerPrvtEntity );
      }
      else
      {
		customerPrvtEntity = customerPrvtDAO.find( customerPrvtEntity );
      }
      fncVO.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );
	  fncVO.setCustFullName2Text( customerPrvtEntity.getData().getCustFullName2Text() );
	  fncVO.setCustFullName3Text( customerPrvtEntity.getData().getCustFullName3Text() );
	  fncVO.setCustFullName4Text( customerPrvtEntity.getData().getCustFullName4Text() );
	  fncVO.setCurAcctNbr(customerPrvtEntity.getData().getCurAcctNbr());
	  fncVO.setInvstCurAcctNbr(customerPrvtEntity.getData().getInvstCurAcctNbr());
      fncVO.setFromSearch( false );

    }
    else
    {
      fncVO.setCustFullNameText( "" );
	  fncVO.setCustFullName2Text( "" );
	  fncVO.setCustFullName3Text( "" );
	  fncVO.setCustFullName4Text( "" );
      fncVO.setCustNbr( null );

    }

  }

  //Backup dos critérios de pesquisa.
  protected void updateSearchFields( BaseFncVO fncVO_ )
  {

    BaseMrDocPrvtListFncVO fncVO = ( BaseMrDocPrvtListFncVO ) fncVO_;
    if ( fncVO.getExecutingList().equals( "S" ) )
    {
      fncVO.setCustNbrFromList( fncVO.getCustNbr() );
      fncVO.setCustFullNameTextFromList( fncVO.getCustFullNameText() );
    }
    else if ( fncVO.getExecutingList().equals( "N" ) )
    {
      fncVO.setCustNbr( fncVO.getCustNbrFromList() );
      fncVO.setCustFullNameText( fncVO.getCustFullNameTextFromList() );
    }

  }

}