package com.citibank.ods.modules.client.curacctprmntinstr.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals.FuncionalityFormatKeys;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.modules.client.curacctprmntinstr.form.CurAcctPrmntInstrHistoryListForm;
import com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject.CurAcctPrmntInstrHistoryListFncVO;
import com.citibank.ods.persistence.pl.dao.TplCurAcctPrmntInstrHistDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.curacctprmntinstr.functionality;
 * @version 1.0
 * @author angelica.almeida,18/06/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class CurAcctPrmntInstrHistoryListFnc extends
    BaseCurAcctPrmntInstrListFnc
{
  public void list( BaseFncVO fncVO_ )
  {
    CurAcctPrmntInstrHistoryListFncVO curAcctPrmntInstrHistListFncVO = ( CurAcctPrmntInstrHistoryListFncVO ) fncVO_;
    TplCurAcctPrmntInstrHistDAO tplCurAcctPrmntInstrHistDAO = ODSDAOFactory.getInstance().getTplCurAcctPrmntInstrHistDAO();
    DataSet results = tplCurAcctPrmntInstrHistDAO.list(
                                                        curAcctPrmntInstrHistListFncVO.getCurAcctNbrSrc(),
                                                        curAcctPrmntInstrHistListFncVO.getCustNbrHistSrc(),
                                                        curAcctPrmntInstrHistListFncVO.getPrmntInstrCodeHistSrc(),
                                                        curAcctPrmntInstrHistListFncVO.getPrmntInstrInvstCurAcctIndSrc(),
                                                        curAcctPrmntInstrHistListFncVO.getCurAcctPrmntInstrRefDate(),
                                                        curAcctPrmntInstrHistListFncVO.getCustFullNameSrc() );
    curAcctPrmntInstrHistListFncVO.setResults( results );

    if ( results.size() > 0 )
    {
      curAcctPrmntInstrHistListFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      curAcctPrmntInstrHistListFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }
  }

  public void load( BaseFncVO fncVO_ )
  {
    super.loadDomains( fncVO_ );
    CurAcctPrmntInstrHistoryListFncVO curAcctPrmntInstrHistListFncVO = ( CurAcctPrmntInstrHistoryListFncVO ) fncVO_;

    if ( curAcctPrmntInstrHistListFncVO.isFromSearch() )
    {
      loadCustomerName( curAcctPrmntInstrHistListFncVO );
    }
    else
    {
      curAcctPrmntInstrHistListFncVO.setResults( null );
      curAcctPrmntInstrHistListFncVO.setCurAcctNbrSrc( null );
      curAcctPrmntInstrHistListFncVO.setPrmntInstrInvstCurAcctIndSrc( null );
      curAcctPrmntInstrHistListFncVO.setCurAcctPrmntInstrRefDate( null );
    }

  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    CurAcctPrmntInstrHistoryListForm curAcctPrmntInstrHistoryListForm = ( CurAcctPrmntInstrHistoryListForm ) form_;
    CurAcctPrmntInstrHistoryListFncVO listFncVO = ( CurAcctPrmntInstrHistoryListFncVO ) fncVO_;

    SimpleDateFormat formatter = new SimpleDateFormat(
                                                       FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );
    try
    {
      BigInteger custNbrHist = ( curAcctPrmntInstrHistoryListForm.getCustNbrHistSrc() != null
                                 && curAcctPrmntInstrHistoryListForm.getCustNbrHistSrc().length() > 0
                                                                                                     ? new BigInteger(
                                                                                                                       curAcctPrmntInstrHistoryListForm.getCustNbrHistSrc() )
                                                                                                     : null );

      BigInteger prmntInstrCodeHist = ( curAcctPrmntInstrHistoryListForm.getPrmntInstrCodeHistSrc() != null
                                        && curAcctPrmntInstrHistoryListForm.getPrmntInstrCodeHistSrc().length() > 0
                                                                                                                   ? new BigInteger(
                                                                                                                                     curAcctPrmntInstrHistoryListForm.getPrmntInstrCodeHistSrc() )
                                                                                                                   : null );
      listFncVO.setCustNbrHistSrc( custNbrHist );
      listFncVO.setPrmntInstrCodeHistSrc( prmntInstrCodeHist );
      listFncVO.setCurAcctPrmntInstrRefDate( formatter.parse( curAcctPrmntInstrHistoryListForm.getCurAcctPrmntInstrRefDate() ) );
    }
    catch ( Exception e )
    {
      listFncVO.setCurAcctPrmntInstrRefDate( null );
    }
  }

  /**
   * Cria objeto CurAcctPrmntInstrHistoryListFncVO.
   */
  public BaseFncVO createFncVO()
  {
    return new CurAcctPrmntInstrHistoryListFncVO();
  }

  public void loadCustomerName(
                               CurAcctPrmntInstrHistoryListFncVO curAcctPrmntInstrHistoryListFncVO_ )
  {
    if ( curAcctPrmntInstrHistoryListFncVO_.getCustNbrHistSrc() != null
         && curAcctPrmntInstrHistoryListFncVO_.getCustNbrHistSrc().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               curAcctPrmntInstrHistoryListFncVO_.getCustNbrHistSrc() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      curAcctPrmntInstrHistoryListFncVO_.setCustFullNameSrc( customerPrvtEntity.getData().getCustFullNameText() );
    }
    else
    {
      curAcctPrmntInstrHistoryListFncVO_.setCustFullNameSrc( "" );
    }
  }

}