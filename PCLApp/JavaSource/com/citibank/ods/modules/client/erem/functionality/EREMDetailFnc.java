package com.citibank.ods.modules.client.erem.functionality;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.exception.NoRowsReturnedException;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtCmplEntity;
import com.citibank.ods.entity.pl.TplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.TplErEmEntity;
import com.citibank.ods.entity.pl.TplErEmMovEntity;
import com.citibank.ods.entity.pl.TplErEntity;
import com.citibank.ods.entity.pl.TplErMovEntity;
import com.citibank.ods.entity.pl.TplRoleCustEntity;
import com.citibank.ods.entity.pl.valueobject.TplErEmEntityVO;
import com.citibank.ods.modules.client.erem.form.EREMDetailForm;
import com.citibank.ods.modules.client.erem.functionality.valueobject.BaseEREMDetailFncVO;
import com.citibank.ods.modules.client.erem.functionality.valueobject.EREMDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplErEmDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplCustomerPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplErDAO;
import com.citibank.ods.persistence.pl.dao.TplErEmDAO;
import com.citibank.ods.persistence.pl.dao.TplErEmMovDAO;
import com.citibank.ods.persistence.pl.dao.TplErMovDAO;
import com.citibank.ods.persistence.pl.dao.TplRoleCustDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.factory.OracleODSDAOFactory;

/**
 * @author leonardo.nakada
 *  
 */
public class EREMDetailFnc extends BaseEREMDetailFnc implements ODSDetailFnc
{

  /**
   * Retorna a instância do DAO utilizado pelo Detail
   */
  protected BaseTplErEmDAO getDAO()
  {
    return null;
  }

