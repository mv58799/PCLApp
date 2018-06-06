/*
 * Created on Mar 12, 2007
 *
 */
package com.citibank.ods.modules.product.aggrprodprvt.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplAggrProdPrvtEntity;
import com.citibank.ods.modules.product.aggrprodprvt.form.BaseAggrProdPrvtDetailForm;
import com.citibank.ods.modules.product.aggrprodprvt.functionality.valueobject.BaseAggrProdPrvtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplAggrProdPrvtDAO;

/**
 * @author leonardo.nakada
 *
 */
public abstract class BaseAggrProdPrvtDetailFnc extends BaseFnc
{
  /*
   * Diplay Name - Código
   */
  protected static final String C_DISPLAY_NAME_CODE = "Código do Agregador de Produtos";
  
  /*
   * Diplay Name - Código
   */
  protected static final String C_DISPLAY_NAME_TEXT = "Descrição do Agregador de Produtos";
  
  /*
   * Atualiza o FncVO com as informacoes da Form
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseAggrProdPrvtDetailForm productAggrDetailForm = (BaseAggrProdPrvtDetailForm) form_; 
    BaseAggrProdPrvtDetailFncVO productAggrDetailFncVO = (BaseAggrProdPrvtDetailFncVO) fncVO_;
    
    productAggrDetailFncVO.getAggrProdPrvtEntity().getData().setPrvtProdAggrCode(productAggrDetailForm.getPrvtProdAggrCode());
    productAggrDetailFncVO.getAggrProdPrvtEntity().getData().setPrvtProdAggrText(productAggrDetailForm.getPrvtProdAggrText());
    productAggrDetailFncVO.getAggrProdPrvtEntity().getData().setIxCode(productAggrDetailForm.getIxCode());
    productAggrDetailFncVO.getAggrProdPrvtEntity().getData().setAggrProdPrvtIxCodeDomain(productAggrDetailForm.getAggrProdPrvtIxCodeDomain());
  }

  /*
   * Atualiza a Form com as informacoes do FncVO
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseAggrProdPrvtDetailForm productAggrDetailForm = (BaseAggrProdPrvtDetailForm) form_; 
    BaseAggrProdPrvtDetailFncVO productAggrDetailFncVO = (BaseAggrProdPrvtDetailFncVO) fncVO_;
    
    productAggrDetailForm.setPrvtProdAggrCode(productAggrDetailFncVO.getAggrProdPrvtEntity().getData().getPrvtProdAggrCode());
    productAggrDetailForm.setPrvtProdAggrText(productAggrDetailFncVO.getAggrProdPrvtEntity().getData().getPrvtProdAggrText());
    productAggrDetailForm.setIxCode(productAggrDetailFncVO.getAggrProdPrvtEntity().getData().getIxCode());
    productAggrDetailForm.setAggrProdPrvtIxCodeDomain(productAggrDetailFncVO.getAggrProdPrvtEntity().getData().getAggrProdPrvtIxCodeDomain());
  }
  
  /**
   * Carregamento do FncVO
   * 
   * @param aggrProdPrvtDetailFncVO_
   */
  public void load ( BaseAggrProdPrvtDetailFncVO aggrProdPrvtDetailFncVO_ ){
    BaseTplAggrProdPrvtDAO aggrProdPrvtDAO = this.getDAO();
    
    BaseTplAggrProdPrvtEntity aggrProdPrvtEntity = aggrProdPrvtDAO.find(aggrProdPrvtDetailFncVO_.getAggrProdPrvtEntity());
    aggrProdPrvtDetailFncVO_.setAggrProdPrvtEntity(aggrProdPrvtEntity);
    
  }
  
  /**
   * Método abstrato que retorna a instancia do DAO
   * para realizar a consulta pelo detalhe
   * 
   * @return
   */
  protected abstract BaseTplAggrProdPrvtDAO getDAO();
}
