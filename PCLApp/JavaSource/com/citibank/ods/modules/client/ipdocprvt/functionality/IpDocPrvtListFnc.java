package com.citibank.ods.modules.client.ipdocprvt.functionality;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtListFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.client.ipdocprvt.functionality;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 *  
 */

public class IpDocPrvtListFnc extends BaseIpDocPrvtListFnc implements
    ODSListFnc
{

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new IpDocPrvtListFncVO();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    IpDocPrvtListFncVO ipDocPrvtListFncVO = ( IpDocPrvtListFncVO ) fncVO_;

    validateList( fncVO_ );
	
	//Alteraçao G&P INICIO 30/05/2008
	load( fncVO_);
	//Alteraçao G&P FIM 30/05/2008
    
    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplIpDocPrvtDAO ipDocPrvtDAO = factory.getTplIpDocPrvtDAO();
    // Recupera valores do DAO
    DataSet result = ipDocPrvtDAO.list(
                                        ipDocPrvtListFncVO.getCustNbrSrc(),
                                        ipDocPrvtListFncVO.getIpDocCodeSrc(),
                                        ipDocPrvtListFncVO.getIpDocTextSrc(),
                                        ipDocPrvtListFncVO.getIpInvstCurAcctInd(),
                                        ipDocPrvtListFncVO.getCustFullNameText() );

    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    ipDocPrvtListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      ipDocPrvtListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      ipDocPrvtListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void load( BaseFncVO fncVO_ )
  {
    BaseIpDocPrvtListFncVO ipDocPrvtListFncVO = ( BaseIpDocPrvtListFncVO ) fncVO_;
    loadIpInvstCurAcctIndDomain( ipDocPrvtListFncVO );

    if ( !ipDocPrvtListFncVO.isFromSearch()
         && !ipDocPrvtListFncVO.equals( "true" ) )
    {
      ipDocPrvtListFncVO.setIpDocCodeSrc( null );
      ipDocPrvtListFncVO.setCustFullNameText( "" );
      ipDocPrvtListFncVO.setCustNbrSrc( null );
      ipDocPrvtListFncVO.setResults( null );
    }
    else
    {
      if ( ipDocPrvtListFncVO.getCustNbrSrc() != null )
      {
        loadCustFullNameText( ipDocPrvtListFncVO );
      }
    }
    ipDocPrvtListFncVO.setFromSearch( false );
    ipDocPrvtListFncVO.setFromCurAcct("false");
    

  }

  public void loadCustFullNameText( BaseIpDocPrvtListFncVO ipDocPrvtFncVO_ )
  {
    if ( ipDocPrvtFncVO_.getCustNbrSrc() != null
         && ipDocPrvtFncVO_.getCustNbrSrc().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr( ipDocPrvtFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      if ( tplCustomerPrvtDAO.exists( customerPrvtEntity ) )
      {
        //Realiza a consulta no DAO
        customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

        //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
        ipDocPrvtFncVO_.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );

      }
      else
      {
        ipDocPrvtFncVO_.setCustFullNameText( "" );
      }
    }
    else
    {
      ipDocPrvtFncVO_.setCustFullNameText( "" );
    }
  }

  private void loadIpInvstCurAcctIndDomain(
                                           BaseIpDocPrvtListFncVO ipDocPrvtListFncVO_ )
  {
    ipDocPrvtListFncVO_.setIpInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }

}