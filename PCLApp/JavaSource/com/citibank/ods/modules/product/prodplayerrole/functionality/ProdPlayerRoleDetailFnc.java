package com.citibank.ods.modules.product.prodplayerrole.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplPlayerEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleMovEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleEntityVO;
import com.citibank.ods.modules.product.prodplayerrole.form.ProdPlayerRoleDetailForm;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.ProdPlayerRoleDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

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

public class ProdPlayerRoleDetailFnc extends BaseProdPlayerRoleDetailFnc
    implements ODSDetailFnc
{
  private static final String C_INSERT = "inserido";

  private static final String C_PRODUCT = "produto";

  /**
   * Retorna uma instância de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProdPlayerRoleDetailFncVO();
  }

  public void insert( BaseFncVO fncVO_ )
  {
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity = null;
    TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO;

    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProdPlayerRoleDetailFncVO detailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;

      //Retira da lista final os ítens que foram inseridos e excluídos na mesma
      // operação.
      int relationList = detailFncVO.getBaseTplProdPlayerRoleEntityList().size();
      Iterator resultsIterator;

      for ( int count = 0; count < relationList; count++ )
      {

        resultsIterator = detailFncVO.getSelectedItemsInGrid().iterator();
        int max = 0;

        while ( resultsIterator.hasNext() )
        {
          resultsIterator.next();
          if ( detailFncVO.getSelectedItemsInGrid().get( max ).equals( "S" )
               && detailFncVO.getDeletedItems().get( max ).equals( "S" ) )
          {
            detailFncVO.getBaseTplProdPlayerRoleEntityList().remove( max );
            detailFncVO.getSelectedItemsInGrid().remove( max );
            detailFncVO.getDeletedItems().remove( max );
            break;
          }
          else if ( detailFncVO.getSelectedItemsInGrid().get( max ).equals( "N" )
                    && detailFncVO.getDeletedItems().get( max ).equals( "N" ) )
          {
            detailFncVO.getBaseTplProdPlayerRoleEntityList().remove( max );
            detailFncVO.getSelectedItemsInGrid().remove( max );
            detailFncVO.getDeletedItems().remove( max );
            break;
          }
          max++;

        }
      }

      ArrayList listProdPlayerRole = detailFncVO.getBaseTplProdPlayerRoleEntityList();

      //Varre a lista final e seta a operação.
      for ( int i = 0; i < detailFncVO.getBaseTplProdPlayerRoleEntityList().size(); i++ )
      {
        tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) detailFncVO.getBaseTplProdPlayerRoleEntityList().get(
                                                                                                                    i );
        tplProdPlayerRoleEntity.getData().setLastUpdDate( new Date() );
        tplProdPlayerRoleEntity.getData().setLastUpdUserId(
                                                            fncVO_.getLoggedUser() != null
                                                                                          ? fncVO_.getLoggedUser().getUserID()
                                                                                          : "" );
        if ( detailFncVO.getSelectedItemsInGrid().get( i ).equals( "S" )
             && detailFncVO.getDeletedItems().get( i ).equals( "N" ) )
        {
          tplProdPlayerRoleMovEntity = new TplProdPlayerRoleMovEntity(
                                                                       tplProdPlayerRoleEntity,
                                                                       BaseODSEntity.C_OPERN_CODE_INSERT );
        }
        else if ( detailFncVO.getSelectedItemsInGrid().get( i ).equals( "N" )
                  && detailFncVO.getDeletedItems().get( i ).equals( "S" ) )
        {
          tplProdPlayerRoleMovEntity = new TplProdPlayerRoleMovEntity(
                                                                       tplProdPlayerRoleEntity,
                                                                       BaseODSEntity.C_OPERN_CODE_DELETE );
        }

        tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();
        tplProdPlayerRoleMovDAO.insert( tplProdPlayerRoleMovEntity );

      }
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity;
    TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO;

    this.validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProdPlayerRoleDetailFncVO detailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
      ArrayList listAssociation = detailFncVO.getBaseTplProdPlayerRoleEntityList();
      tplProdPlayerRoleMovDAO = OracleODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();

      for ( Iterator ite = listAssociation.iterator(); ite.hasNext(); )
      {
        tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) ite.next();
        tplProdPlayerRoleEntity.getData().setLastUpdDate( new Date() );
        tplProdPlayerRoleEntity.getData().setLastUpdUserId(
                                                            fncVO_.getLoggedUser() != null
                                                                                          ? fncVO_.getLoggedUser().getUserID()
                                                                                          : "" );
        tplProdPlayerRoleMovEntity = new TplProdPlayerRoleMovEntity(
                                                                     tplProdPlayerRoleEntity,
                                                                     TplProdPlayerRoleMovEntity.C_OPERN_CODE_DELETE );
        tplProdPlayerRoleMovDAO.insert( tplProdPlayerRoleMovEntity );
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();

    int findItemsDeleted = 0;

    for ( int i = 0; i < prodPlayerRoleDetailFncVO.getDeletedItems().size(); i++ )
    {
      if ( prodPlayerRoleDetailFncVO.getDeletedItems().get( i ).equals( "S" ) )
      {
        findItemsDeleted++;

      }
    }
    ArrayList listProdPlayerRole = prodPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList();

    if ( findItemsDeleted == listProdPlayerRole.size() )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_MANDATORY_ASSOCIATION, C_INSERT,
                       C_PRODUCT );
    }
    else
    {
      for ( int i = 0; i < listProdPlayerRole.size(); i++ )
      {
        tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) listProdPlayerRole.get( i );

        if ( tplProdPlayerRoleMovDAO.exists( tplProdPlayerRoleEntity.getData().getPlyrCnpjNbr() ) )
        {
          fncVO_.addError( ProdPlayerRoleDetailFncVO.C_ERROR_IN_MOVEMENT );
        }
      }
    }

  }

  public void validateInsertDomain( BaseFncVO fncVO_ )
  {
    //Cria instancia das entities de ProdPlayerRole e Produto
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    TplProdPlayerRoleEntity tplProdPlayerRoleEntityInsert;
    TplProductEntity tplProductEntity;

    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    TplProdPlayerRoleEntityVO prodPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) prodPlayerRoleDetailFncVO.getProductToInsertFncVO().getData();

    //Validar campos obrigatórios
    if ( prodPlayerRoleDetailFncVO.getSelectedProduct().isEmpty()
         || prodPlayerRoleDetailFncVO.getSelectedProduct() == null )
    {
      fncVO_.addError( ProdPlayerRoleDetailFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                       C_INSERT, C_PRODUCT );
    }

    if ( prodPlayerRoleEntityVO.getPlyrRoleTypeCode() == null
         || prodPlayerRoleEntityVO.getPlyrRoleTypeCode().equals( "" ) )
    {
      fncVO_.addError( ProdPlayerRoleDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_ROLE );

    }

    //Verificar se a associação está duplicada no grid
    if ( prodPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList() != null
         && !prodPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList().isEmpty() )
    {
      for ( int i = 0; i < prodPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList().size(); i++ )
      {
        tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) prodPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList().get(
                                                                                                                                  i );
        for ( int j = 0; j < prodPlayerRoleDetailFncVO.getSelectedProduct().size(); j++ )
        {
          if ( prodPlayerRoleDetailFncVO.getSelectedProduct().get( j ).equals(
                                                                               "S" ) )
          {
            tplProductEntity = ( TplProductEntity ) prodPlayerRoleDetailFncVO.getListProduct().get(
                                                                                                    j );
            tplProdPlayerRoleEntityInsert = new TplProdPlayerRoleEntity();
            tplProdPlayerRoleEntityInsert.getData().setProdCode(
                                                                 tplProductEntity.getData().getProdCode() );
            tplProdPlayerRoleEntityInsert.getData().setSysCode(
                                                                tplProductEntity.getData().getSysCode() );
            tplProdPlayerRoleEntityInsert.getData().setSysSegCode(
                                                                   tplProductEntity.getData().getSysSegCode() );
            tplProdPlayerRoleEntityInsert.getData().setPlyrRoleTypeCode(
                                                                         tplProdPlayerRoleEntity.getData().getPlyrRoleTypeCode() );
            tplProdPlayerRoleEntityInsert.getData().setPlyrCnpjNbr(
                                                                    tplProdPlayerRoleEntity.getData().getPlyrCnpjNbr() );

            if ( tplProdPlayerRoleEntity.equals( tplProdPlayerRoleEntityInsert ) )
            {
              fncVO_.addError( ProdPlayerRoleDetailFncVO.C_ERROR_DUPLICATED_ELEMENT_IN_GRID );
            }
          }
        }
      }
    }

  }

  /*
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    ArrayList listProdPlayerRole = prodPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList();
    TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();

    //Verifica se já existe alguma associação do player em movimento
    if ( listProdPlayerRole != null && listProdPlayerRole.size() > 0 )
    {
      for ( int i = 0; i < listProdPlayerRole.size(); i++ )
      {
        tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) listProdPlayerRole.get( i );

        if ( tplProdPlayerRoleMovDAO.exists( tplProdPlayerRoleEntity.getData().getPlyrCnpjNbr() ) )
        {
          fncVO_.addError( ProdPlayerRoleDetailFncVO.C_ERROR_IN_MOVEMENT );
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    TplProductEntity tplProductEntity;

    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
    ArrayList listAssoc = tplProdPlayerRoleDAO.selectByPlyr( prodPlayerRoleDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );
    prodPlayerRoleDetailFncVO.setBaseTplProdPlayerRoleEntityList( listAssoc );

    TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();

    if ( listAssoc != null && listAssoc.size() > 0 )
    {
      for ( int i = 0; i < listAssoc.size(); i++ )
      {
        tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) listAssoc.get( i );
        tplProductEntity = new TplProductEntity();
        tplProductEntity.getData().setProdCode(
                                                tplProdPlayerRoleEntity.getData().getProdCode() );
        tplProductEntity.getData().setSysCode(
                                               tplProdPlayerRoleEntity.getData().getSysCode() );
        tplProductEntity.getData().setSysSegCode(
                                                  tplProdPlayerRoleEntity.getData().getSysSegCode() );

        tplProductEntity = tplProductDAO.find( tplProductEntity );

        tplProdPlayerRoleEntity.getDataProduct().setProdName(
                                                              tplProductEntity.getData().getProdName() );
      }
    }

    //  Obter os dados do player selecionado
    TplPlayerDAO tplPlayerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    TplPlayerEntity tplPlayerEntity = ( TplPlayerEntity ) tplPlayerDAO.find( prodPlayerRoleDetailFncVO.getTplPlayerEntity() );
    prodPlayerRoleDetailFncVO.setTplPlayerEntity( tplPlayerEntity );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    TplProductEntity tplProductEntity;

    if ( !prodPlayerRoleDetailFncVO.isFromSearch() )
    {
      prodPlayerRoleDetailFncVO.getProductToInsertFncVO().getData().setPlyrRoleTypeCode(
                                                                                         null );

      //Obtem a lista de associações relacionadas ao player selecionado
      TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
      ArrayList listAssoc = tplProdPlayerRoleDAO.selectByPlyr( prodPlayerRoleDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );
      prodPlayerRoleDetailFncVO.setBaseTplProdPlayerRoleEntityList( listAssoc );

      TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();

      if ( listAssoc != null && listAssoc.size() > 0 )
      {
        for ( int i = 0; i < listAssoc.size(); i++ )
        {
          tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) listAssoc.get( i );
          tplProductEntity = new TplProductEntity();
          tplProductEntity.getData().setProdCode(
                                                  tplProdPlayerRoleEntity.getData().getProdCode() );
          tplProductEntity.getData().setSysCode(
                                                 tplProdPlayerRoleEntity.getData().getSysCode() );
          tplProductEntity.getData().setSysSegCode(
                                                    tplProdPlayerRoleEntity.getData().getSysSegCode() );

          tplProductEntity = tplProductDAO.find( tplProductEntity );

          tplProdPlayerRoleEntity.getDataProduct().setProdName(
                                                                tplProductEntity.getData().getProdName() );
        }
      }

      //Obtem os papéis associados ao player selecionado
      TplPlayerRoleDAO tplPlayerRoleDAO = ODSDAOFactory.getInstance().getTplPlayerRoleDAO();
      DataSet prodPlayerRoleTypes = tplPlayerRoleDAO.selectByPlyr( prodPlayerRoleDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );
      prodPlayerRoleDetailFncVO.setProdPlayerRoleTypes( prodPlayerRoleTypes );

      //Obter os dados do player selecionado
      TplPlayerDAO tplPlayerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
      TplPlayerEntity tplPlayerEntity = ( TplPlayerEntity ) tplPlayerDAO.find( prodPlayerRoleDetailFncVO.getTplPlayerEntity() );
      prodPlayerRoleDetailFncVO.setTplPlayerEntity( tplPlayerEntity );
    }
    prodPlayerRoleDetailFncVO.setAssociation( false );
    prodPlayerRoleDetailFncVO.setFromSearch( false );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {

    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();

    if ( tplProdPlayerRoleMovDAO.exists( prodPlayerRoleDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() ) )
    {
      fncVO_.addError( ProdPlayerRoleDetailFncVO.C_ERROR_IN_MOVEMENT );
    }

    if ( !fncVO_.hasErrors() )
    {
      TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
      TplProductEntity tplProductEntity;

      TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
      ArrayList listAssoc = tplProdPlayerRoleDAO.selectByPlyr( prodPlayerRoleDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );
      prodPlayerRoleDetailFncVO.setBaseTplProdPlayerRoleEntityList( listAssoc );

      TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();

      if ( listAssoc != null && listAssoc.size() > 0 )
      {
        for ( int i = 0; i < listAssoc.size(); i++ )
        {
          tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) listAssoc.get( i );
          tplProductEntity = new TplProductEntity();
          tplProductEntity.getData().setProdCode(
                                                  tplProdPlayerRoleEntity.getData().getProdCode() );
          tplProductEntity.getData().setSysCode(
                                                 tplProdPlayerRoleEntity.getData().getSysCode() );
          tplProductEntity.getData().setSysSegCode(
                                                    tplProdPlayerRoleEntity.getData().getSysSegCode() );

          tplProductEntity = tplProductDAO.find( tplProductEntity );

          tplProdPlayerRoleEntity.getDataProduct().setProdName(
                                                                tplProductEntity.getData().getProdName() );
        }
      }
    }
  }

  /**
   * Insere um elemento na lista
   * @param fncVO_
   */
  public void insertDomain( BaseFncVO fncVO_ )
  {
    fncVO_.clearErrors();
    this.validateInsertDomain( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;

      //Adiciona os produtos selecionados na lista de produtos associados ao
      // player
      int listProduct = prodPlayerRoleDetailFncVO.getSelectedProduct().size();
      TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
      TplProductEntity tplProductEntity;

      for ( int count = 0; count < listProduct; count++ )
      {
        tplProductEntity = ( TplProductEntity ) prodPlayerRoleDetailFncVO.getListProduct().get(
                                                                                                count );
        tplProdPlayerRoleEntity = new TplProdPlayerRoleEntity();

        if ( prodPlayerRoleDetailFncVO.getSelectedProduct().get( count ).equals(
                                                                                 "S" ) )
        {
          tplProdPlayerRoleEntity.getData().setProdCode(
                                                         tplProductEntity.getData().getProdCode() );
          tplProdPlayerRoleEntity.getData().setSysCode(
                                                        tplProductEntity.getData().getSysCode() );
          tplProdPlayerRoleEntity.getData().setSysSegCode(
                                                           tplProductEntity.getData().getSysSegCode() );

          tplProdPlayerRoleEntity.getData().setPlyrCnpjNbr(
                                                            prodPlayerRoleDetailFncVO.getProductToInsertFncVO().getData().getPlyrCnpjNbr() );
          tplProdPlayerRoleEntity.getData().setPlyrRoleTypeCode(
                                                                 prodPlayerRoleDetailFncVO.getProductToInsertFncVO().getData().getPlyrRoleTypeCode() );
          tplProdPlayerRoleEntity.getDataProduct().setProdName(
                                                                tplProductEntity.getData().getProdName() );

          prodPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList().add(
                                                                              tplProdPlayerRoleEntity );
          prodPlayerRoleDetailFncVO.getSelectedItemsInGrid().add( "S" );
          prodPlayerRoleDetailFncVO.getDeletedItems().add( "N" );

        }
      }
      prodPlayerRoleDetailFncVO.setAssociation( true );
    }

  }

  /**
   * Remove um elemento da lista
   * @param fncVO_
   */
  public void deleteDomain( BaseFncVO fncVO_ )
  {
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    ProdPlayerRoleDetailForm detailForm = ( ProdPlayerRoleDetailForm ) form_;

    super.updateFncVOFromForm( form_, fncVO_ );
    prodPlayerRoleDetailFncVO.getTplPlayerEntity().getData().setPlyrCnpjNbr(
                                                                             detailForm.getPlyrCnpjNbr() );

    //Obtem os dados da tela que serão inseridos no grid
    TplProdPlayerRoleEntity tplProdPlayerRoleEntityInsert = new TplProdPlayerRoleEntity();
    TplProdPlayerRoleEntityVO tplProdPlayerRoleEntityVO = ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntityInsert.getData();

    tplProdPlayerRoleEntityVO.setPlyrCnpjNbr( detailForm.getPlyrCnpjNbr() );
    tplProdPlayerRoleEntityVO.setPlyrRoleTypeCode( ODSConstraintDecoder.recodeRoleType( detailForm.getPlyrRoleTypeCode() ) );
    prodPlayerRoleDetailFncVO.setProductToInsertFncVO( tplProdPlayerRoleEntityInsert );

    prodPlayerRoleDetailFncVO.setClickedSearch( "" );
  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    String prodCode;
    String sysCode;
    BigInteger sysSegCode;
    String strSysSegCode;
    String plyrCnpjNbr;
    String plyrRoleType;
    String prodName;
    String[] row;

    super.updateFormFromFncVO( form_, fncVO_ );
    String[][] resultGrid = null;

    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    ProdPlayerRoleDetailForm detailForm = ( ProdPlayerRoleDetailForm ) form_;
    ArrayList listProductAssocPlayerRole = prodPlayerRoleDetailFncVO.getBaseTplProdPlayerRoleEntityList();

    //Preenche o nome do player
    detailForm.setPlyrName( prodPlayerRoleDetailFncVO.getTplPlayerEntity().getData().getPlyrName() );

    if ( listProductAssocPlayerRole != null
         && listProductAssocPlayerRole.size() > 0 )
    {
      resultGrid = new String[ listProductAssocPlayerRole.size() ][];
      int rowIndex = 0;
      for ( Iterator ite = listProductAssocPlayerRole.iterator(); ite.hasNext(); rowIndex++ )
      {

        tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) ite.next();

        prodCode = tplProdPlayerRoleEntity.getData().getProdCode();

        sysCode = tplProdPlayerRoleEntity.getData().getSysCode();

        sysSegCode = tplProdPlayerRoleEntity.getData().getSysSegCode();
        strSysSegCode = sysSegCode != null ? sysSegCode.toString() : "";

        plyrCnpjNbr = tplProdPlayerRoleEntity.getData().getPlyrCnpjNbr();

        plyrRoleType = ODSConstraintDecoder.decodeRoleType( tplProdPlayerRoleEntity.getData().getPlyrRoleTypeCode() );

        prodName = tplProdPlayerRoleEntity.getDataProduct().getProdName();

        row = new String[] { plyrCnpjNbr, plyrRoleType, prodCode, sysCode,
                            strSysSegCode, prodName };
        resultGrid[ rowIndex ] = row;
      }
      detailForm.setProdPlayerRoleDomains( resultGrid );

    }
    else
    {
      detailForm.setProdPlayerRoleDomains( null );
    }

    detailForm.setClickedSearch( prodPlayerRoleDetailFncVO.getClickedSearch() );
  }

  public void existsInMovement( BaseFncVO fncVO_ )
  {
    fncVO_.clearErrors();
    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;
    TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();
    if ( tplProdPlayerRoleMovDAO.exists( prodPlayerRoleDetailFncVO.getProductToInsertFncVO().getData().getPlyrCnpjNbr() ) )
    {
      fncVO_.addError( BaseODSFncVO.C_ERROR_IN_MOVEMENT );
    }
  }

  /**
   * Retorna a instancia do DAO
   * @see com.citibank.ods.modules.product.prodplayerrole.functionality.BaseProdPlayerRoleDetailFnc#getDAO()
   */
  public BaseTplProdPlayerRoleDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
  }

  /**
   * Retorna a instancia do PlayerRoleDAO
   * @see com.citibank.ods.modules.product.prodplayerrole.functionality.BaseProdPlayerRoleDetailFnc#getDAO()
   */
  public BaseTplPlayerRoleDAO getPlayerRoleDAO()
  {
    return ODSDAOFactory.getInstance().getTplPlayerRoleDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
  }

  public void listProduct( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;

    TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ( TplProdPlayerRoleDAO ) this.getDAO();
    prodPlayerRoleDetailFncVO.setListProduct( tplProdPlayerRoleDAO.listProduct(
                                                                                prodPlayerRoleDetailFncVO.getProdCodeSrc(),
                                                                                prodPlayerRoleDetailFncVO.getProdNameSrc() ) );
    
    

    if ( prodPlayerRoleDetailFncVO.getListProduct().size() > 0 )
    {
      prodPlayerRoleDetailFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      prodPlayerRoleDetailFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  public void clearPage( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleDetailFncVO prodPlayerRoleDetailFncVO = ( ProdPlayerRoleDetailFncVO ) fncVO_;

    prodPlayerRoleDetailFncVO.setListProduct( new ArrayList() );
    prodPlayerRoleDetailFncVO.setSelectedProduct( null );
  }
}