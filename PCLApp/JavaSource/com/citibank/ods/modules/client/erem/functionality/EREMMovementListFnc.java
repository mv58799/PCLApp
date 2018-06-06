package com.citibank.ods.modules.client.erem.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.erem.form.EREMMovementListForm;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplErEmMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class EREMMovementListFnc extends BaseEREMListFnc implements ODSListFnc
{

  /**
   * Retorna instância de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new EREMMovementListFncVO();
  }

  /**
   * Realiza a consulta em Lista a partir dos critérios passados por parâmetro
   */
  public void list( BaseFncVO fncVO_ )
  {
    EREMMovementListFncVO listFncVO = ( EREMMovementListFncVO ) fncVO_;

    TplErEmMovDAO tplErEmDAO = ODSDAOFactory.getInstance().getTplErEmMovDAO();
    DataSet result = tplErEmDAO.list( listFncVO.getErNbrSrc(),
                                      listFncVO.getEmNbrSrc(),
                                      listFncVO.getCustNbrSrc(),
                                      listFncVO.getCustFullNameTextSrc(),
                                      listFncVO.getReltnNbrSrc(),
                                      listFncVO.getAcctNbr(),
                                      listFncVO.getLastUpdUserId() );
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
    EREMMovementListFncVO listFncVO = ( EREMMovementListFncVO ) fncVO_;
    listFncVO.setResults( null );
    if ( listFncVO.isFromSearch() )
    {
      loadCustText( listFncVO );
      listFncVO.setFromSearch( false );
    }
    else
    {
      listFncVO.setCustNbrSrc( null );
      listFncVO.setCustFullNameTextSrc( null );
      listFncVO.setEmNbrSrc( null );
      listFncVO.setErNbrSrc( null );
      listFncVO.setReltnNbrSrc( null );
      listFncVO.setAcctNbr( null );
      listFncVO.setLastUpdUserId( null );
    }

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
   * @see com.citibank.ods.modules.client.erem.functionality.BaseEREMListFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    EREMMovementListFncVO listFncVO = ( EREMMovementListFncVO ) fncVO_;
    EREMMovementListForm listForm = ( EREMMovementListForm ) form_;

    String lastUpdUserId = listForm.getLastUpdUserIdSrc();
    listFncVO.setLastUpdUserId( lastUpdUserId );
  }

}