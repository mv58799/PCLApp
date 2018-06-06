package com.citibank.ods.modules.product.product.functionality;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplProductCorpEntity;
import com.citibank.ods.entity.pl.TplProductCorpHistEntity;
import com.citibank.ods.entity.pl.TplProductCorpMovEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.TplProductHistEntity;
import com.citibank.ods.entity.pl.TplProductMovEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerHistEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerMovEntity;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductHistEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerMovEntityVO;
import com.citibank.ods.modules.product.product.form.ProductMovementDetailForm;
import com.citibank.ods.modules.product.product.functionality.valueobject.ProductMovementDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductCorpDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductDAO;
import com.citibank.ods.persistence.pl.dao.TplProductCorpDAO;
import com.citibank.ods.persistence.pl.dao.TplProductCorpHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProductCorpMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.TplProductHistDAO;
import com.citibank.ods.persistence.pl.dao.TplProductMovDAO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerDAO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerHistDAO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @author atilio.l.araujo,Apr 19, 2007
 *  
 */

public class ProductMovementDetailFnc extends BaseProductDetailFnc implements
    ODSMovementDetailFnc
{

  private static final String C_APPROVED = "Aprovado";

  /**
   * Carregamento inicial - Retorna instancia do FncVO
   */
  public BaseFncVO createFncVO()
  {
    return new ProductMovementDetailFncVO();
  }

  /**
   * Altera os dados de uma sub-família com pendência de aprovação
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#update(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      ProductMovementDetailFncVO productMovementDetailFncVO = ( ProductMovementDetailFncVO ) fncVO_;
      TplProductMovEntityVO tplProductMovEntityVO = ( TplProductMovEntityVO ) productMovementDetailFncVO.getBaseTplProductEntity().getData();

      // Preenche o usuario e a data de ultima alteração
      tplProductMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );
      tplProductMovEntityVO.setLastUpdDate( new Date() );

      TplProductMovDAO tplProductDAO = ODSDAOFactory.getInstance().getTplProductMovDAO();
      tplProductDAO.update( ( TplProductMovEntity ) productMovementDetailFncVO.getBaseTplProductEntity() );
      
      TplProductCorpMovEntityVO tplProductCorpMovEntityVO = ( TplProductCorpMovEntityVO ) productMovementDetailFncVO.getBaseTplProductCorpEntity().getData();

      tplProductCorpMovEntityVO.setLastUpdUserId( fncVO_.getLoggedUser().getUserID() );
      tplProductCorpMovEntityVO.setLastUpdDate( tplProductMovEntityVO.getLastUpdDate() );

      TplProductCorpMovDAO tplProductCorpDAO = ODSDAOFactory.getInstance().getTplProductCorpMovDAO();
      tplProductCorpDAO.update( ( TplProductCorpMovEntity ) productMovementDetailFncVO.getBaseTplProductCorpEntity() );
      
      List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity = tplProductMovEntityVO.getListProductPlayerRiskVO();
      
      TplRiskFamilyProdPlayerMovDAO riskFamilyProdPlayerMovDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerMovDAO();
      
      TplProductMovEntity movEntity = ( TplProductMovEntity ) productMovementDetailFncVO.getBaseTplProductEntity();
	  
	  riskFamilyProdPlayerMovDAO.deleteAll(movEntity);
	  
      for(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity : listTplRiskFamilyProdPlayerMovEntity){
    	  TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = (TplRiskFamilyProdPlayerMovEntityVO)tplRiskFamilyProdPlayerMovEntity.getData();
    	  
    	  tplRiskFamilyProdPlayerMovEntityVO.setProdFamlCode(new BigInteger(TplProductEntity.C_EMISSOR_RENDA_FIXA_FAMILE_CODE));
  		
    	  tplRiskFamilyProdPlayerMovEntityVO.setLastUpdUserId(tplProductMovEntityVO.getLastUpdUserId());
    	  tplRiskFamilyProdPlayerMovEntityVO.setLastUpdDate(tplProductMovEntityVO.getLastUpdDate());
    	  tplRiskFamilyProdPlayerMovEntityVO.setProdCode(tplProductMovEntityVO.getProdCode());
    	  tplRiskFamilyProdPlayerMovEntityVO.setSysCode(tplProductMovEntityVO.getSysCode());
    	  tplRiskFamilyProdPlayerMovEntityVO.setSysSegCode(tplProductMovEntityVO.getSysSegCode());
    	  
    	  tplRiskFamilyProdPlayerMovEntityVO.setOpernCode(tplProductMovEntityVO.getOpernCode());    	      	     	 
    	  
    	  if(riskFamilyProdPlayerMovDAO.exists(tplRiskFamilyProdPlayerMovEntity)){
    		  riskFamilyProdPlayerMovDAO.update(tplRiskFamilyProdPlayerMovEntity);
    	  }
    	  else{
    		  riskFamilyProdPlayerMovDAO.insert(tplRiskFamilyProdPlayerMovEntity);
    	  }
      }
      
    }

  }

  /**
   * Aprova os dados de uma sub-família com pendecia de aprovação
   * 
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#approve(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void approve(BaseFncVO fncVO_) {
		validateApprove(fncVO_);

		if (!fncVO_.hasErrors()) {
			TplProductMovDAO productMovDAO = ODSDAOFactory.getInstance().getTplProductMovDAO();

			// Recupera o registro que está sendo aprovado
			TplProductMovEntity movDetailEntity = (TplProductMovEntity) productMovDAO.find(((ProductMovementDetailFncVO) fncVO_).getBaseTplProductEntity());

			TplProductCorpMovDAO productCorpMovDAO = ODSDAOFactory.getInstance().getTplProductCorpMovDAO();

			// Recupera a parte Corp
			TplProductCorpMovEntity movDetailCorpEntity = (TplProductCorpMovEntity) productCorpMovDAO.find(((ProductMovementDetailFncVO) fncVO_)
					.getBaseTplProductEntity());
			
			movDetailCorpEntity.getData().setLastUpdUserId(movDetailEntity.getData().getLastUpdUserId());

			// Recupara os Emissores de renda fixa se existir
			TplRiskFamilyProdPlayerMovDAO tplRiskFamilyProdPlayerMovDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerMovDAO();

			List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity = tplRiskFamilyProdPlayerMovDAO.list(movDetailEntity);
			TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO) movDetailEntity.getData();
			tplProductMovEntityVO.setListProductPlayerRiskVO(listTplRiskFamilyProdPlayerMovEntity);

			Date novaData = new Date();
			
			// Constroi um objeto entity de Current com os dados de movimento
			TplProductEntity productEntity = new TplProductEntity(movDetailEntity, novaData, fncVO_.getLoggedUser().getUserID(),
					TplProductEntity.C_REC_STAT_CODE_ACTIVE);

			// Constroi um objeto entity de Current com os dados de movimento
			TplProductCorpEntity productCorpEntity = new TplProductCorpEntity(movDetailCorpEntity, novaData, fncVO_.getLoggedUser().getUserID(),
					TplProductEntity.C_REC_STAT_CODE_ACTIVE);

			// Recupera o opernCode de movimento para as operacoes
			String movOpernCode = ((TplProductMovEntityVO) movDetailEntity.getData()).getOpernCode();
			// Recupera o opernCode de movimento para as operacoes
			String movCorpOpernCode = ((TplProductCorpMovEntityVO) movDetailCorpEntity.getData()).getOpernCode();

			// 1. Se a operacao for de delete, seta recStatCode para inativo
			if (TplProductEntity.C_OPERN_CODE_DELETE.equals(movOpernCode)) {
				((TplProductEntityVO) productEntity.getData()).setRecStatCode(TplProductEntity.C_REC_STAT_CODE_INACTIVE);
			}

			// 1. Se a operacao for de delete, seta recStatCode para inativo
			if (TplProductEntity.C_OPERN_CODE_DELETE.equals(movCorpOpernCode)) {
				((TplProductCorpEntityVO) productCorpEntity.getData()).setRecStatCode(TplProductEntity.C_REC_STAT_CODE_INACTIVE);
			}

			// 2. Se existe um registro na tabela de Current com o mesmo código,
			// copia para histórico e atualiza Current
			TplProductDAO productDAO = ODSDAOFactory.getInstance().getTplProductDAO();

			if (productDAO.exists(productEntity)) {
				TplProductEntity productEntityOld = (TplProductEntity) productDAO.find(productEntity);

				// Recupera o Emissor da randa fixa
				TplRiskFamilyProdPlayerDAO tplRiskFamilyProdPlayerDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerDAO();
				TplProductEntityVO tplProductEntityOldVO = (TplProductEntityVO) productEntityOld.getData();
				List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplRiskFamilyProdPlayerDAO.list((TplProductEntity) productEntityOld, null);

				tplProductEntityOldVO.setListProductPlayerRiskVO(listTplRiskFamilyProdPlayerEntity);

				// Insere histórico
				TplProductHistEntity tplProductHistEntity = new TplProductHistEntity(productEntityOld, new Date());

				// novo
				this.insertInHist(tplProductHistEntity);

				// atualiza Current
				// productDAO.update( productEntity );
				this.updateInCurrent(productEntity);
			} else {
				this.insertInCurrent(productEntity);
				// productDAO.insert( productEntity );
			}

			// 2. Se existe um registro na tabela de Current com o mesmo código,
			// copia para histórico e atualiza Current
			TplProductCorpDAO productCorpDAO = ODSDAOFactory.getInstance().getTplProductCorpDAO();

			if (productCorpDAO.exists(productEntity)) {
				TplProductCorpEntity productCorpEntityOld = (TplProductCorpEntity) productCorpDAO.find(productEntity);

				// Insere histórico
				TplProductCorpHistEntity tplProductCorpHistEntity = new TplProductCorpHistEntity(productCorpEntityOld, new Date());

				// novo
				this.insertCorpInHist(tplProductCorpHistEntity);

				// atualiza Current
				// productDAO.update( productEntity );
				this.updateCorpInCurrent(productCorpEntity);
			} else {
				this.insertCorpInCurrent(productCorpEntity);
				// productDAO.insert( productEntity );
			}

			// 3. Remove o movimento Produto CORP
			this.deleteCorpMov(movDetailCorpEntity);

			// 3. Remove o movimento Produto
			this.deleteMov(movDetailEntity);

		}

	}


/**
 * Reprova os dados de sub-família com pendencia de aprovação
 * 
 * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#reprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
 */
  public void reprove( BaseFncVO fncVO_ )
  {
    // realiza validação
    validateReprove( fncVO_ );

    if ( !fncVO_.hasErrors() )
    {
      TplProductMovDAO productMovDAO = ODSDAOFactory.getInstance().getTplProductMovDAO();
      // 1 - Obtem o registro selecionado
      TplProductMovEntity movDetailEntity = ( TplProductMovEntity ) productMovDAO.find( ( ( ProductMovementDetailFncVO ) fncVO_ ).getBaseTplProductEntity() );

      TplRiskFamilyProdPlayerMovDAO riskFamilyProdPlayerMovDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerMovDAO();
      
      // 2 - deleta todos os Emissores renda fixa
      riskFamilyProdPlayerMovDAO.deleteAll(movDetailEntity);
      
      TplProductCorpMovDAO productCorpMovDAO = ODSDAOFactory.getInstance().getTplProductCorpMovDAO();

      // 3 - Obtem o registro selecionado Corp
      TplProductCorpMovEntity movDetailCorpEntity = ( TplProductCorpMovEntity ) productCorpMovDAO.find( ( ( ProductMovementDetailFncVO ) fncVO_ ).getBaseTplProductEntity() );      

      // 4 - Remove movimento Corp
      productCorpMovDAO.delete( movDetailCorpEntity );      
      
      // 5 - Remove movimento
      productMovDAO.delete( movDetailEntity );
    }
  }

  /**
   * Validar a alteração 1. Usuário != nulo 2. Usuário = lastUpdUpserId (Usuário
   * que está alterando deve ser igual ao usuário que inseriu o registro p/
   * aprovação)
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProductMovementDetailFncVO productMovementDetailFncVO = ( ProductMovementDetailFncVO ) fncVO_;
    TplProductMovEntityVO tplProductMovEntityVO = ( TplProductMovEntityVO ) productMovementDetailFncVO.getBaseTplProductEntity().getData();

    //testar campos obrigatórios
    if ( ( tplProductMovEntityVO.getProdCode() == null )
         || ( tplProductMovEntityVO.getProdCode().equals( "" ) ) )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductMovementDetailFncVO.C_PROD_CODE_DESCRIPTION );
    }

    if ( ( tplProductMovEntityVO.getSysCode() == null )
         || ( tplProductMovEntityVO.getSysCode().equals( "" ) ) )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductMovementDetailFncVO.C_SYS_CODE_DESCRIPTION );
    }

    if ( ( tplProductMovEntityVO.getSysSegCode() == null )
         || ( tplProductMovEntityVO.getSysSegCode().equals( "" ) ) )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductMovementDetailFncVO.C_SYS_SEG_CODE_DESCRIPTION );
    }

    /*if ( ( tplProductMovEntityVO.getProdFamlCode() == null )
         || ( tplProductMovEntityVO.getProdFamlCode().equals( "" ) ) )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductMovementDetailFncVO.C_PROD_FAML_CODE_DESCRIPTION );
    }*/

    if ( ( tplProductMovEntityVO.getProdName() == null )
         || ( tplProductMovEntityVO.getProdName().equals( "" ) ) )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductMovementDetailFncVO.C_PROD_NAME_DESCRIPTION );
    }

    if ( ( tplProductMovEntityVO.getProdText() == null )
         || ( tplProductMovEntityVO.getProdText().equals( "" ) ) )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductMovementDetailFncVO.C_PROD_TEXT_DESCRIPTION );
    }

    if ( tplProductMovEntityVO.getProdStatCode() == null
         || tplProductMovEntityVO.getProdStatCode() == "" )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductMovementDetailFncVO.C_PROD_STAT_CODE );
    }
    
    if(tplProductMovEntityVO.getProdSubFamlCode() == null || tplProductMovEntityVO.getProdSubFamlCode().equals("")){
    	fncVO_.addError(ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
    					ProductMovementDetailFncVO.C_PROD_SUB_FAML_CODE_DESCRIPTION);
    }
    
    if(tplProductMovEntityVO.getProdRiskCatCode() == null || tplProductMovEntityVO.getProdRiskCatCode().equals("")){
    	if(!tplProductMovEntityVO.getSysCode().equals("PM")){
	    	fncVO_.addError(ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
	    					ProductMovementDetailFncVO.C_PROD_EMISSOR_CAT_RISK_CODE);
    	}
    }
    
    if(tplProductMovEntityVO.getProdSentIaInd() == null || tplProductMovEntityVO.getProdSentIaInd().equals("")){
    	fncVO_.addError(ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
						ProductMovementDetailFncVO.C_PROD_SENT_IA_IND);
    }
    
    if(tplProductMovEntityVO.getAssocClassProdCode() == null || tplProductMovEntityVO.getAssocClassProdCode().equals("")){
    	if(!tplProductMovEntityVO.getSysCode().equals("PM")){
	    	fncVO_.addError(ProductMovementDetailFncVO.C_ERROR_MANDATORY_FIELD,
							ProductMovementDetailFncVO.C_ASSOC_CLASS_PROD_CODE);
    	}
    }

    //Testar usuário
    if ( productMovementDetailFncVO.getLoggedUser() == null
         || !productMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                            tplProductMovEntityVO.getLastUpdUserId() ) )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_ERROR_APPROVAL_UPDATE_USER_NOT_AUTHORIZED );
    }

    //Testar a operação, se for delete adiciona erro
    if ( !fncVO_.hasErrors() )
    {
      TplProductMovDAO tplProductMovDAO = ODSDAOFactory.getInstance().getTplProductMovDAO();
      TplProductMovEntity tplProductMovEntity = ( TplProductMovEntity ) tplProductMovDAO.find( productMovementDetailFncVO.getBaseTplProductEntity() );

      String opernCode = ( ( TplProductMovEntityVO ) tplProductMovEntity.getData() ).getOpernCode();

      if ( TplProductMovEntity.C_OPERN_CODE_DELETE.equals( opernCode ) )
      {
        productMovementDetailFncVO.addError( ProductMovementDetailFncVO.C_ERROR_CANNOT_UPD_DELETE_IN_MOVEMENT );
      }
    }
  }

  /**
   * Validar a aprovação 1. Usuário != nulo 2. Usuário != lastUpdUpserId
   * (Usuário que está aprovando deve ser diferente do usuário que inseriu o
   * registro p/ aprovação)
   */
  public void validateApprove( BaseFncVO fncVO_ )
  {
    ProductMovementDetailFncVO productMovementDetailFncVO = ( ProductMovementDetailFncVO ) fncVO_;
    TplProductMovEntityVO productMovEntityVO = ( TplProductMovEntityVO ) productMovementDetailFncVO.getBaseTplProductEntity().getData();

    if ( !existsProductActive( productMovEntityVO ) )
    {
      fncVO_.addError( ProductMovementDetailFncVO.C_INVALID_FK, C_APPROVED,
                       ProductMovementDetailFncVO.C_PROD_CODE_DESCRIPTION );
    }

    //testar usuário
    if ( productMovementDetailFncVO.getLoggedUser() == null
         || productMovementDetailFncVO.getLoggedUser().getUserID().equals(
                                                                           productMovEntityVO.getLastUpdUserId() ) )
    {

      productMovementDetailFncVO.addError( ProductMovementDetailFncVO.C_ERROR_APPROVAL_USER_NOT_AUTHORIZED );
    }
  }

  /**
   * Inserindo as informações no Form a partir do FncVO - Campos específicos
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {

    super.updateFormFromFncVO( form_, fncVO_ );

    ProductMovementDetailForm productMovementDetailForm = ( ProductMovementDetailForm ) form_;
    ProductMovementDetailFncVO productMovementDetailFncVO = ( ProductMovementDetailFncVO ) fncVO_;
    TplProductMovEntityVO productMovEntityVO = ( TplProductMovEntityVO ) productMovementDetailFncVO.getBaseTplProductEntity().getData();

    String opernCode = productMovEntityVO.getOpernCode();

    if ( opernCode != null && !"".equals( opernCode ) )
    {
      productMovementDetailForm.setOpernCode( ODSConstraintDecoder.decodeOpern( opernCode ) );
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#validateReprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateReprove( BaseFncVO fncVO_ )
  {
    /**
     * "[Method description]"
     * 
     * @param
     * @return
     * @exception
     * @see
     */

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForUpdate(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    super.load( ( ProductMovementDetailFncVO ) fncVO_ );
    super.loadCorp( ( ProductMovementDetailFncVO ) fncVO_ );
    super.loadDomains( ( ProductMovementDetailFncVO ) fncVO_ );
    super.loadProdFamlCode( ( ProductMovementDetailFncVO ) fncVO_ );
    
    //Complementa as informacoes dos emissores
    super.completeDataListaEmisso( ( ProductMovementDetailFncVO ) fncVO_);
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSMovementDetailFnc#loadForApprove(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForApprove( BaseFncVO fncVO_ )
  {
    super.load( ( ProductMovementDetailFncVO ) fncVO_ );
    super.loadCorp( ( ProductMovementDetailFncVO ) fncVO_ );
    super.loadDomains( ( ProductMovementDetailFncVO ) fncVO_ );
    super.loadProdFamlCode( ( ProductMovementDetailFncVO ) fncVO_ );
    
    //Complementa as informacoes dos emissores
    super.completeDataListaEmisso(( ProductMovementDetailFncVO ) fncVO_ );
  }

  /**
   * Carregamento inicial - Detalhe
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
  }

  /**
   * Retorna o DAO
   */
  protected BaseTplProductDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProductMovDAO();
  }
  
  @Override
  protected BaseTplProductCorpDAO getCorpDAO() {
	  return ODSDAOFactory.getInstance().getTplProductCorpMovDAO();
  }    

  private boolean existsProductActive(
                                      TplProductMovEntityVO tplProductMovEntityVO_ )
  {

    TplProductDAO productDAO = ODSDAOFactory.getInstance().getTplProductDAO();
    TplProductEntity productEntity = new TplProductEntity();
    productEntity.getData().setProdCode( tplProductMovEntityVO_.getProdCode() );
    productEntity.getData().setSysCode( tplProductMovEntityVO_.getSysCode() );
    productEntity.getData().setSysSegCode(
                                           tplProductMovEntityVO_.getSysSegCode() );

    return productDAO.existsActive( productEntity );
  }
  
  /**
   * Insere no historico mais os emissores de renda fixa
   * 
   * @param fncVO_
   * @param opernCode_
   */
  private void insertInHist(TplProductHistEntity tplProductHistEntity) {
		
		TplProductHistDAO productHistDAO = ODSDAOFactory.getInstance().getTplProductHistDAO();
		productHistDAO.insert( tplProductHistEntity );

		TplProductHistEntityVO tplProductHistEntityVO = (TplProductHistEntityVO) tplProductHistEntity.getData();

		//Verifica se tem lista de emissor
		if (tplProductHistEntityVO.getListProductPlayerRiskVO() != null && tplProductHistEntityVO.getListProductPlayerRiskVO().size() > 0) {

			List<TplRiskFamilyProdPlayerHistEntity> listTplRiskFamilyProdPlayerHistEntity = tplProductHistEntityVO.getListProductPlayerRiskVO();

			TplRiskFamilyProdPlayerHistDAO riskFamilyProdPlayerHistDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerHistDAO();

			for (TplRiskFamilyProdPlayerHistEntity tplRiskFamilyProdPlayerHistEntity : listTplRiskFamilyProdPlayerHistEntity) {

				riskFamilyProdPlayerHistDAO.insert(tplRiskFamilyProdPlayerHistEntity);
			}
		}
	}
  
  /**
   * Insere no historico Produto Corp
   * 
   * @param fncVO_
   * @param opernCode_
   */
  private void insertCorpInHist(TplProductCorpHistEntity tplProductCorpHistEntity) {
		
		TplProductCorpHistDAO productCorpHistDAO = ODSDAOFactory.getInstance().getTplProductCorpHistDAO();
		productCorpHistDAO.insert( tplProductCorpHistEntity );

	}  
  
  /**
   * Insert na tabela corrente, e seus emissores de renda fixa se existir.
   * 
   * @param tplProductEntity
   */
  private void insertInCurrent(TplProductEntity tplProductEntity) {

		TplProductDAO productDAO = ODSDAOFactory.getInstance().getTplProductDAO();
		productDAO.insert(tplProductEntity);

		TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) tplProductEntity.getData();

		// Verifica se tem lista de emissor
		if (tplProductEntityVO.getListProductPlayerRiskVO() != null && tplProductEntityVO.getListProductPlayerRiskVO().size() > 0) {

			List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();

			TplRiskFamilyProdPlayerDAO riskFamilyProdPlayerDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerDAO();

			for (TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity : listTplRiskFamilyProdPlayerEntity) {

				riskFamilyProdPlayerDAO.insert(tplRiskFamilyProdPlayerEntity);
			}
		}
	}
  
  /**
	 * Insert na tabela corrente Corp.
	 * 
	 * @param tplProductEntity
	 */
	private void insertCorpInCurrent(TplProductCorpEntity tplProductCorpEntity) {

		TplProductCorpDAO productCorpDAO = ODSDAOFactory.getInstance().getTplProductCorpDAO();
		productCorpDAO.insert(tplProductCorpEntity);

	}  
  
  /**
	 * Update na tabela corrente, e de seus emissores de renda fixa se existir.
	 * 
	 * Antes do update nos Emissores inativa todos. Assim garante os excluidos.
	 *  
	 * @param tplProductEntity
	 */
  private void updateInCurrent(TplProductEntity tplProductEntity) {

		TplProductDAO productDAO = ODSDAOFactory.getInstance().getTplProductDAO();
		productDAO.update(tplProductEntity);

		TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) tplProductEntity.getData();

		TplRiskFamilyProdPlayerDAO riskFamilyProdPlayerDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerDAO();

		if (tplProductEntityVO.getListProductPlayerRiskVO() != null && tplProductEntityVO.getListProductPlayerRiskVO().size() >= 0) {

			//Inativa todos os Emissores antes do update, garantindo os Excluidos.
			riskFamilyProdPlayerDAO.inactivateEmissor(tplProductEntity);

			List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();

			for (TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity : listTplRiskFamilyProdPlayerEntity) {
				
				if (riskFamilyProdPlayerDAO.exists(tplRiskFamilyProdPlayerEntity)) {					
					riskFamilyProdPlayerDAO.update(tplRiskFamilyProdPlayerEntity);
				}
				else{
					riskFamilyProdPlayerDAO.insert(tplRiskFamilyProdPlayerEntity);
				}
			}
		}
	} 
  
  /**
	 * Update na tabela corrente Corp.
	 * 
	 * Antes do update nos Emissores inativa todos. Assim garante os excluidos.
	 * 
	 * @param tplProductEntity
	 */
	private void updateCorpInCurrent(TplProductCorpEntity tplProductCorpEntity) {

		TplProductCorpDAO productCorpDAO = ODSDAOFactory.getInstance().getTplProductCorpDAO();
		productCorpDAO.update(tplProductCorpEntity);

	}    
  
  /**
   * Delete dos Produtos MOV e seus Emissores de renda fixa
   * 
   * @param movDetailEntity
   */
  private void deleteMov(TplProductMovEntity tplProductMovEntity) {
	  TplProductMovDAO productMovDAO = ODSDAOFactory.getInstance().getTplProductMovDAO();
	  
	  TplRiskFamilyProdPlayerMovDAO riskFamilyProdPlayerMovDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerMovDAO();

	  //Deleta os Emissores
	  riskFamilyProdPlayerMovDAO.deleteAll(tplProductMovEntity);

	  //Deleta o Produto
	  productMovDAO.delete( tplProductMovEntity );
  }
  
  /**
   * Delete dos Produtos MOV e seus Emissores de renda fixa
   * 
   * @param movDetailEntity
   */
  private void deleteCorpMov(TplProductCorpMovEntity tplProductCorpMovEntity) {
	  TplProductCorpMovDAO productCorpMovDAO = ODSDAOFactory.getInstance().getTplProductCorpMovDAO();

	  //Deleta o Produto
	  productCorpMovDAO.delete( tplProductCorpMovEntity );
  }  

  /**
   * 
   * @param fncVO_
   */
  public void insertEmissor(BaseFncVO fncVO_) {
	  
	  this.validateInsertEmissor(fncVO_);
	  
	  if ( !fncVO_.hasErrors() ){
		  
		  ProductMovementDetailFncVO detailFncVO = ( ProductMovementDetailFncVO ) fncVO_;
		  String strEmissorCode = detailFncVO.getEmissorProdEmissorCode();
		  String strRiskCatCode = detailFncVO.getEmissorProdRiskCatCode();
		  
		  ProductDetailFnc productDetailFnc = new ProductDetailFnc();
		  
		  String emissorText = productDetailFnc.getEmissorDataSetText( strEmissorCode, detailFncVO.getProdEmissorsCodeDomain(), "PLYR_CNPJ_NBR", "PLYR_NAME" );	  
		  
		  BigDecimal riskCatCode = new BigDecimal(strRiskCatCode);
		  
		  String riskCatText = productDetailFnc.getRiskDataSetText( riskCatCode, detailFncVO.getProdRiskCatCodeDomain(), "PROD_INVST_RISK_CODE", "PROD_INVST_RISK_TEXT" );
		  
		  BigInteger emissorProdRiskCatCode = new BigInteger(strRiskCatCode);
		  
		  TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity = new TplRiskFamilyProdPlayerMovEntity();
		  tplRiskFamilyProdPlayerMovEntity.getData().setPlyrCnpjNbr(strEmissorCode);
		  tplRiskFamilyProdPlayerMovEntity.getData().setPlayerEmissorText(emissorText);
		  
		  tplRiskFamilyProdPlayerMovEntity.getData().setProdInvstRiskCode(emissorProdRiskCatCode);
		  tplRiskFamilyProdPlayerMovEntity.getData().setCatRiskText(riskCatText);
		  
		  TplProductMovEntity tplProductMovEntity = (TplProductMovEntity)detailFncVO.getBaseTplProductEntity();
		  TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO) tplProductMovEntity.getData();
		  
		  List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity = tplProductMovEntityVO.getListProductPlayerRiskVO();
		  listTplRiskFamilyProdPlayerMovEntity.add(tplRiskFamilyProdPlayerMovEntity);
		  
		  detailFncVO.setEmissorProdRiskCatCode(null);
		  detailFncVO.setEmissorProdEmissorCode(null);
	  }
	
  }  
  
  /**
   * Valida a inserção de Emissores.
   * 
   * @param BaseFncVO - Objeto com o estado atual da aplicação.
   */
  private void validateInsertEmissor(BaseFncVO fncVO_) {
	  
	  ProductMovementDetailFncVO detailFncVO = ( ProductMovementDetailFncVO ) fncVO_;
	  String strEmissorCode = detailFncVO.getEmissorProdEmissorCode();
	  String strRiskCatCode = detailFncVO.getEmissorProdRiskCatCode();

		// Validar Campos Obrigatórios
		if (strEmissorCode == null || strEmissorCode.trim().equals("")) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
					C_PROD_EMISSOR_CODE_DOMAIN_NOT_NULL);
		}

		if (strRiskCatCode == null || strRiskCatCode.trim().equals("")) {
			fncVO_.addError(BaseODSFncVO.C_ERROR_MANDATORY_FIELD,
					C_PROD_EMISSOR_CAT_RISK_CODE_DOMAIN_NOT_NULL);
		}
		
		TplProductMovEntity tplProductMovEntity = (TplProductMovEntity)detailFncVO.getBaseTplProductEntity();
		TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO) tplProductMovEntity.getData();
		  
		List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity = tplProductMovEntityVO.getListProductPlayerRiskVO();
		
		//Verifica se o Emissor ja nao foi inserido.
		for(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity : listTplRiskFamilyProdPlayerMovEntity){
			
			if(tplRiskFamilyProdPlayerMovEntity != null 
					&& tplRiskFamilyProdPlayerMovEntity.getData().getPlyrCnpjNbr() != null 
					&& tplRiskFamilyProdPlayerMovEntity.getData().getPlyrCnpjNbr().trim().equals(strEmissorCode)){
				
				fncVO_.addError(BaseODSFncVO.C_ERROR_EMISSOR_DUPLICATE);
				
				break;
			}
		}
		
	}

  /**
	 * Delete emissor do produto
	 * 
	 * @param form_
	 * @param fncVO_
	 */
  public void deleteEmissor(BaseFncVO fncVO_ ){
	  
	  ProductMovementDetailFncVO detailFncVO = ( ProductMovementDetailFncVO ) fncVO_;
	  
	  String emissorIndex = detailFncVO.getEmissorSeqNbr();
	  
	  TplProductMovEntity TplProductEntity = (TplProductMovEntity)detailFncVO.getBaseTplProductEntity();
	  TplProductMovEntityVO tplProductEntityVO = (TplProductMovEntityVO) TplProductEntity.getData();	  
	  
	  List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();
	  
	  listTplRiskFamilyProdPlayerEntity.remove(Integer.parseInt(emissorIndex));	  
	  
	  detailFncVO.setEmissorProdRiskCatCode(null);
	  detailFncVO.setEmissorProdEmissorCode(null);
  }    

}