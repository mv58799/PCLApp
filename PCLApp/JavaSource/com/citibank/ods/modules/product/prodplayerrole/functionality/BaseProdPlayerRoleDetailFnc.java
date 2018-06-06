package com.citibank.ods.modules.product.prodplayerrole.functionality;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.modules.product.prodplayerrole.form.BaseProdPlayerRoleDetailForm;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.BaseProdPlayerRoleDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdPlayerRoleDAO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.modules.product.playerproduct.functionality;
 * @version 1.0
 * @author atilio.l.araujo,Apr 4, 2007
 *  
 */

public abstract class BaseProdPlayerRoleDetailFnc extends BaseFnc
{
  protected static final String C_DISPLAY_PLYR_CNPJ_NBR = "CNPJ do Player";

  protected static final String C_DISPLAY_PLYR_ROLE = "Papel do Player";

  protected static final String C_DISPLAY_PROD_CODE = "Código do Produto";

  protected static final String C_DISPLAY_SYS_CODE = "Código do Sistema";

  protected static final String C_DISPLAY_SYS_SEG_CODE = "Segmento do Sistema";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdPlayerRoleDetailFncVO fncVO = ( BaseProdPlayerRoleDetailFncVO ) fncVO_;
    BaseProdPlayerRoleDetailForm form = ( BaseProdPlayerRoleDetailForm ) form_;

    //Critérios de pesquisa do grid de Produtos

    fncVO.setProdCodeSrc( form.getProdCode() != null
                          && !form.getProdCode().equals( "" )
                                                             ? form.getProdCode()
                                                             : "" );
    fncVO.setProdNameSrc( form.getProdName() != null
                          && !form.getProdName().equals( "" )
                                                             ? form.getProdName()
                                                             : "" );

    fncVO.setSysSegCodeSrc( form.getSysSegCodeSrc() != null
                            && !form.getSysSegCodeSrc().equals( "" )
                                                                    ? form.getSysSegCodeSrc()
                                                                    : "" );

    //Manipulação dos arrays de string para os itens inseridos e excluídos do
    // grid
    if ( !fncVO.isAssociation() )
    {
      if ( form.getSelectedItemsInGrid() != null )
      {
        fncVO.setSelectedItemsInGrid( new ArrayList() );
        for ( int i = 0; i < form.getSelectedItemsInGrid().length; i++ )
        {
          fncVO.getSelectedItemsInGrid().add( form.getSelectedItemsInGrid()[ i ] );

        }

      }
      else
      {
        fncVO.setSelectedItemsInGrid( new ArrayList() );

      }
    }

    if ( form.getSelectedProduct() != null )
    {
      fncVO.setSelectedProduct( new ArrayList() );
      for ( int j = 0; j < form.getSelectedProduct().length; j++ )
      {
        fncVO.getSelectedProduct().add( form.getSelectedProduct()[ j ] );
      }
    }
    else
    {
      fncVO.setSelectedProduct( new ArrayList() );
    }
    
