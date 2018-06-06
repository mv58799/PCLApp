package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplBrokerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBrokerHistEntityVO;

/**
 * @author Hamilton Matos
 */
public class TplBrokerHistEntity extends BaseTplBrokerEntity
{
  public TplBrokerHistEntity()
  {
    m_data = new TplBrokerHistEntityVO();
  }

  public TplBrokerHistEntity( TplBrokerEntity tplBrokerEntity_, Date bkrRefDate_ )
  {
    m_data = new TplBrokerHistEntityVO();

    TplBrokerHistEntityVO tplBrokerHistEntityVO = ( TplBrokerHistEntityVO ) m_data;
    TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) tplBrokerEntity_.getData();

    tplBrokerHistEntityVO.setBkrRefDate( bkrRefDate_ );
    tplBrokerHistEntityVO.setBkrCnpjNbr( tplBrokerEntityVO.getBkrCnpjNbr() );
    tplBrokerHistEntityVO.setBkrNameText( tplBrokerEntityVO.getBkrNameText() );
    tplBrokerHistEntityVO.setBkrAddrText( tplBrokerEntityVO.getBkrAddrText() );
    tplBrokerHistEntityVO.setBkrApprvCrLimDlrAmt( tplBrokerEntityVO.getBkrApprvCrLimDlrAmt() );
	//Correçao G&P - 09/05/2008 - rviana:    
    //tplBrokerHistEntityVO.setBkrApprvCrLimLcyAmt( tplBrokerEntityVO.getBkrApprvCrLimDlrAmt() );
	tplBrokerHistEntityVO.setBkrApprvCrLimLcyAmt( tplBrokerEntityVO.getBkrApprvCrLimLcyAmt() );
	//Fim correçao G&P - 09/05/2008.
    tplBrokerHistEntityVO.setBkrApprvDate( tplBrokerEntityVO.getBkrApprvDate() );
    tplBrokerHistEntityVO.setBkrApprvStatCode( tplBrokerEntityVO.getBkrApprvStatCode() );
    tplBrokerHistEntityVO.setBkrAuthProcSitText( tplBrokerEntityVO.getBkrAuthProcSitText() );
    tplBrokerHistEntityVO.setBkrBmfMktCode( tplBrokerEntityVO.getBkrBmfMktCode() );
    tplBrokerHistEntityVO.setBkrBovespaMktCode( tplBrokerEntityVO.getBkrBovespaMktCode() );
    tplBrokerHistEntityVO.setBkrCommentText( tplBrokerEntityVO.getBkrCommentText() );
    tplBrokerHistEntityVO.setBkrRbtBmfRate( tplBrokerEntityVO.getBkrRbtBmfRate() );
    tplBrokerHistEntityVO.setBkrRbtBovespaRate( tplBrokerEntityVO.getBkrRbtBovespaRate() );
    tplBrokerHistEntityVO.setBkrReqCrLimDlrAmt( tplBrokerEntityVO.getBkrReqCrLimDlrAmt() );
    tplBrokerHistEntityVO.setBkrReqCrLimLcyAmt( tplBrokerEntityVO.getBkrReqCrLimLcyAmt() );
    tplBrokerHistEntityVO.setBkrReqDate( tplBrokerEntityVO.getBkrReqDate() );
    tplBrokerHistEntityVO.setBkrRnwDate( tplBrokerEntityVO.getBkrRnwDate() );
    tplBrokerHistEntityVO.setBkrSuplServText( tplBrokerEntityVO.getBkrSuplServText() );
    tplBrokerHistEntityVO.setLastAuthDate( tplBrokerEntityVO.getLastAuthDate() );
    tplBrokerHistEntityVO.setLastAuthUserId( tplBrokerEntityVO.getLastAuthUserId() );
    tplBrokerHistEntityVO.setLastUpdDate( tplBrokerEntityVO.getLastUpdDate() );
    tplBrokerHistEntityVO.setLastUpdUserId( tplBrokerEntityVO.getLastUpdUserId() );
    tplBrokerHistEntityVO.setRecStatCode( tplBrokerEntityVO.getRecStatCode() );
  }
}