  /**
   * Retorna uma instancia de um FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new EREMDetailFncVO();
  }

  /**
   * Realiza a inserção dos dados
   */
  public void insert( BaseFncVO fncVO_ )
  {
    TplErEmEntity tplErEmEntity;
    TplErEmMovEntity tplErEmMovEntity;

    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      EREMDetailFncVO eremDetailFncVO = ( EREMDetailFncVO ) fncVO_;

      TplErEmMovDAO tplErEmMovDAO = ODSDAOFactory.getInstance().getTplErEmMovDAO();

      for ( int i = 0; i < eremDetailFncVO.getSelectedItemsInGrid().size(); i++ )
      {
        for ( int j = 0; j < eremDetailFncVO.getBaseTplErEmEntities().size(); j++ )
        {
          if ( eremDetailFncVO.getSelectedItemsInGrid().get( i ).equals( "S" )
               && eremDetailFncVO.getDeletedItems().get( i ).equals( "S" ) )
          {
            eremDetailFncVO.getBaseTplErEmEntities().remove( j );
            break;
          }

        }
      }

      ArrayList relations = eremDetailFncVO.getBaseTplErEmEntities();

      for ( Iterator ite = relations.iterator(); ite.hasNext(); )
      {
        tplErEmEntity = ( TplErEmEntity ) ite.next();
        tplErEmEntity.getData().setLastUpdDate( new Date() );
        tplErEmEntity.getData().setLastUpdUserId(
                                                  fncVO_.getLoggedUser().getUserID() );

        tplErEmMovEntity = new TplErEmMovEntity(
                                                 tplErEmEntity,
                                                 TplErEmMovEntity.C_OPERN_CODE_INSERT );

        tplErEmMovDAO.insert( tplErEmMovEntity );
      }
    }
  }

  /**
   * Realiza a atualização dos dados
   */
  public void update( BaseFncVO fncVO_ )
  {

    TplErEmEntity egEntity;
    TplErEmMovEntity egMovEntity = null;

    this.validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      EREMDetailFncVO eremDetailFncVO = ( EREMDetailFncVO ) fncVO_;
      TplErEmMovDAO TplErEmMovDAO = OracleODSDAOFactory.getInstance().getTplErEmMovDAO();

      //Retira da lista final os ítens que foram inseridos e excluídos na mesma
      // operação.
      int relationList = eremDetailFncVO.getBaseTplErEmEntities().size();
      Iterator resultsIterator;

      for ( int count = 0; count < relationList; count++ )
      {

        resultsIterator = eremDetailFncVO.getSelectedItemsInGrid().iterator();
        int max = 0;

        while ( resultsIterator.hasNext() )
        {
          resultsIterator.next();
          if ( eremDetailFncVO.getSelectedItemsInGrid().get( max ).equals( "S" )
               && eremDetailFncVO.getDeletedItems().get( max ).equals( "S" ) )
          {
            eremDetailFncVO.getBaseTplErEmEntities().remove( max );
            eremDetailFncVO.getSelectedItemsInGrid().remove( max );
            eremDetailFncVO.getDeletedItems().remove( max );
            break;
          }
          else if ( eremDetailFncVO.getSelectedItemsInGrid().get( max ).equals(
                                                                                "N" )
                    && eremDetailFncVO.getDeletedItems().get( max ).equals( "N" ) )
          {
            eremDetailFncVO.getBaseTplErEmEntities().remove( max );
            eremDetailFncVO.getSelectedItemsInGrid().remove( max );
            eremDetailFncVO.getDeletedItems().remove( max );
            break;
          }
          max++;

        }

      }

      ArrayList relations = eremDetailFncVO.getBaseTplErEmEntities();
      int count = 0;
      for ( Iterator ite = relations.iterator(); ite.hasNext(); count++ )
      {
        egEntity = ( TplErEmEntity ) ite.next();
        egEntity.getData().setLastUpdDate( new Date() );
        egEntity.getData().setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );

        if ( eremDetailFncVO.getSelectedItemsInGrid().get( count ).equals( "S" )
             && eremDetailFncVO.getDeletedItems().get( count ).equals( "N" ) )
        {

          egMovEntity = new TplErEmMovEntity(
                                              egEntity,
                                              TplErEmMovEntity.C_OPERN_CODE_INSERT );
        }
        else if ( eremDetailFncVO.getSelectedItemsInGrid().get( count ).equals(
                                                                                "N" )
                  && eremDetailFncVO.getDeletedItems().get( count ).equals( "S" ) )
        {
          egMovEntity = new TplErEmMovEntity(
                                              egEntity,
                                              TplErEmMovEntity.C_OPERN_CODE_DELETE );
        }

        TplErEmMovDAO.insert( egMovEntity );
      }
    }
  }

  /**
   * Realiza o delete do registro
   */
  public void delete( BaseFncVO fncVO_ )
  {

    TplErEmEntity egEntity;
    TplErEmMovEntity egMovEntity;

    this.validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      EREMDetailFncVO EREMDetailFncVO = ( EREMDetailFncVO ) fncVO_;
      ArrayList relations = EREMDetailFncVO.getBaseTplErEmEntities();
      TplErEmMovDAO TplErEmMovDAO = OracleODSDAOFactory.getInstance().getTplErEmMovDAO();
      
      List<String> listEmNbr = new ArrayList<String>();

      for ( Iterator ite = relations.iterator(); ite.hasNext(); )
      {
        egEntity = ( TplErEmEntity ) ite.next();
        egMovEntity = new TplErEmMovEntity( egEntity,
                                            TplErEmEntity.C_OPERN_CODE_DELETE );
        egMovEntity.getData().setLastUpdDate( new Date() );
        egMovEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID() : "" );
        //Verifica se Em já foi incluido na tabela de movimento. Essa validacao corrigiu o problema qdo há mais de 1 cliente com o mesmo EM, ou seja, insere apenas uma vez o ER x EM
        if (!listEmNbr.contains(egMovEntity.getData().getEmNbr())){ 
        	TplErEmMovDAO.insert( egMovEntity );
            listEmNbr.add(egMovEntity.getData().getEmNbr());
        }
      }
      
      //Verifica se existe Er relacionado	  
	  TplErEntity tplErEntity = this.findEr(EREMDetailFncVO);
	  if(tplErEntity!= null){
	  	//Verifica se o Er relacionado não está em movimento 
	  	if(!this.existsERInMovement(EREMDetailFncVO)){
			
			tplErEntity.getData().setLastUpdDate( new Date() );
			tplErEntity.getData().setRecStatCode(TplErMovEntity.C_REC_STAT_CODE_ACTIVE);
			tplErEntity.getData().setLastUpdUserId(fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID() : "" );
			tplErEntity.getData().setReltnEndReasCode(EREMDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasCode());
			tplErEntity.getData().setReltnEndReasText(EREMDetailFncVO.getBaseTplErEntity().getData().getReltnEndReasText());		
		
			TplErMovEntity tplErMovEntity = new TplErMovEntity(tplErEntity,TplErMovEntity.C_OPERN_CODE_DELETE );		                                                     
			TplErMovDAO tplErMovDAO = ODSDAOFactory.getInstance().getTplErMovDAO();
			tplErMovDAO.insert( tplErMovEntity );
	  	}
	  	
	  }      
      
    }      
    	
  }

  

  /**
   * Validações da Inserção
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    EREMDetailFncVO eremDetailFncVO = ( EREMDetailFncVO ) fncVO_;
    ArrayList list = eremDetailFncVO.getBaseTplErEmEntities();

    //Verifica se existe items selecionados para exclusão se existir verifica
    // se o tamanho da lista de ítens exclúidos e do mesmo tamanho da lista
    // final
    int findItemsDeleted = 0;
    for ( int i = 0; i < eremDetailFncVO.getDeletedItems().size(); i++ )
    {
      if ( eremDetailFncVO.getDeletedItems().get( i ).equals( "S" ) )
      {
        findItemsDeleted++;

      }
    }

    if ( findItemsDeleted == list.size() )
    {
      eremDetailFncVO.addError( EREMDetailFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                                EREMDetailFncVO.C_INSERT,
                                EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
    }
    else
    {
      TplErEmEntity firstRelationErEntityInArray = ( TplErEmEntity ) list.iterator().next();
      String firstErNbrInArray = firstRelationErEntityInArray.getData().getErNbr();
      TplErEmDAO tplErEmDAO = OracleODSDAOFactory.getInstance().getTplErEmDAO();
      
	  if ( !eremDetailFncVO.hasErrors() )
	  {
		  //Verifica se o registro ER existe.
		  TplErEntity tplErEntity = this.findEr(eremDetailFncVO);
		  if(tplErEntity == null){
			eremDetailFncVO.addError( EREMDetailFncVO.C_ERROR_REG_NOT_EXISTS,
								EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
		  }
	  }

      TplErEmMovDAO tplErEmMovDAO = OracleODSDAOFactory.getInstance().getTplErEmMovDAO();
      if ( tplErEmMovDAO.existsRelation( firstErNbrInArray ) )
      {
        eremDetailFncVO.addError( EREMDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                                  EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
      }

      Iterator itErEM = list.iterator();
      
      //Valida cada 
      while  (itErEM.hasNext()){
    	  TplErEmEntity erEmEntity = (TplErEmEntity)itErEM.next();
    	  if ( tplErEmDAO.existsRelationActive( firstErNbrInArray, erEmEntity.getData().getEmNbr() ) )
    	  {
    		  eremDetailFncVO.addError(
    				  EREMDetailFncVO.C_ERROR_DUPLICATE_ASSOCIATION_ER_EM,
    				  erEmEntity.getData().getEmNbr(), firstErNbrInArray );
    	  }
      }

      
      
    }
  }

  /**
   * Realiza as validações de Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    EREMDetailFncVO eremDetailFncVO = ( EREMDetailFncVO ) fncVO_;
    ArrayList list = eremDetailFncVO.getBaseTplErEmEntities();

    int findItemsDeleted = 0;

    for ( int i = 0; i < eremDetailFncVO.getDeletedItems().size(); i++ )
    {
      if ( eremDetailFncVO.getDeletedItems().get( i ).equals( "S" ) )
      {
        findItemsDeleted++;

      }
    }

    //Validar se existe associações para alterar
    if ( findItemsDeleted == list.size() )
    {
      eremDetailFncVO.addError( EREMDetailFncVO.C_ERROR_MANDATORY_ASSOCIATION,
                                EREMDetailFncVO.C_UPDATE,
                                EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
    }
    else
    {
      //verificar se existe registro em movimento
      if ( this.existsInMovement( eremDetailFncVO ) )
      {
        eremDetailFncVO.addError( EREMDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                                  EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
      }
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    EREMDetailFncVO eremDetailFncVO = ( EREMDetailFncVO ) fncVO_;
    ArrayList list = eremDetailFncVO.getBaseTplErEmEntities();

    TplErEmEntity firstRelationEgEntityInArray = ( TplErEmEntity ) list.iterator().next();
    String firstEgNbrInArray = firstRelationEgEntityInArray.getData().getErNbr();
    TplErEmMovDAO TplErEmMovDAO = OracleODSDAOFactory.getInstance().getTplErEmMovDAO();

    if ( TplErEmMovDAO.existsRelation( firstEgNbrInArray ) )
    {
      eremDetailFncVO.addError( EREMDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                                EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    EREMDetailFncVO erEmDetailFncVO = ( EREMDetailFncVO ) fncVO_;

    String erNbr = erEmDetailFncVO.getBaseTplErEmEntity().getData().getErNbr();
    TplErEmDAO tplRelationErDAO = ODSDAOFactory.getInstance().getTplErEmDAO();
    
	super.loadEr( erEmDetailFncVO );

    ArrayList list = tplRelationErDAO.listByErNbr( erNbr, null );
    if ( list != null )
    {
      erEmDetailFncVO.setBaseTplErEmEntities( list );
    }
    
	super.loadDomains( erEmDetailFncVO );
  }

  /**
   * Carregamentos iniciais - Insert
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    EREMDetailFncVO detailFncVO = ( EREMDetailFncVO ) fncVO_;
    super.loadDomains( detailFncVO );
    if ( detailFncVO.isFromSearch() )
    {
      loadCustText( detailFncVO );
      loadEmNbr( detailFncVO );
      detailFncVO.setFromSearch( false );
    }
    else
    {
      ArrayList list = new ArrayList();
      detailFncVO.setBaseTplErEmEntities( list );
      detailFncVO.getBaseTplErEmEntity().getData().setRoleCustCode( null );
      detailFncVO.getBaseTplErEmEntity().getData().setErNbr( null );
      detailFncVO.getBaseTplErEmEntity().getData().setEmNbr( null );
      detailFncVO.setCustFullNameText( null );
      detailFncVO.setCustNbr( null );

    }
  }

  protected void loadCustFullNameText( BaseEREMDetailFncVO erEMDetailFncVO_ )
  {

    if ( !erEMDetailFncVO_.getBaseTplErEmEntity().getData().getEmNbr().equals(
                                                                               "" ) )
    {

      TplCustomerPrvtCmplEntity customerPrvtcmplEntity = new TplCustomerPrvtCmplEntity();
      customerPrvtcmplEntity.getData().setEmNbr(
                                                 erEMDetailFncVO_.getBaseTplErEmEntity().getData().getEmNbr() );

      //Obtém a instância da Factory
      ODSDAOFactory factory = ODSDAOFactory.getInstance();
      //Obtém a instância do DAO necessário
      TplCustomerPrvtCmplDAO tplCustomerPrvtDAO = factory.getTplCustomerPrvtCmplDAO();

      //Realiza a consulta no DAO
      customerPrvtcmplEntity = ( TplCustomerPrvtCmplEntity ) tplCustomerPrvtDAO.findEmNbr( customerPrvtcmplEntity );

      TplCustomerPrvtDAO tplCustomerDAO = factory.getTplCustomerPrvtDAO();

      BaseTplCustomerPrvtEntity customerPrvtEntity = new BaseTplCustomerPrvtEntity();
      customerPrvtEntity.getData().setCustNbr(
                                               customerPrvtcmplEntity.getData().getCustNbr() );

      customerPrvtEntity = tplCustomerDAO.find( customerPrvtEntity );

      //Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
      erEMDetailFncVO_.setCustFullNameText( customerPrvtEntity.getData().getCustFullNameText() );
    }
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    EREMDetailFncVO erDetailFncVO = ( EREMDetailFncVO ) fncVO_;
    if ( this.existsInMovement( erDetailFncVO ) )
    {
    	fncVO_.addError( EREMDetailFncVO.C_ERROR_IN_MOVEMENT);
    }
    else
    {
      super.loadDomains( erDetailFncVO );

      if ( erDetailFncVO.isFromSearch() )
      {
        loadCustText( erDetailFncVO );
        loadEmNbr( erDetailFncVO );
        erDetailFncVO.setFromSearch( false );
      }
      else
      {
        String erNbr = erDetailFncVO.getBaseTplErEmEntity().getData().getErNbr();
        TplErEmDAO tplRelationErDAO = ODSDAOFactory.getInstance().getTplErEmDAO();

        ArrayList list = tplRelationErDAO.listByErNbr( erNbr, null );
        if ( list != null )
        {
          erDetailFncVO.setBaseTplErEmEntities( list );
        }

        erDetailFncVO.getBaseTplErEmEntity().getData().setEmNbr( null );
        erDetailFncVO.getBaseTplErEmEntity().getData().setRoleCustCode( null );
        erDetailFncVO.setCustFullNameText( null );
        erDetailFncVO.setCustNbr( null );

      }
    }

  }

  /**
   * Carregamentos iniciais - Delete
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
	
    EREMDetailFncVO erDetailFncVO = ( EREMDetailFncVO ) fncVO_;
	
    if ( this.existsInMovement( erDetailFncVO ) )
    {
       erDetailFncVO.addError( EREMDetailFncVO.C_ERROR_RELATION_IN_MOVEMENT,
                              EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
    }
    else
    {
      String erNbr = erDetailFncVO.getBaseTplErEmEntity().getData().getErNbr();
      TplErEmDAO tplRelationErDAO = ODSDAOFactory.getInstance().getTplErEmDAO();

      ArrayList list = tplRelationErDAO.listByErNbr( erNbr, null );
      if ( list != null )
      {
        erDetailFncVO.setBaseTplErEmEntities( list );
      }
      super.loadDomains(erDetailFncVO);
    }
  }

  /*
   * Realiza as validações do insert domain
   */
  private void validateInsertDomain( EREMDetailFncVO detailFncVO_ )
  {
    EREMDetailFncVO detailFncVO = ( EREMDetailFncVO ) detailFncVO_;
    TplErEmEntityVO erEmEntityVO = ( TplErEmEntityVO ) detailFncVO.getBaseTplErEmEntity().getData();

    // Validação de campos obrigatórios

    if ( erEmEntityVO.getErNbr() == null || erEmEntityVO.getErNbr().equals( "" ) )
    {
      detailFncVO.addError( EREMDetailFncVO.C_ERROR_MANDATORY_FIELD,
                            EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
    }

    if ( erEmEntityVO.getEmNbr() == null || erEmEntityVO.getEmNbr().equals( "" ) )
    {
      detailFncVO.addError( EREMDetailFncVO.C_ERROR_MANDATORY_FIELD,
                            EREMDetailFncVO.C_EM_NBR_DESCRIPTION );
    }

    if ( erEmEntityVO.getRoleCustCode() == null
         || erEmEntityVO.getRoleCustCode().equals( "" ) )
    {
      detailFncVO.addError( EREMDetailFncVO.C_ERROR_MANDATORY_FIELD,
                            EREMDetailFncVO.C_ROLE_CUST_CODE_DESCRIPTION );
    }
    
    if ( !detailFncVO.hasErrors() )
    {
      // Valida se o registro a ser inserido
      // possui o mesmo ER dos outros
      ArrayList list = detailFncVO.getBaseTplErEmEntities();
      if ( !list.isEmpty() )
      {
        TplErEmEntity tplErEmEntity = ( TplErEmEntity ) list.iterator().next();
        String firstErNbr = tplErEmEntity.getData().getErNbr();

        if ( !erEmEntityVO.getErNbr().equals( firstErNbr ) )
        {
          detailFncVO.addError( EREMDetailFncVO.C_ERROR_DUPLICATE_REG_IN_GRID,
                                EREMDetailFncVO.C_ER_NBR_DESCRIPTION );
        }
      }

      // Verifica se já existe essa associação ER x EM no Grid
      if ( detailFncVO.getBaseTplErEmEntities().contains(
                                                          detailFncVO.getBaseTplErEmEntity() ) )
      {
        detailFncVO.addError( EREMDetailFncVO.C_ERROR_DUPLICATED_ELEMENT_IN_GRID );
      }
    }

    //Verifica se o registro existe.
    if ( !detailFncVO.hasErrors() )
    {
      TplCustomerPrvtCmplDAO tplCustomerPrvtCmplDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtCmplDAO();
      if ( !tplCustomerPrvtCmplDAO.existsEmNbr( erEmEntityVO.getEmNbr() ) )
      {
        detailFncVO.addError( EREMDetailFncVO.C_ERROR_REG_NOT_EXISTS,
                              EREMDetailFncVO.C_EM_NBR_DESCRIPTION );
      }
    }
    
    
    //Verifica se o registro existe.
    if ( !detailFncVO.hasErrors() ) {
    	TplCustomerPrvtDAO tplCustomerPrvtDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtDAO();
    	TplCustomerPrvtEntity tplCustomerPrvtEntity = new TplCustomerPrvtEntity();
    	
       	if (detailFncVO.getCustNbr() == null){
       	   detailFncVO.addError( EREMDetailFncVO.C_ERROR_MANDATORY_FIELD,
                   EREMDetailFncVO.C_CUST_NBR_DESCRIPTION );
       	} else {
    	  
    		tplCustomerPrvtEntity.getData().setCustNbr( detailFncVO.getCustNbr() );
       		if (!tplCustomerPrvtDAO.exists( tplCustomerPrvtEntity )){
       			detailFncVO.addError( EREMDetailFncVO.C_ERROR_REGISTER_NOT_FOUND,
       					EREMDetailFncVO.C_CUST_NBR_DESCRIPTION );
       		}
       	}
    }
    
  }

  /*
   * Insere o domínio no Grid
   */
  public void insertDomain( BaseFncVO fncVO_ )
  {
    EREMDetailFncVO detailFncVO = ( EREMDetailFncVO ) fncVO_;
    this.validateInsertDomain( detailFncVO );
    if ( !detailFncVO.hasErrors() )
    {
      // Entity to Insert
      TplErEmEntity tplErEmEntity = ( TplErEmEntity ) detailFncVO.getBaseTplErEmEntity();

      // Cópia
      TplErEmEntity cpTplErEmEntity = new TplErEmEntity();
      cpTplErEmEntity.getData().setErNbr( tplErEmEntity.getData().getErNbr() );
      cpTplErEmEntity.getData().setEmNbr( tplErEmEntity.getData().getEmNbr() );
      cpTplErEmEntity.getData().setRoleCustCode(
                                                 tplErEmEntity.getData().getRoleCustCode() );

      // Recuperando o Role Cust
      TplRoleCustDAO roleCustDAO = ODSDAOFactory.getInstance().getTplRoleCustDAO();
      TplRoleCustEntity tplRoleCustEntity = new TplRoleCustEntity();
      tplRoleCustEntity.getData().setRoleCustCode(
                                                   tplErEmEntity.getData().getRoleCustCode() );
      cpTplErEmEntity.setRoleCustEntity( ( TplRoleCustEntity ) roleCustDAO.find( tplRoleCustEntity ) );

      if ( detailFncVO.getCustNbr() != null )
      {
        TplCustomerPrvtDAO tplCustomerPrvtDAO = ODSDAOFactory.getInstance().getTplCustomerPrvtDAO();
        TplCustomerPrvtEntity tplCustomerPrvtEntity = new TplCustomerPrvtEntity();
        tplCustomerPrvtEntity.getData().setCustNbr( detailFncVO.getCustNbr() );
        cpTplErEmEntity.setCustomerPrvtEntity( ( TplCustomerPrvtEntity ) tplCustomerPrvtDAO.find( tplCustomerPrvtEntity ) );
      }

      detailFncVO.getBaseTplErEmEntities().add( cpTplErEmEntity );
      detailFncVO.getSelectedItemsInGrid().add( "S" );
      detailFncVO.getDeletedItems().add( "N" );
    }
  }

  /*
   * Remove o domínio do Grid
   */
  public void deleteDomain( BaseFncVO fncVO_ )
  {
    EREMDetailFncVO eremDetailFncVO = ( EREMDetailFncVO ) fncVO_;
    ArrayList list = eremDetailFncVO.getBaseTplErEmEntities();

    TplErEmEntity tplEREMEntity = new TplErEmEntity();
    tplEREMEntity.getData().setErNbr( eremDetailFncVO.getSelectedErNbrInGrid() );
    tplEREMEntity.getData().setEmNbr( eremDetailFncVO.getSelectedEmNbrInGrid() );

    list.remove( tplEREMEntity );
  }

  /**
   * Atualiza a Form com as informações vindas dos FncVO
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    TplErEmEntity egEntity;
    TplErEmEntityVO egEntityVO;

    super.updateFormFromFncVO( form_, fncVO_ );

    EREMDetailFncVO eremDetailFncVO = ( EREMDetailFncVO ) fncVO_;
    EREMDetailForm detailForm = ( EREMDetailForm ) form_;
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );

    ArrayList list = eremDetailFncVO.getBaseTplErEmEntities();

    String[][] grid = new String[ list.size() ][ 8 ];
    int indexLinha = 0;
    for ( Iterator ite = list.iterator(); ite.hasNext(); indexLinha++ )
    {
      egEntity = ( TplErEmEntity ) ite.next();
      egEntityVO = ( TplErEmEntityVO ) egEntity.getData();

      String strErNbr = "";
      if ( egEntityVO.getErNbr() != null )
      {
        strErNbr = egEntityVO.getErNbr().toString();
      }
      grid[ indexLinha ][ EREMDetailForm.COL_POS_ER_NBR ] = strErNbr;

      String strEmNbr = "";
      if ( egEntityVO.getEmNbr() != null )
      {
        strEmNbr = egEntityVO.getEmNbr();
      }
      grid[ indexLinha ][ EREMDetailForm.COL_POS_EM_NBR ] = strEmNbr;

      String strRoleCustCode = "";
      TplRoleCustEntity tplRoleCustEntity = egEntity.getRoleCustEntity();
      if ( tplRoleCustEntity != null
           && tplRoleCustEntity.getData().getRoleCustText() != null )
      {
        strRoleCustCode = tplRoleCustEntity.getData().getRoleCustText();
      }
      grid[ indexLinha ][ EREMDetailForm.COL_POS_ROLE_CUST_CODE ] = strRoleCustCode;

      String strCustFullName = "";
      TplCustomerPrvtEntity tplCustomerPrvtEntity = egEntity.getCustomerPrvtEntity();
      if ( tplCustomerPrvtEntity != null
           && tplCustomerPrvtEntity.getData().getCustFullNameText() != null )
      {
        strCustFullName = tplCustomerPrvtEntity.getData().getCustFullNameText();
      }
      grid[ indexLinha ][ EREMDetailForm.COL_POS_CUST_FULL_NAME ] = strCustFullName;

      String strLastUpdUserId = "";
      if ( egEntityVO.getLastUpdUserId() != null )
      {
        strLastUpdUserId = egEntityVO.getLastUpdUserId();
      }
      grid[ indexLinha ][ EREMDetailForm.COL_POS_LAST_UPD_USER_ID ] = strLastUpdUserId;

      String strLastUpdDate = "";
      if ( egEntityVO.getLastUpdDate() != null )
      {
        strLastUpdDate = dateFormat.format( egEntityVO.getLastUpdDate() );
      }
      grid[ indexLinha ][ EREMDetailForm.COL_POS_LAST_UPD_DATE ] = strLastUpdDate;

      String strLastAuthUserId = "";
      if ( egEntityVO.getLastUpdUserId() != null )
      {
        strLastAuthUserId = egEntityVO.getLastAuthUserId();
      }
      grid[ indexLinha ][ EREMDetailForm.COL_POS_LAST_AUTH_USER_ID ] = strLastAuthUserId;

      String strLastAuthDate = "";
      if ( egEntityVO.getLastAuthDate() != null )
      {
        strLastAuthDate = dateFormat.format( egEntityVO.getLastAuthDate() );
      }
      grid[ indexLinha ][ EREMDetailForm.COL_POS_LAST_AUTH_DATE ] = strLastAuthDate;
    }

    detailForm.setErEmGrid( grid );
  }

  /**
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   */

  private boolean existsInMovement( EREMDetailFncVO detailFncVO_ )
  {
    TplErEmEntity tplErEmEntity = ( TplErEmEntity ) detailFncVO_.getBaseTplErEmEntity();
    String erNbr = tplErEmEntity.getData().getErNbr();
    TplErEmMovDAO tplErEmMovDAO = OracleODSDAOFactory.getInstance().getTplErEmMovDAO();

    return tplErEmMovDAO.existsRelation( erNbr );
  }
  
  private boolean existsERInMovement(EREMDetailFncVO detailFncVO_)
  {
	TplErMovDAO erMovDAO = ODSDAOFactory.getInstance().getTplErMovDAO();

	TplErMovEntity tplErMovEntity = new TplErMovEntity();
	tplErMovEntity.getData().setErNbr(detailFncVO_.getBaseTplErEmEntity().getData().getErNbr());

	return erMovDAO.existsMovement(tplErMovEntity);
	  
  }
  
  private TplErEntity findEr(EREMDetailFncVO detailFncVO_)
  {
	TplErDAO erDAO = ODSDAOFactory.getInstance().getTplErDAO();
	TplErEntity tplErEntity = new TplErEntity();
	tplErEntity.getData().setErNbr(detailFncVO_.getBaseTplErEmEntity().getData().getErNbr());
		
	try{
	  tplErEntity  = (TplErEntity)erDAO.find(tplErEntity);
	}
	catch(NoRowsReturnedException e){
	  tplErEntity = null;
	}
		
	return tplErEntity;		
				
  }

  
  
  
}