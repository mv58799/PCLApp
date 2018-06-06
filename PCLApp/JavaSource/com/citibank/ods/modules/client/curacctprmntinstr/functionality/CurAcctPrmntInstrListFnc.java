package com.citibank.ods.modules.client.curacctprmntinstr.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.functionality;
 * @version 1.0
 * @author michele.monteiro,18/06/2007
 *  
 */

public class CurAcctPrmntInstrListFnc extends BaseCurAcctPrmntInstrListFnc
{
  public void list( BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrListFncVO curAcctPrmntInstrListFncVO = ( CurAcctPrmntInstrListFncVO ) fncVO_;
    TplCurAcctPrmntInstrDAO tplCurAcctPrmntInstrDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrDAO();
    DataSet results = tplCurAcctPrmntInstrDAO.list(
                                                    curAcctPrmntInstrListFncVO.getCurAcctNbrSrc(),
                                                    curAcctPrmntInstrListFncVO.getCustNbrSrc(),
                                                    curAcctPrmntInstrListFncVO.getPrmntInstrCodeSrc(),
                                                    curAcctPrmntInstrListFncVO.getPrmntInstrInvstCurAcctIndSrc(),
                                                    curAcctPrmntInstrListFncVO.getCustFullNameSrc());
    curAcctPrmntInstrListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      curAcctPrmntInstrListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      curAcctPrmntInstrListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  public void load( BaseFncVO fncVO_ )
  {
    super.loadDomains( fncVO_ );
    CurAcctPrmntInstrListFncVO curAcctPrmntInstrListFncVO = ( CurAcctPrmntInstrListFncVO ) fncVO_;

    if ( curAcctPrmntInstrListFncVO.isFromSearch() )
    {
      loadCustomerName( curAcctPrmntInstrListFncVO );
    }
    else
    {
      curAcctPrmntInstrListFncVO.setResults( null );
      curAcctPrmntInstrListFncVO.setCurAcctNbrSrc( null );
      curAcctPrmntInstrListFncVO.setCustNbrSrc( null );
      curAcctPrmntInstrListFncVO.setCustFullNameSrc( null );
      curAcctPrmntInstrListFncVO.setPrmntInstrCodeSrc( null );
      curAcctPrmntInstrListFncVO.setPrmntInstrInvstCurAcctIndSrc( null );
    }

  }

  /**
   * Cria objeto CurAcctPrmntInstrListFncVO.
   */
  public BaseFncVO createFncVO()
  {
    return new CurAcctPrmntInstrListFncVO();
  }

}