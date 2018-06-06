/*
 * Created on Mar 14, 2007
 *
 */
package com.citibank.ods.modules.product.prodriskcatprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.prodriskcatprvt.functionality.valueobject.ProdRiskCatPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *
 */
public class ProdRiskCatPrvtListFnc extends BaseProdRiskCatPrvtListFnc
    implements ODSListFnc
{

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdRiskCatPrvtListFncVO();
  }

  /* (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdRiskCatPrvtListFncVO catPrvtListFncVO = (ProdRiskCatPrvtListFncVO) fncVO_;
    
    // 	Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    
    TplProdRiskCatPrvtDAO tplProdRiskCatPrvtDAO = factory.getTplProdRiskCatPrvtDAO();
    DataSet results = tplProdRiskCatPrvtDAO.list(catPrvtListFncVO.getProdRiskCatCodeSrc(), catPrvtListFncVO.getProdRiskCatTextSrc());
    
    catPrvtListFncVO.setResults(results);
    
    if ( results.size() > 0 )
    {
      catPrvtListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      catPrvtListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Carregamentos iniciais da tela de consulta
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    //Limpando os dados para o carregamento
    ProdRiskCatPrvtListFncVO riskCatPrvtListFncVO = (ProdRiskCatPrvtListFncVO) fncVO_;
    riskCatPrvtListFncVO.setResults(null);
    riskCatPrvtListFncVO.setProdRiskCatCodeSrc(null);
    riskCatPrvtListFncVO.setProdRiskCatTextSrc(null);
  }

  /**
   * Realiza as validações iniciais para a consulta em lista
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    // TODO Verificar se o código é um BigInteger
  }
}
