/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject;

import com.citibank.ods.entity.pl.TplCustomerPrvtCmplMovEntity;

/**
 * @author bruno.zanetti
 *
 */
public class CustomerPrvtCmplMovementDetailFncVO extends BaseCustomerPrvtCmplDetailFncVO
{

  private TplCustomerPrvtCmplMovEntity tplCustomerPrvtCmplMovEntity = null; 

  public CustomerPrvtCmplMovementDetailFncVO()
  {
    tplCustomerPrvtCmplMovEntity = new TplCustomerPrvtCmplMovEntity();
  }  
  /**
   * @return Returns tplCustomerPrvtCmplMovEntity.
   */
  public TplCustomerPrvtCmplMovEntity getTplCustomerPrvtCmplMovEntity()
  {
    return tplCustomerPrvtCmplMovEntity;
  }
  /**
   * @param tplCustomerPrvtCmplMovEntity_ Field tplCustomerPrvtCmplMovEntity to be setted.
   */
  public void setTplCustomerPrvtCmplMovEntity(
                                              TplCustomerPrvtCmplMovEntity tplCustomerPrvtCmplMovEntity_ )
  {
    tplCustomerPrvtCmplMovEntity = tplCustomerPrvtCmplMovEntity_;
  }
  
  public static final String C_LAST_UPD_DATE_DESCRIPTION = "Data de Última Atualização";
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Usuário da Última Atualização";
  public static final String C_LAST_AUTH_DATE_DESCRIPTION = "Data de Última Autorização";
  public static final String C_LAST_AUTH_USER_ID_DESCRIPTION = "Usuário da Última Autorização";
  public static final String C_OPERN_CODE_DESCRIPTION = "Código de Operação";

}