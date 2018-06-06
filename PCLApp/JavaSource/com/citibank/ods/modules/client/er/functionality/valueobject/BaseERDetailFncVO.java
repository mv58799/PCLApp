/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplErEntity;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class BaseERDetailFncVO extends BaseODSFncVO
{
	public static final String C_ER_NBR_DESCRIPTION = "Número do ER";
	/**
	 * Entity
	 */
	protected BaseTplErEntity baseTplErEntity;
	
	private DataSet erReltnTrfIndDomain;
	
	private DataSet reltnEndReasCodeDomain;
	
	private DataSet equityClassCodeDomain;	
	
	/**
	 * @return
	 */
	public BaseTplErEntity getBaseTplErEntity() {
		return baseTplErEntity;
	}

	/**
	 * @return
	 */
	public DataSet getEquityClassCodeDomain() {
		return equityClassCodeDomain;
	}

	/**
	 * @return
	 */
	public DataSet getErReltnTrfIndDomain() {
		return erReltnTrfIndDomain;
	}

	/**
	 * @return
	 */
	public DataSet getReltnEndReasCodeDomain() {
		return reltnEndReasCodeDomain;
	}

	/**
	 * @param entity
	 */
	public void setBaseTplErEntity(BaseTplErEntity baseTplErEntity) {
		this.baseTplErEntity = baseTplErEntity;
	}

	/**
	 * @param set
	 */
	public void setEquityClassCodeDomain(DataSet equityClassCodeDomain) {
		this.equityClassCodeDomain = equityClassCodeDomain;
	}

	/**
	 * @param set
	 */
	public void setErReltnTrfIndDomain(DataSet erReltnTrfIndDomain) {
		this.erReltnTrfIndDomain = erReltnTrfIndDomain;
	}

	/**
	 * @param string
	 */
	public void setReltnEndReasCodeDomain(DataSet reltnEndReasCodeDomain) {
		this.reltnEndReasCodeDomain = reltnEndReasCodeDomain;
	}

}
