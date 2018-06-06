package com.citibank.ods.modules.product.prodplayerrole.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplPlayerEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleHistEntity;
import com.citibank.ods.entity.pl.TplProdPlayerRoleMovEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProdPlayerRoleMovEntityVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerMovementDetailFncVO;
import com.citibank.ods.modules.product.prodplayerrole.form.ProdPlayerRoleMovementDetailForm;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.ProdPlayerRoleDetailFncVO;
import com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject.ProdPlayerRoleMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

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

public class ProdPlayerRoleMovementDetailFnc extends
    BaseProdPlayerRoleDetailFnc implements ODSMovementDetailFnc
{

  private static final String C_INSERT = "inserido";

  private static final String C_PRODUCT = "produto";

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProdPlayerRoleMovementDetailFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {

    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntityNew;
    TplProdPlayerRoleMovEntityVO tplProdPlayerRoleMovEntityVO;

    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovementDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
      TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();
      tplProdPlayerRoleMovDAO.delete( prodPlayerRoleMovementDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );

      for ( int j = 0; j < prodPlayerRoleMovementDetailFncVO.getSelectedItemsInGrid().size(); j++ )
      {
        for ( int i = 0; i < prodPlayerRoleMovementDetailFncVO.getBaseTplProdPlayerRoleEntityList().size(); i++ )
        {
          if ( prodPlayerRoleMovementDetailFncVO.getDeletedItems().get( j ).equals(
                                                                                    "S" ) )
          {
            prodPlayerRoleMovementDetailFncVO.getBaseTplProdPlayerRoleEntityList().remove(
                                                                                           i );
            break;
          }

        }
      }
      ArrayList listProdPlayerRoleMov = prodPlayerRoleMovementDetailFncVO.getBaseTplProdPlayerRoleEntityList();

      for ( int i = 0; i < listProdPlayerRoleMov.size(); i++ )
      {
        tplProdPlayerRoleMovEntityNew = ( TplProdPlayerRoleMovEntity ) listProdPlayerRoleMov.get( i );
        tplProdPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntityNew.getData();
        tplProdPlayerRoleMovEntityVO.setLastUpdDate( new Date() );
        tplProdPlayerRoleMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );

        if ( tplProdPlayerRoleMovEntityVO.getopernCode() == null )
        {
          tplProdPlayerRoleMovEntityVO.setOpernCode( BaseODSEntity.C_OPERN_CODE_INSERT );
        }
        tplProdPlayerRoleMovDAO.insert( tplProdPlayerRoleMovEntityNew );
      }

    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {

    TplProdPlayerRoleEntity tplProdPlayerRoleEntity;
    TplProdPlayerRoleHistEntity tplProdPlayerRoleHistEntity;
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity;
    TplProdPlayerRoleMovEntityVO tplProdPlayerRoleMovEntityVO;

    validateApprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
      //Desclaração dos DAOs
      TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();
      TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
      TplProdPlayerRoleHistDAO tplProdPlayerRoleHistDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleHistDAO();

      ArrayList listProdPlayerRoleMov = prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList();
      ArrayList listProdPlayerRoleCurrent = tplProdPlayerRoleDAO.selectByPlyr( prodPlayerRoleMovDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );

      //Adiciona os produtos da tabela corrente na tabela de histórico.
      if ( listProdPlayerRoleCurrent != null
           && listProdPlayerRoleCurrent.size() > 0 )
      {
        for ( int i = 0; i < listProdPlayerRoleCurrent.size(); i++ )
        {
          tplProdPlayerRoleEntity = ( TplProdPlayerRoleEntity ) listProdPlayerRoleCurrent.get( i );
          tplProdPlayerRoleHistEntity = new TplProdPlayerRoleHistEntity(
                                                                         tplProdPlayerRoleEntity,
                                                                         new Date() );
          tplProdPlayerRoleHistDAO.insert( tplProdPlayerRoleHistEntity );
        }
      }

      for ( int i = 0; i < listProdPlayerRoleMov.size(); i++ )
      {
        tplProdPlayerRoleMovEntity = ( TplProdPlayerRoleMovEntity ) listProdPlayerRoleMov.get( i );
        tplProdPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity.getData();

        tplProdPlayerRoleEntity = new TplProdPlayerRoleEntity(
                                                               tplProdPlayerRoleMovEntity,
                                                               new Date(),
                                                               fncVO_.getLoggedUser().getUserID(),
                                                               TplProdPlayerRoleEntity.C_REC_STAT_CODE_ACTIVE );

        if ( tplProdPlayerRoleMovEntityVO.getopernCode().equals(
                                                                 TplProdPlayerRoleMovEntity.C_OPERN_CODE_DELETE ) )
        {
          ( ( TplProdPlayerRoleEntityVO ) tplProdPlayerRoleEntity.getData() ).setRecStatCode( TplProdPlayerRoleEntity.C_REC_STAT_CODE_INACTIVE );
          tplProdPlayerRoleDAO.update( tplProdPlayerRoleEntity );
        }
        else
        {

          if ( tplProdPlayerRoleDAO.exists( tplProdPlayerRoleEntity ) )
          {
            tplProdPlayerRoleDAO.update( tplProdPlayerRoleEntity );
          }
          else
          {
            tplProdPlayerRoleDAO.insert( tplProdPlayerRoleEntity );
          }
        }
      }

      // Remove o movimento
      tplProdPlayerRoleMovDAO.delete( prodPlayerRoleMovDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    if ( !fncVO_.hasErrors() )
    {
      ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
      TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();

      String plyrCnpjNbr = prodPlayerRoleMovDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr();
      if ( plyrCnpjNbr != null && !plyrCnpjNbr.equals( "" ) )
      {
        //Exclui todas as associações relacionadas ao player
        tplProdPlayerRoleMovDAO.delete( plyrCnpjNbr );
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity = ( TplProdPlayerRoleMovEntity ) prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList().get(
                                                                                                                                                                  0 );
    TplProdPlayerRoleMovEntityVO tplProdPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity.getData();

    // Testar usuário
    if ( prodPlayerRoleMovDetailFncVO.getLoggedUser() == null
         || !prodPlayerRoleMovDetailFncVO.getLoggedUser().getUserID().equals(
                                                                              tplProdPlayerRoleMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( PlayerMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }
    if ( prodPlayerRoleMovDetailFncVO.getSelectedItemsInGrid() == null
         || prodPlayerRoleMovDetailFncVO.getSelectedItemsInGrid().isEmpty() )
    {
      fncVO_.addError( ProdPlayerRoleDetailFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                       C_INSERT, C_PRODUCT );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity = ( TplProdPlayerRoleMovEntity ) prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList().get(
                                                                                                                                                                  0 );
    TplProdPlayerRoleMovEntityVO tplProdPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity.getData();

    // Testar usuário
    if ( prodPlayerRoleMovDetailFncVO.getLoggedUser() == null
         || prodPlayerRoleMovDetailFncVO.getLoggedUser().getUserID().equals(
                                                                             tplProdPlayerRoleMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( PlayerMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {

    TplProductEntity tplProductEntity;
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity;
    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;

    if ( !prodPlayerRoleMovDetailFncVO.isFromSearch() )
    {
      prodPlayerRoleMovDetailFncVO.getProductToInsertFncVO().getData().setPlyrRoleTypeCode(
                                                                                            null );

      TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();
      ArrayList listAssoc = tplProdPlayerRoleMovDAO.selectByPlyr( prodPlayerRoleMovDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );
      prodPlayerRoleMovDetailFncVO.setBaseTplProdPlayerRoleEntityList( listAssoc );

      TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();

      if ( listAssoc != null && listAssoc.size() > 0 )
      {
        for ( int i = 0; i < listAssoc.size(); i++ )
        {
          tplProdPlayerRoleMovEntity = ( TplProdPlayerRoleMovEntity ) listAssoc.get( i );
          tplProductEntity = new TplProductEntity();
          tplProductEntity.getData().setProdCode(
                                                  tplProdPlayerRoleMovEntity.getData().getProdCode() );
          tplProductEntity.getData().setSysCode(
                                                 tplProdPlayerRoleMovEntity.getData().getSysCode() );
          tplProductEntity.getData().setSysSegCode(
                                                    tplProdPlayerRoleMovEntity.getData().getSysSegCode() );

          tplProductEntity = tplProductDAO.find( tplProductEntity );

          tplProdPlayerRoleMovEntity.getDataProduct().setProdName(
                                                                   tplProductEntity.getData().getProdName() );
        }
      }

      //  Obter os dados do player selecionado
      TplPlayerDAO tplPlayerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
      TplPlayerEntity tplPlayerEntity = ( TplPlayerEntity ) tplPlayerDAO.find( prodPlayerRoleMovDetailFncVO.getTplPlayerEntity() );
      prodPlayerRoleMovDetailFncVO.setTplPlayerEntity( tplPlayerEntity );

      //Obtem os papéis associados ao player selecionado
      TplPlayerRoleDAO tplPlayerRoleDAO = ODSDAOFactory.getInstance().getTplPlayerRoleDAO();
      DataSet prodPlayerRoleTypes = tplPlayerRoleDAO.selectByPlyr( prodPlayerRoleMovDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );
      prodPlayerRoleMovDetailFncVO.setProdPlayerRoleTypes( prodPlayerRoleTypes );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    TplProductEntity tplProductEntity;
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity;
    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
    TplProdPlayerRoleMovDAO tplProdPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();
    ArrayList listAssoc = tplProdPlayerRoleMovDAO.selectByPlyr( prodPlayerRoleMovDetailFncVO.getTplPlayerEntity().getData().getPlyrCnpjNbr() );
    prodPlayerRoleMovDetailFncVO.setBaseTplProdPlayerRoleEntityList( listAssoc );

    TplProductDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductDAO();

    if ( listAssoc != null && listAssoc.size() > 0 )
    {
      for ( int i = 0; i < listAssoc.size(); i++ )
      {
        tplProdPlayerRoleMovEntity = ( TplProdPlayerRoleMovEntity ) listAssoc.get( i );
        tplProductEntity = new TplProductEntity();
        tplProductEntity.getData().setProdCode(
                                                tplProdPlayerRoleMovEntity.getData().getProdCode() );
        tplProductEntity.getData().setSysCode(
                                               tplProdPlayerRoleMovEntity.getData().getSysCode() );
        tplProductEntity.getData().setSysSegCode(
                                                  tplProdPlayerRoleMovEntity.getData().getSysSegCode() );

        tplProductEntity = tplProductDAO.find( tplProductEntity );

        tplProdPlayerRoleMovEntity.getDataProduct().setProdName(
                                                                 tplProductEntity.getData().getProdName() );
      }
    }

    //  Obter os dados do player selecionado
    TplPlayerDAO tplPlayerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    TplPlayerEntity tplPlayerEntity = ( TplPlayerEntity ) tplPlayerDAO.find( prodPlayerRoleMovDetailFncVO.getTplPlayerEntity() );
    prodPlayerRoleMovDetailFncVO.setTplPlayerEntity( tplPlayerEntity );
  }

  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
    ProdPlayerRoleMovementDetailForm detailForm = ( ProdPlayerRoleMovementDetailForm ) form_;

    super.updateFncVOFromForm( form_, fncVO_ );

    //Obtem os dados da tela que serão inseridos no grid
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntityInsert = new TplProdPlayerRoleMovEntity();
    TplProdPlayerRoleMovEntityVO tplProdPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntityInsert.getData();

	String[] codeArray = null;
	String plyrCnpjNbr = null;
	if(detailForm.getSelectedCode()!= null && !detailForm.getSelectedCode().equals("")){
	  codeArray = detailForm.getSelectedCode().split(","); 	
	}
	
	if(codeArray!= null){
		plyrCnpjNbr = codeArray[0];
	}
	else if(detailForm.getPlyrCnpjNbr()!= null && !detailForm.getPlyrCnpjNbr().equals("")){
		plyrCnpjNbr = detailForm.getPlyrCnpjNbr();
	}
	tplProdPlayerRoleMovEntityVO.setPlyrCnpjNbr(plyrCnpjNbr);
    prodPlayerRoleMovDetailFncVO.getTplPlayerEntity().getData().setPlyrCnpjNbr(	plyrCnpjNbr );

    tplProdPlayerRoleMovEntityVO.setPlyrRoleTypeCode( ODSConstraintDecoder.recodeRoleType( detailForm.getPlyrRoleTypeCode() ) );
    prodPlayerRoleMovDetailFncVO.setProductToInsertFncVO( tplProdPlayerRoleMovEntityInsert );

    prodPlayerRoleMovDetailFncVO.setClickedSearch( "" );
  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    String prodCode;
    String sysCode;
    BigInteger sysSegCode;
    String strSysSegCode;
    String plyrCnpjNbr;
    String plyrRoleType;
    String prodName;
    String opernCode;
    String[] row;

    super.updateFormFromFncVO( form_, fncVO_ );
    String[][] resultGrid = null;

    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
    ProdPlayerRoleMovementDetailForm detailForm = ( ProdPlayerRoleMovementDetailForm ) form_;
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_PRESENTATION );
    ArrayList listProductAssocPlayerRole = prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList();

    //Preenche o nome do player
    detailForm.setPlyrName( prodPlayerRoleMovDetailFncVO.getTplPlayerEntity().getData().getPlyrName() );
    detailForm.setPlyrRoleTypeCode( prodPlayerRoleMovDetailFncVO.getProductToInsertFncVO().getData().getPlyrRoleTypeCode() );
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity = null;

    if ( listProductAssocPlayerRole != null
         && listProductAssocPlayerRole.size() > 0 )
    {
      resultGrid = new String[ listProductAssocPlayerRole.size() ][];
      int rowIndex = 0;
      for ( Iterator ite = listProductAssocPlayerRole.iterator(); ite.hasNext(); rowIndex++ )
      {

        tplProdPlayerRoleMovEntity = ( TplProdPlayerRoleMovEntity ) ite.next();

        prodCode = tplProdPlayerRoleMovEntity.getData().getProdCode();

        sysCode = tplProdPlayerRoleMovEntity.getData().getSysCode();

        sysSegCode = tplProdPlayerRoleMovEntity.getData().getSysSegCode();
        strSysSegCode = sysSegCode != null ? sysSegCode.toString() : "";

        plyrCnpjNbr = tplProdPlayerRoleMovEntity.getData().getPlyrCnpjNbr();

        plyrRoleType = ODSConstraintDecoder.decodeRoleType( tplProdPlayerRoleMovEntity.getData().getPlyrRoleTypeCode() );

        prodName = tplProdPlayerRoleMovEntity.getDataProduct().getProdName();

        opernCode = ODSConstraintDecoder.decodeOpern( ( ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity.getData() ).getopernCode() );

        row = new String[] { plyrCnpjNbr, plyrRoleType, prodCode, sysCode,
                            strSysSegCode, prodName, opernCode };
        resultGrid[ rowIndex ] = row;
      }
      detailForm.setProdPlayerRoleDomains( resultGrid );
      detailForm.setLastUpdUserId( tplProdPlayerRoleMovEntity.getData().getLastUpdUserId() );

      if ( tplProdPlayerRoleMovEntity.getData().getLastUpdDate() != null )
      {
        detailForm.setLastUpdDate( dateFormat.format( tplProdPlayerRoleMovEntity.getData().getLastUpdDate() ) );
      }

      String strOpernCode = ( ODSConstraintDecoder.decodeOpern( ( ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity.getData() ).getopernCode() ) );
      if ( strOpernCode != null )
      {
        detailForm.setOpernCode( strOpernCode );
      }

    }
    else
    {
      detailForm.setProdPlayerRoleDomains( null );
    }
    detailForm.setClickedSearch( prodPlayerRoleMovDetailFncVO.getClickedSearch() );
  }

  public void validateInsertDomain( BaseFncVO fncVO_ )
  {

    //Cria instancia das entities de ProdPlayerRole e Produto
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovementEntity;
    TplProdPlayerRoleMovEntity tplProdPlayerRoleMovementEntityInsert;
    TplProductEntity tplProductEntity;

    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;
    TplProdPlayerRoleMovEntityVO prodPlayerRoleMovEntityVO = ( TplProdPlayerRoleMovEntityVO ) prodPlayerRoleMovDetailFncVO.getProductToInsertFncVO().getData();

    //Validar campos obrigatórios
    if ( prodPlayerRoleMovEntityVO.getPlyrCnpjNbr() == null
         || prodPlayerRoleMovEntityVO.getPlyrCnpjNbr().equals( "" ) )
    {
      fncVO_.addError(
                       ProdPlayerRoleMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_CNPJ_NBR );
    }

    if ( prodPlayerRoleMovEntityVO.getPlyrRoleTypeCode() == null
         || prodPlayerRoleMovEntityVO.getPlyrRoleTypeCode().equals( "" ) )
    {
      fncVO_.addError(
                       ProdPlayerRoleMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_ROLE );
    }

    //Verificar se a associação está duplicada no grid
    if ( prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList() != null
         && !prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList().isEmpty() )
    {
      for ( int i = 0; i < prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList().size(); i++ )
      {
        tplProdPlayerRoleMovementEntity = ( TplProdPlayerRoleMovEntity ) prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList().get(
                                                                                                                                                i );
        for ( int j = 0; j < prodPlayerRoleMovDetailFncVO.getSelectedProduct().size(); j++ )
        {
          if ( prodPlayerRoleMovDetailFncVO.getSelectedProduct().get( j ).equals(
                                                                                  "S" ) )
          {
            tplProductEntity = ( TplProductEntity ) prodPlayerRoleMovDetailFncVO.getListProduct().get(
                                                                                                       j );
            tplProdPlayerRoleMovementEntityInsert = new TplProdPlayerRoleMovEntity();
            tplProdPlayerRoleMovementEntityInsert.getData().setProdCode(
                                                                         tplProductEntity.getData().getProdCode() );
            tplProdPlayerRoleMovementEntityInsert.getData().setSysCode(
                                                                        tplProductEntity.getData().getSysCode() );
            tplProdPlayerRoleMovementEntityInsert.getData().setSysSegCode(
                                                                           tplProductEntity.getData().getSysSegCode() );
            tplProdPlayerRoleMovementEntityInsert.getData().setPlyrRoleTypeCode(
                                                                                 tplProdPlayerRoleMovementEntity.getData().getPlyrRoleTypeCode() );
            tplProdPlayerRoleMovementEntityInsert.getData().setPlyrCnpjNbr(
                                                                            tplProdPlayerRoleMovementEntity.getData().getPlyrCnpjNbr() );

            if ( tplProdPlayerRoleMovementEntity.equals( tplProdPlayerRoleMovementEntityInsert ) )
            {
              fncVO_.addError( ProdPlayerRoleDetailFncVO.C_ERROR_DUPLICATED_ELEMENT_IN_GRID );
            }
          }
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

      ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;

      //Adiciona os produtos selecionados na lista de produtos associados ao
      // player
      int listProduct = prodPlayerRoleMovDetailFncVO.getSelectedProduct().size();
      TplProdPlayerRoleMovEntity tplProdPlayerRoleMovEntity;
      TplProductEntity tplProductEntity;

      for ( int count = 0; count < listProduct; count++ )
      {
        tplProductEntity = ( TplProductEntity ) prodPlayerRoleMovDetailFncVO.getListProduct().get(
                                                                                                   count );
        tplProdPlayerRoleMovEntity = new TplProdPlayerRoleMovEntity();

        if ( prodPlayerRoleMovDetailFncVO.getSelectedProduct().get( count ).equals(
                                                                                    "S" ) )
        {
          tplProdPlayerRoleMovEntity.getData().setProdCode(
                                                            tplProductEntity.getData().getProdCode() );
          tplProdPlayerRoleMovEntity.getData().setSysCode(
                                                           tplProductEntity.getData().getSysCode() );
          tplProdPlayerRoleMovEntity.getData().setSysSegCode(
                                                              tplProductEntity.getData().getSysSegCode() );

          tplProdPlayerRoleMovEntity.getData().setPlyrCnpjNbr(
                                                               prodPlayerRoleMovDetailFncVO.getProductToInsertFncVO().getData().getPlyrCnpjNbr() );
          tplProdPlayerRoleMovEntity.getData().setPlyrRoleTypeCode(
                                                                    prodPlayerRoleMovDetailFncVO.getProductToInsertFncVO().getData().getPlyrRoleTypeCode() );
          tplProdPlayerRoleMovEntity.getDataProduct().setProdName(
                                                                   tplProductEntity.getData().getProdName() );
          ( ( TplProdPlayerRoleMovEntityVO ) tplProdPlayerRoleMovEntity.getData() ).setOpernCode( TplProdPlayerRoleEntity.C_OPERN_CODE_INSERT );

          prodPlayerRoleMovDetailFncVO.getBaseTplProdPlayerRoleEntityList().add(
                                                                                 tplProdPlayerRoleMovEntity );
          prodPlayerRoleMovDetailFncVO.getSelectedItemsInGrid().add( "S" );
          prodPlayerRoleMovDetailFncVO.getDeletedItems().add( "N" );

        }
      }
      prodPlayerRoleMovDetailFncVO.setAssociation( true );

    }

  }

  /**
   * Remove um elemento na lista
   * @param fncVO_
   */
  public void deleteDomain( BaseFncVO fncVO_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.prodplayerrole.functionality.BaseProdPlayerRoleDetailFnc#getDAO()
   */
  public BaseTplProdPlayerRoleDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProdPlayerRoleMovDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.prodplayerrole.functionality.BaseProdPlayerRoleDetailFnc#getPlayerRoleDAO()
   */
  public BaseTplPlayerRoleDAO getPlayerRoleDAO()
  {
    return ODSDAOFactory.getInstance().getTplPlayerRoleMovDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    //
  }

  public void listProduct( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;

    TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
    prodPlayerRoleMovDetailFncVO.setListProduct( tplProdPlayerRoleDAO.listProduct(
                                                                                   prodPlayerRoleMovDetailFncVO.getProdCodeSrc(),
                                                                                   prodPlayerRoleMovDetailFncVO.getProdNameSrc() ) );

    if ( prodPlayerRoleMovDetailFncVO.getListProduct().size() > 0 )
    {
      prodPlayerRoleMovDetailFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      prodPlayerRoleMovDetailFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

  }

  public void clearPage( BaseFncVO fncVO_ )
  {
    ProdPlayerRoleMovementDetailFncVO prodPlayerRoleMovDetailFncVO = ( ProdPlayerRoleMovementDetailFncVO ) fncVO_;

    prodPlayerRoleMovDetailFncVO.setListProduct( new ArrayList() );
    prodPlayerRoleMovDetailFncVO.setSelectedProduct( null );
  }

}