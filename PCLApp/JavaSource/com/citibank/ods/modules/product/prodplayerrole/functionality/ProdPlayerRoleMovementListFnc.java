package com.citibank.ods.modules.product.prodplayerrole.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.ProdPlayerRoleMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.product.playerproduct.functionality;
 * @version 1.0
 * @author atilio.l.araujo,Apr 4, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class ProdPlayerRoleMovementListFnc extends BaseProdPlayerRoleListFnc
    implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleMovementListFncVO prodPlayerRoleMovementListFncVO = ( ProdPlayerRoleMovementListFncVO ) fncVO_;
    TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();
    DataSet results = tplProdPlayerRoleMovDAO.list(
                                                    prodPlayerRoleMovementListFncVO.getPlyrCnpjNbrSrc(),
                                                    prodPlayerRoleMovementListFncVO.getPlyrNameSrc(),
                                                    prodPlayerRoleMovementListFncVO.getPlyrRoleNameSrc(),
                                                    prodPlayerRoleMovementListFncVO.getProdCodeSrc(),
                                                    prodPlayerRoleMovementListFncVO.getSysCodeSrc(),
                                                    prodPlayerRoleMovementListFncVO.getSysSegCodeSrc(),
                                                    prodPlayerRoleMovementListFncVO.getProdNameSrc(),
                                                    prodPlayerRoleMovementListFncVO.getLastUpdUserIdSrc() );
    prodPlayerRoleMovementListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      prodPlayerRoleMovementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      prodPlayerRoleMovementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    super.loadPlayerName(fncVO_);
    ProdPlayerRoleMovementListFncVO prodPlayerRoleMovementListFncVO = ( ProdPlayerRoleMovementListFncVO ) fncVO_;
    DataSet resultDomain = ODSConstraintDecoder.decodeRoleType();
    
    prodPlayerRoleMovementListFncVO.setProdPlayerRoleDomain( resultDomain );
    prodPlayerRoleMovementListFncVO.setPlyrRoleTypeCode( null );
    prodPlayerRoleMovementListFncVO.setProdCodeSrc( null );
    prodPlayerRoleMovementListFncVO.setSysCodeSrc( null );
    prodPlayerRoleMovementListFncVO.setSysSegCodeSrc( null );
    prodPlayerRoleMovementListFncVO.setResults( null );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {

  }

  public BaseFncVO createFncVO()
  {
    return new ProdPlayerRoleMovementListFncVO();
  }

}