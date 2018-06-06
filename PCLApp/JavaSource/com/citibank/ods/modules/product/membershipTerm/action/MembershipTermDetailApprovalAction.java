package com.citibank.ods.modules.product.membershipTerm.action;

import com.citibank.ods.common.action.BaseODSAction;
import com.citibank.ods.common.functionality.BaseFnc;
import com.citibank.ods.common.functionality.ODSMovementDetailFnc;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.modules.product.membershipTerm.functionality.MembershipTermDetailApprovalFnc;
import com.citibank.ods.modules.product.membershipTerm.functionality.valueobject.MembershipTermDetailFncVO;

public class MembershipTermDetailApprovalAction extends BaseODSAction {

	private static final String C_SCREEN_NAME = "MembershipTerm.MembershipTermDetail";

	@Override
	protected BaseFnc getFuncionality() {
		return new MembershipTermDetailApprovalFnc();
	}

	@Override
	protected String getScreenName() {
		return C_SCREEN_NAME;
	}

	@Override
	protected String executeAction(BaseFncVO fncVO_, String mode_) {
		ODSMovementDetailFnc fnc = (ODSMovementDetailFnc) getFuncionality();

		String forwardKey = "";

		if (mode_.equals(C_MODE_UPDATE)) {
			fnc.update(fncVO_);
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
		ODSMovementDetailFnc fnc = (ODSMovementDetailFnc) getFuncionality();

		fncVO_.clearErrors();
		fncVO_.clearMessages();

		if (mode_.equals(C_MODE_UPDATE)) {
			fnc.loadForUpdate(fncVO_);

		} else if (mode_.equals(C_MODE_APPROVAL)) {
			fnc.loadForApprove(fncVO_);

		} else if (mode_.equals(C_MODE_CONSULT)) {
			fnc.loadForConsult(fncVO_);

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
		String[] splittedInvokePath = invokePath_.split("\\.");

		ODSMovementDetailFnc fnc = (ODSMovementDetailFnc) getFuncionality();

		String forwardKey = "";

		if (splittedInvokePath[2].equals(C_MODE_APPROVAL) && splittedInvokePath[3].equals(C_ACTION_REPROVE)) {
			fnc.reprove(fncVO_);

			if (!fncVO_.hasErrors()) {
				fncVO_.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
				forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
			} else {
				forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[2];
			}
		} else if (splittedInvokePath[2].equals(C_MODE_APPROVAL) && splittedInvokePath[3].equals(C_ACTION_APPROVE)) {
			fnc.approve(fncVO_);

			if (!fncVO_.hasErrors()) {
				fncVO_.addMessage(BaseODSFncVO.C_MESSAGE_SUCESS);
				forwardKey = getScreenName() + C_SPLITTER_CHAR + C_ACTION_BACK;
			} else {
				forwardKey = getScreenName() + C_SPLITTER_CHAR + splittedInvokePath[2];
			}
		} else {
			forwardKey = extraActions(fncVO_, invokePath_);
		}

		return forwardKey;
	}

	@Override
	public String getFncVOPublishName() {
		return MembershipTermDetailFncVO.class.getName();
	}

}