    if ( form.getDeletedItems() != null )
    {
      fncVO.setDeletedItems( new ArrayList() );
      for ( int j = 0; j < form.getDeletedItems().length; j++ )
      {
        fncVO.getDeletedItems().add( form.getDeletedItems()[ j ] );
      }
    }
    else
    {
      fncVO.setDeletedItems( new ArrayList() );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProdPlayerRoleDetailForm baseProdPlayerRoleDetailForm = ( BaseProdPlayerRoleDetailForm ) form_;
    BaseProdPlayerRoleDetailFncVO baseProdPlayerRoleDetailFncVO = ( BaseProdPlayerRoleDetailFncVO ) fncVO_;

    //Atualiza os critérios de pesquisa do form
    baseProdPlayerRoleDetailForm.setProdCode( baseProdPlayerRoleDetailFncVO.getProdCodeSrc() != null
                                              && !baseProdPlayerRoleDetailFncVO.getProdCodeSrc().equals(
                                                                                                         "" )
                                                                                                             ? baseProdPlayerRoleDetailFncVO.getProdCodeSrc()
                                                                                                             : "" );
    baseProdPlayerRoleDetailForm.setProdName( baseProdPlayerRoleDetailFncVO.getProdNameSrc() != null
                                              && !baseProdPlayerRoleDetailFncVO.getProdNameSrc().equals(
                                                                                                         "" )
                                                                                                             ? baseProdPlayerRoleDetailFncVO.getProdNameSrc()
                                                                                                             : "" );
    //Manipulação dos arrays de string para os itens inseridos e excluídos do
    // grid
    if ( baseProdPlayerRoleDetailFncVO.getSelectedItemsInGrid() != null
         && !baseProdPlayerRoleDetailFncVO.getSelectedItemsInGrid().isEmpty() )
    {
      String[] itemsSelectedInForm = new String[ baseProdPlayerRoleDetailFncVO.getSelectedItemsInGrid().size() ];

      for ( int i = 0; i < baseProdPlayerRoleDetailFncVO.getSelectedItemsInGrid().size(); i++ )
      {
        itemsSelectedInForm[ i ] = ( String ) baseProdPlayerRoleDetailFncVO.getSelectedItemsInGrid().get(
                                                                                                          i );
      }

      baseProdPlayerRoleDetailForm.setSelectedItemsInGrid( itemsSelectedInForm );
    }
    else
    {
      String[] itemsLoaded = new String[ baseProdPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList().size() ];

      for ( int i = 0; i < baseProdPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList().size(); i++ )
      {
        itemsLoaded[ i ] = "N";
      }

      baseProdPlayerRoleDetailForm.setSelectedItemsInGrid( itemsLoaded );

    }

    //Manipulação do array de produtos selecionado para associação ao player
    if ( baseProdPlayerRoleDetailFncVO.getSelectedProduct() != null
         && !baseProdPlayerRoleDetailFncVO.getSelectedProduct().isEmpty() )
    {
      String[] productSelectedInGrid = new String[ baseProdPlayerRoleDetailFncVO.getSelectedProduct().size() ];

      for ( int i = 0; i < baseProdPlayerRoleDetailFncVO.getSelectedProduct().size(); i++ )
      {
        productSelectedInGrid[ i ] = ( String ) baseProdPlayerRoleDetailFncVO.getSelectedProduct().get(
                                                                                                        i );
      }

      baseProdPlayerRoleDetailForm.setSelectedProduct( productSelectedInGrid );
    }
    else
    {
      String[] productSelected = new String[ baseProdPlayerRoleDetailFncVO.getListProduct().size() ];

      for ( int i = 0; i < baseProdPlayerRoleDetailFncVO.getListProduct().size(); i++ )
      {
        productSelected[ i ] = "N";
      }

      baseProdPlayerRoleDetailForm.setSelectedProduct( productSelected );

    }
    
    if ( baseProdPlayerRoleDetailFncVO.getDeletedItems() != null
         && !baseProdPlayerRoleDetailFncVO.getDeletedItems().isEmpty() )
    {
      String[] deletedItemsInList = new String[ baseProdPlayerRoleDetailFncVO.getDeletedItems().size() ];

      for ( int i = 0; i < baseProdPlayerRoleDetailFncVO.getDeletedItems().size(); i++ )
      {
        deletedItemsInList[ i ] = ( String ) baseProdPlayerRoleDetailFncVO.getDeletedItems().get(
                                                                                                  i );
      }

      baseProdPlayerRoleDetailForm.setDeletedItems( deletedItemsInList );
    }
    else
    {
      String[] deletedItems = new String[ baseProdPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList().size() ];

      for ( int i = 0; i < baseProdPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList().size(); i++ )
      {
        deletedItems[ i ] = "N";
      }

      baseProdPlayerRoleDetailForm.setDeletedItems( deletedItems );

    }



    //Manipulação da lista de produtos
    String[][] resultGrid = null;
    ArrayList listProduct = baseProdPlayerRoleDetailFncVO.getListProduct();
    TplProductEntity tplProductEntity;
    String[] row;

    if ( listProduct != null && listProduct.size() > 0 )
    {
      resultGrid = new String[ listProduct.size() ][];
      int rowIndex = 0;
      for ( Iterator ite = listProduct.iterator(); ite.hasNext(); rowIndex++ )
      {

        tplProductEntity = ( TplProductEntity ) ite.next();

        row = new String[] {
                            tplProductEntity.getData().getProdCode(),
                            tplProductEntity.getData().getSysSegCode().toString(),
                            tplProductEntity.getData().getSysCode(),
                            tplProductEntity.getData().getProdName() };
        resultGrid[ rowIndex ] = row;
      }
      baseProdPlayerRoleDetailForm.setListProduct( resultGrid );

    }
    else
    {
      baseProdPlayerRoleDetailForm.setListProduct( null );
    }

    baseProdPlayerRoleDetailForm.setProdPlayerRoleTypes( baseProdPlayerRoleDetailFncVO.getProdPlayerRoleTypes() );

  }

  public void load( BaseFncVO fncVO_ )
  {
  }

  public abstract BaseTplProdPlayerRoleDAO getDAO();

  public abstract BaseTplPlayerRoleDAO getPlayerRoleDAO();
}