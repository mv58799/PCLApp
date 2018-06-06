/*
 * Created on Apr 15, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.relationeg.functionality.valueobject;

import java.math.BigInteger;
import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseRelationEgDetailFncVO extends BaseODSFncVO
{
  private DataSet m_results;

  /*
   * Campos Description
   */
  public static final String C_RELTN_NBR_DESCRIPTION = "Número do Relacionamento";

  public static final String C_EG_NBR_DESCRIPTION = "EG";

  public static final String C_INSERT = "inserido";

  public static final String C_UPDATE = "alterado";

  private String clickedSearch;

  private boolean isFromSearch = false;
  
  // Combo de ER
  private DataSet m_erNbrDomain = null;

  //Lista de itens selecionados no grid
  ArrayList m_selectedItemsInGrid = new ArrayList();
  
  //Lista de itens selecionados no grid para exclusão
  ArrayList m_deletedItems = new ArrayList();
  
  // Número do ER
  private String m_erNbr = "";

  public boolean isFromSearch()
  {
    return isFromSearch;
  }

  public void setFromSearch( boolean isFromSearch_ )
  {
    isFromSearch = isFromSearch_;
  }

  /*
   * Domains
   */
  private BigInteger m_reltnNbr;

  private String m_egNbr;

  /*
   * Selected Domains
   */
  private String m_selectedEgNbrInGrid;

  private BigInteger m_selectedReltnNbrInGrid;

  /*
   * Elementos inseridos no grid
   */
  protected ArrayList m_baseTplRelationEgEntityArray = new ArrayList();

  /**
   * @return Returns the baseTplRelationEgEntityArray.
   */
  public ArrayList getBaseTplRelationEgEntityArray()
  {
    return m_baseTplRelationEgEntityArray;
  }

  /**
   * @param baseTplRelationEgEntityArray_ The baseTplRelationEgEntityArray to
   *          set.
   */
  public void setBaseTplRelationEgEntityArray(
                                              ArrayList baseTplRelationEgEntityArray_ )
  {
    m_baseTplRelationEgEntityArray = baseTplRelationEgEntityArray_;
  }

  /**
   * @return Returns the egNbr.
   */
  public String getEgNbr()
  {
    return m_egNbr;
  }

  /**
   * @param egNbr_ The egNbr to set.
   */
  public void setEgNbr( String egNbr_ )
  {
    m_egNbr = egNbr_;
  }

  /**
   * @return Returns the reltnNbr.
   */
  public BigInteger getReltnNbr()
  {
    return m_reltnNbr;
  }

  /**
   * @param reltnNbr_ The reltnNbr to set.
   */
  public void setReltnNbr( BigInteger reltnNbr_ )
  {
    m_reltnNbr = reltnNbr_;
  }

  /**
   * @return Returns the results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ The results to set.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Returns the selectedEgNbrInGrid.
   */
  public String getSelectedEgNbrInGrid()
  {
    return m_selectedEgNbrInGrid;
  }

  /**
   * @param selectedEgNbrInGrid_ The selectedEgNbrInGrid to set.
   */
  public void setSelectedEgNbrInGrid( String selectedEgNbrInGrid_ )
  {
    m_selectedEgNbrInGrid = selectedEgNbrInGrid_;
  }

  /**
   * @return Returns the selectedReltnNbrInGrid.
   */
  public BigInteger getSelectedReltnNbrInGrid()
  {
    return m_selectedReltnNbrInGrid;
  }

  /**
   * @param selectedReltnNbrInGrid_ The selectedReltnNbrInGrid to set.
   */
  public void setSelectedReltnNbrInGrid( BigInteger selectedReltnNbrInGrid_ )
  {
    m_selectedReltnNbrInGrid = selectedReltnNbrInGrid_;
  }

  /**
   * @return Returns the clickedSearch.
   */
  public String getClickedSearch()
  {
    return clickedSearch;
  }

  /**
   * @param clickedSearch_ The clickedSearch to set.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    clickedSearch = clickedSearch_;
  }

  /**
   * @return Returns selectedItemsInGrid.
   */
  public ArrayList getSelectedItemsInGrid()
  {
    return m_selectedItemsInGrid;
  }

  /**
   * @param selectedItemsInGrid_ Field selectedItemsInGrid to be setted.
   */
  public void setSelectedItemsInGrid( ArrayList selectedItemsInGrid_ )
  {
    m_selectedItemsInGrid = selectedItemsInGrid_;
  }
  
  /**
   * @return Returns deletedItems.
   */
  public ArrayList getDeletedItems()
  {
    return m_deletedItems;
  }
  /**
   * @param deletedItems_ Field deletedItems to be setted.
   */
  public void setDeletedItems( ArrayList deletedItems_ )
  {
    m_deletedItems = deletedItems_;
  }
  
  
  public DataSet getErNbrDomain()
  {
    return m_erNbrDomain;
  }
  public void setErNbrDomain( DataSet erNbrDomain_ )
  {
    m_erNbrDomain = erNbrDomain_;
  }
  
  
  public String getErNbr()
  {
    return m_erNbr;
  }
  public void setErNbr( String erNbr_ )
  {
    m_erNbr = erNbr_;
  }
}