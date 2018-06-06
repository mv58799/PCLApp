package com.citibank.ods.modules.client.erem.functionality.valueobject;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplErEmEntity;
import com.citibank.ods.entity.pl.BaseTplErEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.erem.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseEREMDetailFncVO extends BaseODSFncVO
{
  /**
   * Descriptions
   */
  public static final String C_ER_NBR_DESCRIPTION = "Número do ER";

  public static final String C_EM_NBR_DESCRIPTION = "Número do EM";

  public static final String C_INSERT = "inserido";

  public static final String C_UPDATE = "alterado";

  public static final String C_ROLE_CUST_CODE_DESCRIPTION = "Papel do Cliente";

  public static final String C_CUST_NBR_DESCRIPTION = "Número do Cliente";
  
  private String m_custFullNameText;

  private BigInteger m_custNbr = null;

  private String m_clickedSearch;

  private boolean isFromSearch = false;

  //Lista de itens selecionados no grid
  ArrayList m_selectedItemsInGrid = new ArrayList();
  
  //Lista de itens que serão excluídos selecionados no grid. 
  ArrayList m_deletedItems = new ArrayList();
  
  //Dominios de Dados Complementares
  
  private DataSet erReltnTrfIndDomain;
	
  private DataSet reltnEndReasCodeDomain;
	
  private DataSet equityClassCodeDomain;	

  /*
   * Array de Entities
   */
  private ArrayList baseTplErEmEntities = new ArrayList();

  /**
   * Entity
   */
  protected BaseTplErEmEntity m_baseTplErEmEntity = null;
  
  protected BaseTplErEntity baseTplErEntity = null;

  /*
   * DataSet de Role Customer
   */
  private DataSet m_roleCustCodeDomain;

  /*
   * Elementos selecionados no Grid
   */
  private String m_selectedErNbrInGrid;

  private String m_selectedEmNbrInGrid;

	/**
	 * @return Returns the baseTplErEmEntity.
	 */
	public BaseTplErEmEntity getBaseTplErEmEntity() {
		return m_baseTplErEmEntity;
	}
	
	/**
	 * @param baseTplErEmEntity_ The baseTplErEmEntity to set.
	 */
	public void setBaseTplErEmEntity(BaseTplErEmEntity baseTplErEmEntity_) {
		m_baseTplErEmEntity = baseTplErEmEntity_;
	}
	
	/**
	 * @return Returns the roleCustCodeDomain.
	 */
	public DataSet getRoleCustCodeDomain() {
		return m_roleCustCodeDomain;
	}
	
	/**
	 * @param roleCustCodeDomain_ The roleCustCodeDomain to set.
	 */
	public void setRoleCustCodeDomain(DataSet roleCustCodeDomain_) {
		m_roleCustCodeDomain = roleCustCodeDomain_;
	}
	
	/**
	 * @return Returns the baseTplErEmEntities.
	 */
	public ArrayList getBaseTplErEmEntities() {
		return baseTplErEmEntities;
	}
	
	/**
	 * @param baseTplErEmEntities_ The baseTplErEmEntities to set.
	 */
	public void setBaseTplErEmEntities(ArrayList baseTplErEmEntities_) {
		baseTplErEmEntities = baseTplErEmEntities_;
	}
	
	/**
	 * @return Returns the selectedEmNbr.
	 */
	public String getSelectedEmNbrInGrid() {
		return m_selectedEmNbrInGrid;
	}
	
	/**
	 * @param selectedEmNbr_ The selectedEmNbr to set.
	 */
	public void setSelectedEmNbrInGrid(String selectedEmNbr_) {
		m_selectedEmNbrInGrid = selectedEmNbr_;
	}
	
	/**
	 * @return Returns the selectedErNbr.
	 */
	public String getSelectedErNbrInGrid() {
		return m_selectedErNbrInGrid;
	}
	
	/**
	 * @param selectedErNbr_ The selectedErNbr to set.
	 */
	public void setSelectedErNbrInGrid(String selectedErNbr_) {
		m_selectedErNbrInGrid = selectedErNbr_;
	}
	
	/**
	 * @return Returns the clickedSearch.
	 */
	public String getClickedSearch() {
		return m_clickedSearch;
	}
	
	/**
	 * @param clickedSearch_ The clickedSearch to set.
	 */
	public void setClickedSearch(String clickedSearch_) {
		m_clickedSearch = clickedSearch_;
	}
	
	/**
	 * @return Returns custFullNameText.
	 */
	public String getCustFullNameText() {
		return m_custFullNameText;
	}
	
	/**
	 * @param custFullNameText_ Field custFullNameText to be setted.
	 */
	public void setCustFullNameText(String custFullNameText_) {
		m_custFullNameText = custFullNameText_;
	}
	
	public boolean isFromSearch() {
		return isFromSearch;
	}
	
	public void setFromSearch(boolean isFromSearch_) {
		isFromSearch = isFromSearch_;
	}
	
	public BigInteger getCustNbr() {
		return m_custNbr;
	}
	
	public void setCustNbr(BigInteger custNbr_) {
		m_custNbr = custNbr_;
	}
	
	public ArrayList getSelectedItemsInGrid() {
		return m_selectedItemsInGrid;
	}
	
	public void setSelectedItemsInGrid(ArrayList selectedItemsInGrid_) {
		m_selectedItemsInGrid = selectedItemsInGrid_;
	}
	
	/**
	 * @return Returns deletedItems.
	 */
	public ArrayList getDeletedItems() {
		return m_deletedItems;
	}
	/**
	 * @param deletedItems_ Field deletedItems to be setted.
	 */
	public void setDeletedItems(ArrayList deletedItems_) {
		m_deletedItems = deletedItems_;
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
	 * @param set
	 */
	public void setEquityClassCodeDomain(DataSet equityClassCodeDomain_) {
		equityClassCodeDomain = equityClassCodeDomain_;
	}
	
	/**
	 * @param set
	 */
	public void setErReltnTrfIndDomain(DataSet erReltnTrfIndDomain_) {
		erReltnTrfIndDomain = erReltnTrfIndDomain_;
	}
	
	/**
	 * @param set
	 */
	public void setReltnEndReasCodeDomain(DataSet reltnEndReasCodeDomain_) {
		reltnEndReasCodeDomain = reltnEndReasCodeDomain_;
	}

	/**
	 * @return
	 */
	  public BaseTplErEntity getBaseTplErEntity() {
		return baseTplErEntity;
	  }
	
	/**
	 * @param entity
	 */
	  public void setBaseTplErEntity(BaseTplErEntity baseTplErEntity) {
		this.baseTplErEntity = baseTplErEntity;
	  }

}