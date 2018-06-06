package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplCustomerBrokerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplCustomerBrokerHistEntityVO;
/**
 *@see package com.citibank.ods.modules.client.customerbroker.functionality.valueobject; 
 *@version 1.0
 *@author Hamilton Matos,Jul 26, 2007
 */

public class TplCustomerBrokerHistEntity extends BaseTplCustomerBrokerEntity
{
  public TplCustomerBrokerHistEntity()
  {
    m_data = new TplCustomerBrokerHistEntityVO();
  }

  public TplCustomerBrokerHistEntity( TplCustomerBrokerEntity tplCustomerBrokerEntity_,
                              Date plyrDateRef_ )
   {
     m_data = new TplCustomerBrokerHistEntityVO();

     TplCustomerBrokerHistEntityVO tplCustomerBrokerHistEntityVO = ( TplCustomerBrokerHistEntityVO ) m_data;
     TplCustomerBrokerEntityVO tplCustomerBrokerEntityVO = ( TplCustomerBrokerEntityVO ) tplCustomerBrokerEntity_.getData();

//     tplCustomerBrokerHistEntityVO.setPlyrCnpjNbr( tplCustomerBrokerEntityVO.getPlyrCnpjNbr() );
//     tplCustomerBrokerHistEntityVO.setPlyrRefDate(plyrDateRef_);
//     tplCustomerBrokerHistEntityVO.setPlyrName( tplCustomerBrokerEntityVO.getPlyrName() );
//     tplCustomerBrokerHistEntityVO.setPlyrAddrText( tplCustomerBrokerEntityVO.getPlyrAddrText() );
//     tplCustomerBrokerHistEntityVO.setPlyrDueDlgDate( tplCustomerBrokerEntityVO.getPlyrDueDlgDate() );
//     tplCustomerBrokerHistEntityVO.setPlyrDueDlgExecInd( tplCustomerBrokerEntityVO.getPlyrDueDlgExecInd() );
//     tplCustomerBrokerHistEntityVO.setPlyrDueDlgEndDate( tplCustomerBrokerEntityVO.getPlyrDueDlgEndDate() );
//     tplCustomerBrokerHistEntityVO.setPlyrDueDlgRnwDate( tplCustomerBrokerEntityVO.getPlyrDueDlgRnwDate() );
//     tplCustomerBrokerHistEntityVO.setPlyrInvstCmtteApprvDate( tplCustomerBrokerEntityVO.getPlyrInvstCmtteApprvDate() );
//     tplCustomerBrokerHistEntityVO.setPlyrApprvRstrnText( tplCustomerBrokerEntityVO.getPlyrApprvRstrnText() );
//     tplCustomerBrokerHistEntityVO.setPlyrSuplServText( tplCustomerBrokerEntityVO.getPlyrSuplServText() );
//     tplCustomerBrokerHistEntityVO.setPlyrCmntText( tplCustomerBrokerEntityVO.getPlyrCmntText() );
//     tplCustomerBrokerHistEntityVO.setLastUpdUserId( tplCustomerBrokerEntityVO.getLastUpdUserId() );
//     tplCustomerBrokerHistEntityVO.setLastUpdDate( tplCustomerBrokerEntityVO.getLastUpdDate() );
//     tplCustomerBrokerHistEntityVO.setLastAuthUserId( tplCustomerBrokerEntityVO.getLastAuthUserId() );
//     tplCustomerBrokerHistEntityVO.setLastAuthDate( tplCustomerBrokerEntityVO.getLastAuthDate() );
//     tplCustomerBrokerHistEntityVO.setRecStatCode( tplCustomerBrokerEntityVO.getRecStatCode() );
   }

}
