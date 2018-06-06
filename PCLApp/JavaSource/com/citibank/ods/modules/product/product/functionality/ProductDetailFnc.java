package com.citibank.ods.modules.product.product.functionality;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplProductCorpEntity;
import com.citibank.ods.entity.pl.TplProductCorpMovEntity;
import com.citibank.ods.entity.pl.TplProductEntity;
import com.citibank.ods.entity.pl.TplProductMovEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerEntity;
import com.citibank.ods.entity.pl.TplRiskFamilyProdPlayerMovEntity;
import com.citibank.ods.entity.pl.valueobject.BaseTplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductCorpEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplProductMovEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplRiskFamilyProdPlayerMovEntityVO;
import com.citibank.ods.modules.product.product.form.ProductDetailForm;
import com.citibank.ods.modules.product.product.functionality.valueobject.ProductDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductCorpDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProductDAO;
import com.citibank.ods.persistence.pl.dao.To3ProductAccountDAO;
import com.citibank.ods.persistence.pl.dao.TplProdPlayerRoleDAO;
import com.citibank.ods.persistence.pl.dao.TplProductCorpMovDAO;
import com.citibank.ods.persistence.pl.dao.TplProductDAO;
import com.citibank.ods.persistence.pl.dao.TplProductMovDAO;
import com.citibank.ods.persistence.pl.dao.TplRiskFamilyProdPlayerMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author acacio.domingos,Apr 19, 2007
 *  
 */

