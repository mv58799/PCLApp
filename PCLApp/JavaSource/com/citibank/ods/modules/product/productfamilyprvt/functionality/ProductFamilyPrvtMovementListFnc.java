/*
 * Created on Mar 18, 2007
 *
 */
package com.citibank.ods.modules.product.productfamilyprvt.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.productfamilyprvt.form.ProductFamilyPrvtMovementListForm;
import com.citibank.ods.modules.product.productfamilyprvt.functionality.valueobject.ProductFamilyPrvtMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class ProductFamilyPrvtMovementListFnc extends
    BaseProductFamilyPrvtListFnc implements ODSListFnc
{

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProductFamilyPrvtMovementListFncVO();
  }

  /**
   * Realiza a consulta dos registro que estão com pendência de aprovação, dados
   * os critérios passados
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtMovementListFncVO movementListFncVO = ( ProductFamilyPrvtMovementListFncVO ) fncVO_;

    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TplProductFamilyPrvtMovDAO tplProductFamilyPrvtMovDAO = factory.getTplProductFamilyPrvtMovDAO();
    DataSet results = tplProductFamilyPrvtMovDAO.list(
                                                       movementListFncVO.getProdFamlCodeSrc(),
                                                       movementListFncVO.getProdFamlNameSrc(),
                                                       movementListFncVO.getProdFamlTextSrc(),
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
    ProductFamilyPrvtMovementListFncVO productFamilyPrvtMovementListFncVO = ( ProductFamilyPrvtMovementListFncVO ) fncVO_;
    ProductFamilyPrvtMovementListForm productFamilyPrvtMovementListForm = ( ProductFamilyPrvtMovementListForm ) form_;

    String lastUpdUserIdSrc = ( productFamilyPrvtMovementListForm.getLastUpdUserIdSrc() != null
                                && productFamilyPrvtMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                                       ? productFamilyPrvtMovementListForm.getLastUpdUserIdSrc()
                                                                                                       : null );
    productFamilyPrvtMovementListFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

  }

  /**
   * Carregamento inicial da Lista
   */
  public void load( BaseFncVO fncVO_ )
  {
    ProductFamilyPrvtMovementListFncVO movementListFncVO = ( ProductFamilyPrvtMovementListFncVO ) fncVO_;
    movementListFncVO.setProdFamlCodeSrc( null );
    movementListFncVO.setProdFamlNameSrc( null );
    movementListFncVO.setProdFamlTextSrc( null );
    movementListFncVO.setResults( null );
  }

  /**
   * Realiza a validação da lista
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    // TODO Auto-generated method stub

  }

}