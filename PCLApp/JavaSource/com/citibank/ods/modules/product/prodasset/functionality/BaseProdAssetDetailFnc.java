/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodasset.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplProdAssetEntity;
import com.citibank.ods.modules.product.prodasset.form.BaseProdAssetDetailForm;
import com.citibank.ods.modules.product.prodasset.functionality.valueobject.BaseProdAssetDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class BaseProdAssetDetailFnc extends BaseFnc
{
  protected static final String C_PROD_Asset_CODE = "Código da Classe";

  protected static final String C_PROD_ASSET_TEXT = "Descrição da Classe";

  /**
   * Atualiza os atributos do FncVO com os atributos vindos da Form
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFncVOFromForm( ActionForm form_, BaseFncVO fncVO_ )
  {
	BaseProdAssetDetailFncVO baseProdAssetDetailFncVO = ( BaseProdAssetDetailFncVO ) fncVO_;
	BaseProdAssetDetailForm baseProdAssetDetailForm = ( BaseProdAssetDetailForm ) form_;

	if ( baseProdAssetDetailForm.getProdAssetCode() != null
		 && !"".equals( baseProdAssetDetailForm.getProdAssetCode() ) )
	{
	  baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().setProdAssetCode(new BigInteger(baseProdAssetDetailForm.getProdAssetCode()));
	}
	
	//Seta o código do asset caso o mesmo venha seleciado da consulta de aprovação centralizada
	if(baseProdAssetDetailForm.getSelectedCode()!= null && !baseProdAssetDetailForm.getSelectedCode().equals("")){
		baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().setProdAssetCode(new BigInteger(baseProdAssetDetailForm.getSelectedCode().substring(1,8)));
	}
	
	baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().setProdAssetText(baseProdAssetDetailForm.getProdAssetText().trim() );
	
	if(baseProdAssetDetailForm.getAssetClassCustRptOrderNbr() != null 
	   && !"".equals( baseProdAssetDetailForm.getAssetClassCustRptOrderNbr())){
		baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().setAssetClassCustRptOrderNbr(
		                new BigInteger(baseProdAssetDetailForm.getAssetClassCustRptOrderNbr()));
	
	}  																					  
	else{
		baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().setAssetClassCustRptOrderNbr(null);
	}
		
  }

  /**
   * Atualiza os atributos da Form com os atributos vindos do FncVO
   * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
   *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
   */
  public void updateFormFromFncVO( ActionForm form_, BaseFncVO fncVO_ )
  {
	BaseProdAssetDetailFncVO baseProdAssetDetailFncVO = ( BaseProdAssetDetailFncVO ) fncVO_;
	BaseProdAssetDetailForm baseProdAssetDetailForm = ( BaseProdAssetDetailForm ) form_;

	//Setar Código
	if ( baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getProdAssetCode() != null )
	{
	  baseProdAssetDetailForm.setProdAssetCode( baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getProdAssetCode().toString() );
	}
	else{
	  baseProdAssetDetailForm.setProdAssetCode(null);
	}
	
	if ( baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getAssetClassCustRptOrderNbr() != null )
	{
	  baseProdAssetDetailForm.setAssetClassCustRptOrderNbr( baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getAssetClassCustRptOrderNbr().toString() );
	}
	else{
	  baseProdAssetDetailForm.setAssetClassCustRptOrderNbr(null);
	}	

	//Setar Descrição
	baseProdAssetDetailForm.setProdAssetText( baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getProdAssetText() );
	//Setar Usuário
	baseProdAssetDetailForm.setLastUpdUserId( baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getLastUpdUserId() );
		
	//Formatar a data para o Formato DD/MM/AAAA - HH/MM
	SimpleDateFormat formatDate = new SimpleDateFormat(
														Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
	Date formDate = new Date();
	formDate = baseProdAssetDetailFncVO.getBaseTplProdAssetEntity().getData().getLastUpdDate();
	if ( formDate != null )
	{
	  //Setar Data no formato correto
	  String date = formatDate.format( formDate );
	  baseProdAssetDetailForm.setLastUpdDate( date );
	}
	
	
  }
  
  /**
	 * Realiza o carregamento dos dados de família de Produtos
	 * @author leonardo.nakada
	 */
	protected void loadProductFamilyDomain(
										   BaseProdAssetDetailFncVO detailFncVO )
	{
	  TplProdAssetDAO dao = ODSDAOFactory.getInstance().getTplProdAssetDAO();
	  DataSet resultDomain = dao.loadDomain();
	  detailFncVO.setProdAssetCodeDomain( resultDomain );
	}
	
	/**
	   * Carrega todos os domínios utilizados pela transação
	   */
	  protected void loadDomains( BaseProdAssetDetailFncVO detailFncVO )
	  {
		this.loadProductFamilyDomain( detailFncVO );
	  }

  /**
   * Recupera um elemento de Qualificador de Produto, dado os critérios
   */
  public void loadProdAsset( BaseProdAssetDetailFncVO detailFncVO_ )
  {
	BaseTplProdAssetDAO prodAssetDAO = this.getDAO();
	BaseTplProdAssetEntity tplProdAssetEntity = ( BaseTplProdAssetEntity ) prodAssetDAO.find( detailFncVO_.getBaseTplProdAssetEntity() );
	detailFncVO_.setBaseTplProdAssetEntity( tplProdAssetEntity );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
   */

  protected abstract BaseTplProdAssetDAO getDAO();


}
