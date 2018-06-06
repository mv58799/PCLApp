package com.citibank.ods.modules.product.membershipTerm.action;

import org.apache.commons.lang.NotImplementedException;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.modules.product.membershipTerm.functionality.MembershipTermListFnc;
import com.citibank.ods.modules.product.membershipTerm.functionality.valueobject.MembershipTermListFncVO;

public class MembershipTermListAction extends BaseODSAction {

	private static final String C_SCREEN_NAME = "MembershipTerm.MembershipTermList";

	private static final String C_FORWARD_KEY_INSERT = "MembershipTerm.MembershipTermDetail.Insert";
	private static final String C_FORWARD_KEY_UPDATE = "MembershipTerm.MembershipTermDetail.Update";

	@Override
	protected BaseFnc getFuncionality() {
		return new MembershipTermListFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO_, String mode_) {
		String forwardKey = getScreenName() + C_SPLITTER_CHAR + mode_;

		MembershipTermListFnc fnc = (MembershipTermListFnc) getFuncionality();

		if (fnc.verificarExisteContaCorrenteODS(fncVO_)) {
			if (fnc.verificarExisteTermoAdesao(fncVO_, TableTypeEnum.MOVEMENT)) {
				fncVO_.addError(MembershipTermListFncVO.C_ERROR_TERM_ADHESION_PENDENTE_APROVACAO);

			} else {
				if (fnc.verificarExisteTermoAdesao(fncVO_, TableTypeEnum.EFFECTIVE)) {
					forwardKey = C_FORWARD_KEY_UPDATE;
				} else {
					forwardKey = C_FORWARD_KEY_INSERT;
				}
			}

		} else {
			fncVO_.addError(MembershipTermListFncVO.C_ERROR_TERM_ADHESION_CONTA_CORRENTE_NAO_ENCONTRADA);
		}

		return forwardKey;
	}

	@Override
	protected String showAction(BaseFncVO fncVO_, String mode_) {
		clear(fncVO_);

		return getScreenName() + C_SPLITTER_CHAR + mode_;
	}

	@Override
	protected String specificActions(BaseFncVO fncVO_, String invokePath_) {
		return extraActions(fncVO_, invokePath_);
	}

	@Override
	protected String extraActions(BaseFncVO fncVO_, String invokePath_) {
		throw new NotImplementedException();
	}

	@Override
	public String getFncVOPublishName() {
		return MembershipTermListFncVO.class.getName();
	}

	private void clear(BaseFncVO fncVO_) {
		MembershipTermListFncVO fncVO = (MembershipTermListFncVO) fncVO_;
		fncVO.setCurAcctNbr(null);
	}

}
