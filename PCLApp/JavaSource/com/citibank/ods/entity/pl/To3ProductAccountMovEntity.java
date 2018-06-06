package com.citibank.ods.entity.pl;

import com.citibank.ods.entity.pl.valueobject.To3ProductAccountEntityVO;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountMovEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * Agregador da tabela de movimento de contratos
 * @author michele.monteiro,16/05/2007
 *  
 */

public class To3ProductAccountMovEntity extends BaseTo3ProductAccountEntity
{
  /**
   * Cria novo objeto To3ProductAccountMovEntity
   */
  public To3ProductAccountMovEntity()
  {
    m_data = new To3ProductAccountMovEntityVO();
  }

  /**
   * Construtor - Carrega os atributos de movimento com os atributos da current
   */
  public To3ProductAccountMovEntity(
                                    To3ProductAccountEntity to3ProductAccountEntity_,
                                    String opernCode_ )
  {
    m_data = new To3ProductAccountMovEntityVO();
    To3ProductAccountEntityVO to3ProductAccountEntityVO = ( To3ProductAccountEntityVO ) to3ProductAccountEntity_.getData();

    m_data.setCurAcctNbr( to3ProductAccountEntityVO.getCurAcctNbr() );
    m_data.setCustNbr( to3ProductAccountEntityVO.getCustNbr() );
    m_data.setLastAuthDate( to3ProductAccountEntityVO.getLastAuthDate() );
    m_data.setLastAuthUserId( to3ProductAccountEntityVO.getLastAuthUserId() );
    m_data.setLastUpdDate( to3ProductAccountEntityVO.getLastUpdDate() );
    m_data.setLastUpdUserId( to3ProductAccountEntityVO.getLastUpdUserId() );
    m_data.setOrigProdAcctNbr( to3ProductAccountEntityVO.getOrigProdAcctNbr() );
    m_data.setProdAcctCode( to3ProductAccountEntityVO.getProdAcctCode() );
    m_data.setProdAcctEndDate( to3ProductAccountEntityVO.getProdAcctEndDate() );
    m_data.setProdAcctIsinCode( to3ProductAccountEntityVO.getProdAcctIsinCode() );
    m_data.setProdAcctLegalBusCode( to3ProductAccountEntityVO.getProdAcctLegalBusCode() );
    m_data.setProdAcctPlcy23aInd( to3ProductAccountEntityVO.getProdAcctPlcy23aInd() );
    m_data.setProdAcctPlcy23bInd( to3ProductAccountEntityVO.getProdAcctPlcy23bInd() );
    m_data.setProdAcctPortfMgmtCode( to3ProductAccountEntityVO.getProdAcctPortfMgmtCode() );
    m_data.setProdAcctSitCode( to3ProductAccountEntityVO.getProdAcctSitCode() );
    m_data.setProdAcctStaDate( to3ProductAccountEntityVO.getProdAcctStaDate() );
    m_data.setProdCode( to3ProductAccountEntityVO.getProdCode() );
    m_data.setProdUnderAcctCode( to3ProductAccountEntityVO.getProdUnderAcctCode() );
    m_data.setRecStatCode( to3ProductAccountEntityVO.getRecStatCode() );
    m_data.setReltnNbr( to3ProductAccountEntityVO.getReltnNbr() );
    m_data.setSysCode( to3ProductAccountEntityVO.getSysCode() );
    m_data.setSysSegCode( to3ProductAccountEntityVO.getSysSegCode() );
    ( ( To3ProductAccountMovEntityVO ) m_data ).setOpernCode( opernCode_ );
  }

}