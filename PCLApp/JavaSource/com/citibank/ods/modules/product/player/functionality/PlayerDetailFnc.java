package com.citibank.ods.modules.product.player.functionality;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;
import com.citibank.ods.Globals;
import com.citibank.ods.common.entity.BaseODSEntity;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.BaseTplPlayerRoleEntity;
import com.citibank.ods.entity.pl.TplPlayerEntity;
import com.citibank.ods.entity.pl.TplPlayerMovEntity;
import com.citibank.ods.entity.pl.TplPlayerRoleMovEntity;
import com.citibank.ods.entity.pl.TplShortNamePlayerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplPlayerEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplPlayerRoleMovEntityVO;
import com.citibank.ods.modules.product.player.form.PlayerDetailForm;
import com.citibank.ods.modules.product.player.functionality.valueobject.PlayerDetailFncVO;
import com.citibank.ods.modules.product.player.functionality.valueobject.ShortNameVO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplPlayerRoleMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplShortNamePlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author angelica.almeida
 *  
 */
public class PlayerDetailFnc extends BasePlayerDetailFnc implements
    ODSDetailFnc
{

  private static final String C_ASSOCIATION = "Associações";

  private static final String C_PLYR_ROLE = "Papel Player";

  private static final String C_PLAYER = "Player";

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#insert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void insert( BaseFncVO fncVO_ )
  {

    BaseTplPlayerRoleEntity baseTplPlayerRoleEntity;
    TplPlayerRoleMovEntity playerRoleMovEntity;

    this.validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      PlayerDetailFncVO detailFncVO = ( PlayerDetailFncVO ) fncVO_;
      TplPlayerEntity tplPlayerEntity = ( TplPlayerEntity ) detailFncVO.getBaseTplPlayerEntity();
      tplPlayerEntity.getData().setLastUpdDate( new Date() );
      tplPlayerEntity.getData().setLastUpdUserId(
                                                  fncVO_.getLoggedUser() != null
                                                                                ? fncVO_.getLoggedUser().getUserID()
                                                                                : "" );
      TplPlayerMovEntity tplPlayerMovEntity = new TplPlayerMovEntity(
                                                                      tplPlayerEntity,
                                                                      TplPlayerMovEntity.C_OPERN_CODE_INSERT );

      TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();
      tplPlayerMovDAO.insert( tplPlayerMovEntity );

      TplPlayerRoleMovDAO tplPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplPlayerRoleMovDAO();
      ArrayList playerRoleNames = detailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames();

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
        ( ( TplPlayerRoleMovEntityVO ) playerRoleMovEntity.getData() ).setOpernCode( TplPlayerRoleMovEntity.C_OPERN_CODE_INSERT );
        tplPlayerRoleMovDAO.insert( playerRoleMovEntity );

      }
      //Insere lista de Mnemonicos
      ShortNameVO shortNameVO;
      TplShortNamePlayerMovEntity  tplShortNamePlayerMovEntity;
      TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();
      List<ShortNameVO> shortNameList = detailFncVO.getIssueShortNameList();
      for(int i=0;i<shortNameList.size();i++){
    	  shortNameVO = shortNameList.get(i);
    	  shortNameVO.setOpernCode(BaseODSEntity.C_OPERN_CODE_INSERT);
    	  shortNameVO.setLastUpdUserId(	fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID() : "");
    	  shortNameVO.setLastUpdDate(new Date());
    	  tplShortNamePlayerMovEntity = new TplShortNamePlayerMovEntity(shortNameVO);  
    	  tplShortNamePlayerMovDAO.insert(tplShortNamePlayerMovEntity);
      }
    }

  }

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    BaseTplPlayerRoleEntity baseTplPlayerRoleEntity;
    TplPlayerRoleMovEntity playerRoleMovEntity;

    this.validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      PlayerDetailFncVO detailFncVO = ( PlayerDetailFncVO ) fncVO_;
      TplPlayerEntity tplPlayerEntity = ( TplPlayerEntity ) detailFncVO.getBaseTplPlayerEntity();
      tplPlayerEntity.getData().setLastUpdDate( new Date() );
      tplPlayerEntity.getData().setLastUpdUserId(
                                                  fncVO_.getLoggedUser() != null
                                                                                ? fncVO_.getLoggedUser().getUserID()
                                                                                : "" );
      TplPlayerMovEntity tplPlayerMovEntity = new TplPlayerMovEntity(
                                                                      tplPlayerEntity,
                                                                      TplPlayerMovEntity.C_OPERN_CODE_UPDATE );

      TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();
      tplPlayerMovDAO.insert( tplPlayerMovEntity );

      //Inserir papéis do player
      TplPlayerRoleMovDAO tplPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplPlayerRoleMovDAO();
      ArrayList playerRoleNames = detailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames();

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
        ( ( TplPlayerRoleMovEntityVO ) playerRoleMovEntity.getData() ).setOpernCode( TplPlayerMovEntity.C_OPERN_CODE_UPDATE );
        tplPlayerRoleMovDAO.insert( playerRoleMovEntity );

      }
      //Insere lista de Mnemonicos
      ShortNameVO shortNameVO;
      TplShortNamePlayerMovEntity  tplShortNamePlayerMovEntity;
      TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();
      List<ShortNameVO> shortNameList = detailFncVO.getIssueShortNameList();
      for(int i=0;i<shortNameList.size();i++){
    	  shortNameVO = shortNameList.get(i);
    	  shortNameVO.setOpernCode(BaseODSEntity.C_OPERN_CODE_UPDATE);
    	  shortNameVO.setLastUpdUserId(	fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID() : "");
    	  shortNameVO.setLastUpdDate(new Date());
    	  tplShortNamePlayerMovEntity = new TplShortNamePlayerMovEntity(shortNameVO);  
    	  tplShortNamePlayerMovDAO.insert(tplShortNamePlayerMovEntity);
      }
    }
  }

  /**
   * 
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#delete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void delete( BaseFncVO fncVO_ )
  {
    BaseTplPlayerRoleEntity baseTplPlayerRoleEntity;
    TplPlayerRoleMovEntity playerRoleMovEntity;

    PlayerDetailFncVO detailFncVO = ( PlayerDetailFncVO ) fncVO_;
    validateDelete( detailFncVO );
    if ( !detailFncVO.hasErrors() )
    {
      TplPlayerDAO tplPlayerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
      TplPlayerEntity tplPlayerEntity = ( TplPlayerEntity ) tplPlayerDAO.find( detailFncVO.getBaseTplPlayerEntity() );

      tplPlayerEntity.getData().setLastUpdDate( new Date() );
      tplPlayerEntity.getData().setLastUpdUserId(
                                                  fncVO_.getLoggedUser() != null
                                                                                ? fncVO_.getLoggedUser().getUserID()
                                                                                : "" );

      TplPlayerMovEntity tplPlayerMovEntity = new TplPlayerMovEntity(
                                                                      tplPlayerEntity,
                                                                      TplPlayerMovEntity.C_OPERN_CODE_DELETE );
      TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();
      tplPlayerMovDAO.insert( tplPlayerMovEntity );

      //Papéis do player
      TplPlayerRoleMovDAO tplPlayerRoleMovDAO = ODSDAOFactory.getInstance().getTplPlayerRoleMovDAO();
      TplPlayerRoleDAO tplPlayerRoleDAO = ODSDAOFactory.getInstance().getTplPlayerRoleDAO();
      ArrayList playerRoleNames = tplPlayerRoleDAO.selectByPk( tplPlayerEntity.getData().getPlyrCnpjNbr() );

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
        ( ( TplPlayerRoleMovEntityVO ) playerRoleMovEntity.getData() ).setOpernCode( TplPlayerMovEntity.C_OPERN_CODE_DELETE );
        tplPlayerRoleMovDAO.insert( playerRoleMovEntity );

      }
      
      //Mnemonicos
      ShortNameVO shortNameVO;
      TplShortNamePlayerMovEntity  tplShortNamePlayerMovEntity;
      TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();
      List<ShortNameVO> shortNameList = detailFncVO.getIssueShortNameList();
      for(int i=0;i<shortNameList.size();i++){
    	  shortNameVO = shortNameList.get(i);
    	  shortNameVO.setOpernCode(BaseODSEntity.C_OPERN_CODE_DELETE);
    	  shortNameVO.setLastUpdUserId(	fncVO_.getLoggedUser() != null ? fncVO_.getLoggedUser().getUserID() : "");
    	  shortNameVO.setLastUpdDate(new Date());
    	  tplShortNamePlayerMovEntity = new TplShortNamePlayerMovEntity(shortNameVO);  
    	  tplShortNamePlayerMovDAO.insert(tplShortNamePlayerMovEntity);
      }

    }

  }

  /**
   * Realiza as validações de inserção
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    PlayerDetailFncVO playerDetailFncVO = ( PlayerDetailFncVO ) fncVO_;
    TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) playerDetailFncVO.getBaseTplPlayerEntity().getData();

    //Validar Campos Obrigatórios
    if ( tplPlayerEntityVO.getPlyrCnpjNbr() == null
         || tplPlayerEntityVO.getPlyrCnpjNbr().equals( "" ) )
    {
      fncVO_.addError( PlayerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_CNPJ_NBR );
    }

    if ( tplPlayerEntityVO.getPlyrName() == null
         || tplPlayerEntityVO.getPlyrName().equals( "" ) )
    {
      fncVO_.addError( PlayerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_NAME );
    }

    if ( playerDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames() == null
         || playerDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames().size() < 1 )
    {
      fncVO_.addError( PlayerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_ROLE );
    }

    if ( !fncVO_.hasErrors() )
    {
      //Validar se já existe um registro com este codigo na "Current",
      if ( this.existsActive( playerDetailFncVO ) )
      {
        fncVO_.addError( PlayerDetailFncVO.C_ERROR_DUPLICATE_PK );
      }

      //Validar se já existe movimento com este codigo
      if ( this.existsInMovement( playerDetailFncVO ) )
      {
        fncVO_.addError( PlayerDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
      
      //validar se já existe um mnemonico cadastrado com o este valor
      if(this.existsInIssue( playerDetailFncVO )!= null){
    	  fncVO_.addError(PlayerDetailFncVO.C_INVALID_ISSUE,this.existsInIssue( playerDetailFncVO ));
      }
    }

  }

  /**
   * Realiza as validações de alteração
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    PlayerDetailFncVO playerDetailFncVO = ( PlayerDetailFncVO ) fncVO_;
    TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) playerDetailFncVO.getBaseTplPlayerEntity().getData();

    //Validar Campos Obrigatórios
    if ( tplPlayerEntityVO.getPlyrCnpjNbr() == null
         || tplPlayerEntityVO.getPlyrCnpjNbr().equals( "" ) )
    {
      fncVO_.addError( PlayerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_CNPJ_NBR );
    }

    if ( tplPlayerEntityVO.getPlyrName() == null
         || tplPlayerEntityVO.getPlyrName().equals( "" ) )
    {
      fncVO_.addError( PlayerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_NAME );
    }

    if ( playerDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames() == null
         || playerDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames().size() < 1 )
    {
      fncVO_.addError( PlayerDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       C_DISPLAY_PLYR_ROLE );
    }

    if ( !fncVO_.hasErrors() )
    {
      //Validar se existe um registro com este codigo na "Current",
      if ( !this.existsActive( playerDetailFncVO ) )
      {
        fncVO_.addError( PlayerDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      //Validar se já existe movimento com este codigo
      if ( this.existsInMovement( playerDetailFncVO ) )
      {
        fncVO_.addError( PlayerDetailFncVO.C_ERROR_IN_MOVEMENT );
      }

      //Validar se existem associações relacionadas a algum dos papéis
      // excluidos
      ArrayList listRoleTypes = new ArrayList();
      listRoleTypes = this.verifyAssociations(
                                               tplPlayerEntityVO.getPlyrCnpjNbr(),
                                               playerDetailFncVO.getBaseTplPlayerEntity().getPlyrRoleNames() );

      if ( listRoleTypes != null && listRoleTypes.size() > 0 )
      {
        for ( int i = 0; i < listRoleTypes.size(); i++ )
        {
          fncVO_.addError( PlayerDetailFncVO.C_ERROR_IS_REFERENCED,
                           C_PLYR_ROLE + " " + listRoleTypes.get( i ),
                           " " + C_ASSOCIATION );
        }
      }
      if ( this.existsInIssue(playerDetailFncVO) != null)
      {
        fncVO_.addError( PlayerDetailFncVO.C_INVALID_ISSUE, this.existsInIssue( playerDetailFncVO ) );
      }
    }
  }

  /**
   * Realiza as validações de exclusão
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#validateDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    PlayerDetailFncVO playerDetailFncVO = ( PlayerDetailFncVO ) fncVO_;
    if ( !fncVO_.hasErrors() )
    {
      //Validar se existe um registro com este codigo na "Current",
      if ( !this.existsActive( playerDetailFncVO ) )
      {
        fncVO_.addError( PlayerDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      //Validar se já existe movimento com este codigo
      if ( this.existsInMovement( playerDetailFncVO ) )
      {
        fncVO_.addError( PlayerDetailFncVO.C_ERROR_IN_MOVEMENT );
      }

      if ( this.existsAssociation( playerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr() ) )
      {
        fncVO_.addError( PlayerDetailFncVO.C_ERROR_IS_REFERENCED, C_PLAYER,
                         C_ASSOCIATION );
      }

    }
  }

  /**
   * Carregamento inicial - detalhe
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
    super.load( ( PlayerDetailFncVO ) fncVO_ );
    super.loadDomains( ( PlayerDetailFncVO ) fncVO_ );

  }

  /**
   * Carregamento inicial - inserção
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    PlayerDetailFncVO detailFncVO = ( PlayerDetailFncVO ) fncVO_;
    TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) detailFncVO.getBaseTplPlayerEntity().getData();

    tplPlayerEntityVO.setPlyrCnpjNbr( null );
    tplPlayerEntityVO.setPlyrName( null );
    tplPlayerEntityVO.setPlyrAddrText( null );
    tplPlayerEntityVO.setPlyrDueDlgDate( null );
    tplPlayerEntityVO.setPlyrDueDlgExecInd( null );
    tplPlayerEntityVO.setPlyrDueDlgEndDate( null );
    tplPlayerEntityVO.setPlyrDueDlgRnwDate( null );
    tplPlayerEntityVO.setPlyrInvstCmtteApprvDate( null );
    tplPlayerEntityVO.setPlyrApprvRstrnText( null );
    tplPlayerEntityVO.setPlyrSuplServText( null );
    tplPlayerEntityVO.setPlyrCmntText( null );

    detailFncVO.getBaseTplPlayerEntity().setPlyrRoleNames( null );
    detailFncVO.setIssueShortNameList(new ArrayList<ShortNameVO>());
    detailFncVO.setIssueShortNameText(null);

  }

  /**
   * Carregamento inicial - alteração
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    if ( this.existsInMovement( ( PlayerDetailFncVO ) fncVO_ ) )
    {
      fncVO_.addError( PlayerDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      super.load( ( PlayerDetailFncVO ) fncVO_ );
      super.loadDomains( ( PlayerDetailFncVO ) fncVO_ );
    }
  }

  /**
   * Carregamento inicial - exclusão
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForDelete(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    if ( this.existsInMovement( ( PlayerDetailFncVO ) fncVO_ ) )
    {
      fncVO_.addError( PlayerDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      super.load( ( PlayerDetailFncVO ) fncVO_ );
      super.loadDomains( ( PlayerDetailFncVO ) fncVO_ );
    }
  }

  /**
   * Verifica se existe um registro na tabela de movimento com os critérios
   * passados
   */
  private boolean existsInMovement( PlayerDetailFncVO playerDetailFncVO_ )
  {
    TplPlayerMovDAO tplPlayerMovDAO = ODSDAOFactory.getInstance().getTplPlayerMovDAO();

    TplPlayerMovEntity tplPlayerMovEntity = new TplPlayerMovEntity();
    tplPlayerMovEntity.getData().setPlyrCnpjNbr(
                                                 playerDetailFncVO_.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr() );
    return tplPlayerMovDAO.exists( tplPlayerMovEntity );
  }

  /**
   * Verifica se já existe um registro na tabela de "Current" com o código
   * passado e o seu status é "Ativo"
   */
  private boolean existsActive( PlayerDetailFncVO playerDetailFncVO_ )
  {
    TplPlayerDAO tplPlayerDAO = ODSDAOFactory.getInstance().getTplPlayerDAO();
    return tplPlayerDAO.existsActive( ( TplPlayerEntity ) playerDetailFncVO_.getBaseTplPlayerEntity() );
  }

  /**
   * Retorna uma instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new PlayerDetailFncVO();
  }

  /**
   * Retorna uma instancia do DAO da Current
   */
  protected BaseTplPlayerDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplPlayerDAO();
  }

  /**
   * Retorna o DAO do Player Role
   * @see com.citibank.ods.modules.product.player.functionality.BasePlayerDetailFnc#getPlayerRoleDAO()
   */
  protected BaseTplPlayerRoleDAO getPlayerRoleDAO()
  {
    return ODSDAOFactory.getInstance().getTplPlayerRoleDAO();
  }

  /**
   * Seta na Form os campos específicos de current
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    PlayerDetailFncVO detailfncVO = ( PlayerDetailFncVO ) fncVO_;
    PlayerDetailForm detailForm = ( PlayerDetailForm ) form_;
    TplPlayerEntityVO tplPlayerEntityVO = ( TplPlayerEntityVO ) detailfncVO.getBaseTplPlayerEntity().getData();
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    detailForm.setLastAuthUserId( tplPlayerEntityVO.getLastAuthUserId() );
    if ( tplPlayerEntityVO.getLastAuthDate() != null
         && !tplPlayerEntityVO.getLastAuthDate().equals( "" ) )
    {
      detailForm.setLastAuthDate( dateFormat.format( tplPlayerEntityVO.getLastAuthDate() ) );

    }
    else
    {
      detailForm.setLastAuthDate( null );
    }

    String recStatCode = ( ( TplPlayerEntityVO ) ( detailfncVO.getBaseTplPlayerEntity().getData() ) ).getRecStatCode();
    if ( recStatCode != null && !"".equals( recStatCode ) )
    {
      String strRecStatCode = ODSConstraintDecoder.decodeRecStatus( recStatCode );
      detailForm.setRecStatCode( strRecStatCode );
    }
    
    if(this.existsActive( detailfncVO )){
    	if(detailForm.getLoadIssue().equals("S")){
	    	TplShortNamePlayerDAO tplShortNamePlayerDAO = ODSDAOFactory.getInstance().getTplShortNamePlayerDAO();
	    	List<ShortNameVO> shortNameList = tplShortNamePlayerDAO.selectByPlyr(detailfncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr());
	    	detailForm.setIssueShortNameList(shortNameList);
	    	detailfncVO.setLoadIssue("N");
    	}
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

  private boolean existsAssociation( String plyrCnpjNbr_ )
  {
    ArrayList listAssociation = new ArrayList();
    TplProdPlayerRoleDAO tplProdPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();

    listAssociation = tplProdPlayerRoleDAO.selectByPlyr( plyrCnpjNbr_ );

    if ( listAssociation.size() > 0 )
    {
      return true;
    }
    else
    {
      return false;
    }

  }
  public void insertIssue(BaseFncVO fncVO_){	  
	  PlayerDetailFncVO detailfncVO = ( PlayerDetailFncVO ) fncVO_;
	  if(detailfncVO.getIssueShortNameText() != null && !detailfncVO.getIssueShortNameText().equals("")){
		  if(validateIssue(fncVO_)){
			  ShortNameVO shortNameVO = new ShortNameVO();
			  shortNameVO.setPlyrCnpjNbr(detailfncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr());
			  shortNameVO.setLastUpdUserId(detailfncVO.getBaseTplPlayerEntity().getData().getLastUpdUserId());
			  shortNameVO.setIssueShortName(detailfncVO.getIssueShortNameText().toUpperCase().trim());
			  detailfncVO.getIssueShortNameList().add(shortNameVO);
			  fncVO_.clearErrors();
		  } 
	  }else{
		  fncVO_.addError( PlayerDetailFncVO.C_ERROR_MANDATORY_FIELD, C_DISPLAY_ISSUE );
	  }
  }
  
  public void deleteIssue(BaseFncVO fncVO_){
	PlayerDetailFncVO detailfncVO = ( PlayerDetailFncVO ) fncVO_;	
	List<ShortNameVO> shortNameList = detailfncVO.getIssueShortNameList();
	ShortNameVO shortNameVO = shortNameList.get(detailfncVO.getShortNameIdx());
	shortNameList.remove(shortNameVO);
	detailfncVO.setIssueShortNameList(shortNameList);
  }
  
  public boolean validateIssue(BaseFncVO fncVO_){
	  PlayerDetailFncVO detailfncVO = ( PlayerDetailFncVO ) fncVO_;
	  if(detailfncVO.getIssueShortNameList().size() > 0){
		  List<ShortNameVO> shortNameList = detailfncVO.getIssueShortNameList();
		  for(int i=0; i< shortNameList.size(); i++){
			  ShortNameVO shortNameVO = shortNameList.get(i);
			  if(shortNameVO.getIssueShortName().equals(detailfncVO.getIssueShortNameText().toUpperCase().trim())){
				  fncVO_.addError(BaseODSFncVO.C_INVALID_ISSUE,detailfncVO.getIssueShortNameText().toUpperCase());
				  return false;
			  }
		  }		  
	  }
	  return true;
  }
  
  private String existsInIssue( PlayerDetailFncVO playerDetailFncVO ){
	  //Lista valores contidos no objeto a ser inserido
	  List<ShortNameVO> shortNameObjList = playerDetailFncVO.getIssueShortNameList();
	  //Lista valores contidos na base tpl_short_name_player
	  TplShortNamePlayerDAO tplShortNamePlayerDAO =  ODSDAOFactory.getInstance().getTplShortNamePlayerDAO();
	  List<ShortNameVO> shortNameDataList = tplShortNamePlayerDAO.selectByPlyrCnpj(playerDetailFncVO.getBaseTplPlayerEntity().getData().getPlyrCnpjNbr());;
	  //Lista valores contidos na base tpl_short_name_player_mov
	  TplShortNamePlayerMovDAO tplShortNamePlayerMovDAO =  ODSDAOFactory.getInstance().getTplShortNamePlayerMovDAO();	  
	  List<ShortNameVO> shortNameDataMovList = tplShortNamePlayerMovDAO.selectByPlyr(null);
	  
	  for(int indexObj = 0;indexObj< shortNameObjList.size();indexObj++){
		  ShortNameVO shortNameObjVO = shortNameObjList.get(indexObj);
		  //Verifica se o valor do obj se encontra na base
		  for(int indexData = 0;indexData< shortNameDataList.size();indexData++){
			  ShortNameVO shortNameDataVO = shortNameDataList.get(indexData);
			  if(shortNameObjVO.getIssueShortName().equals(shortNameDataVO.getIssueShortName())){
				  return shortNameDataVO.getIssueShortName();
			  }
		  }
		  //Verifica se o valor do obj se encontra na base mov
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