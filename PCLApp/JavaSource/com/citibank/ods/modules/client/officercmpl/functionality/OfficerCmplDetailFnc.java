package com.citibank.ods.modules.client.officercmpl.functionality;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.ODSConstraintDecoder;
import com.citibank.ods.entity.pl.TplOfficerCmplEntity;
import com.citibank.ods.entity.pl.TplOfficerCmplMovementEntity;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplEntityVO;
import com.citibank.ods.entity.pl.valueobject.TplOfficerCmplMovementEntityVO;
import com.citibank.ods.modules.client.officercmpl.form.BaseOfficerCmplDetailForm;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.BaseOfficerCmplDetailFncVO;
import com.citibank.ods.modules.client.officercmpl.functionality.valueobject.OfficerCmplDetailFncVO;
import com.citibank.ods.persistence.pl.dao.BaseTplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplDAO;
import com.citibank.ods.persistence.pl.dao.TplOfficerCmplMovDAO;
import com.citibank.ods.persistence.pl.dao.factory.ODSDAOFactory;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.officercmpl.functionality;
 * @version 1.0
 * @author fabio.luppi.gil,Mar 5, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * Classe Fnc de detalhe dos dados complementares de Officer. Esta classe
 * concentra as regras de negócio deste sub-módulo.
 * 
 * </PRE>
 */

