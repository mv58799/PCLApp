package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplBrokerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBrokerMovEntityVO;

/**
 * @author Hamilton Matos
 */
public class TplBrokerEntity extends BaseTplBrokerEntity
{
  public TplBrokerEntity()
  {
    m_data = new TplBrokerEntityVO();
  }

  public TplBrokerEntity( TplBrokerMovEntity tplBrokerMovEntity_,
                         Date lastAuthDate_, String lastAuthUserId_,
                         String recStatCode_ )
  {
    m_data = new TplBrokerEntityVO();

    TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) m_data;
    TplBrokerMovEntityVO tplBrokerMovEntityVO = ( TplBrokerMovEntityVO ) tplBrokerMovEntity_.getData();

    tplBrokerEntityVO.setBkrCnpjNbr( tplBrokerMovEntityVO.getBkrCnpjNbr() );
    tplBrokerEntityVO.setBkrNameText( tplBrokerMovEntityVO.getBkrNameText() );
    tplBrokerEntityVO.setBkrAddrText( tplBrokerMovEntityVO.getBkrAddrText() );
    tplBrokerEntityVO.setBkrApprvCrLimDlrAmt( tplBrokerMovEntityVO.getBkrApprvCrLimDlrAmt() );
	//Correçao G&P - 09/05/2008 - rviana:
    //tplBrokerEntityVO.setBkrApprvCrLimLcyAmt( tplBrokerMovEntityVO.getBkrApprvCrLimDlrAmt() );
	tplBrokerEntityVO.setBkrApprvCrLimLcyAmt( tplBrokerMovEntityVO.getBkrApprvCrLimLcyAmt() );
	//Fim correçao G&P - 09/05/2008.
    tplBrokerEntityVO.setBkrApprvDate( tplBrokerMovEntityVO.getBkrApprvDate() );
    tplBrokerEntityVO.setBkrApprvStatCode( tplBrokerMovEntityVO.getBkrApprvStatCode() );
    tplBrokerEntityVO.setBkrAuthProcSitText( tplBrokerMovEntityVO.getBkrAuthProcSitText() );
    tplBrokerEntityVO.setBkrBmfMktCode( tplBrokerMovEntityVO.getBkrBmfMktCode() );
    tplBrokerEntityVO.setBkrBovespaMktCode( tplBrokerMovEntityVO.getBkrBovespaMktCode() );
    tplBrokerEntityVO.setBkrCommentText( tplBrokerMovEntityVO.getBkrCommentText() );
    tplBrokerEntityVO.setBkrRbtBmfRate( tplBrokerMovEntityVO.getBkrRbtBmfRate() );
    tplBrokerEntityVO.setBkrRbtBovespaRate( tplBrokerMovEntityVO.getBkrRbtBovespaRate() );
    tplBrokerEntityVO.setBkrReqCrLimDlrAmt( tplBrokerMovEntityVO.getBkrReqCrLimDlrAmt() );
    tplBrokerEntityVO.setBkrReqCrLimLcyAmt( tplBrokerMovEntityVO.getBkrReqCrLimLcyAmt() );
    tplBrokerEntityVO.setBkrReqDate( tplBrokerMovEntityVO.getBkrReqDate() );
    tplBrokerEntityVO.setBkrRnwDate( tplBrokerMovEntityVO.getBkrRnwDate() );
    tplBrokerEntityVO.setBkrSuplServText( tplBrokerMovEntityVO.getBkrSuplServText() );
    tplBrokerEntityVO.setLastUpdDate( tplBrokerMovEntityVO.getLastUpdDate() );
    tplBrokerEntityVO.setLastUpdUserId( tplBrokerMovEntityVO.getLastUpdUserId() );
    tplBrokerEntityVO.setLastAuthDate( lastAuthDate_ );
    tplBrokerEntityVO.setLastAuthUserId( lastAuthUserId_ );
    tplBrokerEntityVO.setRecStatCode( recStatCode_ );
  }

}