public class ProductDetailFnc extends BaseProductDetailFnc implements
    ODSDetailFnc
{

  /*
   * Constante que identifica a inserção
   */
  private static final String C_INSERT_OPERATION = "Inserção";

  private static final String C_PRODUCT = "Produto";

  private static final String C_ASSOCIATION = "player associado a este produto";

  private static final String C_ASSOC_CUST = "cliente associado a este produto";
  
  private static final BigInteger TITULOS_PUBLICOS_CARTEIRAS = new BigInteger("18");
  
  private static final BigInteger TITULOS_PUBLICOS_TESOURARIA = new BigInteger("19");
  
  private static final BigInteger TITULOS_PRIVADOS_CONSUMER = new BigInteger("20");
  
  private static final BigInteger TITULOS_PRIVADO_CARTEIRAS = new BigInteger("21");

  private static final BigInteger TITULOS_PRIVADO_TESOURARIA = new BigInteger("23");
  

  /**
   * Insere os dados de uma Sub Família de Produtos.
   */
  public void insert( BaseFncVO fncVO_ )
  {
    validateInsert( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      this.insertInMovement( fncVO_, TplProductEntity.C_OPERN_CODE_INSERT );
    }
  }

  /*
   * Método genérico utilizado para inserir um elemento na tabela de movimento
   */
  private void insertInMovement( BaseFncVO fncVO_, String opernCode_ )
  {
    ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
    TplProductEntity productEntity = ( TplProductEntity ) detailFncVO.getBaseTplProductEntity();
    TplProductEntityVO tplProductEntityVO = (TplProductEntityVO)productEntity.getData();
    
    tplProductEntityVO.setLastUpdDate( new Date() );
    tplProductEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
                                                                    ? fncVO_.getLoggedUser().getUserID()
                                                                    : "" );
    
    TplProductCorpEntity productCorpEntity = ( TplProductCorpEntity ) detailFncVO.getBaseTplProductCorpEntity();
    TplProductCorpEntityVO tplProductCorpEntityVO = (TplProductCorpEntityVO)productCorpEntity.getData();
    
    tplProductCorpEntityVO.setLastUpdDate( tplProductEntityVO.getLastUpdDate() );
    tplProductCorpEntityVO.setLastUpdUserId( fncVO_.getLoggedUser() != null
                                                                    ? fncVO_.getLoggedUser().getUserID()
                                                                    : "" );    
    
    TplProductMovEntity productMovEntity = new TplProductMovEntity(
                                                                    productEntity,
                                                                    opernCode_ );
    
    TplProductMovDAO productMovDAO = ODSDAOFactory.getInstance().getTplProductMovDAO();
    productMovDAO.insert( productMovEntity );
    
    TplProductCorpMovEntity productCorpMovEntity = new TplProductCorpMovEntity(productCorpEntity, opernCode_ );

    TplProductCorpMovDAO productCorpMovDAO = ODSDAOFactory.getInstance().getTplProductCorpMovDAO();
    productCorpMovDAO.insert( productCorpMovEntity );    
    
    //TplProductEntity productEntity = ( TplProductEntity ) detailFncVO.getBaseTplProductEntity();
    TplProductMovEntityVO tplProductMovEntityVO = (TplProductMovEntityVO)productMovEntity.getData();
    
    //So insere emissor se o sub produto for (18,19,20,21,23)    
    if(tplProductMovEntityVO.getProdSubFamlCode() != null && 
    		  (!tplProductMovEntityVO.getProdSubFamlCode().equals(TITULOS_PUBLICOS_CARTEIRAS)
    		&& !tplProductMovEntityVO.getProdSubFamlCode().equals(TITULOS_PUBLICOS_TESOURARIA)
    		&& !tplProductMovEntityVO.getProdSubFamlCode().equals(TITULOS_PRIVADOS_CONSUMER)
    		&& !tplProductMovEntityVO.getProdSubFamlCode().equals(TITULOS_PRIVADO_CARTEIRAS)
    		&& !tplProductMovEntityVO.getProdSubFamlCode().equals(TITULOS_PRIVADO_TESOURARIA))){
    	
    	//Caso nao seja os subprodutos acima zera a lista de emissores pois nao seram inseridos
    	//O Usuario pode ter inserido um emissor e depois trocou o subproduto que nao exige incluir emissor. 
    	tplProductMovEntityVO.setListProductPlayerRiskVO(new ArrayList<TplRiskFamilyProdPlayerMovEntity>());
    }
    	
	    //Verifica se tem lista de emissor
	    if(tplProductMovEntityVO.getListProductPlayerRiskVO() != null && tplProductMovEntityVO.getListProductPlayerRiskVO().size() > 0){    	
	    	
	    	List<TplRiskFamilyProdPlayerMovEntity> listTplRiskFamilyProdPlayerMovEntity = tplProductMovEntityVO.getListProductPlayerRiskVO();
	    	
	    	TplRiskFamilyProdPlayerMovDAO riskFamilyProdPlayerMovDAO = ODSDAOFactory.getInstance().getTplRiskFamilyProdPlayerMovDAO();	
	    	
	    	for(TplRiskFamilyProdPlayerMovEntity tplRiskFamilyProdPlayerMovEntity : listTplRiskFamilyProdPlayerMovEntity){
	    		//Persiste os Emissores do Produto.
	    		TplRiskFamilyProdPlayerMovEntityVO tplRiskFamilyProdPlayerMovEntityVO = (TplRiskFamilyProdPlayerMovEntityVO) tplRiskFamilyProdPlayerMovEntity.getData();
	    		
	    		//Renda Fixa tem o Codigo 10
	    		tplRiskFamilyProdPlayerMovEntityVO.setProdFamlCode(new BigInteger(TplProductEntity.C_EMISSOR_RENDA_FIXA_FAMILE_CODE));
	
	    		tplRiskFamilyProdPlayerMovEntityVO.setLastUpdUserId(tplProductMovEntityVO.getLastUpdUserId());
	    		tplRiskFamilyProdPlayerMovEntityVO.setLastUpdDate(tplProductMovEntityVO.getLastUpdDate());
	    		tplRiskFamilyProdPlayerMovEntityVO.setProdCode(tplProductMovEntityVO.getProdCode());
	    		tplRiskFamilyProdPlayerMovEntityVO.setSysCode(tplProductMovEntityVO.getSysCode());
	    		tplRiskFamilyProdPlayerMovEntityVO.setSysSegCode(tplProductMovEntityVO.getSysSegCode());
	    		
	    		riskFamilyProdPlayerMovDAO.insert(tplRiskFamilyProdPlayerMovEntity);
	    	}
	    }

  }

  /**
   * Altera os dados de uma Sub Família de Produtos.
   */
  public void update( BaseFncVO fncVO_ )
  {
    validateUpdate( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      this.insertInMovement( fncVO_, TplProductEntity.C_OPERN_CODE_UPDATE );
    }
  }

  /**
   * Remove uma Sub Família de Produtos.
   */
  public void delete( BaseFncVO fncVO_ )
  {
    validateDelete( fncVO_ );
    if ( !fncVO_.hasErrors() )
    {
      ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
      super.loadProduct( detailFncVO );
      super.loadProductCorp( detailFncVO );
      this.insertInMovement( fncVO_, TplProductEntity.C_OPERN_CODE_DELETE );
    }
  }

  /**
   * Validação de Insert
   */
  public void validateInsert( BaseFncVO fncVO_ )
  {
    ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
    BaseTplProductEntityVO productEntityVO = detailFncVO.getBaseTplProductEntity().getData();

    //  Validar Campos Obrigatórios
    if ( productEntityVO.getProdCode() == null
         || productEntityVO.getProdCode() == "" )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getSysCode() == null
         || productEntityVO.getSysCode().equals( "" ) )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_SYS_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getSysSegCode() == null
         || productEntityVO.getSysSegCode().intValue() == 0 )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_SYS_SEG_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getProdName() == null
         || productEntityVO.getProdName() == "" )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_NAME_DESCRIPTION );
    }

    if ( productEntityVO.getProdText() == null
         || productEntityVO.getProdText() == "" )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_TEXT_DESCRIPTION );
    }

    if ( productEntityVO.getProdStatCode() == null
         || productEntityVO.getProdStatCode() == "" )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_STAT_CODE );
    }
    
    if(productEntityVO.getProdRiskCatCode() == null || productEntityVO.getProdRiskCatCode().equals("")){
    	if(!productEntityVO.getSysCode().equals("PM")){
	    	fncVO_.addError(ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
	    					ProductDetailFncVO.C_PROD_EMISSOR_CAT_RISK_CODE);
    	}
    }
    
    if(productEntityVO.getProdSubFamlCode() == null || productEntityVO.getProdSubFamlCode().equals("")){
    	fncVO_.addError(ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
    					ProductDetailFncVO.C_PROD_SUB_FAML_CODE_DESCRIPTION);
    } 
    
    if(productEntityVO.getProdSentIaInd() == null || productEntityVO.getProdSentIaInd().equals("")){
    	fncVO_.addError(ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
						ProductDetailFncVO.C_PROD_SENT_IA_IND);
    }
    
    if(productEntityVO.getAssocClassProdCode() == null || productEntityVO.getAssocClassProdCode().equals("")){
    	if(!productEntityVO.getSysCode().equals("PM")){
	    	fncVO_.addError(ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
							ProductDetailFncVO.C_ASSOC_CLASS_PROD_CODE);
    	}
    }
    //  Validar se já existe um registro com este codigo na "Current",
    // caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_ERROR_DUPLICATE_PK );
      }

      //  Validar se já existe movimento com este codigo
      if ( this.existsInMovement( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_ERROR_IN_MOVEMENT );
      }

      // Validar se a família de produtos passada existe e é válida
      if ( !this.existsProductActive( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_INVALID_FK, C_INSERT_OPERATION,
                         ProductDetailFncVO.C_PROD_CODE_DESCRIPTION );
      }
    }
  }

  /**
   * Validação de Update
   */
  public void validateUpdate( BaseFncVO fncVO_ )
  {
    ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
    BaseTplProductEntityVO productEntityVO = detailFncVO.getBaseTplProductEntity().getData();

    //  Validar Campos Obrigatórios
    if ( productEntityVO.getProdCode() == null
         || productEntityVO.getProdCode() == "" )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getSysCode() == null
         || productEntityVO.getSysCode().equals( "" ) )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_SYS_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getSysSegCode() == null
         || productEntityVO.getSysSegCode().intValue() == 0 )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_SYS_SEG_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getProdName() == null
         || productEntityVO.getProdName().equals( "" ) )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_NAME_DESCRIPTION );
    }

    if ( productEntityVO.getProdText() == null
         || productEntityVO.getProdText().equals( "" ) )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_TEXT_DESCRIPTION );
    }

    if ( productEntityVO.getProdStatCode() != null)
         if(productEntityVO.getProdStatCode() == "" )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_STAT_CODE );
    }
    
    if(productEntityVO.getProdRiskCatCode() == null || productEntityVO.getProdRiskCatCode().equals("")){
    	if(!productEntityVO.getSysCode().equals("PM")){
    		fncVO_.addError(ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
    					ProductDetailFncVO.C_PROD_EMISSOR_CAT_RISK_CODE);
    	}
    }
    
    if(productEntityVO.getProdSubFamlCode() == null || productEntityVO.getProdSubFamlCode().equals("")){
    	fncVO_.addError(ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
    					ProductDetailFncVO.C_PROD_SUB_FAML_CODE_DESCRIPTION);
    }
    
    if(productEntityVO.getProdSentIaInd() == null || productEntityVO.getProdSentIaInd().equals("")){
    	fncVO_.addError(ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
						ProductDetailFncVO.C_PROD_SENT_IA_IND);
    }
    
    if(productEntityVO.getAssocClassProdCode() == null || productEntityVO.getAssocClassProdCode().equals("")){
    	if(!productEntityVO.getSysCode().equals("PM")){
	    	fncVO_.addError(ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
							ProductDetailFncVO.C_ASSOC_CLASS_PROD_CODE);
    	}
    }

    //  caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      //  Validar se já existe movimento com este codigo
      if ( this.existsInMovement( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
    }
  }

  /**
   * Validação de Delete
   */
  public void validateDelete( BaseFncVO fncVO_ )
  {
    ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
    BaseTplProductEntityVO productEntityVO = detailFncVO.getBaseTplProductEntity().getData();

    //  Validar Campos Obrigatórios
    if ( productEntityVO.getProdCode() == null
         || productEntityVO.getProdCode() == "" )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getSysCode() == null
         || productEntityVO.getSysCode().equals( "" ) )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_SYS_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getSysSegCode() == null
         || productEntityVO.getSysSegCode().intValue() == 0 )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_SYS_SEG_CODE_DESCRIPTION );
    }

    if ( productEntityVO.getProdName() == null
         || productEntityVO.getProdName().equals( "" ) )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_NAME_DESCRIPTION );
    }

    if ( productEntityVO.getProdText() == null
         || productEntityVO.getProdText().equals( "" ) )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_MANDATORY_FIELD,
                       ProductDetailFncVO.C_PROD_TEXT_DESCRIPTION );
    }

    //  caso os campos obrigatórios estejam presentes
    if ( !fncVO_.hasErrors() )
    {
      if ( !this.existsActive( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_ERROR_PK_NOT_FOUND );
      }

      //  Validar se já existe movimento com este codigo
      if ( this.existsInMovement( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_ERROR_IN_MOVEMENT );
      }
      if ( this.existsDependAssocProdPlayer( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_ERROR_IS_REFERENCED, C_PRODUCT,
                         C_ASSOCIATION );
      }

      if ( this.existsDependAssocCustomer( detailFncVO ) )
      {
        fncVO_.addError( ProductDetailFncVO.C_ERROR_IS_REFERENCED, C_PRODUCT,
                         C_ASSOC_CUST );
      }
    }
  }

  /**
   * Carregamentos iniciais - Tela de detalhe
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForConsult(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForConsult( BaseFncVO fncVO_ )
  {
	ProductDetailFncVO fncVO = ( ProductDetailFncVO ) fncVO_;  	
	
    super.load( fncVO );
    super.loadCorp(fncVO);
    super.loadDomains( fncVO );
	super.loadProdFamlCode( fncVO );
	
    //Complementa as informacoes dos emissores
    super.completeDataListaEmisso(fncVO );
	
  }

  /**
   * Carregamentos iniciais - Insert
   * @see com.citibank.ods.common.functionality.ODSDetailFnc#loadForInsert(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void loadForInsert( BaseFncVO fncVO_ )
  {
    //
  }

  /**
   * Carregamentos iniciais - Update
   */
  public void loadForUpdate( BaseFncVO fncVO_ )
  {
    ProductDetailFncVO fncVO = ( ProductDetailFncVO ) fncVO_;
    //Alteraçao G&P INICIO 29/05/2008
    //limpa o subcodigo da familia da sessao
	//fncVO.setProdSubFamlCode(null);
    
    

    if ( this.existsInMovement( fncVO ) )
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      
	  super.loadProdFamlCode( fncVO );
	  super.load( fncVO );	  
	  super.loadCorp( fncVO );
	  super.loadDomains( fncVO );    
	  
      //Complementa as informacoes dos emissores
      super.completeDataListaEmisso(fncVO);
      
    }

  }

  /**
   * Carregamentos iniciais - Delete
   *  
   */
  public void loadForDelete( BaseFncVO fncVO_ )
  {
    ProductDetailFncVO fncVO = (ProductDetailFncVO) fncVO_;
    
    if ( this.existsInMovement( fncVO))
    {
      fncVO_.addError( ProductDetailFncVO.C_ERROR_IN_MOVEMENT );
    }
    else
    {
      super.load(fncVO);
      super.loadCorp(fncVO);
      super.loadDomains(fncVO);
      
      //Complementa as informacoes dos emissores
      super.completeDataListaEmisso(fncVO);
    }

  }

  /**
   * Retorna o DAO utilizado pelo método load da super classe
   */
  protected BaseTplProductDAO getDAO()
  {
    return ODSDAOFactory.getInstance().getTplProductDAO();
  }
  
  @Override
  protected BaseTplProductCorpDAO getCorpDAO() {
	  return ODSDAOFactory.getInstance().getTplProductCorpDAO();
  }  

  /**
   * Verifica se existe um registro na tabela de movimento
   */
  private boolean existsInMovement( ProductDetailFncVO detailFncVO_ )
  {
    TplProductMovDAO productMovDAO = ODSDAOFactory.getInstance().getTplProductMovDAO();

    TplProductMovEntity productMovEntity = new TplProductMovEntity();
    productMovEntity.getData().setProdCode(
                                            detailFncVO_.getBaseTplProductEntity().getData().getProdCode() );
    productMovEntity.getData().setSysCode(
                                           detailFncVO_.getBaseTplProductEntity().getData().getSysCode() );

    productMovEntity.getData().setSysSegCode(
                                              detailFncVO_.getBaseTplProductEntity().getData().getSysSegCode() );

    return productMovDAO.exists( productMovEntity );
  }

  /**
   * Verifica se já existe um registro na tabela de "Current" com o código
   * passado e o seu status é "Ativo"
   */
  private boolean existsActive( ProductDetailFncVO detailFncVO_ )
  {
    TplProductDAO productDAO = ODSDAOFactory.getInstance().getTplProductDAO();
    return productDAO.existsActive( ( TplProductEntity ) detailFncVO_.getBaseTplProductEntity() );
  }

  private boolean existsDependAssocProdPlayer( ProductDetailFncVO detailFncVO_ )
  {

    TplProdPlayerRoleDAO prodPlayerRoleDAO = ODSDAOFactory.getInstance().getTplProdPlayerRoleDAO();
    TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) detailFncVO_.getBaseTplProductEntity().getData();
    DataSet result = prodPlayerRoleDAO.list(
                                             null,
                                             null,
                                             null,
                                             tplProductEntityVO.getProdCode(),
                                             tplProductEntityVO.getSysCode(),
                                             tplProductEntityVO.getSysSegCode().toString(),
                                             null );

    if ( result.size() > 0 )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  private boolean existsDependAssocCustomer( ProductDetailFncVO detailFncVO_ )
  {

    To3ProductAccountDAO prodAccountDAO = ODSDAOFactory.getInstance().getTo3ProductAccountDAO();
    TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) detailFncVO_.getBaseTplProductEntity().getData();
    DataSet result = prodAccountDAO.list( null, null, null,
                                          tplProductEntityVO.getProdCode(),
                                          null, null, null, null,"","" );

    if ( result.size() > 0 )
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Verifica se o produto passado é valido
   */
  private boolean existsProductActive( ProductDetailFncVO detailFncVO_ )
  {
    TplProductDAO productDAO = ODSDAOFactory.getInstance().getTplProductDAO();
    TplProductEntity entity = new TplProductEntity();
    entity.getData().setProdCode(
                                  detailFncVO_.getBaseTplProductEntity().getData().getProdCode() );
    entity.getData().setSysCode(
                                 detailFncVO_.getBaseTplProductEntity().getData().getSysCode() );
    entity.getData().setSysSegCode(
                                    detailFncVO_.getBaseTplProductEntity().getData().getSysSegCode() );
    return productDAO.existsActive( entity );
  }

  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    super.updateFormFromFncVO( form_, fncVO_ );
    ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
    ProductDetailForm detailForm = ( ProductDetailForm ) form_;
    TplProductEntityVO tplProductEntityVO = ( TplProductEntityVO ) detailFncVO.getBaseTplProductEntity().getData();
    SimpleDateFormat dateFormat = new SimpleDateFormat(
                                                        Globals.FuncionalityFormatKeys.C_FORMAT_DATE_DDMMYYYY );

    detailForm.setLastAuthUserId( tplProductEntityVO.getLastAuthUserId() );
    if ( tplProductEntityVO.getLastAuthDate() != null
         && !tplProductEntityVO.getLastAuthDate().equals( "" ) )
    {
      detailForm.setLastAuthDate( dateFormat.format( tplProductEntityVO.getLastAuthDate() ) );
    }
    else
    {
      detailForm.setLastAuthDate( null );
    }

    String recStatCode = ( ( TplProductEntityVO ) ( detailFncVO.getBaseTplProductEntity().getData() ) ).getRecStatCode();
    if ( recStatCode != null && !"".equals( recStatCode ) )
    {
      String strRecStatCode = ODSConstraintDecoder.decodeRecStatus( recStatCode );
      detailForm.setRecStatCode( strRecStatCode );
    }
