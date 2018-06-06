package com.citibank.ods.entity.pl;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtMovEntityVO;

/**
 * Classe que instancia a entidade correspondente a tabela Tpl_Bkr_Portf_Mgmt
 * @author Hamilton Matos
 */

public class TplBkrPortfMgmtEntity extends BaseTplBkrPortfMgmtEntity
{

  public TplBkrPortfMgmtEntity()
  {
    m_data = new TplBkrPortfMgmtEntityVO();
  }

  // Construtor de entidade TplBkrPortfMgmtEntity a partir de uma entidade de
  // movimento TplBkrPortfMgmtMovEntity, data e hora que o usuário aprovou o
  // registro cadastrado, código do usuário que aprovou o cadastro do registro e
  // status registro
  public TplBkrPortfMgmtEntity(
                               TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_,
                               Date lastAuthDate_, String lastAuthUserId_,
                               String recStatCode_ )
  {
    m_data = new TplBkrPortfMgmtEntityVO();

    TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) m_data;
    TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtMovEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity_.getData();

    tplBkrPortfMgmtEntityVO.setBkrCnpjNbr( tplBkrPortfMgmtMovEntityVO.getBkrCnpjNbr() );
    tplBkrPortfMgmtEntityVO.setProdAcctCode( tplBkrPortfMgmtMovEntityVO.getProdAcctCode() );
    tplBkrPortfMgmtEntityVO.setProdUnderAcctCode( tplBkrPortfMgmtMovEntityVO.getProdUnderAcctCode() );
    tplBkrPortfMgmtEntityVO.setBovespaCurAcctNbr( tplBkrPortfMgmtMovEntityVO.getBovespaCurAcctNbr() );
    tplBkrPortfMgmtEntityVO.setBovespaInvstAcctNbr( tplBkrPortfMgmtMovEntityVO.getBovespaInvstAcctNbr() );
    tplBkrPortfMgmtEntityVO.setBmfCurAcctNbr( tplBkrPortfMgmtMovEntityVO.getBmfCurAcctNbr() );
    tplBkrPortfMgmtEntityVO.setBmfInvstAcctNbr( tplBkrPortfMgmtMovEntityVO.getBmfInvstAcctNbr() );
    tplBkrPortfMgmtEntityVO.setLastUpdDate( tplBkrPortfMgmtMovEntityVO.getLastUpdDate() );
    tplBkrPortfMgmtEntityVO.setLastUpdUserId( tplBkrPortfMgmtMovEntityVO.getLastUpdUserId() );
    tplBkrPortfMgmtEntityVO.setLastAuthDate( lastAuthDate_ );
    tplBkrPortfMgmtEntityVO.setLastAuthUserId( lastAuthUserId_ );
    tplBkrPortfMgmtEntityVO.setRecStatCode( recStatCode_ );
  }

  // Construtor da entidade TplBkrPortfMgmtEntity a partir de uma entidade de
  // movimento TplBkrPortfMgmtMovEntity
  public TplBkrPortfMgmtEntity(
                               TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity_ )
  {
    m_data = new TplBkrPortfMgmtEntityVO();

    TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) m_data;
    TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtMovEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity_.getData();

    tplBkrPortfMgmtEntityVO.setBkrCnpjNbr( tplBkrPortfMgmtMovEntityVO.getBkrCnpjNbr() );
    tplBkrPortfMgmtEntityVO.setProdAcctCode( tplBkrPortfMgmtMovEntityVO.getProdAcctCode() );
    tplBkrPortfMgmtEntityVO.setProdUnderAcctCode( tplBkrPortfMgmtMovEntityVO.getProdUnderAcctCode() );

    tplBkrPortfMgmtEntityVO.setBovespaCurAcctNbr( tplBkrPortfMgmtMovEntityVO.getBovespaCurAcctNbr() );
    tplBkrPortfMgmtEntityVO.setBovespaInvstAcctNbr( tplBkrPortfMgmtMovEntityVO.getBovespaInvstAcctNbr() );
    tplBkrPortfMgmtEntityVO.setBmfCurAcctNbr( tplBkrPortfMgmtMovEntityVO.getBmfCurAcctNbr() );
    tplBkrPortfMgmtEntityVO.setBmfInvstAcctNbr( tplBkrPortfMgmtMovEntityVO.getBmfInvstAcctNbr() );
    tplBkrPortfMgmtEntityVO.setLastUpdDate( tplBkrPortfMgmtMovEntityVO.getLastUpdDate() );
    tplBkrPortfMgmtEntityVO.setLastUpdUserId( tplBkrPortfMgmtMovEntityVO.getLastUpdUserId() );
  }

  // Construtor de uma entidade TplBkrPortfMgmtEntity a partir de uma entidaed
  // de corretora TplBrokerEntity, número do cliente, número da corretora,
  // código da conta produto e código da sub conta produto
  public TplBkrPortfMgmtEntity( TplBrokerEntity tplBrokerEntity_,
                               BigInteger custNbr_, BigInteger bkrCustNbr_,
                               BigInteger prodAcctCode_,
                               BigInteger prodUnderAcctCode_ )
  {
    m_data = new TplBkrPortfMgmtEntityVO();

    TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) m_data;

    tplBkrPortfMgmtEntityVO.setCustNbr( custNbr_ );
    tplBkrPortfMgmtEntityVO.setBkrCnpjNbr( tplBrokerEntity_.getData().getBkrCnpjNbr() );
    tplBkrPortfMgmtEntityVO.setBkrCustNbr( bkrCustNbr_ );
    tplBkrPortfMgmtEntityVO.setProdAcctCode( prodAcctCode_ );
    tplBkrPortfMgmtEntityVO.setProdUnderAcctCode( prodUnderAcctCode_ );
    tplBkrPortfMgmtEntityVO.setBkrNameText( tplBrokerEntity_.getData().getBkrNameText() );
    tplBkrPortfMgmtEntityVO.setBkrAddrText( tplBrokerEntity_.getData().getBkrAddrText() );

  }

}