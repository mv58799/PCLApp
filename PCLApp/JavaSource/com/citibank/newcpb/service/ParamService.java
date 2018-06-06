package com.citibank.newcpb.service;

import com.citibank.newcpb.persistence.dao.TplPrvtParamDAO;

public class ParamService extends CommonService {
	
	public String getParamValue(String paramKey) {
		TplPrvtParamDAO dao = new TplPrvtParamDAO();
		return dao.getParamValue(paramKey);
	}
}