//    detailForm.setFundDistFormTypeCode(detailFncVO.getFundDistFormTypeCode());
//  	detailForm.setTermText(detailFncVO.getTermText());
//  	detailForm.setStrategyStartDate(Util.dateToString(detailFncVO.getStrategyStartDate(), "dd/MM/yyyy"));
//  	detailForm.setStrategyCloseDate(Util.dateToString(detailFncVO.getStrategyCloseDate(), "dd/MM/yyyy"));
//  	detailForm.setApplicationStatCode(detailFncVO.getApplicationStatCode());
//  	detailForm.setWthdrStatCode(detailFncVO.getWthdrStatCode());
//  	detailForm.setPerfmRateText(detailFncVO.getPerfmRateText());
//  	detailForm.setCloseDate(Util.dateToString(detailFncVO.getCloseDate(), "dd/MM/yyyy"));
  }
  
  /**
   * Insere emissor do produto
   * 
   * @param form_
   * @param fncVO_
   */
  public void insertEmissor(BaseFncVO fncVO_ ){
	  
	  this.validateInsertEmissor(fncVO_);
	  
	  if ( !fncVO_.hasErrors() ){
		  
		  ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
		  String strEmissorCode = detailFncVO.getEmissorProdEmissorCode();
		  String strRiskCatCode = detailFncVO.getEmissorProdRiskCatCode();
		  
		  String emissorText = this.getEmissorDataSetText( strEmissorCode, detailFncVO.getProdEmissorsCodeDomain(), "PLYR_CNPJ_NBR", "PLYR_NAME" );	  
		  
		  BigDecimal riskCatCode = new BigDecimal(strRiskCatCode);
		  
		  String riskCatText = this.getRiskDataSetText( riskCatCode, detailFncVO.getProdRiskCatCodeDomain(), "PROD_INVST_RISK_CODE", "PROD_INVST_RISK_TEXT" );
		  
		  BigInteger emissorProdRiskCatCode = new BigInteger(strRiskCatCode);
		  
		  TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity = new TplRiskFamilyProdPlayerEntity();
		  tplRiskFamilyProdPlayerEntity.getData().setPlyrCnpjNbr(strEmissorCode);
		  tplRiskFamilyProdPlayerEntity.getData().setPlayerEmissorText(emissorText);
		  
		  tplRiskFamilyProdPlayerEntity.getData().setProdInvstRiskCode(emissorProdRiskCatCode);
		  tplRiskFamilyProdPlayerEntity.getData().setCatRiskText(riskCatText);
		  
		  TplProductEntity TplProductEntity = (TplProductEntity)detailFncVO.getBaseTplProductEntity();
		  TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) TplProductEntity.getData();
		  
		  List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();
		  listTplRiskFamilyProdPlayerEntity.add(tplRiskFamilyProdPlayerEntity);
		  
		  detailFncVO.setEmissorProdRiskCatCode(null);
		  detailFncVO.setEmissorProdEmissorCode(null);
	  }
  }
  
  /**
   * Recupera o text do DataSet passando o Code
   * 
   * @param selectedCode
   * @param dataSet
   * @param code
   * @param text
   * @return
   */
  public String getRiskDataSetText(BigDecimal selectedCode, DataSet dataSet, String code, String text){
	  
	  int indexRiskCatCode = -1;
	  
	  String retornText = "";

	  ArrayList listaRiskCatCode = dataSet.getColumnValuesByName(code);
	  
	  if(listaRiskCatCode != null && listaRiskCatCode.size() > 0){
		
		  indexRiskCatCode = listaRiskCatCode.indexOf(selectedCode);
		  
		  ArrayList listaCatText = dataSet.getColumnValuesByName(text);
		  
		  if(listaCatText != null && listaCatText.size() > 0){
			  retornText = (String) listaCatText.get(indexRiskCatCode);
		  }
	  }
	  
	  return retornText;
  }
  
  /**
   * Recupera o text do DataSet passando o Code
   * 
   * @param selectedCode
   * @param dataSet
   * @param code
   * @param text
   * @return
   */
  public String getEmissorDataSetText(String selectedCode, DataSet dataSet, String code, String text){
	  
	  int indexRiskCatCode = -1;
	  
	  String retornText = "";

	  ArrayList listaRiskCatCode = dataSet.getColumnValuesByName(code);
	  
	  if(listaRiskCatCode != null && listaRiskCatCode.size() > 0){
		
		  indexRiskCatCode = listaRiskCatCode.indexOf(selectedCode);
		  
		  ArrayList listaCatText = dataSet.getColumnValuesByName(text);
		  
		  if(listaCatText != null && listaCatText.size() > 0){
			  retornText = (String) listaCatText.get(indexRiskCatCode);
		  }
	  }
	  
	  return retornText;
  }  
  
  /**
   * Valida a inserção de Emissores.
   * 
   * @param BaseFncVO - Objeto com o estado atual da aplicação.
   */
  private void validateInsertEmissor(BaseFncVO fncVO_) {
	  
	  ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
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
		
		TplProductEntity TplProductEntity = (TplProductEntity)detailFncVO.getBaseTplProductEntity();
		TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) TplProductEntity.getData();
		  
		List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();
		
		//Verifica se o Emissor ja nao foi inserido.
		for(TplRiskFamilyProdPlayerEntity tplRiskFamilyProdPlayerEntity : listTplRiskFamilyProdPlayerEntity){
			
			if(tplRiskFamilyProdPlayerEntity != null 
					&& tplRiskFamilyProdPlayerEntity.getData().getPlyrCnpjNbr() != null 
					&& tplRiskFamilyProdPlayerEntity.getData().getPlyrCnpjNbr().trim().equals(strEmissorCode)){
				
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
	  
	  ProductDetailFncVO detailFncVO = ( ProductDetailFncVO ) fncVO_;
	  
	  String emissorIndex = detailFncVO.getEmissorSeqNbr();
	  
	  TplProductEntity TplProductEntity = (TplProductEntity)detailFncVO.getBaseTplProductEntity();
	  TplProductEntityVO tplProductEntityVO = (TplProductEntityVO) TplProductEntity.getData();	  
	  
	  List<TplRiskFamilyProdPlayerEntity> listTplRiskFamilyProdPlayerEntity = tplProductEntityVO.getListProductPlayerRiskVO();
	  
	  listTplRiskFamilyProdPlayerEntity.remove(Integer.parseInt(emissorIndex));	  
	  
	  detailFncVO.setEmissorProdRiskCatCode(null);
	  detailFncVO.setEmissorProdEmissorCode(null);
  }  

  /**
   * Retorna uma instancia do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */
  public BaseFncVO createFncVO()
  {
    return new ProductDetailFncVO();
  }
  
}