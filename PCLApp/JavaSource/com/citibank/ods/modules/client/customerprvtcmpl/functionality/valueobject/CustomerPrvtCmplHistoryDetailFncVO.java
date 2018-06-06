/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject;

import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;

/**
 * @author fernando.salgado
 *
 */
public class CustomerPrvtCmplHistoryDetailFncVO extends BaseCustomerPrvtCmplDetailFncVO
{
  
  private TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity = null; 

  public CustomerPrvtCmplHistoryDetailFncVO()
  {
    tplCustomerPrvtCmplEntity = new TplCustomerPrvtCmplEntity();
  }  
  
  

  /**
   * @return Returns tplCustomerPrvtCmplEntity.
   */
  public TplCustomerPrvtCmplEntity getTplCustomerPrvtCmplEntity()
  {
    return tplCustomerPrvtCmplEntity;
  }
  /**
   * @param tplCustomerPrvtCmplEntity_ Field tplCustomerPrvtCmplEntity to be setted.
   */
  public void setTplCustomerPrvtCmplEntity(
                                           TplCustomerPrvtCmplEntity tplCustomerPrvtCmplEntity_ )
  {
    tplCustomerPrvtCmplEntity = tplCustomerPrvtCmplEntity_;
  }
  
  public static final String C_CUST_REF_DATE_DESCRIPTION = "Data de Referência do Registro";

}