package com.citibank.ods.modules.product.fundsubscription.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.util.TableTypeEnum;
import com.citibank.ods.common.util.Util;
import com.citibank.ods.entity.pl.valueobject.TplDataSubscptEntityVO;
import com.citibank.ods.modules.product.fundsubscription.form.valueobject.FundSubscriptionInvestorDataVO;
import com.citibank.ods.persistence.pl.dao.rdb.oracle.OracleTplDataSubscptDAO;

public class FundSubscriptionCheckEnrollmentAction  extends Action{

	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		String enrollment = request.getParameter("enrollment");
		String cpf = request.getParameter("cpf");

		if (!Util.isEmpty(cpf)){
			 boolean exists = false;
			OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
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
			
		}else if (!Util.isEmpty(enrollment)){
		
		 boolean exists = false;
		 boolean existInMov = false;
		if (!com.citibank.ods.common.util.Util.isEmpty(enrollment)){
			OracleTplDataSubscptDAO dao = new OracleTplDataSubscptDAO();
			TplDataSubscptEntityVO vo = dao.searchDataSubscrptByEnrollment(enrollment, TableTypeEnum.EFFECTIVE);
			TplDataSubscptEntityVO voMov = dao.searchDataSubscrptByEnrollment(enrollment, TableTypeEnum.MOVEMENT);
			if (vo != null || voMov != null)
				exists  =true;
			
			existInMov = voMov != null;
		}
		
		response.setContentType("application/json; charset=iso-8859-1");
		response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.println("{exists:"+exists+ ", isMov: "+existInMov+ " }");	
		
		out.close();
		
	}
	return null;
	}
}
