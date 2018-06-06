package com.citibank.ods.entity.pl;

import java.math.BigInteger;

import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtMovEntityVO;

/**
 * Classe que instancia a entidade correspondente a tabela
 * Tpl_Bkr_Portf_Mgmt_Mov
 * @author Hamilton Matos
 */

public class TplBkrPortfMgmtMovEntity extends BaseTplBkrPortfMgmtEntity
{

  public TplBkrPortfMgmtMovEntity()
  {
    m_data = new TplBkrPortfMgmtMovEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de movimento com os atributos da current
   */
  public TplBkrPortfMgmtMovEntity(
                                  TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity_,
                                  String opernCode_ )
  {
    m_data = new TplBkrPortfMgmtMovEntityVO();
    TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtMovEntityVO = ( TplBkrPortfMgmtMovEntityVO ) m_data;

    tplBkrPortfMgmtMovEntityVO.setProdAcctCode( tplBkrPortfMgmtEntity_.getData().getProdAcctCode() );
    tplBkrPortfMgmtMovEntityVO.setProdUnderAcctCode( tplBkrPortfMgmtEntity_.getData().getProdUnderAcctCode() );

    tplBkrPortfMgmtMovEntityVO.setCustNbr( tplBkrPortfMgmtEntity_.getData().getCustNbr() );

    tplBkrPortfMgmtMovEntityVO.setBovespaCurAcctNbr( tplBkrPortfMgmtEntity_.getData().getBovespaCurAcctNbr() );
    tplBkrPortfMgmtMovEntityVO.setBovespaInvstAcctNbr( tplBkrPortfMgmtEntity_.getData().getBovespaInvstAcctNbr() );
    tplBkrPortfMgmtMovEntityVO.setBmfCurAcctNbr( tplBkrPortfMgmtEntity_.getData().getBmfCurAcctNbr() );
    tplBkrPortfMgmtMovEntityVO.setBmfInvstAcctNbr( tplBkrPortfMgmtEntity_.getData().getBmfInvstAcctNbr() );

    tplBkrPortfMgmtMovEntityVO.setBkrCnpjNbr( tplBkrPortfMgmtEntity_.getData().getBkrCnpjNbr() );
    tplBkrPortfMgmtMovEntityVO.setBkrCustNbr( tplBkrPortfMgmtEntity_.getData().getBkrCustNbr() );
    tplBkrPortfMgmtMovEntityVO.setLastUpdUserId( tplBkrPortfMgmtEntity_.getData().getLastUpdUserId() );
    tplBkrPortfMgmtMovEntityVO.setLastUpdDate( tplBkrPortfMgmtEntity_.getData().getLastUpdDate() );
    tplBkrPortfMgmtMovEntityVO.setOpernCode( opernCode_ );
  }

  public TplBkrPortfMgmtMovEntity( TplBrokerEntity tplBrokerEntity_,
                                  BigInteger custNbr_, BigInteger bkrCustNbr_,
                                  BigInteger prodAcctCode_,
                                  BigInteger prodUnderAcctCode_ )
  {
    m_data = new TplBkrPortfMgmtMovEntityVO();

    TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtMovEntityVO ) m_data;

    tplBkrPortfMgmtEntityVO.setCustNbr( custNbr_ );
    tplBkrPortfMgmtEntityVO.setBkrCnpjNbr( tplBrokerEntity_.getData().getBkrCnpjNbr() );
    tplBkrPortfMgmtEntityVO.setBkrCustNbr( bkrCustNbr_ );
    tplBkrPortfMgmtEntityVO.setProdAcctCode( prodAcctCode_ );
    tplBkrPortfMgmtEntityVO.setProdUnderAcctCode( prodUnderAcctCode_ );
    tplBkrPortfMgmtEntityVO.setBkrNameText( tplBrokerEntity_.getData().getBkrNameText() );
    tplBkrPortfMgmtEntityVO.setBkrAddrText( tplBrokerEntity_.getData().getBkrAddrText() );

  }
}