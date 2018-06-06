package com.citibank.ods.modules.product.prodplayerrole.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.entity.pl.TplPlayerEntity;
import com.citibank.ods.modules.product.prodplayerrole.form.BaseProdPlayerRoleListForm;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.BaseProdPlayerRoleListFncVO;
import com.citibank.ods.persistence.pl.dao.TplPlayerDAO;
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

public class BaseProdPlayerRoleListFnc extends BaseFnc implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdPlayerRoleListForm baseProdPlayerRoleListForm = ( BaseProdPlayerRoleListForm ) form_;
    BaseProdPlayerRoleListFncVO baseProdPlayerRoleListfncVO = ( BaseProdPlayerRoleListFncVO ) fncVO_;

    baseProdPlayerRoleListfncVO.setPlyrCnpjNbrSrc( baseProdPlayerRoleListForm.getPlyrCnpjNbrSrc() );
    baseProdPlayerRoleListfncVO.setPlyrNameSrc( baseProdPlayerRoleListForm.getPlyrNameSrc() );
    baseProdPlayerRoleListfncVO.setPlyrRoleNameSrc( baseProdPlayerRoleListForm.getPlyrRoleNameSrc() );
    baseProdPlayerRoleListfncVO.setProdNameSrc( baseProdPlayerRoleListForm.getProdNameSrc() );
    baseProdPlayerRoleListfncVO.setProdCodeSrc( baseProdPlayerRoleListForm.getProdCodeSrc() );
    baseProdPlayerRoleListfncVO.setSysCodeSrc( baseProdPlayerRoleListForm.getSysCodeSrc() );
    baseProdPlayerRoleListfncVO.setLastUpdUserIdSrc( baseProdPlayerRoleListForm.getLastUpdUserIdSrc() );

    if ( baseProdPlayerRoleListForm.getSysSegCodeSrc() != null
         && !baseProdPlayerRoleListForm.getSysSegCodeSrc().equals( "" ) )
    {
      baseProdPlayerRoleListfncVO.setSysSegCodeSrc( baseProdPlayerRoleListForm.getSysSegCodeSrc() );
    }
    else
    {
      baseProdPlayerRoleListfncVO.setSysSegCodeSrc( null );
    }

    baseProdPlayerRoleListfncVO.setClickedSearch( "" );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdPlayerRoleListForm baseProdPlayerRoleListForm = ( BaseProdPlayerRoleListForm ) form_;
    BaseProdPlayerRoleListFncVO baseProdPlayerRoleListfncVO = ( BaseProdPlayerRoleListFncVO ) fncVO_;

    baseProdPlayerRoleListForm.setProdPlayerRoleDomain( baseProdPlayerRoleListfncVO.getProdPlayerRoleDomain() );
    baseProdPlayerRoleListForm.setPlyrCnpjNbrSrc( baseProdPlayerRoleListfncVO.getPlyrCnpjNbrSrc() );
    baseProdPlayerRoleListForm.setPlyrNameSrc( baseProdPlayerRoleListfncVO.getPlyrNameSrc() );
    baseProdPlayerRoleListForm.setPlyrRoleNameSrc( baseProdPlayerRoleListfncVO.getPlyrRoleNameSrc() );
    baseProdPlayerRoleListForm.setProdNameSrc( baseProdPlayerRoleListfncVO.getProdNameSrc() );
    baseProdPlayerRoleListForm.setProdCodeSrc( baseProdPlayerRoleListfncVO.getProdCodeSrc() );
    baseProdPlayerRoleListForm.setSysCodeSrc( baseProdPlayerRoleListfncVO.getSysCodeSrc() );
    baseProdPlayerRoleListForm.setLastUpdUserIdSrc( baseProdPlayerRoleListfncVO.getLastUpdUserIdSrc() );
    if ( baseProdPlayerRoleListfncVO.getSysSegCodeSrc() != null
         && !baseProdPlayerRoleListfncVO.getSysSegCodeSrc().equals( "" ) )
    {
      baseProdPlayerRoleListForm.setSysSegCodeSrc( baseProdPlayerRoleListfncVO.getSysSegCodeSrc() );
    }
    else
    {
      baseProdPlayerRoleListForm.setSysSegCodeSrc( null );
    }

    baseProdPlayerRoleListForm.setResults( baseProdPlayerRoleListfncVO.getResults() );
    baseProdPlayerRoleListForm.setClickedSearch( baseProdPlayerRoleListfncVO.getClickedSearch() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
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
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
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

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {

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

  public void loadPlayerName( BaseFncVO fncVO_ )
  {

    BaseProdPlayerRoleListFncVO fncVO = ( BaseProdPlayerRoleListFncVO ) fncVO_;
    BaseTplPlayerEntity tplPlayerEntity = new TplPlayerEntity();

    if ( !fncVO.getPlyrCnpjNbrSrc().equals("") && fncVO.isFromSearch() )
    {
      tplPlayerEntity.getData().setPlyrCnpjNbr( fncVO.getPlyrCnpjNbrSrc() );
      ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
      TplPlayerDAO playerRoleDAO = odsDAOFactory.getTplPlayerDAO();
      tplPlayerEntity = playerRoleDAO.find( tplPlayerEntity );
      fncVO.setPlyrNameSrc( tplPlayerEntity.getData().getPlyrName() );
    }
    else
    {
      fncVO.setPlyrNameSrc( "" );

    }

    fncVO.setFromSearch( false );
  }

}