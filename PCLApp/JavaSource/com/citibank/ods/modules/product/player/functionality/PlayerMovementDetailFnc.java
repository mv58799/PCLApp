/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionForm;


import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplPlayerEntity;
import com.citibank.ods.entity.pl.TplPlayerHistEntity;
import com.citibank.ods.entity.pl.TplPlayerMovEntity;
import com.citibank.ods.entity.pl.TplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplPlayerRoleHistEntity;
import com.citibank.ods.entity.pl.TplPlayerRoleMovEntity;
import com.citibank.ods.entity.pl.TplShortNamePlayerEntity;
import com.citibank.ods.entity.pl.TplShortNamePlayerHistEntity;
import com.citibank.ods.entity.pl.TplShortNamePlayerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleMovEntityVO;
import com.citibank.ods.modules.product.player.form.PlayerMovementDetailForm;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerDetailFncVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerMovementDetailFncVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerHistDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleHistDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerHistDAO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author atilio.l.araujo
 * 
 *  
 */
public class PlayerMovementDetailFnc extends BasePlayerDetailFnc implements
    ODSMovementDetailFnc
{

  private static final String C_ASSOCIATION = "Associações";

  private static final String C_PLYR_ROLE = "Papel Player";

  private static final String C_PLAYER = "Player";

  /**
   * Altera os dados de um Player com pendecia de aprovação
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {

    BaseTplPlayerRoleEntity baseTplPlayerRoleEntity;
    TplPlayerRoleMovEntity playerRoleMovEntity;

    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      PlayerMovementDetailFncVO movDetailFncVO = ( PlayerMovementDetailFncVO ) fncVO_;
      TplPlayerMovEntityVO tplPlayerMovEntityVO = ( TplPlayerMovEntityVO ) movDetailFncVO.getBaseTplPlayerEntity().getData();

      //Preenche o usuario e a data de ultima alteração
      tplPlayerMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );
      tplPlayerMovEntityVO.setLastUpdDate( new Date() );

      TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();
      tplPlayerMovDAO.update( ( TplPlayerMovEntity ) movDetailFncVO.getBaseTplPlayerEntity() );

      TplPlayerRoleMovDAO tplPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplPlayerRoleMovDAO();
      tplPlayerRoleMovDAO.delete( tplPlayerMovEntityVO.getPlyrCnpjNbr() );

      ArrayList playerRoleNames = movDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames();

      for ( int i = 0; i < playerRoleNames.size(); i++ )
      {
        baseTplPlayerRoleEntity = ( BaseTplPlayerRoleEntity ) playerRoleNames.get( i );
        playerRoleMovEntity = new TplPlayerRoleMovEntity();
        playerRoleMovEntity.getData().setPlyrCnpjNbr(
                                                      baseTplPlayerRoleEntity.getData().getPlyrCnpjNbr() );
        playerRoleMovEntity.getData().setPlyrRoleTypeCode(
                                                           baseTplPlayerRoleEntity.getData().getPlyrRoleTypeCode() );
        playerRoleMovEntity.getData().setLastUpdDate( new Date() );
        playerRoleMovEntity.getData().setLastUpdUserId(
                                                        fncVO_.getLoggedUser() != null
                                                                                      ? fncVO_.getLoggedUser().getUserID()
                                                                                      : "" );
        ( ( TplPlayerRoleMovEntityVO ) playerRoleMovEntity.getData() ).setOpernCode( tplPlayerMovEntityVO.getOpernCode() );
        tplPlayerRoleMovDAO.insert( playerRoleMovEntity );

      }
      //Exclui os Mnemonicos Antigos
      TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();
      tplShortNamePlayerMovDAO.delete(movDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr());
      //Insere os Mnemonicos Novos
      ShortNameVO shortNameVO;
      TplShortNamePlayerMovEntity  tplShortNamePlayerMovEntity;
      java.util.List<ShortNameVO> shortNameList = movDetailFncVO.getIssueShortNameList();
      for(int i=0;i<shortNameList.size();i++){
    	  shortNameVO = shortNameList.get(i);
    	  shortNameVO.setOpernCode(tplPlayerMovEntityVO.getOpernCode());
    	  shortNameVO.setLastUpdDate(new Date());
    	  shortNameVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
                                                               ? fncVO_.getLoggedUser().getUserID()
                                                               : "" );
    	  tplShortNamePlayerMovEntity = new TplShortNamePlayerMovEntity(shortNameVO);  
    	  tplShortNamePlayerMovDAO.insert(tplShortNamePlayerMovEntity);
      }      
    }
  }

  /**
   * Aprova os dados de um Player com pendencia de aprovação.
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve( BaseFncVO fncVO_ )
  {
	ShortNameVO shortNameVO;
	  
    TplPlayerRoleEntity tplPlayerRoleEntity;
    TplPlayerRoleHistEntity tplPlayerRoleHistEntity;
    TplPlayerRoleMovEntity tplPlayerRoleMovEntity;
    
    //Mnemonico
    TplShortNamePlayerEntity tplShortNamePlayerEntity;
    TplShortNamePlayerHistEntity tplShortNamePlayerHistEntity;

    validateApprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      PlayerMovementDetailFncVO playerMovementDetailFncVO = ( PlayerMovementDetailFncVO ) fncVO_;
      TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();
      TplPlayerRoleMovDAO tplPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplPlayerRoleMovDAO();
      TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();      

      //Recupera registro que esta sendo aprovado
      TplPlayerMovEntity tplPlayerMovEntity = ( TplPlayerMovEntity ) tplPlayerMovDAO.find( playerMovementDetailFncVO.getBaseTplPlayerEntity() );
      tplPlayerMovEntity.setPlyrRoleNames( tplPlayerRoleMovDAO.selectByPk( tplPlayerMovEntity.getData().getPlyrCnpjNbr() ) );
      tplPlayerMovEntity.setPlyrIssueNames(tplShortNamePlayerMovDAO.selectByPlyr(tplPlayerMovEntity.getData().getPlyrCnpjNbr()));
      
      ArrayList playerRoleNamesMov = tplPlayerMovEntity.getPlyrRoleNames();
      //Lista Mnemonicos em movimento
      java.util.List<ShortNameVO> shortNamesMov = tplPlayerMovEntity.getPlyrIssueNames(); 
      
      //Cria um objeto de entity current com os dados de movimento
      TplPlayerEntity tplPlayerEntity = new TplPlayerEntity(
                                                             tplPlayerMovEntity,
                                                             new Date(),
                                                             fncVO_.getLoggedUser().getUserID(),
                                                             TplPlayerMovEntity.C_REC_STAT_CODE_ACTIVE );

      //Recupera o opernCode de movimento
      String movOpernCode = ( ( TplPlayerMovEntityVO ) tplPlayerMovEntity.getData() ).getOpernCode();

      //Instancia DAO da current Player Role
      TplPlayerRoleDAO tplPlayerRoleDAO = ODSDAOFactory.getInstance().getTplPlayerRoleDAO();
      
      //Instancia DAO da tpl_short_name_player
      TplShortNamePlayerDAO tplShortNamePlayerDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerDAO();

      //Intancia DAO da current
      TplPlayerDAO tplPlayerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();

      if ( tplPlayerDAO.exists( tplPlayerEntity ) )
      {
        //Recupera o registro atual da current
        TplPlayerEntity tplPlayerEntityOld = ( TplPlayerEntity ) tplPlayerDAO.find( tplPlayerEntity );
        ArrayList playerRoleNamesCurrent = tplPlayerRoleDAO.selectByPk( tplPlayerEntity.getData().getPlyrCnpjNbr() );

        //Cria entity history com os dados de current
        TplPlayerHistEntity tplPlayerHistEntity = new TplPlayerHistEntity(
                                                                           tplPlayerEntityOld,
                                                                           new Date() );

        //Insere historico
        TplPlayerHistDAO tplPlayerHistDAO = ODSDAOFactory.getInstance().getTplPlayerHistDAO();
        TplPlayerRoleHistDAO tplPlayerRoleHistDAO = ODSDAOFactory.getInstance().getTplPlayerRoleHistDAO();
        tplPlayerHistDAO.insert( tplPlayerHistEntity );

        for ( int i = 0; i < playerRoleNamesCurrent.size(); i++ )
        {
          tplPlayerRoleEntity = ( TplPlayerRoleEntity ) playerRoleNamesCurrent.get( i );
          tplPlayerRoleHistEntity = new TplPlayerRoleHistEntity(
                                                                 tplPlayerRoleEntity,
                                                                 new Date() );
          tplPlayerRoleHistDAO.insert( tplPlayerRoleHistEntity );

        }

        //Inativa todos os papéis
        tplPlayerRoleDAO.inactivate( tplPlayerMovEntity.getData().getPlyrCnpjNbr() );
        
        
        //Insere Mnemonicos - Historico        
    	TplShortNamePlayerHistDAO tplShortNamePlayerHistDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerHistDAO();
    	java.util.List<ShortNameVO> shortNamesList = tplShortNamePlayerDAO.selectByPk(tplPlayerEntity.getData().getPlyrCnpjNbr());
    	for(int i=0;i<shortNamesList.size();i++){
    		shortNameVO = shortNamesList.get(i);      	  	
      	  	tplShortNamePlayerHistEntity = new TplShortNamePlayerHistEntity(shortNameVO, new Date()); 
      	    tplShortNamePlayerHistDAO.insert(tplShortNamePlayerHistEntity);
    	}
    	//Inativa os Mnemonicos deste Player
    	tplShortNamePlayerDAO.inactivate(tplPlayerMovEntity.getData().getPlyrCnpjNbr());
        //Fim
    	

        //Se operação for delete seta o recStatusCode como Inativo
        if ( TplPlayerMovEntity.C_OPERN_CODE_DELETE.equals( movOpernCode ) )
        {
          ( ( TplPlayerEntityVO ) tplPlayerEntity.getData() ).setRecStatCode( TplPlayerEntity.C_REC_STAT_CODE_INACTIVE );
        }
        else
        {
          //Insere os papeis na current apartir da movimento
          for ( int i = 0; i < playerRoleNamesMov.size(); i++ )
          {
            tplPlayerRoleMovEntity = ( TplPlayerRoleMovEntity ) playerRoleNamesMov.get( i );
            tplPlayerRoleEntity = new TplPlayerRoleEntity(
                                                           tplPlayerRoleMovEntity,
                                                           new Date(),
                                                           fncVO_.getLoggedUser().getUserID(),
                                                           TplPlayerRoleMovEntity.C_REC_STAT_CODE_ACTIVE );

            if ( tplPlayerRoleDAO.exists( tplPlayerRoleEntity ) )
            {
              tplPlayerRoleDAO.activate( tplPlayerRoleEntity );
            }
            else
            {
              tplPlayerRoleDAO.insert( tplPlayerRoleEntity );
            }
          }
          //Insere Mnemonicos - Tpl_short_name_player
          for ( int i = 0; i < shortNamesMov.size(); i++ ){
        	  
        	  shortNameVO = shortNamesMov.get(i);   
        	  shortNameVO.setLastAuthDate( new Date() );
        	  shortNameVO.setLastAuthUserId(fncVO_.getLoggedUser().getUserID());
        	  shortNameVO.setRecStatCode(TplPlayerRoleMovEntity.C_REC_STAT_CODE_ACTIVE);
        	  
        	  tplShortNamePlayerEntity = new TplShortNamePlayerEntity(shortNameVO);
        	  
        	  if ( tplShortNamePlayerDAO.exists( tplShortNamePlayerEntity ) )
              {
        		  tplShortNamePlayerDAO.activate(tplShortNamePlayerEntity);
              }else{
            	  tplShortNamePlayerDAO.insert(tplShortNamePlayerEntity);
              }        	  
          }          
          //Fim
        }

        //Atualiza current
        tplPlayerDAO.update( tplPlayerEntity );

      }
      else
      {
        tplPlayerDAO.insert( tplPlayerEntity );

        //Insere os papeis na current apartir da movimento
        for ( int i = 0; i < playerRoleNamesMov.size(); i++ )
        {
          tplPlayerRoleMovEntity = ( TplPlayerRoleMovEntity ) playerRoleNamesMov.get( i );
          tplPlayerRoleEntity = new TplPlayerRoleEntity(
                                                         tplPlayerRoleMovEntity,
                                                         new Date(),
                                                         fncVO_.getLoggedUser().getUserID(),
                                                         TplPlayerRoleMovEntity.C_REC_STAT_CODE_ACTIVE );

          if ( tplPlayerRoleDAO.exists( tplPlayerRoleEntity ) )
          {
            tplPlayerRoleDAO.activate( tplPlayerRoleEntity );
          }
          else
          {
            tplPlayerRoleDAO.insert( tplPlayerRoleEntity );
          }
        }
        //Insere Mnemonicos - Tpl_short_name_player
        for ( int i = 0; i < shortNamesMov.size(); i++ ){
      	  
      	  shortNameVO = shortNamesMov.get(i); 
      	  shortNameVO.setLastAuthDate( new Date() );
      	  shortNameVO.setLastAuthUserId(fncVO_.getLoggedUser().getUserID());
      	  shortNameVO.setRecStatCode(TplPlayerRoleMovEntity.C_REC_STAT_CODE_ACTIVE);
      	  
      	  tplShortNamePlayerEntity = new TplShortNamePlayerEntity(shortNameVO);
      	  
      	  if ( tplShortNamePlayerDAO.exists( tplShortNamePlayerEntity ) ){
      		  tplShortNamePlayerDAO.activate(tplShortNamePlayerEntity);
      	  }else{
          	  tplShortNamePlayerDAO.insert(tplShortNamePlayerEntity);
          }        	  
        }  
        //Fim
      }
      //Deleta registro da movimento
      tplShortNamePlayerMovDAO.delete(tplPlayerMovEntity.getData().getPlyrCnpjNbr());
      tplPlayerRoleMovDAO.delete( tplPlayerMovEntity.getData().getPlyrCnpjNbr() );
      tplPlayerMovDAO.delete( tplPlayerMovEntity );   

    }

  }

  /**
   * Rejeita os dados de um player com pendencia de aprovação
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void reprove( BaseFncVO fncVO_ )
  {
    validateReprove( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      PlayerMovementDetailFncVO playerMovementDetailFncVO = ( PlayerMovementDetailFncVO ) fncVO_;
      TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();
      TplPlayerRoleMovDAO tplPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplPlayerRoleMovDAO();
      TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();
      
      //Recupera registro que esta sendo reprovado
      TplPlayerMovEntity tplPlayerMovEntity = ( TplPlayerMovEntity ) tplPlayerMovDAO.find( playerMovementDetailFncVO.getBaseTplPlayerEntity() );

      // Deleta registro da movimento
      tplShortNamePlayerMovDAO.delete(tplPlayerMovEntity.getData().getPlyrCnpjNbr());
      tplPlayerRoleMovDAO.delete( tplPlayerMovEntity.getData().getPlyrCnpjNbr() );
      tplPlayerMovDAO.delete( tplPlayerMovEntity );
    }

  }

  /**
   * Realiza a validação de alteração de um registro com pendencia de aprovação
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    PlayerMovementDetailFncVO playerMovementDetailFncVO = ( PlayerMovementDetailFncVO ) fncVO_;
    TplPlayerMovEntityVO tplPlayerMovEntityVO = ( TplPlayerMovEntityVO ) playerMovementDetailFncVO.getBaseTplPlayerEntity().getData();

    //Testar campos obrigatórios
    if ( tplPlayerMovEntityVO.getPlyrCnpjNbr() == null
         || tplPlayerMovEntityVO.getPlyrCnpjNbr().equals( "" ) )
    {
      fncVO_.addError( PlayerMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_CNPJ_NBR );
    }

    if ( tplPlayerMovEntityVO.getPlyrName() == null
         || tplPlayerMovEntityVO.getPlyrName().equals( "" ) )
    {
      fncVO_.addError( PlayerMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_NAME );
    }

    if ( playerMovementDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames() == null
         || playerMovementDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames().size() < 1 )
    {
      fncVO_.addError( PlayerMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_ROLE );
    }

    //Testar usuário
    if ( playerMovementDetailFncVO.getLoggedUser() == null
         || !playerMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                           tplPlayerMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( PlayerMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }

    //Testar a operação, se for delete adiciona erro
    if ( !fncVO_.hasErrors() )
    {
      TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();
      TplPlayerMovEntity tplPlayerMovEntity = ( TplPlayerMovEntity ) tplPlayerMovDAO.find( playerMovementDetailFncVO.getBaseTplPlayerEntity() );

      String opernCode = ( ( TplPlayerMovEntityVO ) tplPlayerMovEntity.getData() ).getOpernCode();

      if ( TplPlayerMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
      {
        playerMovementDetailFncVO.addError( PlayerMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
      }
    }

    if ( !fncVO_.hasErrors() )
    {
      //    Validar se existem associações relacionadas a algum dos papéis
      // excluidos
      ArrayList listRoleTypes = new ArrayList();
      listRoleTypes = this.verifyAssociations(
                                               tplPlayerMovEntityVO.getPlyrCnpjNbr(),
                                               playerMovementDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames() );

      if ( listRoleTypes != null && listRoleTypes.size() > 0 )
      {
        for ( int i = 0; i < listRoleTypes.size(); i++ )
        {
          fncVO_.addError( PlayerDetailFncVO.C_ERROR_IS_REFERENCED,
                           C_PLYR_ROLE + " " + listRoleTypes.get( i ),
                           " " + C_ASSOCIATION );
        }
      }
    }
    if ( this.existsInIssue(playerMovementDetailFncVO)!= null)
    {
      fncVO_.addError( PlayerDetailFncVO.C_INVALID_ISSUE,this.existsInIssue(playerMovementDetailFncVO) );
    }

  }

  /**
   * Realiza a validação de aprovação de um registro com pendencia de aprovação
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    PlayerMovementDetailFncVO playerMovementDetailFncVO = ( PlayerMovementDetailFncVO ) fncVO_;
    TplPlayerMovEntityVO tplPlayerMovEntityVO = ( TplPlayerMovEntityVO ) playerMovementDetailFncVO.getBaseTplPlayerEntity().getData();

    // Testar usuário
    if ( playerMovementDetailFncVO.getLoggedUser() == null
         || playerMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                          tplPlayerMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( PlayerMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    super.load( ( PlayerMovementDetailFncVO ) fncVO_ );
    super.loadDomains( ( PlayerMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Carregamento inicial - alteração
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.load( ( PlayerMovementDetailFncVO ) fncVO_ );

    super.loadDomains( ( PlayerMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Carregamento inicial - aprovação
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.load( ( PlayerMovementDetailFncVO ) fncVO_ );
    super.loadDomains( ( PlayerMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Retorna instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new PlayerMovementDetailFncVO();
  }

  /**
   * Retorna o DAO
   */
  public BaseTplPlayerDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplPlayerMovDAO();
  }

  /**
   * Retorna o DAO do Player Role
   * @see com.citibank.ods.modules.product.player.functionality.BasePlayerDetailFnc#getPlayerRoleDAO()
   */
  protected BaseTplPlayerRoleDAO getPlayerRoleDAO()
  {
    return ODSDAOFactory.getInstance().getTplPlayerRoleMovDAO();
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Seta na Form os campos específicos de movimento.
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    PlayerMovementDetailFncVO detailfncVO = ( PlayerMovementDetailFncVO ) fncVO_;
    PlayerMovementDetailForm movementDetailForm = ( PlayerMovementDetailForm ) form_;

    String opernCode = ( ( TplPlayerMovEntityVO ) ( detailfncVO.getBaseTplPlayerEntity().getData() ) ).getOpernCode();
    if ( opernCode != null && !"".equals( opernCode ) )
    {
      String strOpernCode = ODSConstraintDecoder.decodeOpern( opernCode );
      movementDetailForm.setOpernCode( strOpernCode );
    }
    
    if(movementDetailForm.getLoadIssue().equals("S")){
	  //Atualiza Mnemonicos
	  TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();
	  java.util.List<ShortNameVO> shortNameList = tplShortNamePlayerMovDAO.selectByPlyr(detailfncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr());
	  movementDetailForm.setIssueShortNameList(shortNameList);
	  detailfncVO.setLoadIssue("N");
    }
  }

  private ArrayList verifyAssociations( String plyrCnpjNbr_,
                                       ArrayList listPlayerRole_ )
  {
    BaseTplPlayerRoleEntity tplPlayerRoleEntity;

    ArrayList list = new ArrayList();
    list = listPlayerRole_;
    String roleTypes = "";

    if ( list != null && list.size() > 0 )
    {
      for ( int i = 0; i < list.size(); i++ )
      {
        tplPlayerRoleEntity = ( BaseTplPlayerRoleEntity ) list.get( i );
        roleTypes = roleTypes + ",'"
                    + tplPlayerRoleEntity.getData().getPlyrRoleTypeCode() + "'";
      }
    }

    roleTypes = roleTypes.substring( 1 );
    TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
    list = tplProdPlayerRoleDAO.getRoleTypes( plyrCnpjNbr_, roleTypes );

    return list;
  }
  
  public void insertIssue(BaseFncVO fncVO_){	  
	  PlayerMovementDetailFncVO detailFncVO = (PlayerMovementDetailFncVO)fncVO_;
	  if(detailFncVO.getIssueShortNameText() != null && !detailFncVO.getIssueShortNameText().equals("")){
		  java.util.List<ShortNameVO> shortNameList = new ArrayList<ShortNameVO>();
		  if(validateIssue(fncVO_)){
			  if(detailFncVO.getIssueShortNameList().size() > 0){
				  shortNameList = detailFncVO.getIssueShortNameList(); 
			  }
			  ShortNameVO shortNameVO = new ShortNameVO();
			  shortNameVO.setIssueShortName(detailFncVO.getIssueShortNameText().toUpperCase().trim());
			  shortNameVO.setPlyrCnpjNbr(detailFncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr());
			  shortNameVO.setLastUpdUserId(detailFncVO.getBaseTplPlayerEntity().getData().getLastUpdUserId());
			  shortNameList.add(shortNameVO);
			  detailFncVO.setIssueShortNameList(shortNameList);
		  }
	  }else{
		  fncVO_.addError( PlayerDetailFncVO.C_ERROR_MANDATORY_FIELD, C_DISPLAY_ISSUE );
	  }
  }
  
  public void deleteIssue(BaseFncVO fncVO_){
	  PlayerMovementDetailFncVO detailFncVO = (PlayerMovementDetailFncVO)fncVO_;
	  java.util.List<ShortNameVO> shortNameList = detailFncVO.getIssueShortNameList();
	  ShortNameVO shortNameVO = shortNameList.get(detailFncVO.getShortNameIdx());
	  shortNameList.remove(shortNameVO);
	  detailFncVO.setIssueShortNameList(shortNameList);	  
  }
  
  public boolean validateIssue(BaseFncVO fncVO_){
	  PlayerMovementDetailFncVO detailfncVO = ( PlayerMovementDetailFncVO ) fncVO_;
	  if(detailfncVO.getIssueShortNameList().size() > 0){
		  java.util.List<ShortNameVO> shortNameList = detailfncVO.getIssueShortNameList();
		  for(int i=0; i< shortNameList.size(); i++){
			  ShortNameVO shortNameVO = shortNameList.get(i);
			  if(shortNameVO.getIssueShortName().equals(detailfncVO.getIssueShortNameText().toUpperCase().trim())){
				  fncVO_.addError(BaseODSFncVO.C_INVALID_ISSUE, detailfncVO.getIssueShortNameText().toUpperCase());
				  return false;
			  }
		  }		  
	  }
	  return true;
  }
  
  private String existsInIssue( PlayerMovementDetailFncVO playerDetailFncVO ){
	  //Lista valores contidos no objeto a ser inserido
	  java.util.List<ShortNameVO> shortNameObjList = playerDetailFncVO.getIssueShortNameList();
	  //Lista valores contidos na base tpl_short_name_player
	  TplShortNamePlayerDAO tplShortNamePlayerDAO =  ODSDAOFactory.getInstance().getTplShortNamePlayerDAO();
	  java.util.List<ShortNameVO> shortNameDataList = tplShortNamePlayerDAO.selectByPlyrCnpj(playerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr());
	  //Lista valores contidos na base tpl_short_name_player_mov
	  TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO =  ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();	  
	  java.util.List<ShortNameVO> shortNameDataMovList = tplShortNamePlayerMovDAO.selectByPlyrCnpj(playerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr());
	  for(int indexObj = 0;indexObj< shortNameObjList.size();indexObj++){
		  ShortNameVO shortNameObjVO = shortNameObjList.get(indexObj);
		  //Verifica se o valor do obj se enconta na base
		  for(int indexData = 0;indexData< shortNameDataList.size();indexData++){
			  ShortNameVO shortNameDataVO = shortNameDataList.get(indexData);
			  if(shortNameObjVO.getIssueShortName().equals(shortNameDataVO.getIssueShortName())){
				  return shortNameDataVO.getIssueShortName();
			  }
		  }
		  //Verifica se o valor do obj se enconta na base mov
		  for(int indexDataMov = 0;indexDataMov< shortNameDataMovList.size();indexDataMov++){
			  ShortNameVO shortNameDataMovVO = shortNameDataMovList.get(indexDataMov);
			  if(shortNameObjVO.getIssueShortName().equals(shortNameDataMovVO.getIssueShortName())){
				  return shortNameDataMovVO.getIssueShortName();
			  }
		  }
	  }	  
	  return null;
  }
}