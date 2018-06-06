/*
 * Created on Apr 15, 2007
 *
 */
package com.citibank.ods.modules.client.relationeg.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.relationeg.form.RelationEgMovementListForm;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class RelationEgMovementListFnc extends BaseRelationEgListFnc implements
    ODSListFnc
{

  /**
   * Executa a consulta em Lista do sub-módulo de movimento
   */
  public void list( BaseFncVO fncVO_ )
  {
    RelationEgMovementListFncVO egMovementListFncVO = ( RelationEgMovementListFncVO ) fncVO_;

    TplRelationEgMovDAO tplRelationEgMovDAO = ODSDAOFactory.getInstance().getTplRelationEgMovDAO();
    DataSet results = tplRelationEgMovDAO.list(
                                                egMovementListFncVO.getEgNbr(),
                                                egMovementListFncVO.getLastUpdUserIdSrc(),
                                                egMovementListFncVO.getReltnNbr(),
                                                egMovementListFncVO.getErNbrSrc() );

    egMovementListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      egMovementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      egMovementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    // Atualizando campos comuns
    super.updateFncVOFromForm( form_, fncVO_ );

    // Acertando os tipos
    RelationEgMovementListFncVO relationEgMovementListFncVO = ( RelationEgMovementListFncVO ) fncVO_;
    RelationEgMovementListForm relationEgMovementListForm = ( RelationEgMovementListForm ) form_;

    String lastUpdUserIdSrc = ( relationEgMovementListForm.getLastUpdUserIdSrc() != null
                                && relationEgMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                                ? relationEgMovementListForm.getLastUpdUserIdSrc()
                                                                                                : null );
    relationEgMovementListFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

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
    return new RelationEgMovementListFncVO();
  }

}