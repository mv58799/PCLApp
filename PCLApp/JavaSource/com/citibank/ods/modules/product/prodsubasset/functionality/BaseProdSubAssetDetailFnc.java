/*
 * Created on 19/08/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.product.prodsubasset.functionality;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.Globals;
import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplProdSubAssetEntity;
import com.citibank.ods.modules.product.prodsubasset.form.BaseProdSubAssetDetailForm;
import com.citibank.ods.modules.product.prodsubasset.functionality.valueobject.BaseProdSubAssetDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.TplProdSubAssetDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

/**
 * @author rcoelho
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class BaseProdSubAssetDetailFnc extends BaseFnc {
	protected static final String C_PROD_SUBASSET_CODE = "Código da Sub Classe";

	protected static final String C_PROD_SUBASSET_TEXT =
		"Descrição da Sub Classe";

	protected static final String C_PROD_ASSET_TEXT = "Classe de Ativo";

	protected static final String C_SUB_ASSET_CLASS_RPT_ORDER_NBR = "Ordem";

	/**
	 * Atualiza os atributos do FncVO com os atributos vindos da Form
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFncVOFromForm(org.apache.struts.action.ActionForm,
	 *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		BaseProdSubAssetDetailFncVO baseProdSubAssetDetailFncVO =
			(BaseProdSubAssetDetailFncVO) fncVO_;
		BaseProdSubAssetDetailForm baseProdSubAssetDetailForm =
			(BaseProdSubAssetDetailForm) form_;

		if (baseProdSubAssetDetailForm.getProdSubAssetCode() != null
			&& !"".equals(baseProdSubAssetDetailForm.getProdSubAssetCode())) {
			baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setProdSubAssetCode(
					new BigInteger(
						baseProdSubAssetDetailForm.getProdSubAssetCode()));
		}

		//	Seta o código do asset caso o mesmo venha seleciado da consulta de aprovação centralizada
		if (baseProdSubAssetDetailForm.getSelectedCode() != null
			&& !baseProdSubAssetDetailForm.getSelectedCode().equals("")) {
			baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setProdSubAssetCode(
					new BigInteger(
						baseProdSubAssetDetailForm.getSelectedCode().substring(	1,8)));
		}

		if (baseProdSubAssetDetailForm.getProdSubAssetText() != null ){
			baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setProdSubAssetText(
				baseProdSubAssetDetailForm.getProdSubAssetText().trim());
		}

		if (baseProdSubAssetDetailForm.getProdAssetCode() != null
			&& !"".equals(baseProdSubAssetDetailForm.getProdAssetCode())) {
			baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setProdAssetCode(
					new BigInteger(
						baseProdSubAssetDetailForm.getProdAssetCode()));
		} else {
			baseProdSubAssetDetailFncVO
				.getBaseTplProdSubAssetEntity().getData().setProdAssetCode(null);
		}

		if (baseProdSubAssetDetailForm.getSubAssetClassRptOrderNbr() != null
			&& !"".equals(
				baseProdSubAssetDetailForm.getSubAssetClassRptOrderNbr())) {
			baseProdSubAssetDetailFncVO
				.getBaseTplProdSubAssetEntity().getData().setSubAssetClassRptOrderNbr(
					new BigInteger(
						baseProdSubAssetDetailForm.getSubAssetClassRptOrderNbr()));
		} else {
			baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setSubAssetClassRptOrderNbr(null);
		}

		if (baseProdSubAssetDetailForm.getIxCode() != null
				&& !"".equals(
					baseProdSubAssetDetailForm.getIxCode())) {
				baseProdSubAssetDetailFncVO
					.getBaseTplProdSubAssetEntity().getData().setIxCode(
							baseProdSubAssetDetailForm.getIxCode());
			} else {
				baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().setIxCode(null);;
			}
		
		
	}

	/**
	 * Atualiza os atributos da Form com os atributos vindos do FncVO
	 * @see com.citibank.ods.common.functionality.BaseFnc#updateFormFromFncVO(org.apache.struts.action.ActionForm,
	 *      com.citibank.ods.common.functionality.valueobject.BaseFncVO)
	 */
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		BaseProdSubAssetDetailFncVO baseProdSubAssetDetailFncVO =
			(BaseProdSubAssetDetailFncVO) fncVO_;
		BaseProdSubAssetDetailForm baseProdSubAssetDetailForm =
			(BaseProdSubAssetDetailForm) form_;

		if (baseProdSubAssetDetailFncVO
			.getBaseTplProdSubAssetEntity().getData().getProdSubAssetCode()!= null) {
			baseProdSubAssetDetailForm.setProdSubAssetCode(
				baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdSubAssetCode().toString());
		} else {
			baseProdSubAssetDetailForm.setProdSubAssetCode(null);
		}

		//Setar Descrição
		baseProdSubAssetDetailForm.setProdSubAssetText(
			baseProdSubAssetDetailFncVO
				.getBaseTplProdSubAssetEntity().getData().getProdSubAssetText());
		//Setar Usuário
		baseProdSubAssetDetailForm.setLastUpdUserId(
			baseProdSubAssetDetailFncVO	.getBaseTplProdSubAssetEntity().getData().getLastUpdUserId());
		//setar os domínios da Asset
		baseProdSubAssetDetailForm.setProdAssetCodeDomain(
			baseProdSubAssetDetailFncVO.getProdAssetCodeDomain());

		//setar ordem
		if (baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getSubAssetClassRptOrderNbr()!= null) {
			baseProdSubAssetDetailForm.setSubAssetClassRptOrderNbr(
				baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getSubAssetClassRptOrderNbr().toString());
		} else {
			baseProdSubAssetDetailForm.setSubAssetClassRptOrderNbr(null);
		}

		if (baseProdSubAssetDetailFncVO	.getBaseTplProdSubAssetEntity().getData().getProdAssetCode()!= null) {
			baseProdSubAssetDetailForm.setProdAssetCode(
				baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getProdAssetCode().toString());
		} else {
			baseProdSubAssetDetailForm.setProdAssetCode(null);
		}
		
		if (baseProdSubAssetDetailFncVO	.getBaseTplProdSubAssetEntity().getData().getIxCode()!= null) {
			baseProdSubAssetDetailForm.setIxCode(
				baseProdSubAssetDetailFncVO.getBaseTplProdSubAssetEntity().getData().getIxCode());
		} else {
			baseProdSubAssetDetailForm.setProdAssetCode(null);
		}
		

		//Formatar a data para o Formato DD/MM/AAAA - HH/MM
		SimpleDateFormat formatDate =
			new SimpleDateFormat(
				Globals.FuncionalityFormatKeys.C_FORMAT_DATETIME_DDMMYYYY_HHMM);
		Date formDate = new Date();
		formDate =
			baseProdSubAssetDetailFncVO	.getBaseTplProdSubAssetEntity().getData().getLastUpdDate();
		if (formDate != null) {
			//Setar Data no formato correto
			String date = formatDate.format(formDate);
			baseProdSubAssetDetailForm.setLastUpdDate(date);
		}

	}

	/**
		 * Realiza o carregamento dos dados de família de Produtos
		 * @author leonardo.nakada
		 */
	protected void loadAssetDomain(BaseProdSubAssetDetailFncVO detailFncVO) {
		TplProdSubAssetDAO dao =
			ODSDAOFactory.getInstance().getTplProdSubAssetDAO();
		DataSet resultDomain = dao.loadDomain();
		detailFncVO.setProdAssetCodeDomain(resultDomain);
	}

	/**
	   * Carrega todos os domínios utilizados pela transação
	   */
	protected void loadDomains(BaseProdSubAssetDetailFncVO detailFncVO) {
		this.loadAssetDomain(detailFncVO);
	}

	/**
	 * Recupera um elemento de Qualificador de Produto, dado os critérios
	 */
	public void loadProdSubAsset(BaseProdSubAssetDetailFncVO detailFncVO_) {
		BaseTplProdSubAssetDAO prodSubAssetDAO = this.getDAO();
		BaseTplProdSubAssetEntity tplProdSubAssetEntity =
			(BaseTplProdSubAssetEntity) prodSubAssetDAO.find(
				detailFncVO_.getBaseTplProdSubAssetEntity());
		detailFncVO_.setBaseTplProdSubAssetEntity(tplProdSubAssetEntity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.citibank.ods.common.functionality.BaseFnc#createFncVO()
	 */

	protected abstract BaseTplProdSubAssetDAO getDAO();

}
