package com.citibank.newcpb.service;

import java.util.ArrayList;

import com.citibank.newcpb.form.CentralApprovalForm;
import com.citibank.newcpb.persistence.dao.TplPrvtCentrApprovalMovDAO;
import com.citibank.newcpb.vo.RegisterMovPendingToApproveVO;

public class CentralApprovalService extends CommonService{

	public void list(CentralApprovalForm form) {

		TplPrvtCentrApprovalMovDAO dao = new TplPrvtCentrApprovalMovDAO();
		ArrayList<RegisterMovPendingToApproveVO> resultList = dao.list(form.getModuleProcessTextFilter(), form.getUserIdFilter());

		form.setResultList(resultList);

		if (resultList == null || resultList.isEmpty()) {
			form.addMessage(CentralApprovalForm.C_NO_REGISTER_FOUND);
		}

	}

	public void load(CentralApprovalForm form) {
		getDomainLists(form);
		form.setResultList(null);
	}

	public void getDomainLists(CentralApprovalForm form) {
		// Obtém a instância do DAO necessário
		TplPrvtCentrApprovalMovDAO dao = new TplPrvtCentrApprovalMovDAO();

		// Realiza a consulta no DAO
		ArrayList<String> result = dao.loadDomain();

		// Atualiza o(s) atributo(s) do fncVO com o(s) dado(s) retornado(s)
		form.setModuleProcessDomainList(result);
	}
}
