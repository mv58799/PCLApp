/*
 * Created on 14/11/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.To3ProductAccountEntity;
import com.citibank.ods.entity.pl.TplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplDocTransferEntity;
import com.citibank.ods.entity.pl.TplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.TplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.TplIpDocTransFinancEntity;


/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class IpDocTransFinancDetailFncVO extends BaseIpDocTransFinancDetailFncVO
{

  public IpDocTransFinancDetailFncVO()
  {
	m_baseTplIpDocTransFinancEntity = new TplIpDocTransFinancEntity();

	m_baseTplDocTransferEntity = new TplDocTransferEntity();

	m_baseTplIpDocCallbackEntity = new TplIpDocCallbackEntity();
	
	m_baseTplCurAcctPrmntInstrEntity = new TplCurAcctPrmntInstrEntity();
	
	m_baseTplIpDocPrvtEntity = new TplIpDocPrvtEntity();
	
	m_baseTplCustomerPrvtEntity = new TplCustomerPrvtEntity();
	
	m_to3ProductAccountEntity = new To3ProductAccountEntity(); 
  }

 
}