package com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject;

import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * 
 * @see package
 *      com.citibank.ods.modules.product.playerproduct.functionality.valueobject;
 * @version 1.0
 * @author atilio.l.araujo,Apr 4, 2007
 */

public class BaseProdPlayerRoleDetailFncVO extends BaseODSFncVO
{

  private boolean isFromSearch = false;

  //Tela destino do botão buscar
  private String m_clickedSearch = "";

  private boolean m_isAssociation = false;

  public ArrayList m_baseTplProdPlayerRoleEntityList = null;

  // Domains prodPlayerRoleDomains
  private String[][] prodPlayerRoleDomains = null;

  // Dataset com os tipos de papéis do Player
  private DataSet m_prodPlayerRoleTypes = null;

  //Lista de itens selecionados no grid
  ArrayList m_selectedItemsInGrid = new ArrayList();

  //Lista de itens selecionados no grid para exclusão
  ArrayList m_deletedItems = new ArrayList();

  //Campos Critério de pesquisa - Produto

  //Código do produto
  private String m_prodCodeSrc = "";

  //Nome do produto
  private String m_prodNameSrc = "";

  //Segmento do Sistema
  private String m_sysSegSrc = "";

  //Código do Sistema
  private String m_sysSegCodeSrc = "";
  
  //Lista de Produtos
  ArrayList m_listProduct = new ArrayList();
  
  //Lista de Produtos selecionado 
  ArrayList m_selectedProduct = new ArrayList();
  

  /**
   * Obter a lista de associações
   */
  public ArrayList getBaseTplProdPlayerRoleEntityList()
  {
    return m_baseTplProdPlayerRoleEntityList;
  }

  /**
   * Setar a lista de associações
   */
  public void setBaseTplProdPlayerRoleEntityList(
                                                 ArrayList baseTplProdPlayerRoleEntityList_ )
  {
    m_baseTplProdPlayerRoleEntityList = baseTplProdPlayerRoleEntityList_;
  }

  /**
   * @return Returns prodPlayerRoleDomains.
   */
  public String[][] getProdPlayerRoleDomains()
  {
    return prodPlayerRoleDomains;
  }

  /**
   * @param prodPlayerRoleDomains_ Field prodPlayerRoleDomains to be setted.
   */
  public void setProdPlayerRoleDomains( String[][] prodPlayerRoleDomains_ )
  {
    prodPlayerRoleDomains = prodPlayerRoleDomains_;
  }

  /**
   * @return Returns prodPLayerRoleTypes.
   */
  public DataSet getProdPlayerRoleTypes()
  {
    return m_prodPlayerRoleTypes;
  }

  /**
   * @param prodPLayerRoleTypes_ Field prodPLayerRoleTypes to be setted.
   */
  public void setProdPlayerRoleTypes( DataSet prodPlayerRoleTypes_ )
  {
    m_prodPlayerRoleTypes = prodPlayerRoleTypes_;
  }

  public boolean isFromSearch()
  {
    return isFromSearch;
  }

  public void setFromSearch( boolean isFromSearch_ )
  {
    isFromSearch = isFromSearch_;
  }

  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * @return Returns isAssociation.
   */
  public boolean isAssociation()
  {
    return m_isAssociation;
  }

  /**
   * @param isAssociation_ Field isAssociation to be setted.
   */
  public void setAssociation( boolean isAssociation_ )
  {
    m_isAssociation = isAssociation_;
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
  
  
  /**
   * @return Returns prodCodeSrc.
   */
  public String getProdCodeSrc()
  {
    return m_prodCodeSrc;
  }
  /**
   * @param prodCodeSrc_ Field prodCodeSrc to be setted.
   */
  public void setProdCodeSrc( String prodCodeSrc_ )
  {
    m_prodCodeSrc = prodCodeSrc_;
  }
  /**
   * @return Returns prodNameSrc.
   */
  public String getProdNameSrc()
  {
    return m_prodNameSrc;
  }
  /**
   * @param prodNameSrc_ Field prodNameSrc to be setted.
   */
  public void setProdNameSrc( String prodNameSrc_ )
  {
    m_prodNameSrc = prodNameSrc_;
  }
  /**
   * @return Returns sysSegCodeSrc.
   */
  public String getSysSegCodeSrc()
  {
    return m_sysSegCodeSrc;
  }
  /**
   * @param sysSegCodeSrc_ Field sysSegCodeSrc to be setted.
   */
  public void setSysSegCodeSrc( String sysSegCodeSrc_ )
  {
    m_sysSegCodeSrc = sysSegCodeSrc_;
  }
  /**
   * @return Returns sysSegSrc.
   */
  public String getSysSegSrc()
  {
    return m_sysSegSrc;
  }
  /**
   * @param sysSegSrc_ Field sysSegSrc to be setted.
   */
  public void setSysSegSrc( String sysSegSrc_ )
  {
    m_sysSegSrc = sysSegSrc_;
  }
  
  
  /**
   * @return Returns listProduct.
   */
  public ArrayList getListProduct()
  {
    return m_listProduct;
  }
  /**
   * @param listProduct_ Field listProduct to be setted.
   */
  public void setListProduct( ArrayList listProduct_ )
  {
    m_listProduct = listProduct_;
  }
  
  
  /**
   * @return Returns selectedProduct.
   */
  public ArrayList getSelectedProduct()
  {
    return m_selectedProduct;
  }
  /**
   * @param selectedProduct_ Field selectedProduct to be setted.
   */
  public void setSelectedProduct( ArrayList selectedProduct_ )
  {
    m_selectedProduct = selectedProduct_;
  }
}