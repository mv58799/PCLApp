/*
 * Created on 14/11/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl;
import java.util.ArrayList;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplIpDocTransFinancEntityVO;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class BaseTplIpDocTransFinancEntity extends BaseODSEntity
{
	public static final int C_CUST_FULL_NAME_TEXT_SIZE = 60;
	
	public static final int C_CUST_CUR_ACCT_NBR_SIZE = 11;

	public static final int C_INVST_CUR_ACCT_NBR_SIZE = 11;
	
	public static final int C_BRANCH_NBR_SIZE = 4;
	
	public static final int C_BANK_NBR_SIZE = 3;
	
	public static final int C_BENE_CUR_ACCT_NBR_SIZE = 15;
	
	public static final int C_BENE_CPF_CNPJ_NBR_SIZE = 14;
	
	public static final int C_BENE_NAME_TEXT_SIZE = 60;

	public static final int C_TRF_AMT_NBR_SIZE = 19;

	public static final int C_CHNNL_ATTD_TEXT_SIZE = 40;
	
	public static final int C_PHONE_DDD_CODE_SIZE = 4;
	
	public static final int C_PHONE_NBR_SIZE = 10;
	
	public static final int C_PHONE_EXT_NBR_SIZE = 4;
	
	public static final int C_FULL_NAME_TEXT_SIZE = 60;
	
	public static final int C_FULL_NAME_1_TEXT_SIZE = 60;
	
	public static final int C_FULL_NAME_2_TEXT_SIZE = 60;
  
	protected ArrayList m_baseDocTransferEntities;
	
	protected BaseTplIpDocTransFinancEntityVO m_data;
	
	protected ArrayList m_baseIpDocCallbackEntities;
	
	/**
	   * @return Returns data.
	   */
	  public BaseTplIpDocTransFinancEntityVO getData()
	  {
		return m_data;
	  }
	/**
	 * @return Returns baseIpDocCallbackEntities.
	 */
	public ArrayList getBaseIpDocCallbackEntities()
	{
	  return m_baseIpDocCallbackEntities;
	}

	/**
	 * @param baseIpDocCallbackEntities_ Field baseIpDocCallbackEntities to be
	 *          setted.
	 */
	public void setBaseIpDocCallbackEntities( ArrayList baseIpDocCallbackEntities_ )
	{
	  m_baseIpDocCallbackEntities = baseIpDocCallbackEntities_;
	}
	
	/**
	   * @return Returns baseDocTransferEntities.
	   */
	  public ArrayList getBaseDocTransferEntities()
	  {
		return m_baseDocTransferEntities;
	  }

	  /**
	   * @param baseDocTransferEntities_ Field baseDocTransferEntities to be setted.
	   */
	  public void setBaseDocTransferEntities( ArrayList baseDocTransferEntities_ )
	  {
		m_baseDocTransferEntities = baseDocTransferEntities_;
	  }

	  

}
