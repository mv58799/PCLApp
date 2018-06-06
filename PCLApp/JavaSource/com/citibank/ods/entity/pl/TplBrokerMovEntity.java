package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.TplBrokerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBrokerMovEntityVO;

/**
 * @author Hamilton Matos
 */
public class TplBrokerMovEntity extends BaseTplBrokerEntity
{
  /**
   * Constante do tamanho do nome do último usuário a modificar o registro.
   */
  public static final int C_LAST_UPD_USER_ID_SIZE = 20;

  public TplBrokerMovEntity()
  {
    m_data = new TplBrokerMovEntityVO();
  }

  public TplBrokerMovEntity( TplBrokerEntity tplBrokerEntity_, String opernCode_ )
  {
    m_data = new TplBrokerMovEntityVO();

    TplBrokerMovEntityVO tplBrokerMovEntityVO = ( TplBrokerMovEntityVO ) m_data;
    TplBrokerEntityVO tplBrokerEntityVO = ( TplBrokerEntityVO ) tplBrokerEntity_.getData();

    tplBrokerMovEntityVO.setBkrCnpjNbr( tplBrokerEntityVO.getBkrCnpjNbr() );
    tplBrokerMovEntityVO.setBkrNameText( tplBrokerEntityVO.getBkrNameText() );
    tplBrokerMovEntityVO.setBkrAddrText( tplBrokerEntityVO.getBkrAddrText() );
    tplBrokerMovEntityVO.setBkrApprvCrLimDlrAmt( tplBrokerEntityVO.getBkrApprvCrLimDlrAmt() );
	//Correçao G&P - 09/05/2008 - rviana:
    //tplBrokerMovEntityVO.setBkrApprvCrLimLcyAmt( tplBrokerEntityVO.getBkrApprvCrLimDlrAmt() );
	tplBrokerMovEntityVO.setBkrApprvCrLimLcyAmt( tplBrokerEntityVO.getBkrApprvCrLimLcyAmt() );
	//Fim correçao G&P - 09/05/2008.
    tplBrokerMovEntityVO.setBkrApprvDate( tplBrokerEntityVO.getBkrApprvDate() );
    tplBrokerMovEntityVO.setBkrApprvStatCode( tplBrokerEntityVO.getBkrApprvStatCode() );
    tplBrokerMovEntityVO.setBkrAuthProcSitText( tplBrokerEntityVO.getBkrAuthProcSitText() );
    tplBrokerMovEntityVO.setBkrBmfMktCode( tplBrokerEntityVO.getBkrBmfMktCode() );
    tplBrokerMovEntityVO.setBkrBovespaMktCode( tplBrokerEntityVO.getBkrBovespaMktCode() );
    tplBrokerMovEntityVO.setBkrCommentText( tplBrokerEntityVO.getBkrCommentText() );
    tplBrokerMovEntityVO.setBkrRbtBmfRate( tplBrokerEntityVO.getBkrRbtBmfRate() );
    tplBrokerMovEntityVO.setBkrRbtBovespaRate( tplBrokerEntityVO.getBkrRbtBovespaRate() );
    tplBrokerMovEntityVO.setBkrReqCrLimDlrAmt( tplBrokerEntityVO.getBkrReqCrLimDlrAmt() );
    tplBrokerMovEntityVO.setBkrReqCrLimLcyAmt( tplBrokerEntityVO.getBkrReqCrLimLcyAmt() );
    tplBrokerMovEntityVO.setBkrReqDate( tplBrokerEntityVO.getBkrReqDate() );
    tplBrokerMovEntityVO.setBkrRnwDate( tplBrokerEntityVO.getBkrRnwDate() );
    tplBrokerMovEntityVO.setBkrSuplServText( tplBrokerEntityVO.getBkrSuplServText() );
    tplBrokerMovEntityVO.setLastUpdDate( tplBrokerEntityVO.getLastUpdDate() );
    tplBrokerMovEntityVO.setLastUpdUserId( tplBrokerEntityVO.getLastUpdUserId() );
    tplBrokerMovEntityVO.setOpernCode( opernCode_ );
  }

}