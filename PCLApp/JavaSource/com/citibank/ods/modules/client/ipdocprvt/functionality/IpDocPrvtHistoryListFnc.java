package com.citibank.ods.modules.client.ipdocprvt.functionality;

import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.ipdocprvt.form.IpDocPrvtHistoryListForm;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtListFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.IpDocPrvtHistoryListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplIpDocPrvtHistDAO;
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

public class IpDocPrvtHistoryListFnc extends BaseIpDocPrvtListFnc implements
    ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new IpDocPrvtHistoryListFncVO();
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    IpDocPrvtHistoryListForm ipDocPrvtHistoryListForm = ( IpDocPrvtHistoryListForm ) form_;
    IpDocPrvtHistoryListFncVO listFncVO = ( IpDocPrvtHistoryListFncVO ) fncVO_;

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
    try
    {
      listFncVO.setIpDocRefDate( formatter.parse( ipDocPrvtHistoryListForm.getIpDocRefDateSrc() ) );
    }
    catch ( Exception e )
    {
      listFncVO.setIpDocRefDate( null );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#list(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void list( BaseFncVO fncVO_ )
  {
    IpDocPrvtHistoryListFncVO ipDocPrvtHistoryListFncVO = ( IpDocPrvtHistoryListFncVO ) fncVO_;

    validateList( fncVO_ );

    //Obtém a instância da Factory
    ODSDAOFactory factory = ODSDAOFactory.getInstance();
    //Obtém a instância do DAO necessário
    TplIpDocPrvtHistDAO ipDocPrvtHistDAO = factory.getTplIpDocPrvtHistDAO();
    // Recupera valores do DAO
    DataSet result = ipDocPrvtHistDAO.list(
                                            ipDocPrvtHistoryListFncVO.getCustNbrSrc(),
                                            ipDocPrvtHistoryListFncVO.getIpDocCodeSrc(),
                                            ipDocPrvtHistoryListFncVO.getIpDocTextSrc(),
                                            ipDocPrvtHistoryListFncVO.getIpInvstCurAcctInd(),
                                            ipDocPrvtHistoryListFncVO.getIpDocRefDate(),
                                            ipDocPrvtHistoryListFncVO.getCustFullNameText() );

    //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
    ipDocPrvtHistoryListFncVO.setResults( result );

    if ( result.size() > 0 )
    {
      ipDocPrvtHistoryListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      ipDocPrvtHistoryListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */

  public void load( BaseFncVO fncVO_ )
  {
    IpDocPrvtHistoryListFncVO ipDocPrvtHistoryListFncVO = ( IpDocPrvtHistoryListFncVO ) fncVO_;
    loadIpInvstCurAcctIndDomain( ipDocPrvtHistoryListFncVO );
    loadCustFullNameText( ipDocPrvtHistoryListFncVO );
    //Não limpa a consulta se voltar de uma busca.
    if ( !ipDocPrvtHistoryListFncVO.isFromSearch() )
    {
      ipDocPrvtHistoryListFncVO.setResults( null );
      ipDocPrvtHistoryListFncVO.setFromSearch( false );
    }

  }

  public void loadCustFullNameText(
                                   BaseIpDocPrvtListFncVO ipDocPrvtHistoryFncVO_ )
  {
    if ( ipDocPrvtHistoryFncVO_.getCustNbrSrc() != null
         && ipDocPrvtHistoryFncVO_.getCustNbrSrc().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               ipDocPrvtHistoryFncVO_.getCustNbrSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Verifica se o número do cliente existe na base de dados
      if ( tplCustomerPrvtDAO.exists( customerPrvtEntity ) )
      {
        //Realiza a consulta no DAO
        customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

        //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
        ipDocPrvtHistoryFncVO_.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );
      }
      else
      {
        //Se não existir este cliente seta vazio no nome do cliente.
        ipDocPrvtHistoryFncVO_.setCustFullNameText( "" );
      }

    }

  }

  private void loadIpInvstCurAcctIndDomain(
                                           IpDocPrvtHistoryListFncVO ipDocPrvtListFncVO_ )
  {
    ipDocPrvtListFncVO_.setIpInvstCurAcctIndDomain( ODSConstraintDecoder.decodeIndicador() );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
  }

}