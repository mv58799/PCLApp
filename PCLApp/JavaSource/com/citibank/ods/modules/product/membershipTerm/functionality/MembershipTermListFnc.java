package com.citibank.ods.modules.product.membershipTerm.functionality;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.valueobject.TplTermAdhesionEntityVO;
import com.citibank.ods.modules.product.membershipTerm.form.MembershipTermListForm;
import com.citibank.ods.modules.product.membershipTerm.functionality.valueobject.MembershipTermListFncVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplTermAdhesionDAO;

public class MembershipTermListFnc extends BaseFnc {

	/**
	 * Verifica se a conta corrente informada existe na ODS.
	 * 
	 * @param fncVO_
	 * @return <code>true</code>, se a conta corrente existe na ODS.<br/><code>false</code>, se a conta corrente não existe na ODS.
	 */
	public Boolean verificarExisteContaCorrenteODS(BaseFncVO fncVO_) {
		MembershipTermListFncVO fncVO = (MembershipTermListFncVO) fncVO_;

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		BaseTo3ProductAccountEntity productAccount = dao.getProductAccountByCurAcctNbr(fncVO.getCurAcctNbr());

		return productAccount != null;
	}

	/**
	 * Verifica se existe um termo de adesao para a conta corrente informada.
	 * 
	 * @param fncVO_
	 * @return <code>true</code>, se existe um termo de adesão para a conta corrente.<br/><code>false</code>, se não existe um termo de adesão para a conta corrente.
	 */
	public Boolean verificarExisteTermoAdesao(BaseFncVO fncVO_, TableTypeEnum tableType) {
		MembershipTermListFncVO fncVO = (MembershipTermListFncVO) fncVO_;

		OracleTplTermAdhesionDAO dao = new OracleTplTermAdhesionDAO();

		List<TplTermAdhesionEntityVO> termos = dao.findByCurAcctNbr(fncVO.getCurAcctNbr(), tableType);

		return !termos.isEmpty();
	}

	@Override
	public void updateFncVOFromForm(ActionForm form_, BaseFncVO fncVO_) {
		MembershipTermListForm form = (MembershipTermListForm) form_;
		MembershipTermListFncVO fncVO = (MembershipTermListFncVO) fncVO_;

		try {
			fncVO.setCurAcctNbr(Long.parseLong(form.getCurAcctNbr()));
		} catch (NumberFormatException e) { }
	}

	@Override
	public void updateFormFromFncVO(ActionForm form_, BaseFncVO fncVO_) {
		MembershipTermListForm form = (MembershipTermListForm) form_;
		MembershipTermListFncVO fncVO = (MembershipTermListFncVO) fncVO_;

		form.setCurAcctNbr(fncVO.getCurAcctNbr() == null ? null : String.valueOf(fncVO.getCurAcctNbr()));
	}

	@Override
	public BaseFncVO createFncVO() {
		return new MembershipTermListFncVO();
	}

}
