package com.citibank.ods.modules.product.membershipTerm.action;

import org.apache.commons.lang.NotImplementedException;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.membershipTerm.functionality.MembershipTermDetailFnc;
import com.citibank.ods.modules.product.membershipTerm.functionality.valueobject.MembershipTermDetailFncVO;

public class MembershipTermDetailAction extends BaseODSAction {

	private static final String C_SCREEN_NAME = "MembershipTerm.MembershipTermDetail";

	@Override
	protected BaseFnc getFuncionality() {
		return new MembershipTermDetailFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO_, String mode_) {
		ODSDetailFnc fnc = (ODSDetailFnc) getFuncionality();

		String forwardKey = "";

		if (mode_.equals(C_MODE_UPDATE)) {
			fnc.validateUpdate(fncVO_);

			if (!fncVO_.hasErrors()) {
				fnc.update(fncVO_);
			}

		} else if (mode_.equals(C_MODE_INSERT)) {
			fnc.validateInsert(fncVO_);

			if (!fncVO_.hasErrors()) {
				fnc.insert(fncVO_);
			}

		} else if (mode_.equals(C_MODE_DELETE)) {
			fnc.delete(fncVO_);

		}

		if (!fncVO_.hasErrors()) {
			fncVO_.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
			forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
		} else {
			forwardKey = getScreenName() + C_SPLITTER_CHAR + mode_;
		}

		return forwardKey;
	}

	@Override
	protected String showAction(BaseFncVO fncVO_, String mode_) {
		ODSDetailFnc fnc = (ODSDetailFnc) getFuncionality();

		fncVO_.clearErrors();
		fncVO_.clearMessages();

		if (mode_.equals(C_MODE_CONSULT)) {
			fnc.loadForConsult(fncVO_);

		} else if (mode_.equals(C_MODE_UPDATE)) {
			fnc.loadForUpdate(fncVO_);

		} else if (mode_.equals(C_MODE_INSERT)) {
			fnc.loadForInsert(fncVO_);

		} else if (mode_.equals(C_MODE_DELETE)) {
			fnc.loadForDelete(fncVO_);

		}

		String forwardKey = "";

		if (fncVO_.hasErrors()) {
			forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
		} else {
			forwardKey = getScreenName() + C_SPLITTER_CHAR + mode_;
		}

		return forwardKey;
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
		return MembershipTermDetailFncVO.class.getName();
	}

}
