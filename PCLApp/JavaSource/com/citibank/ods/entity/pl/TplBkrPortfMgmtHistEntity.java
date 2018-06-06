package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtHistEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtMovEntityVO;

/**
 * Classe que instancia a entidade correspondente a tabela
 * Tpl_Bkr_Portf_Mgmt_Hist
 * @author Hamilton Matos
 */

public class TplBkrPortfMgmtHistEntity extends BaseTplBkrPortfMgmtEntity
{

  public TplBkrPortfMgmtHistEntity()
  {
    m_data = new TplBkrPortfMgmtHistEntityVO();
  }

  public TplBkrPortfMgmtHistEntity(
                                   TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_,
                                   Date lastAuthDate_, String lastAuthUserId_,
                                   String recStatCode_ )
  {
    m_data = new TplBkrPortfMgmtHistEntityVO();

    TplBkrPortfMgmtHistEntityVO tplBkrPortfMgmtHistEntityVO = ( TplBkrPortfMgmtHistEntityVO ) m_data;
    TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtMovEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity_.getData();

    tplBkrPortfMgmtHistEntityVO.setProdAcctCode( tplBkrPortfMgmtMovEntityVO.getProdAcctCode() );
    tplBkrPortfMgmtHistEntityVO.setProdUnderAcctCode( tplBkrPortfMgmtMovEntityVO.getProdUnderAcctCode() );
    tplBkrPortfMgmtHistEntityVO.setBkrCnpjNbr( tplBkrPortfMgmtMovEntityVO.getBkrCnpjNbr() );
    tplBkrPortfMgmtHistEntityVO.setBkrPortfMgmtRefDate( new Date() );
    tplBkrPortfMgmtHistEntityVO.setBovespaCurAcctNbr( tplBkrPortfMgmtMovEntityVO.getBovespaCurAcctNbr() );
    tplBkrPortfMgmtHistEntityVO.setBovespaInvstAcctNbr( tplBkrPortfMgmtMovEntityVO.getBovespaInvstAcctNbr() );
    tplBkrPortfMgmtHistEntityVO.setBmfCurAcctNbr( tplBkrPortfMgmtMovEntityVO.getBmfCurAcctNbr() );
    tplBkrPortfMgmtHistEntityVO.setBmfInvstAcctNbr( tplBkrPortfMgmtMovEntityVO.getBmfInvstAcctNbr() );
    tplBkrPortfMgmtHistEntityVO.setLastUpdDate( tplBkrPortfMgmtMovEntityVO.getLastUpdDate() );
    tplBkrPortfMgmtHistEntityVO.setLastUpdUserId( tplBkrPortfMgmtMovEntityVO.getLastUpdUserId() );
    tplBkrPortfMgmtHistEntityVO.setLastAuthDate( lastAuthDate_ );
    tplBkrPortfMgmtHistEntityVO.setLastAuthUserId( lastAuthUserId_ );
    tplBkrPortfMgmtHistEntityVO.setRecStatCode( recStatCode_ );
  }

  public TplBkrPortfMgmtHistEntity( TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity_ )
  {
    m_data = new TplBkrPortfMgmtHistEntityVO();

    TplBkrPortfMgmtHistEntityVO tplBkrPortfMgmtHistEntityVO = ( TplBkrPortfMgmtHistEntityVO ) m_data;
    TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPortfMgmtEntity_.getData();

    tplBkrPortfMgmtHistEntityVO.setProdAcctCode( tplBkrPortfMgmtEntityVO.getProdAcctCode() );
    tplBkrPortfMgmtHistEntityVO.setProdUnderAcctCode( tplBkrPortfMgmtEntityVO.getProdUnderAcctCode() );
    tplBkrPortfMgmtHistEntityVO.setBkrCnpjNbr( tplBkrPortfMgmtEntityVO.getBkrCnpjNbr() );
    tplBkrPortfMgmtHistEntityVO.setBkrPortfMgmtRefDate( new Date() );
    tplBkrPortfMgmtHistEntityVO.setBovespaCurAcctNbr( tplBkrPortfMgmtEntityVO.getBovespaCurAcctNbr() );
    tplBkrPortfMgmtHistEntityVO.setBovespaInvstAcctNbr( tplBkrPortfMgmtEntityVO.getBovespaInvstAcctNbr() );
    tplBkrPortfMgmtHistEntityVO.setBmfCurAcctNbr( tplBkrPortfMgmtEntityVO.getBmfCurAcctNbr() );
    tplBkrPortfMgmtHistEntityVO.setBmfInvstAcctNbr( tplBkrPortfMgmtEntityVO.getBmfInvstAcctNbr() );
    tplBkrPortfMgmtHistEntityVO.setLastUpdDate( tplBkrPortfMgmtEntityVO.getLastUpdDate() );
    tplBkrPortfMgmtHistEntityVO.setLastUpdUserId( tplBkrPortfMgmtEntityVO.getLastUpdUserId() );
    tplBkrPortfMgmtHistEntityVO.setLastAuthDate( tplBkrPortfMgmtEntityVO.getLastAuthDate() );
    tplBkrPortfMgmtHistEntityVO.setLastAuthUserId( tplBkrPortfMgmtEntityVO.getLastAuthUserId() );
    tplBkrPortfMgmtHistEntityVO.setRecStatCode( tplBkrPortfMgmtEntityVO.getRecStatCode() );
  }

}