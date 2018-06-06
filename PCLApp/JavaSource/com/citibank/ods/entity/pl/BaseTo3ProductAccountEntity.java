package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTo3ProductAccountEntityVO;
import com.citibank.ods.entity.pl.valueobject.To3ProductAccountEntityVO;

/**
 * @author m.nakamura
 * 
 * Agregador da tabela de contrato.
 */
public class BaseTo3ProductAccountEntity extends BaseODSEntity implements
    Cloneable
{
  // O entity VO de contrato
  protected BaseTo3ProductAccountEntityVO m_data;

  //Tamanho da coluna número do relacionamento no banco de dados
  public final static int C_RELTN_NBR_SIZE = 11;

  //Tamanho da coluna número do cliente no banco de dados
  public final static int C_CUST_NBR_SIZE = 11;

  //Tamanho da coluna número da conta corrente no banco de dados
  public final static int C_CUR_ACCT_NBR_SIZE = 15;

  //Tamanho da coluna número do código do produto no banco de dados
  public final static int C_PROD_CODE_SIZE = 10;
  
  //Tamanho da coluna código isin no banco de dados
  public final static int C_PROD_ACCT_ISIN_CODE_SIZE = 20;

  //Tamanho do código da Entidade Legal
  public final static int C_PROD_ACCT_LEGAL_BUS_CODE_SIZE = 3;
  
  //Tamanho do código da  carteira adminstrada da conta produto
  public final static int C_PROD_ACCT_PORTF_MGMT_CODE_SIZE = 2;
  
  //Tamanho do campo usuário da última atualização.
  public final static int C_LAST_UPD_USER_ID_SIZE = 20;

  /**
   * Recupera o entity VO de contrato.
   * 
   * @return BaseTo3ProductAccountEntityVO - O entity VO de contrato.
   */
  public BaseTo3ProductAccountEntityVO getData()
  {
    return m_data;
  }

  public Object clone()
  {
    To3ProductAccountEntityVO productAccountEntityVO = new To3ProductAccountEntityVO();

    productAccountEntityVO.setProdAcctCode( m_data.getProdAcctCode() );
    productAccountEntityVO.setProdUnderAcctCode( m_data.getProdUnderAcctCode() );
    productAccountEntityVO.setCustNbr( m_data.getCustNbr() );
    productAccountEntityVO.setReltnNbr( m_data.getReltnNbr() );
    productAccountEntityVO.setCurAcctNbr( m_data.getCurAcctNbr() );
    productAccountEntityVO.setSysCode( m_data.getSysCode() );
    productAccountEntityVO.setSysSegCode( m_data.getSysSegCode() );
    productAccountEntityVO.setProdCode( m_data.getProdCode() );
    productAccountEntityVO.setOrigProdAcctNbr( m_data.getOrigProdAcctNbr() );
    productAccountEntityVO.setProdAcctStaDate( m_data.getProdAcctStaDate() );
    productAccountEntityVO.setProdAcctEndDate( m_data.getProdAcctEndDate() );
    productAccountEntityVO.setProdAcctSitCode( m_data.getProdAcctSitCode() );
    productAccountEntityVO.setAcctAmt( m_data.getAcctAmt() );
    productAccountEntityVO.setBalRefDate( m_data.getBalRefDate() );
    productAccountEntityVO.setRecStatCode( m_data.getRecStatCode() );
    To3ProductAccountEntity productAccountEntity = new To3ProductAccountEntity(productAccountEntityVO);

    return productAccountEntity;
  }
}