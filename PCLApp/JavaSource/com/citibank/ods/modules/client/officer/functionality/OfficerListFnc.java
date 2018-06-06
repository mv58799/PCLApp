package com.citibank.ods.modules.client.officer.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.client.officer.functionality.valueobject.OfficerListFncVO;
import com.citibank.ods.persistence.bg.dao.TbgOfficerDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.officer.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 26, 2007
 *  
 */

public class OfficerListFnc extends BaseOfficerListFnc implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    OfficerListFncVO officerListFncVO = ( OfficerListFncVO ) fncVO_;

    // Obtém a instância da Factory

    ODSDAOFactory factory = ODSDAOFactory.getInstance();

    TbgOfficerDAO officerDAO = factory.getTbgOfficerDAO();
    DataSet result = officerDAO.list( officerListFncVO.getOffcrNbrSrc(),
                                      officerListFncVO.getOffcrNameTextSrc(),
                                      officerListFncVO.getOffcrRealNbrSrc(),
                                      officerListFncVO.getOffcrIntlNbrSrc(),
                                      officerListFncVO.getOffcrTypeCodeSrc() );

    officerListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      officerListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      officerListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    super.load( fncVO_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }

  public void clearPage( BaseFncVO fncVO_ )
  {
    //Casting do fncVO específico
    OfficerListFncVO officerListFncVO = ( OfficerListFncVO ) fncVO_;

    officerListFncVO.clearErrors();
    officerListFncVO.clearMessages();
    officerListFncVO.setResults( null );
    officerListFncVO.setOffcrNbrSrc( null );
    officerListFncVO.setOffcrNameTextSrc( null );
    officerListFncVO.setOffcrRealNbrSrc( null );
    officerListFncVO.setOffcrIntlNbrSrc( null );
  }

}