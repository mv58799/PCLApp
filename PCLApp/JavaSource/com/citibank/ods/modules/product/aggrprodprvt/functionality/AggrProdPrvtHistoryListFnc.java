/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.functionality;

import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.aggrprodprvt.form.AggrProdPrvtHistoryListForm;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.AggrProdPrvtHistoryListFncVO;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.BaseAggrProdPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplAggrProdPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class AggrProdPrvtHistoryListFnc extends BaseAggrProdPrvtListFnc
    implements ODSListFnc
{

  /**
   * Retorna a instancia do VO do Histórico - Product Aggregate
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new AggrProdPrvtHistoryListFncVO();
  }

  /**
   * Realiza a consulta de histórico de um agregador de produto
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    AggrProdPrvtHistoryListFncVO productAggrHistListFncVO = ( AggrProdPrvtHistoryListFncVO ) fncVO_;

    // Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplAggrProdPrvtHistDAO aggrProdPrvtHistDAO = factory.getTplAggrProdPrvtHistDAO();
    DataSet result = aggrProdPrvtHistDAO.listProductAggregateHistory(
                                                                      productAggrHistListFncVO.getPrvtProdAggrCodeHistSrc(),
                                                                      productAggrHistListFncVO.getPrvtProdAggrTextSrc(),
                                                                      productAggrHistListFncVO.getPrvtProdAggrRefDate() );

    productAggrHistListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      productAggrHistListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      productAggrHistListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /**
   * Atualiza o FncVO com as informacoes da Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    AggrProdPrvtHistoryListForm productAggrListForm = ( AggrProdPrvtHistoryListForm ) form_;
    AggrProdPrvtHistoryListFncVO productAggrListFncVO = ( AggrProdPrvtHistoryListFncVO ) fncVO_;

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
    try
    {
      productAggrListFncVO.setPrvtProdAggrCodeHistSrc( productAggrListForm.getPrvtProdAggrCodeHistSrc() );
      productAggrListFncVO.setPrvtProdAggrRefDate( formatter.parse( productAggrListForm.getPrvtProdAggrRefDate() ) );
    }
    catch ( Exception e )
    {
      productAggrListFncVO.setPrvtProdAggrRefDate( null );
    }
  }

  /**
   * Atualiza a Form com as informacoes do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );

    AggrProdPrvtHistoryListForm productAggrListForm = ( AggrProdPrvtHistoryListForm ) form_;
    AggrProdPrvtHistoryListFncVO productAggrListFncVO = ( AggrProdPrvtHistoryListFncVO ) fncVO_;

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
    try
    {
      productAggrListForm.setPrvtProdAggrRefDate( formatter.format( productAggrListFncVO.getPrvtProdAggrRefDate() ) );
    }
    catch ( Exception e )
    {
      productAggrListForm.setPrvtProdAggrRefDate( null );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Realiza os Carregamentos iniciais
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BaseAggrProdPrvtListFncVO productAggrListFncVO = ( BaseAggrProdPrvtListFncVO ) fncVO_;
    productAggrListFncVO.setResults( null );

    //productAggrListFncVO.setPrvtProdAggrCodeSrc( null );
    productAggrListFncVO.setPrvtProdAggrTextSrc( null );
    ( ( AggrProdPrvtHistoryListFncVO ) productAggrListFncVO ).setPrvtProdAggrRefDate( null );
  }
}