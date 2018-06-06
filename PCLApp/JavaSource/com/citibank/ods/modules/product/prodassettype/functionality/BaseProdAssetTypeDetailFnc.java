/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodassettype.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplProdAssetTypeEntity;
import com.citibank.ods.modules.product.prodassettype.form.BaseProdAssetTypeDetailForm;
import com.citibank.ods.modules.product.prodassettype.functionality.valueobject.BaseProdAssetTypeDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdAssetTypeDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class BaseProdAssetTypeDetailFnc extends BaseFnc {
	protected static final String C_PROD_ASSETTYPE_CODE = "Tipo de Ativo";

	protected static final String C_PROD_ASSETTYPE_TEXT ="Descrição do tipo de Ativo";
		
	protected static final String C_PROD_SUB_ASSET_TEXT ="Sub Classe de Ativo";	
			
	protected static final String C_ASSET_TYPE_CUST_RPT_ORDER_NBR = "Ordem do Tipo de Ativo";			

	/**
	 * Atualiza os atributos do FncVO com os atributos vindos da Form
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
	 *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		BaseProdAssetTypeDetailFncVO baseProdAssetTypeDetailFncVO =
			(BaseProdAssetTypeDetailFncVO) fncVO_;
		
		BaseProdAssetTypeDetailForm baseProdAssetTypeDetailForm =
			(BaseProdAssetTypeDetailForm) form_;
				
		if (baseProdAssetTypeDetailForm.getProdAssetTypeCode() != null
			&& !"".equals(baseProdAssetTypeDetailForm.getProdAssetTypeCode())) {
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetTypeCode(
					new BigInteger(
						baseProdAssetTypeDetailForm.getProdAssetTypeCode()));
		}
		else{
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetTypeCode(null);			
		}
		
		//Seta o código do asset caso o mesmo venha seleciado da consulta de aprovação centralizada
		if(baseProdAssetTypeDetailForm.getSelectedCode()!= null && !baseProdAssetTypeDetailForm.getSelectedCode().equals("")){
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetTypeCode(new BigInteger(baseProdAssetTypeDetailForm.getSelectedCode().substring(1,8)));
		}		

		if (baseProdAssetTypeDetailForm.getProdAssetTypeText() == null){
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetTypeText(null);			
		} else {
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetTypeText(
					baseProdAssetTypeDetailForm.getProdAssetTypeText().trim());
		}
		
		if (baseProdAssetTypeDetailForm.getProdSubAssetCode() != null && !baseProdAssetTypeDetailForm.getProdSubAssetCode().equals("")) {
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdSubAssetCode(
					new BigInteger(
						baseProdAssetTypeDetailForm.getProdSubAssetCode()));
		}
		else{
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdSubAssetCode(null);
		}
		
		if(baseProdAssetTypeDetailForm.getAssetTypeCustRptOrderNbr() != null &&
		  !"".equals(baseProdAssetTypeDetailForm.getAssetTypeCustRptOrderNbr())){
		  	
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setAssetTypeCustRptOrderNbr(
					new BigInteger(baseProdAssetTypeDetailForm.getAssetTypeCustRptOrderNbr()));
		}else{
			baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().setAssetTypeCustRptOrderNbr(null);
		}		
		
		//Controla frag para refresh na tela para linkar uma sub asset a uma asset class
		if ( baseProdAssetTypeDetailForm.getFindType().equals("true")){
			baseProdAssetTypeDetailFncVO.setRefreshAssetCode(true);
			baseProdAssetTypeDetailForm.setFindType("false");		
		}
		else{
			baseProdAssetTypeDetailFncVO.setRefreshAssetCode(false);
		}
		
	}

	/**
	 * Atualiza os atributos da Form com os atributos vindos do FncVO
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
	 *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		
		BaseProdAssetTypeDetailFncVO baseProdAssetTypeDetailFncVO =
			(BaseProdAssetTypeDetailFncVO) fncVO_;
		
		BaseProdAssetTypeDetailForm baseProdAssetTypeDetailForm =
			(BaseProdAssetTypeDetailForm) form_;

		if (baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeCode()!= null) {
			baseProdAssetTypeDetailForm.setProdAssetTypeCode(
				baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeCode().toString());
		} else {
			baseProdAssetTypeDetailForm.setProdAssetTypeCode(null);
		}

		//Setar Descrição
		baseProdAssetTypeDetailForm.setProdAssetTypeText(baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetTypeText());
		//Setar Usuário
		baseProdAssetTypeDetailForm.setLastUpdUserId(baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getLastUpdUserId());
		
		//Formatar a data para o Formato DD/MM/AAAA - HH/MM
		SimpleDateFormat formatDate = new SimpleDateFormat(Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM );
		Date formDate = new Date();
		formDate = baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getLastUpdDate();
		if ( formDate != null )
		{
			//Setar Data no formato correto
		  String date = formatDate.format( formDate );
		  baseProdAssetTypeDetailForm.setLastUpdDate( date );
		}
		
		//setar os domínios da Asset
		baseProdAssetTypeDetailForm.setProdAssetCodeDomain(	baseProdAssetTypeDetailFncVO.getProdAssetCodeDomain());
		
		if (baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetCode() != null) {
			baseProdAssetTypeDetailForm.setProdAssetCode(
				baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdAssetCode().toString());
		} else {
			baseProdAssetTypeDetailForm.setProdAssetCode(null);
		}

		//setar os domínios da Sub Asset
		baseProdAssetTypeDetailForm.setProdSubAssetCodeDomain(baseProdAssetTypeDetailFncVO.getProdSubAssetCodeDomain());

		if (baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode() != null) {
			baseProdAssetTypeDetailForm.setProdSubAssetCode(baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode().toString());
		} else {
			baseProdAssetTypeDetailForm.setProdSubAssetCode(null);
		}
		
		if (baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getAssetTypeCustRptOrderNbr() != null){
			baseProdAssetTypeDetailForm.setAssetTypeCustRptOrderNbr(baseProdAssetTypeDetailFncVO.getBaseTplProdAssetTypeEntity().getData().getAssetTypeCustRptOrderNbr().toString());
		}else{
			baseProdAssetTypeDetailForm.setAssetTypeCustRptOrderNbr(null);
		}		
	}

	/**
		 * Realiza o carregamento dos dados de família de Produtos
		 * @author leonardo.nakada
		 */
	protected void loadAssetDomain(BaseProdAssetTypeDetailFncVO detailFncVO) {
		TplProdAssetTypeDAO dao =
			ODSDAOFactory.getInstance().getTplProdAssetTypeDAO();
		DataSet resultDomain = dao.loadDomain();
		detailFncVO.setProdAssetCodeDomain(resultDomain);
	}

	/**
	* Realiza o carregamento dos dados de família de Produtos	
	*/
	protected void loadSubAssetDomain(BaseProdAssetTypeDetailFncVO detailFncVO) {
		TplProdAssetTypeDAO dao = ODSDAOFactory.getInstance().getTplProdAssetTypeDAO();
		DataSet resultDomain = dao.loadSubAsset();
		detailFncVO.setProdSubAssetCodeDomain(resultDomain);
	}

	/**
	   * Carrega todos os domínios utilizados pela transação
	   */
	protected void loadDomains(BaseProdAssetTypeDetailFncVO detailFncVO) {
		this.loadSubAssetDomain(detailFncVO);
		this.loadAssetDomain(detailFncVO);
		
	}

	/**
	 * Recupera um elemento de Qualificador de Produto, dado os critérios
	 */
	public void loadProdAssetType(BaseProdAssetTypeDetailFncVO detailFncVO_) {
		BaseTplProdAssetTypeDAO prodAssetTypeDAO = this.getDAO();
		BaseTplProdAssetTypeEntity tplProdAssetTypeEntity =
			(BaseTplProdAssetTypeEntity) prodAssetTypeDAO.find(
				detailFncVO_.getBaseTplProdAssetTypeEntity());
		detailFncVO_.setBaseTplProdAssetTypeEntity(tplProdAssetTypeEntity);
	}
	
	protected void loadAssetBySubAsset(BaseProdAssetTypeDetailFncVO detailFncVO){
		TplProdAssetTypeDAO dao = ODSDAOFactory.getInstance().getTplProdAssetTypeDAO();
		
		detailFncVO.getBaseTplProdAssetTypeEntity().getData().setProdAssetCode(
		      dao.loadAssetBySubAsset(detailFncVO.getBaseTplProdAssetTypeEntity().getData().getProdSubAssetCode()));
		
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
	 */

	protected abstract BaseTplProdAssetTypeDAO getDAO();

}
