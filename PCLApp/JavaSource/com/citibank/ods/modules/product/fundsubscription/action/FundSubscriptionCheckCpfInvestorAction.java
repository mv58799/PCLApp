package com.citibank.ods.modules.product.fundsubscription.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionInvestorDataVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;

public class FundSubscriptionCheckCpfInvestorAction  extends Action{

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

 		 boolean exists = false;
		OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
		String cpf = request.getParameter("cpf");
		FundSubscriptionInvestorDataVO investor = null;
		if (!com.citibank.ods.common.util.Util.isEmpty(cpf)){
			investor = dao.defInvestorNameAndCode(Long.valueOf(cpf.replaceAll("\\D", "")));
			if (investor != null){
				exists = true;
			}
		}
		response.setContentType("application/json; charset=iso-8859-1");
		response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        if (exists){
        	out.println(String.format("{exists:%s,name:'%s',code:%s  }", exists,investor.getInvestorName(), investor.getInvestorCode() ));
        }else{
        	out.println(String.format("{exists:%s, name:'%s',code:%s }", exists, 0, "null"));	
        }
		out.close();
		
		return null;
	}
}
