/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodriskcatprvt.form.ProdRiskCatPrvtMovementListForm;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.ProdRiskCatPrvtMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProdRiskCatPrvtMovementListFnc extends BaseProdRiskCatPrvtListFnc
    implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdRiskCatPrvtMovementListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtMovementListFncVO movementListFncVO = ( ProdRiskCatPrvtMovementListFncVO ) fncVO_;
    //	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProdRiskCatPrvtMovDAO movDAO = factory.getTplProdRiskCatPrvtMovDAO();
    
    /** *** 20110321 ***
     * As funcionalidades que utilizavam a tabela PL.TPL_PROD_RISK_CAT_PRVT devem utilizar a tabela
     * PL.TPL_RISK_INVST_PROD_RDIP (apenas funcionalidades que utilizem consulta, as telas de 
     * inserção e alteração serão removidas)
     */    
//    DataSet results = movDAO.list( movementListFncVO.getProdRiskCatCodeSrc(),
//                                   movementListFncVO.getProdRiskCatTextSrc(),
//                                   movementListFncVO.getLastUpdUserIdSrc() );
//
//    movementListFncVO.setResults( results );
//
//    if ( results.size() > 0 )
//    {
//      movementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
//    }
//    else
//    {
//      movementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
//    }
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Atualizando campos comuns
    super.updateFncVOFromForm( form_, fncVO_ );

    // Acertando os tipos
    ProdRiskCatPrvtMovementListFncVO prodRiskCatPrvtMovementListFncVO = ( ProdRiskCatPrvtMovementListFncVO ) fncVO_;
    ProdRiskCatPrvtMovementListForm prodRiskCatPrvtMovementListForm = ( ProdRiskCatPrvtMovementListForm ) form_;

    String lastUpdUserIdSrc = ( prodRiskCatPrvtMovementListForm.getLastUpdUserIdSrc() != null
                                && prodRiskCatPrvtMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                                     ? prodRiskCatPrvtMovementListForm.getLastUpdUserIdSrc()
                                                                                                     : null );
    prodRiskCatPrvtMovementListFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    //  Limpando os dados para o carregamento
    ProdRiskCatPrvtMovementListFncVO riskCatPrvtMovementListFncVO = ( ProdRiskCatPrvtMovementListFncVO ) fncVO_;
    riskCatPrvtMovementListFncVO.setResults( null );
    riskCatPrvtMovementListFncVO.setProdRiskCatCodeSrc( null );
    riskCatPrvtMovementListFncVO.setProdRiskCatTextSrc( null );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    //
  }
}