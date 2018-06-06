package com.citibank.ods.modules.client.ipdocprvt.functionality;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtMovementListForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtListFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtMovementListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.ipdocprvt.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class IpDocPrvtMovementListFnc extends BaseIpDocPrvtListFnc implements
    ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new IpDocPrvtMovementListFncVO();
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    IpDocPrvtMovementListForm ipDocPrvtMovementListForm = ( IpDocPrvtMovementListForm ) form_;
    IpDocPrvtMovementListFncVO ipDocPrvtMovementListFncVO = ( IpDocPrvtMovementListFncVO ) fncVO_;

    String lastUpdUserIdSrc = ( ipDocPrvtMovementListForm.getLastUpdUserIdSrc() != null
                                && ipDocPrvtMovementListForm.getLastUpdUserIdSrc().length() > 0
                                                                                               ? ipDocPrvtMovementListForm.getLastUpdUserIdSrc()
                                                                                               : null );
    ipDocPrvtMovementListFncVO.setLastUpdUserId( lastUpdUserIdSrc );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    IpDocPrvtMovementListFncVO ipDocPrvtMovementListFncVO = ( IpDocPrvtMovementListFncVO ) fncVO_;

    validateList( fncVO_ );

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplIpDocPrvtMovDAO ipDocPrvtMovDAO = factory.getTplIpDocPrvtMovDAO();
    // Recupera valores do DAO
    DataSet result = ipDocPrvtMovDAO.list(
                                           ipDocPrvtMovementListFncVO.getCustNbrSrc(),
                                           ipDocPrvtMovementListFncVO.getIpDocCodeSrc(),
                                           ipDocPrvtMovementListFncVO.getIpDocTextSrc(),
                                           ipDocPrvtMovementListFncVO.getIpInvstCurAcctInd(),
                                           ipDocPrvtMovementListFncVO.getLastUpdUserId(),
                                           ipDocPrvtMovementListFncVO.getCustFullNameText() );

    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    ipDocPrvtMovementListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      ipDocPrvtMovementListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      ipDocPrvtMovementListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    IpDocPrvtMovementListFncVO ipDocPrvtMovementListFncVO = ( IpDocPrvtMovementListFncVO ) fncVO_;
    loadIpInvstCurAcctIndDomain( ipDocPrvtMovementListFncVO );

    if ( !ipDocPrvtMovementListFncVO.isFromSearch() )
    {
      ipDocPrvtMovementListFncVO.setResults( null );
      ipDocPrvtMovementListFncVO.setCustFullNameText( "" );
      ipDocPrvtMovementListFncVO.setCustNbrSrc( null );
      ipDocPrvtMovementListFncVO.setIpDocCodeSrc( null );

    }
    else
    {
      if ( ipDocPrvtMovementListFncVO.getCustNbrSrc() != null )
      {
        loadCustFullNameText( ipDocPrvtMovementListFncVO );
      }
    }

  }

  public void loadCustFullNameText(
                                   BaseIpDocPrvtListFncVO ipDocPrvtMovementFncVO_ )
  {
    if ( ipDocPrvtMovementFncVO_.getCustNbrSrc() != null
         && ipDocPrvtMovementFncVO_.getCustNbrSrc().intValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               ipDocPrvtMovementFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      if ( tplCustomerPrvtDAO.exists( customerPrvtEntity ) )
      {

        //Realiza a consulta no DAO
        customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

        //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
        ipDocPrvtMovementFncVO_.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );

      }
      else
      {
        ipDocPrvtMovementFncVO_.setCustFullNameText( "" );
      }
    }
    else
    {
      ipDocPrvtMovementFncVO_.setCustFullNameText( "" );
    }
  }

  private void loadIpInvstCurAcctIndDomain(
                                           IpDocPrvtMovementListFncVO ipDocPrvtMovementListFncVO_ )
  {
    ipDocPrvtMovementListFncVO_.setIpInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }

}