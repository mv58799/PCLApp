/*
 * Created on Apr 15, 2007
 *
 */
package com.citibank.ods.modules.client.relationeg.functionality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplRelationEgEntity;
import com.citibank.ods.entity.pl.TplRelationEgMovEntity;
import com.citibank.ods.entity.pl.TplRelationPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplRelationEgEntityVO;
import com.citibank.ods.modules.client.relationeg.form.RelationEgDetailForm;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgDetailFncVO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgDAO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgMovDAO;
import com.citibank.ods.persistence.pl.dao.TplRelationPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class RelationEgDetailFnc extends BaseRelationEgDetailFnc implements
    ODSDetailFnc
{

  /**
   * Realiza a inserção do registro no BD
   */
  public void insert( BaseFncVO fncVO_ )
  {

    TplRelationEgEntity egEntity;
    TplRelationEgMovEntity egMovEntity;

    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;

      TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();

      int relationList = relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size();
      List<Integer> indexToDel = new ArrayList<Integer>();
      
      for ( int count = 0; count < relationList; count++ )
      {
      
          if ( relationEgDetailFncVO.getDeletedItems().get( count ).equals( "S" ) )
          {
        	  indexToDel.add(count);  
          }     
      }
      
      //ordena decrescente
      Comparator comparator = Collections.reverseOrder();
      Collections.sort(indexToDel,comparator);


      if (indexToDel != null && !indexToDel.isEmpty()){
    	  for (Iterator ite = indexToDel.iterator(); ite.hasNext(); ){
    		  int i = (Integer)ite.next();
    		  relationEgDetailFncVO.getBaseTplRelationEgEntityArray().remove( i );
    	  }
      }

      ArrayList relations = relationEgDetailFncVO.getBaseTplRelationEgEntityArray();

      for ( Iterator ite = relations.iterator(); ite.hasNext(); )
      {
        egEntity = ( TplRelationEgEntity ) ite.next();
        egEntity.getData().setLastUpdDate( new Date() );
        egEntity.getData().setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );
        egMovEntity = new TplRelationEgMovEntity(
                                                  egEntity,
                                                  TplRelationEgEntity.C_OPERN_CODE_INSERT );

        tplRelationEgMovDAO.insert( egMovEntity );
      }
      relationEgDetailFncVO.setFromSearch( false );
    }

  }

  /**
   * Executa a atualização das informações de EG x Relacionamentos
   */
  public void update( BaseFncVO fncVO_ )
  {

    TplRelationEgEntity egEntity;
    TplRelationEgMovEntity egMovEntity = null;

    this.validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      //Casting do fncVO específico.
      RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;

      //Cria instancias dos DAOS que serão utilizados.
      TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();

      //Retira da lista final os ítens que foram inseridos e excluídos na mesma
      // operação.
      int relationList = relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size();
      Iterator resultsIterator;

      for ( int count = 0; count < relationList; count++ )
      {

        resultsIterator = relationEgDetailFncVO.getSelectedItemsInGrid().iterator();
        int max = 0;

        while ( resultsIterator.hasNext() )
        {
          resultsIterator.next();
          if ( relationEgDetailFncVO.getSelectedItemsInGrid().get( max ).equals(
                                                                                 "S" )
               && relationEgDetailFncVO.getDeletedItems().get( max ).equals(
                                                                             "S" ) )
          {
            relationEgDetailFncVO.getBaseTplRelationEgEntityArray().remove( max );
            relationEgDetailFncVO.getSelectedItemsInGrid().remove( max );
            relationEgDetailFncVO.getDeletedItems().remove( max );
            break;
          }
          else if ( relationEgDetailFncVO.getSelectedItemsInGrid().get( max ).equals(
                                                                                      "N" )
                    && relationEgDetailFncVO.getDeletedItems().get( max ).equals(
                                                                                  "N" ) )
          {
            relationEgDetailFncVO.getBaseTplRelationEgEntityArray().remove( max );
            relationEgDetailFncVO.getSelectedItemsInGrid().remove( max );
            relationEgDetailFncVO.getDeletedItems().remove( max );
            break;
          }
          max++;

        }

      }
      for ( int count = 0; count < relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size(); count++ )

      {
        egEntity = ( TplRelationEgEntity ) relationEgDetailFncVO.getBaseTplRelationEgEntityArray().get(
                                                                                                        count );
        egEntity.getData().setLastUpdDate( new Date() );
        egEntity.getData().setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );

        if ( relationEgDetailFncVO.getSelectedItemsInGrid().get( count ).equals(
                                                                                 "S" )
             && relationEgDetailFncVO.getDeletedItems().get( count ).equals(
                                                                             "N" ) )
        {
          egMovEntity = new TplRelationEgMovEntity(
                                                    egEntity,
                                                    TplRelationEgEntity.C_OPERN_CODE_INSERT );

        }
        else if ( relationEgDetailFncVO.getSelectedItemsInGrid().get( count ).equals(
                                                                                      "N" )
                  && relationEgDetailFncVO.getDeletedItems().get( count ).equals(
                                                                                  "S" ) )
        {
          egMovEntity = new TplRelationEgMovEntity(
                                                    egEntity,
                                                    TplRelationEgEntity.C_OPERN_CODE_DELETE );
        }

        tplRelationEgMovDAO.insert( egMovEntity );

      }

    }
  }

  /**
   * Realiza a remoção de associações EG x Relacionamento
   */
  public void delete( BaseFncVO fncVO_ )
  {

    TplRelationEgEntity egEntity;
    TplRelationEgMovEntity egMovEntity;

    this.validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;
      ArrayList relations = relationEgDetailFncVO.getBaseTplRelationEgEntityArray();
      TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();

      for ( Iterator ite = relations.iterator(); ite.hasNext(); )
      {
        egEntity = ( TplRelationEgEntity ) ite.next();
        egMovEntity = new TplRelationEgMovEntity(
                                                  egEntity,
                                                  TplRelationEgEntity.C_OPERN_CODE_DELETE );
        egMovEntity.getData().setLastUpdDate( new Date() );
        egMovEntity.getData().setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );
        tplRelationEgMovDAO.insert( egMovEntity );
      }
    }
  }

  /**
   * Validações de Insert
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;

    ArrayList list = relationEgDetailFncVO.getBaseTplRelationEgEntityArray();

    int findItemsDeleted = 0;

    for ( int i = 0; i < relationEgDetailFncVO.getDeletedItems().size(); i++ )
    {
      if ( relationEgDetailFncVO.getDeletedItems().get( i ).equals( "S" ) )
      {
        findItemsDeleted++;

      }
    }
    if ( findItemsDeleted == list.size() )
    {
      relationEgDetailFncVO.addError(
                                      RelationEgDetailFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                                      RelationEgDetailFncVO.C_INSERT,
                                      RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
    }
    else
    {
      TplRelationEgEntity firstRelationEgEntityInArray = ( TplRelationEgEntity ) list.iterator().next();
      String firstEgNbrInArray = firstRelationEgEntityInArray.getData().getEgNbr();
      String firstErNbrInArray = firstRelationEgEntityInArray.getData().getErNbr();
      TplRelationEgDAO tplRelationEgDAO = OracleODSDAOFactory.getInstance().getTplRelationEgDAO();

      if ( tplRelationEgDAO.existsRelationActive( firstEgNbrInArray,
                                                  firstErNbrInArray ) )
      {
        relationEgDetailFncVO.addError(
                                        RelationEgDetailFncVO.C_ERROR_DUPLICATE_ASSOCIATION,
                                        RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
      }

      TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();
      if ( tplRelationEgMovDAO.existsRelation( firstEgNbrInArray,
                                               firstErNbrInArray ) )
      {
        relationEgDetailFncVO.addError(
                                        RelationEgDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                                        RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
      }
      
      Iterator it = list.iterator();
      
      if (!relationEgDetailFncVO.hasErrors() ) {

    	  int count = 0;
    	  while ( it.hasNext() ) {
    		  TplRelationEgEntity tplRelationEgEntity = (TplRelationEgEntity)it.next();

    		  if  (relationEgDetailFncVO.getDeletedItems().get( count ).equals( "N" )) {
    			  //Valida se o relacionamento já tem EG associado (1 relacionamento só pode ter 1 EG associado), se nao for exclusao
    			  if ( tplRelationEgDAO.existsEG( tplRelationEgEntity.getData().getReltnNbr() ) )
    			  {
    				  relationEgDetailFncVO.addError(
    						  RelationEgDetailFncVO.C_ERROR_DUPLICATE_ASSOCIATION_RELTN_EG,
    						  tplRelationEgEntity.getData().getReltnNbr().toString() );
    			  }
    		  }
    		  count++;
    	  }
      }
    }
  }

  /**
   * Realiza as validações de Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;
    ArrayList list = relationEgDetailFncVO.getBaseTplRelationEgEntityArray();

    int findItemsDeleted = 0;

    for ( int i = 0; i < relationEgDetailFncVO.getDeletedItems().size(); i++ )
    {
      if ( relationEgDetailFncVO.getDeletedItems().get( i ).equals( "S" ) )
      {
        findItemsDeleted++;

      }
    }
    if ( findItemsDeleted == list.size() )
    {
      relationEgDetailFncVO.addError(
                                      RelationEgDetailFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                                      RelationEgDetailFncVO.C_UPDATE,
                                      RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
    }
    else
    {
      TplRelationEgEntity firstRelationEgEntityInArray = ( TplRelationEgEntity ) list.iterator().next();
      String firstEgNbrInArray = firstRelationEgEntityInArray.getData().getEgNbr();
      String firstErNbrInArray = firstRelationEgEntityInArray.getData().getErNbr();
      TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();

      if ( tplRelationEgMovDAO.existsRelation( firstEgNbrInArray,
                                               firstErNbrInArray ) )
      {
        relationEgDetailFncVO.addError(
                                        RelationEgDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                                        RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
      }
      if (!relationEgDetailFncVO.hasErrors()) {

    	  TplRelationEgDAO tplRelationEgDAO = OracleODSDAOFactory.getInstance().getTplRelationEgDAO();
    	  Iterator it = list.iterator();

    	  int count = 0;
    	  while ( it.hasNext() ) {
    		  TplRelationEgEntity tplRelationEgEntity = (TplRelationEgEntity)it.next();

    		  if  (relationEgDetailFncVO.getDeletedItems().get( count ).equals( "N" ) && relationEgDetailFncVO.getSelectedItemsInGrid().get(count).equals("S")) {
    			  //Valida se o relacionamento já tem EG associado (1 relacionamento só pode ter 1 EG associado), se nao for exclusao e é inclusao 
    			  if ( tplRelationEgDAO.existsEG( tplRelationEgEntity.getData().getReltnNbr() ) )
    			  {
    				  relationEgDetailFncVO.addError(
    						  RelationEgDetailFncVO.C_ERROR_DUPLICATE_ASSOCIATION_RELTN_EG,
    						  tplRelationEgEntity.getData().getReltnNbr().toString() );
    			  }
    		  }
    		  count++;
    	  }
      }
    }
  }

  /**
   * Realiza as validações - Delete
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;
    ArrayList list = relationEgDetailFncVO.getBaseTplRelationEgEntityArray();
    if ( list == null || list.size() <= 0 )
    {
      relationEgDetailFncVO.addError(
                                      RelationEgDetailFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                                      RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
    }
    else
    {
      TplRelationEgEntity firstRelationEgEntityInArray = ( TplRelationEgEntity ) list.iterator().next();
      String firstEgNbrInArray = firstRelationEgEntityInArray.getData().getEgNbr();
      String firstErNbrInArray = firstRelationEgEntityInArray.getData().getErNbr();

      TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();

      if ( tplRelationEgMovDAO.existsRelation( firstEgNbrInArray,
                                               firstErNbrInArray ) )
      {
        relationEgDetailFncVO.addError(
                                        RelationEgDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                                        RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
      }
    }
  }

  /**
   * Carrega a tela de detalhe do relacionamento EG
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {

    super.loadErNbr( fncVO_ );
    RelationEgDetailFncVO egDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;

    String egNbr = egDetailFncVO.getEgNbr();
    String erNbr = egDetailFncVO.getErNbr();

    TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();

    TplRelationEgDAO tplRelationEgDAO = ODSDAOFactory.getInstance().getTplRelationEgDAO();

    egDetailFncVO.setReltnNbr( null );

    ArrayList list = tplRelationEgDAO.listByEgNbr( egNbr, erNbr );
    egDetailFncVO.setBaseTplRelationEgEntityArray( list );

  }

  /**
   * Carregamentos iniciais - Insert
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    super.loadErNbr( fncVO_ );
    RelationEgDetailFncVO egDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;

    if ( !egDetailFncVO.isFromSearch() )
    {
      ArrayList list = new ArrayList();
      egDetailFncVO.setBaseTplRelationEgEntityArray( list );
      egDetailFncVO.setReltnNbr( null );
      egDetailFncVO.setFromSearch( false );
      egDetailFncVO.setErNbr( null );
      egDetailFncVO.setEgNbr( null );
      egDetailFncVO.setDeletedItems(new ArrayList());
      egDetailFncVO.setSelectedItemsInGrid(new ArrayList());
      
    }

  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.loadErNbr( fncVO_ );
    RelationEgDetailFncVO egDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;
    String egNbr = egDetailFncVO.getEgNbr();
    String erNbr = egDetailFncVO.getErNbr();

    TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();
    if ( tplRelationEgMovDAO.existsRelation( egNbr, erNbr ) )
    {
      egDetailFncVO.addError(
                              RelationEgDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                              RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
    }
    if ( !egDetailFncVO.isFromSearch() )
    {

      TplRelationEgDAO tplRelationEgDAO = ODSDAOFactory.getInstance().getTplRelationEgDAO();

      ArrayList list = tplRelationEgDAO.listByEgNbr( egNbr, erNbr );
      egDetailFncVO.setBaseTplRelationEgEntityArray( list );
    }
    egDetailFncVO.setFromSearch( false );
  }

  /**
   * Carregamentos iniciais do Delete
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    super.loadErNbr( fncVO_ );
    RelationEgDetailFncVO egDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;

    String egNbr = egDetailFncVO.getEgNbr();
    String erNbr = egDetailFncVO.getErNbr();

    TplRelationEgMovDAO tplRelationEgMovDAO = OracleODSDAOFactory.getInstance().getTplRelationEgMovDAO();
    if ( tplRelationEgMovDAO.existsRelation( egNbr, erNbr ) )
    {
      egDetailFncVO.addError(
                              RelationEgDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                              RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
    }
    else
    {
      TplRelationEgDAO tplRelationEgDAO = ODSDAOFactory.getInstance().getTplRelationEgDAO();

      egDetailFncVO.setReltnNbr( null );

      ArrayList list = tplRelationEgDAO.listByEgNbr( egNbr, erNbr );
      egDetailFncVO.setBaseTplRelationEgEntityArray( list );
    }
  }

  /*
   * Insere o domínio no Grid
   */
  public void insertDomain( BaseFncVO fncVO_ )
  {
    RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;
    this.validateInsertDomain( relationEgDetailFncVO );
    if ( !relationEgDetailFncVO.hasErrors() )
    {

      TplRelationPrvtEntity relationPrvtEntity = super.findRelationByPk( relationEgDetailFncVO.getReltnNbr() );
      if ( relationPrvtEntity != null )
      {
        TplRelationEgEntity tplRelationEgEntity = new TplRelationEgEntity();
        tplRelationEgEntity.getData().setEgNbr(
                                                relationEgDetailFncVO.getEgNbr() );
        tplRelationEgEntity.getData().setErNbr(
                                                relationEgDetailFncVO.getErNbr() );
        tplRelationEgEntity.getData().setReltnNbr(
                                                   relationEgDetailFncVO.getReltnNbr() );
        super.findCustomers( relationPrvtEntity );
        tplRelationEgEntity.setRelationPrvtEntity( relationPrvtEntity );
        relationEgDetailFncVO.getBaseTplRelationEgEntityArray().add(
                                                                     tplRelationEgEntity );
        relationEgDetailFncVO.getSelectedItemsInGrid().add( "S" );
        relationEgDetailFncVO.getDeletedItems().add( "N" );
      }
      else
      {
        relationEgDetailFncVO.addError(
                                        RelationEgDetailFncVO.C_ERROR_REG_NOT_EXISTS,
                                        RelationEgDetailFncVO.C_RELTN_NBR_DESCRIPTION );
      }
    }
  }

  /*
   * Realiza as validações do insert domain
   */
  private void validateInsertDomain(
                                    RelationEgDetailFncVO relationEgDetailFncVO_ )
  {
    relationEgDetailFncVO_.clearErrors();

    if ( relationEgDetailFncVO_.getReltnNbr() == null )
    {
      relationEgDetailFncVO_.addError(
                                       RelationEgDetailFncVO.C_ERROR_MANDATORY_FIELD,
                                       RelationEgDetailFncVO.C_RELTN_NBR_DESCRIPTION );
    }

    if ( relationEgDetailFncVO_.getEgNbr() == null
         || relationEgDetailFncVO_.getEgNbr().equals( "" ) )
    {
      relationEgDetailFncVO_.addError(
                                       RelationEgDetailFncVO.C_ERROR_MANDATORY_FIELD,
                                       RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
    }

    if ( !relationEgDetailFncVO_.hasErrors() )
    {
      // Valida se o registro a ser inserido
      // possui o mesmo EG dos outros
      ArrayList list = relationEgDetailFncVO_.getBaseTplRelationEgEntityArray();
      if ( !list.isEmpty() )
      {
        TplRelationEgEntity tplRelationEgEntity = ( TplRelationEgEntity ) list.iterator().next();
        String firstEgNbr = tplRelationEgEntity.getData().getEgNbr();

        if ( !relationEgDetailFncVO_.getEgNbr().equals( firstEgNbr ) )
        {
          relationEgDetailFncVO_.addError(
                                           RelationEgDetailFncVO.C_ERROR_DUPLICATE_REG_IN_GRID,
                                           RelationEgDetailFncVO.C_EG_NBR_DESCRIPTION );
        }
      }

      // Verifica se existe algum registro duplicado no Grid
      TplRelationEgEntity tplRelationEgEntity = new TplRelationEgEntity();
      tplRelationEgEntity.getData().setEgNbr( relationEgDetailFncVO_.getEgNbr() );
      tplRelationEgEntity.getData().setReltnNbr(
                                                 relationEgDetailFncVO_.getReltnNbr() );
      if ( list.contains( tplRelationEgEntity ) )
      {
        relationEgDetailFncVO_.addError( RelationEgDetailFncVO.C_ERROR_DUPLICATED_ELEMENT_IN_GRID );
      }
    }

    //Valida se o relacionamento existe na TPL_RELATION_PRVT
    if ( !relationEgDetailFncVO_.hasErrors() )
    {
      TplRelationPrvtEntity prvtEntity = new TplRelationPrvtEntity();
      prvtEntity.getData().setReltnNbr( relationEgDetailFncVO_.getReltnNbr() );

      TplRelationPrvtDAO relationPrvtDAO = OracleODSDAOFactory.getInstance().getTplRelationPrvtDAO();
      if ( !relationPrvtDAO.existsActive( prvtEntity ) )
      {
        relationEgDetailFncVO_.addError(
                                         RelationEgDetailFncVO.C_ERROR_REG_NOT_EXISTS,
                                         RelationEgDetailFncVO.C_RELTN_NBR_DESCRIPTION );
      }
    }
    
  }

  /*
   * Remove o domínio do Grid
   */
  public void deleteDomain( BaseFncVO fncVO_ )
  {
    RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;
    ArrayList list = relationEgDetailFncVO.getBaseTplRelationEgEntityArray();

    TplRelationEgEntity tplRelationEgEntity = new TplRelationEgEntity();
    tplRelationEgEntity.getData().setEgNbr(
                                            relationEgDetailFncVO.getSelectedEgNbrInGrid() );
    if ( relationEgDetailFncVO.getSelectedReltnNbrInGrid() != null
         && !relationEgDetailFncVO.getSelectedReltnNbrInGrid().equals( "" ) )
    {
      tplRelationEgEntity.getData().setReltnNbr(
                                                 relationEgDetailFncVO.getSelectedReltnNbrInGrid() );
    }

    list.remove( tplRelationEgEntity );
    relationEgDetailFncVO.clearErrors();
  }

  /**
   * Retorna uma instância de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new RelationEgDetailFncVO();
  }

  /**
   * Atualiza as informações do FncVO com os valores vindos da Form
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFncVOFromForm( form_, fncVO_ );
  }

  /**
   * Atualiza as informações do Form com os valores vindos do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    TplRelationEgEntity egEntity;
    TplRelationEgEntityVO egEntityVO;

    super.updateFormFromFncVO( form_, fncVO_ );

    RelationEgDetailForm relationEgDetailForm = ( RelationEgDetailForm ) form_;
    RelationEgDetailFncVO relationEgDetailFncVO = ( RelationEgDetailFncVO ) fncVO_;

    String[][] grid = new String[ relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size() ][ 5 ];
    int indexLinha = 0;
    for ( Iterator ite = relationEgDetailFncVO.getBaseTplRelationEgEntityArray().iterator(); ite.hasNext(); indexLinha++ )
    {
      egEntity = ( TplRelationEgEntity ) ite.next();
      egEntityVO = ( TplRelationEgEntityVO ) egEntity.getData();

      String strReltnlNbr = "";
      if ( egEntityVO.getReltnNbr() != null )
      {
        strReltnlNbr = egEntityVO.getReltnNbr().toString();
      }
      grid[ indexLinha ][ RelationEgDetailForm.COL_POS_RETLN_NBR ] = strReltnlNbr;

      String strEgNbr = "";
      if ( egEntityVO.getEgNbr() != null )
      {
        strEgNbr = egEntityVO.getEgNbr();
      }
      grid[ indexLinha ][ RelationEgDetailForm.COL_POS_EG_NBR ] = strEgNbr;

      if ( egEntity.getRelationPrvtEntity() != null )
      {
        TplCustomerPrvtEntity tplCustomerPrvtEntity = null;

        tplCustomerPrvtEntity = egEntity.getRelationPrvtEntity().getCustomerPrvtCmplEntity1();
        if ( tplCustomerPrvtEntity != null
             && tplCustomerPrvtEntity.getData().getCustShortNameText() != null )
        {
          grid[ indexLinha ][ RelationEgDetailForm.COL_POS_CLIENT_OWNER ] = tplCustomerPrvtEntity.getData().getCustShortNameText();
        }
        else
        {
          grid[ indexLinha ][ RelationEgDetailForm.COL_POS_CLIENT_OWNER ] = "";
        }

        tplCustomerPrvtEntity = egEntity.getRelationPrvtEntity().getCustomerPrvtCmplEntity2();
        if ( tplCustomerPrvtEntity != null
             && tplCustomerPrvtEntity.getData().getCustShortNameText() != null )
        {
          grid[ indexLinha ][ RelationEgDetailForm.COL_POS_CUST_1 ] = tplCustomerPrvtEntity.getData().getCustShortNameText();
        }
        else
        {
          grid[ indexLinha ][ RelationEgDetailForm.COL_POS_CUST_1 ] = "";
        }

        tplCustomerPrvtEntity = egEntity.getRelationPrvtEntity().getCustomerPrvtCmplEntity3();
        if ( tplCustomerPrvtEntity != null
             && tplCustomerPrvtEntity.getData().getCustShortNameText() != null )
        {
          grid[ indexLinha ][ RelationEgDetailForm.COL_POS_CUST_2 ] = tplCustomerPrvtEntity.getData().getCustShortNameText();
        }
        else
        {
          grid[ indexLinha ][ RelationEgDetailForm.COL_POS_CUST_2 ] = "";
        }
      }
    }
    relationEgDetailForm.setEgRelationsGrid( grid );

  }
}