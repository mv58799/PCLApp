/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.entity.pl;

import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplErEntityVO;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class BaseTplErEntity extends BaseODSEntity
{
	protected BaseTplErEntityVO m_data;
	
	public BaseTplErEntityVO getData()
	{
		return m_data;
	}
}
