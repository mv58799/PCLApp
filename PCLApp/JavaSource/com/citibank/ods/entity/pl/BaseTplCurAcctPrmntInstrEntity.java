package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.bg.valueobject.BaseTbgPointAcctInvstEntityVO;
import com.citibank.ods.entity.pl.valueobject.BaseTplCurAcctPrmntInstrEntityVO;
import com.citibank.ods.entity.pl.valueobject.BaseTplIpDocPrvtEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.pl;
 * @version 1.0
 * @author michele.monteiro,04/06/2007
 *  
 */

public class BaseTplCurAcctPrmntInstrEntity extends BaseODSEntity
{
  /**
   * Constante do tamanho do campo número da conta produto.
   */
  public static final int C_PROD_ACCT_CODE_SIZE = 17;

  /**
   * Constante do tamanho do campo número da sub conta produto.
   */
  public static final int C_PROD_UNDER_ACCT_CODE_SIZE = 11;

  /**
   * Constante do tamanho do campo número do cliente.
   */
  public static final int C_CUST_NBR = 11;

  /**
   * Constante do tamanho da instrução permanente.
   */
  public static final int C_PRMNT_INSTR_CODE = 6;

  // O entity VO de contrato
  protected BaseTplCurAcctPrmntInstrEntityVO m_data;

  // EntityVO de ip
  protected BaseTplIpDocPrvtEntityVO m_dataIP;

  //EntityVo de Tbb_point_acct_invst
  protected BaseTbgPointAcctInvstEntityVO m_dataPoint;

  /**
   * Recupera o entity VO de conta corrente associada a IP.
   * 
   * @return BaseTplCurAcctPrmntInstrEntityVO - O entity VO de contrato.
   */
  public BaseTplCurAcctPrmntInstrEntityVO getData()
  {
    return m_data;
  }

  public BaseTplIpDocPrvtEntityVO getDataIP()
  {
    return m_dataIP;
  }

  public BaseTbgPointAcctInvstEntityVO getDataPoint()
  {
    return m_dataPoint;
  }

  public boolean equals(
                        BaseTplCurAcctPrmntInstrEntity baseTplCurAcctPrmntInstrEntity_ )
  {
    BaseTplCurAcctPrmntInstrEntityVO baseTplCurAcctPrmntInstrEntityVO = baseTplCurAcctPrmntInstrEntity_.getData();

    if ( m_data.getPrmntInstrCode().equals(
                                            baseTplCurAcctPrmntInstrEntityVO.getPrmntInstrCode() )
         && m_dataIP.getIpDocText().equals(
                                            baseTplCurAcctPrmntInstrEntity_.getDataIP().getIpDocText() )
         && m_dataIP.getIpInvstCurAcctInd().equals(
                                                    baseTplCurAcctPrmntInstrEntity_.getDataIP().getIpInvstCurAcctInd() ) )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

}