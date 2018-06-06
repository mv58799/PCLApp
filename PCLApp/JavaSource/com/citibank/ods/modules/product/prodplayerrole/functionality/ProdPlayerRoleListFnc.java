package com.citibank.ods.modules.product.prodplayerrole.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.ProdPlayerRoleListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO;
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

public class ProdPlayerRoleListFnc extends BaseProdPlayerRoleListFnc implements
    ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleListFncVO prodPlayerRoleListFncVO = ( ProdPlayerRoleListFncVO ) fncVO_;
    TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
    DataSet results = tplProdPlayerRoleDAO.list(
                                                 prodPlayerRoleListFncVO.getPlyrCnpjNbrSrc(),
                                                 prodPlayerRoleListFncVO.getPlyrNameSrc(),
                                                 prodPlayerRoleListFncVO.getPlyrRoleNameSrc(),
                                                 prodPlayerRoleListFncVO.getProdCodeSrc(),
                                                 prodPlayerRoleListFncVO.getSysCodeSrc(),
                                                 prodPlayerRoleListFncVO.getSysSegCodeSrc(),
                                                 prodPlayerRoleListFncVO.getProdNameSrc() );
    prodPlayerRoleListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      prodPlayerRoleListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      prodPlayerRoleListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    super.loadPlayerName(fncVO_);
    ProdPlayerRoleListFncVO prodPlayerRoleListFncVO = ( ProdPlayerRoleListFncVO ) fncVO_;
    DataSet resultDomain = ODSConstraintDecoder.decodeRoleType();

    prodPlayerRoleListFncVO.setProdPlayerRoleDomain( resultDomain );
    prodPlayerRoleListFncVO.setPlyrRoleTypeCode( null );
    prodPlayerRoleListFncVO.setProdCodeSrc( null );
    prodPlayerRoleListFncVO.setSysCodeSrc( null );
    prodPlayerRoleListFncVO.setSysSegCodeSrc( null );
    prodPlayerRoleListFncVO.setResults( null );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */

  }

  public BaseFncVO createFncVO()
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */
    return new ProdPlayerRoleListFncVO();
  }

}