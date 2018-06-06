package com.citibank.ods.modules.client.bkrportfmgmt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMessage;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtHistEntity;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtMovEntity;
import com.citibank.ods.entity.pl.TplBrokerEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBrokerEntityVO;
import com.citibank.ods.modules.client.bkrportfmgmt.form.BaseBkrPortfMgmtDetailForm;
import com.citibank.ods.modules.client.bkrportfmgmt.form.BkrPortfMgmtMovementDetailForm;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BaseBkrPortfMgmtDetailFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BkrPortfMgmtDetailFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BkrPortfMgmtMovementDetailFncVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplBkrPortfMgmtDAO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtHistDAO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtMovDAO;
import com.citibank.ods.persistence.pl.dao.TplBrokerDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * 
 * @author Hamilton Matos
 */

public class BkrPortfMgmtMovementDetailFnc extends BaseBkrPortfMgmtDetailFnc
    implements ODSMovementDetailFnc
{

  // Variáveis utilizadas na manipulação do mapa de check boxes.
  // Índice 0: Lista de ítens marcados para atualização
  private static final int C_UPDATE_INDEX = 0;

  // Índice 1: Lista de ítens marcados para remoção
  private static final int C_DELETE_INDEX = 1;

  // Índice 2: Lista de ítens marcados para inserção
  private static final int C_INSERT_INDEX = 2;

  // Variáveis utilizadas na manipulação do mapa de checkboxes
  // Checkbox marcado
  private static final String C_CHECKED = "S";

  // Checkbox não marcado
  private static final String C_UNCHECKED = "N";

  // Constante de alteração
  private static final String C_UPDATE = "Alteração";

  // Constante de exclusão
  private static final String C_DELETE = "Exclusão";

  // Constante de inserção
  private static final String C_INSERT = "Inserção";

  // Código de aprovação
  private static final String C_APPROVAL_CODE = "A";

  // Código da Conta Produto
  private static final String C_PROD_ACCT_CODE = "PROD_ACCT_CODE";

  // Código da sub conta produto
  private static final String C_PROD_UNDER_ACCT_CODE = "PROD_UNDER_ACCT_CODE";

  /**
   * Seta na Form os campos específicos de current
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    BkrPortfMgmtMovementDetailForm bkrPortfMgmtDetailForm = ( BkrPortfMgmtMovementDetailForm ) form_;
    BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

    if ( bkrPortfMgmtDetailFncVO.isApprove() == true )
    {
      super.updateFormFromFncVO( form_, fncVO_ );

      bkrPortfMgmtDetailForm.setBkrCnpjNbrInBkrPortfMgmtGrid( new String[ 0 ] );
      bkrPortfMgmtDetailForm.setBkrNameTextInBkrPortfMgmtGrid( new String[ 0 ] );
      bkrPortfMgmtDetailForm.setBovespaCurAcctNbrInBkrPortfMgmtGrid( new String[ 0 ] );
      bkrPortfMgmtDetailForm.setBovespaInvstAcctNbrInBkrPortfMgmtGrid( new String[ 0 ] );
      bkrPortfMgmtDetailForm.setBmfInvstAcctNbrInBkrPortfMgmtGrid( new String[ 0 ] );
      bkrPortfMgmtDetailForm.setBmfCurAcctNbrInBkrPortfMgmtGrid( new String[ 0 ] );
      bkrPortfMgmtDetailForm.setOpernCodeInBkrPortfMgmtGrid( new String[ 0 ] );

      if ( bkrPortfMgmtDetailFncVO.isPmaItemClicked() == true )
      {
        loadBkrPortfMgmtGrid( bkrPortfMgmtDetailForm, bkrPortfMgmtDetailFncVO );
      }
    }
    else if ( bkrPortfMgmtDetailFncVO.isUpdate() == true )
    {
      if ( ( ( BaseBkrPortfMgmtDetailFncVO ) fncVO_ ).isAssociationConfirmed() == false )
      {
        TplBrokerEntity tplBrokerEntity;
        TplBrokerEntityVO tplBrokerEntityVO;

        TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity;
        TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO;

        BkrPortfMgmtMovementDetailForm bkrPortfMgmtDetailForm1 = ( BkrPortfMgmtMovementDetailForm ) form_;
        BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtDetailFncVO1 = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

        super.updateFormFromFncVO( bkrPortfMgmtDetailForm1,
                                   bkrPortfMgmtDetailFncVO1 );

        // Popula os valores dos checkboxes do grid "Consulta de Corretora".
        populateMovementBrokerGridCheckBoxes( bkrPortfMgmtDetailFncVO1,
                                              bkrPortfMgmtDetailForm1 );

        // Seta os valores dos checkboxes do grid "Corretoras Associadas a
        // Carteira Administrada".
        populateMovementBkrPortfMgmtGridCheckBoxes( bkrPortfMgmtDetailFncVO1,
                                                    bkrPortfMgmtDetailForm1 );

        loadMovementBrokerGrid( bkrPortfMgmtDetailForm1,
                                bkrPortfMgmtDetailFncVO1 );

        loadMovementBkrPortfMgmtGrid( bkrPortfMgmtDetailForm1,
                                      bkrPortfMgmtDetailFncVO1 );
      }
      else
      {
        BigInteger custNbr = ( ( BaseBkrPortfMgmtDetailFncVO ) fncVO_ ).getBaseTplBkrPortfMgmtEntity().getData().getCustNbr();
        BkrPortfMgmtMovementDetailForm bkrPortfMgmtDetailForm1 = ( BkrPortfMgmtMovementDetailForm ) form_;

        bkrPortfMgmtDetailForm1.setBkrPortfMgmtGrid( new String[ 0 ][ 0 ] );

        bkrPortfMgmtDetailForm1.setBkrCnpjNbrInBkrPortfMgmtGrid( null );

        bkrPortfMgmtDetailForm1.setInsertSelectedItemsInBkrPortfMgmtGrid( null );
        bkrPortfMgmtDetailForm1.setUpdateSelectedItemsInBkrPortfMgmtGrid( null );
        bkrPortfMgmtDetailForm1.setDeleteSelectedItemsInBkrPortfMgmtGrid( null );

        bkrPortfMgmtDetailForm1.setSelectedItemsInBkrPortfMgmtGrid( new String[ 0 ] );

        bkrPortfMgmtDetailForm1.setSelectedProdAcctCode( null );
        bkrPortfMgmtDetailForm1.setSelectedProdUnderAcctCode( null );

        bkrPortfMgmtDetailForm1.setBovespaCurAcctNbrInBkrPortfMgmtGrid( null );
        bkrPortfMgmtDetailForm1.setBovespaInvstAcctNbrInBkrPortfMgmtGrid( null );
        bkrPortfMgmtDetailForm1.setBmfCurAcctNbrInBkrPortfMgmtGrid( null );
        bkrPortfMgmtDetailForm1.setBmfInvstAcctNbrInBkrPortfMgmtGrid( null );

        ( ( BaseBkrPortfMgmtDetailFncVO ) fncVO_ ).setAssociationConfirmed( false );
      }

    }

  }

  /**
   * Atualiza as informações do FncVO com os valores vindos da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );

    BkrPortfMgmtMovementDetailForm detailForm = ( BkrPortfMgmtMovementDetailForm ) form_;
    BkrPortfMgmtMovementDetailFncVO detailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

    if ( detailForm.getIsApprove().equals( "true" ) )
    {
      detailFncVO.setApprove( true );
    }
    else if ( detailForm.getIsUpdate().equals( "true" ) )
    {
      detailFncVO.setUpdate( true );

      Map checkBoxMap = detailFncVO.getCheckBoxMap();
      ArrayList updateCheckBoxList = new ArrayList();
      ArrayList deleteCheckBoxList = new ArrayList();
      ArrayList insertCheckBoxList = new ArrayList();

      // Cria chave composta a partir de carteira admnistrada selecionada no
      // form.
      ArrayList key = composeKeyFromForm( detailForm );

      if ( detailForm.getUpdateSelectedItemsInBkrPortfMgmtGrid() != null )
      {
        // Popula as listas de check boxes.
        populateCheckBoxLists( detailForm, updateCheckBoxList,
                               deleteCheckBoxList, insertCheckBoxList );

        // Popula o mapa de check boxes.
        populateCheckBoxMap( detailForm, detailFncVO, checkBoxMap,
                             updateCheckBoxList, deleteCheckBoxList,
                             insertCheckBoxList );

        // Popula lista de entidades.
        populateBkrPortfMgmtEntityList( detailForm, detailFncVO );

      }
      // Seta chave atual no map de chaves.
      detailFncVO.setBkrPortfMgmtMapKey( key );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.bkrportfmgmt.functionality.BaseBkrPortfMgmtDetailFnc#getDAO()
   */
  protected BaseTplBkrPortfMgmtDAO getDAO()
  {
    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BkrPortfMgmtMovementDetailFncVO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    if ( !fncVO_.hasErrors() )
    {
      BkrPortfMgmtMovementDetailFncVO detailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;
      Map checkBoxMap = detailFncVO.getCheckBoxMap();
      Map bkrPortfMgmtEntityMap = detailFncVO.getBkrPortfMgmtMap();

      ArrayList checkBoxSelections = new ArrayList();
      ArrayList bkrPortfMgmtEntityList = new ArrayList();

      for ( Iterator it = checkBoxMap.keySet().iterator(); it.hasNext(); )
      {
        Object ite = it.next();
        BigInteger prodAcctCode = ( BigInteger ) ( ( ArrayList ) ite ).get( 0 );
        BigInteger prodUnderAcctCode = ( BigInteger ) ( ( ArrayList ) ite ).get( 1 );

        ArrayList key = new ArrayList();
        key.add( prodAcctCode );
        key.add( prodUnderAcctCode );

        checkBoxSelections = ( ArrayList ) checkBoxMap.get( ite );
        bkrPortfMgmtEntityList = ( ArrayList ) bkrPortfMgmtEntityMap.get( ite );

        ArrayList entities = ( ArrayList ) detailFncVO.getBkrPortfMgmtMap().get(
                                                                                 key );

        String updateAction;
        String deleteAction;
        String inserAction;
        // Insere entidades na tabela de movimento conforme código de operação.
        insertEntities( fncVO_, checkBoxSelections, bkrPortfMgmtEntityList,
                        prodAcctCode, prodUnderAcctCode, entities );
      }
      detailFncVO.setAssociationConfirmed( true );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {
    if ( !fncVO_.hasErrors() )
    {
      ArrayList updateTplBkrPortfMgmtEntityList = new ArrayList();
      ArrayList deleteTplBkrPortfMgmtEntityList = new ArrayList();
      ArrayList insertTplBkrPortfMgmtEntityList = new ArrayList();

      BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtMovementDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

      TplBkrPortfMgmtDAO tplBkrPortfMgmtDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtDAO();
      TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();
      TplBkrPortfMgmtHistDAO tplBkrPortfMgmtHistDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtHistDAO();

      ArrayList tplBkrPortfMgmtMovEntityList = bkrPortfMgmtMovementDetailFncVO.getSelectedBkrPortfMgmtEntityList();

      for ( int i = 0; i < tplBkrPortfMgmtMovEntityList.size(); i++ )
      {
        TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity = ( TplBkrPortfMgmtMovEntity ) tplBkrPortfMgmtMovEntityList.get( i );

        TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtMovEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtMovEntity.getData();

        this.validateApprove( bkrPortfMgmtMovementDetailFncVO,
                              tplBkrPortfMgmtMovEntityVO );

        String opernCode = tplBkrPortfMgmtMovEntityVO.getOpernCode();

        if ( !fncVO_.hasErrors() )
        {
          if ( opernCode.equals( C_UPDATE ) )
          {
            updateTplBkrPortfMgmtEntityList.add( tplBkrPortfMgmtMovEntity );
          }
          if ( opernCode.equals( C_DELETE ) )
          {
            deleteTplBkrPortfMgmtEntityList.add( tplBkrPortfMgmtMovEntity );
          }
          if ( opernCode.equals( C_INSERT ) )
          {
            insertTplBkrPortfMgmtEntityList.add( tplBkrPortfMgmtMovEntity );
          }

        }

      }
      for ( int j = 0; j < updateTplBkrPortfMgmtEntityList.size()
                       && !fncVO_.hasErrors(); j++ )
      {
        validateUpdate(
                        bkrPortfMgmtMovementDetailFncVO,
                        ( TplBkrPortfMgmtMovEntity ) updateTplBkrPortfMgmtEntityList.get( j ) );

        TplBkrPortfMgmtMovEntity movEntity = ( TplBkrPortfMgmtMovEntity ) updateTplBkrPortfMgmtEntityList.get( j );

        TplBkrPortfMgmtEntity currentEntity = new TplBkrPortfMgmtEntity(
                                                                         movEntity );

        currentEntity = ( TplBkrPortfMgmtEntity ) tplBkrPortfMgmtDAO.find( currentEntity );

        tplBkrPortfMgmtHistDAO.insert( new TplBkrPortfMgmtHistEntity(
                                                                      currentEntity ) );

        tplBkrPortfMgmtDAO.update( new TplBkrPortfMgmtEntity(
                                                              movEntity,
                                                              new Date(),
                                                              fncVO_.getLoggedUser().getUserID(),
                                                              TplBkrPortfMgmtMovEntity.C_REC_STAT_CODE_ACTIVE ) );

        tplBkrPortfMgmtMovDAO.delete( movEntity );
      }

      for ( int k = 0; k < insertTplBkrPortfMgmtEntityList.size()
                       && !fncVO_.hasErrors(); k++ )
      {
        validateInsert(
                        bkrPortfMgmtMovementDetailFncVO,
                        ( TplBkrPortfMgmtMovEntity ) insertTplBkrPortfMgmtEntityList.get( k ) );

        TplBkrPortfMgmtMovEntity movEntity = ( TplBkrPortfMgmtMovEntity ) insertTplBkrPortfMgmtEntityList.get( k );

        // Se houver o registro na tabela corrente com status inativo, realiza
        // atualização
        if ( tplBkrPortfMgmtDAO.existsInactive( new TplBkrPortfMgmtEntity(
                                                                           movEntity ) ) == true )
        {
          TplBkrPortfMgmtEntity currentEntity = new TplBkrPortfMgmtEntity(
                                                                           movEntity );

          currentEntity = ( TplBkrPortfMgmtEntity ) tplBkrPortfMgmtDAO.find( currentEntity );

          tplBkrPortfMgmtHistDAO.insert( new TplBkrPortfMgmtHistEntity(
                                                                        currentEntity ) );

          tplBkrPortfMgmtDAO.update( new TplBkrPortfMgmtEntity(
                                                                movEntity,
                                                                new Date(),
                                                                fncVO_.getLoggedUser().getUserID(),
                                                                TplBkrPortfMgmtMovEntity.C_REC_STAT_CODE_ACTIVE ) );

          tplBkrPortfMgmtMovDAO.delete( movEntity );
        }
        // Caso contrário, realiza inserção na tabela corrente
        else
        {
          tplBkrPortfMgmtDAO.insert( new TplBkrPortfMgmtEntity(
                                                                movEntity,
                                                                new Date(),
                                                                fncVO_.getLoggedUser().getUserID(),
                                                                TplBkrPortfMgmtMovEntity.C_REC_STAT_CODE_ACTIVE ) );
          tplBkrPortfMgmtMovDAO.delete( movEntity );
        }

      }

      for ( int l = 0; l < deleteTplBkrPortfMgmtEntityList.size()
                       && !fncVO_.hasErrors(); l++ )
      {
        validateDelete(
                        bkrPortfMgmtMovementDetailFncVO,
                        ( TplBkrPortfMgmtMovEntity ) deleteTplBkrPortfMgmtEntityList.get( l ) );
        TplBkrPortfMgmtMovEntity movEntity = ( TplBkrPortfMgmtMovEntity ) deleteTplBkrPortfMgmtEntityList.get( l );

        TplBkrPortfMgmtEntity currentEntity = new TplBkrPortfMgmtEntity(
                                                                         movEntity );

        currentEntity = ( TplBkrPortfMgmtEntity ) tplBkrPortfMgmtDAO.find( currentEntity );

        tplBkrPortfMgmtHistDAO.insert( new TplBkrPortfMgmtHistEntity(
                                                                      currentEntity ) );

        tplBkrPortfMgmtDAO.delete( new TplBkrPortfMgmtEntity( movEntity ) );
        tplBkrPortfMgmtMovDAO.delete( movEntity );
      }

      if ( !fncVO_.hasErrors() )
      {
        bkrPortfMgmtMovementDetailFncVO.setPortfolioLoaded( false );

        bkrPortfMgmtMovementDetailFncVO.setApprovalMessages( new ActionMessage(
                                                                                BaseODSFncVO.C_MESSAGE_SUCESS ) );
      }

    }
  }

  private void validateDelete( BaseFncVO fncVO_,
                              TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity )
  {
  }

  private void validateInsert( BaseFncVO fncVO_,
                              TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity )
  {
    //
  }

  private void validateUpdate( BaseFncVO fncVO_,
                              TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity )
  {
    TplBkrPortfMgmtDAO tplBkrPortfMgmtDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtDAO();
    TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = new TplBkrPortfMgmtEntity(
                                                                             tplBkrPortfMgmtMovEntity );

    if ( !tplBkrPortfMgmtDAO.existsActive( tplBkrPortfMgmtEntity ) )
    {
      fncVO_.addError( BkrPortfMgmtMovementDetailFncVO.C_ERROR_PK_NOT_FOUND );
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
      BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtMovementDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

      TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();

      ArrayList tplBkrPortfMgmtMovEntityList = bkrPortfMgmtMovementDetailFncVO.getSelectedBkrPortfMgmtEntityList();

      for ( int i = 0; i < tplBkrPortfMgmtMovEntityList.size(); i++ )
      {
        TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity = ( TplBkrPortfMgmtMovEntity ) tplBkrPortfMgmtMovEntityList.get( i );
        tplBkrPortfMgmtMovDAO.delete( tplBkrPortfMgmtMovEntity );
      }
      if ( !fncVO_.hasErrors() )
      {
        bkrPortfMgmtMovementDetailFncVO.setPortfolioLoaded( false );

        bkrPortfMgmtMovementDetailFncVO.setApprovalMessages( new ActionMessage(
                                                                                BaseODSFncVO.C_MESSAGE_SUCESS ) );
      }
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    //
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    //
  }

  public void validateApprove(
                              BaseFncVO fncVO_,
                              TplBkrPortfMgmtMovEntityVO tplbkrPortfMgmtMovEntityVO_ )
  {
    BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtMovementDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

    // Testa usuário
    if ( bkrPortfMgmtMovementDetailFncVO.getLoggedUser() == null
         || bkrPortfMgmtMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                                tplbkrPortfMgmtMovEntityVO_.getLastUpdUserId() ) )
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
    BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

    bkrPortfMgmtDetailFncVO.setApprove( false );

    bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setProdAcctCode(
                                                                                      null );
    bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setProdUnderAcctCode(
                                                                                           null );
    bkrPortfMgmtDetailFncVO.setBaseTplBkrPortfMgmtEntityArray( new ArrayList() );
    bkrPortfMgmtDetailFncVO.setBkrPortfMgmtMap( new HashMap() );

    bkrPortfMgmtDetailFncVO.setBaseTplBrokerEntityArray( new ArrayList() );
    bkrPortfMgmtDetailFncVO.setSelectedItemsInBrokerGrid( new ArrayList() );
    bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrCnpjNbr(
                                                                                    null );
    bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrNameText(
                                                                                     null );

    bkrPortfMgmtDetailFncVO.setCheckBoxMap( new HashMap() );

    this.loadCustomerFullNameText( bkrPortfMgmtDetailFncVO );

    this.loadAuthorizedPortfolio( bkrPortfMgmtDetailFncVO );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtMovementDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

    if ( bkrPortfMgmtMovementDetailFncVO.getApprovalMessages().getKey() != null
         && bkrPortfMgmtMovementDetailFncVO.getApprovalMessages().getKey().equals(
                                                                                   BaseODSFncVO.C_MESSAGE_SUCESS ) )
    {
      fncVO_.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }

    bkrPortfMgmtMovementDetailFncVO.setUpdate( false );

    bkrPortfMgmtMovementDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setProdAcctCode(
                                                                                              null );
    bkrPortfMgmtMovementDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setProdUnderAcctCode(
                                                                                                   null );
    bkrPortfMgmtMovementDetailFncVO.setPmaItemClicked( false );

    bkrPortfMgmtMovementDetailFncVO.setBaseTplBkrPortfMgmtEntityArray( new ArrayList() );

    loadCustomerFullNameText( bkrPortfMgmtMovementDetailFncVO );

    this.loadPortfolio( bkrPortfMgmtMovementDetailFncVO );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carrega o nome do cliente.
   *  
   */
  private void loadCustomerFullNameText(
                                        BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtMovementDetailFncVO_ )
  {
    if ( bkrPortfMgmtMovementDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr() != null
         && bkrPortfMgmtMovementDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               bkrPortfMgmtMovementDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      bkrPortfMgmtMovementDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().setCustFullNameText(
                                                                                                     customerPrvtEntity.getData().getCustFullNameText() );
    }
  }

  /**
   * "Carregamento dos dados de carteira administrada"
   *  
   */
  private void loadPortfolio( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtMovementDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

    TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();

    BigInteger custNbr = bkrPortfMgmtMovementDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr();
    String loggedUser = bkrPortfMgmtMovementDetailFncVO.getLoggedUser().getUserID();
    DataSet results = tplBkrPortfMgmtMovDAO.listPortfolio( custNbr, loggedUser );

    bkrPortfMgmtMovementDetailFncVO.setPortfolioResults( results );

    if ( results.size() <= 0 )
    {
      bkrPortfMgmtMovementDetailFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

    ArrayList tplBkrPortfMgmtEntityList = new ArrayList();

    // Inicializa as chaves do mapa bkrPortfMgmtMap do fncVO com os valores
    // PROD_ACCT_CODE e PROD_UNDER_ACCT_CODE de cada carteira administrada.
    for ( int i = 0; i < results.size(); i++ )
    {
      BigInteger prodAcctCode = ( results.getBigDecimal( i, C_PROD_ACCT_CODE ) ).toBigInteger();
      BigInteger prodUnderAcctCode = ( results.getBigDecimal( i,
                                                              C_PROD_UNDER_ACCT_CODE ) ).toBigInteger();

      ArrayList key = new ArrayList();
      key.add( prodAcctCode );
      key.add( prodUnderAcctCode );

      ArrayList bkrPortfMgmtList = tplBkrPortfMgmtMovDAO.listForBkrPorftMgmtGrid(
                                                                                  prodAcctCode,
                                                                                  prodUnderAcctCode );

      for ( int j = 0; j < bkrPortfMgmtList.size(); j++ )
      {
        tplBkrPortfMgmtEntityList.add( bkrPortfMgmtList.get( j ) );
      }

      bkrPortfMgmtMovementDetailFncVO.setBaseTplBkrPortfMgmtEntityArray( tplBkrPortfMgmtEntityList );

      bkrPortfMgmtMovementDetailFncVO.getBkrPortfMgmtMap().put( key,
                                                                tplBkrPortfMgmtEntityList );
    }

  }

  /**
   * Realiza consulta em lista de Corretoras Associadas a Carteira Administrada.
   *  
   */
  public void listBkrPortfMgmt( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtMovementDetailFncVO detailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;
    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtMovEntity ) detailFncVO.getBaseTplBkrPortfMgmtEntity();

    if ( detailFncVO.isUpdate() == false && detailFncVO.isApprove() == true )
    {
      detailFncVO.setPmaItemClicked( true );
    }
    else if ( detailFncVO.isUpdate() == true
              && detailFncVO.isApprove() == false )
    {

      if ( ( ( BaseBkrPortfMgmtDetailFncVO ) fncVO_ ).isAssociationConfirmed() == false )
      {
        if ( !fncVO_.hasErrors() )
        {
          BkrPortfMgmtMovementDetailFncVO detailFncVOMovementCheck = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;
          detailFncVOMovementCheck.getBaseTplBkrPortfMgmtEntity().getData().setBkrCnpjNbr(
                                                                                           "" );
          detailFncVOMovementCheck.getBaseTplBkrPortfMgmtEntity().getData().setProdAcctCode(
                                                                                             detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode() );
          detailFncVOMovementCheck.getBaseTplBkrPortfMgmtEntity().getData().setProdUnderAcctCode(
                                                                                                  detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode() );

          ArrayList key = new ArrayList();
          key.add( detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode() );
          key.add( detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode() );

          Map bkrPortfMgmtMap = detailFncVO.getBkrPortfMgmtMap();
          Map checkBoxMap = detailFncVO.getCheckBoxMap();
          ArrayList checkBoxList = new ArrayList();
          checkBoxList = ( ArrayList ) checkBoxMap.get( key );

          detailFncVO.setBaseTplBkrPortfMgmtEntityArray( ( ArrayList ) bkrPortfMgmtMap.get( key ) );

          ArrayList updateSelectedItensInBkrPortfMgmtGrid = new ArrayList();
          ArrayList deleteSelectedItensInBkrPortfMgmtGrid = new ArrayList();
          ArrayList insertSelectedItensInBkrPortfMgmtGrid = new ArrayList();

          for ( int i = 0; i < detailFncVO.getBaseTplBkrPortfMgmtEntityArray().size(); i++ )
          {

            if ( checkBoxList != null && !checkBoxList.isEmpty()
                 && !( ( ArrayList ) checkBoxList.get( 0 ) ).isEmpty() )
            {
              updateSelectedItensInBkrPortfMgmtGrid.add( ( ( ArrayList ) checkBoxList.get( 0 ) ).get( i ) );
              deleteSelectedItensInBkrPortfMgmtGrid.add( ( ( ArrayList ) checkBoxList.get( 1 ) ).get( i ) );
              insertSelectedItensInBkrPortfMgmtGrid.add( ( ( ArrayList ) checkBoxList.get( 2 ) ).get( i ) );
            }
            else
            {
              updateSelectedItensInBkrPortfMgmtGrid.add( C_UNCHECKED );
              deleteSelectedItensInBkrPortfMgmtGrid.add( C_UNCHECKED );
              insertSelectedItensInBkrPortfMgmtGrid.add( C_UNCHECKED );
            }
          }

          ArrayList checkBoxListForMap = new ArrayList();
          checkBoxListForMap.add( updateSelectedItensInBkrPortfMgmtGrid );
          checkBoxListForMap.add( deleteSelectedItensInBkrPortfMgmtGrid );
          checkBoxListForMap.add( insertSelectedItensInBkrPortfMgmtGrid );
          checkBoxMap.put( key, checkBoxListForMap );

          detailFncVO.setConfirmAssociationEnabled( true );
        }
      }
    }

  }

  /**
   * Realiza o carregamento do grid "Corretoras Associadas a Carteira
   * Administrada".
   */
  private void loadBkrPortfMgmtGrid(
                                    BkrPortfMgmtMovementDetailForm bkrPortfMgmtDetailForm,
                                    BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtDetailFncVO )
  {

    BigInteger selectedProdAcctCode = bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode();
    BigInteger selectedProdUnderAcctCode = bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode();
    ArrayList tplBkrPortfMgmtMovEntityList = new ArrayList();
    for ( int i = 0; i < bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size(); i++ )
    {
      BigInteger prodAcctCodeInEntityArray = ( ( TplBkrPortfMgmtMovEntity ) bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().get(
                                                                                                                                             i ) ).getData().getProdAcctCode();
      BigInteger prodUnderAcctCodeInEntityArray = ( ( TplBkrPortfMgmtMovEntity ) bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().get(
                                                                                                                                                  i ) ).getData().getProdUnderAcctCode();
      if ( prodAcctCodeInEntityArray.equals( selectedProdAcctCode )
           && prodUnderAcctCodeInEntityArray.equals( selectedProdUnderAcctCode ) )
      {
        tplBkrPortfMgmtMovEntityList.add( ( TplBkrPortfMgmtMovEntity ) bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().get(
                                                                                                                                        i ) );

      }
    }

    bkrPortfMgmtDetailFncVO.setSelectedBkrPortfMgmtEntityList( tplBkrPortfMgmtMovEntityList );

    String[] bkrCnpjNbrList = new String[ tplBkrPortfMgmtMovEntityList.size() ];
    String[] bkrNameTextList = new String[ tplBkrPortfMgmtMovEntityList.size() ];
    String[] bovespaCurAcctNbrList = new String[ tplBkrPortfMgmtMovEntityList.size() ];
    String[] bovespaInvstAcctNbrList = new String[ tplBkrPortfMgmtMovEntityList.size() ];
    String[] bmfCurAcctNbrList = new String[ tplBkrPortfMgmtMovEntityList.size() ];
    String[] bmfInvstAcctNbrList = new String[ tplBkrPortfMgmtMovEntityList.size() ];
    String[] opernCodeList = new String[ tplBkrPortfMgmtMovEntityList.size() ];

    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity;
    TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtEntityVO;

    String[] bkrPortfMgmtGrid = new String[ tplBkrPortfMgmtMovEntityList.size() ];

    int indexLinha = 0;

    for ( int i = 0; i < tplBkrPortfMgmtMovEntityList.size(); i++ )
    {
      tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtMovEntity ) tplBkrPortfMgmtMovEntityList.get( i );
      tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtEntity.getData();

      if ( tplBkrPortfMgmtEntityVO.getBkrCnpjNbr() != null )
      {
        bkrCnpjNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBkrCnpjNbr().toString() );
      }
      if ( tplBkrPortfMgmtEntityVO.getBkrNameText() != null )
      {
        bkrNameTextList[ i ] = ( tplBkrPortfMgmtEntityVO.getBkrNameText().toString() );
      }
      if ( tplBkrPortfMgmtEntityVO.getBovespaCurAcctNbr() != null )
      {
        bovespaCurAcctNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBovespaCurAcctNbr().toString() );
      }
      if ( tplBkrPortfMgmtEntityVO.getBovespaInvstAcctNbr() != null )
      {
        bovespaInvstAcctNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBovespaInvstAcctNbr().toString() );
      }
      if ( tplBkrPortfMgmtEntityVO.getBmfCurAcctNbr() != null )
      {
        bmfCurAcctNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBmfCurAcctNbr().toString() );
      }
      if ( tplBkrPortfMgmtEntityVO.getBmfInvstAcctNbr() != null )
      {
        bmfInvstAcctNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBmfInvstAcctNbr().toString() );
      }
      if ( tplBkrPortfMgmtEntityVO.getOpernCode() != null )
      {
        opernCodeList[ i ] = ( tplBkrPortfMgmtEntityVO.getOpernCode().toString() );
      }
    }

    bkrPortfMgmtDetailForm.setBkrCnpjNbrInBkrPortfMgmtGrid( bkrCnpjNbrList );
    bkrPortfMgmtDetailForm.setBkrNameTextInBkrPortfMgmtGrid( bkrNameTextList );
    bkrPortfMgmtDetailForm.setBovespaCurAcctNbrInBkrPortfMgmtGrid( bovespaCurAcctNbrList );
    bkrPortfMgmtDetailForm.setBovespaInvstAcctNbrInBkrPortfMgmtGrid( bovespaInvstAcctNbrList );
    bkrPortfMgmtDetailForm.setBmfCurAcctNbrInBkrPortfMgmtGrid( bmfCurAcctNbrList );
    bkrPortfMgmtDetailForm.setBmfInvstAcctNbrInBkrPortfMgmtGrid( bmfInvstAcctNbrList );
    bkrPortfMgmtDetailForm.setOpernCodeInBkrPortfMgmtGrid( opernCodeList );

  }

  /**
   * "Cria uma chave composta a partir da carteira administrada selecionada no
   * form. A chave é baseada nos valores das variáveis m_prodAccCode e
   * m_prodUnderAcctCode.
   */
  private ArrayList composeKeyFromForm( BaseBkrPortfMgmtDetailForm detailForm )
  {
    ArrayList key = new ArrayList();
    if ( detailForm.getSelectedProdAcctCode() != null
         && !detailForm.getSelectedProdAcctCode().equals( "" )
         && detailForm.getSelectedProdUnderAcctCode() != null
         && !detailForm.getSelectedProdUnderAcctCode().equals( "" ) )
    {
      BigInteger prodAcctCode = new BigInteger(
                                                detailForm.getSelectedProdAcctCode() );
      BigInteger prodUnderAcctCode = new BigInteger(
                                                     detailForm.getSelectedProdUnderAcctCode() );

      key.add( prodAcctCode );
      key.add( prodUnderAcctCode );
    }
    return key;
  }

  /**
   * "Popula lista de entidades conforme dados do grid "Corretoras Associadas a
   * Carteira Administrada"
   *  
   */
  private void populateBkrPortfMgmtEntityList(
                                              BaseBkrPortfMgmtDetailForm detailForm_,
                                              BaseBkrPortfMgmtDetailFncVO detailFncVO_ )
  {

    BaseBkrPortfMgmtDetailForm detailForm = ( BaseBkrPortfMgmtDetailForm ) detailForm_;
    BaseBkrPortfMgmtDetailFncVO detailFncVO = ( BaseBkrPortfMgmtDetailFncVO ) detailFncVO_;
    ArrayList tplBkrPortfMgmtEntityList = detailFncVO.getBaseTplBkrPortfMgmtEntityArray();

    if ( !tplBkrPortfMgmtEntityList.isEmpty() )
    {
      for ( int i = 0; i < detailForm.getBovespaCurAcctNbrInBkrPortfMgmtGrid().length; i++ )
      {
        String bovespaCurAcctAcctNbr = detailForm.getBovespaCurAcctNbrInBkrPortfMgmtGrid()[ i ];
        String bovespaInvstAcctNbr = detailForm.getBovespaInvstAcctNbrInBkrPortfMgmtGrid()[ i ];
        String bmfCurAcctAcctNbr = detailForm.getBmfCurAcctNbrInBkrPortfMgmtGrid()[ i ];
        String bmfInvstAcctNbr = detailForm.getBmfInvstAcctNbrInBkrPortfMgmtGrid()[ i ];
        TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtMovEntity ) tplBkrPortfMgmtEntityList.get( i );
        TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtEntity.getData();
        tplBkrPortfMgmtEntityVO.setBovespaCurAcctNbr( bovespaCurAcctAcctNbr );
        tplBkrPortfMgmtEntityVO.setBovespaInvstAcctNbr( bovespaInvstAcctNbr );
        tplBkrPortfMgmtEntityVO.setBmfCurAcctNbr( bmfCurAcctAcctNbr );
        tplBkrPortfMgmtEntityVO.setBmfInvstAcctNbr( bmfInvstAcctNbr );
      }
    }

  }

  /**
   * "Popula as listas de check boxes."
   */
  private void populateCheckBoxLists( BaseBkrPortfMgmtDetailForm detailForm,
                                     ArrayList updateCheckBoxList,
                                     ArrayList deleteCheckBoxList,
                                     ArrayList insertCheckBoxList )
  {
    for ( int i = 0; i < detailForm.getUpdateSelectedItemsInBkrPortfMgmtGrid().length; i++ )
    {
      updateCheckBoxList.add( detailForm.getUpdateSelectedItemsInBkrPortfMgmtGrid()[ i ] );
    }

    for ( int i = 0; i < detailForm.getDeleteSelectedItemsInBkrPortfMgmtGrid().length; i++ )
    {
      deleteCheckBoxList.add( detailForm.getDeleteSelectedItemsInBkrPortfMgmtGrid()[ i ] );
    }

    for ( int i = 0; i < detailForm.getInsertSelectedItemsInBkrPortfMgmtGrid().length; i++ )
    {
      insertCheckBoxList.add( detailForm.getInsertSelectedItemsInBkrPortfMgmtGrid()[ i ] );
    }
  }

  /**
   * "Popula o mapa de check boxes. A chave do mapa é composta pela carteira
   * administrada selecionada (prodAcctCode e prodUnderAcctCode) e os valores
   * são a lista de itens para atualização (updateCheckBoxList) na posição 0, a
   * lista de itens para remoção (deleteCheckBoxList) na posição 1 e a lista de
   * itens para inserção (insertCheckBoxList) na posição 2."
   *  
   */
  private void populateCheckBoxMap( BaseBkrPortfMgmtDetailForm detailForm,
                                   BaseBkrPortfMgmtDetailFncVO detailFncVO,
                                   Map checkBoxMap,
                                   ArrayList updateCheckBoxList,
                                   ArrayList deleteCheckBoxList,
                                   ArrayList insertCheckBoxList )
  {
    ArrayList previousKey1 = new ArrayList();
    if ( detailFncVO.getBkrPortfMgmtMapKey() != null
         && !detailFncVO.getBkrPortfMgmtMapKey().isEmpty()
         && detailFncVO.getBkrPortfMgmtMapKey().get( 0 ) != null
         && detailFncVO.getBkrPortfMgmtMapKey().get( 1 ) != null )
    {
      previousKey1.add( detailFncVO.getBkrPortfMgmtMapKey().get( 0 ) );
      previousKey1.add( detailFncVO.getBkrPortfMgmtMapKey().get( 1 ) );
    }
    ArrayList formKey1 = new ArrayList();
    formKey1.add( detailForm.getSelectedProdAcctCode() );
    formKey1.add( detailForm.getSelectedProdUnderAcctCode() );

    if ( ( formKey1.get( 0 ) != null && formKey1.get( 1 ) != null
           && !previousKey1.isEmpty() && previousKey1.get( 0 ) != null && previousKey1.get( 1 ) != null )
         || detailForm.getInsertBtnPressed().equals( "true" ) )
    {
      ArrayList checkMapList = new ArrayList();
      checkMapList.add( updateCheckBoxList );
      checkMapList.add( deleteCheckBoxList );

      if ( detailFncVO.getCheckBoxMap().get( previousKey1 ) != null )
      {
        ArrayList checkBoxList = ( ArrayList ) detailFncVO.getCheckBoxMap().get(
                                                                                 previousKey1 );
        ArrayList insertCheck = ( ArrayList ) checkBoxList.get( 2 );
        checkMapList.add( insertCheck );
      }
      else
      {
        checkMapList.add( insertCheckBoxList );
      }
      checkBoxMap.put( detailFncVO.getBkrPortfMgmtMapKey(), checkMapList );
    }
  }

  /**
   * "Carregamento dos dados de carteira administrada às quais o usuário logado
   * possui acesso para alteração."
   *  
   */
  private void loadAuthorizedPortfolio( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtMovementDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

    TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();

    BigInteger custNbr = bkrPortfMgmtMovementDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr();
    String loggedUser = bkrPortfMgmtMovementDetailFncVO.getLoggedUser().getUserID();
    DataSet results = tplBkrPortfMgmtMovDAO.listAuthorizedPortfolio( custNbr,
                                                                     loggedUser );

    bkrPortfMgmtMovementDetailFncVO.setPortfolioResults( results );

    if ( results.size() <= 0 )
    {
      bkrPortfMgmtMovementDetailFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

    // Inicializa as chaves do mapa bkrPortfMgmtMap do fncVO com os valores
    // PROD_ACCT_CODE e PROD_UNDER_ACCT_CODE de cada carteira administrada.
    for ( int i = 0; i < results.size(); i++ )
    {
      BigInteger prodAcctCode = ( results.getBigDecimal( i, C_PROD_ACCT_CODE ) ).toBigInteger();
      BigInteger prodUnderAcctCode = ( results.getBigDecimal( i,
                                                              C_PROD_UNDER_ACCT_CODE ) ).toBigInteger();

      ArrayList key = new ArrayList();
      key.add( prodAcctCode );
      key.add( prodUnderAcctCode );

      ArrayList bkrPortfMgmtList = tplBkrPortfMgmtMovDAO.listForBkrPorftMgmtGrid(
                                                                                  prodAcctCode,
                                                                                  prodUnderAcctCode );
      ArrayList tplBkrPortfMgmtEntityList = new ArrayList();
      for ( int j = 0; j < bkrPortfMgmtList.size(); j++ )
      {
        tplBkrPortfMgmtEntityList.add( bkrPortfMgmtList.get( j ) );
      }
      bkrPortfMgmtMovementDetailFncVO.getBkrPortfMgmtMap().put( key,
                                                                tplBkrPortfMgmtEntityList );

      bkrPortfMgmtMovementDetailFncVO.setBaseTplBkrPortfMgmtEntityArray( tplBkrPortfMgmtEntityList );
    }

  }

  /**
   * "Popula os valores dos checkboxes do grid "Consulta de Corretora".
   */
  private void populateMovementBrokerGridCheckBoxes(
                                                    BaseBkrPortfMgmtDetailFncVO baseBkrPortfMgmtDetailFncVO,
                                                    BaseBkrPortfMgmtDetailForm baseBkrPortfMgmtDetailForm )
  {
    String[] itemsSelectedInForm = new String[ baseBkrPortfMgmtDetailFncVO.getSelectedItemsInBrokerGrid().size() ];

    if ( baseBkrPortfMgmtDetailFncVO.getSelectedItemsInBrokerGrid() != null
         && !baseBkrPortfMgmtDetailFncVO.getSelectedItemsInBrokerGrid().isEmpty() )
    {
      for ( int i = 0; i < baseBkrPortfMgmtDetailFncVO.getSelectedItemsInBrokerGrid().size(); i++ )
      {
        itemsSelectedInForm[ i ] = ( String ) baseBkrPortfMgmtDetailFncVO.getSelectedItemsInBrokerGrid().get(
                                                                                                              i );
      }
    }
    baseBkrPortfMgmtDetailForm.setSelectedItemsInBrokerGrid( itemsSelectedInForm );
  }

  /**
   * "Seta os valores dos checkboxes do grid "Corretoras Associadas a Carteira
   * Administrada"."
   *  
   */
  private void populateMovementBkrPortfMgmtGridCheckBoxes(
                                                          BaseBkrPortfMgmtDetailFncVO baseBkrPortfMgmtDetailFncVO,
                                                          BaseBkrPortfMgmtDetailForm baseBkrPortfMgmtDetailForm )
  {
    Map checkBoxMap = baseBkrPortfMgmtDetailFncVO.getCheckBoxMap();
    ArrayList checkBoxList = new ArrayList();
    ArrayList key = new ArrayList();
    key.add( baseBkrPortfMgmtDetailForm.getSelectedProdAcctCode() );
    key.add( baseBkrPortfMgmtDetailForm.getSelectedProdUnderAcctCode() );

    ArrayList checkBoxValue = ( ArrayList ) baseBkrPortfMgmtDetailFncVO.getCheckBoxMap().get(
                                                                                              baseBkrPortfMgmtDetailFncVO.getBkrPortfMgmtMapKey() );////////////

    if ( baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray() != null )

    {
      String[] updateItemsSelectedInForm = new String[ baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];
      String[] deleteItemsSelectedInForm = new String[ baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];
      String[] insertItemsSelectedInForm = new String[ baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];

      for ( int i = 0; i < baseBkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size(); i++ )
      {
        if ( checkBoxValue != null )
        {
          updateItemsSelectedInForm[ i ] = ( String ) ( ( ArrayList ) checkBoxValue.get( C_UPDATE_INDEX ) ).get( i );
          deleteItemsSelectedInForm[ i ] = ( String ) ( ( ArrayList ) checkBoxValue.get( C_DELETE_INDEX ) ).get( i );
          insertItemsSelectedInForm[ i ] = ( String ) ( ( ArrayList ) checkBoxValue.get( C_INSERT_INDEX ) ).get( i );
        }
        else
        {
          updateItemsSelectedInForm[ i ] = C_UNCHECKED;
          deleteItemsSelectedInForm[ i ] = C_UNCHECKED;
          insertItemsSelectedInForm[ i ] = C_UNCHECKED;
        }
      }
      baseBkrPortfMgmtDetailForm.setUpdateSelectedItemsInBkrPortfMgmtGrid( updateItemsSelectedInForm );
      baseBkrPortfMgmtDetailForm.setDeleteSelectedItemsInBkrPortfMgmtGrid( deleteItemsSelectedInForm );
      baseBkrPortfMgmtDetailForm.setInsertSelectedItemsInBkrPortfMgmtGrid( insertItemsSelectedInForm );
    }

  }

  /**
   * Realiza o carregamento do grid "Consulta de Corretora".
   */
  private void loadMovementBrokerGrid(
                                      BkrPortfMgmtMovementDetailForm bkrPortfMgmtDetailForm,
                                      BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtDetailFncVO )
  {
    TplBrokerEntity tplBrokerEntity;
    TplBrokerEntityVO tplBrokerEntityVO;
    String[][] brokerGrid = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBrokerEntityArray().size() ][ 3 ];
    int indexLinha = 0;
    for ( int i = 0; i < bkrPortfMgmtDetailFncVO.getBaseTplBrokerEntityArray().size(); i++ )
    {
      tplBrokerEntity = ( TplBrokerEntity ) bkrPortfMgmtDetailFncVO.getBaseTplBrokerEntityArray().get(
                                                                                                       i );
      tplBrokerEntityVO = ( TplBrokerEntityVO ) tplBrokerEntity.getData();

      String strBkrCnpjNbr = "";
      if ( tplBrokerEntityVO.getBkrCnpjNbr() != null )
      {
        strBkrCnpjNbr = tplBrokerEntityVO.getBkrCnpjNbr().toString();
      }
      brokerGrid[ i ][ BkrPortfMgmtMovementDetailForm.COL_POS_BKR_CNPJ_NBR ] = strBkrCnpjNbr;

      String strBkrNameText = "";
      if ( tplBrokerEntityVO.getBkrNameText() != null )
      {
        strBkrNameText = tplBrokerEntityVO.getBkrNameText().toString();
      }
      brokerGrid[ i ][ BkrPortfMgmtMovementDetailForm.COL_POS_BKR_NAME_TEXT ] = strBkrNameText;

      String strBkrAddrText = "";
      if ( tplBrokerEntityVO.getBkrAddrText() != null )
      {
        strBkrAddrText = tplBrokerEntityVO.getBkrAddrText().toString();
      }
      brokerGrid[ i ][ BkrPortfMgmtMovementDetailForm.COL_POS_BKR_ADDR_TEXT ] = strBkrAddrText;

    }

    bkrPortfMgmtDetailForm.setBrokerGrid( brokerGrid );
  }

  /**
   * Realiza o carregamento do grid "Corretoras Associadas a Carteira
   * Administrada".
   */
  private void loadMovementBkrPortfMgmtGrid(
                                            BkrPortfMgmtMovementDetailForm bkrPortfMgmtDetailForm,
                                            BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtDetailFncVO )
  {

    String[] bkrCnpjNbrList = new String[ 0 ];
    String[] bkrNameTextList = new String[ 0 ];
    String[] bovespaCurAcctNbrList = new String[ 0 ];
    String[] bovespaInvstAcctNbrList = new String[ 0 ];
    String[] bmfCurAcctNbrList = new String[ 0 ];
    String[] bmfInvstAcctNbrList = new String[ 0 ];
    String[] opernCodeList = new String[ 0 ];

    if ( bkrPortfMgmtDetailFncVO.getBkrPortfMgmtMap() != null
         && bkrPortfMgmtDetailForm.getSelectedProdAcctCode() != null
         && !bkrPortfMgmtDetailForm.getSelectedProdAcctCode().equals( "" )
         && bkrPortfMgmtDetailForm.getSelectedProdUnderAcctCode() != null
         && !bkrPortfMgmtDetailForm.getSelectedProdUnderAcctCode().equals( "" ) )
    {
      Map bkrPortfMgmtMap = bkrPortfMgmtDetailFncVO.getBkrPortfMgmtMap();
      ArrayList key = new ArrayList();
      key.add( new BigInteger( bkrPortfMgmtDetailForm.getSelectedProdAcctCode() ) );
      key.add( new BigInteger(
                               bkrPortfMgmtDetailForm.getSelectedProdUnderAcctCode() ) );
      ArrayList tplBkrPortfMgmtEntityList = ( ArrayList ) bkrPortfMgmtMap.get( key );

      bkrCnpjNbrList = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];
      bkrNameTextList = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];
      bovespaCurAcctNbrList = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];
      bovespaInvstAcctNbrList = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];
      bmfCurAcctNbrList = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];
      bmfInvstAcctNbrList = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];
      opernCodeList = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];

      TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity;
      TplBkrPortfMgmtMovEntityVO tplBkrPortfMgmtEntityVO;

      String[] bkrPortfMgmtGrid = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];

      int indexLinha = 0;

      for ( int i = 0; i < bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size(); i++ )
      {
        tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtMovEntity ) tplBkrPortfMgmtEntityList.get( i );
        tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtEntity.getData();

        if ( tplBkrPortfMgmtEntityVO.getBkrCnpjNbr() != null )
        {
          bkrCnpjNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBkrCnpjNbr().toString() );
        }
        if ( tplBkrPortfMgmtEntityVO.getBkrNameText() != null )
        {
          bkrNameTextList[ i ] = ( tplBkrPortfMgmtEntityVO.getBkrNameText().toString() );
        }
        if ( tplBkrPortfMgmtEntityVO.getBovespaCurAcctNbr() != null )
        {
          bovespaCurAcctNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBovespaCurAcctNbr() );
        }
        if ( tplBkrPortfMgmtEntityVO.getBovespaInvstAcctNbr() != null )
        {
          bovespaInvstAcctNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBovespaInvstAcctNbr().toString() );
        }
        if ( tplBkrPortfMgmtEntityVO.getBmfCurAcctNbr() != null )
        {
          bmfCurAcctNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBmfCurAcctNbr().toString() );
        }
        if ( tplBkrPortfMgmtEntityVO.getBmfInvstAcctNbr() != null )
        {
          bmfInvstAcctNbrList[ i ] = ( tplBkrPortfMgmtEntityVO.getBmfInvstAcctNbr().toString() );
        }
        if ( tplBkrPortfMgmtEntityVO.getOpernCode() != null )
        {
          opernCodeList[ i ] = ( tplBkrPortfMgmtEntityVO.getOpernCode().toString() );
        }
      }
    }
    bkrPortfMgmtDetailForm.setBkrCnpjNbrInBkrPortfMgmtGrid( bkrCnpjNbrList );
    bkrPortfMgmtDetailForm.setBkrNameTextInBkrPortfMgmtGrid( bkrNameTextList );
    bkrPortfMgmtDetailForm.setBovespaCurAcctNbrInBkrPortfMgmtGrid( bovespaCurAcctNbrList );
    bkrPortfMgmtDetailForm.setBovespaInvstAcctNbrInBkrPortfMgmtGrid( bovespaInvstAcctNbrList );
    bkrPortfMgmtDetailForm.setBmfCurAcctNbrInBkrPortfMgmtGrid( bmfCurAcctNbrList );
    bkrPortfMgmtDetailForm.setBmfInvstAcctNbrInBkrPortfMgmtGrid( bmfInvstAcctNbrList );
    bkrPortfMgmtDetailForm.setOpernCodeInBkrPortfMgmtGrid( opernCodeList );
  }

  /**
   * "Insere entidades na tabela de movimento conforme código de operação"
   */
  private void insertEntities( BaseFncVO fncVO_, ArrayList checkBoxSelections,
                              ArrayList bkrPortfMgmtEntityList,
                              BigInteger prodAcctCode,
                              BigInteger prodUnderAcctCode, ArrayList entities )
  {
    for ( int i = 0; i < entities.size(); i++ )
    {
      String updateAction = ( ( ArrayList ) checkBoxSelections.get( C_UPDATE_INDEX ) ).get(
                                                                                            i ).toString();
      String deleteAction = ( ( ArrayList ) checkBoxSelections.get( C_DELETE_INDEX ) ).get(
                                                                                            i ).toString();
      String inserAction = ( ( ArrayList ) checkBoxSelections.get( C_INSERT_INDEX ) ).get(
                                                                                           i ).toString();
      if ( updateAction.equals( C_CHECKED )
           && deleteAction.equals( C_UNCHECKED )
           && inserAction.equals( C_UNCHECKED ) )
      {
        insertBkrPortfMgmtMovEntity(
                                     fncVO_,
                                     bkrPortfMgmtEntityList,
                                     prodAcctCode,
                                     prodUnderAcctCode,
                                     i,
                                     TplBkrPortfMgmtMovEntity.C_OPERN_CODE_UPDATE );

      }
      else if ( updateAction.equals( C_UNCHECKED )
                && deleteAction.equals( C_CHECKED )
                && inserAction.equals( C_UNCHECKED ) )
      {
        removeBkrPortfMgmtMovEntity(
                                     fncVO_,
                                     bkrPortfMgmtEntityList,
                                     prodAcctCode,
                                     prodUnderAcctCode,
                                     i,
                                     TplBkrPortfMgmtMovEntity.C_OPERN_CODE_DELETE );
      }
      else if ( updateAction.equals( C_CHECKED )
                && deleteAction.equals( C_UNCHECKED )
                && inserAction.equals( C_CHECKED ) )
      {
        insertBkrPortfMgmtMovEntity(
                                     fncVO_,
                                     bkrPortfMgmtEntityList,
                                     prodAcctCode,
                                     prodUnderAcctCode,
                                     i,
                                     TplBkrPortfMgmtMovEntity.C_OPERN_CODE_INSERT );

      }
    }
  }

  /**
   * "Insere uma entidade de movimento na tabela de acordo com o código de
   * operação."
   */
  private void insertBkrPortfMgmtMovEntity( BaseFncVO fncVO_,
                                           ArrayList bkrPortfMgmtEntityList,
                                           BigInteger prodAcctCode,
                                           BigInteger prodUnderAcctCode, int i,
                                           String opernCode )
  {
    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity = ( ( TplBkrPortfMgmtMovEntity ) bkrPortfMgmtEntityList.get( i ) );
    tplBkrPortfMgmtEntity.getData().setLastUpdDate( new Date() );
    tplBkrPortfMgmtEntity.getData().setLastUpdUserId(
                                                      fncVO_.getLoggedUser() != null
                                                                                    ? fncVO_.getLoggedUser().getUserID()
                                                                                    : "" );
    tplBkrPortfMgmtEntity.getData().setProdAcctCode( prodAcctCode );
    tplBkrPortfMgmtEntity.getData().setProdUnderAcctCode( prodUnderAcctCode );

    ( ( TplBkrPortfMgmtMovEntityVO ) tplBkrPortfMgmtEntity.getData() ).setOpernCode( opernCode );

    TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();

    tplBkrPortfMgmtMovDAO.update( tplBkrPortfMgmtEntity );
  }

  /**
   * "Remove uma entidade de movimento
   */
  private void removeBkrPortfMgmtMovEntity( BaseFncVO fncVO_,
                                           ArrayList bkrPortfMgmtEntityList,
                                           BigInteger prodAcctCode,
                                           BigInteger prodUnderAcctCode, int i,
                                           String opernCode )
  {
    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity = ( ( TplBkrPortfMgmtMovEntity ) bkrPortfMgmtEntityList.get( i ) );
    TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();
    tplBkrPortfMgmtMovDAO.delete( tplBkrPortfMgmtEntity );

  }

  /**
   * Realiza consulta em lista de corretoras.
   *  
   */
  public void listBroker( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtMovementDetailFncVO detailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;
    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtMovEntity ) detailFncVO.getBaseTplBkrPortfMgmtEntity();

    ArrayList brokerList = new ArrayList();
    String bkrCnpj = "";
    String bkrNameText = "";

    if ( tplBkrPortfMgmtEntity.getData().getBkrCnpjNbr() != null )
    {
      bkrCnpj = tplBkrPortfMgmtEntity.getData().getBkrCnpjNbr().toString();
    }

    if ( tplBkrPortfMgmtEntity.getData().getBkrNameText() != null )
    {
      bkrNameText = tplBkrPortfMgmtEntity.getData().getBkrNameText().toString();
    }

    TplBrokerDAO tplBrokerDAO = OracleODSDAOFactory.getInstance().getTplBrokerDAO();

    brokerList = tplBrokerDAO.listForBrokerGrid( bkrCnpj, bkrNameText );

    if ( !brokerList.isEmpty() )
    {
      detailFncVO.clearMessages();
      detailFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
    }
    else
    {
      detailFncVO.clearErrors();
      detailFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

    detailFncVO.setBaseTplBrokerEntityArray( brokerList );

    detailFncVO.setSelectedItemsInBrokerGrid( null );
    ArrayList selectedItensInBrokerGrid = new ArrayList();
    for ( int i = 0; i < brokerList.size(); i++ )
    {
      selectedItensInBrokerGrid.add( C_UNCHECKED );
    }
    detailFncVO.setSelectedItemsInBrokerGrid( selectedItensInBrokerGrid );

  }

  /**
   * Insere as corretoras selecionadas no grid de Corretoras no grid de
   * Corretoras Associadas a Carteira Administrada.
   *  
   */
  public void addSelectedBrokers( BaseFncVO fncVO_ )
  {

    BkrPortfMgmtMovementDetailFncVO detailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;
    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtMovEntity ) detailFncVO.getBaseTplBkrPortfMgmtEntity();

    detailFncVO.clearErrors();

    if ( hasSelectedItemsInBrokerGrid( detailFncVO.getSelectedItemsInBrokerGrid() ) == true )
    {
      for ( int i = 0; i < detailFncVO.getBaseTplBrokerEntityArray().size(); i++ )
      {
        if ( detailFncVO.getSelectedItemsInBrokerGrid().get( i ).equals(
                                                                         C_CHECKED ) )
        {
          TplBkrPortfMgmtMovEntity selectedTplBkrPortfMgmtEntity = new TplBkrPortfMgmtMovEntity(
                                                                                                 ( TplBrokerEntity ) detailFncVO.getBaseTplBrokerEntityArray().get(
                                                                                                                                                                    i ),
                                                                                                 detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr(),
                                                                                                 detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getBkrCustNbr(),
                                                                                                 detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode(),
                                                                                                 detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode() );
          populateCheckBoxMap( fncVO_, detailFncVO,
                               selectedTplBkrPortfMgmtEntity );
        }
      }
    }
    else
    {
      detailFncVO.addMessage( BaseODSFncVO.C_ERROR_NO_REGISTER_FOUND_IN_BROKER_GRID );
    }

  }

  /**
   * "Verifica se existem corretoras selecionadas no grid "Consulta de
   * Corretoras"."
   *  
   */
  private boolean hasSelectedItemsInBrokerGrid(
                                               ArrayList selectedItemsInBrokerGrid_ )
  {
    for ( int j = 0; j < selectedItemsInBrokerGrid_.size(); j++ )
    {
      if ( selectedItemsInBrokerGrid_.get( j ).equals( C_CHECKED ) )
      {
        return true;
      }
    }
    return false;
  }

  /**
   * "Popula o mapa de check boxes com os dados inseridos a partir das
   * corretoras inseridas a partir do grid "Consulta de Corretoras"."
   *  
   */
  private void populateCheckBoxMap(
                                   BaseFncVO fncVO_,
                                   BkrPortfMgmtMovementDetailFncVO detailFncVO,
                                   TplBkrPortfMgmtMovEntity selectedTplBkrPortfMgmtEntity )
  {
    if ( !existsInBkrPortfMgmtGrid( fncVO_, detailFncVO,
                                    selectedTplBkrPortfMgmtEntity ) )
    {
      ArrayList key = new ArrayList();
      key = detailFncVO.getBkrPortfMgmtMapKey();

      ArrayList tplBkrPortfMgmtEntityList = ( ArrayList ) detailFncVO.getBkrPortfMgmtMap().get(
                                                                                                key );
      tplBkrPortfMgmtEntityList.add( selectedTplBkrPortfMgmtEntity );
      detailFncVO.getBkrPortfMgmtMap().put( key, tplBkrPortfMgmtEntityList );

      ArrayList checkBoxList = ( ArrayList ) detailFncVO.getCheckBoxMap().get(
                                                                               key );

      ( ( ArrayList ) checkBoxList.get( C_UPDATE_INDEX ) ).add( C_CHECKED );
      ( ( ArrayList ) checkBoxList.get( C_DELETE_INDEX ) ).add( C_UNCHECKED );
      ( ( ArrayList ) checkBoxList.get( C_INSERT_INDEX ) ).add( C_CHECKED );

      detailFncVO.getCheckBoxMap().put( key, checkBoxList );
    }
  }

  /**
   * "Verifica se a corretora inserida a partir do grid "Consulta de Corretoras"
   * já existe no grid "Corretoras Associadas a Carteira Administrada"."
   *  
   */
  private boolean existsInBkrPortfMgmtGrid(
                                           BaseFncVO fncVO_,
                                           BkrPortfMgmtMovementDetailFncVO detailFncVO,
                                           TplBkrPortfMgmtMovEntity selectedTplBkrPortfMgmtEntity )
  {

    boolean existInBkrPortfMgmt = false;

    for ( int j = 0; j < detailFncVO.getBaseTplBkrPortfMgmtEntityArray().size(); j++ )
    {

      TplBkrPortfMgmtMovEntity tplBkrPortfMgmtEntityInBkrPortfMgmtGrid = ( TplBkrPortfMgmtMovEntity ) detailFncVO.getBaseTplBkrPortfMgmtEntityArray().get(
                                                                                                                                                           j );
      if ( selectedTplBkrPortfMgmtEntity.getData().getBkrCnpjNbr().equals(
                                                                           tplBkrPortfMgmtEntityInBkrPortfMgmtGrid.getData().getBkrCnpjNbr() ) )
      {
        fncVO_.addError(
                         BkrPortfMgmtDetailFncVO.C_ERROR_DUPLICATE_BROKER,
                         selectedTplBkrPortfMgmtEntity.getData().getBkrCnpjNbr().toString() );

        existInBkrPortfMgmt = true;

        break;
      }
    }
    return existInBkrPortfMgmt;
  }

  /**
   * Limpa os campos referentes à consulta de corretora.
   *  
   */
  public void clearPage( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtMovementDetailFncVO bkrPortfMgmtDetailFncVO = ( BkrPortfMgmtMovementDetailFncVO ) fncVO_;

    bkrPortfMgmtDetailFncVO.setSelectedItemsInBrokerGrid( new ArrayList() );
    bkrPortfMgmtDetailFncVO.setBaseTplBrokerEntityArray( new ArrayList() );
    bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrCnpjNbr(
                                                                                    null );
    bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrNameText(
                                                                                     null );
  }

}