package com.citibank.newcpb.service;

import java.util.ArrayList;

import com.citibank.newcpb.bean.ResultTableBean;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnKeAnswDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnOccupationNatureDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnCivilStatDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnCnaeDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnCoTypeDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnCtryDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnEmitTypeDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnRoleCustDAO;
import com.citibank.newcpb.persistence.dao.TplPrvtDmnUfDAO;

public class ResultTableService {

	public ArrayList<ResultTableBean> listCivilState() {

		TplPrvtDmnCivilStatDAO dao = new TplPrvtDmnCivilStatDAO();
		ArrayList<ResultTableBean> resultList = dao.list();

		return resultList;
	}
	
	public ArrayList<ResultTableBean> listCountry() {

		TplPrvtDmnCtryDAO dao = new TplPrvtDmnCtryDAO();
		ArrayList<ResultTableBean> resultList = dao.list();

		return resultList;
	}
	
	public ArrayList<ResultTableBean> listUf() {

		TplPrvtDmnUfDAO dao = new TplPrvtDmnUfDAO();
		ArrayList<ResultTableBean> resultList = dao.list();

		return resultList;
	}
	
	public ArrayList<ResultTableBean> listEmitType() {

		TplPrvtDmnEmitTypeDAO dao = new TplPrvtDmnEmitTypeDAO();
		ArrayList<ResultTableBean> resultList = dao.list();

		return resultList;
	}
	
	public ArrayList<ResultTableBean> listOccupationNature() {

		TplPrvtDmnOccupationNatureDAO dao = new TplPrvtDmnOccupationNatureDAO();
		ArrayList<ResultTableBean> resultList = dao.list();

		return resultList;
	}
	
	public ArrayList<ResultTableBean> listActivityMain() {

		TplPrvtDmnCnaeDAO dao = new TplPrvtDmnCnaeDAO();
		ArrayList<ResultTableBean> resultList = dao.list();

		return resultList;
	}
	
	public ArrayList<ResultTableBean> listConstType() {

		TplPrvtDmnCoTypeDAO dao = new TplPrvtDmnCoTypeDAO();
		ArrayList<ResultTableBean> resultList = dao.list();

		return resultList;
	}
	
	public ArrayList<ResultTableBean> listRoleCust() {

		TplPrvtDmnRoleCustDAO dao = new TplPrvtDmnRoleCustDAO();
		ArrayList<ResultTableBean> resultList = dao.list();

		return resultList;
	}
	
	public ArrayList<ResultTableBean> listDmnKeAnsw(String questTypeCode ) {
		TplPrvtDmnKeAnswDAO dao = new TplPrvtDmnKeAnswDAO();
		ArrayList<ResultTableBean> resultList = dao.list(questTypeCode);

		return resultList;
	}
	
	
	
	
	
}
