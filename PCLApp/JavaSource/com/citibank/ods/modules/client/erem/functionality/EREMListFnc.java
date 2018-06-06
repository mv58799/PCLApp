package com.citibank.ods.modules.client.erem.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMListFncVO;
import com.citibank.ods.persistence.pl.dao.TplErEmDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class EREMListFnc extends BaseEREMListFnc implements ODSListFnc
{

  /**
   * Retorna instância de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new EREMListFncVO();
  }

  /**
   * Realiza a consulta em Lista a partir dos critérios passados por parâmetro
   */
  public void list( BaseFncVO fncVO_ )
  {
    EREMListFncVO listFncVO = ( EREMListFncVO ) fncVO_;

    TplErEmDAO tplErEmDAO = ODSDAOFactory.getInstance().getTplErEmDAO();
    DataSet result = tplErEmDAO.list( listFncVO.getErNbrSrc(),
                                      listFncVO.getEmNbrSrc(),
                                      listFncVO.getCustNbrSrc(),
                                      listFncVO.getCustFullNameTextSrc(),
                                      listFncVO.getReltnNbrSrc(),
                                      listFncVO.getAcctNbr(),
                                      false);
    listFncVO.setResults( result );
    if ( result.size() > 0 )
    {
      listFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      listFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /**
   * Carregamentos iniciais
   */
  public void load( BaseFncVO fncVO_ )
  {
    EREMListFncVO listFncVO = ( EREMListFncVO ) fncVO_;
    listFncVO.setResults( null );
    if ( listFncVO.isFromSearch() )
    {
      loadCustText( listFncVO );
      listFncVO.setFromSearch( false );
    }
    else
    {
      listFncVO.setErNbrSrc( null );
      listFncVO.setEmNbrSrc( null );
      listFncVO.setReltnNbrSrc( null );
      listFncVO.setCustNbrSrc( null );
      listFncVO.setCustFullNameTextSrc( null );
      listFncVO.setAcctNbr( null );
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

  public void clearPage( BaseFncVO fncVO_ )
  {
    EREMListFncVO listFncVO = ( EREMListFncVO ) fncVO_;

    listFncVO.clearErrors();
    listFncVO.clearMessages();
    listFncVO.setResults( null );
    listFncVO.setErNbrSrc( null );
    listFncVO.setEmNbrSrc( null );
    listFncVO.setReltnNbrSrc( null );
    listFncVO.setCustNbrSrc( null );
    listFncVO.setCustFullNameTextSrc( null );
    listFncVO.setAcctNbr( null );

  }

}