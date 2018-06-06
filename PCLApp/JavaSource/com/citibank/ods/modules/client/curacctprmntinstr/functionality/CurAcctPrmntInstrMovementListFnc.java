package com.citibank.ods.modules.client.curacctprmntinstr.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.curacctprmntinstr.form.CurAcctPrmntInstrMovementListForm;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.functionality;
 * @version 1.0
 * @author angelica.almeida,18/06/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class CurAcctPrmntInstrMovementListFnc extends
    BaseCurAcctPrmntInstrListFnc
{
  public void list( BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrMovementListFncVO curAcctPrmntInstrMovFncVO = ( CurAcctPrmntInstrMovementListFncVO ) fncVO_;
    TplCurAcctPrmntInstrMovDAO tplCurAcctPrmntInstrMovDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrMovDAO();
    DataSet results = tplCurAcctPrmntInstrMovDAO.list(
                                                       curAcctPrmntInstrMovFncVO.getCurAcctNbrSrc(),
                                                       curAcctPrmntInstrMovFncVO.getCustNbrSrc(),
                                                       curAcctPrmntInstrMovFncVO.getPrmntInstrCodeSrc(),
                                                       curAcctPrmntInstrMovFncVO.getPrmntInstrInvstCurAcctIndSrc(),
                                                       curAcctPrmntInstrMovFncVO.getLastUpdUserIdSrc(),
                                                       curAcctPrmntInstrMovFncVO.getCustFullNameSrc() );
    curAcctPrmntInstrMovFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      curAcctPrmntInstrMovFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      curAcctPrmntInstrMovFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  public void load( BaseFncVO fncVO_ )
  {
    super.loadDomains( fncVO_ );
    CurAcctPrmntInstrMovementListFncVO curAcctPrmntInstrMovFncVO = ( CurAcctPrmntInstrMovementListFncVO ) fncVO_;

    if ( curAcctPrmntInstrMovFncVO.isFromSearch() )
    {
      loadCustomerName( curAcctPrmntInstrMovFncVO );
    }
    else
    {
      curAcctPrmntInstrMovFncVO.setResults( null );
      curAcctPrmntInstrMovFncVO.setCurAcctNbrSrc( null );
      curAcctPrmntInstrMovFncVO.setCustNbrSrc( null );
      curAcctPrmntInstrMovFncVO.setPrmntInstrCodeSrc( null );
      curAcctPrmntInstrMovFncVO.setPrmntInstrInvstCurAcctIndSrc( null );
      curAcctPrmntInstrMovFncVO.setLastUpdUserIdSrc( null );
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
    CurAcctPrmntInstrMovementListFncVO curAcctPrmntInstrMovementListFncVO = ( CurAcctPrmntInstrMovementListFncVO ) fncVO_;
    CurAcctPrmntInstrMovementListForm curAcctPrmntInstrMovementListForm = ( CurAcctPrmntInstrMovementListForm ) form_;

    String lastUpdUserIdSrc = ( curAcctPrmntInstrMovementListForm.getLastUpdUserIdSrc() != null
                                && curAcctPrmntInstrMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                                       ? curAcctPrmntInstrMovementListForm.getLastUpdUserIdSrc()
                                                                                                       : null );
    curAcctPrmntInstrMovementListFncVO.setLastUpdUserIdSrc( lastUpdUserIdSrc );

  }

  /**
   * Cria objeto CurAcctPrmntInstrMovementListFncVO.
   */
  public BaseFncVO createFncVO()
  {
    return new CurAcctPrmntInstrMovementListFncVO();
  }

}