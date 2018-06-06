/*
 * Created on Apr 15, 2007
 *
 *
 */
package com.citibank.ods.modules.client.relationeg.functionality;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplRelationEgEntity;
import com.citibank.ods.entity.pl.TplRelationEgHistEntity;
import com.citibank.ods.entity.pl.TplRelationEgMovEntity;
import com.citibank.ods.entity.pl.TplRelationPrvtEntity;
import com.citibank.ods.entity.pl.valueobject.TplRelationEgEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRelationEgMovEntityVO;
import com.citibank.ods.modules.client.relationeg.form.RelationEgMovementDetailForm;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgDetailFncVO;
import com.citibank.ods.modules.client.relationeg.functionality.valueobject.RelationEgMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgDAO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgHistDAO;
import com.citibank.ods.persistence.pl.dao.TplRelationEgMovDAO;
import com.citibank.ods.persistence.pl.dao.TplRelationPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class RelationEgMovementDetailFnc extends BaseRelationEgDetailFnc
    implements ODSMovementDetailFnc
{

  /**
   * Realizar a atualização do registro
   */
  public void update( BaseFncVO fncVO_ )
  {

    TplRelationEgMovEntity egMovEntity;
    TplRelationEgMovEntityVO egMovEntityVO;

    this.validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      RelationEgMovementDetailFncVO egMovDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;
      String egNbr = egMovDetailFncVO.getEgNbr();
      String erNbr = egMovDetailFncVO.getErNbr();

      TplRelationEgMovDAO tplRelationEgMovDAO = ODSDAOFactory.getInstance().getTplRelationEgMovDAO();
      tplRelationEgMovDAO.deleteRelationsByEgNr(erNbr, egNbr );

      String operationOpernCode = TplRelationEgMovEntity.C_OPERN_CODE_INSERT;

      //Retira da lista final os ítens que foram excluídos na mesma
      // operação.
      int relationList = egMovDetailFncVO.getBaseTplRelationEgEntityArray().size();
      Iterator resultsIterator;

      for ( int count = 0; count < relationList; count++ )
      {

        resultsIterator = egMovDetailFncVO.getSelectedItemsInGrid().iterator();
        int max = 0;

        while ( resultsIterator.hasNext() )
        {
          resultsIterator.next();
          //exclui da MOV, caso seja exclusão de um item de inclusao que está ainda na MOV, ou caso seja a desistencia de um item de exclusao que ainda está na MOV
          if ( (  egMovDetailFncVO.getSelectedItemsInGrid().get( max ).equals("S" ) && egMovDetailFncVO.getDeletedItems().get( max ).equals("S" )  ) ||
        		  (  egMovDetailFncVO.getSelectedItemsInGrid().get( max ).equals("N" ) && egMovDetailFncVO.getDeletedItems().get( max ).equals("N" )  ))
          {
        	  egMovDetailFncVO.getBaseTplRelationEgEntityArray().remove( max );
        	  egMovDetailFncVO.getSelectedItemsInGrid().remove( max );
        	  egMovDetailFncVO.getDeletedItems().remove( max );
            break;
          }         
          max++;

        }

      }

      ArrayList list = egMovDetailFncVO.getBaseTplRelationEgEntityArray();
      for ( Iterator ite = list.iterator(); ite.hasNext(); )
      {
        egMovEntity = ( TplRelationEgMovEntity ) ite.next();
        egMovEntityVO = ( TplRelationEgMovEntityVO ) egMovEntity.getData();
        egMovEntityVO.setLastUpdDate( new Date() );
        egMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );

        if ( egMovEntityVO.getOpernCode() == null )
        {
          egMovEntityVO.setOpernCode( operationOpernCode );
        }

        tplRelationEgMovDAO.insert( egMovEntity );
        
      }
    }
  }

  /**
   * Realiza a aprovacao das associações EG x Relacionamentos
   */
  public void approve( BaseFncVO fncVO_ )
  {
    TplRelationEgMovEntity tplRelationEgMovEntity;
    TplRelationEgMovEntityVO tplRelationEgMovEntityVO;
    TplRelationEgEntity tplRelationEgEntity;
    TplRelationEgHistEntity tplRelationEgHistEntity;

    this.validateApprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      RelationEgMovementDetailFncVO egMovDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;
      //Chaves da tabela RelationEGMov - ER_NBR e EG_NBR
      String egNbr = egMovDetailFncVO.getEgNbr();
      String erNbr = egMovDetailFncVO.getErNbr();

      TplRelationEgDAO tplRelationEgDAO = ODSDAOFactory.getInstance().getTplRelationEgDAO();
      TplRelationEgHistDAO tplRelationEgHistDAO = ODSDAOFactory.getInstance().getTplRelationEgHistDAO();

      // Se existe associações na Current, copiar para histórico
      // e remover registros de Current
      if ( tplRelationEgDAO.existsRelation( egNbr, null, erNbr ) )
      {
        ArrayList list = tplRelationEgDAO.listByEgNbr( egNbr, erNbr );
        if ( list != null && !list.isEmpty() )
        {
          Date date = new Date();
          for ( Iterator ite = list.iterator(); ite.hasNext(); )
          {
            tplRelationEgEntity = ( TplRelationEgEntity ) ite.next();
            tplRelationEgHistEntity = new TplRelationEgHistEntity(
                                                                   tplRelationEgEntity,
                                                                   date );

            tplRelationEgHistDAO.insert( tplRelationEgHistEntity );
          }
        }
      }

      // Inserir os novos dados, se for Update ou Insert
      ArrayList approveList = egMovDetailFncVO.getBaseTplRelationEgEntityArray();
      if ( approveList != null && !approveList.isEmpty() )
      {
        Date approveDate = new Date();
        String authUserId = fncVO_.getLoggedUser().getUserID();

        for ( Iterator ite = approveList.iterator(); ite.hasNext(); )
        {
          tplRelationEgMovEntity = ( TplRelationEgMovEntity ) ite.next();
          tplRelationEgMovEntityVO = ( TplRelationEgMovEntityVO ) tplRelationEgMovEntity.getData();

          tplRelationEgEntity = new TplRelationEgEntity(
                                                         tplRelationEgMovEntity,
                                                         authUserId,
                                                         approveDate,
                                                         TplRelationEgEntity.C_REC_STAT_CODE_ACTIVE );

          if ( TplRelationEgMovEntity.C_OPERN_CODE_DELETE.equals( tplRelationEgMovEntityVO.getOpernCode() ) )
          {
            ( ( TplRelationEgEntityVO ) tplRelationEgEntity.getData() ).setRecStatCode( TplRelationEgEntity.C_REC_STAT_CODE_INACTIVE );
            tplRelationEgDAO.update( tplRelationEgEntity );

          }
          else
          {
            if ( tplRelationEgDAO.existsRelation(
                                                  egNbr,
                                                  tplRelationEgMovEntityVO.getReltnNbr(),
                                                  erNbr ) )
            {
              tplRelationEgDAO.update( tplRelationEgEntity );
            }
            else
            {
              tplRelationEgDAO.insert( tplRelationEgEntity );
            }

          }

          TplRelationEgMovDAO tplRelationEgMovDAO = ODSDAOFactory.getInstance().getTplRelationEgMovDAO();
          tplRelationEgMovDAO.deleteRelations( erNbr, egNbr , tplRelationEgMovEntityVO.getReltnNbr());
        }

      
      }
    }
  }

  /**
   * Realiza a reprovação das associações
   */
  public void reprove( BaseFncVO fncVO_ )
  {
	    TplRelationEgMovEntity tplRelationEgMovEntity;
	    TplRelationEgMovEntityVO tplRelationEgMovEntityVO;
	    
    this.validateReprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      RelationEgMovementDetailFncVO egMovDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;
      String egNbr = egMovDetailFncVO.getEgNbr();
      String erNbr = egMovDetailFncVO.getErNbr();

      TplRelationEgMovDAO tplRelationEgMovDAO = ODSDAOFactory.getInstance().getTplRelationEgMovDAO();
      
      ArrayList reproveList = egMovDetailFncVO.getBaseTplRelationEgEntityArray();
      if ( reproveList != null && !reproveList.isEmpty() )
      {
        for ( Iterator ite = reproveList.iterator(); ite.hasNext(); )
        {
          tplRelationEgMovEntity = ( TplRelationEgMovEntity ) ite.next();
          tplRelationEgMovEntityVO = ( TplRelationEgMovEntityVO ) tplRelationEgMovEntity.getData();
          tplRelationEgMovDAO.deleteRelations( erNbr, egNbr, tplRelationEgMovEntityVO.getReltnNbr() );
        }
      }
    }
  }

  /**
   * Realiza as validações de Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    RelationEgMovementDetailFncVO egMovDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;
    String lastUpdUserId = getLastUpdUserFromList( egMovDetailFncVO );

    //  testar usuário
    if ( egMovDetailFncVO.getLoggedUser() == null
         || !egMovDetailFncVO.getLoggedUser().getUserID().equals( lastUpdUserId ) )
    {
      egMovDetailFncVO.addError( RelationEgMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }
    
    TplRelationEgDAO tplRelationEgDAO = OracleODSDAOFactory.getInstance().getTplRelationEgDAO();
    ArrayList list = egMovDetailFncVO.getBaseTplRelationEgEntityArray();

    Iterator it = list.iterator();
  
    int count = 0;
      while ( it.hasNext() ) {
    	  TplRelationEgMovEntity tplRelationEgEntity = (TplRelationEgMovEntity)it.next();
    	  if  (egMovDetailFncVO.getSelectedItemsInGrid().get( count ).equals( "S" ) &&
    			  egMovDetailFncVO.getDeletedItems().get( count ).equals( "N" )) {        	  
    		  //Valida se o relacionamento já tem EG associado (1 relacionamento só pode ter 1 EG associado), se nao for exclusao (de uma inserção que ainda esta no MOV) 
   	  	  	if ( tplRelationEgDAO.existsEG( tplRelationEgEntity.getData().getReltnNbr() ) )
   	  	  		{
   	  	  			egMovDetailFncVO.addError(
                                              RelationEgDetailFncVO.C_ERROR_DUPLICATE_ASSOCIATION_RELTN_EG,
                                              tplRelationEgEntity.getData().getReltnNbr().toString() );
   	  	  		}
    	  }
   	  	  count++;
      }
      

  }

  /**
   * Realiza as validações da aprovação
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    RelationEgMovementDetailFncVO egMovDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;
    String lastUpdUserId = getLastUpdUserFromList( egMovDetailFncVO );

    // testar usuário
    if ( egMovDetailFncVO.getLoggedUser() == null
         || egMovDetailFncVO.getLoggedUser().getUserID().equals( lastUpdUserId ) )
    {
      egMovDetailFncVO.addError( RelationEgMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }

  }

  /*
   * Recupera o usuário da última atualização dos registros
   */
  private String getLastUpdUserFromList(
                                        RelationEgMovementDetailFncVO egMovDetailFncVO )
  {
    ArrayList list = egMovDetailFncVO.getBaseTplRelationEgEntityArray();
    if ( list != null && list.size() > 0 )
    {
      TplRelationEgMovEntity tplRelationEgMovEntity = ( TplRelationEgMovEntity ) list.iterator().next();

      return tplRelationEgMovEntity.getData().getLastUpdUserId();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
  }

  /**
   * Carregamentos iniciais para a atualização
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.loadErNbr( fncVO_ );
    RelationEgMovementDetailFncVO egMovDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;

    //Número EG_NBR e ER_NBR chaves da tabela de RelationEgMov
    String egNbr = egMovDetailFncVO.getEgNbr();
    String erNbr = egMovDetailFncVO.getErNbr();

    TplRelationEgMovDAO tplRelationEgMovDAO = ODSDAOFactory.getInstance().getTplRelationEgMovDAO();

    ArrayList list = tplRelationEgMovDAO.listByEgNbr( egNbr, erNbr );
    if ( list != null && list.size() > 0 )
    {
      TplRelationEgMovEntity relationEgMovEntity = ( TplRelationEgMovEntity ) list.iterator().next();
      TplRelationEgMovEntityVO egMovEntityVO = ( TplRelationEgMovEntityVO ) relationEgMovEntity.getData();

      egMovDetailFncVO.setOpernCode( egMovEntityVO.getOpernCode() );
      egMovDetailFncVO.setBaseTplRelationEgEntityArray( list );
      
      egMovDetailFncVO.setSelectedItemsInGrid( new ArrayList() );
      egMovDetailFncVO.setDeletedItems( new ArrayList() );

 
 	  for ( Iterator ite = list.iterator(); ite.hasNext(); )
  	  {
 		 TplRelationEgMovEntity relEgMovEntity = ( TplRelationEgMovEntity ) ite.next(); 
 		 TplRelationEgMovEntityVO relEgMovEntityVO = ( TplRelationEgMovEntityVO ) relEgMovEntity.getData();
 	      if (relEgMovEntityVO.getOpernCode().equals(TplRelationEgEntity.C_OPERN_CODE_DELETE)) {
 	    	  egMovDetailFncVO.getSelectedItemsInGrid().add("N");
 	    	  egMovDetailFncVO.getDeletedItems().add("S");
 	      } else if (relEgMovEntityVO.getOpernCode().equals(TplRelationEgEntity.C_OPERN_CODE_INSERT)) {
 	    	  egMovDetailFncVO.getSelectedItemsInGrid().add("S");
 	    	  egMovDetailFncVO.getDeletedItems().add("N");     
 	      } else {
 	    	  egMovDetailFncVO.getSelectedItemsInGrid().add("N");
 	    	  egMovDetailFncVO.getDeletedItems().add("N");   	    	  
 	      }
 	     
  	  }
    }
    
    
  }

  /**
   * Carregamentos iniciais para a aprovação
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.loadErNbr( fncVO_ );
    RelationEgMovementDetailFncVO egMovDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;

    //Número EG_NBR e ER_NBR chaves da tabela de RelationEgMov
    String egNbr = egMovDetailFncVO.getEgNbr();
    String erNbr = egMovDetailFncVO.getErNbr();

    TplRelationEgMovDAO tplRelationEgMovDAO = ODSDAOFactory.getInstance().getTplRelationEgMovDAO();

    ArrayList list = tplRelationEgMovDAO.listByEgNbr( egNbr, erNbr );
    egMovDetailFncVO.setBaseTplRelationEgEntityArray( list );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {

  }

  /**
   * Retorna uma instancia de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new RelationEgMovementDetailFncVO();
  }

  /**
   * Atualiza as informações do Form com os valores vindos do FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    TplRelationEgMovEntity egEntity;
    TplRelationEgMovEntityVO egEntityVO;

    super.updateFormFromFncVO( form_, fncVO_ );

    RelationEgMovementDetailForm relationEgDetailForm = ( RelationEgMovementDetailForm ) form_;
    RelationEgMovementDetailFncVO relationEgDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;

    String[][] grid = new String[ relationEgDetailFncVO.getBaseTplRelationEgEntityArray().size() ][ 6 ];
    int indexLinha = 0;
    for ( Iterator ite = relationEgDetailFncVO.getBaseTplRelationEgEntityArray().iterator(); ite.hasNext(); indexLinha++ )
    {
      egEntity = ( TplRelationEgMovEntity ) ite.next();
      egEntityVO = ( TplRelationEgMovEntityVO ) egEntity.getData();

      String strReltnlNbr = "";
      if ( egEntityVO.getReltnNbr() != null )
      {
        strReltnlNbr = egEntityVO.getReltnNbr().toString();
      }
      grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_RETLN_NBR ] = strReltnlNbr;

      String strEgNbr = "";
      if ( egEntityVO.getEgNbr() != null )
      {
        strEgNbr = egEntityVO.getEgNbr();
      }
      grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_EG_NBR ] = strEgNbr;

      if ( egEntity.getRelationPrvtEntity() != null )
      {
        TplCustomerPrvtEntity tplCustomerPrvtEntity = null;

        tplCustomerPrvtEntity = egEntity.getRelationPrvtEntity().getCustomerPrvtCmplEntity1();
        if ( tplCustomerPrvtEntity != null
             && tplCustomerPrvtEntity.getData().getCustShortNameText() != null )
        {
          grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_CLIENT_OWNER ] = tplCustomerPrvtEntity.getData().getCustShortNameText();
        }
        else
        {
          grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_CLIENT_OWNER ] = "";
        }

        tplCustomerPrvtEntity = egEntity.getRelationPrvtEntity().getCustomerPrvtCmplEntity2();
        if ( tplCustomerPrvtEntity != null
             && tplCustomerPrvtEntity.getData().getCustShortNameText() != null )
        {
          grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_CUST_1 ] = tplCustomerPrvtEntity.getData().getCustShortNameText();
        }
        else
        {
          grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_CUST_1 ] = "";
        }

        tplCustomerPrvtEntity = egEntity.getRelationPrvtEntity().getCustomerPrvtCmplEntity3();
        if ( tplCustomerPrvtEntity != null
             && tplCustomerPrvtEntity.getData().getCustShortNameText() != null )
        {
          grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_CUST_2 ] = tplCustomerPrvtEntity.getData().getCustShortNameText();
        }
        else
        {
          grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_CUST_2 ] = "";
        }
        if ( egEntityVO != null && egEntityVO.getOpernCode() != null )
        {
          grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_OPERN_CODE ] = ODSConstraintDecoder.decodeOpern( egEntityVO.getOpernCode() );
        }
        else
        {
          grid[ indexLinha ][ RelationEgMovementDetailForm.COL_POS_OPERN_CODE ] = "";
        }

      }
      relationEgDetailForm.setOpernCode( egEntityVO.getOpernCode() );
      relationEgDetailForm.setLastUpdUserId( egEntityVO.getLastUpdUserId() );
    }

    relationEgDetailForm.setEgRelationsGrid( grid );
  }

  /*
   * Insere o domínio no Grid
   */
  public void insertDomain( BaseFncVO fncVO_ )
  {
    RelationEgMovementDetailFncVO relationEgDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;
    this.validateInsertDomain( relationEgDetailFncVO );
    if ( !relationEgDetailFncVO.hasErrors() )
    {

      TplRelationPrvtEntity relationPrvtEntity = super.findRelationByPk( relationEgDetailFncVO.getReltnNbr() );
      if ( relationPrvtEntity != null )
      {
        TplRelationEgMovEntity tplRelationEgEntity = new TplRelationEgMovEntity();
        tplRelationEgEntity.getData().setEgNbr(
                                                relationEgDetailFncVO.getEgNbr() );
        tplRelationEgEntity.getData().setErNbr(
                                                relationEgDetailFncVO.getErNbr() );
        tplRelationEgEntity.getData().setReltnNbr(
                                                   relationEgDetailFncVO.getReltnNbr() );
        ( ( TplRelationEgMovEntityVO ) tplRelationEgEntity.getData() ).setOpernCode( TplRelationEgEntity.C_OPERN_CODE_INSERT );
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
                                        RelationEgMovementDetailFncVO.C_ERROR_REG_NOT_EXISTS,
                                        RelationEgMovementDetailFncVO.C_RELTN_NBR_DESCRIPTION );
      }
    }
  }

  /*
   * Realiza as validações do insert domain
   */
  private void validateInsertDomain(
                                    RelationEgMovementDetailFncVO relationEgDetailFncVO_ )
  {
    relationEgDetailFncVO_.clearErrors();

    if ( relationEgDetailFncVO_.getReltnNbr() == null )
    {
      relationEgDetailFncVO_.addError(
                                       RelationEgMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                                       RelationEgMovementDetailFncVO.C_RELTN_NBR_DESCRIPTION );
    }

    if ( relationEgDetailFncVO_.getEgNbr() == null
         || relationEgDetailFncVO_.getEgNbr().equals( "" ) )
    {
      relationEgDetailFncVO_.addError(
                                       RelationEgMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                                       RelationEgMovementDetailFncVO.C_EG_NBR_DESCRIPTION );
    }

    if ( !relationEgDetailFncVO_.hasErrors() )
    {
      ArrayList list = relationEgDetailFncVO_.getBaseTplRelationEgEntityArray();
      TplRelationEgMovEntity tplRelationEgEntity = new TplRelationEgMovEntity();
      tplRelationEgEntity.getData().setEgNbr( relationEgDetailFncVO_.getEgNbr() );
      tplRelationEgEntity.getData().setReltnNbr(
                                                 relationEgDetailFncVO_.getReltnNbr() );
      if ( list.contains( tplRelationEgEntity ) )
      {
        relationEgDetailFncVO_.addError( RelationEgMovementDetailFncVO.C_ERROR_DUPLICATED_ELEMENT_IN_GRID );
      }
    }

    if ( !relationEgDetailFncVO_.hasErrors() )
    {
      TplRelationPrvtEntity prvtEntity = new TplRelationPrvtEntity();
      prvtEntity.getData().setReltnNbr( relationEgDetailFncVO_.getReltnNbr() );

      TplRelationPrvtDAO relationPrvtDAO = OracleODSDAOFactory.getInstance().getTplRelationPrvtDAO();
      if ( !relationPrvtDAO.existsActive( prvtEntity ) )
      {
        relationEgDetailFncVO_.addError(
                                         RelationEgMovementDetailFncVO.C_ERROR_REG_NOT_EXISTS,
                                         RelationEgMovementDetailFncVO.C_RELTN_NBR_DESCRIPTION );
      }
    }
  }

  /*
   * Remove o domínio do Grid
   */
  public void deleteDomain( BaseFncVO fncVO_ )
  {
    RelationEgMovementDetailFncVO relationEgDetailFncVO = ( RelationEgMovementDetailFncVO ) fncVO_;
    ArrayList list = relationEgDetailFncVO.getBaseTplRelationEgEntityArray();

    TplRelationEgMovEntity tplRelationEgEntity = new TplRelationEgMovEntity();
    tplRelationEgEntity.getData().setEgNbr(
                                            relationEgDetailFncVO.getSelectedEgNbrInGrid() );
    tplRelationEgEntity.getData().setReltnNbr(
                                               relationEgDetailFncVO.getSelectedReltnNbrInGrid() );

    list.remove( tplRelationEgEntity );
    relationEgDetailFncVO.clearErrors();
  }

}