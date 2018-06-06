/*
 * Created on Mar 17, 2007
 *
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodqlfyprvt.form.ProdQlfyPrvtMovementListForm;
import com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject.ProdQlfyPrvtMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author fernando.salgado
 *  
 */
public class ProdQlfyPrvtMovementListFnc extends BaseProdQlfyPrvtListFnc
    implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdQlfyPrvtMovementListFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdQlfyPrvtMovementListFncVO movementListFncVO = ( ProdQlfyPrvtMovementListFncVO ) fncVO_;
    //	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProdQlfyPrvtMovDAO movDAO = factory.getTplProdQlfyPrvtMovDAO();
    DataSet results = movDAO.list( movementListFncVO.getProdQlfyCodeSrc(),
                                   movementListFncVO.getProdQlfyTextSrc(),
                                   movementListFncVO.getLastUpdUserIdSrc() );

    movementListFncVO.setResults( results );
    
    if ( results.size() > 0 )
    {
      movementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      movementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Atualizando campos comuns
    super.updateFncVOFromForm( form_, fncVO_ );

    // Acertando os tipos
    ProdQlfyPrvtMovementListFncVO prodQlfyPrvtMovementListFncVO = ( ProdQlfyPrvtMovementListFncVO ) fncVO_;
    ProdQlfyPrvtMovementListForm prodQlfyPrvtMovementListForm = ( ProdQlfyPrvtMovementListForm ) form_;

    String lastUpdUserIdSrc = ( prodQlfyPrvtMovementListForm.getLastUpdUserIdSrc() != null
                                && prodQlfyPrvtMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                                  ? prodQlfyPrvtMovementListForm.getLastUpdUserIdSrc()
                                                                                                  : null );
    prodQlfyPrvtMovementListFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    // Limpando os dados para o carregamento
    ProdQlfyPrvtMovementListFncVO prodQlfyPrvtMovementListFncVO = ( ProdQlfyPrvtMovementListFncVO ) fncVO_;
    prodQlfyPrvtMovementListFncVO.setResults( null );
    prodQlfyPrvtMovementListFncVO.setProdQlfyCodeSrc( null );
    prodQlfyPrvtMovementListFncVO.setProdQlfyTextSrc( null );

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