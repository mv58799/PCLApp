package com.citibank.ods.modules.product.broker.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSHistoryDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.broker.functionality.valueobject.BrokerHistoryDetailFncVO;

/**
 * 
 * @author Hamilton Matos
 *  
 */

public class BrokerHistoryDetailFnc extends BrokerDetailFnc implements
    ODSHistoryDetailFnc
{

  public void loadForConsult( BaseFncVO fncVO_ )
  {

  }

  public BaseFncVO createFncVO()
  {
    return new BrokerHistoryDetailFncVO();
  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {

  }

}