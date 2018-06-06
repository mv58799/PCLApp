/*
 * Created on Apr 4, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.product.product.functionality;

import java.math.BigInteger;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSListFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.modules.product.product.form.BaseProductListForm;
import com.citibank.ods.modules.product.product.functionality.valueobject.BaseProductListFncVO;
import com.citibank.ods.persistence.pl.dao.TplProdQlfyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdRiskCatPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubFamlPrvtDAO;
import com.citibank.ods.persistence.pl.dao.TplProductFamilyPrvtDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseProductListFnc extends BaseFnc implements ODSListFnc
{

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProductListFncVO productListFncVO = ( BaseProductListFncVO ) fncVO_;
    BaseProductListForm productListForm = ( BaseProductListForm ) form_;

    String formProdCodeSrc = productListForm.getProdCodeSrc();
    productListFncVO.setProdCodeSrc( formProdCodeSrc );

    String formProdNameSrc = productListForm.getProdNameSrc();
    productListFncVO.setProdNameSrc( formProdNameSrc );
    String orderby = productListForm.getOrderBy();
    productListFncVO.setOrderBy(orderby);

    String formProdFamlCodeSrc = productListForm.getProdFamlCodeSrc();
    if ( formProdFamlCodeSrc != null && !formProdFamlCodeSrc.equals( "" ) )
    {
      productListFncVO.setProdFamlCodeSrc( new BigInteger( formProdFamlCodeSrc ) );
    }
    else
    {
      productListFncVO.setProdFamlCodeSrc( null );
    }
    
    String formProdQlfyCodeSrc = productListForm.getProdQlfyCodeSrc();
    if ( formProdQlfyCodeSrc != null && !formProdQlfyCodeSrc.equals( "" ) )
    {
      productListFncVO.setProdQlfyCodeSrc( new BigInteger( formProdQlfyCodeSrc ) );
    }
    else
    {
      productListFncVO.setProdQlfyCodeSrc( null );
    }

    String formProdSubFamlCodeSrc = productListForm.getProdSubFamlCodeSrc();
    if ( formProdSubFamlCodeSrc != null && !formProdSubFamlCodeSrc.equals( "" ) )
    {
      productListFncVO.setProdSubFamlCodeSrc( new BigInteger(
                                                              formProdSubFamlCodeSrc ) );
    }
    else
    {
      productListFncVO.setProdSubFamlCodeSrc( null );
    }

    String formProdRiskCatCodeSrc = productListForm.getProdRiskCatCodeSrc();
    if ( formProdRiskCatCodeSrc != null && !formProdRiskCatCodeSrc.equals( "" ) )
    {
      productListFncVO.setProdRiskCatCodeSrc( new BigInteger(
                                                              formProdRiskCatCodeSrc ) );
    }
    else
    {
      productListFncVO.setProdRiskCatCodeSrc( null );
    }

    String formLastUpdUserId = productListForm.getLastUpdUserIdSrc();
    productListFncVO.setLastUpdUserIdSrc( formLastUpdUserId );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
    BaseProductListForm baseProductListForm = ( BaseProductListForm ) form_;
    BaseProductListFncVO baseProductListFncVO = ( BaseProductListFncVO ) fncVO_;

    baseProductListForm.setProdCodeSrc( baseProductListFncVO.getProdCodeSrc() );
    baseProductListForm.setProdNameSrc( baseProductListFncVO.getProdNameSrc() );
    if ( baseProductListFncVO.getProdQlfyCodeSrc() != null && !baseProductListFncVO.getProdQlfyCodeSrc().equals("") )
    {
      baseProductListForm.setProdQlfyCodeSrc(baseProductListFncVO.getProdQlfyCodeSrc().toString());
    }
    else
    {
      baseProductListForm.setProdQlfyCodeSrc(null);
    }
    baseProductListFncVO.setProdSubFamlCodeSrc(baseProductListFncVO.getProdSubFamlCodeSrc() );    
    if (baseProductListFncVO.getProdRiskCatCodeSrc() != null && !baseProductListFncVO.getProdRiskCatCodeSrc().equals("") )
    {
      baseProductListForm.setProdRiskCatCodeSrc( baseProductListFncVO.getProdRiskCatCodeSrc().toString() );
    }
    else
    {
      baseProductListForm.setProdRiskCatCodeSrc( null );
    }
    
    if (baseProductListFncVO.getProdFamlCodeSrc() != null && baseProductListFncVO.getProdFamlCodeSrc().intValue() != 0 )
    {
      baseProductListForm.setProdFamlCodeSrc( baseProductListFncVO.getProdFamlCodeSrc().toString() );
    }
    else
    {
      baseProductListForm.setProdFamlCodeSrc( null );
    }

    baseProductListForm.setResults( baseProductListFncVO.getResults() );

    baseProductListForm.setProdSubFamlCodeDomain( baseProductListFncVO.getProdSubFamlCodeDomain() );
    baseProductListForm.setProdQlfyCodeDomain( baseProductListFncVO.getProdQlfyCodeDomain() );
    baseProductListForm.setProdRiskCodeDomain( baseProductListFncVO.getProdRiskDomain() );
    baseProductListForm.setProdFamlCodeDomain( baseProductListFncVO.getProdFamlCodeDomain() );
    baseProductListForm.setLastUpdUserIdSrc( baseProductListFncVO.getLastUpdUserIdSrc() );
    
    if(baseProductListFncVO.getListProduct() != null){
		baseProductListForm.setListProduct(baseProductListFncVO.getListProduct());    	
    }
    else{
		baseProductListForm.setListProduct(null);
    }
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#load(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */

  /**
   * Realiza o carregamento dos dados de família de Produtos
   * @author
   */  
  protected void loadProductFamilyDomain( BaseProductListFncVO listFncVO_ )
  {
    TplProductFamilyPrvtDAO prodFamlPrvtDAO = ODSDAOFactory.getInstance().getTplProductFamilyPrvtDAO();
    DataSet resultDomain = prodFamlPrvtDAO.loadDomain();
    listFncVO_.setProdFamlCodeDomain( resultDomain );
  }
  
  protected void loadProductSubFamilyDomain( BaseProductListFncVO listFncVO_ )
  {
    TplProdSubFamlPrvtDAO prodSubFamlPrvtDAO = ODSDAOFactory.getInstance().getTplProductSubFamilyPrvtDAO();
    DataSet resultDomain = prodSubFamlPrvtDAO.loadDomain();
    listFncVO_.setProdSubFamlCodeDomain( resultDomain );
  }

  protected void loadProductQlfyDomain( BaseProductListFncVO listFncVO_ )
  {
    TplProdQlfyPrvtDAO prodQlfyPrvtDAO = ODSDAOFactory.getInstance().getTplProdQlfyPrvtDAO();
    DataSet resultDomain = prodQlfyPrvtDAO.loadDomain();
    listFncVO_.setProdQlfyCodeDomain( resultDomain );
  }

  protected void loadProductRiskDomain( BaseProductListFncVO listFncVO_ )
  {
    TplProdRiskCatPrvtDAO prodRiskCatPrvtDAO = ODSDAOFactory.getInstance().getTplProdRiskCatPrvtDAO();
    DataSet resultDomain = prodRiskCatPrvtDAO.loadDomain();
    listFncVO_.setProdRiskDomain( resultDomain );
  }

  protected void loadDomains( BaseProductListFncVO listFncVO_ )
  {
    this.loadProductSubFamilyDomain( listFncVO_ );

    this.loadProductQlfyDomain( listFncVO_ );

    this.loadProductRiskDomain( listFncVO_ );
    
    this.loadProductFamilyDomain( listFncVO_ );

  }


  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.ODSListFnc#validateList(com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void validateList( BaseFncVO fncVO_ )
  {
    // 
  }

}

