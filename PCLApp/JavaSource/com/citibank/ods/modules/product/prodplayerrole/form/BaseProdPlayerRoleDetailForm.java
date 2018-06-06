package com.citibank.ods.modules.product.prodplayerrole.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplPlayerEntity;
import com.citibank.ods.entity.pl.BaseTplProductEntity;
import com.citibank.ods.modules.product.player.form.PlayerDetailable;
import com.citibank.ods.modules.product.player.functionality.valueobject.BasePlayerDetailFncVO;
import com.citibank.ods.modules.product.product.functionality.valueobject.BaseProductDetailFncVO;

/**
 * @author atilio.l.araujo
 *  
 */

public class BaseProdPlayerRoleDetailForm extends BaseForm implements
    PlayerDetailable
{

  // Data e Hora da Ultima atualizacao efetuada pelo usuario.
  private String m_lastUpdDate = "";

  // Codigo do Usuario que efetuou a ultima atualizacao registro.
  private String m_lastUpdUserId = "";

  // CNPJ do Player.
  private String m_plyrCnpjNbr = "";

  // Tipo do Papel do Player (Administrador, Gestor, Custodiante, Controlador,
  // Auditor)
  private String m_plyrRoleTypeCode = "";

  // Codigo do Produto.
  private String m_prodCode = "";

  // Codigo do sistema origem do cadastro do Produto.
  private String m_sysCodeSrc = "";

  // Codigo da segmentacao do sistema origem do cadastro do Produto.
  private String m_sysSegCodeSrc = "";

  // Domains prodPlayerRoleDomains
  private String[][] prodPlayerRoleDomains = null;

  // Dataset com os tipos de papéis do Player
  private DataSet m_prodPlayerRoleTypes = null;

  //Tela destino do botão buscar
  private String m_clickedSearch = "";

  // Lista com os ítens selecionados no grid
  private String[] m_selectedItemsInGrid = null;

  // Lista com os produtos selecionados no grid
  private String[] m_selectedProduct = null;

  // Lista com os ítens a serem excluídos
  private String[] m_deletedItems = null;

  //Lista de produtos
  private String[][] m_listProduct;

  //Nome do produto
  private String m_prodName = "";

  /**
   * @return Returns m_lastUpdDate.
   */
  public String getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field m_lastUpdDate to be setted.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns m_lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field m_lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns m_plyrCnpjNbr.
   */
  public String getPlyrCnpjNbr()
  {
    return m_plyrCnpjNbr;
  }

  /**
   * @param plyrCnpjNbr_ Field m_plyrCnpjNbr to be setted.
   */
  public void setPlyrCnpjNbr( String plyrCnpjNbr_ )
  {
    m_plyrCnpjNbr = plyrCnpjNbr_;
  }

  /**
   * @return Returns m_plyrRoleTypeCode.
   */
  public String getPlyrRoleTypeCode()
  {
    return m_plyrRoleTypeCode;
  }

  /**
   * @param plyrRoleTypeCode_ Field m_plyrRoleTypeCode to be setted.
   */
  public void setPlyrRoleTypeCode( String plyrRoleTypeCode_ )
  {
    m_plyrRoleTypeCode = plyrRoleTypeCode_;
  }

  /**
   * @return Returns prodPlayerRoleDomains.
   */
  public String[][] getProdPlayerRoleDomains()
  {
    return prodPlayerRoleDomains;
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

  /**
   * @param prodPlayerRoleDomains Field prodPlayerRoleDomains to be setted.
   */
  public void setProdPlayerRoleDomains( String[][] prodPlayerRoleDomains )
  {
    this.prodPlayerRoleDomains = prodPlayerRoleDomains;
  }

  /**
   * Realiza as validações de tipos e tamanhos
   */
  public ActionErrors validate( ActionMapping actionMapping_,
                               HttpServletRequest request_ )
  {
    ActionErrors errors = new ActionErrors();

    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_CODE_DESCRIPTION,
                                    m_prodCode,
                                    BaseTplProductEntity.C_PROD_CODE_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BaseProductDetailFncVO.C_PROD_NAME_DESCRIPTION,
                                    m_prodName,
                                    BaseTplProductEntity.C_PROD_NAME_SIZE,
                                    errors );

    ODSValidator.validateMaxLength(
                                    BasePlayerDetailFncVO.C_PLYR_CNPJ_NBR_DESCRIPTION,
                                    m_plyrCnpjNbr,
                                    BaseTplPlayerEntity.C_PLYR_CNPJ_NBR_SIZE,
                                    errors );

    return errors;
  }

  public String getProdCode()
  {
    return m_prodCode;
  }

  public void setProdCode( String prodCodeSrc_ )
  {
    m_prodCode = prodCodeSrc_;
  }

  public String getSysCodeSrc()
  {
    return m_sysCodeSrc;
  }

  public void setSysCodeSrc( String sysCodeSrc_ )
  {
    m_sysCodeSrc = sysCodeSrc_;
  }

  public String getSysSegCodeSrc()
  {
    return m_sysSegCodeSrc;
  }

  public void setSysSegCodeSrc( String sysSegCodeSrc_ )
  {
    m_sysSegCodeSrc = sysSegCodeSrc_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.player.form.PlayerDetailable#getSelectedPlyrCnpjNbr()
   */
  public String getSelectedPlyrCnpjNbr()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.player.form.PlayerDetailable#setSelectedPlyrCnpjNbr(java.lang.String)
   */
  public void setSelectedPlyrCnpjNbr( String setSelectedPlyrCnpjNbr_ )
  {
    if ( setSelectedPlyrCnpjNbr_ != null
         && !setSelectedPlyrCnpjNbr_.equals( "" ) )
    {
      m_plyrCnpjNbr = setSelectedPlyrCnpjNbr_;
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#setSelectedProdCode(java.lang.String)
   */
  public void setSelectedProdCode( String selectedProdCode_ )
  {
    setProdCode( selectedProdCode_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#getSelectedProdCode()
   */
  public String getSelectedProdCode()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#setSelectedSysCode(java.lang.String)
   */
  public void setSelectedSysCode( String selectedSysCode_ )
  {
    setSysCodeSrc( selectedSysCode_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#getSelectedSysCode()
   */
  public String getSelectedSysCode()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#setSelectedSysSegCode(java.lang.String)
   */
  public void setSelectedSysSegCode( String selectedSysSegCode_ )
  {
    setSysSegCodeSrc( selectedSysSegCode_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.product.form.ProductSearchable#getSelectedSysSegCode()
   */
  public String getSelectedSysSegCode()
  {
    return null;
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
   * @return Returns selectedItemsInGrid.
   */
  public String[] getSelectedItemsInGrid()
  {
    return m_selectedItemsInGrid;
  }

  /**
   * @param selectedItemsInGrid_ Field selectedItemsInGrid to be setted.
   */
  public void setSelectedItemsInGrid( String[] selectedItemsInGrid_ )
  {
    m_selectedItemsInGrid = selectedItemsInGrid_;
  }

  /**
   * @return Returns deletedItems.
   */
  public String[] getDeletedItems()
  {
    return m_deletedItems;
  }

  /**
   * @param deletedItems_ Field deletedItems to be setted.
   */
  public void setDeletedItems( String[] deletedItems_ )
  {
    m_deletedItems = deletedItems_;
  }

  /**
   * @return Returns listProduct.
   */
  public String[][] getListProduct()
  {
    return m_listProduct;
  }

  /**
   * @param listProduct_ Field listProduct to be setted.
   */
  public void setListProduct( String[][] listProduct_ )
  {
    m_listProduct = listProduct_;
  }

  /**
   * @return Returns selectedProduct.
   */
  public String[] getSelectedProduct()
  {
    return m_selectedProduct;
  }

  /**
   * @return Returns prodNameSrc.
   */
  public String getProdName()
  {
    return m_prodName;
  }

  /**
   * @param prodNameSrc_ Field prodNameSrc to be setted.
   */
  public void setProdName( String prodNameSrc_ )
  {
    m_prodName = prodNameSrc_;
  }

  /**
   * @param selectedProduct_ Field selectedProduct to be setted.
   */
  public void setSelectedProduct( String[] selectedProduct_ )
  {
    m_selectedProduct = selectedProduct_;
  }

  public void reset( ActionMapping arg0_, HttpServletRequest arg1_ )
  {
    if ( m_selectedProduct != null )
    {
      for ( int i = 0; i < m_selectedProduct.length; i++ )
      {
        m_selectedProduct[ i ] = "N";
      }
    }
    if ( m_selectedItemsInGrid != null )
    {
      for ( int i = 0; i < m_selectedItemsInGrid.length; i++ )
      {
        m_selectedItemsInGrid[ i ] = "N";
      }
    }

    if ( m_deletedItems != null )
    {
      for ( int i = 0; i < m_deletedItems.length; i++ )
      {
        m_deletedItems[ i ] = "N";
      }
    }

  }
}