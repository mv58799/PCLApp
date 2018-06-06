package com.citibank.ods.entity.bg;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.bg.valueobject.BaseTbgPointAcctInvstEntityVO;

//
//�2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.bg;
 * @version 1.0
 * @author michele.monteiro,18/06/2007
 *  
 */

public class BaseTbgPointAcctInvstEntity extends BaseODSEntity
{

  // O entity VO de Conta Investimento
  protected BaseTbgPointAcctInvstEntityVO m_data;

  //Tamanho da coluna do n�mero da entidade
  public final static int C_INVST_ACCT_BUS_NBR = 3;

  //Tamanho da coluna do n�mero da conta investimento
  public final static int C_INVST_ACCT_BRCH_NBR = 3;

  //Tamanho do campo n�mero da filial
  public final static int C_ACCT_BRCH_NBR = 3;

  //Tamanho da coluna do n�mero da entidade
  public final static int C_ACCT_BUS_NBR = 3;

  //Tamanho da coluna do n�mero da conta corrente
  public final static int C_CUR_ACCT_NBR = 11;

  //Tamanho da coluna do n�mero da conta investimento
  public final static int C_INVST_CUR_ACCT_NBR = 11;

  //Tamanho da coluna do c�digo de origem do movimento
  public final static int C_CUST_MOV_ORIG_CODE = 2;

  /**
   * Recupera o entity VO da conta investimento.
   * 
   * @return BaseTbgPointAcctInvstEntityVO
   */
  public BaseTbgPointAcctInvstEntityVO getData()
  {
    return m_data;
  }

}