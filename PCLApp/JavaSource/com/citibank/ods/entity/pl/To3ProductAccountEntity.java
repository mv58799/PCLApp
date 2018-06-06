package com.citibank.ods.entity.pl;

import java.util.Date;

import com.citibank.ods.entity.pl.valueobject.To3ProductAccountEntityVO;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountMovEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de contrato.
 */
public class To3ProductAccountEntity extends BaseTo3ProductAccountEntity
{
  
  
  /**
   * Cria novo objeto To3ProductAccountEntity
   * @param productAccountEntityVO_
   */
  public To3ProductAccountEntity()
  {
    m_data = new To3ProductAccountEntityVO();
  }
  /**
   * Cria novo objeto To3ProductAccountEntity
   * @param productAccountEntityVO_
   */
  public To3ProductAccountEntity(To3ProductAccountEntityVO productAccountEntityVO_)
  {
    m_data = new To3ProductAccountEntityVO();
  }

  /**
   * Cria novo objeto To3ProductAccountEntity com um entitiVO determinado.
   */
  public To3ProductAccountEntity(
                                 To3ProductAccountMovEntity productAccountMovEntity_,
                                 Date lastAuthDate_, String lastAuthUserId_,
                                 String recStatCode_ )
  {
    m_data = new To3ProductAccountEntityVO();
    To3ProductAccountMovEntityVO to3ProductAccountMovEntityVO = ( To3ProductAccountMovEntityVO ) productAccountMovEntity_.getData();

    m_data.setCurAcctNbr( to3ProductAccountMovEntityVO.getCurAcctNbr() );
    m_data.setCustNbr( to3ProductAccountMovEntityVO.getCustNbr() );
    m_data.setLastUpdDate( to3ProductAccountMovEntityVO.getLastUpdDate() );
    m_data.setLastUpdUserId( to3ProductAccountMovEntityVO.getLastUpdUserId() );
    m_data.setOrigProdAcctNbr( to3ProductAccountMovEntityVO.getOrigProdAcctNbr() );
    m_data.setProdAcctCode( to3ProductAccountMovEntityVO.getProdAcctCode() );
    m_data.setProdAcctEndDate( to3ProductAccountMovEntityVO.getProdAcctEndDate() );
    m_data.setProdAcctIsinCode( to3ProductAccountMovEntityVO.getProdAcctIsinCode() );
    m_data.setProdAcctLegalBusCode( to3ProductAccountMovEntityVO.getProdAcctLegalBusCode() );
    m_data.setProdAcctPlcy23aInd( to3ProductAccountMovEntityVO.getProdAcctPlcy23aInd() );
    m_data.setProdAcctPlcy23bInd( to3ProductAccountMovEntityVO.getProdAcctPlcy23bInd() );
    m_data.setProdAcctPortfMgmtCode( to3ProductAccountMovEntityVO.getProdAcctPortfMgmtCode() );
    m_data.setProdAcctSitCode( to3ProductAccountMovEntityVO.getProdAcctSitCode() );
    m_data.setProdAcctStaDate( to3ProductAccountMovEntityVO.getProdAcctStaDate() );
    m_data.setProdCode( to3ProductAccountMovEntityVO.getProdCode() );
    m_data.setProdUnderAcctCode( to3ProductAccountMovEntityVO.getProdUnderAcctCode() );
    m_data.setRecStatCode( to3ProductAccountMovEntityVO.getRecStatCode() );
    m_data.setReltnNbr( to3ProductAccountMovEntityVO.getReltnNbr() );
    m_data.setSysCode( to3ProductAccountMovEntityVO.getSysCode() );
    m_data.setSysSegCode( to3ProductAccountMovEntityVO.getSysSegCode() );
    m_data.setAcctAmt( to3ProductAccountMovEntityVO.getAcctAmt() );
    m_data.setBalRefDate( to3ProductAccountMovEntityVO.getBalRefDate() );
    ( ( To3ProductAccountEntityVO ) m_data ).setLastAuthUserId( lastAuthUserId_ );
    ( ( To3ProductAccountEntityVO ) m_data ).setLastAuthDate( lastAuthDate_ );
    ( ( To3ProductAccountEntityVO ) m_data ).setRecStatCode( recStatCode_ );
  }
}