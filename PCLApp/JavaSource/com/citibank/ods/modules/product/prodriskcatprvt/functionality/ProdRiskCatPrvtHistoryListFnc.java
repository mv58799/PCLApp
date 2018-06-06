/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodriskcatprvt.form.ProdRiskCatPrvtHistoryListForm;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.BaseProdRiskCatPrvtListFncVO;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.ProdRiskCatPrvtHistoryListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtHistDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdRiskCatPrvtHistoryListFnc extends BaseProdRiskCatPrvtListFnc
    implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdRiskCatPrvtHistoryListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtHistoryListFncVO catPrvtHistoryListFncVO = ( ProdRiskCatPrvtHistoryListFncVO ) fncVO_;

    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProdRiskCatPrvtHistDAO tplProdRiskCatPrvtHistDAO = factory.getTplProdRiskCatPrvtHistDAO();
    
    /** *** 20110321 ***
     * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
     * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
     * inserção e alteração serão removidas)
     */    
//    DataSet results = tplProdRiskCatPrvtHistDAO.list(
//                                                      catPrvtHistoryListFncVO.getProdRiskCatCodeHistSrc(),
//                                                      catPrvtHistoryListFncVO.getProdRiskCatTextSrc(),
//                                                      catPrvtHistoryListFncVO.getProdRiskCatRefDateSrc() );
//
//    catPrvtHistoryListFncVO.setResults( results );
//
//    if ( results.size() > 0 )
//    {
//      catPrvtHistoryListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
//    }
//    else
//    {
//      catPrvtHistoryListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
//    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BaseProdRiskCatPrvtListFncVO prodRiskCatPrvtListFncVO = ( BaseProdRiskCatPrvtListFncVO ) fncVO_;
    prodRiskCatPrvtListFncVO.setResults( null );
    prodRiskCatPrvtListFncVO.setProdRiskCatTextSrc( null );
    ( ( ProdRiskCatPrvtHistoryListFncVO ) prodRiskCatPrvtListFncVO ).setProdRiskCatRefDateSrc( null );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * Atualiza o FncVO com as informacoes da Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    ProdRiskCatPrvtHistoryListForm prvtHistoryListForm = ( ProdRiskCatPrvtHistoryListForm ) form_;
    ProdRiskCatPrvtHistoryListFncVO listFncVO = ( ProdRiskCatPrvtHistoryListFncVO ) fncVO_;

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
    try
    {
      if ( prvtHistoryListForm.getProdRiskCatCodeHistSrc() != null
           && !prvtHistoryListForm.getProdRiskCatCodeHistSrc().equals( "" ) )
      {
        listFncVO.setProdRiskCatCodeHistSrc( new BigInteger(
                                                             prvtHistoryListForm.getProdRiskCatCodeHistSrc() ) );
      }
      else
      {
        listFncVO.setProdRiskCatCodeHistSrc( null );
      }
      listFncVO.setProdRiskCatRefDateSrc( formatter.parse( prvtHistoryListForm.getProdRiskCatRefDate() ) );
    }
    catch ( Exception e )
    {
      listFncVO.setProdRiskCatRefDateSrc( null );
    }
  }
}