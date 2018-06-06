package com.citibank.ods.modules.client.bkrportfmgmt.functionality;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtEntity;
import com.citibank.ods.entity.pl.TplBkrPortfMgmtMovEntity;
import com.citibank.ods.entity.pl.TplBrokerEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplBkrPortfMgmtEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplBrokerEntityVO;
import com.citibank.ods.modules.client.bkrportfmgmt.form.BaseBkrPortfMgmtDetailForm;
import com.citibank.ods.modules.client.bkrportfmgmt.form.BkrPortfMgmtDetailForm;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BaseBkrPortfMgmtDetailFncVO;
import com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject.BkrPortfMgmtDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplBkrPortfMgmtDAO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtDAO;
import com.citibank.ods.persistence.pl.dao.TplBkrPortfMgmtMovDAO;
import com.citibank.ods.persistence.pl.dao.TplBrokerDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author Hamilton Matos
 *  
 */

public class BkrPortfMgmtDetailFnc extends BaseBkrPortfMgmtDetailFnc implements
    ODSDetailFnc
{

  // Variáveis utilizadas na manipulação do mapa de checkboxes
  // Índice 0: Lista de ítens marcados para atualização.
  private static final int C_UPDATE_INDEX = 0;

  // Índice 1: Lista de ítens marcados para remoção
  private static final int C_DELETE_INDEX = 1;

  // Índice 2: Lista de ítens marcados para inserção
  private static final int C_INSERT_INDEX = 2;

  // Variáveis utilizadas na manipulação do mapa de check boxes
  // Checkbox marcado
  private static final String C_CHECKED = "S";

  // Checkbox não marcado
  private static final String C_UNCHECKED = "N";

  // Código da Conta Produto
  private static final String C_PROD_ACCT_CODE = "PROD_ACCT_CODE";

  // Código da sub conta produto
  private static final String C_PROD_UNDER_ACCT_CODE = "PROD_UNDER_ACCT_CODE";

  /**
   * Atualiza as informações do FncVO com os valores vindos da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );
    BaseBkrPortfMgmtDetailForm detailForm = ( BaseBkrPortfMgmtDetailForm ) form_;
    BaseBkrPortfMgmtDetailFncVO detailFncVO = ( BaseBkrPortfMgmtDetailFncVO ) fncVO_;
    Map checkBoxMap = detailFncVO.getCheckBoxMap();
    ArrayList updateCheckBoxList = new ArrayList();
    ArrayList deleteCheckBoxList = new ArrayList();
    ArrayList insertCheckBoxList = new ArrayList();

    // Cria chave composta a partir de carteira admnistrada selecionada no form.
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

  /**
   * Seta na Form os campos específicos de current
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BkrPortfMgmtDetailForm bkrPortfMgmtDetailForm = ( BkrPortfMgmtDetailForm ) form_;
    BkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;

    if ( ( ( BaseBkrPortfMgmtDetailFncVO ) fncVO_ ).isAssociationConfirmed() == false )
    {
      super.updateFormFromFncVO( bkrPortfMgmtDetailForm,
                                 bkrPortfMgmtDetailFncVO );

      // Popula os valores dos checkboxes do grid "Consulta de Corretora".
      populateBrokerGridCheckBoxes( bkrPortfMgmtDetailFncVO,
                                    bkrPortfMgmtDetailForm );

      // Seta os valores dos checkboxes do grid "Corretoras Associadas a
      // Carteira Administrada".
      populateBkrPortfMgmtGridCheckBoxes( bkrPortfMgmtDetailFncVO,
                                          bkrPortfMgmtDetailForm );

      loadBrokerGrid( bkrPortfMgmtDetailForm, bkrPortfMgmtDetailFncVO );

      loadBkrPortfMgmtGrid( bkrPortfMgmtDetailForm, bkrPortfMgmtDetailFncVO );
    }
    else
    {
      BigInteger custNbr = ( ( BaseBkrPortfMgmtDetailFncVO ) fncVO_ ).getBaseTplBkrPortfMgmtEntity().getData().getCustNbr();

      bkrPortfMgmtDetailForm.setBkrPortfMgmtGrid( new String[ 0 ][ 0 ] );

      bkrPortfMgmtDetailForm.setBkrCnpjNbrInBkrPortfMgmtGrid( null );

      bkrPortfMgmtDetailForm.setInsertSelectedItemsInBkrPortfMgmtGrid( null );
      bkrPortfMgmtDetailForm.setUpdateSelectedItemsInBkrPortfMgmtGrid( null );
      bkrPortfMgmtDetailForm.setDeleteSelectedItemsInBkrPortfMgmtGrid( null );

      bkrPortfMgmtDetailForm.setSelectedItemsInBkrPortfMgmtGrid( new String[ 0 ] );

      bkrPortfMgmtDetailForm.setSelectedProdAcctCode( null );
      bkrPortfMgmtDetailForm.setSelectedProdUnderAcctCode( null );

      bkrPortfMgmtDetailForm.setBovespaCurAcctNbrInBkrPortfMgmtGrid( null );
      bkrPortfMgmtDetailForm.setBovespaInvstAcctNbrInBkrPortfMgmtGrid( null );
      bkrPortfMgmtDetailForm.setBmfCurAcctNbrInBkrPortfMgmtGrid( null );
      bkrPortfMgmtDetailForm.setBmfInvstAcctNbrInBkrPortfMgmtGrid( null );

      ( ( BaseBkrPortfMgmtDetailFncVO ) fncVO_ ).setAssociationConfirmed( false );
    }

    if ( !fncVO_.hasErrors() )
    {
      bkrPortfMgmtDetailForm.setHasErrors( "false" );
    }
    else
    {
      bkrPortfMgmtDetailForm.setHasErrors( "true" );
    }

    // Controle utilizado na habilitação do botão de inserção de corretoras
    for ( Iterator it = fncVO_.getErrors().get(
                                                BkrPortfMgmtDetailFncVO.C_ERROR_DUPLICATE_BROKER ); it.hasNext(); )
    {
      bkrPortfMgmtDetailForm.setHasErrors( "false" );
      break;
    }

    // Variável de controle para habilitação do botão de inserção de corretoras
    Integer brokerListSize = new Integer(
                                          bkrPortfMgmtDetailFncVO.getBaseTplBrokerEntityArray().size() );
    bkrPortfMgmtDetailForm.setBrokerListSize( brokerListSize.toString() );
  }

  /**
   * Retorna uma instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new BkrPortfMgmtDetailFncVO();
  }

  /**
   * Limpa os campos referentes à consulta de corretora.
   *  
   */
  public void clearPage( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;

    bkrPortfMgmtDetailFncVO.setSelectedItemsInBrokerGrid( new ArrayList() );
    bkrPortfMgmtDetailFncVO.setBaseTplBrokerEntityArray( new ArrayList() );
    bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrCnpjNbr(
                                                                                    null );
    bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().setBkrNameText(
                                                                                     null );
  }

  /**
   * Carregamento inicial - inserção
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {

    BkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;

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

    this.loadPortfolio( bkrPortfMgmtDetailFncVO );

  }

  /**
   * Carregamento inicial - detalhe
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carregamento inicial - alteração
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carregamento inicial - exclusão
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Realiza as validações de alteração
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtDetailFncVO brokerDetailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;
    TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) brokerDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData();

    fncVO_.clearErrors();

    // Validar se já existe associação em movimento
    if ( this.existsInMovement( brokerDetailFncVO ) )
    {
      fncVO_.addError( BkrPortfMgmtDetailFncVO.C_ERROR_IN_MOVEMENT );
    }

  }

  /**
   * Realiza as validações de exclusão
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Realiza as validações de inserção
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    this.validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      BkrPortfMgmtDetailFncVO detailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;
      Map checkBoxMap = detailFncVO.getCheckBoxMap();
      Map bkrPortfMgmtEntityMap = detailFncVO.getBkrPortfMgmtMap();

      ArrayList checkBoxSelections = new ArrayList();
      ArrayList bkrPortfMgmtEntityList = new ArrayList();

      for ( Iterator it = checkBoxMap.keySet().iterator(); it.hasNext(); )
      {
        Object ite = it.next();
        BigInteger prodAcctCode = ( BigInteger ) ( ( ArrayList ) ite ).get( 0 );
        BigInteger prodUnderAcctCode = ( BigInteger ) ( ( ArrayList ) ite ).get( 1 );

        checkBoxSelections = ( ArrayList ) checkBoxMap.get( ite );
        bkrPortfMgmtEntityList = ( ArrayList ) bkrPortfMgmtEntityMap.get( ite );

        // Insere entidades na tabela de movimento conforme código de operação.
        insertEntities( fncVO_, checkBoxSelections, bkrPortfMgmtEntityList,
                        prodAcctCode, prodUnderAcctCode );
      }
      detailFncVO.addMessage( BaseODSFncVO.C_MESSAGE_SUCESS );
      detailFncVO.setAssociationConfirmed( true );
    }
  }

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {
    //  
  }

  /**
   * Realiza consulta em lista de corretoras.
   *  
   */
  public void listBroker( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtDetailFncVO detailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;
    TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtEntity ) detailFncVO.getBaseTplBkrPortfMgmtEntity();

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
      detailFncVO.addError( BaseODSFncVO.C_NO_REGISTER_FOUND );
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
   * Realiza consulta em lista de Corretoras Associadas a Carteira Administrada.
   *  
   */
  public void listBkrPortfMgmt( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtDetailFncVO detailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;
    TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtEntity ) detailFncVO.getBaseTplBkrPortfMgmtEntity();

    fncVO_.clearErrors();
    fncVO_.clearMessages();

    ArrayList key = new ArrayList();
    key.add( detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode() );
    key.add( detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode() );
    Map checkBoxMap = detailFncVO.getCheckBoxMap();

    BkrPortfMgmtDetailFncVO detailFncVOMovementCheck = ( BkrPortfMgmtDetailFncVO ) fncVO_;

    detailFncVOMovementCheck.getBaseTplBkrPortfMgmtEntity().getData().setBkrCnpjNbr(
                                                                                     "" );
    detailFncVOMovementCheck.getBaseTplBkrPortfMgmtEntity().getData().setProdAcctCode(
                                                                                       detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode() );
    detailFncVOMovementCheck.getBaseTplBkrPortfMgmtEntity().getData().setProdUnderAcctCode(
                                                                                            detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode() );

    detailFncVO.setBaseTplBkrPortfMgmtEntityArray( new ArrayList() );
    ArrayList updateSelectedItensInBkrPortfMgmtGrid = new ArrayList();
    ArrayList deleteSelectedItensInBkrPortfMgmtGrid = new ArrayList();
    ArrayList insertSelectedItensInBkrPortfMgmtGrid = new ArrayList();
    ArrayList checkBoxListForMap = new ArrayList();

    if ( existsInMovement( detailFncVOMovementCheck ) )
    {
      fncVO_.addError( BkrPortfMgmtDetailFncVO.C_ERROR_BROKER_IN_MOVEMENT );
      detailFncVO.setConfirmAssociationEnabled( false );

    }
    else
    {
      fncVO_.clearErrors();
      detailFncVO.setConfirmAssociationEnabled( true );

      Map bkrPortfMgmtMap = detailFncVO.getBkrPortfMgmtMap();

      ArrayList checkBoxList = new ArrayList();
      checkBoxList = ( ArrayList ) checkBoxMap.get( key );

      detailFncVO.setBaseTplBkrPortfMgmtEntityArray( ( ArrayList ) bkrPortfMgmtMap.get( key ) );

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

    }

    checkBoxListForMap.add( updateSelectedItensInBkrPortfMgmtGrid );
    checkBoxListForMap.add( deleteSelectedItensInBkrPortfMgmtGrid );
    checkBoxListForMap.add( insertSelectedItensInBkrPortfMgmtGrid );

    checkBoxMap.put( key, checkBoxListForMap );
  }

  /**
   * Insere as corretoras selecionadas no grid de Corretoras no grid de
   * Corretoras Associadas a Carteira Administrada.
   *  
   */
  public void addSelectedBrokers( BaseFncVO fncVO_ )
  {

    BkrPortfMgmtDetailFncVO detailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;
    TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtEntity ) detailFncVO.getBaseTplBkrPortfMgmtEntity();

    detailFncVO.clearErrors();

    if ( hasSelectedItemsInBrokerGrid( detailFncVO.getSelectedItemsInBrokerGrid() ) == true )
    {
      for ( int i = 0; i < detailFncVO.getBaseTplBrokerEntityArray().size(); i++ )
      {
        if ( detailFncVO.getSelectedItemsInBrokerGrid().get( i ).equals(
                                                                         C_CHECKED ) )
        {
          TplBkrPortfMgmtEntity selectedTplBkrPortfMgmtEntity = new TplBkrPortfMgmtEntity(
                                                                                           ( TplBrokerEntity ) detailFncVO.getBaseTplBrokerEntityArray().get(
                                                                                                                                                              i ),
                                                                                           detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr(),
                                                                                           detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getBkrCustNbr(),
                                                                                           detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode(),
                                                                                           detailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode() );
          updateCheckBoxMap( fncVO_, detailFncVO, selectedTplBkrPortfMgmtEntity );
        }
      }
    }
    else
    {
      detailFncVO.addError( BaseODSFncVO.C_ERROR_NO_REGISTER_FOUND_IN_BROKER_GRID );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.product.broker.functionality.BaseBkrPortfMgmtDetailFnc#getDAO()
   */
  protected BaseTplBkrPortfMgmtDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplBkrPortfMgmtDAO();
  }

  /**
   * "Insere entidades na tabela de movimento conforme código de operação"
   */
  private void insertEntities( BaseFncVO fncVO_, ArrayList checkBoxSelections_,
                              ArrayList bkrPortfMgmtEntityList_,
                              BigInteger prodAcctCode_,
                              BigInteger prodUnderAcctCode_ )
  {
    for ( int i = 0; i < ( ( ArrayList ) checkBoxSelections_.get( C_UPDATE_INDEX ) ).size(); i++ )
    {
      String updateAction = ( ( ArrayList ) checkBoxSelections_.get( C_UPDATE_INDEX ) ).get(
                                                                                             i ).toString();
      String deleteAction = ( ( ArrayList ) checkBoxSelections_.get( C_DELETE_INDEX ) ).get(
                                                                                             i ).toString();
      String inserAction = ( ( ArrayList ) checkBoxSelections_.get( C_INSERT_INDEX ) ).get(
                                                                                            i ).toString();
      if ( updateAction.equals( C_CHECKED )
           && deleteAction.equals( C_UNCHECKED )
           && inserAction.equals( C_UNCHECKED ) )
      {
        insertBkrPortfMgmtMovEntity(
                                     fncVO_,
                                     bkrPortfMgmtEntityList_,
                                     prodAcctCode_,
                                     prodUnderAcctCode_,
                                     i,
                                     TplBkrPortfMgmtMovEntity.C_OPERN_CODE_UPDATE );

      }
      else if ( updateAction.equals( C_UNCHECKED )
                && deleteAction.equals( C_CHECKED )
                && inserAction.equals( C_UNCHECKED ) )
      {
        insertBkrPortfMgmtMovEntity(
                                     fncVO_,
                                     bkrPortfMgmtEntityList_,
                                     prodAcctCode_,
                                     prodUnderAcctCode_,
                                     i,
                                     TplBkrPortfMgmtMovEntity.C_OPERN_CODE_DELETE );
      }
      else if ( updateAction.equals( C_CHECKED )
                && deleteAction.equals( C_UNCHECKED )
                && inserAction.equals( C_CHECKED ) )
      {
        insertBkrPortfMgmtMovEntity(
                                     fncVO_,
                                     bkrPortfMgmtEntityList_,
                                     prodAcctCode_,
                                     prodUnderAcctCode_,
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
                                           ArrayList bkrPortfMgmtEntityList_,
                                           BigInteger prodAcctCode_,
                                           BigInteger prodUnderAcctCode_,
                                           int i_, String opernCode_ )
  {
    TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = ( ( TplBkrPortfMgmtEntity ) bkrPortfMgmtEntityList_.get( i_ ) );
    tplBkrPortfMgmtEntity.getData().setLastUpdDate( new Date() );
    tplBkrPortfMgmtEntity.getData().setLastUpdUserId(
                                                      fncVO_.getLoggedUser() != null
                                                                                    ? fncVO_.getLoggedUser().getUserID()
                                                                                    : "" );
    tplBkrPortfMgmtEntity.getData().setProdAcctCode( prodAcctCode_ );
    tplBkrPortfMgmtEntity.getData().setProdUnderAcctCode( prodUnderAcctCode_ );
    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity = new TplBkrPortfMgmtMovEntity(
                                                                                      tplBkrPortfMgmtEntity,
                                                                                      opernCode_ );

    TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();

    tplBkrPortfMgmtMovDAO.insert( tplBkrPortfMgmtMovEntity );
  }

  /**
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   */
  private boolean existsInMovement(
                                   BkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO_ )
  {

    boolean exists = false;

    TplBkrPortfMgmtMovDAO tplBkrPortfMgmtMovDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtMovDAO();

    BigInteger prodAcctCode = bkrPortfMgmtDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().getProdAcctCode();
    BigInteger prodUnderAcctCode = bkrPortfMgmtDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().getProdUnderAcctCode();
    ArrayList key = new ArrayList();
    key.add( prodAcctCode );
    key.add( prodUnderAcctCode );

    TplBkrPortfMgmtMovEntity tplBkrPortfMgmtMovEntity = new TplBkrPortfMgmtMovEntity();

    tplBkrPortfMgmtMovEntity.getData().setProdAcctCode( prodAcctCode );
    tplBkrPortfMgmtMovEntity.getData().setProdUnderAcctCode( prodUnderAcctCode );

    if ( tplBkrPortfMgmtMovDAO.exists( tplBkrPortfMgmtMovEntity ) == true )
    {
      exists = true;

    }

    return exists;

  }

  /**
   * Verifica se já existe um registro na tabela de "Current" com o código
   * passado e o seu status é "Ativo"
   */
  private boolean existsActive( BkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO_ )
  {

    boolean exists = false;

    TplBkrPortfMgmtDAO tplBkrPortfMgmtDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtDAO();

    for ( int i = 0; i < bkrPortfMgmtDetailFncVO_.getBaseTplBkrPortfMgmtEntityArray().size(); i++ )
    {

      ArrayList bkrPortfMgmtEntityList = bkrPortfMgmtDetailFncVO_.getBaseTplBkrPortfMgmtEntityArray();

      // Cria entidade TplBkrPortfMgmtEntity a partir na posição atual da lista
      // de entidades
      TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtEntity ) bkrPortfMgmtEntityList.get( i );
      String bkrCnpjNbr = tplBkrPortfMgmtEntity.getData().getBkrCnpjNbr();
      BigInteger prodAcctCode = tplBkrPortfMgmtEntity.getData().getProdAcctCode();
      BigInteger prodUnderAcctCode = tplBkrPortfMgmtEntity.getData().getProdUnderAcctCode();

      tplBkrPortfMgmtEntity.getData().setBkrCnpjNbr( bkrCnpjNbr );
      tplBkrPortfMgmtEntity.getData().setProdAcctCode( prodAcctCode );
      tplBkrPortfMgmtEntity.getData().setProdUnderAcctCode( prodUnderAcctCode );

      if ( tplBkrPortfMgmtDAO.existsActive( tplBkrPortfMgmtEntity ) == true )
      {
        exists = true;
        break;
      }

    }

    return exists;
  }

  /**
   * Realiza o carregamento do grid "Corretoras Associadas a Carteira
   * Administrada".
   */
  private void loadBkrPortfMgmtGrid(
                                    BkrPortfMgmtDetailForm bkrPortfMgmtDetailForm,
                                    BkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO )
  {

    String[] bkrCnpjNbrList = new String[ 0 ];
    String[] bkrNameTextList = new String[ 0 ];
    String[] bovespaCurAcctNbrList = new String[ 0 ];
    String[] bovespaInvstAcctNbrList = new String[ 0 ];
    String[] bmfCurAcctNbrList = new String[ 0 ];
    String[] bmfInvstAcctNbrList = new String[ 0 ];

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

      TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity;
      TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO;

      String[] bkrPortfMgmtGrid = new String[ bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size() ];

      int indexLinha = 0;

      for ( int i = 0; i < bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntityArray().size(); i++ )
      {
        tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtEntity ) tplBkrPortfMgmtEntityList.get( i );
        tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPortfMgmtEntity.getData();

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
      }
    }
    bkrPortfMgmtDetailForm.setBkrCnpjNbrInBkrPortfMgmtGrid( bkrCnpjNbrList );
    bkrPortfMgmtDetailForm.setBkrNameTextInBkrPortfMgmtGrid( bkrNameTextList );
    bkrPortfMgmtDetailForm.setBovespaCurAcctNbrInBkrPortfMgmtGrid( bovespaCurAcctNbrList );
    bkrPortfMgmtDetailForm.setBovespaInvstAcctNbrInBkrPortfMgmtGrid( bovespaInvstAcctNbrList );
    bkrPortfMgmtDetailForm.setBmfCurAcctNbrInBkrPortfMgmtGrid( bmfCurAcctNbrList );
    bkrPortfMgmtDetailForm.setBmfInvstAcctNbrInBkrPortfMgmtGrid( bmfInvstAcctNbrList );
  }

  /**
   * Realiza o carregamento do grid "Consulta de Corretora".
   */
  private void loadBrokerGrid( BkrPortfMgmtDetailForm bkrPortfMgmtDetailForm,
                              BkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO )
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
      brokerGrid[ i ][ BkrPortfMgmtDetailForm.COL_POS_BKR_CNPJ_NBR ] = strBkrCnpjNbr;

      String strBkrNameText = "";
      if ( tplBrokerEntityVO.getBkrNameText() != null )
      {
        strBkrNameText = tplBrokerEntityVO.getBkrNameText().toString();
      }
      brokerGrid[ i ][ BkrPortfMgmtDetailForm.COL_POS_BKR_NAME_TEXT ] = strBkrNameText;

      String strBkrAddrText = "";
      if ( tplBrokerEntityVO.getBkrAddrText() != null )
      {
        strBkrAddrText = tplBrokerEntityVO.getBkrAddrText().toString();
      }
      brokerGrid[ i ][ BkrPortfMgmtDetailForm.COL_POS_BKR_ADDR_TEXT ] = strBkrAddrText;

    }

    bkrPortfMgmtDetailForm.setBrokerGrid( brokerGrid );
  }

  /**
   * "Atualiza o mapa de check boxes com os dados das corretoras inseridas a
   * partir do grid "Consulta de Corretoras""
   */
  private void updateCheckBoxMap(
                                 BaseFncVO fncVO_,
                                 BkrPortfMgmtDetailFncVO detailFncVO,
                                 TplBkrPortfMgmtEntity selectedTplBkrPortfMgmtEntity )
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
   * "Verifica se a corretora inserida a partir do grid "Consulta de Corretoras"
   * já existe no grid "Corretoras Associadas a Carteira Administrada"."
   *  
   */
  private boolean existsInBkrPortfMgmtGrid(
                                           BaseFncVO fncVO_,
                                           BkrPortfMgmtDetailFncVO detailFncVO,
                                           TplBkrPortfMgmtEntity selectedTplBkrPortfMgmtEntity )
  {

    boolean existInBkrPortfMgmt = false;

    for ( int j = 0; j < detailFncVO.getBaseTplBkrPortfMgmtEntityArray().size(); j++ )
    {

      TplBkrPortfMgmtEntity tplBkrPortfMgmtEntityInBkrPortfMgmtGrid = ( TplBkrPortfMgmtEntity ) detailFncVO.getBaseTplBkrPortfMgmtEntityArray().get(
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
   * "Carregamento dos dados de carteira administrada"
   *  
   */
  private void loadPortfolio( BaseFncVO fncVO_ )
  {
    BkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO = ( BkrPortfMgmtDetailFncVO ) fncVO_;

    TplBkrPortfMgmtDAO tplBkrPortfMgmtDAO = ODSDAOFactory.getInstance().getTplBkrPortfMgmtDAO();
    DataSet results = tplBkrPortfMgmtDAO.listPortfolio( bkrPortfMgmtDetailFncVO.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr() );

    bkrPortfMgmtDetailFncVO.setPortfolioResults( results );

    if ( results.size() <= 0 )
    {
      bkrPortfMgmtDetailFncVO.addMessage( BaseODSFncVO.C_NO_REGISTER_FOUND );
    }

    bkrPortfMgmtDetailFncVO.setPortfolioLoaded( true );

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

      ArrayList bkrPortfMgmtList = tplBkrPortfMgmtDAO.listForBkrPorftMgmtGrid(
                                                                               prodAcctCode,
                                                                               prodUnderAcctCode );
      ArrayList tplBkrPortfMgmtEntityList = new ArrayList();

      for ( int j = 0; j < bkrPortfMgmtList.size(); j++ )
      {
        tplBkrPortfMgmtEntityList.add( bkrPortfMgmtList.get( j ) );
      }

      bkrPortfMgmtDetailFncVO.getBkrPortfMgmtMap().put( key,
                                                        tplBkrPortfMgmtEntityList );
    }

  }

  /**
   * "Popula lista de entidades conforme dados do grid "Corretoras Associadas a
   * Carteira Administrada""
   *  
   */
  private void populateBkrPortfMgmtEntityList(
                                              BaseBkrPortfMgmtDetailForm detailForm,
                                              BaseBkrPortfMgmtDetailFncVO detailFncVO )
  {
    if ( detailFncVO.getBkrPortfMgmtMap() != null
         && detailFncVO.getBkrPortfMgmtMapKey() != null
         && !detailFncVO.getBkrPortfMgmtMapKey().isEmpty() )
    {
      Map bkrPortfMgmtMap = detailFncVO.getBkrPortfMgmtMap();

      ArrayList previousKey = new ArrayList();
      previousKey.add( detailFncVO.getBkrPortfMgmtMapKey().get( 0 ) );
      previousKey.add( detailFncVO.getBkrPortfMgmtMapKey().get( 1 ) );

      ArrayList formKey = new ArrayList();
      formKey.add( detailForm.getSelectedProdAcctCode() );
      formKey.add( detailForm.getSelectedProdUnderAcctCode() );

      ArrayList tplBkrPortfMgmtEntityList = ( ArrayList ) bkrPortfMgmtMap.get( previousKey );

      if ( tplBkrPortfMgmtEntityList != null
           || detailForm.getInsertBtnPressed().equals( "true" ) )
      {
        for ( int i = 0; i < detailForm.getBovespaCurAcctNbrInBkrPortfMgmtGrid().length; i++ )
        {
          String bovespaCurAcctAcctNbr = detailForm.getBovespaCurAcctNbrInBkrPortfMgmtGrid()[ i ];
          String bovespaInvstAcctNbr = detailForm.getBovespaInvstAcctNbrInBkrPortfMgmtGrid()[ i ];
          String bmfCurAcctAcctNbr = detailForm.getBmfCurAcctNbrInBkrPortfMgmtGrid()[ i ];
          String bmfInvstAcctNbr = detailForm.getBmfInvstAcctNbrInBkrPortfMgmtGrid()[ i ];
          TplBkrPortfMgmtEntity tplBkrPortfMgmtEntity = ( TplBkrPortfMgmtEntity ) tplBkrPortfMgmtEntityList.get( i );
          TplBkrPortfMgmtEntityVO tplBkrPortfMgmtEntityVO = ( TplBkrPortfMgmtEntityVO ) tplBkrPortfMgmtEntity.getData();
          tplBkrPortfMgmtEntityVO.setBovespaCurAcctNbr( bovespaCurAcctAcctNbr );
          tplBkrPortfMgmtEntityVO.setBovespaInvstAcctNbr( bovespaInvstAcctNbr );
          tplBkrPortfMgmtEntityVO.setBmfCurAcctNbr( bmfCurAcctAcctNbr );
          tplBkrPortfMgmtEntityVO.setBmfInvstAcctNbr( bmfInvstAcctNbr );
        }
      }
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
   * "Cria uma chave composta a partir da carteira administrada selecionada no
   * form. A chave é baseada nos valores das variáveis m_prodAccCode e
   * m_prodUnderAcctCode."
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
   * "Popula os valores dos checkboxes do grid "Consulta de Corretora"".
   */
  private void populateBrokerGridCheckBoxes(
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
  private void populateBkrPortfMgmtGridCheckBoxes(
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
   * Carrega o nome do cliente.
   *  
   */
  private void loadCustomerFullNameText(
                                        BaseBkrPortfMgmtDetailFncVO bkrPortfMgmtDetailFncVO_ )
  {
    if ( bkrPortfMgmtDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr() != null
         && bkrPortfMgmtDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr().longValue() > 0 )
    {
      TplCustomerPrvtEntity customerPrvtEntity = new TplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               bkrPortfMgmtDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().getCustNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtDAO();

      //Realiza a consulta no DAO
      customerPrvtEntity = ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      bkrPortfMgmtDetailFncVO_.getBaseTplBkrPortfMgmtEntity().getData().setCustFullNameText(
                                                                                             customerPrvtEntity.getData().getCustFullNameText() );
    }
  }

}