public class OfficerCmplDetailFnc extends BaseOfficerCmplDetailFnc implements
		ODSDetailFnc {
	/**
	 * Realiza as validações referentes à remoção de um registro
	 */
	public void validateDelete(BaseFncVO fncVO_) {
		// Validar Campos Obrigatórios
		OfficerCmplDetailFncVO officerCmplDetailFncVO = (OfficerCmplDetailFncVO) fncVO_;

		// Validar se existe um registro com este codigo na "Current",
		// caso os campos obrigatórios estejam presentes
		if (!fncVO_.hasErrors()) {
			if (!this.existsActive(officerCmplDetailFncVO)) {
				fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_PK_NOT_FOUND);
			}

			if (this.existsInMovement(officerCmplDetailFncVO)) {
				fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_IN_MOVEMENT);
			}
		}

	}

	/**
	 * Realiza as validações referentes à insercao de um registro
	 */
	public void validateInsert(BaseFncVO fncVO_) {
		// Validar Campos Obrigatórios
		OfficerCmplDetailFncVO officerCmplDetailFncVO = (OfficerCmplDetailFncVO) fncVO_;

		TplOfficerCmplEntityVO officerCmplEntityVO = (TplOfficerCmplEntityVO) officerCmplDetailFncVO
				.getTplOfficerCmplEntity().getData();
		if (officerCmplEntityVO.getOffcrNbr() == null
				|| officerCmplEntityVO.getOffcrNbr().intValue() == 0) {
			fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_MANDATORY_FIELD,
					OfficerCmplDetailFncVO.C_OFFCR_NBR_DESCRIPTION);
		}

		if (officerCmplEntityVO.getOffcrTypeCode() == null
				|| "".equals(officerCmplEntityVO.getOffcrTypeCode())) {
			fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_MANDATORY_FIELD,
					OfficerCmplDetailFncVO.C_OFFCR_TYPE_CODE_DESCRIPTION);
		}

		// caso os campos obrigatórios estejam presentes
		if (!fncVO_.hasErrors()) {
			//    Validar se já existe um registro com este codigo na "Current",
			if (this.existsActive(officerCmplDetailFncVO)) {
				fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_DUPLICATE_PK);
			}

			// Validar se já existe movimento com este codigo, caso os campos
			// obrigatórios estejam presentes
			if (this.existsInMovement(officerCmplDetailFncVO)) {
				fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_IN_MOVEMENT);
			}
		}
	}

	/**
	 * "Validas as regras de negócio para veriricar se a alteração pode ser
	 * efetuada. Em caso de informações invalidas, os erros são adicionados a
	 * respectiva propriedade do FncVO recebido como parâmetro."
	 * 
	 * @param officerCmplDetailFncVO_
	 *            FncVO da funcionalidade contendo os dados que serão
	 *            processados.
	 */
	public void validateUpdate(BaseFncVO fncVO_) {
		// Validar Campos Obrigatórios
		OfficerCmplDetailFncVO officerCmplDetailFncVO = (OfficerCmplDetailFncVO) fncVO_;
		TplOfficerCmplEntityVO officerCmplEntityVO = (TplOfficerCmplEntityVO) officerCmplDetailFncVO
				.getTplOfficerCmplEntity().getData();

		if (officerCmplEntityVO.getOffcrTypeCode() == null
				|| "".equals(officerCmplEntityVO.getOffcrTypeCode())) {
			fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_MANDATORY_FIELD,
					OfficerCmplDetailFncVO.C_OFFCR_TYPE_CODE_DESCRIPTION);
		}

		//  Validar se existe um registro com este codigo na "Current",
		// caso os campos obrigatórios estejam presentes
		if (!fncVO_.hasErrors()) {
			if (!this.existsActive(officerCmplDetailFncVO)) {
				fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_PK_NOT_FOUND);
			}

			if (this.existsInMovement(officerCmplDetailFncVO)) {
				fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_IN_MOVEMENT);
			}
		}

	}

	/**
	 * Retorna a instância de um FncVO
	 */
	public BaseFncVO createFncVO() {
		return new OfficerCmplDetailFncVO();
	}

	/**
	 * Recupera a instância de um DAO
	 */
	protected BaseTplOfficerCmplDAO getDAO() {
		ODSDAOFactory odsDAOFactory = ODSDAOFactory.getInstance();
		TplOfficerCmplDAO tplOfficerCmplDAO = odsDAOFactory
				.getTplOfficerCmplDAO();
		return tplOfficerCmplDAO;
	}

	/**
	 * Realiza a atualização de um registro no banco
	 * 
	 * @param officerCmplDetailFncVO_
	 */
	public void update(OfficerCmplDetailFncVO officerCmplDetailFncVO_) {
		this.validateUpdate(officerCmplDetailFncVO_);
		if (!officerCmplDetailFncVO_.hasErrors()) {
			this.createMovementRecord(officerCmplDetailFncVO_,
					TplOfficerCmplMovementEntity.C_OPERN_CODE_UPDATE);
		}
	}

	/**
	 * 
	 * "Cria o registro na tabela de movimento contendo as novas informações."
	 * 
	 * @param officerCmplDetailFncVO_
	 *            FncVO da funcionalidade contendo os dados que serão
	 *            processados.
	 */
	private void createMovementRecord(
			OfficerCmplDetailFncVO officerCmplDetailFncVO_, String opernCode_) {
		//entity atual que será utilizada para gerar registro do movimento.
		TplOfficerCmplEntity officerCmplEntity = (TplOfficerCmplEntity) officerCmplDetailFncVO_
				.getTplOfficerCmplEntity();
		//entity de movimento que será populada para ser inserida no bando de
		// dados.
		TplOfficerCmplMovementEntity officerCmplMovementEntity = new TplOfficerCmplMovementEntity();

		ODSDAOFactory odsDaoFactory = ODSDAOFactory.getInstance();
		TplOfficerCmplMovDAO tplOfficerCmplMovDAO = odsDaoFactory
				.getTplOfficerCmplMovDAO();

		officerCmplMovementEntity.getData().setLastUpdDate(new Date());
		officerCmplMovementEntity.getData().setLastUpdUserId(
				officerCmplDetailFncVO_.getLoggedUser().getUserID());
		if (officerCmplEntity.getData().getOffcrIntlNbr() != null) {
			officerCmplMovementEntity.getData().setOffcrIntlNbr(
					officerCmplEntity.getData().getOffcrIntlNbr());
		}
		officerCmplMovementEntity.getData().setOffcrNbr(
				officerCmplEntity.getData().getOffcrNbr());
		officerCmplMovementEntity.getData().setOffcrTypeCode(
				officerCmplEntity.getData().getOffcrTypeCode());

		((TplOfficerCmplMovementEntityVO) officerCmplMovementEntity.getData())
				.setOpernCode(opernCode_);

		tplOfficerCmplMovDAO.insert(officerCmplMovementEntity);

	}

	/**
	 * Realiza a atualização do Form a partir dos dados do FncVO
	 */
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		super.updateFormFromFncVO(form_, fncVO_);

		BaseOfficerCmplDetailForm form = (BaseOfficerCmplDetailForm) form_;
		BaseOfficerCmplDetailFncVO fncVO = (BaseOfficerCmplDetailFncVO) fncVO_;

		String recStatCode = (((TplOfficerCmplEntityVO) fncVO
				.getTplOfficerCmplEntity().getData()).getRecStatCode() != null ? ((TplOfficerCmplEntityVO) fncVO
				.getTplOfficerCmplEntity().getData()).getRecStatCode()
				.toString()
				: null);

		form.setRecStatCode(recStatCode);
	}

	/**
	 * Realiza a inserção de um registro, inserindo uma entrada na tabela de
	 * movimento para a aprovação
	 */
	public void insert(BaseFncVO fncVO_) {
		OfficerCmplDetailFncVO officerCmplDetailFncVO = (OfficerCmplDetailFncVO) fncVO_;
		if (!officerCmplDetailFncVO.hasErrors()) {
			if (this.existsActive(officerCmplDetailFncVO)) {
				validateUpdate(officerCmplDetailFncVO);
				createMovementRecord(officerCmplDetailFncVO,
						TplOfficerCmplMovementEntity.C_OPERN_CODE_UPDATE);
			} else {
				validateInsert(officerCmplDetailFncVO);
				createMovementRecord(officerCmplDetailFncVO,
						TplOfficerCmplMovementEntity.C_OPERN_CODE_INSERT);

			}
		}
	}

	/**
	 * Realiza a atualização de um registro. , inserindo uma entrada na tabela
	 * de movimento para a aprovação
	 */
	public void update(BaseFncVO fncVO_) {
	}

	/**
	 * Realiza a remoçao de um registro. , inserindo uma entrada na tabela de
	 * movimento para a aprovação
	 */
	public void delete(BaseFncVO fncVO_) {
		this.validateDelete(fncVO_);
		if (!fncVO_.hasErrors()) {
			OfficerCmplDetailFncVO officerCmplDetailFncVO = (OfficerCmplDetailFncVO) fncVO_;
			createMovementRecord(officerCmplDetailFncVO,
					TplOfficerCmplMovementEntity.C_OPERN_CODE_DELETE);
		}
	}

	/**
	 * Carregamentos iniciais da tela de detalhe
	 */
	public void loadForConsult(BaseFncVO fncVO_) {
		OfficerCmplDetailFncVO cmplDetailFncVO = (OfficerCmplDetailFncVO) fncVO_;
		super.loadOfficerTypeDomain(cmplDetailFncVO);
		super.loadOfficerCmpl(cmplDetailFncVO);
		super.loadOfficerNameText(cmplDetailFncVO);

		TplOfficerCmplEntityVO cmplEntityVO = (TplOfficerCmplEntityVO) cmplDetailFncVO
				.getTplOfficerCmplEntity().getData();
		String recStatCodeDecodified = ODSConstraintDecoder
				.decodeRecStatus(cmplEntityVO.getRecStatCode());
		cmplEntityVO.setRecStatCode(recStatCodeDecodified);
	}

	/**
	 * Carregamentos iniciais da tela de insert
	 */
	public void loadForInsert(BaseFncVO fncVO_) {
		OfficerCmplDetailFncVO officerCmplDetailFncVO = (OfficerCmplDetailFncVO) fncVO_;
		super.loadOfficerTypeDomain(officerCmplDetailFncVO);
		// 	Validar se já existe movimento com este codigo, caso os campos
		// obrigatórios estejam presentes

		if (this.existsInMovement((OfficerCmplDetailFncVO) fncVO_)) {
			fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_IN_MOVEMENT);
		} else {

			if (this.existsActive(officerCmplDetailFncVO)) {
				super.loadOfficerCmpl(officerCmplDetailFncVO);
			}
			super.loadOfficerTypeDomain(officerCmplDetailFncVO);
			super.loadOfficerNameText(officerCmplDetailFncVO);
		}

	}

	/**
	 * Carregamentos iniciais da tela de update
	 */
	public void loadForUpdate(BaseFncVO fncVO_) {

	}

	/**
	 * Carregamentos iniciais da tela de Delete
	 */
	public void loadForDelete(BaseFncVO fncVO_) {
		// 	Validar se já existe movimento com este codigo, caso os campos
		// obrigatórios estejam presentes
		if (this.existsInMovement((OfficerCmplDetailFncVO) fncVO_)) {
			fncVO_.addError(OfficerCmplDetailFncVO.C_ERROR_IN_MOVEMENT);
		} else {
			super.loadOfficerTypeDomain((OfficerCmplDetailFncVO) fncVO_);
			super.loadOfficerCmpl((OfficerCmplDetailFncVO) fncVO_);
			super.loadOfficerNameText((OfficerCmplDetailFncVO) fncVO_);
			OfficerCmplDetailFncVO cmplDetailFncVO = (OfficerCmplDetailFncVO) fncVO_;
			TplOfficerCmplEntityVO cmplEntityVO = (TplOfficerCmplEntityVO) cmplDetailFncVO
					.getTplOfficerCmplEntity().getData();
			String recStatCodeDecodified = ODSConstraintDecoder
					.decodeRecStatus(cmplEntityVO.getRecStatCode());
			cmplEntityVO.setRecStatCode(recStatCodeDecodified);
		}
	}

	/*
	 * Verifica se existe um registro na tabela de movimento com os critérios
	 * passados
	 */
	private boolean existsInMovement(OfficerCmplDetailFncVO catPrvtDetailFncVO_) {
		TplOfficerCmplMovDAO tplOfficerCmplMovDAO = ODSDAOFactory.getInstance()
				.getTplOfficerCmplMovDAO();

		TplOfficerCmplMovementEntity prvtMovEntity = new TplOfficerCmplMovementEntity();
		prvtMovEntity.getData().setOffcrNbr(
				catPrvtDetailFncVO_.getTplOfficerCmplEntity().getData()
						.getOffcrNbr());

		return tplOfficerCmplMovDAO.exists(prvtMovEntity);
	}

	/*
	 * Verifica se já existe um registro na tabela de "Current" com o código
	 * passado e o seu status é "Ativo"
	 */
	private boolean existsActive(OfficerCmplDetailFncVO catPrvtDetailFncVO_) {
		TplOfficerCmplDAO catPrvtDAO = ODSDAOFactory.getInstance()
				.getTplOfficerCmplDAO();
		return catPrvtDAO
				.existsActive((TplOfficerCmplEntity) catPrvtDetailFncVO_
						.getTplOfficerCmplEntity());
	}
}